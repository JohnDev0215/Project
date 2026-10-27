package com.amazon.music.find.utils;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: StringUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/find/utils/StringUtils;", "", "()V", "normalize", "", "target", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StringUtils {
    public static final StringUtils INSTANCE = new StringUtils();

    private StringUtils() {
    }

    public final String normalize(String target) {
        Intrinsics.checkNotNullParameter(target, "target");
        String strNormalizeSpace = org.apache.commons.lang3.StringUtils.normalizeSpace(StringsKt__StringsKt.trim((CharSequence) target).toString());
        Intrinsics.checkNotNullExpressionValue(strNormalizeSpace, "normalizeSpace(\n\t\t\t\ttarget.trim()\n\t\t\t)");
        String lowerCase = strNormalizeSpace.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String strStripAccents = org.apache.commons.lang3.StringUtils.stripAccents(lowerCase);
        Intrinsics.checkNotNullExpressionValue(strStripAccents, "stripAccents(\n\t\t\tStringU…im()\n\t\t\t).lowercase()\n\t\t)");
        return StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(strStripAccents, "’", "'", false, 4, (Object) null), "‘", "'", false, 4, (Object) null);
    }
}
