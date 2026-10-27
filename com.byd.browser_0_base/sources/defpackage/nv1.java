package defpackage;

import android.graphics.Rect;
import java.util.Arrays;
import org.chromium.base.UnguessableToken;

/* JADX INFO: compiled from: PaintPreviewFrame.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nv1 {
    public final UnguessableToken a;
    public final int b;
    public final int c;
    public nv1[] d;
    public Rect[] e;
    public final int f;
    public final int g;

    public nv1(UnguessableToken unguessableToken, int i, int i2, int i3, int i4) {
        this.a = unguessableToken;
        this.b = i;
        this.c = i2;
        this.f = i3;
        this.g = i4;
    }

    public final boolean a(boolean z) {
        if (this.e != null && this.d != null) {
            int i = 0;
            while (true) {
                nv1[] nv1VarArr = this.d;
                if (i >= nv1VarArr.length) {
                    break;
                }
                nv1 nv1Var = nv1VarArr[i];
                Rect rect = this.e[i];
                if ((z && (nv1Var.b > rect.width() || nv1Var.c > rect.height())) || nv1Var.a(true)) {
                    return true;
                }
                i++;
            }
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj == null || nv1.class != obj.getClass()) {
            return false;
        }
        nv1 nv1Var = (nv1) obj;
        return this.a.equals(nv1Var.a) && this.c == nv1Var.c && this.b == nv1Var.b && Arrays.equals(this.d, nv1Var.d) && Arrays.equals(this.e, nv1Var.e);
    }

    public final String toString() {
        return "Guid : " + this.a + ", ContentWidth : " + this.b + ", ContentHeight: " + this.c + ", SubFrames: " + Arrays.deepToString(this.d) + ", SubFrameClips: " + Arrays.deepToString(this.e);
    }
}
