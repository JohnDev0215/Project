package com.amazon.avod.qos.reporter.internal;

import com.amazon.avod.events.EventManager;
import com.amazon.avod.events.EventPriority;
import com.amazon.avod.events.PlayerEventType;
import com.amazon.avod.events.data.BaseEventData;
import com.amazon.avod.http.PlaybackAuthProvider;
import com.amazon.avod.http.internal.TokenKey;
import com.amazon.avod.media.events.dao.MediaEventsDatabase;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.internal.metrics.Metrics;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class QoSMetricEventSender {
    private final EventManager mEventManager;
    private final Set<String> mExcludedEvents;
    private final Executor mExecutor;
    private final Set<String> mImmediateEvents;
    private final boolean mIsSdkPlayer;
    private final MediaEventsDatabase mMediaEventsDatabase;
    private final PlaybackAuthProvider mPlaybackAuthProvider;
    private final boolean mShouldDropLegacyEventsOnInsufficientDiskSpace;
    private final boolean mShouldDropQoSEventsForSdkPlayer;

    public QoSMetricEventSender(@Nonnull EventManager eventManager, @Nonnull QoSConfig qoSConfig) {
        this(eventManager, qoSConfig.getBlacklistedGenericEvents(), qoSConfig.getImmediateUploadEvents(), qoSConfig.shouldDropQoSEventsForSdkPlayer(), MediaSystemSharedDependencies.getInstance().isSDKPlayer(), MediaSystemSharedDependencies.getInstance().getPlaybackAuthProvider(), qoSConfig.shouldDropLegacyEventsOnInsufficientDiskSpace(), MediaEventsDatabase.getInstance(), ScheduledExecutorBuilder.newBuilderFor(QoSMetricEventSender.class, new String[0]).withFixedThreadPoolSize(1).build());
    }

    QoSMetricEventSender(EventManager eventManager, Set<String> set, Set<String> set2, boolean z, boolean z2, PlaybackAuthProvider playbackAuthProvider, boolean z3, @Nonnull MediaEventsDatabase mediaEventsDatabase, @Nonnull Executor executor) {
        this.mEventManager = eventManager;
        this.mExcludedEvents = set;
        this.mImmediateEvents = set2;
        this.mShouldDropQoSEventsForSdkPlayer = z;
        this.mIsSdkPlayer = z2;
        this.mPlaybackAuthProvider = playbackAuthProvider;
        this.mShouldDropLegacyEventsOnInsufficientDiskSpace = z3;
        this.mMediaEventsDatabase = (MediaEventsDatabase) Preconditions.checkNotNull(mediaEventsDatabase, "mediaEventsDatabase");
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor, "executor");
    }

    public void send(@Nonnull String str, @Nullable String str2, @Nonnull MetricsBuilder metricsBuilder) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(metricsBuilder);
        send(str, str2, metricsBuilder, this.mPlaybackAuthProvider.getTokenKeyForCurrentUser());
    }

    public void send(@Nonnull final String str, @Nullable final String str2, @Nonnull final MetricsBuilder metricsBuilder, @Nullable final TokenKey tokenKey) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(metricsBuilder);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.qos.reporter.internal.QoSMetricEventSender$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$send$0(str, str2, metricsBuilder, tokenKey);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: sendInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$send$0(@Nonnull String str, @Nullable String str2, @Nonnull MetricsBuilder metricsBuilder, @Nullable TokenKey tokenKey) {
        if (this.mExcludedEvents.contains(str)) {
            return;
        }
        if (this.mIsSdkPlayer && this.mShouldDropQoSEventsForSdkPlayer) {
            DLog.devf("Dropping QoS event of type: %s, subtype: %s, note: %s, error_message: %s", str, metricsBuilder.eventSubtype, metricsBuilder.note, metricsBuilder.errorMessage);
            return;
        }
        if (this.mShouldDropLegacyEventsOnInsufficientDiskSpace && !this.mMediaEventsDatabase.hasSufficientDiskSpace()) {
            DLog.warnf("Dropping QoS event due to insufficient disk space, type: %s, subtype: %s, note: %s, error_message: %s", str, metricsBuilder.eventSubtype, metricsBuilder.note, metricsBuilder.errorMessage);
            return;
        }
        Metrics metricsBuild = metricsBuilder.eventType(str).build();
        PlayerEventType playerEventType = PlayerEventType.QOS;
        if (this.mImmediateEvents.contains(str)) {
            playerEventType = PlayerEventType.QOS_IMMEDIATE;
        }
        BaseEventData baseEventData = new BaseEventData(playerEventType, metricsBuild.getEventType(), str2, EventPriority.MediumLow, metricsBuild.getTitleId(), metricsBuild.getJsonString(), tokenKey);
        DLog.devf("Queuing QoS event %s", baseEventData);
        this.mEventManager.queueEventAsync(baseEventData);
    }
}
