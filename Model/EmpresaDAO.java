package javawithmysql.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javawithmysql.util.DataBaseConection;

public class EmpresaDAO {
    public List<Empresa> getAll() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();
        String query = "SELECT * FROM empresa";
        
        try (Connection connection = DataBaseConection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
             
            while (resultSet.next()) {
                int id = resultSet.getInt("EmpCod");
                String nombre = resultSet.getString("EmpNom");
                String detalle = resultSet.getString("DelPetCod");                
                // Obtener más columnas según tu esquema de tabla
                
                empresas.add(new Empresa(id, nombre, detalle));
            }
        }
        
        return empresas;
    }
    
    public void insert(Empresa empresa) throws SQLException {
        String query = "INSERT INTO empresa (EmpNom, DelPetCod) VALUES (?, ?)";
        
        try (Connection connection = DataBaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, empresa.getEmpNom());
            preparedStatement.setString(2, empresa.getDetalle());
            preparedStatement.executeUpdate();
        }
    }
    
    public void update(Empresa empresa) throws SQLException {
        String query = "UPDATE empresa SET EmpNom = ?, DelPetCod = ? WHERE EmpCod = ?";
        
        try (Connection connection = DataBaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, empresa.getEmpNom());
            preparedStatement.setString(2, empresa.getDetalle());
            preparedStatement.setInt(3, empresa.getEmpCod());
            preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM empresa WHERE EmpCod = ?";
        
        try (Connection connection = DataBaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
