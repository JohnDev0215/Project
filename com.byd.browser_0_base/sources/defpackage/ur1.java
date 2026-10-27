package defpackage;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewTreeObserver;
import org.chromium.chrome.browser.omnibox.LocationBarLayout;
import org.chromium.chrome.browser.omnibox.suggestions.e;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: OmniboxSuggestionsDropdownEmbedderImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ur1 implements e, View.OnLayoutChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ComponentCallbacks {
    public final WindowAndroid f;
    public final it3 g;
    public final View h;
    public final View i;
    public final Context j;
    public int l;
    public int m;
    public int n;
    public pt3 o;
    public e80 p;
    public final aq1<e.a> e = new aq1<>();
    public final int[] k = new int[2];

    public ur1(g3 g3Var, it3 it3Var, View view, LocationBarLayout locationBarLayout) {
        this.f = g3Var;
        this.g = it3Var;
        this.h = view;
        this.i = locationBarLayout;
        Context context = view.getContext();
        this.j = context;
        context.registerComponentCallbacks(this);
        Configuration configuration = context.getResources().getConfiguration();
        this.m = configuration.smallestScreenWidthDp;
        this.n = configuration.screenHeightDp;
        f();
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final e.a a(ko0 ko0Var) {
        return this.e.p(ko0Var);
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final boolean b() {
        return pr1.e.a() ? this.m >= 600 : DeviceFormFactor.b(this.f);
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final e.a c() {
        return this.e.f;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final it3 d() {
        return this.g;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final void e(ko0 ko0Var) {
        this.e.d(ko0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            r11 = this;
            android.view.View r0 = r11.h
            android.view.View r1 = r0.getRootView()
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r1.findViewById(r2)
            int[] r2 = r11.k
            defpackage.ao3.c(r1, r0, r2)
            r1 = 1
            r1 = r2[r1]
            int r3 = r0.getMeasuredHeight()
            int r6 = r3 + r1
            boolean r1 = r11.b()
            r3 = 0
            if (r1 == 0) goto L4d
            android.view.View r1 = r11.i
            defpackage.ao3.c(r0, r1, r2)
            android.content.Context r4 = r11.j
            boolean r4 = defpackage.pr1.e(r4)
            if (r4 == 0) goto L36
            r0 = r2[r3]
            int r1 = r1.getMeasuredWidth()
            goto L52
        L36:
            int r4 = r0.getMeasuredWidth()
            r5 = r2[r3]
            int r0 = r0.getMeasuredWidth()
            int r1 = r1.getMeasuredWidth()
            int r0 = r0 - r1
            r1 = r2[r3]
            int r0 = r0 - r1
            r10 = r0
            r7 = r4
            r9 = r5
            r5 = r3
            goto L56
        L4d:
            int r1 = r0.getMeasuredWidth()
            r0 = r3
        L52:
            r5 = r0
            r7 = r1
            r9 = r3
            r10 = r9
        L56:
            boolean r0 = defpackage.pr1.a()
            if (r0 == 0) goto L73
            e80 r0 = r11.p
            if (r0 == 0) goto L62
            int r3 = r0.f
        L62:
            org.chromium.ui.base.WindowAndroid r0 = r11.f
            ya0 r0 = r0.h
            int r1 = r11.n
            float r1 = (float) r1
            float r0 = r0.d
            float r1 = r1 * r0
            r0 = 1056964608(0x3f000000, float:0.5)
            float r1 = r1 + r0
            int r0 = (int) r1
            int r0 = r0 - r6
            int r0 = r0 - r3
            goto L74
        L73:
            r0 = -1
        L74:
            r8 = r0
            org.chromium.chrome.browser.omnibox.suggestions.e$a r0 = new org.chromium.chrome.browser.omnibox.suggestions.e$a
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10)
            aq1<org.chromium.chrome.browser.omnibox.suggestions.e$a> r11 = r11.e
            r11.r(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ur1.f():void");
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final void onAttachedToWindow() {
        zp1 zp1Var;
        View view = this.h;
        view.addOnLayoutChangeListener(this);
        this.i.addOnLayoutChangeListener(this);
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        if (pr1.a()) {
            e80 e80Var = new e80(new l10(28, this));
            this.p = e80Var;
            WindowAndroid windowAndroid = this.f;
            if (windowAndroid == null) {
                aj3<m01> aj3Var = m01.j;
                zp1Var = null;
            } else {
                zp1Var = (zp1) m01.j.e(windowAndroid.r);
            }
            InsetObserverView insetObserverView = zp1Var != null ? (InsetObserverView) zp1Var.get() : null;
            e80Var.i = insetObserverView;
            insetObserverView.j.add(e80Var);
            insetObserverView.i.add(e80Var);
        }
        onConfigurationChanged(this.j.getResources().getConfiguration());
        f();
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (i == this.m && this.n == i2) {
            return;
        }
        this.m = i;
        this.n = i2;
        if (pr1.e.a() || pr1.a()) {
            f();
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.e
    public final void onDetachedFromWindow() {
        View view = this.h;
        view.removeOnLayoutChangeListener(this);
        this.i.removeOnLayoutChangeListener(this);
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        e80 e80Var = this.p;
        if (e80Var != null) {
            e80Var.i.j.remove(e80Var);
            e80Var.i.i.remove(e80Var);
            e80Var.g = false;
            e80Var.e = -1;
            e80Var.f = 0;
            e80Var.i = null;
            this.p = null;
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        View view = this.h;
        int[] iArr = this.k;
        view.getLocationInWindow(iArr);
        int i = this.l;
        int i2 = iArr[1];
        boolean z = false;
        boolean z2 = i != i2;
        this.l = i2;
        if (!z2) {
            if (view.getRootWindowInsets() != null) {
                pt3 pt3VarI = pt3.i(view, view.getRootWindowInsets());
                z = !pt3VarI.equals(this.o);
                this.o = pt3VarI;
            }
            if (!z) {
                return;
            }
        }
        f();
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        f();
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
