package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.notifications.channels.LocaleChangedBroadcastReceiver;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.quickactionsearchwidget.QuickActionSearchWidgetProvider;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.safe_browsing.SafeBrowsingApiBridge;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;
import org.chromium.ui.accessibility.AccessibilityState;
import org.chromium.ui.base.SelectFileDialog;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o90 implements Runnable {
    public final /* synthetic */ int e;

    public /* synthetic */ o90(int i) {
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        File[] fileArrListFiles;
        int i = 3;
        int i2 = 0;
        switch (this.e) {
            case 0:
                PostTask.c(d73.g, new qe3(i));
                break;
            case 1:
                break;
            case 2:
                n3.x();
                break;
            case 3:
                SafeBrowsingApiBridge.ensureInitialized();
                break;
            case 4:
                Object obj = b62.d;
                r82.c();
                break;
            case 5:
                Object obj2 = b62.d;
                sk skVar = bq.a;
                if (N.M09VlOh_("FeatureNotificationGuide")) {
                }
                break;
            case 6:
                SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                Set<String> setH = sharedPreferencesManager.h("webapk_uninstalled_packages");
                if (!setH.isEmpty()) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    WebappRegistry.b.a.d(null);
                    for (String str : setH) {
                        oj2.O("WebApk.Uninstall.Browser", true);
                        ds3 ds3VarC = WebappRegistry.b.a.c(ns3.b(str));
                        if (ds3VarC != null) {
                            SharedPreferences sharedPreferences = ds3VarC.b;
                            long j = sharedPreferences.getLong("webapk_uninstall_timestamp", 0L);
                            if (j == 0) {
                                j = jCurrentTimeMillis;
                            }
                            N.MdWkMq2F(sharedPreferences.getString("webapk_manifest_url", null), 0, sharedPreferences.getInt("webapk_version_code", 0), ds3VarC.b.getInt("launch_count", 0), j - sharedPreferences.getLong("webapk_install_timestamp", 0L));
                        }
                    }
                    sharedPreferencesManager.p("webapk_uninstalled_packages", new HashSet());
                    break;
                }
                break;
            case 7:
                Object obj3 = b62.d;
                sk skVar2 = bq.a;
                if (N.M09VlOh_("VideoTutorials")) {
                    ((em3) N.MBuXqyoS(Profile.d())).d(new fm3(i2));
                } else {
                    SharedPreferencesManager.getInstance().removeKey("Chrome.VideoTutorials.ShareUrls");
                }
                break;
            case 8:
                int i3 = LocaleChangedBroadcastReceiver.a;
                xz.b(true);
                break;
            case 9:
                u82 u82Var = QuickActionSearchWidgetProvider.a;
                Object obj4 = ThreadUtils.a;
                Context context = xz.a;
                context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) QuickActionSearchWidgetProvider.QuickActionSearchWidgetProviderSearch.class), 1, 1);
                Context context2 = xz.a;
                context2.getPackageManager().setComponentEnabledSetting(new ComponentName(context2, (Class<?>) QuickActionSearchWidgetProvider.QuickActionSearchWidgetProviderDino.class), 1, 1);
                break;
            case 10:
                SettingsActivity settingsActivity = SettingsActivity.P;
                LocaleManager.getInstance().a.getClass();
                SharedPreferencesManager.getInstance().l("LocaleManager_PREF_AUTO_SWITCH", false);
                break;
            case 11:
                oj2.U(2, 3, "NewTabPage.SuggestTiles.SelectedTileType");
                break;
            case 12:
                oj2.O("Blink.FedCm.SignUp.TermsOfServiceClicked", true);
                break;
            case 13:
                for (ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl : ChildProcessLauncherHelperImpl.s.values()) {
                    if (childProcessLauncherHelperImpl.d) {
                        childProcessLauncherHelperImpl.c();
                    }
                }
                break;
            case 14:
                for (ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl2 : ChildProcessLauncherHelperImpl.s.values()) {
                    if (childProcessLauncherHelperImpl2.d && childProcessLauncherHelperImpl2.l) {
                        qn qnVar = childProcessLauncherHelperImpl2.g.g;
                        if (qnVar.g()) {
                            qnVar.a();
                            childProcessLauncherHelperImpl2.l = false;
                        }
                    }
                }
                re reVar = ChildProcessLauncherHelperImpl.v;
                if (reVar != null) {
                    LauncherThread.b.removeCallbacks(reVar.h);
                }
                break;
            case 15:
                AccessibilityState.a();
                break;
            default:
                String[] strArr = SelectFileDialog.l;
                try {
                    File fileC = uh3.c(xz.a);
                    if (fileC.isDirectory() && (fileArrListFiles = fileC.listFiles()) != null) {
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        int length = fileArrListFiles.length;
                        while (i2 < length) {
                            File file = fileArrListFiles[i2];
                            if (jCurrentTimeMillis2 - file.lastModified() > SelectFileDialog.k && !file.delete()) {
                                Log.e("cr_SelectFileDialog", "Failed to delete: " + file);
                            }
                            i2++;
                        }
                    }
                } catch (IOException e) {
                    Log.w("cr_SelectFileDialog", "Failed to delete captured camera files.", e);
                    return;
                }
                break;
        }
    }
}
