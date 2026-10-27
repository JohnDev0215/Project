package com.byd.can;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class CanNativeHelper {
    private static final String TAG = "CanNativeHelper";
    private static final String version = "V1.2.7";
    private final boolean DBG = true;
    private a mListener;
    private long mNativeContext;

    public interface a {
        void p(int i2, byte[] bArr, int i3);
    }

    static {
        System.out.println("CanNativeHelper:start load JniCanCollect lib");
        try {
            System.loadLibrary("JniCanCollect");
        } catch (UnsatisfiedLinkError e2) {
            System.out.println("CanNativeHelper:can not load JniCanCollect lib,error:" + e2.getMessage());
        }
        System.out.println("CanNativeHelper:load JniCanCollect lib sucess");
    }

    public CanNativeHelper() {
        System.out.println("CanNativeHelper:new CanNativeHelper object");
    }

    private final native void native_destroy();

    private native void native_init(Object obj);

    private native byte native_sendCloudCmd(Object obj, byte[] bArr, int i2);

    private final native void native_setparam(int i2);

    private static void postDataFromNative(Object obj, int i2, byte[] bArr, int i3) {
        a aVar;
        CanNativeHelper canNativeHelper = (CanNativeHelper) ((WeakReference) obj).get();
        if (canNativeHelper == null || (aVar = canNativeHelper.mListener) == null) {
            return;
        }
        aVar.p(i2, bArr, i3);
    }

    public void destroy() {
        native_destroy();
    }

    public void init() {
        native_init(new WeakReference(this));
    }

    public byte sendCloudCmd(byte[] bArr, int i2) {
        System.out.println("CanNativeHelper:sendCloudCmd start send json command to server");
        return native_sendCloudCmd(this, bArr, i2);
    }

    public void setListener(a aVar) {
        this.mListener = aVar;
    }

    public void setparam(int i2) {
        native_setparam(i2);
    }
}
