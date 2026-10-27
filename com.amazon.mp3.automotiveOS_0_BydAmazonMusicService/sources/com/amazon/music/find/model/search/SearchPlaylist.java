package com.amazon.music.find.model.search;

import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchPlaylist.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u001a\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\tR\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/find/model/search/SearchPlaylist;", "Lcom/amazon/music/find/model/search/SearchItem;", "authors", "", "", "getAuthors", "()Ljava/util/List;", MediaTrack.ROLE_DESCRIPTION, "getDescription", "()Ljava/lang/String;", "isSharedUserPlaylist", "", "()Z", "languageOfPerformances", "getLanguageOfPerformances", "numReviews", "", "getNumReviews", "()I", "ownership", "getOwnership", "playlistId", "getPlaylistId", "rating", "", "getRating", "()F", "trackCount", "getTrackCount", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchPlaylist extends SearchItem {
    List<String> getAuthors();

    String getDescription();

    List<String> getLanguageOfPerformances();

    int getNumReviews();

    boolean getOwnership();

    String getPlaylistId();

    float getRating();

    int getTrackCount();

    boolean isSharedUserPlaylist();
}
