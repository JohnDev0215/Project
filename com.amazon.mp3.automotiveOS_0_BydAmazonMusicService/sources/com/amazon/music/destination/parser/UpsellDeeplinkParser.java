package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.UpsellDestination;
import com.amazon.music.destination.core.DeeplinkParser;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellDeeplinkParser implements DeeplinkParser<UpsellDestination> {
    public static final String UPSELL_SEGMENT_NAME = "upsell";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public UpsellDestination parse(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        return new UpsellDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), ParserUtil.getUpsellReason(pathSegments), ParserUtil.getUpsellSource(pathSegments), ParserUtil.getUpsellIsBlocking(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/upsell" + ParserUtil.optional("/.*");
    }
}
