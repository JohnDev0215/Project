package defpackage;

import android.graphics.Rect;

/* JADX INFO: renamed from: bP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0549bP {
    public final int a;
    public final int b;
    public final Rect c;
    public final int[] d;
    public final int[] e;
    public Rect f;

    public C0549bP(int i, int i2, Rect rect, int[] iArr, int[] iArr2) {
        this.a = i;
        this.b = i2;
        this.c = new Rect(rect.left, rect.top, this.a - rect.right, this.b - rect.bottom);
        int[] iArr3 = new int[iArr.length];
        this.d = iArr3;
        this.e = new int[iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, this.e, 0, iArr2.length);
        int[] iArr4 = this.d;
        int i3 = iArr4[0];
        int[] iArr5 = this.e;
        this.f = new Rect(i3, iArr5[0], iArr4[1], iArr5[1]);
    }
}
