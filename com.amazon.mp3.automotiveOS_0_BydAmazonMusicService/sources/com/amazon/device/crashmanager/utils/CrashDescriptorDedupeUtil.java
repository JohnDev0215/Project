package com.amazon.device.crashmanager.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class CrashDescriptorDedupeUtil {
    private static final String KEY_DESCRIPTORS = "CrashDescriptors";
    private static final String KEY_SEPARATOR = "#";
    private static final DPLogger log = new DPLogger("CrashManager.DedupeUtil");
    private final Map<String, Integer> mDescriptorMap = new HashMap();
    private final SharedPreferences mSharedPreferences;

    public CrashDescriptorDedupeUtil(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
        loadFromSharedPreference();
    }

    public void increaseCounter(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = str + "#" + str2;
        Map<String, Integer> map = this.mDescriptorMap;
        map.put(str3, Integer.valueOf(map.get(str3) != null ? 1 + this.mDescriptorMap.get(str3).intValue() : 1));
        log.debug("increaseCounter", "increaseCounter, descriptor=" + str3 + " counter=" + this.mDescriptorMap.get(str3), new Object[0]);
    }

    public void prune(String str, String str2) {
        this.mSharedPreferences.edit().remove(str + "#" + str2).commit();
    }

    public void clearAll() {
        this.mSharedPreferences.edit().clear().commit();
    }

    public boolean contains(String str, String str2) {
        return getCount(str, str2) > 0;
    }

    public int getCount(String str, String str2) {
        return this.mSharedPreferences.getInt(str + "#" + str2, 0);
    }

    public void persistCrashDescriptors() {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.clear();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : this.mDescriptorMap.entrySet()) {
            log.debug("saveToSharedPreferences", "Save Key =", entry.getKey());
            sb.append(entry.getKey());
            sb.append(" ");
            if (entry.getValue() != null) {
                editorEdit.putInt(entry.getKey(), entry.getValue().intValue());
            }
        }
        editorEdit.putString(KEY_DESCRIPTORS, sb.toString());
        editorEdit.commit();
    }

    private void loadFromSharedPreference() {
        String string = this.mSharedPreferences.getString(KEY_DESCRIPTORS, null);
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split(" ")) {
                try {
                    int i = this.mSharedPreferences.getInt(str, 0);
                    if (i > 0) {
                        this.mDescriptorMap.put(str, Integer.valueOf(i));
                    }
                } catch (ClassCastException e) {
                    log.warn("loadFromSharedPreferences", "Error retrieving descriptor.", e);
                }
            }
        }
        log.debug("loadFromSharedPreference", "finish loading, map size = " + this.mDescriptorMap.size(), new Object[0]);
    }

    public static void crashDuplicateHeader(String str, String str2, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil, Writer writer, DetUtil.HeaderProcessor headerProcessor) throws Exception {
        Integer numValueOf;
        if (str == null || str2 == null || (numValueOf = Integer.valueOf(crashDescriptorDedupeUtil.getCount(str, str2))) == null || numValueOf.intValue() <= 1) {
            return;
        }
        headerProcessor.process("CrashDuplicateCount", numValueOf.toString(), writer);
    }
}
