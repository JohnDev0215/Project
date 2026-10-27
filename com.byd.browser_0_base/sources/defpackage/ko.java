package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import org.chromium.components.browser_ui.widget.R$drawable;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ChipViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ko {
    public static void a(PropertyModel propertyModel, ChipView chipView, v62 v62Var) {
        if (ho.a == v62Var) {
            chipView.setOnClickListener(new kg(10, propertyModel));
            return;
        }
        PropertyModel.r<String> rVar = ho.b;
        if (rVar == v62Var) {
            chipView.getPrimaryTextView().setContentDescription((CharSequence) propertyModel.j(rVar));
            return;
        }
        PropertyModel.n nVar = ho.c;
        if (nVar == v62Var) {
            chipView.setEnabled(propertyModel.k(nVar));
            return;
        }
        PropertyModel.p pVar = ho.d;
        if (pVar == v62Var || ho.e == v62Var) {
            int iH = propertyModel.h(pVar);
            boolean z = false;
            if (iH == -1) {
                chipView.a(-1, false);
                return;
            }
            ArrayList arrayListB = propertyModel.b();
            PropertyModel.n nVar2 = ho.g;
            if (arrayListB.contains(nVar2) && propertyModel.k(nVar2)) {
                z = true;
            }
            if (z) {
                iH = R$drawable.ic_check_googblue_24dp;
            }
            chipView.a(iH, propertyModel.k(ho.e));
            return;
        }
        if (ho.f == v62Var) {
            return;
        }
        PropertyModel.n nVar3 = ho.g;
        if (nVar3 == v62Var) {
            chipView.setSelected(propertyModel.k(nVar3));
            return;
        }
        PropertyModel.l<String> lVar = ho.h;
        if (lVar == v62Var) {
            chipView.getPrimaryTextView().setText((CharSequence) propertyModel.j(lVar));
            return;
        }
        PropertyModel.p pVar2 = ho.i;
        if (pVar2 == v62Var) {
            int iH2 = propertyModel.h(pVar2);
            if (iH2 == 0) {
                chipView.getPrimaryTextView().setEllipsize(null);
                chipView.getPrimaryTextView().setMaxWidth(Integer.MAX_VALUE);
            } else {
                chipView.getPrimaryTextView().setEllipsize(TextUtils.TruncateAt.END);
                chipView.getPrimaryTextView().setMaxWidth(iH2);
            }
        }
    }
}
