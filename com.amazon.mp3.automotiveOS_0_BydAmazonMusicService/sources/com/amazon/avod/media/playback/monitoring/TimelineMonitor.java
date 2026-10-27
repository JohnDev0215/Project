package com.amazon.avod.media.playback.monitoring;

import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class TimelineMonitor {
    private final ConcurrentMap<VideoPlayer, MonitoringContext> mMonitoredPlayers;
    private final ScheduledFuture<?> mMonitoringFuture;
    protected final Runnable mMonitoringTask;
    private final ScheduledExecutorService mScheduledExecutor;

    public TimelineMonitor() {
        this(ScheduledExecutorBuilder.newBuilderFor(TimelineMonitor.class, new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    TimelineMonitor(ScheduledExecutorService scheduledExecutorService) {
        this.mMonitoredPlayers = new MapMaker().weakKeys().makeMap();
        Runnable runnable = new Runnable() { // from class: com.amazon.avod.media.playback.monitoring.TimelineMonitor.1
            private final List<TimelineMonitoringTask> mScheduledTasks = Lists.newLinkedList();

            @Override // java.lang.Runnable
            public void run() {
                Iterator it = TimelineMonitor.this.mMonitoredPlayers.entrySet().iterator();
                while (it.hasNext()) {
                    MonitoringContext monitoringContext = (MonitoringContext) ((Map.Entry) it.next()).getValue();
                    long jUpdate = monitoringContext.update();
                    long currentPlayerTimeMilliseconds = monitoringContext.getCurrentPlayerTimeMilliseconds();
                    for (TimelineMonitoringTask timelineMonitoringTask : monitoringContext.getTasks()) {
                        if (timelineMonitoringTask.isExpired(jUpdate, currentPlayerTimeMilliseconds)) {
                            this.mScheduledTasks.add(timelineMonitoringTask);
                        }
                    }
                    for (TimelineMonitoringTask timelineMonitoringTask2 : this.mScheduledTasks) {
                        timelineMonitoringTask2.getTask().run();
                        if (timelineMonitoringTask2.shouldRemoveAfterExecutingTask()) {
                            monitoringContext.getTasks().remove(timelineMonitoringTask2);
                        }
                    }
                    this.mScheduledTasks.clear();
                }
            }
        };
        this.mMonitoringTask = runnable;
        ScheduledExecutorService scheduledExecutorService2 = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService);
        this.mScheduledExecutor = scheduledExecutorService2;
        this.mMonitoringFuture = scheduledExecutorService2.scheduleAtFixedRate(runnable, 0L, 200L, TimeUnit.MILLISECONDS);
    }

    public void scheduleTask(VideoPlayer videoPlayer, TimelineMonitoringTask timelineMonitoringTask) {
        MonitoringContext monitoringContextPutIfAbsent;
        Preconditions.checkNotNull(videoPlayer);
        Preconditions.checkNotNull(timelineMonitoringTask);
        Preconditions.checkState(!this.mScheduledExecutor.isShutdown(), "Cannot call after shutdown()");
        MonitoringContext monitoringContext = this.mMonitoredPlayers.get(videoPlayer);
        if (monitoringContext == null && (monitoringContextPutIfAbsent = this.mMonitoredPlayers.putIfAbsent(videoPlayer, (monitoringContext = new MonitoringContext(videoPlayer)))) != null) {
            monitoringContext = monitoringContextPutIfAbsent;
        }
        monitoringContext.getTasks().add(timelineMonitoringTask);
    }

    public void removeTask(VideoPlayer videoPlayer, TimelineMonitoringTask timelineMonitoringTask) {
        Preconditions.checkNotNull(videoPlayer);
        Preconditions.checkNotNull(timelineMonitoringTask);
        Preconditions.checkState(!this.mScheduledExecutor.isShutdown(), "Cannot call after shutdown()");
        MonitoringContext monitoringContext = this.mMonitoredPlayers.get(videoPlayer);
        if (monitoringContext != null) {
            monitoringContext.getTasks().remove(timelineMonitoringTask);
        }
    }

    public void stopMonitoring(VideoPlayer videoPlayer) {
        Preconditions.checkNotNull(videoPlayer);
        Preconditions.checkState(!this.mScheduledExecutor.isShutdown(), "Cannot call after shutdown()");
        this.mMonitoredPlayers.remove(videoPlayer);
    }

    public void shutdown() {
        this.mMonitoredPlayers.clear();
        this.mMonitoringFuture.cancel(false);
        this.mScheduledExecutor.shutdown();
    }

    private static class MonitoringContext {
        private long mCurrentPlayerTimeMilliseconds;
        private final WeakReference<VideoPlayer> mPlayer;
        private final List<TimelineMonitoringTask> mTasks = Lists.newCopyOnWriteArrayList(TimelineMonitor.newTaskList());

        public MonitoringContext(VideoPlayer videoPlayer) {
            this.mPlayer = new WeakReference<>(videoPlayer);
            update();
        }

        public long getCurrentPlayerTimeMilliseconds() {
            return this.mCurrentPlayerTimeMilliseconds;
        }

        public long update() {
            long j = this.mCurrentPlayerTimeMilliseconds;
            VideoPlayer videoPlayer = this.mPlayer.get();
            if (videoPlayer != null) {
                this.mCurrentPlayerTimeMilliseconds = videoPlayer.getCurrentPosition();
            }
            return j;
        }

        public List<TimelineMonitoringTask> getTasks() {
            return this.mTasks;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<TimelineMonitoringTask> newTaskList() {
        return Lists.newLinkedList();
    }
}
