package com.neusoft.optimus.utility.sign;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class SignVerify {
    private static SignVerify instance = new SignVerify();
    Map<String, Boolean> verifyList = new HashMap();

    public native String getVerifyVersion();

    public native boolean verifyEnablePermissionKey(Context context, String str);

    public native int verifySignKey(Context context, String str);

    public native boolean verifyWhiteApp(Context context);

    static {
        System.loadLibrary("signer_verify");
    }

    private SignVerify() {
    }

    public static SignVerify instance() {
        return instance;
    }

    public boolean appVerifySignKey(Context context, String str) {
        boolean z = verifySignKey(context, str) == 1;
        this.verifyList.put(context.getPackageName(), Boolean.valueOf(z));
        return z;
    }

    public boolean appVerify(Context context) {
        Boolean bool = this.verifyList.get(context.getPackageName());
        SignVerify signVerify = new SignVerify();
        if (bool != null) {
            return bool.booleanValue();
        }
        return signVerify.verifyWhiteApp(context);
    }
}
