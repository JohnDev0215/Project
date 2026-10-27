package com.byd.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.byd.naviserviceapi.INaviService;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceClient {
    private static final String TAG = "demo";
    private ServiceConnection naviConnection = new ServiceConnection() { // from class: com.byd.demo.VoiceClient.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            INaviService.Stub.asInterface(service);
            Log.i(VoiceClient.TAG, "connect NaviService Successful。");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Log.i(VoiceClient.TAG, "disconnected NaviService ");
        }
    };

    public void bindNaviService(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.demo", "com.byd.demo.NaviDemoService"));
        context.bindService(intent, this.naviConnection, 1);
    }
}
