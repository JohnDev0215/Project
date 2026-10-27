package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.DigitalSubscriptionsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class DigitalSubscriptionsDeeplinkParser implements DeeplinkParser<DigitalSubscriptionsDestination> {
    private static final String DIGITAL_SUBSCRIPTIONS = "digital-subscriptions";
    private static final String DIGITAL_SUBSCRIPTIONS_REGEX = "/digital-subscriptions(/.*)*";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return DIGITAL_SUBSCRIPTIONS_REGEX;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public DigitalSubscriptionsDestination parse(Uri uri) {
        return new DigitalSubscriptionsDestination(ParserUtil.findSegmentsAfter(uri, DIGITAL_SUBSCRIPTIONS), ParserUtil.getQueryParameters(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
