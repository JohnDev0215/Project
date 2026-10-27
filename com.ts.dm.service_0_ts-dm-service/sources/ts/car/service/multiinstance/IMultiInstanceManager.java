package ts.car.service.multiinstance;

import android.content.ComponentName;
import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes.dex */
public interface IMultiInstanceManager extends IPlatformService {
    boolean startInstanceForExtendedDisplay(ComponentName componentName, int i) throws RemoteException;

    boolean startInstanceForPrimaryDisplay(ComponentName componentName, int i) throws RemoteException;

    boolean stopInstanceForExtendedDisplay(String str, int i) throws RemoteException;

    boolean stopInstanceForPrimaryDisplay(String str, int i) throws RemoteException;
}
