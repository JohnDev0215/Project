package com.ts.carplay.lib.resourcemanager;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class CpScreenOffManager {
    private static final int ACTIVITY_CHANGED = 5;
    private static final int APP_START = 9;
    private static final int CONNECTED = 10;
    private static final int DISCONNECTED = 11;
    private static final int SCREEN_OFF = 2;
    private static final int SCREEN_ON = 1;
    private static final int SIRI_END = 8;
    private static final int SIRI_END_WAIT_TIME = 4000;
    private static final int SIRI_START = 4;
    private static final int SIRI_STOP = 7;
    private static final String TAG = "CpScreenOffManager";
    private static final int TELEPHONY_START = 3;
    private static final int TELEPHONY_STOP = 6;
    private HandlerThread mScreenOffManagerThread;
    private ScreenOffManagerStateMachine mScreenOffStateMachine;
    private ScreenResourceManager mScreenResourceManager;
    static CpScreenOffManager sCpScreenOffManager = null;
    private static boolean ifStartTimer = false;
    private boolean isChangeActivity = false;
    private boolean isReleaseScreenByTake = false;
    private boolean isNeedNotReturnToScreenOff = false;

    public CpScreenOffManager(ScreenResourceManager manager) {
        this.mScreenResourceManager = null;
        this.mScreenResourceManager = manager;
        HandlerThread handlerThread = new HandlerThread("screenOffManagerThread");
        this.mScreenOffManagerThread = handlerThread;
        handlerThread.start();
        ScreenOffManagerStateMachine screenOffManagerStateMachine = new ScreenOffManagerStateMachine(TAG, this.mScreenOffManagerThread.getLooper());
        this.mScreenOffStateMachine = screenOffManagerStateMachine;
        screenOffManagerStateMachine.start();
        sCpScreenOffManager = this;
    }

    public static CpScreenOffManager getInstance() {
        CpScreenOffManager cpScreenOffManager = sCpScreenOffManager;
        if (cpScreenOffManager == null) {
            throw new IllegalStateException("Attempted to retrieve a CpScreenOffManager before constructor was called.");
        }
        return cpScreenOffManager;
    }

    private class ScreenOffManagerStateMachine extends StateMachine {
        private IState mCurState;
        private IdleState mIdleState;
        private int mScreenEntity;
        private ScreenOffState mScreenOffState;
        private SiriPhoneEndingState mSiriPhoneEndingState;
        private SiriState mSiriState;
        private TimerTask mSiriWaitEndTask;
        private Timer mSiriWaitEndTimer;
        private TelephonyState mTelephonyState;

        public ScreenOffManagerStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mScreenOffState = new ScreenOffState();
            this.mTelephonyState = new TelephonyState();
            this.mSiriState = new SiriState();
            this.mSiriPhoneEndingState = new SiriPhoneEndingState();
            this.mSiriWaitEndTimer = new Timer();
            this.mScreenEntity = 0;
            IdleState idleState = this.mIdleState;
            this.mCurState = idleState;
            addState(idleState);
            addState(this.mScreenOffState);
            addState(this.mTelephonyState);
            addState(this.mSiriState);
            addState(this.mSiriPhoneEndingState);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                screenOffManagerStateMachine.mCurState = screenOffManagerStateMachine.mIdleState;
                LogUtil.info(CpScreenOffManager.TAG, "IdleState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                if (message.what == 2) {
                    CpScreenOffManager.this.mScreenResourceManager.screenStatusChangedTo(CpConst.ScreenStatus.STATUS_OFF);
                    ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine.transitionTo(screenOffManagerStateMachine.mScreenOffState);
                    return true;
                }
                return false;
            }
        }

        class ScreenOffState extends State {
            ScreenOffState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                screenOffManagerStateMachine.mCurState = screenOffManagerStateMachine.mScreenOffState;
                LogUtil.info(CpScreenOffManager.TAG, "ScreenOffState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    CpScreenOffManager.this.mScreenResourceManager.screenStatusChangedTo(CpConst.ScreenStatus.STATUS_ON);
                    ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine.transitionTo(screenOffManagerStateMachine.mIdleState);
                    return true;
                }
                if (i == 3) {
                    ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(true, false);
                    ScreenOffManagerStateMachine screenOffManagerStateMachine2 = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine2.transitionTo(screenOffManagerStateMachine2.mTelephonyState);
                    return true;
                }
                if (i == 4) {
                    ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(true, false);
                    ScreenOffManagerStateMachine screenOffManagerStateMachine3 = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine3.transitionTo(screenOffManagerStateMachine3.mSiriState);
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
                ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                screenOffManagerStateMachine.mCurState = screenOffManagerStateMachine.mTelephonyState;
                CpScreenOffManager.this.isReleaseScreenByTake = false;
                LogUtil.info(CpScreenOffManager.TAG, "TelephonyState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 5) {
                    CpScreenOffManager.this.isChangeActivity = true;
                    return true;
                }
                if (i != 6) {
                    if (i == 11) {
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                        ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine.transitionTo(screenOffManagerStateMachine.mIdleState);
                        return true;
                    }
                    return false;
                }
                if (CpScreenOffManager.this.isChangeActivity) {
                    CpScreenOffManager.this.isChangeActivity = false;
                    CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                    if (CpScreenOffManager.this.isReleaseScreenByTake) {
                        ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(true, true);
                    } else {
                        ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(false, false);
                    }
                    ScreenOffManagerStateMachine screenOffManagerStateMachine2 = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine2.transitionTo(screenOffManagerStateMachine2.mIdleState);
                } else {
                    ScreenOffManagerStateMachine screenOffManagerStateMachine3 = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine3.transitionTo(screenOffManagerStateMachine3.mSiriPhoneEndingState);
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                CpScreenOffManager.this.isReleaseScreenByTake = false;
            }
        }

        class SiriState extends State {
            SiriState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                screenOffManagerStateMachine.mCurState = screenOffManagerStateMachine.mSiriState;
                CpScreenOffManager.this.isReleaseScreenByTake = false;
                LogUtil.info(CpScreenOffManager.TAG, "SiriState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 3) {
                    ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine.transitionTo(screenOffManagerStateMachine.mTelephonyState);
                    return true;
                }
                if (i == 5) {
                    CpScreenOffManager.this.isChangeActivity = true;
                    return true;
                }
                if (i != 7) {
                    if (i == 11) {
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                        ScreenOffManagerStateMachine screenOffManagerStateMachine2 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine2.transitionTo(screenOffManagerStateMachine2.mIdleState);
                        return true;
                    }
                    return false;
                }
                if (CpScreenOffManager.this.isChangeActivity) {
                    if (CpScreenOffManager.this.isNeedNotReturnToScreenOff) {
                        CpScreenOffManager.this.isChangeActivity = false;
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        if (CpScreenOffManager.this.isReleaseScreenByTake) {
                            CpScreenOffManager.this.mScreenResourceManager.carplayScreenChangedUnBorrow();
                            ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(true, true);
                        } else {
                            ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(false, false);
                        }
                        ScreenOffManagerStateMachine screenOffManagerStateMachine3 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine3.transitionTo(screenOffManagerStateMachine3.mIdleState);
                    } else {
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                        ScreenOffManagerStateMachine screenOffManagerStateMachine4 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine4.transitionTo(screenOffManagerStateMachine4.mScreenOffState);
                    }
                } else {
                    ScreenOffManagerStateMachine screenOffManagerStateMachine5 = ScreenOffManagerStateMachine.this;
                    screenOffManagerStateMachine5.transitionTo(screenOffManagerStateMachine5.mSiriPhoneEndingState);
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                CpScreenOffManager.this.isReleaseScreenByTake = false;
            }
        }

        class SiriPhoneEndingState extends State {
            SiriPhoneEndingState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                screenOffManagerStateMachine.mCurState = screenOffManagerStateMachine.mSiriPhoneEndingState;
                LogUtil.info(CpScreenOffManager.TAG, "SiriPhoneEndingState enter");
                if (ScreenOffManagerStateMachine.this.mScreenEntity == 2) {
                    ScreenOffManagerStateMachine.this.carPlaySiriEnded();
                } else {
                    ScreenOffManagerStateMachine.this.startSiriWaitTimer();
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                switch (message.what) {
                    case 3:
                        ScreenOffManagerStateMachine screenOffManagerStateMachine = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine.transitionTo(screenOffManagerStateMachine.mTelephonyState);
                        break;
                    case 4:
                        ScreenOffManagerStateMachine.this.stopSiriWaitTimer();
                        ScreenOffManagerStateMachine screenOffManagerStateMachine2 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine2.transitionTo(screenOffManagerStateMachine2.mSiriState);
                        break;
                    case 5:
                        ScreenOffManagerStateMachine.this.stopSiriWaitTimer();
                        CpScreenOffManager.this.isChangeActivity = true;
                        ScreenOffManagerStateMachine screenOffManagerStateMachine3 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine3.transitionTo(screenOffManagerStateMachine3.mIdleState);
                        break;
                    case 6:
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                        ScreenOffManagerStateMachine screenOffManagerStateMachine4 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine4.transitionTo(screenOffManagerStateMachine4.mScreenOffState);
                        break;
                    case 8:
                        if (CpScreenOffManager.this.isNeedNotReturnToScreenOff) {
                            CpScreenOffManager.this.isChangeActivity = false;
                            CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                            CpScreenOffManager.this.mScreenResourceManager.carplayScreenChangedUnBorrow();
                            ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(true, true);
                            ScreenOffManagerStateMachine screenOffManagerStateMachine5 = ScreenOffManagerStateMachine.this;
                            screenOffManagerStateMachine5.transitionTo(screenOffManagerStateMachine5.mIdleState);
                        } else {
                            CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                            CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                            ScreenOffManagerStateMachine screenOffManagerStateMachine6 = ScreenOffManagerStateMachine.this;
                            screenOffManagerStateMachine6.transitionTo(screenOffManagerStateMachine6.mScreenOffState);
                        }
                        break;
                    case 9:
                        CpScreenOffManager.this.mScreenResourceManager.changeToCarPlay();
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        ScreenOffManagerStateMachine.this.setDeviceMirroringRunning(true, true);
                        ScreenOffManagerStateMachine screenOffManagerStateMachine7 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine7.transitionTo(screenOffManagerStateMachine7.mIdleState);
                        break;
                    case 11:
                        CpScreenOffManager.this.clearNeedReturnToScreenOffSts();
                        CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(false, false);
                        ScreenOffManagerStateMachine screenOffManagerStateMachine8 = ScreenOffManagerStateMachine.this;
                        screenOffManagerStateMachine8.transitionTo(screenOffManagerStateMachine8.mIdleState);
                        break;
                }
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceMirroringRunning(final boolean on, final boolean isRunning) {
            try {
                new Thread(new Runnable() { // from class: com.ts.carplay.lib.resourcemanager.CpScreenOffManager.ScreenOffManagerStateMachine.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CpScreenOffManager.this.mScreenResourceManager.setDeviceMirroringRunning(on, isRunning);
                    }
                }).start();
            } catch (Exception e) {
                LogUtil.info(CpScreenOffManager.TAG, "setDeviceMirroringRunning Exception");
            }
        }

        public void startSiriWaitTimer() {
            LogUtil.info(CpScreenOffManager.TAG, "startSiriWaitTimer");
            boolean unused = CpScreenOffManager.ifStartTimer = true;
            this.mScreenEntity = 0;
            onSiriWaitHandler();
            this.mSiriWaitEndTimer.schedule(this.mSiriWaitEndTask, 4000L);
        }

        public void stopSiriWaitTimer() {
            LogUtil.info(CpScreenOffManager.TAG, "stopSiriWaitTimer");
            boolean unused = CpScreenOffManager.ifStartTimer = false;
            this.mSiriWaitEndTask.cancel();
        }

        public void onSiriWaitHandler() {
            this.mSiriWaitEndTask = new TimerTask() { // from class: com.ts.carplay.lib.resourcemanager.CpScreenOffManager.ScreenOffManagerStateMachine.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    LogUtil.info(CpScreenOffManager.TAG, "onSiriWaitEndHandler mScreenEntity = " + ScreenOffManagerStateMachine.this.mScreenEntity);
                    ScreenOffManagerStateMachine.this.stopSiriWaitTimer();
                    LogUtil.info(CpScreenOffManager.TAG, "Controller still hold the screen, map started");
                    ScreenOffManagerStateMachine.this.carPlayAppChanged();
                }
            };
        }

        public void carPlayAppChanged() {
            sendMessage(9);
        }

        public void carPlaySiriEnded() {
            sendMessage(8);
        }

        public void screenResourceChanged(int screenEntity) {
            LogUtil.info(CpScreenOffManager.TAG, "screenResourceChanged screenEntity = " + screenEntity);
            if (CpScreenOffManager.ifStartTimer) {
                LogUtil.info(CpScreenOffManager.TAG, "ifStartTimer " + CpScreenOffManager.ifStartTimer);
                if (screenEntity == 2) {
                    stopSiriWaitTimer();
                    carPlaySiriEnded();
                }
            }
            this.mScreenEntity = screenEntity;
        }

        public IState getCurState() {
            return this.mCurState;
        }
    }

    public void screenStatusChangedTo(CpConst.ScreenStatus screenStatus) {
        LogUtil.info(TAG, "screenStatusChangedTo screenStatus = " + screenStatus);
        if (screenStatus == CpConst.ScreenStatus.STATUS_ON) {
            this.mScreenOffStateMachine.sendMessage(1);
            return;
        }
        if (ifStartTimer) {
            LogUtil.info(TAG, "ifStartTimer " + ifStartTimer + " enter screenoff in advance");
            this.mScreenOffStateMachine.stopSiriWaitTimer();
            this.mScreenOffStateMachine.carPlayAppChanged();
        }
        this.mScreenOffStateMachine.sendMessage(2);
    }

    public void carPlayPhoneStatusChanged(CpConst.AppStatus appStatus) {
        LogUtil.info(TAG, "carPlayPhoneStatusChanged appStatus=" + appStatus);
        if (appStatus == CpConst.AppStatus.STATUS_ON) {
            this.mScreenOffStateMachine.sendMessage(3);
        } else {
            this.mScreenOffStateMachine.sendMessage(6);
        }
    }

    public void carPlaySiriStatusChanged(CpConst.AppStatus appStatus) {
        LogUtil.info(TAG, "carPlaySiriStatusChanged appStatus=" + appStatus + " isNeedNotReturnToScreenOff=" + this.isNeedNotReturnToScreenOff);
        if (appStatus == CpConst.AppStatus.STATUS_ON) {
            this.mScreenOffStateMachine.sendMessage(4);
        } else {
            this.mScreenOffStateMachine.sendMessage(7);
        }
    }

    public void setNeedReturnNotToScreenOffSts(boolean status) {
        LogUtil.info(TAG, "setNeedReturnNotToScreenOffSts, status=" + status);
        this.isNeedNotReturnToScreenOff = status;
    }

    public void clearNeedReturnToScreenOffSts() {
        LogUtil.info(TAG, "clearNeedReturnToScreenOffSts");
        this.isNeedNotReturnToScreenOff = false;
    }

    public void topActivityChanged() {
        LogUtil.info(TAG, "topActivityChanged isChangeActivity=" + this.isChangeActivity);
        if (!this.isChangeActivity) {
            this.mScreenOffStateMachine.sendMessage(5);
        }
    }

    public void setReleaseByTakeStatus() {
        LogUtil.info(TAG, "setReleaseByTakeStatus curState=" + this.mScreenOffStateMachine.getCurState());
        if (this.mScreenOffStateMachine.getCurState() == this.mScreenOffStateMachine.mSiriState || this.mScreenOffStateMachine.getCurState() == this.mScreenOffStateMachine.mTelephonyState) {
            this.isReleaseScreenByTake = true;
        }
    }

    public boolean isScreenOff() {
        if (this.mScreenOffStateMachine.mScreenOffState == this.mScreenOffStateMachine.getCurState()) {
            return true;
        }
        return false;
    }

    public boolean isSiriState() {
        if (this.mScreenOffStateMachine.mSiriState == this.mScreenOffStateMachine.getCurState()) {
            return true;
        }
        return false;
    }

    public void carPlayScreenResourceChanged(int screenEntity) {
        this.mScreenOffStateMachine.screenResourceChanged(screenEntity);
    }

    public void onSessionEstablished() {
        LogUtil.info(TAG, "onSessionEstablished");
    }

    public void onSessionDestoryed() {
        LogUtil.info(TAG, "onSessionDestoryed");
        this.mScreenOffStateMachine.sendMessage(11);
    }
}
