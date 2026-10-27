package com.amazon.music.media.auto.tab.search;

import com.amazon.music.media.auto.R$string;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextSearchConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/media/auto/tab/search/SearchContentTypes;", "", "stringResource", "", "(Ljava/lang/String;II)V", "getStringResource", "()I", "SONGS", "ALBUMS", "ARTISTS", "PLAYLISTS", "STATIONS", "TOP_HITS", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum SearchContentTypes {
    SONGS(R$string.dmusic_media_browser_service_library_songs),
    ALBUMS(R$string.dmusic_media_browser_service_library_albums),
    ARTISTS(R$string.dmusic_media_browser_service_library_artists),
    PLAYLISTS(R$string.dmusic_media_browser_service_library_playlists),
    STATIONS(R$string.dmusic_media_browser_service_search_stations),
    TOP_HITS(R$string.dmusic_media_browser_service_search_top_hits);

    private final int stringResource;

    SearchContentTypes(int i) {
        this.stringResource = i;
    }

    public final int getStringResource() {
        return this.stringResource;
    }
}
