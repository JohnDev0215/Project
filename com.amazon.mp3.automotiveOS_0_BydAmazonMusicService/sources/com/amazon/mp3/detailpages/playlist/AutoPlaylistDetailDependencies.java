package com.amazon.mp3.detailpages.playlist;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistDetailDependencies.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/AutoPlaylistDetailDependencies;", "Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailDependencies;", "()V", "isCatalog", "", "()Z", "pageSubType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "isOnDemandPlaylist", "(Ljava/lang/Boolean;)Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoPlaylistDetailDependencies implements PlaylistDetailDependencies {
    @Override // com.amazon.mp3.detailpages.playlist.PlaylistDetailDependencies
    public boolean isCatalog() {
        return false;
    }

    @Override // com.amazon.mp3.detailpages.playlist.PlaylistDetailDependencies
    public PageSubType pageSubType(Boolean isOnDemandPlaylist) {
        return null;
    }

    @Override // com.amazon.mp3.detailpages.playlist.PlaylistDetailDependencies
    public PageType pageType() {
        return PageType.DETAIL_AUTO_PLAYLIST;
    }
}
