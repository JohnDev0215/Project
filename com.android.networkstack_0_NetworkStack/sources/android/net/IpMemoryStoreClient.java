package android.net;

import android.content.Context;
import android.net.ipmemorystore.NetworkAttributes;
import android.net.ipmemorystore.OnNetworkAttributesRetrievedListener;
import android.net.ipmemorystore.OnStatusListener;
import android.net.ipmemorystore.Status;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public abstract class IpMemoryStoreClient {
    private static final String TAG = "IpMemoryStoreClient";

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws RemoteException;
    }

    protected abstract void runWhenServiceReady(Consumer<IIpMemoryStore> consumer) throws ExecutionException;

    public IpMemoryStoreClient(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("missing context");
        }
    }

    private void ignoringRemoteException(ThrowingRunnable throwingRunnable) {
        ignoringRemoteException("Failed to execute remote procedure call", throwingRunnable);
    }

    private void ignoringRemoteException(String str, ThrowingRunnable throwingRunnable) {
        try {
            throwingRunnable.run();
        } catch (RemoteException e) {
            Log.e(TAG, str, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$storeNetworkAttributes$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$storeNetworkAttributes$1$IpMemoryStoreClient(final String str, final NetworkAttributes networkAttributes, final OnStatusListener onStatusListener, final IIpMemoryStore iIpMemoryStore) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$4LLLcxcDI48Nnc_rkm7mdSQsa2U
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                iIpMemoryStore.storeNetworkAttributes(str, networkAttributes.toParcelable(), OnStatusListener.toAIDL(onStatusListener));
            }
        });
    }

    public void storeNetworkAttributes(final String str, final NetworkAttributes networkAttributes, final OnStatusListener onStatusListener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$0LhXdcPG7yJtV5UggjyJkRbARKU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$storeNetworkAttributes$1$IpMemoryStoreClient(str, networkAttributes, onStatusListener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException unused) {
            if (onStatusListener == null) {
                return;
            }
            ignoringRemoteException("Error storing network attributes", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$FjB7dm6lAwZ6pH1lqvrhxtLFOm8
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    onStatusListener.onComplete(new Status(-5));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$retrieveNetworkAttributes$13, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$retrieveNetworkAttributes$13$IpMemoryStoreClient(final String str, final OnNetworkAttributesRetrievedListener onNetworkAttributesRetrievedListener, final IIpMemoryStore iIpMemoryStore) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$Uc0QFR5a_MhzwuvUoWpz73NAAEs
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                iIpMemoryStore.retrieveNetworkAttributes(str, OnNetworkAttributesRetrievedListener.toAIDL(onNetworkAttributesRetrievedListener));
            }
        });
    }

    public void retrieveNetworkAttributes(final String str, final OnNetworkAttributesRetrievedListener onNetworkAttributesRetrievedListener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$OnrcybvxwSrQUBY_VqGsD_5lQfI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$retrieveNetworkAttributes$13$IpMemoryStoreClient(str, onNetworkAttributesRetrievedListener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException unused) {
            ignoringRemoteException("Error retrieving network attributes", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$JTvBo0T3ntOmEDS60qZyBJUlJio
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    onNetworkAttributesRetrievedListener.onNetworkAttributesRetrieved(new Status(-5), null, null);
                }
            });
        }
    }
}
