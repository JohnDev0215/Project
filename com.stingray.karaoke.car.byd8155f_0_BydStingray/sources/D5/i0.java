package d5;

import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends K {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final i0 f13428r = new i0(0, null, new Object[0]);
    public final transient Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient Object[] f13429p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f13430q;

    public i0(int i7, Object obj, Object[] objArr) {
        this.o = obj;
        this.f13429p = objArr;
        this.f13430q = i7;
    }

    public static Object g(Object[] objArr, int i7, int i10, int i11) {
        J j3 = null;
        if (i7 == 1) {
            Objects.requireNonNull(objArr[i11]);
            Objects.requireNonNull(objArr[i11 ^ 1]);
            return null;
        }
        int i12 = i10 - 1;
        int i13 = -1;
        if (i10 <= 128) {
            byte[] bArr = new byte[i10];
            Arrays.fill(bArr, (byte) -1);
            int i14 = 0;
            for (int i15 = 0; i15 < i7; i15++) {
                int i16 = (i15 * 2) + i11;
                int i17 = (i14 * 2) + i11;
                Object obj = objArr[i16];
                Objects.requireNonNull(obj);
                Object obj2 = objArr[i16 ^ 1];
                Objects.requireNonNull(obj2);
                int iT = AbstractC1095q.t(obj.hashCode());
                while (true) {
                    int i18 = iT & i12;
                    int i19 = bArr[i18] & 255;
                    if (i19 == 255) {
                        bArr[i18] = (byte) i17;
                        if (i14 < i15) {
                            objArr[i17] = obj;
                            objArr[i17 ^ 1] = obj2;
                        }
                        i14++;
                    } else {
                        if (obj.equals(objArr[i19])) {
                            int i20 = i19 ^ 1;
                            Object obj3 = objArr[i20];
                            Objects.requireNonNull(obj3);
                            j3 = new J(obj, obj2, obj3);
                            objArr[i20] = obj2;
                            break;
                        }
                        iT = i18 + 1;
                    }
                }
            }
            return i14 == i7 ? bArr : new Object[]{bArr, Integer.valueOf(i14), j3};
        }
        if (i10 <= 32768) {
            short[] sArr = new short[i10];
            Arrays.fill(sArr, (short) -1);
            int i21 = 0;
            for (int i22 = 0; i22 < i7; i22++) {
                int i23 = (i22 * 2) + i11;
                int i24 = (i21 * 2) + i11;
                Object obj4 = objArr[i23];
                Objects.requireNonNull(obj4);
                Object obj5 = objArr[i23 ^ 1];
                Objects.requireNonNull(obj5);
                int iT2 = AbstractC1095q.t(obj4.hashCode());
                while (true) {
                    int i25 = iT2 & i12;
                    int i26 = sArr[i25] & 65535;
                    if (i26 == 65535) {
                        sArr[i25] = (short) i24;
                        if (i21 < i22) {
                            objArr[i24] = obj4;
                            objArr[i24 ^ 1] = obj5;
                        }
                        i21++;
                    } else {
                        if (obj4.equals(objArr[i26])) {
                            int i27 = i26 ^ 1;
                            Object obj6 = objArr[i27];
                            Objects.requireNonNull(obj6);
                            j3 = new J(obj4, obj5, obj6);
                            objArr[i27] = obj5;
                            break;
                        }
                        iT2 = i25 + 1;
                    }
                }
            }
            return i21 == i7 ? sArr : new Object[]{sArr, Integer.valueOf(i21), j3};
        }
        int[] iArr = new int[i10];
        Arrays.fill(iArr, -1);
        int i28 = 0;
        int i29 = 0;
        while (i28 < i7) {
            int i30 = (i28 * 2) + i11;
            int i31 = (i29 * 2) + i11;
            Object obj7 = objArr[i30];
            Objects.requireNonNull(obj7);
            Object obj8 = objArr[i30 ^ 1];
            Objects.requireNonNull(obj8);
            int iT3 = AbstractC1095q.t(obj7.hashCode());
            while (true) {
                int i32 = iT3 & i12;
                int i33 = iArr[i32];
                if (i33 == i13) {
                    iArr[i32] = i31;
                    if (i29 < i28) {
                        objArr[i31] = obj7;
                        objArr[i31 ^ 1] = obj8;
                    }
                    i29++;
                } else {
                    if (obj7.equals(objArr[i33])) {
                        int i34 = i33 ^ 1;
                        Object obj9 = objArr[i34];
                        Objects.requireNonNull(obj9);
                        j3 = new J(obj7, obj8, obj9);
                        objArr[i34] = obj8;
                        break;
                    }
                    iT3 = i32 + 1;
                    i13 = -1;
                }
            }
            i28++;
            i13 = -1;
        }
        return i29 == i7 ? iArr : new Object[]{iArr, Integer.valueOf(i29), j3};
    }

    public static Object h(Object obj, Object[] objArr, int i7, int i10, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i7 == 1) {
            Object obj3 = objArr[i10];
            Objects.requireNonNull(obj3);
            if (!obj3.equals(obj2)) {
                return null;
            }
            Object obj4 = objArr[i10 ^ 1];
            Objects.requireNonNull(obj4);
            return obj4;
        }
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length - 1;
            int iT = AbstractC1095q.t(obj2.hashCode());
            while (true) {
                int i11 = iT & length;
                int i12 = bArr[i11] & 255;
                if (i12 == 255) {
                    return null;
                }
                if (obj2.equals(objArr[i12])) {
                    return objArr[i12 ^ 1];
                }
                iT = i11 + 1;
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            int length2 = sArr.length - 1;
            int iT2 = AbstractC1095q.t(obj2.hashCode());
            while (true) {
                int i13 = iT2 & length2;
                int i14 = sArr[i13] & 65535;
                if (i14 == 65535) {
                    return null;
                }
                if (obj2.equals(objArr[i14])) {
                    return objArr[i14 ^ 1];
                }
                iT2 = i13 + 1;
            }
        } else {
            int[] iArr = (int[]) obj;
            int length3 = iArr.length - 1;
            int iT3 = AbstractC1095q.t(obj2.hashCode());
            while (true) {
                int i15 = iT3 & length3;
                int i16 = iArr[i15];
                if (i16 == -1) {
                    return null;
                }
                if (obj2.equals(objArr[i16])) {
                    return objArr[i16 ^ 1];
                }
                iT3 = i15 + 1;
            }
        }
    }

    @Override // d5.K
    public final f0 c() {
        return new f0(this, this.f13429p, 0, this.f13430q);
    }

    @Override // d5.K
    public final g0 d() {
        return new g0(this, new h0(this.f13429p, 0, this.f13430q));
    }

    @Override // d5.K
    public final AbstractC1075D e() {
        return new h0(this.f13429p, 1, this.f13430q);
    }

    @Override // d5.K, java.util.Map
    public final Object get(Object obj) {
        Object objH = h(this.o, this.f13429p, this.f13430q, 0, obj);
        if (objH == null) {
            return null;
        }
        return objH;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f13430q;
    }
}
