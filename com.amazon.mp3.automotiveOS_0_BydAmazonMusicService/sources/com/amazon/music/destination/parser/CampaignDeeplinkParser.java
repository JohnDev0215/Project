package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.CampaignDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class CampaignDeeplinkParser implements DeeplinkParser<CampaignDestination> {
    private static final String CAMPAIGN = "campaigns";
    private static final String CAMPAIGN_PATH_REGEX = "/campaigns(/.*)?";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return CAMPAIGN_PATH_REGEX;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public CampaignDestination parse(Uri uri) {
        return new CampaignDestination(ParserUtil.getQueryParameters(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), ParserUtil.findSegmentsAfter(uri, CAMPAIGN));
    }
}
