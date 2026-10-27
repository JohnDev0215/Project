package org.chromium.chrome.browser.browserservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import defpackage.a8;
import defpackage.ap1;
import defpackage.dp1;
import defpackage.ds3;
import defpackage.f02;
import defpackage.ft1;
import defpackage.lf3;
import defpackage.ns3;
import defpackage.pj;
import defpackage.pp;
import defpackage.s50;
import defpackage.sf3;
import defpackage.xa3;
import defpackage.xz;
import defpackage.z01;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* JADX INFO: loaded from: classes.dex */
public class InstalledWebappBroadcastReceiver extends BroadcastReceiver {
    public static final HashSet e = new HashSet(Arrays.asList("android.intent.action.PACKAGE_DATA_CLEARED", "android.intent.action.PACKAGE_FULLY_REMOVED"));
    public final a a;
    public final z01 b;
    public final pj c;
    public final f02 d;

    public static class a {
    }

    public InstalledWebappBroadcastReceiver() {
        a aVar = new a();
        z01 z01Var = new z01();
        ((s50) pp.d()).a.getClass();
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        a8.n(sharedPreferencesManager);
        pj pjVar = new pj(sharedPreferencesManager);
        f02 f02VarH = ((s50) pp.d()).h();
        this.a = aVar;
        this.b = z01Var;
        this.c = pjVar;
        this.d = f02VarH;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra;
        String schemeSpecificPart;
        z01 z01Var = this.b;
        if (intent == null || !e.contains(intent.getAction()) || (intExtra = intent.getIntExtra("android.intent.extra.UID", -1)) == -1) {
            return;
        }
        boolean zEquals = "android.intent.action.PACKAGE_FULLY_REMOVED".equals(intent.getAction());
        if (zEquals && intent.getData() != null && (schemeSpecificPart = intent.getData().getSchemeSpecificPart()) != null && schemeSpecificPart.startsWith("org.chromium.webapk")) {
            SharedPreferencesManager.getInstance().b("webapk_uninstalled_packages", schemeSpecificPart);
            String strB = ns3.b(schemeSpecificPart);
            WebappRegistry webappRegistry = WebappRegistry.b.a;
            webappRegistry.d(strB);
            ds3 ds3VarC = webappRegistry.c(strB);
            if (ds3VarC != null) {
                ds3VarC.b.edit().putLong("webapk_uninstall_timestamp", System.currentTimeMillis()).apply();
            }
        }
        xa3 xa3VarQ = xa3.q("BrowserServices.ClientAppDataLoad");
        try {
            if (z01Var.e().contains(String.valueOf(intExtra))) {
                xa3VarQ.close();
                this.a.getClass();
                String strB2 = z01.b(intExtra);
                Set<String> setEmptySet = Collections.emptySet();
                SharedPreferences sharedPreferences = z01Var.a;
                Set<String> stringSet = sharedPreferences.getStringSet(strB2, setEmptySet);
                Set<String> stringSet2 = sharedPreferences.getStringSet(z01.c(intExtra), Collections.emptySet());
                Iterator<String> it = stringSet2.iterator();
                while (it.hasNext()) {
                    ft1 ft1VarB = ft1.b(it.next());
                    if (ft1VarB != null) {
                        f02 f02Var = this.d;
                        dp1 dp1Var = f02Var.b;
                        dp1Var.getClass();
                        String string = ft1VarB.toString();
                        ap1 ap1Var = new ap1(dp1Var, ft1VarB);
                        sf3 sf3Var = dp1Var.b;
                        sf3Var.getClass();
                        sf3Var.a(Uri.parse(string), new lf3(sf3Var, xz.a.getResources().getString(R$string.notification_category_group_general), ap1Var));
                        f02Var.c.a.d(4, ft1VarB);
                    }
                }
                String string2 = sharedPreferences.getString(z01.a(intExtra), null);
                int i = ClearDataDialogActivity.D;
                Intent intent2 = new Intent(context, (Class<?>) ClearDataDialogActivity.class);
                intent2.putExtra("org.chromium.chrome.extra.app_name", string2);
                intent2.putExtra("org.chromium.chrome.extra.domains", new ArrayList(stringSet));
                intent2.putExtra("org.chromium.chrome.extra.origins", new ArrayList(stringSet2));
                intent2.putExtra("org.chromium.chrome.extra.app_uninstalled", zEquals);
                intent2.addFlags(268959744);
                context.startActivity(intent2);
                String string3 = sharedPreferences.getString(z01.d(intExtra), null);
                SharedPreferencesManager sharedPreferencesManager = this.c.a;
                sharedPreferencesManager.j("trusted_web_activity_disclosure_accepted_packages", string3);
                sharedPreferencesManager.j("Chrome.TrustedWebActivities.DisclosureAcceptedPackages", string3);
                if (zEquals) {
                    HashSet hashSetE = z01Var.e();
                    hashSetE.remove(String.valueOf(intExtra));
                    sharedPreferences.edit().putStringSet("trusted_web_activity_uids", hashSetE).apply();
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString(z01.a(intExtra), null);
                    editorEdit.putString(z01.d(intExtra), null);
                    editorEdit.putStringSet(z01.b(intExtra), null);
                    editorEdit.putStringSet(z01.c(intExtra), null);
                    editorEdit.apply();
                }
            }
        } finally {
            try {
                xa3VarQ.close();
            } catch (Throwable unused) {
            }
        }
    }
}
