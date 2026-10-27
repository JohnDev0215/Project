package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.NotificationUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class MessengerUtils {
    private static final String KEY_STRING = "MESSENGER_UTILS";
    private static final int WHAT_SEND = 2;
    private static final int WHAT_SUBSCRIBE = 0;
    private static final int WHAT_UNSUBSCRIBE = 1;
    private static a sLocalClient;
    private static ConcurrentHashMap<String, MessageCallback> subscribers = new ConcurrentHashMap<>();
    private static Map<String, a> sClientMap = new HashMap();

    public interface MessageCallback {
        void messageCall(Bundle bundle);
    }

    public static class ServerService extends Service {
        private final ConcurrentHashMap<Integer, Messenger> mClientMap = new ConcurrentHashMap<>();

        @SuppressLint({"HandlerLeak"})
        private final Handler mReceiveClientMsgHandler;
        private final Messenger messenger;

        public class a extends Handler {
            public a() {
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    ServerService.this.mClientMap.put(Integer.valueOf(message.arg1), message.replyTo);
                    return;
                }
                if (i == 1) {
                    ServerService.this.mClientMap.remove(Integer.valueOf(message.arg1));
                } else if (i != 2) {
                    super.handleMessage(message);
                } else {
                    ServerService.this.sendMsg2Client(message);
                    ServerService.this.consumeServerProcessCallback(message);
                }
            }
        }

        public ServerService() {
            a aVar = new a();
            this.mReceiveClientMsgHandler = aVar;
            this.messenger = new Messenger(aVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void consumeServerProcessCallback(Message message) {
            String string;
            MessageCallback messageCallback;
            Bundle data = message.getData();
            if (data == null || (string = data.getString(MessengerUtils.KEY_STRING)) == null || (messageCallback = (MessageCallback) MessengerUtils.subscribers.get(string)) == null) {
                return;
            }
            messageCallback.messageCall(data);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendMsg2Client(Message message) {
            for (Messenger messenger : this.mClientMap.values()) {
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
        @Nullable
        public IBinder onBind(Intent intent) {
            return this.messenger.getBinder();
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            Bundle extras;
            startForeground(1, NotificationUtils.getNotification(NotificationUtils.ChannelConfig.DEFAULT_CHANNEL_CONFIG, null));
            if (intent != null && (extras = intent.getExtras()) != null) {
                Message messageObtain = Message.obtain(this.mReceiveClientMsgHandler, 2);
                messageObtain.replyTo = this.messenger;
                messageObtain.setData(extras);
                sendMsg2Client(messageObtain);
                consumeServerProcessCallback(messageObtain);
            }
            return 2;
        }
    }

    public static class a {
        public String a;
        public Messenger b;
        public LinkedList<Bundle> c = new LinkedList<>();

        @SuppressLint({"HandlerLeak"})
        public HandlerC0015a d = new HandlerC0015a(this);
        public Messenger e = new Messenger(this.d);
        public b f = new b();

        /* JADX INFO: renamed from: com.blankj.utilcode.util.MessengerUtils$a$a, reason: collision with other inner class name */
        public class HandlerC0015a extends Handler {
            public HandlerC0015a(a aVar) {
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                MessageCallback messageCallback;
                Bundle data = message.getData();
                data.setClassLoader(MessengerUtils.class.getClassLoader());
                String string = data.getString(MessengerUtils.KEY_STRING);
                if (string == null || (messageCallback = (MessageCallback) MessengerUtils.subscribers.get(string)) == null) {
                    return;
                }
                messageCallback.messageCall(data);
            }
        }

        public class b implements ServiceConnection {
            public b() {
            }

            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("MessengerUtils", "client service connected " + componentName);
                a.this.b = new Messenger(iBinder);
                Message messageObtain = Message.obtain(a.this.d, 0, ProcessUtils.getCurrentProcessName().hashCode(), 0);
                messageObtain.getData().setClassLoader(MessengerUtils.class.getClassLoader());
                a aVar = a.this;
                messageObtain.replyTo = aVar.e;
                try {
                    aVar.b.send(messageObtain);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                a.this.c();
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
                Log.w("MessengerUtils", "client service disconnected:" + componentName);
                a aVar = a.this;
                aVar.b = null;
                if (aVar.a()) {
                    return;
                }
                Log.e("MessengerUtils", "client service rebind failed: " + componentName);
            }
        }

        public a(String str) {
            this.a = str;
        }

        public final boolean a() {
            if (TextUtils.isEmpty(this.a)) {
                return Utils.getApp().bindService(new Intent(Utils.getApp(), (Class<?>) ServerService.class), this.f, 1);
            }
            if (!AppUtils.isAppInstalled(this.a)) {
                StringBuilder sbK = spi.c.k("bind: the app is not installed -> ");
                sbK.append(this.a);
                Log.e("MessengerUtils", sbK.toString());
                return false;
            }
            if (AppUtils.isAppRunning(this.a)) {
                Intent intent = new Intent(spi.c.h(new StringBuilder(), this.a, ".messenger"));
                intent.setPackage(this.a);
                return Utils.getApp().bindService(intent, this.f, 1);
            }
            StringBuilder sbK2 = spi.c.k("bind: the app is not running -> ");
            sbK2.append(this.a);
            Log.e("MessengerUtils", sbK2.toString());
            return false;
        }

        public final boolean b(Bundle bundle) {
            Message messageObtain = Message.obtain(this.d, 2);
            bundle.setClassLoader(MessengerUtils.class.getClassLoader());
            messageObtain.setData(bundle);
            messageObtain.replyTo = this.e;
            try {
                this.b.send(messageObtain);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }

        public final void c() {
            if (this.c.isEmpty()) {
                return;
            }
            for (int size = this.c.size() - 1; size >= 0; size--) {
                if (b(this.c.get(size))) {
                    this.c.remove(size);
                }
            }
        }

        public final void d(Bundle bundle) {
            if (this.b != null) {
                c();
                if (b(bundle)) {
                    return;
                }
                this.c.addFirst(bundle);
                return;
            }
            this.c.addFirst(bundle);
            Log.i("MessengerUtils", "save the bundle " + bundle);
        }

        public final void e() {
            Message messageObtain = Message.obtain(this.d, 1, ProcessUtils.getCurrentProcessName().hashCode(), 0);
            messageObtain.replyTo = this.e;
            try {
                this.b.send(messageObtain);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Utils.getApp().unbindService(this.f);
            } catch (Exception unused) {
            }
        }
    }

    public static void post(@NonNull String str, @NonNull Bundle bundle) {
        bundle.putString(KEY_STRING, str);
        a aVar = sLocalClient;
        if (aVar != null) {
            aVar.d(bundle);
        } else {
            Intent intent = new Intent(Utils.getApp(), (Class<?>) ServerService.class);
            intent.putExtras(bundle);
            startServiceCompat(intent);
        }
        Iterator<a> it = sClientMap.values().iterator();
        while (it.hasNext()) {
            it.next().d(bundle);
        }
    }

    public static void register(String str) {
        if (sClientMap.containsKey(str)) {
            Log.i("MessengerUtils", "register: client registered: " + str);
            return;
        }
        a aVar = new a(str);
        if (aVar.a()) {
            sClientMap.put(str, aVar);
            return;
        }
        Log.e("MessengerUtils", "register: client bind failed: " + str);
    }

    private static void startServiceCompat(Intent intent) {
        try {
            intent.setFlags(32);
            Utils.getApp().startForegroundService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void subscribe(@NonNull String str, @NonNull MessageCallback messageCallback) {
        subscribers.put(str, messageCallback);
    }

    public static void unregister(String str) {
        if (!sClientMap.containsKey(str)) {
            Log.i("MessengerUtils", "unregister: client didn't register: " + str);
            return;
        }
        a aVar = sClientMap.get(str);
        sClientMap.remove(str);
        if (aVar != null) {
            aVar.e();
        }
    }

    public static void unsubscribe(@NonNull String str) {
        subscribers.remove(str);
    }

    public static void unregister() {
        if (ProcessUtils.isMainProcess()) {
            if (!ServiceUtils.isServiceRunning(ServerService.class.getName())) {
                Log.i("MessengerUtils", "Server service isn't running.");
                return;
            } else {
                Utils.getApp().stopService(new Intent(Utils.getApp(), (Class<?>) ServerService.class));
            }
        }
        a aVar = sLocalClient;
        if (aVar != null) {
            aVar.e();
        }
    }

    public static void register() {
        if (ProcessUtils.isMainProcess()) {
            if (ServiceUtils.isServiceRunning(ServerService.class.getName())) {
                Log.i("MessengerUtils", "Server service is running.");
                return;
            } else {
                startServiceCompat(new Intent(Utils.getApp(), (Class<?>) ServerService.class));
                return;
            }
        }
        if (sLocalClient == null) {
            a aVar = new a(null);
            if (aVar.a()) {
                sLocalClient = aVar;
                return;
            } else {
                Log.e("MessengerUtils", "Bind service failed.");
                return;
            }
        }
        Log.i("MessengerUtils", "The client have been bind.");
    }
}
