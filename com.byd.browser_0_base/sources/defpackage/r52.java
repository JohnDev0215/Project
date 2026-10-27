package defpackage;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import com.google.android.apps.chrome.appwidget.bookmarks.BookmarkThumbnailWidgetProvider;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.bookmarkswidget.BookmarkWidgetProvider;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.crash.browser.ProcessExitReasonFromSystem;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class r52 extends i9<Void> {
    public final /* synthetic */ b62 h;

    public r52(b62 b62Var) {
        this.h = b62Var;
    }

    public static void o() {
        File file;
        AtomicBoolean atomicBoolean;
        f10 f10Var = f10.b;
        MinidumpUploadServiceImpl.b.set(true);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        int iD = sharedPreferencesManager.d(0, "Chrome.CrashReporting.LastSessionBrowserPid");
        String str = sharedPreferencesManager.d(0, "Chrome.CrashReporting.LastSessionApplicationState") == 1 ? "Foreground2" : "Background2";
        sharedPreferencesManager.m(Process.myPid(), "Chrome.CrashReporting.LastSessionBrowserPid");
        ar2 ar2Var = new ar2(1);
        ar2Var.v(ApplicationStatus.getStateForApplication());
        if (ThreadUtils.g()) {
            ApplicationStatus.e(ar2Var);
        } else {
            PostTask.c(rh3.b, new aw(16, ar2Var));
        }
        if (iD != 0) {
            int iA = ProcessExitReasonFromSystem.a(iD);
            ProcessExitReasonFromSystem.b(iA, "Stability.Android.SystemExitReason.Browser");
            ProcessExitReasonFromSystem.b(iA, "Stability.Android.SystemExitReason.Browser.".concat(str));
        }
        for (String str2 : MinidumpUploadServiceImpl.d) {
            f10Var.getClass();
            String strB = f10.b(str2);
            SharedPreferencesManager sharedPreferencesManager2 = f10Var.a;
            int iD2 = sharedPreferencesManager2.d(0, strB);
            while (true) {
                atomicBoolean = MinidumpUploadServiceImpl.c;
                if (iD2 <= 0) {
                    break;
                }
                oj2.U(1, 2, "Tab.AndroidCrashUpload_".concat(str2));
                if ("Browser".equals(str2)) {
                    atomicBoolean.set(true);
                }
                iD2--;
            }
            for (int iD3 = sharedPreferencesManager2.d(0, f10.a(str2)); iD3 > 0; iD3--) {
                oj2.U(0, 2, "Tab.AndroidCrashUpload_".concat(str2));
                if ("Browser".equals(str2)) {
                    atomicBoolean.set(true);
                }
            }
            sharedPreferencesManager2.m(0, f10.b(str2));
            sharedPreferencesManager2.m(0, f10.a(str2));
        }
        d10 d10Var = new d10(xz.a.getCacheDir());
        d10Var.a();
        d10Var.f();
        File[] fileArrG = d10Var.g(d10.c);
        if (fileArrG.length > 1) {
            for (int i = 1; i < fileArrG.length; i++) {
                d10.k(fileArrG[i]);
            }
        }
        if (fileArrG.length > 0) {
            file = fileArrG[0];
            if (!(d10.c.matcher(file.getName()).find() ? (new Date().getTime() - file.lastModified()) / 3600000 < 12 : false)) {
                d10.k(file);
                file = null;
            }
        } else {
            file = null;
        }
        File[] fileArrE = d10Var.e();
        if (fileArrE.length > 0) {
            fa1.g("ProcessInitHandler", "Attempting to upload %d accumulated crash dumps.", Integer.valueOf(fileArrE.length));
            MinidumpUploadServiceImpl.f();
        }
        if (file != null) {
            PostTask.c(d73.g, new ia1(file));
        }
    }

    @Override // defpackage.i9
    public final Void b() {
        try {
            TraceEvent.q("ChromeBrowserInitializer.onDeferredStartup.doInBackground", null);
            o();
            WebappRegistry webappRegistry = WebappRegistry.b.a;
            int i = BookmarkWidgetProvider.a;
            Context context = xz.a;
            if (AppWidgetManager.getInstance(context) != null) {
                context.sendBroadcast(new Intent(BookmarkWidgetProvider.a(context), null, context, BookmarkThumbnailWidgetProvider.class));
            }
            b62.a(this.h);
            WebappRegistry.b.a.d(null);
            xt1.a();
            return null;
        } finally {
            TraceEvent.C("ChromeBrowserInitializer.onDeferredStartup.doInBackground");
        }
    }

    @Override // defpackage.i9
    public final void l(Void r15) {
        WebappRegistry webappRegistry = WebappRegistry.b.a;
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferences sharedPreferences = webappRegistry.c;
        if (jCurrentTimeMillis - sharedPreferences.getLong("last_cleanup", 0L) < 2419200000L) {
            return;
        }
        HashMap map = webappRegistry.b;
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            ds3 ds3Var = (ds3) entry.getValue();
            String string = ds3Var.b.getString("webapk_package_name", null);
            SharedPreferences sharedPreferences2 = ds3Var.b;
            if (string != null) {
                if (((String) entry.getKey()).startsWith("webapk-") ? SharedPreferencesManager.getInstance().h("webapk_uninstalled_packages").contains(string) ? false : true ^ zt1.e(string) : true) {
                    ds3Var.a();
                    sharedPreferences2.edit().clear().apply();
                    it.remove();
                }
            } else if (jCurrentTimeMillis - sharedPreferences2.getLong("last_used", 0L) >= 7862400000L) {
                ds3Var.a();
                sharedPreferences2.edit().clear().apply();
                it.remove();
            }
        }
        sharedPreferences.edit().putLong("last_cleanup", jCurrentTimeMillis).putStringSet("webapp_set", map.keySet()).apply();
    }
}
