package com.ts.car.input;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class InputApplication extends Application {
    private static final int MSG_PARSE_CONFIG = 1;
    private static final String TAG = "InputApplication";
    private ConfigHandler mConfigHandler;
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.ts.car.input.InputApplication.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.log(InputApplication.TAG, "onServiceConnected after bind InputCoreService");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.log(InputApplication.TAG, "onServiceDisconnected after bind InputCoreService");
        }
    };
    private HandlerThread mHandlerThread;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        startService();
    }

    private void startService() {
        try {
            Intent startIntent = new Intent(this, (Class<?>) InputCoreService.class);
            bindService(startIntent, this.mConnection, 1);
            LogUtils.log(TAG, "onCreate startService startIntent : " + startIntent.toString());
        } catch (Exception ex) {
            LogUtils.loge(TAG, "onCreate startService error : " + ex);
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        stop();
    }

    public void start() {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            ConfigHandler configHandler = new ConfigHandler(this.mHandlerThread.getLooper());
            this.mConfigHandler = configHandler;
            configHandler.sendEmptyMessage(1);
        }
    }

    private void stop() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mHandlerThread = null;
            this.mConfigHandler = null;
        }
    }

    private class ConfigHandler extends Handler {
        public ConfigHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                ConfigUtils cfg = new ConfigUtils();
                ConfigParser parser = new ConfigParser();
                LogUtils.log(InputApplication.TAG, "ParseConfig cfg :" + cfg.toString() + ", parser : " + parser.toString());
            }
        }
    }
}
