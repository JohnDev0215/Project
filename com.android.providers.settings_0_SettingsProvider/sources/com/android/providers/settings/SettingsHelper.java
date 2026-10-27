package com.android.providers.settings;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.backup.IBackupManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.icu.util.ULocale;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.LocaleList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.ArraySet;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.app.LocalePicker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SettingsHelper {
    private static final ArraySet<String> sBroadcastOnRestore;
    private static SettingsLookup sGlobalLookup;
    private static SettingsLookup sSecureLookup;
    private static SettingsLookup sSystemLookup;
    private AudioManager mAudioManager;
    private Context mContext;
    private TelephonyManager mTelephonyManager;

    private interface SettingsLookup {
        String lookup(ContentResolver contentResolver, String str, int i);
    }

    static {
        ArraySet<String> arraySet = new ArraySet<>(4);
        sBroadcastOnRestore = arraySet;
        arraySet.add("enabled_notification_listeners");
        sBroadcastOnRestore.add("enabled_vr_listeners");
        sBroadcastOnRestore.add("enabled_accessibility_services");
        sBroadcastOnRestore.add("bluetooth_on");
        sBroadcastOnRestore.add("ui_night_mode");
        sBroadcastOnRestore.add("dark_theme_custom_start_time");
        sBroadcastOnRestore.add("dark_theme_custom_end_time");
        sBroadcastOnRestore.add("accessibility_display_magnification_navbar_enabled");
        sSystemLookup = new SettingsLookup() { // from class: com.android.providers.settings.SettingsHelper.1
            @Override // com.android.providers.settings.SettingsHelper.SettingsLookup
            public String lookup(ContentResolver contentResolver, String str, int i) {
                return Settings.System.getStringForUser(contentResolver, str, i);
            }
        };
        sSecureLookup = new SettingsLookup() { // from class: com.android.providers.settings.SettingsHelper.2
            @Override // com.android.providers.settings.SettingsHelper.SettingsLookup
            public String lookup(ContentResolver contentResolver, String str, int i) {
                return Settings.Secure.getStringForUser(contentResolver, str, i);
            }
        };
        sGlobalLookup = new SettingsLookup() { // from class: com.android.providers.settings.SettingsHelper.3
            @Override // com.android.providers.settings.SettingsHelper.SettingsLookup
            public String lookup(ContentResolver contentResolver, String str, int i) {
                return Settings.Global.getStringForUser(contentResolver, str, i);
            }
        };
    }

    public SettingsHelper(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    public void restoreValue(Context context, ContentResolver contentResolver, ContentValues contentValues, Uri uri, String str, String str2, int i) {
        boolean z;
        String strLookup;
        if (isReplacedSystemSetting(str)) {
            return;
        }
        SettingsLookup settingsLookup = uri.equals(Settings.Secure.CONTENT_URI) ? sSecureLookup : uri.equals(Settings.System.CONTENT_URI) ? sSystemLookup : sGlobalLookup;
        if (sBroadcastOnRestore.contains(str)) {
            strLookup = settingsLookup.lookup(contentResolver, str, 0);
            z = true;
        } else {
            z = false;
            strLookup = null;
        }
        try {
            if ("sound_effects_enabled".equals(str)) {
                setSoundEffects(Integer.parseInt(str2) == 1);
            } else {
                if (!"backup_auto_restore".equals(str)) {
                    if (isAlreadyConfiguredCriticalAccessibilitySetting(str)) {
                        if (z) {
                            context.sendBroadcastAsUser(new Intent("android.os.action.SETTING_RESTORED").setPackage("android").addFlags(1073741824).putExtra("setting_name", str).putExtra("new_value", str2).putExtra("previous_value", strLookup).putExtra("restored_from_sdk_int", i), UserHandle.SYSTEM, null);
                            return;
                        }
                        return;
                    }
                    if (!"ringtone".equals(str)) {
                        if (!"notification_sound".equals(str)) {
                            if ("alarm_alert".equals(str)) {
                            }
                        }
                    }
                    setRingtone(str, str2);
                    if (z) {
                        context.sendBroadcastAsUser(new Intent("android.os.action.SETTING_RESTORED").setPackage("android").addFlags(1073741824).putExtra("setting_name", str).putExtra("new_value", str2).putExtra("previous_value", strLookup).putExtra("restored_from_sdk_int", i), UserHandle.SYSTEM, null);
                        return;
                    }
                    return;
                }
                setAutoRestore(Integer.parseInt(str2) == 1);
            }
            contentValues.clear();
            contentValues.put("name", str);
            contentValues.put("value", str2);
            contentResolver.insert(uri, contentValues);
            if (z) {
                context.sendBroadcastAsUser(new Intent("android.os.action.SETTING_RESTORED").setPackage("android").addFlags(1073741824).putExtra("setting_name", str).putExtra("new_value", str2).putExtra("previous_value", strLookup).putExtra("restored_from_sdk_int", i), UserHandle.SYSTEM, null);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (z) {
                context.sendBroadcastAsUser(new Intent("android.os.action.SETTING_RESTORED").setPackage("android").addFlags(1073741824).putExtra("setting_name", str).putExtra("new_value", str2).putExtra("previous_value", strLookup).putExtra("restored_from_sdk_int", i), UserHandle.SYSTEM, null);
            }
            throw th;
        }
    }

    public String onBackupValue(String str, String str2) {
        if (!"ringtone".equals(str) && !"notification_sound".equals(str) && !"alarm_alert".equals(str)) {
            return isReplacedSystemSetting(str) ? getRealValueForSystemSetting(str) : str2;
        }
        if (str2 == null) {
            if (!"ringtone".equals(str)) {
                return "_silent";
            }
            TelephonyManager telephonyManager = this.mTelephonyManager;
            if (telephonyManager == null || !telephonyManager.isVoiceCapable()) {
                return null;
            }
            return "_silent";
        }
        return getCanonicalRingtoneValue(str2);
    }

    @VisibleForTesting
    public String getRealValueForSystemSetting(String str) {
        return Settings.Secure.getString(this.mContext.getContentResolver(), str + "_original");
    }

    @VisibleForTesting
    public boolean isReplacedSystemSetting(String str) {
        return "screen_off_timeout".equals(str) && Settings.Secure.getInt(this.mContext.getContentResolver(), "backup_skip_user_facing_data", 0) != 0;
    }

    private void setRingtone(String str, String str2) {
        Uri uriUncanonicalize;
        if (str2 == null) {
            return;
        }
        if ("_silent".equals(str2)) {
            uriUncanonicalize = null;
        } else {
            uriUncanonicalize = this.mContext.getContentResolver().uncanonicalize(Uri.parse(str2));
            if (uriUncanonicalize == null) {
                return;
            }
        }
        RingtoneManager.setActualDefaultRingtoneUri(this.mContext, getRingtoneType(str), uriUncanonicalize);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int getRingtoneType(java.lang.String r4) {
        /*
            r3 = this;
            int r3 = r4.hashCode()
            r0 = -1485694789(0xffffffffa77218bb, float:-3.3597653E-15)
            r1 = 2
            r2 = 1
            if (r3 == r0) goto L2a
            r0 = -1236583518(0xffffffffb64b3ba2, float:-3.0284068E-6)
            if (r3 == r0) goto L20
            r0 = 394569198(0x1784a5ee, float:8.572187E-25)
            if (r3 == r0) goto L16
            goto L34
        L16:
            java.lang.String r3 = "alarm_alert"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L34
            r3 = r1
            goto L35
        L20:
            java.lang.String r3 = "ringtone"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L34
            r3 = 0
            goto L35
        L2a:
            java.lang.String r3 = "notification_sound"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L34
            r3 = r2
            goto L35
        L34:
            r3 = -1
        L35:
            if (r3 == 0) goto L55
            if (r3 == r2) goto L54
            if (r3 != r1) goto L3d
            r3 = 4
            return r3
        L3d:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Incorrect ringtone name: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        L54:
            return r1
        L55:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsHelper.getRingtoneType(java.lang.String):int");
    }

    private String getCanonicalRingtoneValue(String str) {
        Uri uriCanonicalize = this.mContext.getContentResolver().canonicalize(Uri.parse(str));
        if (uriCanonicalize == null) {
            return null;
        }
        return uriCanonicalize.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isAlreadyConfiguredCriticalAccessibilitySetting(java.lang.String r5) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsHelper.isAlreadyConfiguredCriticalAccessibilitySetting(java.lang.String):boolean");
    }

    private void setAutoRestore(boolean z) {
        try {
            IBackupManager iBackupManagerAsInterface = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
            if (iBackupManagerAsInterface != null) {
                iBackupManagerAsInterface.setAutoRestore(z);
            }
        } catch (RemoteException unused) {
        }
    }

    private void setSoundEffects(boolean z) {
        if (z) {
            this.mAudioManager.loadSoundEffects();
        } else {
            this.mAudioManager.unloadSoundEffects();
        }
    }

    byte[] getLocaleData() {
        return this.mContext.getResources().getConfiguration().getLocales().toLanguageTags().getBytes();
    }

    private static Locale toFullLocale(Locale locale) {
        return (locale.getScript().isEmpty() || locale.getCountry().isEmpty()) ? ULocale.addLikelySubtags(ULocale.forLocale(locale)).toLocale() : locale;
    }

    @VisibleForTesting
    public static LocaleList resolveLocales(LocaleList localeList, LocaleList localeList2, String[] strArr) {
        HashMap map = new HashMap(strArr.length);
        for (String str : strArr) {
            Locale localeForLanguageTag = Locale.forLanguageTag(str);
            map.put(toFullLocale(localeForLanguageTag), localeForLanguageTag);
        }
        ArrayList arrayList = new ArrayList(localeList2.size());
        for (int i = 0; i < localeList2.size(); i++) {
            Locale locale = localeList2.get(i);
            map.remove(toFullLocale(locale));
            arrayList.add(locale);
        }
        for (int i2 = 0; i2 < localeList.size(); i2++) {
            Locale locale2 = (Locale) map.remove(toFullLocale(localeList.get(i2)));
            if (locale2 != null) {
                arrayList.add(locale2);
            }
        }
        return arrayList.size() == localeList2.size() ? localeList2 : new LocaleList((Locale[]) arrayList.toArray(new Locale[arrayList.size()]));
    }

    void setLocaleData(byte[] bArr, int i) {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        LocaleList localeListForLanguageTags = LocaleList.forLanguageTags(new String(bArr, 0, i).replace('_', '-'));
        if (localeListForLanguageTags.isEmpty()) {
            return;
        }
        String[] supportedLocales = LocalePicker.getSupportedLocales(this.mContext);
        LocaleList locales = configuration.getLocales();
        LocaleList localeListResolveLocales = resolveLocales(localeListForLanguageTags, locales, supportedLocales);
        if (localeListResolveLocales.equals(locales)) {
            return;
        }
        try {
            IActivityManager service = ActivityManager.getService();
            Configuration configuration2 = service.getConfiguration();
            configuration2.setLocales(localeListResolveLocales);
            configuration2.userSetLocale = true;
            service.updatePersistentConfiguration(configuration2);
        } catch (RemoteException unused) {
        }
    }

    void applyAudioSettings() {
        new AudioManager(this.mContext).reloadAudioSettings();
    }
}
