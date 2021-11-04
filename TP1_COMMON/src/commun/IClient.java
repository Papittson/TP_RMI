package commun;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	public void afficherAlerte(int nbPatients, String hausseBaisse) throws RemoteException;
	}
