package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IHwBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.util.StringUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Objects;
import vendor.bosch.tbox.V1_0.DynamicInfo;
import vendor.bosch.tbox.V1_0.ITbox;
import vendor.bosch.tbox.V1_0.ITboxRilCallback;

/* JADX INFO: loaded from: classes.dex */
public class TBoxSignalController extends SignalController<TBoxSignalState, SignalController.IconGroup> {
    public static final String DEFAULT_ICCID = "NULL_ICCID";
    public static final int INIT_TBOX = 1;
    public static final byte NETWORK_2G_ONLINE = 2;
    public static final byte NETWORK_3G_ONLINE = 3;
    public static final byte NETWORK_ACTIVITY_IN = 1;
    public static final byte NETWORK_ACTIVITY_NONE = 0;
    public static final byte NETWORK_ACTIVITY_OUT = 2;
    public static final byte NETWORK_CONNECTING = 1;
    public static final byte NETWORK_NONE = 0;
    public static final byte NETWORK_STRENGTH_0 = 0;
    public static final byte NETWORK_STRENGTH_1 = 1;
    public static final byte NETWORK_STRENGTH_2 = 2;
    public static final byte NETWORK_STRENGTH_3 = 3;
    public static final byte NETWORK_STRENGTH_5 = 5;
    private BydRilCallback mBydRilCallback;
    private final IHwBinder.DeathRecipient mDeathRecipient;
    private DynamicInfo mDynamicInfo;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private volatile ITbox mITbox;
    private String mIccidValue;

    public TBoxSignalController(Context context, CallbackHandler callbackHandler, NetworkControllerImpl networkController) {
        super("TBoxSignalController", context, 0, callbackHandler, networkController);
        this.mDeathRecipient = new IHwBinder.DeathRecipient() { // from class: com.android.systemui.statusbar.policy.TBoxSignalController.2
            public void serviceDied(long l) {
                BydLogHelper.logD("serviceDied");
                TBoxSignalController.this.mITbox = null;
                TBoxSignalController.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
            }
        };
        HandlerThread handlerThread = new HandlerThread("init TBox");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.android.systemui.statusbar.policy.TBoxSignalController.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg.what != 1 || TBoxSignalController.this.mITbox != null) {
                    return false;
                }
                TBoxSignalController.this.startUp();
                if (TBoxSignalController.this.mITbox != null) {
                    return false;
                }
                TBoxSignalController.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
                return false;
            }
        });
    }

    public void startUp() {
        BydLogHelper.logD("startUp");
        try {
            this.mITbox = ITbox.getService();
            if (this.mITbox != null) {
                this.mITbox.linkToDeath(this.mDeathRecipient, 0L);
                this.mBydRilCallback = new BydRilCallback();
                this.mITbox.registerTboxRilCallback(this.mBydRilCallback);
            } else {
                Log.i("TBoxSignalController", "mITbox getService null");
            }
        } catch (Throwable th) {
            BydLogHelper.logE("e:" + th);
            th.printStackTrace();
        }
    }

    public void reset() {
        BydLogHelper.logD();
        if (this.mITbox == null || this.mBydRilCallback == null) {
            return;
        }
        try {
            this.mITbox.unregisterTboxRilCallback(this.mBydRilCallback);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isOnline() {
        return (((TBoxSignalState) this.mCurrentState).state == 0 || ((TBoxSignalState) this.mCurrentState).state == 1) ? false : true;
    }

    public boolean isDataEnabled() {
        if (this.mITbox != null) {
            try {
                byte apn2State = this.mITbox.getApn2State();
                Log.i("TBoxSignalController", "apn2State:" + ((int) apn2State));
                return apn2State != 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.i("TBoxSignalController", "mITbox is null");
        }
        if (this.mCurrentState != 0) {
            Log.i("TBoxSignalController", "mCurrentState.apn2_status:" + ((TBoxSignalState) this.mCurrentState).apn2_status);
            return ((TBoxSignalState) this.mCurrentState).apn2_status != 1;
        }
        BydLogHelper.logD("mCurrentState is null");
        return true;
    }

    public String getExternalIccid() {
        if (this.mITbox == null) {
            return null;
        }
        try {
            return this.mITbox.getExternalIccid();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.android.systemui.statusbar.policy.SignalController
    public void notifyListeners(NetworkController.SignalCallback callback) {
        BydLogHelper.logD("state:" + ((TBoxSignalState) this.mCurrentState).state + " , level:" + ((TBoxSignalState) this.mCurrentState).level + " , updown_status:" + ((TBoxSignalState) this.mCurrentState).updown_status + " , apn2_status:" + ((TBoxSignalState) this.mCurrentState).apn2_status);
        if (((TBoxSignalState) this.mCurrentState).state == 0 || ((TBoxSignalState) this.mCurrentState).state == 1) {
            callback.setTBoxNoneSignal((TBoxSignalState) this.mCurrentState);
        } else {
            callback.setTBoxIndicators((TBoxSignalState) this.mCurrentState);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.systemui.statusbar.policy.SignalController
    public TBoxSignalState cleanState() {
        return new TBoxSignalState();
    }

    private class BydRilCallback extends ITboxRilCallback.Stub {
        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onApn1Changed(byte b) throws RemoteException {
        }

        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onApn3Changed(byte b) throws RemoteException {
        }

        private BydRilCallback() {
        }

        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onDynamicInfoChanged(DynamicInfo dynamicInfo) throws RemoteException {
            TBoxSignalController.this.mDynamicInfo = dynamicInfo;
            TBoxSignalController.this.updateTBoxSignal();
        }

        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onApn2Changed(byte b) throws RemoteException {
            BydLogHelper.logD("onApn2Changed b:" + ((int) b));
            if (TBoxSignalController.this.mCurrentState != 0) {
                ((TBoxSignalState) TBoxSignalController.this.mCurrentState).apn2_status = b;
                TBoxSignalController.this.notifyListenersIfNecessary();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onRealApn2Changed(byte b) throws RemoteException {
            BydLogHelper.logD("onRealApn2Changed b:" + ((int) b));
        }

        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onRilConnectChanged(byte b) throws RemoteException {
            TBoxSignalController.this.updateTBoxConnectChange(b);
        }

        @Override // vendor.bosch.tbox.V1_0.ITboxRilCallback
        public void onIccidChanged(String s) throws RemoteException {
            BydLogHelper.logD("onIccidChanged s:" + StringUtil.desensitizeString(s, 0));
            String str = s == null ? TBoxSignalController.DEFAULT_ICCID : s;
            BydLogHelper.logD("onIccidChanged iccid:" + StringUtil.desensitizeString(str, 0) + " ,mIccidValue:" + StringUtil.desensitizeString(TBoxSignalController.this.mIccidValue, 0));
            if (str.equals(TBoxSignalController.this.mIccidValue)) {
                return;
            }
            TBoxSignalController.this.mIccidValue = str;
            TBoxSignalController.this.updateShowTelephoneData(s);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTBoxConnectChange(byte b) {
        Log.w("TBoxSignalController", "onRilConnectChanged b:" + ((int) b));
        if (b != 0 || this.mCurrentState == 0) {
            return;
        }
        ((TBoxSignalState) this.mCurrentState).state = 0;
        notifyListenersIfNecessary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTBoxSignal() {
        if (this.mDynamicInfo != null) {
            BydLogHelper.logD("mDynamicInfo state: " + ((int) this.mDynamicInfo.state) + " ,signal_strength: " + ((int) this.mDynamicInfo.signal_strength));
            ((TBoxSignalState) this.mCurrentState).level = this.mDynamicInfo.signal_strength;
            ((TBoxSignalState) this.mCurrentState).state = this.mDynamicInfo.state;
            ((TBoxSignalState) this.mCurrentState).updown_status = this.mDynamicInfo.updown_status;
            ((TBoxSignalState) this.mCurrentState).apn2_status = this.mDynamicInfo.apn2_status;
            notifyListenersIfNecessary();
            return;
        }
        BydLogHelper.logD("mDynamicInfo is null");
    }

    public void updateShowTelephoneData(String s) {
        notifyIccid((s == null || s.length() == 0) ? false : true);
    }

    public static class TBoxSignalState extends SignalController.State {
        public int apn2_status;
        public int state;
        public int updown_status;

        @Override // com.android.systemui.statusbar.policy.SignalController.State
        public void copyFrom(SignalController.State s) {
            super.copyFrom(s);
            TBoxSignalState tBoxSignalState = (TBoxSignalState) s;
            this.state = tBoxSignalState.state;
            this.updown_status = tBoxSignalState.updown_status;
            this.apn2_status = tBoxSignalState.apn2_status;
        }

        @Override // com.android.systemui.statusbar.policy.SignalController.State
        public boolean equals(Object o) {
            if (!super.equals(o)) {
                return false;
            }
            TBoxSignalState tBoxSignalState = (TBoxSignalState) o;
            return Objects.equals(Integer.valueOf(tBoxSignalState.state), Integer.valueOf(this.state)) && tBoxSignalState.updown_status == this.updown_status && tBoxSignalState.apn2_status == this.apn2_status;
        }
    }
}
