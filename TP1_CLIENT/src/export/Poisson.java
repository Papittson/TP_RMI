package export;

import commun.Espece;

@SuppressWarnings("serial")
public class Poisson extends Espece {
	
	/* Constructeur*/
	public Poisson(String name, int lifeSpan) {
		super(name,lifeSpan); //super pour appeler le constructeur de la classe mère
		
	}
	
	/*Methode*/
	public String toString() {
		return "Espèce de Poisson : "+this.getName()+"\nDurée de vie : "+this.getLifeSpan(); //on utilise les accesseurs car les attributs dans le classe Espece sont private 
	}
}
