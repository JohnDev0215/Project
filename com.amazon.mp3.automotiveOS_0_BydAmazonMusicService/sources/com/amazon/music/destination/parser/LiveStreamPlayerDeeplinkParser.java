package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.LiveStreamPlayerDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class LiveStreamPlayerDeeplinkParser implements DeeplinkParser<LiveStreamPlayerDestination> {
    private static final String LIVE_SEGMENT = "live";
    private static final String STREAMS_SEGMENT = "streams";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public LiveStreamPlayerDestination parse(Uri uri) {
        return new LiveStreamPlayerDestination(ParserUtil.findSegmentAfter(uri, STREAMS_SEGMENT), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + LIVE_SEGMENT + "/" + STREAMS_SEGMENT + "/[A-Za-z0-9_-]+" + ParserUtil.optional("/.*");
    }
}
