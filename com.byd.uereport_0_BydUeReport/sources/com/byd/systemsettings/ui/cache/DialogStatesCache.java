package com.byd.systemsettings.ui.cache;

import com.byd.systemsettings.ui.cache.model.DialogState;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogStatesCache {
    public static final String TAG = "DialogStatesCache";
    private static ConcurrentHashMap<String, DialogState> mDialogStateMap;

    public static void clear() {
        ConcurrentHashMap<String, DialogState> concurrentHashMap = mDialogStateMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static DialogState get(String str) {
        if (mDialogStateMap == null) {
            mDialogStateMap = new ConcurrentHashMap<>();
        }
        return mDialogStateMap.get(str);
    }

    public static void put(String str, DialogState dialogState) {
        if (mDialogStateMap == null) {
            mDialogStateMap = new ConcurrentHashMap<>();
        }
        mDialogStateMap.put(str, dialogState);
    }

    public static void remove(String str) {
        ConcurrentHashMap<String, DialogState> concurrentHashMap = mDialogStateMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(str);
        }
    }
}
