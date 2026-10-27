package com.ecarx.xui.adaptapi.pms;

import android.content.Context;
import android.content.SharedPreferences;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionImp implements IPermission {
    private Context context;
    private boolean permissionEnable;
    private SignVerify sv = SignVerify.instance();
    private String cTag = "IPermission";
    IPermissionListener _listener = null;

    public PermissionImp(Context context) {
        this.context = context;
    }

    @Override // com.ecarx.xui.adaptapi.pms.IPermission
    public boolean enablePermissionModule(Context context, String str, boolean z) {
        LogTool.d(this.cTag, "enablePermissionModule context=" + context.getPackageName() + " enableKey=" + str + " enable=" + z);
        if (!this.sv.verifyEnablePermissionKey(context, str)) {
            return false;
        }
        LogTool.d(this.cTag, "verifyEnablePermissionKey success");
        SharedPreferences sharedPreferences = context.getSharedPreferences("permission", 0);
        this.permissionEnable = z;
        sharedPreferences.edit().putBoolean("perEnable", this.permissionEnable).apply();
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.pms.IPermission
    public boolean isPermissionModuleEnabled() {
        this.permissionEnable = this.context.getSharedPreferences("permission", 0).getBoolean("perEnable", true);
        LogTool.d(this.cTag, "isPermissionModuleEnabled permissionEnable=" + this.permissionEnable);
        return this.permissionEnable;
    }

    @Override // com.ecarx.xui.adaptapi.pms.IPermission
    public void requestPermission(Context context, String str, IPermissionListener iPermissionListener) {
        requestPermission(context, str, new int[]{0}, iPermissionListener);
    }

    public boolean appVerify(Context context) {
        return this.sv.appVerify(context);
    }

    @Override // com.ecarx.xui.adaptapi.pms.IPermission
    public void requestPermission(Context context, String str, int[] iArr, IPermissionListener iPermissionListener) {
        LogTool.d(this.cTag, "requestPermission context=" + context.getPackageName() + " apiKey=" + str + " listener=" + iPermissionListener);
        if (iPermissionListener == null || !this.permissionEnable) {
            return;
        }
        this._listener = iPermissionListener;
        boolean zAppVerifySignKey = this.sv.appVerifySignKey(context, str);
        LogTool.d(this.cTag, "appVerifySignKey res=" + zAppVerifySignKey);
        if (zAppVerifySignKey) {
            this._listener.onPermissionAllowed(iArr);
        } else {
            this._listener.onPermissionDenied(iArr);
        }
    }

    @Override // com.ecarx.xui.adaptapi.pms.IPermission
    public void releasePermissionListener(IPermissionListener iPermissionListener) {
        this._listener = null;
    }
}
