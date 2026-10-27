package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import org.chromium.base.SysUtils;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;

/* JADX INFO: compiled from: AssistantVoiceSearchService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u8 implements TemplateUrlService.c, IdentityManager.a, z1 {
    public final a e;
    public final Context f;
    public final zi0 g;
    public final TemplateUrlService h;
    public final bs0 i;
    public final IdentityManager j;
    public final AccountManagerFacade k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public String q;

    /* JADX INFO: compiled from: AssistantVoiceSearchService.java */
    public interface a {
        void k();
    }

    public u8(Context context, zi0 zi0Var, TemplateUrlService templateUrlService, bs0 bs0Var, a aVar, IdentityManager identityManager, AccountManagerFacade accountManagerFacade) {
        this.f = context;
        this.g = zi0Var;
        this.h = templateUrlService;
        this.i = bs0Var;
        this.e = aVar;
        this.j = identityManager;
        this.k = accountManagerFacade;
        accountManagerFacade.a(this);
        identityManager.b.a(this);
        templateUrlService.a(this);
        sk skVar = bq.a;
        this.m = N.M09VlOh_("OmniboxAssistantVoiceSearch");
        this.n = N.M6bsIDpc("OmniboxAssistantVoiceSearch", "colorful_mic", false);
        this.q = N.MMltG$kc("OmniboxAssistantVoiceSearch", "min_agsa_version");
        this.p = N.M6bsIDpc("OmniboxAssistantVoiceSearch", "enable_multi_account_check", true);
        this.l = templateUrlService.e();
        this.o = a();
    }

    @Override // defpackage.z1
    public final void F() {
        new t8(this, a()).c(i9.e);
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        boolean zE = this.h.e();
        if (this.l == zE) {
            return;
        }
        this.l = zE;
        this.o = a();
        a aVar = this.e;
        if (aVar == null) {
            return;
        }
        aVar.k();
    }

    public final boolean a() {
        if (this.f.getPackageManager() == null || !this.n) {
            return false;
        }
        return this.m && b(null, true);
    }

    public final boolean b(ArrayList arrayList, boolean z) {
        ComponentName componentNameResolveActivity;
        this.i.getClass();
        if (!zt1.e("com.google.android.googlequicksearchbox")) {
            if (z) {
                return false;
            }
            arrayList.add(11);
        }
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setPackage("com.google.android.googlequicksearchbox");
        if (!((intent.getPackage().equals("com.google.android.googlequicksearchbox") && (componentNameResolveActivity = intent.resolveActivity(xz.a.getPackageManager())) != null) ? zt1.e(componentNameResolveActivity.getPackageName()) : false)) {
            if (z) {
                return false;
            }
            arrayList.add(0);
        }
        PackageInfo packageInfoC = zt1.c(0, "com.google.android.googlequicksearchbox");
        if (bs0.b(packageInfoC == null ? null : packageInfoC.versionName, TextUtils.isEmpty(this.q) ? "11.7" : this.q)) {
            if (z) {
                return false;
            }
            arrayList.add(1);
        }
        zi0 zi0Var = this.g;
        zi0Var.getClass();
        xz.a.getPackageName();
        zi0Var.b();
        if (z) {
            return false;
        }
        arrayList.add(4);
        zi0Var.b();
        if (z) {
            return false;
        }
        arrayList.add(5);
        if (!this.l) {
            if (z) {
                return false;
            }
            arrayList.add(7);
        }
        if (SysUtils.isLowEndDevice()) {
            if (z) {
                return false;
            }
            arrayList.add(9);
        }
        sk skVar = bq.a;
        if (!N.M09VlOh_("AssistantNonPersonalizedVoiceSearch")) {
            if (!this.j.b(1)) {
                if (z) {
                    return false;
                }
                arrayList.add(8);
            }
            if (this.p) {
                this.k.b();
                if (z) {
                    return false;
                }
                arrayList.add(10);
            }
        }
        return z || arrayList.size() == 0;
    }

    public final void c(String str) {
        ArrayList arrayList = new ArrayList();
        oj2.O("Assistant.VoiceSearch.UserEligibility".concat(str), b(arrayList, false));
        this.i.getClass();
        PackageInfo packageInfoC = zt1.c(0, "com.google.android.googlequicksearchbox");
        Integer numValueOf = null;
        String str2 = packageInfoC == null ? null : packageInfoC.versionName;
        if (str2 != null) {
            Matcher matcher = bs0.d.matcher(str2);
            if (matcher.find() && matcher.groupCount() >= 2) {
                try {
                    int i = Integer.parseInt(matcher.group(1));
                    if (i > 999) {
                        Log.e("cr_GSAState", "Major verison exceeded maximum of 999.");
                    } else {
                        int i2 = Integer.parseInt(matcher.group(2));
                        if (i2 > 999) {
                            Log.e("cr_GSAState", "Minor verison exceeded maximum of 999.");
                        } else {
                            numValueOf = Integer.valueOf((i * 1000) + i2);
                        }
                    }
                } catch (NumberFormatException unused) {
                    Log.e("cr_GSAState", "Version was incorrectly formatted.");
                }
            }
        }
        if (numValueOf != null) {
            oj2.a0(numValueOf.intValue(), "Assistant.VoiceSearch.AgsaVersion".concat(str));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            oj2.U(((Integer) it.next()).intValue(), 12, "Assistant.VoiceSearch.UserEligibility.FailureReason".concat(str));
        }
    }

    @Override // org.chromium.components.signin.identitymanager.IdentityManager.a
    public final void m(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        new t8(this, a()).c(i9.e);
    }
}
