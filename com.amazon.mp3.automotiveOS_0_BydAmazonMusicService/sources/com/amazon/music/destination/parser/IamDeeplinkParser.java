package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.IamDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class IamDeeplinkParser implements DeeplinkParser<IamDestination> {
    private static final String CAMPAIGNS_PATH_SEGMENT = "campaigns";
    private static final String CATEGORY_QUERY_SEGMENT = "category";
    private static final String IAM_PATH_SEGMENT = "iam";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public IamDestination parse(Uri uri) {
        return new IamDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), false, ParserUtil.findSegmentAfter(uri, CAMPAIGNS_PATH_SEGMENT), uri.getQueryParameter(CATEGORY_QUERY_SEGMENT));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/iam/campaigns" + ParserUtil.optional("/[-\\w]+(\\?.+)?");
    }
}
