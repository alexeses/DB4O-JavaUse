package com.github.alexeses;

import com.github.alexeses.db.Access;
import com.github.alexeses.pojo.Persona;

import java.util.ArrayList;

public class Main {

    static Access accesoDB = new Access();

    public static void main(String[] args) {

        resetFileDB();
        addPersonas();
        consultarPersonas();

        consultaPorNombre("Juan");
        consultarPorCiudad("Madrid");
        eliminarNombreEdad("Juan", 21);
        consultaPorNombre("Juan");
        modificarCiudadxNombre("Juan", "Teruel");

        accesoDB.cerrarDB();
    }

    /**
     * Este método elimina todas las personas de la base de datos
     */
    private static void resetFileDB() {
        java.io.File fichero = new java.io.File("src/main/resources/PersonasDB.yap");
        if (fichero.delete()) {
            System.out.println("[DEBUG] Fichero borrado correctamente");
        } else {
            System.out.println("[DEBUG] No se ha podido borrar el fichero");
        }
    }
    private static void modificarCiudadxNombre(String n, String c) {
        accesoDB.modificarCiudadxNombre(n, c);
    }

    private static void eliminarNombreEdad(String n, int e) {

        accesoDB.eliminarNombreEdad(n, e);

    }

    /**
     * Este método anade personas a la base de datos para poder realizar las pruebas
     */
    private static void addPersonas() {
        Persona personas[] = {new Persona("Juan", "Guadalajara", 21),
                new Persona("Ana", "Madrid", 23),
                new Persona("Luis", "Granada", 26),
                new Persona("Pedro", "Asturias", 31),
                new Persona("Juan", "Guadalajara", 41),
                new Persona("Ana", "Madrid", 53),
                new Persona("Luis", "Granada", 26),
                new Persona("Pedro", "Asturias", 31),
                new Persona("Juan", "Guadalajara", 41),
                new Persona("Ana", "Madrid", 53),
                new Persona("Luis", "Granada", 26),
                new Persona("Pedro", "Asturias", 31)};

        for (Persona persona : personas) {
            accesoDB.insertar(persona);
        }

        System.out.println("[DEBUG] Personas insertadas correctamente");
    }

    /**
     * Este método consulta todas las personas que su ciudad sea el parámetro de entrada
     * @param c
     */
    private static void consultarPorCiudad(String c) {
        ArrayList<Persona> listaPersonas = accesoDB.consultaPorCiudad(c);

        System.out.println("Personas de la ciudad de " + c + ":");

        if (listaPersonas.isEmpty()) {
            System.out.println("No hay personas de esa ciudad");
        } else {
            for (Persona p : listaPersonas) {
                System.out.println(p);
            }
        }
    }

    /**
     * Este método consulta todas las personas que su nombre sea el parámetro de entrada
     * @param n
     */
    private static void consultaPorNombre(String n) {

        ArrayList<Persona> listaPersonas = accesoDB.consultaPorNombre(n);

        if (listaPersonas.isEmpty()) {
            System.out.println("No hay personas con el nombre " + n);
        } else {
            System.out.println("Personas con el nombre " + n);
            for (Persona p : listaPersonas) {
                System.out.println(p);
            }
        }

    }

    /**
     * Este método consulta todas las personas de la base de datos
     */
    private static void consultarPersonas() {
        ArrayList<Persona> listaPersonas = accesoDB.consularTodas();

        if (listaPersonas.isEmpty()) {
            System.out.println("No se han encontrado datos");
        }else {
            System.out.println("Se han encontrado " + listaPersonas.size() + " personas");
            for (Persona persona : listaPersonas) {
                System.out.println(persona);
            }
        }
    }

}