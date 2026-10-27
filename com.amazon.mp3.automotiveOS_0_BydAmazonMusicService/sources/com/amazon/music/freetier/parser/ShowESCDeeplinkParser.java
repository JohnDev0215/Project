package com.amazon.music.freetier.parser;

import android.net.Uri;
import com.amazon.music.destination.core.DeeplinkParser;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.freetier.destination.ShowESCDestination;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShowESCDeeplinkParser.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/freetier/parser/ShowESCDeeplinkParser;", "Lcom/amazon/music/destination/core/DeeplinkParser;", "Lcom/amazon/music/freetier/destination/ShowESCDestination;", "()V", "getUrlRegex", "", "parse", "uri", "Landroid/net/Uri;", "DMMFreeTier_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShowESCDeeplinkParser implements DeeplinkParser<ShowESCDestination> {
    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/custom-mix";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ShowESCDestination parse(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new ShowESCDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
