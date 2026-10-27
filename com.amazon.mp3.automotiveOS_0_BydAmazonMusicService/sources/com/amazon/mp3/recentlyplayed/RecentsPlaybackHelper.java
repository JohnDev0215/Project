package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPlaybackComponent;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.harley.PlaybackPersistenceManager;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.StationItem;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class RecentsPlaybackHelper {
    private static final String TAG = "RecentsPlaybackHelper";

    public enum RecentRemoteEntityType {
        PRIME_ALBUM,
        PRIME_PLAYLIST,
        STATION,
        PLAYLIST,
        LIBRARY_ALBUM,
        PRIME_ARTIST,
        LIBRARY_ARTIST,
        ALL_SONGS,
        SMART_PLAYLIST,
        NONE
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentsPlaybackHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType;

        static {
            int[] iArr = new int[RecentRemoteEntityType.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType = iArr;
            try {
                iArr[RecentRemoteEntityType.PRIME_PLAYLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.STATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.PRIME_ALBUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.PRIME_ARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.LIBRARY_ARTIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.LIBRARY_ALBUM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.SMART_PLAYLIST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.ALL_SONGS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[RecentRemoteEntityType.NONE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static void handleRemoteRecentPlaybackForNightwing(final Context context, final RecentlyPlayedItem item, final boolean isFullScreen) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[getRemoteEntityType(item).ordinal()];
        if (i == 1) {
            if (item.getCatalogStatusTiers().getIsFree() && item.getCatalogStatusTiers().getIsFreeOnDemandPlayable()) {
                FreeTierPlaybackUtil.playCloudStation(context, new PlayableEntityIdentifier(item.getCollectionAsin(), PlayableEntityIdentifierType.ASIN), true, true, false, PlaybackPageType.GOLDEN_PLAYLIST);
                return;
            } else {
                StationUtils.startSFA(context, item.getCollectionAsin(), PlayableEntityIdentifierType.ASIN.toString(), PlaybackPageType.SFA, ControlSource.APP_UI);
                return;
            }
        }
        if (i == 2) {
            StationItem stationItemFromRemoteRecent = StationStorageUtil.fromRemoteRecent(item);
            StationUtils.startSFA(context, stationItemFromRemoteRecent.getIdentifier(), stationItemFromRemoteRecent.getIdentifierType(), PlaybackPageType.SFA, ControlSource.APP_UI);
        } else if (i == 3 || i == 4) {
            StationUtils.startSFA(context, item.getCollectionAsin(), PlayableEntityIdentifierType.ASIN.toString(), PlaybackPageType.SFA, ControlSource.APP_UI);
        }
    }

    public static void handleRemoteRecentPlayback(final Context context, final RecentlyPlayedItem item, final boolean isFullScreen) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackHelper$RecentRemoteEntityType[getRemoteEntityType(item).ordinal()]) {
            case 1:
                startPlaylistPlayback(context, item.getCollectionAsin(), item.getTrackAsin(), isFullScreen);
                break;
            case 2:
                if (MediaProvider.Station.isStationFromAnything(item.getContentUri())) {
                    StationUtils.startSFA(context, MediaProvider.Station.getStationFromAnythingSeedId(item.getContentUri()), "STATION_SEED_ID", true, false, PlaybackPageType.RECENTLY_PLAYED, null, ControlSource.APP_UI, false);
                } else {
                    startStationPlayback(context, item, isFullScreen);
                }
                break;
            case 3:
                startAlbumPlayback(context, item.getCollectionAsin(), item.getTrackAsin(), isFullScreen);
                break;
            case 4:
                if (context instanceof FragmentActivity) {
                    new PrimeArtistPlaybackHandler((FragmentActivity) context).startPlayback(item.convertToPrimeArtist(), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN);
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                RecentsPlaybackTask.createPlaybackTask(context, item, isFullScreen).execute(new Void[0]);
                break;
            case 9:
                startAllSongsPlayback(context, item, isFullScreen);
                break;
            case 10:
                Log.error(TAG, "Failed to initiate playback for " + item);
                BauhausToastUtils.showTextToast(context, R.string.dmusic_recent_playback_error, 0);
                break;
        }
    }

    public static void handleRemoteRecentPlayback(final Context context, final RecentlyPlayedItem item) {
        handleRemoteRecentPlayback(context, item, true);
    }

    private static void startStationPlayback(final Context context, RecentlyPlayedItem recentlyPlayedItem, final boolean isFullScreen) {
        StationUtils.playStation(context, PrimeItemsTransformationUtil.toPrimeStation(StationStorageUtil.fromRemoteRecent(recentlyPlayedItem)), true, isFullScreen, PlaybackPageType.RECENTLY_PLAYED, null);
    }

    private static void startAlbumPlayback(final Context context, final String albumAsin, final String trackAsin, final boolean isFullScreen) {
        PrimeCollectionTask.Task task;
        PrimeCollectionTask primeCollectionTaskCreatePlaybackTask;
        if (isFullScreen) {
            task = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        } else {
            task = PrimeCollectionTask.Task.PLAYBACK;
        }
        PrimeCollectionTask.Task task2 = task;
        if (!StringUtil.isNullOrWhiteSpaces(trackAsin)) {
            primeCollectionTaskCreatePlaybackTask = PrimeCollectionTask.createPlaybackTask(context, albumAsin, trackAsin, PlaybackPageType.RECENTLY_PLAYED, task2, new QueryAlbumByAsin(context), (PrimeCollectionTask.OnUriReadyCallback) null);
        } else {
            primeCollectionTaskCreatePlaybackTask = PrimeCollectionTask.createPlaybackTask(context, albumAsin, PlaybackPageType.RECENTLY_PLAYED, new QueryAlbumByAsin(context), task2);
        }
        primeCollectionTaskCreatePlaybackTask.execute();
    }

    private static void startAllSongsPlayback(final Context context, final RecentlyPlayedItem recentlyPlayedItem, final boolean isFullScreen) {
        ContextMenuPlaybackComponent.startPlayback(context, recentlyPlayedItem.getContentUri(), 0, null, null, PlaybackPageType.RECENTLY_PLAYED, Clock.now(), isFullScreen, false, recentlyPlayedItem.getContentUri());
    }

    private static void startPlaylistPlayback(final Context context, final String playlistAsin, final String trackAsin, final boolean isFullScreen) {
        GetPrimePlaylistTracksTask.createPlaybackTask(context, playlistAsin, trackAsin, PlaybackPageType.RECENTLY_PLAYED, isFullScreen ? GetPrimePlaylistTracksTask.Task.PLAYBACK_FULLSCREEN : GetPrimePlaylistTracksTask.Task.PLAYBACK).execute(new Void[0]);
    }

    public static RecentRemoteEntityType getRemoteEntityType(final RecentlyPlayedItem item) {
        RecentlyPlayedItem.Type itemType = item.getItemType();
        item.getTrackAsin();
        String trackLuid = item.getTrackLuid();
        if (itemType == null) {
            return RecentRemoteEntityType.NONE;
        }
        RecentlyPlayedItem.Type type = RecentlyPlayedItem.Type.ALBUM;
        if (itemType.equals(type) && !StringUtil.isNullOrWhiteSpaces(trackLuid)) {
            return RecentRemoteEntityType.LIBRARY_ALBUM;
        }
        if (itemType.equals(type)) {
            return RecentRemoteEntityType.PRIME_ALBUM;
        }
        if (itemType.equals(RecentlyPlayedItem.Type.PRIME_PLAYLIST)) {
            return RecentRemoteEntityType.PRIME_PLAYLIST;
        }
        if (itemType.equals(RecentlyPlayedItem.Type.STATION)) {
            return RecentRemoteEntityType.STATION;
        }
        if (itemType.equals(RecentlyPlayedItem.Type.ARTIST)) {
            return RecentRemoteEntityType.LIBRARY_ARTIST;
        }
        if (itemType.equals(RecentlyPlayedItem.Type.PRIME_ARTIST)) {
            return RecentRemoteEntityType.PRIME_ARTIST;
        }
        if (itemType.equals(RecentlyPlayedItem.Type.PLAYLIST)) {
            return RecentRemoteEntityType.PLAYLIST;
        }
        if (RecentlyPlayedItem.Type.ALL_SONGS.equals(itemType)) {
            return RecentRemoteEntityType.ALL_SONGS;
        }
        if (RecentlyPlayedItem.Type.SMART_PLAYLIST.equals(itemType) && !StringUtil.isNullOrWhiteSpaces(trackLuid)) {
            return RecentRemoteEntityType.SMART_PLAYLIST;
        }
        return RecentRemoteEntityType.NONE;
    }

    public static void startRecentlyPlayed(final RecentlyPlayedItem item, final FragmentActivity activity, final RecentStationsUpgradeHandler handler) {
        try {
            Uri currentUri = MediaItemHelper.getCurrentUri();
            Uri contentUri = item.getContentUri();
            if (contentUri == null) {
                Log.error(TAG, "Failed to initiate playback because contentUri is null for " + item);
                BauhausToastUtils.showTextToast(activity, R.string.dmusic_recent_playback_error, 0);
                return;
            }
            Uri uriBuild = contentUri.buildUpon().appendPath("tracks").build();
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI);
            if ((!contentUri.equals(currentUri) && !uriBuild.equals(currentUri)) || MediaProvider.PrimeBrowseTracksCollection.isCollection(uriBuild)) {
                String trackProviderState = item.getTrackProviderState();
                if (MediaProvider.Station.isStation(contentUri)) {
                    if (!ConnectivityUtil.hasAnyInternetConnection()) {
                        NoConnectionDialog.newInstance(false).show(activity.getSupportFragmentManager(), "NoConnectionDialog");
                        return;
                    } else if (MediaProvider.Station.isLegacyStation(contentUri)) {
                        handler.doStationsUpgrade(intentForContentUri, MediaProvider.Station.getLegacySeedId(contentUri), trackProviderState, item);
                        return;
                    } else if (MediaProvider.Station.isStationFromAnything(contentUri)) {
                        StationUtils.startSFA(activity, MediaProvider.Station.getStationFromAnythingSeedId(contentUri), "STATION_SEED_ID", PlaybackPageType.RECENTLY_PLAYED, ControlSource.APP_UI);
                        return;
                    }
                }
                if (DefaultUriMatcher.match(contentUri) == 36) {
                    PlaybackPersistenceManager.getInstance().restoreAlexaRecents(contentUri, ControlSource.APP_UI);
                } else if (!StringUtil.isNullOrWhiteSpaces(trackProviderState)) {
                    PlaybackPersistenceManager.getInstance().restoreFromRecents(new JSONObject(trackProviderState), ControlSource.APP_UI);
                } else {
                    handleRemoteRecentPlayback(activity, item);
                    return;
                }
            }
            activity.startActivity(intentForContentUri);
            activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
        } catch (JSONException e) {
            Log.error(TAG, "Failed to initiate playback for " + item, e);
            BauhausToastUtils.showTextToast(activity, R.string.dmusic_recent_playback_error, 0);
        }
    }
}
