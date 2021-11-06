package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

import commun.Espece;
import commun.IAnimal;
import commun.ICabinetVeterinaire;
import commun.IClient;
import commun.IDossierSuivi;
import export.Poisson;

public class Client extends UnicastRemoteObject implements IClient {
	public Client() throws RemoteException {
	}

	public void afficherAlerte(int nbPatient, String hausseBaisse) throws RemoteException {
		System.out.println("Le seuil de " + nbPatient + " patients a été atteint à la " + hausseBaisse);
	}

	public static void main(String[] args) throws NotBoundException {
		String host = (args.length < 1) ? null : args[0];
		try {

			System.setProperty("java.security.policy", "file:///..\\security.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			Client client = new Client();

			Registry registry = LocateRegistry.getRegistry(host);

			ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("cabinet");

			cabinet.addClient(client);

			Poisson clown = new Poisson("Poisson Clown", 50);

			cabinet.addPatient("Bloup", "LePoisson", clown, "Poisson Clown", "Fait des bulles");
			IAnimal animalR = cabinet.rechercheAnimal("Bloup");
			if (animalR == null) {
				System.out.println("pas trouvé");
				return;
			}

			Scanner sc = new Scanner(System.in);

			System.out.println("Bonjour, entrez votre nom s'il vous plaît");
			String nameOwner = sc.nextLine();

			int choice = -1;
			do {
				System.out.println(
						"Que voulez vous faire?\nPour ajouter un animal entrez 1\nPour supprimer des animaux entrez 2\nPour ajouter plus de 100 animaux entrez 3\nPour consulter le dossier de votre animal entrez 4\nPour quitter la saisie entrez 0");

				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {

					System.out.print("Entrez le nom de l'animal : ");
					String name = sc.nextLine();

					System.out.print("Entrez le nom de l'espece : ");
					String nameSpecies = sc.nextLine();
					System.out.print("Entrez la durée de vie de cette espèce en année entière : ");
					int lifeSpan = sc.nextInt();
					sc.nextLine();
					Espece species = new Espece(nameSpecies, lifeSpan);

					System.out.print("Entrez la race de l'animal : ");
					String race = sc.nextLine();

					System.out.print("Décrivez l'état de votre animal : ");
					String suivi = sc.nextLine();

					cabinet.addPatient(name, nameOwner, species, race, suivi);

				} else if (choice == 2) {
					System.out.print("Entrez le nom de l'animal à supprimer : ");
					String name = sc.nextLine();
					cabinet.removePatient(name, nameOwner);
				} else if (choice == 3) {
					System.out.print("Entrez le nombre d'animaux à ajouter ( >=100) : ");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice >= 100) {
						System.out.print(
								"Les animaux auront des attributs par défaut, sauf le nom de l'animal et le nom du propriétaire, le nom de l'animal sera sous cette forme : Animal n°150 ;");
						for (int i = 1; i <= choice; i++) {
							cabinet.addPatient("Animal_n°" + i, nameOwner, null, null, null);
						}
						System.out.print("Souhaitez vous supprimer certains de ces " + choice
								+ " animaux que vous venez d'ajouter?  Entrez o pour oui    Entrez n pour non  : ");
						String on = sc.nextLine();
						if (on.equals("o")) {
							System.out.print("Entrez le nombre d'animaux à supprimer: ");
							int nbSup = sc.nextInt();
							sc.nextLine();
							for (int i = choice; i > choice - nbSup; i--) {
								cabinet.removePatient("Animal_n°" + i, nameOwner);
							}
						}
					} 
				}else if (choice == 4) {
					List<IAnimal> animDispo = cabinet.getAnimalByOwner(nameOwner);
					String animDispoAffich = "Vos animaux enregistrés : ";
					for (IAnimal animal : animDispo) {
						animDispoAffich+= animal.getName()+"   ";
					}
					System.out.println(animDispoAffich);
					System.out.print("Entrez le nom de votre animal: ");
					String name = sc.nextLine();
					boolean check = false;
					for (IAnimal animal : animDispo) {
						if(animal.getName().equals(name)) {
							check = true;
							System.out.println("Voici le dossier de votre animal:"+animal.getDossierSuivi().getInformation());
							
						}
					} 
					if (!check) {
						System.out.println("Votre animal n'a pas été trouvé");
					}
					
				}
			}while (choice != 0);
			sc.close();
			cabinet.removeClient(client);

			// IDossierSuivi suivi = animalR.getDossierSuivi();
			// System.out.println(suivi.getInformation());

			// Pour que le client puisse modifier le dossier de suivi il doit faire
			// animal.getDossierSuivi().setInformation("blabla");
			// Pour que le client puisse lire le dossier de suivi il doit faire
			// sysout(animal.getDossierSuivi());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
