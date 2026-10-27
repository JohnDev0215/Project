package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class QueuedEntityMetadataBase implements Comparable<QueuedEntityMetadataBase> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase");
    private List<Capability> capabilities;
    private ClientMetricsInfo clientMetricsInfo;
    private double durationInSeconds;
    private String entityReference;
    private boolean isNewQueue;
    private Map<String, String> metricsContext;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated QueuedEntityMetadataBase queuedEntityMetadataBase) {
        if (queuedEntityMetadataBase == null) {
            return -1;
        }
        if (queuedEntityMetadataBase == this) {
            return 0;
        }
        String entityReference = getEntityReference();
        String entityReference2 = queuedEntityMetadataBase.getEntityReference();
        if (entityReference != entityReference2) {
            if (entityReference == null) {
                return -1;
            }
            if (entityReference2 == null) {
                return 1;
            }
            int iCompareTo = entityReference.compareTo(entityReference2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, String> metricsContext = getMetricsContext();
        Map<String, String> metricsContext2 = queuedEntityMetadataBase.getMetricsContext();
        if (metricsContext != metricsContext2) {
            if (metricsContext == null) {
                return -1;
            }
            if (metricsContext2 == null) {
                return 1;
            }
            if (metricsContext instanceof Comparable) {
                int iCompareTo2 = ((Comparable) metricsContext).compareTo(metricsContext2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!metricsContext.equals(metricsContext2)) {
                int iHashCode = metricsContext.hashCode();
                int iHashCode2 = metricsContext2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        ClientMetricsInfo clientMetricsInfo = getClientMetricsInfo();
        ClientMetricsInfo clientMetricsInfo2 = queuedEntityMetadataBase.getClientMetricsInfo();
        if (clientMetricsInfo != clientMetricsInfo2) {
            if (clientMetricsInfo == null) {
                return -1;
            }
            if (clientMetricsInfo2 == null) {
                return 1;
            }
            int iCompareTo3 = clientMetricsInfo.compareTo(clientMetricsInfo2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (!isIsNewQueue() && queuedEntityMetadataBase.isIsNewQueue()) {
            return -1;
        }
        if (isIsNewQueue() && !queuedEntityMetadataBase.isIsNewQueue()) {
            return 1;
        }
        List<Capability> capabilities = getCapabilities();
        List<Capability> capabilities2 = queuedEntityMetadataBase.getCapabilities();
        if (capabilities != capabilities2) {
            if (capabilities == null) {
                return -1;
            }
            if (capabilities2 == null) {
                return 1;
            }
            if (capabilities instanceof Comparable) {
                int iCompareTo4 = ((Comparable) capabilities).compareTo(capabilities2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!capabilities.equals(capabilities2)) {
                int iHashCode3 = capabilities.hashCode();
                int iHashCode4 = capabilities2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        if (getDurationInSeconds() < queuedEntityMetadataBase.getDurationInSeconds()) {
            return -1;
        }
        return getDurationInSeconds() > queuedEntityMetadataBase.getDurationInSeconds() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QueuedEntityMetadataBase)) {
            return false;
        }
        QueuedEntityMetadataBase queuedEntityMetadataBase = (QueuedEntityMetadataBase) obj;
        return internalEqualityCheck(getEntityReference(), queuedEntityMetadataBase.getEntityReference()) && internalEqualityCheck(getMetricsContext(), queuedEntityMetadataBase.getMetricsContext()) && internalEqualityCheck(getClientMetricsInfo(), queuedEntityMetadataBase.getClientMetricsInfo()) && internalEqualityCheck(Boolean.valueOf(isIsNewQueue()), Boolean.valueOf(queuedEntityMetadataBase.isIsNewQueue())) && internalEqualityCheck(getCapabilities(), queuedEntityMetadataBase.getCapabilities()) && internalEqualityCheck(Double.valueOf(getDurationInSeconds()), Double.valueOf(queuedEntityMetadataBase.getDurationInSeconds()));
    }

    public List<Capability> getCapabilities() {
        return this.capabilities;
    }

    public ClientMetricsInfo getClientMetricsInfo() {
        return this.clientMetricsInfo;
    }

    public double getDurationInSeconds() {
        return this.durationInSeconds;
    }

    public String getEntityReference() {
        return this.entityReference;
    }

    public Map<String, String> getMetricsContext() {
        return this.metricsContext;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEntityReference(), getMetricsContext(), getClientMetricsInfo(), Boolean.valueOf(isIsNewQueue()), getCapabilities(), Double.valueOf(getDurationInSeconds()));
    }

    public boolean isIsNewQueue() {
        return this.isNewQueue;
    }

    public void setCapabilities(List<Capability> list) {
        this.capabilities = list;
    }

    public void setClientMetricsInfo(ClientMetricsInfo clientMetricsInfo) {
        this.clientMetricsInfo = clientMetricsInfo;
    }

    public void setDurationInSeconds(double d) {
        this.durationInSeconds = d;
    }

    public void setEntityReference(String str) {
        this.entityReference = str;
    }

    public void setIsNewQueue(boolean z) {
        this.isNewQueue = z;
    }

    public void setMetricsContext(Map<String, String> map) {
        this.metricsContext = map;
    }
}
