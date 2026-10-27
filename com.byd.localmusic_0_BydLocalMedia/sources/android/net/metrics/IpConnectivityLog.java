package android.net.metrics;

import android.annotation.SystemApi;
import android.net.ConnectivityMetricsEvent;
import android.net.IIpConnectivityMetrics;
import android.net.Network;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.util.BitUtils;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class IpConnectivityLog {
    private static final boolean DBG = false;
    public static final String SERVICE_NAME = "connmetrics";
    private static final String TAG = IpConnectivityLog.class.getSimpleName();
    private IIpConnectivityMetrics mService;

    public interface Event extends Parcelable {
    }

    @SystemApi
    public IpConnectivityLog() {
    }

    public IpConnectivityLog(IIpConnectivityMetrics iIpConnectivityMetrics) {
        this.mService = iIpConnectivityMetrics;
    }

    private boolean checkLoggerService() {
        if (this.mService != null) {
            return true;
        }
        IIpConnectivityMetrics iIpConnectivityMetricsAsInterface = IIpConnectivityMetrics.Stub.asInterface(ServiceManager.getService(SERVICE_NAME));
        if (iIpConnectivityMetricsAsInterface == null) {
            return false;
        }
        this.mService = iIpConnectivityMetricsAsInterface;
        return true;
    }

    public boolean log(ConnectivityMetricsEvent connectivityMetricsEvent) {
        if (!checkLoggerService()) {
            return false;
        }
        if (connectivityMetricsEvent.timestamp == 0) {
            connectivityMetricsEvent.timestamp = System.currentTimeMillis();
        }
        try {
            return this.mService.logEvent(connectivityMetricsEvent) >= 0;
        } catch (RemoteException e) {
            Log.e(TAG, "Error logging event", e);
            return false;
        }
    }

    public boolean log(long j, Event event) {
        ConnectivityMetricsEvent connectivityMetricsEventMakeEv = makeEv(event);
        connectivityMetricsEventMakeEv.timestamp = j;
        return log(connectivityMetricsEventMakeEv);
    }

    public boolean log(String str, Event event) {
        ConnectivityMetricsEvent connectivityMetricsEventMakeEv = makeEv(event);
        connectivityMetricsEventMakeEv.ifname = str;
        return log(connectivityMetricsEventMakeEv);
    }

    public boolean log(Network network, int[] iArr, Event event) {
        return log(network.netId, iArr, event);
    }

    public boolean log(int i, int[] iArr, Event event) {
        ConnectivityMetricsEvent connectivityMetricsEventMakeEv = makeEv(event);
        connectivityMetricsEventMakeEv.netId = i;
        connectivityMetricsEventMakeEv.transports = BitUtils.packBits(iArr);
        return log(connectivityMetricsEventMakeEv);
    }

    public boolean log(Event event) {
        return log(makeEv(event));
    }

    private static ConnectivityMetricsEvent makeEv(Event event) {
        ConnectivityMetricsEvent connectivityMetricsEvent = new ConnectivityMetricsEvent();
        connectivityMetricsEvent.data = event;
        return connectivityMetricsEvent;
    }
}
