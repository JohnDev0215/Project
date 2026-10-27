package com.ecarx.xui.adaptapi.oncall;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.oncall.Call;

/* JADX INFO: loaded from: classes2.dex */
public abstract class OnCall extends AdaptAPI {
    public static final String ACTION_ONCALL_STARTED = "ecarx.xui.intent.action.ONCALL_STARTED";
    public static final String ACTION_ONCALL_UNSUBSCRIBED = "ecarx.xui.intent.action.ONCALL_UNSUBSCRIBED";
    public static final String EXTRA_ONCALL_TYPE = "ecarx.xui.intent.extra.ONCALL_TYPE";

    public interface IECallModeListener {
        void onECallModeChange(int i);
    }

    public interface SubscribeCallback {
        void onNoSubscribe(int i);
    }

    public abstract Call getCurrentCall();

    public abstract int getLastECallRemainTime();

    public abstract boolean isECallInCallbackMode();

    public abstract boolean isSubscribed(int i);

    public abstract void setECallModeListener(IECallModeListener iECallModeListener);

    public abstract void setSubscribeCallback(SubscribeCallback subscribeCallback);

    public abstract Call startCall(int i, Call.StateListener stateListener);

    public abstract void unsetECallModeListener(IECallModeListener iECallModeListener);

    public static OnCall create(Context context) {
        return new OnCallImpl(context);
    }
}
