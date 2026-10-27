package t3;

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
    public static Context f8049b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h f8050c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static i f8048a = new i("MVC-REMOTE");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static RemoteCallbackList<t3.d> f8051d = new RemoteCallbackList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ArrayList<b> f8052e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static ArrayList<u3.g> f8053f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static ExecutorService f8054g = Executors.newFixedThreadPool(1, new a());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static ArrayList<c> f8055h = new ArrayList<>();

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f8056a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("RemoteNotifyThread-" + this.f8056a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f8057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f8058b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f8059c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f8060d;

        public b(IBinder iBinder, String str, int i8, long j8) {
            this.f8057a = iBinder;
            this.f8058b = str;
            this.f8059c = i8;
            this.f8060d = j8;
        }
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public interface c {
        void a(int i8);
    }

    /* JADX INFO: compiled from: RemoteModelHelper.java */
    public static class d implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public t3.d f8061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f8062b;

        public d(t3.d dVar) {
            this.f8061a = dVar;
            this.f8062b = m.h(dVar.asBinder());
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Iterator it = m.f8055h.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(this.f8062b);
            }
            m.r(this.f8061a);
        }
    }

    public static void c(c cVar) {
        synchronized (f8055h) {
            if (!f8055h.contains(cVar)) {
                f8055h.add(cVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v6 */
    public static Reply d(CallbackMsg callbackMsg) {
        Reply reply;
        if (f8051d == null) {
            Reply reply2 = new Reply();
            reply2.l(-1);
            reply2.k("notifyMessage failed: mCallbacks is null");
            return reply2;
        }
        synchronized (m.class) {
            int iBeginBroadcast = f8051d.beginBroadcast();
            reply = null;
            int i8 = 0;
            CallbackMsg callbackMsg2 = callbackMsg;
            while (i8 < iBeginBroadcast) {
                try {
                    t3.d dVar = (t3.d) f8051d.getBroadcastItem(i8);
                    if (dVar != null && callbackMsg2.m() == h(dVar.asBinder()) && callbackMsg2.g() == g(dVar.asBinder())) {
                        if (callbackMsg2.s() || !callbackMsg2.r().equals(Void.TYPE.getName())) {
                            Reply replyH0 = dVar.h0(callbackMsg2);
                            reply = replyH0;
                            callbackMsg2 = replyH0;
                        } else {
                            dVar.O(callbackMsg2);
                            Reply reply3 = new Reply();
                            reply3.l(0);
                            reply = reply3;
                            callbackMsg2 = callbackMsg2;
                        }
                        f8051d.finishBroadcast();
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.l(-1);
                    reply4.k(Log.getStackTraceString(th));
                    reply = reply4;
                }
                i8++;
                reply = reply;
                callbackMsg2 = callbackMsg2;
            }
            f8051d.finishBroadcast();
        }
        return reply;
    }

    public static Reply e(int i8, Reply reply) {
        Reply reply2;
        if (f8051d == null) {
            Reply reply3 = new Reply();
            reply3.l(-1);
            reply3.k("notifyMessage failed: mCallbacks is null");
            return reply3;
        }
        synchronized (m.class) {
            int iBeginBroadcast = f8051d.beginBroadcast();
            reply2 = null;
            for (int i9 = 0; i9 < iBeginBroadcast; i9++) {
                try {
                    t3.d dVar = (t3.d) f8051d.getBroadcastItem(i9);
                    if (dVar != null && i8 == h(dVar.asBinder())) {
                        dVar.i0(reply);
                        break;
                    }
                } catch (Throwable th) {
                    Reply reply4 = new Reply();
                    reply4.l(-1);
                    reply4.k(Log.getStackTraceString(th));
                    reply2 = reply4;
                }
            }
            f8051d.finishBroadcast();
        }
        return reply2;
    }

    public static void f() {
        if (f8050c == null) {
            h hVar = new h(f8049b);
            f8050c = hVar;
            hVar.f(f8048a.c());
            f8050c.a();
        }
    }

    public static long g(IBinder iBinder) {
        synchronized (m.class) {
            for (b bVar : f8052e) {
                if (iBinder == bVar.f8057a) {
                    return bVar.f8060d;
                }
            }
            return 0L;
        }
    }

    public static int h(IBinder iBinder) {
        synchronized (m.class) {
            for (b bVar : f8052e) {
                if (iBinder == bVar.f8057a) {
                    return bVar.f8059c;
                }
            }
            return 0;
        }
    }

    public static Reply i(GetMsg getMsg) {
        int size = f8053f.size();
        f8048a.d("getInstance: size = " + size);
        for (u3.g gVar : f8053f) {
            if (c7.c.b(getMsg.n(), gVar.x())) {
                return gVar.w(getMsg);
            }
        }
        Reply reply = new Reply();
        reply.l(-1);
        reply.k("RpcResponse list is empty");
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
        for (u3.g gVar : f8053f) {
            if (c7.c.b(invokeMsg.n(), gVar.x())) {
                return gVar.y(invokeMsg);
            }
        }
        Reply reply = new Reply();
        reply.l(-1);
        reply.k("RpcResponse list is empty");
        return reply;
    }

    public static boolean l(IBinder iBinder) {
        synchronized (m.class) {
            Iterator<b> it = f8052e.iterator();
            while (it.hasNext()) {
                if (iBinder == it.next().f8057a) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Object m(j jVar) {
        f();
        return f8050c.d().e(f8050c.d().j(jVar));
    }

    public static boolean n(t3.d dVar, String str, int i8, long j8) {
        Objects.requireNonNull(dVar, "callback is null");
        f8048a.d("registerCallback: " + dVar);
        IBinder iBinderAsBinder = dVar.asBinder();
        if (l(iBinderAsBinder)) {
            return false;
        }
        o(iBinderAsBinder, str, i8, j8);
        try {
            iBinderAsBinder.linkToDeath(new d(dVar), 0);
        } catch (RemoteException e8) {
            f8048a.b(e8);
        }
        return f8051d.register(dVar);
    }

    public static void o(IBinder iBinder, String str, int i8, long j8) {
        synchronized (m.class) {
            f8052e.add(new b(iBinder, str, i8, j8));
            f8048a.d("registerClient: " + iBinder + ", " + str + ", " + i8 + ", " + j8);
        }
    }

    public static void p(u3.g gVar) {
        Iterator<u3.g> it = f8053f.iterator();
        while (it.hasNext()) {
            if (c7.c.b(gVar.x(), it.next().x())) {
                throw new RuntimeException("Cannot register the RpcResponse use exist describe");
            }
        }
        if (f8053f.contains(gVar)) {
            return;
        }
        f8053f.add(gVar);
    }

    public static void q(GCMsg gCMsg) {
        Iterator<u3.g> it = f8053f.iterator();
        while (it.hasNext()) {
            it.next().s(gCMsg);
        }
    }

    public static boolean r(t3.d dVar) {
        Objects.requireNonNull(dVar);
        f8048a.d("unregisterCallback: " + dVar);
        s(dVar.asBinder());
        return f8051d.unregister(dVar);
    }

    public static void s(IBinder iBinder) {
        synchronized (m.class) {
            Iterator<b> it = f8052e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b next = it.next();
                if (next.f8057a == iBinder) {
                    f8052e.remove(next);
                    f8048a.d("unregisterClient: " + next.f8057a + ", " + next.f8058b);
                    break;
                }
            }
        }
    }
}
