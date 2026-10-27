package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voLua {
    private static final String LIB_NAME = "voLua";
    private static final String TAG = "@@@voLua.java";
    private static boolean mJNILoaded = false;
    private long mNativeContext = 0;
    private long mLuaJNIPlayer = 0;

    private native int nativeCreate(String str, Object obj, Object obj2, Object obj3);

    private native int nativeDestroy();

    private native int nativeVOEvent(int i, int i2, int i3, Object obj);

    private static boolean loadJNI(String str) {
        boolean z = mJNILoaded;
        if (z) {
            return z;
        }
        boolean zLoadProductLib = voLoadJNI.loadProductLib(str, LIB_NAME);
        mJNILoaded = zLoadProductLib;
        if (!zLoadProductLib) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
        }
        return mJNILoaded;
    }

    public boolean init(String str, Object obj) {
        return init(str, obj, null, null);
    }

    public boolean init(String str, Object obj, Object obj2, Object obj3) {
        if (!voLoadJNI.loadProductLib(str, LIB_NAME)) {
            return false;
        }
        this.mNativeContext = 0L;
        if ("".equals(str)) {
            str = null;
        }
        nativeCreate(str, obj, obj2, obj3);
        return 0 != this.mNativeContext;
    }

    public boolean uninit() {
        nativeDestroy();
        return true;
    }

    public int onVOEvent(int i, int i2, int i3, Object obj) {
        return nativeVOEvent(i, i2, i3, obj);
    }
}
