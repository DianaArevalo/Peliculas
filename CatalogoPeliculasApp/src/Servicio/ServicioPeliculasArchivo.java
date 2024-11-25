package Servicio;


import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //si ya existe no se vuelve a crear
            if (archivo.exists()) {
                System.out.println("El archivo ya existe!");
            } else {
                //se crea vacio
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            }


        } catch (Exception e) {
            System.out.println("Ocurrio un error al abrir el archivo " + e.getMessage());
        }
    }


    @Override
    public void listarPeliculas() {
        // volvemos a abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("**Listado de Peliculas**");
            //Abrir archivo
            var entrada = new BufferedReader(new FileReader(archivo));
            //leer
            String linea;
            linea = entrada.readLine();
            //leemos todas las lineas
            while (linea != null) {
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                // lleer la siguiente linea
                linea = entrada.readLine();
            }
            //cerrar el archivo
            entrada.close();
        } catch (Exception e) {
            System.out.println("error de lectura de archivo: " + e.getMessage());
        }
    }

        @Override
        public void agregarPelicula (Pelicula pelicula){
            boolean anexar = false;
            var archivo = new File(NOMBRE_ARCHIVO);
            try {
                //revisamos si existe el archivo
                anexar = archivo.exists();
                var salida = new PrintWriter(new FileWriter(archivo, anexar));
                // agregar la pelicula
                salida.println(pelicula);
                salida.close();
                System.out.println("Se agrego al archivo: "+ pelicula);
            } catch (Exception e) {
                System.out.println("Ocurrio un error al agregar pelicula: " + e.getMessage());
            }


        }

        @Override
        public void buscarPelicula (Pelicula pelicula){
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            //Abrimos el archivo para lectura linea a linea
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null){
                //buscamos sin importar mayusculas o minusculas
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;
                }
                //leemos la siguiente linea antes de la siguiente iteracion
                lineaTexto = entrada.readLine();
                indice++;
            }
            //imprimir resultados
            if(encontrada)
                System.out.println("Pelicula " + lineaTexto
                        + "encontrada - linea " + indice);
            else
                System.out.println("No se encontro la pelicula " + pelicula.getNombre());
            entrada.close();
        }catch (Exception e){
            System.out.println("Ocurrio un error al buscar el archivo: " + e.getMessage());
        }

        }
    }

