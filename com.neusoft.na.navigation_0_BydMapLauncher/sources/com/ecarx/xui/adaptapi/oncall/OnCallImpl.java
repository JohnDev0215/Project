package com.ecarx.xui.adaptapi.oncall;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.oncall.Call;
import com.ecarx.xui.adaptapi.oncall.OnCall;
import com.neusoft.shockwave.tboxclient.INotifyXCallInfoforAPIInterface;
import com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
public class OnCallImpl extends OnCall {
    public static final String CALLBACKSTRING = "call back";
    public static final String NORMALSTRING = "normal";
    public static final String TAG = "ONCALL";
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.ecarx.xui.adaptapi.oncall.OnCallImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(OnCallImpl.TAG, "onServiceConnected");
            OnCallImpl.this.mOncallInterface = IOncallApiAidlInterface.Stub.asInterface(iBinder);
            try {
                OnCallImpl.this.mOncallInterface.requestOnCallInfoCallback(OnCallImpl.this.new notifyXCallBack());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(OnCallImpl.TAG, "onServiceDisconnected");
            OnCallImpl.this.mOncallInterface = null;
        }
    };
    private Context mContext;
    private OnCall.IECallModeListener mECallModeListener;
    private IOncallApiAidlInterface mOncallInterface;

    public OnCallImpl(Context context) {
        Log.d(TAG, "OnCallImpl");
        this.mContext = context;
        bindService();
    }

    private void bindService() {
        if (this.mContext != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.neusoft.shockwave.tboxclient", "com.neusoft.shockwave.tboxclient.specific.service.OncallAidlService"));
            this.mContext.bindService(intent, this.mConnection, 1);
        }
    }

    public class notifyXCallBack extends INotifyXCallInfoforAPIInterface.Stub {
        public notifyXCallBack() {
        }

        @Override // com.neusoft.shockwave.tboxclient.INotifyXCallInfoforAPIInterface
        public void xCallInfoCallBack(String str, String str2, String str3) throws RemoteException {
            Log.d(OnCallImpl.TAG, "notifyXCallBack callType = " + str + ", callStatus = " + str2 + ", callBackMode = " + str3);
            if (str3 == null || !str3.equals(OnCallImpl.CALLBACKSTRING) || OnCallImpl.this.mECallModeListener == null) {
                return;
            }
            OnCallImpl.this.mECallModeListener.onECallModeChange(1);
        }
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public Call getCurrentCall() {
        Log.d(TAG, "getCurrentCall");
        CallImpl callImpl = new CallImpl();
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mOncallInterface;
        if (iOncallApiAidlInterface != null) {
            callImpl.setmAidl(iOncallApiAidlInterface);
        }
        return callImpl;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public Call startCall(int i, Call.StateListener stateListener) {
        Log.d(TAG, "startCall type = " + i);
        if (i != 1) {
            return null;
        }
        if (this.mOncallInterface != null) {
            try {
                CallImpl callImpl = new CallImpl();
                callImpl.setmAidl(this.mOncallInterface);
                this.mOncallInterface.startCall(i);
                return callImpl;
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        Log.d(TAG, "startCall mOncallInterface = null");
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public boolean isSubscribed(int i) {
        Log.d(TAG, "isSubscribed type = " + i);
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public int getLastECallRemainTime() {
        Log.d(TAG, "getLastECallRemainTime");
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public boolean isECallInCallbackMode() {
        boolean zIsECallInCallbackMode;
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mOncallInterface;
        if (iOncallApiAidlInterface != null) {
            try {
                zIsECallInCallbackMode = iOncallApiAidlInterface.isECallInCallbackMode();
            } catch (RemoteException e) {
                e.printStackTrace();
                zIsECallInCallbackMode = false;
            }
            Log.d(TAG, "isECallInCallbackMode result = " + zIsECallInCallbackMode);
            return zIsECallInCallbackMode;
        }
        Log.d(TAG, "isECallInCallbackMode mOncallInterface = null");
        zIsECallInCallbackMode = false;
        Log.d(TAG, "isECallInCallbackMode result = " + zIsECallInCallbackMode);
        return zIsECallInCallbackMode;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public void setECallModeListener(OnCall.IECallModeListener iECallModeListener) {
        Log.d(TAG, "setECallModeListener");
        this.mECallModeListener = iECallModeListener;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public void unsetECallModeListener(OnCall.IECallModeListener iECallModeListener) {
        Log.d(TAG, "unsetECallModeListener");
        this.mECallModeListener = null;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.OnCall
    public void setSubscribeCallback(OnCall.SubscribeCallback subscribeCallback) {
        Log.d(TAG, "setSubscribeCallback");
    }
}
