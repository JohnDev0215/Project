package com.google.common.collect;

import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: loaded from: classes2.dex */
@DoNotMock("Use Interners.new*Interner")
public interface Interner<E> {
    E intern(E e);
}
