package com.amazon.music.platform.providers;

import com.amazon.music.platform.locator.ProvidersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: LoggingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getTag", "", "", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class LoggingProviderKt {
    public static final String getTag(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(obj.getClass()));
        return originalClassName == null ? "MusicPlatformCore" : originalClassName;
    }
}
