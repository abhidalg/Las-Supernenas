package modelo;

import java.util.List;
/**
 * Representa la entidad de datos enviada por el cliente.
 * Contiene las listas de cantidades y nombres de entidades necesarias para el taller.
 */
public class Solicitud {
    private List<Integer> cantidadesIniciales;
    private List<String> nombreEntidades;
    public Solicitud(List<Integer> cantidadesIniciales,List<String> nombreEntidades){
        this.cantidadesIniciales=cantidadesIniciales;
        this.nombreEntidades=nombreEntidades;
    }

    public Solicitud() {

    }

    /**
     * @return Lista de valores enteros con las cantidades iniciales.
     */
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
