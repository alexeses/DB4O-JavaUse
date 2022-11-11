package com.github.alexeses.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.github.alexeses.pojo.Persona;

import java.util.ArrayList;

/**
 * Esta clase es la encargada de realizar las operaciones de acceso a la base de datos.
 * @author Alex Gheorghe
 * @version 1.0
 * @date 2022-11-11
 */

public class Access {

    static final String PATH_DB = "src/main/resources/PersonasDB.yap";
    ObjectContainer db;

    public Access() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), PATH_DB);
    }

    public void cerrarDB() {
        db.close();
        System.out.println("[DEBUG] DB cerrada correctamente");
    }

    public void insertar(Persona persona) {
        db.store(persona);
    }


    /**
     * Consulta todas las personas de la base de datos
     * @return ArrayList<Persona>
     */
    public ArrayList<Persona> consularTodas(){
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

        ObjectSet<Persona> setPersonas = db.queryByExample(Persona.class);

        listaPersonas.addAll(setPersonas);

        return listaPersonas;
    }
    /**
     * Consulta por nombre
     * @param nombre
     * @return ArrayList<Persona>
     */

    public ArrayList<Persona> consultaPorNombre(String nombre) {

        Persona personaBusqueda = new Persona();

        personaBusqueda.setNombre(nombre);

        ObjectSet<Persona> setPersonas = db.queryByExample(personaBusqueda);

        return new ArrayList<>(setPersonas);
    }

    /**
     * Elimina todas las personas con el nombre y edad indicados
     * @param c
     * @return ArrayList<Persona>
     */

    public ArrayList<Persona> consultaPorCiudad(String c) {

            Persona personaBusqueda = new Persona();

            personaBusqueda.setCiudad(c);

            ObjectSet<Persona> setPersonas = db.queryByExample(personaBusqueda);

            return new ArrayList<>(setPersonas);
    }

    /**
     * Elimina todas las personas con el nombre y edad indicados
     * @param n
     * @param e
     */

    public void eliminarNombreEdad(String n, int e) {

            int eliminados = 0;

            Persona personaBusqueda = new Persona();

            personaBusqueda.setNombre(n);
            personaBusqueda.setEdad(e);

            ObjectSet<Persona> setPersonas = db.queryByExample(personaBusqueda);

            for (Persona persona : setPersonas) {
                eliminados++;
                db.delete(persona);
                System.out.println("[DEBUG] Se han eliminado a: " + eliminados + " personas");
            }

    }

    /**
     * Modifica la ciudad de una persona que tenga el nombre pasado como parámetro
     * @param n
     * @param c
     */
    public void modificarCiudadxNombre(String n, String c) {

                int modificados = 0;

                Persona personaBusqueda = new Persona();

                personaBusqueda.setNombre(n);

                ObjectSet<Persona> setPersonas = db.queryByExample(personaBusqueda);

                for (Persona persona : setPersonas) {
                    modificados++;
                    persona.setCiudad(c);
                    db.store(persona);
                    System.out.println("[DEBUG] Se han modificado a: " + modificados + " personas");
                }
    }
}
