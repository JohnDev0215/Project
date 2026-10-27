package com.ts.androidauto.app.model.vehicle;

import android.content.Context;
import android.os.Looper;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.common.VehicleInfoLoader;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleModel extends IModel {
    private static final String TAG = "AAP" + VehicleModel.class.getSimpleName();
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private IAndroidAutoRemoteUiManagerListener mAndroidAutoRemoteUiManagerListener;
    private Context mContext;
    private boolean mIsNeedSendVehicleInfo;
    private boolean mIsVehicleInfoReady;
    private VehicleData mVehicleInfo;

    public VehicleModel(Looper looper) {
        super(looper);
        this.mAndroidAutoRemoteUiManagerListener = new AndroidAutoRemoteUiManagerListener();
        this.mIsVehicleInfoReady = false;
        this.mIsNeedSendVehicleInfo = false;
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
        createVehicleInfoAsync();
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.removeAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
            this.mAndroidAutoRemoteUiManager = null;
        }
        removeCallbacksAndMessages(null);
    }

    private void createVehicleInfoAsync() {
        post(new Runnable() { // from class: com.ts.androidauto.app.model.vehicle.-$$Lambda$VehicleModel$ZC3KB654ql19PLwZhiTFCsS3d-A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createVehicleInfoAsync$0$VehicleModel();
            }
        });
    }

    public /* synthetic */ void lambda$createVehicleInfoAsync$0$VehicleModel() {
        LogUtil.info(TAG, "updateVehicleInfoAsync");
        this.mVehicleInfo = VehicleInfoLoader.getInstance().getVehicleInfo(this.mContext);
        this.mIsVehicleInfoReady = true;
        if (this.mIsNeedSendVehicleInfo) {
            sendVehicleInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendVehicleInfo() {
        LogUtil.info(TAG, "sendVehicleInfo entry");
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager == null) {
            LogUtil.warning(TAG, "sendVehicleInfo: mAndroidAutoRemoteUiManager is null.");
        } else {
            androidAutoRemoteUiManager.sendVehicleInfo(this.mVehicleInfo);
            this.mIsNeedSendVehicleInfo = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AndroidAutoRemoteUiManagerListener implements IAndroidAutoRemoteUiManagerListener {
        private AndroidAutoRemoteUiManagerListener() {
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onServiceStateChanged(boolean connected) {
            LogUtil.info(VehicleModel.TAG, "onServiceStateChanged connected=" + connected);
            if (connected) {
                VehicleModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.model.vehicle.-$$Lambda$VehicleModel$AndroidAutoRemoteUiManagerListener$eOmwsmIQDmjVzq79KybXoVuDwBE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onServiceStateChanged$0$VehicleModel$AndroidAutoRemoteUiManagerListener();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onServiceStateChanged$0$VehicleModel$AndroidAutoRemoteUiManagerListener() {
            if (VehicleModel.this.mIsVehicleInfoReady) {
                VehicleModel.this.sendVehicleInfo();
            } else {
                VehicleModel.this.mIsNeedSendVehicleInfo = true;
            }
        }
    }
}
