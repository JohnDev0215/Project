package com.ts.carplay.common.util;

import java.lang.Exception;

/* JADX INFO: loaded from: classes.dex */
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws Exception;
}
