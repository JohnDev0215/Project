package org.chromium.chrome.browser.omnibox.geo;

import J.N;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import defpackage.c6;
import defpackage.hs0;
import defpackage.oj2;
import defpackage.oo3;
import defpackage.xz;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.omnibox.geo.a;
import org.chromium.chrome.browser.omnibox.geo.b;
import org.chromium.chrome.browser.omnibox.geo.f;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: loaded from: classes2.dex */
public class GeolocationHeader {
    public static long a = Long.MAX_VALUE;

    public static String a(Location location) {
        if (location == null) {
            return null;
        }
        long time = location.getTime() * 1000;
        int latitude = (int) (location.getLatitude() * 1.0E7d);
        int longitude = (int) (location.getLongitude() * 1.0E7d);
        int accuracy = (int) (location.getAccuracy() * 1000.0f);
        a.C0098a c0098aS = a.S();
        c0098aS.y();
        a.O((a) c0098aS.f, latitude);
        c0098aS.y();
        a.P((a) c0098aS.f, longitude);
        a aVarBuild = c0098aS.build();
        b.a aVarW = b.W();
        aVarW.y();
        b.P((b) aVarW.f, aVarBuild);
        aVarW.y();
        b.T((b) aVarW.f);
        aVarW.y();
        b.Q((b) aVarW.f);
        aVarW.y();
        b.U((b) aVarW.f, time);
        aVarW.y();
        b.S((b) aVarW.f, accuracy);
        return Base64.encodeToString(aVarW.build().q(), 10);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(org.chromium.chrome.browser.omnibox.geo.f r10) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.geo.GeolocationHeader.b(org.chromium.chrome.browser.omnibox.geo.f):java.lang.String");
    }

    public static int c(Profile profile, String str, boolean z) {
        TraceEvent traceEventH0 = TraceEvent.H0("GeolocationHeader.geoHeaderStateForUrl", null);
        try {
            if (profile.h()) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return 1;
            }
            if (!N.M$l72hrq(str)) {
                if (traceEventH0 == null) {
                    return 2;
                }
                traceEventH0.close();
                return 2;
            }
            Uri uri = Uri.parse(str);
            if (!"https".equals(uri.getScheme())) {
                if (traceEventH0 == null) {
                    return 3;
                }
                traceEventH0.close();
                return 3;
            }
            if (!hasGeolocationPermission()) {
                if (z) {
                    oj2.U(5, 8, "Geolocation.HeaderSentOrNot");
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return 4;
            }
            boolean zMwP0orC3 = N.MwP0orC3(profile, uri.toString());
            String string = uri.toString();
            if (!(!(zMwP0orC3 && Integer.valueOf(N.MrCE1oma(profile, 4, string, string)).intValue() == 1))) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return 0;
            }
            if (z) {
                oj2.U(0, 8, "Geolocation.HeaderSentOrNot");
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return 4;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String d(java.lang.String r12, org.chromium.chrome.browser.profiles.Profile r13, org.chromium.chrome.browser.tab.Tab r14) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.geo.GeolocationHeader.d(java.lang.String, org.chromium.chrome.browser.profiles.Profile, org.chromium.chrome.browser.tab.Tab):java.lang.String");
    }

    public static int e() {
        TraceEvent traceEventH0 = TraceEvent.H0("GeolocationHeader.getLocationSource", null);
        try {
            try {
                int i = Settings.Secure.getInt(xz.a.getContentResolver(), "location_mode");
                if (i == 3) {
                    if (traceEventH0 == null) {
                        return 0;
                    }
                    traceEventH0.close();
                    return 0;
                }
                if (i == 1) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return 2;
                }
                if (i == 2) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return 1;
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return 3;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (Settings.SettingNotFoundException unused2) {
            Log.e("cr_GeolocationHeader", "Error getting the LOCATION_MODE");
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return 3;
        }
    }

    public static void f(Profile profile, TemplateUrlService templateUrlService) {
        LocationManager locationManager;
        Location lastKnownLocation;
        if (profile != null && hasGeolocationPermission()) {
            if (c(profile, templateUrlService.d("some_query", null), false) == 0) {
                if (a == Long.MAX_VALUE) {
                    a = SystemClock.elapsedRealtime();
                }
                Context context = xz.a;
                Object obj = ThreadUtils.a;
                if (hs0.c(context, "android.permission.ACCESS_COARSE_LOCATION") && hs0.a == null && (((lastKnownLocation = (locationManager = (LocationManager) context.getSystemService("location")).getLastKnownLocation("network")) == null || hs0.b(lastKnownLocation) > 300000) && locationManager.isProviderEnabled("network"))) {
                    hs0.a aVar = new hs0.a(locationManager);
                    hs0.a = aVar;
                    try {
                        locationManager.requestSingleUpdate("network", aVar, (Looper) null);
                    } catch (NullPointerException unused) {
                        hs0.a.d = true;
                    }
                }
                oo3.b(xz.a);
            }
        }
    }

    public static boolean g(f.b bVar) {
        if (bVar == null || bVar.b == null) {
            return true;
        }
        String str = bVar.a;
        if (str == null || str.indexOf(95) < 0) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        return lowerCase.contains("_nomap") || lowerCase.contains("_optout");
    }

    @CalledByNative
    public static String getGeoHeader(String str, Profile profile) {
        if (profile == null) {
            return null;
        }
        return d(str, profile, null);
    }

    @CalledByNative
    public static boolean hasGeolocationPermission() {
        return c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }
}
