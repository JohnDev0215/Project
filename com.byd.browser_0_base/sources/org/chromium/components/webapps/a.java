package org.chromium.components.webapps;

import android.view.View;
import defpackage.ao3;

/* JADX INFO: compiled from: AddToHomescreenDialogView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements View.OnLayoutChangeListener {
    public final /* synthetic */ c e;

    public a(c cVar) {
        this.e = cVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        c cVar = this.e;
        if (cVar.n.getMeasuredHeight() != cVar.h.getMeasuredHeight() || cVar.h.getBackground() == null) {
            return;
        }
        cVar.h.getLayoutParams().height = cVar.h.getPaddingBottom() + cVar.n.getMeasuredHeight();
        ao3.e(view, "AddToHomescreenDialogView.<init>.OnLayoutChangeListener.onLayoutChange");
        view.removeOnLayoutChangeListener(this);
    }
}
