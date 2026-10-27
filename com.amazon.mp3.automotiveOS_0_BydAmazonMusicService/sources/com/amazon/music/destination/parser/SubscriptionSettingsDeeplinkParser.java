package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.SubscriptionSettingsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class SubscriptionSettingsDeeplinkParser implements DeeplinkParser<SubscriptionSettingsDestination> {
    private static final String SETTINGS_SEGMENT = "settings";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/settings(/.*)?";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public SubscriptionSettingsDestination parse(Uri uri) {
        String strFindSegmentAfter = ParserUtil.findSegmentAfter(uri, SETTINGS_SEGMENT);
        SubscriptionSettingsDestination.SubscriptionSetting subscriptionSettingFromString = strFindSegmentAfter != null ? SubscriptionSettingsDestination.SubscriptionSetting.fromString(strFindSegmentAfter) : null;
        if (subscriptionSettingFromString == null) {
            subscriptionSettingFromString = SubscriptionSettingsDestination.SubscriptionSetting.NONE;
        }
        SubscriptionSettingsDestination.SubscriptionSetting subscriptionSetting = subscriptionSettingFromString;
        String userConfirmationParam = ParserUtil.getUserConfirmationParam(uri);
        if (userConfirmationParam == null) {
            userConfirmationParam = "true";
        }
        return new SubscriptionSettingsDestination(subscriptionSetting, ParserUtil.getAction(uri), ParserUtil.getQueryParameters(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), Boolean.parseBoolean(userConfirmationParam));
    }
}
