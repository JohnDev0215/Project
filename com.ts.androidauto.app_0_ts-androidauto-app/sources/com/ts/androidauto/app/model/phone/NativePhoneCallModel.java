package com.ts.androidauto.app.model.phone;

import android.content.Context;
import android.os.Looper;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener;

/* JADX INFO: loaded from: classes2.dex */
public class NativePhoneCallModel extends IModel {
    private static final String TAG = "AAP" + NativePhoneCallModel.class.getSimpleName();
    private static final int XCALL_STATUS_CALL_CONNECTED = 6;
    private static final int XCALL_STATUS_CALL_CONNECTING = 5;
    private static final int XCALL_STATUS_CALL_DATA_SENDING = 7;
    private static final int XCALL_STATUS_CALL_END = 8;
    private static final int XCALL_STATUS_CALL_FAILED = 4;
    private static final int XCALL_STATUS_CALL_FAILED_RECONNECTION = 3;
    private static final int XCALL_STATUS_CALL_START = 1;
    private static final int XCALL_STATUS_E_CALL_PRECALL = 10;
    private static final int XCALL_STATUS_INCOMING_CALL = 2;
    private static final int XCALL_STATUS_INVALID = 0;
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private IAndroidAutoRemoteUiManagerListener mAndroidAutoRemoteUiManagerListener;
    private Context mContext;
    private int mIsPreNativeCallStatus;
    private int mNativePhoneCallStatus;

    public NativePhoneCallModel(Looper looper) {
        super(looper);
        this.mNativePhoneCallStatus = 1;
        this.mAndroidAutoRemoteUiManagerListener = new AndroidAutoRemoteUiManagerListener();
        this.mIsPreNativeCallStatus = -1;
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        this.mAndroidAutoRemoteUiManager = androidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.addAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
        }
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        if (this.mAndroidAutoRemoteUiManager != null) {
            this.mAndroidAutoRemoteUiManager = null;
        }
    }

    private class AndroidAutoRemoteUiManagerListener implements IAndroidAutoRemoteUiManagerListener {
        private AndroidAutoRemoteUiManagerListener() {
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onServiceStateChanged(boolean connected) {
            LogUtil.info(NativePhoneCallModel.TAG, "onServiceStateChanged connected=" + connected);
            if (connected && NativePhoneCallModel.this.mAndroidAutoRemoteUiManager != null) {
                int currentCallState = NativePhoneCallModel.this.mAndroidAutoRemoteUiManager.getEcallStatus();
                NativePhoneCallModel.this.convertCallStatus(currentCallState);
                NativePhoneCallModel.this.sendNativePhoneCallStatus();
                LogUtil.info(NativePhoneCallModel.TAG, "onServiceStateChanged currentCallState " + currentCallState);
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onEcallStatusChanged(int status) {
            LogUtil.info(NativePhoneCallModel.TAG, "onEcallStatusChanged ecallstatus: " + status);
            NativePhoneCallModel.this.convertCallStatus(status);
            NativePhoneCallModel.this.sendNativePhoneCallStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convertCallStatus(int ebCallStates) {
        if (ebCallStates != 10) {
            switch (ebCallStates) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    break;
                default:
                    this.mNativePhoneCallStatus = 1;
                    break;
            }
        }
        this.mNativePhoneCallStatus = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNativePhoneCallStatus() {
        if (this.mNativePhoneCallStatus != this.mIsPreNativeCallStatus) {
            LogUtil.info(TAG, "sendNativePhoneCallStatus: " + this.mNativePhoneCallStatus);
            post(new Runnable() { // from class: com.ts.androidauto.app.model.phone.-$$Lambda$NativePhoneCallModel$YzuhVFSC4Pm22I2KX-lzPDO9bOg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sendNativePhoneCallStatus$0$NativePhoneCallModel();
                }
            });
            this.mIsPreNativeCallStatus = this.mNativePhoneCallStatus;
        }
    }

    public /* synthetic */ void lambda$sendNativePhoneCallStatus$0$NativePhoneCallModel() {
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.sendNativePhoneCallStatus(this.mNativePhoneCallStatus);
        }
    }
}
