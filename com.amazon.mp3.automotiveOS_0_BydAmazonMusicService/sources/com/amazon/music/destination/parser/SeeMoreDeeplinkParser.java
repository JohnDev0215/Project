package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.SeeMoreDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class SeeMoreDeeplinkParser implements DeeplinkParser<SeeMoreDestination> {
    private static final String SEEMORE_SEGMENT = "seeMore";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return ".*/seeMore";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public SeeMoreDestination parse(Uri uri) {
        return new SeeMoreDestination(ParserUtil.getNextQueryParam(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
