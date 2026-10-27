package android.net;

import android.content.Context;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class NetworkStackIpMemoryStore extends IpMemoryStoreClient {
    private final IIpMemoryStore mService;

    public NetworkStackIpMemoryStore(Context context, IIpMemoryStore iIpMemoryStore) {
        super(context);
        this.mService = iIpMemoryStore;
    }

    @Override // android.net.IpMemoryStoreClient
    protected void runWhenServiceReady(Consumer<IIpMemoryStore> consumer) throws ExecutionException {
        consumer.accept(this.mService);
    }
}
