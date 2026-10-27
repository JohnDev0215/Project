package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.FamilyInviteDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class FamilyInviteDeeplinkParser implements DeeplinkParser<FamilyInviteDestination> {
    private static final String FAMILY_PATH_REGEX = "(/unlimited/family/invite)|(/family)(\\?.+)?";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return FAMILY_PATH_REGEX;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public FamilyInviteDestination parse(Uri uri) {
        return new FamilyInviteDestination(ParserUtil.getQueryParameters(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
