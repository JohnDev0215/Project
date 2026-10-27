package com.amazon.bolthttp;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.amazon.bolthttp.internal.AndroidLogEmitter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class BoltConfig {
    private final ConnectivityManager mConnectivityManager;
    private final long mKeepAliveDurationMs;
    private final LogEmitter mLogEmitter;
    private final String mLogTag;
    private final LoggingLevel mLoggingLevel;
    private final int mMaxIdleConnections;
    private final NetworkStrategy mNetworkStrategy;
    private final Proxy mProxy;
    private final RequestStrategy mRequestStrategy;
    private final ThreadingModel mThreadingModel;
    private final boolean mUseCustomConnectionPool;
    private final WifiManager mWifiManager;

    public enum LoggingLevel {
        SILENT(0),
        MINIMAL(1),
        STANDARD(2),
        VERBOSE(3);

        public final int level;

        LoggingLevel(int i) {
            this.level = i;
        }
    }

    private BoltConfig(Builder builder) {
        this.mThreadingModel = builder.mThreadingModel;
        this.mLoggingLevel = builder.mLoggingLevel;
        this.mLogTag = builder.mLogTag;
        this.mConnectivityManager = builder.mConnectivityManager;
        this.mRequestStrategy = builder.mRequestStrategy;
        this.mNetworkStrategy = builder.mNetworkStrategy;
        this.mWifiManager = builder.mWifiManager;
        this.mLogEmitter = builder.mLogEmitter;
        this.mProxy = builder.mProxy;
        this.mUseCustomConnectionPool = builder.mUseCustomConnectionPool;
        this.mMaxIdleConnections = builder.mMaxIdleConnections;
        this.mKeepAliveDurationMs = builder.mKeepAliveDurationMs;
    }

    @Nonnull
    public ThreadingModel getThreadingModel() {
        return this.mThreadingModel;
    }

    @Nonnull
    public LoggingLevel getLoggingLevel() {
        return this.mLoggingLevel;
    }

    @Nonnull
    public String getLogTag() {
        return this.mLogTag;
    }

    @Nonnull
    public LogEmitter getLogEmitter() {
        return this.mLogEmitter;
    }

    @Nonnull
    public RequestStrategy getRequestStrategy() {
        return this.mRequestStrategy;
    }

    @Nonnull
    public NetworkStrategy getNetworkStrategy() {
        return this.mNetworkStrategy;
    }

    @Nullable
    public ConnectivityManager getConnectivityManager() {
        return this.mConnectivityManager;
    }

    @Nullable
    public WifiManager getWifiManager() {
        return this.mWifiManager;
    }

    @Nullable
    public Proxy getProxy() {
        return this.mProxy;
    }

    public boolean useCustomConnectionPool() {
        return this.mUseCustomConnectionPool;
    }

    public int getMaxIdleConnections() {
        return this.mMaxIdleConnections;
    }

    public long getKeepAliveDurationMillis() {
        return this.mKeepAliveDurationMs;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ConnectivityManager mConnectivityManager;
        private long mKeepAliveDurationMs;
        private LogEmitter mLogEmitter;
        private String mLogTag;
        private LoggingLevel mLoggingLevel;
        private int mMaxIdleConnections;
        private NetworkStrategy mNetworkStrategy;
        private Proxy mProxy;
        private RequestStrategy mRequestStrategy;
        private ThreadingModel mThreadingModel;
        private boolean mUseCustomConnectionPool;
        private WifiManager mWifiManager;

        private Builder() {
            this.mLoggingLevel = LoggingLevel.STANDARD;
            this.mLogTag = "BoltHttp";
            this.mLogEmitter = new AndroidLogEmitter();
            this.mRequestStrategy = RequestStrategy.builder().build();
            this.mNetworkStrategy = NetworkStrategy.builder().build();
            this.mUseCustomConnectionPool = false;
            this.mMaxIdleConnections = 5;
            this.mKeepAliveDurationMs = TimeUnit.MINUTES.toMillis(5L);
        }

        public Builder setThreadingModel(@Nonnull ThreadingModel threadingModel) {
            this.mThreadingModel = (ThreadingModel) Preconditions.checkNotNull(threadingModel, "threadingModel");
            return this;
        }

        public Builder setLoggingLevel(@Nonnull LoggingLevel loggingLevel) {
            this.mLoggingLevel = (LoggingLevel) Preconditions.checkNotNull(loggingLevel, "loggingLevel");
            return this;
        }

        public Builder setLogTag(@Nonnull String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "logTag");
            this.mLogTag = str;
            return this;
        }

        public Builder setConnectivityManager(@Nullable ConnectivityManager connectivityManager) {
            this.mConnectivityManager = connectivityManager;
            return this;
        }

        public Builder setWifiManager(@Nullable WifiManager wifiManager) {
            this.mWifiManager = wifiManager;
            return this;
        }

        public Builder setProxy(@Nullable Proxy proxy) {
            this.mProxy = proxy;
            return this;
        }

        @Nonnull
        public BoltConfig build() {
            Preconditions.checkNotNull(this.mThreadingModel, "setThreadingModel() never called");
            return new BoltConfig(this);
        }
    }
}
