package com.bugsnag.android;

import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: ThrowableExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"safeUnrollCauses", "", "", "bugsnag-android-core_release"}, k = 2, mv = {1, 4, 2})
@JvmName(name = "ThrowableUtils")
public final class ThrowableUtils {
    public static final List<Throwable> safeUnrollCauses(Throwable th) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (th != null && linkedHashSet.add(th)) {
            th = th.getCause();
        }
        return CollectionsKt___CollectionsKt.toList(linkedHashSet);
    }
}
