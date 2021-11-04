package server;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commun.Espece;
import commun.IAnimal;

public class Server {
	
	public Server() {} ;

	public static void main(String[] args) {
		
			try {
				
				
				String absolutePath = (new File("..\\TP1_CLIENT\\bin\\").getCanonicalPath()+"\\").replace("\\","\\\\");
				String absolutePathPolicy = (new File("file:///..\\security.policy").getCanonicalPath()).replace("\\","\\\\");
				
				System.setProperty("java.security.policy",	absolutePathPolicy);
				System.setSecurityManager (new SecurityManager());
				System.setProperty( "java.rmi.server.codebase", "file:///"+absolutePath);
				
				Registry registry = LocateRegistry.createRegistry(1099);
				if (registry == null)
					System.err.println("Registry not found");
				else {
					System.out.println("Server Ready !");
					
					Espece chat=new Espece("CHAT", 17);
					Espece chien=new Espece("CHIEN", 14);
					Espece tortue=new Espece("TORTUE", 120);
					
					Animal animal1=new Animal("Gouttelette", "LeChat",chat,"Persan", new DossierSuivi("En bonne santé"));
					Animal animal2=new Animal("Nicolas", "LeChien",chien,"Labrador", new DossierSuivi("A la patte cassée"));
					Animal animal3=new Animal("Regis", "LaTortue",tortue,"Testudo", new DossierSuivi("En surpoids"));
					
					List<IAnimal> nouveauxPatients = new ArrayList<IAnimal>();
					
					nouveauxPatients.add(animal1);
					nouveauxPatients.add(animal2);
					nouveauxPatients.add(animal3);
					
					CabinetVeterinaire cabinet = new CabinetVeterinaire(nouveauxPatients);
					System.out.println(cabinet.getPatients());
					
					registry.rebind("cabinet", cabinet);
					
					
					
				}
				
			} catch (Exception e) {
				System.out.println("Serveur_Exception "+e.toString()) ;
				e.printStackTrace();
			}
		

	}

}
