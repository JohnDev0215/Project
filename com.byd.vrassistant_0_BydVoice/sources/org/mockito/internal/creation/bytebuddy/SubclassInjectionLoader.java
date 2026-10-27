package org.mockito.internal.creation.bytebuddy;

import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

/* JADX INFO: loaded from: classes4.dex */
class SubclassInjectionLoader implements SubclassLoader {
    SubclassInjectionLoader() {
    }

    @Override // org.mockito.internal.creation.bytebuddy.SubclassLoader
    public ClassLoadingStrategy<ClassLoader> getStrategy(Class<?> cls) {
        return ClassLoadingStrategy.Default.INJECTION.with(cls.getProtectionDomain());
    }
}
