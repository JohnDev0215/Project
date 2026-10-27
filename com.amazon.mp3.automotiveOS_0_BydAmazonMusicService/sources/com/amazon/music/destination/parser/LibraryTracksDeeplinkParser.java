package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.LibraryTracksDestination;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryTracksDeeplinkParser extends AbstractLibraryDeeplinkParser<LibraryTracksDestination> {
    private static final String TRACKS_SEGMENT = "tracks";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public LibraryTracksDestination parse(Uri uri) {
        return new LibraryTracksDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return AbstractLibraryDeeplinkParser.getLibraryRegexPrefix() + TRACKS_SEGMENT;
    }
}
