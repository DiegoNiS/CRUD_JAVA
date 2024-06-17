package javawithmysql.Model;

public class Granja {
    private String graCod;
    private String graNom;
    private String graDir;
    private String graNot;
    private String graEstReg;
    private int empCod;

    public Granja(String graCod, String graNom, String graDir, String graNot, String graEstReg, int empCod) {
        this.graCod = graCod;
        this.graNom = graNom;
        this.graDir = graDir;
        this.graNot = graNot;
        this.graEstReg = graEstReg;
        this.empCod = empCod;
    }

    public String getGraCod() {
        return graCod;
    }

    public void setGraCod(String graCod) {
        this.graCod = graCod;
    }

    public String getGraNom() {
        return graNom;
    }

    public void setGraNom(String graNom) {
        this.graNom = graNom;
    }

    public String getGraDir() {
        return graDir;
    }

    public void setGraDir(String graDir) {
        this.graDir = graDir;
    }

    public String getGraNot() {
        return graNot;
    }

    public void setGraNot(String graNot) {
        this.graNot = graNot;
    }

    public String getGraEstReg() {
        return graEstReg;
    }

    public void setGraEstReg(String graEstReg) {
        this.graEstReg = graEstReg;
    }

    public int getEmpCod() {
        return empCod;
    }

    public void setEmpCod(int empCod) {
        this.empCod = empCod;
    }

    @Override
    public String toString() {
        return "Granja{" +
                "graCod='" + graCod + '\'' +
                ", graNom='" + graNom + '\'' +
                ", graDir='" + graDir + '\'' +
                ", graNot='" + graNot + '\'' +
                ", graEstReg='" + graEstReg + '\'' +
                ", empCod=" + empCod +
                '}';
    }
}
