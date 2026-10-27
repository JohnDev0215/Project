package com.amazon.music.find.model.search;

import com.amazon.music.find.converter.BrushV2Converter;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchPodcastShow.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/find/model/search/SearchPodcastShow;", "Lcom/amazon/music/find/model/search/SearchItem;", BrushV2Converter.KEY_IS_EXPLICIT, "", "()Z", "podcastShowId", "", "getPodcastShowId", "()Ljava/lang/String;", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchPodcastShow extends SearchItem {
    String getPodcastShowId();

    String getSubtitle();

    boolean isExplicit();
}
