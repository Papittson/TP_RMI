package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import commun.Espece;
import commun.IAnimal;
import commun.IDossierSuivi;

@SuppressWarnings("serial")
public class Animal extends UnicastRemoteObject implements IAnimal {

	/* Attributs */

	private String name;
	private String nameOwner;
	private Espece espece;
	private String race;
	private IDossierSuivi dossierSuivi;

	/* Constructeurs */
	
	public Animal(String name, String nameOwner, Espece espece, String race, IDossierSuivi dossierSuivi) throws RemoteException {
		this.name = name;
		this.nameOwner = nameOwner;
		this.espece = espece;
		this.race = race;
		this.dossierSuivi = dossierSuivi;
	}
	
	/* Méthodes */
	
	public String toString(){
		return "nom : "+this.name+"\nnom du propriétaire: "+this.nameOwner+"\nEspece: "+this.espece.getName()+"\n"+this.dossierSuivi;
	}
	
	public void afficher() throws RemoteException {
		System.out.println(this);
	}
	
	/* Accesseurs */
	
	 public String getName() throws RemoteException {
		 return this.name;
	 }
	 public void setName(String name) throws RemoteException{
		 this.name = name;
	 }
	 
	 public String getNameOwner() throws RemoteException{
		 return this.nameOwner;
	 }
	 public void setNameOwner(String nameOwner) throws RemoteException{
		 this.nameOwner = nameOwner;
	 }
	 
	 public Espece getEspece() throws RemoteException{
		 return this.espece;
	 }
	 public void setEspece(Espece espece) throws RemoteException{
		 this.espece = espece;
	 }
	 
	 public String getRace() throws RemoteException{
		 return this.race;
	 }
	 public void setRace(String race) throws RemoteException{
		 this.race = race;
	 }
	 
	 public IDossierSuivi getDossierSuivi() throws RemoteException{
		 return this.dossierSuivi;
	 }
	 public void setDossierSuivi(IDossierSuivi dossierSuivi) throws RemoteException{
		 this.dossierSuivi = dossierSuivi;
	 }
	  
	 
}
