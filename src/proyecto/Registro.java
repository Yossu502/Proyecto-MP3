package proyecto;

public class Registro {
    private String Artista;
    private String Grupo;
    private String NombreCancion;
    private String Album;
    private String AñoAlbum;
    private String Comentario;
    private String Genero;
  
    public Registro() {
        this.Artista = new String();
        this.Grupo = new String();
        this.NombreCancion = new String();
        this.Album = new String();
        this.AñoAlbum = new String();
        this.Comentario = new String();
        this.Genero = new String();
    }

    public Registro(String Artista, String Grupo, String NombreCancion, String Album, String AñoAlbum, String Comentario, String Genero) {
        this.Artista = Artista;
        this.Grupo = Grupo;
        this.NombreCancion = NombreCancion;
        this.Album = Album;
        this.AñoAlbum = AñoAlbum;
        this.Comentario = Comentario;
        this.Genero = Genero;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String Artista) {
        this.Artista = Artista;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public String getNombreCancion() {
        return NombreCancion;
    }

    public void setNombreCancion(String NombreCancion) {
        this.NombreCancion = NombreCancion;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }

    public String getAñoAlbum() {
        return AñoAlbum;
    }

    public void setAñoAlbum(String AñoAlbum) {
        this.AñoAlbum = AñoAlbum;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }
    
    @Override
    public String toString() {
        return "Artista: "+Artista+
                "\nGrupo: "+Grupo+
                "\nNombre Cancion: "+NombreCancion+
                "\nAlbum: "+Album+
                "\nAño Del Album: "+AñoAlbum+
                "\nComentario: "+Comentario+
                "\nGenero: "+Genero;
    }
}
