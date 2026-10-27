package com.spotify.eventsender.eventsender;

import com.google.protobuf.ByteString;
import com.google.protobuf.i;
import com.spotify.eventsender.droppedevents.EventState;
import com.spotify.messages.EventSenderStats2NonAuth;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Pair;
import p.hz0;
import p.ik0;
import p.ou;
import p.rb0;
import p.ry0;
import p.tj0;
import p.v03;
import p.vj0;

/* JADX INFO: loaded from: classes.dex */
public final class c implements hz0 {
    public static EventSenderStats2NonAuth a(List list, Map map, Map map2) {
        ry0 ry0Var;
        ry0 ry0Var2;
        v03.j(list, "allEvents");
        v03.j(map, "allRateLimitedEvents");
        v03.j(map2, "allEventStates");
        TreeMap treeMap = new TreeMap(map);
        tj0 tj0VarR = EventSenderStats2NonAuth.R();
        TreeSet treeSet = new TreeSet();
        kotlin.collections.b.v0(treeSet, new String[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        EventState eventState = EventState.ENTERED;
        v03.i(tj0VarR, "invoke$lambda$8");
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        TreeSet treeSet2 = treeSet;
        Map mapI0 = kotlin.collections.c.i0(new Pair(eventState, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$1(1, tj0VarR, tj0.class, "addStatesListEnteredCountsTotal", "addStatesListEnteredCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))), new Pair(EventState.REJECTED_CLIENT, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$2(1, tj0VarR, tj0.class, "addStatesListRejectedClientCountsTotal", "addStatesListRejectedClientCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))), new Pair(EventState.DROPPED, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$3(1, tj0VarR, tj0.class, "addStatesListDroppedCountsTotal", "addStatesListDroppedCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))), new Pair(EventState.PERSISTED, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$4(1, tj0VarR, tj0.class, "addStatesListPersistedCountsTotal", "addStatesListPersistedCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))), new Pair(EventState.REJECTED_BACKEND, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$5(1, tj0VarR, tj0.class, "addStatesListRejectedBackendCountsTotal", "addStatesListRejectedBackendCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))), new Pair(EventState.DELIVERED, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$6(1, tj0VarR, tj0.class, "addStatesListDeliveredCountsTotal", "addStatesListDeliveredCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))), new Pair(EventState.DELETED, new vj0(new EventSenderStats2NonAuthDataSource$EventSenderStatsCreator$invoke$1$stateToFunctionsMap$7(1, tj0VarR, tj0.class, "addStatesListDeletedCountsTotal", "addStatesListDeletedCountsTotal(J)Lcom/spotify/messages/EventSenderStats2NonAuth$Builder;", 0))));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ik0 ik0Var = (ik0) it.next();
            TreeSet treeSet3 = treeSet2;
            treeSet3.add(ik0Var.b);
            String str = ik0Var.d;
            LinkedHashMap linkedHashMap3 = linkedHashMap2;
            List list2 = (List) linkedHashMap3.get(str);
            if (list2 != null) {
                list2.add(ik0Var);
            } else {
                linkedHashMap3.put(str, v03.D(ik0Var));
            }
            treeSet2 = treeSet3;
            linkedHashMap2 = linkedHashMap3;
        }
        TreeSet treeSet4 = treeSet2;
        treeSet4.addAll(treeMap.keySet());
        treeSet4.addAll(map2.keySet());
        tj0VarR.d(treeSet4);
        Iterator it2 = linkedHashMap2.entrySet().iterator();
        while (it2.hasNext()) {
            List<ik0> list3 = (List) ((Map.Entry) it2.next()).getValue();
            tj0VarR.f(list3.size());
            byte[] bArr = ((ik0) list3.get(0)).c;
            ByteString byteString = ByteString.c;
            tj0VarR.m(ByteString.c(bArr, 0, bArr.length));
            for (ik0 ik0Var2 : list3) {
                tj0VarR.e(ou.e0(treeSet4, ik0Var2.b));
                long j = ik0Var2.f;
                Long l = ik0Var2.e;
                tj0VarR.g(l != null ? l.longValue() : j);
                tj0VarR.j(ik0Var2.g);
                tj0VarR.h(j);
            }
        }
        for (Map.Entry entry : map2.entrySet()) {
            String str2 = (String) entry.getKey();
            List<rb0> list4 = (List) entry.getValue();
            tj0VarR.s(ou.e0(treeSet4, str2));
            Set setP0 = ou.p0(EventState.e);
            for (rb0 rb0Var : list4) {
                vj0 vj0Var = (vj0) mapI0.get(rb0Var.d);
                if (vj0Var != null && (ry0Var2 = vj0Var.a) != null) {
                }
                setP0.remove(rb0Var.d);
            }
            Iterator it3 = setP0.iterator();
            while (it3.hasNext()) {
                vj0 vj0Var2 = (vj0) mapI0.get((EventState) it3.next());
                if (vj0Var2 != null && (ry0Var = vj0Var2.a) != null) {
                }
            }
        }
        for (Map.Entry entry2 : treeMap.entrySet()) {
            String str3 = (String) entry2.getKey();
            Integer num = (Integer) entry2.getValue();
            tj0VarR.l(ou.e0(treeSet4, str3));
            tj0VarR.k(num.intValue());
        }
        i iVarBuild = tj0VarR.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …  }\n            }.build()");
        return (EventSenderStats2NonAuth) iVarBuild;
    }

    @Override // p.hz0
    public final /* bridge */ /* synthetic */ Object e(Object obj, Object obj2, Object obj3) {
        return a((List) obj, (Map) obj2, (Map) obj3);
    }
}
