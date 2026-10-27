package defpackage;

import android.graphics.Rect;

/* JADX INFO: compiled from: CustomToast.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l50 {
    public final Object a;
    public Object b;
    public Object c;
    public Object d;

    public /* synthetic */ l50(int i, int i2, Rect rect, int[] iArr, int[] iArr2) {
        this.a = new Rect(rect.left, rect.top, i - rect.right, i2 - rect.bottom);
        int[] iArr3 = new int[iArr.length];
        this.b = iArr3;
        this.c = new int[iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, (int[]) this.c, 0, iArr2.length);
        int[] iArr4 = (int[]) this.b;
        int i3 = iArr4[0];
        int[] iArr5 = (int[]) this.c;
        this.d = new Rect(i3, iArr5[0], iArr4[1], iArr5[1]);
    }
}
