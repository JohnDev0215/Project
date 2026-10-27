package org.chromium.chrome.browser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import defpackage.bs3;
import defpackage.ds3;
import defpackage.i9;
import defpackage.we;
import defpackage.xz;
import defpackage.z70;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.chrome.browser.webapps.f;
import org.chromium.components.webapps.R$string;
import org.chromium.components.webapps.WebappsUtils;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutHelper {
    public static final HashMap a = new HashMap();
    public static final c b = new c();

    public class a extends i9<Intent> {
        public static final /* synthetic */ int v = 0;
        public final /* synthetic */ Bitmap h;
        public final /* synthetic */ String i;
        public final /* synthetic */ String j;
        public final /* synthetic */ String k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ int n;
        public final /* synthetic */ int o;
        public final /* synthetic */ long p;
        public final /* synthetic */ long q;
        public final /* synthetic */ String r;
        public final /* synthetic */ boolean s;
        public final /* synthetic */ int t;
        public final /* synthetic */ String u;

        public a(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap, boolean z, int i, int i2, int i3, long j, long j2) {
            this.h = bitmap;
            this.i = str;
            this.j = str2;
            this.k = str3;
            this.l = str4;
            this.m = str5;
            this.n = i;
            this.o = i2;
            this.p = j;
            this.q = j2;
            this.r = str6;
            this.s = z;
            this.t = i3;
            this.u = str7;
        }

        @Override // defpackage.i9
        public final Intent b() {
            Intent intentA = ShortcutHelper.a(this.i, this.j, this.k, this.l, this.m, we.a(this.h), 3, this.n, this.o, this.p, this.q, this.r.isEmpty(), this.s);
            intentA.putExtra("org.chromium.chrome.browser.webapp_mac", Base64.encodeToString(bs3.b(this.j), 0));
            intentA.putExtra("org.chromium.chrome.browser.webapp_source", this.t);
            return intentA;
        }

        @Override // defpackage.i9
        public final void l(Intent intent) {
            Intent intent2 = intent;
            ShortcutHelper.b.getClass();
            String str = this.i;
            c.a(str, this.u, this.h, this.s, intent2);
            WebappRegistry webappRegistry = WebappRegistry.b.a;
            z70 z70Var = new z70(intent2, 2, str);
            webappRegistry.getClass();
            new f(webappRegistry, str, z70Var).c(i9.e);
        }
    }

    public class b extends i9<String> {
        public final /* synthetic */ Bitmap h;
        public final /* synthetic */ ds3 i;

        public b(Bitmap bitmap, ds3 ds3Var) {
            this.h = bitmap;
            this.i = ds3Var;
        }

        @Override // defpackage.i9
        public final String b() {
            return we.a(this.h);
        }

        @Override // defpackage.i9
        public final void l(String str) {
            this.i.b.edit().putString("splash_icon", str).apply();
        }
    }

    public static class c {
        public static void a(String str, String str2, Bitmap bitmap, boolean z, Intent intent) {
            if (!WebappsUtils.b()) {
                Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
                intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
                intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
                xz.a.sendBroadcast(intent2);
                Toast.b(0, xz.a, xz.a.getString(R$string.added_to_homescreen, str2)).e();
                return;
            }
            Context context = xz.a;
            if (bitmap != null) {
                try {
                    ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(new ShortcutInfo.Builder(context, str).setShortLabel(str2).setLongLabel(str2).setIcon(z ? Icon.createWithAdaptiveBitmap(bitmap) : Icon.createWithBitmap(bitmap)).setIntent(intent).build(), null);
                } catch (IllegalStateException unused) {
                }
            } else {
                Log.e("cr_WebappsUtils", "Failed to find an icon for " + str2 + ", not adding.");
            }
        }
    }

    public static Intent a(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3, long j, long j2, boolean z, boolean z2) {
        Intent intent = new Intent();
        Intent intent2 = intent.setPackage(xz.a.getPackageName());
        b.getClass();
        intent2.setAction("com.google.android.apps.chrome.webapps.WebappManager.ACTION_START_WEBAPP").putExtra("org.chromium.chrome.browser.webapp_id", str).putExtra("org.chromium.chrome.browser.webapp_url", str2).putExtra("org.chromium.chrome.browser.webapp_scope", str3).putExtra("org.chromium.chrome.browser.webapp_name", str4).putExtra("org.chromium.chrome.browser.webapp_short_name", str5).putExtra("org.chromium.chrome.browser.webapp_icon", str6).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", i).putExtra("org.chromium.chrome.browser.webapp_display_mode", i2).putExtra("org.chromium.content_public.common.orientation", i3).putExtra("org.chromium.chrome.browser.theme_color", j).putExtra("org.chromium.chrome.browser.background_color", j2).putExtra("org.chromium.chrome.browser.is_icon_generated", z).putExtra("org.chromium.chrome.browser.webapp_icon_adaptive", z2);
        return intent;
    }

    @CalledByNative
    public static void addShortcut(String str, String str2, String str3, Bitmap bitmap, boolean z, int i, String str4) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
        intent.putExtra("org.chromium.chrome.browser.webapp_id", str);
        intent.putExtra("org.chromium.chrome.browser.webapp_source", i);
        intent.setPackage(xz.a.getPackageName());
        b.getClass();
        c.a(str, str3, bitmap, z, intent);
    }

    @CalledByNative
    public static void addWebapp(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap, boolean z, int i, int i2, int i3, long j, long j2) {
        new a(str, str2, str3, str5, str6, str7, str4, bitmap, z, i, i2, i3, j, j2).c(i9.e);
    }

    public static String b(String str) {
        Uri uri = Uri.parse(str);
        String encodedPath = uri.getEncodedPath();
        int iLastIndexOf = encodedPath == null ? -1 : encodedPath.lastIndexOf("/");
        if (iLastIndexOf < 0) {
            encodedPath = "/";
        } else if (iLastIndexOf < encodedPath.length() - 1) {
            encodedPath = encodedPath.substring(0, iLastIndexOf + 1);
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.encodedPath(encodedPath);
        builderBuildUpon.fragment("");
        builderBuildUpon.query("");
        return builderBuildUpon.build().toString();
    }

    @CalledByNative
    public static boolean doesOriginContainAnyInstalledTwa(String str) {
        WebappRegistry webappRegistry = WebappRegistry.b.a;
        String lowerCase = str.toLowerCase(Locale.getDefault());
        return webappRegistry.d.g().contains(lowerCase) && !webappRegistry.b().contains(lowerCase);
    }

    @CalledByNative
    public static boolean doesOriginContainAnyInstalledWebApk(String str) {
        WebappRegistry webappRegistry = WebappRegistry.b.a;
        String lowerCase = str.toLowerCase(Locale.getDefault());
        for (ds3 ds3Var : webappRegistry.b.values()) {
            String string = ds3Var.a.startsWith("webapk-") ? ds3Var.b.getString("scope", "") : "";
            if (!string.isEmpty() && string.startsWith(lowerCase)) {
                return true;
            }
        }
        return false;
    }

    @CalledByNative
    public static String[] getOriginsWithInstalledWebApksOrTwas() {
        HashSet hashSetA = WebappRegistry.b.a.a();
        return (String[]) hashSetA.toArray(new String[hashSetA.size()]);
    }

    @CalledByNative
    public static void setForceWebApkUpdate(String str) {
        ds3 ds3VarC = WebappRegistry.b.a.c(str);
        if (ds3VarC != null) {
            ds3VarC.b(true);
        }
    }

    @CalledByNative
    public static void storeWebappSplashImage(String str, Bitmap bitmap) {
        ds3 ds3VarC = WebappRegistry.b.a.c(str);
        if (ds3VarC == null) {
            a.put(str, bitmap);
        } else {
            new b(bitmap, ds3VarC).c(i9.e);
        }
    }
}
