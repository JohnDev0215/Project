package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.AllArtistMerchDestination;
import com.amazon.music.destination.core.DeeplinkParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllArtistMerchDeeplinkParser.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/destination/parser/AllArtistMerchDeeplinkParser;", "Lcom/amazon/music/destination/core/DeeplinkParser;", "Lcom/amazon/music/destination/AllArtistMerchDestination;", "()V", "getUrlRegex", "", "parse", "uri", "Landroid/net/Uri;", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AllArtistMerchDeeplinkParser implements DeeplinkParser<AllArtistMerchDestination> {
    private static final String ARTISTS_SEGMENT = "artists";
    private static final String MERCH_SEGMENT = "merch";
    private static final String QUERY_PARAM_CATEGORY = "category";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public AllArtistMerchDestination parse(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new AllArtistMerchDestination(ParserUtil.findSegmentAfter(uri, MERCH_SEGMENT), uri.getQueryParameter(QUERY_PARAM_CATEGORY), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        String str = "/" + ARTISTS_SEGMENT + "/" + MERCH_SEGMENT + "/" + ParserUtil.ASIN_REGEX + ParserUtil.optional("/.*");
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder().apply(builderAction).toString()");
        return str;
    }
}
