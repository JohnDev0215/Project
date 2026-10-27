package com.android.phone.vvm;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.VisualVoicemailSmsFilterSettings;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class VisualVoicemailSmsFilterConfig {
    public static void enableVisualVoicemailSmsFilter(Context context, String str, int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) {
        Editor editor = new Editor(context, str, i);
        Editor.access$000(editor, "_enabled", true);
        Editor.access$100(editor, "_prefix", visualVoicemailSmsFilterSettings.clientPrefix);
        Editor.access$200(editor, "_originating_numbers", visualVoicemailSmsFilterSettings.originatingNumbers);
        Editor.access$300(editor, "_destination_port", visualVoicemailSmsFilterSettings.destinationPort);
        editor.apply();
    }

    public static void disableVisualVoicemailSmsFilter(Context context, String str, int i) {
        Editor editor = new Editor(context, str, i);
        Editor.access$000(editor, "_enabled", false);
        editor.apply();
    }

    public static VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(Context context, int i) {
        ComponentName remotePackage = RemoteVvmTaskManager.getRemotePackage(context, i);
        return getVisualVoicemailSmsFilterSettings(context, remotePackage == null ? "com.android.phone" : remotePackage.getPackageName(), i);
    }

    public static VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(Context context, String str, int i) {
        Reader reader = new Reader(context, str, i);
        if (reader.getBoolean("_enabled", false)) {
            return new VisualVoicemailSmsFilterSettings.Builder().setClientPrefix(reader.getString("_prefix", "//VVM")).setOriginatingNumbers(reader.getStringSet("_originating_numbers", VisualVoicemailSmsFilterSettings.DEFAULT_ORIGINATING_NUMBERS)).setDestinationPort(reader.getInt("_destination_port", -1)).setPackageName(str).build();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.createDeviceProtectedStorageContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String makePerPhoneAccountKeyPrefix(String str, int i) {
        return "vvm_sms_filter_config_" + str + "_" + i;
    }

    private static class Editor {
        private final String mKeyPrefix;
        private final SharedPreferences.Editor mPrefsEditor;

        static /* synthetic */ Editor access$000(Editor editor, String str, boolean z) {
            editor.setBoolean(str, z);
            return editor;
        }

        static /* synthetic */ Editor access$100(Editor editor, String str, String str2) {
            editor.setString(str, str2);
            return editor;
        }

        static /* synthetic */ Editor access$200(Editor editor, String str, List list) {
            editor.setStringList(str, list);
            return editor;
        }

        static /* synthetic */ Editor access$300(Editor editor, String str, int i) {
            editor.setInt(str, i);
            return editor;
        }

        public Editor(Context context, String str, int i) {
            this.mPrefsEditor = VisualVoicemailSmsFilterConfig.getSharedPreferences(context).edit();
            this.mKeyPrefix = VisualVoicemailSmsFilterConfig.makePerPhoneAccountKeyPrefix(str, i);
        }

        private Editor setInt(String str, int i) {
            this.mPrefsEditor.putInt(makeKey(str), i);
            return this;
        }

        private Editor setString(String str, String str2) {
            this.mPrefsEditor.putString(makeKey(str), str2);
            return this;
        }

        private Editor setBoolean(String str, boolean z) {
            this.mPrefsEditor.putBoolean(makeKey(str), z);
            return this;
        }

        private Editor setStringList(String str, List<String> list) {
            this.mPrefsEditor.putStringSet(makeKey(str), new ArraySet(list));
            return this;
        }

        public void apply() {
            this.mPrefsEditor.apply();
        }

        private String makeKey(String str) {
            return this.mKeyPrefix + str;
        }
    }

    private static class Reader {
        private final String mKeyPrefix;
        private final SharedPreferences mPrefs;

        public Reader(Context context, String str, int i) {
            this.mPrefs = VisualVoicemailSmsFilterConfig.getSharedPreferences(context);
            this.mKeyPrefix = VisualVoicemailSmsFilterConfig.makePerPhoneAccountKeyPrefix(str, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getInt(String str, int i) {
            return this.mPrefs.getInt(makeKey(str), i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getString(String str, String str2) {
            return this.mPrefs.getString(makeKey(str), str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean getBoolean(String str, boolean z) {
            return this.mPrefs.getBoolean(makeKey(str), z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<String> getStringSet(String str, List<String> list) {
            Set<String> stringSet = this.mPrefs.getStringSet(makeKey(str), null);
            return stringSet == null ? list : new ArrayList(stringSet);
        }

        private String makeKey(String str) {
            return this.mKeyPrefix + str;
        }
    }
}
