package proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proyecto {  
    private final Archivo archivo;
    private static final String nombreArchivo ="canciones.data";

    
    public Proyecto() throws IOException, FileNotFoundException, ArchivoNoValidoException {
        archivo = new Archivo(nombreArchivo);
    }
    
    public void agregarDatos(String A, String G, String NC, String AL, String ALL, String Com, String Gen) throws IOException{
        archivo.agregarRegistro(
                new Registro(A, G, NC, AL, ALL, Com, Gen));
        archivo.escribirDatos();
    }
    
    public String tag(String tag){
        if("TALB".equals(tag)){
            
            return "Álbum: ";
        }
        else if("TIT2".equals(tag)){
            
            return "Nombre de la canción: ";
        }
        else if("TPE1".equals(tag)){
            
            return "Artista: ";
        }
        else if("TPE2".equals(tag)){
            
            return "Grupo: ";
        }
        else if("TCON".equals(tag)){
            return "Genero: ";
        }
        else if("COMM".equals(tag)){
            return "Comentario: ";
        }
        else if("TDRC".equals(tag)){
            return "Fecha de lanzamiento: ";
        }
        else if("TYER".equals(tag)){
            return "Año del álbum: ";
        }
        else if("TRCK".equals(tag)){
            return "Etiqueta del track: ";
        }
        else{
            return null;
        }  
    }
    
    public void MeterAArrayList(String A, String G, String NC, String AL, String ALL, String Com, String Gen){
  
    
    }
     
    public void listarFicherosPorCarpeta(final File carpeta) throws FileNotFoundException, IOException  {
        String Artista, Grupo, NombreCancion, Album, AñoAlbum, Comentario, Genero;
        Artista = new String();
        Grupo = new String();
        NombreCancion = new String();
        Album = new String();
        AñoAlbum = new String();
        Comentario = new String();
        Genero = new String();
        
        ArrayList <String> Informacion = new ArrayList<>();
        for(final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
            } else {
                 if (ficheroEntrada.getName().endsWith(".mp3") || ficheroEntrada.getName().endsWith(".MP3"))
                    {
                        //
                        short pos=3; int totalTS;
                        RandomAccessFile archivo = new RandomAccessFile(ficheroEntrada, "r");
                        archivo.seek(pos);
                        byte Bver = archivo.readByte();

                        if (Bver==3){       
                            pos=6;
                            archivo.seek(pos);
                            totalTS = archivo.readInt();
                        }
                        else if(Bver== 4){  
                            pos=6;
                            archivo.seek(pos);
                            totalTS = archivo.readInt()/2;
                        }
                        else{
                            totalTS=0;
                        }
                        pos=(short) (pos+4);                
                        while(pos < totalTS){
                            //Busca la posicion del cursor
                            archivo.seek(pos);
                            //Lee la etiqueta.
                            byte[] TTag = new byte[4];
                            archivo.read(TTag);
                            String tagg = new String(TTag);
                            String read1 = tag(tagg);
                            //Lee el tamaño del string.
                            int tagSize = archivo.readInt();
                            if (read1!=null){
                                System.out.println(tagg + " => " +read1);
                                archivo.seek(pos+10);
                                byte[] info = new byte[tagSize];
                                archivo.read(info);
                                String cadena = new String(info);
                                System.out.println(cadena);
                                if ("TPE1".equals(tagg)){
                                    Artista = cadena;
                                }else if ("TPE2".equals(tagg)){
                                    Grupo = cadena;
                                }else if ("TIT2".equals(tagg)){
                                    NombreCancion = cadena;
                                }else if ("TALB".equals(tagg)){
                                    Album = cadena;
                                }else if ("TYER".equals(tagg)){
                                    AñoAlbum = cadena;
                                }else if ("COMM".equals(tagg)){
                                    Comentario = cadena;
                                } else if ("TCON".equals(tagg)){
                                    Genero = cadena;
                                }
                            }
                            pos=(short) (pos+10+tagSize);
                        }
                        archivo.close();
                        agregarDatos(Artista, Grupo, NombreCancion, Album, AñoAlbum, Comentario, Genero);
                        System.out.println("\n");
                    }   
            }
        } 
    }
     
    public static void main(String[] args) throws FileNotFoundException, ArchivoNoValidoException, IOException {
        Proyecto mp3 = new Proyecto();
        try { 
           
            String ruta = System.getProperty("user.dir");
            File folder = new File(ruta);
            mp3.listarFicherosPorCarpeta(folder);
            File[] listOfFiles = folder.listFiles();
    }   catch (IOException ex) {
            Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
