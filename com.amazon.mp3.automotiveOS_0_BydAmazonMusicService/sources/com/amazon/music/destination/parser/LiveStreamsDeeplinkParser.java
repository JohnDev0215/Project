package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.LiveStreamsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class LiveStreamsDeeplinkParser implements DeeplinkParser<LiveStreamsDestination> {
    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/live/streams";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public LiveStreamsDestination parse(Uri uri) {
        return new LiveStreamsDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
