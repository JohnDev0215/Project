package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.config.Util;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceID {
    private static final String TAG = "DeviceID";
    private static final Pattern ZERO_PATTERN = Pattern.compile("^0+$");
    private static final Pattern CHARACTERS_TO_REPLACE = Pattern.compile("[\\W_]+");

    public static synchronized String getId(final Context context) {
        String cachedId;
        cachedId = getCachedId(context);
        if (hasEntropy(context, cachedId)) {
            Log.verbose(TAG, "Using cached id");
        } else {
            if (cachedId != null) {
                handleInvalidCachedId(context);
            }
            cachedId = generateId();
            cacheDeviceId(cachedId, context);
        }
        Log.verbose(TAG, "Device ID: %s", cachedId);
        return cachedId;
    }

    public static String normalizeId(final String id) {
        String strReplaceAll = CHARACTERS_TO_REPLACE.matcher(id).replaceAll("");
        int length = strReplaceAll.length();
        if (length > 50) {
            strReplaceAll = strReplaceAll.substring(length - 50, length);
        }
        return strReplaceAll.toUpperCase(Locale.ROOT);
    }

    private static String getCachedId(final Context context) {
        return SettingsUtil.getPrefs(context).getString(context.getString(R.string.setting_key_cached_device_id), null);
    }

    private static String generateId() {
        return normalizeId(Build.MODEL + UUID.randomUUID().toString().replace("-", ""));
    }

    private static void cacheDeviceId(final String id, final Context context) {
        if (id == null || id.length() <= 0) {
            return;
        }
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_cached_device_id), id);
        editorEdit.apply();
    }

    private static void handleInvalidCachedId(final Context context) {
        String cachedId = getCachedId(context);
        if (cachedId == null || hasEntropy(context, cachedId)) {
            return;
        }
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.remove(context.getString(R.string.setting_key_cached_device_id));
        editorEdit.apply();
        AccountCredentialUtil.get().removeCredentialsAndSettings();
        String str = TAG;
        Log.warning(str, "Client had a bad id previously. Signing out.");
        Log.debug(str, "Bad id was: %s", cachedId);
    }

    private static boolean hasEntropy(final Context context, final String id) {
        if (id == null || id.length() < 8 || getIdentifierBlacklist(context).contains(normalizeId(id))) {
            return false;
        }
        return !ZERO_PATTERN.matcher(id).find();
    }

    private static Set<String> getIdentifierBlacklist(Context context) {
        HashSet hashSet = new HashSet();
        String string = AmazonApplication.getConfiguration(context).getString("identifier_blacklist", null);
        if (string != null) {
            List listAsList = Arrays.asList(Util.SPLIT_CSV.split(string));
            hashSet = new HashSet(listAsList.size());
            Iterator it = listAsList.iterator();
            while (it.hasNext()) {
                hashSet.add(Util.normalizeId((String) it.next()));
            }
        }
        return hashSet;
    }
}
