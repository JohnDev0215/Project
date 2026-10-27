package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSMPDRM {
    private static final String LIB_NAME = "voDRMWrapJni";
    private static final String TAG = "@@@voOSMPDRM";
    private static boolean mJNILoaded = false;
    private Long mDrmHandle = new Long(0);

    private native int nativeCreatDRM(String str, String str2);

    private native int nativeDestoryDRM(Long l);

    public int creatDRM(String str, String str2) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " Creat DRM handle by lib name " + str2, new Object[0]);
        }
        if (!loadJNI(str)) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        this.mDrmHandle = new Long(0L);
        try {
            int iNativeCreatDRM = nativeCreatDRM(str, str2);
            voLog.i(TAG, "nativeCreatDRM  mDrmHandle: " + this.mDrmHandle, new Object[0]);
            return this.mDrmHandle.equals(0L) ? voOSType.VOOSMP_ERR_JNI : iNativeCreatDRM;
        } catch (UnsatisfiedLinkError unused) {
            if (!voLog.enablePrintLog()) {
                return voOSType.VOOSMP_ERR_JNI;
            }
            voLog.e(TAG, "Call native function creatDRM error.", new Object[0]);
            return voOSType.VOOSMP_ERR_JNI;
        }
    }

    public int destoryDRM() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " destory DRM handle 0x" + Long.toHexString(this.mDrmHandle.longValue()), new Object[0]);
        }
        try {
            return nativeDestoryDRM(this.mDrmHandle);
        } catch (UnsatisfiedLinkError unused) {
            if (voLog.enablePrintLog()) {
                voLog.e(TAG, "Call native function nativeDestoryDRM error.", new Object[0]);
            }
            return voOSType.VOOSMP_ERR_JNI;
        }
    }

    private static boolean loadJNI(String str) {
        boolean z = mJNILoaded;
        if (z) {
            return z;
        }
        boolean zLoadProductLib = voLoadJNI.loadProductLib(str, LIB_NAME);
        mJNILoaded = zLoadProductLib;
        if (!zLoadProductLib && voLog.enablePrintLog()) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
        }
        return mJNILoaded;
    }

    public Long getmDrmHandle() {
        return this.mDrmHandle;
    }
}
