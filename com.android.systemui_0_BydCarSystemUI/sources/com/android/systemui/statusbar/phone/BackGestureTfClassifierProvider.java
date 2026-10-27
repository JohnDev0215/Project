package com.android.systemui.statusbar.phone;

import android.content.res.AssetManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BackGestureTfClassifierProvider {
    private static final String TAG = "BackGestureTfClassifierProvider";

    public boolean isActive() {
        return false;
    }

    public float predict(Object[] featuresVector) {
        return -1.0f;
    }

    public void release() {
    }

    public Map<String, Integer> loadVocab(AssetManager am) {
        return new HashMap();
    }
}
