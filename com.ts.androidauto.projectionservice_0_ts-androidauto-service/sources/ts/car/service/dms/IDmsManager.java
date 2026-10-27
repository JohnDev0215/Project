package ts.car.service.dms;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IDmsManager extends IPlatformService {
    String getVersion() throws RemoteException;

    void prepare() throws RemoteException;

    int registerDmsStatusListener(IDmsStatusListener iDmsStatusListener) throws RemoteException;

    void release() throws RemoteException;

    void start() throws RemoteException;

    void stop() throws RemoteException;

    int unregisterDmsStatusListener(IDmsStatusListener iDmsStatusListener) throws RemoteException;
}
