package org.chromium.content.browser;

import android.content.Context;
import android.os.Process;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.NativeClassQualifiedName;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.RegistrationPolicyAlwaysRegister;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class BackgroundSyncNetworkObserver implements NetworkChangeNotifierAutoDetect.Observer {
    private static final String TAG = "cr_BgSyncNetObserver";
    private static BackgroundSyncNetworkObserver sInstance;
    private Context mContext;
    private List<Long> mNativePtrs;
    private NetworkChangeNotifierAutoDetect mNotifier;

    @NativeClassQualifiedName("BackgroundSyncNetworkObserverAndroid::Observer")
    private native void nativeNotifyConnectionTypeChanged(long j, int i);

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
    public void onMaxBandwidthChanged(double d) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
    public void onNetworkConnect(int i, int i2) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
    public void onNetworkDisconnect(int i) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
    public void onNetworkSoonToDisconnect(int i) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
    public void purgeActiveNetworkList(int[] iArr) {
    }

    private BackgroundSyncNetworkObserver(Context context) {
        ThreadUtils.assertOnUiThread();
        this.mContext = context;
        this.mNativePtrs = new ArrayList();
    }

    private static boolean canCreateObserver(Context context) {
        return ApiCompatibilityUtils.checkPermission(context, "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0;
    }

    private static BackgroundSyncNetworkObserver createObserver(Context context, long j) {
        ThreadUtils.assertOnUiThread();
        if (sInstance == null) {
            sInstance = new BackgroundSyncNetworkObserver(context);
        }
        sInstance.registerObserver(j);
        return sInstance;
    }

    private void registerObserver(long j) {
        ThreadUtils.assertOnUiThread();
        if (!canCreateObserver(this.mContext)) {
            RecordHistogram.recordBooleanHistogram("BackgroundSync.NetworkObserver.HasPermission", false);
            return;
        }
        if (this.mNotifier == null) {
            this.mNotifier = new NetworkChangeNotifierAutoDetect(this, this.mContext, new RegistrationPolicyAlwaysRegister());
            RecordHistogram.recordBooleanHistogram("BackgroundSync.NetworkObserver.HasPermission", true);
        }
        this.mNativePtrs.add(Long.valueOf(j));
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mNotifier;
        nativeNotifyConnectionTypeChanged(j, networkChangeNotifierAutoDetect.getCurrentConnectionType(networkChangeNotifierAutoDetect.getCurrentNetworkState()));
    }

    private void removeObserver(long j) {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect;
        ThreadUtils.assertOnUiThread();
        this.mNativePtrs.remove(Long.valueOf(j));
        if (this.mNativePtrs.size() != 0 || (networkChangeNotifierAutoDetect = this.mNotifier) == null) {
            return;
        }
        networkChangeNotifierAutoDetect.destroy();
        this.mNotifier = null;
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
    public void onConnectionTypeChanged(int i) {
        ThreadUtils.assertOnUiThread();
        Iterator<Long> it = this.mNativePtrs.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(it.next().longValue(), i);
        }
    }
}
