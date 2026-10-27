package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class y3 extends EnvironmentUtils {
    public static final String e = "com.amazon.identity.auth.device.y3";

    static {
        Map<String, String> map = EnvironmentUtils.f306a;
        map.put("www.amazon.com.mx", "amzn_device_mx");
        map.put("www.amazon.co.uk", "amzn_device_uk");
        map.put("www.amazon.ca", "amzn_device_ca");
        map.put("www.amazon.de", "amzn_device_de");
        map.put("www.amazon.fr", "amzn_device_fr");
        map.put("www.amazon.it", "amzn_device_it");
        map.put("www.amazon.in", "amzn_device_in");
        map.put("www.amazon.es", "amzn_device_es");
        map.put("www.amazon.co.jp", "amzn_device_jp");
        map.put("www.amazon.cn", "amzn_device_cn");
        map.put("www.amazon.com.br", "amzn_device_br");
        map.put("www.amazon.nl", "amzn_device_nl");
        map.put("www.amazon.com.au", "amzn_device_au");
        map.put("www.amazon.ru", "amzn_device_ru");
        map.put("www.amazon.com.sa", "amzn_device_sa");
        map.put("www.amazon.ae", "amzn_device_ae");
        map.put("www.amazon.se", "amzn_device_se");
        map.put("www.amazon.pl", "amzn_device_pl");
        map.put("www.amazon.com.co", "amzn_device_co");
        map.put("www.amazon.cl", "amzn_device_cl");
        map.put("www.amazon.com.ng", "amzn_device_ng");
        map.put("www.amazon.co.za", "amzn_device_za");
        map.put("www.amazon.com.be", "amzn_device_be");
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String a() {
        return ".amazon.cn";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            ga.a(e);
            return "www.amazon.com";
        }
        if (str.startsWith(".")) {
            return "www" + str;
        }
        if (str.startsWith("amazon.")) {
            return "www." + str;
        }
        if (str.startsWith("www")) {
            return str;
        }
        throw new RuntimeException("Input was non-empty and doesn't look like a valid partial domain in production: " + str + ". If you were attempting to hit devo or pre-prod, please try following the steps to switch again.");
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String b() {
        return ".amazon.co.jp";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String b(Bundle bundle) {
        String strA = a(bundle);
        return !TextUtils.isEmpty(strA) ? strA : a(q9.a(bundle));
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String c() {
        return ".amazon.co.uk";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String c(String str) {
        if (str.startsWith("www.")) {
            return str.substring(3);
        }
        if (str.startsWith(".")) {
            return str;
        }
        return "." + str;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String d() {
        return ".amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String e() {
        return "dcape-na.amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String e(String str) {
        if (TextUtils.isEmpty(str)) {
            ga.a(e);
            return "api.amazon.com";
        }
        if (str.startsWith(".")) {
            return "api" + str;
        }
        if (str.startsWith("amazon.")) {
            return "api." + str;
        }
        if (str.startsWith("api.")) {
            return str;
        }
        if (str.startsWith("www")) {
            return "api" + str.substring(3);
        }
        throw new RuntimeException("Input was non-empty and doesn't look like a valid partial domain in production: " + str + ". If you were attempting to hit devo or pre-prod, please try following the steps to switch again.");
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String f() {
        return "www.amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public boolean f(String str) {
        return str.matches("api.amazon.*");
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String g() {
        return "firs-ta-g7g.amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public int i() {
        return 443;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String j() {
        return "ATVPDKIKX0DER";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String a(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
        }
        return null;
    }
}
