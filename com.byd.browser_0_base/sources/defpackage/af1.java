package defpackage;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.View;
import defpackage.df1;
import java.util.HashMap;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.i;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: MenuButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class af1 {
    public final Activity a;
    public final PropertyModel b;
    public cf1 c;
    public z6 d;
    public MenuButton e;
    public j f;

    /* JADX INFO: compiled from: MenuButtonCoordinator.java */
    public interface a {
        void u();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, df1$a] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, df1$b] */
    public af1(rs1 rs1Var, vj vjVar, g3 g3Var, a aVar, ww wwVar, boolean z, nw2 nw2Var, ThemeColorProvider themeColorProvider, zq2 zq2Var, wz wzVar, int i) {
        Activity activity = (Activity) g3Var.k().get();
        this.a = activity;
        this.e = (MenuButton) activity.findViewById(i);
        HashMap mapE = PropertyModel.e(df1.k);
        PropertyModel.r<df1.a> rVar = df1.h;
        ?? aVar2 = new df1.a(false, false);
        int i2 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = aVar2;
        mapE.put(rVar, gVar);
        PropertyModel.r<df1.b> rVar2 = df1.i;
        ColorStateList colorStateList = themeColorProvider.g;
        Integer num = themeColorProvider.f;
        ?? bVar = new df1.b(num != null ? num.intValue() : 3, colorStateList);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = bVar;
        mapE.put(rVar2, gVar2);
        PropertyModel.n nVar = df1.f;
        PropertyModel.a aVar3 = new PropertyModel.a();
        aVar3.a = true;
        mapE.put(nVar, aVar3);
        PropertyModel.r<nw2<ef1>> rVar3 = df1.g;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = zq2Var;
        mapE.put(rVar3, gVar3);
        PropertyModel propertyModel = new PropertyModel(mapE);
        this.b = propertyModel;
        cf1 cf1Var = new cf1(propertyModel, z, new p11(12, this), wwVar, themeColorProvider, nw2Var, vjVar, aVar, rs1Var, g3Var, zq2Var, wzVar);
        this.c = cf1Var;
        cf1Var.c.p(new dr(16, this));
        MenuButton menuButton = this.e;
        if (menuButton != null) {
            this.f = j.b(propertyModel, menuButton, new ff1());
        }
    }

    public final void a() {
        cf1 cf1Var = this.c;
        if (cf1Var != null) {
            if (cf1Var.b != null) {
                ((i7) cf1Var.d).h.remove(cf1Var);
                cf1Var.b = null;
            }
            this.c = null;
        }
        j jVar = this.f;
        if (jVar != null) {
            jVar.c();
            this.f = null;
        }
        this.e = null;
        this.d = null;
    }

    public final AnimatorSet b(boolean z) {
        float f;
        cf1 cf1Var = this.c;
        MenuButton menuButton = this.e;
        boolean z2 = menuButton != null && menuButton.getLayoutDirection() == 1;
        float f2 = 0.0f;
        if (z) {
            float f3 = cf1Var.q.getDisplayMetrics().density;
            int i = cf1Var.t;
            if (z2) {
                i = -i;
            }
            f2 = i * f3;
            f = 0.0f;
        } else {
            cf1Var.getClass();
            f = 1.0f;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        PropertyModel.o oVar = df1.j;
        PropertyModel propertyModel = cf1Var.g;
        animatorSet.playTogether(i.a(propertyModel, oVar, f2), i.a(propertyModel, df1.a, f));
        return animatorSet;
    }

    public final void c(boolean z) {
        cf1 cf1Var = this.c;
        if (cf1Var == null) {
            return;
        }
        cf1Var.g.l(df1.f, z);
    }

    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        MenuButton menuButton = this.e;
        if (menuButton == null) {
            return;
        }
        menuButton.setOnKeyListener(onKeyListener);
    }
}
