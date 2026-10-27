package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.TrackDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class TrackDeeplinkParser implements DeeplinkParser<TrackDestination> {
    private static final String TRACK_SEGMENT = "tracks";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public TrackDestination parse(Uri uri) {
        return new TrackDestination(null, ParserUtil.findSegmentAfter(uri, TRACK_SEGMENT), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + TRACK_SEGMENT + "/" + ParserUtil.ASIN_REGEX + ParserUtil.optional("/.*");
    }
}
