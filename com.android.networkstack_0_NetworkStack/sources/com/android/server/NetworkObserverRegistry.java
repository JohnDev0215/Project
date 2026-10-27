package com.android.server;

import android.net.INetd;
import android.net.INetdUnsolicitedEventListener;
import android.net.InetAddresses;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.RouteInfo;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NetworkObserverRegistry extends INetdUnsolicitedEventListener.Stub {
    private static final String TAG = NetworkObserverRegistry.class.getSimpleName();
    private final ConcurrentHashMap<NetworkObserver, Optional<Handler>> mObservers = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    interface NetworkObserverEventCallback {
        void sendCallback(NetworkObserver networkObserver);
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public String getInterfaceHash() {
        return "63adaa5098e4d8621e90c5a84f7cb93505c79311";
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public int getInterfaceVersion() {
        return 4;
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onStrictCleartextDetected(int i, String str) {
    }

    public void register(INetd iNetd) throws RemoteException {
        iNetd.registerUnsolicitedEventListener(this);
    }

    public void registerObserverForNonblockingCallback(NetworkObserver networkObserver) {
        this.mObservers.put(networkObserver, Optional.empty());
    }

    public void unregisterObserver(NetworkObserver networkObserver) {
        this.mObservers.remove(networkObserver);
    }

    private void invokeForAllObservers(final NetworkObserverEventCallback networkObserverEventCallback) {
        for (Map.Entry<NetworkObserver, Optional<Handler>> entry : this.mObservers.entrySet()) {
            final NetworkObserver key = entry.getKey();
            Optional<Handler> value = entry.getValue();
            if (value.isPresent()) {
                value.get().post(new Runnable() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$H4HK5CAHD8Ri-PWQITzTDllgbL4
                    @Override // java.lang.Runnable
                    public final void run() {
                        networkObserverEventCallback.sendCallback(key);
                    }
                });
                return;
            } else {
                try {
                    networkObserverEventCallback.sendCallback(key);
                } catch (RuntimeException e) {
                    Log.e(TAG, "Error sending callback to observer", e);
                }
            }
        }
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceClassActivityChanged(final boolean z, final int i, final long j, final int i2) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$fcEOcHlx-3mGqmmxRXUf2YAdCs0
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceClassActivityChanged(z, i, j, i2);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onQuotaLimitReached(final String str, final String str2) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$815I-TXsLeTAxmrzAqYtqGfAqVg
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onQuotaLimitReached(str, str2);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceDnsServerInfo(final String str, final long j, final String[] strArr) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$34jsWTE0-YzFSky0b3w6EbkYk7M
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceDnsServerInfo(str, j, strArr);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceAddressUpdated(String str, final String str2, int i, int i2) {
        final LinkAddress linkAddress = new LinkAddress(str, i, i2);
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$oJaQ4CVyRqydS6yisqflfNfbo7Q
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceAddressUpdated(linkAddress, str2);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceAddressRemoved(String str, final String str2, int i, int i2) {
        final LinkAddress linkAddress = new LinkAddress(str, i, i2);
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$cNchMEkfuWKCf_o2aUKAsODVRa8
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceAddressRemoved(linkAddress, str2);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceAdded(final String str) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$grqMVS3HQMFFTPGP0XEs18mEdBU
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceAdded(str);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceRemoved(final String str) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$NlFbtfr_35xW3_TGFF6cMmduLrI
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceRemoved(str);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceChanged(final String str, final boolean z) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$QLVE3TUrVjZh61B27gpY4vNXP2s
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceChanged(str, z);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceLinkStateChanged(final String str, final boolean z) {
        invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$Q7H1NC1hN07wYLtZvR9DmONSxWg
            @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
            public final void sendCallback(NetworkObserver networkObserver) {
                networkObserver.onInterfaceLinkStateChanged(str, z);
            }
        });
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onRouteChanged(boolean z, String str, String str2, String str3) {
        final RouteInfo routeInfo = new RouteInfo(new IpPrefix(str), "".equals(str2) ? null : InetAddresses.parseNumericAddress(str2), str3, 1);
        if (z) {
            invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$RCPZvFLbsGihvWKvZ4iE71kq8D8
                @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
                public final void sendCallback(NetworkObserver networkObserver) {
                    networkObserver.onRouteUpdated(routeInfo);
                }
            });
        } else {
            invokeForAllObservers(new NetworkObserverEventCallback() { // from class: com.android.server.-$$Lambda$NetworkObserverRegistry$PUSn88ltcws_eZ2MBwzioYlxRb8
                @Override // com.android.server.NetworkObserverRegistry.NetworkObserverEventCallback
                public final void sendCallback(NetworkObserver networkObserver) {
                    networkObserver.onRouteRemoved(routeInfo);
                }
            });
        }
    }
}
