package com.amazon.mp3.detailpages.playlist;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistDetailDependencies.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailDependencies;", "", "isCatalog", "", "()Z", "pageSubType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "isOnDemandPlaylist", "(Ljava/lang/Boolean;)Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaylistDetailDependencies {
    boolean isCatalog();

    PageSubType pageSubType(Boolean isOnDemandPlaylist);

    PageType pageType();
}
