package defpackage;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.r12;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: VideoPlayerViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dm3 implements j.a<PropertyModel, cm3, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        cm3 cm3Var = (cm3) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.n nVar = bm3.a;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            cm3Var.getClass();
            cm3Var.c.setVisibility(zK ? 0 : 8);
            return;
        }
        PropertyModel.n nVar2 = bm3.b;
        if (v62Var == nVar2) {
            boolean zK2 = propertyModel.k(nVar2);
            cm3Var.getClass();
            cm3Var.e.setVisibility(zK2 ? 0 : 8);
            return;
        }
        PropertyModel.n nVar3 = bm3.c;
        if (v62Var == nVar3) {
            cm3Var.a.findViewById(R$id.try_now).setVisibility(propertyModel.k(nVar3) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar4 = bm3.d;
        if (v62Var == nVar4) {
            cm3Var.a.findViewById(R$id.share_button).setVisibility(propertyModel.k(nVar4) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar5 = bm3.e;
        if (v62Var == nVar5) {
            cm3Var.a.findViewById(R$id.close_button).setVisibility(propertyModel.k(nVar5) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar6 = bm3.f;
        if (v62Var == nVar6) {
            cm3Var.a.findViewById(R$id.watch_next).setVisibility(propertyModel.k(nVar6) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar7 = bm3.g;
        if (v62Var == nVar7) {
            cm3Var.a.findViewById(R$id.change_language).setVisibility(propertyModel.k(nVar7) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar8 = bm3.h;
        if (v62Var == nVar8) {
            cm3Var.a.findViewById(R$id.play_button).setVisibility(propertyModel.k(nVar8) ? 0 : 8);
            return;
        }
        PropertyModel.r<String> rVar = bm3.j;
        if (v62Var == rVar) {
            ((TextView) cm3Var.a.findViewById(R$id.change_language)).setText((CharSequence) propertyModel.j(rVar));
            return;
        }
        if (v62Var == bm3.o) {
            cm3Var.a.findViewById(R$id.close_button).setOnClickListener(new cy0(7, propertyModel));
            return;
        }
        if (v62Var == bm3.n) {
            cm3Var.a.findViewById(R$id.share_button).setOnClickListener(new ij3(10, propertyModel));
            return;
        }
        if (v62Var == bm3.k) {
            cm3Var.a.findViewById(R$id.watch_next).setOnClickListener(new kg(8, propertyModel));
            return;
        }
        if (v62Var == bm3.m) {
            cm3Var.a.findViewById(R$id.try_now).setOnClickListener(new rg1(3, propertyModel));
            return;
        }
        if (v62Var == bm3.l) {
            cm3Var.a.findViewById(R$id.change_language).setOnClickListener(new cy0(8, propertyModel));
            return;
        }
        if (v62Var == bm3.i) {
            cm3Var.a.findViewById(R$id.play_button).setOnClickListener(new ij3(11, propertyModel));
            return;
        }
        PropertyModel.r<r12.b.a> rVar2 = bm3.p;
        if (v62Var == rVar2) {
            r12.b.a aVar = (r12.b.a) propertyModel.j(rVar2);
            int i = R$id.top_half;
            View view = cm3Var.d;
            View viewFindViewById = view.findViewById(i);
            View viewFindViewById2 = view.findViewById(R$id.bottom_half);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewFindViewById2.getLayoutParams();
            if (aVar == r12.b.a.PAUSED) {
                layoutParams.weight = 0.5f;
                layoutParams2.weight = 0.5f;
            } else if (aVar == r12.b.a.ENDED) {
                layoutParams.weight = 0.62f;
                layoutParams2.weight = 0.38f;
            }
        }
    }
}
