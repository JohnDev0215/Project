package defpackage;

import com.google.common.collect.b;
import com.google.common.collect.d;

/* JADX INFO: compiled from: SingletonImmutableSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class wn2<E> extends d<E> {
    public final transient E g;

    public wn2(E e) {
        this.g = e;
    }

    @Override // com.google.common.collect.a
    public final int a(int i, Object[] objArr) {
        objArr[i] = this.g;
        return i + 1;
    }

    @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.g.equals(obj);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: f */
    public final xi3<E> iterator() {
        return new d31(this.g);
    }

    @Override // com.google.common.collect.d, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.g.hashCode();
    }

    @Override // com.google.common.collect.d
    public final b<E> p() {
        return b.t(this.g);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String string = this.g.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 2);
        sb.append('[');
        sb.append(string);
        sb.append(']');
        return sb.toString();
    }
}
