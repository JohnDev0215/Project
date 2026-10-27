package com.ts.carplay.lib.resourcemanager;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CarPlayResourceStateChange;
import com.ts.carplay.lib.common.CarPlayStartInfo;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.lib.ts.CarPlayStackCmdHandler;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class AudioResourceManager {
    private static final int AUDIO_CHANGED_TO_CARPLAY = 6;
    private static final int AUDIO_CHANGED_TO_HU = 5;
    private static final int AUDIO_FOCUS_GAIN = 1;
    private static final int AUDIO_FOCUS_GAIN_TRANSIENT = 2;
    private static final int AUDIO_FOCUS_GAIN_TRANSIENT_CAN_DUCK = 3;
    private static final int AUDIO_FOCUS_LOSS = -1;
    private static final int AUDIO_FOCUS_LOSS_TRANSIENT = -2;
    private static final int AUDIO_FOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;
    private static final int AUDIO_TYPE_CARPLAY = 4;
    private static final int AUDIO_TYPE_HU_HIGHEST_PRIORITY = 3;
    private static final int AUDIO_TYPE_HU_HIGH_PRIORITY = 2;
    private static final int AUDIO_TYPE_HU_NORMAL_PRIORITY = 1;
    private static final int AUDIO_TYPE_UNKNOWN = 0;
    private static final int CARPLAY_APP_STARTED = 22;
    private static final int CARPLAY_APP_STOPPED = 23;
    private static final int CARPLAY_AUDIO_RQEUST = 8;
    private static final int CARPLAY_AUDIO_START = 4;
    private static final int CARPLAY_CONNECTED = 10;
    private static final int CARPLAY_DISCONNECTED = 11;
    private static final int CP_APP_STATE_NONE = 0;
    private static final int CP_APP_STATE_PHONE = 2;
    private static final int CP_APP_STATE_SIRI = 1;
    private static final int CP_FACETIME = 3;
    private static final int CP_LASTAUDIO_INITIAL = -2;
    private static final int CP_SIRI = 4;
    private static final int CP_TELPHONE = 2;
    private static final int CP_TELPHONE_8K = 1;
    private static final int ENTITY_WAIT_TIME = 2000;
    private static final int HU_CARPLAY_AUDIO_START = 4;
    private static final int HU_CARPLAY_PHONE_START = 6;
    private static final int HU_CARPLAY_SIRI_START = 5;
    private static final int HU_HIGHEST_AUDIO_START = 3;
    private static final int HU_HIGH_AUDIO_START = 2;
    private static final int HU_HIGH_AUDIO_STOP = 12;
    private static final int HU_NORMAL_AUDIO_START = 1;
    private static final int PLAY_CARPLAY_MUSIC = 7;
    private static final String TAG = "CarPlay AudioResourceManager";
    private CarPlayManager mCarPlayManager;
    private HandlerThread mResourceManagerThread;
    private AudioStateMachine mStateMachine;
    private int mTransferInStartInfo = 0;
    private String mBorrowId = "";
    private String mReqBorrowId = "";
    private List<String> mBorrowUserList = new ArrayList();
    private List<String> mBorrowNeverList = new ArrayList();
    private int mLastAudioPriority = -2;
    private boolean mIsNeedSendUnborrow = false;
    private int mCurrentCpFocus = -1;
    private int mCurrentAlternateAudio = -1;
    private int mCurrentAuxOutAudio = -1;
    private int mDuckDurationMs = 0;
    private IState mStartInfoState = null;
    private Timer mEntityTimer = new Timer();
    private TimerTask mEntityTimerTask = null;
    private boolean mCarPlayConnected = false;

    public AudioResourceManager(CarPlayManager manager) {
        this.mCarPlayManager = manager;
        HandlerThread handlerThread = new HandlerThread("AudioResourceManager");
        this.mResourceManagerThread = handlerThread;
        handlerThread.start();
        AudioStateMachine audioStateMachine = new AudioStateMachine(TAG, this.mResourceManagerThread.getLooper());
        this.mStateMachine = audioStateMachine;
        audioStateMachine.start();
    }

    public void audioEntityChanged(int audioEntity, int perAudioEntity) {
        LogUtil.info(TAG, "audioResourceChanged entity:" + audioEntity + " curState:" + this.mStateMachine.getCurState().getName() + " curEntity:" + this.mStateMachine.mAudioEntity);
        if (audioEntity == 2 && this.mStateMachine.mAudioEntity == 1) {
            this.mStateMachine.mAudioEntity = audioEntity;
            stopEntityTimer();
            int i = this.mCurrentCpFocus;
            if (i != -1) {
                abandonAudioFocus(i);
            }
        }
        this.mStateMachine.mAudioEntity = audioEntity;
        this.mStateMachine.mPerAudioEntity = perAudioEntity;
    }

    public void duckAudio(int durationMs, int volume) {
        if (durationMs <= 0) {
            LogUtil.warning(TAG, "duckAudio, invalid durationMs:" + durationMs);
        } else {
            this.mDuckDurationMs = durationMs;
        }
        CarPlayManager carPlayManager = this.mCarPlayManager;
        if (carPlayManager != null) {
            carPlayManager.duckAudio(durationMs, volume);
            if (this.mCurrentAlternateAudio == 1) {
                this.mCurrentAlternateAudio = 4;
                requestAudioFocusDirectly(4);
                LogUtil.warning(TAG, "duckAudio, AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK to DUCK");
            }
        }
    }

    public void unduckAudio(int durationMs) {
        this.mDuckDurationMs = 0;
        CarPlayManager carPlayManager = this.mCarPlayManager;
        if (carPlayManager != null) {
            carPlayManager.unduckAudio(durationMs);
            int i = this.mCurrentAlternateAudio;
            if (i == 4) {
                abandonAudioFocus(i);
                this.mCurrentAlternateAudio = 1;
                LogUtil.warning(TAG, "unduckAudio, AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK to UNDUCK");
            }
        }
    }

    public void accessoryAudioChange(int priority, String borrowId) {
        LogUtil.info(TAG, "accessoryAudioChange, priority = " + priority + "borrowId" + borrowId);
        if (borrowId != null && !borrowId.isEmpty()) {
            setReqBorrowId(borrowId);
        }
        if (priority == 4) {
            LogUtil.debug(TAG, "accessoryAudioChange, carplay navi not in statemachine");
            return;
        }
        if (priority == 7) {
            LogUtil.debug(TAG, "accessoryAudioChange, carplay auxout not in statemachine");
            return;
        }
        if (priority == 1 && this.mCurrentCpFocus == -1) {
            CarPlayLastUserMode carPlayLastUserMode = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource = CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode.setLastUserMode(carPlayResource, 1);
        } else if (priority == 0) {
            CarPlayLastUserMode carPlayLastUserMode2 = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource2 = CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode2.setLastUserMode(carPlayResource2, 0);
        }
        if (priority != 0) {
            if (priority != 1) {
                if (priority == 2) {
                    this.mStateMachine.sendMessage(5);
                    return;
                }
                if (priority == 3) {
                    this.mStateMachine.sendMessage(6);
                    return;
                }
                if (priority == 5) {
                    this.mStateMachine.sendMessage(2);
                    return;
                }
                if (priority == 6) {
                    this.mStateMachine.sendMessage(3);
                    return;
                } else if (priority == 8) {
                    this.mStateMachine.sendMessage(6);
                    return;
                } else if (priority != 9) {
                    return;
                }
            }
            this.mStateMachine.sendMessage(4);
            return;
        }
        this.mStateMachine.sendMessage(1);
    }

    private class AudioStateMachine extends StateMachine {
        public int mAudioEntity;
        private IState mCurState;
        private IdleState mIdleState;
        private InBorrowNever mInBorrowNever;
        private InBorrowUser mInBorrowUser;
        private InCarPlayAudio mInCarPlayAudio;
        private InTake mInTake;
        public int mPerAudioEntity;
        private Message mPreMessage;
        private IState mPreState;

        AudioStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mInCarPlayAudio = new InCarPlayAudio();
            this.mInTake = new InTake();
            this.mInBorrowUser = new InBorrowUser();
            this.mInBorrowNever = new InBorrowNever();
            this.mAudioEntity = 0;
            this.mPerAudioEntity = 0;
            IdleState idleState = this.mIdleState;
            this.mCurState = idleState;
            addState(idleState);
            addState(this.mInCarPlayAudio);
            addState(this.mInTake);
            addState(this.mInBorrowUser);
            addState(this.mInBorrowNever);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mCurState = audioStateMachine.mIdleState;
                LogUtil.info(AudioResourceManager.TAG, "IdleState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                switch (message.what) {
                    case 1:
                        AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                        AudioStateMachine audioStateMachine = AudioStateMachine.this;
                        audioStateMachine.transitionTo(audioStateMachine.mInTake);
                        break;
                    case 2:
                        AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                        AudioResourceManager.this.addBorrowUserId(AudioResourceManager.this.getBorrowId());
                        AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, AudioResourceManager.this.getBorrowId());
                        AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                        audioStateMachine2.transitionTo(audioStateMachine2.mInBorrowUser);
                        break;
                    case 3:
                        AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                        AudioResourceManager.this.addBorrowNeverId(AudioResourceManager.this.getBorrowId());
                        AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, AudioResourceManager.this.getBorrowId());
                        AudioStateMachine audioStateMachine3 = AudioStateMachine.this;
                        audioStateMachine3.transitionTo(audioStateMachine3.mInBorrowNever);
                        break;
                    case 4:
                    case 5:
                    case 6:
                        AudioStateMachine audioStateMachine4 = AudioStateMachine.this;
                        audioStateMachine4.transitionTo(audioStateMachine4.mInCarPlayAudio);
                        break;
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mPreMessage = audioStateMachine.getCurrentMessage();
                AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                audioStateMachine2.mPreState = audioStateMachine2.getCurState();
            }
        }

        class InCarPlayAudio extends State {
            InCarPlayAudio() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mCurState = audioStateMachine.mInCarPlayAudio;
                LogUtil.info(AudioResourceManager.TAG, "InCarPlayAudio enter");
                AudioResourceManager.this.clearBorrowNeverListNoCmd();
                AudioResourceManager.this.clearBorrowUserListNoCmd();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (AudioResourceManager.this.mStateMachine.mAudioEntity == 1) {
                            AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                        }
                        AudioStateMachine audioStateMachine = AudioStateMachine.this;
                        audioStateMachine.transitionTo(audioStateMachine.mInTake);
                        break;
                    case 2:
                        if (AudioResourceManager.this.mStateMachine.mAudioEntity == 1) {
                            AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                            AudioResourceManager.this.addBorrowUserId(AudioResourceManager.this.getBorrowId());
                            AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, AudioResourceManager.this.getBorrowId());
                        }
                        AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                        audioStateMachine2.transitionTo(audioStateMachine2.mInBorrowUser);
                        break;
                    case 3:
                        AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                        AudioResourceManager.this.addBorrowNeverId(AudioResourceManager.this.getBorrowId());
                        AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, AudioResourceManager.this.getBorrowId());
                        AudioStateMachine audioStateMachine3 = AudioStateMachine.this;
                        audioStateMachine3.transitionTo(audioStateMachine3.mInBorrowNever);
                        break;
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mPreMessage = audioStateMachine.getCurrentMessage();
                AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                audioStateMachine2.mPreState = audioStateMachine2.getCurState();
            }
        }

        class InTake extends State {
            InTake() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mCurState = audioStateMachine.mInTake;
                LogUtil.info(AudioResourceManager.TAG, "InTake enter");
                AudioResourceManager.this.clearBorrowNeverListNoCmd();
                AudioResourceManager.this.clearBorrowUserListNoCmd();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                    AudioResourceManager.this.addBorrowUserId(AudioResourceManager.this.getBorrowId());
                    AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, AudioResourceManager.this.getBorrowId());
                    AudioStateMachine audioStateMachine = AudioStateMachine.this;
                    audioStateMachine.transitionTo(audioStateMachine.mInBorrowUser);
                    return true;
                }
                if (i == 3) {
                    AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                    AudioResourceManager.this.addBorrowNeverId(AudioResourceManager.this.getBorrowId());
                    AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, AudioResourceManager.this.getBorrowId());
                    AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                    audioStateMachine2.transitionTo(audioStateMachine2.mInBorrowNever);
                    return true;
                }
                if (i == 4 || i == 5 || i == 6) {
                    AudioStateMachine audioStateMachine3 = AudioStateMachine.this;
                    audioStateMachine3.transitionTo(audioStateMachine3.mInCarPlayAudio);
                    return true;
                }
                return false;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mPreMessage = audioStateMachine.getCurrentMessage();
                AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                audioStateMachine2.mPreState = audioStateMachine2.getCurState();
            }
        }

        class InBorrowUser extends State {
            InBorrowUser() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mCurState = audioStateMachine.mInBorrowUser;
                LogUtil.info(AudioResourceManager.TAG, "InBorrowUser enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (AudioResourceManager.this.mStartInfoState != AudioResourceManager.this.mStateMachine.mInBorrowUser) {
                            if (AudioResourceManager.this.mStateMachine.getPreState() == AudioResourceManager.this.mStateMachine.mInTake) {
                                AudioResourceManager.this.clearBorrowUserList();
                            } else {
                                AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                            }
                            AudioStateMachine audioStateMachine = AudioStateMachine.this;
                            audioStateMachine.transitionTo(audioStateMachine.mInTake);
                        } else {
                            int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO);
                            CarPlayLastUserMode.getInstance();
                            if (lastUserModeState == 1) {
                                AudioResourceManager.this.mStateMachine.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, AudioResourceManager.this.getBorrowId());
                            } else {
                                AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                            }
                            AudioResourceManager.this.mStartInfoState = null;
                            AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                            audioStateMachine2.transitionTo(audioStateMachine2.mInTake);
                        }
                        break;
                    case 2:
                        if (!AudioResourceManager.this.isInBorrowUserList(AudioResourceManager.this.getReqBorrowId())) {
                            AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                            AudioResourceManager.this.addBorrowUserId(AudioResourceManager.this.getBorrowId());
                            AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, AudioResourceManager.this.getBorrowId());
                        } else if (!AudioResourceManager.this.getReqBorrowId().equals(AudioResourceManager.this.getBorrowId())) {
                            AudioStateMachine.this.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, AudioResourceManager.this.getBorrowId());
                            AudioResourceManager.this.removeBorrowUserId(AudioResourceManager.this.getBorrowId());
                            AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                        }
                        break;
                    case 3:
                        AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                        AudioResourceManager.this.addBorrowNeverId(AudioResourceManager.this.getBorrowId());
                        AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, AudioResourceManager.this.getBorrowId());
                        AudioStateMachine audioStateMachine3 = AudioStateMachine.this;
                        audioStateMachine3.transitionTo(audioStateMachine3.mInBorrowNever);
                        break;
                    case 4:
                        if (AudioResourceManager.this.mStateMachine.mAudioEntity != 1) {
                            AudioResourceManager.this.clearBorrowUserList();
                        }
                        AudioStateMachine audioStateMachine4 = AudioStateMachine.this;
                        audioStateMachine4.transitionTo(audioStateMachine4.mInCarPlayAudio);
                        break;
                    case 5:
                        AudioStateMachine audioStateMachine5 = AudioStateMachine.this;
                        audioStateMachine5.transitionTo(audioStateMachine5.mInCarPlayAudio);
                        break;
                    case 6:
                        AudioStateMachine audioStateMachine6 = AudioStateMachine.this;
                        audioStateMachine6.transitionTo(audioStateMachine6.mInCarPlayAudio);
                        break;
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mPreMessage = audioStateMachine.getCurrentMessage();
                AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                audioStateMachine2.mPreState = audioStateMachine2.getCurState();
            }
        }

        class InBorrowNever extends State {
            InBorrowNever() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mCurState = audioStateMachine.mInBorrowNever;
                LogUtil.info(AudioResourceManager.TAG, "InBorrowNever enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (AudioResourceManager.this.mStartInfoState != AudioResourceManager.this.mStateMachine.mInBorrowNever) {
                            if (AudioResourceManager.this.mStateMachine.getPreState() == AudioResourceManager.this.mStateMachine.mInTake) {
                                AudioResourceManager.this.clearBorrowNeverList();
                                AudioResourceManager.this.clearBorrowUserList();
                            } else {
                                AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                            }
                            AudioStateMachine audioStateMachine = AudioStateMachine.this;
                            audioStateMachine.transitionTo(audioStateMachine.mInTake);
                        } else {
                            int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO);
                            CarPlayLastUserMode.getInstance();
                            if (lastUserModeState == 1) {
                                AudioResourceManager.this.mStateMachine.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, AudioResourceManager.this.getBorrowId());
                            } else {
                                AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                            }
                            AudioResourceManager.this.mStartInfoState = null;
                            AudioStateMachine audioStateMachine2 = AudioStateMachine.this;
                            audioStateMachine2.transitionTo(audioStateMachine2.mInTake);
                        }
                        break;
                    case 2:
                        AudioResourceManager.this.clearBorrowNeverList();
                        AudioStateMachine audioStateMachine3 = AudioStateMachine.this;
                        audioStateMachine3.transitionTo(audioStateMachine3.mInBorrowUser);
                        break;
                    case 3:
                        if (!AudioResourceManager.this.isInBorrowNeverList(AudioResourceManager.this.getReqBorrowId())) {
                            AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                            AudioResourceManager.this.addBorrowNeverId(AudioResourceManager.this.getBorrowId());
                            AudioStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, AudioResourceManager.this.getBorrowId());
                        } else if (!AudioResourceManager.this.getReqBorrowId().equals(AudioResourceManager.this.getBorrowId())) {
                            AudioStateMachine.this.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, AudioResourceManager.this.getBorrowId());
                            AudioResourceManager.this.removeBorrowNeverId(AudioResourceManager.this.getBorrowId());
                            AudioResourceManager.this.setBorrowId(AudioResourceManager.this.getReqBorrowId());
                        }
                        break;
                    case 4:
                    case 5:
                    case 6:
                        AudioResourceManager.this.clearBorrowNeverList();
                        AudioResourceManager.this.clearBorrowUserList();
                        AudioStateMachine audioStateMachine4 = AudioStateMachine.this;
                        audioStateMachine4.transitionTo(audioStateMachine4.mInCarPlayAudio);
                        break;
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                AudioStateMachine audioStateMachine = AudioStateMachine.this;
                audioStateMachine.mPreState = audioStateMachine.getCurState();
            }
        }

        public void acquireResource(CpConst.CarPlayTransfer transfer, CpConst.CarPlayConstraint constraint, String borrowId) {
            if (!AudioResourceManager.this.mCarPlayConnected) {
                LogUtil.info(AudioResourceManager.TAG, "acquireResource carplay not connected");
                return;
            }
            LogUtil.info(AudioResourceManager.TAG, "acquireResource transfer:" + transfer.ordinal() + " constraint:" + constraint.ordinal());
            CarPlayResourceStateChange stateChange = new CarPlayResourceStateChange();
            stateChange.transferPriority = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER;
            if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE) {
                stateChange.takeConstraint = constraint;
                stateChange.borrowOrUnborrowConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME;
            } else {
                stateChange.takeConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID;
                stateChange.borrowOrUnborrowConstraint = constraint;
                stateChange.borrowId = borrowId;
            }
            CarPlayStackCmdHandler.getInstance().acquireResource(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO.ordinal(), transfer, stateChange);
        }

        public void releaseResource(CpConst.CarPlayTransfer transfer, String borrowId) {
            if (!AudioResourceManager.this.mCarPlayConnected) {
                LogUtil.info(AudioResourceManager.TAG, "releaseResource carplay not connected");
                if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW) {
                    AudioResourceManager.this.mIsNeedSendUnborrow = true;
                    return;
                }
                return;
            }
            LogUtil.info(AudioResourceManager.TAG, "releaseResource transfer:" + transfer.ordinal());
            if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW) {
                CarPlayResourceStateChange stateChange = new CarPlayResourceStateChange();
                stateChange.transferPriority = CpConst.CarPlayPriority.CARPLAY_PRIORITY_INVALID;
                stateChange.takeConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID;
                stateChange.borrowOrUnborrowConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID;
                stateChange.borrowId = borrowId;
                CarPlayStackCmdHandler.getInstance().acquireResource(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO.ordinal(), transfer, stateChange);
                return;
            }
            CarPlayStackCmdHandler.getInstance().releaseResource(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO.ordinal(), transfer.ordinal());
        }

        public void audioPlay() {
            LogUtil.info(AudioResourceManager.TAG, "audioPlay");
            if (!AudioResourceManager.this.mCarPlayConnected) {
                LogUtil.info(AudioResourceManager.TAG, "releaseResource carplay not connected");
            } else {
                CarPlayStackCmdHandler.getInstance().audioPlayControl(2, true);
                CarPlayStackCmdHandler.getInstance().audioPlayControl(2, false);
            }
        }

        public IState getPreState() {
            return this.mPreState;
        }

        public IState getCurState() {
            return this.mCurState;
        }
    }

    public void carplayAudioChangedBorrow() {
        LogUtil.info(TAG, "carplayAudioChangedBorrow,curState=" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(2);
    }

    public void carplayAudioChangedUnBorrow() {
        LogUtil.info(TAG, "carplayAudioChangedUnBorrow,curState=" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(12);
    }

    public void backCameraStatusChangedTo(CpConst.AppState state) {
        if (!this.mCarPlayManager.getDrivingMediaVolMuteStatus()) {
            LogUtil.info(TAG, "backCameraStatusChangedTo, drvingmute false, needn't borrow never!");
            return;
        }
        IState curState = this.mStateMachine.getCurState();
        LogUtil.info(TAG, "backCameraStatusChangedTo AppState = " + state + ", CurState=" + curState);
        if (state != CpConst.AppState.APP_ON) {
            CpConst.AppState appState = CpConst.AppState.APP_OFF;
        }
    }

    public void clearLocalStatus() {
        LogUtil.info(TAG, "clearLocalStatus");
        this.mStateMachine.mAudioEntity = 0;
        this.mStateMachine.mPerAudioEntity = 0;
    }

    public void powerStatusChangedTo(CpConst.PowerStatus powerStatus) {
        LogUtil.info(TAG, "powerStatusChangedTo powerStatus = " + powerStatus);
        if (powerStatus == CpConst.PowerStatus.STATUS_OFF) {
            this.mStateMachine.sendMessage(2);
        } else if (powerStatus == CpConst.PowerStatus.STATUS_ON) {
            this.mStateMachine.sendMessage(12);
        }
    }

    public void releaseCurrentAudio() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean requestAudioFocusDirectly(int audioRequest) {
        LogUtil.info(TAG, "requestAudioFocusDirectly audioRequest = " + audioRequest);
        boolean result = this.mCarPlayManager.requestAudioFocus(audioRequest);
        CarPlayManager carPlayManager = this.mCarPlayManager;
        if (carPlayManager != null) {
            switch (audioRequest) {
                case 0:
                case 7:
                    stopEntityTimer();
                    this.mCarPlayManager.rspAudioFocusReq(2, true);
                    break;
                case 1:
                case 4:
                    carPlayManager.rspAudioFocusReq(1, true);
                    break;
                case 2:
                    stopEntityTimer();
                    this.mCarPlayManager.rspAudioFocusReq(4, true);
                    break;
                case 3:
                    stopEntityTimer();
                    this.mCarPlayManager.rspAudioFocusReq(3, true);
                    break;
                case 5:
                    stopEntityTimer();
                    this.mCarPlayManager.rspAudioFocusReq(5, true);
                    break;
                case 6:
                case 8:
                    this.mCarPlayManager.rspAudioFocusReq(4, true);
                    break;
                default:
                    LogUtil.warning(TAG, "requestAudioFocusDirectly invalid Req:" + audioRequest);
                    break;
            }
        }
        return result;
    }

    public boolean abandonAudioFocus(int audioRequest) {
        LogUtil.info(TAG, "abandonAudioFocus audioRequest = " + audioRequest);
        boolean result = this.mCarPlayManager.abandonAudioFocus(audioRequest);
        return result;
    }

    public int getCurrentCpFocus() {
        return this.mCurrentCpFocus;
    }

    public int audioResourceRequest(int requestType, int sampleRate) {
        LogUtil.debug(TAG, "audioResourceRequest, requestType:" + requestType + " sampleRate:" + sampleRate + " curState=" + this.mStateMachine.getCurState().getName());
        boolean result = false;
        if (requestType == 0 || requestType == 1) {
            if (sampleRate == 16000) {
                this.mCarPlayManager.rspAudioFocusReq(requestType, true);
            } else if (sampleRate == 48000) {
                if (this.mDuckDurationMs > 0) {
                    this.mCurrentAlternateAudio = 4;
                } else {
                    this.mCurrentAlternateAudio = 1;
                }
                result = requestAudioFocusDirectly(this.mCurrentAlternateAudio);
                LogUtil.debug(TAG, "request navi.");
            }
        } else if (requestType != 2) {
            if (requestType == 3) {
                this.mCurrentCpFocus = 3;
                result = requestAudioFocusDirectly(3);
                LogUtil.debug(TAG, "request phone.");
            } else if (requestType != 4) {
                if (requestType == 5) {
                    this.mCurrentCpFocus = 5;
                    result = requestAudioFocusDirectly(5);
                    LogUtil.debug(TAG, "request ringtone.");
                }
            } else if (sampleRate != 16000) {
                if (sampleRate == 24000) {
                    this.mCurrentCpFocus = 2;
                    result = requestAudioFocusDirectly(2);
                    LogUtil.debug(TAG, "request vr.");
                } else if (sampleRate != 48000) {
                    LogUtil.warning(TAG, "sample rate error!");
                } else {
                    if (this.mDuckDurationMs > 0) {
                        this.mCurrentAuxOutAudio = 8;
                    } else {
                        this.mCurrentAuxOutAudio = 6;
                    }
                    result = requestAudioFocusDirectly(this.mCurrentAuxOutAudio);
                    LogUtil.debug(TAG, "request auxout.");
                }
            }
        } else if (this.mStateMachine.mPerAudioEntity != 2) {
            this.mCurrentCpFocus = -1;
            requestAudioFocusDirectly(0);
            LogUtil.debug(TAG, "request media.");
        } else {
            this.mCurrentCpFocus = 7;
            requestAudioFocusDirectly(7);
            LogUtil.debug(TAG, "request tmp media.");
        }
        if (result) {
            return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_AVAILABLE.ordinal();
        }
        return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_BUSY.ordinal();
    }

    public int audioResourceAbandon(int requestType, int sampleRate, int vocoderInfo) {
        LogUtil.info(TAG, "audioResourceAbandon, requestType:" + requestType + " sampleRate:" + sampleRate + " vocoderInfo:" + vocoderInfo + " curState=" + this.mStateMachine.getCurState().getName());
        boolean result = false;
        if (requestType == 0 || requestType == 1) {
            if (sampleRate != 16000 && sampleRate == 48000) {
                if (4 == this.mCurrentAlternateAudio && !this.mCarPlayConnected && this.mDuckDurationMs > 0) {
                    LogUtil.debug(TAG, "abandon duck alternate after tear down if session is disconnected.");
                    unduckAudio(this.mDuckDurationMs);
                }
                result = abandonAudioFocus(this.mCurrentAlternateAudio);
                this.mCurrentAlternateAudio = -1;
                LogUtil.debug(TAG, "abandon alternate audio , result= " + result);
            }
        } else if (requestType != 3) {
            if (requestType == 4) {
                if (sampleRate == 48000) {
                    if (8 == this.mCurrentAuxOutAudio && !this.mCarPlayConnected && this.mDuckDurationMs > 0) {
                        LogUtil.debug(TAG, "abandon duck auxout after tear down if session is disconnected.");
                        unduckAudio(this.mDuckDurationMs);
                    }
                    result = abandonAudioFocus(this.mCurrentAuxOutAudio);
                    LogUtil.info(TAG, "abandon auxout, result = " + result);
                }
                if (sampleRate == 24000) {
                    if (CarPlayLastUserMode.getInstance().isRealAudioLastUserMode()) {
                        result = abandonAudioFocus(2);
                        LogUtil.info(TAG, "abandon siri, result = " + result);
                    } else {
                        startEntityTimer();
                        LogUtil.info(TAG, "siri startEntityTimer.");
                    }
                }
            } else if (requestType == 5) {
                if (CarPlayLastUserMode.getInstance().isRealAudioLastUserMode()) {
                    result = abandonAudioFocus(5);
                    LogUtil.info(TAG, "abandon phone alert, result = " + result);
                } else {
                    startEntityTimer();
                    LogUtil.info(TAG, "alert startEntityTimer.");
                }
            }
        } else if (CarPlayLastUserMode.getInstance().isRealAudioLastUserMode()) {
            result = abandonAudioFocus(3);
            LogUtil.info(TAG, "abandon phone audio, result = " + result);
        } else {
            startEntityTimer();
            LogUtil.info(TAG, "phone startEntityTimer.");
        }
        if (result) {
            return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_AVAILABLE.ordinal();
        }
        return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_BUSY.ordinal();
    }

    public void recordStartInfo(int transfer) {
        LogUtil.info(TAG, "audio: recordStartInfo transfer = " + transfer);
        this.mTransferInStartInfo = transfer;
    }

    public CpConst.ResourceStatus getCurResourceState() {
        LogUtil.info(TAG, "audio: getCurResourceState");
        if (this.mStateMachine.getCurState() != null) {
            LogUtil.info(TAG, " curState:" + this.mStateMachine.getCurState().getName());
        }
        if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowUser) {
            return CpConst.ResourceStatus.RESOURCE_IN_BORROW_USER;
        }
        int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO);
        if (lastUserModeState == 1) {
            LogUtil.info(TAG, "Last User Mode is CarPlay");
            return CpConst.ResourceStatus.RESOURCE_IN_NICE_TO_HAVE;
        }
        return CpConst.ResourceStatus.RESOURCE_IN_TAKE;
    }

    public boolean isBorrowed() {
        if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowUser || this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowNever) {
            return true;
        }
        return false;
    }

    public CarPlayStartInfo getLastResourceState() {
        LogUtil.info(TAG, "audio: getLastResourceState");
        if (this.mStateMachine.getCurState() != null) {
            LogUtil.info(TAG, " curState:" + this.mStateMachine.getCurState().getName());
        }
        if (this.mStateMachine.getPreState() != null) {
            LogUtil.info(TAG, " PreState:" + this.mStateMachine.getPreState().getName());
        }
        this.mStartInfoState = this.mStateMachine.getCurState();
        CarPlayStartInfo outStartInfo = new CarPlayStartInfo();
        int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO);
        outStartInfo.mainAudioTransferInfo.borrowId = getBorrowId();
        LogUtil.info(TAG, " getLastResourceState = " + lastUserModeState + ", borrowId=" + getBorrowId());
        if (this.mStateMachine.getCurState() != this.mStateMachine.mInBorrowUser) {
            if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowNever) {
                outStartInfo.mainAudioTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal();
                outStartInfo.mainAudioTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
                outStartInfo.mainAudioTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
                outStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER.ordinal();
                recordStartInfo(3);
                return outStartInfo;
            }
            if (lastUserModeState != 0) {
                if (this.mStateMachine.getCurState() == this.mStateMachine.mInTake) {
                    outStartInfo.mainAudioTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal();
                    outStartInfo.mainAudioTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
                    outStartInfo.mainAudioTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
                    outStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
                    recordStartInfo(1);
                }
                if (1 == lastUserModeState) {
                    LogUtil.info(TAG, " decide to launch carplay audio.");
                    outStartInfo.mainAudioTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal();
                    outStartInfo.mainAudioTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_NICETOHAVE.ordinal();
                    outStartInfo.mainAudioTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
                    outStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
                    recordStartInfo(4);
                }
                return outStartInfo;
            }
            LogUtil.info(TAG, " decide to donot launch carplay audio.");
            outStartInfo.mainAudioTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal();
            outStartInfo.mainAudioTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
            outStartInfo.mainAudioTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
            outStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
            recordStartInfo(1);
            return outStartInfo;
        }
        outStartInfo.mainAudioTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal();
        outStartInfo.mainAudioTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
        outStartInfo.mainAudioTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
        outStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
        recordStartInfo(2);
        return outStartInfo;
    }

    private int audioTypeToPriority(int audioId) {
        return 1;
    }

    public void onSessionEstablished() {
        LogUtil.info(TAG, "onSessionEstablished, mTransferInStartInfo = " + this.mTransferInStartInfo + " currentState = " + this.mStateMachine.getCurState().getName());
        this.mCarPlayConnected = true;
        if ((this.mTransferInStartInfo == 2 && this.mStateMachine.getCurState() != this.mStateMachine.mInBorrowUser) || this.mIsNeedSendUnborrow) {
            this.mIsNeedSendUnborrow = false;
            clearBorrowUserList();
        }
    }

    public void clearNeedSendUnborrow() {
        LogUtil.info(TAG, "clearNeedSendUnborrow");
        this.mIsNeedSendUnborrow = false;
    }

    public void onSessionDestoryed(LinkDevice device) {
        LogUtil.info(TAG, "onSessionDestoryed");
        this.mCarPlayConnected = false;
        this.mStartInfoState = null;
        stopEntityTimer();
        int i = this.mCurrentCpFocus;
        if (i != -1) {
            abandonAudioFocus(i);
        }
        this.mCurrentCpFocus = -1;
    }

    public boolean isInCarPlayState() {
        LogUtil.info(TAG, "isInCarPlayState, curState=" + this.mStateMachine.getCurState().getName());
        if (this.mStateMachine.getCurState() == this.mStateMachine.mInCarPlayAudio) {
            return true;
        }
        return false;
    }

    private void startEntityTimer() {
        if (this.mEntityTimerTask == null) {
            TimerTask timerTask = new TimerTask() { // from class: com.ts.carplay.lib.resourcemanager.AudioResourceManager.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    LogUtil.info(AudioResourceManager.TAG, "startEntityTimer mAudioEntity: " + AudioResourceManager.this.mStateMachine.mAudioEntity + ", mCurrentCpFocus: " + AudioResourceManager.this.mCurrentCpFocus);
                    if (AudioResourceManager.this.mStateMachine.mAudioEntity == 1) {
                        AudioResourceManager audioResourceManager = AudioResourceManager.this;
                        audioResourceManager.abandonAudioFocus(audioResourceManager.mCurrentCpFocus);
                    }
                    AudioResourceManager.this.stopEntityTimer();
                }
            };
            this.mEntityTimerTask = timerTask;
            this.mEntityTimer.schedule(timerTask, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopEntityTimer() {
        if (this.mEntityTimerTask != null) {
            LogUtil.info(TAG, "stopEntityTimer");
            this.mEntityTimerTask.cancel();
            this.mEntityTimerTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBorrowUserId(String id) {
        List<String> list = this.mBorrowUserList;
        if (list != null && !list.contains(id)) {
            this.mBorrowUserList.add(id);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeBorrowUserId(String id) {
        List<String> list = this.mBorrowUserList;
        if (list != null && list.contains(id)) {
            this.mBorrowUserList.remove(id);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInBorrowUserList(String id) {
        List<String> list = this.mBorrowUserList;
        if (list == null) {
            return false;
        }
        boolean res = list.contains(id);
        return res;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBorrowUserList() {
        List<String> list = this.mBorrowUserList;
        if (list != null) {
            for (String id : list) {
                if (id != null) {
                    this.mStateMachine.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, id);
                }
            }
            this.mBorrowUserList.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBorrowUserListNoCmd() {
        List<String> list = this.mBorrowUserList;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInBorrowNeverList(String id) {
        List<String> list = this.mBorrowNeverList;
        if (list == null) {
            return false;
        }
        boolean res = list.contains(id);
        return res;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBorrowNeverId(String id) {
        List<String> list = this.mBorrowNeverList;
        if (list != null && !list.contains(id)) {
            this.mBorrowNeverList.add(id);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeBorrowNeverId(String id) {
        List<String> list = this.mBorrowNeverList;
        if (list != null && list.contains(id)) {
            this.mBorrowNeverList.remove(id);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBorrowNeverList() {
        List<String> list = this.mBorrowNeverList;
        if (list != null) {
            for (String id : list) {
                if (id != null) {
                    this.mStateMachine.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, id);
                }
            }
            this.mBorrowNeverList.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBorrowNeverListNoCmd() {
        List<String> list = this.mBorrowNeverList;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBorrowId(String id) {
        this.mBorrowId = id;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getBorrowId() {
        return this.mBorrowId;
    }

    private void setReqBorrowId(String id) {
        this.mReqBorrowId = id;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getReqBorrowId() {
        return this.mReqBorrowId;
    }
}
