package org.chromium.content.browser.input;

import android.graphics.Matrix;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import defpackage.h01;
import defpackage.i01;

/* JADX INFO: compiled from: CursorAnchorInfoController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {
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
    public h01 r;
    public final a s;
    public final InterfaceC0150b t;

    /* JADX INFO: compiled from: CursorAnchorInfoController.java */
    public interface a {
    }

    /* JADX INFO: renamed from: org.chromium.content.browser.input.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAnchorInfoController.java */
    public interface InterfaceC0150b {
    }

    public b(i01 i01Var, c cVar, org.chromium.content.browser.input.a aVar) {
        this.r = i01Var;
        this.s = cVar;
        this.t = aVar;
    }

    public final void a(View view) {
        if (this.e) {
            if (this.n == null) {
                CursorAnchorInfo.Builder builder = this.q;
                builder.reset();
                a aVar = this.s;
                String str = ((c) aVar).a.x;
                int i = ((c) aVar).a.v;
                int i2 = ((c) aVar).a.w;
                int i3 = ((c) aVar).a.y;
                int i4 = ((c) aVar).a.z;
                if (str != null && i3 >= 0 && i4 <= str.length()) {
                    builder.setComposingText(i3, str.subSequence(i3, i4));
                    float[] fArr = this.d;
                    if (fArr != null) {
                        int length = fArr.length / 4;
                        for (int i5 = 0; i5 < length; i5++) {
                            int i6 = i5 * 4;
                            this.q.addCharacterBounds(i3 + i5, fArr[i6], fArr[i6 + 1], fArr[i6 + 2], fArr[i6 + 3], 1);
                        }
                    }
                }
                builder.setSelectionRange(i, i2);
                Matrix matrix = this.o;
                float f = this.f;
                matrix.setScale(f, f);
                matrix.postTranslate(this.g, this.h);
                builder.setMatrix(matrix);
                if (this.i) {
                    CursorAnchorInfo.Builder builder2 = this.q;
                    float f2 = this.k;
                    float f3 = this.l;
                    float f4 = this.m;
                    builder2.setInsertionMarkerLocation(f2, f3, f4, f4, this.j ? 1 : 2);
                }
                this.n = builder.build();
            }
            h01 h01Var = this.r;
            if (h01Var != null) {
                h01Var.c(view, this.n);
            }
            this.b = false;
        }
    }
}
