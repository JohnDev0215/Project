package com.spotify.eventsender.droppedevents;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import p.he1;
import p.ou;

/* JADX INFO: loaded from: classes.dex */
public enum EventState {
    ENTERED(0),
    REJECTED_CLIENT(1),
    DROPPED(2),
    PERSISTED(3),
    REJECTED_BACKEND(4),
    DELIVERED(5),
    DELETED(6),
    UNKNOWN(-1);

    public static final LinkedHashMap c;
    public static final Set e;
    public static final int k;
    private final int index;

    static {
        EventState[] eventStateArrValues = values();
        int iB = he1.B(eventStateArrValues.length);
        LinkedHashMap linkedHashMap = new LinkedHashMap(iB < 16 ? 16 : iB);
        for (EventState eventState : eventStateArrValues) {
            linkedHashMap.put(Integer.valueOf(eventState.index), eventState);
        }
        c = linkedHashMap;
        EventState[] eventStateArrValues2 = values();
        ArrayList arrayList = new ArrayList();
        for (EventState eventState2 : eventStateArrValues2) {
            if (eventState2 != UNKNOWN) {
                arrayList.add(eventState2);
            }
        }
        Set setQ0 = ou.q0(arrayList);
        e = setQ0;
        k = setQ0.size();
    }

    EventState(int i) {
        this.index = i;
    }
}
