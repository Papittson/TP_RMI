package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import commun.Espece;
import commun.IAnimal;
import commun.ICabinetVeterinaire;
import commun.IClient;

@SuppressWarnings("serial")
public class CabinetVeterinaire extends UnicastRemoteObject implements ICabinetVeterinaire {
	/* Attributs */
	private List<IAnimal> patients;
	private List<IClient> clients = new ArrayList<IClient>();

	/* Constructeurs */
	public CabinetVeterinaire(List<IAnimal> patients) throws RemoteException {
		this.patients = patients;
	}

	/* Accesseurs */
	public void setPatients(List<IAnimal> patients) { // remplace la liste des patients par une nouvelle liste
		this.patients = patients;
	}

	public List<IAnimal> getPatients() throws RemoteException {
		return this.patients;
	}

	/* Méthodes */
	
	public void addClient(IClient client) throws RemoteException {
		this.clients.add(client);
	}
	
	
	public ArrayList<IAnimal> getAnimalByOwner(String nameOwner) throws RemoteException { //recherche les animaux par nom du propriétaire
		ArrayList<IAnimal> res = new ArrayList<IAnimal>();
		for (IAnimal animal : patients) {
			if (animal.getNameOwner().equals(nameOwner)) {
				res.add(animal);
			}
		}
		return res;
	}

	public IAnimal rechercheAnimal(String nom) throws RemoteException {
		for (IAnimal i : patients) {
			if (i.getName().equals(nom)) {
				return i;
			}
		}
		return null;
	}

	public void addPatient(String name, String nameOwner, Espece espece, String race, String suivi)
			throws RemoteException {
		DossierSuivi dossierSuivi = new DossierSuivi(suivi);
		Animal animal = new Animal(name, nameOwner, espece, race, dossierSuivi);
		this.patients.add(animal);
		// on envoie un msg à tous les clients
		int size = patients.size();
		if (size == 100 || size == 500 || size == 1000) {
			for (IClient client : this.clients) {
				client.afficherAlerte(size, "hausse");
			}

		}
	}

	public void removeClient(IClient client) throws RemoteException {
		clients.remove(client);
	}
	
	
	public void removePatient(String name, String nameOwner) throws RemoteException {// changer la recherche par un code patient ou par le
		IAnimal aniSupp = null;															// nameOwner
		for (IAnimal animal : patients) {
			if (animal.getName().equals(name) && animal.getNameOwner().equals(nameOwner)) {
				aniSupp = animal;
				break;
			}
		}
		this.patients.remove(aniSupp);
		int size = patients.size();
		if (size == 100 || size == 500 || size == 1000) {
			for (IClient client : this.clients) {
				client.afficherAlerte(size, "baisse");
			}
		}	

	}
}