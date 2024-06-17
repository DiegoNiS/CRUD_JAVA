package javawithmysql.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javawithmysql.util.DataBaseConection;

public class GranjaDAO {
    private Connection connection;

    public GranjaDAO() {
        try{
            connection = DataBaseConection.getConnection();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    // Método para insertar una granja
    public void insert(Granja granja) throws SQLException {
        String sql = "INSERT INTO granja (GraCod, GraNom, GraDir, GraNot, GraEstReg, EmpCod) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, granja.getGraCod());
            stmt.setString(2, granja.getGraNom());
            stmt.setString(3, granja.getGraDir());
            stmt.setString(4, granja.getGraNot());
            stmt.setString(5, granja.getGraEstReg());
            stmt.setInt(6, granja.getEmpCod());
            stmt.executeUpdate();
        }
    }

    // Método para actualizar una granja
    public void update(Granja granja) throws SQLException {
        String sql = "UPDATE granja SET GraNom=?, GraDir=?, GraNot=?, GraEstReg=?, EmpCod=? WHERE GraCod=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, granja.getGraNom());
            stmt.setString(2, granja.getGraDir());
            stmt.setString(3, granja.getGraNot());
            stmt.setString(4, granja.getGraEstReg());
            stmt.setInt(5, granja.getEmpCod());
            stmt.setString(6, granja.getGraCod());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar una granja por su código
    public void delete(String graCod) throws SQLException {
        String sql = "DELETE FROM granja WHERE GraCod=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, graCod);
            stmt.executeUpdate();
        }
    }

    // Método para obtener todas las granjas
    public List<Granja> getAll() throws SQLException {
        List<Granja> granjas = new ArrayList<>();
        String sql = "SELECT * FROM granja";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Granja granja = new Granja(
                        rs.getString("GraCod"),
                        rs.getString("GraNom"),
                        rs.getString("GraDir"),
                        rs.getString("GraNot"),
                        rs.getString("GraEstReg"),
                        rs.getInt("EmpCod")
                );
                granjas.add(granja);
            }
        }
        return granjas;
    }
}
