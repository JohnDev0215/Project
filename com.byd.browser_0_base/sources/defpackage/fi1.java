package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: ModalDialogViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public class fi1 implements j.a<PropertyModel, ModalDialogView, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(PropertyModel propertyModel, ModalDialogView modalDialogView, v62 v62Var) {
        PropertyModel.r<String> rVar = di1.c;
        if (rVar == v62Var) {
            modalDialogView.setTitle((CharSequence) propertyModel.j(rVar));
            return;
        }
        PropertyModel.p pVar = di1.d;
        if (pVar == v62Var) {
            modalDialogView.setTitleMaxLines(propertyModel.h(pVar));
            return;
        }
        PropertyModel.r<Drawable> rVar2 = di1.e;
        if (rVar2 == v62Var) {
            modalDialogView.setTitleIcon((Drawable) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<CharSequence> rVar3 = di1.f;
        if (rVar3 == v62Var) {
            modalDialogView.setMessageParagraph1((CharSequence) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.r<CharSequence> rVar4 = di1.g;
        if (rVar4 == v62Var) {
            modalDialogView.setMessageParagraph2((CharSequence) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.r<View> rVar5 = di1.h;
        if (rVar5 == v62Var) {
            modalDialogView.setCustomView((View) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.r<View> rVar6 = di1.i;
        if (rVar6 == v62Var) {
            modalDialogView.setCustomButtonBar((View) propertyModel.j(rVar6));
            return;
        }
        PropertyModel.r<String> rVar7 = di1.j;
        if (rVar7 == v62Var) {
            modalDialogView.a(0).setText((String) propertyModel.j(rVar7));
            modalDialogView.b();
            return;
        }
        PropertyModel.r<String> rVar8 = di1.k;
        if (rVar8 == v62Var) {
            modalDialogView.a(0).setContentDescription((String) propertyModel.j(rVar8));
            return;
        }
        if (di1.l == v62Var) {
            modalDialogView.a(0).setEnabled(!propertyModel.k(r3));
            return;
        }
        PropertyModel.r<String> rVar9 = di1.m;
        if (rVar9 == v62Var) {
            modalDialogView.a(1).setText((String) propertyModel.j(rVar9));
            modalDialogView.b();
            return;
        }
        PropertyModel.r<String> rVar10 = di1.n;
        if (rVar10 == v62Var) {
            modalDialogView.a(1).setContentDescription((String) propertyModel.j(rVar10));
            return;
        }
        if (di1.o == v62Var) {
            modalDialogView.a(1).setEnabled(!propertyModel.k(r3));
            return;
        }
        PropertyModel.r<CharSequence> rVar11 = di1.p;
        if (rVar11 == v62Var) {
            modalDialogView.setFooterMessage((CharSequence) propertyModel.j(rVar11));
            return;
        }
        PropertyModel.n nVar = di1.t;
        if (nVar == v62Var) {
            modalDialogView.setTitleScrollable(propertyModel.k(nVar));
            return;
        }
        if (di1.a == v62Var) {
            modalDialogView.setOnButtonClickedCallback(new aq3(3, propertyModel));
            return;
        }
        if (di1.q == v62Var) {
            return;
        }
        PropertyModel.h hVar = di1.r;
        if (hVar == v62Var) {
            modalDialogView.setFilterTouchForSecurity(propertyModel.k(hVar));
            return;
        }
        PropertyModel.l<Runnable> lVar = di1.s;
        if (lVar == v62Var) {
            modalDialogView.setOnTouchFilteredCallback((Runnable) propertyModel.j(lVar));
            return;
        }
        if (di1.b == v62Var || di1.u == v62Var) {
            return;
        }
        PropertyModel.h hVar2 = di1.v;
        if (hVar2 != v62Var && di1.w != v62Var && di1.y != v62Var) {
            PropertyModel.q qVar = di1.A;
            if (qVar == v62Var) {
                modalDialogView.setButtonTapProtectionDurationMs(propertyModel.i(qVar));
                return;
            }
            return;
        }
        boolean z = propertyModel.k(hVar2) || propertyModel.k(di1.w);
        boolean zK = propertyModel.k(di1.y);
        modalDialogView.h = z;
        modalDialogView.i = zK;
        ao3.e(modalDialogView, "BoundedLinearLayout.setIgnoreConstraints");
    }
}
