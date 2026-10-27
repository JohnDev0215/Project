package ts.car.service.wifi;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.wifi.data.SoftApClientInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface IExtWifiManager extends IPlatformService {
    public static final String ACCESS_WIFI_EXT = "android.permission.ACCESS_WIFI_EXT";
    public static final int SOFTAP_ACCESS_CONTROL_BLACKLIST = 2;
    public static final int SOFTAP_ACCESS_CONTROL_NONE = 0;
    public static final int SOFTAP_ACCESS_CONTROL_WHITELIST = 1;
    public static final int SOFTAP_CLIENT_EVENT_CONNECTED = 0;
    public static final int SOFTAP_CLIENT_EVENT_DISCONNECTED = 2;
    public static final int SOFTAP_CLIENT_EVENT_INFO_UPDATED = 1;
    public static final String UPDATE_WIFI_EXT = "android.permission.UPDATE_WIFI_EXT";

    boolean addClientsToAcl(int i, List<String> list) throws RemoteException;

    List<String> getClientsInAcl(int i) throws RemoteException;

    List<SoftApClientInfo> getConnectedSoftApClients() throws RemoteException;

    int getSoftApAccessControl() throws RemoteException;

    boolean isStationAutoConnectEnabled() throws RemoteException;

    boolean registerListener(IExtWifiListener iExtWifiListener) throws RemoteException;

    boolean removeClientsFromAcl(int i, List<String> list) throws RemoteException;

    boolean setSoftApAccessControl(int i) throws RemoteException;

    boolean setStationAutoConnectEnabled(boolean z) throws RemoteException;

    boolean unregisterListener(IExtWifiListener iExtWifiListener) throws RemoteException;
}
