package com.amazon.digitalmusicplayback;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.digitalmusicplayback.internal.AndroidSystemInformation;
import com.amazon.digitalmusicplayback.internal.AudioDriverImpl;
import com.amazon.digitalmusicplayback.internal.HttpClientFactoryImpl;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import com.amazon.digitalmusicplayback.internal.NetworkStatusProviderImpl;
import com.amazon.digitalmusicplayback.internal.PlayerInternals;
import com.amazon.digitalmusicplayback.internal.ThreadFactoryImpl;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerFactory {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        try {
            System.loadLibrary("c++_shared");
            System.loadLibrary("widevine_ce_cdm_shared");
            System.loadLibrary("av");
            System.loadLibrary("dmengine");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Error loading library: " + e.toString());
            throw e;
        }
    }

    @Deprecated
    public static Player create(Context context, PlaybackListener playbackListener, LoggingListener loggingListener, MetricsListener metricsListener, AuthenticationListener authenticationListener, ConcurrencyListener concurrencyListener, WeblabClient weblabClient) {
        return create(context, playbackListener, loggingListener, metricsListener, authenticationListener, concurrencyListener, weblabClient, null);
    }

    public static Player create(Context context, PlaybackListener playbackListener, LoggingListener loggingListener, MetricsListener metricsListener, AuthenticationListener authenticationListener, ConcurrencyListener concurrencyListener, WeblabClient weblabClient, @Nullable MetricsAttributesProvider metricsAttributesProvider) {
        LoggingFacade loggingFacade = new LoggingFacade(loggingListener);
        AndroidSystemInformation androidSystemInformation = new AndroidSystemInformation(loggingFacade, context);
        return PlayerInternals.createPlayer(playbackListener, loggingListener, loggingFacade, metricsListener, authenticationListener, concurrencyListener, new NetworkStatusProviderImpl(context, loggingFacade), new AudioDriverImpl(loggingFacade, context, androidSystemInformation), new ThreadFactoryImpl(loggingFacade), new HttpClientFactoryImpl(loggingFacade), weblabClient, metricsAttributesProvider, androidSystemInformation);
    }
}
