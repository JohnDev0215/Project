package ts.car.service.locationext;

import android.os.RemoteException;
import ts.car.service.IPlatformService;
import ts.car.service.locationext.data.GnssExtLocationGnss;
import ts.car.service.locationext.data.GnssExtMmfData;
import ts.car.service.locationext.data.GnssHwInfo;

/* JADX INFO: loaded from: classes.dex */
public interface ILocationExtManager extends IPlatformService {
    boolean enableLocationMessage(short s, boolean z) throws RemoteException;

    int getFirstLocationTime() throws RemoteException;

    GnssHwInfo getGnssHwInfo() throws RemoteException;

    GnssExtLocationGnss.LocationGnss getLocationGnss() throws RemoteException;

    boolean injectMmf(GnssExtMmfData gnssExtMmfData) throws RemoteException;

    boolean registerListener(ILocationExtResultListener iLocationExtResultListener) throws RemoteException;

    boolean setLocationGnss(GnssExtLocationGnss.LocationGnss locationGnss) throws RemoteException;

    boolean unregisterListener(ILocationExtResultListener iLocationExtResultListener) throws RemoteException;
}
