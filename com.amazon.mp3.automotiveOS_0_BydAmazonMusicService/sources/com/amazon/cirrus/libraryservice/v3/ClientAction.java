package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ClientAction implements Comparable<ClientAction> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.ClientAction");
    private String actionName;
    private Map<String, String> additionalDetails;
    private List<Map<String, String>> contentInfo;
    private Map<String, String> metricsContext;
    private Date timestamp;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ClientAction clientAction) {
        if (clientAction == null) {
            return -1;
        }
        if (clientAction == this) {
            return 0;
        }
        Map<String, String> additionalDetails = getAdditionalDetails();
        Map<String, String> additionalDetails2 = clientAction.getAdditionalDetails();
        if (additionalDetails != additionalDetails2) {
            if (additionalDetails == null) {
                return -1;
            }
            if (additionalDetails2 == null) {
                return 1;
            }
            if (additionalDetails instanceof Comparable) {
                int iCompareTo = ((Comparable) additionalDetails).compareTo(additionalDetails2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!additionalDetails.equals(additionalDetails2)) {
                int iHashCode = additionalDetails.hashCode();
                int iHashCode2 = additionalDetails2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String actionName = getActionName();
        String actionName2 = clientAction.getActionName();
        if (actionName != actionName2) {
            if (actionName == null) {
                return -1;
            }
            if (actionName2 == null) {
                return 1;
            }
            int iCompareTo2 = actionName.compareTo(actionName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date timestamp = getTimestamp();
        Date timestamp2 = clientAction.getTimestamp();
        if (timestamp != timestamp2) {
            if (timestamp == null) {
                return -1;
            }
            if (timestamp2 == null) {
                return 1;
            }
            int iCompareTo3 = timestamp.compareTo(timestamp2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, String> metricsContext = getMetricsContext();
        Map<String, String> metricsContext2 = clientAction.getMetricsContext();
        if (metricsContext != metricsContext2) {
            if (metricsContext == null) {
                return -1;
            }
            if (metricsContext2 == null) {
                return 1;
            }
            if (metricsContext instanceof Comparable) {
                int iCompareTo4 = ((Comparable) metricsContext).compareTo(metricsContext2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!metricsContext.equals(metricsContext2)) {
                int iHashCode3 = metricsContext.hashCode();
                int iHashCode4 = metricsContext2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Map<String, String>> contentInfo = getContentInfo();
        List<Map<String, String>> contentInfo2 = clientAction.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            if (contentInfo instanceof Comparable) {
                int iCompareTo5 = ((Comparable) contentInfo).compareTo(contentInfo2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!contentInfo.equals(contentInfo2)) {
                int iHashCode5 = contentInfo.hashCode();
                int iHashCode6 = contentInfo2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClientAction)) {
            return false;
        }
        ClientAction clientAction = (ClientAction) obj;
        return internalEqualityCheck(getAdditionalDetails(), clientAction.getAdditionalDetails()) && internalEqualityCheck(getActionName(), clientAction.getActionName()) && internalEqualityCheck(getTimestamp(), clientAction.getTimestamp()) && internalEqualityCheck(getMetricsContext(), clientAction.getMetricsContext()) && internalEqualityCheck(getContentInfo(), clientAction.getContentInfo());
    }

    public String getActionName() {
        return this.actionName;
    }

    public Map<String, String> getAdditionalDetails() {
        return this.additionalDetails;
    }

    public List<Map<String, String>> getContentInfo() {
        return this.contentInfo;
    }

    public Map<String, String> getMetricsContext() {
        return this.metricsContext;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAdditionalDetails(), getActionName(), getTimestamp(), getMetricsContext(), getContentInfo());
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public void setAdditionalDetails(Map<String, String> map) {
        this.additionalDetails = map;
    }

    public void setContentInfo(List<Map<String, String>> list) {
        this.contentInfo = list;
    }

    public void setMetricsContext(Map<String, String> map) {
        this.metricsContext = map;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }
}
