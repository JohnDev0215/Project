package com.amazon.music.media.playback.player;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.ContentConfigData;
import com.amazon.video.sdk.player.playlist.Playlist;
import com.amazon.video.sdk.player.playlist.PlaylistFeature;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;

/* JADX INFO: compiled from: NoOpPlaylistFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPlaylistFeature;", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeature;", "Lcom/amazon/video/sdk/player/playlist/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/video/sdk/player/playlist/Playlist;", "getPlaylist", "()Lcom/amazon/video/sdk/player/playlist/Playlist;", "<init>", "()V", "NoOpPlaylist", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class NoOpPlaylistFeature implements PlaylistFeature {
    private final Playlist playlist = new NoOpPlaylist();

    /* JADX INFO: compiled from: NoOpPlaylistFeature.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPlaylistFeature$NoOpPlaylist;", "Lcom/amazon/video/sdk/player/playlist/Playlist;", "()V", ContextMappingConstants.ITEMS, "", "Lcom/amazon/video/sdk/player/ContentConfig;", "getItems", "()Ljava/util/List;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpPlaylist implements Playlist {
        private final List<ContentConfig> items = CollectionsKt__CollectionsJVMKt.listOf(new ContentConfigData("", "", 0L, null, null, null, false, false, 248, null));

        @Override // com.amazon.video.sdk.player.playlist.Playlist
        public List<ContentConfig> getItems() {
            return this.items;
        }
    }
}
