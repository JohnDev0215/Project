package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: PlaylistRepositoryEvents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"libraryPlaylistUpdateEventTypes", "", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "getLibraryPlaylistUpdateEventTypes", "()Ljava/util/List;", "playlistDetailPageUpdateEventTypes", "getPlaylistDetailPageUpdateEventTypes", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRepositoryEventsKt {
    private static final List<RepositoryEventType> libraryPlaylistUpdateEventTypes;
    private static final List<RepositoryEventType> playlistDetailPageUpdateEventTypes;

    public static final List<RepositoryEventType> getLibraryPlaylistUpdateEventTypes() {
        return libraryPlaylistUpdateEventTypes;
    }

    static {
        PlaylistUpdateEventType playlistUpdateEventType = PlaylistUpdateEventType.PRIVACY_STATUS_CHANGED;
        libraryPlaylistUpdateEventTypes = CollectionsKt__CollectionsKt.listOf((Object[]) new RepositoryEventType[]{PlaylistUpdateEventType.FOLLOWED, PlaylistUpdateEventType.UNFOLLOWED, playlistUpdateEventType, RecentActivityUpdateEventType.PRIME_PLAYLIST, RecentActivityUpdateEventType.UDO_PLAYLIST});
        playlistDetailPageUpdateEventTypes = CollectionsKt__CollectionsJVMKt.listOf(playlistUpdateEventType);
    }

    public static final List<RepositoryEventType> getPlaylistDetailPageUpdateEventTypes() {
        return playlistDetailPageUpdateEventTypes;
    }
}
