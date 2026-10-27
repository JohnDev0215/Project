package com.ts.carplay.app.service.model.appstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import com.iflytek.speech.NativeHandle;
import com.iflytek.speech.libissmvw;
import com.iflytek.speech.mvw.IMVWListener;
import com.ts.carplay.app.service.common.IModel;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.app.service.model.ModelConstant;
import com.ts.carplay.utils.LogUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class AppStateManager extends IModel {
    private static final String ACTION_ECALL_STATE_CHANGE = "com.byd.system.ecall.ACTION_EVENT_CHANGE";
    private static final String ACTION_VR_END = "android.broadcast.voice.request.release";
    private static final String ACTION_VR_START = "android.broadcast.voice.request.start";
    public static final String AUXIN_PATH = "/sdcard/auxin_java.pcm";
    private static final int BYTE_LENGTH = 1024;
    public static final String DUMP_AUDIO_FLAG_KEY = "test.mic.flag";
    private static final String ECALL_WIDGET_EXTRA_STATE = "isShow";
    private static final int ECALL_WIDGET_EXTRA_STATE_HIDEN = 0;
    private static final int ECALL_WIDGET_EXTRA_STATE_SHOWN = 1;
    private static final int MVW_LANGUAGE = 100;
    private static final int PAIRING_REQUEST_PRIORITY = 1000;
    public static final int PREPARE_CONFIG_FILE_FINISH = 2;
    public static final int REBUILD_SOCKET_DELAY = 200;
    private static final String RES_FILE_NAME = "Res.zip";
    private static String RES_PATH = "";
    private static final String SIRI_INIT_PATH = "/MVWRes/FirstRes";
    private static final int SIRI_SCENE = 1;
    private static final int SOCKET_BUFFER_SIZE = 1024;
    private static final String SOCKET_NAME = "cp_audio_socket";
    private static final int SOCKET_SLEEP_TIME = 10;
    private static final String STATE = "state";
    private static final int STATE_END = 0;
    private static final int STATE_START = 1;
    private static final String TAG = "AppStateManager";
    private static final String VALUE_TRUE = "true";
    private static final String VR_MIC_KEY = "usemic";
    private static final String VR_USER_KEY = "who";
    private static final String VR_USER_NAME_BYD = "bydvoice";
    private static final String VR_USER_NAME_CARPLAY = "carplay";
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
    private LocalServerSocket audioSocketServer;
    BroadcastReceiver avmStatusReceiver;
    private BtPhoneCallStateListener mBtPhoneCallStateListener;
    private boolean mCanUseSiri;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private ICarPlayRemoteManagerListener mCarPlayRemoteManagerListener;
    private Context mContext;
    private String mCurrentLanguage;
    BroadcastReceiver mEcallWidgetStateReceiver;
    private FileChannel mFileChannel;
    private Handler mHandler;
    private boolean mIsAvm;
    private boolean mIsBtPhoneCallActive;
    private boolean mIsBtPhoneSpeechActive;
    private boolean mIsCarPlaySessionActive;
    private boolean mIsCpCallActive;
    private boolean mIsDumpAudio;
    private boolean mIsEcallActive;
    private boolean mIsNativeCallActive;
    private boolean mIsNativeVrActive;
    private boolean mIsPreBtPhoneSpeechActive;
    private boolean mIsPreNativeCallActive;
    IMVWListener mMvwListener;
    private NativeHandle mNativeHandle;
    private RandomAccessFile mRandomFile;
    private boolean mSiriInit;
    private boolean mSiriStart;
    private List<String> mSupportedLanguages;
    private TelephonyManager mTelephonyManager;
    private boolean socketRunning;
    BroadcastReceiver vrReceiver;

    public AppStateManager(Looper looper) {
        super(looper);
        this.mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener();
        this.mTelephonyManager = null;
        this.mSupportedLanguages = new ArrayList();
        this.mCurrentLanguage = "en-GB";
        this.mIsNativeVrActive = false;
        this.mIsCarPlaySessionActive = false;
        this.mIsCpCallActive = false;
        this.mIsEcallActive = false;
        this.mIsBtPhoneCallActive = false;
        this.mIsBtPhoneSpeechActive = false;
        this.mIsPreBtPhoneSpeechActive = false;
        this.mIsNativeCallActive = false;
        this.mIsPreNativeCallActive = false;
        this.mSiriInit = false;
        this.mSiriStart = false;
        this.mCanUseSiri = false;
        this.mIsAvm = false;
        this.socketRunning = false;
        this.mRandomFile = null;
        this.mFileChannel = null;
        this.mIsDumpAudio = false;
        this.vrReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.appstate.AppStateManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    String user = intent.getStringExtra(AppStateManager.VR_USER_KEY);
                    LogUtil.info(AppStateManager.TAG, "vrReceiver user:" + user + " mIsNativeVrActive:" + AppStateManager.this.mIsNativeVrActive + " action:" + action);
                    if (AppStateManager.ACTION_VR_START.equals(action)) {
                        if (AppStateManager.VR_USER_NAME_BYD.equals(user) && !AppStateManager.this.mIsNativeVrActive) {
                            AppStateManager.this.mIsNativeVrActive = true;
                            AppStateManager.this.mCarPlayRemoteManager.setNativeAppState(2, AppStateManager.this.mIsNativeVrActive, 2);
                        }
                    } else if (AppStateManager.ACTION_VR_END.equals(action) && AppStateManager.VR_USER_NAME_BYD.equals(user) && AppStateManager.this.mIsNativeVrActive) {
                        AppStateManager.this.mIsNativeVrActive = false;
                        AppStateManager.this.mCarPlayRemoteManager.setNativeAppState(2, AppStateManager.this.mIsNativeVrActive, -1);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.avmStatusReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.appstate.AppStateManager.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    if (ModelConstant.ACTION_AVM_STATUS.equals(action)) {
                        AppStateManager.this.mIsAvm = intent.getBooleanExtra(ModelConstant.KEY_AVM_STATUS, false);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.mEcallWidgetStateReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.appstate.AppStateManager.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    int state = intent.getIntExtra(AppStateManager.ECALL_WIDGET_EXTRA_STATE, 0);
                    if (action == null) {
                        LogUtil.debug(AppStateManager.TAG, "mEcallWidgetStateReceiver.onReceive: action is null");
                        return;
                    }
                    LogUtil.info(AppStateManager.TAG, "mEcallWidgetStateReceiver.onReceive(), action=" + action + ", state=" + state);
                    if (AppStateManager.ACTION_ECALL_STATE_CHANGE.equals(action)) {
                        if (1 != state) {
                            AppStateManager.this.mIsEcallActive = false;
                        }
                        AppStateManager.this.sendCallState();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.mMvwListener = new IMVWListener() { // from class: com.ts.carplay.app.service.model.appstate.AppStateManager.4
            @Override // com.iflytek.speech.mvw.IMVWListener
            public void onMVWWakeup(int nMvwScene, int nMvwId, int nMvwScore, String lParam) {
                LogUtil.info(AppStateManager.TAG, "onMVWWakeup nMvwScene:" + nMvwScene + ",mIsAvm:" + AppStateManager.this.mIsAvm + ",nMvwId:" + nMvwId + ",nMvwScore:" + nMvwScore + ",lParam:" + lParam);
                if (!AppStateManager.this.mIsNativeVrActive) {
                    if (AppStateManager.this.mCanUseSiri && AppStateManager.this.mCarPlayRemoteManager != null && !AppStateManager.this.mIsAvm) {
                        AppStateManager.this.mCarPlayRemoteManager.requestSiri(4, SystemClock.uptimeMillis());
                        return;
                    }
                    return;
                }
                LogUtil.info(AppStateManager.TAG, "onMVWWakeup Native VR is active!");
            }

            @Override // com.iflytek.speech.mvw.IMVWListener
            public void onMVWMsgProc_(long uMsg, long wParam, String lParam) {
                LogUtil.info(AppStateManager.TAG, "onMVWMsgProc_ uMsg:" + uMsg + ",mCanUseSiri:" + AppStateManager.this.mCanUseSiri + ",mIsAvm:" + AppStateManager.this.mIsAvm + ",wParam:" + wParam + ",lParam:" + lParam);
                if (!AppStateManager.this.mIsNativeVrActive) {
                    if (AppStateManager.this.mCanUseSiri && !AppStateManager.this.mIsAvm && AppStateManager.this.mCarPlayRemoteManager != null && uMsg == 40003) {
                        AppStateManager.this.mCarPlayRemoteManager.requestSiri(4, SystemClock.uptimeMillis());
                        return;
                    }
                    return;
                }
                LogUtil.info(AppStateManager.TAG, "onMVWMsgProc_ Native VR is active!");
            }
        };
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        this.mSupportedLanguages.add(this.mCurrentLanguage);
        CarPlayRemoteManager carPlayRemoteManager = CarPlayRemoteManager.getInstance();
        this.mCarPlayRemoteManager = carPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.addCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
        }
        registerVrStartAndEndReceiver(this.mContext);
        registerAvmStatusReceiver(this.mContext);
        registerEcallStateReceiver(this.mContext);
        initHandler();
        prepareCfgFile();
        this.mBtPhoneCallStateListener = new BtPhoneCallStateListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        if (telephonyManager != null) {
            telephonyManager.listen(this.mBtPhoneCallStateListener, 32);
        }
        TelephonyManager telephonyManager2 = this.mTelephonyManager;
        if (telephonyManager2 != null) {
            int callState = telephonyManager2.getCallState();
            LogUtil.info(TAG, "callState :" + callState);
            notifyBtCallState(callState);
            sendCallState();
        }
        CarPlayRemoteManager carPlayRemoteManager2 = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager2 != null) {
            int currentEcallState = carPlayRemoteManager2.getEcallStatus();
            convertCallStatus(currentEcallState);
            sendCallState();
            LogUtil.info(TAG, "init currentEcallState " + currentEcallState);
        }
        try {
            this.audioSocketServer = new LocalServerSocket(SOCKET_NAME);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void uninit() {
        BroadcastReceiver broadcastReceiver;
        BroadcastReceiver broadcastReceiver2;
        BroadcastReceiver broadcastReceiver3;
        LogUtil.info(TAG, "uninit");
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.removeCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
            this.mCarPlayRemoteManager = null;
        }
        this.mIsDumpAudio = false;
        removeCallbacksAndMessages(null);
        Context context = this.mContext;
        if (context != null && (broadcastReceiver3 = this.vrReceiver) != null) {
            context.unregisterReceiver(broadcastReceiver3);
            this.vrReceiver = null;
        }
        Context context2 = this.mContext;
        if (context2 != null && (broadcastReceiver2 = this.avmStatusReceiver) != null) {
            context2.unregisterReceiver(broadcastReceiver2);
            this.avmStatusReceiver = null;
        }
        Context context3 = this.mContext;
        if (context3 != null && (broadcastReceiver = this.mEcallWidgetStateReceiver) != null) {
            context3.unregisterReceiver(broadcastReceiver);
            this.mEcallWidgetStateReceiver = null;
        }
        if (this.mSiriStart) {
            libissmvw.stop(this.mNativeHandle);
            this.mSiriStart = false;
            if (this.mSiriInit) {
                libissmvw.destroy(this.mNativeHandle);
                this.mNativeHandle = null;
                this.mSiriInit = false;
            }
        }
        this.socketRunning = false;
        LocalServerSocket localServerSocket = this.audioSocketServer;
        if (localServerSocket != null) {
            try {
                localServerSocket.close();
                this.audioSocketServer = null;
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void registerVrStartAndEndReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction(ACTION_VR_START);
        filter.addAction(ACTION_VR_END);
        context.registerReceiver(this.vrReceiver, filter);
    }

    private void registerAvmStatusReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction(ModelConstant.ACTION_AVM_STATUS);
        context.registerReceiver(this.avmStatusReceiver, filter);
    }

    private void registerEcallStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ECALL_STATE_CHANGE);
        context.registerReceiver(this.mEcallWidgetStateReceiver, filter);
    }

    private class BtPhoneCallStateListener extends PhoneStateListener {
        public BtPhoneCallStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int state, String inComingNumber) {
            LogUtil.debug(AppStateManager.TAG, "BtPhoneCallStateListener state: " + state + " inComingNumber: " + inComingNumber);
            AppStateManager.this.notifyBtCallState(state);
            AppStateManager.this.sendCallState();
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            super.onCellInfoChanged(cellInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBtCallState(int state) {
        if (state == 0) {
            this.mIsBtPhoneSpeechActive = false;
            this.mIsBtPhoneCallActive = false;
        } else if (state == 1) {
            this.mIsBtPhoneSpeechActive = false;
            this.mIsBtPhoneCallActive = true;
        } else if (state == 2) {
            this.mIsBtPhoneSpeechActive = true;
            this.mIsBtPhoneCallActive = true;
        }
    }

    private void convertCallStatus(int ebCallStates) {
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
                    this.mIsEcallActive = false;
                    break;
            }
        }
        this.mIsEcallActive = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convertCallActivedStatus(int ebCallStates) {
        if (ebCallStates != 10) {
            switch (ebCallStates) {
            }
        }
        this.mIsEcallActive = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAudioSocket() {
        LogUtil.debug(TAG, "startAudioSocket,socketRunning:" + this.socketRunning + " mCanUseSiri:" + this.mCanUseSiri);
        if (!this.socketRunning && this.audioSocketServer != null && this.mIsCarPlaySessionActive) {
            this.socketRunning = true;
            LogUtil.debug(TAG, "create audio thread and start");
            new AudioThread("audio_thread").start();
        }
    }

    private class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        private CarPlayRemoteManagerListener() {
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlaySessionStateChanged(int status) {
            if (status == 2) {
                AppStateManager.this.mIsDumpAudio = SystemProperties.getInt(AppStateManager.DUMP_AUDIO_FLAG_KEY, 0) == 1;
                AppStateManager.this.mIsCarPlaySessionActive = true;
            } else if (status == 3) {
                AppStateManager.this.mIsCarPlaySessionActive = false;
                AppStateManager.this.mCanUseSiri = false;
                AppStateManager.this.socketRunning = false;
                AppStateManager.this.mIsDumpAudio = false;
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onAppStatesChanged(int appState, int entity, int speechMode) {
            LogUtil.debug(AppStateManager.TAG, "onAppStatesChanged appState = " + appState + " entity = " + entity + " speechMode = " + speechMode);
            if (appState == 0) {
                AppStateManager.this.phoneCallManager(entity, speechMode);
            } else if (appState == 2) {
                AppStateManager.this.siriManager(entity);
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onEnhancedSiriParametersChanged(int voiceActivationMode, String voiceModelLanguage) {
            LogUtil.warning(AppStateManager.TAG, "onEnhancedSiriParametersChanged voiceActivationMode:" + voiceActivationMode + " voiceModelLanguage:" + voiceModelLanguage);
            if (voiceActivationMode == 2) {
                AppStateManager.this.mCanUseSiri = false;
            } else {
                AppStateManager.this.mCanUseSiri = true;
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onAuxInUpdateState(int state) {
            LogUtil.warning(AppStateManager.TAG, "onAuxInUpdateState state:" + state);
            if (state == 0) {
                AppStateManager.this.socketRunning = false;
            } else if (state == 1) {
                AppStateManager.this.startAudioSocket();
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlayDisplayStatusChanged(boolean isShow) {
            LogUtil.warning(AppStateManager.TAG, "onCarPlayDisplayStatusChanged isShow:" + isShow);
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onEcallStatusChanged(int status) {
            LogUtil.info(AppStateManager.TAG, "onEcallStatusChanged ecallstatus: " + status);
            AppStateManager.this.convertCallActivedStatus(status);
            AppStateManager.this.sendCallState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void phoneCallManager(int entity, int speechMode) {
        if (entity == 1) {
            this.mIsCpCallActive = true;
            if (this.mIsNativeCallActive) {
                LogUtil.debug(TAG, "CarPlay call interrupt native call.");
                this.mIsNativeCallActive = false;
                return;
            }
            return;
        }
        this.mIsCpCallActive = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void siriManager(int entity) {
        if (entity == 1) {
            sendCpSiriBroadCast(1);
            LogUtil.debug(TAG, "onAppStatesChanged entity = " + entity);
        }
        if (entity == 0 || entity == 2) {
            sendCpSiriBroadCast(0);
            LogUtil.debug(TAG, "onAppStatesChanged entity = " + entity);
        }
    }

    private void sendCpSiriBroadCast(int state) {
        LogUtil.info(TAG, "sendCpSiriBroadCast state =" + state);
        if (this.mContext != null) {
            Intent intent = new Intent();
            if (state == 0) {
                intent.setAction(ACTION_VR_END);
                LogUtil.info(TAG, "sendCpSiriBroadCast CP siri stop");
            } else {
                intent.setAction(ACTION_VR_START);
                intent.putExtra(VR_MIC_KEY, VALUE_TRUE);
                LogUtil.info(TAG, "sendCpSiriBroadCast CP siri start");
            }
            intent.putExtra(VR_USER_KEY, VR_USER_NAME_CARPLAY);
            intent.putExtra(STATE, state);
            this.mContext.sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallState() {
        if (this.mCarPlayRemoteManager == null) {
            return;
        }
        this.mIsNativeCallActive = this.mIsBtPhoneCallActive || this.mIsEcallActive;
        LogUtil.debug(TAG, "currentNativeCallStates mIsNativeCallActive: " + this.mIsNativeCallActive + ", mIsPreNativeCallActive: " + this.mIsPreNativeCallActive + ", mIsEcallActive: " + this.mIsEcallActive + ", mIsBtPhoneCallActive: " + this.mIsBtPhoneCallActive + ", mIsCPCallActive: " + this.mIsCpCallActive + ", mIsBtPhoneSpeechActive: " + this.mIsBtPhoneSpeechActive + ", mIsPreBtPhoneSpeechActive: " + this.mIsPreBtPhoneSpeechActive);
        if (this.mIsPreNativeCallActive != this.mIsNativeCallActive || this.mIsPreBtPhoneSpeechActive != this.mIsBtPhoneSpeechActive) {
            post(new Runnable() { // from class: com.ts.carplay.app.service.model.appstate.-$$Lambda$AppStateManager$87z4wJqPHqIscFIINV-B0fAqZDk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sendCallState$0$AppStateManager();
                }
            });
        }
        this.mIsPreNativeCallActive = this.mIsNativeCallActive;
        this.mIsPreBtPhoneSpeechActive = this.mIsBtPhoneSpeechActive;
    }

    public /* synthetic */ void lambda$sendCallState$0$AppStateManager() {
        if (this.mIsNativeCallActive) {
            if (this.mIsBtPhoneCallActive && !this.mIsBtPhoneSpeechActive) {
                this.mCarPlayRemoteManager.setNativeAppState(0, true, -1);
                return;
            } else {
                this.mCarPlayRemoteManager.setNativeAppState(0, true, 1);
                return;
            }
        }
        this.mCarPlayRemoteManager.setNativeAppState(0, false, -1);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: com.ts.carplay.app.service.model.appstate.AppStateManager.5
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 2) {
                    AppStateManager.this.mNativeHandle = new NativeHandle();
                    libissmvw.setMvwLanguage(100);
                    LogUtil.debug(AppStateManager.TAG, "path:" + AppStateManager.RES_PATH + AppStateManager.SIRI_INIT_PATH);
                    int result = libissmvw.createEx(AppStateManager.this.mNativeHandle, AppStateManager.RES_PATH + AppStateManager.SIRI_INIT_PATH, AppStateManager.this.mMvwListener);
                    LogUtil.debug(AppStateManager.TAG, "create_result:" + result);
                    if (result == 0) {
                        AppStateManager.this.mSiriInit = true;
                        int startResult = libissmvw.start(AppStateManager.this.mNativeHandle, 1);
                        LogUtil.debug(AppStateManager.TAG, "startResult:" + startResult);
                        if (startResult == 0) {
                            AppStateManager.this.mSiriStart = true;
                        }
                    }
                }
            }
        };
    }

    private void prepareCfgFile() {
        RES_PATH = this.mContext.getFilesDir().getAbsolutePath() + "/Res";
        LogUtil.debug(TAG, "RES_PATH:" + RES_PATH);
        if (isFolderExist(RES_PATH)) {
            LogUtil.debug(TAG, "RES_PATH is exist");
            this.mHandler.sendEmptyMessage(2);
        } else {
            LogUtil.debug(TAG, "RES_PATH is not exist");
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.app.service.model.appstate.-$$Lambda$AppStateManager$E56FXximWTxGuV3C9lh1iekwp9M
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$prepareCfgFile$1$AppStateManager();
                }
            });
        }
    }

    public /* synthetic */ void lambda$prepareCfgFile$1$AppStateManager() {
        String rootPath = this.mContext.getFilesDir().getAbsolutePath() + "/";
        LogUtil.debug(TAG, "rootPath:" + rootPath);
        copyAssetsFileToDst(this.mContext, RES_FILE_NAME, rootPath);
        try {
            unZipFile(rootPath + RES_FILE_NAME, rootPath);
            this.mHandler.sendEmptyMessage(2);
        } catch (IOException exception) {
            exception.printStackTrace();
            LogUtil.debug(TAG, "unzip error:" + exception.getMessage());
        }
    }

    private boolean isFolderExist(String folderPath) {
        File file = new File(folderPath);
        return file.exists();
    }

    public class AudioThread extends Thread {
        public AudioThread(String threadName) {
            super(threadName);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LocalSocket receiver = null;
            InputStream in = null;
            try {
                LogUtil.info(AppStateManager.TAG, "socket run");
                while (AppStateManager.this.socketRunning) {
                    receiver = AppStateManager.this.audioSocketServer.accept();
                    byte[] buffer = new byte[1024];
                    byte[] readBuffer = new byte[1024];
                    if (receiver != null) {
                        in = receiver.getInputStream();
                        while (AppStateManager.this.mSiriStart && AppStateManager.this.socketRunning) {
                            int len = in.read(readBuffer);
                            if (len != -1) {
                                if (len != buffer.length) {
                                    buffer = new byte[len];
                                }
                                System.arraycopy(readBuffer, 0, buffer, 0, len);
                                libissmvw.appendAudioDataOld(AppStateManager.this.mNativeHandle, buffer, len);
                                AppStateManager.this.saveAudioData(buffer, len);
                            } else {
                                try {
                                    sleep(10L);
                                } catch (InterruptedException exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    }
                }
                LogUtil.info(AppStateManager.TAG, "socket end");
                if (in != null) {
                    in.close();
                }
                if (receiver != null) {
                    receiver.close();
                }
            } catch (IOException exception2) {
                exception2.printStackTrace();
                LogUtil.info(AppStateManager.TAG, "socket error," + exception2.getMessage());
                AppStateManager.this.socketRunning = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAudioData(byte[] dataToWrite, int size) {
        if (!this.mIsDumpAudio) {
            return;
        }
        try {
            if (this.mRandomFile == null) {
                LogUtil.warning(TAG, "Test: new file");
                RandomAccessFile randomAccessFile = new RandomAccessFile(AUXIN_PATH, "rw");
                this.mRandomFile = randomAccessFile;
                FileChannel channel = randomAccessFile.getChannel();
                this.mFileChannel = channel;
                channel.truncate(0L);
            }
            this.mRandomFile.write(dataToWrite, 0, size);
        } catch (FileNotFoundException exception) {
            LogUtil.warning(TAG, "Test: Save meida File FileNotFoundException:" + exception.getMessage());
        } catch (IOException exception2) {
            LogUtil.warning(TAG, "Test: Save meida File IOException:" + exception2.getMessage());
        } catch (Exception exception3) {
            LogUtil.warning(TAG, "Test: Save meida File Exception:" + exception3.getMessage());
        }
    }

    public void copyAssetsFileToDst(Context context, String fileName, String dstPath) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            try {
                try {
                    is = context.getAssets().open(fileName);
                    fos = new FileOutputStream(dstPath + "/" + fileName);
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int byteCount = is.read(buffer);
                        if (byteCount == -1) {
                            break;
                        } else {
                            fos.write(buffer, 0, byteCount);
                        }
                    }
                    if (is != null) {
                        is.close();
                    }
                    fos.flush();
                    fos.close();
                    LogUtil.info(TAG, "copy file finish - " + fileName);
                } catch (Exception var9) {
                    var9.printStackTrace();
                    if (is != null) {
                        is.close();
                    }
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }
                    LogUtil.info(TAG, "copy file finish - " + fileName);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    is.close();
                } catch (Exception exception2) {
                    exception2.printStackTrace();
                    throw th;
                }
            }
            if (0 != 0) {
                fos.flush();
                fos.close();
            }
            LogUtil.info(TAG, "copy file finish - " + fileName);
            throw th;
        }
    }

    public static void unZipFile(String fileName, String decompressDir) throws IOException {
        ZipFile zf = new ZipFile(fileName, "UTF-8");
        Enumeration enumeration = zf.getEntries();
        while (enumeration.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) enumeration.nextElement();
            String entryName = ze2.getName();
            String path = decompressDir + "/" + entryName;
            if (ze2.isDirectory()) {
                LogUtil.info(TAG, "create folder - " + entryName);
                File decompressDirFile = new File(path);
                if (!decompressDirFile.exists()) {
                    decompressDirFile.mkdirs();
                }
            } else {
                LogUtil.info(TAG, "unzip - " + entryName);
                String fileDir = path.substring(0, path.lastIndexOf("/"));
                File fileDirFile = new File(fileDir);
                if (!fileDirFile.exists()) {
                    fileDirFile.mkdirs();
                }
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(decompressDir + "/" + entryName));
                BufferedInputStream bi = new BufferedInputStream(zf.getInputStream(ze2));
                byte[] readContent = new byte[1024];
                for (int readCount = bi.read(readContent); readCount != -1; readCount = bi.read(readContent)) {
                    bos.write(readContent, 0, readCount);
                }
                bos.close();
            }
        }
        zf.close();
    }
}
