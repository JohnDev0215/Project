package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.time.StopWatch;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistsMigrationStats {
    private static final String TAG = "PlaylistsMigrationStats";
    private boolean migrated;
    private String migrationFailedMessage;
    private int numberOfPlaylistsToMigrate = 0;
    private int numberOfFailedToMigratePlaylists = 0;
    private final Map<PreUnityPlaylist, PlaylistMigrationResult> playlistsMigrationStats = new HashMap();

    class PlaylistMigrationResult {
        private String errorMessage;
        private boolean migrated;
        private StopWatch playlistMigrationStopWatch;
        private MigrationStats tracksMigrationStats;

        PlaylistMigrationResult() {
        }

        public void migrationCompleted() {
            this.migrated = true;
        }

        public void migrationFailed(String reason) {
            this.migrated = false;
            this.errorMessage = reason;
        }

        public void setTracksMigrationStats(final MigrationStats tracksMigrationStats) {
            this.tracksMigrationStats = tracksMigrationStats;
        }

        public void startMigrationStopWatch() {
            StopWatch stopWatch = new StopWatch();
            this.playlistMigrationStopWatch = stopWatch;
            stopWatch.start();
        }

        void stopMigrationStopWatch() {
            StopWatch stopWatch = this.playlistMigrationStopWatch;
            if (stopWatch != null) {
                stopWatch.stop();
            }
        }

        public String toString() {
            if (this.errorMessage != null) {
                return "PlaylistMigrationResult{not migrated, errorMessage='" + this.errorMessage + "', elapsedTimeMs =" + this.playlistMigrationStopWatch.getTime() + ", tracksMigrationStats=" + this.tracksMigrationStats + '}';
            }
            return "PlaylistMigrationResult{ migrated = " + this.migrated + ", elapsedTimeMs =" + this.playlistMigrationStopWatch.getTime() + ", tracksMigrationStats=" + this.tracksMigrationStats + '}';
        }
    }

    public void migrationFailed(String errorMessage) {
        this.migrated = false;
        this.migrationFailedMessage = errorMessage;
    }

    public void playlistMigrationCompleted(final PreUnityPlaylist preUnityPlaylist) {
        getPlaylistMigrationResult(preUnityPlaylist).migrationCompleted();
    }

    public void playlistMigrationFailed(final PreUnityPlaylist preUnityPlaylist, final String reason) {
        getPlaylistMigrationResult(preUnityPlaylist).migrationFailed(reason);
        this.numberOfFailedToMigratePlaylists++;
    }

    public void addTracksMigrationStats(final PreUnityPlaylist preUnityPlaylist, final MigrationStats tracksMigrationStats) {
        getPlaylistMigrationResult(preUnityPlaylist).setTracksMigrationStats(tracksMigrationStats);
    }

    public void logStats(final String message) {
        if (this.migrationFailedMessage != null) {
            Log.error(TAG, message + ": " + this.migrationFailedMessage);
            return;
        }
        if (this.numberOfPlaylistsToMigrate == 0) {
            Log.verbose(TAG, message + ": No playlists to migrate.");
            return;
        }
        Log.verbose(TAG, message + ": Number of migrated playlists: " + (this.playlistsMigrationStats.size() - this.numberOfFailedToMigratePlaylists) + " out of " + this.numberOfPlaylistsToMigrate + ", failed to migrate: " + this.numberOfFailedToMigratePlaylists);
        for (PreUnityPlaylist preUnityPlaylist : this.playlistsMigrationStats.keySet()) {
            PlaylistMigrationResult playlistMigrationResult = getPlaylistMigrationResult(preUnityPlaylist);
            Log.verbose(TAG, message + ": Playlist: " + preUnityPlaylist + ", " + playlistMigrationResult);
        }
    }

    public void startMigrationStopWatch(final PreUnityPlaylist preUnityPlaylist) {
        getPlaylistMigrationResult(preUnityPlaylist).startMigrationStopWatch();
    }

    public void stopMigrationStopWatch(final PreUnityPlaylist preUnityPlaylist) {
        getPlaylistMigrationResult(preUnityPlaylist).stopMigrationStopWatch();
    }

    private PlaylistMigrationResult getPlaylistMigrationResult(final PreUnityPlaylist preUnityPlaylist) {
        if (this.playlistsMigrationStats.containsKey(preUnityPlaylist)) {
            return this.playlistsMigrationStats.get(preUnityPlaylist);
        }
        PlaylistMigrationResult playlistMigrationResult = new PlaylistMigrationResult();
        this.playlistsMigrationStats.put(preUnityPlaylist, playlistMigrationResult);
        return playlistMigrationResult;
    }

    public int getNumberOfPlaylistsToMigrate() {
        return this.numberOfPlaylistsToMigrate;
    }

    public int getNumberOfMigratedPlaylists() {
        return this.playlistsMigrationStats.size();
    }

    public void setNumberOfPlaylistsToMigrate(final int numberOfPlaylistsToMigrate) {
        this.numberOfPlaylistsToMigrate = numberOfPlaylistsToMigrate;
    }

    public int getNumberOfFailedToMigratePlaylists() {
        return this.numberOfFailedToMigratePlaylists;
    }

    public boolean isPlaylistsCollectionMigrated() {
        if (this.migrationFailedMessage != null) {
            return false;
        }
        if (this.numberOfPlaylistsToMigrate == 0) {
            return true;
        }
        return this.numberOfFailedToMigratePlaylists == 0 && this.playlistsMigrationStats.size() == this.numberOfPlaylistsToMigrate;
    }
}
