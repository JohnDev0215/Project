package com.ts.car.core;

import com.ts.car.core.provider.FoundationProviderFactory;
import com.ts.car.core.provider.IFoundationProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class FoundationProvider {
    public static <T> T create(Class<T> cls, Object... objArr) {
        IFoundationProvider foundationProvider;
        if (cls == null || (foundationProvider = FoundationProviderFactory.getFoundationProvider()) == null) {
            return null;
        }
        return (T) foundationProvider.create(cls, objArr);
    }

    public static <T> T[] creates(Class<T> cls, Object... objArr) {
        IFoundationProvider foundationProvider;
        if (cls == null || (foundationProvider = FoundationProviderFactory.getFoundationProvider()) == null) {
            return null;
        }
        return (T[]) foundationProvider.creates(cls, objArr);
    }
}
