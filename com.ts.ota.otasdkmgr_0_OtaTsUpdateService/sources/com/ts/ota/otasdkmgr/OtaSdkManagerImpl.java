package com.ts.ota.otasdkmgr;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.IOtaSdkManager;
import com.ts.ota.otasdkmgr.ITestCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
class OtaSdkManagerImpl extends OtaSdkManager implements IBinder.DeathRecipient {
    private static final int MSG_RETRY = 101;
    private static final int MSG_RETRY_DELAY_TIME = 1000;
    private static final String TAG = "dota.OtaSdkManagerImpl";
    private static final int TBOX_COMMAND_FAILED = 1;
    private static final int TBOX_COMMAND_SUCCESS = 0;
    private static final boolean TBOX_DEFAULT_RESULT = false;
    private static final String TBOX_DEFAULT_VALUE = "";
    private static final int TBOX_ERROR_CODE = -1;
    public static final String TEST = "test.byteArray";
    private IAbsBYDTBoxListener mAppActivateListener;
    private Context mContext;
    private HandlerThread mHandlerThread;
    private RetryHandler mRetryHandler;
    private IUpdateListener mUpdateListener;
    private IOtaSdkManager mService = null;
    private List<ITestTboxCallBack> mTestTboxCallBack = new ArrayList();
    private List<IAbsMessageCallback> mCloudServiceCallback = new ArrayList();
    private List<ITboxCallBack> mITboxCallBack = new ArrayList();
    private List<IAbsBYDTBoxListener> mITboxListener = new ArrayList();
    private List<IAbsBYDAutoOtaListener> mIOtaListener = new ArrayList();
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private ServiceConnection connection = new ServiceConnection() { // from class: com.ts.ota.otasdkmgr.OtaSdkManagerImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder binder) {
            try {
                Log.e(OtaSdkManagerImpl.TAG, "onServiceConnected: service = " + binder + ", descrip:" + binder.getInterfaceDescriptor());
                OtaSdkManagerImpl.this.mService = IOtaSdkManager.Stub.asInterface(binder);
                OtaSdkManagerImpl.this.mService.registerListener(OtaSdkManagerImpl.this.mAbsBydAutoOtaListener);
                OtaSdkManagerImpl.this.mService.registerTestListener(OtaSdkManagerImpl.this.mTestCallback);
                Log.e(OtaSdkManagerImpl.TAG, "onServiceConnected: mService = " + OtaSdkManagerImpl.this.mService);
                String packageName = OtaSdkManagerImpl.this.mContext.getPackageName();
                Log.w(OtaSdkManagerImpl.TAG, "packageName : " + packageName + " mUpdateListener : " + OtaSdkManagerImpl.this.mUpdateListener);
                if (OtaSdkManagerImpl.this.mUpdateListener != null) {
                    OtaSdkManagerImpl.this.mService.registerCallback(OtaSdkManagerImpl.this.mUpdateListener);
                }
                synchronized (OtaSdkManagerImpl.this.mCloudServiceCallback) {
                    if (!OtaSdkManagerImpl.this.mCloudServiceCallback.isEmpty()) {
                        for (IAbsMessageCallback absMsgCallback : OtaSdkManagerImpl.this.mCloudServiceCallback) {
                            Log.w(OtaSdkManagerImpl.TAG, "onServiceConnected: IAbsMessageCallback->" + absMsgCallback);
                            OtaSdkManagerImpl.this.mService.setCloudServiceListener(absMsgCallback);
                        }
                    }
                }
                synchronized (OtaSdkManagerImpl.this.mITboxListener) {
                    if (!OtaSdkManagerImpl.this.mITboxListener.isEmpty()) {
                        for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                            Log.w(OtaSdkManagerImpl.TAG, "onServiceConnected: IAbsBYDTBoxListener->" + listener);
                            OtaSdkManagerImpl.this.mService.registerTboxListener(listener);
                        }
                    }
                }
            } catch (Exception ex) {
                Log.e(OtaSdkManagerImpl.TAG, ex.getMessage(), ex);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Log.e(OtaSdkManagerImpl.TAG, "onServiceDisconnected:  " + name);
            OtaSdkManagerImpl.this.mService = null;
        }
    };
    IAbsBYDAutoOtaListener.Stub mAbsBydAutoOtaListener = new IAbsBYDAutoOtaListener.Stub() { // from class: com.ts.ota.otasdkmgr.OtaSdkManagerImpl.2
        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onTurnOffState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onTurnOffState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onTurnOffState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onTurnOffState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onTurnOffState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onSmartPowerState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onSmartPowerState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onSmartPowerState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onSmartPowerState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onSmartPowerState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onDcState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onDcState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onDcState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onDcState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onDcState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onIg3RelayState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onIg3RelayState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onIg3RelayState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onIg3RelayState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onIg3RelayState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onIg1RelayState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onIg1RelayState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onIg1RelayState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onIg1RelayState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onIg1RelayState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onIg4RelayState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onIg4RelayState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onIg4RelayState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onIg4RelayState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onIg4RelayState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onBackupAreaDownloadState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onBackupAreaDownloadState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onBackupAreaDownloadState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onBackupAreaDownloadState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onBackupAreaDownloadState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void on7dfExistState(boolean exist) {
            Log.w(OtaSdkManagerImpl.TAG, "on7dfExistState: state = " + exist);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.on7dfExistState(exist);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "on7dfExistState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "on7dfExistState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onBcmOtaState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onBcmOtaState: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onBcmOtaState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onBcmOtaState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onBcmOtaState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onLFDoorLockStatus(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onLFDoorLockStatus: state = " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onLFDoorLockStatus(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onLFDoorLockStatus: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onLFDoorLockStatus: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onProhibitPowerOnState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, " onProhibitPowerOnState.in state : " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onProhibitPowerOnState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onProhibitPowerOnState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onProhibitPowerOnState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onStartKeyState(int state) {
            Log.w(OtaSdkManagerImpl.TAG, " onStartKeyState.in state : " + state);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onStartKeyState(state);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onStartKeyState: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onStartKeyState: mIOtaListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onNfcOperationCommand(int cmd) {
            Log.w(OtaSdkManagerImpl.TAG, "onNfcOperationCommand: cmd = " + cmd);
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onNfcOperationCommand(cmd);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onNfcOperationCommand: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onSocValueChanged(int value) {
            Log.w(OtaSdkManagerImpl.TAG, "onSocValueChanged: value = " + value);
            if (OtaSdkManagerImpl.this.mIOtaListener != null) {
                for (IAbsBYDAutoOtaListener listener : OtaSdkManagerImpl.this.mIOtaListener) {
                    try {
                        listener.onSocValueChanged(value);
                    } catch (RemoteException e) {
                        Log.e(OtaSdkManagerImpl.TAG, "onSocValueChanged: failed");
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onSocValueChanged: mIOtaListener is null");
        }
    };
    IAbsBYDTBoxListener.Stub mBydTboxListener = new IAbsBYDTBoxListener.Stub() { // from class: com.ts.ota.otasdkmgr.OtaSdkManagerImpl.3
        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onKeyListResponse(int totalNo, List<DigitalKeyBeanSdk> resp) {
            Log.w(OtaSdkManagerImpl.TAG, "onKeyListResponse");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onKeyListResponse(totalNo, resp);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onKeyListResponse: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteNfcKeyResult(int op, int result) {
            Log.w(OtaSdkManagerImpl.TAG, "onDeleteNfcKeyResult");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onDeleteNfcKeyResult(op, result);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onDeleteNfcKeyResult: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onAppActivateResult(int result, int appTag) {
            Log.w(OtaSdkManagerImpl.TAG, "onAppActivateResult");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onAppActivateResult(result, appTag);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onAppActivateResult: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteAllNfcKeyResult(int type, int result) {
            Log.w(OtaSdkManagerImpl.TAG, "onDeleteAllNfcKeyResult");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onDeleteAllNfcKeyResult(type, result);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onDeleteAllNfcKeyResult: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteNfcKeyByKeyNoResult(String keyNo, int result) {
            Log.w(OtaSdkManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onDeleteNfcKeyByKeyNoResult(keyNo, result);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteOwnIphoneKeyResult(String keyNo, int result) {
            Log.w(OtaSdkManagerImpl.TAG, "onDeleteOwnIphoneKeyResult");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onDeleteOwnIphoneKeyResult(keyNo, result);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onDeleteOwnIphoneKeyResult: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onKeyListPageRefresh() {
            Log.w(OtaSdkManagerImpl.TAG, "onKeyListPageRefresh");
            if (OtaSdkManagerImpl.this.mITboxListener != null) {
                for (IAbsBYDTBoxListener listener : OtaSdkManagerImpl.this.mITboxListener) {
                    try {
                        listener.onKeyListPageRefresh();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
                return;
            }
            Log.e(OtaSdkManagerImpl.TAG, "onKeyListPageRefresh: mITboxListener is null");
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onMobileNetworkStateChanged(int state) {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onNfcOperationCommand(int cmd) {
        }
    };
    ITestCallback.Stub mTestCallback = new ITestCallback.Stub() { // from class: com.ts.ota.otasdkmgr.OtaSdkManagerImpl.4
        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onNetworkOperatorList(TestInfo code) throws RemoteException {
            Log.w(OtaSdkManagerImpl.TAG, "onNetworkOperatorList: code : " + code);
            if (OtaSdkManagerImpl.this.mTestTboxCallBack == null) {
                Log.e(OtaSdkManagerImpl.TAG, "onNetworkOperatorList: mTestTboxCallBack is null");
                return;
            }
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onNetworkOperatorList(code.getData());
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onTboxRestartChanged(int state) throws RemoteException {
            Log.w(OtaSdkManagerImpl.TAG, "onTboxRestartChanged: state : " + state);
            if (OtaSdkManagerImpl.this.mTestTboxCallBack == null) {
                Log.e(OtaSdkManagerImpl.TAG, "onTboxRestartChanged: mTestTboxCallBack is null");
                return;
            }
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onTboxRestartChanged(state);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onUpdateTrafficData() throws RemoteException {
            Log.w(OtaSdkManagerImpl.TAG, "onUpdateTrafficData: ");
            if (OtaSdkManagerImpl.this.mTestTboxCallBack == null) {
                Log.e(OtaSdkManagerImpl.TAG, "onUpdateTrafficData: mTestTboxCallBack is null");
                return;
            }
            for (int i = 0; i < OtaSdkManagerImpl.this.mITboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mITboxCallBack.get(i) != null) {
                    ((ITboxCallBack) OtaSdkManagerImpl.this.mITboxCallBack.get(i)).onUpdateTrafficData();
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onCloudServiceConnectStateChanged(int state) throws RemoteException {
            Log.w(OtaSdkManagerImpl.TAG, "onCloudServiceConnectStateChanged: state " + state);
            if (OtaSdkManagerImpl.this.mTestTboxCallBack == null) {
                Log.e(OtaSdkManagerImpl.TAG, "onCloudServiceConnectStateChanged: mTestTboxCallBack is null");
                return;
            }
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onCloudServiceConnectStateChanged(state);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onTboxCmd2Ivi(int cmd) throws RemoteException {
            Log.w(OtaSdkManagerImpl.TAG, "onTboxCmd2Ivi: cmd " + cmd);
            for (int i = 0; i < OtaSdkManagerImpl.this.mITboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mITboxCallBack.get(i) != null) {
                    ((ITboxCallBack) OtaSdkManagerImpl.this.mITboxCallBack.get(i)).onTboxCmd2Ivi(cmd);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onNetworkOperatorModeChange(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onNetworkOperatorModeChange state:" + state);
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onNetworkOperatorModeChange(state);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onOperatorInfoChange(List<NetworkOperatorAidl> list) {
            Log.w(OtaSdkManagerImpl.TAG, "onOperatorInfoChange list:" + list);
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onOperatorInfoChange((ArrayList) list);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onManuallyStationedNetworkChange(String data, int curNetType, int state, String lastData, int lastNetType) {
            Log.w(OtaSdkManagerImpl.TAG, "onManuallyStationedNetworkChange data:" + data + " curNetType:" + curNetType + " state:" + state + " lastData:" + lastData + " lastNetType:" + lastNetType);
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onManuallyStationedNetworkChange(data, curNetType, state, lastData, lastNetType);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onAutomaticNetworkChange(int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onAutomaticNetworkChange state:" + state);
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onAutomaticNetworkChange(state);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onSimCardStateChange(int status) {
            Log.w(OtaSdkManagerImpl.TAG, "onSimCardStateChange status:" + status);
            for (int i = 0; i < OtaSdkManagerImpl.this.mTestTboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mTestTboxCallBack.get(i) != null) {
                    ((ITestTboxCallBack) OtaSdkManagerImpl.this.mTestTboxCallBack.get(i)).onSimCardStateChange(status);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onSyncApnParamResult(int resultCode) {
            Log.w(OtaSdkManagerImpl.TAG, "onSyncApnParamResult resultCode : " + resultCode);
            for (int i = 0; i < OtaSdkManagerImpl.this.mITboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mITboxCallBack.get(i) != null) {
                    ((ITboxCallBack) OtaSdkManagerImpl.this.mITboxCallBack.get(i)).onSyncApnParamResult(resultCode);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onGetApnParamFromTbox(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) {
            Log.w(OtaSdkManagerImpl.TAG, "onGetApnParamFromTBox");
            for (int i = 0; i < OtaSdkManagerImpl.this.mITboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mITboxCallBack.get(i) != null) {
                    ((ITboxCallBack) OtaSdkManagerImpl.this.mITboxCallBack.get(i)).onGetApnParamFromTbox(apn, username, pwd, apnType, apnProtocol, proxy, port);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onGetTrafficFromTbox(int apnType, String iccid, String time, long dayUpTraffic, long dayDownTraffic, long monthUpTraffic, long monthDownTraffic) {
            Log.w(OtaSdkManagerImpl.TAG, "onGetTrafficFromTbox");
            for (int i = 0; i < OtaSdkManagerImpl.this.mITboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mITboxCallBack.get(i) != null) {
                    ((ITboxCallBack) OtaSdkManagerImpl.this.mITboxCallBack.get(i)).onGetTrafficFromTbox(apnType, iccid, time, dayUpTraffic, dayDownTraffic, monthUpTraffic, monthDownTraffic);
                }
            }
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onGetTboxNetworkState(int apn, int state) {
            Log.w(OtaSdkManagerImpl.TAG, "onGetTboxNetworkState");
            for (int i = 0; i < OtaSdkManagerImpl.this.mITboxCallBack.size(); i++) {
                if (OtaSdkManagerImpl.this.mITboxCallBack.get(i) != null) {
                    ((ITboxCallBack) OtaSdkManagerImpl.this.mITboxCallBack.get(i)).onGetTboxNetworkState(apn, state);
                }
            }
        }
    };

    OtaSdkManagerImpl(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("OtaSdkManagerImpl context= ");
        sb.append(context == null ? "null" : context.getPackageName());
        Log.e(TAG, sb.toString());
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mRetryHandler = new RetryHandler(this.mHandlerThread.getLooper());
        tryConnectService();
    }

    class RetryHandler extends Handler {
        public RetryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == OtaSdkManagerImpl.MSG_RETRY) {
                OtaSdkManagerImpl.this.tryConnectService();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryConnectService() {
        this.mRetryHandler.removeMessages(MSG_RETRY);
        Intent serviceIntent = new Intent("android.Otasdk.OtaTsUpdateService");
        serviceIntent.setPackage("com.ts.ota.otasdkmgr");
        Log.e(TAG, "tryConnectService: intent = " + serviceIntent);
        boolean isSuccess = this.mContext.bindService(serviceIntent, 1, this.mExecutorService, this.connection);
        Log.e(TAG, "tryConnectService: intent = " + serviceIntent + ",connection = " + this.connection + " is success = " + isSuccess);
        if (!isSuccess) {
            this.mRetryHandler.sendEmptyMessageDelayed(MSG_RETRY, 1000L);
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        Log.e(TAG, "binderDied: tryConnectService!!!");
        try {
            if (this.mService != null && this.mService.asBinder() != null) {
                this.mService.asBinder().unlinkToDeath(this, 0);
            }
        } catch (Exception exception) {
            Log.e(TAG, "unlinkToDeath failed, msg:" + exception.getMessage());
        }
        this.mService = null;
        tryConnectService();
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean ready() {
        boolean status = true;
        if (this.mService == null) {
            status = false;
        }
        Log.e(TAG, "ready()");
        return status;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getTestString() throws RemoteException {
        String version = null;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                version = iOtaSdkManager.getTestString();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getTestString", ex);
            }
        } else {
            Log.e(TAG, "getTestString.null == mService");
        }
        Log.e(TAG, "getTestString=" + version);
        return version;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getDevicetype() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getDevicetype.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getDevicetype.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getDevicetype();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getDevicetype", ex);
            }
        }
        Log.w(TAG, " getDevicetype.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getSetPermission() throws RemoteException {
        String rstate = "not impl";
        Log.w(TAG, " getSetPermission.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getSetPermission.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getSetPermission();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getSetPermission", ex);
            }
        }
        Log.w(TAG, " getSetPermission.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getGetPermission() throws RemoteException {
        String rstate = "not impl";
        Log.w(TAG, " getGetPermission.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getGetPermission.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getGetPermission();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getGetPermission", ex);
            }
        }
        Log.w(TAG, " getGetPermission.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getOTAInfoACK() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getOTAInfoACK.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getOTAInfoACK.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getOTAInfoACK();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getOTAInfoACK", ex);
            }
        }
        Log.w(TAG, " getOTAInfoACK.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int notifyTargetID(byte[] id) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " notifyTargetID.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " notifyTargetID.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.notifyTargetID(id);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. notifyTargetID", ex);
            }
        }
        Log.w(TAG, " notifyTargetID.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int notifyTargetID2(byte[] channelAndId) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " notifyTargetID2.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " notifyTargetID2.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.notifyTargetID2(channelAndId);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. notifyTargetID2", ex);
            }
        }
        Log.w(TAG, " notifyTargetID2.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendOTAData(byte[] data) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendOTAData.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendOTAData.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendOTAData(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendOTAData", ex);
            }
        }
        Log.w(TAG, " sendOTAData.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendOTAData2(byte[] channelAndData) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendOTAData2.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendOTAData2.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendOTAData2(channelAndData);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendOTAData2", ex);
            }
        }
        Log.w(TAG, " sendOTAData2.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int StartOTA() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " StartOTA.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " StartOTA.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.StartOTA();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. StartOTA", ex);
            }
        }
        Log.w(TAG, " StartOTA.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int FinishOTA() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " FinishOTA.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " FinishOTA.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.FinishOTA();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. FinishOTA", ex);
            }
        }
        Log.w(TAG, " FinishOTA.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendOTAServiceData(byte[] data) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendOTAServiceData.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendOTAServiceData.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendOTAServiceData(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendOTAServiceData", ex);
            }
        }
        Log.w(TAG, " sendOTAServiceData.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendECUIds(int purpose, byte[] ids) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendECUIds.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendECUIds.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendECUIds(purpose, ids);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendECUIds", ex);
            }
        }
        Log.w(TAG, " sendECUIds.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendOTACtlCMD(byte[] controlCmd) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendOTACtlCMD.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendOTACtlCMD.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendOTACtlCMD(controlCmd);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendOTACtlCMD", ex);
            }
        }
        Log.w(TAG, " sendOTACtlCMD.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getECUVersion() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getECUVersion.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getECUVersion.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getECUVersion();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getECUVersion", ex);
            }
        }
        Log.w(TAG, " getECUVersion.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getECUSoftcode() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getECUSoftcode.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getECUSoftcode.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getECUSoftcode();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getECUSoftcode", ex);
            }
        }
        Log.w(TAG, " getECUSoftcode.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getECUVersionAndSoftcode() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getECUVersionAndSoftcode.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getECUVersionAndSoftcode.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getECUVersionAndSoftcode();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getECUVersionAndSoftcode", ex);
            }
        }
        Log.w(TAG, " getECUVersionAndSoftcode.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int queryECUVersionAndSoftcode(byte[] ids) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " queryECUVersionAndSoftcode.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " queryECUVersionAndSoftcode.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.queryECUVersionAndSoftcode(ids);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. queryECUVersionAndSoftcode", ex);
            }
        }
        Log.w(TAG, " queryECUVersionAndSoftcode.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getMCUVersionAndSoftcode() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getMCUVersionAndSoftcode.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getMCUVersionAndSoftcode.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getMCUVersionAndSoftcode();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getMCUVersionAndSoftcode", ex);
            }
        }
        Log.w(TAG, " getMCUVersionAndSoftcode.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int queryMCUVersionAndSoftcode(byte[] ids) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " queryMCUVersionAndSoftcode.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " queryMCUVersionAndSoftcode.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.queryMCUVersionAndSoftcode(ids);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. queryMCUVersionAndSoftcode", ex);
            }
        }
        Log.w(TAG, " queryMCUVersionAndSoftcode.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getPoweroffInform() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getPoweroffInform.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getPoweroffInform.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getPoweroffInform();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getPoweroffInform", ex);
            }
        }
        Log.w(TAG, " getPoweroffInform.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getOTAState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getOTAState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getOTAState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getOTAState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getOTAState", ex);
            }
        }
        Log.w(TAG, " getOTAState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getOTATimecountState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getOTATimecountState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getOTATimecountState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getOTATimecountState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getOTATimecountState", ex);
            }
        }
        Log.w(TAG, " getOTATimecountState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendOTATimecountCtlCMD(int install, int user, int hour, int minute) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendOTATimecountCtlCMD.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendOTATimecountCtlCMD.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendOTATimecountCtlCMD(install, user, hour, minute);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendOTATimecountCtlCMD", ex);
            }
        }
        Log.w(TAG, " sendOTATimecountCtlCMD.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setScreenState(int state) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setScreenState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setScreenState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setScreenState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setScreenState", ex);
            }
        }
        Log.w(TAG, " setScreenState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getDischargeMainContactorState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getDischargeMainContactorState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getDischargeMainContactorState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getDischargeMainContactorState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getDischargeMainContactorState", ex);
            }
        }
        Log.w(TAG, " getDischargeMainContactorState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendECUIds2(int purpose, byte[] ids) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendECUIds2.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendECUIds2.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendECUIds2(purpose, ids);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendECUIds2", ex);
            }
        }
        Log.w(TAG, " sendECUIds2.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getPowerOnForbidState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getPowerOnForbidState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getPowerOnForbidState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getPowerOnForbidState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getPowerOnForbidState", ex);
            }
        }
        Log.w(TAG, " getPowerOnForbidState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getLFDoorLockState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getLFDoorLockState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getLFDoorLockState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getLFDoorLockState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getLFDoorLockState", ex);
            }
        }
        Log.w(TAG, " getLFDoorLockState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setPowerOnForbidState(int state) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setPowerOnForbidState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setPowerOnForbidState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setPowerOnForbidState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setPowerOnForbidState", ex);
            }
        }
        Log.w(TAG, " setPowerOnForbidState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getCanInfo() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getCanInfo.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getCanInfo.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getCanInfo();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getCanInfo", ex);
            }
        }
        Log.w(TAG, " getCanInfo.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setCanInfoSwitchState(int state) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setCanInfoSwitchState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setCanInfoSwitchState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setCanInfoSwitchState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setCanInfoSwitchState", ex);
            }
        }
        Log.w(TAG, " setCanInfoSwitchState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public double getBatteryPowerVoltage() throws RemoteException {
        double rstate = -1.0d;
        Log.w(TAG, " getBatteryPowerVoltage.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getBatteryPowerVoltage.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getBatteryPowerVoltage();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getBatteryPowerVoltage", ex);
            }
        }
        Log.w(TAG, " getBatteryPowerVoltage.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public byte[] getFaultCodeInfo() throws RemoteException {
        byte[] rstate = null;
        Log.w(TAG, " getFaultCodeInfo.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getFaultCodeInfo.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getFaultCodeInfo();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getFaultCodeInfo", ex);
            }
        }
        Log.w(TAG, " getFaultCodeInfo.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getLocalDiagStatus() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getLocalDiagStatus.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getLocalDiagStatus.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getLocalDiagStatus();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getLocalDiagStatus", ex);
            }
        }
        Log.w(TAG, " getLocalDiagStatus.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setMcuMessage(int message) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setMcuMessage.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setMcuMessage.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setMcuMessage(message);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setMcuMessage", ex);
            }
        }
        Log.w(TAG, " setMcuMessage.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int syncMcuState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " syncMcuState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " syncMcuState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.syncMcuState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. syncMcuState", ex);
            }
        }
        Log.w(TAG, " syncMcuState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int sendMonitorTable(byte[] id) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " sendMonitorTable.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " sendMonitorTable.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.sendMonitorTable(id);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendMonitorTable", ex);
            }
        }
        Log.w(TAG, " sendMonitorTable.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setAllStatus() {
        Log.w(TAG, " setAllStatus.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setAllStatus.null == mService");
        } else {
            try {
                iOtaSdkManager.setAllStatus();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAllStatus", ex);
            }
        }
        Log.w(TAG, " setAllStatus.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getType() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getType.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getType.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getType();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getType", ex);
            }
        }
        Log.w(TAG, " getType.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int[] getFeatureList() throws RemoteException {
        int[] rstate = null;
        Log.w(TAG, " getFeatureList.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getFeatureList.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getFeatureList();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getFeatureList", ex);
            }
        }
        Log.w(TAG, " getFeatureList.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getVehicleVersion() throws RemoteException {
        String rstate = "not impl";
        Log.w(TAG, " getVehicleVersion.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getVehicleVersion.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getVehicleVersion();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getVehicleVersion", ex);
            }
        }
        Log.w(TAG, " getVehicleVersion.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean getEcuInfo(List<Info> infos, IAbsMessageCallback callback) throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " getEcuInfo.in list:" + infos);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getEcuInfo(infos, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getEcuInfo", ex);
            }
        } else {
            Log.e(TAG, " getEcuInfo.null == mService");
        }
        Log.w(TAG, " getEcuInfo.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean getAQSlot(IAbsMessageCallback callback) throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " getAQSlot.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getAQSlot.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getAQSlot(callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getAQSlot", ex);
            }
        }
        Log.w(TAG, " getAQSlot.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setAQSlot(int slot, IAbsMessageCallback callback) throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " setAQSlot.in slot->" + slot);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setAQSlot(slot, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAQSlot", ex);
            }
        } else {
            Log.e(TAG, " setAQSlot.null == mService");
        }
        Log.w(TAG, " setAQSlot.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void QNXreboot() {
        boolean rstate = false;
        Log.w(TAG, " QNXreboot.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " QNXreboot.null == mService");
        } else {
            try {
                iOtaSdkManager.QNXreboot();
                rstate = true;
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. QNXreboot", ex);
            }
        }
        Log.w(TAG, " QNXreboot.out= " + rstate);
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    @Deprecated
    public int getVehicleType() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getVehicleType.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getVehicleType.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getVehicleType();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getVehicleType", ex);
            }
        }
        Log.w(TAG, " getVehicleType.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getTurnOffState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getTurnOffState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getTurnOffState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getTurnOffState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getTurnOffState", ex);
            }
        }
        Log.w(TAG, " getTurnOffState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getIgnitionState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getIgnitionState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getIgnitionState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getIgnitionState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getIgnitionState", ex);
            }
        }
        Log.w(TAG, " getIgnitionState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getBurglarState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBurglarState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getBurglarState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getBurglarState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getBurglarState", ex);
            }
        }
        Log.w(TAG, " getBurglarState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getBatteryTemp(int batteryType) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBurglarState.in batteryType->" + batteryType);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getBatteryTemp(batteryType);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getBatteryTemp", ex);
            }
        } else {
            Log.e(TAG, " getBurglarState.null == mService");
        }
        Log.w(TAG, " getBurglarState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getEpbState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getEpbState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getEpbState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getEpbState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getEpbState", ex);
            }
        }
        Log.w(TAG, " getEpbState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean getSmartPowerUsable() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " getSmartPowerUsable.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getSmartPowerUsable.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getSmartPowerUsable();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getSmartPowerUsable", ex);
            }
        }
        Log.w(TAG, " getSmartPowerUsable.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getSmartPowerState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getSmartPowerState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getSmartPowerState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getSmartPowerState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getSmartPowerState", ex);
            }
        }
        Log.w(TAG, " getSmartPowerState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getSmartPowerException() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getSmartPowerException.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getSmartPowerException.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getSmartPowerException();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getSmartPowerException", ex);
            }
        }
        Log.w(TAG, " getSmartPowerException.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getDcState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getDcState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getDcState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getDcState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getDcState", ex);
            }
        }
        Log.w(TAG, " getDcState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getBatteryType() {
        return 0;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getBatteryVoltage(int batteryType) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBatteryVoltage.in batteryType->" + batteryType);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getBatteryVoltage(batteryType);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getBatteryVoltage", ex);
            }
        } else {
            Log.e(TAG, " getBatteryVoltage.null == mService");
        }
        Log.w(TAG, " getBatteryVoltage.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getChargeState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getChargeState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getChargeState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getChargeState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getChargeState", ex);
            }
        }
        Log.w(TAG, " getChargeState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getBatteryManagementDeviceState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBatteryManagementDeviceState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getBatteryManagementDeviceState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getBatteryManagementDeviceState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getBatteryManagementDeviceState", ex);
            }
        }
        Log.w(TAG, " getBatteryManagementDeviceState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getDischargeState(int type) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getDischargeState.in type->" + type);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getDischargeState(type);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getDischargeState", ex);
            }
        } else {
            Log.e(TAG, " getDischargeState.null == mService");
        }
        Log.w(TAG, " getDischargeState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getChargeAndDischargeSystemState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getChargeAndDischargeSystemState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getChargeAndDischargeSystemState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getChargeAndDischargeSystemState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getChargeAndDischargeSystemState", ex);
            }
        }
        Log.w(TAG, " getChargeAndDischargeSystemState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getSmartChargeState(int batteryType) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getSmartChargeState.in batteryType->" + batteryType);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getSmartChargeState(batteryType);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getSmartChargeState", ex);
            }
        } else {
            Log.e(TAG, " getSmartChargeState.null == mService");
        }
        Log.w(TAG, " getSmartChargeState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getIg3RelayState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getIg3RelayState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getIg3RelayState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getIg3RelayState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getIg3RelayState", ex);
            }
        }
        Log.w(TAG, " getIg3RelayState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getBcmOtaState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBcmOtaState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getBcmOtaState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getBcmOtaState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getBcmOtaState", ex);
            }
        }
        Log.w(TAG, " getBcmOtaState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean get7dfExistState() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " get7dfExistState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " get7dfExistState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.get7dfExistState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. get7dfExistState", ex);
            }
        }
        Log.w(TAG, " get7dfExistState.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setSmartPower(int state) {
        Log.w(TAG, " setSmartPower.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setSmartPower(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setSmartPower", ex);
            }
        } else {
            Log.e(TAG, " setSmartPower.null == mService");
        }
        Log.w(TAG, " setSmartPower.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setEclPower(int state) {
        Log.w(TAG, " setEclPower.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setEclPower(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setEclPower", ex);
            }
        } else {
            Log.e(TAG, " setEclPower.null == mService");
        }
        Log.w(TAG, " setEclPower.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setQuitSmartCharge(int state) {
        Log.w(TAG, " setQuitSmartCharge.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setQuitSmartCharge(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setQuitSmartCharge", ex);
            }
        } else {
            Log.e(TAG, " setQuitSmartCharge.null == mService");
        }
        Log.w(TAG, " setQuitSmartCharge.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setQuitChargeAndDischarge(int state) {
        Log.w(TAG, " setQuitChargeAndDischarge.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setQuitChargeAndDischarge(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setQuitChargeAndDischarge", ex);
            }
        } else {
            Log.e(TAG, " setQuitChargeAndDischarge.null == mService");
        }
        Log.w(TAG, " setQuitChargeAndDischarge.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setOtaModel(int state) {
        Log.w(TAG, " setOtaModel.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setOtaModel(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setOtaModel", ex);
            }
        } else {
            Log.e(TAG, " setOtaModel.null == mService");
        }
        Log.w(TAG, " setOtaModel.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setOtaPower(int state) {
        Log.w(TAG, " setOtaPower.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setOtaPower(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setOtaPower", ex);
            }
        } else {
            Log.e(TAG, " setOtaPower.null == mService");
        }
        Log.w(TAG, " setOtaPower.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setHighVoltageCmd(int command) {
        Log.w(TAG, " setHighVoltageCmd.in command->" + command);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setHighVoltageCmd(command);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setHighVoltageCmd", ex);
            }
        } else {
            Log.e(TAG, " setHighVoltageCmd.null == mService");
        }
        Log.w(TAG, " setHighVoltageCmd.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setBackupAreaDownload(int state) {
        Log.w(TAG, " setBackupAreaDownload.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setBackupAreaDownload(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setBackupAreaDownload", ex);
            }
        } else {
            Log.e(TAG, " setBackupAreaDownload.null == mService");
        }
        Log.w(TAG, " setBackupAreaDownload.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setBackLightState(int state) {
        Log.w(TAG, " setBackLightState.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.setBackLightState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setBackLightState", ex);
            }
        } else {
            Log.e(TAG, " setBackLightState.null == mService");
        }
        Log.w(TAG, " setBackLightState.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    @Deprecated
    public void startUpdate(int type, IUpdateListener listener) {
        Log.w(TAG, " startUpdate.in");
        Log.w(TAG, " startUpdate.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    @Deprecated
    public void stopUpdate() {
        Log.w(TAG, " stopUpdate.in");
        Log.w(TAG, " stopUpdate.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void registerCallback(IUpdateListener listener) {
        Log.w(TAG, " registerCallback.in listener = " + listener);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                this.mUpdateListener = listener;
                iOtaSdkManager.registerCallback(listener);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. registerCallback", ex);
            }
        } else {
            Log.e(TAG, " registerCallback.null == mService");
        }
        Log.w(TAG, " registerCallback.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void unregisterCallback(IUpdateListener listener) {
        Log.w(TAG, " unregisterCallback.in listener = " + listener);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.unregisterCallback(listener);
                this.mUpdateListener = null;
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. unregisterCallback", ex);
            }
        } else {
            Log.e(TAG, " unregisterCallback.null == mService");
        }
        Log.w(TAG, " unregisterCallback.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void startUpdate(String path) {
        Log.w(TAG, " startUpdate.in path->" + path);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.startUpdate(path);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. startUpdate", ex);
            }
        } else {
            Log.e(TAG, " startUpdate.null == mService");
        }
        Log.w(TAG, " startUpdate.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void startDownloadBackup(String path) {
        Log.w(TAG, " startDownloadBackup.in path->" + path);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.startDownloadBackup(path);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. startDownloadBackup", ex);
            }
        } else {
            Log.e(TAG, " startDownloadBackup.null == mService");
        }
        Log.w(TAG, " startDownloadBackup.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void startUpdateTbox(String path) {
        Log.w(TAG, " startUpdateTbox.in path->" + path);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.startUpdateTbox(path);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. startUpdateTbox", ex);
            }
        } else {
            Log.e(TAG, " startUpdateTbox.null == mService");
        }
        Log.w(TAG, " startUpdateTbox.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void registerListener(IAbsBYDAutoOtaListener listener) {
        Log.w(TAG, " registerListener.in listener = " + listener);
        List<IAbsBYDAutoOtaListener> list = this.mIOtaListener;
        if (list != null && !list.contains(listener)) {
            this.mIOtaListener.add(listener);
        }
        Log.w(TAG, " registerListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void registerAppActivateListener(IAbsBYDTBoxListener listener) {
        Log.w(TAG, " register IAbsBYDTBoxListener.in listener = " + listener);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                this.mAppActivateListener = listener;
                iOtaSdkManager.registerAppActivateListener(listener);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. registerListener", ex);
            }
        } else {
            Log.e(TAG, " registerListener.null == mService");
        }
        Log.w(TAG, " register IAbsBYDTBoxListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void unregisterListener(IAbsBYDAutoOtaListener listener) {
        Log.w(TAG, " unregisterListener.in listener = " + listener);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.unregisterListener(listener);
                if (this.mIOtaListener != null && this.mIOtaListener.contains(listener)) {
                    this.mIOtaListener.remove(listener);
                }
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. unregisterListener", ex);
            }
        } else {
            Log.e(TAG, " unregisterListener.null == mService");
        }
        Log.w(TAG, " unregisterListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void registerTboxListener(ITboxCallBack listener) {
        Log.w(TAG, " register IAbsBYDTBoxListener.in listener = " + listener);
        List<ITboxCallBack> list = this.mITboxCallBack;
        if (list != null && !list.contains(listener)) {
            this.mITboxCallBack.add(listener);
        }
        Log.w(TAG, " register IAbsBYDTBoxListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void unregisterTboxListener(ITboxCallBack listener) {
        Log.w(TAG, " unregister IAbsBYDAutoOtaListener.in listener = " + listener);
        List<ITboxCallBack> list = this.mITboxCallBack;
        if (list != null && list.contains(listener)) {
            this.mITboxCallBack.remove(listener);
        }
        Log.w(TAG, " unregister IAbsBYDAutoOtaListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void registerTboxListener(IAbsBYDTBoxListener listener) {
        Log.w(TAG, " registerTboxListener IAbsBYDTBoxListener:" + listener);
        List<IAbsBYDTBoxListener> list = this.mITboxListener;
        if (list != null && !list.contains(listener)) {
            this.mITboxListener.add(listener);
        }
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.registerTboxListener(listener);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. registerTboxListener", ex);
            }
        } else {
            Log.e(TAG, " registerTboxListener.null == mService");
        }
        Log.w(TAG, " registerTboxListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void unregisterTboxListener(IAbsBYDTBoxListener listener) {
        Log.w(TAG, " unregisterTboxListener IAbsBYDTBoxListener:" + listener);
        List<IAbsBYDTBoxListener> list = this.mITboxListener;
        if (list != null && list.contains(listener)) {
            this.mITboxListener.remove(listener);
        }
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.unregisterTboxListener(listener);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. unregisterTboxListener", ex);
            }
        } else {
            Log.e(TAG, " unregisterTboxListener.null == mService");
        }
        Log.w(TAG, " unregisterTboxListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getTBoxSerialNumber() throws RemoteException {
        Log.w(TAG, "getTBoxSerialNumber.in");
        String sn = null;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "getTBoxSerialNumber.null == mService");
        } else {
            try {
                sn = iOtaSdkManager.getTBoxSerialNumber();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getTBoxSerialNumber: ", ex);
            }
        }
        Log.w(TAG, "getTBoxSerialNumber.out");
        return sn;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getTBoxSoftVersion() throws RemoteException {
        Log.w(TAG, "getTBoxSoftVersion.in");
        String tboxVer = null;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "getTBoxSoftVersion.null == mService");
        } else {
            try {
                tboxVer = iOtaSdkManager.getTBoxSoftVersion();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getTBoxSoftVersion: ", ex);
            }
        }
        Log.w(TAG, "getTBoxSoftVersion.out");
        return tboxVer;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getTBoxHardwareVersion() throws RemoteException {
        Log.w(TAG, "getTBoxHardwareVersion.in");
        String tboxHwVer = null;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "getTBoxHardwareVersion.null == mService");
        } else {
            try {
                tboxHwVer = iOtaSdkManager.getTBoxHardwareVersion();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getTBoxHardwareVersion: ", ex);
            }
        }
        Log.w(TAG, "getTBoxHardwareVersion.out");
        return tboxHwVer;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean getTBoxUpgradeLog(String path) throws RemoteException {
        Log.w(TAG, "getTBoxUpgradeLog.in path->" + path);
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                result = iOtaSdkManager.getTBoxUpgradeLog(path);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getTBoxUpgradeLog: ", ex);
            }
        } else {
            Log.e(TAG, "getTBoxUpgradeLog.null == mService");
        }
        Log.w(TAG, "getTBoxUpgradeLog.out");
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean decodeAndCheckSign(Bundle bundle) throws RemoteException {
        Log.w(TAG, "decodeAndCheckSign.in bundle->" + bundle);
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                result = iOtaSdkManager.decodeAndCheckSign(bundle);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.decodeAndCheckSign: ", ex);
            }
        } else {
            Log.e(TAG, "decodeAndCheckSign.null == mService");
        }
        Log.w(TAG, "decodeAndCheckSign.out");
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public float getVehicleSpeed() throws RemoteException {
        Log.w(TAG, "getVehicleSpeed.in");
        float rstate = -1.0f;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "getVehicleSpeed.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getVehicleSpeed();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getVehicleSpeed: ", ex);
            }
        }
        Log.w(TAG, "getVehicleSpeed.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getSocValue() throws RemoteException {
        Log.w(TAG, "getSocValue.in");
        int rstate = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "getSocValue.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getSocValue();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException.getSocValue: ", ex);
            }
        }
        Log.w(TAG, "getSocValue.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setAppointmentTime(int day, int hours, int minutes, int type, int wakeup, IAbsMessageCallback callback) {
        Log.w(TAG, " setAppointmentTime.in-> day:" + day + " hours:" + hours + " minutes:" + minutes);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setAppointmentTime(day, hours, minutes, type, wakeup, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAppointmentTime", ex);
            }
        } else {
            Log.e(TAG, " setAppointmentTime.null == mService");
        }
        Log.w(TAG, " setAppointmentTime.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean sendCanNM(int source, int state, IAbsMessageCallback callback) {
        Log.w(TAG, " sendCanNM.in source->" + source + " state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.sendCanNM(source, state, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. sendCanNM", ex);
            }
        } else {
            Log.e(TAG, " sendCanNM.null == mService");
        }
        Log.w(TAG, " sendCanNM.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setUpdateFlag(int flag, IAbsMessageCallback callback) {
        Log.w(TAG, " setUpdateFlag.in flag->" + flag);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setUpdateFlag(flag, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setUpdateFlag", ex);
            }
        } else {
            Log.e(TAG, " setUpdateFlag.null == mService");
        }
        Log.w(TAG, " setUpdateFlag.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setQnxOtaState(int state, IAbsMessageCallback callback) {
        Log.w(TAG, " setQnxOtaState.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setQnxOtaState(state, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setQnxOtaState", ex);
            }
        } else {
            Log.e(TAG, " setQnxOtaState.null == mService");
        }
        Log.w(TAG, " setQnxOtaState.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setAndroidOtaState(int state, IAbsMessageCallback callback) {
        Log.w(TAG, " setAndroidOtaState.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setAndroidOtaState(state, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAndroidOtaState", ex);
            }
        } else {
            Log.e(TAG, " setAndroidOtaState.null == mService");
        }
        Log.w(TAG, " setAndroidOtaState.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setCloudServiceListener(IAbsMessageCallback callback) {
        synchronized (this.mCloudServiceCallback) {
            Log.w(TAG, " setCloudServiceListener.in");
            this.mCloudServiceCallback.add(callback);
            if (this.mService != null) {
                try {
                    this.mService.setCloudServiceListener(callback);
                } catch (RemoteException ex) {
                    Log.e(TAG, "RemoteException. setCloudServiceListener", ex);
                }
            } else {
                Log.e(TAG, " setCloudServiceListener.null == mService");
            }
            Log.w(TAG, " setCloudServiceListener.out");
        }
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void stopCloudServiceListener(IAbsMessageCallback callback) {
        Log.w(TAG, " stopCloudServiceListener.in");
        if (this.mService == null) {
            Log.e(TAG, " stopCloudServiceListener.null == mService");
        } else {
            try {
                if (this.mCloudServiceCallback.contains(callback)) {
                    this.mCloudServiceCallback.remove(callback);
                }
                this.mService.stopCloudServiceListener(callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. stopCloudServiceListener", ex);
            }
        }
        Log.w(TAG, " stopCloudServiceListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void notifyTboxOtaState(int state) {
        Log.w(TAG, " notifyTboxOtaState.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.notifyTboxOtaState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. notifyTboxOtaState", ex);
            }
        } else {
            Log.e(TAG, " notifyTboxOtaState.null == mService");
        }
        Log.w(TAG, " notifyTboxOtaState.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getCloudServiceVersion() throws RemoteException {
        Log.w(TAG, " getCloudServiceVersion.in");
        String cloudVer = null;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getCloudServiceVersion.null == mService");
        } else {
            try {
                cloudVer = iOtaSdkManager.getCloudServiceVersion();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getCloudServiceVersion", ex);
            }
        }
        Log.w(TAG, " getCloudServiceVersion.out");
        return cloudVer;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean switchCloudServiceEnv(int env) {
        Log.w(TAG, " switchCloudServiceEnv.in env->" + env);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                return iOtaSdkManager.switchCloudServiceEnv(env);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. switchCloudServiceEnv", ex);
            }
        } else {
            Log.e(TAG, " switchCloudServiceEnv.null == mService");
        }
        Log.w(TAG, " switchCloudServiceEnv.out");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getCloudServiceConnectState() throws RemoteException {
        Log.w(TAG, " getCloudServiceConnectState.in");
        int connState = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getCloudServiceConnectState.null == mService");
        } else {
            try {
                connState = iOtaSdkManager.getCloudServiceConnectState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getCloudServiceConnectState", ex);
            }
        }
        Log.w(TAG, " getCloudServiceConnectState.out");
        return connState;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getCloudServieEv() throws RemoteException {
        Log.w(TAG, " getCloudServieEv.in");
        int cloudEnv = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getCloudServieEv.null == mService");
        } else {
            try {
                cloudEnv = iOtaSdkManager.getCloudServieEv();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getCloudServieEv", ex);
            }
        }
        Log.w(TAG, " getCloudServieEv.out");
        return cloudEnv;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean executeCMD(Bundle cmd) throws RemoteException {
        Log.w(TAG, " executeCMD.in cmd->" + cmd);
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                result = iOtaSdkManager.executeCMD(cmd);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. executeCMD", ex);
            }
        } else {
            Log.e(TAG, " executeCMD.null == mService");
        }
        Log.w(TAG, " executeCMD.out");
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setMobileNetworkState(boolean state) {
        Log.w(TAG, " setMobileNetworkState.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setMobileNetworkState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setMobileNetworkState", ex);
            }
        } else {
            Log.e(TAG, " setMobileNetworkState.null == mService");
        }
        Log.w(TAG, " setMobileNetworkState.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setAppActivateInfo(byte supplierType, byte autoVinResult, byte supplierName, byte activateState, long activateTime, long licenseTime, byte activateCountry, byte activateRegion, String appPkgName) {
        Log.w(TAG, " setAppActivateInfo.in->supplierType:" + ((int) supplierType) + " autoVinResult:" + ((int) autoVinResult) + " supplierName:" + ((int) supplierName) + " activateState:" + ((int) activateState) + " activateTime" + activateTime + " licenseTime:" + licenseTime + " activateCountry:" + ((int) activateCountry) + " activateRegion:" + ((int) activateRegion) + " appPkgName:" + appPkgName);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setAppActivateInfo(supplierType, autoVinResult, supplierName, activateState, activateTime, licenseTime, activateCountry, activateRegion, appPkgName);
                return;
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAppActivateInfo", ex);
                return;
            }
        }
        Log.e(TAG, " setAppActivateInfo.null == mService");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setAppActivateInfo(int supplierType, int autoVinResult, int supplierName, int activateState, long activateTime, long licenseTime, int activateCountry, int activateRegion, String appPkgName) {
        String str;
        Log.w(TAG, " setAppActivateInfo.in->supplierType:" + supplierType + " autoVinResult:" + autoVinResult + " supplierName:" + supplierName + " activateState:" + activateState + " activateTime" + activateTime + " licenseTime:" + licenseTime + " activateCountry:" + activateCountry + " activateRegion:" + activateRegion + " appPkgName:" + appPkgName);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            str = TAG;
            try {
                iOtaSdkManager.setAppActivateInfoV2(supplierType, autoVinResult, supplierName, activateState, activateTime, licenseTime, activateCountry, activateRegion, appPkgName);
            } catch (RemoteException ex) {
                Log.e(str, "RemoteException. setAppActivateInfo", ex);
            }
        } else {
            str = TAG;
            Log.e(str, " setAppActivateInfo.null == mService");
        }
        Log.w(str, " setAppActivateInfo.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getLFDoorLockStatus() throws RemoteException {
        Log.w(TAG, " getLFDoorLockStatus.in");
        int state = 0;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getLFDoorLockStatus.null == mService");
        } else {
            try {
                state = iOtaSdkManager.getLFDoorLockStatus();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getLFDoorLockStatus", ex);
            }
        }
        Log.w(TAG, " getLFDoorLockStatus.out");
        return state;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getProhibitPowerOnState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getProhibitPowerOnState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getProhibitPowerOnState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getProhibitPowerOnState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getProhibitPowerOnState", ex);
            }
        }
        Log.w(TAG, " getProhibitPowerOnState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setMultimediaSelfUpdate(int state) throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " setMultimediaSelfUpdate.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setMultimediaSelfUpdate(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setMultimediaSelfUpdate", ex);
            }
        } else {
            Log.e(TAG, " setMultimediaSelfUpdate.null == mService");
        }
        Log.w(TAG, " setMultimediaSelfUpdate.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getNetworkOperatorList() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getNetworkOperatorList.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getNetworkOperatorList.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getNetworkOperatorList();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getNetworkOperatorList", ex);
            }
        }
        Log.w(TAG, " getNetworkOperatorList.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getCurNetworkOperator() throws RemoteException {
        String rstate = null;
        Log.w(TAG, " getCurNetworkOperator.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getCurNetworkOperator.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getCurNetworkOperator();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getCurNetworkOperator", ex);
            }
        }
        Log.w(TAG, " getCurNetworkOperator.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setNetworkOperator(String code) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setNetworkOperator.in code->" + code);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setNetworkOperator(code);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setNetworkOperator", ex);
            }
        } else {
            Log.e(TAG, " setNetworkOperator.null == mService");
        }
        Log.w(TAG, " setNetworkOperator.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setRestartTbox(int state) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setRestartTbox.in state->" + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setRestartTbox(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setRestartTbox", ex);
            }
        } else {
            Log.e(TAG, " setRestartTbox.null == mService");
        }
        Log.w(TAG, " setRestartTbox.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getTboxState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getTboxState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getTboxState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getTboxState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getTboxState", ex);
            }
        }
        Log.w(TAG, " getTboxState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void registerTestListener(ITestTboxCallBack callback) {
        Log.w(TAG, "registerTestListener.in");
        this.mTestTboxCallBack.add(callback);
        Log.w(TAG, "registerTestListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void unregisterTestListener(ITestTboxCallBack callback) {
        Log.w(TAG, "unregisterTestListener.in");
        List<ITestTboxCallBack> list = this.mTestTboxCallBack;
        if (list != null && list.contains(callback)) {
            this.mTestTboxCallBack.remove(callback);
        }
        Log.w(TAG, "unregisterTestListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean getMobileNetworkState() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " getMobileNetworkState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getMobileNetworkState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getMobileNetworkState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getMobileNetworkState", ex);
            }
        }
        Log.w(TAG, " getMobileNetworkState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void getDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) {
        Log.w(TAG, " getDiagnosticTroubleCode.in list:" + info);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.getDiagnosticTroubleCode(info, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getDiagnosticTroubleCode", ex);
            }
        } else {
            Log.e(TAG, " getDiagnosticTroubleCode.null == mService");
        }
        Log.w(TAG, " getDiagnosticTroubleCode.out = true");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void clearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) {
        Log.w(TAG, " clearDiagnosticTroubleCode.in list:" + info);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.clearDiagnosticTroubleCode(info, callback);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. clearDiagnosticTroubleCode", ex);
            }
        } else {
            Log.e(TAG, " clearDiagnosticTroubleCode.null == mService");
        }
        Log.w(TAG, " clearDiagnosticTroubleCode.out = true");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int notifyTboxTrafficResult(int result) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " notifyTboxTrafficResult.in result->" + result);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.notifyTboxTrafficResult(result);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. notifyTboxTrafficResult", ex);
            }
        } else {
            Log.e(TAG, " notifyTboxTrafficResult.null == mService");
        }
        Log.w(TAG, " notifyTboxTrafficResult.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setCloudServieEv(int ev) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setCloudServieEv.in ev->" + ev);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setCloudServieEv(ev);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setCloudServieEv", ex);
            }
        } else {
            Log.e(TAG, " setCloudServieEv.null == mService");
        }
        Log.w(TAG, " setCloudServieEv.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean isConnectTbox() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " isConnectTbox.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " isConnectTbox.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.isConnectTbox();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. isConnectTbox", ex);
            }
        }
        Log.w(TAG, " isConnectTbox.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, String keyId) {
        Log.w(TAG, " queryKeyListReq.in->carAccount:" + carAccount + " entityKeyTag:" + entityKeyTag + " keyType:" + keyType + " userId:" + userId + " keyId:" + keyId);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.queryKeyListReq(carAccount, entityKeyTag, keyType, userId, keyId);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. queryKeyListReq", ex);
            }
        } else {
            Log.e(TAG, " queryKeyListReq.null == mService");
        }
        Log.w(TAG, " queryKeyListReq.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, byte[] keyId) {
        Log.w(TAG, " queryKeyListReq.in->carAccount:" + carAccount + " entityKeyTag:" + entityKeyTag + " keyType:" + keyType + " userId:" + userId + " keyId:" + Arrays.toString(keyId));
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " queryKeyListReq.null == mService");
        } else {
            try {
                iOtaSdkManager.queryKeyListByKeyArray(carAccount, entityKeyTag, keyType, userId, keyId);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. queryKeyListReq", ex);
            }
        }
        Log.w(TAG, " queryKeyListReq.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void uploadNfcOpenInfo() {
        Log.w(TAG, " uploadNfcOpenInfo.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " uploadNfcOpenInfo.null == mService");
        } else {
            try {
                iOtaSdkManager.uploadNfcOpenInfo();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. uploadNfcOpenInfo", ex);
            }
        }
        Log.w(TAG, " uploadNfcOpenInfo.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void deleteNfcKey(int op) {
        Log.w(TAG, " deleteNfcKey.in op->" + op);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.deleteNfcKey(op);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. deleteNfcKey", ex);
            }
        } else {
            Log.e(TAG, " deleteNfcKey.null == mService");
        }
        Log.w(TAG, " deleteNfcKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void deleteSingleNfcKey(int op, String no) {
        Log.w(TAG, " delete single nfckey.in op->" + op + " no->" + no);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.deleteSingleNfcKey(op, no);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. deleteNfcKey", ex);
            }
        } else {
            Log.e(TAG, " deleteNfcKey.null == mService");
        }
        Log.w(TAG, " deleteNfcKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void deleteOwnIphoneKey(int op, String no) {
        Log.w(TAG, " delete iphone nfckey.in op->" + op + " no->" + no);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.deleteOwnIphoneKey(op, no);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. deleteOwnIphoneKey", ex);
            }
        } else {
            Log.e(TAG, " deleteOwnIphoneKey.null == mService");
        }
        Log.w(TAG, " deleteOwnIphoneKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void startRegisterNFCKey() {
        Log.w(TAG, " startRegisterNFCKey.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " startRegisterNFCKey.null == mService");
        } else {
            try {
                iOtaSdkManager.startRegisterNFCKey();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. startRegisterNFCKey", ex);
            }
        }
        Log.w(TAG, " startRegisterNFCKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getIg1RelayState() throws RemoteException {
        int rstate = 0;
        Log.w(TAG, " getIg1RelayState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getIg1RelayState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getIg1RelayState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getIg1RelayState", ex);
            }
        }
        Log.w(TAG, " getIg1RelayState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getIg4RelayState() throws RemoteException {
        int rstate = 0;
        Log.w(TAG, " getIg4RelayState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getIg4RelayState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getIg4RelayState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getIg4RelayState", ex);
            }
        }
        Log.w(TAG, " getIg4RelayState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void resetOta() {
        Log.w(TAG, " resetOta.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " resetOta.null == mService");
        } else {
            try {
                iOtaSdkManager.resetOta();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. resetOta", ex);
            }
        }
        Log.w(TAG, " resetOta.out = true");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int notifyTboxCmdResult(int cmd, int result) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " notifyTboxCmdResult.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " notifyTboxCmdResult.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.notifyTboxCmdResult(cmd, result);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. notifyTboxCmdResult", ex);
            }
        }
        Log.w(TAG, " notifyTboxCmdResult.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int uploadCdr(int length, String cdrStr) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " uploadCdr.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " uploadCdr.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.uploadCdr(length, cdrStr);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. uploadCdr", ex);
            }
        }
        Log.w(TAG, " uploadCdr.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getAutoVin() throws RemoteException {
        String rstate = null;
        Log.w(TAG, " getAutoVin.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getAutoVin.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getAutoVin();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getAutoVin", ex);
            }
        }
        Log.w(TAG, " getAutoVin.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int updateHsm(String path) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " updateHsm.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " updateHsm.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.updateHsm(path);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. updateHsm", ex);
            }
        }
        Log.w(TAG, " updateHsm.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setTcpDumpState(boolean state) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " setTcpDumpState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setTcpDumpState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setTcpDumpState(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setTcpDumpState", ex);
            }
        }
        Log.w(TAG, " setTcpDumpState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int clearTboxLog() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " clearTboxLog.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " clearTboxLog.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.clearTboxLog();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. clearTboxLog", ex);
            }
        }
        Log.w(TAG, " clearTboxLog.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setApn3State(boolean state) throws RemoteException {
        int rstate = 1;
        Log.w(TAG, " setApn3State.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setApn3State.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setApn3State(state);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setApn3State", ex);
            }
        }
        Log.w(TAG, " setApn3State.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getApn1FailReson() throws RemoteException {
        Log.w(TAG, " getApn1FailReson.in");
        int rstate = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getApn1FailReson.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.getApn1FailReson();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getApn1FailReson", ex);
            }
        }
        Log.w(TAG, " getApn1FailReson.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getApn1Address() throws RemoteException {
        Log.w(TAG, " getApn1Address.in");
        String result = TBOX_DEFAULT_VALUE;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getApn1Address.null == mService");
        } else {
            try {
                result = iOtaSdkManager.getApn1Address();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getApn1Address", ex);
            }
        }
        Log.w(TAG, " getApn1Address.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public String getYunUuidStr() throws RemoteException {
        Log.w(TAG, " getYunUuidStr.in");
        String result = TBOX_DEFAULT_VALUE;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " getYunUuidStr.null == mService");
        } else {
            try {
                result = iOtaSdkManager.getYunUuidStr();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getYunUuidStr", ex);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" getYunUuidStr.out = ");
        sb.append(result == null ? 0 : result.length());
        Log.w(TAG, sb.toString());
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setQueryOwnersKey(int config, int op) throws RemoteException {
        Log.w(TAG, " setQueryOwnersKey.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setQueryOwnersKey.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setQueryOwnersKey(config, op);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setQueryOwnersKey", ex);
            }
        }
        Log.w(TAG, " setQueryOwnersKey.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setQueryOwnersKeyV2(byte[] data) throws RemoteException {
        Log.w(TAG, " setQueryOwnersKeyV2.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setQueryOwnersKeyV2.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setQueryOwnersKeyV2(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setQueryOwnersKeyV2", ex);
            }
        }
        Log.w(TAG, " setQueryOwnersKeyV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setRegisterKeyStartPairingV2(byte[] data) throws RemoteException {
        Log.w(TAG, " setRegisterKeyStartPairingV2.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setRegisterKeyStartPairingV2.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setRegisterKeyStartPairingV2(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setRegisterKeyStartPairingV2", ex);
            }
        }
        Log.w(TAG, " setRegisterKeyStartPairingV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setAllowStartTimeAfterUnlockV2(byte[] data) throws RemoteException {
        Log.w(TAG, " setAllowStartTimeAfterUnlockV2.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setAllowStartTimeAfterUnlockV2.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setAllowStartTimeAfterUnlockV2(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAllowStartTimeAfterUnlockV2", ex);
            }
        }
        Log.w(TAG, " setAllowStartTimeAfterUnlockV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean deleteNfcFindKeyV2(byte[] data) throws RemoteException {
        Log.w(TAG, " deleteNfcFindKeyV2.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " deleteNfcFindKeyV2.null == mService");
        } else {
            try {
                result = iOtaSdkManager.deleteNfcFindKeyV2(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. deleteNfcFindKeyV2", ex);
            }
        }
        Log.w(TAG, " deleteNfcFindKeyV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean offOnlineRemoveKey97V2(byte[] data) throws RemoteException {
        Log.w(TAG, " offOnlineRemoveKey97V2.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " offOnlineRemoveKey97V2.null == mService");
        } else {
            try {
                result = iOtaSdkManager.offOnlineRemoveKey97V2(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. offOnlineRemoveKey97V2", ex);
            }
        }
        Log.w(TAG, " offOnlineRemoveKey97V2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean offOnlineRemoveKey98V2(byte[] data) throws RemoteException {
        Log.w(TAG, " offOnlineRemoveKey98V2.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " offOnlineRemoveKey98V2.null == mService");
        } else {
            try {
                result = iOtaSdkManager.offOnlineRemoveKey98V2(data);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. offOnlineRemoveKey98V2", ex);
            }
        }
        Log.w(TAG, " offOnlineRemoveKey98V2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setRegisterKeyStartPairing(int config, int op) throws RemoteException {
        Log.w(TAG, " setRegisterKeyStartPairing.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setRegisterKeyStartPairing.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setRegisterKeyStartPairing(config, op);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setRegisterKeyStartPairing", ex);
            }
        }
        Log.w(TAG, " setRegisterKeyStartPairing.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setAllowStartTimeAfterUnlock(int config, int value) throws RemoteException {
        Log.w(TAG, " setAllowStartTimeAfterUnlock.in");
        boolean result = false;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setAllowStartTimeAfterUnlock.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setAllowStartTimeAfterUnlock(config, value);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAllowStartTimeAfterUnlock", ex);
            }
        }
        Log.w(TAG, " setAllowStartTimeAfterUnlock.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setOperationCmdAndKeyType(int cmd, int type) throws RemoteException {
        Log.w(TAG, " setOperationCmdAndKeyType.in");
        int result = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setOperationCmdAndKeyType.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setOperationCmdAndKeyType(cmd, type);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setOperationCmdAndKeyType", ex);
            }
        }
        Log.w(TAG, " setOperationCmdAndKeyType.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setKeyIdOne(byte[] arr) throws RemoteException {
        Log.w(TAG, " setKeyIdOne.in");
        int result = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setKeyIdOne.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setKeyIdOne(arr);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setKeyIdOne", ex);
            }
        }
        Log.w(TAG, " setKeyIdOne.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setKeyIdTwo(byte[] arr) throws RemoteException {
        Log.w(TAG, " setKeyIdTwo.in");
        int result = -1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, " setKeyIdTwo.null == mService");
        } else {
            try {
                result = iOtaSdkManager.setKeyIdTwo(arr);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setKeyIdTwo", ex);
            }
        }
        Log.w(TAG, " setKeyIdTwo.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean relayDataToTbox40A(byte[] data) {
        Log.w(TAG, " relayDataToTbox40A.in ");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean sendRemoteLockOrUnlockStatus(int type, int status) {
        Log.w(TAG, " sendRemoteLockOrUnlockStatus.in type : " + type + ", status : " + status);
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean reqNetworkOperatorMode() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, "reqNetworkOperatorMode.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "reqNetworkOperatorMode.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.reqNetworkOperatorMode();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. reqNetworkOperatorMode", ex);
            }
        }
        Log.w(TAG, "reqNetworkOperatorMode.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean reqOperatorInfo() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, "reqOperatorInfo.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "reqOperatorInfo.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.reqOperatorInfo();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. reqOperatorInfo", ex);
            }
        }
        Log.w(TAG, "reqOperatorInfo.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setManuallyStationedNetwork(String network, int sigLevel) throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, "setManuallyStationedNetwork.in network : " + network + ", sigLevel : " + sigLevel);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setManuallyStationedNetwork(network, sigLevel);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setManuallyStationedNetwork", ex);
            }
        } else {
            Log.e(TAG, "setManuallyStationedNetwork.null == mService");
        }
        Log.w(TAG, "setManuallyStationedNetwork.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean setAutomaticNetwork() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, "setAutomaticNetwork.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "setAutomaticNetwork.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setAutomaticNetwork();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setAutomaticNetwork", ex);
            }
        }
        Log.w(TAG, "setAutomaticNetwork.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public boolean reqSimCardState() throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, "reqSimCardState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "reqSimCardState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.reqSimCardState();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. reqSimCardState", ex);
            }
        }
        Log.w(TAG, "reqSimCardState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setQxdmLogState(int logState, int logType) throws RemoteException {
        int rstate = 1;
        Log.w(TAG, "setQxdmLogState.in");
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager == null) {
            Log.e(TAG, "setQxdmLogState.null == mService");
        } else {
            try {
                rstate = iOtaSdkManager.setQxdmLogState(logState, logType);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setQxdmLogState", ex);
            }
        }
        Log.w(TAG, "setQxdmLogState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public void setUsbUpdateState(int state) {
        Log.w(TAG, " setUsbUpdateState.in state : " + state);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                iOtaSdkManager.setUsbUpdateState(state);
                return;
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setUsbUpdateState", ex);
                return;
            }
        }
        Log.e(TAG, " setUsbUpdateState.null == mService");
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int enableSimcardNetwork(int simCardType) throws RemoteException {
        int rstate = 1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.enableSimcardNetwork(simCardType);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. enableSimcardNetwork", ex);
            }
        } else {
            Log.e(TAG, " enableSimcardNetwork.null == mService");
        }
        Log.w(TAG, " enableSimcardNetwork.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int syncApnParam(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) throws RemoteException {
        int rstate = 1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.syncApnParam(apn, username, pwd, apnType, apnProtocol, proxy, port);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. syncApnParam", ex);
            }
        } else {
            Log.e(TAG, " syncApnParam.null == mService");
        }
        Log.w(TAG, " syncApnParam.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getTrafficFromTbox(int apnType, String iccid) throws RemoteException {
        int rstate = 1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getTrafficFromTbox(apnType, iccid);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getTrafficFromTbox", ex);
            }
        } else {
            Log.e(TAG, " getTrafficFromTbox.null == mService");
        }
        Log.w(TAG, " getTrafficFromTbox.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int setNonWhiteListNetworkEnable(boolean enableFlag) throws RemoteException {
        int rstate = 1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.setNonWhiteListNetworkEnable(enableFlag);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. setNonWhiteListNetworkEnable", ex);
            }
        } else {
            Log.e(TAG, " setNonWhiteListNetworkEnable.null == mService");
        }
        Log.w(TAG, " setNonWhiteListNetworkEnable.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getApnParamFromTbox() throws RemoteException {
        int rstate = 1;
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getApnParamFromTbox();
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getApnParamFromTbox", ex);
            }
        } else {
            Log.e(TAG, " getApnParamFromTbox.null == mService");
        }
        Log.w(TAG, " getApnParamFromTbox.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.OtaSdkManager
    public int getTboxNetworkState(int apnType) throws RemoteException {
        int rstate = 1;
        Log.w(TAG, " getTboxNetworkState.in apnType : " + apnType);
        IOtaSdkManager iOtaSdkManager = this.mService;
        if (iOtaSdkManager != null) {
            try {
                rstate = iOtaSdkManager.getTboxNetworkState(apnType);
            } catch (RemoteException ex) {
                Log.e(TAG, "RemoteException. getTboxNetworkState", ex);
            }
        } else {
            Log.e(TAG, " getTboxNetworkState.null == mService");
        }
        Log.w(TAG, " getTboxNetworkState.out = " + rstate);
        return rstate;
    }
}
