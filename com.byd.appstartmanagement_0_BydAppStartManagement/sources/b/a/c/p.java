package b.a.c;

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
    public static Context f248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static j f249c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static k f247a = new k("MVC-REMOTE");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static RemoteCallbackList<e> f250d = new RemoteCallbackList<>();
    public static ArrayList<b> e = new ArrayList<>();
    public static ArrayList<b.a.c.u.k> f = new ArrayList<>();
    public static ExecutorService g = Executors.newFixedThreadPool(1, new a());
    public static ArrayList<c> h = new ArrayList<>();

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f251a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("RemoteNotifyThread-" + this.f251a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f252a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f253b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f254c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f255d;

        public b(IBinder iBinder, String str, int i, long j) {
            this.f252a = iBinder;
            this.f253b = str;
            this.f254c = i;
            this.f255d = j;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public interface c {
        void clientDisconnect(int i);
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class d implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f257b;

        public d(e eVar) {
            this.f256a = eVar;
            this.f257b = p.h(eVar.asBinder());
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Iterator it = p.h.iterator();
            while (it.hasNext()) {
                ((c) it.next()).clientDisconnect(this.f257b);
            }
            p.s(this.f256a);
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
        if (f250d == null) {
            Reply reply2 = new Reply();
            reply2.h(-1);
            reply2.g("notifyMessage failed: mCallbacks is null");
            return reply2;
        }
        synchronized (p.class) {
            int iBeginBroadcast = f250d.beginBroadcast();
            reply = null;
            int i = 0;
            CallbackMsg callbackMsg2 = callbackMsg;
            while (i < iBeginBroadcast) {
                try {
                    e eVar = (e) f250d.getBroadcastItem(i);
                    if (eVar != null && callbackMsg2.j() == h(eVar.asBinder()) && callbackMsg2.b() == g(eVar.asBinder())) {
                        if (callbackMsg2.s() || !callbackMsg2.r().equals(Void.TYPE.getName())) {
                            Reply replyT = eVar.t(callbackMsg2);
                            reply = replyT;
                            callbackMsg2 = replyT;
                        } else {
                            eVar.o(callbackMsg2);
                            Reply reply3 = new Reply();
                            reply3.h(0);
                            reply = reply3;
                            callbackMsg2 = callbackMsg2;
                        }
                        f250d.finishBroadcast();
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
            f250d.finishBroadcast();
        }
        return reply;
    }

    public static Reply e(int i, Reply reply) {
        Reply reply2;
        if (f250d == null) {
            Reply reply3 = new Reply();
            reply3.h(-1);
            reply3.g("notifyMessage failed: mCallbacks is null");
            return reply3;
        }
        synchronized (p.class) {
            int iBeginBroadcast = f250d.beginBroadcast();
            reply2 = null;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    e eVar = (e) f250d.getBroadcastItem(i2);
                    if (eVar != null && i == h(eVar.asBinder())) {
                        eVar.u(reply);
                        break;
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.h(-1);
                    reply4.g(Log.getStackTraceString(th));
                    reply2 = reply4;
                }
            }
            f250d.finishBroadcast();
        }
        return reply2;
    }

    public static void f() {
        if (f249c == null) {
            j jVar = new j(f248b);
            f249c = jVar;
            jVar.f(f247a.c());
            f249c.a();
        }
    }

    public static long g(IBinder iBinder) {
        synchronized (p.class) {
            for (b bVar : e) {
                if (iBinder == bVar.f252a) {
                    return bVar.f255d;
                }
            }
            return 0L;
        }
    }

    public static int h(IBinder iBinder) {
        synchronized (p.class) {
            for (b bVar : e) {
                if (iBinder == bVar.f252a) {
                    return bVar.f254c;
                }
            }
            return 0;
        }
    }

    public static Reply i(GetMsg getMsg) {
        int size = f.size();
        f247a.d("getInstance: size = " + size);
        for (b.a.c.u.k kVar : f) {
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
        for (b.a.c.u.k kVar : f) {
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
            Iterator<b> it = e.iterator();
            while (it.hasNext()) {
                if (iBinder == it.next().f252a) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Object m(l lVar) {
        f();
        return f249c.d().l(f249c.d().u(lVar));
    }

    public static boolean n(e eVar, String str, int i, long j) {
        Objects.requireNonNull(eVar, "callback is null");
        f247a.d("registerCallback: " + eVar);
        IBinder iBinderAsBinder = eVar.asBinder();
        if (l(iBinderAsBinder)) {
            return false;
        }
        o(iBinderAsBinder, str, i, j);
        try {
            iBinderAsBinder.linkToDeath(new d(eVar), 0);
        } catch (RemoteException e2) {
            f247a.b(e2);
        }
        return f250d.register(eVar);
    }

    public static void o(IBinder iBinder, String str, int i, long j) {
        synchronized (p.class) {
            e.add(new b(iBinder, str, i, j));
            f247a.d("registerClient: " + iBinder + ", " + str + ", " + i + ", " + j);
        }
    }

    public static void p(b.a.c.u.k kVar) {
        Iterator<b.a.c.u.k> it = f.iterator();
        while (it.hasNext()) {
            if (c.a.a.a.a.c.b(kVar.B(), it.next().B())) {
                throw new RuntimeException("Cannot register the RpcResponse use exist describe");
            }
        }
        if (f.contains(kVar)) {
            return;
        }
        f.add(kVar);
    }

    public static void q(c cVar) {
        synchronized (h) {
            h.remove(cVar);
        }
    }

    public static void r(GCMsg gCMsg) {
        Iterator<b.a.c.u.k> it = f.iterator();
        while (it.hasNext()) {
            it.next().s(gCMsg);
        }
    }

    public static boolean s(e eVar) {
        Objects.requireNonNull(eVar);
        f247a.d("unregisterCallback: " + eVar);
        t(eVar.asBinder());
        return f250d.unregister(eVar);
    }

    public static void t(IBinder iBinder) {
        synchronized (p.class) {
            Iterator<b> it = e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b next = it.next();
                if (next.f252a == iBinder) {
                    e.remove(next);
                    f247a.d("unregisterClient: " + next.f252a + ", " + next.f253b);
                    break;
                }
            }
        }
    }

    public static void u(b.a.c.u.k kVar) {
        f.remove(kVar);
    }
}
