package com.amazon.music.file;

import java.lang.Exception;

/* JADX INFO: loaded from: classes4.dex */
public interface FileItemTransformer<T, X extends Exception> {
    String transform(T t) throws Exception;
}
