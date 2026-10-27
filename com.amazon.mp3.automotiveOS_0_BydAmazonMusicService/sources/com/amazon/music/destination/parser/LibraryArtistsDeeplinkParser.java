package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.LibraryArtistsDestination;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryArtistsDeeplinkParser extends AbstractLibraryDeeplinkParser<LibraryArtistsDestination> {
    private final String ARTISTS_SEGMENT = "artists";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public LibraryArtistsDestination parse(Uri uri) {
        return new LibraryArtistsDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return AbstractLibraryDeeplinkParser.getLibraryRegexPrefix() + "artists";
    }
}
