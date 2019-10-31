package proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proyecto {  
   
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
    
 
    
//    public void EnviarDatos(String tag, String InfoCancion) throws IOException{
//        if("TPE1".equals(tag)){
//            NuevoRegistro.setArtista(InfoCancion);
//            //Artista
//        } else if("TIT2".equals(tag)){
//            NuevoRegistro.setNombreCancion(InfoCancion);
//            //Nombre de la canción:
//        }else if("TPE2".equals(tag)){
//            NuevoRegistro.setGrupo(InfoCancion);
//           //Grupo
//        }else if("TCON".equals(tag)){
//            NuevoRegistro.setGenero(InfoCancion);
//            //Genero
//        }else if("TALB".equals(tag)){
//            NuevoRegistro.setAlbum(InfoCancion);
//           //Álbum
//        }else if("TYER".equals(tag)){
//            NuevoRegistro.setAñoAlbum(InfoCancion);
//            //Año del álbum
//        }else if("COMM".equals(tag)){
//            NuevoRegistro.setComentario(InfoCancion);
//            //Comentario
//        }
//       
//    }
    
    public void listarFicherosPorCarpeta(final File carpeta) throws FileNotFoundException, IOException  {
        ArrayList <String> Informacion = new ArrayList<>();
        for(final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
            } else {
                 if (ficheroEntrada.getName().endsWith(".mp3") || ficheroEntrada.getName().endsWith(".MP3"))
                    {
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
//                              EnviarDatos(tagg, cadena);
                                System.out.println(cadena);
                            }
                            pos=(short) (pos+10+tagSize);
                        }
                        archivo.close();

                        System.out.println("\n");
                    }   
            }
        } 
    }
     
    public static void main(String[] args) throws FileNotFoundException, ArchivoNoValidoException {
        try { 
            Proyecto mp3 = new Proyecto();
            String files;
            String ruta = System.getProperty("user.dir");
            File folder = new File(ruta);
            mp3.listarFicherosPorCarpeta(folder);
            File[] listOfFiles = folder.listFiles();
    }   catch (IOException ex) {
            Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
