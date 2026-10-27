package android.net;

import android.content.Context;
import android.net.ipmemorystore.Blob;
import android.net.ipmemorystore.NetworkAttributes;
import android.net.ipmemorystore.OnBlobRetrievedListener;
import android.net.ipmemorystore.OnDeleteStatusListener;
import android.net.ipmemorystore.OnL2KeyResponseListener;
import android.net.ipmemorystore.OnNetworkAttributesRetrievedListener;
import android.net.ipmemorystore.OnSameL3NetworkResponseListener;
import android.net.ipmemorystore.OnStatusListener;
import android.net.ipmemorystore.Status;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public abstract class IpMemoryStoreClient {
    private static final String TAG = IpMemoryStoreClient.class.getSimpleName();
    private final Context mContext;

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
        this.mContext = context;
    }

    private void ignoringRemoteException(ThrowingRunnable r) {
        ignoringRemoteException("Failed to execute remote procedure call", r);
    }

    private void ignoringRemoteException(String message, ThrowingRunnable r) {
        try {
            r.run();
        } catch (RemoteException e) {
            Log.e(TAG, message, e);
        }
    }

    public /* synthetic */ void lambda$storeNetworkAttributes$1$IpMemoryStoreClient(final String l2Key, final NetworkAttributes attributes, final OnStatusListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$4LLLcxcDI48Nnc_rkm7mdSQsa2U
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.storeNetworkAttributes(l2Key, attributes.toParcelable(), OnStatusListener.toAIDL(listener));
            }
        });
    }

    public void storeNetworkAttributes(final String l2Key, final NetworkAttributes attributes, final OnStatusListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$0LhXdcPG7yJtV5UggjyJkRbARKU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$storeNetworkAttributes$1$IpMemoryStoreClient(l2Key, attributes, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            if (listener == null) {
                return;
            }
            ignoringRemoteException("Error storing network attributes", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$FjB7dm6lAwZ6pH1lqvrhxtLFOm8
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onComplete(new Status(-5));
                }
            });
        }
    }

    public /* synthetic */ void lambda$storeBlob$4$IpMemoryStoreClient(final String l2Key, final String clientId, final String name, final Blob data, final OnStatusListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$4eqT-tDGA25PNMyU_1yqQCF2gOo
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.storeBlob(l2Key, clientId, name, data, OnStatusListener.toAIDL(listener));
            }
        });
    }

    public void storeBlob(final String l2Key, final String clientId, final String name, final Blob data, final OnStatusListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$OI4Zw2djhZoG0D4IE2ujC0Iv6G4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$storeBlob$4$IpMemoryStoreClient(l2Key, clientId, name, data, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            if (listener == null) {
                return;
            }
            ignoringRemoteException("Error storing blob", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$Rs7okZ0ViR35WkNSGbyhqEXxJxc
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onComplete(new Status(-5));
                }
            });
        }
    }

    public void findL2Key(final NetworkAttributes attributes, final OnL2KeyResponseListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$uI7nYxd7GfJucRXO9KcNTbbWOlc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$findL2Key$7$IpMemoryStoreClient(attributes, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            ignoringRemoteException("Error finding L2 Key", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$xx1upXTRGTVc0003KEhaxwIwwN8
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onL2KeyResponse(new Status(-5), null);
                }
            });
        }
    }

    public /* synthetic */ void lambda$findL2Key$7$IpMemoryStoreClient(final NetworkAttributes attributes, final OnL2KeyResponseListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$2bQLFhsJeYf5bkZg0-91OSOTEJY
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.findL2Key(attributes.toParcelable(), OnL2KeyResponseListener.toAIDL(listener));
            }
        });
    }

    public void isSameNetwork(final String l2Key1, final String l2Key2, final OnSameL3NetworkResponseListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$uHUebZ3pZ1jD5N1tZNdyTy8zBCE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$isSameNetwork$10$IpMemoryStoreClient(l2Key1, l2Key2, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            ignoringRemoteException("Error checking for network sameness", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$V28n1xp79cKTZf0npS-vzf7FUo8
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onSameL3NetworkResponse(new Status(-5), null);
                }
            });
        }
    }

    public /* synthetic */ void lambda$isSameNetwork$10$IpMemoryStoreClient(final String l2Key1, final String l2Key2, final OnSameL3NetworkResponseListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$A2hOjZriLOXFq3Aij0wHaYZQOSc
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.isSameNetwork(l2Key1, l2Key2, OnSameL3NetworkResponseListener.toAIDL(listener));
            }
        });
    }

    public /* synthetic */ void lambda$retrieveNetworkAttributes$13$IpMemoryStoreClient(final String l2Key, final OnNetworkAttributesRetrievedListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$Uc0QFR5a_MhzwuvUoWpz73NAAEs
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.retrieveNetworkAttributes(l2Key, OnNetworkAttributesRetrievedListener.toAIDL(listener));
            }
        });
    }

    public void retrieveNetworkAttributes(final String l2Key, final OnNetworkAttributesRetrievedListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$OnrcybvxwSrQUBY_VqGsD_5lQfI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$retrieveNetworkAttributes$13$IpMemoryStoreClient(l2Key, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            ignoringRemoteException("Error retrieving network attributes", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$JTvBo0T3ntOmEDS60qZyBJUlJio
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onNetworkAttributesRetrieved(new Status(-5), null, null);
                }
            });
        }
    }

    public /* synthetic */ void lambda$retrieveBlob$16$IpMemoryStoreClient(final String l2Key, final String clientId, final String name, final OnBlobRetrievedListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$284VFgqq7BBkkwVNFLIrF3c59Es
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.retrieveBlob(l2Key, clientId, name, OnBlobRetrievedListener.toAIDL(listener));
            }
        });
    }

    public void retrieveBlob(final String l2Key, final String clientId, final String name, final OnBlobRetrievedListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$3VeddAdCuqfXquVC2DlGvI3eVPM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$retrieveBlob$16$IpMemoryStoreClient(l2Key, clientId, name, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            ignoringRemoteException("Error retrieving blob", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$hPxh-gsDi3P-N7OFwwZBxGXYZTs
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onBlobRetrieved(new Status(-5), null, null, null);
                }
            });
        }
    }

    public void delete(final String l2Key, final boolean needWipe, final OnDeleteStatusListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$a8c2GwoXcCl_-sEog7RVlSk_ceg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$delete$19$IpMemoryStoreClient(l2Key, needWipe, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            if (listener == null) {
                return;
            }
            ignoringRemoteException("Error deleting from the memory store", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$QtZ59p2ym62gf590YlnfhqMCuvs
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onComplete(new Status(-5), 0);
                }
            });
        }
    }

    public /* synthetic */ void lambda$delete$19$IpMemoryStoreClient(final String l2Key, final boolean needWipe, final OnDeleteStatusListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$EROyQL0gB8UDAPPZPb6g61OzIOY
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.delete(l2Key, needWipe, OnDeleteStatusListener.toAIDL(listener));
            }
        });
    }

    public void deleteCluster(final String cluster, final boolean needWipe, final OnDeleteStatusListener listener) {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$HZ4eDRlSSn3cUdwF2DXBPHIJgvY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$deleteCluster$22$IpMemoryStoreClient(cluster, needWipe, listener, (IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException e) {
            if (listener == null) {
                return;
            }
            ignoringRemoteException("Error deleting from the memory store", new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$L08s_WnZzlkHdu2WoxmH38FuUQg
                @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
                public final void run() {
                    listener.onComplete(new Status(-5), 0);
                }
            });
        }
    }

    public /* synthetic */ void lambda$deleteCluster$22$IpMemoryStoreClient(final String cluster, final boolean needWipe, final OnDeleteStatusListener listener, final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$idEqoYRvV5eEJx3wft06F3h5j5k
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.deleteCluster(cluster, needWipe, OnDeleteStatusListener.toAIDL(listener));
            }
        });
    }

    public void factoryReset() {
        try {
            runWhenServiceReady(new Consumer() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$HDF9y-EeiuVlo2_HJE-8sOk7nMk
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$factoryReset$25$IpMemoryStoreClient((IIpMemoryStore) obj);
                }
            });
        } catch (ExecutionException m) {
            Log.e(TAG, "Error executing factory reset", m);
        }
    }

    public /* synthetic */ void lambda$factoryReset$25$IpMemoryStoreClient(final IIpMemoryStore service) {
        ignoringRemoteException(new ThrowingRunnable() { // from class: android.net.-$$Lambda$IpMemoryStoreClient$6cTsI8p06zitBaVklP4K-8kixMc
            @Override // android.net.IpMemoryStoreClient.ThrowingRunnable
            public final void run() throws RemoteException {
                service.factoryReset();
            }
        });
    }
}
