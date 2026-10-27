package com.android.providers.contacts;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.VersionInfo;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ContactsUpgradeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            SharedPreferences sharedPreferences = context.getSharedPreferences("ContactsUpgradeReceiver", 0);
            int i = sharedPreferences.getInt("db_version", 0);
            String string = VersionInfo.ICU_VERSION.toString();
            String osVersionString = getOsVersionString();
            String string2 = sharedPreferences.getString("icu_version", "");
            String string3 = sharedPreferences.getString("os_version", "");
            if (i == 1400 && string2.equals(string) && string3.equals(osVersionString)) {
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("db_version", 1400);
            editorEdit.putString("icu_version", string);
            editorEdit.putString("os_version", osVersionString);
            editorEdit.commit();
            ContactsDatabaseHelper contactsDatabaseHelper = ContactsDatabaseHelper.getInstance(context);
            ProfileDatabaseHelper profileDatabaseHelper = ProfileDatabaseHelper.getInstance(context);
            CallLogDatabaseHelper callLogDatabaseHelper = CallLogDatabaseHelper.getInstance(context);
            Log.i("ContactsUpgradeReceiver", "Creating or opening contacts database");
            contactsDatabaseHelper.getWritableDatabase();
            contactsDatabaseHelper.forceDirectoryRescan();
            profileDatabaseHelper.getWritableDatabase();
            callLogDatabaseHelper.getWritableDatabase();
            ContactsProvider2.updateLocaleOffline(context, contactsDatabaseHelper, profileDatabaseHelper);
            EventLogTags.writeContactsUpgradeReceiver(System.currentTimeMillis() - jCurrentTimeMillis);
        } catch (Throwable th) {
            Log.wtf("ContactsUpgradeReceiver", "Error during upgrade attempt. Disabling receiver.", th);
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) ContactsUpgradeReceiver.class), 2, 1);
        }
    }

    private static String getOsVersionString() {
        return Build.ID;
    }
}
