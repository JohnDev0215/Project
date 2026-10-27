package defpackage;

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
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class T1 implements Handler.Callback, ServiceConnection {
    public final Context a;
    public final HandlerThread b;
    public final Handler c;
    public final Map d = new HashMap();
    public Set e = new HashSet();

    public T1(Context context) {
        this.a = context;
        HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
        this.b = handlerThread;
        handlerThread.start();
        this.c = new Handler(this.b.getLooper(), this);
    }

    public final void a(S1 s1) {
        boolean z;
        if (Log.isLoggable("NotifManCompat", 3)) {
            StringBuilder sbF = Z7.f("Processing component ");
            sbF.append(s1.a);
            sbF.append(", ");
            sbF.append(s1.d.size());
            sbF.append(" queued tasks");
            sbF.toString();
        }
        if (s1.d.isEmpty()) {
            return;
        }
        if (s1.b) {
            z = true;
        } else {
            boolean zBindService = this.a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(s1.a), this, 33);
            s1.b = zBindService;
            if (zBindService) {
                s1.e = 0;
            } else {
                StringBuilder sbF2 = Z7.f("Unable to bind to listener ");
                sbF2.append(s1.a);
                Log.w("NotifManCompat", sbF2.toString());
                this.a.unbindService(this);
            }
            z = s1.b;
        }
        if (!z || s1.c == null) {
            b(s1);
            return;
        }
        while (true) {
            U1 u1 = (U1) s1.d.peek();
            if (u1 == null) {
                break;
            }
            try {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    String str = "Sending task " + u1;
                }
                ((Q1) u1).a(s1.c);
                s1.d.remove();
            } catch (DeadObjectException unused) {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    StringBuilder sbF3 = Z7.f("Remote service has died: ");
                    sbF3.append(s1.a);
                    sbF3.toString();
                }
            } catch (RemoteException e) {
                StringBuilder sbF4 = Z7.f("RemoteException communicating with ");
                sbF4.append(s1.a);
                Log.w("NotifManCompat", sbF4.toString(), e);
            }
        }
        if (s1.d.isEmpty()) {
            return;
        }
        b(s1);
    }

    public final void b(S1 s1) {
        if (this.c.hasMessages(3, s1.a)) {
            return;
        }
        int i = s1.e + 1;
        s1.e = i;
        if (i <= 6) {
            this.c.sendMessageDelayed(this.c.obtainMessage(3, s1.a), (1 << (i - 1)) * 1000);
            return;
        }
        StringBuilder sbF = Z7.f("Giving up on delivering ");
        sbF.append(s1.d.size());
        sbF.append(" tasks to ");
        sbF.append(s1.a);
        sbF.append(" after ");
        sbF.append(s1.e);
        sbF.append(" retries");
        Log.w("NotifManCompat", sbF.toString());
        s1.d.clear();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Set set;
        int i = message.what;
        if (i != 0) {
            if (i == 1) {
                R1 r1 = (R1) message.obj;
                ComponentName componentName = r1.a;
                IBinder iBinder = r1.b;
                S1 s1 = (S1) this.d.get(componentName);
                if (s1 != null) {
                    s1.c = AbstractBinderC0523b.c(iBinder);
                    s1.e = 0;
                    a(s1);
                }
                return true;
            }
            if (i != 2) {
                if (i != 3) {
                    return false;
                }
                S1 s12 = (S1) this.d.get((ComponentName) message.obj);
                if (s12 != null) {
                    a(s12);
                }
                return true;
            }
            S1 s13 = (S1) this.d.get((ComponentName) message.obj);
            if (s13 != null) {
                if (s13.b) {
                    this.a.unbindService(this);
                    s13.b = false;
                }
                s13.c = null;
            }
            return true;
        }
        U1 u1 = (U1) message.obj;
        String string = Settings.Secure.getString(this.a.getContentResolver(), "enabled_notification_listeners");
        synchronized (V1.c) {
            if (string != null) {
                if (!string.equals(V1.d)) {
                    String[] strArrSplit = string.split(":", -1);
                    HashSet hashSet = new HashSet(strArrSplit.length);
                    for (String str : strArrSplit) {
                        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                        if (componentNameUnflattenFromString != null) {
                            hashSet.add(componentNameUnflattenFromString.getPackageName());
                        }
                    }
                    V1.e = hashSet;
                    V1.d = string;
                }
                set = V1.e;
            } else {
                set = V1.e;
            }
        }
        if (!set.equals(this.e)) {
            this.e = set;
            List<ResolveInfo> listQueryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet2 = new HashSet();
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (set.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName2 = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName2 + ", not adding listener record.");
                    } else {
                        hashSet2.add(componentName2);
                    }
                }
            }
            for (ComponentName componentName3 : hashSet2) {
                if (!this.d.containsKey(componentName3)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        String str2 = "Adding listener record for " + componentName3;
                    }
                    this.d.put(componentName3, new S1(componentName3));
                }
            }
            Iterator it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!hashSet2.contains(entry.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        StringBuilder sbF = Z7.f("Removing listener record for ");
                        sbF.append(entry.getKey());
                        sbF.toString();
                    }
                    S1 s14 = (S1) entry.getValue();
                    if (s14.b) {
                        this.a.unbindService(this);
                        s14.b = false;
                    }
                    s14.c = null;
                    it.remove();
                }
            }
        }
        for (S1 s15 : this.d.values()) {
            s15.d.add(u1);
            a(s15);
        }
        return true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            String str = "Connected to service " + componentName;
        }
        this.c.obtainMessage(1, new R1(componentName, iBinder)).sendToTarget();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            String str = "Disconnected from service " + componentName;
        }
        this.c.obtainMessage(2, componentName).sendToTarget();
    }
}
