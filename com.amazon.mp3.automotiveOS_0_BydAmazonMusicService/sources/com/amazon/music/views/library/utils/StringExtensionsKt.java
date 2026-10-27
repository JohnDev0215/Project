package com.amazon.music.views.library.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: StringExtensions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0001\u001a\u00020\u0000*\u00020\u0000¨\u0006\u0002"}, d2 = {"", "removeExplicitTitle", "DMMViewLibrary_release"}, k = 2, mv = {1, 6, 0})
public final class StringExtensionsKt {
    public static final String removeExplicitTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt__StringsKt.trim((CharSequence) StringsKt__StringsJVMKt.replace$default(str, "[Explicit]", "", false, 4, (Object) null)).toString();
    }
}
