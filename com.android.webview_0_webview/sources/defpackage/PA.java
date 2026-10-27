package defpackage;

import android.graphics.Matrix;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class PA {
    public boolean a;
    public boolean b;
    public boolean c;
    public float[] d;
    public boolean e;
    public float f;
    public float g;
    public float h;
    public boolean i;
    public boolean j;
    public float k;
    public float l;
    public float m;
    public CursorAnchorInfo n;
    public final Matrix o = new Matrix();
    public final int[] p = new int[2];
    public final CursorAnchorInfo.Builder q = new CursorAnchorInfo.Builder();
    public ND r;
    public final NA s;
    public final OA t;

    public PA(ND nd, NA na, OA oa) {
        this.r = nd;
        this.s = na;
        this.t = oa;
    }

    public boolean a(boolean z, boolean z2, View view) {
        boolean z3 = this.a;
        if (!z3) {
            return false;
        }
        if (this.c && !z2 && z3) {
            this.n = null;
        }
        this.c = z2;
        if (z) {
            this.b = true;
            b(view);
        }
        return true;
    }

    public final void b(View view) {
        if (this.e) {
            if (this.n == null) {
                this.q.reset();
                NA na = this.s;
                String str = ((RA) na).a.L;
                int i = ((RA) na).a.f30J;
                int i2 = ((RA) na).a.K;
                int i3 = ((RA) na).a.M;
                int i4 = ((RA) na).a.N;
                if (str != null && i3 >= 0 && i4 <= str.length()) {
                    this.q.setComposingText(i3, str.subSequence(i3, i4));
                    float[] fArr = this.d;
                    if (fArr != null) {
                        int length = fArr.length / 4;
                        for (int i5 = 0; i5 < length; i5++) {
                            int i6 = i5 * 4;
                            this.q.addCharacterBounds(i3 + i5, fArr[i6], fArr[i6 + 1], fArr[i6 + 2], fArr[i6 + 3], 1);
                        }
                    }
                }
                this.q.setSelectionRange(i, i2);
                Matrix matrix = this.o;
                float f = this.f;
                matrix.setScale(f, f);
                this.o.postTranslate(this.g, this.h);
                this.q.setMatrix(this.o);
                if (this.i) {
                    CursorAnchorInfo.Builder builder = this.q;
                    float f2 = this.k;
                    float f3 = this.l;
                    float f4 = this.m;
                    builder.setInsertionMarkerLocation(f2, f3, f4, f4, this.j ? 1 : 2);
                }
                this.n = this.q.build();
            }
            ND nd = this.r;
            if (nd != null) {
                CursorAnchorInfo cursorAnchorInfo = this.n;
                InputMethodManager inputMethodManagerB = ((WA) nd).b();
                if (inputMethodManagerB != null) {
                    inputMethodManagerB.updateCursorAnchorInfo(view, cursorAnchorInfo);
                }
            }
            this.b = false;
        }
    }
}
