package commun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ICabinetVeterinaire extends Remote {
	// on ne met pas setPatients dans l'interface car on ne veut pas que le client
	// remplace la liste de patients par une nouvelle liste
	public List<IAnimal> getPatients() throws RemoteException;

	public void addPatient(String name, String nameOwner, Espece espece, String race, String suivi)
			throws RemoteException;// on n'ajoute qu'un seul patient

	public IAnimal rechercheAnimal(String nom) throws RemoteException; // recherche de l'animal
	public ArrayList<IAnimal> getAnimalByOwner(String nameOwner) throws RemoteException;// recherche de l'animal par nom du propriétaire
	public void addClient(IClient client) throws RemoteException;

	public void removePatient(String nameAnimal, String nameOwner) throws RemoteException;
	public void removeClient(IClient client) throws RemoteException;
}
