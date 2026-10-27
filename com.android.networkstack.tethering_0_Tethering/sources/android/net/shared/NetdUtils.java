package android.net.shared;

import android.net.INetd;
import android.net.IpPrefix;
import android.net.RouteInfo;
import android.net.TetherConfigParcel;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemClock;
import android.system.OsConstants;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NetdUtils {
    private static final String TAG = "NetdUtils";

    public static void tetherStart(INetd iNetd, boolean z, String[] strArr) throws RemoteException, ServiceSpecificException {
        TetherConfigParcel tetherConfigParcel = new TetherConfigParcel();
        tetherConfigParcel.usingLegacyDnsProxy = z;
        tetherConfigParcel.dhcpRanges = strArr;
        iNetd.tetherStartWithConfiguration(tetherConfigParcel);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public static void tetherInterface(INetd iNetd, String str, IpPrefix ipPrefix) throws RemoteException, ServiceSpecificException {
        tetherInterface(iNetd, str, ipPrefix, 20, 50);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public static void tetherInterface(INetd iNetd, String str, IpPrefix ipPrefix, int i, int i2) throws RemoteException, ServiceSpecificException {
        iNetd.tetherInterfaceAdd(str);
        networkAddInterface(iNetd, str, i, i2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RouteInfo(ipPrefix, null, str, 1));
        RouteUtils.addRoutesToLocalNetwork(iNetd, str, arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    private static void networkAddInterface(INetd iNetd, String str, int i, int i2) throws RemoteException, ServiceSpecificException {
        for (int i3 = 1; i3 <= i; i3++) {
            try {
                iNetd.networkAddInterface(99, str);
                return;
            } catch (ServiceSpecificException e) {
                if (e.errorCode == OsConstants.EBUSY && i3 < i) {
                    SystemClock.sleep(i2);
                } else {
                    Log.e(TAG, "Retry Netd#networkAddInterface failure: " + e);
                    throw e;
                }
            }
        }
    }

    public static void untetherInterface(INetd iNetd, String str) throws RemoteException, ServiceSpecificException {
        try {
            iNetd.tetherInterfaceRemove(str);
        } finally {
            iNetd.networkRemoveInterface(99, str);
        }
    }
}
