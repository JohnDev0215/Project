package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f177a = "com.amazon.identity.auth.device.ab";

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3) {
        String strC;
        try {
            if (z3) {
                String host = new URL(str).getHost();
                if (!TextUtils.isEmpty(host)) {
                    strC = "." + host.trim();
                } else {
                    throw new MalformedURLException("Host of the url is null");
                }
            } else {
                strC = EnvironmentUtils.c.c(new URL(str).getHost());
            }
            MAPCookie mAPCookie = new MAPCookie(str2, str3, strC, str5, str4, null, z, z2);
            bb.a(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(str, mAPCookie.d());
            bb.a(context);
        } catch (MalformedURLException e) {
            yd.a("failedParsingCookieDomain", "MalformedURLException");
            Log.e(ga.a(f177a), "Could not parse cookie domain from malformed URL.", e);
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri uri = Uri.parse(str);
        if (uri == null) {
            Log.e(ga.a(f177a), "error happens when parsing the url string");
            return null;
        }
        return String.format(Locale.US, "%s://%s", uri.getScheme(), uri.getAuthority());
    }
}
