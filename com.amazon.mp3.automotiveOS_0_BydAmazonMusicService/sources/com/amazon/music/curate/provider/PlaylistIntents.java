package com.amazon.music.curate.provider;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistIntents.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/curate/provider/PlaylistIntents;", "", "()V", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistIntents {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ACTION_REFRESH_PLAYLIST = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".REFRESH_PLAYLIST");
    private static final String ACTION_REMOVE_TRACK_FROM_PLAYLIST = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".REMOVE_TRACK_FROM_PLAYLIST");
    private static final String EXTRA_PLAYLIST_ID = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".PLAYLIST_ID");
    private static final String EXTRA_TRACK_ID = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".TRACK_ID");
    private static final String EXTRA_INDEX = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".INDEX");
    private static final String ACTION_REMOVE_CATALOG_PLAYLIST = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".REMOVE_CATALOG_PLAYLIST");
    private static final String ACTION_UPDATE_CATALOG_PLAYLIST = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".UPDATE_CATALOG_PLAYLIST");
    private static final String ACTION_REQUEST_LIBRARY_SYNC = Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".REQUEST_LIBRARY_SYNC");

    @JvmStatic
    public static final String getActionSyncPlaylist() {
        return INSTANCE.getActionSyncPlaylist();
    }

    /* JADX INFO: compiled from: PlaylistIntents.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J\b\u0010\u0019\u001a\u00020\u0004H\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/curate/provider/PlaylistIntents$Companion;", "", "()V", "ACTION_REFRESH_PLAYLIST", "", "getACTION_REFRESH_PLAYLIST", "()Ljava/lang/String;", "ACTION_REMOVE_CATALOG_PLAYLIST", "getACTION_REMOVE_CATALOG_PLAYLIST", "ACTION_REMOVE_TRACK_FROM_PLAYLIST", "getACTION_REMOVE_TRACK_FROM_PLAYLIST", "ACTION_REQUEST_LIBRARY_SYNC", "getACTION_REQUEST_LIBRARY_SYNC", "ACTION_UPDATE_CATALOG_PLAYLIST", "getACTION_UPDATE_CATALOG_PLAYLIST", "EXTRA_INDEX", "getEXTRA_INDEX", "EXTRA_PLAYLIST_ID", "getEXTRA_PLAYLIST_ID", "EXTRA_TRACK_ID", "getEXTRA_TRACK_ID", "createRefreshPlaylistIntent", "Landroid/content/Intent;", "playlistId", "createSyncPlaylistIntent", "getActionSyncPlaylist", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final String getActionSyncPlaylist() {
            return Intrinsics.stringPlus(PlaylistIntents.class.getName(), ".SYNC_PLAYLIST");
        }

        public final String getACTION_REFRESH_PLAYLIST() {
            return PlaylistIntents.ACTION_REFRESH_PLAYLIST;
        }

        public final String getACTION_REMOVE_TRACK_FROM_PLAYLIST() {
            return PlaylistIntents.ACTION_REMOVE_TRACK_FROM_PLAYLIST;
        }

        public final String getEXTRA_PLAYLIST_ID() {
            return PlaylistIntents.EXTRA_PLAYLIST_ID;
        }

        public final String getEXTRA_TRACK_ID() {
            return PlaylistIntents.EXTRA_TRACK_ID;
        }

        public final String getEXTRA_INDEX() {
            return PlaylistIntents.EXTRA_INDEX;
        }

        public final String getACTION_REMOVE_CATALOG_PLAYLIST() {
            return PlaylistIntents.ACTION_REMOVE_CATALOG_PLAYLIST;
        }

        public final String getACTION_UPDATE_CATALOG_PLAYLIST() {
            return PlaylistIntents.ACTION_UPDATE_CATALOG_PLAYLIST;
        }

        public final String getACTION_REQUEST_LIBRARY_SYNC() {
            return PlaylistIntents.ACTION_REQUEST_LIBRARY_SYNC;
        }

        public final Intent createRefreshPlaylistIntent(String playlistId) {
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            Intent intent = new Intent(getACTION_REFRESH_PLAYLIST());
            intent.putExtra(getEXTRA_PLAYLIST_ID(), playlistId);
            return intent;
        }

        public final Intent createSyncPlaylistIntent(String playlistId) {
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            Intent intent = new Intent(getActionSyncPlaylist());
            intent.putExtra(getEXTRA_PLAYLIST_ID(), playlistId);
            return intent;
        }
    }
}
