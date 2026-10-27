package com.google.protobuf;

import defpackage.lk;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LazyField.java */
/* JADX INFO: loaded from: classes.dex */
public final class e0 extends f0 {
    public final r0 e;

    /* JADX INFO: compiled from: LazyField.java */
    public static class a<K> implements Map.Entry<K, Object> {
        public final Map.Entry<K, e0> e;

        public a() {
            throw null;
        }

        public a(Map.Entry entry) {
            this.e = entry;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return this.e.getKey();
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            e0 value = this.e.getValue();
            if (value == null) {
                return null;
            }
            return value.c();
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            if (!(obj instanceof r0)) {
                throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
            }
            e0 value = this.e.getValue();
            r0 r0Var = value.c;
            value.a = null;
            value.d = null;
            value.c = (r0) obj;
            return r0Var;
        }
    }

    /* JADX INFO: compiled from: LazyField.java */
    public static class b<K> implements Iterator<Map.Entry<K, Object>> {
        public final Iterator<Map.Entry<K, Object>> e;

        public b(Iterator<Map.Entry<K, Object>> it) {
            this.e = it;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.e.hasNext();
        }

        @Override // java.util.Iterator
        public final Object next() {
            Map.Entry<K, Object> next = this.e.next();
            return next.getValue() instanceof e0 ? new a(next) : next;
        }

        @Override // java.util.Iterator
        public final void remove() {
            this.e.remove();
        }
    }

    public e0(s sVar, lk lkVar) {
        super(lkVar, sVar);
        this.e = null;
    }

    public final r0 c() {
        return a(this.e);
    }

    @Override // com.google.protobuf.f0
    public final boolean equals(Object obj) {
        return c().equals(obj);
    }

    @Override // com.google.protobuf.f0
    public final int hashCode() {
        return c().hashCode();
    }

    public final String toString() {
        return c().toString();
    }
}
