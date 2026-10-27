package com.amazon.music.platform.locator;

import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: Providers.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"getOriginalClassName", "", "key", "", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ProvidersKt {
    public static final String getOriginalClassName(Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (key instanceof String) {
            return (String) key;
        }
        if (key instanceof Class) {
            String simpleName = ((Class) key).getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "key.simpleName");
            return simpleName;
        }
        if (!(key instanceof KClass)) {
            return null;
        }
        String simpleName2 = JvmClassMappingKt.getJavaClass((KClass) key).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName2, "key.java.simpleName");
        return simpleName2;
    }
}
