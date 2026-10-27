package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.MenuItem;
import android.view.View;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AppMenuUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r7 {
    public static void a(PropertyModel propertyModel, View view, v62 v62Var) {
        PropertyModel.n nVar = j7.k;
        if (v62Var == nVar) {
            if (!propertyModel.k(nVar)) {
                view.setTag(R$id.menu_item_enter_anim_id, null);
                return;
            }
            int iH = propertyModel.h(j7.j);
            int i = R$id.menu_item_enter_anim_id;
            float f = view.getContext().getResources().getDisplayMetrics().density;
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, f * (-10.0f), 0.0f));
            animatorSet.setStartDelay((iH * 30) + 80);
            animatorSet.setDuration(350L);
            animatorSet.setInterpolator(pc.e);
            animatorSet.addListener(new p7(view));
            view.setTag(i, animatorSet);
        }
    }

    public static PropertyModel b(MenuItem menuItem) {
        PropertyModel.b bVar = new PropertyModel.b(j7.o);
        bVar.c(j7.a, menuItem.getItemId());
        bVar.e(j7.b, menuItem.getTitle());
        bVar.e(j7.c, menuItem.getTitleCondensed());
        bVar.e(j7.h, menuItem.getIcon());
        bVar.b(j7.f, menuItem.isCheckable());
        bVar.b(j7.g, menuItem.isChecked());
        bVar.b(j7.d, menuItem.isEnabled());
        return bVar.a();
    }
}
