package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MigrationStats {
    private long migrationTimeMs;
    private int totalTracksToMigrate;
    private int tracksMigrated;
    private final List<Uri> migratedTracksContentUris = new ArrayList();
    private final List<PreUnityTrack> tracksNotMigrated = new ArrayList();

    public boolean isAllTracksMigrated() {
        return this.tracksMigrated == this.totalTracksToMigrate;
    }

    public int getTotalTracksToMigrate() {
        return this.totalTracksToMigrate;
    }

    public int getTracksMigrated() {
        return this.tracksMigrated;
    }

    public List<Uri> getMigratedTracksContentUris() {
        return this.migratedTracksContentUris;
    }

    public void setTotalTracksToMigrate(final int totalTracksToMigrate) {
        this.totalTracksToMigrate = totalTracksToMigrate;
    }

    public void setMigrationTimeMs(final long migrationTimeMs) {
        this.migrationTimeMs = migrationTimeMs;
    }

    public void setTracksMigrated(final int tracksMigrated) {
        this.tracksMigrated = tracksMigrated;
    }

    public void incrementTracksMigrated() {
        this.tracksMigrated++;
    }

    public void addMigratedTracksContentUri(Uri trackContentUri) {
        this.migratedTracksContentUris.add(trackContentUri);
    }

    public void addNotMigratedTrack(PreUnityTrack preUnityTrack) {
        this.tracksNotMigrated.add(preUnityTrack);
    }

    public String toString() {
        return "MigrationStats{allTracksMigrated=" + isAllTracksMigrated() + ", totalTracksToMigrate=" + this.totalTracksToMigrate + ", tracksMigrated=" + this.tracksMigrated + ", migrationTimeMs=" + this.migrationTimeMs + '}';
    }
}
