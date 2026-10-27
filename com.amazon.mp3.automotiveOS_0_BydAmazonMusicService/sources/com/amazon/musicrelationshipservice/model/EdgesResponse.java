package com.amazon.musicrelationshipservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class EdgesResponse implements Comparable<EdgesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicrelationshipservice.model.EdgesResponse");
    private int edgeCount;
    private List<Edge> failures;
    private List<Edge> success;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EdgesResponse edgesResponse) {
        if (edgesResponse == null) {
            return -1;
        }
        if (edgesResponse == this) {
            return 0;
        }
        if (getEdgeCount() < edgesResponse.getEdgeCount()) {
            return -1;
        }
        if (getEdgeCount() > edgesResponse.getEdgeCount()) {
            return 1;
        }
        List<Edge> failures = getFailures();
        List<Edge> failures2 = edgesResponse.getFailures();
        if (failures != failures2) {
            if (failures == null) {
                return -1;
            }
            if (failures2 == null) {
                return 1;
            }
            if (failures instanceof Comparable) {
                int iCompareTo = ((Comparable) failures).compareTo(failures2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!failures.equals(failures2)) {
                int iHashCode = failures.hashCode();
                int iHashCode2 = failures2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Edge> success = getSuccess();
        List<Edge> success2 = edgesResponse.getSuccess();
        if (success != success2) {
            if (success == null) {
                return -1;
            }
            if (success2 == null) {
                return 1;
            }
            if (success instanceof Comparable) {
                int iCompareTo2 = ((Comparable) success).compareTo(success2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!success.equals(success2)) {
                int iHashCode3 = success.hashCode();
                int iHashCode4 = success2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EdgesResponse)) {
            return false;
        }
        EdgesResponse edgesResponse = (EdgesResponse) obj;
        return internalEqualityCheck(Integer.valueOf(getEdgeCount()), Integer.valueOf(edgesResponse.getEdgeCount())) && internalEqualityCheck(getFailures(), edgesResponse.getFailures()) && internalEqualityCheck(getSuccess(), edgesResponse.getSuccess());
    }

    public int getEdgeCount() {
        return this.edgeCount;
    }

    public List<Edge> getFailures() {
        return this.failures;
    }

    public List<Edge> getSuccess() {
        return this.success;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getEdgeCount()), getFailures(), getSuccess());
    }

    public void setEdgeCount(int i) {
        this.edgeCount = i;
    }

    public void setFailures(List<Edge> list) {
        this.failures = list;
    }

    public void setSuccess(List<Edge> list) {
        this.success = list;
    }
}
