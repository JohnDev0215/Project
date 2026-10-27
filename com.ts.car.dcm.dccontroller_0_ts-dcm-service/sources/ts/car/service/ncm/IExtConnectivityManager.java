package ts.car.service.ncm;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.ncm.data.IfaceInfo;
import ts.car.service.ncm.data.IpConfiguration;

/* JADX INFO: loaded from: classes.dex */
public interface IExtConnectivityManager extends IPlatformService {
    public static final String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";
    public static final String CHANGE_NETWORK_STATE = "android.permission.CHANGE_NETWORK_STATE";

    boolean enableInterface(String str, boolean z) throws RemoteException;

    boolean enableMobileNetwork(boolean z) throws RemoteException;

    List<IfaceInfo> getIfaceInfos() throws RemoteException;

    IfaceInfo.IfaceState getIfaceState(String str) throws RemoteException;

    IpConfiguration getIpConfiguration(String str) throws RemoteException;

    boolean getMobileNetworkEnable() throws RemoteException;

    void registerListener(IExtNetworkListener iExtNetworkListener) throws RemoteException;

    void unregisterListener(IExtNetworkListener iExtNetworkListener) throws RemoteException;
}
