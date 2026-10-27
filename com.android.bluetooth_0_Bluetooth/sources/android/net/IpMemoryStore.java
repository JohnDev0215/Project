package android.net;

import android.content.Context;
import android.net.IIpMemoryStoreCallbacks;
import android.net.networkstack.ModuleNetworkStackClient;
import android.util.Log;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes.dex */
public class IpMemoryStore extends IpMemoryStoreClient {
    private static final String TAG = IpMemoryStore.class.getSimpleName();
    private final CompletableFuture<IIpMemoryStore> mService;
    private final AtomicReference<CompletableFuture<IIpMemoryStore>> mTailNode;

    public IpMemoryStore(Context context) {
        super(context);
        this.mService = new CompletableFuture<>();
        this.mTailNode = new AtomicReference<>(this.mService);
        getModuleNetworkStackClient(context).fetchIpMemoryStore(new IIpMemoryStoreCallbacks.Stub() { // from class: android.net.IpMemoryStore.1
            @Override // android.net.IIpMemoryStoreCallbacks
            public void onIpMemoryStoreFetched(IIpMemoryStore memoryStore) {
                IpMemoryStore.this.mService.complete(memoryStore);
            }

            @Override // android.net.IIpMemoryStoreCallbacks
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.IIpMemoryStoreCallbacks
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }
        });
    }

    @Override // android.net.IpMemoryStoreClient
    protected void runWhenServiceReady(final Consumer<IIpMemoryStore> cb) throws ExecutionException {
        this.mTailNode.getAndUpdate(new UnaryOperator() { // from class: android.net.-$$Lambda$IpMemoryStore$LPW97BoNSL4rh_RVPiAHfCbmGHU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CompletableFuture) obj).handle(new BiFunction() { // from class: android.net.-$$Lambda$IpMemoryStore$pFctTFAvh_Nxb_aTb0gjNsixGeM
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj2, Object obj3) {
                        return IpMemoryStore.lambda$runWhenServiceReady$0(consumer, (IIpMemoryStore) obj2, (Throwable) obj3);
                    }
                });
            }
        });
    }

    static /* synthetic */ IIpMemoryStore lambda$runWhenServiceReady$0(Consumer cb, IIpMemoryStore store, Throwable exception) {
        if (exception != null) {
            Log.wtf(TAG, "Error fetching IpMemoryStore", exception);
            return store;
        }
        try {
            cb.accept(store);
        } catch (Exception e) {
            Log.wtf(TAG, "Exception occured: " + e.getMessage());
        }
        return store;
    }

    protected ModuleNetworkStackClient getModuleNetworkStackClient(Context context) {
        return ModuleNetworkStackClient.getInstance(context);
    }

    public static IpMemoryStore getMemoryStore(Context context) {
        return new IpMemoryStore(context);
    }
}
