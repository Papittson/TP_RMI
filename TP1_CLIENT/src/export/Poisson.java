package export;

import commun.Espece;

@SuppressWarnings("serial")
public class Poisson extends Espece {
	
	/* Constructeur*/
	public Poisson(String name, int lifeSpan) {
		super(name,lifeSpan); //super pour appeler le constructeur de la classe m�re
		
	}
	
	/*Methode*/
	public String toString() {
		return "Esp�ce de Poisson : "+this.getName()+"\nDur�e de vie : "+this.getLifeSpan(); //on utilise les accesseurs car les attributs dans le classe Espece sont private 
	}
}
