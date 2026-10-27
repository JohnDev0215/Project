package com.byd.music.utils.mediahelper;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/* JADX INFO: loaded from: classes5.dex */
public class AudioPlayerService extends Service {
    public static final String ACTION_LAST = "last";
    public static final String ACTION_NEXT = "next";
    public static final String ACTION_PAUSE = "pause";
    public static final String ACTION_PLAY = "play";
    private Binder mBinder = new ServiceBinder();

    public class ServiceBinder extends Binder {
        public ServiceBinder() {
        }

        public AudioPlayerService getService() {
            return AudioPlayerService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String str;
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            switch (action.hashCode()) {
                case 3314326:
                    str = ACTION_LAST;
                    break;
                case 3377907:
                    str = ACTION_NEXT;
                    break;
                case 3443508:
                    str = ACTION_PLAY;
                    break;
                case 106440182:
                    str = ACTION_PAUSE;
                    break;
            }
            action.equals(str);
        }
        return super.onStartCommand(intent, i, i2);
    }
}
