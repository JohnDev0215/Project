package defpackage;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import defpackage.kj;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tc3 implements kj.a {
    public ga1 e;
    public final /* synthetic */ g f;

    public tc3(g gVar) {
        this.f = gVar;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        g gVar = this.f;
        Drawable background = gVar.l.getBackground();
        ToolbarControlContainer toolbarControlContainer = gVar.l;
        if (background != null) {
            if (this.e == null) {
                ga1 ga1Var = new ga1(2, this);
                this.e = ga1Var;
                toolbarControlContainer.addOnLayoutChangeListener(ga1Var);
                return;
            }
            return;
        }
        int iA = gVar.X.a();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) toolbarControlContainer.getLayoutParams();
        if (marginLayoutParams.topMargin == iA) {
            return;
        }
        marginLayoutParams.topMargin = iA;
        toolbarControlContainer.setLayoutParams(marginLayoutParams);
    }
}
