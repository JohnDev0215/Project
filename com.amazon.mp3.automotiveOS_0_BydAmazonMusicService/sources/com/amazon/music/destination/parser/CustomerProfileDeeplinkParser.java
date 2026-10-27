package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.CustomerProfileDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class CustomerProfileDeeplinkParser implements DeeplinkParser<CustomerProfileDestination> {
    private static final String PROFILE_SEGMENT = "profiles";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public CustomerProfileDestination parse(Uri uri) {
        return new CustomerProfileDestination(ParserUtil.findSegmentAfter(uri, PROFILE_SEGMENT), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + PROFILE_SEGMENT + "/" + ParserUtil.CUSTOMER_PROFILE_REGEX + ParserUtil.optional("/.*");
    }
}
