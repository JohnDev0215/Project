package com.amazon.mp3.cloudqueue.sequencer;

import android.net.Uri;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.util.Log;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: InterludeMediaItem.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\b\b\u0016\u0018\u0000 $2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002$%Bw\b\u0016\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004\u0012\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0010 \u001a\u00020\u0004\u0012\u0006\u0010!\u001a\u00020\f¢\u0006\u0004\b\"\u0010#J\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0012\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\r\u001a\u00020\fH\u0016R\u0016\u0010\u000e\u001a\u00020\u00048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006&"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/InterludeMediaItem;", "Lcom/amazon/music/media/playback/MediaItem;", "Lcom/amazon/mp3/cloudqueue/sequencer/PlaybackControlVisibilityQueryable;", "Lcom/amazon/mp3/cloudqueue/sequencer/QueueEntityCapabilityQueryable;", "", "getInterludeId", "", "canRate", "canSeek", "isInterlude", "action", "canDisplay", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "getQueueEntityCapabilities", "mInterludeId", "Ljava/lang/String;", "capability", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "interludeAudioUrl", "interludeName", "interludeSubTitle", "", "durationInMilli", "Lcom/amazon/music/media/playback/MediaLinkProvider;", "mediaLinkProvider", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "collectionInfo", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "interludeArtWorkUrl", "", "metricsContext", "interludeId", "queueEntityCapability", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLcom/amazon/music/media/playback/MediaLinkProvider;Lcom/amazon/music/media/playback/MediaCollectionInfo;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;)V", "Companion", "InterludeImageUriProvider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class InterludeMediaItem extends MediaItem implements PlaybackControlVisibilityQueryable, QueueEntityCapabilityQueryable {
    private final QueueEntityCapability capability;
    private String mInterludeId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = InterludeMediaItem.class.getSimpleName();
    private static final String INTERLUDE_PREFIX = "interlude";
    private static final String HTTPS_PREFIX = "https";

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        return false;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canSeek() {
        return true;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean isInterlude() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InterludeMediaItem(String interludeAudioUrl, String interludeName, String str, long j, MediaLinkProvider mediaLinkProvider, MediaCollectionInfo mediaCollectionInfo, PlaybackPageType playbackPageType, String str2, Map<String, String> metricsContext, String interludeId, QueueEntityCapability queueEntityCapability) {
        super(new MediaItemId(MediaItemId.Type.URL, interludeAudioUrl), interludeName, j, MediaItem.Type.PRIME_CONTENT, new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withCastable(true).withExplicit(false).withContentAccessType(ContentAccessType.PRIME).withContentAccessType(ContentAccessType.NIGHTWING).build(), null, mediaLinkProvider, new InterludeImageUriProvider(str2), playbackPageType, false, mediaCollectionInfo, new MediaCollectionInfo[0]);
        Intrinsics.checkNotNullParameter(interludeAudioUrl, "interludeAudioUrl");
        Intrinsics.checkNotNullParameter(interludeName, "interludeName");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        Intrinsics.checkNotNullParameter(interludeId, "interludeId");
        Intrinsics.checkNotNullParameter(queueEntityCapability, "queueEntityCapability");
        setMetricsContext(metricsContext);
        this.mInterludeId = interludeId;
        this.capability = queueEntityCapability;
        addMediaItemId(new MediaItemId(MediaItemId.Type.INTERLUDE, INSTANCE.convertUrlFormat(interludeAudioUrl)));
        if (str != null) {
            addMediaCollectionInfo(new MediaCollectionInfo(str, null, null, MediaCollectionType.ARTIST, getMediaAccessInfo(), new MediaCollectionId[0]));
        }
    }

    public final String getInterludeId() {
        String str = this.mInterludeId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mInterludeId");
        return null;
    }

    /* JADX INFO: compiled from: InterludeMediaItem.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/InterludeMediaItem$InterludeImageUriProvider;", "Lcom/amazon/music/media/playback/MediaItem$ImageUriProvider;", "Lcom/amazon/music/media/playback/MediaItem$ImageInfo;", "imageInfo", "Landroid/net/Uri;", "getImageUri", "createImageUri", "", "interludeArtWorkUrl", "Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class InterludeImageUriProvider implements MediaItem.ImageUriProvider {
        private final String interludeArtWorkUrl;

        public InterludeImageUriProvider(String str) {
            this.interludeArtWorkUrl = str;
        }

        @Override // com.amazon.music.media.playback.MediaItem.ImageUriProvider
        public Uri getImageUri(MediaItem.ImageInfo imageInfo) {
            Intrinsics.checkNotNullParameter(imageInfo, "imageInfo");
            Uri uri = Uri.parse(this.interludeArtWorkUrl);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(interludeArtWorkUrl)");
            return uri;
        }

        @Override // com.amazon.music.media.playback.MediaItem.ImageUriProvider
        public Uri createImageUri(MediaItem.ImageInfo imageInfo) {
            Intrinsics.checkNotNullParameter(imageInfo, "imageInfo");
            Uri uri = Uri.parse(this.interludeArtWorkUrl);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(interludeArtWorkUrl)");
            return uri;
        }
    }

    /* JADX INFO: compiled from: InterludeMediaItem.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/InterludeMediaItem$Companion;", "", "()V", "HTTPS_PREFIX", "", "INTERLUDE_PREFIX", "TAG", "kotlin.jvm.PlatformType", "convertUrlFormat", ImagesContract.URL, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String convertUrlFormat(String url) {
            return (url == null || !StringsKt__StringsJVMKt.startsWith$default(url, InterludeMediaItem.HTTPS_PREFIX, false, 2, null)) ? "" : StringsKt__StringsJVMKt.replace$default(url, InterludeMediaItem.HTTPS_PREFIX, InterludeMediaItem.INTERLUDE_PREFIX, false, 4, (Object) null);
        }
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.PlaybackControlVisibilityQueryable
    public boolean canDisplay(String action) {
        ActionStrategy displayable;
        if (Intrinsics.areEqual(action, "PREVIOUS")) {
            displayable = this.capability.getPrevious();
        } else if (Intrinsics.areEqual(action, "NEXT")) {
            displayable = this.capability.getNext();
        } else if (Intrinsics.areEqual(action, "SCRUB_BACKWARD")) {
            displayable = this.capability.getScrubBackward();
        } else if (Intrinsics.areEqual(action, "SCRUB_FORWARD")) {
            displayable = this.capability.getScrubForward();
        } else if (Intrinsics.areEqual(action, "RATE_TRACK")) {
            displayable = this.capability.getRate();
        } else if (Intrinsics.areEqual(action, "LOOP_ONE")) {
            displayable = this.capability.getLoopOne();
        } else {
            if (Intrinsics.areEqual(action, "SHUFFLE") ? true : Intrinsics.areEqual(action, "LOOP") ? true : Intrinsics.areEqual(action, "CASTING")) {
                Log.warning(TAG, "displayable state should get from " + ((Object) action) + " at queue level, fallback to capability.displayable");
                displayable = this.capability.getDisplayable();
            } else if (Intrinsics.areEqual(action, QueueEntityCapabilityTranslator.QueueEntityCapability.DISPLAYABLE.name())) {
                displayable = this.capability.getDisplayable();
            } else {
                Log.warning(TAG, "unhandled " + ((Object) action) + ", set canDisplay to false");
                return false;
            }
        }
        if (!Intrinsics.areEqual(displayable, this.capability.getDisplayable())) {
            Log.error(TAG, "FIXME: " + ((Object) action) + " canDisplay is computed wrongly, temporarily use displayable=" + this.capability.getDisplayable() + ", it should be " + displayable);
        }
        return CloudQueueDigitalXPUtil.canPerformActionStrategy$default(this.capability.getDisplayable(), null, 2, null);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable
    /* JADX INFO: renamed from: getQueueEntityCapabilities, reason: from getter */
    public QueueEntityCapability getCapability() {
        return this.capability;
    }
}
