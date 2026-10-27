package defpackage;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.chrome.browser.feed.c;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ExploreSurfaceViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ti0 {
    public static void a(ViewGroup viewGroup, PropertyModel propertyModel, boolean z) {
        PropertyModel.r<qi0> rVar = zr2.f;
        if (propertyModel.j(rVar) == null) {
            return;
        }
        c.a aVar = ((qi0) propertyModel.j(rVar)).b.q;
        if (!z) {
            uh3.l(aVar);
            return;
        }
        viewGroup.addView(aVar);
        if (propertyModel.k(zr2.b)) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) aVar.getLayoutParams();
            layoutParams.bottomMargin = propertyModel.h(zr2.a);
            layoutParams.topMargin = propertyModel.h(zr2.g);
        }
    }
}
