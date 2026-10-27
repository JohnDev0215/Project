package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.AlexaDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaDeeplinkParser implements DeeplinkParser<AlexaDestination> {
    private static final String ALEXA_SEGMENT = "/alexa/launch";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return ALEXA_SEGMENT;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public AlexaDestination parse(Uri uri) {
        return new AlexaDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
