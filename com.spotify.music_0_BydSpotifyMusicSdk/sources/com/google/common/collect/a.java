package com.google.common.collect;

import com.google.common.base.Optional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import p.b41;
import p.g61;
import p.he1;
import p.kv0;
import p.oz0;
import p.p02;
import p.y31;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Iterable {
    public final Optional c;

    public a() {
        this.c = Optional.a();
    }

    public static a a(Iterable iterable) {
        return iterable instanceof a ? (a) iterable : new kv0(iterable, iterable);
    }

    public final Iterable b() {
        return (Iterable) this.c.g(this);
    }

    public final ImmutableList c() {
        Object[] objArrCopyOf;
        Iterable iterableB = b();
        y31 y31Var = ImmutableList.e;
        iterableB.getClass();
        if (iterableB instanceof Collection) {
            return ImmutableList.o((Collection) iterableB);
        }
        Iterator it = iterableB.iterator();
        if (!it.hasNext()) {
            return RegularImmutableList.m;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ImmutableList.n(next);
        }
        he1.e(4, "initialCapacity");
        Object[] objArrCopyOf2 = new Object[4];
        next.getClass();
        int i = 0 + 1;
        if (objArrCopyOf2.length < i) {
            objArrCopyOf2 = Arrays.copyOf(objArrCopyOf2, p02.h(objArrCopyOf2.length, i));
        }
        int i2 = 0 + 1;
        objArrCopyOf2[0] = next;
        boolean z = false;
        while (it.hasNext()) {
            Object next2 = it.next();
            next2.getClass();
            int i3 = i2 + 1;
            if (objArrCopyOf2.length < i3) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf2, p02.h(objArrCopyOf2.length, i3));
            } else if (z) {
                objArrCopyOf = (Object[]) objArrCopyOf2.clone();
            } else {
                objArrCopyOf2[i2] = next2;
                i2++;
            }
            objArrCopyOf2 = objArrCopyOf;
            z = false;
            objArrCopyOf2[i2] = next2;
            i2++;
        }
        return ImmutableList.m(i2, objArrCopyOf2);
    }

    public final ImmutableSet d() {
        Iterable iterableB = b();
        int i = ImmutableSet.k;
        if (iterableB instanceof Collection) {
            return ImmutableSet.o((Collection) iterableB);
        }
        Iterator it = iterableB.iterator();
        if (!it.hasNext()) {
            return RegularImmutableSet.r;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return new SingletonImmutableSet(next);
        }
        b41 b41Var = new b41();
        b41Var.v(next);
        while (it.hasNext()) {
            b41Var.v(it.next());
        }
        int i2 = b41Var.h;
        if (i2 == 0) {
            return RegularImmutableSet.r;
        }
        if (i2 == 1) {
            Object obj = b41Var.g[0];
            Objects.requireNonNull(obj);
            return new SingletonImmutableSet(obj);
        }
        ImmutableSet immutableSetN = ImmutableSet.n(i2, b41Var.g);
        b41Var.h = immutableSetN.size();
        b41Var.i = true;
        return immutableSetN;
    }

    public final a e(oz0 oz0Var) {
        Iterable iterableB = b();
        iterableB.getClass();
        return a(new g61(iterableB, oz0Var, 1));
    }

    public final String toString() {
        Iterator it = b().iterator();
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(it.next());
            z = false;
        }
        sb.append(']');
        return sb.toString();
    }

    public a(Iterable iterable) {
        this.c = Optional.e(iterable);
    }
}
