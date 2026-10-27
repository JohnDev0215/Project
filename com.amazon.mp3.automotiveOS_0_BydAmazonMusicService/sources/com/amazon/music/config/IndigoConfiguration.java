package com.amazon.music.config;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class IndigoConfiguration implements Configuration {
    private static final Logger LOG = LoggerFactory.getLogger(IndigoConfiguration.class.getSimpleName());
    private Attributes mApplicationAttributes;
    private final Context mContext;
    private Endpoint mCurrentEndpoint;
    private Thread mLoaderThread;
    private IndigoLocalEndpoint mLocalEndpoint;
    private ConfigurationLoader mRemoteConfigLoader;
    private IndigoRemoteEndpoint mRemoteEndpoint;
    private Thread mRemoteEndpointThread;
    private volatile boolean isWeblabControllerInitComplete = false;
    private volatile boolean isUserInfoReady = false;
    private long mLastRemoteLoadAttempt = 0;
    private CountDownLatch mLoaderThreadFinishedLatch = new CountDownLatch(0);
    private final Object mLoaderThreadStateLock = new Object();
    private final Object mCurrentEndpointLock = new Object();

    public interface Endpoint {
        String getValue(String str);
    }

    IndigoConfiguration(Context context, ConfigurationLoader configurationLoader, Attributes attributes) {
        this.mLocalEndpoint = null;
        this.mRemoteEndpoint = null;
        this.mContext = (Context) Validate.notNull(context, "context cannot be null", new Object[0]);
        this.mRemoteConfigLoader = (ConfigurationLoader) Validate.notNull(configurationLoader, "remoteConfigLoader cannot be null", new Object[0]);
        this.mApplicationAttributes = (Attributes) Validate.notNull(attributes, "applicationAttributes cannot be null", new Object[0]);
        this.mLocalEndpoint = new IndigoLocalEndpoint(context, configurationLoader, attributes);
        this.mRemoteEndpoint = new IndigoRemoteEndpoint(context, configurationLoader, attributes);
        setEndpoint(this.mLocalEndpoint);
    }

    @Override // com.amazon.music.config.Configuration
    public void reloadRemoteEndpoint() {
        try {
            waitForLoaderThread();
            reload();
        } catch (InterruptedException unused) {
            LOG.info("Remote endpoint wait interrupted, reload will not proceed");
        }
    }

    @Override // com.amazon.music.config.Configuration
    public String getString(String str) {
        return getString(str, null);
    }

    @Override // com.amazon.music.config.Configuration
    public String getString(String str, String str2) {
        try {
            String value = getValue(str);
            return value == null ? str2 : value;
        } catch (InterruptedException unused) {
            LOG.info("InterruptedException waiting for endpoint to load, returning default value");
            return str2;
        }
    }

    @Override // com.amazon.music.config.Configuration
    public int getInteger(String str, int i) {
        try {
            try {
                return Integer.parseInt(getValue(str));
            } catch (InterruptedException unused) {
                LOG.info("InterruptedException waiting for endpoint to load, returning default value");
                return i;
            }
        } catch (NumberFormatException unused2) {
            return i;
        }
    }

    @Override // com.amazon.music.config.Configuration
    public long getLong(String str, long j) {
        try {
            try {
                return Long.parseLong(getValue(str));
            } catch (InterruptedException unused) {
                LOG.info("InterruptedException waiting for endpoint to load, returning default value");
                return j;
            }
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    @Override // com.amazon.music.config.Configuration
    public String getEndpoint() {
        return isRemoteEndpointLoaded() ? "remote" : ImagesContract.LOCAL;
    }

    @Override // com.amazon.music.config.Configuration
    public String getStage() {
        return isRemoteEndpointLoaded() ? IndigoRemoteEndpoint.getStage() : IndigoLocalEndpoint.getStage();
    }

    @Override // com.amazon.music.config.Configuration
    public boolean isRemoteEndpointLoaded() {
        boolean z;
        synchronized (this.mCurrentEndpointLock) {
            z = this.mCurrentEndpoint == this.mRemoteEndpoint;
        }
        return z;
    }

    private String getValue(String str) throws InterruptedException {
        String value;
        synchronized (this.mCurrentEndpointLock) {
            if (this.isUserInfoReady && this.isWeblabControllerInitComplete) {
                refreshRemoteEndpointIfStale();
            }
            value = this.mCurrentEndpoint.getValue(str);
        }
        return value;
    }

    private synchronized boolean refreshRemoteEndpointIfStale() {
        Runnable runnable = new Runnable() { // from class: com.amazon.music.config.IndigoConfiguration.1
            @Override // java.lang.Runnable
            public void run() {
                IndigoConfiguration.this.reload();
                IndigoConfiguration.this.mRemoteEndpointThread = null;
            }
        };
        if (this.mRemoteEndpointThread != null) {
            return false;
        }
        if (!this.mRemoteEndpoint.loaded() || this.mRemoteEndpoint.expired(true)) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.mLastRemoteLoadAttempt > CoroutineLiveDataKt.DEFAULT_TIMEOUT) {
                this.mLastRemoteLoadAttempt = jCurrentTimeMillis;
                Thread thread = new Thread(runnable);
                this.mRemoteEndpointThread = thread;
                thread.setName("IndigoConfiguration.RemoteEndpointThread");
                this.mRemoteEndpointThread.setPriority(1);
                this.mRemoteEndpointThread.start();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reload() {
        synchronized (this.mLoaderThreadStateLock) {
            if (this.mLoaderThread != null) {
                return;
            }
            this.mLoaderThreadFinishedLatch = new CountDownLatch(1);
            LoaderThread loaderThread = new LoaderThread();
            this.mLoaderThread = loaderThread;
            loaderThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndpoint(Endpoint endpoint) {
        synchronized (this.mCurrentEndpointLock) {
            if (endpoint == this.mCurrentEndpoint) {
                return;
            }
            this.mCurrentEndpoint = endpoint;
            if (endpoint == this.mRemoteEndpoint) {
                this.mContext.sendBroadcast(new Intent("com.amazon.mp3.configuration.REMOTE_CONFIG_LOADED"));
                LOG.info("active endpoint = remote");
            } else {
                if (endpoint == this.mLocalEndpoint) {
                    this.mContext.sendBroadcast(new Intent("com.amazon.mp3.configuration.LOCAL_CONFIG_LOADED"));
                    LOG.info("active endpoint = local");
                    return;
                }
                throw new RuntimeException("IndigoConfiguration.setActiveEndpoint(endpoint): endpoint == null!");
            }
        }
    }

    private void waitForLoaderThread() throws InterruptedException {
        synchronized (this.mLoaderThreadStateLock) {
            try {
                try {
                    this.mLoaderThreadFinishedLatch.await();
                } catch (InterruptedException e) {
                    LOG.warn("waitForRemoteEndpoint() latch await interrupted!");
                    throw new RuntimeException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private class LoaderThread extends Thread {
        public LoaderThread() {
            setName("IndigoConfiguration.LoaderThread");
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0073  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 349
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.config.IndigoConfiguration.LoaderThread.run():void");
        }
    }

    @Override // com.amazon.music.config.Configuration
    public void clearConfiguration() {
        IndigoRemoteEndpoint.clear();
    }

    public void setIsUserInfoReady(boolean z) {
        this.isUserInfoReady = z;
    }

    public void setIsWebLabControllerInitComplete(boolean z) {
        this.isWeblabControllerInitComplete = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlatformAndroidMarket() {
        return "Android".equals(this.mApplicationAttributes.getPlatformName());
    }
}
