package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import defpackage.xt2;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: StatusViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cu2 implements j.a<PropertyModel, StatusView, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        StatusView statusView = (StatusView) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.o oVar = xt2.a;
        if (oVar.equals(v62Var)) {
            statusView.setAlpha(propertyModel.g(oVar));
            return;
        }
        PropertyModel.n nVar = xt2.b;
        if (nVar.equals(v62Var)) {
            statusView.setAnimationsEnabled(propertyModel.k(nVar));
            return;
        }
        PropertyModel.n nVar2 = xt2.c;
        if (nVar2.equals(v62Var)) {
            statusView.setIncognitoBadgeVisibility(propertyModel.k(nVar2));
            return;
        }
        PropertyModel.p pVar = xt2.d;
        if (pVar.equals(v62Var)) {
            statusView.setSeparatorColor(propertyModel.h(pVar));
            return;
        }
        PropertyModel.n nVar3 = xt2.e;
        if (nVar3.equals(v62Var)) {
            statusView.setStatusIconShown(propertyModel.k(nVar3));
            return;
        }
        PropertyModel.n nVar4 = xt2.f;
        if (nVar4.equals(v62Var)) {
            statusView.setStatusIconBackgroundVisibility(propertyModel.k(nVar4));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar = xt2.g;
        if (rVar.equals(v62Var)) {
            statusView.setStatusClickListener((View.OnClickListener) propertyModel.j(rVar));
            return;
        }
        PropertyModel.p pVar2 = xt2.h;
        if (pVar2.equals(v62Var)) {
            statusView.setStatusAccessibilityToast(propertyModel.h(pVar2));
            return;
        }
        PropertyModel.p pVar3 = xt2.i;
        if (pVar3.equals(v62Var)) {
            statusView.setStatusAccessibilityDoubleTapDescription(propertyModel.h(pVar3));
            return;
        }
        PropertyModel.o oVar2 = xt2.j;
        if (oVar2.equals(v62Var)) {
            statusView.setStatusIconAlpha(propertyModel.g(oVar2));
            return;
        }
        PropertyModel.p pVar4 = xt2.k;
        if (pVar4.equals(v62Var)) {
            statusView.setStatusIconDescription(propertyModel.h(pVar4));
            return;
        }
        PropertyModel.r<xt2.b> rVar2 = xt2.l;
        if (rVar2.equals(v62Var)) {
            xt2.b bVar = (xt2.b) propertyModel.j(rVar2);
            if (bVar == null) {
                statusView.t = null;
                statusView.c(0, null);
                return;
            }
            Drawable drawableA = bVar.a(statusView.getContext(), statusView.getResources());
            int i = bVar.e;
            Runnable runnable = bVar.f;
            statusView.t = drawableA;
            statusView.c(i, runnable);
            return;
        }
        PropertyModel.p pVar5 = xt2.m;
        if (pVar5.equals(v62Var)) {
            statusView.setVerboseStatusTextColor(propertyModel.h(pVar5));
            return;
        }
        PropertyModel.p pVar6 = xt2.n;
        if (pVar6.equals(v62Var)) {
            statusView.setVerboseStatusTextContent(propertyModel.h(pVar6));
            return;
        }
        PropertyModel.n nVar5 = xt2.o;
        if (nVar5.equals(v62Var)) {
            statusView.setVerboseStatusTextVisible(propertyModel.k(nVar5));
            return;
        }
        PropertyModel.p pVar7 = xt2.p;
        if (pVar7.equals(v62Var)) {
            statusView.setVerboseStatusTextWidth(propertyModel.h(pVar7));
        }
    }
}
