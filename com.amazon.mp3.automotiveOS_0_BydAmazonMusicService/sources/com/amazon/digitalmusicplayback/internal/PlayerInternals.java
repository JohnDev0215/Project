package com.amazon.digitalmusicplayback.internal;

import com.amazon.digitalmusicplayback.AuthenticationListener;
import com.amazon.digitalmusicplayback.ConcurrencyListener;
import com.amazon.digitalmusicplayback.HttpClientFactory;
import com.amazon.digitalmusicplayback.LoggingListener;
import com.amazon.digitalmusicplayback.MetricsAttributesProvider;
import com.amazon.digitalmusicplayback.MetricsListener;
import com.amazon.digitalmusicplayback.NetworkStatusProvider;
import com.amazon.digitalmusicplayback.PlaybackListener;
import com.amazon.digitalmusicplayback.Player;
import com.amazon.digitalmusicplayback.WeblabClient;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlayerInternals {
    public static Player createPlayer(PlaybackListener playbackListener, LoggingListener loggingListener, LoggingSettingsListener loggingSettingsListener, MetricsListener metricsListener, AuthenticationListener authenticationListener, ConcurrencyListener concurrencyListener, NetworkStatusProvider networkStatusProvider, AudioDriver audioDriver, PlatformThreadFactory platformThreadFactory, HttpClientFactory httpClientFactory, WeblabClient weblabClient, MetricsAttributesProvider metricsAttributesProvider, PlatformSystemInformation platformSystemInformation) {
        return CppProxy.createPlayer(playbackListener, loggingListener, loggingSettingsListener, metricsListener, authenticationListener, concurrencyListener, networkStatusProvider, audioDriver, platformThreadFactory, httpClientFactory, weblabClient, metricsAttributesProvider, platformSystemInformation);
    }

    private static final class CppProxy extends PlayerInternals {
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        public static native Player createPlayer(PlaybackListener playbackListener, LoggingListener loggingListener, LoggingSettingsListener loggingSettingsListener, MetricsListener metricsListener, AuthenticationListener authenticationListener, ConcurrencyListener concurrencyListener, NetworkStatusProvider networkStatusProvider, AudioDriver audioDriver, PlatformThreadFactory platformThreadFactory, HttpClientFactory httpClientFactory, WeblabClient weblabClient, MetricsAttributesProvider metricsAttributesProvider, PlatformSystemInformation platformSystemInformation);

        private native void nativeDestroy(long j);

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
        }

        public void _djinni_private_destroy() {
            if (this.destroyed.getAndSet(true)) {
                return;
            }
            nativeDestroy(this.nativeRef);
        }

        protected void finalize() throws Throwable {
            _djinni_private_destroy();
            super.finalize();
        }
    }
}
