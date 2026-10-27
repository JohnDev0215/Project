package org.mockito.internal.creation.bytebuddy;

import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

/* JADX INFO: loaded from: classes4.dex */
public interface SubclassLoader {
    ClassLoadingStrategy<ClassLoader> getStrategy(Class<?> cls);
}
