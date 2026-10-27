package defpackage;

import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: ad, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0500ad implements Handler.Callback {
    public static final Status n = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status o = new Status(4, "The user must be signed in to make this API call.");
    public static final Object p = new Object();
    public static C0500ad q;
    public final Context d;
    public final C1223nc e;
    public final C0059Df f;
    public final Handler m;
    public long a = 5000;
    public long b = 120000;
    public long c = 10000;
    public final AtomicInteger g = new AtomicInteger(1);
    public final AtomicInteger h = new AtomicInteger(0);
    public final Map i = new ConcurrentHashMap(5, 0.75f, 1);
    public AbstractC1443rd j = null;
    public final Set k = new C1083l1();
    public final Set l = new C1083l1();

    public C0500ad(Context context, Looper looper, C1223nc c1223nc) {
        this.d = context;
        this.m = new HandlerC0347Tg(looper, this);
        this.e = c1223nc;
        this.f = new C0059Df(c1223nc);
        Handler handler = this.m;
        handler.sendMessage(handler.obtainMessage(6));
    }

    public static C0500ad c(Context context) {
        C0500ad c0500ad;
        synchronized (p) {
            if (q == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                Context applicationContext = context.getApplicationContext();
                C1223nc c1223nc = C1223nc.c;
                q = new C0500ad(applicationContext, looper, C1223nc.c);
            }
            c0500ad = q;
        }
        return c0500ad;
    }

    public final void a(AbstractC0092Fc abstractC0092Fc) {
        C0289Qc c0289Qc = abstractC0092Fc.d;
        C0415Xc c0415Xc = (C0415Xc) this.i.get(c0289Qc);
        if (c0415Xc == null) {
            c0415Xc = new C0415Xc(this, abstractC0092Fc);
            this.i.put(c0289Qc, c0415Xc);
        }
        if (c0415Xc.b()) {
            this.l.add(c0289Qc);
        }
        c0415Xc.a();
    }

    public final boolean b(ConnectionResult connectionResult, int i) {
        C1223nc c1223nc = this.e;
        Context context = this.d;
        Objects.requireNonNull(c1223nc);
        PendingIntent pendingIntentC = connectionResult.e() ? connectionResult.u : c1223nc.c(context, connectionResult.t, 0, null);
        if (pendingIntentC == null) {
            return false;
        }
        c1223nc.k(context, connectionResult.t, GoogleApiActivity.a(context, pendingIntentC, i));
        return true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        C0415Xc c0415Xc;
        Feature[] featureArrF;
        int i = message.what;
        int i2 = 0;
        switch (i) {
            case 1:
                this.c = ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.m.removeMessages(12);
                for (C0289Qc c0289Qc : this.i.keySet()) {
                    Handler handler = this.m;
                    handler.sendMessageDelayed(handler.obtainMessage(12, c0289Qc), this.c);
                }
                return true;
            case 2:
                AbstractC1719we abstractC1719we = (AbstractC1719we) message.obj;
                Iterator it = ((C1413r1) abstractC1719we.a.keySet()).iterator();
                while (true) {
                    C1304p1 c1304p1 = (C1304p1) it;
                    if (c1304p1.hasNext()) {
                        C0289Qc c0289Qc2 = (C0289Qc) c1304p1.next();
                        C0415Xc c0415Xc2 = (C0415Xc) this.i.get(c0289Qc2);
                        if (c0415Xc2 == null) {
                            abstractC1719we.a(c0289Qc2, new ConnectionResult(13), null);
                        } else if (((BaseGmsClient) c0415Xc2.b).a()) {
                            ConnectionResult connectionResult = ConnectionResult.w;
                            ((BaseGmsClient) c0415Xc2.b).f();
                            abstractC1719we.a(c0289Qc2, connectionResult, "com.google.android.gms");
                        } else {
                            Cif.c(c0415Xc2.m.m);
                            if (c0415Xc2.l != null) {
                                Cif.c(c0415Xc2.m.m);
                                abstractC1719we.a(c0289Qc2, c0415Xc2.l, null);
                            } else {
                                Cif.c(c0415Xc2.m.m);
                                c0415Xc2.f.add(abstractC1719we);
                                c0415Xc2.a();
                            }
                        }
                    }
                }
                return true;
            case 3:
                for (C0415Xc c0415Xc3 : this.i.values()) {
                    c0415Xc3.p();
                    c0415Xc3.a();
                }
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
            case 8:
            case 13:
                C0892he c0892he = (C0892he) message.obj;
                C0415Xc c0415Xc4 = (C0415Xc) this.i.get(c0892he.c.d);
                if (c0415Xc4 == null) {
                    a(c0892he.c);
                    c0415Xc4 = (C0415Xc) this.i.get(c0892he.c.d);
                }
                if (!c0415Xc4.b() || this.h.get() == c0892he.b) {
                    c0415Xc4.k(c0892he.a);
                } else {
                    c0892he.a.e(n);
                    c0415Xc4.e();
                }
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                int i3 = message.arg1;
                ConnectionResult connectionResult2 = (ConnectionResult) message.obj;
                Iterator it2 = this.i.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        c0415Xc = (C0415Xc) it2.next();
                        if (c0415Xc.h == i3) {
                        }
                    } else {
                        c0415Xc = null;
                    }
                }
                if (c0415Xc != null) {
                    C1223nc c1223nc = this.e;
                    int i4 = connectionResult2.t;
                    Objects.requireNonNull(c1223nc);
                    String strB = ConnectionResult.b(i4);
                    String str = connectionResult2.v;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(strB).length() + 69);
                    sb.append("Error resolution was canceled by the user, original error message: ");
                    sb.append(strB);
                    sb.append(": ");
                    sb.append(str);
                    Status status = new Status(17, sb.toString());
                    Cif.c(c0415Xc.m.m);
                    c0415Xc.j(status, null, false);
                } else {
                    StringBuilder sb2 = new StringBuilder(76);
                    sb2.append("Could not find API instance ");
                    sb2.append(i3);
                    sb2.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb2.toString(), new Exception());
                }
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                if (this.d.getApplicationContext() instanceof Application) {
                    Application application = (Application) this.d.getApplicationContext();
                    synchronized (ComponentCallbacks2C0325Sc.w) {
                        if (!ComponentCallbacks2C0325Sc.w.v) {
                            application.registerActivityLifecycleCallbacks(ComponentCallbacks2C0325Sc.w);
                            application.registerComponentCallbacks(ComponentCallbacks2C0325Sc.w);
                            ComponentCallbacks2C0325Sc.w.v = true;
                        }
                        break;
                    }
                    ComponentCallbacks2C0325Sc componentCallbacks2C0325Sc = ComponentCallbacks2C0325Sc.w;
                    C0362Ud c0362Ud = new C0362Ud(this);
                    Objects.requireNonNull(componentCallbacks2C0325Sc);
                    synchronized (ComponentCallbacks2C0325Sc.w) {
                        componentCallbacks2C0325Sc.u.add(c0362Ud);
                        break;
                    }
                    ComponentCallbacks2C0325Sc componentCallbacks2C0325Sc2 = ComponentCallbacks2C0325Sc.w;
                    if (!componentCallbacks2C0325Sc2.t.get()) {
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(runningAppProcessInfo);
                        if (!componentCallbacks2C0325Sc2.t.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                            componentCallbacks2C0325Sc2.s.set(true);
                        }
                    }
                    if (!componentCallbacks2C0325Sc2.s.get()) {
                        this.c = 300000L;
                    }
                }
                return true;
            case 7:
                a((AbstractC0092Fc) message.obj);
                return true;
            case 9:
                if (this.i.containsKey(message.obj)) {
                    C0415Xc c0415Xc5 = (C0415Xc) this.i.get(message.obj);
                    Cif.c(c0415Xc5.m.m);
                    if (c0415Xc5.j) {
                        c0415Xc5.a();
                    }
                }
                return true;
            case 10:
                Iterator it3 = this.l.iterator();
                while (it3.hasNext()) {
                    ((C0415Xc) this.i.remove((C0289Qc) it3.next())).e();
                }
                this.l.clear();
                return true;
            case 11:
                if (this.i.containsKey(message.obj)) {
                    C0415Xc c0415Xc6 = (C0415Xc) this.i.get(message.obj);
                    Cif.c(c0415Xc6.m.m);
                    if (c0415Xc6.j) {
                        c0415Xc6.q();
                        C0500ad c0500ad = c0415Xc6.m;
                        Status status2 = c0500ad.e.e(c0500ad.d) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error.");
                        Cif.c(c0415Xc6.m.m);
                        c0415Xc6.j(status2, null, false);
                        c0415Xc6.b.disconnect();
                    }
                }
                return true;
            case 12:
                if (this.i.containsKey(message.obj)) {
                    ((C0415Xc) this.i.get(message.obj)).t(true);
                }
                return true;
            case 14:
                throw null;
            case 15:
                C0433Yc c0433Yc = (C0433Yc) message.obj;
                if (this.i.containsKey(c0433Yc.a)) {
                    C0415Xc c0415Xc7 = (C0415Xc) this.i.get(c0433Yc.a);
                    if (c0415Xc7.k.contains(c0433Yc) && !c0415Xc7.j) {
                        if (((BaseGmsClient) c0415Xc7.b).a()) {
                            c0415Xc7.o();
                        } else {
                            c0415Xc7.a();
                        }
                    }
                }
                return true;
            case 16:
                C0433Yc c0433Yc2 = (C0433Yc) message.obj;
                if (this.i.containsKey(c0433Yc2.a)) {
                    C0415Xc c0415Xc8 = (C0415Xc) this.i.get(c0433Yc2.a);
                    if (c0415Xc8.k.remove(c0433Yc2)) {
                        c0415Xc8.m.m.removeMessages(15, c0433Yc2);
                        c0415Xc8.m.m.removeMessages(16, c0433Yc2);
                        Feature feature = c0433Yc2.b;
                        ArrayList arrayList = new ArrayList(c0415Xc8.a.size());
                        for (AbstractC0218Md abstractC0218Md : c0415Xc8.a) {
                            if ((abstractC0218Md instanceof AbstractC1335pe) && (featureArrF = ((AbstractC1335pe) abstractC0218Md).f(c0415Xc8)) != null) {
                                int length = featureArrF.length;
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= length) {
                                        i5 = -1;
                                    } else if (!AbstractC0893hf.a(featureArrF[i5], feature)) {
                                        i5++;
                                    }
                                }
                                if (i5 >= 0) {
                                    arrayList.add(abstractC0218Md);
                                }
                            }
                        }
                        int size = arrayList.size();
                        while (i2 < size) {
                            Object obj = arrayList.get(i2);
                            i2++;
                            AbstractC0218Md abstractC0218Md2 = (AbstractC0218Md) obj;
                            c0415Xc8.a.remove(abstractC0218Md2);
                            abstractC0218Md2.c(new C0253Oc(feature));
                        }
                    }
                }
                return true;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
    }
}
