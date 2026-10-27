package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.RecommendedDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedDeeplinkParser implements DeeplinkParser<RecommendedDestination> {
    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/recommended";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public RecommendedDestination parse(Uri uri) {
        return new RecommendedDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
