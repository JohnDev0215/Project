package com.ecarx.xui.adaptapi.vehicle.ota;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.neusoft.shockwave.tboxclient.IOtaAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
public class OtaImpl {
    private IOtaAidlInterface _aidl;
    private Context _context;
    private OtaProxy _proxy;
    private final String cTag = "OtaImpl";
    private ServiceConnection _serviceConnection = new ServiceConnection() { // from class: com.ecarx.xui.adaptapi.vehicle.ota.OtaImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("OtaImpl", "onServiceConnected binder = " + iBinder);
            OtaImpl.this._aidl = IOtaAidlInterface.Stub.asInterface(iBinder);
            Log.d("OtaImpl", "onServiceConnected binder = " + OtaImpl.this._aidl.asBinder());
            OtaImpl.this._proxy.set_aidl(OtaImpl.this._aidl);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("OtaImpl", "onServiceDisconnected");
            OtaImpl.this._aidl = null;
        }
    };

    public OtaImpl(Context context) {
        Log.d("OtaImpl", "OtaImpl");
        this._context = context;
        init();
    }

    private void init() {
        this._proxy = new OtaProxy();
    }

    public IOta getIOta() {
        Log.d("OtaImpl", "getIOta");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.neusoft.shockwave.tboxclient", "com.neusoft.shockwave.tboxclient.specific.service.OtaAidlService"));
        if (this._context.bindService(intent, this._serviceConnection, 1)) {
            Log.d("OtaImpl", "bindService success");
            return this._proxy;
        }
        Log.d("OtaImpl", "bindService fail");
        return null;
    }
}
