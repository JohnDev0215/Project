package com.idd.comm;

import android.content.Context;
import dalvik.system.BaseDexClassLoader;

/* JADX INFO: loaded from: classes4.dex */
public class IddServiceManagerLoader {
    private static volatile IIddServiceManager instance;

    public static IIddServiceManager getInstance(Context context) {
        if (instance == null) {
            synchronized (IddServiceManagerLoader.class) {
                if (instance == null) {
                    try {
                        instance = (IIddServiceManager) new BaseDexClassLoader("/system/framework/idd-idps-comm.jar", null, null, IddServiceManagerLoader.class.getClassLoader()).loadClass("com.idd.comm.IddServiceManager").newInstance();
                        instance.init(context);
                    } catch (Exception e) {
                        e.printStackTrace();
                        instance = null;
                    }
                }
            }
        }
        return instance;
    }
}
