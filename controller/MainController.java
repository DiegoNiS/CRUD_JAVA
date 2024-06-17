package javawithmysql.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javawithmysql.Model.Empresa;
import javawithmysql.Model.EmpresaDAO;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {
    @FXML
    private TableView<Empresa> tableView;
    @FXML
    private TableColumn<Empresa, Integer> EmpCod;
    @FXML
    private TableColumn<Empresa, String> EmpNom;
    @FXML
    private TableColumn<Empresa, String> detalle;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;

    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    private ObservableList<Empresa> empresas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmpCod.setCellValueFactory(new PropertyValueFactory<>("empCod"));
        EmpNom.setCellValueFactory(new PropertyValueFactory<>("empNom"));
        detalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));

        loadEmpresas();
        
    }    
    
    private void loadEmpresas() {
        try {
            List<Empresa> empresaList = empresaDAO.getAll();
            empresas = FXCollections.observableArrayList(empresaList);
            tableView.setItems(empresas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleInsert() {
        String nombre = nombreField.getText();
        String detalle = apellidoField.getText();
        
        if (!nombre.isEmpty() && !detalle.isEmpty()) {
            Empresa empresa = new Empresa(0, nombre, detalle);
            try {
                empresaDAO.insert(empresa);
                loadEmpresas();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleUpdate() {
        Empresa selectedEmpresa = tableView.getSelectionModel().getSelectedItem();
        if (selectedEmpresa != null) {
            selectedEmpresa.setEmpNom(nombreField.getText());
            selectedEmpresa.setDetalle(apellidoField.getText());
            
            try {
                empresaDAO.update(selectedEmpresa);
                loadEmpresas();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDelete() {
        Empresa selectedEmpresa = tableView.getSelectionModel().getSelectedItem();
        if (selectedEmpresa != null) {
            try {
                empresaDAO.delete(selectedEmpresa.getEmpCod());
                loadEmpresas();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        nombreField.clear();
        apellidoField.clear();
    }
    
    @FXML
    private void cerrar() {
        Platform.exit();
    }
}
