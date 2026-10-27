package org.chromium.net;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.NativeClassQualifiedName;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("net")
public class NetworkChangeNotifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static NetworkChangeNotifier sInstance;
    private NetworkChangeNotifierAutoDetect mAutoDetector;
    private final Context mContext;
    private int mCurrentConnectionType = 0;
    private double mCurrentMaxBandwidth = Double.POSITIVE_INFINITY;
    private int mMaxBandwidthConnectionType = 0;
    private final ArrayList<Long> mNativeChangeNotifiers = new ArrayList<>();
    private final ObserverList<ConnectionTypeObserver> mConnectionTypeObservers = new ObserverList<>();

    public interface ConnectionTypeObserver {
        void onConnectionTypeChanged(int i);
    }

    private static native double nativeGetMaxBandwidthForConnectionSubtype(int i);

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyConnectionTypeChanged(long j, int i, int i2);

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyMaxBandwidthChanged(long j, double d);

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyOfNetworkConnect(long j, int i, int i2);

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyOfNetworkDisconnect(long j, int i);

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyOfNetworkSoonToDisconnect(long j, int i);

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyPurgeActiveNetworkList(long j, int[] iArr);

    protected NetworkChangeNotifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static NetworkChangeNotifier init(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkChangeNotifier(context);
        }
        return sInstance;
    }

    public static boolean isInitialized() {
        return sInstance != null;
    }

    static void resetInstanceForTests(NetworkChangeNotifier networkChangeNotifier) {
        sInstance = networkChangeNotifier;
    }

    public int getCurrentConnectionType() {
        return this.mCurrentConnectionType;
    }

    public int getCurrentConnectionSubtype() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return 0;
        }
        return networkChangeNotifierAutoDetect.getCurrentConnectionSubtype(networkChangeNotifierAutoDetect.getCurrentNetworkState());
    }

    public double getCurrentMaxBandwidthInMbps() {
        return this.mCurrentMaxBandwidth;
    }

    public int getCurrentDefaultNetId() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return -1;
        }
        return networkChangeNotifierAutoDetect.getDefaultNetId();
    }

    public int[] getCurrentNetworksAndTypes() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        return networkChangeNotifierAutoDetect == null ? new int[0] : networkChangeNotifierAutoDetect.getNetworksAndTypes();
    }

    public static double getMaxBandwidthForConnectionSubtype(int i) {
        return nativeGetMaxBandwidthForConnectionSubtype(i);
    }

    public void addNativeObserver(long j) {
        this.mNativeChangeNotifiers.add(Long.valueOf(j));
    }

    public void removeNativeObserver(long j) {
        this.mNativeChangeNotifiers.remove(Long.valueOf(j));
    }

    public static NetworkChangeNotifier getInstance() {
        return sInstance;
    }

    public static void setAutoDetectConnectivityState(boolean z) {
        getInstance().setAutoDetectConnectivityStateInternal(z, new RegistrationPolicyApplicationStatus());
    }

    public static void registerToReceiveNotificationsAlways() {
        getInstance().setAutoDetectConnectivityStateInternal(true, new RegistrationPolicyAlwaysRegister());
    }

    public static void setAutoDetectConnectivityState(NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy) {
        getInstance().setAutoDetectConnectivityStateInternal(true, registrationPolicy);
    }

    private void destroyAutoDetector() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect != null) {
            networkChangeNotifierAutoDetect.destroy();
            this.mAutoDetector = null;
        }
    }

    private void setAutoDetectConnectivityStateInternal(boolean z, NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy) {
        if (z) {
            if (this.mAutoDetector == null) {
                NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = new NetworkChangeNotifierAutoDetect(new NetworkChangeNotifierAutoDetect.Observer() { // from class: org.chromium.net.NetworkChangeNotifier.1
                    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onConnectionTypeChanged(int i) {
                        NetworkChangeNotifier.this.updateCurrentConnectionType(i);
                    }

                    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onMaxBandwidthChanged(double d) {
                        NetworkChangeNotifier.this.updateCurrentMaxBandwidth(d);
                    }

                    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onNetworkConnect(int i, int i2) {
                        NetworkChangeNotifier.this.notifyObserversOfNetworkConnect(i, i2);
                    }

                    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onNetworkSoonToDisconnect(int i) {
                        NetworkChangeNotifier.this.notifyObserversOfNetworkSoonToDisconnect(i);
                    }

                    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onNetworkDisconnect(int i) {
                        NetworkChangeNotifier.this.notifyObserversOfNetworkDisconnect(i);
                    }

                    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void purgeActiveNetworkList(int[] iArr) {
                        NetworkChangeNotifier.this.notifyObserversToPurgeActiveNetworkList(iArr);
                    }
                }, this.mContext, registrationPolicy);
                this.mAutoDetector = networkChangeNotifierAutoDetect;
                NetworkChangeNotifierAutoDetect.NetworkState currentNetworkState = networkChangeNotifierAutoDetect.getCurrentNetworkState();
                updateCurrentConnectionType(this.mAutoDetector.getCurrentConnectionType(currentNetworkState));
                updateCurrentMaxBandwidth(this.mAutoDetector.getCurrentMaxBandwidthInMbps(currentNetworkState));
                return;
            }
            return;
        }
        destroyAutoDetector();
    }

    public static void forceConnectivityState(boolean z) {
        setAutoDetectConnectivityState(false);
        getInstance().forceConnectivityStateInternal(z);
    }

    private void forceConnectivityStateInternal(boolean z) {
        if ((this.mCurrentConnectionType != 6) != z) {
            updateCurrentConnectionType(z ? 0 : 6);
            updateCurrentMaxBandwidth(z ? Double.POSITIVE_INFINITY : 0.0d);
        }
    }

    public static void fakeNetworkConnected(int i, int i2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkConnect(i, i2);
    }

    public static void fakeNetworkSoonToBeDisconnected(int i) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkSoonToDisconnect(i);
    }

    public static void fakeNetworkDisconnected(int i) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkDisconnect(i);
    }

    public static void fakePurgeActiveNetworkList(int[] iArr) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversToPurgeActiveNetworkList(iArr);
    }

    public static void fakeDefaultNetwork(int i, int i2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionTypeChange(i2, i);
    }

    public static void fakeMaxBandwidthChanged(double d) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfMaxBandwidthChange(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentConnectionType(int i) {
        this.mCurrentConnectionType = i;
        notifyObserversOfConnectionTypeChange(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentMaxBandwidth(double d) {
        if (d == this.mCurrentMaxBandwidth && this.mCurrentConnectionType == this.mMaxBandwidthConnectionType) {
            return;
        }
        this.mCurrentMaxBandwidth = d;
        this.mMaxBandwidthConnectionType = this.mCurrentConnectionType;
        notifyObserversOfMaxBandwidthChange(d);
    }

    void notifyObserversOfConnectionTypeChange(int i) {
        notifyObserversOfConnectionTypeChange(i, getCurrentDefaultNetId());
    }

    private void notifyObserversOfConnectionTypeChange(int i, int i2) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(it.next().longValue(), i, i2);
        }
        Iterator<ConnectionTypeObserver> it2 = this.mConnectionTypeObservers.iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionTypeChanged(i);
        }
    }

    void notifyObserversOfMaxBandwidthChange(double d) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            nativeNotifyMaxBandwidthChanged(it.next().longValue(), d);
        }
    }

    void notifyObserversOfNetworkConnect(int i, int i2) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkConnect(it.next().longValue(), i, i2);
        }
    }

    void notifyObserversOfNetworkSoonToDisconnect(int i) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkSoonToDisconnect(it.next().longValue(), i);
        }
    }

    void notifyObserversOfNetworkDisconnect(int i) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkDisconnect(it.next().longValue(), i);
        }
    }

    void notifyObserversToPurgeActiveNetworkList(int[] iArr) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            nativeNotifyPurgeActiveNetworkList(it.next().longValue(), iArr);
        }
    }

    public static void addConnectionTypeObserver(ConnectionTypeObserver connectionTypeObserver) {
        getInstance().addConnectionTypeObserverInternal(connectionTypeObserver);
    }

    private void addConnectionTypeObserverInternal(ConnectionTypeObserver connectionTypeObserver) {
        this.mConnectionTypeObservers.addObserver(connectionTypeObserver);
    }

    public static void removeConnectionTypeObserver(ConnectionTypeObserver connectionTypeObserver) {
        getInstance().removeConnectionTypeObserverInternal(connectionTypeObserver);
    }

    private void removeConnectionTypeObserverInternal(ConnectionTypeObserver connectionTypeObserver) {
        this.mConnectionTypeObservers.removeObserver(connectionTypeObserver);
    }

    public static NetworkChangeNotifierAutoDetect getAutoDetectorForTest() {
        return getInstance().mAutoDetector;
    }

    public static boolean isOnline() {
        return getInstance().getCurrentConnectionType() != 6;
    }
}
