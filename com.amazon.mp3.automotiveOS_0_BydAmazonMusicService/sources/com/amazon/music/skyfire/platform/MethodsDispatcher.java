package com.amazon.music.skyfire.platform;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MethodsDispatcher.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "T", "", "dispatchMethods", "", "ownerId", "", "methods", "", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface MethodsDispatcher<T> {
    void dispatchMethods(String ownerId, List<? extends T> methods);
}
