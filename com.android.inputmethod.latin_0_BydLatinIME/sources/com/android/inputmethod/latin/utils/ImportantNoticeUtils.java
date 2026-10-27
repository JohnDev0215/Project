package com.android.inputmethod.latin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.permissions.PermissionsUtil;
import com.android.inputmethod.latin.settings.SettingsValues;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class ImportantNoticeUtils {
    private static final String KEY_SUGGEST_CONTACTS_NOTICE = "important_notice_suggest_contacts";
    static final String KEY_TIMESTAMP_OF_CONTACTS_NOTICE = "timestamp_of_suggest_contacts_notice";
    private static final String PREFERENCE_NAME = "important_notice_pref";
    private static final String Settings_Secure_USER_SETUP_COMPLETE = "user_setup_complete";
    private static final String TAG = "ImportantNoticeUtils";
    static final long TIMEOUT_OF_IMPORTANT_NOTICE = TimeUnit.HOURS.toMillis(23);
    private static final int USER_SETUP_IS_NOT_COMPLETE = 0;

    private ImportantNoticeUtils() {
    }

    static boolean isInSystemSetupWizard(final Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), Settings_Secure_USER_SETUP_COMPLETE) == 0;
        } catch (Settings.SettingNotFoundException unused) {
            Log.w(TAG, "Can't find settings in Settings.Secure: key=user_setup_complete");
            return false;
        }
    }

    static SharedPreferences getImportantNoticePreferences(final Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0);
    }

    static boolean hasContactsNoticeShown(final Context context) {
        return getImportantNoticePreferences(context).getBoolean(KEY_SUGGEST_CONTACTS_NOTICE, false);
    }

    public static boolean shouldShowImportantNotice(final Context context, final SettingsValues settingsValues) {
        if (!settingsValues.mUseContactsDict || hasContactsNoticeShown(context) || PermissionsUtil.checkAllPermissionsGranted(context, "android.permission.READ_CONTACTS") || TextUtils.isEmpty(getSuggestContactsNoticeTitle(context)) || isInSystemSetupWizard(context)) {
            return false;
        }
        if (!hasContactsNoticeTimeoutPassed(context, System.currentTimeMillis())) {
            return true;
        }
        updateContactsNoticeShown(context);
        return false;
    }

    public static String getSuggestContactsNoticeTitle(final Context context) {
        return context.getResources().getString(R.string.important_notice_suggest_contact_names);
    }

    static boolean hasContactsNoticeTimeoutPassed(final Context context, final long currentTimeInMillis) {
        SharedPreferences importantNoticePreferences = getImportantNoticePreferences(context);
        if (!importantNoticePreferences.contains(KEY_TIMESTAMP_OF_CONTACTS_NOTICE)) {
            importantNoticePreferences.edit().putLong(KEY_TIMESTAMP_OF_CONTACTS_NOTICE, currentTimeInMillis).apply();
        }
        return currentTimeInMillis - importantNoticePreferences.getLong(KEY_TIMESTAMP_OF_CONTACTS_NOTICE, currentTimeInMillis) >= TIMEOUT_OF_IMPORTANT_NOTICE;
    }

    public static void updateContactsNoticeShown(final Context context) {
        getImportantNoticePreferences(context).edit().putBoolean(KEY_SUGGEST_CONTACTS_NOTICE, true).remove(KEY_TIMESTAMP_OF_CONTACTS_NOTICE).apply();
    }
}
