package modelo;

import java.util.List;

public class Solicitud {
    private List<Integer> cantidadesIniciales;
    private List<String> nombreEntidades;
    public Solicitud(List<Integer> cantidadesIniciales,List<String> nombreEntidades){
        this.cantidadesIniciales=cantidadesIniciales;
        this.nombreEntidades=nombreEntidades;
    }
    public List<Integer> getCantidadesIniciales(){
        return this.cantidadesIniciales;
    }
    public void setCantidadesIniciales(List<Integer> cantidadesIniciales){
        this.cantidadesIniciales=cantidadesIniciales;
    }
    public List<String> getNombreEntidades(){
        return this.nombreEntidades;
    }
    public void setNombreEntidades(List<String> nombreEntidades){
        this.nombreEntidades=nombreEntidades;
    }
}
