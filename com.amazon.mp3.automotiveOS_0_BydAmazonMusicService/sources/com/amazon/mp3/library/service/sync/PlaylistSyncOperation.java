package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.library.util.EditPlaylistMetricsLogger;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.api.PlaylistChangeEntry;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import java.util.Collection;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistSyncOperation extends SyncOperation implements PlaylistSyncHandler {
    private static final String LOGTAG = "PlaylistSyncOperation";
    private int mEstimated;
    private boolean mForceUpdatePlaylists;
    private int mProcessed;
    private int mUpdated;

    public PlaylistSyncOperation(Context context, SQLiteDatabase db, final boolean forceUpdatePlaylists) {
        super(context, db);
        this.mEstimated = 0;
        this.mProcessed = 0;
        this.mUpdated = 0;
        this.mForceUpdatePlaylists = forceUpdatePlaylists;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    public int sync() throws SyncOperation.AccountStatusException {
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            return 0;
        }
        try {
            MC2PlaylistApi mC2PlaylistApi = new MC2PlaylistApi();
            Context context = getContext();
            boolean z = true;
            boolean z2 = CatalogStatusTiersOperation.shouldPerformFullSync(context) || shouldPerformFullSync(context);
            if (!this.mForceUpdatePlaylists && !z2) {
                z = false;
            }
            this.mForceUpdatePlaylists = z;
            mC2PlaylistApi.getChangedPlaylistsSinceCheckpoint(context, this, z, z2);
            PrimePlaylistUtil.deleteUnreferencedPrimePlaylistTracks(context);
            PlaylistSyncHelper.syncUnifiedPlaylistsToCloud(context);
            PlaylistSyncHelper.autodownloadUnfinishedUnifiedPlaylists(context);
            LikesServiceProxy.INSTANCE.initLikesCache(context);
            new EditPlaylistMetricsLogger(getContext()).setPlaylistEditedOffline(false);
            return 0;
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(LOGTAG, "Caught http client exception", e);
            return 2;
        } catch (Exception e2) {
            Log.error(LOGTAG, "Sync. Cannot execute Full Sync operation. Exception:", e2);
            return 3;
        }
    }

    @Override // com.amazon.mp3.library.service.sync.PlaylistSyncHandler
    public void onGetChangedPlaylistsSinceCheckpointPageResponseReceived(Collection<PlaylistChangeEntry> changedPlaylists, boolean isForceUpdate) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (PlaylistChangeEntry playlistChangeEntry : changedPlaylists) {
            String playlistId = playlistChangeEntry.getPlaylistId();
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playlist$api$PlaylistChangeEntry$PlaylistChangeType[playlistChangeEntry.getType().ordinal()];
            if (i == 1) {
                hashSet2.add(playlistId);
                Log.debug(LOGTAG, "Playlist to update: %s", playlistId);
            } else if (i == 2) {
                hashSet.add(playlistId);
                hashSet2.remove(playlistId);
                Log.debug(LOGTAG, "Playlist to remove: %s", playlistId);
            }
        }
        Context context = getContext();
        Log.warning(LOGTAG, "Playlists to update: %d  delete: %d", Integer.valueOf(hashSet2.size()), Integer.valueOf(hashSet.size()));
        if (hashSet2.size() > 0) {
            try {
                PlaylistSyncHelper.syncUpdatedPlaylist(context, hashSet2, this.mForceUpdatePlaylists);
                this.mUpdated += hashSet2.size();
            } catch (Exception e) {
                Log.error(LOGTAG, "Error calling syncUpdatedPlaylist()", e);
            }
        }
        if (hashSet.size() > 0) {
            PlaylistSyncHelper.syncDeletedPlaylists(context, hashSet);
        }
        hashSet2.addAll(hashSet);
        PlaylistSyncHelper.syncLocalPlaylists(context, hashSet2);
        if (isForceUpdate) {
            setShouldPerformSync(context, false);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.service.sync.PlaylistSyncOperation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playlist$api$PlaylistChangeEntry$PlaylistChangeType;

        static {
            int[] iArr = new int[PlaylistChangeEntry.PlaylistChangeType.values().length];
            $SwitchMap$com$amazon$mp3$playlist$api$PlaylistChangeEntry$PlaylistChangeType = iArr;
            try {
                iArr[PlaylistChangeEntry.PlaylistChangeType.UPDATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playlist$api$PlaylistChangeEntry$PlaylistChangeType[PlaylistChangeEntry.PlaylistChangeType.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getProgress() {
        int i = this.mEstimated;
        if (i == 0) {
            return 0;
        }
        return (this.mProcessed * 100) / i;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getUpdatedResourceCount() {
        return this.mUpdated;
    }

    public static boolean shouldPerformFullSync(Context context) {
        return SyncService.getPlaylistsFullSync(context) == "perform_sync";
    }

    public static void setShouldPerformSync(Context context, boolean performSync) {
        if (performSync) {
            SyncService.setPlaylistsFullSync(context, "perform_sync");
        } else {
            SyncService.setPlaylistsFullSync(context, "sync_not_needed");
        }
    }
}
