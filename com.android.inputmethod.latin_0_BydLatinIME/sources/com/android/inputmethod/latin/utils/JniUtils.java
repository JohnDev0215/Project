package com.android.inputmethod.latin.utils;

import android.util.Log;
import com.android.inputmethod.latin.define.JniLibName;

/* JADX INFO: loaded from: classes2.dex */
public final class JniUtils {
    private static final String TAG = "JniUtils";

    public static void loadNativeLibrary() {
    }

    static {
        try {
            System.loadLibrary(JniLibName.JNI_LIB_NAME);
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "Could not load native library jni_latinime", e);
        }
    }

    private JniUtils() {
    }
}
