package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SuggestRequest implements Comparable<SuggestRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SuggestRequest");
    private String customerId;
    private String deviceId;
    private String deviceType;
    private String musicTerritory;
    private String query;
    private String sessionId;
    private Integer size;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SuggestRequest suggestRequest) {
        if (suggestRequest == null) {
            return -1;
        }
        if (suggestRequest == this) {
            return 0;
        }
        String deviceId = getDeviceId();
        String deviceId2 = suggestRequest.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo = deviceId.compareTo(deviceId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = suggestRequest.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo2 = customerId.compareTo(customerId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = suggestRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo3 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = suggestRequest.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo4 = deviceType.compareTo(deviceType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String sessionId = getSessionId();
        String sessionId2 = suggestRequest.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo5 = sessionId.compareTo(sessionId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer size = getSize();
        Integer size2 = suggestRequest.getSize();
        if (size != size2) {
            if (size == null) {
                return -1;
            }
            if (size2 == null) {
                return 1;
            }
            int iCompareTo6 = size.compareTo(size2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String query = getQuery();
        String query2 = suggestRequest.getQuery();
        if (query != query2) {
            if (query == null) {
                return -1;
            }
            if (query2 == null) {
                return 1;
            }
            int iCompareTo7 = query.compareTo(query2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SuggestRequest)) {
            return false;
        }
        SuggestRequest suggestRequest = (SuggestRequest) obj;
        return internalEqualityCheck(getDeviceId(), suggestRequest.getDeviceId()) && internalEqualityCheck(getCustomerId(), suggestRequest.getCustomerId()) && internalEqualityCheck(getMusicTerritory(), suggestRequest.getMusicTerritory()) && internalEqualityCheck(getDeviceType(), suggestRequest.getDeviceType()) && internalEqualityCheck(getSessionId(), suggestRequest.getSessionId()) && internalEqualityCheck(getSize(), suggestRequest.getSize()) && internalEqualityCheck(getQuery(), suggestRequest.getQuery());
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getQuery() {
        return this.query;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public Integer getSize() {
        return this.size;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeviceId(), getCustomerId(), getMusicTerritory(), getDeviceType(), getSessionId(), getSize(), getQuery());
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setSize(Integer num) {
        this.size = num;
    }
}
