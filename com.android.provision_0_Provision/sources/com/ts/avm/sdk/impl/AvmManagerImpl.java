package com.ts.avm.sdk.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.ts.avm.IAvmCallListener;
import com.ts.avm.IAvmServiceCommandListener;
import com.ts.avm.IAvmServiceInterface;
import com.ts.avm.IAvmServiceListener;
import com.ts.avm.bean.AvmCallInfoBean;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.avm.sdk.utils.LogManager;
import com.ts.avm.sdk.wrappers.CmdBuilder;
import com.ts.avm.sdk.wrappers.CmdPool;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AvmManagerImpl extends AvmManager {
    public static final int AVM_CALIBRATION_CODE = 67108864;
    public static final int AVM_COMMAND_CODE = 33554432;
    public static final int AVM_COMMON_SRC_ID = 9;
    public static final int AVM_COMMON_TARGET_ID = 7;
    public static final int AVM_DOCKER_CODE = 67108992;
    public static final int AVM_ORIENTATION_CODE = 33554688;
    public static final int AVM_RECEIVE_CODE = Integer.MIN_VALUE;
    public static final int AVM_STATE_CHANGE_CODE = 4194304;
    public static final int AVM_THEME_CODE = 33554448;
    public static final int AVM_VIEW_MODE_CHANGE_CODE = 229376;
    public static final int AVM_VR_CODE = 16777216;
    private static final int BACK_LINE_MULTIMEDIA = 2;
    private static final int BACK_LINE_NOT_SUPPORT = 0;
    private static final int BACK_LINE_PAN_INTERNAL = 1;
    private static final String CLASS_NAME = "com.ts.avm.AvmAndroidService";
    private static final int COMMON_ERROR = -5;
    private static final int MSG_RETRY_BIND_SERVICE = 0;
    private static final String NATIVE_RETRY = "retry_thread_avm";
    private static final int NULL_OBJECT_ERROR = -4;
    private static final String PACKAGE_NAME = "com.ts.avm";
    private static final int PHONE_NUM_MIN_LENGTH = 4;
    private static final int REMOTE_ERROR = -2;
    private static final int SERVICE_STATE_ERROR = -3;
    public static final int SET_NAVI_STATE = 53554691;
    public static final int SET_PROVISION_FINISHED = 33554690;
    private static final int STATE_ERROR = -1;
    private static final int SUCCESS = 0;
    public static final int SWITCH_RADAR_ALARM_TONE = 129;
    public static final int SYNC_HVAC_ICON_CODE = 33554687;
    private static final String TAG = "AvmManagerImpl";
    private static final int TIME_RETRY_BIND_SERVICE = 1000;
    private static volatile AvmManager manager;
    private IAvmServiceInterface iAvmServiceInterface;
    private Context mContext;
    private LocalHandler mRetryHandler;
    private HandlerThread mRetryHandlerThread;
    private ServiceConnection mServiceConnection;
    private boolean mServiceBound = false;
    private boolean mBinderResult = false;
    private boolean mInitialized = false;
    private int mAvmWorkState = 0;
    private int mAvmViewState = 0;
    private final IAvmServiceListener.Stub iAvmServiceListener = new IAvmServiceListener.Stub() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.1
        @Override // com.ts.avm.IAvmServiceListener
        public void onAvmServiceStatusChanged(int i, String str) throws RemoteException {
            AvmManagerImpl.this.mAvmWorkState = i;
            synchronized (AvmManagerImpl.this.mStatusListenerList) {
                LogManager.logd(AvmManagerImpl.TAG, "mStatusListenerList " + AvmManagerImpl.this.mStatusListenerList.size());
                for (AvmManager.AvmStatusListener avmStatusListener : AvmManagerImpl.this.mStatusListenerList) {
                    if (avmStatusListener != null) {
                        avmStatusListener.onStatusChanged(i, str);
                    }
                }
            }
        }
    };
    private final IAvmServiceCommandListener.Stub iAvmServiceCommandListener = new IAvmServiceCommandListener.Stub() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.2
        @Override // com.ts.avm.IAvmServiceCommandListener
        public void onCommandReceived(CmdBean cmdBean) throws RemoteException {
            LogManager.logd(AvmManagerImpl.TAG, "onCommandReceived id:" + cmdBean.getId());
            if (cmdBean.getId() == 229376 || cmdBean.getId() == 33554432) {
                AvmManagerImpl.this.mAvmViewState = cmdBean.getValue()[0];
                synchronized (AvmManagerImpl.this.mViewStateListenerList) {
                    for (AvmManager.AvmViewStateListener avmViewStateListener : AvmManagerImpl.this.mViewStateListenerList) {
                        if (avmViewStateListener != null) {
                            avmViewStateListener.onAvmViewStateChanged(cmdBean.getValue()[0]);
                        }
                    }
                }
                return;
            }
            if (cmdBean.getId() == 67108992) {
                synchronized (AvmManagerImpl.this.mDockerStatusListenerList) {
                    for (AvmManager.AvmDockerStatusListener avmDockerStatusListener : AvmManagerImpl.this.mDockerStatusListenerList) {
                        if (avmDockerStatusListener != null) {
                            avmDockerStatusListener.onAvmDockerStatusChanged(cmdBean.getValue()[0]);
                        }
                    }
                }
            }
        }
    };
    private final IAvmCallListener.Stub iAvmCallListener = new IAvmCallListener.Stub() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.3
        @Override // com.ts.avm.IAvmCallListener
        public void onAvmCallStatusChanged(String str, int i, int i2) throws RemoteException {
            String strSubstring = "";
            if (str != null) {
                strSubstring = str.length() > 4 ? str.substring(str.length() - 4, str.length()) : str;
            }
            LogManager.logd(AvmManagerImpl.TAG, " onAvmCallStatusChanged: phonenum:" + strSubstring + " calltype:" + i + " action:" + i2);
            synchronized (AvmManagerImpl.this.mAvmCallListenersList) {
                for (AvmManager.AvmCallListener avmCallListener : AvmManagerImpl.this.mAvmCallListenersList) {
                    if (avmCallListener != null) {
                        avmCallListener.onCallStatusChange(str, i, i2);
                    }
                }
            }
        }
    };
    private final ArrayList<IAvmServiceConnectionListener> mAvmServiceStateList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmStatusListener> mStatusListenerList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmViewStateListener> mViewStateListenerList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmDockerStatusListener> mDockerStatusListenerList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmCallListener> mAvmCallListenersList = new ArrayList<>();

    private AvmManagerImpl() {
        HandlerThread handlerThread = new HandlerThread(NATIVE_RETRY);
        this.mRetryHandlerThread = handlerThread;
        handlerThread.start();
        this.mRetryHandler = new LocalHandler(this.mRetryHandlerThread.getLooper());
    }

    public static synchronized AvmManager getInstance() {
        if (manager == null) {
            manager = new AvmManagerImpl();
        }
        return manager;
    }

    class LocalHandler extends Handler {
        public LocalHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                LogManager.logd(AvmManagerImpl.TAG, "handleMessage MSG_RETRY_BIND_SERVICE");
                AvmManagerImpl.this.reBindService();
            }
        }
    }

    private void bindService() {
        LocalHandler localHandler;
        Intent intent = new Intent(CLASS_NAME);
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        if (this.mContext != null) {
            String str = TAG;
            LogManager.logi(str, "BindService Connection:");
            this.mBinderResult = this.mContext.bindService(intent, getConnection(), 1);
            LogManager.logi(str, "BindService Result:" + this.mBinderResult + " " + (this.mRetryHandler == null));
            if (this.mBinderResult || (localHandler = this.mRetryHandler) == null) {
                return;
            }
            if (localHandler.hasMessages(0)) {
                this.mRetryHandler.removeMessages(0);
            }
            this.mRetryHandler.sendEmptyMessageDelayed(0, 1000L);
            return;
        }
        LogManager.loge(TAG, "ERROR: Should Call setContext first.");
    }

    private ServiceConnection getConnection() {
        synchronized (AvmManagerImpl.class) {
            if (this.mServiceConnection == null) {
                this.mServiceConnection = new ServiceConnection() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.4
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        LogManager.logd(AvmManagerImpl.TAG, "onServiceConnected: " + Thread.currentThread().getName());
                        AvmManagerImpl.this.iAvmServiceInterface = IAvmServiceInterface.Stub.asInterface(iBinder);
                        try {
                            if (AvmManagerImpl.this.iAvmServiceInterface.registerAvmStatusListener(AvmManagerImpl.this.iAvmServiceListener) < 0) {
                                LogManager.loge(AvmManagerImpl.TAG, "registerAvmStatusListener ERROR: ", new Throwable());
                            }
                            if (AvmManagerImpl.this.iAvmServiceInterface.registerCommandListener(AvmManagerImpl.this.iAvmServiceCommandListener) < 0) {
                                LogManager.loge(AvmManagerImpl.TAG, "registerCommandListener ERROR: ", new Throwable());
                            }
                            if (AvmManagerImpl.this.iAvmServiceInterface.registerCallStatusListener(AvmManagerImpl.this.iAvmCallListener) < 0) {
                                LogManager.loge(AvmManagerImpl.TAG, "registerCallStatusListener ERROR: ", new Throwable());
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        synchronized (AvmManagerImpl.this.mAvmServiceStateList) {
                            if (AvmManagerImpl.this.iAvmServiceInterface == null) {
                                for (IAvmServiceConnectionListener iAvmServiceConnectionListener : AvmManagerImpl.this.mAvmServiceStateList) {
                                    if (iAvmServiceConnectionListener != null) {
                                        iAvmServiceConnectionListener.onError(0, "IBinder is null");
                                    }
                                }
                                LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: IBinder is null!");
                                return;
                            }
                            LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: Binder is success.");
                            for (IAvmServiceConnectionListener iAvmServiceConnectionListener2 : AvmManagerImpl.this.mAvmServiceStateList) {
                                if (iAvmServiceConnectionListener2 != null) {
                                    iAvmServiceConnectionListener2.onServiceConnected();
                                }
                            }
                            AvmManagerImpl.this.mServiceBound = true;
                        }
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName componentName) {
                        LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: Unbinder is success.");
                        AvmManagerImpl.this.mServiceBound = false;
                        AvmManagerImpl.this.mBinderResult = false;
                        synchronized (AvmManagerImpl.this.mAvmServiceStateList) {
                            if (AvmManagerImpl.this.iAvmServiceInterface == null) {
                                for (IAvmServiceConnectionListener iAvmServiceConnectionListener : AvmManagerImpl.this.mAvmServiceStateList) {
                                    if (iAvmServiceConnectionListener != null) {
                                        iAvmServiceConnectionListener.onError(0, "IBinder is null");
                                    }
                                }
                                LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: IBinder is null!");
                                return;
                            }
                            LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: Binder is success.");
                            for (IAvmServiceConnectionListener iAvmServiceConnectionListener2 : AvmManagerImpl.this.mAvmServiceStateList) {
                                if (iAvmServiceConnectionListener2 != null) {
                                    iAvmServiceConnectionListener2.onServiceDisConnected();
                                }
                            }
                        }
                    }
                };
            }
        }
        return this.mServiceConnection;
    }

    private void setContext(Context context) {
        this.mContext = context;
    }

    private void unBindService() {
        HandlerThread handlerThread = this.mRetryHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mRetryHandlerThread = null;
            this.mRetryHandler = null;
        }
        if (this.mServiceBound && this.mBinderResult) {
            this.mContext.unbindService(getConnection());
            this.mServiceBound = false;
            this.mBinderResult = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reBindService() {
        LogManager.logv(TAG, "reBindService: " + this.mServiceBound);
        if (this.mServiceBound) {
            return;
        }
        bindService();
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void init(Context context, IAvmServiceConnectionListener iAvmServiceConnectionListener) {
        synchronized (this.mAvmServiceStateList) {
            if (!this.mAvmServiceStateList.contains(iAvmServiceConnectionListener)) {
                this.mAvmServiceStateList.add(iAvmServiceConnectionListener);
            }
        }
        synchronized (this) {
            setContext(context);
            bindService();
            this.mInitialized = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void deinit(IAvmServiceConnectionListener iAvmServiceConnectionListener) {
        synchronized (this.mAvmServiceStateList) {
            if (iAvmServiceConnectionListener == null) {
                LogManager.loge(TAG, "deinit: NULL Object input.", new Throwable());
            } else if (this.mAvmServiceStateList.contains(iAvmServiceConnectionListener)) {
                this.mAvmServiceStateList.remove(iAvmServiceConnectionListener);
            } else {
                LogManager.loge(TAG, "deinit: this listener has not been registered.", new Throwable());
            }
        }
        synchronized (this) {
            unBindService();
            this.mInitialized = false;
            this.mContext = null;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int voiceRecognitionControl(AvmManager.VoiceEvent voiceEvent) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "voiceRecognitionControl ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(voiceEvent));
                    LogManager.logd(TAG, "voiceRecognitionControl: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "voiceRecognitionControl ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int show() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "show ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.State.START));
                    LogManager.logd(TAG, "show: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "show ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int hide() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "hide ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.State.STOP));
                    LogManager.logd(TAG, "hide: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "hide ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int onBack() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "onBack ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.Back.DEFAULT));
                    LogManager.logd(TAG, "onBack: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "onBack ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int getBackLineConfig() {
        synchronized (this) {
            LogManager.logd(TAG, "getBackLineConfig");
        }
        return 1;
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int switchTheme(AvmManager.Theme theme) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "switchTheme ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(theme));
                    LogManager.logd(TAG, "switchTheme: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "switchTheme ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int switchOrientation(AvmManager.Orientation orientation) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "switchOrientation ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(orientation));
                    LogManager.logd(TAG, "switchOrientation: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "switchOrientation ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int switchLanguage(AvmManager.Language language) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "switchLanguage ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(language));
                    LogManager.logd(TAG, "switchLanguage: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "switchLanguage ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int calibrate(AvmManager.Calibration calibration) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(calibration));
                    LogManager.logd(TAG, "calibrate: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int getAvmWorkState() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    return iAvmServiceInterface.getAvmStatus();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int getAvmViewMode() {
        return this.mAvmViewState;
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerAvmViewStatusListener(AvmManager.AvmViewStateListener avmViewStateListener) {
        synchronized (this.mViewStateListenerList) {
            if (avmViewStateListener == null) {
                LogManager.loge(TAG, "registerAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (!this.mViewStateListenerList.contains(avmViewStateListener)) {
                this.mViewStateListenerList.add(avmViewStateListener);
                return 0;
            }
            LogManager.loge(TAG, "registerAvmViewStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterAvmViewStatusListener(AvmManager.AvmViewStateListener avmViewStateListener) {
        synchronized (this.mViewStateListenerList) {
            if (avmViewStateListener == null) {
                LogManager.loge(TAG, "unregisterAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.mViewStateListenerList.contains(avmViewStateListener)) {
                this.mViewStateListenerList.remove(avmViewStateListener);
                return 0;
            }
            LogManager.loge(TAG, "unregisterAvmViewStatusListener: this listener has not been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerAvmDockerStatusListener(AvmManager.AvmDockerStatusListener avmDockerStatusListener) {
        synchronized (this.mDockerStatusListenerList) {
            if (avmDockerStatusListener == null) {
                LogManager.loge(TAG, "registerAvmDockerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (!this.mDockerStatusListenerList.contains(avmDockerStatusListener)) {
                this.mDockerStatusListenerList.add(avmDockerStatusListener);
                return 0;
            }
            LogManager.loge(TAG, "registerAvmDockerStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterAvmDockerStatusListener(AvmManager.AvmDockerStatusListener avmDockerStatusListener) {
        synchronized (this.mDockerStatusListenerList) {
            if (avmDockerStatusListener == null) {
                LogManager.loge(TAG, "unregisterAvmDockerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.mDockerStatusListenerList.contains(avmDockerStatusListener)) {
                this.mDockerStatusListenerList.remove(avmDockerStatusListener);
                return 0;
            }
            LogManager.loge(TAG, "unregisterAvmDockerStatusListener: this listener has not been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerAvmStatusListener(AvmManager.AvmStatusListener avmStatusListener) {
        return registerStatusListener(avmStatusListener);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterAvmStatusListener(AvmManager.AvmStatusListener avmStatusListener) {
        return unregisterStatusListener(avmStatusListener);
    }

    public int start() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "start ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.startAvm();
                    LogManager.logd(TAG, "start: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "start ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    public synchronized int stop() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "stop ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.stopAvm();
                    LogManager.logd(TAG, "stop: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "stop ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    public int registerStatusListener(AvmManager.AvmStatusListener avmStatusListener) {
        synchronized (this.mStatusListenerList) {
            if (avmStatusListener == null) {
                LogManager.loge(TAG, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (!this.mStatusListenerList.contains(avmStatusListener)) {
                this.mStatusListenerList.add(avmStatusListener);
                return 0;
            }
            LogManager.loge(TAG, "registerStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    public int unregisterStatusListener(AvmManager.AvmStatusListener avmStatusListener) {
        synchronized (this.mStatusListenerList) {
            if (avmStatusListener == null) {
                LogManager.loge(TAG, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.mStatusListenerList.contains(avmStatusListener)) {
                this.mStatusListenerList.remove(avmStatusListener);
                return 0;
            }
            LogManager.loge(TAG, "unregisterStatusListener: this listener has not been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int killAvm() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "killAvm ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.Kill.DEFAULT));
                    LogManager.logd(TAG, "killAvm: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "killAvm ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int syncHvacIcon2Qnx(int i) {
        String str = TAG;
        LogManager.logd(str, "syncHvacIcon2Qnx value:" + i);
        CmdBean cmdBeanBuild = new CmdBuilder().setId(SYNC_HVAC_ICON_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{i, 0, 0, 0}).build();
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(str, "syncHvacIcon2Qnx ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(cmdBeanBuild);
                    LogManager.logd(str, "syncHvacIcon2Qnx: called success value = " + i);
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(str, "syncHvacIcon2Qnx ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int setProvisionFinished(int i) {
        String str = TAG;
        LogManager.logd(str, "setProvisionFinished mode:" + i);
        CmdBean cmdBeanBuild = new CmdBuilder().setId(SET_PROVISION_FINISHED).setSrcId(9).setTargetId(7).setValue(new int[]{i, 0, 0, 0}).build();
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(str, "setProvisionFinished ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(cmdBeanBuild);
                    LogManager.logd(str, "setProvisionFinished: called success value = " + i);
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(str, "setProvisionFinished ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int setNaviState(int i) {
        String str = TAG;
        LogManager.logd(str, "setNaviState mode:" + i);
        CmdBean cmdBeanBuild = new CmdBuilder().setId(SET_NAVI_STATE).setSrcId(9).setTargetId(7).setValue(new int[]{i, 0, 0, 0}).build();
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(str, "setNaviState ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(cmdBeanBuild);
                    LogManager.logd(str, "setNaviState: called success value = " + i);
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(str, "setNaviState ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int startPip() {
        String str = TAG;
        LogManager.logd(str, "startPip");
        CmdBean cmdBeanBuild = new CmdBuilder().setId(CmdPool.CONTROL_PIP).setSrcId(9).setTargetId(7).setValue(new int[]{1, 0, 0, 0}).build();
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(str, "startPip ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(cmdBeanBuild);
                    LogManager.logd(str, "startPip: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(str, "startPip ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int stopPip() {
        String str = TAG;
        LogManager.logd(str, "stopPip");
        CmdBean cmdBeanBuild = new CmdBuilder().setId(CmdPool.CONTROL_PIP).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, 0}).build();
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(str, "stopPip ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(cmdBeanBuild);
                    LogManager.logd(str, "stopPip: called success");
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(str, "stopPip ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int switchRadarAlarmToneStatus(boolean z) {
        String str = TAG;
        LogManager.logd(str, "switchRadarAlarmToneStatus : isOn? :" + z);
        CmdBean cmdBeanBuild = new CmdBuilder().setId(129).setSrcId(9).setTargetId(7).setValue(new int[]{z ? 1 : 0, 0, 0, 0}).build();
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(str, "switchRadarAlarmToneStatus ERROR: should call init first", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCommand(cmdBeanBuild);
                    LogManager.logd(str, "switchRadarAlarmToneStatus: called success value = " + (z ? 1 : 0));
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(str, "switchRadarAlarmToneStatus ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int sendCallInfo(String str, String str2, String str3, int i, int i2, int i3, boolean z) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "sendCallInfo ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendCallInfo(str, str2, str3, i, i2, i3, z);
                    if (str == null) {
                        str = "";
                    } else if (str.length() > 4) {
                        str = str.substring(str.length() - 4, str.length());
                    }
                    LogManager.logd(TAG, "sendCallInfo: called success phoneNum:" + str + " callTime:" + i + " callType:" + i2 + " callState:" + i3 + " isMute:" + z);
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "sendCallInfo ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerCallStatusListener(AvmManager.AvmCallListener avmCallListener) {
        synchronized (this.mAvmCallListenersList) {
            if (avmCallListener == null) {
                LogManager.loge(TAG, "registerCallStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (!this.mAvmCallListenersList.contains(avmCallListener)) {
                this.mAvmCallListenersList.add(avmCallListener);
                return 0;
            }
            LogManager.loge(TAG, "registerCallStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterCallStatusListener(AvmManager.AvmCallListener avmCallListener) {
        synchronized (this.mAvmCallListenersList) {
            if (avmCallListener == null) {
                LogManager.loge(TAG, "unregisterCallStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.mAvmCallListenersList.contains(avmCallListener)) {
                this.mAvmCallListenersList.remove(avmCallListener);
                return 0;
            }
            LogManager.loge(TAG, "unregisterCallStatusListener: this listener has not been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int sendMultiCallInfo(List<AvmCallInfoBean> list) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "sendMultiCallInfo ERROR: should call init first.", new Throwable());
                return -1;
            }
            IAvmServiceInterface iAvmServiceInterface = this.iAvmServiceInterface;
            if (iAvmServiceInterface != null) {
                try {
                    iAvmServiceInterface.sendMultiCallInfo(list);
                    for (int i = 0; i < list.size(); i++) {
                        AvmCallInfoBean avmCallInfoBean = list.get(i);
                        if (avmCallInfoBean != null) {
                            LogManager.logd(TAG, avmCallInfoBean.toString());
                        }
                    }
                    return 0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "sendCallInfo ERROR: this function should be called after getting service connection callback.", new Throwable());
            return -3;
        }
    }
}
