package l8;

import A7.m;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.C1988c;
import n7.C1997l;
import n7.u;

/* JADX INFO: renamed from: l8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1783a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f18822a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18823b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f18824e;

    public AbstractC1783a(int... iArr) {
        List listZ0;
        m.f(iArr, "numbers");
        this.f18822a = iArr;
        Integer numR = AbstractC1996k.R(iArr, 0);
        this.f18823b = numR != null ? numR.intValue() : -1;
        Integer numR2 = AbstractC1996k.R(iArr, 1);
        this.c = numR2 != null ? numR2.intValue() : -1;
        Integer numR3 = AbstractC1996k.R(iArr, 2);
        this.d = numR3 != null ? numR3.intValue() : -1;
        if (iArr.length <= 3) {
            listZ0 = u.l;
        } else {
            if (iArr.length > 1024) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("BinaryVersion with length more than 1024 are not supported. Provided length "), iArr.length, '.'));
            }
            listZ0 = AbstractC1998m.z0(new C1988c(new C1997l(iArr), 3, iArr.length));
        }
        this.f18824e = listZ0;
    }

    public final boolean a(int i7, int i10, int i11) {
        int i12 = this.f18823b;
        if (i12 > i7) {
            return true;
        }
        if (i12 < i7) {
            return false;
        }
        int i13 = this.c;
        if (i13 > i10) {
            return true;
        }
        return i13 >= i10 && this.d >= i11;
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass().equals(obj.getClass())) {
            AbstractC1783a abstractC1783a = (AbstractC1783a) obj;
            if (this.f18823b == abstractC1783a.f18823b && this.c == abstractC1783a.c && this.d == abstractC1783a.d && m.a(this.f18824e, abstractC1783a.f18824e)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = this.f18823b;
        int i10 = (i7 * 31) + this.c + i7;
        int i11 = (i10 * 31) + this.d + i10;
        return this.f18824e.hashCode() + (i11 * 31) + i11;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        for (int i7 : this.f18822a) {
            if (i7 == -1) {
                break;
            }
            arrayList.add(Integer.valueOf(i7));
        }
        return arrayList.isEmpty() ? "unknown" : AbstractC1998m.f0(arrayList, ".", null, null, null, 62);
    }
}
