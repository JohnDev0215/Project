package p;

import com.google.protobuf.ByteString;
import com.spotify.eventsender.eventsender.MissingTransportException;
import com.spotify.eventsender.gabo.EventEnvelope;
import com.spotify.eventsender.gabo.PublishEventsRequest;
import com.spotify.eventsender.gabo.PublishEventsResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class uz0 {
    public final s81 a;
    public final vz0 b;

    public uz0(zz0 zz0Var, vz0 vz0Var) {
        this.a = zz0Var;
        this.b = vz0Var;
    }

    public static Set b(v51 v51Var) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : v51Var) {
            if (!((PublishEventsResponse.EventError) obj).g()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(((PublishEventsResponse.EventError) it.next()).e()));
        }
        return ou.q0(arrayList2);
    }

    public static Set c(int i, v51 v51Var) {
        i51 i51VarX = vr2.x(0, i);
        v03.j(i51VarX, "<this>");
        TreeSet treeSet = new TreeSet();
        ou.m0(i51VarX, treeSet);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<E> it = v51Var.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(Integer.valueOf(((PublishEventsResponse.EventError) it.next()).e()));
        }
        Set setP0 = ou.p0(treeSet);
        setP0.removeAll(linkedHashSet);
        return setP0;
    }

    public final PublishEventsRequest a(List list) {
        v03.j(list, "events");
        ArrayList arrayList = new ArrayList(lu.W(list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ai0 ai0Var = (ai0) it.next();
            this.b.getClass();
            v03.j(ai0Var, "event");
            com.spotify.eventsender.gabo.a aVarI = EventEnvelope.i();
            aVarI.e(ai0Var.b);
            Iterable<pr1> iterable = ai0Var.e;
            ArrayList arrayList2 = new ArrayList(lu.W(iterable));
            for (pr1 pr1Var : iterable) {
                com.spotify.eventsender.gabo.b bVarG = EventEnvelope.EventFragment.g();
                bVarG.e((String) pr1Var.a);
                bVarG.d((ByteString) pr1Var.b);
                arrayList2.add((EventEnvelope.EventFragment) bVarG.build());
            }
            aVarI.d(arrayList2);
            aVarI.f(ai0Var.c);
            aVarI.g(ai0Var.d);
            com.google.protobuf.i iVarBuild = aVarI.build();
            v03.i(iVarBuild, "newBuilder()\n        .se…eNumber)\n        .build()");
            arrayList.add((EventEnvelope) iVarBuild);
        }
        zy1 zy1VarG = PublishEventsRequest.g();
        zy1VarG.d(arrayList);
        com.google.protobuf.i iVarBuild2 = zy1VarG.build();
        v03.i(iVarBuild2, "newBuilder().addAllEvent(envelopes).build()");
        return (PublishEventsRequest) iVarBuild2;
    }

    public final v03 d(List list) throws MissingTransportException {
        v03.j(list, "events");
        tz0 tz0Var = (tz0) this.a.getLast();
        if (tz0Var == null) {
            throw new MissingTransportException();
        }
        u42 u42VarB = tz0Var.a(a(list)).b();
        int i = u42VarB.a.l;
        PublishEventsResponse publishEventsResponse = (PublishEventsResponse) u42VarB.b;
        if (i != 200 || publishEventsResponse == null) {
            return new lk0(u4.e("Request failed with error code: ", i));
        }
        int size = list.size();
        v51 v51VarE = publishEventsResponse.e();
        v03.i(v51VarE, "responseBody.errorList");
        Set setC = c(size, v51VarE);
        v51 v51VarE2 = publishEventsResponse.e();
        v03.i(v51VarE2, "responseBody.errorList");
        return new mk0(setC, b(v51VarE2));
    }
}
