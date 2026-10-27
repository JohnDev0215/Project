package com.amazon.video.sdk.player.videopreviewasset;

import android.graphics.drawable.Drawable;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackTrickplayFeature;
import com.amazon.video.sdk.avod.playbackclient.trickplay.TrickplayImageLoader;
import com.amazon.video.sdk.player.FeatureConfigs;
import com.amazon.video.sdk.player.PlayerConfig;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetEvent;
import com.google.firebase.perf.util.Constants;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: VideoPreviewAssetFeatureImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100JI\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u00002\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u00062\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J.\u0010\u001b\u001a\u00020\n\"\b\b\u0000\u0010\u0004*\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016J.\u0010\u001c\u001a\u00020\n\"\b\b\u0000\u0010\u0004*\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016R \u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR \u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\"\u0010%\u001a\u00020$8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,¨\u00061"}, d2 = {"Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureImpl;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent;", "E", "videoPreviewEvent", "", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEventListener;", "playbackEventListenerSet", "playbackEvenOneTimeListenerSet", "", "onVideoPreviewEvent", "(Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent;Ljava/util/Set;Ljava/util/Set;)V", "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "onPrepared", "", "isDestroyed", "onTerminate", "", "currentVideoPosition", "requestVideoPreviewAsset", "Ljava/lang/Class;", NotificationCompat.CATEGORY_EVENT, "callback", DebugKt.DEBUG_PROPERTY_VALUE_ON, DebugKt.DEBUG_PROPERTY_VALUE_OFF, "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent$VideoPreviewChange;", "videoPreviewChangeEventListenerSet", "Ljava/util/Set;", "videoPreviewChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/avod/playbackclient/trickplay/TrickplayImageLoader$ImageLoaderListener;", "trickplayImageLoaderListener", "Lcom/amazon/video/sdk/avod/playbackclient/trickplay/TrickplayImageLoader$ImageLoaderListener;", "Lcom/amazon/video/sdk/avod/playbackclient/activity/feature/PlaybackTrickplayFeature;", "videoPreviewFeature", "Lcom/amazon/video/sdk/avod/playbackclient/activity/feature/PlaybackTrickplayFeature;", "getVideoPreviewFeature", "()Lcom/amazon/video/sdk/avod/playbackclient/activity/feature/PlaybackTrickplayFeature;", "setVideoPreviewFeature", "(Lcom/amazon/video/sdk/avod/playbackclient/activity/feature/PlaybackTrickplayFeature;)V", Constants.ENABLE_DISABLE, "Z", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "<init>", "(Lcom/amazon/video/sdk/player/PlayerConfig;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class VideoPreviewAssetFeatureImpl implements VideoPreviewAssetFeature {
    private boolean isEnabled;
    private final TrickplayImageLoader.ImageLoaderListener trickplayImageLoaderListener;
    private final Set<VideoPreviewAssetEventListener<VideoPreviewAssetEvent.VideoPreviewChange>> videoPreviewChangeEventListenerSet;
    private final Set<VideoPreviewAssetEventListener<VideoPreviewAssetEvent.VideoPreviewChange>> videoPreviewChangeOneTimeEventListenerSet;
    public PlaybackTrickplayFeature videoPreviewFeature;

    public VideoPreviewAssetFeatureImpl(PlayerConfig config) {
        VideoPreviewAssetFeatureConfig videoPreviewAsset;
        Intrinsics.checkNotNullParameter(config, "config");
        this.videoPreviewChangeEventListenerSet = new LinkedHashSet();
        this.videoPreviewChangeOneTimeEventListenerSet = new LinkedHashSet();
        TrickplayImageLoader.ImageLoaderListener imageLoaderListener = new TrickplayImageLoader.ImageLoaderListener() { // from class: com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureImpl$$ExternalSyntheticLambda0
            @Override // com.amazon.video.sdk.avod.playbackclient.trickplay.TrickplayImageLoader.ImageLoaderListener
            public final void onImageAvailable(Drawable drawable) {
                VideoPreviewAssetFeatureImpl.m2490trickplayImageLoaderListener$lambda0(this.f$0, drawable);
            }
        };
        this.trickplayImageLoaderListener = imageLoaderListener;
        FeatureConfigs features = config.getFeatures();
        if ((features == null || (videoPreviewAsset = features.getVideoPreviewAsset()) == null || !videoPreviewAsset.getPreloadAssets()) ? false : true) {
            setVideoPreviewFeature(new PlaybackTrickplayFeature(config.getContext()));
            getVideoPreviewFeature().initialize(imageLoaderListener);
            this.isEnabled = true;
        } else {
            DLog.warnf("VideoPreviewAssetFeature is not enabled on the given PlayerConfig");
            this.isEnabled = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trickplayImageLoaderListener$lambda-0, reason: not valid java name */
    public static final void m2490trickplayImageLoaderListener$lambda0(VideoPreviewAssetFeatureImpl this$0, Drawable drawable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        this$0.onVideoPreviewEvent(new VideoPreviewAssetEvent.VideoPreviewChange(new VideoPreviewAssetData(drawable)), this$0.videoPreviewChangeEventListenerSet, this$0.videoPreviewChangeOneTimeEventListenerSet);
    }

    public final PlaybackTrickplayFeature getVideoPreviewFeature() {
        PlaybackTrickplayFeature playbackTrickplayFeature = this.videoPreviewFeature;
        if (playbackTrickplayFeature != null) {
            return playbackTrickplayFeature;
        }
        Intrinsics.throwUninitializedPropertyAccessException("videoPreviewFeature");
        return null;
    }

    public final void setVideoPreviewFeature(PlaybackTrickplayFeature playbackTrickplayFeature) {
        Intrinsics.checkNotNullParameter(playbackTrickplayFeature, "<set-?>");
        this.videoPreviewFeature = playbackTrickplayFeature;
    }

    public void onPrepared(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        if (this.isEnabled) {
            getVideoPreviewFeature().prepareForPlayback(videoPresentation);
        }
    }

    public void onTerminate(boolean isDestroyed) {
        if (this.isEnabled) {
            getVideoPreviewFeature().reset();
            if (isDestroyed) {
                getVideoPreviewFeature().destroy();
            }
        }
    }

    @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature
    public void requestVideoPreviewAsset(long currentVideoPosition) {
        if (this.isEnabled) {
            getVideoPreviewFeature().requestTrickplay(currentVideoPosition);
        }
    }

    @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature
    public <E extends VideoPreviewAssetEvent> void on(Class<E> event, VideoPreviewAssetEventListener<E> callback) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (Intrinsics.areEqual(event, VideoPreviewAssetEvent.VideoPreviewChange.class)) {
            this.videoPreviewChangeEventListenerSet.add(callback);
        } else if (Intrinsics.areEqual(event, VideoPreviewAssetEvent.VideoPreviewChange.class)) {
            this.videoPreviewChangeOneTimeEventListenerSet.add(callback);
        }
    }

    @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature
    public <E extends VideoPreviewAssetEvent> void off(Class<E> event, VideoPreviewAssetEventListener<E> callback) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (Intrinsics.areEqual(event, VideoPreviewAssetEvent.VideoPreviewChange.class)) {
            this.videoPreviewChangeEventListenerSet.remove(callback);
            this.videoPreviewChangeOneTimeEventListenerSet.remove(callback);
        }
    }

    private final <E extends VideoPreviewAssetEvent> void onVideoPreviewEvent(E videoPreviewEvent, Set<VideoPreviewAssetEventListener<E>> playbackEventListenerSet, Set<VideoPreviewAssetEventListener<E>> playbackEvenOneTimeListenerSet) {
        Iterator<T> it = playbackEventListenerSet.iterator();
        while (it.hasNext()) {
            ((VideoPreviewAssetEventListener) it.next()).on(videoPreviewEvent);
        }
        Iterator<T> it2 = playbackEvenOneTimeListenerSet.iterator();
        while (it2.hasNext()) {
            ((VideoPreviewAssetEventListener) it2.next()).on(videoPreviewEvent);
        }
        playbackEvenOneTimeListenerSet.clear();
    }
}
