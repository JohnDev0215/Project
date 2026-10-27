package b.b.l;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.CallbackMsg;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: RemoteModelHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Context f2004b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static j f2005c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static k f2003a = new k("MVC-REMOTE");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static RemoteCallbackList<e> f2006d = new RemoteCallbackList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ArrayList<b> f2007e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static ArrayList<b.b.l.u.k> f2008f = new ArrayList<>();
    public static ExecutorService g = Executors.newFixedThreadPool(1, new a());
    public static ArrayList<c> h = new ArrayList<>();

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f2009a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("RemoteNotifyThread-" + this.f2009a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f2010a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2011b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2012c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f2013d;

        public b(IBinder iBinder, String str, int i, long j) {
            this.f2010a = iBinder;
            this.f2011b = str;
            this.f2012c = i;
            this.f2013d = j;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public interface c {
        void clientDisconnect(int i);
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class d implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f2014a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2015b;

        public d(e eVar) {
            this.f2014a = eVar;
            this.f2015b = p.h(eVar.asBinder());
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Iterator it = p.h.iterator();
            while (it.hasNext()) {
                ((c) it.next()).clientDisconnect(this.f2015b);
            }
            p.s(this.f2014a);
        }
    }

    public static void c(c cVar) {
        synchronized (h) {
            if (!h.contains(cVar)) {
                h.add(cVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v6 */
    public static Reply d(CallbackMsg callbackMsg) {
        Reply reply;
        if (f2006d == null) {
            Reply reply2 = new Reply();
            reply2.h(-1);
            reply2.g("notifyMessage failed: mCallbacks is null");
            return reply2;
        }
        synchronized (p.class) {
            int iBeginBroadcast = f2006d.beginBroadcast();
            reply = null;
            int i = 0;
            CallbackMsg callbackMsg2 = callbackMsg;
            while (i < iBeginBroadcast) {
                try {
                    e eVar = (e) f2006d.getBroadcastItem(i);
                    if (eVar != null && callbackMsg2.j() == h(eVar.asBinder()) && callbackMsg2.b() == g(eVar.asBinder())) {
                        if (callbackMsg2.s() || !callbackMsg2.r().equals(Void.TYPE.getName())) {
                            Reply replyQ = eVar.Q(callbackMsg2);
                            reply = replyQ;
                            callbackMsg2 = replyQ;
                        } else {
                            eVar.G(callbackMsg2);
                            Reply reply3 = new Reply();
                            reply3.h(0);
                            reply = reply3;
                            callbackMsg2 = callbackMsg2;
                        }
                        f2006d.finishBroadcast();
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.h(-1);
                    reply4.g(Log.getStackTraceString(th));
                    reply = reply4;
                }
                i++;
                reply = reply;
                callbackMsg2 = callbackMsg2;
            }
            f2006d.finishBroadcast();
        }
        return reply;
    }

    public static Reply e(int i, Reply reply) {
        Reply reply2;
        if (f2006d == null) {
            Reply reply3 = new Reply();
            reply3.h(-1);
            reply3.g("notifyMessage failed: mCallbacks is null");
            return reply3;
        }
        synchronized (p.class) {
            int iBeginBroadcast = f2006d.beginBroadcast();
            reply2 = null;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    e eVar = (e) f2006d.getBroadcastItem(i2);
                    if (eVar != null && i == h(eVar.asBinder())) {
                        eVar.R(reply);
                        break;
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.h(-1);
                    reply4.g(Log.getStackTraceString(th));
                    reply2 = reply4;
                }
            }
            f2006d.finishBroadcast();
        }
        return reply2;
    }

    public static void f() {
        if (f2005c == null) {
            j jVar = new j(f2004b);
            f2005c = jVar;
            jVar.f(f2003a.c());
            f2005c.a();
        }
    }

    public static long g(IBinder iBinder) {
        synchronized (p.class) {
            for (b bVar : f2007e) {
                if (iBinder == bVar.f2010a) {
                    return bVar.f2013d;
                }
            }
            return 0L;
        }
    }

    public static int h(IBinder iBinder) {
        synchronized (p.class) {
            for (b bVar : f2007e) {
                if (iBinder == bVar.f2010a) {
                    return bVar.f2012c;
                }
            }
            return 0;
        }
    }

    public static Reply i(GetMsg getMsg) {
        int size = f2008f.size();
        f2003a.d("getInstance: size = " + size);
        for (b.b.l.u.k kVar : f2008f) {
            if (c.a.a.a.a.c.b(getMsg.k(), kVar.B())) {
                return kVar.A(getMsg);
            }
        }
        Reply reply = new Reply();
        reply.h(-1);
        reply.g("RpcResponse list is empty");
        return reply;
    }

    public static l j(Map<String, String> map) {
        l lVar = new l();
        lVar.a(map.get("actionName"));
        lVar.d("1".equals(map.get("actionSync")));
        lVar.c("1".equals(map.get("actionLog")));
        return lVar;
    }

    public static Reply k(InvokeMsg invokeMsg) {
        for (b.b.l.u.k kVar : f2008f) {
            if (c.a.a.a.a.c.b(invokeMsg.k(), kVar.B())) {
                return kVar.C(invokeMsg);
            }
        }
        Reply reply = new Reply();
        reply.h(-1);
        reply.g("RpcResponse list is empty");
        return reply;
    }

    public static boolean l(IBinder iBinder) {
        synchronized (p.class) {
            Iterator<b> it = f2007e.iterator();
            while (it.hasNext()) {
                if (iBinder == it.next().f2010a) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Object m(l lVar) {
        f();
        return f2005c.d().l(f2005c.d().u(lVar));
    }

    public static boolean n(e eVar, String str, int i, long j) {
        Objects.requireNonNull(eVar, "callback is null");
        f2003a.d("registerCallback: " + eVar);
        IBinder iBinderAsBinder = eVar.asBinder();
        if (l(iBinderAsBinder)) {
            return false;
        }
        o(iBinderAsBinder, str, i, j);
        try {
            iBinderAsBinder.linkToDeath(new d(eVar), 0);
        } catch (RemoteException e2) {
            f2003a.b(e2);
        }
        return f2006d.register(eVar);
    }

    public static void o(IBinder iBinder, String str, int i, long j) {
        synchronized (p.class) {
            f2007e.add(new b(iBinder, str, i, j));
            f2003a.d("registerClient: " + iBinder + ", " + str + ", " + i + ", " + j);
        }
    }

    public static void p(b.b.l.u.k kVar) {
        Iterator<b.b.l.u.k> it = f2008f.iterator();
        while (it.hasNext()) {
            if (c.a.a.a.a.c.b(kVar.B(), it.next().B())) {
                throw new RuntimeException("Cannot register the RpcResponse use exist describe");
            }
        }
        if (f2008f.contains(kVar)) {
            return;
        }
        f2008f.add(kVar);
    }

    public static void q(c cVar) {
        synchronized (h) {
            h.remove(cVar);
        }
    }

    public static void r(GCMsg gCMsg) {
        Iterator<b.b.l.u.k> it = f2008f.iterator();
        while (it.hasNext()) {
            it.next().s(gCMsg);
        }
    }

    public static boolean s(e eVar) {
        Objects.requireNonNull(eVar);
        f2003a.d("unregisterCallback: " + eVar);
        t(eVar.asBinder());
        return f2006d.unregister(eVar);
    }

    public static void t(IBinder iBinder) {
        synchronized (p.class) {
            Iterator<b> it = f2007e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b next = it.next();
                if (next.f2010a == iBinder) {
                    f2007e.remove(next);
                    f2003a.d("unregisterClient: " + next.f2010a + ", " + next.f2011b);
                    break;
                }
            }
        }
    }

    public static void u(b.b.l.u.k kVar) {
        f2008f.remove(kVar);
    }
}
