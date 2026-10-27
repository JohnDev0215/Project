package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.MerchShopDestination;
import com.amazon.music.destination.core.DeeplinkParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MerchShopDeeplinkParser.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/destination/parser/MerchShopDeeplinkParser;", "Lcom/amazon/music/destination/core/DeeplinkParser;", "Lcom/amazon/music/destination/MerchShopDestination;", "()V", "getUrlRegex", "", "parse", "uri", "Landroid/net/Uri;", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MerchShopDeeplinkParser implements DeeplinkParser<MerchShopDestination> {
    private static final String MERCH_SEGMENT = "merch";
    private static final String QUERY_PARAM_CATEGORY = "category";
    private static final String QUERY_PARAM_NAME = "name";
    private static final String QUERY_PARAM_SEQUENCE = "sequence";
    private static final String SHOP_SEGMENT = "shop";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public MerchShopDestination parse(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new MerchShopDestination(uri.getQueryParameter(QUERY_PARAM_CATEGORY), uri.getQueryParameter(QUERY_PARAM_SEQUENCE), uri.getQueryParameter("name"), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        String str = "/" + MERCH_SEGMENT + "/" + SHOP_SEGMENT + ParserUtil.optional("/.*");
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder().apply(builderAction).toString()");
        return str;
    }
}
