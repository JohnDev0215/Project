package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.LibraryAlbumsDestination;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryAlbumsDeeplinkParser extends AbstractLibraryDeeplinkParser<LibraryAlbumsDestination> {
    private final String ALBUMS_SEGMENT = "albums";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public LibraryAlbumsDestination parse(Uri uri) {
        return new LibraryAlbumsDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return AbstractLibraryDeeplinkParser.getLibraryRegexPrefix() + "albums";
    }
}
