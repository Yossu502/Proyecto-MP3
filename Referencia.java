package proyecto;

import java.util.Objects;


public class Referencia implements Comparable<Referencia> {
    private String nombrebuscar;
    private long posicion;
    

    public Referencia(String nombrebuscar) {
        this(nombrebuscar,0l);
    }
    
 
    public Referencia(String nombrebuscar, long posicion) throws NullPointerException{
       this.nombrebuscar = nombrebuscar;
       this.posicion = posicion;
    }

    public String getNombrebuscar() {
        return nombrebuscar;
    }

    public void setNombrebuscar(String nombrebuscar) {
        this.nombrebuscar = nombrebuscar;
    }

    public long getPosicion() {
        return posicion;
    }

    public void setPosicion(long posicion) {
        this.posicion = posicion;
    }

    


    @Override
    public int compareTo(Referencia o) {
        return this.nombrebuscar.compareTo(o.getNombrebuscar());
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Referencia) {
            return this.nombrebuscar.equals(((Referencia)o).getNombrebuscar());
        }            
        else if (o instanceof String) {
            return this.nombrebuscar.equals((String)o);
        }
        else
            return false;
    }
    
  
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.nombrebuscar);
        return hash;
    }
}
