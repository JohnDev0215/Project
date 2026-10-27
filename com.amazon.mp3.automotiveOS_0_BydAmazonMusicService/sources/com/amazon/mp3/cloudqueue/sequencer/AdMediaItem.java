package com.amazon.mp3.cloudqueue.sequencer;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import com.amazon.digitalmusicxp.types.SingleActionStrategy;
import com.amazon.mp3.cloudqueue.PlaybackActionCapability;
import com.amazon.mp3.cloudqueue.model.AdEntity;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
public class AdMediaItem extends MediaItem implements PlaybackActionCapabilityQueryable, PlaybackControlVisibilityQueryable {
    private final String adAudioUrl;

    @Nullable
    private final String adClickUrl;
    private final AdEntity adEntity;
    private final String adImageUrl;
    private final String artistName;

    @NonNull
    private final String blockRef;
    private final QueueEntityCapability capabilities;

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        return false;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canSeek() {
        return false;
    }

    public AdMediaItem(final AdEntity adEntity, final MediaCollectionInfo collectionInfo, final MediaLinkProvider mediaLinkProvider, final PlaybackPageType playbackPageType) {
        super(new MediaItemId(MediaItemId.Type.URL, adEntity.getAdAudioUrl() == null ? "" : convertUrlFormat(adEntity.getAdAudioUrl())), adEntity.getAdName(), TimeUnit.SECONDS.toMillis(adEntity.getDurationInSeconds()), MediaItem.Type.AD_CONTENT, new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withCastable(adEntity.getPlaybackActionCapability("CASTING").getActionStatus() == 0).withExplicit(false).withContentAccessType(ContentAccessType.NIGHTWING).build(), null, mediaLinkProvider, new AdImageUriProvider(adEntity), playbackPageType, false, collectionInfo, new MediaCollectionInfo[0]);
        this.adEntity = adEntity;
        this.adAudioUrl = adEntity.getAdAudioUrl();
        this.adImageUrl = adEntity.getAdImageUrl();
        this.adClickUrl = adEntity.getAdClickUrl();
        this.blockRef = adEntity.getBlockRef();
        this.artistName = adEntity.getProductName() != null ? adEntity.getProductName() : "";
        setMetricsContext(adEntity.getMetricsContext());
        this.capabilities = null;
    }

    public AdMediaItem(final String adAudioUrl, final String adName, final String artistName, final long durationInMs, final String adImageUrl, final String adClickUrl, final String contentBlockRef, final MediaCollectionInfo collectionInfo, final MediaLinkProvider mediaLinkProvider, final PlaybackPageType playbackPageType, final Map<String, String> metricsContext, final QueueEntityCapability capabilities) {
        super(new MediaItemId(MediaItemId.Type.URL, adAudioUrl == null ? "" : convertUrlFormat(adAudioUrl)), adName, durationInMs, MediaItem.Type.AD_CONTENT, new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withCastable(false).withExplicit(false).withContentAccessType(ContentAccessType.NIGHTWING).build(), null, mediaLinkProvider, new AdImageUriProvider(adImageUrl), playbackPageType, false, collectionInfo, new MediaCollectionInfo[0]);
        this.adAudioUrl = adAudioUrl;
        this.adImageUrl = adImageUrl;
        this.adClickUrl = adClickUrl;
        this.blockRef = contentBlockRef;
        this.artistName = artistName;
        this.capabilities = capabilities;
        setMetricsContext(metricsContext);
        this.adEntity = null;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.PlaybackActionCapabilityQueryable
    public PlaybackActionCapability getPlaybackActionCapability(String action) {
        return this.adEntity.getPlaybackActionCapability(action);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.PlaybackControlVisibilityQueryable
    public boolean canDisplay(String action) {
        QueueEntityCapability queueEntityCapability = this.capabilities;
        if (queueEntityCapability != null) {
            return ((Boolean) Optional.ofNullable(queueEntityCapability).map(new Function() { // from class: com.amazon.mp3.cloudqueue.sequencer.AdMediaItem$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((QueueEntityCapability) obj).getDisplayable();
                }
            }).map(new Function() { // from class: com.amazon.mp3.cloudqueue.sequencer.AdMediaItem$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ActionStrategy) obj).getSingleActionStrategy();
                }
            }).map(new Function() { // from class: com.amazon.mp3.cloudqueue.sequencer.AdMediaItem$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((SingleActionStrategy) obj).getBooleanAction();
                }
            }).orElse(Boolean.FALSE)).booleanValue();
        }
        PlaybackActionCapability playbackActionCapability = this.adEntity.getPlaybackActionCapability(action);
        if (playbackActionCapability != null) {
            return playbackActionCapability.canDisplay();
        }
        return false;
    }

    public static class AdImageUriProvider implements MediaItem.ImageUriProvider {
        private String mAdImageUri;

        public AdImageUriProvider(AdEntity adEntity) {
            this.mAdImageUri = adEntity.getAdImageUrl();
        }

        public AdImageUriProvider(@NonNull String url) {
            this.mAdImageUri = url;
        }

        @Override // com.amazon.music.media.playback.MediaItem.ImageUriProvider
        public Uri getImageUri(@NonNull final MediaItem.ImageInfo imageInfo) {
            return Uri.parse(this.mAdImageUri);
        }

        @Override // com.amazon.music.media.playback.MediaItem.ImageUriProvider
        public Uri createImageUri(@NonNull final MediaItem.ImageInfo imageInfo) {
            return Uri.parse(this.mAdImageUri);
        }
    }

    @Nullable
    public Uri getAdClickUri() {
        if (TextUtils.isEmpty(this.adClickUrl)) {
            return null;
        }
        return Uri.parse(this.adClickUrl);
    }

    @NonNull
    public String getAdBlockRef() {
        return this.blockRef;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    @NonNull
    public String getArtistName() {
        return this.artistName;
    }

    private static String convertUrlFormat(@NonNull String url) {
        return url.startsWith("https") ? url.replace("https", "adurl") : url;
    }
}
