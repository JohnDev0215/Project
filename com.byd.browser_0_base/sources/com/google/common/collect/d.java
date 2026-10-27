package com.google.common.collect;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import com.google.common.collect.b;
import defpackage.mx0;
import defpackage.n3;
import defpackage.wn2;
import defpackage.z7;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: ImmutableSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d<E> extends com.google.common.collect.a<E> implements Set<E> {
    public transient b<E> f;

    /* JADX INFO: compiled from: ImmutableSet.java */
    public static class a implements Serializable {
        private static final long serialVersionUID = 0;
        public final Object[] e;

        public a(Object[] objArr) {
            this.e = objArr;
        }

        public Object readResolve() {
            return d.t(this.e);
        }
    }

    public static int q(int i) {
        int iMax = Math.max(i, 2);
        if (iMax >= 751619276) {
            return TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1);
        do {
            iHighestOneBit <<= 1;
        } while (((double) iHighestOneBit) * 0.7d < iMax);
        return iHighestOneBit;
    }

    public static <E> d<E> s(int i, Object... objArr) {
        if (i == 0) {
            return f.m;
        }
        if (i == 1) {
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            return new wn2(obj);
        }
        int iQ = q(i);
        Object[] objArr2 = new Object[iQ];
        int i2 = iQ - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj2 = objArr[i5];
            if (obj2 == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i5);
                throw new NullPointerException(sb.toString());
            }
            int iHashCode = obj2.hashCode();
            int iO1 = n3.o1(iHashCode);
            while (true) {
                int i6 = iO1 & i2;
                Object obj3 = objArr2[i6];
                if (obj3 == null) {
                    objArr[i4] = obj2;
                    objArr2[i6] = obj2;
                    i3 += iHashCode;
                    i4++;
                    break;
                }
                if (obj3.equals(obj2)) {
                    break;
                }
                iO1++;
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            Object obj4 = objArr[0];
            Objects.requireNonNull(obj4);
            return new wn2(obj4);
        }
        if (q(i4) < iQ / 2) {
            return s(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new f(i3, i2, i4, objArr, objArr2);
    }

    public static <E> d<E> t(E[] eArr) {
        int length = eArr.length;
        return length != 0 ? length != 1 ? s(eArr.length, (Object[]) eArr.clone()) : new wn2(eArr[0]) : f.m;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof d) && w() && ((d) obj).w() && hashCode() != obj.hashCode()) {
            return false;
        }
        return z7.f(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return z7.H(this);
    }

    @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public b<E> p() {
        b<E> bVar = this.f;
        if (bVar != null) {
            return bVar;
        }
        b<E> bVarU = u();
        this.f = bVarU;
        return bVarU;
    }

    public b<E> u() {
        Object[] array = toArray();
        b.a aVar = b.f;
        return b.p(array.length, array);
    }

    public boolean w() {
        return this instanceof mx0;
    }

    @Override // com.google.common.collect.a
    public Object writeReplace() {
        return new a(toArray());
    }
}
