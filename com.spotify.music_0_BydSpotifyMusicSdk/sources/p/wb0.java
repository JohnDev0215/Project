package p;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.ToNumberPolicy;
import com.google.gson.internal.Excluder;
import com.spotify.eventsender.droppedevents.EventState;
import com.spotify.eventsender.droppedevents.proto.EventCounters;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class wb0 implements ub0, cj0 {
    public final wc1 a;
    public final xy1 b;
    public final com.google.gson.a c;
    public final String d;
    public final Type e;
    public final LinkedHashMap f;
    public final ArrayList g;

    public wb0(wc1 wc1Var, ij0 ij0Var) {
        v03.j(wc1Var, "logger");
        this.a = wc1Var;
        this.b = ij0Var;
        Excluder excluder = Excluder.n;
        LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.c;
        FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.c;
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ToNumberPolicy toNumberPolicy = ToNumberPolicy.c;
        ToNumberPolicy toNumberPolicy2 = ToNumberPolicy.e;
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + arrayList.size() + 3);
        arrayList3.addAll(arrayList);
        Collections.reverse(arrayList3);
        ArrayList arrayList4 = new ArrayList(arrayList2);
        Collections.reverse(arrayList4);
        arrayList3.addAll(arrayList4);
        boolean z = com.google.gson.internal.sql.a.a;
        com.google.gson.a aVar = new com.google.gson.a(excluder, fieldNamingPolicy, map, true, longSerializationPolicy, arrayList3, toNumberPolicy, toNumberPolicy2);
        this.c = aVar;
        this.d = "DroppedEventsPersisterImplLock";
        this.e = new vb0().b;
        this.f = new LinkedHashMap();
        this.g = new ArrayList(new fb(new fk0[]{new tb0(aVar), new ek0(aVar)}, true));
    }

    public static void h(LinkedHashMap linkedHashMap, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            gk0 gk0Var = (gk0) entry.getValue();
            Object gk0Var2 = linkedHashMap.get(str);
            if (gk0Var2 == null) {
                gk0Var2 = new gk0();
                linkedHashMap.put(str, gk0Var2);
            }
            gk0 gk0Var3 = (gk0) gk0Var2;
            v03.j(gk0Var, "other");
            qb0[] qb0VarArr = gk0Var.a;
            int length = qb0VarArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                qb0 qb0Var = qb0VarArr[i];
                qb0[] qb0VarArr2 = gk0Var3.a;
                qb0 qb0Var2 = qb0VarArr2[i2];
                qb0Var2.c(qb0Var);
                qb0VarArr2[i2] = qb0Var2;
                i++;
                i2++;
            }
        }
    }

    @Override // p.cj0
    public final String b() {
        return "ESDrop";
    }

    @Override // p.cj0
    public final void c(FileInputStream fileInputStream) throws IOException {
        Map linkedHashMap;
        byte[] bArrK = he1.K(fileInputStream);
        String str = new String(bArrK, cs.a);
        try {
            if (str.length() <= 4 || !((str.charAt(0) == '1' || str.charAt(0) == '2') && str.charAt(1) == '\n' && str.charAt(2) == '\n' && str.charAt(3) == '{' && str.charAt(str.length() - 1) == '}')) {
                EventCounters eventCountersJ = EventCounters.j(bArrK);
                v03.i(eventCountersJ, "parseFrom(data)");
                linkedHashMap = j(eventCountersJ);
            } else {
                linkedHashMap = i(str);
            }
        } catch (RuntimeException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "";
            }
            ((u11) this.a).v("Cannot parse data: ".concat(message));
            linkedHashMap = new LinkedHashMap();
        }
        synchronized (this.d) {
            h(this.f, linkedHashMap);
        }
    }

    @Override // p.cj0
    public final void e(FileOutputStream fileOutputStream) {
        LinkedHashMap linkedHashMapK = k(this.f);
        if (!linkedHashMapK.isEmpty()) {
            com.spotify.eventsender.droppedevents.proto.a aVarI = EventCounters.i();
            aVarI.e();
            for (Map.Entry entry : linkedHashMapK.entrySet()) {
                String str = (String) entry.getKey();
                gk0 gk0Var = (gk0) entry.getValue();
                com.spotify.eventsender.droppedevents.proto.b bVarH = EventCounters.Counters.h();
                gk0Var.getClass();
                qb0[] qb0VarArr = gk0Var.a;
                ArrayList arrayList = new ArrayList(qb0VarArr.length);
                for (qb0 qb0Var : qb0VarArr) {
                    arrayList.add(Long.valueOf(qb0Var.b()));
                }
                bVarH.d(arrayList);
                aVarI.d(str, (EventCounters.Counters) bVarH.build());
            }
            com.google.protobuf.i iVarBuild = aVarI.build();
            v03.i(iVarBuild, "countersBuilder.build()");
            ((EventCounters) iVarBuild).writeTo(fileOutputStream);
        }
    }

    public final void g(String str, EventState eventState) {
        v03.j(str, "eventName");
        v03.j(eventState, "state");
        if (((zl1) ((zi0) this.b.get())).j) {
            synchronized (this.d) {
                try {
                    LinkedHashMap linkedHashMap = this.f;
                    Object gk0Var = linkedHashMap.get(str);
                    if (gk0Var == null) {
                        gk0Var = new gk0();
                        linkedHashMap.put(str, gk0Var);
                    }
                    qb0 qb0Var = ((gk0) gk0Var).a[eventState.ordinal()];
                    qb0Var.e(qb0Var.b() + 1);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final Map i(String str) {
        List listZ0 = kotlin.text.b.z0(str, new String[]{"\n\n"});
        if (listZ0.size() != 2) {
            return new LinkedHashMap();
        }
        int i = Integer.parseInt((String) listZ0.get(0));
        if (i == 3) {
            String str2 = (String) listZ0.get(1);
            Object objC = this.c.c(this.e, str2);
            v03.i(objC, "{\n                gson.f…reportType)\n            }");
            return (Map) objC;
        }
        int i2 = i - 1;
        if (i2 >= 0) {
            ArrayList arrayList = this.g;
            if (i2 < arrayList.size()) {
                return ((fk0) arrayList.get(i2)).a((String) listZ0.get(1));
            }
        }
        throw new IllegalStateException("No EventStatesCounter migration for version 3".toString());
    }

    public final LinkedHashMap j(EventCounters eventCounters) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int iH = eventCounters.h();
        wc1 wc1Var = this.a;
        if (iH == 3) {
            Map mapG = eventCounters.g();
            v03.i(mapG, "protoCounters.eventNameToCountersMapMap");
            for (Map.Entry entry : mapG.entrySet()) {
                String str = (String) entry.getKey();
                EventCounters.Counters counters = (EventCounters.Counters) entry.getValue();
                u51 u51VarF = counters.f();
                v03.i(u51VarF, "protoEventCounters.countersList");
                gk0 gk0Var = null;
                if (((vd1) u51VarF).k <= EventState.k) {
                    gk0 gk0Var2 = new gk0();
                    int i = 0;
                    for (Object obj : u51VarF) {
                        int i2 = i + 1;
                        if (i < 0) {
                            v03.P();
                            throw null;
                        }
                        long jLongValue = ((Number) obj).longValue();
                        qb0 qb0Var = gk0Var2.a[i];
                        qb0Var.e(jLongValue + qb0Var.b());
                        i = i2;
                    }
                    gk0Var = gk0Var2;
                }
                if (gk0Var == null) {
                    u51 u51VarF2 = counters.f();
                    v03.i(u51VarF2, "protoEventCounters.countersList");
                    ArrayList arrayList = new ArrayList(lu.W(u51VarF2));
                    Iterator<E> it = u51VarF2.iterator();
                    while (it.hasNext()) {
                        arrayList.add(String.valueOf((Long) it.next()));
                    }
                    ((u11) wc1Var).v("Cannot parse counters proto array " + arrayList);
                } else {
                    v03.i(str, "protoEventName");
                    linkedHashMap.put(str, gk0Var);
                }
            }
        } else {
            ((u11) wc1Var).v("Unknown counters proto file version " + eventCounters + ".fileFormatVersion");
        }
        return linkedHashMap;
    }

    public final LinkedHashMap k(LinkedHashMap linkedHashMap) {
        LinkedHashMap linkedHashMap2;
        synchronized (this.d) {
            linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap2;
    }

    public final void l(List list) {
        v03.j(list, "reportedEvents");
        synchronized (this.d) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                rb0 rb0Var = (rb0) it.next();
                gk0 gk0Var = (gk0) this.f.get(rb0Var.a);
                if (gk0Var != null) {
                    EventState eventState = rb0Var.d;
                    long j = rb0Var.b;
                    v03.j(eventState, "state");
                    gk0Var.a[eventState.ordinal()].d(j);
                }
            }
        }
    }
}
