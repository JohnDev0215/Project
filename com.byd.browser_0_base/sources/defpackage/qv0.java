package defpackage;

import android.text.TextUtils;
import defpackage.rv0;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HomepageManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class qv0 implements rv0.a, PartnerBrowserCustomizations.a {
    public static qv0 d;
    public final SharedPreferencesManager a = SharedPreferencesManager.getInstance();
    public final b<a> b = new b<>();
    public final n3 c;

    /* JADX INFO: compiled from: HomepageManager.java */
    public interface a {
        void a();
    }

    public qv0() {
        rv0.a().i.a(this);
        PartnerBrowserCustomizations.b().f = this;
        this.c = new n3();
    }

    public static String b() {
        if (PartnerBrowserCustomizations.b().d()) {
            return PartnerBrowserCustomizations.b().a().j();
        }
        String strG = SharedPreferencesManager.getInstance().g("Chrome.Homepage.PartnerCustomizedDefaultGurl", "");
        String strJ = !strG.equals("") ? GURL.a(strG).j() : SharedPreferencesManager.getInstance().g("Chrome.Homepage.PartnerCustomizedDefaultUri", "");
        return !strJ.equals("") ? strJ : "chrome://newtab/";
    }

    public static String c() {
        String strB;
        if (!e()) {
            return null;
        }
        qv0 qv0VarD = d();
        qv0VarD.getClass();
        if (rv0.b()) {
            strB = rv0.a().f.j();
        } else {
            SharedPreferencesManager sharedPreferencesManager = qv0VarD.a;
            strB = sharedPreferencesManager.readBoolean("Chrome.Homepage.UseNTP", false) ? "chrome://newtab/" : sharedPreferencesManager.readBoolean("homepage_partner_enabled", true) ? b() : sharedPreferencesManager.g("homepage_custom_uri", "");
        }
        return TextUtils.isEmpty(strB) ? "chrome-native://newtab/" : strB;
    }

    public static qv0 d() {
        if (d == null) {
            d = new qv0();
        }
        return d;
    }

    public static boolean e() {
        return rv0.b() || d().a.readBoolean("homepage", false);
    }

    @Override // rv0.a
    public final void a() {
        f();
    }

    public final void f() {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }
}
