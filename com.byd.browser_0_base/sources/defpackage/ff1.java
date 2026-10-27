package defpackage;

import defpackage.df1;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: MenuButtonViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ff1 implements j.a<PropertyModel, MenuButton, v62> {
    public boolean a;

    @Override // org.chromium.ui.modelutil.j.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void b(PropertyModel propertyModel, MenuButton menuButton, v62 v62Var) {
        PropertyModel.o oVar = df1.a;
        if (v62Var == oVar) {
            menuButton.setAlpha(propertyModel.g(oVar));
            return;
        }
        PropertyModel.r<z6> rVar = df1.b;
        if (v62Var == rVar) {
            menuButton.setAppMenuButtonHelper((z6) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<String> rVar2 = df1.c;
        if (v62Var == rVar2) {
            menuButton.e.setContentDescription((String) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.n nVar = df1.d;
        if (v62Var == nVar) {
            menuButton.setClickable(propertyModel.k(nVar));
            return;
        }
        PropertyModel.n nVar2 = df1.e;
        if (v62Var == nVar2) {
            menuButton.setMenuButtonHighlight(propertyModel.k(nVar2));
            return;
        }
        PropertyModel.n nVar3 = df1.f;
        if (v62Var == nVar3) {
            menuButton.setVisibility(propertyModel.k(nVar3) ? 0 : 8);
            return;
        }
        PropertyModel.r<nw2<ef1>> rVar3 = df1.g;
        if (v62Var == rVar3) {
            if (this.a) {
                return;
            }
            menuButton.setStateSupplier((nw2) propertyModel.j(rVar3));
            this.a = true;
            return;
        }
        PropertyModel.r<df1.a> rVar4 = df1.h;
        if (v62Var == rVar4) {
            b(propertyModel, menuButton, rVar3);
            df1.a aVar = (df1.a) propertyModel.j(rVar4);
            boolean z = aVar.a;
            boolean z2 = aVar.b;
            if (z) {
                menuButton.b(z2);
                return;
            } else {
                menuButton.a(z2);
                return;
            }
        }
        PropertyModel.r<df1.b> rVar5 = df1.i;
        if (v62Var == rVar5) {
            b(propertyModel, menuButton, rVar3);
            df1.b bVar = (df1.b) propertyModel.j(rVar5);
            menuButton.e(bVar.b, bVar.a);
        } else {
            PropertyModel.o oVar2 = df1.j;
            if (v62Var == oVar2) {
                menuButton.setTranslationX(propertyModel.g(oVar2));
            }
        }
    }
}
