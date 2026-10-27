package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TrackEventList implements Comparable<TrackEventList> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.TrackEventList");
    private List<TrackEvent> events;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackEventList trackEventList) {
        List<TrackEvent> events;
        List<TrackEvent> events2;
        if (trackEventList == null) {
            return -1;
        }
        if (trackEventList != this && (events = getEvents()) != (events2 = trackEventList.getEvents())) {
            if (events == null) {
                return -1;
            }
            if (events2 == null) {
                return 1;
            }
            if (events instanceof Comparable) {
                int iCompareTo = ((Comparable) events).compareTo(events2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!events.equals(events2)) {
                int iHashCode = events.hashCode();
                int iHashCode2 = events2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TrackEventList) {
            return internalEqualityCheck(getEvents(), ((TrackEventList) obj).getEvents());
        }
        return false;
    }

    public List<TrackEvent> getEvents() {
        return this.events;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEvents());
    }

    public void setEvents(List<TrackEvent> list) {
        this.events = list;
    }
}
