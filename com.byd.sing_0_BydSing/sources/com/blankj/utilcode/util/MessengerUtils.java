package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.blankj.utilcode.util.n;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MessengerUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, a> f2552a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Map<String, ?> f2553b = new HashMap();

    public static class ServerService extends Service {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ConcurrentHashMap<Integer, Messenger> f2554b = new ConcurrentHashMap<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @SuppressLint({"HandlerLeak"})
        private final Handler f2555c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Messenger f2556d;

        class a extends Handler {
            a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    ServerService.this.f2554b.put(Integer.valueOf(message.arg1), message.replyTo);
                    return;
                }
                if (i == 1) {
                    ServerService.this.f2554b.remove(Integer.valueOf(message.arg1));
                } else if (i != 2) {
                    super.handleMessage(message);
                } else {
                    ServerService.this.e(message);
                    ServerService.this.d(message);
                }
            }
        }

        public ServerService() {
            a aVar = new a();
            this.f2555c = aVar;
            this.f2556d = new Messenger(aVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(Message message) {
            String string;
            a aVar;
            Bundle data = message.getData();
            if (data == null || (string = data.getString("MESSENGER_UTILS")) == null || (aVar = (a) MessengerUtils.f2552a.get(string)) == null) {
                return;
            }
            aVar.a(data);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(Message message) {
            for (Messenger messenger : this.f2554b.values()) {
                if (messenger != null) {
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override // android.app.Service
        public IBinder onBind(Intent intent) {
            return this.f2556d.getBinder();
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            Bundle extras;
            if (Build.VERSION.SDK_INT >= 26) {
                startForeground(1, y.m(n.a.f2614a, null));
            }
            if (intent != null && (extras = intent.getExtras()) != null) {
                Message messageObtain = Message.obtain(this.f2555c, 2);
                messageObtain.replyTo = this.f2556d;
                messageObtain.setData(extras);
                e(messageObtain);
                d(messageObtain);
            }
            return 2;
        }
    }

    public interface a {
        void a(Bundle bundle);
    }
}
