package com.android.launcher3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.launcher3.compat.LauncherAppsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SessionCommitReceiver extends BroadcastReceiver {
    public static final String ADD_ICON_PREFERENCE_INITIALIZED_KEY = "pref_add_icon_to_home_initialized";
    public static final String ADD_ICON_PREFERENCE_KEY = "pref_add_icon_to_home";
    private static final String MARKER_PROVIDER_PREFIX = ".addtohomescreen";
    private static final String TAG = "SessionCommitReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (isEnabled(context) && Utilities.ATLEAST_OREO) {
            PackageInstaller.SessionInfo sessionInfo = (PackageInstaller.SessionInfo) intent.getParcelableExtra(PackageInstaller.EXTRA_SESSION);
            UserHandle userHandle = (UserHandle) intent.getParcelableExtra(Intent.EXTRA_USER);
            if (TextUtils.isEmpty(sessionInfo.getAppPackageName()) || sessionInfo.getInstallReason() != 4) {
                return;
            }
            queueAppIconAddition(context, sessionInfo.getAppPackageName(), userHandle);
        }
    }

    public static void queueAppIconAddition(Context context, String packageName, UserHandle user) {
        List<LauncherActivityInfo> activityList = LauncherAppsCompat.getInstance(context).getActivityList(packageName, user);
        if (activityList == null || activityList.isEmpty()) {
            return;
        }
        InstallShortcutReceiver.queueActivityInfo(activityList.get(0), context);
    }

    public static boolean isEnabled(Context context) {
        return Utilities.getPrefs(context).getBoolean(ADD_ICON_PREFERENCE_KEY, true);
    }

    public static void applyDefaultUserPrefs(final Context context) {
        if (Utilities.ATLEAST_OREO) {
            SharedPreferences prefs = Utilities.getPrefs(context);
            if (prefs.getAll().isEmpty()) {
                prefs.edit().putBoolean(ADD_ICON_PREFERENCE_KEY, true).apply();
            } else {
                if (prefs.contains(ADD_ICON_PREFERENCE_INITIALIZED_KEY)) {
                    return;
                }
                new PrefInitTask(context).executeOnExecutor(Utilities.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }
    }

    private static class PrefInitTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;

        PrefInitTask(Context context) {
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voids) {
            Utilities.getPrefs(this.mContext).edit().putBoolean(SessionCommitReceiver.ADD_ICON_PREFERENCE_KEY, readValueFromMarketApp()).putBoolean(SessionCommitReceiver.ADD_ICON_PREFERENCE_INITIALIZED_KEY, true).apply();
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0077 A[PHI: r2
  0x0077: PHI (r2v6 android.database.Cursor) = (r2v5 android.database.Cursor), (r2v7 android.database.Cursor) binds: [B:21:0x0075, B:15:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean readValueFromMarketApp() {
            /*
                r9 = this;
                android.content.Context r0 = r9.mContext
                android.content.pm.PackageManager r0 = r0.getPackageManager()
                android.content.Intent r1 = new android.content.Intent
                java.lang.String r2 = "android.intent.action.MAIN"
                r1.<init>(r2)
                java.lang.String r2 = "android.intent.category.APP_MARKET"
                android.content.Intent r1 = r1.addCategory(r2)
                r2 = 1114112(0x110000, float:1.561203E-39)
                android.content.pm.ResolveInfo r0 = r0.resolveActivity(r1, r2)
                r1 = 1
                if (r0 != 0) goto L1d
                return r1
            L1d:
                r2 = 0
                android.content.Context r9 = r9.mContext     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                r9.<init>()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                java.lang.String r4 = "content://"
                java.lang.StringBuilder r9 = r9.append(r4)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                android.content.pm.ActivityInfo r0 = r0.activityInfo     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                java.lang.String r0 = r0.packageName     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                java.lang.StringBuilder r9 = r9.append(r0)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                java.lang.String r0 = ".addtohomescreen"
                java.lang.StringBuilder r9 = r9.append(r0)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                android.net.Uri r4 = android.net.Uri.parse(r9)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                boolean r9 = r2.moveToNext()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                if (r9 == 0) goto L68
                java.lang.String r9 = "value"
                int r9 = r2.getColumnIndexOrThrow(r9)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                int r9 = r2.getInt(r9)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
                if (r9 == 0) goto L61
                goto L62
            L61:
                r1 = 0
            L62:
                if (r2 == 0) goto L67
                r2.close()
            L67:
                return r1
            L68:
                if (r2 == 0) goto L7a
                goto L77
            L6b:
                r9 = move-exception
                goto L7b
            L6d:
                r9 = move-exception
                java.lang.String r0 = "SessionCommitReceiver"
                java.lang.String r3 = "Error reading add to homescreen preference"
                android.util.Log.d(r0, r3, r9)     // Catch: java.lang.Throwable -> L6b
                if (r2 == 0) goto L7a
            L77:
                r2.close()
            L7a:
                return r1
            L7b:
                if (r2 == 0) goto L80
                r2.close()
            L80:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.SessionCommitReceiver.PrefInitTask.readValueFromMarketApp():boolean");
        }
    }
}
