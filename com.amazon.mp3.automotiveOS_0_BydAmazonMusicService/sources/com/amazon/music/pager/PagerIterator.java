package com.amazon.music.pager;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public interface PagerIterator<T> {
    boolean hasNext();

    T next() throws ExecutionException, NoSuchElementException;
}
