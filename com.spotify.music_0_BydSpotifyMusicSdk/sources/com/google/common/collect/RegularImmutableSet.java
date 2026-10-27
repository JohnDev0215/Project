package com.google.common.collect;

import p.he1;
import p.oq2;

/* JADX INFO: loaded from: classes.dex */
final class RegularImmutableSet<E> extends ImmutableSet<E> {
    public static final Object[] q = null;
    public static final RegularImmutableSet r;
    public final transient Object[] l;
    public final transient int m;
    public final transient Object[] n;
    public final transient int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f37p;

    static {
        Object[] objArr = new Object[0];
        r = new RegularImmutableSet(0, 0, 0, objArr, objArr);
    }

    public RegularImmutableSet(int i, int i2, int i3, Object[] objArr, Object[] objArr2) {
        this.l = objArr;
        this.m = i;
        this.n = objArr2;
        this.o = i2;
        this.f37p = i3;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int b(Object[] objArr) {
        Object[] objArr2 = this.l;
        int i = this.f37p;
        System.arraycopy(objArr2, 0, objArr, 0, i);
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final Object[] c() {
        return this.l;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.n;
            if (objArr.length != 0) {
                int iP = he1.P(obj.hashCode());
                while (true) {
                    int i = iP & this.o;
                    Object obj2 = objArr[i];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    iP = i + 1;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int d() {
        return this.f37p;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int e() {
        return 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean h() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.m;
    }

    @Override // com.google.common.collect.ImmutableCollection
    /* JADX INFO: renamed from: i */
    public final oq2 iterator() {
        return a().listIterator(0);
    }

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList p() {
        return ImmutableList.m(this.f37p, this.l);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f37p;
    }
}
