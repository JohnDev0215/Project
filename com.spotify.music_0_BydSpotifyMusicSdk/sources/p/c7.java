package p;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class c7 implements wk2 {
    public final String a;
    public final zk1 b;
    public final zb0 c;
    public final UUID d;
    public final ConcurrentHashMap e;
    public final ConcurrentHashMap f;
    public final ConcurrentHashMap g;
    public volatile String h;
    public final AtomicBoolean i;
    public Long j;
    public final ConcurrentHashMap k;
    public final Set l;
    public final Set m;

    public c7(String str, zk1 zk1Var) {
        zb0 zb0Var = new zb0();
        v03.j(str, "category");
        v03.j(zk1Var, "timestampProvider");
        this.a = str;
        this.b = zk1Var;
        this.c = zb0Var;
        UUID uuidRandomUUID = UUID.randomUUID();
        v03.i(uuidRandomUUID, "randomUUID()");
        this.d = uuidRandomUUID;
        this.e = new ConcurrentHashMap();
        this.f = new ConcurrentHashMap();
        this.g = new ConcurrentHashMap();
        this.i = new AtomicBoolean(true);
        this.k = new ConcurrentHashMap();
        Set setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        v03.i(setNewSetFromMap, "newSetFromMap(ConcurrentHashMap())");
        this.l = setNewSetFromMap;
        Set setNewSetFromMap2 = Collections.newSetFromMap(new ConcurrentHashMap());
        v03.i(setNewSetFromMap2, "newSetFromMap(ConcurrentHashMap())");
        this.m = setNewSetFromMap2;
        if (!(!ng2.c0(str))) {
            throw new IllegalArgumentException("Category must not be empty".toString());
        }
    }

    public static Set f(Collection collection, long j) {
        ArrayList arrayList = new ArrayList(lu.W(collection));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            n01 n01Var = (n01) it.next();
            Long l = (Long) n01Var.c;
            arrayList.add(new n01(n01Var.a, n01Var.b, l != null ? Long.valueOf(l.longValue() - j) : null, n01Var.d));
        }
        return ou.q0(arrayList);
    }

    public final c7 a(String str, String str2) {
        v03.j(str2, "value");
        this.f.put(str, str2);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0273  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.vk2 b() {
        /*
            Method dump skipped, instruction units count: 706
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.c7.b():p.vk2");
    }

    public final void c(String str) {
        v03.j(str, "identifier");
        zk1 zk1Var = this.b;
        zk1Var.getClass();
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        zk1Var.a.getClass();
        long micros = timeUnit.toMicros(SystemClock.elapsedRealtimeNanos());
        nq1 nq1Var = (nq1) this.k.get(str);
        if (nq1Var == null) {
            this.m.add(str);
            return;
        }
        String str2 = this.h;
        n01 n01Var = nq1Var.a;
        String str3 = n01Var.a;
        if (str2 == null) {
            str2 = n01Var.b;
        }
        Object obj = n01Var.c;
        Long l = (Long) obj;
        n01 n01Var2 = new n01(str3, str2, obj, l != null ? Long.valueOf(micros - l.longValue()) : null);
        this.g.put(n01Var2.a, n01Var2);
        this.k.remove(nq1Var.a.a);
    }

    public final Set d() {
        Collection collectionValues = this.k.values();
        v03.i(collectionValues, "ongoingPoints.values");
        ArrayList arrayList = new ArrayList(lu.W(collectionValues));
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            arrayList.add(((nq1) it.next()).a);
        }
        Long l = this.j;
        return f(arrayList, l != null ? l.longValue() : 0L);
    }

    public final Set e() {
        Collection collectionValues = this.k.values();
        v03.i(collectionValues, "ongoingPoints.values");
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (((nq1) obj).b) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((nq1) it.next()).a.a);
        }
        return ou.q0(arrayList2);
    }

    public final void g(String str) {
        v03.j(str, "identifier");
        zk1 zk1Var = this.b;
        zk1Var.getClass();
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        zk1Var.a.getClass();
        long micros = timeUnit.toMicros(SystemClock.elapsedRealtimeNanos());
        if (!this.g.containsKey(str)) {
            ConcurrentHashMap concurrentHashMap = this.k;
            if (!concurrentHashMap.containsKey(str)) {
                if (this.j == null) {
                    this.j = Long.valueOf(micros);
                }
                concurrentHashMap.put(str, new nq1(new n01(str, null, Long.valueOf(micros), null), true));
                return;
            }
        }
        this.l.add(str);
    }
}
