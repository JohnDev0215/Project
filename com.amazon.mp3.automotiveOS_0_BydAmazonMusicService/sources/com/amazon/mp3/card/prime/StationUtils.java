package com.amazon.mp3.card.prime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.casting.CastingPlaybackProvider;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.prime.station.StationsFactory;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.mp3.util.AutoPlaySFAUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.StationSequencer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.station.StationItem;
import com.amazon.music.subscription.BrowseMode;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import com.amplifyframework.core.model.ModelIdentifier;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class StationUtils {
    private static final String TAG = "StationUtils";

    private StationUtils() {
    }

    public static void startAutoPlaySFA(final Context context, final List<Track> identifiers) {
        StationItem stationItem;
        if (identifiers == null || identifiers.isEmpty()) {
            Log.error(TAG, "Empty identifier passed to startAutoPlaySFA, ignoring");
            return;
        }
        boolean z = false;
        if (identifiers.size() == 1) {
            stationItem = new StationItem(new CatalogStatusTiers(true, true, false, false).toSet(), StationItem.buildExplicitMap(false), context.getString(R.string.dmusic_sfa_title), "", "", "", "", "", "TRACK_SEED", identifiers.get(0).getIdentifier());
        } else {
            stationItem = new StationItem(new CatalogStatusTiers(true, true, false, false).toSet(), StationItem.buildExplicitMap(false), context.getString(R.string.dmusic_sfa_title), "", "", "", "", "", "TRACK_LIST_SEED", identifiers);
        }
        if (!CarModeUtility.isInCarMode() && !AutomotiveServiceUtil.INSTANCE.isAutomotiveServiceRunning()) {
            z = true;
        }
        playStationForSFA(context, stationItem, true, z, PlaybackPageType.EXTERNAL);
    }

    public static void startSFA(final Context context, final List<Track> identifiers, final String identifierType, final PlaybackPageType playbackPageType) {
        startSFA(context, ControlSource.APP_UI, identifiers, identifierType, null, true, playbackPageType, null, false, true);
    }

    public static void startSFA(final Context context, final List<PlayableEntityIdentifier> identifiers, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation) {
        startSFA(context, identifiers, (String) null, true, playbackPageType, upsellInformation);
    }

    public static void startSFA(final Context context, final List<PlayableEntityIdentifier> identifiers, @Nullable final String queueTitle, final boolean startFullScreen, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation) {
        if (identifiers.size() > 1) {
            startSFA(context, ControlSource.APP_UI, convertPlayableEntityIdentifierToTracks(identifiers), "TRACK_LIST_SEED", queueTitle, startFullScreen, playbackPageType, upsellInformation, false, true);
        } else {
            PlayableEntityIdentifier playableEntityIdentifier = identifiers.get(0);
            startSFA(context, playableEntityIdentifier.getIdentifier(), playableEntityIdentifier.getPlayableEntityIdentifierType().toString(), ControlSource.APP_UI, queueTitle, true, startFullScreen, playbackPageType, upsellInformation, false, true);
        }
    }

    public static void startSFA(final Context context, final ControlSource controlSource, final List<Track> identifiers, final String identifierType, @Nullable final String queueTitle, final boolean startFullScreen, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation, final boolean is3PVoiceInitiated, final boolean isUserInitiated) {
        if (identifiers == null || identifiers.isEmpty()) {
            Log.error(TAG, "Empty identifier passed to start SFA, ignoring");
        } else {
            playStation(context, buildDummySFAStationItem(identifiers, identifierType), queueTitle, controlSource, true, startFullScreen, playbackPageType, null, Clock.now(), null, null, upsellInformation, null, is3PVoiceInitiated, isUserInitiated);
        }
    }

    public static void startSFA(final Context context, final String identifier, final String identifierType, final PlaybackPageType playbackPageType, final ControlSource controlSource, final boolean isUserInitiated) {
        startSFA(context, identifier, identifierType, true, true, playbackPageType, null, controlSource, isUserInitiated);
    }

    public static void startSFA(final Context context, final String identifier, final String identifierType, final PlaybackPageType playbackPageType, final ControlSource controlSource) {
        startSFA(context, identifier, identifierType, true, true, playbackPageType, null, controlSource, true);
    }

    public static void startSFA(final Context context, final String identifier, final String identifierType, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation, final ControlSource controlSource, final boolean isUserInitiated) {
        if (TextUtils.isEmpty(identifier)) {
            Log.error(TAG, "Empty identifier passed to start SFA, ignoring");
        } else {
            playStation(context, buildDummySFAStationItem(identifier, identifierType), startPlayback, startFullscreen, playbackPageType, Clock.now(), upsellInformation, controlSource, isUserInitiated);
        }
    }

    public static void startSFA(final Context context, final String identifier, final String identifierType, final ControlSource controlSource, @Nullable final String queueTitle, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation, final boolean isVoiceInitiated, final boolean isUserInitiated) {
        if (TextUtils.isEmpty(identifier)) {
            Log.error(TAG, "Empty identifier passed to start SFA, ignoring");
        } else {
            playStation(context, buildDummySFAStationItem(identifier, identifierType), queueTitle, controlSource, startPlayback, startFullscreen, playbackPageType, null, Clock.now(), null, null, upsellInformation, null, isVoiceInitiated, isUserInitiated);
        }
    }

    public static void playStation(final Context context, final Station station, final PlaybackPageType playbackPageType) {
        playStation(context, station, true, true, playbackPageType, null);
    }

    public static void playStation(final Context context, final Station searchStation, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation) {
        playStation(context, Station.toStationItem(searchStation), startPlayback, startFullscreen, playbackPageType, null, null, upsellInformation);
    }

    public static void playStation(final Context context, final Station station, final ControlSource controlSource, @Nullable final String queueTitle, final boolean startFullscreen, final PlaybackPageType playbackPageType, @Nullable final PlaybackListener playbackListener, final boolean isVoiceInitiated, final boolean isUserInitiated) {
        playStation(context, Station.toStationItem(station), queueTitle, controlSource, true, startFullscreen, playbackPageType, null, Clock.now(), null, null, null, playbackListener, isVoiceInitiated, isUserInitiated);
    }

    public static void playStation(final Context context, final Station searchStation, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final String refMarker, final String associateTag) {
        playStation(context, Station.toStationItem(searchStation), startPlayback, startFullscreen, playbackPageType, refMarker, associateTag, null);
    }

    public static void playStation(final Context context, final StationItem stationItem, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final String refMarker, final String associateTag, final UpsellInformation upsellInformation) {
        playStation(context, stationItem, ControlSource.APP_UI, startPlayback, startFullscreen, playbackPageType, null, Clock.now(), refMarker, associateTag, upsellInformation, false, true);
    }

    public static void playStationForSFA(final Context context, final StationItem stationItem, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType) {
        final long jNow = Clock.now();
        AutoPlaySFAUtil.onInitiateSfa();
        StationStorageUtil.insertStationWithDataChecks(context, stationItem);
        StartPlaybackUtil.startStationPlayback(context, stationItem.getTitle(), stationItem, getStationUri(stationItem), startFullscreen, playbackPageType, ControlSource.APP_UI, null, null, null, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.card.prime.StationUtils$$ExternalSyntheticLambda3
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public final void handlePlayback() {
                StationUtils.lambda$playStationForSFA$0(context, stationItem, playbackPageType, startPlayback, jNow, startFullscreen);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playStationForSFA$0(Context context, StationItem stationItem, PlaybackPageType playbackPageType, boolean z, long j, boolean z2) {
        ControlSource controlSource = ControlSource.APP_UI;
        StationSequencer stationSequencerCreateStationSequencer = createStationSequencer(context, controlSource, stationItem, playbackPageType, null);
        if (isStationValid(stationSequencerCreateStationSequencer)) {
            AutoPlaySFAUtil.onInitiateSfa();
            boolean zStartStationFromSequence = startStationFromSequence(controlSource, stationSequencerCreateStationSequencer, z, j);
            if (z2) {
                stationInFullScreen(context, zStartStationFromSequence);
            }
        }
    }

    public static void playStationForATC(final Context context, final StationItem stationItem, final Boolean startPlayback, final Boolean startFullscreen, final PlaybackPageType playbackPageType, final String refMarker, final String tag) {
        CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.card.prime.StationUtils$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                StationUtils.lambda$playStationForATC$1(context, stationItem, startPlayback, startFullscreen, playbackPageType, refMarker, tag, (CloudQueueAppClient) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playStationForATC$1(Context context, StationItem stationItem, Boolean bool, Boolean bool2, PlaybackPageType playbackPageType, String str, String str2, CloudQueueAppClient cloudQueueAppClient) throws Exception {
        if (cloudQueueAppClient instanceof NoOpCloudQueueAppClient) {
            startStationSequencerHelper(context, stationItem, ControlSource.APP_UI, bool.booleanValue(), bool2.booleanValue(), playbackPageType, null, Clock.now(), str, str2);
        } else {
            playStation(context, stationItem, bool.booleanValue(), bool2.booleanValue(), playbackPageType, str, str2, null);
        }
    }

    private static boolean isStationValid(StationSequencer stationSequencer) {
        return stationSequencer.isStationAvailableBlocking();
    }

    @SuppressLint({"CheckResult"})
    public static void playStation(final Context context, final StationItem stationItem, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final long startTime, final UpsellInformation upsellInformation, final ControlSource controlSource, final boolean isUserInitiated) {
        playStation(context, stationItem, controlSource, startPlayback, startFullscreen, playbackPageType, null, startTime, null, null, upsellInformation, false, isUserInitiated);
    }

    public static void playStation(final Context context, final StationItem stationItem, final ControlSource controlSource, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final MediaCollectionId additionalMediaCollectionId, final long startTime, final String refMarker, final String associateTag, final UpsellInformation upsellInformation, final boolean isVoiceInitiated, final boolean isUserInitiated) {
        playStation(context, stationItem, null, controlSource, startPlayback, startFullscreen, playbackPageType, additionalMediaCollectionId, startTime, refMarker, associateTag, upsellInformation, null, isVoiceInitiated, isUserInitiated);
    }

    @SuppressLint({"CheckResult"})
    public static void playStation(final Context context, final StationItem stationItem, @Nullable final String queueTitle, final ControlSource controlSource, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final MediaCollectionId additionalMediaCollectionId, final long startTime, final String refMarker, final String associateTag, final UpsellInformation upsellInformation, @Nullable final PlaybackListener playbackListener, final boolean is3PVoiceInitiated, final boolean isUserInitiated) {
        boolean zIsCloudQueuePlaybackEnabled;
        stationItem.getNextToken();
        boolean zEquals = ControlSource.ALEXA.equals(controlSource);
        if ("PREFER_KEY_OVER_NEXT_TOKEN".equals(stationItem.getIdentifierType())) {
            zIsCloudQueuePlaybackEnabled = zEquals ? true : Feature.t1r_cloud_queue.isEnabled();
        } else {
            zIsCloudQueuePlaybackEnabled = AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled();
        }
        if (zEquals && !is3PVoiceInitiated) {
            zIsCloudQueuePlaybackEnabled = zIsCloudQueuePlaybackEnabled && AmazonApplication.getCapabilities().isCloudQueueEnabledForAOM(controlSource);
        }
        StationStorageUtil.insertStationWithDataChecks(context, stationItem);
        if (zIsCloudQueuePlaybackEnabled) {
            StartPlaybackUtil.startStationPlayback(context, TextUtils.isEmpty(queueTitle) ? context.getString(R.string.station) : queueTitle, stationItem, getStationUri(stationItem), startFullscreen, playbackPageType, controlSource, additionalMediaCollectionId, upsellInformation, playbackListener, isUserInitiated, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.card.prime.StationUtils$$ExternalSyntheticLambda1
                @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                public final void handlePlayback() {
                    StationUtils.startStationSequencerHelper(context, stationItem, controlSource, startPlayback, startFullscreen, playbackPageType, additionalMediaCollectionId, startTime, refMarker, associateTag);
                }
            });
        } else {
            startStationSequencerHelper(context, stationItem, controlSource, startPlayback, startFullscreen, playbackPageType, additionalMediaCollectionId, startTime, refMarker, associateTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startStationSequencerHelper(final Context context, final StationItem stationItem, final ControlSource controlSource, final boolean startPlayback, final boolean startFullscreen, final PlaybackPageType playbackPageType, final MediaCollectionId additionalMediaCollectionId, final long startTime, final String refMarker, final String associateTag) {
        boolean zStartStationFromSequence;
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        if (isCurrentlyPlaying(controlSource, stationItem)) {
            if (startPlayback) {
                controller.play(startTime);
            }
            zStartStationFromSequence = false;
        } else {
            zStartStationFromSequence = startStationFromSequence(controlSource, createStationSequencer(context, controlSource, stationItem, playbackPageType, additionalMediaCollectionId), startPlayback, startTime);
        }
        if (startFullscreen) {
            stationInFullScreen(context, zStartStationFromSequence, refMarker, associateTag);
        }
    }

    private static void stationInFullScreen(Context context, boolean willStartPlayback) {
        stationInFullScreen(context, willStartPlayback, null, null);
    }

    private static void stationInFullScreen(Context context, boolean willStartPlayback, String refMarker, String associateTag) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
        intentForContentUri.putExtra("com.amazon.music.station.EXTRA_SHOW_STATION_LOADING", willStartPlayback);
        if (!(context instanceof Activity)) {
            intentForContentUri.setFlags(268435456);
        }
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), refMarker);
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), associateTag);
        context.startActivity(intentForContentUri);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
        }
    }

    private static boolean startStationFromSequence(final ControlSource controlSource, StationSequencer stationSequencer, boolean startPlayback, long startTime) {
        SequencerPlaybackProvider sequencerPlaybackProvider;
        Playback playback = Playback.getInstance();
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(controlSource);
        PlaybackProvider playbackProvider = playback.getPlaybackProvider();
        if (playbackProvider instanceof CastingPlaybackProvider) {
            ((CastingPlaybackProvider) playbackProvider).startSequencer(stationSequencer, controlSource, startTime);
            return true;
        }
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
        } else {
            sequencerPlaybackProvider = (SequencerPlaybackProvider) playback.getPlaybackConfig().getDefaultPlaybackProvider(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext()));
            controller.stop(ChangeReason.NEW_SOURCE);
            Log.info(TAG, "playStation switching to local playback, calling setPlaybackProvider() -> shutdown(false)");
            playback.setPlaybackProvider(sequencerPlaybackProvider);
        }
        if (startPlayback) {
            sequencerPlaybackProvider.startSequencer(stationSequencer, controller.getControlSource(), startTime);
            return startPlayback;
        }
        sequencerPlaybackProvider.setSequencer(stationSequencer, controller.getControlSource());
        return startPlayback;
    }

    @WorkerThread
    public static List<String> getAsinListSeedFromFollowedPlaylistId(final Context context, final String sharedPlaylistId) {
        ArrayList arrayList = new ArrayList();
        if (sharedPlaylistId == null) {
            Log.error(TAG, "sharedPlaylistId is null when getting the seed for SFA");
            return arrayList;
        }
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("PrimePlaylistToTrack", new String[]{"track_asin"}, "playlist_id = " + sharedPlaylistId, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    do {
                        arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndex("track_asin")));
                        if (arrayList.size() >= 100) {
                            cursorQuery.close();
                            return arrayList;
                        }
                    } while (cursorQuery.moveToNext());
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    @WorkerThread
    public static List<String> getAsinListSeedFromUserPlaylistLuid(final Context context, final String playListLuid) {
        ArrayList arrayList = new ArrayList();
        if (playListLuid == null) {
            Log.error(TAG, "playListLuid is null when getting the seed for SFA");
            return arrayList;
        }
        return getAsinListSeedFromUserPlaylistUri(context, MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playListLuid));
    }

    @WorkerThread
    public static List<String> getAsinListSeedFromUserPlaylistLuid(final Context context, final String playListLuid, final String sourceId) {
        if (sourceId == null) {
            return getAsinListSeedFromUserPlaylistLuid(context, playListLuid);
        }
        ArrayList arrayList = new ArrayList();
        if (playListLuid == null) {
            Log.error(TAG, "playListLuid is null when getting the seed for SFA");
            return arrayList;
        }
        return getAsinListSeedFromUserPlaylistUri(context, MediaProvider.UdoPlaylists.getContentUriFromLuid(context, sourceId, playListLuid));
    }

    @WorkerThread
    public static List<String> getAsinListSeedFromPlaylistId(final Context context, final String playListid, final String sourceId) {
        CatalogPlaylist catalogPlaylist = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playListid));
        if (catalogPlaylist != null) {
            return getAsinListSeedFromFollowedPlaylistId(context, String.valueOf(catalogPlaylist.getDatabaseId()));
        }
        return getAsinListSeedFromUserPlaylistLuid(context, playListid, sourceId);
    }

    @WorkerThread
    public static boolean containsValidAsinsInUserPlaylist(final Context context, final String playListLuid, String sourceId) {
        if (playListLuid == null) {
            return false;
        }
        if (sourceId == null) {
            sourceId = "cirrus";
        }
        return PlaylistUtil.hasAsinsInUdoPlaylist(context, MediaProvider.UdoPlaylists.getContentUriFromLuid(context, sourceId, playListLuid));
    }

    @WorkerThread
    public static List<String> getAsinListSeedFromUserPlaylistUri(final Context context, final Uri udoPlaylistUri) {
        ArrayList arrayList = new ArrayList();
        if (udoPlaylistUri == null) {
            Log.error(TAG, "udoPlaylistUri is null when getting the seed for SFA");
            return arrayList;
        }
        Set<String> udoPlaylistTracksLuids = PlaylistUtil.getUdoPlaylistTracksLuids(context, udoPlaylistUri);
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = udoPlaylistTracksLuids.iterator();
        for (int i = 0; i < Math.min(udoPlaylistTracksLuids.size(), 100); i++) {
            arrayList2.add(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR + it.next() + ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        }
        return queryAsinListFromLuidList(context, arrayList2);
    }

    @WorkerThread
    public static List<String> getAsinListFromTrackLuidSet(final Context context, final Set<String> trackLuidSet) {
        return getAsinListFromTrackLuidList(context, new ArrayList(trackLuidSet));
    }

    @WorkerThread
    public static List<String> getAsinListFromTrackLuidList(final Context context, final List<String> trackLuidList) {
        ArrayList arrayList = new ArrayList();
        if (trackLuidList == null || trackLuidList.size() == 0) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = trackLuidList.iterator();
        for (int i = 0; i < Math.min(trackLuidList.size(), 100); i++) {
            arrayList2.add(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR + it.next() + ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        }
        return queryAsinListFromLuidList(context, arrayList2);
    }

    @WorkerThread
    private static List<String> queryAsinListFromLuidList(final Context context, final List<String> luidList) {
        ArrayList arrayList = new ArrayList();
        if (luidList != null && luidList.size() != 0) {
            Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"asin"}, "luid in (" + TextUtils.join(",", luidList) + ")", null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
                            if (!TextUtils.isEmpty(string)) {
                                arrayList.add(string);
                            }
                        } while (cursorQuery.moveToNext());
                    }
                } catch (Throwable th) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    public static List<Track> getTrackListSeedFromQueue() {
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            ArrayList arrayList = new ArrayList();
            QueueSequenceSlice currentPlayingLocalQueueSequenceSlice = CloudQueueSequencer.INSTANCE.getCurrentPlayingLocalQueueSequenceSlice();
            if (currentPlayingLocalQueueSequenceSlice != null) {
                Iterator<EntityReference> it = currentPlayingLocalQueueSequenceSlice.getEntityReferences().iterator();
                while (it.hasNext()) {
                    addTrack(arrayList, it.next().getIdentifier());
                }
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        PlayQueueSequencer playQueueSequencer = PlayQueueSequencer.getInstance();
        if (playQueueSequencer == null) {
            return arrayList2;
        }
        Iterator<String> it2 = playQueueSequencer.getSfaTracksFromQueue().iterator();
        while (it2.hasNext()) {
            addTrack(arrayList2, it2.next());
        }
        return arrayList2;
    }

    private static void addTrack(List<Track> tracks, String asin) {
        Track track = new Track();
        track.setIdentifier(asin);
        track.setIdentifierType("ASIN");
        tracks.add(track);
    }

    public static boolean isCurrentlyPlaying(final ControlSource controlSource, final StationItem stationItem) {
        String string;
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(controlSource);
        if (stationItem.getKey() == null) {
            return false;
        }
        MediaCollectionId mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.STATION_KEY, stationItem.getKey());
        String nextToken = stationItem.getNextToken();
        if (nextToken == null) {
            return false;
        }
        try {
            string = new JSONObject(nextToken).getString("queueId");
        } catch (JSONException e) {
            Log.error(TAG, e.getMessage());
            string = "";
        }
        MediaCollectionId.Type type = MediaCollectionId.Type.STATION_INSTANCE_ID;
        MediaCollectionId mediaCollectionId2 = new MediaCollectionId(type, string);
        MediaCollectionInfo mediaCollectionInfo = controller.getMediaCollectionInfo();
        if (!string.isEmpty() && mediaCollectionInfo != null && mediaCollectionInfo.getId(type) != null && !mediaCollectionInfo.getId(type).equals(mediaCollectionId2)) {
            controller.stop(ChangeReason.NEW_SOURCE);
        }
        if (mediaCollectionInfo == null || mediaCollectionInfo.getId() == null || !mediaCollectionInfo.getId().equals(mediaCollectionId)) {
            return false;
        }
        return string.isEmpty() || (mediaCollectionInfo.getId(type) != null && mediaCollectionInfo.getId(type).equals(mediaCollectionId2));
    }

    public static StationSequencer createStationSequencer(final Context context, final ControlSource controlSource, final StationItem stationItem, final PlaybackPageType playbackPageType, @Nullable final MediaCollectionId additionalMediaCollectionId) {
        MediaCollectionType mediaCollectionType;
        Playback playback = Playback.getInstance();
        if (isCurrentlyPlaying(controlSource, stationItem)) {
            PlaybackProvider playbackProvider = playback.getPlaybackProvider();
            if (playbackProvider instanceof SequencerPlaybackProvider) {
                Sequencer sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer();
                if (sequencer instanceof StationSequencer) {
                    return (StationSequencer) sequencer;
                }
            }
        }
        boolean zIsExplicitLanguageFilterEnabled = AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
        Marketplace homeMarketPlace = AccountDetailUtil.get(context).getHomeMarketPlace();
        StationsFactory stationsFactory = StationsFactory.getInstance(context);
        new LikesServiceProxy(context);
        Uri contentUri = null;
        String targetId = CastingUtil.isCastingToAlexa() ? CastingSessionManager.getInstance().getCastingDevice().getTargetId() : null;
        com.amazon.music.station.Station stationCreate = com.amazon.music.station.Station.create(homeMarketPlace, stationItem, stationsFactory.getStationService(), zIsExplicitLanguageFilterEnabled, targetId);
        StationStorageUtil.insertStationWithDataChecks(context, stationItem);
        if (!TextUtils.isEmpty(stationItem.getIdentifierType())) {
            if (stationItem.getIdentifierType().equals("STATION_SEED_ID")) {
                contentUri = MediaProvider.Station.getContentUriWithSeeds(stationItem.getIdentifier());
                Log.info(TAG, "start Station From Anything from Seed ID: " + stationItem.getIdentifier());
            } else {
                Log.info(TAG, "start Station From Anything from: " + stationItem.getIdentifierType());
            }
            mediaCollectionType = SequencerHelper.getMediaCollectionType(new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME) ? SelectionSourceType.PRIME_STATION_SEED : SelectionSourceType.UNLIMITED_STATION_SEED);
        } else {
            contentUri = MediaProvider.Station.getContentUri(stationItem.getKey());
            mediaCollectionType = SequencerHelper.getMediaCollectionType(SelectionSourceHelper.getSelectionSourceType(contentUri));
        }
        Uri uri = contentUri;
        StationSequencer stationSequencer = new StationSequencer(stationItem, stationCreate, stationsFactory.getStationService(), playback.getPlaybackConfig().getMediaLinkProvider(), playback.getPlaybackConfig().getMediaItemImageUriProvider(), mediaCollectionType, playbackPageType, ControlSource.ALEXA.equals(controlSource));
        if (stationSequencer.getMediaCollectionInfo() != null) {
            if (!TextUtils.isEmpty(stationItem.getKey())) {
                stationSequencer.getMediaCollectionInfo().addId(new MediaCollectionId(MediaCollectionId.Type.STATION_KEY, stationItem.getKey()));
            }
            if (uri != null) {
                stationSequencer.getMediaCollectionInfo().addId(new MediaCollectionId(MediaCollectionId.Type.STATION_URI, uri.toString()));
            }
            if (additionalMediaCollectionId != null) {
                stationSequencer.getMediaCollectionInfo().addId(additionalMediaCollectionId);
            }
        }
        stationSequencer.setRemoteTargetId(targetId);
        return stationSequencer;
    }

    public static List<Track> convertAsinsToTracks(final List<String> asinList) {
        ArrayList arrayList = new ArrayList();
        for (String str : asinList) {
            Track track = new Track();
            track.setIdentifier(str);
            track.setIdentifierType("ASIN");
            arrayList.add(track);
        }
        return arrayList;
    }

    public static List<String> convertTracksToAsins(final List<Track> trackList) {
        ArrayList arrayList = new ArrayList();
        if (trackList != null) {
            Iterator<Track> it = trackList.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getIdentifier());
            }
        }
        return arrayList;
    }

    public static List<Track> convertPlayableEntityIdentifierToTracks(final List<PlayableEntityIdentifier> entityList) {
        ArrayList arrayList = new ArrayList();
        for (PlayableEntityIdentifier playableEntityIdentifier : entityList) {
            Track track = new Track();
            track.setIdentifier(playableEntityIdentifier.getIdentifier());
            track.setIdentifierType(playableEntityIdentifier.getPlayableEntityIdentifierType().toString());
            arrayList.add(track);
        }
        return arrayList;
    }

    public static List<Track> getTrackListFromSharedPlaylistWithLimitation(@NonNull final CatalogPlaylist catalogPlaylist) {
        ArrayList arrayList = new ArrayList();
        Iterator<CatalogPlaylistTrack> it = catalogPlaylist.getTracks().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getAsin());
        }
        return getTrackListFromSharedPlaylistWithLimitation(arrayList);
    }

    public static List<Track> getTrackListFromSharedPlaylistWithLimitation(@NonNull final List<String> asinList) {
        ArrayList arrayList = new ArrayList();
        for (String str : asinList) {
            Track track = new Track();
            track.setIdentifier(str);
            track.setIdentifierType("ASIN");
            arrayList.add(track);
            if (arrayList.size() == 100) {
                break;
            }
        }
        return arrayList;
    }

    private static Uri getStationUri(StationItem stationItem) {
        if (!TextUtils.isEmpty(stationItem.getIdentifierType()) && !"PREFER_KEY_OVER_NEXT_TOKEN".equals(stationItem.getIdentifierType())) {
            return MediaProvider.Station.getContentUriWithSeeds(stationItem.getIdentifier());
        }
        return MediaProvider.Station.getContentUri(stationItem.getKey());
    }

    private static StationItem buildDummySFAStationItem(@NonNull final List<Track> identifiers, @NonNull final String identifierType) {
        return new StationItem(new CatalogStatusTiers(true, true, false, false).toSet(), StationItem.buildExplicitMap(false), "", "", "", "", "", "", identifierType, identifiers);
    }

    private static StationItem buildDummySFAStationItem(@NonNull final String identifier, @NonNull final String identifierType) {
        return new StationItem(new CatalogStatusTiers(true, true, false, false).toSet(), StationItem.buildExplicitMap(false), "", "", "", "", "", "", identifierType, identifier);
    }

    public static void sendStartSFAUiClickMetrics(final String entityId, @NonNull final EntityIdType entityIdType) {
        sendStartSFAUiClickMetrics(entityId, entityIdType, null);
    }

    public static void sendStartSFAUiClickMetrics(final String entityId, @NonNull final EntityIdType entityIdType, final PageType pageType) {
        if (entityId == null) {
            Log.error(TAG, "Empty identifier passed to start SFA UI click metrics, ignoring");
            return;
        }
        UiClickEvent.Builder builderWithEntityIdType = UiClickEvent.builder(ActionType.START_STATION_FROM_ANYTHING).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withEntityId(entityId).withEntityIdType(entityIdType);
        if (pageType != null) {
            builderWithEntityIdType.withPageType(pageType);
        }
        MetricsHolder.getManager().handleEvent(builderWithEntityIdType.build());
    }

    public static void startStationFromAnythingForRecentlyPlayedItem(final Context context, final RecentlyPlayedItem recentlyPlayedItem, final ControlSource controlSource) {
        final String sFAIdentifierTypeFromRecentlyPlayedItem = getSFAIdentifierTypeFromRecentlyPlayedItem(recentlyPlayedItem);
        UpsellUtil.getUpsellSourceEntityFromRecentlyPlayedItem(recentlyPlayedItem);
        final EntityIdType sFAEntityIdTypeFromRecentlyPlayedItem = getSFAEntityIdTypeFromRecentlyPlayedItem(recentlyPlayedItem);
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ARTIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST || MediaProvider.PrimePlaylists.isPrimePlaylist(recentlyPlayedItem.getContentUri())) {
            startSFA(context, recentlyPlayedItem.getCollectionAsin(), sFAIdentifierTypeFromRecentlyPlayedItem, PlaybackPageType.RECENTLY_PLAYED, controlSource, false);
            sendStartSFAUiClickMetrics(recentlyPlayedItem.getCollectionAsin(), sFAEntityIdTypeFromRecentlyPlayedItem);
        } else if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.UdoPlaylists.isUdoPlaylist(recentlyPlayedItem.getContentUri())) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.card.prime.StationUtils$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    StationUtils.lambda$startStationFromAnythingForRecentlyPlayedItem$3(recentlyPlayedItem, context, sFAIdentifierTypeFromRecentlyPlayedItem, sFAEntityIdTypeFromRecentlyPlayedItem);
                }
            });
        } else {
            Log.error(TAG, "unexpected type of recentlyPlayedItem for starting StationFromAnything");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startStationFromAnythingForRecentlyPlayedItem$3(RecentlyPlayedItem recentlyPlayedItem, Context context, String str, EntityIdType entityIdType) {
        List<String> asinListSeedFromFollowedPlaylistId;
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(recentlyPlayedItem.getContentUri())) {
            asinListSeedFromFollowedPlaylistId = getAsinListSeedFromUserPlaylistId(context, recentlyPlayedItem.getCollectionAsin(), recentlyPlayedItem.getSource());
        } else {
            asinListSeedFromFollowedPlaylistId = getAsinListSeedFromFollowedPlaylistId(context, recentlyPlayedItem.getCollectionAsin());
        }
        startSFA(context, convertAsinsToTracks(asinListSeedFromFollowedPlaylistId), str, PlaybackPageType.RECENTLY_PLAYED);
        sendStartSFAUiClickMetrics(StringUtil.join(",", asinListSeedFromFollowedPlaylistId), entityIdType);
    }

    @WorkerThread
    private static List<String> getAsinListSeedFromUserPlaylistId(final Context context, final String userPlaylistId, final String source) {
        ArrayList arrayList = new ArrayList();
        if (userPlaylistId == null) {
            Log.error(TAG, "userPlaylistId is null when getting the seed for SFA");
            return arrayList;
        }
        try {
            Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"luid"}, "_id = " + userPlaylistId, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        List<String> asinListSeedFromUserPlaylistLuid = getAsinListSeedFromUserPlaylistLuid(context, cursorQuery.getString(cursorQuery.getColumnIndex("luid")), source);
                        cursorQuery.close();
                        return asinListSeedFromUserPlaylistLuid;
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception unused) {
            Log.error(TAG, "IO error happens when getting Asin List seed from User Playlist Id: " + userPlaylistId);
        }
        return arrayList;
    }

    private static EntityIdType getSFAEntityIdTypeFromRecentlyPlayedItem(final RecentlyPlayedItem recentlyPlayedItem) {
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM) {
            return EntityIdType.SFA_ALBUM_SEED;
        }
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ARTIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST) {
            return EntityIdType.SFA_ARTIST_SEED;
        }
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(recentlyPlayedItem.getContentUri())) {
            return EntityIdType.SFA_PLAYLIST_SEED;
        }
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.UdoPlaylists.isUdoPlaylist(recentlyPlayedItem.getContentUri())) {
            return EntityIdType.SFA_TRACK_LIST_SEED;
        }
        Log.error(TAG, "unexpected type of recentlyPlayedItem for starting SFA");
        return EntityIdType.SFA_TRACK_SEED;
    }

    private static String getSFAIdentifierTypeFromRecentlyPlayedItem(final RecentlyPlayedItem recentlyPlayedItem) {
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM) {
            return "ALBUM_SEED";
        }
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ARTIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST) {
            return "ARTIST_SEED";
        }
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(recentlyPlayedItem.getContentUri())) {
            return "PLAYLIST_SEED";
        }
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.UdoPlaylists.isUdoPlaylist(recentlyPlayedItem.getContentUri())) {
            return "TRACK_LIST_SEED";
        }
        Log.error(TAG, "unexpected type of recentlyPlayedItem for starting SFA");
        return "ASIN";
    }

    public static boolean isCustomerAllowedToPlaySFAStation() {
        return ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.sfa_station);
    }
}
