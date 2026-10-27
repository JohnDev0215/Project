package com.ts.carplay.lib.resourcemanager;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;

/* JADX INFO: loaded from: classes.dex */
public class CpPowerOffManager {
    private static final int ACTIVITY_CHANGED = 5;
    private static final int APP_START = 9;
    private static final int BACK_TO_CONNECTED = 12;
    private static final int CONNECTED = 10;
    private static final int DISCONNECTED = 11;
    private static final int PLAY_MUSIC = 13;
    private static final int POWER_OFF = 2;
    private static final int POWER_ON = 1;
    private static final int SIRI_END = 8;
    private static final int SIRI_END_WAIT_TIME = 5000;
    private static final int SIRI_START = 4;
    private static final int SIRI_STOP = 7;
    private static final String TAG = "CpPowerOffManager";
    private static final int TELEPHONY_START = 3;
    private static final int TELEPHONY_STOP = 6;
    private AudioResourceManager mAudioResourceManager;
    private HandlerThread mPowerOffManagerThread;
    private PowerOffManagerStateMachine mPowerOffStateMachine;
    private ScreenResourceManager mScreenResourceManager;
    static CpPowerOffManager sCpPowerOffManager = null;
    private static boolean ifStartTimer = false;
    private static boolean isPlayMusic = false;

    public CpPowerOffManager(ScreenResourceManager screenManager, AudioResourceManager audioManager) {
        this.mScreenResourceManager = null;
        this.mAudioResourceManager = null;
        this.mScreenResourceManager = screenManager;
        this.mAudioResourceManager = audioManager;
        HandlerThread handlerThread = new HandlerThread("powerOffManagerThread");
        this.mPowerOffManagerThread = handlerThread;
        handlerThread.start();
        PowerOffManagerStateMachine powerOffManagerStateMachine = new PowerOffManagerStateMachine(TAG, this.mPowerOffManagerThread.getLooper());
        this.mPowerOffStateMachine = powerOffManagerStateMachine;
        powerOffManagerStateMachine.start();
        sCpPowerOffManager = this;
    }

    public static CpPowerOffManager getInstance() {
        CpPowerOffManager cpPowerOffManager = sCpPowerOffManager;
        if (cpPowerOffManager == null) {
            throw new IllegalStateException("Attempted to retrieve a CpPowerOffManager before constructor was called.");
        }
        return cpPowerOffManager;
    }

    private class PowerOffManagerStateMachine extends StateMachine {
        private IState mCurState;
        private IdleState mIdleState;
        private IdleTelState mIdleTelState;
        private PowerOffEndingState mPowerOffEndingState;
        private PowerOffState mPowerOffState;
        private int mScreenEntity;
        private SiriEndingState mSiriEndingState;
        private SiriState mSiriState;
        private TelephonyState mTelephonyState;

        public PowerOffManagerStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mPowerOffState = new PowerOffState();
            this.mIdleTelState = new IdleTelState();
            this.mTelephonyState = new TelephonyState();
            this.mSiriState = new SiriState();
            this.mSiriEndingState = new SiriEndingState();
            this.mPowerOffEndingState = new PowerOffEndingState();
            this.mScreenEntity = 0;
            IdleState idleState = this.mIdleState;
            this.mCurState = idleState;
            addState(idleState);
            addState(this.mPowerOffState);
            addState(this.mIdleTelState);
            addState(this.mTelephonyState);
            addState(this.mSiriState);
            addState(this.mSiriEndingState);
            addState(this.mPowerOffEndingState);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mIdleState;
                LogUtil.info(CpPowerOffManager.TAG, "IdleState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    CpPowerOffManager.this.mScreenResourceManager.powerStatusChangedTo(CpConst.PowerStatus.STATUS_OFF);
                    CpPowerOffManager.this.mAudioResourceManager.powerStatusChangedTo(CpConst.PowerStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mPowerOffState);
                    return true;
                }
                if (i == 3) {
                    PowerOffManagerStateMachine.this.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_ON);
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mIdleTelState);
                    return true;
                }
                if (i == 4) {
                    PowerOffManagerStateMachine.this.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_ON);
                    PowerOffManagerStateMachine powerOffManagerStateMachine3 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine3.transitionTo(powerOffManagerStateMachine3.mSiriState);
                    return true;
                }
                return false;
            }
        }

        class PowerOffState extends State {
            PowerOffState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mPowerOffState;
                LogUtil.info(CpPowerOffManager.TAG, "PowerOffState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    CpPowerOffManager.this.mScreenResourceManager.powerStatusChangedTo(CpConst.PowerStatus.STATUS_ON);
                    CpPowerOffManager.this.mAudioResourceManager.powerStatusChangedTo(CpConst.PowerStatus.STATUS_ON);
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mIdleState);
                    return true;
                }
                if (i == 3) {
                    PowerOffManagerStateMachine.this.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_ON);
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mTelephonyState);
                    return true;
                }
                if (i == 4) {
                    PowerOffManagerStateMachine.this.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_ON);
                    PowerOffManagerStateMachine powerOffManagerStateMachine3 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine3.transitionTo(powerOffManagerStateMachine3.mSiriState);
                    return true;
                }
                return false;
            }
        }

        class IdleTelState extends State {
            IdleTelState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mIdleTelState;
                LogUtil.info(CpPowerOffManager.TAG, "IdleTelState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 6) {
                    CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mIdleState);
                    return true;
                }
                if (i == 11) {
                    CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mIdleState);
                    return true;
                }
                return false;
            }
        }

        class TelephonyState extends State {
            TelephonyState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mTelephonyState;
                LogUtil.info(CpPowerOffManager.TAG, "TelephonyState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 5) {
                    return true;
                }
                if (i == 6) {
                    CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mPowerOffEndingState);
                    return true;
                }
                if (i == 11) {
                    CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mIdleState);
                    return true;
                }
                return false;
            }
        }

        class SiriState extends State {
            SiriState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mSiriState;
                LogUtil.info(CpPowerOffManager.TAG, "SiriState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 3) {
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mTelephonyState);
                    return true;
                }
                if (i == 5) {
                    return true;
                }
                if (i == 7) {
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mSiriEndingState);
                    return true;
                }
                if (i == 11) {
                    CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine3 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine3.transitionTo(powerOffManagerStateMachine3.mIdleState);
                    return true;
                }
                return false;
            }
        }

        class SiriEndingState extends State {
            SiriEndingState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mSiriEndingState;
                LogUtil.info(CpPowerOffManager.TAG, "SiriEndingState enter mScreenEntity = " + PowerOffManagerStateMachine.this.mScreenEntity);
                if (PowerOffManagerStateMachine.this.mScreenEntity != 1) {
                    if (CpPowerOffManager.isPlayMusic) {
                        LogUtil.info(CpPowerOffManager.TAG, "Just PlayMusic");
                        PowerOffManagerStateMachine.this.carPlayAppChanged();
                        PowerOffManagerStateMachine.this.carPlaySiriEnded();
                        PowerOffManagerStateMachine.this.music();
                        return;
                    }
                    LogUtil.info(CpPowerOffManager.TAG, "End siri");
                    PowerOffManagerStateMachine.this.carPlaySiriEnded();
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 3) {
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mTelephonyState);
                    return true;
                }
                if (i == 5) {
                    return true;
                }
                if (i == 11) {
                    CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mIdleState);
                    return true;
                }
                if (i != 8) {
                    if (i == 9) {
                        return true;
                    }
                    return false;
                }
                CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(CpConst.PhoneStatus.STATUS_OFF);
                PowerOffManagerStateMachine powerOffManagerStateMachine3 = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine3.transitionTo(powerOffManagerStateMachine3.mPowerOffEndingState);
                return true;
            }
        }

        class PowerOffEndingState extends State {
            PowerOffEndingState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                powerOffManagerStateMachine.mCurState = powerOffManagerStateMachine.mPowerOffEndingState;
                LogUtil.info(CpPowerOffManager.TAG, "PowerOffEndingState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    PowerOffManagerStateMachine powerOffManagerStateMachine = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine.transitionTo(powerOffManagerStateMachine.mPowerOffState);
                    return true;
                }
                if (i == 12) {
                    CpPowerOffManager.this.mScreenResourceManager.changeToCarPlay();
                    CpPowerOffManager.this.mAudioResourceManager.powerStatusChangedTo(CpConst.PowerStatus.STATUS_ON);
                    PowerOffManagerStateMachine powerOffManagerStateMachine2 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine2.transitionTo(powerOffManagerStateMachine2.mIdleState);
                    return true;
                }
                if (i == 13) {
                    PowerOffManagerStateMachine powerOffManagerStateMachine3 = PowerOffManagerStateMachine.this;
                    powerOffManagerStateMachine3.transitionTo(powerOffManagerStateMachine3.mIdleState);
                    CpPowerOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                    return true;
                }
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void phoneCallStateChanged(final CpConst.PhoneStatus status) {
            try {
                new Thread(new Runnable() { // from class: com.ts.carplay.lib.resourcemanager.CpPowerOffManager.PowerOffManagerStateMachine.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CpPowerOffManager.this.mScreenResourceManager.phoneCallStateChanged(status);
                    }
                }).start();
            } catch (Exception e) {
                LogUtil.info(CpPowerOffManager.TAG, "phoneCallStateChanged Exception");
            }
        }

        public void carPlayAppChanged() {
            sendMessage(9);
        }

        public void carPlaySiriEnded() {
            sendMessage(8);
        }

        public void backToConnected() {
            sendMessage(12);
        }

        public void music() {
            sendMessage(13);
        }

        public void screenResourceChanged(int screenEntity) {
            LogUtil.info(CpPowerOffManager.TAG, "screenResourceChanged screenEntity = " + screenEntity);
            if (CpPowerOffManager.ifStartTimer) {
                LogUtil.info(CpPowerOffManager.TAG, "ifStartTimer " + CpPowerOffManager.ifStartTimer);
                if (screenEntity == 2) {
                    if (CpPowerOffManager.isPlayMusic) {
                        LogUtil.info(CpPowerOffManager.TAG, "Just PlayMusic");
                        carPlayAppChanged();
                        carPlaySiriEnded();
                        music();
                    } else {
                        LogUtil.info(CpPowerOffManager.TAG, "End siri");
                        carPlaySiriEnded();
                    }
                }
            }
            this.mScreenEntity = screenEntity;
        }

        public void playMusic() {
            LogUtil.info(CpPowerOffManager.TAG, "playMusic");
            if (CpPowerOffManager.ifStartTimer) {
                LogUtil.info(CpPowerOffManager.TAG, "ifStartTimer " + CpPowerOffManager.ifStartTimer);
                boolean unused = CpPowerOffManager.isPlayMusic = true;
            }
        }

        public IState getCurState() {
            return this.mCurState;
        }
    }

    public void powerStatusChangedTo(CpConst.PowerStatus powerStatus) {
        LogUtil.info(TAG, "powerStatusChangedTo powerStatus = " + powerStatus);
        if (powerStatus == CpConst.PowerStatus.STATUS_ON) {
            this.mPowerOffStateMachine.sendMessage(1);
        } else {
            this.mPowerOffStateMachine.sendMessage(2);
        }
    }

    public void carPlayPhoneStatusChanged(CpConst.AppStatus appStatus) {
        LogUtil.info(TAG, "carPlayPhoneStatusChanged appStatus=" + appStatus);
        if (appStatus == CpConst.AppStatus.STATUS_ON) {
            this.mPowerOffStateMachine.sendMessage(3);
        } else {
            this.mPowerOffStateMachine.sendMessage(6);
        }
    }

    public void carPlaySiriStatusChanged(CpConst.AppStatus appStatus) {
        LogUtil.info(TAG, "carPlaySiriStatusChanged appStatus=" + appStatus);
        if (appStatus == CpConst.AppStatus.STATUS_ON) {
            this.mPowerOffStateMachine.sendMessage(4);
        } else {
            this.mPowerOffStateMachine.sendMessage(7);
        }
    }

    public void siriPlayMusic() {
        this.mPowerOffStateMachine.playMusic();
    }

    public void topActivityChanged() {
        LogUtil.info(TAG, "topActivityChanged");
        this.mPowerOffStateMachine.sendMessage(5);
    }

    public boolean isPowerOff() {
        if (this.mPowerOffStateMachine.mPowerOffState == this.mPowerOffStateMachine.getCurState()) {
            return true;
        }
        return false;
    }

    public void carPlayScreenResourceChanged(int screenEntity) {
        this.mPowerOffStateMachine.screenResourceChanged(screenEntity);
    }

    public void onSessionEstablished() {
        LogUtil.info(TAG, "onSessionEstablished");
        this.mPowerOffStateMachine.sendMessage(10);
    }

    public void onSessionDestoryed() {
        LogUtil.info(TAG, "onSessionDestoryed");
        this.mPowerOffStateMachine.sendMessage(11);
    }
}
