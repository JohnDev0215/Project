package ts.car.service.bt.interaction;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothInteractionManager extends IPlatformService {
    public static final String ACCESS_BLUETOOTH_EXT = "android.permission.ACCESS_BLUETOOTH_EXT";
    public static final String UPDATE_BLUETOOTH_EXT = "android.permission.UPDATE_BLUETOOTH_EXT";

    int getErrCode() throws RemoteException;

    boolean projSetBtAutoConnect(String str, boolean z) throws RemoteException;

    boolean projectionOff(String str, int i) throws RemoteException;

    boolean projectionOn(String str, int i) throws RemoteException;
}
