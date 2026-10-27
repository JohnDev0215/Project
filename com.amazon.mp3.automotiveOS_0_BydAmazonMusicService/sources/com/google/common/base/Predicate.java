package com.google.common.base;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface Predicate<T> {
    boolean apply(T t);

    boolean equals(@CheckForNull Object obj);
}
