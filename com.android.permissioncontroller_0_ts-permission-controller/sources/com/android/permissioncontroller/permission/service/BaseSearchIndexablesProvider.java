package com.android.permissioncontroller.permission.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.SearchIndexablesContract;
import android.provider.SearchIndexablesProvider;
import android.util.Log;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes8.dex */
public abstract class BaseSearchIndexablesProvider extends SearchIndexablesProvider {
    private static final String EXTRA_SETTINGS_SEARCH_KEY = ":settings:fragment_args_key";
    private static final int PASSWORD_LENGTH = 36;
    private static final String LOG_TAG = BaseSearchIndexablesProvider.class.getSimpleName();
    private static final Object sPasswordLock = new Object();

    public boolean onCreate() {
        return true;
    }

    public Cursor queryXmlResources(String[] projection) {
        return new MatrixCursor(SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS);
    }

    public Cursor queryNonIndexableKeys(String[] projection) {
        return new MatrixCursor(SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS);
    }

    private static String getPassword(Context context) {
        String password;
        synchronized (sPasswordLock) {
            SharedPreferences sharedPreferences = Utils.getDeviceProtectedSharedPreferences(context);
            password = sharedPreferences.getString(Constants.SEARCH_INDEXABLE_PROVIDER_PASSWORD_KEY, null);
            if (password == null) {
                password = UUID.randomUUID().toString();
                sharedPreferences.edit().putString(Constants.SEARCH_INDEXABLE_PROVIDER_PASSWORD_KEY, password).apply();
            }
        }
        return password;
    }

    protected static String createRawDataKey(String key, Context context) {
        return getPassword(context) + context.getPackageName() + ',' + key;
    }

    public static boolean isIntentValid(Intent intent, Context context) {
        String key = intent.getStringExtra(EXTRA_SETTINGS_SEARCH_KEY);
        String passwordFromIntent = key.substring(0, 36);
        String password = getPassword(context);
        boolean verified = Objects.equals(passwordFromIntent, password);
        if (!verified) {
            Log.w(LOG_TAG, "Invalid password: " + passwordFromIntent);
        }
        return verified;
    }

    public static String getOriginalKey(Intent intent) {
        int keyStart;
        String key = intent.getStringExtra(EXTRA_SETTINGS_SEARCH_KEY);
        if (key != null && (keyStart = key.indexOf(44) + 1) <= key.length()) {
            return key.substring(keyStart);
        }
        return null;
    }
}
