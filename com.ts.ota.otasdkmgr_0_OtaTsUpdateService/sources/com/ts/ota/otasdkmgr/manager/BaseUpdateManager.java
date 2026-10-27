package com.ts.ota.otasdkmgr.manager;

import android.os.Bundle;
import android.util.Log;
import com.byd.ota.master.interfaces.IBaseUpdateManager;
import com.byd.ota.master.listener.UpdateBaseListener;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.qnxmanager.QnxManager;

/* JADX INFO: loaded from: classes.dex */
public class BaseUpdateManager implements IBaseUpdateManager {
    private static final String TAG = "TS.BaseUpdateManager";
    private UpdateBaseListener listener = null;
    private QnxManager manager = QnxManager.getInstance();
    private final int TYPE_OTA = 8;
    private final int TYPE_SUBTYPE_DEFAULT = 0;
    private IQnxCallBack.Stub callBack = new IQnxCallBack.Stub() { // from class: com.ts.ota.otasdkmgr.manager.BaseUpdateManager.1
        @Override // com.ts.lib.qnx.IQnxCallBack
        public void onMessageChanged(Bundle bundle) {
            Log.i(BaseUpdateManager.TAG, "register onMessageChanged!!!");
            if (BaseUpdateManager.this.listener == null) {
                Log.e(BaseUpdateManager.TAG, "onMessageChanged: listener is null");
            } else {
                bundle.setClassLoader(getClass().getClassLoader());
                BaseUpdateManager.this.listener.onMessageChanged(bundle);
            }
        }
    };

    public BaseUpdateManager() {
        Log.i(TAG, " BaseUpdateManager.in");
        this.manager.registerCallback(8, 0, this.callBack);
        Log.i(TAG, " BaseUpdateManager.out");
    }

    public boolean sendMessage(int i, Bundle bundle) {
        Log.i(TAG, " sendMessage.in");
        QnxManager qnxManager = this.manager;
        if (qnxManager == null) {
            Log.e(TAG, " sendMessage manager is null");
            return false;
        }
        boolean result = qnxManager.sendMessage(8, i, null, bundle);
        Log.i(TAG, " sendMessage result is " + result);
        Log.i(TAG, " sendMessage.out");
        return result;
    }

    public void registerCallback(UpdateBaseListener updateBaseListener) {
        Log.i(TAG, "registerCallback: UpdateBaseListener= " + updateBaseListener);
        this.listener = updateBaseListener;
    }

    public void unregisterCallback(UpdateBaseListener updateBaseListener) {
        UpdateBaseListener updateBaseListener2 = this.listener;
        if (updateBaseListener2 != null && updateBaseListener2 == updateBaseListener) {
            this.listener = null;
        }
    }
}
