package com.amazon.musicrelationshipservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Edge implements Comparable<Edge> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicrelationshipservice.model.Edge");
    private String exception;
    private String lastUpdatedTime;
    private ObjectIdentifier source;
    private ObjectIdentifier target;
    private String territory;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Edge edge) {
        if (edge == null) {
            return -1;
        }
        if (edge == this) {
            return 0;
        }
        String territory = getTerritory();
        String territory2 = edge.getTerritory();
        if (territory != territory2) {
            if (territory == null) {
                return -1;
            }
            if (territory2 == null) {
                return 1;
            }
            int iCompareTo = territory.compareTo(territory2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        ObjectIdentifier source = getSource();
        ObjectIdentifier source2 = edge.getSource();
        if (source != source2) {
            if (source == null) {
                return -1;
            }
            if (source2 == null) {
                return 1;
            }
            int iCompareTo2 = source.compareTo(source2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String lastUpdatedTime = getLastUpdatedTime();
        String lastUpdatedTime2 = edge.getLastUpdatedTime();
        if (lastUpdatedTime != lastUpdatedTime2) {
            if (lastUpdatedTime == null) {
                return -1;
            }
            if (lastUpdatedTime2 == null) {
                return 1;
            }
            int iCompareTo3 = lastUpdatedTime.compareTo(lastUpdatedTime2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ObjectIdentifier target = getTarget();
        ObjectIdentifier target2 = edge.getTarget();
        if (target != target2) {
            if (target == null) {
                return -1;
            }
            if (target2 == null) {
                return 1;
            }
            int iCompareTo4 = target.compareTo(target2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String type = getType();
        String type2 = edge.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo5 = type.compareTo(type2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String exception = getException();
        String exception2 = edge.getException();
        if (exception != exception2) {
            if (exception == null) {
                return -1;
            }
            if (exception2 == null) {
                return 1;
            }
            int iCompareTo6 = exception.compareTo(exception2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) obj;
        return internalEqualityCheck(getTerritory(), edge.getTerritory()) && internalEqualityCheck(getSource(), edge.getSource()) && internalEqualityCheck(getLastUpdatedTime(), edge.getLastUpdatedTime()) && internalEqualityCheck(getTarget(), edge.getTarget()) && internalEqualityCheck(getType(), edge.getType()) && internalEqualityCheck(getException(), edge.getException());
    }

    public String getException() {
        return this.exception;
    }

    public String getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public ObjectIdentifier getSource() {
        return this.source;
    }

    public ObjectIdentifier getTarget() {
        return this.target;
    }

    public String getTerritory() {
        return this.territory;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTerritory(), getSource(), getLastUpdatedTime(), getTarget(), getType(), getException());
    }

    public void setException(String str) {
        this.exception = str;
    }

    public void setLastUpdatedTime(String str) {
        this.lastUpdatedTime = str;
    }

    public void setSource(ObjectIdentifier objectIdentifier) {
        this.source = objectIdentifier;
    }

    public void setTarget(ObjectIdentifier objectIdentifier) {
        this.target = objectIdentifier;
    }

    public void setTerritory(String str) {
        this.territory = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
