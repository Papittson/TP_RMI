package commun;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Espece implements Serializable {
	/* Attributs */
	private String name;
	private int lifeSpan;
	
	/* Constructeur*/
	public Espece(String name, int lifeSpan) {
		this.name=name;
		this.lifeSpan=lifeSpan;
	}
	
	/*Accesseurs*/
	public String getName() {
		return this.name;
	}
	public int getLifeSpan() {
		return this.lifeSpan;
	}
	/*Methode*/
	public String toString() {
		return "Espèce : "+this.name+"\nDurée de vie : "+this.lifeSpan;
	}
	
	
}
