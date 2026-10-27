package defpackage;

import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StartSurfaceWithParentViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class es2 {

    /* JADX INFO: compiled from: StartSurfaceWithParentViewBinder.java */
    public static class a {
        public final ViewGroup a;
        public final View b;
        public final ViewGroup c;

        public a(ViewGroup viewGroup, View view, FeedSwipeRefreshLayout feedSwipeRefreshLayout) {
            this.a = viewGroup;
            this.b = view;
            this.c = feedSwipeRefreshLayout;
        }
    }

    public static void a(PropertyModel propertyModel, a aVar, v62 v62Var) {
        PropertyModel.n nVar = zr2.e;
        if (nVar != v62Var) {
            PropertyModel.p pVar = zr2.a;
            if (pVar == v62Var) {
                int iH = propertyModel.h(pVar);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) aVar.b.getLayoutParams();
                if (marginLayoutParams != null) {
                    marginLayoutParams.bottomMargin = iH;
                    aVar.b.setLayoutParams(marginLayoutParams);
                    return;
                }
                return;
            }
            PropertyModel.p pVar2 = zr2.g;
            if (pVar2 == v62Var) {
                int iH2 = propertyModel.h(pVar2);
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) aVar.b.getLayoutParams();
                if (marginLayoutParams2 == null) {
                    return;
                }
                marginLayoutParams2.topMargin = iH2;
                aVar.b.setLayoutParams(marginLayoutParams2);
                return;
            }
            return;
        }
        boolean zK = propertyModel.k(nVar);
        if (zK && aVar.b.getParent() == null) {
            ViewGroup viewGroup = aVar.a;
            int i = viewGroup.getChildCount() > 0 ? 1 : 0;
            View view = aVar.b;
            viewGroup.addView(view, i);
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams3 != null) {
                marginLayoutParams3.bottomMargin = propertyModel.h(zr2.a);
            }
            int iH3 = propertyModel.h(zr2.g);
            ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams4 != null) {
                marginLayoutParams4.topMargin = iH3;
                view.setLayoutParams(marginLayoutParams4);
            }
        }
        View view2 = aVar.b;
        ViewGroup viewGroup2 = aVar.c;
        if (!zK) {
            view2.setVisibility(8);
            if (viewGroup2 != null) {
                viewGroup2.setVisibility(8);
                return;
            }
            return;
        }
        if (viewGroup2 != null && viewGroup2.getVisibility() == 8) {
            viewGroup2.setVisibility(0);
        }
        view2.setAlpha(0.0f);
        view2.setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(50L);
        objectAnimatorOfFloat.start();
    }
}
