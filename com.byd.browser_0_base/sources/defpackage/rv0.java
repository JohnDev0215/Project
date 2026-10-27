package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HomepagePolicyManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class rv0 implements PrefChangeRegistrar.a {
    public static rv0 j;
    public boolean e;
    public GURL f;
    public final SharedPreferencesManager h;
    public final b<a> i = new b<>();
    public boolean g = false;

    /* JADX INFO: compiled from: HomepagePolicyManager.java */
    public interface a {
        void a();
    }

    public rv0() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.h = sharedPreferencesManager;
        String strG = sharedPreferencesManager.g("Chrome.Policy.HomepageLocationGurl", null);
        if (strG != null) {
            this.f = GURL.a(strG);
        } else {
            String strG2 = sharedPreferencesManager.g("Chrome.Policy.HomepageLocation", null);
            if (strG2 != null) {
                this.f = new GURL(strG2);
            } else {
                this.f = GURL.emptyGURL();
            }
        }
        this.e = !this.f.k();
        sp.a().f(new ql0(8, this));
    }

    public static rv0 a() {
        if (j == null) {
            j = new rv0();
        }
        return j;
    }

    public static boolean b() {
        return a().e;
    }

    public final void c() {
        PrefService prefServiceK = oj2.k(Profile.d());
        boolean zD = prefServiceK.d("homepage");
        GURL gurlEmptyGURL = GURL.emptyGURL();
        if (zD) {
            gurlEmptyGURL = new GURL(prefServiceK.c("homepage"));
        }
        if (zD == this.e && gurlEmptyGURL != null && gurlEmptyGURL.equals(this.f)) {
            return;
        }
        this.e = zD;
        if (gurlEmptyGURL != null) {
            this.f = gurlEmptyGURL;
        }
        this.h.o("Chrome.Policy.HomepageLocationGurl", this.f.m());
        Iterator<a> it = this.i.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }

    @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
    public final void f() {
        c();
    }
}
