package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: NotificationManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ro1 {
    public static String d;
    public static c g;
    public final Context a;
    public final NotificationManager b;
    public static final Object c = new Object();
    public static HashSet e = new HashSet();
    public static final Object f = new Object();

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    public static class a implements d {
        public final String a;
        public final int b;
        public final String c;
        public final Notification d;

        public a(String str, int i, String str2, Notification notification) {
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = notification;
        }

        @Override // ro1.d
        public final void a(INotificationSideChannel iNotificationSideChannel) {
            iNotificationSideChannel.notify(this.a, this.b, this.c, this.d);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("NotifyTask[packageName:");
            sb.append(this.a);
            sb.append(", id:");
            sb.append(this.b);
            sb.append(", tag:");
            return gq2.i(sb, this.c, "]");
        }
    }

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    public static class b {
        public final ComponentName a;
        public final IBinder b;

        public b(ComponentName componentName, IBinder iBinder) {
            this.a = componentName;
            this.b = iBinder;
        }
    }

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    public static class c implements Handler.Callback, ServiceConnection {
        public final Context e;
        public final HashMap g = new HashMap();
        public HashSet h = new HashSet();
        public final Handler f = new Handler(C0173a.h("NotificationManagerCompat").getLooper(), this);

        /* JADX INFO: compiled from: NotificationManagerCompat.java */
        public static class a {
            public final ComponentName a;
            public INotificationSideChannel c;
            public boolean b = false;
            public final ArrayDeque<d> d = new ArrayDeque<>();
            public int e = 0;

            public a(ComponentName componentName) {
                this.a = componentName;
            }
        }

        public c(Context context) {
            this.e = context;
        }

        public final void a(a aVar) {
            boolean z;
            boolean zIsLoggable = Log.isLoggable("NotifManCompat", 3);
            ComponentName componentName = aVar.a;
            ArrayDeque<d> arrayDeque = aVar.d;
            if (zIsLoggable) {
                Objects.toString(componentName);
                arrayDeque.size();
            }
            if (arrayDeque.isEmpty()) {
                return;
            }
            if (aVar.b) {
                z = true;
            } else {
                Intent component = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(componentName);
                Context context = this.e;
                boolean zBindService = context.bindService(component, this, 33);
                aVar.b = zBindService;
                if (zBindService) {
                    aVar.e = 0;
                } else {
                    Log.w("NotifManCompat", "Unable to bind to listener " + componentName);
                    context.unbindService(this);
                }
                z = aVar.b;
            }
            if (!z || aVar.c == null) {
                b(aVar);
                return;
            }
            while (true) {
                d dVarPeek = arrayDeque.peek();
                if (dVarPeek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        dVarPeek.toString();
                    }
                    dVarPeek.a(aVar.c);
                    arrayDeque.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Objects.toString(componentName);
                    }
                } catch (RemoteException e) {
                    Log.w("NotifManCompat", "RemoteException communicating with " + componentName, e);
                }
            }
            if (arrayDeque.isEmpty()) {
                return;
            }
            b(aVar);
        }

        public final void b(a aVar) {
            Handler handler = this.f;
            ComponentName componentName = aVar.a;
            if (handler.hasMessages(3, componentName)) {
                return;
            }
            int i = aVar.e + 1;
            aVar.e = i;
            if (i <= 6) {
                handler.sendMessageDelayed(handler.obtainMessage(3, componentName), (1 << (i - 1)) * 1000);
                return;
            }
            StringBuilder sb = new StringBuilder("Giving up on delivering ");
            ArrayDeque<d> arrayDeque = aVar.d;
            sb.append(arrayDeque.size());
            sb.append(" tasks to ");
            sb.append(componentName);
            sb.append(" after ");
            sb.append(aVar.e);
            sb.append(" retries");
            Log.w("NotifManCompat", sb.toString());
            arrayDeque.clear();
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            HashSet hashSet;
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    b bVar = (b) message.obj;
                    ComponentName componentName = bVar.a;
                    IBinder iBinder = bVar.b;
                    a aVar = (a) this.g.get(componentName);
                    if (aVar != null) {
                        aVar.c = INotificationSideChannel.Stub.asInterface(iBinder);
                        aVar.e = 0;
                        a(aVar);
                    }
                    return true;
                }
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    a aVar2 = (a) this.g.get((ComponentName) message.obj);
                    if (aVar2 != null) {
                        a(aVar2);
                    }
                    return true;
                }
                a aVar3 = (a) this.g.get((ComponentName) message.obj);
                if (aVar3 != null) {
                    if (aVar3.b) {
                        this.e.unbindService(this);
                        aVar3.b = false;
                    }
                    aVar3.c = null;
                }
                return true;
            }
            d dVar = (d) message.obj;
            String string = Settings.Secure.getString(this.e.getContentResolver(), "enabled_notification_listeners");
            synchronized (ro1.c) {
                if (string != null) {
                    try {
                        if (!string.equals(ro1.d)) {
                            String[] strArrSplit = string.split(":", -1);
                            HashSet hashSet2 = new HashSet(strArrSplit.length);
                            for (String str : strArrSplit) {
                                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                                if (componentNameUnflattenFromString != null) {
                                    hashSet2.add(componentNameUnflattenFromString.getPackageName());
                                }
                            }
                            ro1.e = hashSet2;
                            ro1.d = string;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                hashSet = ro1.e;
            }
            if (!hashSet.equals(this.h)) {
                this.h = hashSet;
                List<ResolveInfo> listQueryIntentServices = this.e.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
                HashSet<ComponentName> hashSet3 = new HashSet();
                for (ResolveInfo resolveInfo : listQueryIntentServices) {
                    if (hashSet.contains(resolveInfo.serviceInfo.packageName)) {
                        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                        ComponentName componentName2 = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                        if (resolveInfo.serviceInfo.permission != null) {
                            Log.w("NotifManCompat", "Permission present on component " + componentName2 + ", not adding listener record.");
                        } else {
                            hashSet3.add(componentName2);
                        }
                    }
                }
                for (ComponentName componentName3 : hashSet3) {
                    if (!this.g.containsKey(componentName3)) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Objects.toString(componentName3);
                        }
                        this.g.put(componentName3, new a(componentName3));
                    }
                }
                Iterator it = this.g.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (!hashSet3.contains(entry.getKey())) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Objects.toString(entry.getKey());
                        }
                        a aVar4 = (a) entry.getValue();
                        if (aVar4.b) {
                            this.e.unbindService(this);
                            aVar4.b = false;
                        }
                        aVar4.c = null;
                        it.remove();
                    }
                }
            }
            for (a aVar5 : this.g.values()) {
                aVar5.d.add(dVar);
                a(aVar5);
            }
            return true;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Objects.toString(componentName);
            }
            this.f.obtainMessage(1, new b(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Objects.toString(componentName);
            }
            this.f.obtainMessage(2, componentName).sendToTarget();
        }
    }

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    public interface d {
        void a(INotificationSideChannel iNotificationSideChannel);
    }

    public ro1(Context context) {
        this.a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    public final boolean a() {
        return this.b.areNotificationsEnabled();
    }

    public final void b(String str, int i, Notification notification) {
        Bundle bundle = notification.extras;
        boolean z = bundle != null && bundle.getBoolean("android.support.useSideChannel");
        NotificationManager notificationManager = this.b;
        if (!z) {
            notificationManager.notify(str, i, notification);
            return;
        }
        a aVar = new a(this.a.getPackageName(), i, str, notification);
        synchronized (f) {
            if (g == null) {
                g = new c(this.a.getApplicationContext());
            }
            g.f.obtainMessage(0, aVar).sendToTarget();
        }
        notificationManager.cancel(str, i);
    }
}
