package n3;

import android.os.Bundle;
import h2.AbstractC1347d;
import java.util.Arrays;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: n3.J, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1935J extends M {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f19836f;

    public static float[] g(String str) {
        return new float[]{Float.valueOf(Float.parseFloat(str)).floatValue()};
    }

    public static int[] h(String str) {
        return new int[]{((Number) M.f19838b.d(str)).intValue()};
    }

    public static long[] i(String str) {
        return new long[]{((Number) M.c.d(str)).longValue()};
    }

    public static boolean[] j(String str) {
        return new boolean[]{((Boolean) M.d.d(str)).booleanValue()};
    }

    @Override // n3.M
    public final Object a(String str, Bundle bundle) {
        switch (this.f19836f) {
            case 0:
                return (boolean[]) AbstractC1347d.f(bundle, "bundle", str, "key", str);
            case 1:
                return (float[]) AbstractC1347d.f(bundle, "bundle", str, "key", str);
            case 2:
                return (int[]) AbstractC1347d.f(bundle, "bundle", str, "key", str);
            case 3:
                return (long[]) AbstractC1347d.f(bundle, "bundle", str, "key", str);
            default:
                return (String[]) AbstractC1347d.f(bundle, "bundle", str, "key", str);
        }
    }

    @Override // n3.M
    public final String b() {
        switch (this.f19836f) {
            case 0:
                return "boolean[]";
            case 1:
                return "float[]";
            case 2:
                return "integer[]";
            case 3:
                return "long[]";
            default:
                return "string[]";
        }
    }

    @Override // n3.M
    public final Object c(Object obj, String str) {
        switch (this.f19836f) {
            case 0:
                boolean[] zArr = (boolean[]) obj;
                if (zArr == null) {
                    return j(str);
                }
                boolean[] zArrJ = j(str);
                int length = zArr.length;
                boolean[] zArrCopyOf = Arrays.copyOf(zArr, length + 1);
                System.arraycopy(zArrJ, 0, zArrCopyOf, length, 1);
                A7.m.c(zArrCopyOf);
                return zArrCopyOf;
            case 1:
                float[] fArr = (float[]) obj;
                if (fArr == null) {
                    return g(str);
                }
                float[] fArrG = g(str);
                int length2 = fArr.length;
                float[] fArrCopyOf = Arrays.copyOf(fArr, length2 + 1);
                System.arraycopy(fArrG, 0, fArrCopyOf, length2, 1);
                A7.m.c(fArrCopyOf);
                return fArrCopyOf;
            case 2:
                int[] iArr = (int[]) obj;
                if (iArr == null) {
                    return h(str);
                }
                int[] iArrH = h(str);
                int length3 = iArr.length;
                int[] iArrCopyOf = Arrays.copyOf(iArr, length3 + 1);
                System.arraycopy(iArrH, 0, iArrCopyOf, length3, 1);
                A7.m.c(iArrCopyOf);
                return iArrCopyOf;
            case 3:
                long[] jArr = (long[]) obj;
                if (jArr == null) {
                    return i(str);
                }
                long[] jArrI = i(str);
                int length4 = jArr.length;
                long[] jArrCopyOf = Arrays.copyOf(jArr, length4 + 1);
                System.arraycopy(jArrI, 0, jArrCopyOf, length4, 1);
                A7.m.c(jArrCopyOf);
                return jArrCopyOf;
            default:
                String[] strArr = (String[]) obj;
                if (strArr == null) {
                    return new String[]{str};
                }
                String[] strArr2 = {str};
                int length5 = strArr.length;
                Object[] objArrCopyOf = Arrays.copyOf(strArr, length5 + 1);
                System.arraycopy(strArr2, 0, objArrCopyOf, length5, 1);
                A7.m.c(objArrCopyOf);
                return (String[]) objArrCopyOf;
        }
    }

    @Override // n3.M
    public final Object d(String str) {
        switch (this.f19836f) {
            case 0:
                return j(str);
            case 1:
                return g(str);
            case 2:
                return h(str);
            case 3:
                return i(str);
            default:
                return new String[]{str};
        }
    }

    @Override // n3.M
    public final void e(Bundle bundle, String str, Object obj) {
        switch (this.f19836f) {
            case 0:
                A7.m.f(str, "key");
                bundle.putBooleanArray(str, (boolean[]) obj);
                break;
            case 1:
                A7.m.f(str, "key");
                bundle.putFloatArray(str, (float[]) obj);
                break;
            case 2:
                A7.m.f(str, "key");
                bundle.putIntArray(str, (int[]) obj);
                break;
            case 3:
                A7.m.f(str, "key");
                bundle.putLongArray(str, (long[]) obj);
                break;
            default:
                A7.m.f(str, "key");
                bundle.putStringArray(str, (String[]) obj);
                break;
        }
    }

    @Override // n3.M
    public final boolean f(Object obj, Object obj2) {
        Boolean[] boolArr;
        Float[] fArr;
        Integer[] numArr;
        Long[] lArr;
        switch (this.f19836f) {
            case 0:
                boolean[] zArr = (boolean[]) obj;
                boolean[] zArr2 = (boolean[]) obj2;
                Boolean[] boolArr2 = null;
                if (zArr != null) {
                    boolArr = new Boolean[zArr.length];
                    int length = zArr.length;
                    for (int i7 = 0; i7 < length; i7++) {
                        boolArr[i7] = Boolean.valueOf(zArr[i7]);
                    }
                } else {
                    boolArr = null;
                }
                if (zArr2 != null) {
                    boolArr2 = new Boolean[zArr2.length];
                    int length2 = zArr2.length;
                    for (int i10 = 0; i10 < length2; i10++) {
                        boolArr2[i10] = Boolean.valueOf(zArr2[i10]);
                    }
                }
                return AbstractC1996k.A(boolArr, boolArr2);
            case 1:
                float[] fArr2 = (float[]) obj;
                float[] fArr3 = (float[]) obj2;
                Float[] fArr4 = null;
                if (fArr2 != null) {
                    fArr = new Float[fArr2.length];
                    int length3 = fArr2.length;
                    for (int i11 = 0; i11 < length3; i11++) {
                        fArr[i11] = Float.valueOf(fArr2[i11]);
                    }
                } else {
                    fArr = null;
                }
                if (fArr3 != null) {
                    fArr4 = new Float[fArr3.length];
                    int length4 = fArr3.length;
                    for (int i12 = 0; i12 < length4; i12++) {
                        fArr4[i12] = Float.valueOf(fArr3[i12]);
                    }
                }
                return AbstractC1996k.A(fArr, fArr4);
            case 2:
                int[] iArr = (int[]) obj;
                int[] iArr2 = (int[]) obj2;
                Integer[] numArr2 = null;
                if (iArr != null) {
                    numArr = new Integer[iArr.length];
                    int length5 = iArr.length;
                    for (int i13 = 0; i13 < length5; i13++) {
                        numArr[i13] = Integer.valueOf(iArr[i13]);
                    }
                } else {
                    numArr = null;
                }
                if (iArr2 != null) {
                    numArr2 = new Integer[iArr2.length];
                    int length6 = iArr2.length;
                    for (int i14 = 0; i14 < length6; i14++) {
                        numArr2[i14] = Integer.valueOf(iArr2[i14]);
                    }
                }
                return AbstractC1996k.A(numArr, numArr2);
            case 3:
                long[] jArr = (long[]) obj;
                long[] jArr2 = (long[]) obj2;
                Long[] lArr2 = null;
                if (jArr != null) {
                    lArr = new Long[jArr.length];
                    int length7 = jArr.length;
                    for (int i15 = 0; i15 < length7; i15++) {
                        lArr[i15] = Long.valueOf(jArr[i15]);
                    }
                } else {
                    lArr = null;
                }
                if (jArr2 != null) {
                    lArr2 = new Long[jArr2.length];
                    int length8 = jArr2.length;
                    for (int i16 = 0; i16 < length8; i16++) {
                        lArr2[i16] = Long.valueOf(jArr2[i16]);
                    }
                }
                return AbstractC1996k.A(lArr, lArr2);
            default:
                return AbstractC1996k.A((String[]) obj, (String[]) obj2);
        }
    }
}
