package com.ts.avm.statemachine;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.manager.AvmCommandManagerListener;
import com.ts.avm.manager.AvmStateManagerListener;
import com.ts.avm.manager.SocketManager;
import com.ts.avm.statemachine.state.AvailableState;
import com.ts.avm.statemachine.state.BackgroundState;
import com.ts.avm.statemachine.state.DisableState;
import com.ts.avm.statemachine.state.ErrorState;
import com.ts.avm.statemachine.state.ForegroundState;
import com.ts.avm.statemachine.state.IdleState;
import com.ts.avm.utils.Command;
import com.ts.avm.utils.LogManager;
import com.ts.avm.utils.wrappers.CmdPool;
import com.ts.avm.utils.wrappers.VideoCatCmd;
import com.ts.avm.utils.wrappers.VideocatCmdBuilder;
import com.ts.statemachine.State;
import com.ts.statemachine.StateMachine;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class AvmStateMachine extends StateMachine implements AvailableState.IAvailableState, BackgroundState.IBackgroundState, DisableState.IDisableState, ErrorState.IErrorState, ForegroundState.IForegroundState, IdleState.IIdleState, SocketManager.MessageListener, SocketManager.SocketStateListener {
    private static final int BYTE_ARRAY_INDEX0 = 0;
    private static final int BYTE_ARRAY_INDEX1 = 1;
    private static final int BYTE_ARRAY_INDEX2 = 2;
    private static final int BYTE_ARRAY_INDEX3 = 3;
    private static final int INTEGER_INDEX16 = 16;
    private static final int INTEGER_INDEX24 = 24;
    private static final int INTEGER_INDEX8 = 8;
    private static final int MAX_BYTE = 255;
    private static final int NAVI_ID = 44660419;
    public static final HashMap<String, Integer> NAVI_MAP;
    private static final int NEUSOFT_NAVIGATION_INDEX = 0;
    private static final String NEUSOFT_NAVIGATION_NAME = "com.neusoft.na.navigation";
    private static final int SRC_ID = 9;
    private static final String TAG = AvmStateMachine.class.getSimpleName();
    private static final int TARGET_ID = 7;
    private AvailableState mAvailableState;
    private AvmCommandManagerListener mAvmCommandListener;
    private BackgroundState mBackgroundState;
    private DisableState mDisableState;
    private ErrorState mErrorState;
    private AvmCommandManagerListener mExtraAvmCommandListener;
    private ForegroundState mForegroundState;
    private HandlerThread mHandlerMessageThread;
    private IdleState mIdleState;
    private Handler mReceiveMessageHandler;
    private SocketManager mSocketManager;
    private AvmStateManagerListener mStatusChangeListener;

    static {
        HashMap<String, Integer> map = new HashMap<>();
        NAVI_MAP = map;
        map.put(NEUSOFT_NAVIGATION_NAME, 0);
    }

    public AvmStateMachine() {
        this(AvmStateMachine.class.getSimpleName());
        LogManager.logd(TAG, "AvmStateMachine: ", new Throwable());
        HandlerThread handlerThread = new HandlerThread("mReceiveMessageThread");
        this.mHandlerMessageThread = handlerThread;
        handlerThread.start();
        this.mReceiveMessageHandler = new Handler(this.mHandlerMessageThread.getLooper());
    }

    protected AvmStateMachine(String name) {
        super(name);
        init();
    }

    protected AvmStateMachine(String name, Looper looper) {
        super(name, looper);
        init();
    }

    protected AvmStateMachine(String name, Handler handler) {
        super(name, handler);
        init();
    }

    private void init() {
        this.mSocketManager = SocketManager.getInstance(SocketManager.TYPE_TCP);
        SocketManager.Config config = new SocketManager.Config();
        config.ip = "192.168.118.2";
        config.port = 60100;
        this.mSocketManager.socketConfig(config);
        this.mSocketManager.registerMessageListener(this);
        this.mSocketManager.registerSocketListener(this);
        this.mAvailableState = new AvailableState(this);
        this.mBackgroundState = new BackgroundState(this);
        this.mDisableState = new DisableState(this);
        this.mErrorState = new ErrorState(this);
        this.mForegroundState = new ForegroundState(this);
        IdleState idleState = new IdleState(this);
        this.mIdleState = idleState;
        addState(idleState);
        addState(this.mAvailableState);
        addState(this.mForegroundState, this.mAvailableState);
        addState(this.mBackgroundState, this.mAvailableState);
        addState(this.mErrorState);
        addState(this.mDisableState);
        setInitialState(this.mIdleState);
    }

    public void initContext(Context context) {
    }

    @Override // com.ts.avm.statemachine.state.AvailableState.IAvailableState
    public void onError(int code, String msg) {
        LogManager.logd(TAG, "onError: ");
    }

    @Override // com.ts.avm.statemachine.state.AvailableState.IAvailableState
    public void onDisable() {
        LogManager.logd(TAG, "onDisable: ");
    }

    @Override // com.ts.avm.statemachine.state.AvailableState.IAvailableState
    public void onCommandTransceiver(CmdBean bean) {
        LogManager.logw(TAG, "videocat cmd send:  id: " + bean.getId() + " src_id: " + bean.getSrcId() + " target_id: " + bean.getTargetId() + " value0: " + bean.getValue()[0] + " value1: " + bean.getValue()[1] + " value2: " + bean.getValue()[2] + " value3: " + bean.getValue()[3]);
        VideoCatCmd cmd = new VideocatCmdBuilder().setId(bean.getId()).setSrcId(bean.getSrcId()).setTargetId(bean.getTargetId()).setValue(bean.getValue()).build();
        this.mSocketManager.sendMessage(cmd.getData());
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public void transferTo(State state) {
        transitionTo(state);
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public void deliverMessage(Message message) {
        deferMessage(message);
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public State getIdleState() {
        return this.mIdleState;
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public State getAvailableState() {
        return this.mAvailableState;
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public State getForegroundState() {
        return this.mForegroundState;
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public State getBackgroundState() {
        return this.mBackgroundState;
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public State getDisabledState() {
        return this.mDisableState;
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public State getErrorState() {
        return this.mErrorState;
    }

    @Override // com.ts.avm.statemachine.IAvmStateListener
    public void onStatusChange(int code, String message) {
        AvmStateManagerListener avmStateManagerListener = this.mStatusChangeListener;
        if (avmStateManagerListener != null) {
            avmStateManagerListener.onAvmStatusChanged(code, message);
        } else {
            LogManager.loge(TAG, "mStatusChangeListener == NULL");
        }
    }

    @Override // com.ts.avm.statemachine.state.AvailableState.IAvailableState
    public void onGoBackGround() {
        LogManager.logd(TAG, "onGoBackGround: ");
        if (getCurrentState().equals(getForegroundState())) {
            this.mSocketManager.sendMessage(CmdPool.getCmdMap().get(Command.STOP_AVM).getData());
        } else {
            LogManager.loge(TAG, "HAS BEEN in BACKGROUND mode");
        }
    }

    @Override // com.ts.avm.statemachine.state.AvailableState.IAvailableState
    public void onGoForeGround() {
        LogManager.logd(TAG, "onGoForeGround: ");
        if (getCurrentState().equals(getBackgroundState())) {
            this.mSocketManager.sendMessage(CmdPool.getCmdMap().get(Command.START_AVM).getData());
        } else {
            LogManager.loge(TAG, "HAS BEEN in FOREGROUND mode");
        }
    }

    @Override // com.ts.avm.statemachine.state.DisableState.IDisableState
    public void onDisableRecovery() {
        LogManager.logd(TAG, "onDisableRecovery: ");
    }

    @Override // com.ts.avm.statemachine.state.ErrorState.IErrorState
    public void onErrorRecovery() {
        LogManager.logd(TAG, "onErrorRecovery: ");
    }

    @Override // com.ts.avm.statemachine.state.IdleState.IIdleState
    public void qnxServiceConnect() {
        LogManager.logd(TAG, "qnxServiceConnect: ");
        this.mSocketManager.initSocket();
    }

    @Override // com.ts.avm.statemachine.state.IdleState.IIdleState
    public void qnxServiceDisconnect() {
        LogManager.logd(TAG, "qnxServiceDisconnect: ");
    }

    @Override // com.ts.avm.manager.SocketManager.MessageListener
    public void onReceiveMessage(byte[] dataArr) {
        VideoCatCmd cmd = CmdPool.parseCommand(dataArr);
        LogManager.logw(TAG, "videocat cmd received:  id: " + cmd.id + " src_id: " + cmd.srcId + " target_id: " + cmd.targetId + " value0: " + cmd.value[0] + " value1: " + cmd.value[1] + " value2: " + cmd.value[2] + " value3: " + cmd.value[3]);
        if (cmd.id == Integer.MIN_VALUE) {
            if (cmd.value[1] < 0) {
                LogManager.loge(TAG, "AVM RECEIVE ERROR: " + cmd.value[1]);
            }
            int i = cmd.value[0];
            if (i == 16777216) {
                cmdResultFeedback(cmd);
            } else if (i == 33554432) {
                LogManager.logd(TAG, "Event.AVM_COMMAND_CODE ", new Throwable());
                if (cmd.value[1] == 0) {
                    sendMessage(33554432, cmd);
                } else if (cmd.value[1] == 1) {
                    LogManager.loge(TAG, "cmd AVM_COMMAND_CODE failed.");
                }
            } else if (i == 33554448) {
                LogManager.logd(TAG, "Event.AVM_THEME_CODE ", new Throwable());
                cmdResultFeedback(cmd);
            } else if (i == 33554688) {
                LogManager.logd(TAG, "Event.AVM_ORIENTATION_CODE ", new Throwable());
                cmdResultFeedback(cmd);
            } else if (i == 67108864) {
                LogManager.logd(TAG, "Event.AVM_CALIBRATION_CODE ", new Throwable());
                if (cmd.value[1] == 0) {
                    sendMessage(Event.AVM_CALIBRATION_CODE, cmd);
                } else if (cmd.value[1] == 1) {
                    LogManager.loge(TAG, "cmd AVM_CALIBRATION_CODE failed.");
                }
            }
        }
        switch (cmd.id) {
            case Event.SWITCH_RADAR_ALARM_TONE /* 129 */:
            case Event.AVM_VIEW_MODE_CHANGE_CODE /* 229376 */:
            case Event.AVM_STATE_CHANGE_CODE /* 4194304 */:
            case Event.AVM_DOCKER_CODE /* 67108992 */:
            case Event.PULL_AVM_PICTURE_SID /* 67109999 */:
            case Event.GET_NAVI_STATE /* 77108765 */:
                cmdResultFeedback(cmd);
                break;
        }
    }

    @Override // com.ts.avm.manager.SocketManager.MessageListener
    public void onAckConfirmed() {
        LogManager.logi(TAG, "onAckConfirmed");
        this.mSocketManager.sendMessage(CmdPool.getCmdMap().get(Command.GET_VIEW_MODE).getData());
        getNaviStateForQnx();
        getProvisonFinishedState();
    }

    private void getProvisonFinishedState() {
        LogManager.logd(TAG, "getProvisonFinishedState");
        CmdBean cmdBean = new CmdBean();
        cmdBean.setId(Event.GET_PROVISION_FINISHED_STATE);
        AvmCommandManagerListener avmCommandManagerListener = this.mAvmCommandListener;
        if (avmCommandManagerListener != null) {
            avmCommandManagerListener.onCommandBeanCallback(cmdBean);
        }
    }

    private void getNaviStateForQnx() {
        LogManager.logd(TAG, "getNaviStateForQnx");
        CmdBean cmdBean = new CmdBean();
        cmdBean.setId(Event.GET_NAVI_STATE);
        AvmCommandManagerListener avmCommandManagerListener = this.mExtraAvmCommandListener;
        if (avmCommandManagerListener != null) {
            avmCommandManagerListener.onCommandBeanCallback(cmdBean);
        }
    }

    private void cmdResultFeedback(final VideoCatCmd cmd) {
        this.mReceiveMessageHandler.post(new Runnable() { // from class: com.ts.avm.statemachine.AvmStateMachine.1
            @Override // java.lang.Runnable
            public void run() {
                if (AvmStateMachine.this.mAvmCommandListener != null) {
                    CmdBean bean = VideoCatCmd.CmdWrapper(cmd);
                    AvmStateMachine.this.mAvmCommandListener.onCommandBeanCallback(bean);
                    if (AvmStateMachine.this.mExtraAvmCommandListener != null) {
                        AvmStateMachine.this.mExtraAvmCommandListener.onCommandBeanCallback(bean);
                    }
                }
            }
        });
    }

    @Override // com.ts.avm.manager.SocketManager.SocketStateListener
    public void onServerConnected() {
        LogManager.logd(TAG, "onServerConnected: ");
        this.mSocketManager.sendMessage(CmdPool.getRegister());
        if (getCurrentState().equals(getIdleState())) {
            LogManager.logd(TAG, "onServerConnected do IdleState change to availableState");
            sendMessage(10);
        } else {
            transferTo(getAvailableState());
        }
    }

    @Override // com.ts.avm.manager.SocketManager.SocketStateListener
    public void onServerDisconnect() {
        LogManager.logd(TAG, "onServerDisconnect: ");
        if (getCurrentState().equals(getAvailableState())) {
            sendMessage(4);
        }
    }

    @Override // com.ts.avm.manager.SocketManager.SocketStateListener
    public void onError() {
        LogManager.loge(TAG, "onError: ");
        transferTo(getErrorState());
        new Handler().postDelayed(new Runnable() { // from class: com.ts.avm.statemachine.AvmStateMachine.2
            @Override // java.lang.Runnable
            public void run() {
                AvmStateMachine.this.mSocketManager.initSocket();
            }
        }, 10000L);
    }

    public void setStatusChangeListener(AvmStateManagerListener mServiceListenerManager) {
        LogManager.logd(TAG, "setStatusChangeListener");
        this.mStatusChangeListener = mServiceListenerManager;
    }

    public void setCommandListener(AvmCommandManagerListener listener) {
        this.mAvmCommandListener = listener;
    }

    public void setExtraCommandListener(AvmCommandManagerListener listener) {
        this.mExtraAvmCommandListener = listener;
    }

    public void sendNaviState2Qnx(int mapType, int naviState, int isForeground, String mapSource) {
        LogManager.logi(TAG, "sendNaviState2Qnx: mapType:" + mapType + "naviState :" + naviState + "isForeground :" + isForeground + " mapSource :" + mapSource);
        Integer mapIndex = NAVI_MAP.get(mapSource);
        if (mapIndex != null) {
            int mapIndexValue = mapIndex.intValue();
            VideoCatCmd cmd = new VideocatCmdBuilder().setId(NAVI_ID).setSrcId(9).setTargetId(7).setValue(new int[]{mapType, naviState, isForeground, mapIndexValue}).build();
            this.mSocketManager.sendMessage(cmd.getData());
        }
    }

    private byte[] string2ByteArray(String str) {
        if (str == null) {
            str = "";
        }
        try {
            byte[] dataStr = str.getBytes("UTF-8");
            byte[] dataDevider = "\u0000".getBytes("UTF-8");
            byte[] dataStr2 = addBytes(dataStr, dataDevider);
            LogManager.logd(TAG, "string2ByteArray: remakeStrLength:" + dataStr2.length);
            return dataStr2;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] intToByteArray(int data) {
        byte[] array = {(byte) (data & MAX_BYTE), (byte) ((data >> 8) & MAX_BYTE), (byte) ((data >> INTEGER_INDEX16) & MAX_BYTE), (byte) ((data >> INTEGER_INDEX24) & MAX_BYTE)};
        return array;
    }

    public byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] dateResult = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, dateResult, 0, data1.length);
        System.arraycopy(data2, 0, dateResult, data1.length, data2.length);
        return dateResult;
    }
}
