package defpackage;

import com.google.common.collect.d;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ImmutableEnumSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class mx0<E extends Enum<E>> extends d<E> {
    public final transient EnumSet<E> g;
    public transient int h;

    /* JADX INFO: compiled from: ImmutableEnumSet.java */
    public static class a<E extends Enum<E>> implements Serializable {
        private static final long serialVersionUID = 0;
        public final EnumSet<E> e;

        public a(EnumSet<E> enumSet) {
            this.e = enumSet;
        }

        public Object readResolve() {
            return new mx0(this.e.clone());
        }
    }

    public mx0(EnumSet<E> enumSet) {
        this.g = enumSet;
    }

    @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.g.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        if (collection instanceof mx0) {
            collection = ((mx0) collection).g;
        }
        return this.g.containsAll(collection);
    }

    @Override // com.google.common.collect.d, java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof mx0) {
            obj = ((mx0) obj).g;
        }
        return this.g.equals(obj);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: f */
    public final xi3<E> iterator() {
        Iterator<E> it = this.g.iterator();
        return it instanceof xi3 ? (xi3) it : new c31(it);
    }

    @Override // com.google.common.collect.d, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i = this.h;
        if (i != 0) {
            return i;
        }
        int iHashCode = this.g.hashCode();
        this.h = iHashCode;
        return iHashCode;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.g.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.g.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return this.g.toString();
    }

    @Override // com.google.common.collect.d, com.google.common.collect.a
    public Object writeReplace() {
        return new a(this.g);
    }
}
