package Presentacion;

import Servicio.IServicioPeliculas;
import Servicio.ServicioPeliculasArchivo;
import Servicio.ServicioPeliculasLista;
import dominio.Pelicula;

import java.util.Scanner;

public class CatalogoPeliculasApp {
    public static void main(String[] args) {

        var salir  = false;
        var consola = new Scanner(System.in);
        //agregamos la implementacion del servicio
        //IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivo();
        while(!salir){
            try{
                mostrarMenu();
                ejecutarOpciones(consola, servicioPeliculas);
            } catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            System.out.println();
        }

    }
    private static void mostrarMenu(){
        System.out.print("""
               ***Catalogo de Peliculas***
               1. Agregar Pelicula
               2. Listar peliculas
               3. Buscar pelicula
               4. Salir
               Elige una opcion: 
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola,
                                            IServicioPeliculas servicioPeliculas){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("Introduce el nombre de la pelicula: ");
                var nombrePelicula = consola.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
            }
            case 2 -> servicioPeliculas.listarPeliculas();
            case 3 ->{
                System.out.println("¿Que pelicula buscas? ");
                var buscarPelicula = consola.nextLine();
                servicioPeliculas.buscarPelicula(
                        new Pelicula(buscarPelicula));
            }
            default -> {
                System.out.println("Hasta pronto! "+ opcion);
                salir = true;
            }
        }
        return salir;
    }
}