package com.amazon.identity.auth.device.env;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.q9;
import com.amazon.identity.auth.device.y3;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class EnvironmentUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, String> f306a = new HashMap();
    public static final Map<String, String> b;
    public static volatile EnvironmentUtils c;
    public static final String d;

    /* JADX INFO: compiled from: DCP */
    public enum Environment {
        prod
    }

    static {
        HashMap map = new HashMap();
        b = map;
        c = new y3();
        d = EnvironmentUtils.class.getName();
        map.put(".amazon.com", ".amazon.com");
        map.put(".amazon.ca", ".amazon.ca");
        map.put(".amazon.com.br", ".amazon.com.br");
        map.put(".amazon.com.mx", ".amazon.com.mx");
        map.put(".amazon.com.co", ".amazon.com.co");
        map.put(".amazon.cl", ".amazon.cl");
        map.put(".amazon.com.au", ".amazon.com.au");
        map.put(".amazon.co.jp", ".amazon.co.jp");
        map.put(".amazon.com.sg", ".amazon.co.jp");
        map.put(".amazon.sg", ".amazon.co.jp");
        map.put(".amazon.cn", ".amazon.cn");
        map.put(".amazon.nl", ".amazon.nl");
        map.put(".amazon.it", ".amazon.it");
        map.put(".amazon.de", ".amazon.de");
        map.put(".amazon.co.uk", ".amazon.co.uk");
        map.put(".amazon.es", ".amazon.es");
        map.put(".amazon.fr", ".amazon.fr");
        map.put(".amazon.in", ".amazon.in");
        map.put(".amazon.com.tr", ".amazon.co.uk");
        map.put(".amazon.eg", ".amazon.co.uk");
        map.put(".amazon.ae", ".amazon.co.uk");
        map.put(".amazon.sa", ".amazon.co.uk");
        map.put(".amazon.se", ".amazon.co.uk");
        map.put(".amazon.pl", ".amazon.co.uk");
        map.put(".amazon.com.ng", ".amazon.co.uk");
        map.put(".amazon.co.za", ".amazon.co.uk");
        map.put(".amazon.com.be", ".amazon.co.uk");
    }

    public static EnvironmentUtils h() {
        return c;
    }

    public abstract String a();

    public abstract String a(Bundle bundle);

    public abstract String a(String str);

    public URL a(String str, String str2) throws MalformedURLException {
        return new URL("https", str, i(), str2);
    }

    public abstract String b();

    public abstract String b(Bundle bundle);

    public String b(String str) {
        return f306a.get(str);
    }

    public abstract String c();

    public String c(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && (bundle2 = bundle.getBundle(MAPAccountManager.KEY_MARKETPLACE_BUNDLE)) != null) {
            String string = bundle2.getString(MAPAccountManager.KEY_MARKETPLACE_DOMAIN);
            if (!TextUtils.isEmpty(string)) {
                Log.i(ga.a(d), "Using client passed marketplace domain root: " + string);
                return string;
            }
        }
        return e(q9.a(bundle));
    }

    public abstract String c(String str);

    public abstract String d();

    public String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return d();
        }
        String strC = c(str);
        Map<String, String> map = b;
        if (map.containsKey(strC)) {
            return map.get(strC);
        }
        return null;
    }

    public abstract String e();

    public abstract String e(String str);

    public abstract String f();

    public abstract boolean f(String str);

    public abstract String g();

    public abstract int i();

    public abstract String j();
}
