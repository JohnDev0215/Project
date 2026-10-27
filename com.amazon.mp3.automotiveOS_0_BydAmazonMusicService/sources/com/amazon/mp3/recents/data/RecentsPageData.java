package com.amazon.mp3.recents.data;

import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsPageData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0002¢\u0006\u0004\b\u000e\u0010\u000fR(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0005\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/recents/data/RecentsPageData;", "", "", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "recentlyPlayed", "Ljava/util/List;", "getRecentlyPlayed", "()Ljava/util/List;", "setRecentlyPlayed", "(Ljava/util/List;)V", "Lcom/amazon/mp3/recents/data/RecentsPlaylistData;", "recentsPlaylistsData", "getRecentsPlaylistsData", "setRecentsPlaylistsData", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsPageData {
    private List<? extends RecentlyPlayedItem> recentlyPlayed;
    private List<RecentsPlaylistData> recentsPlaylistsData;

    public RecentsPageData(List<? extends RecentlyPlayedItem> recentlyPlayed, List<RecentsPlaylistData> recentsPlaylistsData) {
        Intrinsics.checkNotNullParameter(recentlyPlayed, "recentlyPlayed");
        Intrinsics.checkNotNullParameter(recentsPlaylistsData, "recentsPlaylistsData");
        this.recentlyPlayed = recentlyPlayed;
        this.recentsPlaylistsData = recentsPlaylistsData;
    }

    public final List<RecentlyPlayedItem> getRecentlyPlayed() {
        return this.recentlyPlayed;
    }

    public final List<RecentsPlaylistData> getRecentsPlaylistsData() {
        return this.recentsPlaylistsData;
    }
}
