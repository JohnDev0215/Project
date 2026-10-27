package com.byd.vrsettings.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import com.byd.common.bean.WuwInfoBean;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.utils.CustomLogToolDialog;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class LogToolObserver extends BroadcastReceiver {
    public static String TAG = "NEU_LogToolObserver";
    private CustomLogToolDialog logToolDialog;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        RLog.i(TAG, "onReceive: =" + action);
        if (action.equals("com.byd.action.OpenVRSettingsLogSwitch")) {
            if (this.logToolDialog == null) {
                this.mWindowManager = (WindowManager) BydVoiceSettingApplication.getContext().getSystemService("window");
                CustomLogToolDialog customLogToolDialog = new CustomLogToolDialog(BydVoiceSettingApplication.getContext());
                this.logToolDialog = customLogToolDialog;
                customLogToolDialog.setListener(new CustomLogToolDialog.Listener() { // from class: com.byd.vrsettings.receiver.LogToolObserver.1
                    @Override // com.byd.vrsettings.utils.CustomLogToolDialog.Listener
                    public void clickCloseBtn() {
                        LogToolObserver.this.mWindowManager.removeViewImmediate(LogToolObserver.this.logToolDialog);
                    }
                });
                this.mLayoutParams = getBarParams();
            }
            this.mWindowManager.addView(this.logToolDialog, this.mLayoutParams);
            return;
        }
        if (action.equals("bydlogtool.action.savelogresult")) {
            boolean z = intent.getExtras().getBoolean(WuwInfoBean.ReturnKey);
            CustomLogToolDialog customLogToolDialog2 = this.logToolDialog;
            if (customLogToolDialog2 != null) {
                customLogToolDialog2.setPackOk(z);
            }
        }
    }

    private WindowManager.LayoutParams getBarParams() {
        RLog.d(TAG, "getBarParams: ");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = -3;
        layoutParams.type = 2038;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 17;
        layoutParams.flags = 393248;
        return layoutParams;
    }
}
