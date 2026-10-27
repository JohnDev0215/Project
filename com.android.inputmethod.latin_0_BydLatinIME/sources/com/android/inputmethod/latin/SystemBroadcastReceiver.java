package com.android.inputmethod.latin;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Process;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import com.android.inputmethod.dictionarypack.DictionaryPackConstants;
import com.android.inputmethod.dictionarypack.DownloadManagerWrapper;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.setup.SetupActivity;
import com.android.inputmethod.latin.utils.UncachedInputMethodManagerUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class SystemBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "SystemBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MY_PACKAGE_REPLACED".equals(action)) {
            Log.i(TAG, "Package has been replaced: " + context.getPackageName());
            RichInputMethodManager.init(context);
            RichInputMethodManager richInputMethodManager = RichInputMethodManager.getInstance();
            richInputMethodManager.setAdditionalInputMethodSubtypes(richInputMethodManager.getAdditionalSubtypes());
            toggleAppIcon(context);
            removeOldDownloads(context);
        } else if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            Log.i(TAG, "Boot has been completed");
            toggleAppIcon(context);
        } else if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
            Log.i(TAG, "System locale changed");
            KeyboardLayoutSet.onSystemLocaleChanged();
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if ((inputMethodManager.getInputMethodList().isEmpty() ^ true) && UncachedInputMethodManagerUtils.isThisImeCurrent(context, inputMethodManager)) {
            return;
        }
        int iMyPid = Process.myPid();
        Log.i(TAG, "Killing my process: pid=" + iMyPid);
        Process.killProcess(iMyPid);
    }

    private void removeOldDownloads(Context context) {
        try {
            Log.i(TAG, "Removing the old downloads in progress of the previous keyboard version.");
            DownloadManagerWrapper downloadManagerWrapper = new DownloadManagerWrapper(context);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterByStatus(23);
            Cursor cursorQuery = downloadManagerWrapper.query(query);
            if (cursorQuery != null) {
                cursorQuery.moveToFirst();
                while (!cursorQuery.isAfterLast()) {
                    long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
                    downloadManagerWrapper.remove(j);
                    Log.i(TAG, "Removed the download with Id: " + j);
                    cursorQuery.moveToNext();
                }
                cursorQuery.close();
            }
        } catch (Exception unused) {
            Log.e(TAG, "Exception while removing old downloads.");
        }
    }

    private void downloadLatestDictionaries(Context context) {
        context.sendBroadcast(new Intent(DictionaryPackConstants.INIT_AND_UPDATE_NOW_INTENT_ACTION));
    }

    public static void toggleAppIcon(final Context context) {
        boolean z = (context.getApplicationInfo().flags & 1) > 0;
        String str = TAG;
        if (Log.isLoggable(str, 4)) {
            Log.i(str, "toggleAppIcon() : FLAG_SYSTEM = " + z);
        }
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) SetupActivity.class), Settings.readShowSetupWizardIcon(PreferenceManager.getDefaultSharedPreferences(context), context) ? 1 : 2, 1);
    }
}
