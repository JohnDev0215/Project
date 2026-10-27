package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import java.util.Collections;
import rx.Completable;
import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class RecentsPlaybackListener implements OnPlayStateChangedListener {
    private Context mContext;

    @Nullable
    private MediaItem previousMediaItem;
    private RecentsCountDownTimer timer;
    private long prevTrackId = -1;
    private long currentTrackId = -1;
    private final PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.NONE);

    public RecentsPlaybackListener(final Context context) {
        this.timer = RecentsCountDownTimer.getInstance(context);
        this.mContext = context;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentsPlaybackListener$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlayStatus;

        static {
            int[] iArr = new int[PlayStatus.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlayStatus = iArr;
            try {
                iArr[PlayStatus.RENDERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$PlayStatus[this.mPlaybackController.getPlayStatus().ordinal()] == 1) {
            MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
            if (currentMediaItem == null) {
                return;
            }
            MediaItem mediaItem = this.previousMediaItem;
            if (mediaItem == null || !currentMediaItem.hasSameId(mediaItem)) {
                this.timer.start();
                this.previousMediaItem = currentMediaItem;
                if (NowPlayingUtil.isStationPlaying()) {
                    tryAddStationTrackIntoCache();
                    return;
                }
                return;
            }
            this.timer.resume();
            return;
        }
        this.timer.pause();
    }

    private Subscription tryAddStationTrackIntoCache() {
        final MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        return Completable.fromAction(new Action0() { // from class: com.amazon.mp3.recentlyplayed.RecentsPlaybackListener$$ExternalSyntheticLambda0
            @Override // rx.functions.Action0
            public final void call() {
                this.f$0.lambda$tryAddStationTrackIntoCache$0(currentMediaItem);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: persistStationTrack, reason: merged with bridge method [inline-methods] */
    public void lambda$tryAddStationTrackIntoCache$0(final MediaItem mediaItem) {
        ContentOwnershipStatus contentOwnershipStatus;
        if (mediaItem == null) {
            return;
        }
        CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(this.mContext);
        MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        if (mediaItemId == null) {
            mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.OWNED);
        }
        if (mediaItemId == null) {
            return;
        }
        String id = mediaItemId.getId();
        if (StringUtil.isNullOrWhiteSpaces(id) || cTAPrimeCache.hasPrimeContent(id)) {
            return;
        }
        MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
        MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
        String mediaCollectionId = getMediaCollectionId(mediaItem.getMediaCollectionInfo(mediaCollectionType, type));
        String mediaCollectionId2 = getMediaCollectionId(mediaItem.getMediaCollectionInfo(MediaCollectionType.ARTIST, type));
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        Uri uriCreateImageUri = mediaItem.createImageUri(R.dimen.song_tile_art_size, R.dimen.song_tile_art_size, new MediaItem.ImageType[0]);
        if (mediaAccessInfo == null || mediaCollectionId == null || mediaCollectionId2 == null || uriCreateImageUri == null) {
            return;
        }
        if (mediaAccessInfo.isOwned()) {
            contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        } else {
            contentOwnershipStatus = ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        PrimeTrack primeTrack = new PrimeTrack(mediaItem.getName(), id, uriCreateImageUri.toString(), mediaItem.getArtistName(), mediaCollectionId2, mediaItem.getAlbumName(), mediaCollectionId, null, false, contentOwnershipStatus, CatalogStatusTiers.INSTANCE.from(mediaAccessInfo));
        primeTrack.setContentEncoding(mediaItem.getContentEncodings());
        cTAPrimeCache.insertPrimeTracks(mediaCollectionId, mediaItem.getAlbumName(), null, Collections.singletonList(primeTrack));
    }

    @Nullable
    private String getMediaCollectionId(final MediaCollectionInfo mediaCollectionInfo) {
        if (mediaCollectionInfo != null) {
            return mediaCollectionInfo.getId().getId();
        }
        return null;
    }
}
