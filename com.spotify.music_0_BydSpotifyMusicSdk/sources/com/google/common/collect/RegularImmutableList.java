package com.google.common.collect;

import java.util.Objects;
import p.ci1;

/* JADX INFO: loaded from: classes.dex */
class RegularImmutableList<E> extends ImmutableList<E> {
    public static final ImmutableList m = new RegularImmutableList(0, new Object[0]);
    public final transient Object[] k;
    public final transient int l;

    public RegularImmutableList(int i, Object[] objArr) {
        this.k = objArr;
        this.l = i;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public final int b(Object[] objArr) {
        Object[] objArr2 = this.k;
        int i = this.l;
        System.arraycopy(objArr2, 0, objArr, 0, i);
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final Object[] c() {
        return this.k;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int d() {
        return this.l;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int e() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i) {
        ci1.k(i, this.l);
        Object obj = this.k[i];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean h() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.l;
    }
}
