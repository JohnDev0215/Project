package com.ts.car.core.provider;

/* JADX INFO: loaded from: classes.dex */
public interface IFoundationProvider {
    <T> T create(Class<T> cls, Object... objArr);

    <T> T[] creates(Class<T> cls, Object... objArr);
}
