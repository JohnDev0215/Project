package v1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import f.AbstractBinderC1242b;
import f.C1241a;
import f.InterfaceC1243c;
import h6.n;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: v1.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ServiceConnectionC2542i implements Handler.Callback, ServiceConnection {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Handler f22971m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final HashMap f22972n = new HashMap();
    public HashSet o = new HashSet();

    public ServiceConnectionC2542i(Context context) {
        this.l = context;
        HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
        handlerThread.start();
        this.f22971m = new Handler(handlerThread.getLooper(), this);
    }

    public final void a(C2541h c2541h) {
        boolean z6;
        ArrayDeque arrayDeque;
        boolean zIsLoggable = Log.isLoggable("NotifManCompat", 3);
        ComponentName componentName = c2541h.f22968a;
        if (zIsLoggable) {
            Log.d("NotifManCompat", "Processing component " + componentName + ", " + c2541h.d.size() + " queued tasks");
        }
        if (c2541h.d.isEmpty()) {
            return;
        }
        if (c2541h.f22969b) {
            z6 = true;
        } else {
            Intent component = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(componentName);
            Context context = this.l;
            boolean zBindService = context.bindService(component, this, 33);
            c2541h.f22969b = zBindService;
            if (zBindService) {
                c2541h.f22970e = 0;
            } else {
                n.t("NotifManCompat", "Unable to bind to listener " + componentName);
                context.unbindService(this);
            }
            z6 = c2541h.f22969b;
        }
        if (!z6 || c2541h.c == null) {
            b(c2541h);
            return;
        }
        while (true) {
            arrayDeque = c2541h.d;
            C2539f c2539f = (C2539f) arrayDeque.peek();
            if (c2539f == null) {
                break;
            }
            try {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Sending task " + c2539f);
                }
                c2539f.a(c2541h.c);
                arrayDeque.remove();
            } catch (DeadObjectException unused) {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Remote service has died: " + componentName);
                }
            } catch (RemoteException e10) {
                n.u("NotifManCompat", "RemoteException communicating with " + componentName, e10);
            }
        }
        if (arrayDeque.isEmpty()) {
            return;
        }
        b(c2541h);
    }

    public final void b(C2541h c2541h) {
        Handler handler = this.f22971m;
        ComponentName componentName = c2541h.f22968a;
        if (handler.hasMessages(3, componentName)) {
            return;
        }
        int i7 = c2541h.f22970e;
        int i10 = i7 + 1;
        c2541h.f22970e = i10;
        if (i10 <= 6) {
            int i11 = (1 << i7) * 1000;
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Scheduling retry for " + i11 + " ms");
            }
            handler.sendMessageDelayed(handler.obtainMessage(3, componentName), i11);
            return;
        }
        StringBuilder sb = new StringBuilder("Giving up on delivering ");
        ArrayDeque arrayDeque = c2541h.d;
        sb.append(arrayDeque.size());
        sb.append(" tasks to ");
        sb.append(componentName);
        sb.append(" after ");
        sb.append(c2541h.f22970e);
        sb.append(" retries");
        n.t("NotifManCompat", sb.toString());
        arrayDeque.clear();
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HashSet hashSet;
        int i7 = message.what;
        InterfaceC1243c interfaceC1243c = null;
        if (i7 != 0) {
            if (i7 != 1) {
                if (i7 != 2) {
                    if (i7 != 3) {
                        return false;
                    }
                    C2541h c2541h = (C2541h) this.f22972n.get((ComponentName) message.obj);
                    if (c2541h != null) {
                        a(c2541h);
                    }
                    return true;
                }
                C2541h c2541h2 = (C2541h) this.f22972n.get((ComponentName) message.obj);
                if (c2541h2 != null) {
                    if (c2541h2.f22969b) {
                        this.l.unbindService(this);
                        c2541h2.f22969b = false;
                    }
                    c2541h2.c = null;
                }
                return true;
            }
            C2540g c2540g = (C2540g) message.obj;
            ComponentName componentName = c2540g.f22966a;
            IBinder iBinder = c2540g.f22967b;
            C2541h c2541h3 = (C2541h) this.f22972n.get(componentName);
            if (c2541h3 != null) {
                int i10 = AbstractBinderC1242b.c;
                if (iBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(InterfaceC1243c.f14527a);
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1243c)) {
                        C1241a c1241a = new C1241a();
                        c1241a.c = iBinder;
                        interfaceC1243c = c1241a;
                    } else {
                        interfaceC1243c = (InterfaceC1243c) iInterfaceQueryLocalInterface;
                    }
                }
                c2541h3.c = interfaceC1243c;
                c2541h3.f22970e = 0;
                a(c2541h3);
            }
            return true;
        }
        C2539f c2539f = (C2539f) message.obj;
        String string = Settings.Secure.getString(this.l.getContentResolver(), "enabled_notification_listeners");
        synchronized (j.c) {
            if (string != null) {
                try {
                    if (!string.equals(j.d)) {
                        String[] strArrSplit = string.split(":", -1);
                        HashSet hashSet2 = new HashSet(strArrSplit.length);
                        for (String str : strArrSplit) {
                            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                            if (componentNameUnflattenFromString != null) {
                                hashSet2.add(componentNameUnflattenFromString.getPackageName());
                            }
                        }
                        j.f22973e = hashSet2;
                        j.d = string;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            hashSet = j.f22973e;
        }
        if (!hashSet.equals(this.o)) {
            this.o = hashSet;
            List<ResolveInfo> listQueryIntentServices = this.l.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet3 = new HashSet();
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (hashSet.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName2 = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        n.t("NotifManCompat", "Permission present on component " + componentName2 + ", not adding listener record.");
                    } else {
                        hashSet3.add(componentName2);
                    }
                }
            }
            for (ComponentName componentName3 : hashSet3) {
                if (!this.f22972n.containsKey(componentName3)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName3);
                    }
                    this.f22972n.put(componentName3, new C2541h(componentName3));
                }
            }
            Iterator it = this.f22972n.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!hashSet3.contains(entry.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                    }
                    C2541h c2541h4 = (C2541h) entry.getValue();
                    if (c2541h4.f22969b) {
                        this.l.unbindService(this);
                        c2541h4.f22969b = false;
                    }
                    c2541h4.c = null;
                    it.remove();
                }
            }
        }
        for (C2541h c2541h5 : this.f22972n.values()) {
            c2541h5.d.add(c2539f);
            a(c2541h5);
        }
        return true;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Connected to service " + componentName);
        }
        this.f22971m.obtainMessage(1, new C2540g(componentName, iBinder)).sendToTarget();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Disconnected from service " + componentName);
        }
        this.f22971m.obtainMessage(2, componentName).sendToTarget();
    }
}
