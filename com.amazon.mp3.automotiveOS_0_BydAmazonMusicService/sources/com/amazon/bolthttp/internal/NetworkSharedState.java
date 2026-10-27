package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.internal.net.ConnectivityChecker;
import com.amazon.bolthttp.internal.net.WifiLockHolder;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class NetworkSharedState extends BaseSharedState<Affinity$NetworkAffinity> {
    private final ConnectivityChecker mConnectivityChecker;
    private final OkHttpClient mOkHttpClient;
    private final WifiLockHolder mWifiLockHolder;

    @Override // com.amazon.bolthttp.internal.BaseSharedState, com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public /* bridge */ /* synthetic */ BoltConfig getConfig() {
        return super.getConfig();
    }

    @Override // com.amazon.bolthttp.internal.BaseSharedState, com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public /* bridge */ /* synthetic */ Dispatcher getDispatcher() {
        return super.getDispatcher();
    }

    @Override // com.amazon.bolthttp.internal.BaseSharedState, com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public /* bridge */ /* synthetic */ Logger getLogger() {
        return super.getLogger();
    }

    NetworkSharedState(@Nonnull Dispatcher dispatcher, @Nonnull BoltConfig boltConfig) {
        super(dispatcher, boltConfig);
        OkHttpClient.Builder builderNewBuilder = new OkHttpClient().newBuilder();
        if (boltConfig.useCustomConnectionPool()) {
            builderNewBuilder.connectionPool(new ConnectionPool(boltConfig.getMaxIdleConnections(), boltConfig.getKeepAliveDurationMillis(), TimeUnit.MILLISECONDS));
        }
        if (boltConfig.getProxy() != null) {
            builderNewBuilder.proxy(boltConfig.getProxy());
        }
        this.mOkHttpClient = builderNewBuilder.build();
        this.mConnectivityChecker = new ConnectivityChecker(boltConfig, getLogger());
        this.mWifiLockHolder = new WifiLockHolder(getConfig());
    }

    @Nonnull
    public OkHttpClient.Builder newOkHttpClientBuilder() {
        return this.mOkHttpClient.newBuilder();
    }

    @Nonnull
    public ConnectivityChecker getConnectivityChecker() {
        return this.mConnectivityChecker;
    }

    @Nonnull
    public WifiLockHolder getWifiLockHolder() {
        return this.mWifiLockHolder;
    }
}
