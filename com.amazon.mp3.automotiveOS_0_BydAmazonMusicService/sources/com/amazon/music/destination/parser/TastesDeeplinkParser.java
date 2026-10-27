package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.TastesDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class TastesDeeplinkParser implements DeeplinkParser<TastesDestination> {
    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/tastes";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public TastesDestination parse(Uri uri) {
        return new TastesDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
