package javawithmysql.Model;

public class Empresa {
    private int EmpCod; 
    private String EmpNom;
    private String detalle;

    public Empresa(int EmpCod, String EmpNom, String detalle) {
        this.EmpCod = EmpCod;
        this.EmpNom = EmpNom;
        this.detalle = detalle;
    }
    
    public int getEmpCod() {
        return EmpCod;
    }

    public void setEmpCod(int EmpCod) {
        this.EmpCod = EmpCod;
    }

    public String getEmpNom() {
        return EmpNom;
    }

    public void setEmpNom(String EmpNom) {
        this.EmpNom = EmpNom;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String Detalle) {
        this.detalle = Detalle;
    }

    @Override
    public String toString() {
        return "Empresa{" + "EmpCod=" + EmpCod + ", EmpNom=" + EmpNom + ", DelDetCod=" + detalle + '}';
    }
    
}
