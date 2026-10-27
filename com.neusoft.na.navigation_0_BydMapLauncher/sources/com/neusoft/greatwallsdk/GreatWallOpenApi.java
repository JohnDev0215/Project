package com.neusoft.greatwallsdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import com.neusoft.greatwallsdk.IAidlGreatWallInterface;
import com.neusoft.greatwallsdk.IAidlGreatWallListener;
import com.neusoft.greatwallsdk.IAidlRequestCallback;
import com.neusoft.greatwallsdk.IAidlSetSurfaceResultCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GreatWallOpenApi implements IGreatWallInterface {
    private IAidlGreatWallInterface binder;
    private Context context;
    private SetSurfaceResultCallback mSetSurfaceResultCallback;
    private Surface mSurface;
    private String TAG = "XJGreatWallOpenApi";
    private final String SERVICE = "com.neusoft.na.ocservice.greatwallsdk.GreatWallSDKService";
    private boolean serviceStatus = false;
    private IGreatWallListener sysListener = null;
    private IAidlGreatWallListener aidlGreatWallListener = null;
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.neusoft.greatwallsdk.GreatWallOpenApi.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(GreatWallOpenApi.this.TAG, "onServiceConnected: ");
            GreatWallOpenApi.this.serviceStatus = true;
            if (iBinder != null) {
                GreatWallOpenApi.this.binder = IAidlGreatWallInterface.Stub.asInterface(iBinder);
                if (GreatWallOpenApi.this.mSurface != null) {
                    try {
                        GreatWallOpenApi greatWallOpenApi = GreatWallOpenApi.this;
                        greatWallOpenApi.setSurface(greatWallOpenApi.mSurface, GreatWallOpenApi.this.mSetSurfaceResultCallback);
                        Log.i(GreatWallOpenApi.this.TAG, "setSurface done");
                    } catch (RemoteException e) {
                        Log.e(GreatWallOpenApi.this.TAG, "setSurface error:" + e.getMessage());
                    }
                }
            }
            if (GreatWallOpenApi.this.sysListener != null) {
                GreatWallOpenApi.this.setAidlListener();
            }
            GreatWallOpenApi.this.sendServiceStatus(0);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(GreatWallOpenApi.this.TAG, "onServiceDisconnected: ");
            GreatWallOpenApi.this.serviceStatus = false;
            GreatWallOpenApi.this.binder = null;
            GreatWallOpenApi.this.sendServiceStatus(0);
            GreatWallOpenApi.this.reconnection();
        }
    };
    private Handler handler = new Handler();

    /* JADX INFO: Access modifiers changed from: private */
    public void sendServiceStatus(int i) {
        IGreatWallListener iGreatWallListener = this.sysListener;
        if (iGreatWallListener != null) {
            iGreatWallListener.onResult(getSendServiceStatusJson(i));
        }
    }

    public GreatWallOpenApi(Context context) {
        Log.i(this.TAG, "GreatWallOpenApi");
        this.context = context;
        initConnectService();
    }

    private void initConnectService() {
        Log.i(this.TAG, "[initConnectService]");
        boolean zConnectService = connectService();
        Log.i(this.TAG, "[connectService] --> result == " + zConnectService);
        if (zConnectService) {
            return;
        }
        Log.i(this.TAG, "connectService bindService fail, reTry...");
        reconnection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean connectService() {
        Log.i(this.TAG, "[connectService]");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.neusoft.na.navigation", "com.neusoft.na.ocservice.greatwallsdk.GreatWallSDKService"));
        return this.context.bindService(intent, this.serviceConnection, 1);
    }

    @Override // com.neusoft.greatwallsdk.IGreatWallInterface
    public String sync_request(String str) {
        Log.i(this.TAG, "sync_request:action:" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("moduleType");
            int i2 = jSONObject.getInt("actionType");
            if (i == 90000 && i2 == 1) {
                return getSendServiceStatusJson(1);
            }
            IAidlGreatWallInterface iAidlGreatWallInterface = this.binder;
            if (iAidlGreatWallInterface != null) {
                try {
                    return iAidlGreatWallInterface.sync_request(str);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            Log.e(this.TAG, "binder == null");
            return "";
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @Override // com.neusoft.greatwallsdk.IGreatWallInterface
    public void async_request(String str, final RequestCallback requestCallback) {
        Log.i(this.TAG, "async_request action:" + str);
        IAidlGreatWallInterface iAidlGreatWallInterface = this.binder;
        if (iAidlGreatWallInterface != null) {
            try {
                iAidlGreatWallInterface.async_request(str, new IAidlRequestCallback.Stub() { // from class: com.neusoft.greatwallsdk.GreatWallOpenApi.2
                    @Override // com.neusoft.greatwallsdk.IAidlRequestCallback
                    public void onCallBack(String str2) throws RemoteException {
                        requestCallback.onCallBack(str2);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.neusoft.greatwallsdk.IGreatWallInterface
    public void setListener(IGreatWallListener iGreatWallListener) {
        Log.i(this.TAG, "setListener");
        this.sysListener = iGreatWallListener;
        iGreatWallListener.onResult(getSendServiceStatusJson(0));
        setAidlListener();
    }

    @Override // com.neusoft.greatwallsdk.IGreatWallInterface
    public void cancelListener(IGreatWallListener iGreatWallListener) {
        IAidlGreatWallInterface iAidlGreatWallInterface = this.binder;
        if (iAidlGreatWallInterface == null || iGreatWallListener == null) {
            return;
        }
        this.sysListener = null;
        IAidlGreatWallListener iAidlGreatWallListener = this.aidlGreatWallListener;
        if (iAidlGreatWallListener != null) {
            try {
                iAidlGreatWallInterface.cancelListener(iAidlGreatWallListener);
                this.aidlGreatWallListener = null;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.neusoft.greatwallsdk.IGreatWallInterface
    public void setSurface(Surface surface, final SetSurfaceResultCallback setSurfaceResultCallback) throws RemoteException {
        IAidlGreatWallInterface iAidlGreatWallInterface = this.binder;
        if (iAidlGreatWallInterface != null) {
            iAidlGreatWallInterface.setSurface(surface, new IAidlSetSurfaceResultCallback.Stub() { // from class: com.neusoft.greatwallsdk.GreatWallOpenApi.3
                @Override // com.neusoft.greatwallsdk.IAidlSetSurfaceResultCallback
                public void onSetSurfaceCallBack(int i) throws RemoteException {
                    setSurfaceResultCallback.onSetSurfaceCallBack(i);
                }
            });
            return;
        }
        Log.i(this.TAG, "setSurface: waiting for bind");
        this.mSurface = surface;
        this.mSetSurfaceResultCallback = setSurfaceResultCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAidlListener() {
        Log.i(this.TAG, "setAidlListener --> version.20230417");
        IAidlGreatWallInterface iAidlGreatWallInterface = this.binder;
        if (iAidlGreatWallInterface != null && this.sysListener != null) {
            try {
                IAidlGreatWallListener.Stub stub = new IAidlGreatWallListener.Stub() { // from class: com.neusoft.greatwallsdk.GreatWallOpenApi.4
                    @Override // com.neusoft.greatwallsdk.IAidlGreatWallListener
                    public void onResult(String str) throws RemoteException {
                        Log.i(GreatWallOpenApi.this.TAG, "IAidlGreatWallListener.onResult --> " + str);
                        GreatWallOpenApi.this.sysListener.onResult(str);
                    }
                };
                this.aidlGreatWallListener = stub;
                this.binder.setListener(stub);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        if (iAidlGreatWallInterface == null) {
            Log.e(this.TAG, "[setAidlListener] --> binder == null, so do not set adil listener.");
        }
        if (this.sysListener == null) {
            Log.e(this.TAG, "[setAidlListener] --> sysListener == null, so do not set adil listener.");
        }
    }

    private String getSendServiceStatusJson(int i) {
        String str = this.serviceStatus ? i == 0 ? "{\n \"actionType\": 1,\n \"auth\": \"com.neusoft.navigation\",\n \"data\": {\n  \"status\": 0\n },\n \"moduleType\": 90000,\n \"operaType\": 0,\n \"statusCode\": 0\n}" : "{\n \"actionType\": 1,\n \"auth\": \"com.neusoft.navigation\",\n \"data\": {\n  \"status\": 0\n },\n \"moduleType\": 90000,\n \"operaType\": 1,\n \"statusCode\": 0\n}" : i == 0 ? "{\n \"actionType\": 1,\n \"auth\": \"com.neusoft.navigation\",\n \"data\": {\n  \"status\": 1\n },\n \"moduleType\": 90000,\n \"operaType\": 0,\n \"statusCode\": 0\n}" : "{\n \"actionType\": 1,\n \"auth\": \"com.neusoft.navigation\",\n \"data\": {\n  \"status\": 1\n },\n \"moduleType\": 90000,\n \"operaType\": 1,\n \"statusCode\": 0\n}";
        Log.i(this.TAG, "getSendServiceStatusJson:".concat(str));
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void reconnection() {
        Log.i(this.TAG, "reconnection");
        new Thread(new Runnable() { // from class: com.neusoft.greatwallsdk.GreatWallOpenApi.5
            @Override // java.lang.Runnable
            public void run() {
                while (!GreatWallOpenApi.this.serviceStatus) {
                    Log.i(GreatWallOpenApi.this.TAG, "reconnecting");
                    GreatWallOpenApi.this.connectService();
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }
}
