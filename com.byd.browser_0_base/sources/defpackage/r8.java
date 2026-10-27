package defpackage;

/* JADX INFO: compiled from: _Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public class r8 extends z7 {
    public static final void p0(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        i21.e(iArr, "<this>");
        i21.e(iArr2, "destination");
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
    }

    public static final void q0(int i, int i2, int i3, Object[] objArr, Object[] objArr2) {
        i21.e(objArr, "<this>");
        i21.e(objArr2, "destination");
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static /* synthetic */ void r0(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        q0(i, i2, i3, objArr, objArr2);
    }
}
