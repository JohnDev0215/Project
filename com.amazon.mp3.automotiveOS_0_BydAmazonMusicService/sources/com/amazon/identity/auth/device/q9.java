package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class q9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f514a = "com.amazon.identity.auth.device.q9";

    public static String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("sign_in_domain");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String string2 = bundle.getString("com.amazon.identity.ap.domain");
        return TextUtils.isEmpty(string2) ? bundle.getString("com.amazon.dcp.sso.AddAccount.options.AmazonDomain") : string2;
    }

    public static String b(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(MAPAccountManager.KEY_REGISTRATION_DOMAIN);
        return !TextUtils.isEmpty(string) ? string : a(bundle);
    }

    public static String b(Context context, String str) {
        String strD = t5.a(context).a().d(str, "key_panda_marketplace_header");
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        yd.a("getMarketplaceHeaderForDirectedId_FromLegacyDB", new String[0]);
        return EnvironmentUtils.c.e(t5.a(context).a().d(str, "authDomain"));
    }

    public static String a(Context context, String str) {
        String strD = t5.a(context).a().d(str, "key_auth_portal_endpoint");
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        yd.a("getAuthPortalHostForDirectedId_FromLegacyDB", new String[0]);
        return EnvironmentUtils.c.a(t5.a(context).a().d(str, "authDomain"));
    }

    public static String a(t5 t5Var, String str) {
        String strD = t5Var.a().d(str, "key_panda_endpoint");
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        yd.a("getPandaHostForDirectId_FromLegacyDB", new String[0]);
        String strD2 = t5Var.a().d(str, "x-amzn-identity-auth-domain");
        if (TextUtils.isEmpty(strD2)) {
            strD2 = t5Var.a().d(str, "authDomain");
            Log.i(ga.a(f514a), "Cannot get panda registration domain with AccountManagerConstants.PANDA_DOMAIN_KEY, fall back to sign in domain: " + strD2);
        }
        Log.i(ga.a(f514a), String.format("Use legacy partial domain %s in db to construct Panda host", strD2));
        return EnvironmentUtils.c.e(strD2);
    }

    public static String a(String str) {
        if (TextUtils.equals("na.account.amazon.com", str)) {
            yd.b("ConvertLWADomain:NA", new String[0]);
            return "www.amazon.com";
        }
        if (TextUtils.equals("eu.account.amazon.com", str)) {
            yd.b("ConvertLWADomain:EU", new String[0]);
            return "www.amazon.co.uk";
        }
        if (!TextUtils.equals("apac.account.amazon.com", str)) {
            return str;
        }
        yd.b("ConvertLWADomain:FE", new String[0]);
        return "www.amazon.co.jp";
    }
}
