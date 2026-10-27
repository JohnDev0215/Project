package com.amazon.video.sdk.player.timeline;

import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.video.sdk.player.timeline.metadata.AdMetadataData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimelineImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimelineImpl;", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "Lcom/amazon/video/sdk/player/timeline/TimelineItem;", ContextMappingConstants.ITEMS, "Ljava/util/List;", "getItems", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TimelineImpl implements Timeline {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<TimelineItem> items;

    public TimelineImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TimelineImpl) && Intrinsics.areEqual(getItems(), ((TimelineImpl) other).getItems());
    }

    public int hashCode() {
        return getItems().hashCode();
    }

    public String toString() {
        return "TimelineImpl(items=" + getItems() + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TimelineImpl(List<? extends TimelineItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
    }

    public /* synthetic */ TimelineImpl(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    @Override // com.amazon.video.sdk.player.timeline.Timeline
    public List<TimelineItem> getItems() {
        return this.items;
    }

    /* JADX INFO: compiled from: TimelineImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J\u001f\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimelineImpl$Companion;", "", "()V", "AD_CLIP_START_TIME_MS", "", "updateFeatureTimeline", "Lcom/amazon/video/sdk/player/timeline/TimelineImpl;", "adPlan", "Lcom/amazon/avod/media/ads/AdPlan;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "updateLiveTimeline", "updateTimeline", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "updateTimeline$AmazonAndroidVideoPlayer_release", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Timeline updateTimeline$AmazonAndroidVideoPlayer_release(VideoPresentation videoPresentation, AdPlan adPlan) {
            Intrinsics.checkNotNullParameter(adPlan, "adPlan");
            if (videoPresentation != null) {
                if (videoPresentation.getSpecification().isLiveStream()) {
                    return updateLiveTimeline(videoPresentation);
                }
                return updateFeatureTimeline(adPlan, videoPresentation);
            }
            return new TimelineImpl(null, 1, 0 == true ? 1 : 0);
        }

        private final TimelineImpl updateFeatureTimeline(AdPlan adPlan, VideoPresentation videoPresentation) {
            ArrayList arrayList = new ArrayList();
            boolean zShowCountdownTimer = adPlan.showCountdownTimer();
            String titleId = videoPresentation.getSpecification().getTitleId();
            Intrinsics.checkNotNullExpressionValue(titleId, "videoPresentation.specification.titleId");
            ContentInfoData contentInfoData = new ContentInfoData(titleId, ContentType.FEATURE, null, 4, null);
            long duration = videoPresentation.getPlayer().getDuration();
            long j = duration;
            long j2 = 0;
            long j3 = 0;
            long j4 = 0;
            for (AdBreak adBreak : adPlan.getBreaks()) {
                long totalMilliseconds = adBreak.getRelativeStartTime().getTotalMilliseconds();
                long totalMilliseconds2 = adBreak.getAbsoluteStartTime().getTotalMilliseconds();
                long totalMilliseconds3 = adBreak.getDuration().getTotalMilliseconds();
                long j5 = totalMilliseconds2 + totalMilliseconds3;
                if (totalMilliseconds2 > j2) {
                    arrayList.add(new TimelineItemData(null, Long.valueOf(j3), Long.valueOf(totalMilliseconds), contentInfoData, Long.valueOf(j2), Long.valueOf(totalMilliseconds2), 1, null));
                }
                long j6 = j + totalMilliseconds3;
                long j7 = 0;
                for (AdClip adClip : adBreak.getClips()) {
                    long totalMilliseconds4 = adClip.getDuration().getTotalMilliseconds();
                    long j8 = j7 + totalMilliseconds2;
                    long j9 = j4 + totalMilliseconds4;
                    AdSkipInfo adSkipInfo = adClip.getAdSkipInfo();
                    Long lValueOf = adSkipInfo == null ? null : Long.valueOf(adSkipInfo.getOffsetDuration());
                    AdSkipInfo adSkipInfo2 = adClip.getAdSkipInfo();
                    Boolean boolValueOf = adSkipInfo2 == null ? null : Boolean.valueOf(adSkipInfo2.isShowTimer());
                    String infoUrl = adClip.getInfoUrl();
                    List<Object> extensions = adClip.getExtensions();
                    Intrinsics.checkNotNullExpressionValue(extensions, "adClip.extensions");
                    boolean z = zShowCountdownTimer;
                    AdMetadataData adMetadataData = new AdMetadataData(zShowCountdownTimer, lValueOf, boolValueOf, infoUrl, extensions, j9);
                    String adId = adClip.getAdId();
                    if (adId == null) {
                        adId = "";
                    }
                    j7 += totalMilliseconds4;
                    arrayList.add(new TimelineItemData(null, 0L, Long.valueOf(totalMilliseconds4), new ContentInfoData(adId, ContentType.ADVERTISEMENT, adMetadataData), Long.valueOf(j8), Long.valueOf(j8 + totalMilliseconds4), 1, null));
                    zShowCountdownTimer = z;
                    j4 = j9;
                }
                j = j6;
                j3 = totalMilliseconds;
                j2 = j5;
            }
            if (j2 < j) {
                arrayList.add(new TimelineItemData(null, Long.valueOf(j3), Long.valueOf(duration), contentInfoData, Long.valueOf(j2), Long.valueOf(j), 1, null));
            }
            return new TimelineImpl(arrayList);
        }

        private final TimelineImpl updateLiveTimeline(VideoPresentation videoPresentation) {
            ArrayList arrayList = new ArrayList();
            TimeUnit timeUnit = TimeUnit.EPOCH_MILLIS;
            String titleId = videoPresentation.getSpecification().getTitleId();
            Intrinsics.checkNotNullExpressionValue(titleId, "videoPresentation.specification.titleId");
            arrayList.add(new TimelineItemData(timeUnit, null, null, new ContentInfoData(titleId, ContentType.FEATURE, null, 4, null), null, null, 48, null));
            return new TimelineImpl(arrayList);
        }
    }
}
