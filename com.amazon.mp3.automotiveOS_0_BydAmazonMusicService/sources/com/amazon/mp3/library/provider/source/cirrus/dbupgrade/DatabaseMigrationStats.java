package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import androidx.annotation.NonNull;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DatabaseMigrationStats {
    private static final String TAG = "DatabaseMigrationStats";
    private PlaylistsMigrationStats deviceOnlyPlaylistsStats;
    private PlaylistsMigrationStats primePlaylistsStats;
    private PlaylistsMigrationStats sharedPlaylistsStats;
    private MigrationStats tracksMigrationStats;
    private PlaylistsMigrationStats udoPlaylistsStats;

    public boolean isEverythingMigrated() {
        return this.tracksMigrationStats.isAllTracksMigrated() && this.udoPlaylistsStats.isPlaylistsCollectionMigrated() && this.primePlaylistsStats.isPlaylistsCollectionMigrated() && this.sharedPlaylistsStats.isPlaylistsCollectionMigrated() && this.deviceOnlyPlaylistsStats.isPlaylistsCollectionMigrated();
    }

    @NonNull
    public String getWhatIsNotMigratedMsg() {
        String str = !this.tracksMigrationStats.isAllTracksMigrated() ? "tracks, " : "";
        if (!this.udoPlaylistsStats.isPlaylistsCollectionMigrated()) {
            str = str + "UDO playlists, ";
        }
        if (!this.primePlaylistsStats.isPlaylistsCollectionMigrated()) {
            str = str + "Prime playlists, ";
        }
        if (!this.sharedPlaylistsStats.isPlaylistsCollectionMigrated()) {
            str = str + "Shared playlists, ";
        }
        if (this.deviceOnlyPlaylistsStats.isPlaylistsCollectionMigrated()) {
            return str;
        }
        return str + "Device Only playlists ";
    }

    public void setTracksMigrationStats(final MigrationStats tracksMigrationStats) {
        this.tracksMigrationStats = tracksMigrationStats;
    }

    public void setUdoPlaylistsStats(final PlaylistsMigrationStats udoPlaylistsStats) {
        this.udoPlaylistsStats = udoPlaylistsStats;
    }

    public void setPrimePlaylistsStats(final PlaylistsMigrationStats primePlaylistsStats) {
        this.primePlaylistsStats = primePlaylistsStats;
    }

    public void setSharedPlaylistsStats(final PlaylistsMigrationStats sharedPlaylistsStats) {
        this.sharedPlaylistsStats = sharedPlaylistsStats;
    }

    public void setDeviceOnlyPlaylistsStats(final PlaylistsMigrationStats deviceOnlyPlaylistsStats) {
        this.deviceOnlyPlaylistsStats = deviceOnlyPlaylistsStats;
    }

    public void logStats() {
        Log.verbose(TAG, "tracksMigrated: " + this.tracksMigrationStats);
        this.udoPlaylistsStats.logStats("UDO Playlists");
        this.primePlaylistsStats.logStats("Prime Playlists");
        this.sharedPlaylistsStats.logStats("Shared Playlists");
        this.deviceOnlyPlaylistsStats.logStats("Device Only Playlists");
    }

    private List<PlaylistsMigrationStats> getAllPlaylistsMigrationStats() {
        ArrayList arrayList = new ArrayList();
        PlaylistsMigrationStats playlistsMigrationStats = this.udoPlaylistsStats;
        if (playlistsMigrationStats != null) {
            arrayList.add(playlistsMigrationStats);
        }
        PlaylistsMigrationStats playlistsMigrationStats2 = this.primePlaylistsStats;
        if (playlistsMigrationStats2 != null) {
            arrayList.add(playlistsMigrationStats2);
        }
        PlaylistsMigrationStats playlistsMigrationStats3 = this.sharedPlaylistsStats;
        if (playlistsMigrationStats3 != null) {
            arrayList.add(playlistsMigrationStats3);
        }
        PlaylistsMigrationStats playlistsMigrationStats4 = this.deviceOnlyPlaylistsStats;
        if (playlistsMigrationStats4 != null) {
            arrayList.add(playlistsMigrationStats4);
        }
        return arrayList;
    }

    public long getTotalNumberOfTracksToMigrate() {
        MigrationStats migrationStats = this.tracksMigrationStats;
        if (migrationStats != null) {
            return 0 + ((long) migrationStats.getTotalTracksToMigrate());
        }
        return 0L;
    }

    public long getTotalNumberOfTracksMigrated() {
        MigrationStats migrationStats = this.tracksMigrationStats;
        if (migrationStats != null) {
            return 0 + ((long) migrationStats.getTracksMigrated());
        }
        return 0L;
    }

    public int getTotalNumberOfPlaylistsToMigrate() {
        Iterator<PlaylistsMigrationStats> it = getAllPlaylistsMigrationStats().iterator();
        int numberOfPlaylistsToMigrate = 0;
        while (it.hasNext()) {
            numberOfPlaylistsToMigrate += it.next().getNumberOfPlaylistsToMigrate();
        }
        return numberOfPlaylistsToMigrate;
    }

    public int getTotalNumberOfPlaylistsMigrated() {
        Iterator<PlaylistsMigrationStats> it = getAllPlaylistsMigrationStats().iterator();
        int numberOfMigratedPlaylists = 0;
        while (it.hasNext()) {
            numberOfMigratedPlaylists += it.next().getNumberOfMigratedPlaylists();
        }
        return numberOfMigratedPlaylists;
    }
}
