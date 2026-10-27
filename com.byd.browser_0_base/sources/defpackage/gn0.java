package defpackage;

import android.accounts.Account;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.c;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;
import org.chromium.chrome.browser.firstrun.TabbedModeFirstRunActivity;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.webapps.SameTaskWebApkActivity;
import org.chromium.chrome.browser.webapps.WebappActivity;
import org.chromium.chrome.browser.webapps.WebappLauncherActivity;
import org.chromium.components.crash.CrashKeys;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: FirstRunFlowSequencer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class gn0 {
    public final a a = new a();
    public boolean b;
    public Boolean c;
    public List<Account> d;

    /* JADX INFO: compiled from: FirstRunFlowSequencer.java */
    public static class a {
    }

    public gn0(Activity activity, dn dnVar) {
        dnVar.h(new c3(25, this));
    }

    public static boolean a(Intent intent, boolean z) {
        return b(z, intent.getBooleanExtra("Extra.FreChromeLaunchIntentIsCct", false) || c.g(intent));
    }

    public static boolean b(boolean z, boolean z2) {
        if (iv.e().g("disable-fre") || c6.e() || ActivityManager.isRunningInUserTestHarness() || z7.t()) {
            return false;
        }
        if (SharedPreferencesManager.getInstance().readBoolean("Chrome.FirstRun.SkippedByPolicy", false) && (z2 || z)) {
            return false;
        }
        if (z) {
            return (SharedPreferencesManager.getInstance().readBoolean("skip_welcome_page", false) || SharedPreferencesManager.getInstance().readBoolean("lightweight_first_run_flow", false)) ? false : true;
        }
        return true;
    }

    public static boolean c(Context context, Intent intent, boolean z) {
        Intent intentA;
        String str;
        boolean z2;
        Intent intent2;
        cp3 cp3Var;
        if (!a(intent, z)) {
            return false;
        }
        String strO = b.o(intent);
        Uri uri = strO != null ? Uri.parse(strO) : null;
        if (uri != null && "content".equals(uri.getScheme())) {
            context.grantUriPermission(context.getPackageName(), uri, 1);
        }
        CrashKeys.getInstance().set(7, "yes");
        if (fn0.g == null) {
            fn0.g = new fn0();
        }
        try {
            if ((intent.getFlags() & 268435456) != 0) {
                int i = WebappLauncherActivity.e;
                String className = intent.getComponent().getClassName();
                ms3 ms3VarA = (className.startsWith(WebappActivity.class.getName()) || className.equals(SameTaskWebApkActivity.class.getName()) || className.equals(WebappLauncherActivity.class.getName())) ? hp3.a(intent) : null;
                if (ms3VarA == null || (cp3Var = ms3VarA.e) == null) {
                    intentA = intent;
                    str = null;
                } else {
                    js3 js3Var = ms3VarA.d;
                    str = js3Var.f;
                    intentA = WebappLauncherActivity.a(intent, cp3Var.a, js3Var.b);
                }
                Iterator it = ApplicationStatus.b().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (((Activity) it.next()) instanceof FirstRunActivity) {
                        z2 = true;
                        break;
                    }
                }
                if (!z || z2) {
                    Intent intent3 = new Intent(context, (Class<?>) ((context instanceof Activity) && DeviceFormFactor.a(context) ? TabbedModeFirstRunActivity.class : FirstRunActivity.class));
                    intent3.putExtra("Extra.ComingFromChromeIcon", TextUtils.equals(intent.getAction(), "android.intent.action.MAIN"));
                    intent3.putExtra("Extra.FreChromeLaunchIntentIsCct", c.g(intent));
                    intent3.putExtra("Extra.FreIntentCreationElapsedRealtimeMs", SystemClock.elapsedRealtime());
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        intent3.putExtra("Extra.FreChromeLaunchIntentExtras", new Bundle(extras));
                    }
                    intent2 = intent3;
                } else {
                    intent2 = new Intent(context, (Class<?>) LightweightFirstRunActivity.class);
                    if (str != null) {
                        intent2.putExtra("org.chromium.chrome.browser.firstrun.AssociatedAppName", str);
                    }
                }
                intent2.putExtra("Extra.FreChromeLaunchIntent", PendingIntent.getActivity(context, 0, intentA, q11.d(false) | 1207959552));
                if (!(context instanceof Activity)) {
                    intent2.addFlags(268435456);
                }
                context.startActivity(intent2, null);
            } else {
                Intent intent4 = new Intent(intent);
                intent4.addFlags(268435456);
                ComponentName componentName = q11.a;
                context.startActivity(intent4, null);
            }
        } catch (ActivityNotFoundException unused) {
        }
        return true;
    }

    public final void d() {
        if (this.c == null || this.d == null || this.b) {
            return;
        }
        this.b = true;
        Bundle bundle = new Bundle();
        bundle.putBoolean("IsChildAccount", this.c.booleanValue());
        e(bundle);
    }

    public abstract void e(Bundle bundle);

    public final void f(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.c.booleanValue();
        this.a.getClass();
        bundle.putBoolean("ShowSyncConsent", false);
        LocaleManager.getInstance().a.getClass();
        bundle.putBoolean("ShowSearchEnginePage", false);
    }
}
