package org.chromium.chrome.browser.firstrun;

import J.N;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import defpackage.a8;
import defpackage.bi1;
import defpackage.bn0;
import defpackage.dm0;
import defpackage.dn;
import defpackage.dn0;
import defpackage.fn0;
import defpackage.gn0;
import defpackage.hn0;
import defpackage.in0;
import defpackage.jn0;
import defpackage.kn0;
import defpackage.l10;
import defpackage.mo0;
import defpackage.oj2;
import defpackage.s62;
import defpackage.xz;
import defpackage.z7;
import java.util.ArrayList;
import java.util.BitSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.PrivacyFragment;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.signin.services.FREMobileIdentityConsistencyFieldTrial;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.components.metrics.LowEntropySource;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* JADX INFO: loaded from: classes.dex */
public class FirstRunActivity extends FirstRunActivityBase implements jn0 {
    public static final /* synthetic */ int x0 = 0;
    public boolean m0;
    public a o0;
    public Bundle p0;
    public boolean q0;
    public boolean r0;
    public long s0;
    public ViewPager2 v0;
    public kn0 w0;
    public final BitSet l0 = new BitSet(13);
    public final s62<Void> n0 = new s62<>();
    public final ArrayList t0 = new ArrayList();
    public final ArrayList u0 = new ArrayList();

    public class a extends gn0 {
        public a(Activity activity, dn dnVar) {
            super(activity, dnVar);
        }

        @Override // defpackage.gn0
        public final void e(Bundle bundle) {
            FirstRunActivity firstRunActivity = FirstRunActivity.this;
            firstRunActivity.p0 = bundle;
            oj2.b0(SystemClock.elapsedRealtime() - firstRunActivity.s0, "MobileFre.FromLaunch.ChildStatusAvailable");
            firstRunActivity.m2();
            firstRunActivity.R0(((Integer) firstRunActivity.u0.get(0)).intValue());
            final long jElapsedRealtime = SystemClock.elapsedRealtime();
            oj2.b0(jElapsedRealtime - firstRunActivity.s0, "MobileFre.FromLaunch.FirstFragmentInflatedV2");
            Callback callback = new Callback() { // from class: cn0
                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    Long l = (Long) obj;
                    long jLongValue = l.longValue();
                    long j = jElapsedRealtime;
                    if (jLongValue > j) {
                        oj2.b0(l.longValue() - j, "MobileFre.FragmentInflationSpeed.FasterThanAppRestriction");
                    } else {
                        oj2.b0(j - l.longValue(), "MobileFre.FragmentInflationSpeed.SlowerThanAppRestriction");
                    }
                }
            };
            fn0 fn0Var = firstRunActivity.d0;
            fn0Var.getClass();
            Object obj = ThreadUtils.a;
            if (fn0Var.a) {
                callback.onResult(Long.valueOf(fn0Var.c));
            } else {
                fn0Var.e.add(callback);
            }
        }
    }

    @Override // defpackage.jn0
    public final boolean E() {
        this.o0.f(this.p0);
        int currentItem = this.v0.getCurrentItem() + 1;
        while (currentItem < this.w0.k() && !((in0) this.t0.get(currentItem)).b.getAsBoolean()) {
            currentItem++;
        }
        SharedPreferencesManager.getInstance().l("skip_welcome_privacy_page", true);
        try {
            if (!n2(currentItem)) {
                return false;
            }
        } catch (Exception e) {
            Log.e("FirstRunActivity", e.toString());
        }
        R0(((Integer) this.u0.get(currentItem)).intValue());
        return true;
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final bi1 E1() {
        return new bi1(new AppModalPresenter(this));
    }

    @Override // defpackage.jn0
    public final void H() {
        SharedPreferencesManager.getInstance().l("Chrome.FirstRun.SkippedByPolicy", true);
        if (j2()) {
            ApplicationStatus.g(new dn0(this));
        } else {
            finish();
        }
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void K() {
        super.K();
        oj2.q(Profile.d()).j(new l10(14, this));
    }

    @Override // defpackage.jn0
    public final void R0(int i) {
        BitSet bitSet = this.l0;
        if (bitSet.get(i)) {
            return;
        }
        bitSet.set(i);
        if (this.q0) {
            oj2.U(i, 13, "MobileFre.Progress.MainIntent");
        } else {
            oj2.U(i, 13, "MobileFre.Progress.ViewIntent");
        }
    }

    @Override // defpackage.jn0
    public final boolean T0() {
        return this.r0;
    }

    @Override // defpackage.jn0
    public final Bundle V() {
        return this.p0;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void V1() {
        super.V1();
        mo0 mo0VarA = mo0.a();
        mo0VarA.a.getClass();
        mo0VarA.b("FirstRunActivity");
    }

    @Override // defpackage.jn0
    public final void a1() {
        finish();
        FirstRunActivityBase.h2(getIntent());
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void a2() {
        super.a2();
        mo0 mo0VarA = mo0.a();
        mo0VarA.a.getClass();
        mo0VarA.c("FirstRunActivity");
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final Bundle f2(Bundle bundle) {
        return null;
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void g2() {
        Object obj = FREMobileIdentityConsistencyFieldTrial.a;
        synchronized (obj) {
            if (SharedPreferencesManager.getInstance().g("Chrome.FirstRun.FieldTrialEnabled", null) == null) {
                Object obj2 = ThreadUtils.a;
                if (xz.a.a.getBoolean("low_entropy_source_fre_completed", false)) {
                    throw new IllegalStateException("LowEntropySource can't be used from Java after FRE has been completed!");
                }
                String strA = FREMobileIdentityConsistencyFieldTrial.a(LowEntropySource.generateLowEntropySource(), 8000);
                synchronized (obj) {
                    SharedPreferencesManager.getInstance().o("Chrome.FirstRun.FieldTrialEnabled", strA);
                }
            }
        }
        super.g2();
        if (getIntent() != null) {
            this.q0 = getIntent().getBooleanExtra("Extra.ComingFromChromeIcon", false);
            this.r0 = getIntent().getBooleanExtra("Extra.FreChromeLaunchIntentIsCct", false);
            this.s0 = getIntent().getLongExtra("Extra.FreIntentCreationElapsedRealtimeMs", 0L);
        }
        oj2.b0(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.TriggerLayoutInflation");
        setFinishOnTouchOutside(true);
        setContentView(k2());
        l2();
        a aVar = new a(this, this.j0);
        this.o0 = aVar;
        AccountManagerFacadeProvider.getInstance().b().g(new dm0(3, aVar));
        z7.p = true;
        R0(0);
        W1();
        oj2.b0(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.ActivityInflated");
    }

    @Override // defpackage.jn0
    public final void i1(boolean z) {
        if (!SharedPreferencesManager.getInstance().readBoolean("Chrome.FirstRun.init", false)) {
            N.M76Za3Tu(false);
            oj2.Y(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.TosAccepted");
            a8.e(z);
            Log.i("FirstRunActivity", "fww  acceptTermsOfService: 首次进行设置参数");
            PrefService prefServiceK = oj2.k(Profile.d());
            prefServiceK.e("signin.allowed", false);
            prefServiceK.e("search.suggest_enabled", false);
            SharedPreferencesManager.getInstance().l("Chrome.FirstRun.init", true);
        }
        SharedPreferencesManager.getInstance().l("skip_welcome_page", true);
        if (this.c0) {
            z7.i();
        }
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase
    public final void i2(boolean z) {
        super.i2(z);
        oj2.b0(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.PoliciesLoaded");
        m2();
    }

    public View k2() {
        ViewPager2 viewPager2 = new ViewPager2(this);
        this.v0 = viewPager2;
        viewPager2.setUserInputEnabled(false);
        this.v0.setId(R$id.fre_pager);
        this.v0.setOffscreenPageLimit(3);
        this.v0.setLayoutDirection(0);
        return this.v0;
    }

    @Override // defpackage.jn0
    public final s62<Void> l1() {
        return this.n0;
    }

    public final void l2() {
        bn0 bn0Var = new bn0(0);
        boolean z = SharedPreferencesManager.getInstance().readBoolean("skip_welcome_privacy_page", false);
        ArrayList arrayList = this.u0;
        ArrayList arrayList2 = this.t0;
        if (!z) {
            arrayList2.add(new in0(PrivacyFragment.class, bn0Var));
            arrayList.add(1);
        }
        arrayList2.add(new in0(SigninFirstRunFragment.class, bn0Var));
        arrayList.add(2);
        kn0 kn0Var = new kn0(this, arrayList2);
        this.w0 = kn0Var;
        this.v0.setAdapter(kn0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m2() {
        /*
            r10 = this;
            android.os.Bundle r0 = r10.p0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L8
            r0 = r2
            goto L9
        L8:
            r0 = r1
        L9:
            if (r0 != 0) goto Lc
            return
        Lc:
            kn0 r0 = r10.w0
            if (r0 != 0) goto L13
            r10.l2()
        L13:
            boolean r0 = r10.m0
            java.util.ArrayList r3 = r10.t0
            o22 r4 = r10.g0
            s62<java.lang.Void> r5 = r10.n0
            if (r0 != 0) goto L7f
            boolean r0 = r5.d()
            if (r0 == 0) goto L34
            android.os.Bundle r0 = r10.p0
            if (r0 == 0) goto L29
            r0 = r2
            goto L2a
        L29:
            r0 = r1
        L2a:
            if (r0 == 0) goto L34
            java.lang.Object r0 = r4.get()
            if (r0 == 0) goto L34
            r0 = r2
            goto L35
        L34:
            r0 = r1
        L35:
            if (r0 == 0) goto L7f
            org.chromium.chrome.browser.firstrun.FirstRunActivity$a r0 = r10.o0
            android.os.Bundle r6 = r10.p0
            r0.f(r6)
            cp r0 = new cp
            r6 = 2
            r0.<init>(r6, r10)
            s20 r6 = new s20
            r7 = 3
            r6.<init>(r7, r10)
            boolean r7 = r0.getAsBoolean()
            java.util.ArrayList r8 = r10.u0
            if (r7 == 0) goto L64
            in0 r7 = new in0
            java.lang.Class<org.chromium.chrome.browser.firstrun.DefaultSearchEngineFirstRunFragment> r9 = org.chromium.chrome.browser.firstrun.DefaultSearchEngineFirstRunFragment.class
            r7.<init>(r9, r0)
            r3.add(r7)
            r0 = 7
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.add(r0)
        L64:
            in0 r0 = new in0
            java.lang.Class<org.chromium.chrome.browser.firstrun.SyncConsentFirstRunFragment> r7 = org.chromium.chrome.browser.firstrun.SyncConsentFirstRunFragment.class
            r0.<init>(r7, r6)
            r3.add(r0)
            r0 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.add(r0)
            kn0 r0 = r10.w0
            if (r0 == 0) goto L7d
            r0.o()
        L7d:
            r10.m0 = r2
        L7f:
            boolean r0 = r5.d()
            if (r0 == 0) goto L95
            android.os.Bundle r0 = r10.p0
            if (r0 == 0) goto L8b
            r0 = r2
            goto L8c
        L8b:
            r0 = r1
        L8c:
            if (r0 == 0) goto L95
            java.lang.Object r0 = r4.get()
            if (r0 == 0) goto L95
            r1 = r2
        L95:
            if (r1 == 0) goto Lb2
        L97:
            androidx.viewpager2.widget.ViewPager2 r0 = r10.v0
            int r0 = r0.getCurrentItem()
            java.lang.Object r0 = r3.get(r0)
            in0 r0 = (defpackage.in0) r0
            java.util.function.BooleanSupplier r0 = r0.b
            boolean r0 = r0.getAsBoolean()
            if (r0 != 0) goto Lb2
            boolean r0 = r10.E()
            if (r0 == 0) goto Lb2
            goto L97
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.firstrun.FirstRunActivity.m2():void");
    }

    @Override // defpackage.jn0
    public final void n1() {
        oj2.b0(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.NativePolicyAndChildStatusLoaded");
    }

    public final boolean n2(int i) {
        if (i < this.w0.k()) {
            int currentItem = this.v0.getCurrentItem();
            this.v0.c(i, false);
            ArrayList arrayList = this.w0.r;
            hn0 hn0Var = i < arrayList.size() ? (hn0) arrayList.get(i) : null;
            if (hn0Var != null) {
                hn0Var.h();
                if (currentItem > i) {
                    hn0Var.reset();
                }
            }
            return true;
        }
        oj2.Y(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.FreCompleted");
        if (!N.Mfrb03wj()) {
            N.MSb7o$8Q();
        }
        SharedPreferencesManager.getInstance().l("first_run_flow", true);
        Object obj = ThreadUtils.a;
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putBoolean("low_entropy_source_fre_completed", true);
        editorEdit.apply();
        if (j2()) {
            ApplicationStatus.g(new dn0(this));
        } else {
            finish();
        }
        return false;
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, defpackage.vb
    public final int o() {
        if (!this.m0) {
            a1();
            return 0;
        }
        this.o0.f(this.p0);
        int currentItem = this.v0.getCurrentItem();
        do {
            currentItem--;
            if (currentItem <= 0) {
                break;
            }
        } while (!((in0) this.t0.get(currentItem)).b.getAsBoolean());
        a1();
        return 0;
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, android.app.Activity
    public final void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(new Bundle());
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStart() {
        super.onStart();
        for (Activity activity : ApplicationStatus.b()) {
            if ((activity instanceof FirstRunActivity) && activity != this) {
                if (activity.getTaskId() == getTaskId()) {
                    activity.finish();
                } else {
                    activity.finishAndRemoveTask();
                }
            }
        }
    }

    @Override // defpackage.jn0
    public boolean r() {
        return !getResources().getConfiguration().isLayoutSizeAtLeast(3);
    }

    @Override // defpackage.jn0
    public final void s() {
        oj2.b0(SystemClock.elapsedRealtime() - this.s0, "MobileFre.FromLaunch.NativeInitialized");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity
    public final void z1(Fragment fragment) {
        if (fragment instanceof hn0) {
            hn0 hn0Var = (hn0) fragment;
            s62<Void> s62Var = this.n0;
            if (s62Var.d()) {
                hn0Var.a();
            } else {
                s62Var.g(new dm0(2, hn0Var));
            }
        }
    }
}
