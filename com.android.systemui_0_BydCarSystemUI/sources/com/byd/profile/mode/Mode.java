package com.byd.profile.mode;

import android.content.Context;
import com.byd.profile.ProfileManager;
import com.byd.profile.request.RequestManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Mode {
    protected Context mContext;
    protected RequestManager mRequestManager;
    protected State mState;
    protected int modeId;

    public abstract void clear();

    protected abstract RequestManager initRequestManager();

    public abstract boolean isAvailable();

    protected abstract boolean isReady();

    protected abstract boolean showDialog();

    protected abstract boolean toStarted();

    protected abstract boolean toStopped();

    public Mode(Context context) {
        this.mContext = context;
        this.mState = State.INITIALIZED;
    }

    public Mode(Context context, int modeId) {
        this(context);
        this.modeId = modeId;
        this.mRequestManager = initRequestManager();
    }

    private boolean changeState(State state) {
        BydLogHelper.logD("mState = " + this.mState + " ,state = " + state);
        int i = AnonymousClass1.$SwitchMap$com$byd$profile$mode$State[state.ordinal()];
        boolean stopped = true;
        if (i == 1) {
            if (this.mState == State.STARTED) {
                return false;
            }
            boolean zIsReady = isReady();
            if (!zIsReady) {
                return zIsReady;
            }
            this.mState = State.PREPARED;
            return zIsReady;
        }
        if (i == 2) {
            if (this.mState != State.PREPARED && this.mState != State.STARTING) {
                return false;
            }
            this.mState = State.STARTING;
            boolean started = toStarted();
            BydLogHelper.logD("isSuccessful = " + started);
            if (!started) {
                return started;
            }
            this.mState = State.STARTED;
            ProfileManager.getInstance(this.mContext).setProfileMode(this.modeId, true);
            return started;
        }
        if (i != 3) {
            return false;
        }
        if (this.mState == State.STARTED || this.mState == State.STARTING) {
            this.mState = State.STOPPING;
            stopped = toStopped();
        }
        BydLogHelper.logD("isSuccessful = " + stopped);
        if (stopped) {
            this.mState = State.STOPPED;
            ProfileManager.getInstance(this.mContext).setProfileMode(this.modeId, false);
            clear();
        }
        return stopped;
    }

    /* JADX INFO: renamed from: com.byd.profile.mode.Mode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$profile$mode$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$byd$profile$mode$State = iArr;
            try {
                iArr[State.PREPARED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$profile$mode$State[State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$profile$mode$State[State.STOPPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public boolean startMode() {
        if (showDialog()) {
            return false;
        }
        return startModeReal();
    }

    protected boolean startModeReal() {
        BydLogHelper.logD("startModeReal");
        return changeState(State.PREPARED) && changeState(State.STARTED);
    }

    public boolean switchMode(Mode mode) {
        BydLogHelper.logD("switchMode");
        if (mode != null && mode.isStarted()) {
            mode.changeState(State.STOPPED);
        }
        return startMode();
    }

    public boolean stopMode() {
        return changeState(State.STOPPED);
    }

    public void forceStop() {
        this.mState = State.STOPPED;
        toStopped();
        clear();
    }

    public State getState() {
        return this.mState;
    }

    public int getModeId() {
        return this.modeId;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isStarting() {
        return this.mState == State.STARTING;
    }

    public boolean isStarted() {
        BydLogHelper.logD("isStarted mState = " + this.mState);
        return this.mState == State.STARTED;
    }

    public boolean isStopped() {
        return this.mState == State.STOPPED;
    }

    public boolean isWorking() {
        return this.mState == State.STARTING || this.mState == State.STARTED || this.mState == State.STOPPING;
    }

    public String toString() {
        return "ProfileState{, mState=" + this.mState + ", modeId=" + this.modeId + '}';
    }
}
