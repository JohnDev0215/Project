package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.a4;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.b4;
import com.amazon.identity.auth.device.bootstrapSSO.BootstrapSSOService;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.f8;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.i5;
import com.amazon.identity.auth.device.ia;
import com.amazon.identity.auth.device.la;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t4;
import com.amazon.identity.auth.device.t9;
import com.amazon.identity.auth.device.yd;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class IsolatedModeSwitcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile Boolean f321a;
    public static volatile Boolean b;

    private IsolatedModeSwitcher() {
    }

    private static boolean doesAppNeedToSwitchToSSOMode(Context context) {
        if (!isAppInRuntimeIsolatedMode(context) || new MAPAccountManager(context).getAccount() != null) {
            return false;
        }
        Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "No account detected in isolated mode.");
        return true;
    }

    public static boolean doesAppSupportRuntimeIsolatedMode(Context context) {
        if (f321a == null) {
            f321a = Boolean.valueOf(!TextUtils.isEmpty(ia.a(context, context.getPackageName(), "MAPRuntimeIsolateForAccountPool")));
        }
        if (f321a.booleanValue()) {
            Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "Application supports runtime isolated mode switch.");
        }
        return f321a.booleanValue();
    }

    public static synchronized boolean isAppInIsolatedMode(Context context) {
        if (isAppInStaticIsolatedMode(context)) {
            Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "The application is in static isolated mode");
            return true;
        }
        return isAppInRuntimeIsolatedMode(context);
    }

    public static boolean isAppInRuntimeIsolatedMode(Context context) {
        if (!doesAppSupportRuntimeIsolatedMode(context)) {
            return false;
        }
        boolean zBooleanValue = Boolean.valueOf(new f8(context, "runtime_isolated_mode", 0).f313a.getBoolean("isolated", false)).booleanValue();
        Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "Restoring current runtime isolated mode: " + zBooleanValue);
        return zBooleanValue;
    }

    public static boolean isAppInStaticIsolatedMode(Context context) {
        if (ia.a(context, context.getPackageName(), "MAPIsolateApplication", false).booleanValue()) {
            return true;
        }
        if (b != null) {
            return b.booleanValue();
        }
        String packageName = context.getPackageName();
        ArrayList<String> arrayList = new ArrayList();
        String strA = ia.a(context, packageName, "MAPIsolateApplicationOnDevice");
        if (!TextUtils.isEmpty(strA)) {
            for (String str : strA.split("\\|")) {
                arrayList.add(str);
            }
        }
        if (e.a(arrayList)) {
            return false;
        }
        i5 i5Var = new i5(context);
        Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "App needs isolation on devices: " + arrayList.toString());
        for (String str2 : arrayList) {
            if (str2.equalsIgnoreCase("FireOS")) {
                if (qe.a() && qe.l(context)) {
                    return setAppInStaticIsolatedModeAndReturn(true, "FireOS");
                }
            }
            if (str2.equalsIgnoreCase("Canary")) {
                if (la.a("com.amazon.canary", i5Var.f382a) && t9.e(i5Var.f382a)) {
                    return setAppInStaticIsolatedModeAndReturn(true, "Canary");
                }
            }
            if (str2.equalsIgnoreCase("Grover")) {
                if (la.a("com.amazon.fv", i5Var.f382a) && t9.e(i5Var.f382a)) {
                    return setAppInStaticIsolatedModeAndReturn(true, "Grover");
                }
            }
            if (str2.equalsIgnoreCase("3P") && i5Var.f()) {
                return setAppInStaticIsolatedModeAndReturn(true, "3P");
            }
        }
        return setAppInStaticIsolatedModeAndReturn(false, null);
    }

    private static void preActionOnSwitch(Context context, boolean z) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) BootstrapSSOService.class), z ? 2 : 1, 1);
        } catch (IllegalArgumentException unused) {
            ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher", "Component Class %s not found in manifest", "BootstrapSSOService");
        }
    }

    private static boolean setAppInStaticIsolatedModeAndReturn(boolean z, String str) {
        if (z && !TextUtils.isEmpty(str)) {
            Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), String.format(Locale.ENGLISH, "Current platform is %s, entering static isolation mode", str));
        }
        b = Boolean.valueOf(z);
        return z;
    }

    public static synchronized void switchAppToIsolatedModeIfNecessary(Context context, String str) {
        if (doesAppSupportRuntimeIsolatedMode(context)) {
            if (!TextUtils.isEmpty(str)) {
                String strA = ia.a(context, context.getPackageName(), "MAPRuntimeIsolateForAccountPool");
                ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher", "The account is in account pool: %s. Runtime isolated mode needed for account pool: %s.", str, strA);
                if (!TextUtils.equals(str, strA)) {
                    Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "Keep application in SSO mode.");
                    return;
                }
                Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "The application is entering isolated mode.");
                preActionOnSwitch(context, true);
                writeIsolatedModeStateIntoStorage(context, true);
                a4.a(new b4(context)).a(Feature.IsolateApplication, context);
                e.h(context);
                t4.a(context).a();
                ga.c("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher", "Finish generating local common info (version: %d) for isolated mode.", 1);
                yd.a("EnterRuntimeIsolatedMode:" + strA, new String[0]);
            }
        }
    }

    public static synchronized void switchAppToSSOMode(Context context) {
        Log.i(ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher"), "The application is entering SSO mode.");
        preActionOnSwitch(context, false);
        writeIsolatedModeStateIntoStorage(context, false);
        a4.a(new b4(context)).a(Feature.IsolateApplication, context);
        e.h(context);
        yd.a("ExitRuntimeIsolatedMode", new String[0]);
    }

    public static synchronized void switchAppToSSOModeIfNecessary(Context context) {
        if (doesAppNeedToSwitchToSSOMode(context)) {
            switchAppToSSOMode(context);
        } else {
            ga.a("com.amazon.identity.auth.device.framework.IsolatedModeSwitcher");
        }
    }

    private static void writeIsolatedModeStateIntoStorage(Context context, boolean z) {
        new f8(context, "runtime_isolated_mode", 0).a("isolated", Boolean.valueOf(z));
    }
}
