package com.amazon.video.sdk.stream;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamGroup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b'\u0010(J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J%\u0010\r\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\tJ\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012H\u0016R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\u00020\u00188\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001a\u0010#\u001a\u00020\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006)"}, d2 = {"Lcom/amazon/video/sdk/stream/VideoStreamGroupImpl;", "Lcom/amazon/video/sdk/stream/VideoStreamGroup;", "", "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "", "onPrepared", "Lcom/amazon/avod/media/VideoResolution;", "currentResolution", "", "availableResolutions", "updateVariants", "(Lcom/amazon/avod/media/VideoResolution;[Lcom/amazon/avod/media/VideoResolution;)V", "resolution", "Lcom/amazon/video/sdk/stream/VideoVariant;", "resolveStreamQuality", "", "isDestroyed", "onTerminate", "Lcom/amazon/video/sdk/stream/VideoStreamData;", "videoStream", "Lcom/amazon/video/sdk/stream/VideoStreamData;", "Lcom/amazon/video/sdk/stream/VideoStream;", "activeStream", "Lcom/amazon/video/sdk/stream/VideoStream;", "getActiveStream", "()Lcom/amazon/video/sdk/stream/VideoStream;", "", "streams", "Ljava/util/List;", "getStreams", "()Ljava/util/List;", "Lcom/amazon/video/sdk/stream/StreamType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/StreamType;", "getType", "()Lcom/amazon/video/sdk/stream/StreamType;", "<init>", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class VideoStreamGroupImpl implements VideoStreamGroup {
    private final VideoStream activeStream;
    private final List<VideoStream> streams;
    private final StreamType type;
    private final VideoStreamData videoStream;

    /* JADX INFO: compiled from: StreamGroup.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoResolution.ResolutionBand.values().length];
            iArr[VideoResolution.ResolutionBand.SUB_SD.ordinal()] = 1;
            iArr[VideoResolution.ResolutionBand.HD.ordinal()] = 2;
            iArr[VideoResolution.ResolutionBand.HD_1080P.ordinal()] = 3;
            iArr[VideoResolution.ResolutionBand.ULTRA_HD.ordinal()] = 4;
            iArr[VideoResolution.ResolutionBand.UNKNOWN.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public void onPrepared(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
    }

    public void onTerminate(boolean isDestroyed) {
    }

    public VideoStreamGroupImpl() {
        VideoStreamData videoStreamData = new VideoStreamData(null, null, null, 7, null);
        this.videoStream = videoStreamData;
        this.type = StreamType.Video;
        this.activeStream = videoStreamData;
        this.streams = CollectionsKt__CollectionsJVMKt.listOf(videoStreamData);
    }

    @Override // com.amazon.video.sdk.stream.StreamGroup
    public VideoStream getActiveStream() {
        return this.activeStream;
    }

    public final void updateVariants(VideoResolution currentResolution, VideoResolution[] availableResolutions) {
        Intrinsics.checkNotNullParameter(availableResolutions, "availableResolutions");
        this.videoStream.setActiveVariant(resolveStreamQuality(currentResolution));
        VideoStreamData videoStreamData = this.videoStream;
        ArrayList arrayList = new ArrayList(availableResolutions.length);
        int length = availableResolutions.length;
        int i = 0;
        while (i < length) {
            VideoResolution videoResolution = availableResolutions[i];
            i++;
            arrayList.add(resolveStreamQuality(videoResolution));
        }
        videoStreamData.setVariants(CollectionsKt___CollectionsKt.distinct(arrayList));
    }

    public final VideoVariant resolveStreamQuality(VideoResolution resolution) {
        VideoResolution.ResolutionBand resolutionBand = resolution == null ? null : resolution.getResolutionBand();
        int i = resolutionBand == null ? -1 : WhenMappings.$EnumSwitchMapping$0[resolutionBand.ordinal()];
        if (i == 1) {
            return VideoVariant.SD;
        }
        if (i == 2) {
            return VideoVariant.HD;
        }
        if (i == 3) {
            return VideoVariant.HD_1080;
        }
        if (i == 4) {
            return VideoVariant.UHD;
        }
        if (i == 5) {
            return VideoVariant.SD;
        }
        return VideoVariant.SD;
    }
}
