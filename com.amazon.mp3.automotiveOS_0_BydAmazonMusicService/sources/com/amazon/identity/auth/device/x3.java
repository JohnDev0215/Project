package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class x3 {
    public static final String b = "com.amazon.identity.auth.device.x3";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, String> f655a = new HashMap();

    public x3(String str) {
        a(str);
    }

    public String a() {
        if (!"device_auth_access".equalsIgnoreCase(e())) {
            return null;
        }
        ga.a(b);
        if (this.f655a.containsKey("openid.oa2.access_token")) {
            return this.f655a.get("openid.oa2.access_token");
        }
        if (this.f655a.containsKey("openid.oa2.refresh_token")) {
            return this.f655a.get("openid.oa2.refresh_token");
        }
        return null;
    }

    public String b() {
        return this.f655a.get("openid.oa2.authorization_code");
    }

    public String c() {
        if (!this.f655a.containsKey("openid.identity")) {
            return null;
        }
        String str = this.f655a.get("openid.identity");
        return str.substring(str.lastIndexOf("/") + 1);
    }

    public Bundle d() {
        Bundle bundle = new Bundle();
        Map<String, String> map = this.f655a;
        if (map == null) {
            return bundle;
        }
        for (String str : map.keySet()) {
            if (!TextUtils.equals(str, "openid.oa2.scope") && !TextUtils.equals(str, "openid.oa2.access_token")) {
                bundle.putString(str, this.f655a.get(str));
            }
        }
        return bundle;
    }

    public String e() {
        String str = this.f655a.containsKey("openid.oa2.scope") ? this.f655a.get("openid.oa2.scope") : "device_auth_refresh";
        ga.a(b);
        return str;
    }

    public Boolean f() {
        return "1".equalsIgnoreCase(this.f655a.get("new_account")) ? Boolean.TRUE : Boolean.FALSE;
    }

    public final void a(String str) {
        try {
            String query = new URL(str).getQuery();
            if (TextUtils.isEmpty(query)) {
                return;
            }
            for (String str2 : query.split("&")) {
                String[] strArrSplit = str2.split("=");
                if (strArrSplit.length > 1) {
                    this.f655a.put(strArrSplit[0], URLDecoder.decode(strArrSplit[1]));
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Exception parsing Open ID redirect URL: " + e, e);
        }
    }
}
