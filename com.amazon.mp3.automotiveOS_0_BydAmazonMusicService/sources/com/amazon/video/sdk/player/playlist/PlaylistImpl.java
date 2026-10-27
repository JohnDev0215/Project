package com.amazon.video.sdk.player.playlist;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.video.sdk.player.ContentConfig;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Playlist.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistImpl;", "Lcom/amazon/video/sdk/player/playlist/Playlist;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "Lcom/amazon/video/sdk/player/ContentConfig;", "playlistItems", "Ljava/util/List;", "getItems", "()Ljava/util/List;", ContextMappingConstants.ITEMS, "<init>", "(Ljava/util/List;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PlaylistImpl implements Playlist {
    private final List<ContentConfig> playlistItems;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlaylistImpl) && Intrinsics.areEqual(this.playlistItems, ((PlaylistImpl) other).playlistItems);
    }

    public int hashCode() {
        return this.playlistItems.hashCode();
    }

    public String toString() {
        return "PlaylistImpl(playlistItems=" + this.playlistItems + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlaylistImpl(List<? extends ContentConfig> playlistItems) {
        Intrinsics.checkNotNullParameter(playlistItems, "playlistItems");
        this.playlistItems = playlistItems;
    }

    @Override // com.amazon.video.sdk.player.playlist.Playlist
    public List<ContentConfig> getItems() {
        ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) this.playlistItems);
        Intrinsics.checkNotNullExpressionValue(immutableListCopyOf, "copyOf(playlistItems)");
        return immutableListCopyOf;
    }
}
