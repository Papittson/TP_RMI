package server;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import commun.IDossierSuivi;

@SuppressWarnings("serial")
public class DossierSuivi extends UnicastRemoteObject implements IDossierSuivi {
	/* Attribut */
	
	private String information;
	
	
	/* Constructeur */
	
	public DossierSuivi(String information) throws RemoteException {
		this.information=information;
	}
	
	
	/* Accesseurs */
	
	public void setInformation(String information) throws RemoteException {
		this.information = information;
	}
	public String getInformation() throws RemoteException {
		return this.information;
	}
	
	/* Méthodes */
	public String toString(){
		return this.information;
	}
	
}
