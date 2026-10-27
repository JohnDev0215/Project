package org.chromium.chrome.browser.customtabs;

import J.N;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.a30;
import defpackage.aq1;
import defpackage.bi1;
import defpackage.bk3;
import defpackage.bq;
import defpackage.c30;
import defpackage.d30;
import defpackage.g4;
import defpackage.g50;
import defpackage.h50;
import defpackage.hp;
import defpackage.jd;
import defpackage.m20;
import defpackage.mo0;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oc;
import defpackage.oj;
import defpackage.q11;
import defpackage.r30;
import defpackage.s20;
import defpackage.s50;
import defpackage.s51;
import defpackage.t20;
import defpackage.t30;
import defpackage.uh3;
import defpackage.v40;
import defpackage.vq;
import defpackage.wq;
import defpackage.x30;
import defpackage.xx0;
import defpackage.yq2;
import defpackage.z7;
import defpackage.zf1;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.c;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class CustomTabActivity extends BaseCustomTabActivity {
    public static final g4 t1 = new g4();
    public CustomTabsSessionToken p1;
    public h50 r1;
    public final CustomTabsConnection q1 = CustomTabsConnection.e();
    public final a s1 = new a();

    public class a extends a30.a {
        public a() {
        }

        @Override // a30.a
        public final void a() {
            g4 g4Var = CustomTabActivity.t1;
            CustomTabActivity.this.h3();
        }

        @Override // a30.a
        public final void b(Tab tab) {
            g4 g4Var = CustomTabActivity.t1;
            CustomTabActivity.this.h3();
        }

        @Override // a30.a
        public final void c(Tab tab) {
            g4 g4Var = CustomTabActivity.t1;
            CustomTabActivity.this.h3();
        }
    }

    public static void i3(Context context, String str) {
        g50.a aVar = new g50.a();
        aVar.d();
        aVar.b(0);
        g50 g50VarA = aVar.a();
        Uri uri = Uri.parse(str);
        Intent intent = g50VarA.a;
        intent.setData(uri);
        Intent intentE = c.e(context, intent);
        intentE.setPackage(context.getPackageName());
        intentE.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 2);
        intentE.putExtra("com.android.browser.application_id", context.getPackageName());
        if (!(context instanceof Activity)) {
            intentE.addFlags(268435456);
        }
        q11.a(intentE);
        context.startActivity(intentE);
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity, org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void K() {
        int i = 0;
        if (!(this.b1.J() == 2)) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            if (sharedPreferencesManager.readBoolean("first_run_signin_setup", false)) {
                sharedPreferencesManager.removeKey("first_run_signin_setup");
                sharedPreferencesManager.l("first_run_signin_complete", true);
            }
            z7 z7VarM = z7.m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            SigninManager signinManagerD = z7.D(profileD);
            SharedPreferencesManager.getInstance().g("first_run_signin_account_name", null);
            signinManagerD.j();
            oc.a();
        }
        getIntent();
        this.q1.getClass();
        new v40(this.O, this.K, new s20(i, this));
        super.K();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.e7
    public final boolean S0(int i, Bundle bundle) {
        int i2 = (bundle == null || !bundle.containsKey("CustomMenuItemId")) ? -1 : bundle.getInt("CustomMenuItemId");
        if (i2 < 0) {
            return super.S0(i, bundle);
        }
        t30 t30Var = (t30) this.b1;
        String strJ = this.g1.b.getUrl().j();
        String title = this.g1.b.getTitle();
        t30Var.getClass();
        Intent intent = new Intent();
        intent.setData(Uri.parse(strJ));
        intent.putExtra("android.intent.extra.SUBJECT", title);
        ArrayList arrayList = t30Var.t;
        try {
            String str = (String) ((Pair) arrayList.get(i2)).first;
            PendingIntent pendingIntent = (PendingIntent) ((Pair) arrayList.get(i2)).second;
            if (t30Var.J() == 1) {
                intent = null;
            }
            pendingIntent.send(this, 0, intent, null, null);
            if (t30Var.i && TextUtils.equals(str, getString(R$string.download_manager_open_with))) {
                n3.X0("CustomTabsMenuCustomMenuItem.DownloadsUI.OpenWith");
            }
        } catch (PendingIntent.CanceledException unused) {
            Log.e("cr_CustomTabIntentData", "Custom tab in Chrome failed to send pending intent.");
        }
        n3.X0("CustomTabsMenuCustomMenuItem");
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void V1() {
        super.V1();
        mo0 mo0VarA = mo0.a();
        mo0VarA.a.getClass();
        mo0VarA.b("CustomTabActivity");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void a2() {
        super.a2();
        mo0 mo0VarA = mo0.a();
        mo0VarA.a.getClass();
        mo0VarA.c("CustomTabActivity");
        this.N0.q0.g();
        if (this.g1.b != null) {
            ViewGroup viewGroup = (ViewGroup) findViewById(R$id.bottom_container);
            InfoBarContainerView infoBarContainerView = InfoBarContainer.d(this.g1.b).q;
            if (infoBarContainerView != null) {
                infoBarContainerView.setParentView(viewGroup);
            }
        }
        setTaskDescription(new ActivityManager.TaskDescription((String) null, (Bitmap) null, this.b1.m().d()));
        d30 d30VarA = ((jd) this.c0).a();
        oj ojVar = d30VarA.i;
        if ((ojVar.n().isEmpty() && ojVar.h() == null) ? false : true) {
            d30VarA.b().findViewById(R$id.bottombar_shadow).setVisibility(0);
            oj ojVar2 = d30VarA.i;
            if (ojVar2.y() != null) {
                PendingIntent pendingIntentY = ojVar2.y();
                CustomTabBottomBarView customTabBottomBarView = d30VarA.m;
                if (customTabBottomBarView != null) {
                    d30VarA.p = pendingIntentY;
                    customTabBottomBarView.setSwipeHandler(d30VarA);
                }
            }
            RemoteViews remoteViewsH = ojVar2.h();
            if (remoteViewsH != null) {
                n3.X0("CustomTabsRemoteViewsShown");
                d30VarA.o = ojVar2.i();
                d30VarA.n = ojVar2.x();
                d30VarA.e(remoteViewsH);
                return;
            }
            List<m20> listN = ojVar2.n();
            if (listN.isEmpty()) {
                return;
            }
            Activity activity = d30VarA.e;
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setId(R$id.custom_tab_bottom_bar_wrapper);
            linearLayout.setBackgroundColor(ojVar2.m().c());
            for (m20 m20Var : listN) {
                if (!m20Var.c()) {
                    PendingIntent pendingIntentE = m20Var.e();
                    linearLayout.addView(m20Var.f(activity, d30VarA.b(), pendingIntentE != null ? new c30(d30VarA, 0, pendingIntentE) : null));
                }
            }
            d30VarA.b().addView(linearLayout);
        }
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity, org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void b2() {
        Integer numValueOf;
        super.b2();
        this.g1.a.a(this.s1);
        h3();
        this.p1 = this.b1.z();
        Window window = getWindow();
        oj ojVar = this.b1;
        Integer numA = ojVar.m().a();
        Integer numB = ojVar.m().b();
        boolean z = !ojVar.O();
        boolean z2 = (numA == null || z7.h0(numA.intValue())) ? false : true;
        if (numA != null) {
            if (z) {
                uh3.m(window.getDecorView().getRootView(), z2);
            } else {
                if (z2) {
                    float[] fArr = new float[3];
                    Color.colorToHSV(numA.intValue(), fArr);
                    fArr[2] = fArr[2] * 0.6f;
                    numValueOf = Integer.valueOf(Color.HSVToColor(fArr));
                }
                window.setNavigationBarColor(numValueOf.intValue());
            }
            numValueOf = numA;
            window.setNavigationBarColor(numValueOf.intValue());
        }
        if (numB == null && numA != null && z2) {
            numB = Integer.valueOf(getColor(R$color.black_alpha_12));
        }
        if (numB != null) {
            window.setNavigationBarDividerColor(numB.intValue());
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final boolean c2(Intent intent) {
        return (t30.h0(intent, this.p1) && q11.l(intent, "org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 0) == 2) ? false : true;
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity
    public final oj d3(Intent intent, int i) {
        boolean zA;
        boolean zA2 = false;
        if (q11.i(intent, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false)) {
            if (q11.g(intent)) {
                zA = true;
            } else {
                xx0.f0(intent);
                zA = bq.j.a();
            }
            if (zA) {
                zA2 = bq.i.a();
            }
        }
        return zA2 ? new xx0(this, intent) : new t30(i, this, intent);
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity, org.chromium.chrome.browser.app.ChromeActivity
    /* JADX INFO: renamed from: e3 */
    public final s50.a j2(hp hpVar) {
        s50.a aVarJ2 = super.j2(hpVar);
        this.r1 = new h50(this.K, this.e1, new t20(0, this), this.b1);
        return aVarJ2;
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity
    public final void g3() {
        h50 h50Var = this.r1;
        if (h50Var.e.q == 0) {
            h50Var.j = 0;
        }
        super.g3();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final String getPackageName() {
        if (this.n1) {
            oj ojVar = this.b1;
            if (ojVar instanceof t30) {
                Bundle bundle = ((t30) ojVar).e;
                if (bundle == null) {
                    return null;
                }
                return bundle.getString("android:activity.packageName");
            }
        }
        return super.getPackageName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity, org.chromium.chrome.browser.app.ChromeActivity, defpackage.jf1
    public final boolean h1(int i, boolean z) {
        if (i == R$id.bookmark_this_page_id) {
            this.l0.f.a(this.g1.b, false);
            n3.X0("MobileMenuAddToBookmarks");
            return true;
        }
        if (i == R$id.open_in_browser_id) {
            Tab tab = this.g1.b;
            if (this.e1.c()) {
                n3.X0("CustomTabsMenuOpenInChrome");
                Object objC = tab != null ? tab.c() : null;
                CustomTabsSessionToken customTabsSessionToken = this.p1;
                CustomTabsConnection customTabsConnection = this.q1;
                if (objC != null) {
                    customTabsConnection.getClass();
                    N.MLgTz0Wv(objC, "");
                }
                customTabsConnection.getClass();
                Bundle bundle = new Bundle();
                bundle.putLong("timestampUptimeMillis", SystemClock.uptimeMillis());
                customTabsConnection.r(customTabsSessionToken, "onOpenInBrowser", bundle);
            }
            return true;
        }
        if (i != R$id.info_menu_id) {
            return super.h1(i, z);
        }
        Tab tabI = G2().i();
        if (tabI == null) {
            return false;
        }
        String strD = TrustedCdn.d(tabI);
        String strA = strD != null ? bk3.a(strD) : null;
        aq1<bi1> aq1Var = this.D;
        aq1<zf1> aq1Var2 = this.N0.K;
        Objects.requireNonNull(aq1Var2);
        yq2 yq2Var = new yq2(4, aq1Var2);
        nw2<EphemeralTabCoordinator> nw2Var = this.N0.B0;
        wq wqVarA = wq.a();
        WebContents webContentsC = tabI.c();
        if (webContentsC != null && ProfileManager.b) {
            Activity activityB = TabUtils.b(tabI);
            PageInfoController.g(activityB, webContentsC, strA, 1, new vq(activityB, webContentsC, aq1Var, new d.e(tabI), yq2Var, nw2Var, wqVarA), wqVarA);
        }
        return true;
    }

    public final void h3() {
        Tab tab = this.g1.b;
        this.q1.c.f(this.b1.z(), tab == null ? null : tab.c());
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void i2() {
        r30 r30Var = this.a1.J0;
        if (r30Var == null ? false : r30Var.e()) {
            return;
        }
        super.i2();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final s51 l2() {
        return new x30(this);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, android.app.Activity
    public final void onUserLeaveHint() {
        this.r1.j = 1;
        super.onUserLeaveHint();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final Drawable y2() {
        int iE = this.b1.m().e();
        return (!this.b1.R() || iE == 0) ? super.y2() : new ColorDrawable(iE);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final void I1() {
    }
}
