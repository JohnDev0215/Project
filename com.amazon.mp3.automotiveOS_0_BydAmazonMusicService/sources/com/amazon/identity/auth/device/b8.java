package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b8 extends s7 {
    public static final String b = "com.amazon.identity.auth.device.b8";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u7 f241a;

    public b8(u7 u7Var) {
        this.f241a = u7Var;
    }

    public static String b(String str, String str2) {
        return TextUtils.isEmpty(str2) ? String.format("%s.json.%s", AccountConstants.TOKEN_TYPE_COOKIES, str) : String.format("%s.json.%s#%s", AccountConstants.TOKEN_TYPE_COOKIES, str, str2);
    }

    public static String c(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return String.format("json.%s#%s", str, str2);
        }
        return "json." + str;
    }

    public static String d(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : String.format("%s#%s", str, str2);
    }

    @Override // com.amazon.identity.auth.device.s7
    public boolean a(Context context, String str) {
        throw new IllegalStateException("Clear cookies is not supported");
    }

    @Override // com.amazon.identity.auth.device.s7
    public Map<String, String> a(String str, List<MAPCookie> list, String str2, String str3) {
        if (!list.isEmpty()) {
            return a(str2, str3, list);
        }
        ga.a(b);
        return Collections.emptyMap();
    }

    public static String b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return String.format("%s.json.%s.%s", AccountConstants.TOKEN_TYPE_ACTOR_COOKIES, str2, str);
        }
        return String.format("%s.json.%s.%s#%s", AccountConstants.TOKEN_TYPE_ACTOR_COOKIES, str2, str, str3);
    }

    @Override // com.amazon.identity.auth.device.s7
    public List<MAPCookie> a(String str, String str2, String str3) {
        List<MAPCookie> listB;
        if (TextUtils.isEmpty(str)) {
            listB = v9.b(this.f241a.b("com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", d(str2, str3)), null);
            if (e.a(listB)) {
                listB = v9.a(this.f241a.b("com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", c(str2, str3)), (String) null);
                if (!e.a(listB)) {
                    va.f637a.execute(new a8(this, str, str2, str3, new ArrayList(listB)));
                }
            }
        } else {
            listB = v9.b(this.f241a.c(str, a(str2, str3)), str);
            if (e.a(listB)) {
                listB = v9.a(this.f241a.c(str, b(str2, str3)), str);
                if (!e.a(listB)) {
                    va.f637a.execute(new a8(this, str, str2, str3, new ArrayList(listB)));
                }
            }
        }
        return listB;
    }

    @Override // com.amazon.identity.auth.device.s7
    public List<MAPCookie> a(String str, String str2, String str3, String str4) {
        return v9.a(this.f241a.c(str, b(str2, str3, str4)), str);
    }

    @Override // com.amazon.identity.auth.device.s7
    public void a(String str, String str2, String str3, List<MAPCookie> list) {
        if (TextUtils.isEmpty(str)) {
            HashMap map = new HashMap();
            String strD = d(str2, str3);
            Bundle bundle = new Bundle();
            bundle.putStringArray(CookieKeys.KEY_COOKIES, v9.a(list));
            map.put(strD, ma.a(bundle));
            map.put(c(str2, str3), v9.b(list));
            this.f241a.a("com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", map);
            return;
        }
        this.f241a.b(str, a(str2, str3, list));
    }

    @Override // com.amazon.identity.auth.device.s7
    public void a(String str, String str2, String str3, String str4, List<MAPCookie> list) {
        u7 u7Var = this.f241a;
        HashMap map = new HashMap();
        map.put(b(str2, str3, str4), v9.b(list));
        u7Var.b(str, map);
    }

    public static Map<String, String> a(String str, String str2, List<MAPCookie> list) {
        HashMap map = new HashMap();
        String strA = a(str, str2);
        Bundle bundle = new Bundle();
        bundle.putStringArray(CookieKeys.KEY_COOKIES, v9.a(list));
        map.put(strA, ma.a(bundle));
        map.put(b(str, str2), v9.b(list));
        return map;
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return String.format("%s.%s", AccountConstants.TOKEN_TYPE_COOKIES, str);
        }
        return String.format("%s.%s#%s", AccountConstants.TOKEN_TYPE_COOKIES, str, str2);
    }
}
