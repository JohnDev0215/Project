package com.amazon.avod.content.urlvending;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: ContentUrlSelectorUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/avod/content/urlvending/ContentUrlSelectorUtil;", "", "Lcom/amazon/avod/content/urlvending/ContentUrl;", "contentUrl", "convertToHttpsContentUrl", "", ImagesContract.URL, "convertToHttpsUrl", "<init>", "()V", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public final class ContentUrlSelectorUtil {
    public static final ContentUrlSelectorUtil INSTANCE = new ContentUrlSelectorUtil();

    private ContentUrlSelectorUtil() {
    }

    public final ContentUrl convertToHttpsContentUrl(ContentUrl contentUrl) {
        Intrinsics.checkNotNullParameter(contentUrl, "contentUrl");
        String url = contentUrl.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "contentUrl.url");
        return new ContentUrl(convertToHttpsUrl(url), contentUrl.getSessionId(), contentUrl.getCdnName(), contentUrl.getOrigin());
    }

    public final String convertToHttpsUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Preconditions.checkArgument(url.length() > 7, "Url length should be at least 7 characters long -> http://[something]", new Object[0]);
        String strSubstring = url.substring(0, 4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        Preconditions.checkArgument(StringsKt__StringsJVMKt.equals(strSubstring, "http", true), "url should start with http", new Object[0]);
        if (url.charAt(4) == 's' || url.charAt(4) == 'S') {
            return url;
        }
        String strSubstring2 = url.substring(4);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
        StringBuilder sb = new StringBuilder(strSubstring2.length() + 5);
        sb.append("https");
        sb.append(strSubstring2);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "{\n            //url is h…    .toString()\n        }");
        return string;
    }
}
