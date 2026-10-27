package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.DeeplinkTier;
import com.amazon.music.destination.SignupDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class SignupDeeplinkParser implements DeeplinkParser<SignupDestination> {
    private static final String SIGNUP_SEGMENT = "signup";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/signup/.+";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public SignupDestination parse(Uri uri) {
        SignupDestination signupDestination = new SignupDestination(DeeplinkTier.fromString(ParserUtil.findSegmentAfter(uri, SIGNUP_SEGMENT)), ParserUtil.getQueryParameters(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
        String userConfirmationParam = ParserUtil.getUserConfirmationParam(uri);
        if (userConfirmationParam != null) {
            signupDestination.setUserConfirmationRequired(Boolean.parseBoolean(userConfirmationParam));
        }
        return signupDestination;
    }
}
