package com.byd.ttsservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.byd.ttsserviceapi.ITTSServiceApi;
import com.byd.vrassistant.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class TTSServiceProxy {
    public static final String TAG = "NEU_TTSServiceProxy";
    public static final String TTS_SRVICE_NAME = "TTSService";
    private IConnectStatusListener mConnectStatusListener;
    private Context mContext;
    private ITTSServiceApi mTTSServiceApi;
    private boolean mConnected = false;
    protected ServiceConnection mTTSServiceConnection = new ServiceConnection() { // from class: com.byd.ttsservice.TTSServiceProxy.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TTSServiceProxy.TAG, "TTSServiceProxy connect TTSService successful !");
            TTSServiceProxy.this.mConnected = true;
            TTSServiceProxy.this.mTTSServiceApi = ITTSServiceApi.Stub.asInterface(iBinder);
            TTSServiceProxy.this.mConnectStatusListener.onSuccess();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TTSServiceProxy.TAG, "TTSServiceProxy disconnected TTSService ");
            TTSServiceProxy.this.mConnected = false;
            TTSServiceProxy.this.mTTSServiceApi = null;
            TTSServiceProxy.this.mConnectStatusListener.onSuccess();
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            Log.i(TTSServiceProxy.TAG, "TTSService: Died!");
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            Log.i(TTSServiceProxy.TAG, "TTSService: Null Binding!");
        }
    };

    public interface IConnectStatusListener {
        void onFailed();

        void onSuccess();
    }

    public TTSServiceProxy(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    public boolean connect(IConnectStatusListener iConnectStatusListener) {
        this.mConnectStatusListener = iConnectStatusListener;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.byd.vrassistant.NeuVoiceService"));
        return this.mContext.bindService(intent, this.mTTSServiceConnection, 1);
    }

    public ITTSServiceApi getTTSService() {
        return this.mTTSServiceApi;
    }
}
