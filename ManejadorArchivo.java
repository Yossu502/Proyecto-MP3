
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
        byte []nombre = null;
        int longitudNombre;
        longitudNombre = Conversion.unsignedByteAInt(f.readByte());
        if (longitudNombre > 0) {
            nombre = new byte[longitudNombre];
            f.read(nombre);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        
        // apellido
        byte []apellido = null;
        int longitudApellido;
        longitudApellido = Conversion.unsignedByteAInt(f.readByte());
        if (longitudApellido > 0) {
            apellido = new byte[longitudApellido];
            f.read(apellido);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        
        // telefono
        // es el único elemento distinto al resto de campos
        byte []telefono = new byte[8];
        f.read(telefono);
        
        // dirección
        byte []direccion = null;
        int longitudDireccion;
        longitudDireccion = Conversion.unsignedByteAInt(f.readByte());
        if (longitudDireccion > 0) {
            direccion = new byte[longitudDireccion];
            f.read(direccion);
        } else 
            throw new ArchivoNoValidoException("Estructura de registro inválida");
        return null;
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
