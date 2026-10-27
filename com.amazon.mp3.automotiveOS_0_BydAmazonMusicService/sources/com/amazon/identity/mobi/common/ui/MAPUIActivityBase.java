package com.amazon.identity.mobi.common.ui;

import android.app.Activity;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class MAPUIActivityBase extends Activity {
    private int getResourceId(String str, String str2) {
        int identifier = getResources().getIdentifier(str2, str, getPackageName());
        if (identifier != 0) {
            return identifier;
        }
        Log.e("MAPActivityBase", String.format("The %s resource %s has not been found", str, str2));
        throw new IllegalArgumentException(String.format("%s Resource %s not found", str, str2));
    }

    public int getAnimId(String str) {
        return getResourceId("anim", str);
    }

    public int getId(String str) {
        return getResourceId("id", str);
    }

    public int getLayoutId(String str) {
        return getResourceId("layout", str);
    }
}
