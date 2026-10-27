package com.amazon.avod.media.events;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ListeningScheduledThreadPoolExecutor;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.threading.WeakReferenceSet;
import com.amazon.avod.util.DLog;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusCyclicEventMasterController {
    private static final AloysiusCyclicEventMasterController INSTANCE = new AloysiusCyclicEventMasterController();
    private boolean mMasterCyclicReportersEnabled = false;
    private final Set<AloysiusCyclicEventMasterListener> mListeners = WeakReferenceSet.build();
    private final Object mMutex = new Object();
    private final ListeningScheduledThreadPoolExecutor mExecutorService = ScheduledExecutorBuilder.newBuilderFor("AloysiusCyclicEventMasterController", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).buildListeningExecutor();

    public interface AloysiusCyclicEventMasterListener {
        void masterCyclicReporterStatusChanged(boolean z);
    }

    public void onPlaybackStarted() {
        synchronized (this.mMutex) {
            DLog.devf("AloysiusCyclicEventMasterController onPlaybackStarted");
            triggerListeners(true);
        }
    }

    public void onPlaybackStopped() {
        synchronized (this.mMutex) {
            DLog.devf("AloysiusCyclicEventMasterController onPlaybackStopped");
            triggerListeners(false);
        }
    }

    public void registerListener(AloysiusCyclicEventMasterListener aloysiusCyclicEventMasterListener) {
        synchronized (this.mMutex) {
            this.mListeners.add(aloysiusCyclicEventMasterListener);
            aloysiusCyclicEventMasterListener.masterCyclicReporterStatusChanged(this.mMasterCyclicReportersEnabled);
        }
    }

    private void triggerListeners(boolean z) {
        synchronized (this.mMutex) {
            if (this.mMasterCyclicReportersEnabled == z) {
                return;
            }
            this.mMasterCyclicReportersEnabled = z;
            for (final AloysiusCyclicEventMasterListener aloysiusCyclicEventMasterListener : this.mListeners) {
                this.mExecutorService.schedule(new Runnable() { // from class: com.amazon.avod.media.events.AloysiusCyclicEventMasterController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        aloysiusCyclicEventMasterListener.masterCyclicReporterStatusChanged(AloysiusCyclicEventMasterController.this.mMasterCyclicReportersEnabled);
                    }
                }, 0L, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean isEnabled() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mMasterCyclicReportersEnabled;
        }
        return z;
    }

    public static AloysiusCyclicEventMasterController getInstance() {
        return INSTANCE;
    }

    @VisibleForTesting
    AloysiusCyclicEventMasterController() {
    }
}
