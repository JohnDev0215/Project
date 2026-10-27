package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.PlaylistsGenreDestination;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistsGenreDeeplinkParser extends GenreDeeplinkParser<PlaylistsGenreDestination> {
    @Override // com.amazon.music.destination.parser.GenreDeeplinkParser
    protected String getRoot() {
        return "playlists";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public PlaylistsGenreDestination parse(Uri uri) {
        return new PlaylistsGenreDestination(ParserUtil.getGenre(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
