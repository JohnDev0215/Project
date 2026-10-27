package com.amazon.mp3.playback.start;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.StationItem;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartStationPlayback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jv\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0010H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/playback/start/StartStationPlayback;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "startOnlineInteractionPlayback", "", "context", "Landroid/content/Context;", "queueTitle", "stationItem", "Lcom/amazon/music/station/StationItem;", "contentUri", "Landroid/net/Uri;", "fullScreen", "", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "controlSource", "Lcom/amazon/music/media/playback/ControlSource;", "upsellInformation", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "additionalMediaCollectionId", "Lcom/amazon/music/media/playback/MediaCollectionId;", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "noOpCloudQueueClientHandler", "Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", "isUserInitiated", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StartStationPlayback {
    public static final StartStationPlayback INSTANCE = new StartStationPlayback();
    private static final String TAG = StartStationPlayback.class.getSimpleName();

    private StartStationPlayback() {
    }

    @JvmStatic
    @SuppressLint({"CheckResult"})
    public static final void startOnlineInteractionPlayback(final Context context, final String queueTitle, StationItem stationItem, final Uri contentUri, final boolean fullScreen, final PlaybackPageType playbackPageType, final ControlSource controlSource, final UpsellInformation upsellInformation, final MediaCollectionId additionalMediaCollectionId, final PlaybackListener playbackListener, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, final boolean isUserInitiated) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stationItem, "stationItem");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        final QueueSeedContextInput queueSeedContextInputByStationItem = CloudQueueDigitalXPUtil.getQueueSeedContextInputByStationItem(stationItem);
        CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.start.StartStationPlayback$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartStationPlayback.m1489startOnlineInteractionPlayback$lambda0(noOpCloudQueueClientHandler, queueSeedContextInputByStationItem, queueTitle, context, playbackPageType, playbackListener, controlSource, contentUri, fullScreen, additionalMediaCollectionId, upsellInformation, isUserInitiated, (CloudQueueAppClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.start.StartStationPlayback$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartStationPlayback.m1490startOnlineInteractionPlayback$lambda1(playbackListener, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startOnlineInteractionPlayback$lambda-0, reason: not valid java name */
    public static final void m1489startOnlineInteractionPlayback$lambda0(NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, QueueSeedContextInput queueSeedContextInput, String str, Context context, PlaybackPageType playbackPageType, PlaybackListener playbackListener, ControlSource controlSource, Uri contentUri, boolean z, MediaCollectionId mediaCollectionId, UpsellInformation upsellInformation, boolean z2, CloudQueueAppClient cloudQueueAppClient) {
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        if (cloudQueueAppClient instanceof NoOpCloudQueueAppClient) {
            noOpCloudQueueClientHandler.handlePlayback();
            return;
        }
        if (queueSeedContextInput != null) {
            Log.debug(TAG, Intrinsics.stringPlus("queueSeedContextInput play mode = ", queueSeedContextInput.getPlayMode()));
            CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(cloudQueueAppClient, "cloudQueueAppClient");
            CloudQueuePlaybackUtil.Companion.startOnlineQueueInteraction$default(companion, str, cloudQueueAppClient, context, queueSeedContextInput, playbackPageType, playbackListener, controlSource, contentUri, z, mediaCollectionId, upsellInformation, z2, null, null, null, 28672, null);
            return;
        }
        Log.warning(TAG, "queueSeedContextInput is null, unable to play station via cloud queue, use fall back");
        noOpCloudQueueClientHandler.handlePlayback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startOnlineInteractionPlayback$lambda-1, reason: not valid java name */
    public static final void m1490startOnlineInteractionPlayback$lambda1(PlaybackListener playbackListener, Throwable th) {
        Log.error(TAG, "failed to start cloud queue app", th);
        if (playbackListener == null) {
            return;
        }
        playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
    }
}
