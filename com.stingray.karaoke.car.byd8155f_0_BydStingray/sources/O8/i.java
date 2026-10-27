package O8;

import A7.E;
import A7.m;
import L8.q;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import n7.AbstractC1996k;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractSet {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f5194n = 0;
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5195m;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        Object obj2;
        int i7 = this.f5195m;
        if (i7 == 0) {
            this.l = obj;
        } else if (i7 == 1) {
            if (m.a(this.l, obj)) {
                return false;
            }
            this.l = new Object[]{this.l, obj};
        } else if (i7 < 5) {
            Object obj3 = this.l;
            m.d(obj3, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            Object[] objArr = (Object[]) obj3;
            if (AbstractC1996k.z(obj, objArr)) {
                return false;
            }
            int i10 = this.f5195m;
            if (i10 == 4) {
                Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                m.f(objArrCopyOf, "elements");
                LinkedHashSet linkedHashSet = new LinkedHashSet(z.q(objArrCopyOf.length));
                AbstractC1996k.Z(objArrCopyOf, linkedHashSet);
                linkedHashSet.add(obj);
                obj2 = linkedHashSet;
            } else {
                Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i10 + 1);
                m.e(objArrCopyOf2, "copyOf(this, newSize)");
                objArrCopyOf2[objArrCopyOf2.length - 1] = obj;
                obj2 = objArrCopyOf2;
            }
            this.l = obj2;
        } else {
            Object obj4 = this.l;
            m.d(obj4, "null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
            if (!E.d(obj4).add(obj)) {
                return false;
            }
        }
        this.f5195m++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.l = null;
        this.f5195m = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int i7 = this.f5195m;
        if (i7 == 0) {
            return false;
        }
        if (i7 == 1) {
            return m.a(this.l, obj);
        }
        if (i7 < 5) {
            Object obj2 = this.l;
            m.d(obj2, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            return AbstractC1996k.z(obj, (Object[]) obj2);
        }
        Object obj3 = this.l;
        m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Set<T of org.jetbrains.kotlin.utils.SmartSet>");
        return ((Set) obj3).contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        Iterator hVar;
        int i7 = this.f5195m;
        if (i7 == 0) {
            return Collections.emptySet().iterator();
        }
        if (i7 == 1) {
            hVar = new q(this.l, 1);
        } else {
            if (i7 >= 5) {
                Object obj = this.l;
                m.d(obj, "null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
                return E.d(obj).iterator();
            }
            Object obj2 = this.l;
            m.d(obj2, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            hVar = new h((Object[]) obj2);
        }
        return hVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f5195m;
    }
}
