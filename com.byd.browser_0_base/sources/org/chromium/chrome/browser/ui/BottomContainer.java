package org.chromium.chrome.browser.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import defpackage.kj;
import defpackage.w80;
import defpackage.x10;
import defpackage.x7;
import defpackage.zp1;

/* JADX INFO: loaded from: classes2.dex */
public class BottomContainer extends FrameLayout implements w80, kj.a {
    public final x10 e;
    public kj f;
    public x7 g;
    public zp1<Integer> h;
    public float i;

    public BottomContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new x10(12, this);
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        setTranslationY(this.i);
    }

    @Override // defpackage.w80
    public final void destroy() {
        kj kjVar = this.f;
        if (kjVar == null) {
            return;
        }
        kjVar.s(this);
        x7 x7Var = this.g;
        x10 x10Var = this.e;
        x7Var.d(x10Var);
        this.h.d(x10Var);
    }

    @Override // kj.a
    public final void l(int i) {
        setTranslationY(this.i);
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.i = f;
        super.setTranslationY(this.i + (((this.f.u() - this.f.g()) - ((Integer) this.g.f).intValue()) - this.h.get().intValue()));
    }
}
