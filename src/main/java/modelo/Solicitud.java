package modelo;

import java.util.List;
/**
 * Representa la entidad de datos enviada por el cliente.
 * Contiene las listas de cantidades y nombres de entidades necesarias para el taller.
 */
public class Solicitud {
    /**
     * Lista de valores numéricos que representan las cantidades iniciales requeridas.
     */
    private List<Integer> cantidadesIniciales;

    /**
     * Lista de cadenas de texto que representan los nombres de las entidades involucradas.
     */
    private List<String> nombreEntidades;

    /**
     * Constructor parametrizado de la clase Solicitud.
     * Crea una instancia inicializando las cantidades y los nombres de las entidades.
     *
     * @param cantidadesIniciales Lista de valores enteros con las cantidades iniciales.
     * @param nombreEntidades     Lista de cadenas de texto con los nombres de las entidades.
     */
    public Solicitud(List<Integer> cantidadesIniciales,List<String> nombreEntidades){
        this.cantidadesIniciales=cantidadesIniciales;
        this.nombreEntidades=nombreEntidades;
    }

    /**
     * Constructor por defecto de la clase Solicitud.
     * Crea una instancia vacía sin inicializar las listas.
     */
    public Solicitud() {

    }

    /**
     * Obtiene la lista de cantidades iniciales de la solicitud.
     *
     * @return Una {@link List} de enteros con las cantidades iniciales.
     */
    public List<Integer> getCantidadesIniciales(){
        return this.cantidadesIniciales;
    }

    /**
     * Establece la lista de cantidades iniciales para la solicitud.
     *
     * @param cantidadesIniciales La lista de enteros que se desea asignar como cantidades iniciales.
     */
    public void setCantidadesIniciales(List<Integer> cantidadesIniciales){
        this.cantidadesIniciales=cantidadesIniciales;
    }

    /**
     * Obtiene la lista de los nombres de las entidades de la solicitud.
     *
     * @return Una {@link List} de cadenas de texto con los nombres de las entidades.
     */
    public List<String> getNombreEntidades(){
        return this.nombreEntidades;
    }

    /**
     * Establece la lista de los nombres de las entidades para la solicitud.
     *
     * @param nombreEntidades La lista de cadenas de texto que se desea asignar como nombres de entidades.
     */
    public void setNombreEntidades(List<String> nombreEntidades){
        this.nombreEntidades=nombreEntidades;
    }
}
