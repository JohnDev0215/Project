package org.chromium.chrome.browser.toolbar.top;

import J.N;
import android.view.View;
import android.view.ViewStub;
import defpackage.a8;
import defpackage.af1;
import defpackage.bn0;
import defpackage.bq;
import defpackage.bz0;
import defpackage.hz0;
import defpackage.ke0;
import defpackage.sk;
import defpackage.y13;
import java.util.function.BooleanSupplier;

/* JADX INFO: compiled from: TabSwitcherModeTTCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {
    public final ViewStub a;
    public View.OnClickListener c;
    public View.OnClickListener d;
    public org.chromium.chrome.browser.toolbar.a e;
    public y13 f;
    public bz0 g;
    public af1 h;
    public boolean i;
    public CustomTabSwitcherModeTopToolbar j;
    public CustomTabSwitcherModeTopToolbar k;
    public CustomTabSwitcherModeTopToolbar l;
    public final i m;
    public a n;
    public final boolean o;
    public final boolean p;
    public final BooleanSupplier q;
    public ViewStub b = null;
    public final n r = new n(new ke0(25, this));

    /* JADX INFO: compiled from: TabSwitcherModeTTCoordinator.java */
    public class a implements hz0 {
        public a() {
        }

        @Override // defpackage.hz0
        public final void f() {
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = h.this.j;
            if (customTabSwitcherModeTopToolbar != null) {
                customTabSwitcherModeTopToolbar.l(true);
            }
        }

        @Override // defpackage.hz0
        public final void q() {
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = h.this.j;
            if (customTabSwitcherModeTopToolbar != null) {
                customTabSwitcherModeTopToolbar.l(false);
            }
        }
    }

    public h(ViewStub viewStub, af1 af1Var, boolean z, boolean z2, bn0 bn0Var, i iVar) {
        this.a = viewStub;
        this.h = af1Var;
        this.o = z;
        this.p = z2;
        this.q = bn0Var;
        this.m = iVar;
    }

    public final void a(CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar, boolean z) {
        customTabSwitcherModeTopToolbar.j(this.o, z, this.p, this.q, this.m);
        customTabSwitcherModeTopToolbar.setOnTabSwitcherClickHandler(this.c);
        customTabSwitcherModeTopToolbar.setOnNewTabClickHandler(this.d);
        customTabSwitcherModeTopToolbar.setTabCountProvider(this.e);
        customTabSwitcherModeTopToolbar.setTabModelSelector(this.f);
        customTabSwitcherModeTopToolbar.setIncognitoStateProvider(this.g);
        if (this.i) {
            customTabSwitcherModeTopToolbar.n();
        }
    }

    public final boolean b() {
        if (this.o && a8.P() && this.q.getAsBoolean()) {
            sk skVar = bq.a;
            if (!N.MMltG$kc("TabGridLayoutAndroid", "tab_grid_layout_android_new_tab").equals("false")) {
                return true;
            }
        }
        return false;
    }

    public final void c() {
        if (this.f == null || this.j == null || !b() || this.n != null) {
            return;
        }
        a aVar = new a();
        this.n = aVar;
        this.f.h(aVar);
    }

    public final void d() {
        if (this.f == null || this.j == null || !b()) {
            return;
        }
        this.j.l(this.f.g(true).getCount() != 0);
    }

    public void setOnNewTabClickHandler(View.OnClickListener onClickListener) {
        this.d = onClickListener;
        CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = this.j;
        if (customTabSwitcherModeTopToolbar != null) {
            customTabSwitcherModeTopToolbar.setOnNewTabClickHandler(onClickListener);
        }
    }

    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
        this.c = onClickListener;
        CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = this.j;
        if (customTabSwitcherModeTopToolbar != null) {
            customTabSwitcherModeTopToolbar.setOnTabSwitcherClickHandler(onClickListener);
        }
    }
}
