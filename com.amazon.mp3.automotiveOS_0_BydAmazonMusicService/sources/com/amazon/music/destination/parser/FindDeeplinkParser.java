package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class FindDeeplinkParser implements DeeplinkParser<FindDestination> {
    public static final String SEARCH_SEGMENT_NAME = "search";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public FindDestination parse(Uri uri) {
        return new FindDestination(uri);
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return getFindUrlRegex();
    }

    public static String getFindUrlRegex() {
        return "/search" + ParserUtil.optional("/.*");
    }
}
