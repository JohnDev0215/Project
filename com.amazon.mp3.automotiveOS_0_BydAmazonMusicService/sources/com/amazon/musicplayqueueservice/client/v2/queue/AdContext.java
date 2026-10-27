package com.amazon.musicplayqueueservice.client.v2.queue;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AdContext implements Comparable<AdContext> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.AdContext");
    private Map<String, String> additionalMetadata;
    private String advertiserId;
    private String deviceFamily;
    private String ipAddress;
    private String userAgent;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AdContext adContext) {
        if (adContext == null) {
            return -1;
        }
        if (adContext == this) {
            return 0;
        }
        String userAgent = getUserAgent();
        String userAgent2 = adContext.getUserAgent();
        if (userAgent != userAgent2) {
            if (userAgent == null) {
                return -1;
            }
            if (userAgent2 == null) {
                return 1;
            }
            int iCompareTo = userAgent.compareTo(userAgent2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, String> additionalMetadata = getAdditionalMetadata();
        Map<String, String> additionalMetadata2 = adContext.getAdditionalMetadata();
        if (additionalMetadata != additionalMetadata2) {
            if (additionalMetadata == null) {
                return -1;
            }
            if (additionalMetadata2 == null) {
                return 1;
            }
            if (additionalMetadata instanceof Comparable) {
                int iCompareTo2 = ((Comparable) additionalMetadata).compareTo(additionalMetadata2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!additionalMetadata.equals(additionalMetadata2)) {
                int iHashCode = additionalMetadata.hashCode();
                int iHashCode2 = additionalMetadata2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String deviceFamily = getDeviceFamily();
        String deviceFamily2 = adContext.getDeviceFamily();
        if (deviceFamily != deviceFamily2) {
            if (deviceFamily == null) {
                return -1;
            }
            if (deviceFamily2 == null) {
                return 1;
            }
            int iCompareTo3 = deviceFamily.compareTo(deviceFamily2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = adContext.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo4 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String advertiserId = getAdvertiserId();
        String advertiserId2 = adContext.getAdvertiserId();
        if (advertiserId != advertiserId2) {
            if (advertiserId == null) {
                return -1;
            }
            if (advertiserId2 == null) {
                return 1;
            }
            int iCompareTo5 = advertiserId.compareTo(advertiserId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdContext)) {
            return false;
        }
        AdContext adContext = (AdContext) obj;
        return internalEqualityCheck(getUserAgent(), adContext.getUserAgent()) && internalEqualityCheck(getAdditionalMetadata(), adContext.getAdditionalMetadata()) && internalEqualityCheck(getDeviceFamily(), adContext.getDeviceFamily()) && internalEqualityCheck(getIpAddress(), adContext.getIpAddress()) && internalEqualityCheck(getAdvertiserId(), adContext.getAdvertiserId());
    }

    public Map<String, String> getAdditionalMetadata() {
        return this.additionalMetadata;
    }

    public String getAdvertiserId() {
        return this.advertiserId;
    }

    public String getDeviceFamily() {
        return this.deviceFamily;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getUserAgent(), getAdditionalMetadata(), getDeviceFamily(), getIpAddress(), getAdvertiserId());
    }

    public void setAdditionalMetadata(Map<String, String> map) {
        this.additionalMetadata = map;
    }

    public void setAdvertiserId(String str) {
        this.advertiserId = str;
    }

    public void setDeviceFamily(String str) {
        this.deviceFamily = str;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }
}
