package com.amazon.mp3.amplifyqueue.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CustomAmplifyApi.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"removeTwoOrMoreWhitespaces", "", "amplifyqueue_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class CustomAmplifyApiKt {
    public static final String removeTwoOrMoreWhitespaces(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new Regex("\\s{2,}").replace(StringsKt__StringsKt.trim((CharSequence) str).toString(), " ");
    }
}
