package commun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossierSuivi extends Remote{
	public void setInformation(String information) throws RemoteException;
	public String getInformation() throws RemoteException;
	
}
