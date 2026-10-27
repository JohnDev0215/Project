package com.android.providers.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Column;
import android.util.Log;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class MediaUpgradeReceiver extends BroadcastReceiver {
    static final String PREF_DB_VERSION = "db_version";
    static final String TAG = "MediaUpgradeReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, 0);
        int i = sharedPreferences.getInt(PREF_DB_VERSION, 0);
        int databaseVersion = DatabaseHelper.getDatabaseVersion(context);
        if (i == databaseVersion) {
            return;
        }
        sharedPreferences.edit().putInt(PREF_DB_VERSION, databaseVersion).commit();
        try {
            String[] list = context.getDatabasePath("foo").getParentFile().list();
            if (list == null) {
                return;
            }
            for (String str2 : list) {
                if (MediaProvider.isMediaDatabaseName(str2)) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    Log.i(TAG, "---> Start upgrade of media database " + str2);
                    try {
                        str = str2;
                    } catch (Throwable th) {
                        th = th;
                        str = str2;
                    }
                    try {
                        DatabaseHelper databaseHelper = new DatabaseHelper(context, str2, MediaProvider.isInternalMediaDatabaseName(str2), false, false, Column.class, $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk.INSTANCE, null, MediaProvider.MIGRATION_LISTENER, null);
                        databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaUpgradeReceiver$rUdrD5OhRFkttMB5MRI5abInlqA
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return Integer.valueOf(((SQLiteDatabase) obj).getVersion());
                            }
                        });
                        databaseHelper.close();
                    } catch (Throwable th2) {
                        th = th2;
                        Log.wtf(TAG, "Error during upgrade of media db " + str, th);
                    }
                    Log.i(TAG, "<--- Finished upgrade of media database " + str + " in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                }
            }
        } catch (Throwable th3) {
            Log.wtf(TAG, "Error during upgrade attempt.", th3);
        }
    }
}
