package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ArtistDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class ArtistDeeplinkParser implements DeeplinkParser<ArtistDestination> {
    private static final String ARTISTS_SEGMENT = "artists";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ArtistDestination parse(Uri uri) {
        String strFindSegmentAfter = ParserUtil.findSegmentAfter(uri, ARTISTS_SEGMENT);
        return new ArtistDestination(ParserUtil.getTarget(uri), strFindSegmentAfter, ParserUtil.findSegmentAfter(uri, strFindSegmentAfter), null, ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), ParserUtil.getExperienceIdQueryParam(uri), ParserUtil.getStoryIdQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + ARTISTS_SEGMENT + "/" + ParserUtil.ASIN_REGEX + ParserUtil.optional("/.*");
    }
}
