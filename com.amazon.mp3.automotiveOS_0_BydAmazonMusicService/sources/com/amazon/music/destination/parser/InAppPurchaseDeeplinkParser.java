package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.InAppPurchaseDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class InAppPurchaseDeeplinkParser implements DeeplinkParser<InAppPurchaseDestination> {
    private static final String PlAYSTORE_SEGMENT = "playstore";
    private static final String SIGNUP_SEGMENT = "signup";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public InAppPurchaseDestination parse(Uri uri) {
        return new InAppPurchaseDestination(ParserUtil.findSegmentAfter(uri, PlAYSTORE_SEGMENT), ParserUtil.getScheduleIdQueryParam(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/signup/playstore/[A-Za-z0-9\\.]+" + ParserUtil.optional("/.*");
    }
}
