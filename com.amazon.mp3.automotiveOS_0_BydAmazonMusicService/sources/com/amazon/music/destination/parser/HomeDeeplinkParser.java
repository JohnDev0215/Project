package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.HomeDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class HomeDeeplinkParser implements DeeplinkParser<HomeDestination> {
    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "^(/|/home)?(\\?.+)?$";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public HomeDestination parse(Uri uri) {
        return new HomeDestination(ParserUtil.getTarget(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
