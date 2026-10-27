package com.ecarx.xui.adaptapi.sign;

import android.content.Context;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class SignVerify {
    private static final String cTag = "SignVerify";
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
        LogTool.d(cTag, "appVerifySignKey context=" + context.getPackageName() + " keys=" + str);
        boolean z = verifySignKey(context, str) == 1;
        this.verifyList.put(context.getPackageName(), Boolean.valueOf(z));
        LogTool.d(cTag, "appVerifySignKey res=" + z);
        return z;
    }

    public boolean appVerify(Context context) {
        Boolean bool = this.verifyList.get(context.getPackageName());
        LogTool.d(cTag, "appVerify val=" + bool + " context=" + context.getPackageName());
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zVerifyWhiteApp = verifyWhiteApp(context);
        LogTool.d(cTag, "verifyWhiteApp res=" + zVerifyWhiteApp);
        return zVerifyWhiteApp;
    }
}
