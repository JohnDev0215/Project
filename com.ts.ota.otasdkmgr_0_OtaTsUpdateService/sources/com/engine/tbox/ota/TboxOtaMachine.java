package com.engine.tbox.ota;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.FtpInfo;
import com.engine.tbox.item.FtpUtils;
import com.engine.tbox.item.TBoxConnection;
import com.engine.tbox.state.State;
import com.engine.tbox.state.StateMachine;

/* JADX INFO: loaded from: classes.dex */
public class TboxOtaMachine extends StateMachine {
    private static final int MAX_FTP_RETRY = 3;
    private static final String TAG = "TBoxOtaMachine";
    private static final int UPGRADE_FILE_CHECK_FAILED = 4;
    private static final int UPGRADE_FILE_CHECK_SUCCESS = 5;
    private static final int UPGRADE_FTP_FAILED_RETRY = 2;
    private static final int UPGRADE_FTP_SUCCESS = 3;
    private static final int UPGRADE_PROCESS = 7;
    private static final int UPGRADE_PROCESS_TIMEOUT = 6;
    private static final int UPGRADE_PROGRESS_TIMEOUT_TIME = 120000;
    private static final int UPGRADE_RESET = 10;
    private static final int UPGRADE_START = 1;
    private static final int UPGRADE_STATE = 9;
    private static final int UPGRADE_STATE_TIMEOUT = 8;
    private OtaCallback mCallback;
    private TBoxConnection mConn;
    private OtaFailureState mFailureState;
    private String mFilePath;
    private FtpInfo mFtpInfo;
    private OtaFtpRetryState mFtpRetryState;
    private OtaIdleState mIdleState;
    private OtaPrepareState mPrepareState;
    private OtaProcessState mProcessState;
    private int mReason;
    private OtaSuccessState mSuccessState;
    private int retry;

    public interface OtaCallback {
        public static final int FILE_CHECK_FAILED = 2;
        public static final int FTP_FAILED = 4;
        public static final int TBOX_MESSAGE_TIMEOUT = 1;
        public static final int UPGRADE_FAILED = 3;
        public static final int UPGRADE_REQ_FAILED = 5;

        default void process(int process) {
            LogUtils.d(TboxOtaMachine.TAG, "process:" + process, new Object[0]);
        }

        default void upgradeSuccess() {
            LogUtils.d(TboxOtaMachine.TAG, "upgradeSuccess", new Object[0]);
        }

        default void upgradeFailed(int reason) {
            LogUtils.d(TboxOtaMachine.TAG, "upgradeFailed reason:" + reason, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String msgToString(int msg) {
        switch (msg) {
            case 1:
                return "UPGRADE_START";
            case 2:
                return "UPGRADE_FTP_FAILED_RETRY";
            case 3:
                return "UPGRADE_FTP_SUCCESS";
            case 4:
                return "UPGRADE_FILE_CHECK_FAILED";
            case 5:
                return "UPGRADE_FILE_CHECK_SUCCESS";
            case 6:
                return "UPGRADE_PROCESS_TIMEOUT";
            case 7:
                return "UPGRADE_PROCESS";
            case 8:
                return "UPGRADE_STATE_TIMEOUT";
            case 9:
                return "UPGRADE_STATE";
            case 10:
                return "UPGRADE_RESET";
            default:
                return "UPGRADE_UNKNOWN_MSG";
        }
    }

    public TboxOtaMachine(String name) {
        super(name);
        this.retry = 0;
        this.mFilePath = null;
        this.mFtpInfo = null;
        this.mReason = 0;
        this.mIdleState = new OtaIdleState();
        this.mPrepareState = new OtaPrepareState();
        this.mFtpRetryState = new OtaFtpRetryState();
        this.mProcessState = new OtaProcessState();
        this.mSuccessState = new OtaSuccessState();
        this.mFailureState = new OtaFailureState();
        this.mCallback = null;
        initMachine();
    }

    public TboxOtaMachine(String name, Looper looper) {
        super(name, looper);
        this.retry = 0;
        this.mFilePath = null;
        this.mFtpInfo = null;
        this.mReason = 0;
        this.mIdleState = new OtaIdleState();
        this.mPrepareState = new OtaPrepareState();
        this.mFtpRetryState = new OtaFtpRetryState();
        this.mProcessState = new OtaProcessState();
        this.mSuccessState = new OtaSuccessState();
        this.mFailureState = new OtaFailureState();
        this.mCallback = null;
        initMachine();
    }

    public TboxOtaMachine(String name, Handler handler) {
        super(name, handler);
        this.retry = 0;
        this.mFilePath = null;
        this.mFtpInfo = null;
        this.mReason = 0;
        this.mIdleState = new OtaIdleState();
        this.mPrepareState = new OtaPrepareState();
        this.mFtpRetryState = new OtaFtpRetryState();
        this.mProcessState = new OtaProcessState();
        this.mSuccessState = new OtaSuccessState();
        this.mFailureState = new OtaFailureState();
        this.mCallback = null;
        initMachine();
    }

    private void initMachine() {
        addState(this.mIdleState);
        addState(this.mPrepareState, this.mIdleState);
        addState(this.mFtpRetryState, this.mIdleState);
        addState(this.mProcessState, this.mIdleState);
        addState(this.mSuccessState, this.mIdleState);
        addState(this.mFailureState, this.mIdleState);
        setInitialState(this.mIdleState);
        setDbg(true);
    }

    public boolean startUpgrade(String filePath) {
        LogUtils.w(TAG, "startUpgrade:" + filePath, new Object[0]);
        if (TextUtils.isEmpty(filePath)) {
            LogUtils.e(TAG, "startUpgrade with null path", new Object[0]);
            return false;
        }
        this.mFilePath = filePath;
        sendMessage(1);
        return true;
    }

    public void upgradeFileCheck(boolean result) {
        LogUtils.w(TAG, "upgradeFileCheck:" + result, new Object[0]);
        sendMessage(result ? 5 : 4);
    }

    public void sendUpgradeProcess(int process) {
        LogUtils.w(TAG, "sendUpgradeProcess:" + process, new Object[0]);
        sendMessage(7, process);
    }

    public void sendUpgradeResult(boolean z) {
        LogUtils.w(TAG, "sendUpgradeResult:" + z, new Object[0]);
        sendMessage(9, z ? 1 : 0);
    }

    public void clearRetry() {
        this.retry = 0;
    }

    public boolean addRetryTimes() {
        int i = this.retry + 1;
        this.retry = i;
        return i < 3;
    }

    public void addTboxConnection(TBoxConnection conn) {
        this.mConn = conn;
    }

    public void addOtaCallback(OtaCallback callback) {
        this.mCallback = callback;
    }

    class OtaIdleState extends State {
        OtaIdleState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaIdleState enter", new Object[0]);
            TboxOtaMachine.this.clearRetry();
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaIdleState exit", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.w(TboxOtaMachine.TAG, "OtaIdleState processMessage:" + TboxOtaMachine.msgToString(msg.what), new Object[0]);
            if (msg.what == 1) {
                FtpInfo info = TboxOtaMachine.this.mConn.startUpgradeRequest();
                if (info != null) {
                    TboxOtaMachine.this.mFtpInfo = info;
                    TboxOtaMachine.this.clearRetry();
                    TboxOtaMachine tboxOtaMachine = TboxOtaMachine.this;
                    tboxOtaMachine.transitionTo(tboxOtaMachine.mPrepareState);
                } else if (!TboxOtaMachine.this.addRetryTimes()) {
                    TboxOtaMachine.this.mReason = 5;
                    TboxOtaMachine tboxOtaMachine2 = TboxOtaMachine.this;
                    tboxOtaMachine2.transitionTo(tboxOtaMachine2.mFailureState);
                } else {
                    LogUtils.w(TboxOtaMachine.TAG, "ftp info request failed, retry:" + TboxOtaMachine.this.retry, new Object[0]);
                    TboxOtaMachine.this.sendMessageAtFrontOfQueue(1);
                }
            }
            return true;
        }
    }

    class OtaPrepareState extends State {
        OtaPrepareState() {
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.engine.tbox.ota.TboxOtaMachine$OtaPrepareState$1] */
        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaPrepareState enter", new Object[0]);
            new Thread() { // from class: com.engine.tbox.ota.TboxOtaMachine.OtaPrepareState.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    boolean result = FtpUtils.uploadFile(TboxOtaMachine.this.mFtpInfo.getUrl(), TboxOtaMachine.this.mFilePath, TboxOtaMachine.this.mFtpInfo.getUsername(), TboxOtaMachine.this.mFtpInfo.getPassword(), TboxOtaMachine.this.mFtpInfo.getPort());
                    LogUtils.w(TboxOtaMachine.TAG, "ftp result:" + result, new Object[0]);
                    if (TboxOtaMachine.this.mConn != null) {
                        TboxOtaMachine.this.mConn.sendFtpResult(result);
                    }
                    if (result) {
                        TboxOtaMachine.this.sendMessage(3);
                    } else {
                        TboxOtaMachine.this.sendMessage(2);
                    }
                }
            }.start();
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaPrepareState enter", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.w(TboxOtaMachine.TAG, "OtaPrepareState processMessage:" + TboxOtaMachine.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i != 2) {
                if (i == 3) {
                    TboxOtaMachine tboxOtaMachine = TboxOtaMachine.this;
                    tboxOtaMachine.transitionTo(tboxOtaMachine.mProcessState);
                    return true;
                }
                return true;
            }
            if (!TboxOtaMachine.this.addRetryTimes()) {
                TboxOtaMachine.this.mReason = 4;
                TboxOtaMachine tboxOtaMachine2 = TboxOtaMachine.this;
                tboxOtaMachine2.transitionTo(tboxOtaMachine2.mFailureState);
                return true;
            }
            TboxOtaMachine tboxOtaMachine3 = TboxOtaMachine.this;
            tboxOtaMachine3.transitionTo(tboxOtaMachine3.mFtpRetryState);
            return true;
        }
    }

    class OtaFtpRetryState extends State {
        OtaFtpRetryState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaFtpRetryState enter", new Object[0]);
            TboxOtaMachine.this.sendMessage(1);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaFtpRetryState enter", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.w(TboxOtaMachine.TAG, "OtaFtpRetryState processMessage:" + TboxOtaMachine.msgToString(msg.what), new Object[0]);
            if (msg.what == 1) {
                FtpInfo info = TboxOtaMachine.this.mConn.startUpgradeRequest();
                if (info == null) {
                    TboxOtaMachine.this.mReason = 5;
                    TboxOtaMachine tboxOtaMachine = TboxOtaMachine.this;
                    tboxOtaMachine.transitionTo(tboxOtaMachine.mFailureState);
                } else {
                    TboxOtaMachine.this.mFtpInfo = info;
                    TboxOtaMachine tboxOtaMachine2 = TboxOtaMachine.this;
                    tboxOtaMachine2.transitionTo(tboxOtaMachine2.mPrepareState);
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpgradeProcessTimeout() {
        if (hasMessages(6)) {
            removeMessages(6);
        }
        sendMessageDelayed(6, 120000L);
    }

    class OtaProcessState extends State {
        OtaProcessState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaProcessState enter", new Object[0]);
            TboxOtaMachine.this.sendUpgradeProcessTimeout();
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaProcessState enter", new Object[0]);
            TboxOtaMachine.this.removeMessages(6);
            TboxOtaMachine.this.removeMessages(8);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.w(TboxOtaMachine.TAG, "OtaProcessState processMessage:" + TboxOtaMachine.msgToString(msg.what), new Object[0]);
            switch (msg.what) {
                case 4:
                    if (TboxOtaMachine.this.hasMessages(4)) {
                        TboxOtaMachine.this.mReason = 2;
                        TboxOtaMachine.this.removeMessages(4);
                    } else {
                        TboxOtaMachine.this.mReason = 1;
                    }
                    TboxOtaMachine tboxOtaMachine = TboxOtaMachine.this;
                    tboxOtaMachine.transitionTo(tboxOtaMachine.mFailureState);
                    return true;
                case 5:
                    if (TboxOtaMachine.this.hasMessages(4)) {
                        TboxOtaMachine.this.removeMessages(4);
                    }
                    TboxOtaMachine.this.sendUpgradeProcessTimeout();
                    return true;
                case 6:
                    LogUtils.e(TboxOtaMachine.TAG, "wait for upgrade process timeout", new Object[0]);
                    TboxOtaMachine.this.mReason = 1;
                    TboxOtaMachine tboxOtaMachine2 = TboxOtaMachine.this;
                    tboxOtaMachine2.transitionTo(tboxOtaMachine2.mFailureState);
                    return true;
                case 7:
                    TboxOtaMachine.this.removeMessages(4);
                    if (msg.arg1 < 100) {
                        TboxOtaMachine.this.sendUpgradeProcessTimeout();
                    } else {
                        TboxOtaMachine.this.removeMessages(6);
                        TboxOtaMachine.this.sendMessageDelayed(8, 120000L);
                    }
                    if (TboxOtaMachine.this.mCallback != null) {
                        TboxOtaMachine.this.mCallback.process(msg.arg1);
                    }
                    return true;
                case 8:
                    LogUtils.e(TboxOtaMachine.TAG, "wait for upgrade state timeout", new Object[0]);
                    TboxOtaMachine.this.mReason = 1;
                    TboxOtaMachine tboxOtaMachine3 = TboxOtaMachine.this;
                    tboxOtaMachine3.transitionTo(tboxOtaMachine3.mFailureState);
                    return true;
                case 9:
                    LogUtils.w(TboxOtaMachine.TAG, "UPGRADE_STATE:" + msg.arg1, new Object[0]);
                    TboxOtaMachine.this.removeMessages(8);
                    if (msg.arg1 != 1) {
                        TboxOtaMachine.this.mReason = 3;
                        TboxOtaMachine tboxOtaMachine4 = TboxOtaMachine.this;
                        tboxOtaMachine4.transitionTo(tboxOtaMachine4.mFailureState);
                    } else {
                        TboxOtaMachine tboxOtaMachine5 = TboxOtaMachine.this;
                        tboxOtaMachine5.transitionTo(tboxOtaMachine5.mSuccessState);
                    }
                    return true;
                default:
                    return true;
            }
        }
    }

    class OtaSuccessState extends State {
        OtaSuccessState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaSuccessState enter", new Object[0]);
            if (TboxOtaMachine.this.mCallback != null) {
                TboxOtaMachine.this.mCallback.upgradeSuccess();
            }
            TboxOtaMachine.this.sendMessage(10);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaSuccessState enter", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.w(TboxOtaMachine.TAG, "OtaSuccessState processMessage:" + TboxOtaMachine.msgToString(msg.what), new Object[0]);
            if (msg.what == 10) {
                TboxOtaMachine tboxOtaMachine = TboxOtaMachine.this;
                tboxOtaMachine.transitionTo(tboxOtaMachine.mIdleState);
                return true;
            }
            return true;
        }
    }

    class OtaFailureState extends State {
        OtaFailureState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaFailureState enter", new Object[0]);
            if (TboxOtaMachine.this.mCallback != null) {
                TboxOtaMachine.this.mCallback.upgradeFailed(TboxOtaMachine.this.mReason);
            }
            TboxOtaMachine.this.sendMessage(10);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.w(TboxOtaMachine.TAG, "OtaFailureState enter", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.w(TboxOtaMachine.TAG, "OtaFailureState processMessage:" + TboxOtaMachine.msgToString(msg.what), new Object[0]);
            if (msg.what == 10) {
                TboxOtaMachine tboxOtaMachine = TboxOtaMachine.this;
                tboxOtaMachine.transitionTo(tboxOtaMachine.mIdleState);
                return true;
            }
            return true;
        }
    }
}
