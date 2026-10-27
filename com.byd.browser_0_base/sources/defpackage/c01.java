package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Size;
import androidx.preference.Preference;
import defpackage.ai;
import defpackage.cg1;
import defpackage.m60;
import defpackage.mk0;
import defpackage.sf3;
import defpackage.w01;
import defpackage.xb;
import defpackage.xf;
import defpackage.yl2;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.VirtualCardEnrollmentDelegate;
import org.chromium.chrome.browser.autofill.settings.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.feed.FeedStream;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.a;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsSettings;
import org.chromium.chrome.browser.search_resumption.SearchResumptionModuleBridge;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.signin.AccountManagerFacade;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c01 implements e01, b.a, xf.a, m60.e, i.a, AccountManagerFacade.a, w01.a, gq, cg1.a, Preference.c, SearchResumptionModuleBridge.a, ot1, xb.c, vi2, j0.p, m.a, n1.b, h.d, ai.a, sf3.a, LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c01(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // xb.c
    public boolean a() {
        SettingsActivity settingsActivity = (SettingsActivity) this.f;
        SettingsActivity settingsActivity2 = SettingsActivity.P;
        ge2 ge2VarL1 = settingsActivity.L1();
        if (ge2VarL1 instanceof SettingsActivity.a) {
            return ((SettingsActivity.a) ge2VarL1).a();
        }
        return false;
    }

    @Override // sf3.a
    public void f(ft1 ft1Var, vf3 vf3Var) {
        String str = (String) this.f;
        dg3 dg3Var = vf3Var.a;
        dg3Var.getClass();
        Bundle bundle = new Bundle();
        bundle.putString("android.support.customtabs.trusted.PLATFORM_TAG", str);
        bundle.putInt("android.support.customtabs.trusted.PLATFORM_ID", this.e);
        dg3Var.a.cancelNotification(bundle);
    }

    @Override // defpackage.vi2
    public Object get() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 20:
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                int i2 = zh1.o;
                return byteBuffer;
            default:
                int i3 = xl2.t;
                return ((yl2.a) obj).build().l().B();
        }
    }

    @Override // defpackage.ot1
    public boolean h(String str, int i, long j, String str2, byte[] bArr, boolean z) {
        SearchActivity searchActivity = (SearchActivity) this.f;
        Object obj = SearchActivity.q0;
        searchActivity.l2(str, i, str2, bArr);
        return true;
    }

    public yv1 i(int i, int i2, int i3, boolean z) {
        cw1 cw1Var = (cw1) this.f;
        cw1Var.getClass();
        if (i == 1) {
            return new aw1(cw1Var.e, cw1Var.h, cw1Var.j, cw1Var.k, cw1Var.l, cw1Var.m, cw1Var.n, cw1Var.o, z, cw1Var.z);
        }
        if (i == 2) {
            return new lw1(cw1Var.e, cw1Var.i, cw1Var.k, cw1Var.m, cw1Var.n, cw1Var.o, cw1Var.p, z, i2, i3, cw1Var.z, cw1Var.g);
        }
        if (i != 3) {
            return null;
        }
        return new dw1(cw1Var.e, cw1Var.k, cw1Var.m, cw1Var.n, cw1Var.o, cw1Var.z);
    }

    @Override // org.chromium.chrome.browser.autofill.settings.b.a
    public void j(int i, String str) {
        long j = ((VirtualCardEnrollmentDelegate) this.f).a;
        if (j != 0) {
            N.MDUWoeID(j, str, i);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade.a
    public void k(boolean z) {
        dn dnVar = (dn) this.f;
        dnVar.h = Boolean.valueOf(z);
        dnVar.a();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.j0.p
    public void l(int i, Size size, t02 t02Var, boolean z, boolean z2, boolean z3) {
        ((TabContentManager) this.f).e(i, size, t02Var, z, z2);
    }

    @Override // androidx.preference.Preference.c
    public boolean n(Preference preference, Object obj) {
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 15:
                return SecureDnsSettings.M0((SecureDnsSettings) obj2, obj);
            default:
                TracingSettings tracingSettings = (TracingSettings) obj2;
                LinkedHashMap linkedHashMap = TracingSettings.n0;
                tracingSettings.getClass();
                SharedPreferencesManager.getInstance().o("tracing_mode", (String) obj);
                tracingSettings.P0();
                return true;
        }
    }

    @Override // org.chromium.chrome.browser.feed.webfeed.i.a
    public void o() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 8:
                ((FeedStream) obj).j.c();
                break;
            default:
                ((g63) obj).n0.get().p0(false).g(2, vm1.b());
                break;
        }
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        ((mk0.b) this.f).c(this.e, bitmap);
    }

    public void p() {
        a aVar = (a) this.f;
        aVar.d.R(aVar.e.getResources().getString(R$string.languages_split_ready, aVar.d.Y.c, BuildInfo.a.a.a));
        aVar.d.J(true);
        aVar.a.a(aVar.c);
        String str = aVar.d.Y.b;
        Resources resources = aVar.e.getResources();
        tp2 tp2VarA = tp2.a(resources.getString(R$string.languages_infobar_ready, str), aVar.c, 2, 43);
        tp2VarA.d = resources.getString(R$string.languages_infobar_restart);
        tp2VarA.e = null;
        tp2VarA.i = false;
        wp2 wp2Var = aVar.a;
        if (wp2Var.i) {
            wp2Var.c(tp2VarA);
        } else {
            aVar.b = tp2VarA;
        }
    }

    @Override // defpackage.mb1
    public boolean r(Preference preference) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 12:
                int i2 = LanguageSettings.l0;
                return ((LanguageSettings) obj).M0().d("translate.enabled");
            default:
                int i3 = PasswordSettings.n0;
                return ((PasswordSettings) obj).N0().d("credentials_enable_autosignin");
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.b
    public void t(int i, long j) {
        ((q1) this.f).I(i, true);
    }
}
