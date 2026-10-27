package org.chromium.chrome.browser.flags;

import J.N;
import android.content.SharedPreferences;
import com.google.common.collect.c;
import com.google.common.collect.e;
import defpackage.ar;
import defpackage.bq;
import defpackage.sk;
import defpackage.uk;
import defpackage.um3;
import defpackage.wk3;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes.dex */
public class CachedFeatureFlags {
    public static final e a;
    public static final wk3 b;
    public static final um3 c;
    public static final uk d;
    public static String e;

    static {
        c.a aVar = new c.a(4);
        aVar.b("CommandLineOnNonRooted", "command_line_on_non_rooted_enabled");
        aVar.b("SwapPixelFormatToFixConvertFromTranslucent", "swap_pixel_format_to_fix_convert_from_translucent");
        aVar.b("StartSurfaceAndroid", "start_surface_enabled");
        aVar.b("TabGridLayoutAndroid", "grid_tab_switcher_enabled");
        aVar.b("TabGroupsAndroid", "tab_group_android_enabled");
        a = aVar.a();
        b = new wk3();
        c = new um3(3, 0);
        d = new uk();
    }

    public static void a(List<sk> list) {
        Iterator<sk> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next().a;
            String strB = (String) a.get(str);
            if (strB == null) {
                strB = ar.c.b(str);
            }
            sk skVar = bq.a;
            SharedPreferencesManager.getInstance().l(strB, N.M09VlOh_(str));
        }
    }

    public static boolean b(String str, boolean z) {
        int i;
        Boolean boolValueOf;
        uk ukVar = d;
        ukVar.d();
        Object obj = c.f;
        if (((Map) obj) != null) {
            String str2 = (String) ((Map) obj).get(str);
            return str2 != null ? Boolean.valueOf(str2).booleanValue() : z;
        }
        wk3 wk3Var = b;
        synchronized (wk3Var.a) {
            Boolean bool = (Boolean) wk3Var.a.get(str);
            if (bool != null) {
                return bool.booleanValue();
            }
            if (ukVar.c() && (i = ukVar.b.get()) != 1) {
                if (i != 2) {
                    boolValueOf = (i == 3 || i == 4) ? Boolean.valueOf(z) : null;
                } else {
                    SharedPreferences sharedPreferencesB = uk.b();
                    if (sharedPreferencesB.contains(str)) {
                        boolValueOf = Boolean.valueOf(sharedPreferencesB.getBoolean(str, false));
                    }
                }
            }
            if (boolValueOf == null) {
                boolValueOf = Boolean.valueOf(SharedPreferencesManager.getInstance().readBoolean(str, z));
            }
            wk3Var.a.put(str, boolValueOf);
            return boolValueOf.booleanValue();
        }
    }

    @CalledByNative
    public static String getReachedCodeProfilerTrialGroup() {
        if (e == null) {
            e = SharedPreferencesManager.getInstance().g("reached_code_profiler_group", "");
        }
        return e;
    }

    @CalledByNative
    public static boolean isEnabled(String str, boolean z) {
        int i;
        Boolean boolValueOf;
        uk ukVar = d;
        ukVar.d();
        String strB = (String) a.get(str);
        if (strB == null) {
            strB = ar.c.b(str);
        }
        wk3 wk3Var = b;
        synchronized (wk3Var.a) {
            Boolean bool = (Boolean) wk3Var.a.get(strB);
            if (bool != null) {
                return bool.booleanValue();
            }
            if (ukVar.c() && (i = ukVar.b.get()) != 1) {
                if (i != 2) {
                    boolValueOf = (i == 3 || i == 4) ? Boolean.valueOf(z) : null;
                } else {
                    SharedPreferences sharedPreferencesB = uk.b();
                    if (sharedPreferencesB.contains(strB)) {
                        boolValueOf = Boolean.valueOf(sharedPreferencesB.getBoolean(strB, false));
                    }
                }
            }
            if (boolValueOf == null) {
                SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                boolValueOf = sharedPreferencesManager.contains(strB) ? Boolean.valueOf(sharedPreferencesManager.readBoolean(strB, false)) : Boolean.valueOf(z);
            }
            wk3Var.a.put(strB, boolValueOf);
            return boolValueOf.booleanValue();
        }
    }
}
