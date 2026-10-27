package com.google.common.collect;

import p.i61;
import p.oq2;

/* JADX INFO: loaded from: classes.dex */
final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    public final transient Object l;

    public SingletonImmutableSet(Object obj) {
        obj.getClass();
        this.l = obj;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final ImmutableList a() {
        return ImmutableList.n(this.l);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int b(Object[] objArr) {
        objArr[0] = this.l;
        return 1;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.l.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean h() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // com.google.common.collect.ImmutableCollection
    /* JADX INFO: renamed from: i */
    public final oq2 iterator() {
        return new i61(this.l);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.l.toString() + ']';
    }
}
