package android.net;

import android.os.IBinder;
import android.os.RemoteException;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class TestNetworkManager {
    private static final String TAG = TestNetworkManager.class.getSimpleName();
    public static final String TEST_TAP_PREFIX = "testtap";
    public static final String TEST_TUN_PREFIX = "testtun";
    private final ITestNetworkManager mService;

    public TestNetworkManager(ITestNetworkManager iTestNetworkManager) {
        this.mService = (ITestNetworkManager) Preconditions.checkNotNull(iTestNetworkManager, "missing ITestNetworkManager");
    }

    public void teardownTestNetwork(Network network) {
        try {
            this.mService.teardownTestNetwork(network.netId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void setupTestNetwork(String str, LinkProperties linkProperties, boolean z, int[] iArr, IBinder iBinder) {
        try {
            this.mService.setupTestNetwork(str, linkProperties, z, iArr, iBinder);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setupTestNetwork(LinkProperties linkProperties, boolean z, IBinder iBinder) {
        Preconditions.checkNotNull(linkProperties, "Invalid LinkProperties");
        setupTestNetwork(linkProperties.getInterfaceName(), linkProperties, z, new int[0], iBinder);
    }

    public void setupTestNetwork(String str, IBinder iBinder) {
        setupTestNetwork(str, null, true, new int[0], iBinder);
    }

    public void setupTestNetwork(String str, int[] iArr, IBinder iBinder) {
        setupTestNetwork(str, null, true, iArr, iBinder);
    }

    public TestNetworkInterface createTunInterface(LinkAddress[] linkAddressArr) {
        try {
            return this.mService.createTunInterface(linkAddressArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public TestNetworkInterface createTapInterface() {
        try {
            return this.mService.createTapInterface();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
