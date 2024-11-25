package Servicio;

import dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas{

    private final List<Pelicula> peliculas;

    public ServicioPeliculasLista(){
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de Peliculas...");
        peliculas.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
     peliculas.add(pelicula);
        System.out.println("Se agrego la pelicula: " + pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        //regresa el indice de la pelicula
        var indice = peliculas.indexOf(pelicula);
        if(indice == -1)
            System.out.println("No se encontro la pelicula" + pelicula);
        else
        System.out.println("Pelicula encontrada en el indice: " + indice);
        //si no la encuentra devuelve -1
    }

    public static void main(String[] args) {
        //Creamos objetos
        var pelicula3 = new Pelicula("Robot Salvaje");
        var pelicula4 = new Pelicula("Harry Potter");
        //creamos el patron de diseño service
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        // Agregar las peliculas
        servicioPeliculas.agregarPelicula(pelicula3);
        servicioPeliculas.agregarPelicula(pelicula4);
        //listamos las peliculas
        servicioPeliculas.listarPeliculas();
        // buscar la pelicula hash y equals
       servicioPeliculas.buscarPelicula(new Pelicula("DeadPool"));
    }
}
