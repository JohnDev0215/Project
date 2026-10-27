package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import defpackage.di1;
import org.chromium.chrome.browser.tasks.tab_management.TabGridIphDialogView;
import org.chromium.chrome.browser.tasks.tab_management.o;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabGridIphDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c03 implements di1.a {
    public final /* synthetic */ bi1 e;
    public final /* synthetic */ o f;

    public c03(o oVar, bi1 bi1Var) {
        this.f = oVar;
        this.e = bi1Var;
    }

    @Override // di1.a
    public final void b(int i) {
        TabGridIphDialogView tabGridIphDialogView = this.f.b;
        Drawable drawable = tabGridIphDialogView.l;
        TabGridIphDialogView.a aVar = tabGridIphDialogView.n;
        int i2 = p5.k;
        if (drawable != null && aVar != null && (drawable instanceof Animatable)) {
            ((AnimatedVectorDrawable) drawable).unregisterAnimationCallback(aVar.a());
        }
        tabGridIphDialogView.m.stop();
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            this.e.b(1, propertyModel);
        }
    }
}
