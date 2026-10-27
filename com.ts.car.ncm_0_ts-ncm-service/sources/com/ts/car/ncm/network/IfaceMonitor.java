package com.ts.car.ncm.network;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.IState;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;

/* JADX INFO: loaded from: classes.dex */
public class IfaceMonitor extends StateMachine {
    private static final int ADD = 1000;
    static final int BASE = 3000;
    private static final int CHANGE = 1001;
    private static final int CMD_TO_STRING_COUNT = 8;
    static final int EVENT_ALLOW_CONNECT_CS = 3006;
    static final int EVENT_CS_CONNECTED = 3004;
    static final int EVENT_CS_DISCONNECTED = 3005;
    static final int EVENT_IFACE_ADD = 3000;
    static final int EVENT_IFACE_LINK_DOWN = 3003;
    static final int EVENT_IFACE_LINK_UP = 3002;
    static final int EVENT_IFACE_REMOVE = 3001;
    static final int EVENT_NOT_ALLOW_CONNECT_CS = 3007;
    private static final int IFACE_BASE = 1000;
    private static final int REMOVE = 1002;
    private boolean mAdded;
    private Boolean mAllowConnectCs;
    private IAddedState mIAddedState;
    private IBringUpState mIBringUpState;
    private IDefaultState mIDefaultState;
    private IDownState mIDownState;
    private IRemovedState mIRemovedState;
    private IRemovingState mIRemovingState;
    private ITearDownState mITearDownState;
    private IUpState mIUpState;
    private String mIfaceName;
    private boolean mLinkStateUp;
    private ArrayMap<IState, Integer> mStateMap;
    private ExtNetworkTracker mTracker;
    private static final String TAG = IfaceMonitor.class.getSimpleName();
    private static final boolean DBG = Build.IS_USERDEBUG;
    private static String[] sCmdToString = {"EVENT_IFACE_ADD", "EVENT_IFACE_REMOVE", "EVENT_IFACE_LINK_UP", "EVENT_IFACE_LINK_DOWN", "EVENT_CS_CONNECTED", "EVENT_CS_DISCONNECTED", "EVENT_ALLOW_CONNECT_CS", "EVENT_NOT_ALLOW_CONNECT_CS"};

    /* JADX INFO: Access modifiers changed from: private */
    public void printDbgInfo() {
        Log.d(TAG, "mAllowConnectCs:" + this.mAllowConnectCs + " mAdded:" + this.mAdded + " mLinkStateUp:" + this.mLinkStateUp);
    }

    public static IfaceMonitor makeIfaceMonitor(String ifname, Handler handler, ExtNetworkTracker tracker) {
        IfaceMonitor ifMonitor = new IfaceMonitor(ifname, handler, tracker);
        ifMonitor.start();
        return ifMonitor;
    }

    private IfaceMonitor(String ifname, Handler handler, ExtNetworkTracker tracker) {
        super(ifname, handler);
        this.mIfaceName = null;
        this.mTracker = null;
        this.mStateMap = new ArrayMap<>();
        this.mAllowConnectCs = false;
        this.mAdded = false;
        this.mLinkStateUp = false;
        this.mIDefaultState = new IDefaultState();
        this.mIAddedState = new IAddedState();
        this.mIBringUpState = new IBringUpState();
        this.mIUpState = new IUpState();
        this.mITearDownState = new ITearDownState();
        this.mIDownState = new IDownState();
        this.mIRemovingState = new IRemovingState();
        this.mIRemovedState = new IRemovedState();
        this.mIfaceName = ifname;
        this.mTracker = tracker;
        addState(this.mIDefaultState);
        addState(this.mIAddedState, this.mIDefaultState);
        addState(this.mIBringUpState, this.mIDefaultState);
        addState(this.mIUpState, this.mIDefaultState);
        addState(this.mITearDownState, this.mIDefaultState);
        addState(this.mIDownState, this.mIDefaultState);
        addState(this.mIRemovingState, this.mIDefaultState);
        addState(this.mIRemovedState, this.mIDefaultState);
        setInitialState(this.mIDefaultState);
        int beginStatePos = 0 + 1;
        this.mStateMap.put(this.mIDefaultState, 0);
        int beginStatePos2 = beginStatePos + 1;
        this.mStateMap.put(this.mIAddedState, Integer.valueOf(beginStatePos));
        int beginStatePos3 = beginStatePos2 + 1;
        this.mStateMap.put(this.mIBringUpState, Integer.valueOf(beginStatePos2));
        int beginStatePos4 = beginStatePos3 + 1;
        this.mStateMap.put(this.mIUpState, Integer.valueOf(beginStatePos3));
        int beginStatePos5 = beginStatePos4 + 1;
        this.mStateMap.put(this.mITearDownState, Integer.valueOf(beginStatePos4));
        int beginStatePos6 = beginStatePos5 + 1;
        this.mStateMap.put(this.mIDownState, Integer.valueOf(beginStatePos5));
        int beginStatePos7 = beginStatePos6 + 1;
        this.mStateMap.put(this.mIRemovingState, Integer.valueOf(beginStatePos6));
        int i = beginStatePos7 + 1;
        this.mStateMap.put(this.mIRemovedState, Integer.valueOf(beginStatePos7));
        setDbg(DBG);
    }

    public synchronized void ifaceAdd() {
        Message msg = Message.obtain();
        msg.what = 3000;
        sendMessage(msg);
    }

    public synchronized void ifaceRemove() {
        Message msg = Message.obtain();
        msg.what = EVENT_IFACE_REMOVE;
        sendMessage(msg);
    }

    public void networkAgentConnected() {
        Message msg = Message.obtain();
        msg.what = EVENT_CS_CONNECTED;
        sendMessage(msg);
    }

    public void setAllowConnectCs(boolean allow) {
        Message msg = Message.obtain();
        msg.what = allow ? EVENT_ALLOW_CONNECT_CS : EVENT_NOT_ALLOW_CONNECT_CS;
        sendMessage(msg);
    }

    public void networkAgentDisconnected() {
        Message msg = Message.obtain();
        msg.what = EVENT_CS_DISCONNECTED;
        sendMessage(msg);
    }

    public synchronized void ifaceLinkChange(boolean state) {
        Message msg = Message.obtain();
        msg.what = state ? EVENT_IFACE_LINK_UP : EVENT_IFACE_LINK_DOWN;
        sendMessage(msg);
    }

    private class IDefaultState extends State {
        private IDefaultState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDefaultState enter");
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDefaultState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            int i = msg.what;
            if (i == 3000) {
                Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDefaultState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                IfaceMonitor.this.mAdded = true;
                IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                ifaceMonitor.transitionTo(ifaceMonitor.mIAddedState);
                return true;
            }
            if (i == IfaceMonitor.EVENT_ALLOW_CONNECT_CS) {
                Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDefaultState handle event " + IfaceMonitor.this.getWhatToString(msg.what));
                IfaceMonitor.this.mAllowConnectCs = true;
                return true;
            }
            if (i == IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS) {
                Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDefaultState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                IfaceMonitor.this.mAllowConnectCs = false;
                return true;
            }
            IfaceMonitor.this.log("iface:" + IfaceMonitor.this.mIfaceName + " IDefaultState shouldn't happen but ignore msg.what:" + IfaceMonitor.this.getWhatToString(msg.what));
            return false;
        }
    }

    private class IAddedState extends State {
        private IAddedState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IAddedState enter");
            IfaceMonitor.this.mTracker.updateInterface(IfaceMonitor.this.mIfaceName, 1000, false);
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IAddedState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            int messagekey = msg.what;
            switch (messagekey) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("IAddedState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("IAddedState", false);
                    IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                    ifaceMonitor.transitionTo(ifaceMonitor.mIRemovedState);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                    IfaceMonitor.this.setLinkStateUp("IAddedState", true);
                    if (IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                        IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                        ifaceMonitor2.transitionTo(ifaceMonitor2.mIBringUpState);
                    }
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    IfaceMonitor.this.setLinkStateUp("IAddedState", false);
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IAddedState receive a error message:" + IfaceMonitor.this.getWhatToString(messagekey));
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IAddedState handle event: " + IfaceMonitor.this.getWhatToString(messagekey));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    if (IfaceMonitor.this.mLinkStateUp) {
                        IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                        ifaceMonitor3.transitionTo(ifaceMonitor3.mIBringUpState);
                    }
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IAddedState handle event: " + IfaceMonitor.this.getWhatToString(messagekey));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    return true;
                default:
                    IfaceMonitor.this.log("iface:" + IfaceMonitor.this.mIfaceName + " IAddedState shouldn't happen but ignore msg.what:" + IfaceMonitor.this.getWhatToString(messagekey));
                    return false;
            }
        }
    }

    private class IBringUpState extends State {
        private IBringUpState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IBringUpState enter");
            IfaceMonitor.this.mTracker.updateInterface(IfaceMonitor.this.mIfaceName, IfaceMonitor.CHANGE, true);
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IBringUpState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            switch (msg.what) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("IBringUpState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("IBringUpState", false);
                    IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                    ifaceMonitor.transitionTo(ifaceMonitor.mIRemovingState);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                    IfaceMonitor.this.setLinkStateUp("IBringUpState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    IfaceMonitor.this.setLinkStateUp("IBringUpState", false);
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IBringUpState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    if (IfaceMonitor.this.mLinkStateUp && IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                        IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                        ifaceMonitor2.transitionTo(ifaceMonitor2.mIUpState);
                    } else {
                        IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                        ifaceMonitor3.transitionTo(ifaceMonitor3.mITearDownState);
                    }
                    return true;
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IBringUpState receive a error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IBringUpState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IBringUpState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    return true;
                default:
                    return false;
            }
        }
    }

    private class IUpState extends State {
        private IUpState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IUpStateState enter");
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IUpStateState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            switch (msg.what) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("IUpStateState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("IUpStateState", false);
                    IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                    ifaceMonitor.transitionTo(ifaceMonitor.mIRemovingState);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                    IfaceMonitor.this.setLinkStateUp("IUpStateState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    IfaceMonitor.this.setLinkStateUp("IUpStateState", false);
                    IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                    ifaceMonitor2.transitionTo(ifaceMonitor2.mITearDownState);
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IUpStateState receive a error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IUpStateState handle not expect event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IUpStateState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                    ifaceMonitor3.transitionTo(ifaceMonitor3.mITearDownState);
                    return true;
                default:
                    return false;
            }
        }
    }

    private class ITearDownState extends State {
        private ITearDownState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " ITearDownState enter");
            IfaceMonitor.this.mTracker.updateInterface(IfaceMonitor.this.mIfaceName, IfaceMonitor.CHANGE, false);
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " ITearDownState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            switch (msg.what) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("ITearDownState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("ITearDownState", false);
                    IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                    ifaceMonitor.transitionTo(ifaceMonitor.mIRemovingState);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                    IfaceMonitor.this.setLinkStateUp("ITearDownState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    IfaceMonitor.this.setLinkStateUp("ITearDownState", false);
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " ITearDownState receive a error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    return true;
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " ITearDownState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    if (!IfaceMonitor.this.mLinkStateUp || !IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                        if (IfaceMonitor.this.mLinkStateUp && !IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                            IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                            ifaceMonitor2.transitionTo(ifaceMonitor2.mIAddedState);
                        } else {
                            IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                            ifaceMonitor3.transitionTo(ifaceMonitor3.mIDownState);
                        }
                    } else {
                        IfaceMonitor ifaceMonitor4 = IfaceMonitor.this;
                        ifaceMonitor4.transitionTo(ifaceMonitor4.mIBringUpState);
                    }
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " ITearDownState handle not expect event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " ITearDownState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    return true;
                default:
                    return false;
            }
        }
    }

    private class IDownState extends State {
        private IDownState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDownState enter");
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDownState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            switch (msg.what) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("IDownState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("IDownState", false);
                    IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                    ifaceMonitor.transitionTo(ifaceMonitor.mIRemovedState);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                    IfaceMonitor.this.setLinkStateUp("IDownState", true);
                    if (IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                        IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                        ifaceMonitor2.transitionTo(ifaceMonitor2.mIBringUpState);
                    } else {
                        IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                        ifaceMonitor3.transitionTo(ifaceMonitor3.mIAddedState);
                    }
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    IfaceMonitor.this.setLinkStateUp("IDownState", false);
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDownState receive a error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDownState handle not expect event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IDownState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    return true;
                default:
                    return false;
            }
        }
    }

    private class IRemovingState extends State {
        private IRemovingState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovingState enter");
            IfaceMonitor.this.mTracker.updateInterface(IfaceMonitor.this.mIfaceName, IfaceMonitor.CHANGE, false);
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovingState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            switch (msg.what) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("IRemovingState", true);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("IRemovingState", false);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    IfaceMonitor.this.setLinkStateUp("IRemovingState", msg.what == IfaceMonitor.EVENT_IFACE_LINK_UP);
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovingState receive error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    return true;
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovingState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    if (IfaceMonitor.this.mAdded) {
                        if (IfaceMonitor.this.mLinkStateUp && IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                            IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                            ifaceMonitor.transitionTo(ifaceMonitor.mIBringUpState);
                        } else {
                            IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                            ifaceMonitor2.transitionTo(ifaceMonitor2.mIAddedState);
                        }
                    } else {
                        IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                        ifaceMonitor3.transitionTo(ifaceMonitor3.mIRemovedState);
                    }
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovingState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovingState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    return true;
                default:
                    return false;
            }
        }
    }

    private class IRemovedState extends State {
        private IRemovedState() {
        }

        public void enter() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovedState enter");
            IfaceMonitor.this.mTracker.updateInterface(IfaceMonitor.this.mIfaceName, IfaceMonitor.REMOVE, false);
        }

        public void exit() {
            Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovedState exit");
        }

        public boolean processMessage(Message msg) {
            IfaceMonitor.this.printDbgInfo();
            switch (msg.what) {
                case 3000:
                    IfaceMonitor.this.setIfaceAdded("IRemovedState", true);
                    IfaceMonitor ifaceMonitor = IfaceMonitor.this;
                    ifaceMonitor.transitionTo(ifaceMonitor.mIAddedState);
                    return true;
                case IfaceMonitor.EVENT_IFACE_REMOVE /* 3001 */:
                    IfaceMonitor.this.setIfaceAdded("IRemovedState", false);
                    return true;
                case IfaceMonitor.EVENT_IFACE_LINK_UP /* 3002 */:
                case IfaceMonitor.EVENT_IFACE_LINK_DOWN /* 3003 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovedState revice error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.setLinkStateUp("IRemovedState", msg.what == IfaceMonitor.EVENT_IFACE_LINK_UP);
                    if (IfaceMonitor.this.mLinkStateUp && IfaceMonitor.this.mAllowConnectCs.booleanValue()) {
                        IfaceMonitor ifaceMonitor2 = IfaceMonitor.this;
                        ifaceMonitor2.transitionTo(ifaceMonitor2.mIBringUpState);
                    } else {
                        IfaceMonitor ifaceMonitor3 = IfaceMonitor.this;
                        ifaceMonitor3.transitionTo(ifaceMonitor3.mIAddedState);
                    }
                    return true;
                case IfaceMonitor.EVENT_CS_CONNECTED /* 3004 */:
                case IfaceMonitor.EVENT_CS_DISCONNECTED /* 3005 */:
                    Log.e(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovedState receive a error message:" + IfaceMonitor.this.getWhatToString(msg.what));
                    return true;
                case IfaceMonitor.EVENT_ALLOW_CONNECT_CS /* 3006 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovedState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = true;
                    return true;
                case IfaceMonitor.EVENT_NOT_ALLOW_CONNECT_CS /* 3007 */:
                    Log.d(IfaceMonitor.TAG, "iface:" + IfaceMonitor.this.mIfaceName + " IRemovedState handle event: " + IfaceMonitor.this.getWhatToString(msg.what));
                    IfaceMonitor.this.mAllowConnectCs = false;
                    return true;
                default:
                    return false;
            }
        }
    }

    void dispose() {
        Log.d(TAG, "iface:" + this.mIfaceName + " dispose: call quiteNow()");
        quitNow();
    }

    protected String getWhatToString(int what) {
        return cmdToString(what);
    }

    private static String cmdToString(int cmd) {
        String value = null;
        int cmd2 = cmd - 3000;
        if (cmd2 >= 0) {
            String[] strArr = sCmdToString;
            if (cmd2 < strArr.length) {
                value = strArr[cmd2];
            }
        }
        if (value == null) {
            return "unknown" + Integer.toHexString(cmd2 + 3000);
        }
        return value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLinkStateUp(String state, boolean up) {
        this.mAdded = true;
        if (this.mLinkStateUp == up) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("iface:");
            sb.append(this.mIfaceName);
            sb.append("  handle exception event: ");
            sb.append(up ? "EVENT_IFACE_LINK_UP" : "EVENT_IFACE_LINK_DOWN");
            Log.e(str, sb.toString());
        } else {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("iface:");
            sb2.append(this.mIfaceName);
            sb2.append("  handle event: ");
            sb2.append(up ? "EVENT_IFACE_LINK_UP" : "EVENT_IFACE_LINK_DOWN");
            Log.d(str2, sb2.toString());
        }
        this.mLinkStateUp = up;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIfaceAdded(String state, boolean added) {
        if (this.mAdded == added) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("iface:");
            sb.append(this.mIfaceName);
            sb.append(" ");
            sb.append(state);
            sb.append(" handle exception event: ");
            sb.append(added ? "EVENT_IFACE_ADD" : "EVENT_IFACE_REMOVE");
            Log.e(str, sb.toString());
        } else {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("iface:");
            sb2.append(this.mIfaceName);
            sb2.append(" ");
            sb2.append(state);
            sb2.append(" handle event: ");
            sb2.append(added ? "EVENT_IFACE_ADD" : "EVENT_IFACE_REMOVE");
            Log.d(str2, sb2.toString());
        }
        this.mAdded = added;
        if (!added) {
            this.mLinkStateUp = false;
        }
    }

    public int getState() {
        IState state = getCurrentState();
        return this.mStateMap.get(state).intValue();
    }
}
