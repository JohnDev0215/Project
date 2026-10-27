package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.PathInterpolator;
import defpackage.aq1;
import defpackage.in3;
import defpackage.om3;
import defpackage.ot3;
import defpackage.pt3;
import defpackage.wr1;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes2.dex */
public class InsetObserverView extends View {
    public static final /* synthetic */ int k = 0;
    public final Rect e;
    public final org.chromium.base.b<d> f;
    public final b g;
    public final a h;
    public final ArrayList i;
    public final ArrayList j;

    public class a extends ot3.b {
        public a() {
        }
    }

    public static class c extends InsetObserverView {
        public final Rect l;

        public c(Context context) {
            super(context);
            this.l = new Rect();
        }

        @Override // org.chromium.components.browser_ui.widget.InsetObserverView, android.view.View
        public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
            int safeInsetLeft;
            int safeInsetBottom;
            int safeInsetTop;
            int safeInsetRight;
            DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            if (displayCutout != null) {
                safeInsetLeft = displayCutout.getSafeInsetLeft();
                safeInsetTop = displayCutout.getSafeInsetTop();
                safeInsetRight = displayCutout.getSafeInsetRight();
                safeInsetBottom = displayCutout.getSafeInsetBottom();
            } else {
                safeInsetLeft = 0;
                safeInsetBottom = 0;
                safeInsetTop = 0;
                safeInsetRight = 0;
            }
            Rect rect = this.l;
            if (rect.left != safeInsetLeft || rect.top != safeInsetTop || rect.right != safeInsetRight || rect.bottom != safeInsetBottom) {
                rect.set(safeInsetLeft, safeInsetTop, safeInsetRight, safeInsetBottom);
                Iterator<d> it = this.f.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    }
                    ((d) aVar.next()).m(rect);
                }
            }
            return super.onApplyWindowInsets(windowInsets);
        }
    }

    public interface d {
        void a(int i);

        void m(Rect rect);
    }

    public interface e {
        void a();

        void b();

        void c(ot3 ot3Var);

        void d(ot3 ot3Var);
    }

    public interface f extends wr1 {
    }

    public InsetObserverView(Context context) {
        super(context);
        this.i = new ArrayList();
        this.j = new ArrayList();
        setVisibility(4);
        this.e = new Rect();
        org.chromium.base.b<d> bVar = new org.chromium.base.b<>();
        this.f = bVar;
        b bVar2 = new b();
        this.g = bVar2;
        bVar.a(bVar2);
        a aVar = new a();
        this.h = aVar;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (Build.VERSION.SDK_INT >= 30) {
            setWindowInsetsAnimationCallback(new ot3.d.a(aVar));
            return;
        }
        PathInterpolator pathInterpolator = ot3.c.e;
        Object tag = getTag(androidx.core.R$id.tag_on_apply_window_listener);
        View.OnApplyWindowInsetsListener aVar2 = new ot3.c.a(this, aVar);
        setTag(androidx.core.R$id.tag_window_insets_animation_callback, aVar2);
        if (tag == null) {
            setOnApplyWindowInsetsListener(aVar2);
        }
    }

    public ot3.b getInsetAnimationProxyCallbackForTesting() {
        return this.h;
    }

    public zp1<Integer> getSupplierForBottomInset() {
        return this.g;
    }

    public int getSystemWindowInsetsBottom() {
        return this.e.bottom;
    }

    public int getSystemWindowInsetsLeft() {
        return this.e.left;
    }

    public int getSystemWindowInsetsRight() {
        return this.e.right;
    }

    public int getSystemWindowInsetsTop() {
        return this.e.top;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        pt3 pt3VarI = pt3.i(this, windowInsets);
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            pt3VarI = ((f) it.next()).k(this, pt3VarI);
        }
        WindowInsets windowInsetsH = pt3VarI.h();
        int systemWindowInsetLeft = windowInsetsH.getSystemWindowInsetLeft();
        int systemWindowInsetTop = windowInsetsH.getSystemWindowInsetTop();
        int systemWindowInsetRight = windowInsetsH.getSystemWindowInsetRight();
        int systemWindowInsetBottom = windowInsetsH.getSystemWindowInsetBottom();
        Rect rect = this.e;
        if (rect.left != systemWindowInsetLeft || rect.top != systemWindowInsetTop || rect.right != systemWindowInsetRight || rect.bottom != systemWindowInsetBottom) {
            rect.set(systemWindowInsetLeft, systemWindowInsetTop, systemWindowInsetRight, systemWindowInsetBottom);
            Iterator<d> it2 = this.f.iterator();
            while (true) {
                b.a aVar = (b.a) it2;
                if (!aVar.hasNext()) {
                    break;
                }
                ((d) aVar.next()).a(systemWindowInsetBottom);
            }
        }
        return windowInsetsH;
    }

    public class b extends aq1<Integer> implements d {
        @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
        public final void a(int i) {
            r(Integer.valueOf(i));
        }

        @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
        public final void m(Rect rect) {
        }
    }
}
