package com.apollographql.apollo3.api.http.internal;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: UrlEncode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0005H\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"RESERVED_CHARS", "", "getRESERVED_CHARS$annotations", "()V", "percentEncode", "", "urlEncode", "spaceToPlus", "", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UrlEncodeKt {
    private static final String RESERVED_CHARS = "!#$&'\"()*+,/:;=?@[]{}";

    public static /* synthetic */ String urlEncode$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return urlEncode(str, z);
    }

    public static final String urlEncode(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            i++;
            if (StringsKt__StringsKt.contains$default((CharSequence) RESERVED_CHARS, cCharAt, false, 2, (Object) null)) {
                sb.append(percentEncode(cCharAt));
            } else if (z && cCharAt == ' ') {
                sb.append('+');
            } else {
                sb.append(cCharAt);
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private static final String percentEncode(char c) {
        String string = Integer.toString(c, CharsKt__CharJVMKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
        String upperCase = Intrinsics.stringPlus("%", string).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase;
    }
}
