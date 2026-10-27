package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.LibraryPlaylistsDestination;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryPlaylistsDeeplinkParser extends AbstractLibraryDeeplinkParser<LibraryPlaylistsDestination> {
    private static final String PLAYLISTS_SEGMENT = "playlists";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public LibraryPlaylistsDestination parse(Uri uri) {
        return new LibraryPlaylistsDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return AbstractLibraryDeeplinkParser.getLibraryRegexPrefix() + PLAYLISTS_SEGMENT;
    }
}
