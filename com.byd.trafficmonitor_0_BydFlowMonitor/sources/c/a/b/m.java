package c.a.b;

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
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Context f3477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h f3478c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static i f3476a = new i("MVC-REMOTE");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static RemoteCallbackList<c.a.b.d> f3479d = new RemoteCallbackList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ArrayList<b> f3480e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static ArrayList<c.a.b.r.g> f3481f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static ExecutorService f3482g = Executors.newFixedThreadPool(1, new a());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static ArrayList<c> f3483h = new ArrayList<>();

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f3484a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("RemoteNotifyThread-" + this.f3484a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f3485a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3486b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3487c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3488d;

        public b(IBinder iBinder, String str, int i2, long j) {
            this.f3485a = iBinder;
            this.f3486b = str;
            this.f3487c = i2;
            this.f3488d = j;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public interface c {
        void a(int i2);
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class d implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.a.b.d f3489a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3490b;

        public d(c.a.b.d dVar) {
            this.f3489a = dVar;
            this.f3490b = m.h(dVar.asBinder());
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Iterator it = m.f3483h.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(this.f3490b);
            }
            m.r(this.f3489a);
        }
    }

    public static void c(c cVar) {
        synchronized (f3483h) {
            if (!f3483h.contains(cVar)) {
                f3483h.add(cVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v6 */
    public static Reply d(CallbackMsg callbackMsg) {
        Reply reply;
        if (f3479d == null) {
            Reply reply2 = new Reply();
            reply2.p(-1);
            reply2.o("notifyMessage failed: mCallbacks is null");
            return reply2;
        }
        synchronized (m.class) {
            int iBeginBroadcast = f3479d.beginBroadcast();
            reply = null;
            int i2 = 0;
            CallbackMsg callbackMsg2 = callbackMsg;
            while (i2 < iBeginBroadcast) {
                try {
                    c.a.b.d dVar = (c.a.b.d) f3479d.getBroadcastItem(i2);
                    if (dVar != null && callbackMsg2.q() == h(dVar.asBinder()) && callbackMsg2.k() == g(dVar.asBinder())) {
                        if (callbackMsg2.w() || !callbackMsg2.v().equals(Void.TYPE.getName())) {
                            Reply replyW = dVar.w(callbackMsg2);
                            reply = replyW;
                            callbackMsg2 = replyW;
                        } else {
                            dVar.i(callbackMsg2);
                            Reply reply3 = new Reply();
                            reply3.p(0);
                            reply = reply3;
                            callbackMsg2 = callbackMsg2;
                        }
                        f3479d.finishBroadcast();
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.p(-1);
                    reply4.o(Log.getStackTraceString(th));
                    reply = reply4;
                }
                i2++;
                reply = reply;
                callbackMsg2 = callbackMsg2;
            }
            f3479d.finishBroadcast();
        }
        return reply;
    }

    public static Reply e(int i2, Reply reply) {
        Reply reply2;
        if (f3479d == null) {
            Reply reply3 = new Reply();
            reply3.p(-1);
            reply3.o("notifyMessage failed: mCallbacks is null");
            return reply3;
        }
        synchronized (m.class) {
            int iBeginBroadcast = f3479d.beginBroadcast();
            reply2 = null;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    c.a.b.d dVar = (c.a.b.d) f3479d.getBroadcastItem(i3);
                    if (dVar != null && i2 == h(dVar.asBinder())) {
                        dVar.x(reply);
                        break;
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.p(-1);
                    reply4.o(Log.getStackTraceString(th));
                    reply2 = reply4;
                }
            }
            f3479d.finishBroadcast();
        }
        return reply2;
    }

    public static void f() {
        if (f3478c == null) {
            h hVar = new h(f3477b);
            f3478c = hVar;
            hVar.f(f3476a.c());
            f3478c.a();
        }
    }

    public static long g(IBinder iBinder) {
        synchronized (m.class) {
            for (b bVar : f3480e) {
                if (iBinder == bVar.f3485a) {
                    return bVar.f3488d;
                }
            }
            return 0L;
        }
    }

    public static int h(IBinder iBinder) {
        synchronized (m.class) {
            for (b bVar : f3480e) {
                if (iBinder == bVar.f3485a) {
                    return bVar.f3487c;
                }
            }
            return 0;
        }
    }

    public static Reply i(GetMsg getMsg) {
        int size = f3481f.size();
        f3476a.d("getInstance: size = " + size);
        for (c.a.b.r.g gVar : f3481f) {
            if (d.a.a.a.a.c.b(getMsg.r(), gVar.x())) {
                return gVar.w(getMsg);
            }
        }
        Reply reply = new Reply();
        reply.p(-1);
        reply.o("RpcResponse list is empty");
        return reply;
    }

    public static j j(Map<String, String> map) {
        j jVar = new j();
        jVar.a(map.get("actionName"));
        jVar.d("1".equals(map.get("actionSync")));
        jVar.c("1".equals(map.get("actionLog")));
        return jVar;
    }

    public static Reply k(InvokeMsg invokeMsg) {
        for (c.a.b.r.g gVar : f3481f) {
            if (d.a.a.a.a.c.b(invokeMsg.r(), gVar.x())) {
                return gVar.y(invokeMsg);
            }
        }
        Reply reply = new Reply();
        reply.p(-1);
        reply.o("RpcResponse list is empty");
        return reply;
    }

    public static boolean l(IBinder iBinder) {
        synchronized (m.class) {
            Iterator<b> it = f3480e.iterator();
            while (it.hasNext()) {
                if (iBinder == it.next().f3485a) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Object m(j jVar) {
        f();
        return f3478c.d().e(f3478c.d().j(jVar));
    }

    public static boolean n(c.a.b.d dVar, String str, int i2, long j) {
        Objects.requireNonNull(dVar, "callback is null");
        f3476a.d("registerCallback: " + dVar);
        IBinder iBinderAsBinder = dVar.asBinder();
        if (l(iBinderAsBinder)) {
            return false;
        }
        o(iBinderAsBinder, str, i2, j);
        try {
            iBinderAsBinder.linkToDeath(new d(dVar), 0);
        } catch (RemoteException e2) {
            f3476a.b(e2);
        }
        return f3479d.register(dVar);
    }

    public static void o(IBinder iBinder, String str, int i2, long j) {
        synchronized (m.class) {
            f3480e.add(new b(iBinder, str, i2, j));
            f3476a.d("registerClient: " + iBinder + ", " + str + ", " + i2 + ", " + j);
        }
    }

    public static void p(c.a.b.r.g gVar) {
        Iterator<c.a.b.r.g> it = f3481f.iterator();
        while (it.hasNext()) {
            if (d.a.a.a.a.c.b(gVar.x(), it.next().x())) {
                throw new RuntimeException("Cannot register the RpcResponse use exist describe");
            }
        }
        if (f3481f.contains(gVar)) {
            return;
        }
        f3481f.add(gVar);
    }

    public static void q(GCMsg gCMsg) {
        Iterator<c.a.b.r.g> it = f3481f.iterator();
        while (it.hasNext()) {
            it.next().s(gCMsg);
        }
    }

    public static boolean r(c.a.b.d dVar) {
        Objects.requireNonNull(dVar);
        f3476a.d("unregisterCallback: " + dVar);
        s(dVar.asBinder());
        return f3479d.unregister(dVar);
    }

    public static void s(IBinder iBinder) {
        synchronized (m.class) {
            Iterator<b> it = f3480e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b next = it.next();
                if (next.f3485a == iBinder) {
                    f3480e.remove(next);
                    f3476a.d("unregisterClient: " + next.f3485a + ", " + next.f3486b);
                    break;
                }
            }
        }
    }
}
