package org.chromium.chrome.browser.searchwidget;

import android.R;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ap;
import defpackage.aq1;
import defpackage.bg2;
import defpackage.bi1;
import defpackage.c01;
import defpackage.d70;
import defpackage.dt2;
import defpackage.f2;
import defpackage.g3;
import defpackage.hj0;
import defpackage.i83;
import defpackage.it3;
import defpackage.jg2;
import defpackage.jk1;
import defpackage.jz2;
import defpackage.k53;
import defpackage.lj;
import defpackage.m01;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pd;
import defpackage.pq3;
import defpackage.pr1;
import defpackage.ps;
import defpackage.q11;
import defpackage.qh1;
import defpackage.qm0;
import defpackage.rj3;
import defpackage.rs1;
import defpackage.ry2;
import defpackage.se1;
import defpackage.so3;
import defpackage.td2;
import defpackage.ub;
import defpackage.v42;
import defpackage.vj3;
import defpackage.vn2;
import defpackage.vr1;
import defpackage.wd0;
import defpackage.wp2;
import defpackage.xo;
import defpackage.yf2;
import defpackage.yj;
import defpackage.yp;
import defpackage.zb;
import java.lang.ref.WeakReference;
import java.util.function.BooleanSupplier;
import org.chromium.base.SysUtils;
import org.chromium.chrome.R$anim;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.d;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class SearchActivity extends AsyncInitializationActivity implements wp2.c, ub, vj3, vr1 {
    public static final Object q0 = new Object();
    public static c r0;
    public ViewGroup c0;
    public View d0;
    public boolean e0;
    public String f0;
    public int g0;
    public String h0;
    public byte[] i0;
    public SearchActivityLocationBarLayout j0;
    public org.chromium.chrome.browser.omnibox.c k0;
    public wp2 l0;
    public bg2 m0;
    public TabImpl n0;
    public final aq1<Profile> o0 = new aq1<>();
    public final m01 p0 = new m01();

    public class a extends g3 {
        public a(Context context, vn2 vn2Var, d dVar) {
            super(context, vn2Var, dVar);
        }

        @Override // org.chromium.ui.base.WindowAndroid
        public final bi1 n() {
            return SearchActivity.this.P0();
        }
    }

    public static class c {
    }

    public static c j2() {
        synchronized (q0) {
            if (r0 == null) {
                r0 = new c();
            }
        }
        return r0;
    }

    public static int k2(String str) {
        if (TextUtils.equals(str, "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_VOICE_SEARCH") || TextUtils.equals(str, "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_EXTENDED_VOICE_SEARCH")) {
            return 1;
        }
        return TextUtils.equals(str, "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_LENS_SEARCH") ? 2 : 0;
    }

    @Override // defpackage.ub
    public final boolean C() {
        finish();
        overridePendingTransition(0, R$anim.activity_close_exit);
        return true;
    }

    @Override // defpackage.vr1
    public final void D0() {
        h2(yp.c(this, R$dimen.omnibox_suggestion_dropdown_bg_elevation));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final bi1 E1() {
        return new bi1(new AppModalPresenter(this));
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void K() {
        Profile profileD = Profile.d();
        this.o0.r(profileD);
        super.K();
        b bVar = new b();
        WebContents webContentsA = pq3.a(profileD, false);
        ry2 ry2Var = new ry2();
        ry2Var.e = this.O;
        ry2Var.b(1);
        ry2Var.i = webContentsA;
        ry2Var.j = bVar;
        TabImpl tabImplA = ry2Var.a();
        this.n0 = tabImplA;
        tabImplA.b(new LoadUrlParams("about:blank", 0));
        this.m0.c = this.n0;
        td2 td2Var = new td2(22, this);
        j2().getClass();
        LocaleManager.getInstance().b(this, td2Var);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final g3 L1() {
        return new a(this, new vn2(new WeakReference(this)), this.M);
    }

    @Override // defpackage.nj
    public final boolean O() {
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final View P1() {
        return this.j0;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final boolean S1(Intent intent) {
        j2().getClass();
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void g2() {
        zb zbVar;
        if (!SysUtils.isLowEndDevice()) {
            getWindow().addFlags(16777216);
            this.a0 = true;
        }
        this.l0 = new wp2(this, (ViewGroup) findViewById(R.id.content), null);
        bg2 bg2Var = new bg2(this);
        this.m0 = bg2Var;
        bg2Var.b = q11.i(getIntent(), "org.chromium.chrome.browser.ui.searchactivityutils.FROM_QUICK_ACTION_SEARCH_WIDGET", false);
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();
        viewGroup.setFitsSystemWindows(false);
        org.chromium.base.c cVar = this.O.r;
        m01 m01Var = this.p0;
        m01Var.s(cVar);
        int i = InsetObserverView.k;
        m01Var.r(new InsetObserverView.c(this));
        viewGroup.addView((View) m01Var.f, 0);
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this).inflate(R$layout.search_activity, (ViewGroup) null, false);
        viewGroup2.setOnClickListener(new yf2(this));
        if (pr1.e(this)) {
            View viewFindViewById = viewGroup2.findViewById(R$id.toolbar);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.toolbar_edge_padding_modern);
            viewFindViewById.setPaddingRelative(dimensionPixelOffset, viewFindViewById.getPaddingTop(), dimensionPixelOffset, viewFindViewById.getPaddingBottom());
            viewFindViewById.setBackground(new ColorDrawable(yp.c(this, R$dimen.omnibox_suggestion_dropdown_bg_elevation)));
        }
        this.c0 = viewGroup2;
        setContentView(viewGroup2);
        this.j0 = (SearchActivityLocationBarLayout) this.c0.findViewById(R$id.search_location_bar);
        View viewFindViewById2 = this.c0.findViewById(R$id.toolbar);
        this.d0 = viewFindViewById2;
        if (pr1.e(viewFindViewById2.getContext())) {
            ViewGroup.LayoutParams layoutParams = this.d0.getLayoutParams();
            layoutParams.height = getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow) + getResources().getDimensionPixelSize(pr1.d() ? R$dimen.toolbar_url_focus_height_increase_active_color : R$dimen.toolbar_url_focus_height_increase_no_active_color);
            this.d0.setLayoutParams(layoutParams);
            if (!pr1.d()) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.toolbar_url_focus_bottom_padding);
                View view = this.d0;
                view.setPaddingRelative(view.getPaddingStart(), this.d0.getPaddingTop(), this.d0.getPaddingEnd(), dimensionPixelSize);
            }
        }
        if (pr1.b()) {
            Drawable background = this.d0.getBackground();
            if (background instanceof ColorDrawable) {
                ((ColorDrawable) background).getColor();
                getWindow();
                int i2 = dt2.s;
            }
        }
        c01 c01Var = new c01(17, this);
        if (zb.b() || yj.a()) {
            zb zbVar2 = new zb();
            this.k.a(this, zbVar2.e);
            zbVar = zbVar2;
        } else {
            zbVar = null;
        }
        SearchActivityLocationBarLayout searchActivityLocationBarLayout = this.j0;
        View view2 = this.d0;
        aq1<Profile> aq1Var = this.o0;
        v42.g();
        bg2 bg2Var2 = this.m0;
        it3 it3Var = new it3(getWindow());
        g3 g3Var = this.O;
        ap apVar = new ap(1);
        aq1<bi1> aq1Var2 = this.D;
        org.chromium.chrome.browser.omnibox.c cVar2 = new org.chromium.chrome.browser.omnibox.c(searchActivityLocationBarLayout, view2, aq1Var, bg2Var2, null, it3Var, g3Var, apVar, aq1Var2, null, null, this.K, c01Var, this, jg2.a(), new qh1(9), new ps(11), new pd(12), new i83(14), new se1(7), new wd0(16), new BooleanSupplier() { // from class: xf2
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                if (a8.P()) {
                    return n3.H0();
                }
                return false;
            }
        }, new d70(), null, new f2(this, new rs1(), aq1Var2), null, new xo(5), zbVar, this);
        this.k0 = cVar2;
        cVar2.c(true);
        g gVar = this.k0.k;
        gVar.H = true;
        gVar.C(this);
        i2();
        j2().getClass();
        this.I.post(new qm0(28, this));
        W1();
    }

    public final void h2(int i) {
        if (!pr1.e(this) || pr1.d()) {
            return;
        }
        Drawable background = this.c0.findViewById(R$id.search_location_bar).getBackground();
        Drawable background2 = this.c0.findViewById(R$id.toolbar).getBackground();
        background.setTint(i);
        background2.setTint(i);
        if (pr1.b()) {
            getWindow();
            int i2 = dt2.s;
        }
    }

    public final void i2() {
        int iK2 = k2(getIntent().getAction());
        if (q11.i(getIntent(), "org.chromium.chrome.browser.ui.searchactivityutils.FROM_QUICK_ACTION_SEARCH_WIDGET", false)) {
            if (iK2 == 1) {
                n3.X0("QuickActionSearchWidget.VoiceQuery");
            } else if (iK2 == 2) {
                n3.X0("QuickActionSearchWidget.LensQuery");
            } else if (iK2 == 0) {
                n3.X0("QuickActionSearchWidget.TextQuery");
            }
        }
        SearchActivityLocationBarLayout searchActivityLocationBarLayout = this.j0;
        String strR = q11.r(getIntent(), "query");
        so3 so3VarD = this.k0.d();
        g3 g3Var = this.O;
        h hVar = searchActivityLocationBarLayout.k;
        if (strR == null) {
            strR = "";
        }
        hVar.g(rj3.b(strR), 0, 0);
        if (searchActivityLocationBarLayout.r || !(iK2 == 0 || searchActivityLocationBarLayout.o)) {
            searchActivityLocationBarLayout.s = true;
        } else {
            searchActivityLocationBarLayout.d(iK2, so3VarD, g3Var);
        }
    }

    @Override // defpackage.vj3
    public final void k(boolean z) {
        if (z) {
            this.k0.k.h0(false);
        }
    }

    @Override // defpackage.vr1
    public final void k0() {
        h2(yp.c(this, R$dimen.toolbar_text_box_elevation));
    }

    public final void l2(String str, int i, String str2, byte[] bArr) {
        Intent intent;
        if (!this.e0) {
            this.f0 = str;
            this.g0 = i;
            this.h0 = str2;
            this.i0 = bArr;
            return;
        }
        if (TextUtils.isEmpty(str)) {
            intent = null;
        } else {
            GURL gurlH = oj2.h(str);
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(gurlH.b ? gurlH.a : ""));
            intent2.setFlags(268959744);
            intent2.setClass(this, ChromeLauncherActivity.class);
            if (!TextUtils.isEmpty(str2) && bArr != null && bArr.length != 0) {
                intent2.putExtra("com.android.chrome.post_data_type", str2);
                intent2.putExtra("com.android.chrome.post_data", bArr);
            }
            if (q11.i(getIntent(), "org.chromium.chrome.browser.searchwidget.FROM_SEARCH_WIDGET", false)) {
                intent2.putExtra("org.chromium.chrome.browser.searchwidget.FROM_SEARCH_WIDGET", true);
            }
            intent2.putExtra("org.chromium.chrome.browser.searchwidget.FROM_SEARCH_ACTIVITY", true);
            q11.a(intent2);
            intent = intent2;
        }
        if (intent == null) {
            return;
        }
        Bundle bundle = ActivityOptions.makeCustomAnimation(this, R.anim.fade_in, R.anim.fade_out).toBundle();
        ComponentName componentName = q11.a;
        try {
            startActivity(intent, bundle);
        } catch (ActivityNotFoundException unused) {
        }
        n3.X0("SearchWidget.SearchMade");
        LocaleManager.getInstance().a.getClass();
        finish();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        g gVar;
        TabImpl tabImpl = this.n0;
        if (tabImpl != null && tabImpl.isInitialized()) {
            this.n0.destroy();
        }
        org.chromium.chrome.browser.omnibox.c cVar = this.k0;
        if (cVar != null && (gVar = cVar.k) != null) {
            gVar.H(this);
            this.k0.destroy();
            this.k0 = null;
        }
        this.I.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.m0.b = q11.i(getIntent(), "org.chromium.chrome.browser.ui.searchactivityutils.FROM_QUICK_ACTION_SEARCH_WIDGET", false);
        i2();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        super.onPause();
        this.k0.k.s(null, 12, false);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        this.j0.f();
    }

    @Override // wp2.c
    public final wp2 q0() {
        return this.l0;
    }

    public class b implements jz2 {
        @Override // defpackage.jz2
        public final jk1 a(String str, jk1 jk1Var, Tab tab) {
            return null;
        }

        @Override // defpackage.jz2
        public final ContextMenuPopulatorFactory b(Tab tab) {
            return null;
        }

        @Override // defpackage.jz2
        public final lj c(TabImpl tabImpl) {
            return null;
        }

        @Override // defpackage.jz2
        public final hj0 d(TabImpl tabImpl) {
            return null;
        }

        @Override // defpackage.jz2
        public final k53 e(Tab tab) {
            return new a();
        }

        public class a extends k53 {
            @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
            public final int a() {
                return 1;
            }

            @Override // defpackage.k53
            public final boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z) {
                return false;
            }

            @Override // defpackage.k53
            public final boolean shouldResumeRequestsForCreatedWindow() {
                return false;
            }

            @Override // defpackage.k53
            public final void setOverlayMode(boolean z) {
            }
        }
    }
}
