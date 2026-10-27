package com.amazon.avod.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.amazon.avod.config.FrameworkDebugConfig;
import com.amazon.avod.core.Framework;
import com.amazon.avod.perf.MetricsDebugger;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.BoltHttpClient;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceClient {
    private Supplier<BoltHttpClient> mHttpClient;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);

    private static class SingletonHolder {
        private static volatile ServiceClient sInstance = new ServiceClient();

        private SingletonHolder() {
        }
    }

    public static ServiceClient getInstance() {
        return SingletonHolder.sInstance;
    }

    ServiceClient() {
    }

    public void initialize(@Nonnull final Context context) {
        Preconditions.checkNotNull(context, "context");
        this.mInitializationLatch.start(120L, TimeUnit.SECONDS);
        this.mHttpClient = Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.http.ServiceClient$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return this.f$0.lambda$initialize$0(context);
            }
        });
        MetricsDebugger.getInstance().initialize(FrameworkDebugConfig.getInstance().isNetworkCallDebuggingEnabled());
        this.mInitializationLatch.complete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BoltHttpClient lambda$initialize$0(Context context) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "CreateBoltHttpClient");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        BoltHttpClient boltHttpClient = new BoltHttpClient(BoltConfig.builder().setThreadingModel(new ProfiledThreadingModel()).setLoggingLevel(BoltConfig.LoggingLevel.STANDARD).setLogTag("AmazonVideo.http").setConnectivityManager(connectivityManager).setWifiManager(wifiManager).setProxy(isConnectedToIlawNetwork(wifiManager) ? createILAWHttpProxy() : null).build());
        Profiler.endTrace(traceKeyBeginTrace);
        return boltHttpClient;
    }

    private static boolean isConnectedToIlawNetwork(@Nonnull WifiManager wifiManager) {
        WifiInfo connectionInfo;
        return Framework.isDebugConfigurationEnabled() && (connectionInfo = wifiManager.getConnectionInfo()) != null && connectionInfo.getSSID().equalsIgnoreCase("\"ilaw\"");
    }

    @Nonnull
    private Proxy createILAWHttpProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress("ilaw-proxy.iad.corp.amazon.com", 3128));
    }

    public void waitOnInitialized() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }

    public <T> Response<T> executeSync(Request<T> request) {
        return this.mHttpClient.get().executeSync(request);
    }
}
