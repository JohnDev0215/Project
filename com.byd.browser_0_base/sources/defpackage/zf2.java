package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.function.Consumer;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SearchActivityPreferencesManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zf2 implements TemplateUrlService.b, TemplateUrlService.c {
    public static zf2 g;
    public final b<Consumer<a>> e = new b<>();
    public a f;

    /* JADX INFO: compiled from: SearchActivityPreferencesManager.java */
    public static final class a {
        public final String a;
        public final String b;
        public final boolean c;
        public final boolean d;
        public final boolean e;

        public a(String str, String str2, boolean z, boolean z2, boolean z3) {
            this.a = str;
            this.b = str2;
            this.c = z;
            this.d = z2;
            this.e = z3;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.c == aVar.c && this.d == aVar.d && this.e == aVar.e && TextUtils.equals(this.a, aVar.a) && TextUtils.equals(this.b, aVar.b);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.a, this.b, Boolean.valueOf(this.c), Boolean.valueOf(this.d), Boolean.valueOf(this.e)});
        }
    }

    public static zf2 a() {
        Object obj = ThreadUtils.a;
        if (g == null) {
            g = new zf2();
            b();
        }
        return g;
    }

    public static void b() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        c(new a(sharedPreferencesManager.g("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_SHORTNAME", null), sharedPreferencesManager.g("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_URL", null), sharedPreferencesManager.readBoolean("org.chromium.chrome.browser.searchwidget.IS_VOICE_SEARCH_AVAILABLE", true), sharedPreferencesManager.readBoolean("org.chromium.chrome.browser.searchwidget.IS_GOOGLE_LENS_AVAILABLE", false), sharedPreferencesManager.readBoolean("org.chromium.chrome.browser.searchwidget.IS_INCOGNITO_AVAILABLE", true)), false);
    }

    public static void c(a aVar, boolean z) {
        zf2 zf2VarA = a();
        if (aVar.equals(zf2VarA.f)) {
            return;
        }
        zf2VarA.f = aVar;
        PostTask.c(rh3.a, new z93(z, aVar, zf2VarA));
    }

    public static void e(Context context, g3 g3Var) {
        a aVar = a().f;
        String str = aVar.a;
        String str2 = aVar.b;
        boolean zB = to3.b(g3Var);
        s61 s61Var = s61.b;
        DeviceFormFactor.a(context);
        s61Var.a.getClass();
        c(new a(str, str2, zB, false, N.M$3vpOHw()), true);
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        d();
    }

    public final void d() {
        TemplateUrlService templateUrlServiceQ = oj2.q(Profile.d());
        TemplateUrl templateUrlC = templateUrlServiceQ.c();
        if (templateUrlC == null) {
            return;
        }
        GURL gurl = new GURL(N.MjOvYRBS(templateUrlServiceQ.c, templateUrlServiceQ, templateUrlC.b()));
        String strC = templateUrlC.c();
        String strJ = gurl.f().j();
        a aVar = this.f;
        c(new a(strC, strJ, aVar.c, aVar.d, aVar.e), true);
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.b
    public final void g() {
        TemplateUrlService templateUrlServiceQ = oj2.q(Profile.d());
        templateUrlServiceQ.getClass();
        Object obj = ThreadUtils.a;
        templateUrlServiceQ.a.c(this);
        d();
    }
}
