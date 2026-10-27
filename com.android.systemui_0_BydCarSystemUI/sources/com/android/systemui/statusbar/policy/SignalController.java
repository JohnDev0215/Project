package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import com.android.systemui.Constants;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController.IconGroup;
import com.android.systemui.statusbar.policy.SignalController.State;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class SignalController<T extends State, I extends IconGroup> {
    protected static final boolean DEBUG = true;
    private final CallbackHandler mCallbackHandler;
    protected final Context mContext;
    protected final T mCurrentState = (T) cleanState();
    protected final T mLastState = (T) cleanState();
    protected final NetworkControllerImpl mNetworkController;
    protected final String mTag;
    protected final int mTransportType;

    protected abstract T cleanState();

    public abstract void notifyListeners(NetworkController.SignalCallback callback);

    public SignalController(String str, Context context, int i, CallbackHandler callbackHandler, NetworkControllerImpl networkControllerImpl) {
        this.mTag = NetworkControllerImpl.TAG + Constants.CHAR_DOT + str;
        this.mNetworkController = networkControllerImpl;
        this.mTransportType = i;
        this.mContext = context;
        this.mCallbackHandler = callbackHandler;
    }

    public T getState() {
        return this.mCurrentState;
    }

    public void updateConnectivity(BitSet bitSet, BitSet bitSet2) {
        this.mCurrentState.inetCondition = bitSet2.get(this.mTransportType) ? 1 : 0;
        notifyListenersIfNecessary();
    }

    @Deprecated
    public void resetLastState() {
        this.mCurrentState.copyFrom(this.mLastState);
    }

    public boolean isDirty() {
        if (this.mLastState.equals(this.mCurrentState)) {
            return false;
        }
        Log.d(this.mTag, "Change in state from: " + this.mLastState + "\n\tto: " + this.mCurrentState);
        return true;
    }

    public void saveLastState() {
        this.mCurrentState.time = System.currentTimeMillis();
        this.mLastState.copyFrom(this.mCurrentState);
    }

    public int getCurrentIconId() {
        if (this.mCurrentState.connected) {
            BydLogHelper.logD("getCurrentIconId mSbIcons = " + getIcons().mSbIcons[this.mCurrentState.inetCondition][this.mCurrentState.level] + " , inetCondition = " + this.mCurrentState.inetCondition + " , level = " + this.mCurrentState.level);
            return getIcons().mSbIcons[this.mCurrentState.inetCondition][this.mCurrentState.level];
        }
        if (this.mCurrentState.enabled) {
            BydLogHelper.logD("getCurrentIconId mSbDiscState = " + getIcons().mSbDiscState);
            return getIcons().mSbDiscState;
        }
        BydLogHelper.logD("getCurrentIconId mSbNullState = " + getIcons().mSbNullState);
        return getIcons().mSbNullState;
    }

    public void notifyListenersIfNecessary() {
        if (isDirty()) {
            saveLastState();
            notifyListeners();
        }
    }

    public void notifyIccid(boolean show) {
        BydLogHelper.logD("notifyIccid show = " + show);
        this.mCallbackHandler.showTelephoneData(show);
    }

    protected I getIcons() {
        return (I) this.mCurrentState.iconGroup;
    }

    public final void notifyListeners() {
        notifyListeners(this.mCallbackHandler);
    }

    static class IconGroup {
        final String mName;
        final int mSbDiscState;
        final int[][] mSbIcons;
        final int mSbNullState;

        public IconGroup(String name, int[][] sbIcons, int sbNullState, int sbDiscState) {
            this.mName = name;
            this.mSbIcons = sbIcons;
            this.mSbNullState = sbNullState;
            this.mSbDiscState = sbDiscState;
        }

        public String toString() {
            return "IconGroup(" + this.mName + ")";
        }
    }

    public static class State {
        boolean activityIn;
        boolean activityOut;
        public boolean connected;
        public boolean enabled;
        public IconGroup iconGroup;
        public int inetCondition;
        public int level;
        public int rssi;
        public long time;

        public void copyFrom(State state) {
            this.connected = state.connected;
            this.enabled = state.enabled;
            this.level = state.level;
            this.iconGroup = state.iconGroup;
            this.inetCondition = state.inetCondition;
            this.activityIn = state.activityIn;
            this.activityOut = state.activityOut;
            this.rssi = state.rssi;
            this.time = state.time;
        }

        public String toString() {
            if (this.time != 0) {
                StringBuilder sb = new StringBuilder();
                toString(sb);
                return sb.toString();
            }
            return "Empty " + getClass().getSimpleName();
        }

        protected void toString(StringBuilder builder) {
            builder.append("connected=").append(this.connected).append(',').append("enabled=").append(this.enabled).append(',').append("level=").append(this.level).append(',').append("inetCondition=").append(this.inetCondition).append(',').append("iconGroup=").append(this.iconGroup).append(',').append("activityIn=").append(this.activityIn).append(',').append("activityOut=").append(this.activityOut).append(',').append("rssi=").append(this.rssi).append(',').append("lastModified=").append(DateFormat.format("MM-dd HH:mm:ss", this.time));
        }

        public boolean equals(Object o) {
            if (!o.getClass().equals(getClass())) {
                return false;
            }
            State state = (State) o;
            return state.connected == this.connected && state.enabled == this.enabled && state.level == this.level && state.inetCondition == this.inetCondition && state.iconGroup == this.iconGroup && state.activityIn == this.activityIn && state.activityOut == this.activityOut && state.rssi == this.rssi;
        }
    }
}
