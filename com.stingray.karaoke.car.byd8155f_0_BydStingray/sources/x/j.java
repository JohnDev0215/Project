package X;

import A7.m;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class j extends c {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final j f9965m = new j(new Object[0]);
    public final Object[] l;

    public j(Object[] objArr) {
        this.l = objArr;
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.l.length;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        com.bumptech.glide.d.r(i7, d());
        return this.l[i7];
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final int indexOf(Object obj) {
        return AbstractC1996k.T(obj, this.l);
    }

    @Override // X.c
    public final c l(int i7, Object obj) {
        Object[] objArr = this.l;
        com.bumptech.glide.d.s(i7, objArr.length);
        if (i7 == objArr.length) {
            return q(obj);
        }
        if (objArr.length < 32) {
            Object[] objArr2 = new Object[objArr.length + 1];
            AbstractC1996k.G(0, i7, 6, objArr, objArr2);
            AbstractC1996k.D(i7 + 1, i7, objArr.length, objArr, objArr2);
            objArr2[i7] = obj;
            return new j(objArr2);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        m.e(objArrCopyOf, "copyOf(this, size)");
        AbstractC1996k.D(i7 + 1, i7, objArr.length - 1, objArr, objArrCopyOf);
        objArrCopyOf[i7] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = objArr[31];
        return new f(objArrCopyOf, objArr3, objArr.length + 1, 0);
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final int lastIndexOf(Object obj) {
        Object[] objArr = this.l;
        m.f(objArr, "<this>");
        if (obj == null) {
            int length = objArr.length - 1;
            if (length < 0) {
                return -1;
            }
            while (true) {
                int i7 = length - 1;
                if (objArr[length] == null) {
                    return length;
                }
                if (i7 < 0) {
                    return -1;
                }
                length = i7;
            }
        } else {
            int length2 = objArr.length - 1;
            if (length2 < 0) {
                return -1;
            }
            while (true) {
                int i10 = length2 - 1;
                if (obj.equals(objArr[length2])) {
                    return length2;
                }
                if (i10 < 0) {
                    return -1;
                }
                length2 = i10;
            }
        }
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final ListIterator listIterator(int i7) {
        Object[] objArr = this.l;
        com.bumptech.glide.d.s(i7, objArr.length);
        return new d(objArr, i7, objArr.length);
    }

    @Override // X.c
    public final c q(Object obj) {
        Object[] objArr = this.l;
        if (objArr.length >= 32) {
            Object[] objArr2 = new Object[32];
            objArr2[0] = obj;
            return new f(objArr, objArr2, objArr.length + 1, 0);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + 1);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        objArrCopyOf[objArr.length] = obj;
        return new j(objArrCopyOf);
    }

    @Override // X.c
    public final c r(Collection collection) {
        Object[] objArr = this.l;
        if (collection.size() + objArr.length > 32) {
            g gVarS = s();
            gVarS.addAll(collection);
            return gVarS.q();
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, collection.size() + objArr.length);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        int length = objArr.length;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            objArrCopyOf[length] = it.next();
            length++;
        }
        return new j(objArrCopyOf);
    }

    @Override // X.c
    public final g s() {
        return new g(this, null, this.l, 0);
    }

    @Override // X.c
    public final c t(b bVar) {
        Object[] objArr = this.l;
        int length = objArr.length;
        int length2 = objArr.length;
        Object[] objArrCopyOf = objArr;
        boolean z6 = false;
        for (int i7 = 0; i7 < length2; i7++) {
            Object obj = objArr[i7];
            if (((Boolean) bVar.b(obj)).booleanValue()) {
                if (!z6) {
                    objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                    m.e(objArrCopyOf, "copyOf(this, size)");
                    z6 = true;
                    length = i7;
                }
            } else if (z6) {
                objArrCopyOf[length] = obj;
                length++;
            }
        }
        return length == objArr.length ? this : length == 0 ? f9965m : new j(AbstractC1996k.I(objArrCopyOf, 0, length));
    }

    @Override // X.c
    public final c u(int i7) {
        Object[] objArr = this.l;
        com.bumptech.glide.d.r(i7, objArr.length);
        if (objArr.length == 1) {
            return f9965m;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length - 1);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        AbstractC1996k.D(i7, i7 + 1, objArr.length, objArr, objArrCopyOf);
        return new j(objArrCopyOf);
    }

    @Override // X.c
    public final c v(int i7, Object obj) {
        Object[] objArr = this.l;
        com.bumptech.glide.d.r(i7, objArr.length);
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        m.e(objArrCopyOf, "copyOf(this, size)");
        objArrCopyOf[i7] = obj;
        return new j(objArrCopyOf);
    }
}
