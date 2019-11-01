
package proyecto;

import java.io.IOException;
import java.io.RandomAccessFile;

class ManejadorArchivo {
    
    public static void escribirRegistro(Registro r, RandomAccessFile f) throws IOException {
        byte []longitudNombreArtista = {(byte) r.getArtista().length()};
        byte []longitudGrupo = {(byte) r.getGrupo().length()};
        byte []longitudNombreCancion = {(byte) r.getNombreCancion().length()};
        byte []longitudNombreAlbum = {(byte) r.getAlbum().length()};
        byte []longitudAño = {(byte) r.getAñoAlbum().length()};
        byte []longitudComentario = {(byte) r.getComentario().length()};
        byte []longitudGenero = {(byte) r.getGenero().length()};
        
        
        f.write(longitudNombreArtista);
        f.writeBytes(r.getArtista());
        f.write(longitudGrupo);
        f.writeBytes(r.getGrupo());
        f.write(longitudNombreCancion);
        f.writeBytes(r.getNombreCancion());
        f.write(longitudNombreAlbum);
        f.writeBytes(r.getAlbum());
        f.write(longitudAño);
        f.writeBytes(r.getAñoAlbum());
        f.write(longitudComentario);
        f.writeBytes(r.getComentario());
        f.write(longitudGenero);
        f.writeBytes(r.getGenero());
    }
    
 
    public static Registro leerRegistro(RandomAccessFile f) throws IOException, ArchivoNoValidoException {
        Registro r;
        byte []Artista = null;
        int longitudNombre;
        longitudNombre = Conversion.unsignedByteAInt(f.readByte());
        if (longitudNombre > 0) {
            Artista = new byte[longitudNombre];
            f.read(Artista);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");

        byte []Grupo = null;
        int longitudApellido;
        longitudApellido = Conversion.unsignedByteAInt(f.readByte());
        if (longitudApellido > 0) {
            Grupo = new byte[longitudApellido];
            f.read(Grupo);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");

        byte []NombreCancion = null;
        int longitudCancion;
        longitudCancion = Conversion.unsignedByteAInt(f.readByte());
        if (longitudCancion > 0) {
            NombreCancion = new byte[longitudApellido];
            f.read(NombreCancion);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");

        byte []Album = null;
        int longitudAlbum;
        longitudAlbum = Conversion.unsignedByteAInt(f.readByte());
        if (longitudAlbum > 0) {
            Album = new byte[longitudAlbum];
            f.read(Album);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        
        byte []AñoAlbum = null;
        int longitudAñoAlbum;
        longitudAñoAlbum = Conversion.unsignedByteAInt(f.readByte());
        if (longitudAñoAlbum > 0) {
            AñoAlbum = new byte[longitudAñoAlbum];
            f.read(AñoAlbum);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        
        byte []Comentario = null;
        int longitudComentario;
        longitudComentario = Conversion.unsignedByteAInt(f.readByte());
        if (longitudComentario > 0) {
            Comentario = new byte[longitudComentario];
            f.read(Comentario);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        
        byte []Genero = null;
        int longitudGenero;
        longitudGenero = Conversion.unsignedByteAInt(f.readByte());
        if (longitudGenero > 0) {
            Comentario = new byte[longitudGenero];
            f.read(Genero);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        
         r = new Registro(
                new String(Artista), 
                new String(Grupo), 
                new String(NombreCancion),
                new String(Album),
                new String(AñoAlbum),
                new String(Comentario),
                new String(Genero));
        return r;
    }
    
    public static void escribirReferenciaIndice(Referencia r, RandomAccessFile f) throws IOException{
        byte []longitudNombre = {(byte) r.getNombrebuscar().length()};
        
        f.write(Conversion.deLongA3Bytes(r.getPosicion()));
        f.write(longitudNombre);
        f.writeBytes(r.getNombrebuscar());
    }

    public static Referencia leerReferenciaIndice(RandomAccessFile f) throws IOException, ArrayIndexOutOfBoundsException {
        Referencia r;
        byte []apellido;        
        byte []posicion = new byte[3];
        
        f.read(posicion);
        int longitudApellido;

        longitudApellido = Conversion.unsignedByteAInt(f.readByte());
        if (longitudApellido > 0) {
            apellido = new byte[longitudApellido];
            f.read(apellido);
        }
        else
            throw new IOException("La estructura del archivo esta dañada o no es un archivo de agenda");
        r = new Referencia(new String(apellido), Conversion.de3BytesAInt(posicion));
        return r;
    }
}
