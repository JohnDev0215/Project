package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.recentlyplayed.RecentItemsTransformationUtil;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.recentlyplayed.RecentsFactory;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.recents.RecentsController;
import com.amazon.music.recents.RecentsException;
import com.amazon.music.recents.model.ActivityType;
import com.amazon.music.recents.model.ContentQuality;
import com.amazon.music.recents.model.MusicItem;
import com.amazon.music.recents.model.RecentActivityRequest;
import com.amazon.music.recents.model.RecentTrackActivityRequest;
import com.amazon.music.recents.model.RecentlyPlayedAlbum;
import com.amazon.music.recents.model.RecentlyPlayedArtist;
import com.amazon.music.recents.model.RecentlyPlayedCatalogPlaylist;
import com.amazon.music.recents.model.RecentlyPlayedSharedUserPlaylist;
import com.amazon.music.recents.model.RecentlyPlayedStation;
import com.amazon.music.recents.model.RecentlyPlayedTrack;
import com.amazon.music.recents.model.RecentlyPlayedTracksResponse;
import com.amazon.music.recents.model.RecentlyPlayedUserPlaylist;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RecentlyPlayedSyncOperation extends SyncOperation {
    private static final String TAG = "RecentlyPlayedSyncOperation";

    public RecentlyPlayedSyncOperation(final Context context, final SQLiteDatabase db) {
        super(context, db);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() {
        String str = TAG;
        Log.debug(str, "Starting RecentlyPlayedSyncOperation");
        int iSyncRecentEntitiesFromService = syncRecentEntitiesFromService();
        if (AmazonApplication.getCapabilities().isRecentTracksSyncSupported() && iSyncRecentEntitiesFromService == 0) {
            iSyncRecentEntitiesFromService = syncRecentTracksFromService();
        }
        Log.debug(str, "finished RecentlyPlayedSyncOperation");
        return iSyncRecentEntitiesFromService;
    }

    private int syncRecentEntitiesFromService() {
        int i;
        Intent intent;
        try {
            try {
                List<MusicItem> listFetchRecentActivitiesFromService = fetchRecentActivitiesFromService();
                Log.debug(TAG, "Successfully fetched " + listFetchRecentActivitiesFromService.size() + " recentlyPlayedEntities from the service");
                Intent intent2 = new Intent();
                intent2.setAction("recently_played.db");
                getContext().sendBroadcast(intent2);
                persistInRepository(listFetchRecentActivitiesFromService);
                updateHasNoRecentsHistory(listFetchRecentActivitiesFromService.size() == 0);
                return 0;
            } catch (RecentsException e) {
                Log.error(TAG, "Failed to fetch recently played entities from service", e);
                int i2 = AnonymousClass1.$SwitchMap$com$amazon$music$recents$RecentsException$Reason[e.getReason().ordinal()];
                if (i2 == 1) {
                    Intent intent3 = new Intent();
                    intent3.setAction("recently_played.db");
                    getContext().sendBroadcast(intent3);
                    return 2;
                }
                if (i2 != 2) {
                    i = 5;
                    intent = new Intent();
                } else {
                    i = 3;
                    intent = new Intent();
                }
                intent.setAction("recently_played.db");
                getContext().sendBroadcast(intent);
                return i;
            }
        } catch (Throwable th) {
            Intent intent4 = new Intent();
            intent4.setAction("recently_played.db");
            getContext().sendBroadcast(intent4);
            throw th;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.service.sync.RecentlyPlayedSyncOperation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$recents$RecentsException$Reason;

        static {
            int[] iArr = new int[RecentsException.Reason.values().length];
            $SwitchMap$com$amazon$music$recents$RecentsException$Reason = iArr;
            try {
                iArr[RecentsException.Reason.NETWORK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$recents$RecentsException$Reason[RecentsException.Reason.PROCESSING_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void updateHasNoRecentsHistory(boolean hasNoRecentsHistory) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mContext).edit();
        editorEdit.putString("KEY_RECENTS_HISTORY_CHECKED_ON_VERSION", FTUSettingsUtil.getAppVersion(this.mContext));
        editorEdit.putBoolean("KEY_HAS_NO_RECENTS_HISTORY", hasNoRecentsHistory);
        editorEdit.apply();
    }

    private int syncRecentTracksFromService() {
        try {
            List<RecentlyPlayedTrack> listFetchRecentTracksFromService = fetchRecentTracksFromService();
            Log.debug(TAG, "Successfully fetched " + listFetchRecentTracksFromService.size() + " recentlyPlayedTracks from the service");
            persistTracksInRepository(listFetchRecentTracksFromService);
            return 0;
        } catch (RecentsException e) {
            Log.error(TAG, "Failed to fetch recently played tracks from service", e);
            int i = AnonymousClass1.$SwitchMap$com$amazon$music$recents$RecentsException$Reason[e.getReason().ordinal()];
            if (i != 1) {
                return i != 2 ? 5 : 3;
            }
            return 2;
        }
    }

    private List<MusicItem> fetchRecentActivitiesFromService() throws RecentsException {
        return RecentsFactory.createRecentsController(getContext()).getRecentActivity(createRecentActivityRequest());
    }

    private List<RecentlyPlayedTrack> fetchRecentTracksFromService() throws RecentsException {
        ArrayList arrayList = new ArrayList();
        RecentsController recentsControllerCreateRecentsController = RecentsFactory.createRecentsController(getContext());
        String str = "";
        for (int i = 0; i < 5; i++) {
            RecentlyPlayedTracksResponse recentTrackActivity = recentsControllerCreateRecentsController.getRecentTrackActivity(createRecentTrackActivityRequest(str));
            if (recentTrackActivity != null) {
                List<RecentlyPlayedTrack> recentTrackList = recentTrackActivity.getRecentTrackList();
                String pageToken = recentTrackActivity.getPageToken();
                if (recentTrackList != null && !recentTrackList.isEmpty()) {
                    arrayList.addAll(recentTrackList);
                }
                if (pageToken == null) {
                    break;
                }
                str = pageToken;
            }
        }
        return arrayList;
    }

    private RecentActivityRequest createRecentActivityRequest() {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(getContext());
        ArrayList arrayList = new ArrayList();
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            arrayList.add(ContentQuality.HIGHQUALITY);
        }
        return RecentActivityRequest.createBuilder(AccountDetailUtil.getMusicTerritoryOfResidence(), accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType()).withContentQualities(arrayList).build();
    }

    private RecentTrackActivityRequest createRecentTrackActivityRequest(final String pageToken) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(getContext());
        ArrayList arrayList = new ArrayList();
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            arrayList.add(ContentQuality.HIGHQUALITY);
        }
        return new RecentTrackActivityRequest.Builder(AccountDetailUtil.getMusicTerritoryOfResidence(), accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType(), Arrays.asList(ActivityType.PLAYED)).pageToken(pageToken).contentQualities(arrayList).build();
    }

    private void persistInRepository(final List<MusicItem> musicItems) {
        if (musicItems != null) {
            Iterator<MusicItem> it = musicItems.iterator();
            while (it.hasNext()) {
                persist(it.next());
            }
        }
    }

    private void persistTracksInRepository(@Nullable final List<RecentlyPlayedTrack> recentlyPlayedTracks) {
        if (recentlyPlayedTracks != null) {
            ArrayList arrayList = new ArrayList();
            for (RecentlyPlayedTrack recentlyPlayedTrack : recentlyPlayedTracks) {
                if (persist(recentlyPlayedTrack)) {
                    arrayList.add(RecentItemsTransformationUtil.toRecentTrack(recentlyPlayedTrack));
                }
            }
            RecentlyPlayedManager.getInstance(getContext()).bulkAdd(arrayList);
            if (AmazonApplication.getCapabilities().isBadgingInRecentsTrackSupported()) {
                RecentlyPlayedManager.getInstance(getContext()).bulkUpdateRecentTracksContentEncoding(arrayList);
            }
            RecentlyPlayedManager.getInstance(getContext()).pruneRecentTracks(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        }
    }

    private void persist(MusicItem musicItem) {
        RecentlyPlayedItem recentlyPlayedItemFrom;
        new PrimePlaylistDatabaseManager(getContext());
        if (musicItem instanceof RecentlyPlayedAlbum) {
            recentlyPlayedItemFrom = RecentItemsTransformationUtil.from((RecentlyPlayedAlbum) musicItem);
        } else if (musicItem instanceof RecentlyPlayedStation) {
            recentlyPlayedItemFrom = RecentItemsTransformationUtil.from((RecentlyPlayedStation) musicItem);
        } else if (musicItem instanceof RecentlyPlayedCatalogPlaylist) {
            recentlyPlayedItemFrom = RecentItemsTransformationUtil.from((RecentlyPlayedCatalogPlaylist) musicItem);
        } else if (musicItem instanceof RecentlyPlayedUserPlaylist) {
            recentlyPlayedItemFrom = RecentItemsTransformationUtil.from((RecentlyPlayedUserPlaylist) musicItem);
        } else if (musicItem instanceof RecentlyPlayedArtist) {
            recentlyPlayedItemFrom = RecentItemsTransformationUtil.from((RecentlyPlayedArtist) musicItem);
        } else {
            recentlyPlayedItemFrom = musicItem instanceof RecentlyPlayedSharedUserPlaylist ? RecentItemsTransformationUtil.from((RecentlyPlayedSharedUserPlaylist) musicItem) : null;
        }
        if (recentlyPlayedItemFrom != null) {
            recentlyPlayedItemFrom.setContentEncoding(musicItem.getContentEncodings());
            RecentlyPlayedManager.getInstance(getContext()).add(recentlyPlayedItemFrom);
        }
    }

    private boolean persist(final RecentlyPlayedTrack recentlyPlayedTrack) {
        Boolean inLibrary = recentlyPlayedTrack.getInLibrary();
        MusicTrack trackFromObjectId = (inLibrary == null || !inLibrary.booleanValue()) ? null : getTrackFromObjectId(recentlyPlayedTrack.getObjectId());
        if (trackFromObjectId == null) {
            trackFromObjectId = getTrackFromAsin(recentlyPlayedTrack.getAsin());
        }
        if (trackFromObjectId == null) {
            PrimeTrack primeTrack = RecentItemsTransformationUtil.toPrimeTrack(recentlyPlayedTrack);
            if (new CTAPrimeCache(getContext()).insertPrimeTracks(primeTrack.getAlbumAsin(), primeTrack.getAlbum(), primeTrack.getAlbumArtist(), Collections.singletonList(primeTrack)) == 0) {
                Log.error(TAG, "Failed to insert a track to a cache db: album asin: %s, album: %s, artist: %s", primeTrack.getAlbumAsin(), primeTrack.getAlbum(), primeTrack.getAlbumArtist());
                return false;
            }
        }
        return true;
    }

    @Nullable
    private MusicTrack getTrackFromObjectId(@Nullable final String objectId) {
        if (objectId == null) {
            return null;
        }
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(MediaProvider.Tracks.getContentUri("cirrus", objectId));
        if (track == null) {
            Log.info(TAG, "Got OBJECT_ID from Nimbly service but track has been deleted from library: " + objectId);
        }
        return track;
    }

    @Nullable
    private MusicTrack getTrackFromAsin(@Nullable final String trackAsin) {
        Uri uriFindCollectionUriByAsin;
        if (trackAsin == null || (uriFindCollectionUriByAsin = new QuerySongByAsin(getContext()).findCollectionUriByAsin(trackAsin)) == null) {
            return null;
        }
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(uriFindCollectionUriByAsin);
        if (track != null) {
            return track;
        }
        Log.error(TAG, "Found an ASIN in our database but couldn't create track: " + trackAsin);
        return null;
    }
}
