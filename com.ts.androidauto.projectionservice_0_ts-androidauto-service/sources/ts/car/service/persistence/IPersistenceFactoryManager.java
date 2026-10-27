package ts.car.service.persistence;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IPersistenceFactoryManager extends IPlatformService {
    int getDefaultInt(String str, int i) throws RemoteException;

    String getDefaultString(String str, String str2) throws RemoteException;

    int getInt(String str, int i) throws RemoteException;

    String getString(String str, String str2) throws RemoteException;

    boolean putInt(String str, int i) throws RemoteException;

    boolean putString(String str, String str2) throws RemoteException;
}
