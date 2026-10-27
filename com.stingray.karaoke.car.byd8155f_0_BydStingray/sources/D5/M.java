package d5;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class M extends AbstractC1075D implements Set {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f13389n = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient I f13390m;

    public static int t(int i7) {
        int iMax = Math.max(i7, 2);
        if (iMax >= 751619276) {
            if (iMax < 1073741824) {
                return TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
        while (((double) iHighestOneBit) * 0.7d < iMax) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    public static M u(int i7, Object... objArr) {
        if (i7 == 0) {
            return j0.f13433u;
        }
        if (i7 == 1) {
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            return new q0(obj);
        }
        int iT = t(i7);
        Object[] objArr2 = new Object[iT];
        int i10 = iT - 1;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < i7; i13++) {
            Object obj2 = objArr[i13];
            if (obj2 == null) {
                throw new NullPointerException(android.hardware.bydauto.ac.tsimpl.a.e(i13, "at index "));
            }
            int iHashCode = obj2.hashCode();
            int iT2 = AbstractC1095q.t(iHashCode);
            while (true) {
                int i14 = iT2 & i10;
                Object obj3 = objArr2[i14];
                if (obj3 == null) {
                    objArr[i12] = obj2;
                    objArr2[i14] = obj2;
                    i11 += iHashCode;
                    i12++;
                    break;
                }
                if (obj3.equals(obj2)) {
                    break;
                }
                iT2++;
            }
        }
        Arrays.fill(objArr, i12, i7, (Object) null);
        if (i12 == 1) {
            Object obj4 = objArr[0];
            Objects.requireNonNull(obj4);
            return new q0(obj4);
        }
        if (t(i12) < iT / 2) {
            return u(i12, objArr);
        }
        int length = objArr.length;
        if (i12 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i12);
        }
        return new j0(i11, i10, i12, objArr, objArr2);
    }

    public static M v(Collection collection) {
        if ((collection instanceof M) && !(collection instanceof SortedSet)) {
            M m10 = (M) collection;
            if (!m10.r()) {
                return m10;
            }
        }
        Object[] array = collection.toArray();
        return u(array.length, array);
    }

    @Override // d5.AbstractC1075D
    public I a() {
        I i7 = this.f13390m;
        if (i7 != null) {
            return i7;
        }
        I iW = w();
        this.f13390m = iW;
        return iW;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof M) && (this instanceof j0)) {
            M m10 = (M) obj;
            m10.getClass();
            if ((m10 instanceof j0) && hashCode() != obj.hashCode()) {
                return false;
            }
        }
        return AbstractC1095q.j(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return AbstractC1095q.n(this);
    }

    public I w() {
        Object[] array = toArray(AbstractC1075D.l);
        C1078G c1078g = I.f13384m;
        return I.t(array.length, array);
    }
}
