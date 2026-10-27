package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveSubscriptionOffersRequest implements Comparable<RetrieveSubscriptionOffersRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersRequest");
    private String musicTerritory;
    private List<String> scheduleIds;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveSubscriptionOffersRequest retrieveSubscriptionOffersRequest) {
        if (retrieveSubscriptionOffersRequest == null) {
            return -1;
        }
        if (retrieveSubscriptionOffersRequest == this) {
            return 0;
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = retrieveSubscriptionOffersRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> scheduleIds = getScheduleIds();
        List<String> scheduleIds2 = retrieveSubscriptionOffersRequest.getScheduleIds();
        if (scheduleIds != scheduleIds2) {
            if (scheduleIds == null) {
                return -1;
            }
            if (scheduleIds2 == null) {
                return 1;
            }
            if (scheduleIds instanceof Comparable) {
                int iCompareTo2 = ((Comparable) scheduleIds).compareTo(scheduleIds2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!scheduleIds.equals(scheduleIds2)) {
                int iHashCode = scheduleIds.hashCode();
                int iHashCode2 = scheduleIds2.hashCode();
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
        if (!(obj instanceof RetrieveSubscriptionOffersRequest)) {
            return false;
        }
        RetrieveSubscriptionOffersRequest retrieveSubscriptionOffersRequest = (RetrieveSubscriptionOffersRequest) obj;
        return internalEqualityCheck(getMusicTerritory(), retrieveSubscriptionOffersRequest.getMusicTerritory()) && internalEqualityCheck(getScheduleIds(), retrieveSubscriptionOffersRequest.getScheduleIds());
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public List<String> getScheduleIds() {
        return this.scheduleIds;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMusicTerritory(), getScheduleIds());
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setScheduleIds(List<String> list) {
        this.scheduleIds = list;
    }
}
