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
import com.ts.avm.IAvmServiceCommandListener;
import com.ts.avm.IAvmServiceInterface;
import com.ts.avm.IAvmServiceListener;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.avm.sdk.utils.LogManager;
import com.ts.avm.sdk.wrappers.CmdPool;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AvmManagerImpl extends AvmManager {
    public static final int AVM_CALIBRATION_CODE = 67108864;
    public static final int AVM_COMMAND_CODE = 33554432;
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
    private static final int REMOTE_ERROR = -2;
    private static final int SERVICE_STATE_ERROR = -3;
    private static final int STATE_ERROR = -1;
    private static final int SUCCESS = 0;
    private static final int TIME_RETRY_BIND_SERVICE = 1000;
    private IAvmServiceInterface iAvmServiceInterface;
    private Context mContext;
    private LocalHandler mRetryHandler;
    private HandlerThread mRetryHandlerThread;
    private ServiceConnection mServiceConnection;
    private static final String TAG = AvmManagerImpl.class.getSimpleName();
    private static volatile AvmManager manager = null;
    private boolean mServiceBound = false;
    private boolean mBinderResult = false;
    private boolean mInitialized = false;
    private int mAvmWorkState = 0;
    private int mAvmViewState = 0;
    private final IAvmServiceListener.Stub iAvmServiceListener = new IAvmServiceListener.Stub() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.1
        @Override // com.ts.avm.IAvmServiceListener
        public void onAvmServiceStatusChanged(int code, String message) throws RemoteException {
            AvmManagerImpl.this.mAvmWorkState = code;
            synchronized (AvmManagerImpl.this.mStatusListenerList) {
                LogManager.logd(AvmManagerImpl.TAG, "mStatusListenerList " + AvmManagerImpl.this.mStatusListenerList.size());
                for (AvmManager.AvmStatusListener listener : AvmManagerImpl.this.mStatusListenerList) {
                    if (listener != null) {
                        listener.onStatusChanged(code, message);
                    }
                }
            }
        }
    };
    private final IAvmServiceCommandListener.Stub iAvmServiceCommandListener = new IAvmServiceCommandListener.Stub() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.2
        @Override // com.ts.avm.IAvmServiceCommandListener
        public void onCommandReceived(CmdBean cmd) throws RemoteException {
            LogManager.logd(AvmManagerImpl.TAG, "onCommandReceived id:" + cmd.getId());
            if (cmd.getId() == 229376) {
                AvmManagerImpl.this.mAvmViewState = cmd.getValue()[0];
                synchronized (AvmManagerImpl.this.mViewStateListenerList) {
                    for (AvmManager.AvmViewStateListener listener : AvmManagerImpl.this.mViewStateListenerList) {
                        if (listener != null) {
                            listener.onAvmViewStateChanged(cmd.getValue()[0]);
                        }
                    }
                }
                return;
            }
            if (cmd.getId() == 67108992) {
                synchronized (AvmManagerImpl.this.mDockerStatusListenerList) {
                    for (AvmManager.AvmDockerStatusListener listener2 : AvmManagerImpl.this.mDockerStatusListenerList) {
                        if (listener2 != null) {
                            listener2.onAvmDockerStatusChanged(cmd.getValue()[0]);
                        }
                    }
                }
            }
        }
    };
    private final ArrayList<IAvmServiceConnectionListener> mAvmServiceStateList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmStatusListener> mStatusListenerList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmViewStateListener> mViewStateListenerList = new ArrayList<>();
    private final ArrayList<AvmManager.AvmDockerStatusListener> mDockerStatusListenerList = new ArrayList<>();

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
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
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
            LogManager.logi(TAG, "BindService Connection:");
            this.mBinderResult = this.mContext.bindService(intent, getConnection(), 1);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("BindService Result:");
            sb.append(this.mBinderResult);
            sb.append(" ");
            sb.append(this.mRetryHandler == null);
            LogManager.logi(str, sb.toString());
            if (!this.mBinderResult && (localHandler = this.mRetryHandler) != null) {
                if (localHandler.hasMessages(0)) {
                    this.mRetryHandler.removeMessages(0);
                }
                this.mRetryHandler.sendEmptyMessageDelayed(0, 1000L);
                return;
            }
            return;
        }
        LogManager.loge(TAG, "ERROR: Should Call setContext first.");
    }

    private ServiceConnection getConnection() {
        synchronized (AvmManagerImpl.class) {
            if (this.mServiceConnection == null) {
                this.mServiceConnection = new ServiceConnection() { // from class: com.ts.avm.sdk.impl.AvmManagerImpl.3
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        LogManager.logd(AvmManagerImpl.TAG, "onServiceConnected: " + Thread.currentThread().getName());
                        AvmManagerImpl.this.iAvmServiceInterface = IAvmServiceInterface.Stub.asInterface(service);
                        try {
                            int ret = AvmManagerImpl.this.iAvmServiceInterface.registerAvmStatusListener(AvmManagerImpl.this.iAvmServiceListener);
                            if (ret < 0) {
                                LogManager.loge(AvmManagerImpl.TAG, "registerAvmStatusListener ERROR: ", new Throwable());
                            }
                            int ret2 = AvmManagerImpl.this.iAvmServiceInterface.registerCommandListener(AvmManagerImpl.this.iAvmServiceCommandListener);
                            if (ret2 < 0) {
                                LogManager.loge(AvmManagerImpl.TAG, "registerCommandListener ERROR: ", new Throwable());
                            }
                        } catch (RemoteException exception) {
                            exception.printStackTrace();
                        }
                        synchronized (AvmManagerImpl.this.mAvmServiceStateList) {
                            if (AvmManagerImpl.this.iAvmServiceInterface == null) {
                                for (IAvmServiceConnectionListener serviceConnectionListener : AvmManagerImpl.this.mAvmServiceStateList) {
                                    if (serviceConnectionListener != null) {
                                        serviceConnectionListener.onError(0, "IBinder is null");
                                    }
                                }
                                LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: IBinder is null!");
                                return;
                            }
                            LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: Binder is success.");
                            for (IAvmServiceConnectionListener dvrServiceConnectionListener : AvmManagerImpl.this.mAvmServiceStateList) {
                                if (dvrServiceConnectionListener != null) {
                                    dvrServiceConnectionListener.onServiceConnected();
                                }
                            }
                            AvmManagerImpl.this.mServiceBound = true;
                        }
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName name) {
                        LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: Unbinder is success.");
                        AvmManagerImpl.this.mServiceBound = false;
                        AvmManagerImpl.this.mBinderResult = false;
                        synchronized (AvmManagerImpl.this.mAvmServiceStateList) {
                            if (AvmManagerImpl.this.iAvmServiceInterface == null) {
                                for (IAvmServiceConnectionListener serviceConnectionListener : AvmManagerImpl.this.mAvmServiceStateList) {
                                    if (serviceConnectionListener != null) {
                                        serviceConnectionListener.onError(0, "IBinder is null");
                                    }
                                }
                                LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: IBinder is null!");
                                return;
                            }
                            LogManager.logi(AvmManagerImpl.TAG, "onServiceConnected: Binder is success.");
                            for (IAvmServiceConnectionListener dvrServiceConnectionListener : AvmManagerImpl.this.mAvmServiceStateList) {
                                if (dvrServiceConnectionListener != null) {
                                    dvrServiceConnectionListener.onServiceDisConnected();
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
        if (!this.mServiceBound) {
            bindService();
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void init(Context context, IAvmServiceConnectionListener listener) {
        synchronized (this.mAvmServiceStateList) {
            if (!this.mAvmServiceStateList.contains(listener)) {
                this.mAvmServiceStateList.add(listener);
            }
        }
        synchronized (this) {
            setContext(context);
            bindService();
            this.mInitialized = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void deinit(IAvmServiceConnectionListener listener) {
        synchronized (this.mAvmServiceStateList) {
            if (listener == null) {
                LogManager.loge(TAG, "deinit: NULL Object input.", new Throwable());
            } else if (this.mAvmServiceStateList.contains(listener)) {
                this.mAvmServiceStateList.remove(listener);
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
    public int voiceRecognitionControl(AvmManager.VoiceEvent mode) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "voiceRecognitionControl ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(mode));
                    LogManager.logd(TAG, "voiceRecognitionControl: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "voiceRecognitionControl ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int show() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "show ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.State.START));
                    LogManager.logd(TAG, "show: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "show ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int hide() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "hide ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.State.STOP));
                    LogManager.logd(TAG, "hide: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "hide ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int onBack() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "onBack ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.Back.DEFAULT));
                    LogManager.logd(TAG, "onBack: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "onBack ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
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
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(theme));
                    LogManager.logd(TAG, "switchTheme: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "switchTheme ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int switchOrientation(AvmManager.Orientation orientation) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "switchOrientation ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(orientation));
                    LogManager.logd(TAG, "switchOrientation: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "switchOrientation ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int switchLanguage(AvmManager.Language language) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "switchLanguage ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(language));
                    LogManager.logd(TAG, "switchLanguage: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "switchLanguage ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int calibrate(AvmManager.Calibration calibration) {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(calibration));
                    LogManager.logd(TAG, "calibrate: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int getAvmWorkState() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    return this.iAvmServiceInterface.getAvmStatus();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int getAvmViewMode() {
        return this.mAvmViewState;
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerAvmViewStatusListener(AvmManager.AvmViewStateListener listener) {
        synchronized (this.mViewStateListenerList) {
            if (listener == null) {
                LogManager.loge(TAG, "registerAvmViewStatusListener: NULL Object input.", new Throwable());
                return NULL_OBJECT_ERROR;
            }
            if (!this.mViewStateListenerList.contains(listener)) {
                this.mViewStateListenerList.add(listener);
                return 0;
            }
            LogManager.loge(TAG, "registerAvmViewStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterAvmViewStatusListener(AvmManager.AvmViewStateListener listener) {
        synchronized (this.mViewStateListenerList) {
            if (listener == null) {
                LogManager.loge(TAG, "unregisterAvmViewStatusListener: NULL Object input.", new Throwable());
                return NULL_OBJECT_ERROR;
            }
            if (this.mViewStateListenerList.contains(listener)) {
                this.mViewStateListenerList.remove(listener);
                return 0;
            }
            LogManager.loge(TAG, "unregisterAvmViewStatusListener: this listener has not been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerAvmDockerStatusListener(AvmManager.AvmDockerStatusListener listener) {
        synchronized (this.mDockerStatusListenerList) {
            if (listener == null) {
                LogManager.loge(TAG, "registerAvmDockerStatusListener: NULL Object input.", new Throwable());
                return NULL_OBJECT_ERROR;
            }
            if (!this.mDockerStatusListenerList.contains(listener)) {
                this.mDockerStatusListenerList.add(listener);
                return 0;
            }
            LogManager.loge(TAG, "registerAvmDockerStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterAvmDockerStatusListener(AvmManager.AvmDockerStatusListener listener) {
        synchronized (this.mDockerStatusListenerList) {
            if (listener == null) {
                LogManager.loge(TAG, "unregisterAvmDockerStatusListener: NULL Object input.", new Throwable());
                return NULL_OBJECT_ERROR;
            }
            if (this.mDockerStatusListenerList.contains(listener)) {
                this.mDockerStatusListenerList.remove(listener);
                return 0;
            }
            LogManager.loge(TAG, "unregisterAvmDockerStatusListener: this listener has not been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int registerAvmStatusListener(AvmManager.AvmStatusListener listener) {
        return registerStatusListener(listener);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int unregisterAvmStatusListener(AvmManager.AvmStatusListener listener) {
        return unregisterStatusListener(listener);
    }

    public int start() {
        synchronized (this) {
            if (!this.mInitialized) {
                LogManager.loge(TAG, "start ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.startAvm();
                    LogManager.logd(TAG, "start: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "start ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }

    public synchronized int stop() {
        synchronized (this) {
            try {
                try {
                    if (!this.mInitialized) {
                        LogManager.loge(TAG, "stop ERROR: should call init first.", new Throwable());
                        return -1;
                    }
                    if (this.iAvmServiceInterface != null) {
                        try {
                            this.iAvmServiceInterface.stopAvm();
                            LogManager.logd(TAG, "stop: called success");
                            return 0;
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                            return -2;
                        }
                    }
                    LogManager.loge(TAG, "stop ERROR: this function should be called after getting service connection callback.", new Throwable());
                    return SERVICE_STATE_ERROR;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public int registerStatusListener(AvmManager.AvmStatusListener listener) {
        synchronized (this.mStatusListenerList) {
            if (listener == null) {
                LogManager.loge(TAG, "registerStatusListener: NULL Object input.", new Throwable());
                return NULL_OBJECT_ERROR;
            }
            if (!this.mStatusListenerList.contains(listener)) {
                this.mStatusListenerList.add(listener);
                return 0;
            }
            LogManager.loge(TAG, "registerStatusListener: this listener has already been registered.", new Throwable());
            return COMMON_ERROR;
        }
    }

    public int unregisterStatusListener(AvmManager.AvmStatusListener listener) {
        synchronized (this.mStatusListenerList) {
            if (listener == null) {
                LogManager.loge(TAG, "registerStatusListener: NULL Object input.", new Throwable());
                return NULL_OBJECT_ERROR;
            }
            if (this.mStatusListenerList.contains(listener)) {
                this.mStatusListenerList.remove(listener);
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
            if (this.iAvmServiceInterface != null) {
                try {
                    this.iAvmServiceInterface.sendCommand(CmdPool.getCmd(AvmManager.Kill.DEFAULT));
                    LogManager.logd(TAG, "killAvm: called success");
                    return 0;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return -2;
                }
            }
            LogManager.loge(TAG, "killAvm ERROR: this function should be called after getting service connection callback.", new Throwable());
            return SERVICE_STATE_ERROR;
        }
    }
}
