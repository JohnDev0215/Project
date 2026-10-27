package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.GenresDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class GenresDeeplinkParser implements DeeplinkParser<GenresDestination> {
    private static final String GENRES_ID_REGEX = "[-A-Za-z0-9]+";
    private static final String GENRES_SEGMENT = "genres";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public GenresDestination parse(Uri uri) {
        return new GenresDestination(ParserUtil.getTarget(uri), ParserUtil.findSegmentAfter(uri, GENRES_SEGMENT), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + GENRES_SEGMENT + "/" + GENRES_ID_REGEX + ParserUtil.optional("/.*");
    }
}
