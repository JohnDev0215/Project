package com.spotify.playerlimited.player.models;

import com.spotify.playerlimited.player.moshi.MoshiRootName;
import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@MoshiRootName("progress")
@c71(generateAdapter = true)
public final class OfflineProgress {
    public Integer a;
    public Long b;
    public Integer c;
    public Long d;
    public Integer e;
    public Long f;
    public Boolean g;
    public Integer h;
    public Float i;
    public Long j;

    @z61(name = "download_speed")
    public static /* synthetic */ void getDownloadSpeed$annotations() {
    }

    @z61(name = "percent_complete")
    public static /* synthetic */ void getPercentComplete$annotations() {
    }

    @z61(name = "queued_bytes")
    public static /* synthetic */ void getQueuedBytes$annotations() {
    }

    @z61(name = "queued_tracks")
    public static /* synthetic */ void getQueuedTracks$annotations() {
    }

    @z61(name = "seconds_left")
    public static /* synthetic */ void getSecondsLeft$annotations() {
    }

    @z61(name = "synced_bytes")
    public static /* synthetic */ void getSyncedBytes$annotations() {
    }

    @z61(name = "synced_tracks")
    public static /* synthetic */ void getSyncedTracks$annotations() {
    }

    @z61(name = "syncing")
    public static /* synthetic */ void getSyncing$annotations() {
    }

    @z61(name = "total_bytes")
    public static /* synthetic */ void getTotalBytes$annotations() {
    }

    @z61(name = "total_tracks")
    public static /* synthetic */ void getTotalTracks$annotations() {
    }

    public final String toString() {
        return "{queuedTracks=" + this.a + ", queuedBytes=" + this.b + ", syncedTracks=" + this.c + ", syncedBytes=" + this.d + ", totalTracks=" + this.e + ", totalBytes=" + this.f + ", syncing=" + this.g + ", downloadSpeed=" + this.h + ", percentComplete=" + this.i + ", secondsLeft=" + this.j + '}';
    }
}
