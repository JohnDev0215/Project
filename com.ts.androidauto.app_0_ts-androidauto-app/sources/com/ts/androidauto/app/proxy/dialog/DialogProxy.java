package com.ts.androidauto.app.proxy.dialog;

import android.content.Context;
import android.os.Looper;
import com.ts.androidauto.app.common.IProxy;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener;
import com.ts.androidauto.app.proxy.dialog.DialogProxy;

/* JADX INFO: loaded from: classes2.dex */
public class DialogProxy extends IProxy {
    private static final String TAG = "AAP" + DialogProxy.class.getSimpleName();
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private IAndroidAutoRemoteUiManagerListener mAndroidAutoRemoteUiManagerListener;
    private Context mContext;

    public DialogProxy(Looper looper) {
        super(looper);
        this.mAndroidAutoRemoteUiManagerListener = new AndroidAutoRemoteUiManagerListener();
    }

    @Override // com.ts.androidauto.app.common.IProxy
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        this.mAndroidAutoRemoteUiManager = androidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.addAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
        }
    }

    @Override // com.ts.androidauto.app.common.IProxy
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        this.mContext = null;
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.removeAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
            this.mAndroidAutoRemoteUiManager = null;
        }
        removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AndroidAutoRemoteUiManagerListener implements IAndroidAutoRemoteUiManagerListener {
        private AndroidAutoRemoteUiManagerListener() {
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onStatusChanged(final int linkStatus) {
            LogUtil.debug(DialogProxy.TAG, "onStatusChanged linkStatus:" + linkStatus);
            DialogProxy.this.post(new Runnable() { // from class: com.ts.androidauto.app.proxy.dialog.-$$Lambda$DialogProxy$AndroidAutoRemoteUiManagerListener$m3pmwfLltsgdO8qiJ9BzXUJMO6s
                @Override // java.lang.Runnable
                public final void run() {
                    DialogProxy.AndroidAutoRemoteUiManagerListener.lambda$onStatusChanged$0(linkStatus);
                }
            });
        }

        static /* synthetic */ void lambda$onStatusChanged$0(int linkStatus) {
            if (linkStatus != 6 && linkStatus == 4) {
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onErrorCodeNotification(final int errorCode, String message) {
            DialogProxy.this.post(new Runnable() { // from class: com.ts.androidauto.app.proxy.dialog.-$$Lambda$DialogProxy$AndroidAutoRemoteUiManagerListener$VxIf-w7fMdu-Zc6UYN1EeSP6A8w
                @Override // java.lang.Runnable
                public final void run() {
                    DialogProxy.AndroidAutoRemoteUiManagerListener.lambda$onErrorCodeNotification$1(errorCode);
                }
            });
        }

        static /* synthetic */ void lambda$onErrorCodeNotification$1(int errorCode) {
            if (errorCode != 4 && errorCode != 5 && errorCode != 9 && errorCode != 8 && errorCode != 13 && errorCode != 10 && errorCode == 12) {
                LogUtil.verbose(DialogProxy.TAG, "notifyWifiConnectErrorMessage errorCode is " + errorCode);
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, final boolean batteryCritical) {
            DialogProxy.this.post(new Runnable() { // from class: com.ts.androidauto.app.proxy.dialog.-$$Lambda$DialogProxy$AndroidAutoRemoteUiManagerListener$Y9vyHTNkX9PWloYslyTqJ9cOjzc
                @Override // java.lang.Runnable
                public final void run() {
                    DialogProxy.AndroidAutoRemoteUiManagerListener.lambda$onBatteryStatusNotification$2(batteryCritical);
                }
            });
        }

        static /* synthetic */ void lambda$onBatteryStatusNotification$2(boolean batteryCritical) {
        }
    }
}
