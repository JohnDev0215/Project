package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ContactUsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class ContactUsDeeplinkParser implements DeeplinkParser<ContactUsDestination> {
    private static final String CONTACT_US_REGEX = "/contact-us(/.*)*";
    private static final String CONTACT_US_SEGMENT = "contact-us";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return CONTACT_US_REGEX;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ContactUsDestination parse(Uri uri) {
        String strFindSegmentAfter = ParserUtil.findSegmentAfter(uri, CONTACT_US_SEGMENT);
        ContactUsDestination.ContactUsTarget contactUsTarget = ContactUsDestination.ContactUsTarget.LANDING;
        if (strFindSegmentAfter == null) {
            return new ContactUsDestination(contactUsTarget);
        }
        ContactUsDestination.ContactUsTarget contactUsTarget2 = ContactUsDestination.ContactUsTarget.CHAT;
        if (strFindSegmentAfter.contains(contactUsTarget2.getValue())) {
            contactUsTarget = contactUsTarget2;
        }
        return new ContactUsDestination(contactUsTarget);
    }
}
