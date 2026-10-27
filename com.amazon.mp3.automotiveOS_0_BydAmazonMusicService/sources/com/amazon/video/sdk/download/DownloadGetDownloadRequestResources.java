package com.amazon.video.sdk.download;

import android.content.Context;
import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.service.GetPlaybackResourcesServiceClient;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.prs.GetPlaybackResources;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesPlayerRequest;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadGetDownloadRequestResources.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadGetDownloadRequestResources;", "", "()V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadGetDownloadRequestResources {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: DownloadGetDownloadRequestResources.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadGetDownloadRequestResources$Companion;", "", "()V", "getDownloadPRSResources", "Lcom/amazon/video/sdk/download/DownloadGetDownloadRequestResourcesWrapper;", "config", "Lcom/amazon/video/sdk/player/ContentConfig;", "context", "Landroid/content/Context;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadGetDownloadRequestResourcesWrapper getDownloadPRSResources(ContentConfig config, Context context) throws BoltException {
            Optional<AudioVideoUrls> audioVideoUrls;
            AudioVideoUrls audioVideoUrlsOrNull;
            ImmutableList<AudioTrackMetadata> audioTrackMetadataList;
            List listDistinct;
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(context, "context");
            GetPlaybackResources getPlaybackResourcesCreateNonCachingInstance = GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesPlayerRequest());
            Intrinsics.checkNotNullExpressionValue(getPlaybackResourcesCreateNonCachingInstance, "createNonCachingInstance…ResourcesPlayerRequest())");
            boolean z = true;
            PlaybackResourcesWrapper playbackResourcesWrapper = getPlaybackResourcesCreateNonCachingInstance.get(config.getTitleId(), VideoMaterialType.Feature, ConsumptionType.Download, ResourceUsage.ImmediateConsumption, Sets.newHashSet(Resource.PlaybackUrls), config.getSessionContext(), null);
            Intrinsics.checkNotNullExpressionValue(playbackResourcesWrapper, "playbackResourcesFactory…                    null)");
            PRSException pRSExceptionOrNull = playbackResourcesWrapper.getError().orNull();
            if (pRSExceptionOrNull != null) {
                Optional<String> optional = pRSExceptionOrNull.errorCode;
                ContentException.ContentError contentError = GetPlaybackResourcesServiceClient.toContentError(optional != null ? optional.orNull() : null);
                Intrinsics.checkNotNullExpressionValue(contentError, "toContentError(error.errorCode?.orNull())");
                DLog.errorf("DownloadFeature: Cannot make initial PRS call, %s", contentError.toReportableString());
                ImmutableList immutableListOf = ImmutableList.of();
                Intrinsics.checkNotNullExpressionValue(immutableListOf, "of()");
                return new DownloadGetDownloadRequestResourcesWrapper(immutableListOf, new PlaybackError(contentError.getExternalCode(), context));
            }
            PlaybackResources playbackResourcesOrNull = playbackResourcesWrapper.getPlaybackResources().orNull();
            if (playbackResourcesOrNull == null || (audioVideoUrls = playbackResourcesOrNull.getAudioVideoUrls()) == null || (audioVideoUrlsOrNull = audioVideoUrls.orNull()) == null || (audioTrackMetadataList = audioVideoUrlsOrNull.getAudioTrackMetadataList()) == null) {
                listDistinct = null;
            } else {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(audioTrackMetadataList, 10));
                Iterator<AudioTrackMetadata> it = audioTrackMetadataList.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getAudioTrackId());
                }
                listDistinct = CollectionsKt___CollectionsKt.distinct(arrayList);
            }
            if (listDistinct != null && !listDistinct.isEmpty()) {
                z = false;
            }
            if (z) {
                ImmutableList immutableListOf2 = ImmutableList.of();
                Intrinsics.checkNotNullExpressionValue(immutableListOf2, "of()");
                return new DownloadGetDownloadRequestResourcesWrapper(immutableListOf2, null);
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) listDistinct);
            Intrinsics.checkNotNullExpressionValue(immutableListCopyOf, "copyOf(audioTrackIds)");
            return new DownloadGetDownloadRequestResourcesWrapper(immutableListCopyOf, null);
        }
    }
}
