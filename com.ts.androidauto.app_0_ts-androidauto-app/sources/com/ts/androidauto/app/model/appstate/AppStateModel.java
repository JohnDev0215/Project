package com.ts.androidauto.app.model.appstate;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.common.data.Constants;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.INavigationDataListener;

/* JADX INFO: loaded from: classes2.dex */
public class AppStateModel extends IModel {
    private static final int DATA_TYPE_NAVI_SEND_NAVI_STATE = 35;
    private static final String KEY_BUNDLE_MAP_TYPE = "mapType";
    private static final String KEY_BUNDLE_NAVI_STATE = "naviState";
    private static final String KEY_MSG_TYPE = "msgType";
    private static final String KEY_SUB_TYPE = "subtype";
    private static final int MAP_TYPE_LOCAL = 1;
    private static final int MSG_TYPE_NAVI = 3;
    private static final int NAVI_STATE_END = 2;
    private static final int NAVI_STATE_START = 1;
    private static final int SUB_TYPE_NAVI_SEND_NAVI_STATE = 2;
    private static final String TAG = "AppStateModel";
    private AndroidAutoRemoteUiManager androidAutoRemoteUiManager;
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private IClusterCommCallback mClusterCommCallback;
    private Context mContext;
    private boolean mIsNativeNaviActive;
    private boolean mIsPreNativeNaviActive;
    private INavigationDataListener mNavigationDataListener;
    private final IServiceConnectionListener mServiceConnectionListener;

    public AppStateModel(Looper looper) {
        super(looper);
        this.mAndroidAutoAppManager = null;
        this.mIsNativeNaviActive = false;
        this.mIsPreNativeNaviActive = false;
        this.mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.model.appstate.AppStateModel.1
            public void onServiceConnected(IPlatformService service) {
                LogUtil.debug(AppStateModel.TAG, "onServiceConnected.");
                if (AppStateModel.this.mAndroidAutoAppManager != null) {
                    try {
                        AppStateModel.this.mAndroidAutoAppManager.registerNavigationDataListener(AppStateModel.this.mNavigationDataListener);
                        return;
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                LogUtil.debug(AppStateModel.TAG, "mAndroidAutoAppManager is null.");
            }

            public void onServiceDisconnected(IPlatformService service) {
                LogUtil.debug(AppStateModel.TAG, "onServiceDisconnected.");
            }
        };
        this.mNavigationDataListener = new INavigationDataListener() { // from class: com.ts.androidauto.app.model.appstate.AppStateModel.3
            public void onNavigationStatusChanged(int status) {
                AppStateModel.this.convertNavigationStatus(status);
            }
        };
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void init(Context context) throws RemoteException {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        this.androidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        registerClusterCommManagerCallBack();
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void uninit() throws RemoteException {
        LogUtil.info(TAG, "uninit");
        Context context = this.mContext;
        if (context != null && this.mClusterCommCallback != null) {
            ClusterCommManager.getInstance(context).unregisterCallback(16, this.mClusterCommCallback);
            this.mClusterCommCallback = null;
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                iAndroidAutoAppManager.unregisterNavigationDataListener(this.mNavigationDataListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            this.mAndroidAutoAppManager.unregisterConnectionListener(this.mServiceConnectionListener);
            this.mAndroidAutoAppManager = null;
        }
    }

    private void registerClusterCommManagerCallBack() throws RemoteException {
        if (this.mContext != null) {
            this.mClusterCommCallback = new IClusterCommCallback.Stub() { // from class: com.ts.androidauto.app.model.appstate.AppStateModel.2
                @Override // com.ts.lib.cluster.common.IClusterCommCallback
                public void onReceiveCommand(Bundle bundle) {
                    int dataType = bundle.getInt(Constants.Key.DATA_TYPE, -1);
                    int msgType = bundle.getInt(AppStateModel.KEY_MSG_TYPE, -1);
                    int subType = bundle.getInt(AppStateModel.KEY_SUB_TYPE, -1);
                    LogUtil.info(AppStateModel.TAG, "onReceiveCommand---- dataType == " + dataType + " msgType == " + msgType + " subType == " + subType);
                    if (35 == dataType && 3 == msgType && 2 == subType) {
                        int mapType = bundle.getInt(AppStateModel.KEY_BUNDLE_MAP_TYPE, -1);
                        int naviState = bundle.getInt(AppStateModel.KEY_BUNDLE_NAVI_STATE, -1);
                        LogUtil.info(AppStateModel.TAG, "onReceiveCommand---- mapType == " + mapType + " naviState == " + naviState);
                        if (1 == mapType) {
                            if (naviState == 1) {
                                AppStateModel.this.mIsNativeNaviActive = true;
                            } else if (naviState == 2) {
                                AppStateModel.this.mIsNativeNaviActive = false;
                            }
                            AppStateModel.this.sendNativeNaviStatus();
                            return;
                        }
                        if (naviState == 1) {
                            AppStateModel.this.mIsNativeNaviActive = false;
                            AppStateModel.this.sendNativeNaviStatus();
                        }
                    }
                }
            };
            ClusterCommManager.getInstance(this.mContext).registerCallback(16, this.mClusterCommCallback);
            StringBuilder sb = new StringBuilder();
            sb.append("registerClusterCommManager callback ");
            sb.append(this.mClusterCommCallback != null);
            LogUtil.info(TAG, sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean convertNavigationStatus(int naviState) {
        LogUtil.info(TAG, "convertNavigationStatus: " + naviState);
        if (naviState == 1 || naviState == 3) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNativeNaviStatus() {
        boolean z = this.mIsPreNativeNaviActive;
        boolean z2 = this.mIsNativeNaviActive;
        if (z != z2 && z2) {
            LogUtil.info(TAG, "sendNativeNaviStatus: " + this.mIsNativeNaviActive);
            post(new Runnable() { // from class: com.ts.androidauto.app.model.appstate.-$$Lambda$AppStateModel$zBh_RjKHvSxIWZRCKUxnPIqghc8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sendNativeNaviStatus$0$AppStateModel();
                }
            });
        }
        this.mIsPreNativeNaviActive = this.mIsNativeNaviActive;
    }

    public /* synthetic */ void lambda$sendNativeNaviStatus$0$AppStateModel() {
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.androidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.sendNativeNaviStatus(this.mIsNativeNaviActive);
        }
    }
}
