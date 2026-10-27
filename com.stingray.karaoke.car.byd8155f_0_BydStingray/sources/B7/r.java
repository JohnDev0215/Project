package b7;

import B5.RunnableC0080d;
import c7.InterfaceC1035a;
import h5.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import k7.C1707a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A9.b {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final Logger f12313x = Logger.getLogger(r.class.getName());

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final l f12314y;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile boolean f12315n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f12316p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i f12317q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final HashMap f12318r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public n f12319s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ConcurrentLinkedQueue f12320t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ConcurrentLinkedQueue f12321u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ConcurrentLinkedQueue f12322v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ConcurrentLinkedQueue f12323w;

    static {
        l lVar = new l();
        lVar.put("connect", 1);
        lVar.put("connect_error", 1);
        lVar.put("disconnect", 1);
        lVar.put("disconnecting", 1);
        lVar.put("newListener", 1);
        lVar.put("removeListener", 1);
        f12314y = lVar;
    }

    public r(i iVar, String str, C1000a c1000a) {
        super(5);
        this.f12318r = new HashMap();
        this.f12320t = new ConcurrentLinkedQueue();
        this.f12321u = new ConcurrentLinkedQueue();
        this.f12322v = new ConcurrentLinkedQueue();
        this.f12323w = new ConcurrentLinkedQueue();
        this.f12317q = iVar;
        this.f12316p = str;
    }

    public static void a1(r rVar) {
        rVar.getClass();
        f12313x.fine("transport is open - connecting");
        rVar.i1(new j7.d(0));
    }

    public static void b1(r rVar, j7.d dVar) {
        rVar.getClass();
        String str = dVar.c;
        String str2 = rVar.f12316p;
        if (str2.equals(str)) {
            switch (dVar.f17066a) {
                case 0:
                    Object obj = dVar.d;
                    if ((obj instanceof JSONObject) && ((JSONObject) obj).has("sid")) {
                        try {
                            ((JSONObject) dVar.d).getString("sid");
                            rVar.g1();
                        } catch (JSONException unused) {
                            return;
                        }
                    } else {
                        super.N0("connect_error", new I7.a("It seems you are trying to reach a Socket.IO server in v2.x with a v3.x client, which is not possible"));
                    }
                    break;
                case 1:
                    Level level = Level.FINE;
                    Logger logger = f12313x;
                    if (logger.isLoggable(level)) {
                        logger.fine("server disconnect (" + str2 + ")");
                    }
                    rVar.d1();
                    rVar.f1("io server disconnect");
                    break;
                case 2:
                    rVar.h1(dVar);
                    break;
                case 3:
                    rVar.e1(dVar);
                    break;
                case 4:
                    rVar.d1();
                    super.N0("connect_error", dVar.d);
                    break;
                case 5:
                    rVar.h1(dVar);
                    break;
                case 6:
                    rVar.e1(dVar);
                    break;
            }
        }
    }

    public static Object[] j1(JSONArray jSONArray) {
        Object obj;
        int length = jSONArray.length();
        Object[] objArr = new Object[length];
        for (int i7 = 0; i7 < length; i7++) {
            Object obj2 = null;
            try {
                obj = jSONArray.get(i7);
            } catch (JSONException e10) {
                f12313x.log(Level.WARNING, "An error occured while retrieving data from JSONArray", (Throwable) e10);
                obj = null;
            }
            if (!JSONObject.NULL.equals(obj)) {
                obj2 = obj;
            }
            objArr[i7] = obj2;
        }
        return objArr;
    }

    @Override // A9.b
    public final void N0(String str, Object... objArr) {
        if (f12314y.containsKey(str)) {
            throw new RuntimeException(android.hardware.bydauto.ac.tsimpl.a.i("'", str, "' is a reserved event name"));
        }
        C1707a.a(new RunnableC0080d(3, this, objArr, str));
    }

    public final void d1() {
        n nVar = this.f12319s;
        if (nVar != null) {
            Iterator<E> it = nVar.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a();
            }
            this.f12319s = null;
        }
        i iVar = this.f12317q;
        synchronized (iVar.B) {
            try {
                Iterator it2 = iVar.B.values().iterator();
                while (it2.hasNext()) {
                    if (((r) it2.next()).f12319s != null) {
                        i.f12288D.fine("socket is still active, skipping close");
                        return;
                    }
                }
                i.f12288D.fine("disconnect");
                iVar.o = true;
                iVar.f12292p = false;
                if (iVar.f12290C != 3) {
                    iVar.a1();
                }
                iVar.f12295s.d = 0;
                iVar.f12290C = 1;
                h hVar = iVar.f12301y;
                if (hVar != null) {
                    C1707a.a(new d7.d(hVar, 3));
                }
            } finally {
            }
        }
    }

    public final void e1(j7.d dVar) {
        q qVar = (q) this.f12318r.remove(Integer.valueOf(dVar.f17067b));
        Logger logger = f12313x;
        if (qVar != null) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("calling ack %s with %s", Integer.valueOf(dVar.f17067b), dVar.d));
            }
            C1707a.a(new t(15, qVar, j1((JSONArray) dVar.d), false));
            return;
        }
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("bad ack " + dVar.f17067b);
        }
    }

    public final void f1(String str) {
        Logger logger = f12313x;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("close (" + str + ")");
        }
        this.f12315n = false;
        super.N0("disconnect", str);
        HashMap map = this.f12318r;
        for (q qVar : map.values()) {
        }
        map.clear();
    }

    public final void g1() {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        this.f12315n = true;
        while (true) {
            concurrentLinkedQueue = this.f12320t;
            List list = (List) concurrentLinkedQueue.poll();
            if (list == null) {
                break;
            } else {
                super.N0((String) list.get(0), list.toArray());
            }
        }
        concurrentLinkedQueue.clear();
        while (true) {
            ConcurrentLinkedQueue concurrentLinkedQueue2 = this.f12321u;
            j7.d dVar = (j7.d) concurrentLinkedQueue2.poll();
            if (dVar == null) {
                concurrentLinkedQueue2.clear();
                super.N0("connect", new Object[0]);
                return;
            }
            i1(dVar);
        }
    }

    public final void h1(j7.d dVar) {
        ArrayList arrayList = new ArrayList(Arrays.asList(j1((JSONArray) dVar.d)));
        Logger logger = f12313x;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("emitting event %s", arrayList));
        }
        if (dVar.f17067b >= 0) {
            logger.fine("attaching ack callback to event");
            arrayList.add(new q(new boolean[]{false}, dVar.f17067b, this));
        }
        if (!this.f12315n) {
            this.f12320t.add(arrayList);
            return;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        if (!this.f12322v.isEmpty()) {
            Object[] array = arrayList.toArray();
            Iterator it = this.f12322v.iterator();
            while (it.hasNext()) {
                ((InterfaceC1035a) it.next()).q(array);
            }
        }
        super.N0(arrayList.remove(0).toString(), arrayList.toArray());
    }

    public final void i1(j7.d dVar) {
        if (dVar.f17066a == 2) {
            ConcurrentLinkedQueue concurrentLinkedQueue = this.f12323w;
            if (!concurrentLinkedQueue.isEmpty()) {
                Object[] objArrJ1 = j1((JSONArray) dVar.d);
                Iterator it = concurrentLinkedQueue.iterator();
                while (it.hasNext()) {
                    ((InterfaceC1035a) it.next()).q(objArrJ1);
                }
            }
        }
        dVar.c = this.f12316p;
        this.f12317q.b1(dVar);
    }
}
