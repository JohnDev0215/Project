package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumDeeplinkParser implements DeeplinkParser<AlbumDestination> {
    private static final String ALBUM_SEGMENT = "albums";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public AlbumDestination parse(Uri uri) {
        return new AlbumDestination(ParserUtil.findSegmentAfter(uri, ALBUM_SEGMENT), ParserUtil.getTrackAsin(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), ParserUtil.getViewTypeQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + ALBUM_SEGMENT + "/" + ParserUtil.ASIN_REGEX + ParserUtil.optional("/.*");
    }
}
