package com.ecarx.xui.adaptapi.oncall;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.oncall.Call;
import com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
public class CallImpl implements Call {
    public static final String TAG = "CALL";
    private IOncallApiAidlInterface mAidl;

    public void setmAidl(IOncallApiAidlInterface iOncallApiAidlInterface) {
        this.mAidl = iOncallApiAidlInterface;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public int getStatus() {
        int status;
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mAidl;
        if (iOncallApiAidlInterface != null) {
            try {
                status = iOncallApiAidlInterface.getStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
                status = 0;
            }
            Log.d(TAG, "getStatus = " + status);
            return status;
        }
        Log.d(TAG, "getStatus mAidl = null ");
        status = 0;
        Log.d(TAG, "getStatus = " + status);
        return status;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public int getType() {
        int type;
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mAidl;
        if (iOncallApiAidlInterface != null) {
            try {
                type = iOncallApiAidlInterface.getType();
            } catch (RemoteException e) {
                e.printStackTrace();
                type = -2147483647;
            }
            Log.d(TAG, "getType = " + type);
            return type;
        }
        Log.d(TAG, "getType mAidl = null ");
        type = -2147483647;
        Log.d(TAG, "getType = " + type);
        return type;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public int getDuration() {
        int duration;
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mAidl;
        if (iOncallApiAidlInterface != null) {
            try {
                duration = iOncallApiAidlInterface.getDuration();
            } catch (RemoteException e) {
                e.printStackTrace();
                duration = 0;
            }
            Log.d(TAG, "getDuration = " + duration);
            return duration;
        }
        Log.d(TAG, "getDuration mAidl = null ");
        duration = 0;
        Log.d(TAG, "getDuration = " + duration);
        return duration;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public boolean isCallback() {
        boolean zIsCallback;
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mAidl;
        if (iOncallApiAidlInterface != null) {
            try {
                zIsCallback = iOncallApiAidlInterface.isCallback();
            } catch (RemoteException e) {
                e.printStackTrace();
                zIsCallback = false;
            }
            Log.d(TAG, "isCallback = " + zIsCallback);
            return zIsCallback;
        }
        Log.d(TAG, "isCallback mAidl = null ");
        zIsCallback = false;
        Log.d(TAG, "isCallback = " + zIsCallback);
        return zIsCallback;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public void end() {
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mAidl;
        if (iOncallApiAidlInterface != null) {
            try {
                iOncallApiAidlInterface.end();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "end mAidl = null ");
        }
        Log.d(TAG, "CALL end ");
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public int getStartCause() {
        int startCause;
        IOncallApiAidlInterface iOncallApiAidlInterface = this.mAidl;
        if (iOncallApiAidlInterface != null) {
            try {
                startCause = iOncallApiAidlInterface.getStartCause();
            } catch (RemoteException e) {
                e.printStackTrace();
                startCause = 0;
            }
            Log.d(TAG, "getStartCause = " + startCause);
            return startCause;
        }
        Log.d(TAG, "end mAidl = null ");
        startCause = 0;
        Log.d(TAG, "getStartCause = " + startCause);
        return startCause;
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public void setStateListener(Call.StateListener stateListener) {
        Log.d(TAG, "setStateListener");
    }

    @Override // com.ecarx.xui.adaptapi.oncall.Call
    public void unsetStateListener(Call.StateListener stateListener) {
        Log.d(TAG, "unsetStateListener");
    }
}
