package com.amazon.avod.media.events;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.AloysiusCyclicEventMasterController;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PeriodicMediaEventScheduler {

    @Nonnull
    private final AloysiusCyclicEventMasterController mCyclicEventMasterController;
    private final AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener mCyclicEventMasterListener;
    private long mDesiredInterval;
    private final Object mMutex;
    private final List<MediaEventProducer> mProducers;
    private ScheduledFuture<?> mScheduledTask;
    private final ScheduledExecutorService mScheduler;

    public PeriodicMediaEventScheduler(@Nonnull ScheduledExecutorService scheduledExecutorService) {
        this(scheduledExecutorService, AloysiusCyclicEventMasterController.getInstance());
    }

    @VisibleForTesting
    PeriodicMediaEventScheduler(@Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController) {
        this.mDesiredInterval = -1L;
        this.mMutex = new Object();
        AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener aloysiusCyclicEventMasterListener = new AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener() { // from class: com.amazon.avod.media.events.PeriodicMediaEventScheduler.1
            @Override // com.amazon.avod.media.events.AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener
            public void masterCyclicReporterStatusChanged(boolean z) {
                PeriodicMediaEventScheduler.this.updateScheduledStatus();
            }
        };
        this.mCyclicEventMasterListener = aloysiusCyclicEventMasterListener;
        this.mProducers = Lists.newLinkedList();
        this.mScheduler = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController2 = (AloysiusCyclicEventMasterController) Preconditions.checkNotNull(aloysiusCyclicEventMasterController, "cyclicEventMasterController");
        this.mCyclicEventMasterController = aloysiusCyclicEventMasterController2;
        aloysiusCyclicEventMasterController2.registerListener(aloysiusCyclicEventMasterListener);
    }

    public void start(long j) {
        synchronized (this.mMutex) {
            this.mDesiredInterval = j;
            updateScheduledStatus();
        }
    }

    public void stop() {
        synchronized (this.mMutex) {
            this.mDesiredInterval = -1L;
            updateScheduledStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScheduledStatus() {
        synchronized (this.mMutex) {
            if (this.mDesiredInterval != -1 && this.mCyclicEventMasterController.isEnabled()) {
                startInternal(this.mDesiredInterval);
            } else {
                stopInternal();
            }
        }
    }

    private void startInternal(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("intervalInMillis");
        }
        synchronized (this.mMutex) {
            stopInternal();
            this.mScheduledTask = this.mScheduler.scheduleAtFixedRate(new Runnable() { // from class: com.amazon.avod.media.events.PeriodicMediaEventScheduler.2
                @Override // java.lang.Runnable
                public void run() {
                    for (MediaEventProducer mediaEventProducer : PeriodicMediaEventScheduler.this.mProducers) {
                        DLog.devf("calling produce method on %s", mediaEventProducer.getClass().getSimpleName());
                        mediaEventProducer.produce();
                    }
                }
            }, j, j, TimeUnit.MILLISECONDS);
        }
    }

    private void stopInternal() {
        synchronized (this.mMutex) {
            ScheduledFuture<?> scheduledFuture = this.mScheduledTask;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        }
    }

    public void addMediaEventProducer(@Nonnull MediaEventProducer mediaEventProducer) {
        synchronized (this.mMutex) {
            this.mProducers.add((MediaEventProducer) Preconditions.checkNotNull(mediaEventProducer, "mediaEventProducer"));
        }
    }
}
