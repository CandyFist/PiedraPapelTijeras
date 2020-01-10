package modelo;

public class Jugador {
	
	private int id;
    private String alias;
    private String contraseña;
    private int ganadas;
    private int perdidas;
    private int piedras;
    private int papeles;
    private int tijeras;

    public Jugador(String nombre, String contraseña) {
		super();
		this.alias = nombre;
		this.contraseña = contraseña;
	}

public Jugador(int id, String alias, String contraseña, int ganadas, int perdidas, int piedras, int papeles,
			int tijeras) {
		super();
		this.id = id;
		this.alias = alias;
		this.contraseña = contraseña;
		this.ganadas = ganadas;
		this.perdidas = perdidas;
		this.piedras = piedras;
		this.papeles = papeles;
		this.tijeras = tijeras;
	}
   
   
   	


	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getAlias() {
	return alias;
}

public void setAlias(String alias) {
	this.alias = alias;
}

public String getContraseña() {
	return contraseña;
}

public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}

public int getGanadas() {
	return ganadas;
}

public void setGanadas(int ganadas) {
	this.ganadas = ganadas;
}

public int getPerdidas() {
	return perdidas;
}

public void setPerdidas(int perdidas) {
	this.perdidas = perdidas;
}

public int getPiedras() {
	return piedras;
}

public void setPiedras(int piedras) {
	this.piedras = piedras;
}

public int getPapeles() {
	return papeles;
}

public void setPapeles(int papeles) {
	this.papeles = papeles;
}

public int getTijeras() {
	return tijeras;
}

public void setTijeras(int tijeras) {
	this.tijeras = tijeras;
}


	public void savePuntos(boolean ganar) {
        if(ganar) {
            ganadas++;
        } else {
            perdidas++;
        }
    }

    public String sacarPuntos() {
        return getAlias() + " " + getGanadas() + ":" + getPerdidas() + " " + Math.round(sacarRatio()) + '%';
    }

    public float sacarRatio() {
        if (getGanadas() == 0 && getPerdidas() == 0) {
            return 0;
        } else {
            return getGanadas() / (getGanadas() + (float)getPerdidas()) * 100;
        }
    }

    
    @Override
	public String toString() {
		return "Jugador [id=" + id + ", alias=" + alias + ", contraseña=" + contraseña + ", ganadas=" + ganadas
				+ ", perdidas=" + perdidas + ", piedras=" + piedras + ", papeles=" + papeles + ", tijeras=" + tijeras
				+ "]";
	}


}
