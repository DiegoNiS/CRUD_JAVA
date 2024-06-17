package javawithmysql.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javawithmysql.Model.Granja;
import javawithmysql.Model.GranjaDAO;

public class GranjaController implements Initializable {

    @FXML
    private TableView<Granja> tableView;
    @FXML
    private TableColumn<Granja, String> GraCod;
    @FXML
    private TableColumn<Granja, String> GraNom;
    @FXML
    private TableColumn<Granja, String> GraDir;
    @FXML
    private TableColumn<Granja, String> GraNot;
    @FXML
    private TableColumn<Granja, String> GraEstReg;
    @FXML
    private TableColumn<Granja, Integer> EmpCod;
    @FXML
    private TextField GraCodF;
    @FXML
    private TextField GraNomF;
    @FXML
    private TextField GraDirF;
    @FXML
    private TextField GraNotF;
    @FXML
    private TextField GraEstRegF;
    @FXML
    private TextField EmpCodF;

    private final GranjaDAO granjaDAO = new GranjaDAO();
    private ObservableList<Granja> granjas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraCod.setCellValueFactory(new PropertyValueFactory<>("graCod"));
        GraNom.setCellValueFactory(new PropertyValueFactory<>("graNom"));
        GraDir.setCellValueFactory(new PropertyValueFactory<>("graDir"));
        GraNot.setCellValueFactory(new PropertyValueFactory<>("graNot"));
        GraEstReg.setCellValueFactory(new PropertyValueFactory<>("graEstReg"));
        EmpCod.setCellValueFactory(new PropertyValueFactory<>("empCod"));

        loadGranjas();
        
    }    
    
    private void loadGranjas() {
        try {
            List<Granja> granjasList = granjaDAO.getAll();
            granjas = FXCollections.observableArrayList(granjasList);
            tableView.setItems(granjas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleInsert() {
        System.out.println("1");
        String graCod = GraCodF.getText();
        String graNom = GraNomF.getText();
        String graDir = GraDirF.getText();
        String graNot = GraNotF.getText();
        String graEstReg = GraEstRegF.getText();
        String empCod = EmpCodF.getText();
        System.out.println("si");
        
        if (!graCod.isEmpty() && !graNom.isEmpty() && !graDir.isEmpty() && !graNot.isEmpty() && !graEstReg.isEmpty() && !empCod.isEmpty()) {
            Granja granja = new Granja(graCod, graNom, graDir, graNot, graEstReg, Integer.parseInt(empCod));
            System.out.println(granja);
            try {
                granjaDAO.insert(granja);
                System.out.println("inserto bien");
                loadGranjas();
                System.out.println("recargo bien");
                clearFields();
            } catch (SQLException e) {
                System.out.println("no");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleUpdate() {
        Granja selectedGranja = tableView.getSelectionModel().getSelectedItem();
        if (selectedGranja != null) {
            selectedGranja.setGraCod(GraCodF.getText());
            selectedGranja.setGraNom(GraNomF.getText());
            selectedGranja.setGraDir(GraDirF.getText());
            selectedGranja.setGraNot(GraNotF.getText());
            selectedGranja.setGraEstReg(GraEstRegF.getText());
            selectedGranja.setEmpCod(Integer.parseInt(EmpCodF.getText()));
            
            try {
                granjaDAO.update(selectedGranja);
                loadGranjas();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDelete() {
        Granja selectedGranja = tableView.getSelectionModel().getSelectedItem();
        if (selectedGranja != null) {
            try {
                granjaDAO.delete(selectedGranja.getGraCod());
                loadGranjas();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        GraCodF.clear();
        GraNomF.clear();
        GraDirF.clear();
        GraNotF.clear();
        GraEstRegF.clear();
        EmpCodF.clear();
    }
    
    @FXML
    private void cerrar() {
        Platform.exit();
    }
}
