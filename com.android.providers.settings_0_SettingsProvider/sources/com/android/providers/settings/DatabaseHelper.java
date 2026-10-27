package com.android.providers.settings;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.media.AudioSystem;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.sysprop.TelephonyProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.XmlUtils;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class DatabaseHelper extends SQLiteOpenHelper {
    private static final HashSet<String> mValidTables;
    private Context mContext;
    private int mUserHandle;

    static {
        HashSet<String> hashSet = new HashSet<>();
        mValidTables = hashSet;
        hashSet.add("system");
        mValidTables.add("secure");
        mValidTables.add("global");
        mValidTables.add("bluetooth_devices");
        mValidTables.add("bookmarks");
        mValidTables.add("favorites");
        mValidTables.add("old_favorites");
        mValidTables.add("android_metadata");
    }

    static String dbNameForUser(int i) {
        if (i == 0) {
            return "settings.db";
        }
        File file = new File(Environment.getUserSystemDirectory(i), "settings.db");
        if (!file.exists()) {
            Log.i("SettingsProvider", "No previous database file exists - running in in-memory mode");
            return null;
        }
        return file.getPath();
    }

    public DatabaseHelper(Context context, int i) {
        super(context, dbNameForUser(i), (SQLiteDatabase.CursorFactory) null, 118);
        this.mContext = context;
        this.mUserHandle = i;
    }

    public static boolean isValidTable(String str) {
        return mValidTables.contains(str);
    }

    private boolean isInMemory() {
        return getDatabaseName() == null;
    }

    public void dropDatabase() {
        close();
        if (isInMemory()) {
            return;
        }
        File databasePath = this.mContext.getDatabasePath(getDatabaseName());
        if (databasePath.exists()) {
            SQLiteDatabase.deleteDatabase(databasePath);
        }
    }

    private void createSecureTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE secure (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE ON CONFLICT REPLACE,value TEXT);");
        sQLiteDatabase.execSQL("CREATE INDEX secureIndex1 ON secure (name);");
    }

    private void createGlobalTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE global (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE ON CONFLICT REPLACE,value TEXT);");
        sQLiteDatabase.execSQL("CREATE INDEX globalIndex1 ON global (name);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE system (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE ON CONFLICT REPLACE,value TEXT);");
        sQLiteDatabase.execSQL("CREATE INDEX systemIndex1 ON system (name);");
        createSecureTable(sQLiteDatabase);
        if (this.mUserHandle == 0) {
            createGlobalTable(sQLiteDatabase);
        }
        sQLiteDatabase.execSQL("CREATE TABLE bluetooth_devices (_id INTEGER PRIMARY KEY,name TEXT,addr TEXT,channel INTEGER,type INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE bookmarks (_id INTEGER PRIMARY KEY,title TEXT,folder TEXT,intent TEXT,shortcut INTEGER,ordering INTEGER);");
        sQLiteDatabase.execSQL("CREATE INDEX bookmarksIndex1 ON bookmarks (folder);");
        sQLiteDatabase.execSQL("CREATE INDEX bookmarksIndex2 ON bookmarks (shortcut);");
        boolean zIsOnlyCoreApps = false;
        try {
            zIsOnlyCoreApps = IPackageManager.Stub.asInterface(ServiceManager.getService("package")).isOnlyCoreApps();
        } catch (RemoteException unused) {
        }
        if (!zIsOnlyCoreApps) {
            loadBookmarks(sQLiteDatabase);
        }
        loadVolumeLevels(sQLiteDatabase);
        loadSettings(sQLiteDatabase);
    }

    /* JADX WARN: Removed duplicated region for block: B:513:0x07fb  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0800  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x0810  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x0815  */
    @Override // android.database.sqlite.SQLiteOpenHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r79, int r80, int r81) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 3967
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.DatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    public void recreateDatabase(SQLiteDatabase sQLiteDatabase, int i, int i2, int i3) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS global");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS globalIndex1");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS system");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS systemIndex1");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS secure");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS secureIndex1");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS gservices");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS gservicesIndex1");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bluetooth_devices");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bookmarks");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS bookmarksIndex1");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS bookmarksIndex2");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS favorites");
        onCreate(sQLiteDatabase);
        sQLiteDatabase.execSQL("INSERT INTO secure(name,value) values('wiped_db_reason','" + (i + "/" + i2 + "/" + i3) + "');");
    }

    private String[] setToStringArray(Set<String> set) {
        return (String[]) set.toArray(new String[set.size()]);
    }

    private void moveSettingsToNewTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr, boolean z) throws Throwable {
        SQLiteStatement sQLiteStatement;
        SQLiteStatement sQLiteStatementCompileStatement;
        sQLiteDatabase.beginTransaction();
        SQLiteStatement sQLiteStatementCompileStatement2 = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT ");
            sb.append(z ? " OR IGNORE " : "");
            sb.append(" INTO ");
            sb.append(str2);
            sb.append(" (name,value) SELECT name,value FROM ");
            sb.append(str);
            sb.append(" WHERE name=?");
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement(sb.toString());
        } catch (Throwable th) {
            th = th;
            sQLiteStatement = null;
        }
        try {
            sQLiteStatementCompileStatement2 = sQLiteDatabase.compileStatement("DELETE FROM " + str + " WHERE name=?");
            for (String str3 : strArr) {
                sQLiteStatementCompileStatement.bindString(1, str3);
                sQLiteStatementCompileStatement.execute();
                sQLiteStatementCompileStatement2.bindString(1, str3);
                sQLiteStatementCompileStatement2.execute();
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
            if (sQLiteStatementCompileStatement2 != null) {
                sQLiteStatementCompileStatement2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteStatement = sQLiteStatementCompileStatement2;
            sQLiteStatementCompileStatement2 = sQLiteStatementCompileStatement;
            sQLiteDatabase.endTransaction();
            if (sQLiteStatementCompileStatement2 != null) {
                sQLiteStatementCompileStatement2.close();
            }
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            throw th;
        }
    }

    private void movePrefixedSettingsToNewTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) throws Throwable {
        SQLiteStatement sQLiteStatement;
        SQLiteStatement sQLiteStatementCompileStatement;
        sQLiteDatabase.beginTransaction();
        SQLiteStatement sQLiteStatementCompileStatement2 = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT INTO " + str2 + " (name,value) SELECT name,value FROM " + str + " WHERE substr(name,0,?)=?");
        } catch (Throwable th) {
            th = th;
            sQLiteStatement = null;
        }
        try {
            sQLiteStatementCompileStatement2 = sQLiteDatabase.compileStatement("DELETE FROM " + str + " WHERE substr(name,0,?)=?");
            for (String str3 : strArr) {
                sQLiteStatementCompileStatement.bindLong(1, str3.length() + 1);
                sQLiteStatementCompileStatement.bindString(2, str3);
                sQLiteStatementCompileStatement.execute();
                sQLiteStatementCompileStatement2.bindLong(1, str3.length() + 1);
                sQLiteStatementCompileStatement2.bindString(2, str3);
                sQLiteStatementCompileStatement2.execute();
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
            if (sQLiteStatementCompileStatement2 != null) {
                sQLiteStatementCompileStatement2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteStatement = sQLiteStatementCompileStatement2;
            sQLiteStatementCompileStatement2 = sQLiteStatementCompileStatement;
            sQLiteDatabase.endTransaction();
            if (sQLiteStatementCompileStatement2 != null) {
                sQLiteStatementCompileStatement2.close();
            }
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            throw th;
        }
    }

    private void upgradeLockPatternLocation(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("system", new String[]{"_id", "value"}, "name='lock_pattern'", null, null, null, null);
        if (cursorQuery.getCount() > 0) {
            cursorQuery.moveToFirst();
            String string = cursorQuery.getString(1);
            if (!TextUtils.isEmpty(string)) {
                try {
                    new LockPatternUtils(this.mContext).setLockCredential(LockscreenCredential.createPattern(LockPatternUtils.byteArrayToPattern(string.getBytes())), LockscreenCredential.createNone(), 0);
                } catch (IllegalArgumentException unused) {
                }
            }
            cursorQuery.close();
            sQLiteDatabase.delete("system", "name='lock_pattern'", null);
            return;
        }
        cursorQuery.close();
    }

    private void upgradeScreenTimeoutFromNever(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("system", new String[]{"_id", "value"}, "name=? AND value=?", new String[]{"screen_off_timeout", "-1"}, null, null, null);
        if (cursorQuery.getCount() > 0) {
            cursorQuery.close();
            SQLiteStatement sQLiteStatementCompileStatement = null;
            try {
                sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR REPLACE INTO system(name,value) VALUES(?,?);");
                loadSetting(sQLiteStatementCompileStatement, "screen_off_timeout", Integer.toString(1800000));
                if (sQLiteStatementCompileStatement != null) {
                    return;
                } else {
                    return;
                }
            } finally {
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
            }
        }
        cursorQuery.close();
    }

    private void upgradeVibrateSettingFromNone(SQLiteDatabase sQLiteDatabase) {
        int intValueFromSystem = getIntValueFromSystem(sQLiteDatabase, "vibrate_on", 0);
        if ((intValueFromSystem & 3) == 0) {
            intValueFromSystem = AudioSystem.getValueForVibrateSetting(0, 0, 2);
        }
        int valueForVibrateSetting = AudioSystem.getValueForVibrateSetting(intValueFromSystem, 1, intValueFromSystem);
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR REPLACE INTO system(name,value) VALUES(?,?);");
            loadSetting(sQLiteStatementCompileStatement, "vibrate_on", Integer.valueOf(valueForVibrateSetting));
        } finally {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
        }
    }

    private void upgradeAutoBrightness(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("INSERT OR REPLACE INTO system(name,value) values('screen_brightness_mode','" + (this.mContext.getResources().getBoolean(R.bool.def_screen_brightness_automatic_mode) ? "1" : "0") + "');");
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void loadBookmarks(SQLiteDatabase sQLiteDatabase) {
        XmlResourceParser xmlResourceParser;
        Intent intentMakeMainSelectorActivity;
        String string;
        ActivityInfo activityInfo;
        ContentValues contentValues = new ContentValues();
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            XmlResourceParser xml = this.mContext.getResources().getXml(R.xml.bookmarks);
            XmlUtils.beginDocument(xml, "bookmarks");
            int depth = xml.getDepth();
            while (true) {
                int next = xml.next();
                if ((next == 3 && xml.getDepth() <= depth) || next == 1) {
                    return;
                }
                if (next == 2) {
                    if (!"bookmark".equals(xml.getName())) {
                        return;
                    }
                    String attributeValue = xml.getAttributeValue(null, "package");
                    String attributeValue2 = xml.getAttributeValue(null, "class");
                    String attributeValue3 = xml.getAttributeValue(null, "shortcut");
                    String attributeValue4 = xml.getAttributeValue(null, "category");
                    char cCharAt = attributeValue3.charAt(0);
                    if (TextUtils.isEmpty(attributeValue3)) {
                        Log.w("SettingsProvider", "Unable to get shortcut for: " + attributeValue + "/" + attributeValue2);
                    } else if (attributeValue == null || attributeValue2 == null) {
                        xmlResourceParser = xml;
                        if (attributeValue4 != null) {
                            intentMakeMainSelectorActivity = Intent.makeMainSelectorActivity("android.intent.action.MAIN", attributeValue4);
                            string = "";
                            intentMakeMainSelectorActivity.setFlags(268435456);
                            contentValues.put("intent", intentMakeMainSelectorActivity.toUri(0));
                            contentValues.put("title", string);
                            contentValues.put("shortcut", Integer.valueOf(cCharAt));
                            sQLiteDatabase.delete("bookmarks", "shortcut = ?", new String[]{Integer.toString(cCharAt)});
                            sQLiteDatabase.insert("bookmarks", null, contentValues);
                            xml = xmlResourceParser;
                        } else {
                            Log.w("SettingsProvider", "Unable to add bookmark for shortcut " + attributeValue3 + ": missing package/class or category attributes");
                            xml = xmlResourceParser;
                        }
                    } else {
                        ComponentName componentName = new ComponentName(attributeValue, attributeValue2);
                        try {
                            activityInfo = packageManager.getActivityInfo(componentName, 0);
                            xmlResourceParser = xml;
                        } catch (PackageManager.NameNotFoundException e) {
                            xmlResourceParser = xml;
                            ComponentName componentName2 = new ComponentName(packageManager.canonicalToCurrentPackageNames(new String[]{attributeValue})[0], attributeValue2);
                            try {
                                activityInfo = packageManager.getActivityInfo(componentName2, 0);
                                componentName = componentName2;
                            } catch (PackageManager.NameNotFoundException unused) {
                                Log.w("SettingsProvider", "Unable to add bookmark: " + attributeValue + "/" + attributeValue2, e);
                            }
                        }
                        intentMakeMainSelectorActivity = new Intent("android.intent.action.MAIN", (Uri) null);
                        intentMakeMainSelectorActivity.addCategory("android.intent.category.LAUNCHER");
                        intentMakeMainSelectorActivity.setComponent(componentName);
                        string = activityInfo.loadLabel(packageManager).toString();
                        intentMakeMainSelectorActivity.setFlags(268435456);
                        contentValues.put("intent", intentMakeMainSelectorActivity.toUri(0));
                        contentValues.put("title", string);
                        contentValues.put("shortcut", Integer.valueOf(cCharAt));
                        sQLiteDatabase.delete("bookmarks", "shortcut = ?", new String[]{Integer.toString(cCharAt)});
                        sQLiteDatabase.insert("bookmarks", null, contentValues);
                        xml = xmlResourceParser;
                    }
                }
            }
        } catch (IOException e2) {
            Log.w("SettingsProvider", "Got execption parsing bookmarks.", e2);
        } catch (XmlPullParserException e3) {
            Log.w("SettingsProvider", "Got execption parsing bookmarks.", e3);
        }
    }

    private void loadVolumeLevels(SQLiteDatabase sQLiteDatabase) {
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR IGNORE INTO system(name,value) VALUES(?,?);");
            loadSetting(sQLiteStatementCompileStatement, "volume_music", Integer.valueOf(AudioSystem.getDefaultStreamVolume(3)));
            loadSetting(sQLiteStatementCompileStatement, "volume_ring", Integer.valueOf(AudioSystem.getDefaultStreamVolume(2)));
            loadSetting(sQLiteStatementCompileStatement, "volume_system", Integer.valueOf(AudioSystem.getDefaultStreamVolume(1)));
            loadSetting(sQLiteStatementCompileStatement, "volume_voice", Integer.valueOf(AudioSystem.getDefaultStreamVolume(0)));
            loadSetting(sQLiteStatementCompileStatement, "volume_alarm", Integer.valueOf(AudioSystem.getDefaultStreamVolume(4)));
            loadSetting(sQLiteStatementCompileStatement, "volume_notification", Integer.valueOf(AudioSystem.getDefaultStreamVolume(5)));
            loadSetting(sQLiteStatementCompileStatement, "volume_bluetooth_sco", Integer.valueOf(AudioSystem.getDefaultStreamVolume(6)));
            loadSetting(sQLiteStatementCompileStatement, "mode_ringer_streams_affected", Integer.valueOf(this.mContext.getResources().getBoolean(android.R.bool.config_cecSetMenuLanguage_userConfigurable) ? 166 : 174));
            loadSetting(sQLiteStatementCompileStatement, "mute_streams_affected", 111);
        } finally {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
        }
    }

    private void loadVibrateSetting(SQLiteDatabase sQLiteDatabase, boolean z) {
        if (z) {
            sQLiteDatabase.execSQL("DELETE FROM system WHERE name='vibrate_on'");
        }
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR IGNORE INTO system(name,value) VALUES(?,?);");
            int valueForVibrateSetting = AudioSystem.getValueForVibrateSetting(0, 1, 2);
            loadSetting(sQLiteStatementCompileStatement, "vibrate_on", Integer.valueOf(valueForVibrateSetting | AudioSystem.getValueForVibrateSetting(valueForVibrateSetting, 0, 2)));
        } finally {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
        }
    }

    private void loadSettings(SQLiteDatabase sQLiteDatabase) {
        loadSystemSettings(sQLiteDatabase);
        loadSecureSettings(sQLiteDatabase);
        if (this.mUserHandle == 0) {
            loadGlobalSettings(sQLiteDatabase);
        }
    }

    private void loadSystemSettings(SQLiteDatabase sQLiteDatabase) {
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR IGNORE INTO system(name,value) VALUES(?,?);");
            loadBooleanSetting(sQLiteStatementCompileStatement, "dim_screen", R.bool.def_dim_screen);
            loadIntegerSetting(sQLiteStatementCompileStatement, "screen_off_timeout", R.integer.def_screen_off_timeout);
            loadStringSetting(sQLiteStatementCompileStatement, "time_12_24", R.string.def_time_format);
            loadSetting(sQLiteStatementCompileStatement, "dtmf_tone_type", 0);
            loadSetting(sQLiteStatementCompileStatement, "hearing_aid", 0);
            loadSetting(sQLiteStatementCompileStatement, "tty_mode", 0);
            loadIntegerSetting(sQLiteStatementCompileStatement, "screen_brightness", R.integer.def_screen_brightness);
            loadIntegerSetting(sQLiteStatementCompileStatement, "screen_brightness_for_vr", android.R.integer.config_jobSchedulerIdleWindowSlop);
            loadBooleanSetting(sQLiteStatementCompileStatement, "screen_brightness_mode", R.bool.def_screen_brightness_automatic_mode);
            loadBooleanSetting(sQLiteStatementCompileStatement, "accelerometer_rotation", R.bool.def_accelerometer_rotation);
            loadDefaultHapticSettings(sQLiteStatementCompileStatement);
            loadBooleanSetting(sQLiteStatementCompileStatement, "notification_light_pulse", R.bool.def_notification_pulse);
            loadUISoundEffectsSettings(sQLiteStatementCompileStatement);
            loadIntegerSetting(sQLiteStatementCompileStatement, "pointer_speed", R.integer.def_pointer_speed);
            loadIntegerSetting(sQLiteStatementCompileStatement, "screen_orientation", R.integer.def_screen_orientation);
        } finally {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
        }
    }

    private void loadUISoundEffectsSettings(SQLiteStatement sQLiteStatement) {
        loadBooleanSetting(sQLiteStatement, "dtmf_tone", R.bool.def_dtmf_tones_enabled);
        loadBooleanSetting(sQLiteStatement, "sound_effects_enabled", R.bool.def_sound_effects_enabled);
        loadBooleanSetting(sQLiteStatement, "haptic_feedback_enabled", R.bool.def_haptic_feedback);
        loadIntegerSetting(sQLiteStatement, "lockscreen_sounds_enabled", R.integer.def_lockscreen_sounds_enabled);
    }

    private void loadDefaultAnimationSettings(SQLiteStatement sQLiteStatement) {
        loadFractionSetting(sQLiteStatement, "window_animation_scale", R.fraction.def_window_animation_scale, 1);
        loadFractionSetting(sQLiteStatement, "transition_animation_scale", R.fraction.def_window_transition_scale, 1);
    }

    private void loadDefaultHapticSettings(SQLiteStatement sQLiteStatement) {
        loadBooleanSetting(sQLiteStatement, "haptic_feedback_enabled", R.bool.def_haptic_feedback);
    }

    private void loadSecureSettings(SQLiteDatabase sQLiteDatabase) {
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR IGNORE INTO secure(name,value) VALUES(?,?);");
            loadSetting(sQLiteStatementCompileStatement, "mock_location", Integer.valueOf("1".equals(SystemProperties.get("ro.allow.mock.location")) ? 1 : 0));
            loadSecure35Settings(sQLiteStatementCompileStatement);
            loadBooleanSetting(sQLiteStatementCompileStatement, "mount_play_not_snd", R.bool.def_mount_play_notification_snd);
            loadBooleanSetting(sQLiteStatementCompileStatement, "mount_ums_autostart", R.bool.def_mount_ums_autostart);
            loadBooleanSetting(sQLiteStatementCompileStatement, "mount_ums_prompt", R.bool.def_mount_ums_prompt);
            loadBooleanSetting(sQLiteStatementCompileStatement, "mount_ums_notify_enabled", R.bool.def_mount_ums_notify_enabled);
            loadIntegerSetting(sQLiteStatementCompileStatement, "long_press_timeout", R.integer.def_long_press_timeout_millis);
            loadBooleanSetting(sQLiteStatementCompileStatement, "touch_exploration_enabled", R.bool.def_touch_exploration_enabled);
            loadBooleanSetting(sQLiteStatementCompileStatement, "speak_password", R.bool.def_accessibility_speak_password);
            if (SystemProperties.getBoolean("ro.lockscreen.disable.default", false)) {
                loadSetting(sQLiteStatementCompileStatement, "lockscreen.disabled", "1");
            } else {
                loadBooleanSetting(sQLiteStatementCompileStatement, "lockscreen.disabled", R.bool.def_lockscreen_disabled);
            }
            loadBooleanSetting(sQLiteStatementCompileStatement, "screensaver_enabled", android.R.bool.config_buttonTextAllCaps);
            loadBooleanSetting(sQLiteStatementCompileStatement, "screensaver_activate_on_dock", android.R.bool.config_bugReportHandlerEnabled);
            loadBooleanSetting(sQLiteStatementCompileStatement, "screensaver_activate_on_sleep", android.R.bool.config_built_in_sip_phone);
            loadStringSetting(sQLiteStatementCompileStatement, "screensaver_components", android.R.string.config_cameraLaunchGestureSensorStringType);
            loadStringSetting(sQLiteStatementCompileStatement, "screensaver_default_component", android.R.string.config_cameraLaunchGestureSensorStringType);
            loadBooleanSetting(sQLiteStatementCompileStatement, "accessibility_display_magnification_enabled", R.bool.def_accessibility_display_magnification_enabled);
            loadFractionSetting(sQLiteStatementCompileStatement, "accessibility_display_magnification_scale", R.fraction.def_accessibility_display_magnification_scale, 1);
            loadBooleanSetting(sQLiteStatementCompileStatement, "user_setup_complete", R.bool.def_user_setup_complete);
            loadStringSetting(sQLiteStatementCompileStatement, "immersive_mode_confirmations", R.string.def_immersive_mode_confirmations);
            loadBooleanSetting(sQLiteStatementCompileStatement, "install_non_market_apps", R.bool.def_install_non_market_apps);
            loadBooleanSetting(sQLiteStatementCompileStatement, "wake_gesture_enabled", R.bool.def_wake_gesture_enabled);
            loadIntegerSetting(sQLiteStatementCompileStatement, "lock_screen_show_notifications", R.integer.def_lock_screen_show_notifications);
            loadBooleanSetting(sQLiteStatementCompileStatement, "lock_screen_allow_private_notifications", R.bool.def_lock_screen_allow_private_notifications);
            loadIntegerSetting(sQLiteStatementCompileStatement, "sleep_timeout", R.integer.def_sleep_timeout);
            loadStringSetting(sQLiteStatementCompileStatement, "default_input_method", R.string.config_default_input_method);
            loadStringSetting(sQLiteStatementCompileStatement, "enabled_input_methods", R.string.config_enabled_input_method);
        } finally {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
        }
    }

    private void loadSecure35Settings(SQLiteStatement sQLiteStatement) {
        loadBooleanSetting(sQLiteStatement, "backup_enabled", R.bool.def_backup_enabled);
        loadStringSetting(sQLiteStatement, "backup_transport", R.string.def_backup_transport);
    }

    private void loadGlobalSettings(SQLiteDatabase sQLiteDatabase) {
        Resources resources = this.mContext.getResources();
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR IGNORE INTO global(name,value) VALUES(?,?);");
            loadBooleanSetting(sQLiteStatementCompileStatement, "airplane_mode_on", R.bool.def_airplane_mode_on);
            loadBooleanSetting(sQLiteStatementCompileStatement, "theater_mode_on", R.bool.def_theater_mode_on);
            loadStringSetting(sQLiteStatementCompileStatement, "airplane_mode_radios", R.string.def_airplane_mode_radios);
            loadStringSetting(sQLiteStatementCompileStatement, "airplane_mode_toggleable_radios", R.string.airplane_mode_toggleable_radios);
            loadBooleanSetting(sQLiteStatementCompileStatement, "assisted_gps_enabled", R.bool.assisted_gps_enabled);
            loadBooleanSetting(sQLiteStatementCompileStatement, "auto_time", R.bool.def_auto_time);
            loadBooleanSetting(sQLiteStatementCompileStatement, "auto_time_zone", R.bool.def_auto_time_zone);
            int i = 1;
            loadSetting(sQLiteStatementCompileStatement, "stay_on_while_plugged_in", Integer.valueOf(("1".equals(SystemProperties.get("ro.kernel.qemu")) || resources.getBoolean(R.bool.def_stay_on_while_plugged_in)) ? 1 : 0));
            loadIntegerSetting(sQLiteStatementCompileStatement, "wifi_sleep_policy", R.integer.def_wifi_sleep_policy);
            loadSetting(sQLiteStatementCompileStatement, "mode_ringer", 2);
            loadDefaultAnimationSettings(sQLiteStatementCompileStatement);
            loadBooleanSetting(sQLiteStatementCompileStatement, "wifi_on", R.bool.def_wifi_on);
            loadBooleanSetting(sQLiteStatementCompileStatement, "wifi_networks_available_notification_on", R.bool.def_networks_available_notification_on);
            loadBooleanSetting(sQLiteStatementCompileStatement, "bluetooth_on", R.bool.def_bluetooth_on);
            loadSetting(sQLiteStatementCompileStatement, "cdma_cell_broadcast_sms", 1);
            loadSetting(sQLiteStatementCompileStatement, "data_roaming", Integer.valueOf(((Boolean) TelephonyProperties.data_roaming().orElse(Boolean.FALSE)).booleanValue() ? 1 : 0));
            loadBooleanSetting(sQLiteStatementCompileStatement, "device_provisioned", R.bool.def_device_provisioned);
            int integer = resources.getInteger(R.integer.def_download_manager_max_bytes_over_mobile);
            if (integer > 0) {
                loadSetting(sQLiteStatementCompileStatement, "download_manager_max_bytes_over_mobile", Integer.toString(integer));
            }
            int integer2 = resources.getInteger(R.integer.def_download_manager_recommended_max_bytes_over_mobile);
            if (integer2 > 0) {
                loadSetting(sQLiteStatementCompileStatement, "download_manager_recommended_max_bytes_over_mobile", Integer.toString(integer2));
            }
            loadSetting(sQLiteStatementCompileStatement, "mobile_data", Integer.valueOf(((Boolean) TelephonyProperties.mobile_data().orElse(Boolean.TRUE)).booleanValue() ? 1 : 0));
            loadBooleanSetting(sQLiteStatementCompileStatement, "netstats_enabled", R.bool.def_netstats_enabled);
            loadBooleanSetting(sQLiteStatementCompileStatement, "usb_mass_storage_enabled", R.bool.def_usb_mass_storage_enabled);
            loadIntegerSetting(sQLiteStatementCompileStatement, "wifi_max_dhcp_retry_count", R.integer.def_max_dhcp_retries);
            loadBooleanSetting(sQLiteStatementCompileStatement, "wifi_display_on", R.bool.def_wifi_display_on);
            loadStringSetting(sQLiteStatementCompileStatement, "lock_sound", R.string.def_lock_sound);
            loadStringSetting(sQLiteStatementCompileStatement, "unlock_sound", R.string.def_unlock_sound);
            loadStringSetting(sQLiteStatementCompileStatement, "trusted_sound", R.string.def_trusted_sound);
            loadIntegerSetting(sQLiteStatementCompileStatement, "power_sounds_enabled", R.integer.def_power_sounds_enabled);
            loadStringSetting(sQLiteStatementCompileStatement, "low_battery_sound", R.string.def_low_battery_sound);
            loadIntegerSetting(sQLiteStatementCompileStatement, "dock_sounds_enabled", R.integer.def_dock_sounds_enabled);
            loadIntegerSetting(sQLiteStatementCompileStatement, "dock_sounds_enabled_when_accessbility", R.integer.def_dock_sounds_enabled_when_accessibility);
            loadStringSetting(sQLiteStatementCompileStatement, "desk_dock_sound", R.string.def_desk_dock_sound);
            loadStringSetting(sQLiteStatementCompileStatement, "desk_undock_sound", R.string.def_desk_undock_sound);
            loadStringSetting(sQLiteStatementCompileStatement, "car_dock_sound", R.string.def_car_dock_sound);
            loadStringSetting(sQLiteStatementCompileStatement, "car_undock_sound", R.string.def_car_undock_sound);
            loadIntegerSetting(sQLiteStatementCompileStatement, "dock_audio_media_enabled", R.integer.def_dock_audio_media_enabled);
            loadSetting(sQLiteStatementCompileStatement, "set_install_location", 0);
            loadSetting(sQLiteStatementCompileStatement, "default_install_location", 0);
            loadSetting(sQLiteStatementCompileStatement, "emergency_tone", 0);
            loadSetting(sQLiteStatementCompileStatement, "call_auto_retry", 0);
            StringBuilder sb = new StringBuilder();
            List listDefault_network = TelephonyProperties.default_network();
            TelephonyManager telephonyManager = getTelephonyManager();
            int supportedModemCount = telephonyManager != null ? telephonyManager.getSupportedModemCount() : 1;
            int i2 = 0;
            while (i2 < supportedModemCount) {
                int iIntValue = (listDefault_network.size() <= i2 || listDefault_network.get(i2) == null) ? TelephonyManager.DEFAULT_PREFERRED_NETWORK_MODE : ((Integer) listDefault_network.get(i2)).intValue();
                if (i2 > 0) {
                    sb.append(',');
                }
                sb.append(iIntValue);
                i2++;
            }
            loadSetting(sQLiteStatementCompileStatement, "preferred_network_mode", sb.toString());
            loadSetting(sQLiteStatementCompileStatement, "subscription_mode", Integer.valueOf(SystemProperties.getInt("ro.telephony.default_cdma_sub", 0)));
            loadIntegerSetting(sQLiteStatementCompileStatement, "low_battery_sound_timeout", R.integer.def_low_battery_sound_timeout);
            loadIntegerSetting(sQLiteStatementCompileStatement, "wifi_scan_always_enabled", R.integer.def_wifi_scan_always_available);
            loadIntegerSetting(sQLiteStatementCompileStatement, "heads_up_notifications_enabled", R.integer.def_heads_up_enabled);
            loadSetting(sQLiteStatementCompileStatement, "device_name", getDefaultDeviceName());
            if (!resources.getBoolean(android.R.bool.config_cecQuerySadDdpDisabled_allowed)) {
                i = resources.getBoolean(android.R.bool.config_cecQuerySadDd_userConfigurable) ? 2 : 0;
            }
            loadSetting(sQLiteStatementCompileStatement, "lid_behavior", Integer.valueOf(i));
            loadIntegerSetting(sQLiteStatementCompileStatement, "hidden_api_policy", R.integer.def_hidden_api_policy);
        } finally {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
        }
    }

    private void loadSetting(SQLiteStatement sQLiteStatement, String str, Object obj) {
        sQLiteStatement.bindString(1, str);
        sQLiteStatement.bindString(2, obj.toString());
        sQLiteStatement.execute();
    }

    private void loadStringSetting(SQLiteStatement sQLiteStatement, String str, int i) {
        loadSetting(sQLiteStatement, str, this.mContext.getResources().getString(i));
    }

    private void loadBooleanSetting(SQLiteStatement sQLiteStatement, String str, int i) {
        loadSetting(sQLiteStatement, str, this.mContext.getResources().getBoolean(i) ? "1" : "0");
    }

    private void loadIntegerSetting(SQLiteStatement sQLiteStatement, String str, int i) {
        loadSetting(sQLiteStatement, str, Integer.toString(this.mContext.getResources().getInteger(i)));
    }

    private void loadFractionSetting(SQLiteStatement sQLiteStatement, String str, int i, int i2) {
        loadSetting(sQLiteStatement, str, Float.toString(this.mContext.getResources().getFraction(i, i2, i2)));
    }

    private int getIntValueFromSystem(SQLiteDatabase sQLiteDatabase, String str, int i) {
        return getIntValueFromTable(sQLiteDatabase, "system", str, i);
    }

    private int getIntValueFromTable(SQLiteDatabase sQLiteDatabase, String str, String str2, int i) {
        String stringValueFromTable = getStringValueFromTable(sQLiteDatabase, str, str2, null);
        return stringValueFromTable != null ? Integer.parseInt(stringValueFromTable) : i;
    }

    private String getStringValueFromTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = sQLiteDatabase.query(str, new String[]{"value"}, "name='" + str2 + "'", null, null, null, null);
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return str3;
            }
            String string = cursorQuery.getString(0);
            if (string != null) {
                str3 = string;
            }
            return str3;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    private String getOldDefaultDeviceName() {
        return this.mContext.getResources().getString(R.string.def_device_name, Build.MANUFACTURER, Build.MODEL);
    }

    private String getDefaultDeviceName() {
        return this.mContext.getResources().getString(R.string.def_device_name_simple, Build.MODEL);
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) this.mContext.getSystemService("phone");
    }
}
