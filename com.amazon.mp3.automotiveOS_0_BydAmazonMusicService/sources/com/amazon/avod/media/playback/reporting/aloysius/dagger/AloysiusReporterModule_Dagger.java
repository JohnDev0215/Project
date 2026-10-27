package com.amazon.avod.media.playback.reporting.aloysius.dagger;

import android.content.Context;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.PeriodicMediaEventScheduler;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.AloysiusCommunicationService;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusAudioReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDeviceReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDisplayReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusNetworkReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusWeblabReporter;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.purchase.AssociateTagProviderProxy;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusReporterModule_Dagger {
    private AloysiusDeviceReporter mAloysiusDeviceReporter;
    private final ScheduledExecutorService mPeriodicSchedulerExecutor = ScheduledExecutorBuilder.newBuilderFor("PeriodicMediaEventScheduler", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();

    public MediaEventProducer provideAloysiusNetworkReporterEventProducer(MediaEventQueue mediaEventQueue) {
        return new AloysiusNetworkReporter(mediaEventQueue, NetworkConnectionManager.getInstance());
    }

    public MediaEventProducer provideAloysiusAudioReporterEventProducer(MediaEventQueue mediaEventQueue, Provider<VolumeManager> provider) {
        return new AloysiusAudioReporter(mediaEventQueue, provider.get());
    }

    public MediaEventProducer provideAloysiusDeviceReporterEventProducer(MediaEventQueue mediaEventQueue, Context context, RendererSchemeController rendererSchemeController) {
        return provideAloysiusDeviceReporter(mediaEventQueue, context, rendererSchemeController);
    }

    public AloysiusDeviceReporter provideAloysiusDeviceReporter(MediaEventQueue mediaEventQueue, Context context, RendererSchemeController rendererSchemeController) {
        if (this.mAloysiusDeviceReporter == null) {
            MediaSystem mediaSystem = MediaSystem.getInstance();
            this.mAloysiusDeviceReporter = new AloysiusDeviceReporter(mediaEventQueue, mediaSystem.getDeviceIdentity(), mediaSystem.getPlayerName(), mediaSystem.getPlayerSdkVersion(), rendererSchemeController, context, AssociateTagProviderProxy.getInstance());
        }
        return this.mAloysiusDeviceReporter;
    }

    public MediaEventProducer provideAloysiusDisplayReporter(MediaEventQueue mediaEventQueue, Context context) {
        return new AloysiusDisplayReporter(mediaEventQueue, context);
    }

    public MediaEventProducer provideAloysiusWeblabReporter(MediaEventQueue mediaEventQueue) {
        return new AloysiusWeblabReporter(mediaEventQueue);
    }

    public PeriodicMediaEventScheduler providePeriodicMediaEventScheduler() {
        return new PeriodicMediaEventScheduler(this.mPeriodicSchedulerExecutor);
    }

    public QOSCommunicationService provideQOSCommunicationService() {
        return AloysiusCommunicationService.getInstance();
    }
}
