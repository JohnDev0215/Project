package defpackage;

/* JADX INFO: renamed from: Zh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0456Zh extends AbstractC0402Wh {
    public static final AbstractC0402Wh y = new C0456Zh(null, new Object[0], 0);
    public final transient Object v;
    public final transient Object[] w;
    public final transient int x;

    public C0456Zh(Object obj, Object[] objArr, int i) {
        this.v = obj;
        this.w = objArr;
        this.x = i;
    }

    public static IllegalArgumentException a(Object obj, Object obj2, Object[] objArr, int i) {
        String strValueOf = String.valueOf(obj);
        String strValueOf2 = String.valueOf(obj2);
        String strValueOf3 = String.valueOf(objArr[i]);
        String strValueOf4 = String.valueOf(objArr[i ^ 1]);
        StringBuilder sb = new StringBuilder(strValueOf4.length() + strValueOf3.length() + strValueOf2.length() + strValueOf.length() + 39);
        sb.append("Multiple entries with same key: ");
        sb.append(strValueOf);
        sb.append("=");
        sb.append(strValueOf2);
        sb.append(" and ");
        sb.append(strValueOf3);
        sb.append("=");
        sb.append(strValueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    @Override // defpackage.AbstractC0402Wh, java.util.Map
    public final Object get(Object obj) {
        Object obj2 = this.v;
        Object[] objArr = this.w;
        int i = this.x;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return objArr[1];
            }
            return null;
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int iA = AbstractC0348Th.a(obj.hashCode());
            while (true) {
                int i2 = iA & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return objArr[i3 ^ 1];
                }
                iA = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int iA2 = AbstractC0348Th.a(obj.hashCode());
            while (true) {
                int i4 = iA2 & length2;
                int i5 = sArr[i4] & 65535;
                if (i5 == 65535) {
                    return null;
                }
                if (objArr[i5].equals(obj)) {
                    return objArr[i5 ^ 1];
                }
                iA2 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int iA3 = AbstractC0348Th.a(obj.hashCode());
            while (true) {
                int i6 = iA3 & length3;
                int i7 = iArr[i6];
                if (i7 == -1) {
                    return null;
                }
                if (objArr[i7].equals(obj)) {
                    return objArr[i7 ^ 1];
                }
                iA3 = i6 + 1;
            }
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.x;
    }
}
