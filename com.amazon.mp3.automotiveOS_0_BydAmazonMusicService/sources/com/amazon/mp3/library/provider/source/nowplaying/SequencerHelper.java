package com.amazon.mp3.library.provider.source.nowplaying;

import android.net.Uri;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;

/* JADX INFO: loaded from: classes3.dex */
public class SequencerHelper {
    public static long NO_ALBUMID_AVAILABLE = -2;

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType;

        static {
            int[] iArr = new int[SelectionSourceType.values().length];
            $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType = iArr;
            try {
                iArr[SelectionSourceType.PRIME_PLAYLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.PRIME_STATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.PRIME_STATION_SEED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.UNLIMITED_PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.UNLIMITED_STATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.UNLIMITED_STATION_SEED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.USER_PLAYLIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.AUTO_PLAYLIST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.ALBUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.GENRE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.ARTIST.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.SHARED_PLAYLIST.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.GOLDEN_PLAYLIST.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[SelectionSourceType.SONGS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public static MediaAccessInfo getMediaAccessInfo(final SelectionSourceType selectionSourceType, final boolean online) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[selectionSourceType.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return MediaAccessInfo.forEligibility(false, true, !online, online, false, true, true, true, false);
            case 4:
            case 5:
            case 6:
                return MediaAccessInfo.forEligibility(false, true, !online, online, false, false, false, true, false);
            default:
                return MediaAccessInfo.forEligibility(false, true, !online, online, true, true, true, true, false);
        }
    }

    public static MediaCollectionType getMediaCollectionType(final SelectionSourceType selectionSourceType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$metrics$mts$event$types$SelectionSourceType[selectionSourceType.ordinal()]) {
            case 1:
                return MediaCollectionType.PRIME_PLAYLIST;
            case 2:
                return MediaCollectionType.PRIME_STATION;
            case 3:
                return MediaCollectionType.PRIME_STATION_SEED;
            case 4:
                return MediaCollectionType.UNLIMITED_PLAYLIST;
            case 5:
                return MediaCollectionType.UNLIMITED_STATION;
            case 6:
                return MediaCollectionType.UNLIMITED_STATION_SEED;
            case 7:
                return MediaCollectionType.USER_PLAYLIST;
            case 8:
                return MediaCollectionType.AUTO_PLAYLIST;
            case 9:
                return MediaCollectionType.ALBUM;
            case 10:
                return MediaCollectionType.GENRE;
            case 11:
                return MediaCollectionType.ARTIST;
            case 12:
                return MediaCollectionType.SHARED_PLAYLIST;
            case 13:
                return MediaCollectionType.GOLDEN_PLAYLIST;
            default:
                return MediaCollectionType.SONGS;
        }
    }

    public static MediaCollectionInfo updateMediaCollectionInfo(final TrackProvider provider, final MediaCollectionInfo baseInfo, final Uri uri, final String albumAsin, final String artistAsin, final long albumId) {
        MediaCollectionId mediaCollectionId;
        MediaCollectionType type;
        MediaAccessInfo mediaAccessInfo;
        String collectionName = provider.getCollectionName();
        SelectionSourceInfo selectionSourceInfo = SelectionSourceHelper.getSelectionSourceInfo(provider.getUri(), albumAsin, artistAsin, collectionName);
        String selectionSourceId = null;
        if (baseInfo != null) {
            type = baseInfo.getType();
            mediaAccessInfo = baseInfo.getMediaAccessInfo();
            mediaCollectionId = baseInfo.getId();
        } else {
            mediaCollectionId = null;
            type = null;
            mediaAccessInfo = null;
        }
        if (mediaCollectionId == null) {
            mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.LIBRARY_COLLECTION, uri.toString());
        }
        if (selectionSourceInfo != null) {
            if (type == null || mediaAccessInfo == null) {
                MediaCollectionType mediaCollectionType = getMediaCollectionType(selectionSourceInfo.getSelectionSourceType());
                mediaAccessInfo = getMediaAccessInfo(selectionSourceInfo.getSelectionSourceType(), true);
                type = mediaCollectionType;
            }
            selectionSourceId = selectionSourceInfo.getSelectionSourceId();
        }
        MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(collectionName, null, mediaCollectionId, type, mediaAccessInfo, new MediaCollectionId[0]);
        if (selectionSourceInfo != null && ((mediaCollectionId.getType() == MediaCollectionId.Type.LIBRARY_COLLECTION || type == MediaCollectionType.SONGS || !StringUtils.isEmpty(selectionSourceId)) && !ObjectUtils.equal(selectionSourceId, mediaCollectionId.getId()))) {
            mediaCollectionInfo.addId(new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, StringUtils.nullToEmpty(selectionSourceId)));
        }
        if (albumId != NO_ALBUMID_AVAILABLE) {
            mediaCollectionInfo.addId(new MediaCollectionId(MediaCollectionId.Type.CUSTOM, String.valueOf(albumId)));
        }
        return mediaCollectionInfo;
    }

    public static boolean canModifyQueue() {
        return PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentQueue().size() > 0 && !NowPlayingUtil.isStationPlaying() && !NowPlayingUtil.isAlexaPlaying() && !NowPlayingUtil.isCloudStationPlaying() && CastingUtil.isPlayQueueSupported() && Feature.context_menu_play_queue.isEnabled();
    }
}
