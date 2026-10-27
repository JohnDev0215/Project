package defpackage;

import J.N;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.accessibility.R$anim;
import org.chromium.components.browser_ui.accessibility.R$dimen;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PageZoomCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cv1 {
    public final dv1 a;
    public final PropertyModel b;
    public final fv1 c;
    public bv1 d;
    public int e;
    public final d92 f;
    public View g;
    public Profile h;

    public cv1(mc2 mc2Var) {
        this.a = mc2Var;
        PropertyModel propertyModel = new PropertyModel(PropertyModel.e(gv1.j));
        this.b = propertyModel;
        int i = 23;
        propertyModel.p(gv1.d, new x10(i, this));
        this.c = new fv1(propertyModel);
        this.f = new d92(i, this);
    }

    public final void a() {
        View view = this.g;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.g.getContext(), R$anim.slide_out_child_bottom);
        animationLoadAnimation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
        this.g.startAnimation(animationLoadAnimation);
        this.g.setVisibility(8);
        fv1 fv1Var = this.c;
        double d = fv1Var.c;
        if (d != 0.0d) {
            N.MQpBMdiz(fv1Var.b, d);
            int i = hv1.a;
            oj2.O("Accessibility.Android.PageZoom.AppMenuSliderZoomLevelChanged", true);
            oj2.W((int) Math.round(d * 100.0d), 50, 300, hv1.a, "Accessibility.Android.PageZoom.AppMenuSliderZoomLevelValue");
        }
    }

    public final void b(int i) {
        View view = this.g;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, this.g.getContext().getResources().getDimensionPixelSize(R$dimen.page_zoom_view_margins) + i);
        }
    }
}
