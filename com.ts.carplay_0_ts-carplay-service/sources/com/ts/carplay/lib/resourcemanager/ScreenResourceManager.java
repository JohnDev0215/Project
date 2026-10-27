package com.ts.carplay.lib.resourcemanager;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CarPlayResourceStateChange;
import com.ts.carplay.lib.common.CarPlayStartInfo;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.lib.ts.CarPlayStack;
import com.ts.carplay.lib.ts.CarPlayStackCmdHandler;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ScreenResourceManager {
    public static final int ACCESSORY_CONFIRM_WAIT_TIME = 5000;
    private static final int CARPLAY_APP_CONFIRM_TIMER = 1000;
    private static final int CARPLAY_APP_STARTED = 22;
    private static final int CARPLAY_APP_STOPPED = 23;
    private static final int CARPLAY_SCREEN_TEARDOWN = 16;
    private static final int CHECK_APP_IN_TMP_CARPLAY = 28;
    public static final int CONTROLLER_CONFIRM_WAIT_TIME = 5000;
    private static final int CP_APP_STATE_NONE = 0;
    private static final int CP_APP_STATE_PHONE = 2;
    private static final int CP_APP_STATE_SIRI = 1;
    private static final int HU_HIGHEST_SCREEN_START = 3;
    private static final int HU_HIGHEST_SCREEN_STOP = 12;
    private static final int HU_HIGH_SCREEN_START = 2;
    private static final int HU_HIGH_SCREEN_STOP = 13;
    private static final int HU_NORMAL_SCREEN_START = 1;
    private static final int IPHONE_RELEASE_RESOURCE = 8;
    private static final int SCREEN_CHANGED_TO_CARPLAY = 6;
    private static final int SCREEN_CHANGED_TO_TMP_CARPLAY = 25;
    private static final int SCREEN_IN_MAP = 14;
    private static final int SCREEN_RESOURCE_CHANGED_TO_HU = 20;
    private static final int SCREEN_RESOURCE_CHANGE_CONFIRM_TIMER = 1000;
    private static final int SCREEN_TYPE_UNKNOWN = 0;
    private static final String TAG = "CarPlay ScreenResourceManager";
    private static final int TOUCH_DEVICE_LIST = 15;
    private static final int TOUCH_OEM_ICON = 27;
    private static final int VEHICLE_POWER_MODE_OFF = 30;
    private boolean mCarPlayConnected;
    private CarPlayManager mCarPlayManager;
    private HandlerThread mResourceManagerThread;
    private ScreenStateMachine mStateMachine;
    private int mDisPlayExpectedAction = -1;
    private int mTransferInStartInfo = 0;
    private boolean mScreenIsTearDown = false;
    private int mCarPlayAppState = 0;
    private IState currentState = null;
    private IState mStartInfoState = null;
    private boolean mIsPopupShowing = false;
    private boolean mIsScreenPowerOff = false;
    private boolean mIsInBackCamera = false;
    private boolean mUserRequest = false;
    private String mBorrowId = "";
    private String mReqBorrowId = "";
    private List<String> mBorrowUserList = new ArrayList();
    private List<String> mBorrowNeverList = new ArrayList();
    private CpConst.ScreenPriorityId mCurScreenId = CpConst.ScreenPriorityId.PRIORITY_LEVEL_DEFAULT;

    public ScreenResourceManager(CarPlayManager manager) {
        HandlerThread handlerThread = new HandlerThread("ScreenResourceManager");
        this.mResourceManagerThread = handlerThread;
        handlerThread.start();
        ScreenStateMachine screenStateMachine = new ScreenStateMachine(TAG, this.mResourceManagerThread.getLooper());
        this.mStateMachine = screenStateMachine;
        screenStateMachine.start();
        this.mCarPlayManager = manager;
        this.mCarPlayConnected = false;
    }

    private class ScreenStateMachine extends StateMachine {
        private IState mCurState;
        private IdleState mIdleState;
        private InBorrowNever mInBorrowNever;
        private InBorrowUser mInBorrowUser;
        private InCarPlay mInCarPlay;
        private InTake mInTake;
        private Message mPreMessage;
        private IState mPreState;
        public int mScreenEntity;
        public int mScreenPerEntity;

        public ScreenStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mInCarPlay = new InCarPlay();
            this.mInTake = new InTake();
            this.mInBorrowUser = new InBorrowUser();
            this.mInBorrowNever = new InBorrowNever();
            IdleState idleState = this.mIdleState;
            this.mPreState = idleState;
            this.mScreenEntity = 0;
            this.mScreenPerEntity = 0;
            this.mCurState = idleState;
            addState(idleState);
            addState(this.mInCarPlay);
            addState(this.mInTake);
            addState(this.mInBorrowUser);
            addState(this.mInBorrowNever);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            private boolean isFirstChange = false;

            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mCurState = screenStateMachine.mIdleState;
                LogUtil.info(ScreenResourceManager.TAG, "IdleState enter");
                this.isFirstChange = true;
                ScreenResourceManager.this.currentState = ScreenStateMachine.this.getCurrentState();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                LogUtil.info(ScreenResourceManager.TAG, "IdleState message.what = " + message.what);
                int i = message.what;
                if (i == 1) {
                    LogUtil.info(ScreenResourceManager.TAG, "IdleState HU_NORMAL_SCREEN_START");
                    ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                    ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                    screenStateMachine.transitionTo(screenStateMachine.mInTake);
                    return true;
                }
                if (i == 2) {
                    LogUtil.info(ScreenResourceManager.TAG, "IdleState HU_HIGH_SCREEN_START");
                    ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                    ScreenResourceManager.this.addBorrowUserId(ScreenResourceManager.this.getBorrowId());
                    ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, ScreenResourceManager.this.getBorrowId());
                    ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                    screenStateMachine2.transitionTo(screenStateMachine2.mInBorrowUser);
                    return true;
                }
                if (i != 3) {
                    if (i == 6) {
                        LogUtil.info(ScreenResourceManager.TAG, "IdleState SCREEN_CHANGED_TO_CARPLAY");
                        ScreenStateMachine screenStateMachine3 = ScreenStateMachine.this;
                        screenStateMachine3.transitionTo(screenStateMachine3.mInCarPlay);
                        return true;
                    }
                    return false;
                }
                LogUtil.info(ScreenResourceManager.TAG, "IdleState HU_HIGHEST_SCREEN_START");
                ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                ScreenResourceManager.this.addBorrowNeverId(ScreenResourceManager.this.getBorrowId());
                ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, ScreenResourceManager.this.getBorrowId());
                ScreenStateMachine screenStateMachine4 = ScreenStateMachine.this;
                screenStateMachine4.transitionTo(screenStateMachine4.mInBorrowNever);
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mPreMessage = screenStateMachine.getCurrentMessage();
                ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                screenStateMachine2.mPreState = screenStateMachine2.getCurState();
                ScreenResourceManager.this.mScreenIsTearDown = false;
            }
        }

        class InCarPlay extends State {
            InCarPlay() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mCurState = screenStateMachine.mInCarPlay;
                LogUtil.info(ScreenResourceManager.TAG, "PreState:" + ScreenStateMachine.this.getPreState().getName());
                LogUtil.info(ScreenResourceManager.TAG, "InCarPlay enter");
                ScreenResourceManager.this.clearBorrowNeverListNoCmd();
                ScreenResourceManager.this.clearBorrowUserListNoCmd();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    LogUtil.info(ScreenResourceManager.TAG, "InCarPlay HU_NORMAL_SCREEN_START or TOUCH_DEVICE_LIST");
                    if (ScreenResourceManager.this.mStateMachine.mScreenEntity == 1) {
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                    }
                    ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                    screenStateMachine.transitionTo(screenStateMachine.mInTake);
                    return true;
                }
                if (i == 2) {
                    LogUtil.info(ScreenResourceManager.TAG, "InCarPlay HU_HIGH_SCREEN_START");
                    if (ScreenResourceManager.this.mStateMachine.mScreenEntity == 1) {
                        ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                        ScreenResourceManager.this.addBorrowUserId(ScreenResourceManager.this.getBorrowId());
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, ScreenResourceManager.this.getBorrowId());
                    }
                    ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                    screenStateMachine2.transitionTo(screenStateMachine2.mInBorrowUser);
                    return true;
                }
                if (i != 3) {
                    if (i == 8) {
                        LogUtil.info(ScreenResourceManager.TAG, "InCarPlay IPHONE_RELEASE_RESOURCE");
                        ScreenResourceManager.this.releaseScreenFocus();
                        return false;
                    }
                    return false;
                }
                LogUtil.info(ScreenResourceManager.TAG, "InCarPlay HU_HIGHEST_SCREEN_START");
                ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                ScreenResourceManager.this.addBorrowNeverId(ScreenResourceManager.this.getBorrowId());
                ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, ScreenResourceManager.this.getBorrowId());
                ScreenStateMachine screenStateMachine3 = ScreenStateMachine.this;
                screenStateMachine3.transitionTo(screenStateMachine3.mInBorrowNever);
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mPreMessage = screenStateMachine.getCurrentMessage();
                ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                screenStateMachine2.mPreState = screenStateMachine2.getCurState();
                ScreenResourceManager.this.mScreenIsTearDown = false;
            }
        }

        class InTake extends State {
            InTake() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mCurState = screenStateMachine.mInTake;
                LogUtil.info(ScreenResourceManager.TAG, "PreState:" + ScreenStateMachine.this.getPreState().getName());
                LogUtil.info(ScreenResourceManager.TAG, "InTake enter");
                ScreenResourceManager.this.clearBorrowNeverListNoCmd();
                ScreenResourceManager.this.clearBorrowUserListNoCmd();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    if (ScreenResourceManager.this.mStateMachine.mScreenEntity == 1) {
                        LogUtil.info(ScreenResourceManager.TAG, "InTake HU_NORMAL_SCREEN_START");
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                        return true;
                    }
                    return false;
                }
                if (i == 2) {
                    LogUtil.info(ScreenResourceManager.TAG, "InTake HU_HIGH_SCREEN_START");
                    ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                    ScreenResourceManager.this.addBorrowUserId(ScreenResourceManager.this.getBorrowId());
                    ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, ScreenResourceManager.this.getBorrowId());
                    ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                    screenStateMachine.transitionTo(screenStateMachine.mInBorrowUser);
                    return true;
                }
                if (i != 3) {
                    if (i == 6) {
                        LogUtil.info(ScreenResourceManager.TAG, "InTake SCREEN_CHANGED_TO_CARPLAY");
                        ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                        screenStateMachine2.transitionTo(screenStateMachine2.mInCarPlay);
                        return true;
                    }
                    return false;
                }
                LogUtil.info(ScreenResourceManager.TAG, "InTake HU_HIGHEST_SCREEN_START");
                ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                ScreenResourceManager.this.addBorrowNeverId(ScreenResourceManager.this.getBorrowId());
                ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, ScreenResourceManager.this.getBorrowId());
                ScreenStateMachine screenStateMachine3 = ScreenStateMachine.this;
                screenStateMachine3.transitionTo(screenStateMachine3.mInBorrowNever);
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mPreMessage = screenStateMachine.getCurrentMessage();
                ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                screenStateMachine2.mPreState = screenStateMachine2.getCurState();
                ScreenResourceManager.this.mScreenIsTearDown = false;
            }
        }

        class InBorrowUser extends State {
            InBorrowUser() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mCurState = screenStateMachine.mInBorrowUser;
                LogUtil.info(ScreenResourceManager.TAG, "PreState:" + ScreenStateMachine.this.getPreState().getName());
                LogUtil.info(ScreenResourceManager.TAG, "InBorrowUser enter mIsPopupShowing = " + ScreenResourceManager.this.mIsPopupShowing);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    LogUtil.info(ScreenResourceManager.TAG, "InBorrowUser HU_NOMAL_SCREEN_START");
                    if (ScreenResourceManager.this.mStartInfoState != ScreenResourceManager.this.mStateMachine.mInBorrowUser) {
                        if (ScreenResourceManager.this.mStateMachine.getPreState() == ScreenResourceManager.this.mStateMachine.mInTake) {
                            ScreenResourceManager.this.clearBorrowUserList();
                        } else {
                            ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                        }
                        ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                        screenStateMachine.transitionTo(screenStateMachine.mInTake);
                        return true;
                    }
                    int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN);
                    CarPlayLastUserMode.getInstance();
                    if (lastUserModeState == 1) {
                        ScreenResourceManager.this.mStateMachine.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, ScreenResourceManager.this.getBorrowId());
                    } else {
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                    }
                    ScreenResourceManager.this.mStartInfoState = null;
                    ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                    screenStateMachine2.transitionTo(screenStateMachine2.mInTake);
                    return true;
                }
                if (i == 2) {
                    if (!ScreenResourceManager.this.isInBorrowUserList(ScreenResourceManager.this.getReqBorrowId())) {
                        ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                        ScreenResourceManager.this.addBorrowUserId(ScreenResourceManager.this.getBorrowId());
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, ScreenResourceManager.this.getBorrowId());
                    } else if (!ScreenResourceManager.this.getReqBorrowId().equals(ScreenResourceManager.this.getBorrowId())) {
                        ScreenStateMachine.this.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, ScreenResourceManager.this.getBorrowId());
                        ScreenResourceManager.this.removeBorrowUserId(ScreenResourceManager.this.getBorrowId());
                        ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                    }
                    return true;
                }
                if (i == 3) {
                    LogUtil.info(ScreenResourceManager.TAG, "InBorrowUser HU_HIGHEST_SCREEN_START");
                    ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                    ScreenResourceManager.this.addBorrowNeverId(ScreenResourceManager.this.getBorrowId());
                    ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, ScreenResourceManager.this.getBorrowId());
                    ScreenStateMachine screenStateMachine3 = ScreenStateMachine.this;
                    screenStateMachine3.transitionTo(screenStateMachine3.mInBorrowNever);
                    return true;
                }
                if (i == 6) {
                    LogUtil.info(ScreenResourceManager.TAG, "InBorrowUser SCREEN_CHANGED_TO_CARPLAY");
                    if (ScreenResourceManager.this.mStateMachine.mScreenEntity == 1) {
                        if ("displayOff".equals(ScreenResourceManager.this.getBorrowId()) && ScreenResourceManager.this.mDisPlayExpectedAction == 2) {
                            ScreenResourceManager.this.clearBorrowUserList();
                        }
                    } else {
                        ScreenResourceManager.this.clearBorrowUserList();
                    }
                    ScreenStateMachine screenStateMachine4 = ScreenStateMachine.this;
                    screenStateMachine4.transitionTo(screenStateMachine4.mInCarPlay);
                    return true;
                }
                if (i == 8) {
                    boolean release = ScreenResourceManager.this.mStateMachine.mScreenPerEntity == 2;
                    LogUtil.info(ScreenResourceManager.TAG, "InBorrowUser IPHONE_RELEASE_RESOURCE release:" + release);
                    if (!release) {
                        return false;
                    }
                    ScreenResourceManager.this.releaseScreenFocus();
                    return false;
                }
                return false;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mPreMessage = screenStateMachine.getCurrentMessage();
                ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                screenStateMachine2.mPreState = screenStateMachine2.getCurState();
            }
        }

        class InBorrowNever extends State {
            InBorrowNever() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mCurState = screenStateMachine.mInBorrowNever;
                LogUtil.info(ScreenResourceManager.TAG, "PreState:" + ScreenStateMachine.this.getPreState().getName());
                LogUtil.info(ScreenResourceManager.TAG, "InBorrowNever enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    if (ScreenResourceManager.this.mStartInfoState != ScreenResourceManager.this.mStateMachine.mInBorrowNever) {
                        if (ScreenResourceManager.this.mStateMachine.getPreState() == ScreenResourceManager.this.mStateMachine.mInTake) {
                            ScreenResourceManager.this.clearBorrowNeverList();
                            ScreenResourceManager.this.clearBorrowUserList();
                        } else {
                            ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                        }
                        ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                        screenStateMachine.transitionTo(screenStateMachine.mInTake);
                        return true;
                    }
                    int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN);
                    CarPlayLastUserMode.getInstance();
                    if (lastUserModeState == 1) {
                        ScreenResourceManager.this.mStateMachine.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, ScreenResourceManager.this.getBorrowId());
                    } else {
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
                    }
                    ScreenResourceManager.this.mStartInfoState = null;
                    ScreenStateMachine screenStateMachine2 = ScreenStateMachine.this;
                    screenStateMachine2.transitionTo(screenStateMachine2.mInTake);
                    return true;
                }
                if (i == 2) {
                    ScreenResourceManager.this.clearBorrowNeverList();
                    ScreenStateMachine screenStateMachine3 = ScreenStateMachine.this;
                    screenStateMachine3.transitionTo(screenStateMachine3.mInBorrowUser);
                    return true;
                }
                if (i == 3) {
                    if (!ScreenResourceManager.this.isInBorrowNeverList(ScreenResourceManager.this.getReqBorrowId())) {
                        ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                        ScreenResourceManager.this.addBorrowNeverId(ScreenResourceManager.this.getBorrowId());
                        ScreenStateMachine.this.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER, ScreenResourceManager.this.getBorrowId());
                    } else if (!ScreenResourceManager.this.getReqBorrowId().equals(ScreenResourceManager.this.getBorrowId())) {
                        ScreenStateMachine.this.releaseResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW, ScreenResourceManager.this.getBorrowId());
                        ScreenResourceManager.this.removeBorrowNeverId(ScreenResourceManager.this.getBorrowId());
                        ScreenResourceManager.this.setBorrowId(ScreenResourceManager.this.getReqBorrowId());
                    }
                    return true;
                }
                if (i == 6) {
                    ScreenResourceManager.this.clearBorrowNeverList();
                    ScreenResourceManager.this.clearBorrowUserList();
                    ScreenStateMachine screenStateMachine4 = ScreenStateMachine.this;
                    screenStateMachine4.transitionTo(screenStateMachine4.mInCarPlay);
                    return true;
                }
                if (i == 8) {
                    boolean release = ScreenResourceManager.this.mStateMachine.mScreenPerEntity == 2;
                    LogUtil.info(ScreenResourceManager.TAG, "InBorrowNever IPHONE_RELEASE_RESOURCE release:" + release);
                    if (!release) {
                        return false;
                    }
                    ScreenResourceManager.this.releaseScreenFocus();
                    return false;
                }
                return false;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                ScreenStateMachine screenStateMachine = ScreenStateMachine.this;
                screenStateMachine.mPreState = screenStateMachine.getCurState();
            }
        }

        public IState getPreState() {
            return this.mPreState;
        }

        public void launchApp(int app) {
            LogUtil.info(ScreenResourceManager.TAG, "launchApp app = " + app);
            CarPlayStack.getInstance().appLaunch(app);
        }

        public void screenResourceChangeConfirm() {
            LogUtil.info(ScreenResourceManager.TAG, "screenResourceChangeConfirm mScreenIsTearDown = " + ScreenResourceManager.this.mScreenIsTearDown);
            if (ScreenResourceManager.this.mScreenIsTearDown) {
                try {
                    new Thread(new Runnable() { // from class: com.ts.carplay.lib.resourcemanager.ScreenResourceManager.ScreenStateMachine.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SystemClock.sleep(1000L);
                            LogUtil.info(ScreenResourceManager.TAG, "screenResourceChangeConfirm after sleep mScreenEntity = " + ScreenStateMachine.this.mScreenEntity);
                            if (ScreenStateMachine.this.mScreenEntity == 2) {
                                ScreenStateMachine.this.sendMessage(20);
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    LogUtil.info(ScreenResourceManager.TAG, "screenResourceChangeConfirm Exception");
                }
            }
        }

        public void acquireResource(CpConst.CarPlayTransfer transfer, CpConst.CarPlayConstraint constraint, String borrowId) {
            if (!ScreenResourceManager.this.mCarPlayConnected) {
                LogUtil.info(ScreenResourceManager.TAG, "acquireResource carplay not connected");
                return;
            }
            LogUtil.info(ScreenResourceManager.TAG, "[ATS]acquireResource transfer:" + CpConst.CarPlayTransfer.toString(transfer) + " constraint:" + CpConst.CarPlayConstraint.toString(constraint));
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
            CarPlayStackCmdHandler.getInstance().acquireResource(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN.ordinal(), transfer, stateChange);
        }

        public void releaseResource(CpConst.CarPlayTransfer transfer, String borrowId) {
            if (!ScreenResourceManager.this.mCarPlayConnected) {
                LogUtil.info(ScreenResourceManager.TAG, "releaseResource carplay not connected");
                return;
            }
            LogUtil.info(ScreenResourceManager.TAG, "[ATS]releaseScreenResource transfer:" + CpConst.CarPlayTransfer.toString(transfer) + " curEntity: " + this.mScreenEntity);
            if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW) {
                CarPlayResourceStateChange stateChange = new CarPlayResourceStateChange();
                stateChange.transferPriority = CpConst.CarPlayPriority.CARPLAY_PRIORITY_INVALID;
                stateChange.takeConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID;
                stateChange.borrowOrUnborrowConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID;
                stateChange.borrowId = borrowId;
                CarPlayStackCmdHandler.getInstance().acquireResource(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN.ordinal(), transfer, stateChange);
                return;
            }
            CarPlayStackCmdHandler.getInstance().releaseResource(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN.ordinal(), transfer.ordinal());
        }

        public void requestUi() {
            LogUtil.info(ScreenResourceManager.TAG, "requestUi");
            if (!ScreenResourceManager.this.mCarPlayConnected) {
                LogUtil.info(ScreenResourceManager.TAG, "releaseResource carplay not connected");
            } else {
                CarPlayStackCmdHandler.getInstance().requestUi();
            }
        }

        public IState getCurState() {
            return this.mCurState;
        }
    }

    public void deviceListTouched() {
        LogUtil.info(TAG, "deviceListTouched curState:" + this.mStateMachine.getCurState().getName());
        if (this.mStateMachine.mInBorrowUser == this.mStateMachine.getCurState() || this.mStateMachine.mInCarPlay == this.mStateMachine.getCurState()) {
            this.mStateMachine.sendMessage(15);
            CpScreenOffManager.getInstance().topActivityChanged();
        }
    }

    public void screenChangedToCarPlay(boolean isQuested) {
        LogUtil.info(TAG, "screenChangedToCarPlay curState:" + this.mStateMachine.getCurState().getName() + " mCarPlayAppState:" + this.mCarPlayAppState + " isQuested:" + isQuested);
        if (this.mStateMachine.mInCarPlay != this.mStateMachine.getCurState()) {
            if (isQuested) {
                this.mStateMachine.sendMessage(25);
                this.mStateMachine.sendMessageDelayed(28, 1000L);
            } else {
                this.mStateMachine.sendMessage(6);
            }
        }
    }

    private void normalScreenChanged() {
        LogUtil.info(TAG, "normalScreenChanged curState:" + this.mStateMachine.getCurState().getName());
        CpScreenOffManager.getInstance().topActivityChanged();
        if (this.mStateMachine.mInTake != this.mStateMachine.getCurState()) {
            CpScreenOffManager.getInstance().setReleaseByTakeStatus();
            this.mStateMachine.sendMessage(1);
        }
    }

    private void highScreenChanged() {
        LogUtil.info(TAG, "highScreenChanged cur:" + this.mStateMachine.getCurState().getName());
        if (this.mStateMachine.mInBorrowUser != this.mStateMachine.getCurState()) {
            this.mStateMachine.sendMessage(2);
        }
    }

    private void highestScreenChanged() {
        LogUtil.info(TAG, "highestScreenChanged cur:" + this.mStateMachine.getCurState().getName());
        if (this.mStateMachine.mInBorrowNever != this.mStateMachine.getCurState()) {
            this.mStateMachine.sendMessage(3);
        }
    }

    public void highScreenStop() {
        LogUtil.info(TAG, "highScreenStop cur:" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(13);
    }

    public void highestScreenStop() {
        LogUtil.info(TAG, "highestScreenStop cur:" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(12);
    }

    private void carplayScreenChanged(boolean isUserInitiated) {
    }

    public void accessoryScreenChange(int priority, boolean playbk, String borrowId, int action) {
        LogUtil.info(TAG, "[ATS]accessoryScreenChange, priority = " + priority + " playbk = " + playbk + " borrowId = " + borrowId + " action = " + action);
        if (borrowId != null && !borrowId.isEmpty()) {
            setReqBorrowId(borrowId);
        }
        if (action == 1) {
            if (priority != 0) {
                if (priority == 1) {
                    this.mStateMachine.sendMessage(6);
                    return;
                } else if (priority == 6) {
                    this.mStateMachine.sendMessage(2);
                    return;
                } else {
                    if (priority == 7) {
                        this.mStateMachine.sendMessage(3);
                        return;
                    }
                    return;
                }
            }
            this.mStateMachine.sendMessage(1);
        }
    }

    public void screenResourceChanged(int screenEntity, int perScreenEntity) {
        LogUtil.info(TAG, "[ATS]screenResourceChanged entity:" + screenEntity + " curState:" + this.mStateMachine.getCurState().getName() + " curEntity:" + this.mStateMachine.mScreenEntity + "perScreenEntity" + perScreenEntity);
        this.mStateMachine.mScreenPerEntity = perScreenEntity;
        if (this.mStateMachine.mScreenEntity == 1 && screenEntity == 2) {
            this.mCarPlayManager.removeJudgeProjectionMsg();
        }
        if (screenEntity == 2) {
            CpScreenOffManager.getInstance().carPlayScreenResourceChanged(2);
            if (this.mStateMachine.mScreenEntity == 2) {
                LogUtil.info(TAG, "ignore overlap");
                return;
            } else if (this.mStateMachine.mScreenEntity == 0) {
                this.mStateMachine.mScreenEntity = 2;
                LogUtil.info(TAG, "ignore CARPLAY_ENTITY_NONE");
                return;
            } else {
                this.mStateMachine.mScreenEntity = 2;
                this.mStateMachine.sendMessage(8);
                return;
            }
        }
        if (screenEntity == 1) {
            CpScreenOffManager.getInstance().carPlayScreenResourceChanged(1);
            if (this.mStateMachine.mScreenEntity == 1) {
                LogUtil.info(TAG, "ignore overlap");
                return;
            } else {
                this.mStateMachine.mScreenEntity = 1;
                return;
            }
        }
        if (screenEntity == 3) {
            this.mStateMachine.mScreenEntity = 3;
        }
    }

    public int getEntity() {
        return this.mStateMachine.mScreenEntity;
    }

    public void notification(int notify, int param1, int param2, int param3) {
        LogUtil.info(TAG, "notification: notify:" + notify + " param1:" + param1 + " param2:" + param2 + " param3:" + param3);
        if (notify == 2) {
            this.mStateMachine.sendMessage(16);
        }
    }

    public int releaseScreenFocus() {
        LogUtil.info(TAG, "releaseScreenFocus");
        this.mCarPlayManager.releaseScreen();
        return 0;
    }

    public void screenStatusChangedTo(CpConst.ScreenStatus screenStatus) {
        LogUtil.info(TAG, "screenStatusChangedTo screenStatus = " + screenStatus);
        if (screenStatus == CpConst.ScreenStatus.STATUS_OFF) {
            this.mIsScreenPowerOff = true;
            this.mStateMachine.sendMessage(2);
            return;
        }
        if (screenStatus == CpConst.ScreenStatus.STATUS_ON) {
            LogUtil.info(TAG, "screenStatusChangedTo mIsPopupShowing = " + this.mIsPopupShowing);
            this.mIsScreenPowerOff = false;
            if (this.mIsPopupShowing) {
                LogUtil.info(TAG, "Ignore screen on during popup showing");
            } else {
                this.mStateMachine.sendMessage(13);
            }
        }
    }

    public void powerStatusChangedTo(CpConst.PowerStatus powerStatus) {
        LogUtil.info(TAG, "powerStatusChangedTo powerStatus = " + powerStatus);
        if (powerStatus == CpConst.PowerStatus.STATUS_OFF) {
            this.mIsScreenPowerOff = true;
            this.mStateMachine.sendMessage(2);
            return;
        }
        if (powerStatus == CpConst.PowerStatus.STATUS_ON) {
            LogUtil.info(TAG, "powerStatusChangedTo mIsPopupShowing = " + this.mIsPopupShowing);
            this.mIsScreenPowerOff = false;
            if (this.mIsPopupShowing) {
                LogUtil.info(TAG, "Ignore power on during popup showing");
            } else {
                this.mStateMachine.sendMessage(13);
            }
        }
    }

    public void popupStatusChangedTo(CpConst.PowerStatus status) {
        LogUtil.info(TAG, "popupStatusChangedTo status = " + status);
        if (status == CpConst.PowerStatus.STATUS_OFF) {
            LogUtil.info(TAG, "popupStatusChangedTo mIsScreenPowerOff = " + this.mIsScreenPowerOff);
            this.mIsPopupShowing = false;
            if (this.mIsScreenPowerOff) {
                LogUtil.info(TAG, "Ignore popup off during screen/power off");
                return;
            } else {
                this.mStateMachine.sendMessage(13);
                return;
            }
        }
        if (status == CpConst.PowerStatus.STATUS_ON) {
            this.mIsPopupShowing = true;
            this.mStateMachine.sendMessage(2);
        }
    }

    public void backCameraStatusChangedTo(CpConst.AppState state) {
        LogUtil.info(TAG, "backCameraStatusChangedTo AppState = " + state);
        if (state == CpConst.AppState.APP_ON) {
            this.mIsInBackCamera = true;
            this.mStateMachine.sendMessage(3);
        } else if (state == CpConst.AppState.APP_OFF) {
            this.mStateMachine.sendMessage(12);
            this.mIsInBackCamera = false;
        }
    }

    public void forceChangeToTakeState() {
        LogUtil.info(TAG, "forceChangeToTakeState,curState=" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.acquireResource(CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE, CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER, "");
    }

    public void vehiclePowerModeChangedTo(boolean ignoff) {
        LogUtil.info(TAG, "vehiclePowerModeChangedTo powerMode = " + ignoff);
        if (ignoff) {
            this.mStateMachine.sendMessage(30);
        }
    }

    public void carplayScreenChangedBorrow() {
        LogUtil.info(TAG, "carplayScreenChangedBorrow,curState=" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(2);
    }

    public void carplayScreenChangedUnBorrow() {
        LogUtil.info(TAG, "carplayScreenChangedUnBorrow,curState=" + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(13);
    }

    public void nativePhoneStatusChangedTo(CpConst.AppState state) {
        LogUtil.info(TAG, "nativePhoneStatusChangedTo AppState = " + state);
        if (state == CpConst.AppState.APP_ON) {
            this.mStateMachine.sendMessage(2);
        } else if (state == CpConst.AppState.APP_OFF) {
            this.mStateMachine.sendMessage(13);
        }
    }

    public void phoneCallStateChanged(CpConst.PhoneStatus phoneCallState) {
    }

    public void setDeviceMirroringRunning(boolean on, boolean isRunning) {
        LogUtil.info(TAG, "setDeviceMirroringRunning,isRunning =" + isRunning);
        this.mCarPlayManager.setDeviceMirroringRunning(on, isRunning);
    }

    public int screenResourceRequest() {
        LogUtil.info(TAG, "[ATS]screenResourceRequest,curState=" + this.mStateMachine.getCurState().getName());
        int res = this.mCarPlayManager.requestVideoFocus();
        if (res == CpConst.VideoFocusReqResult.VIDEOFOCUS_REQUEST_GRANTED.ordinal()) {
            LogUtil.info(TAG, "screen focus request success");
            return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_AVAILABLE.ordinal();
        }
        LogUtil.info(TAG, "screen focus request fail");
        return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_BUSY.ordinal();
    }

    public void recordStartInfo(int transfer) {
        LogUtil.info(TAG, "screen: recordStartInfo transfer = " + transfer);
        this.mTransferInStartInfo = transfer;
    }

    public CpConst.ResourceStatus getCurResourceState() {
        LogUtil.info(TAG, "screen: getCurResourceState");
        if (this.mStateMachine.getCurState() != null) {
            LogUtil.info(TAG, " curState:" + this.mStateMachine.getCurState().getName());
        }
        if (this.mStateMachine.getCurState() != this.mStateMachine.mInBorrowNever) {
            if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowUser) {
                return CpConst.ResourceStatus.RESOURCE_IN_BORROW_USER;
            }
            int lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN);
            if (lastUserModeState == 1) {
                LogUtil.info(TAG, "Last User Mode is CarPlay");
                return CpConst.ResourceStatus.RESOURCE_IN_NICE_TO_HAVE;
            }
            return CpConst.ResourceStatus.RESOURCE_IN_TAKE;
        }
        return CpConst.ResourceStatus.RESOURCE_IN_BORROW_NEVER;
    }

    public boolean isBorrowed() {
        if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowUser || this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowNever) {
            return true;
        }
        return false;
    }

    public CarPlayStartInfo getLastResourceState() {
        int lastUserModeState;
        LogUtil.info(TAG, "screen: getLastResourceState");
        if (this.mStateMachine.getCurState() != null) {
            LogUtil.info(TAG, " curState:" + this.mStateMachine.getCurState().getName());
        }
        if (this.mStateMachine.getPreState() != null) {
            LogUtil.info(TAG, " PreState:" + this.mStateMachine.getPreState().getName());
        }
        this.mStartInfoState = this.mStateMachine.getCurState();
        CarPlayStartInfo outStartInfo = new CarPlayStartInfo();
        if (this.mUserRequest) {
            lastUserModeState = 1;
        } else {
            lastUserModeState = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN);
        }
        outStartInfo.screenTransferInfo.borrowId = getBorrowId();
        LogUtil.info(TAG, "getLastResourceState lastUserModeState = " + lastUserModeState + ", borrowId=" + getBorrowId());
        if (this.mStateMachine.getCurState() != this.mStateMachine.mInBorrowUser) {
            if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowNever) {
                outStartInfo.screenTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal();
                outStartInfo.screenTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
                outStartInfo.screenTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
                outStartInfo.screenTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_NEVER.ordinal();
                recordStartInfo(3);
                return outStartInfo;
            }
            if (lastUserModeState == 0) {
                LogUtil.info(TAG, " decide to donot launch carplay screen.");
                outStartInfo.screenTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal();
                outStartInfo.screenTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
                outStartInfo.screenTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
                outStartInfo.screenTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
                recordStartInfo(1);
            }
            if (1 == lastUserModeState) {
                LogUtil.info(TAG, " decide to launch carplay screen ");
                outStartInfo.screenTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal();
                outStartInfo.screenTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_NICETOHAVE.ordinal();
                outStartInfo.screenTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
                outStartInfo.screenTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
                recordStartInfo(4);
            }
            return outStartInfo;
        }
        outStartInfo.screenTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal();
        outStartInfo.screenTransferInfo.transferPriorityValue = CpConst.CarPlayPriority.CARPLAY_PRIORITY_USER.ordinal();
        outStartInfo.screenTransferInfo.takeConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
        outStartInfo.screenTransferInfo.borrowOrUnborrowConstraintValue = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
        recordStartInfo(2);
        return outStartInfo;
    }

    public void setUserRequest(boolean userRequest) {
        this.mUserRequest = userRequest;
    }

    private int screenTypeToPriority(int screenId) {
        LogUtil.info(TAG, "screenTypeToPriority screenId=" + screenId);
        return 0;
    }

    public void onSessionEstablished() {
        LogUtil.info(TAG, "onSessionEstablished, mTransferInStartInfo = " + this.mTransferInStartInfo + " currentState = " + this.mStateMachine.getCurState().getName());
        this.mCarPlayConnected = true;
    }

    public void onSessionDestoryed(LinkDevice device) {
        LogUtil.info(TAG, "onSessionDestoryed curstate:" + this.mStateMachine.getCurState().getName() + " mScreenPerEntity:" + this.mStateMachine.mScreenPerEntity);
        if (this.mStateMachine.mScreenPerEntity == 1) {
            CarPlayLastUserMode carPlayLastUserMode = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode.setLastUserMode(carPlayResource, 1);
        } else if (this.mStateMachine.mScreenPerEntity == 0) {
            if (this.mStateMachine.getPreState() == this.mStateMachine.mInCarPlay && (this.mStateMachine.mCurState == this.mStateMachine.mInBorrowNever || this.mStateMachine.mCurState == this.mStateMachine.mInBorrowUser)) {
                CarPlayLastUserMode carPlayLastUserMode2 = CarPlayLastUserMode.getInstance();
                CpConst.CarPlayResource carPlayResource2 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
                CarPlayLastUserMode.getInstance();
                carPlayLastUserMode2.setLastUserMode(carPlayResource2, 1);
            } else if (this.mStateMachine.mScreenEntity == 1) {
                CarPlayLastUserMode carPlayLastUserMode3 = CarPlayLastUserMode.getInstance();
                CpConst.CarPlayResource carPlayResource3 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
                CarPlayLastUserMode.getInstance();
                carPlayLastUserMode3.setLastUserMode(carPlayResource3, 1);
            } else {
                CarPlayLastUserMode carPlayLastUserMode4 = CarPlayLastUserMode.getInstance();
                CpConst.CarPlayResource carPlayResource4 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
                CarPlayLastUserMode.getInstance();
                carPlayLastUserMode4.setLastUserMode(carPlayResource4, 0);
            }
        } else {
            CarPlayLastUserMode carPlayLastUserMode5 = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource5 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode5.setLastUserMode(carPlayResource5, 0);
        }
        this.mStateMachine.mScreenEntity = 2;
        this.mStartInfoState = null;
        this.mCarPlayConnected = false;
        this.mUserRequest = false;
    }

    public boolean isInCarPlayState() {
        LogUtil.info(TAG, "isInCarPlayState curstate" + this.mStateMachine.getCurState());
        if (this.mStateMachine.getCurState() == this.mStateMachine.mInCarPlay) {
            return true;
        }
        return false;
    }

    public boolean isScreenShowAllow() {
        LogUtil.info(TAG, "isScreenShowAllow curstate" + this.mStateMachine.getCurState().getName());
        if (this.mStateMachine.getCurState() == this.mStateMachine.mInBorrowNever) {
            return false;
        }
        return true;
    }

    public void clearLocalStatus() {
        LogUtil.info(TAG, "clearLocalStatus");
        this.mStateMachine.mScreenEntity = 0;
        this.mStateMachine.mScreenPerEntity = 0;
    }

    public void changeToCarPlay() {
        LogUtil.info(TAG, "changeToCarPlay, currentstate: " + this.mStateMachine.getCurState().getName());
        this.mStateMachine.sendMessage(14);
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

    public void setDisPlayOffExcepted(int action) {
        this.mDisPlayExpectedAction = action;
    }
}
