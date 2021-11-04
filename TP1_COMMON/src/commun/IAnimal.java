package commun;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IAnimal extends Remote{
	
	public void afficher() throws RemoteException;
	
	public String getName() throws RemoteException ;
	 public void setName(String name) throws RemoteException;
	 
	 public String getNameOwner() throws RemoteException;
	 public void setNameOwner(String nameOwner) throws RemoteException;
	 
	 public Espece getEspece() throws RemoteException;
	 
	 public String getRace() throws RemoteException;
	 public void setRace(String race) throws RemoteException;
	 
	 public IDossierSuivi getDossierSuivi() throws RemoteException;
	 public void setDossierSuivi(IDossierSuivi dossierSuivi) throws RemoteException;
	

}
