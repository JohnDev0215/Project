package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Filter implements Comparable<Filter> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.Filter");
    private String attributeName;
    private String attributeValue;
    private String comparisonType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Filter filter) {
        if (filter == null) {
            return -1;
        }
        if (filter == this) {
            return 0;
        }
        String attributeName = getAttributeName();
        String attributeName2 = filter.getAttributeName();
        if (attributeName != attributeName2) {
            if (attributeName == null) {
                return -1;
            }
            if (attributeName2 == null) {
                return 1;
            }
            int iCompareTo = attributeName.compareTo(attributeName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String comparisonType = getComparisonType();
        String comparisonType2 = filter.getComparisonType();
        if (comparisonType != comparisonType2) {
            if (comparisonType == null) {
                return -1;
            }
            if (comparisonType2 == null) {
                return 1;
            }
            int iCompareTo2 = comparisonType.compareTo(comparisonType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String attributeValue = getAttributeValue();
        String attributeValue2 = filter.getAttributeValue();
        if (attributeValue != attributeValue2) {
            if (attributeValue == null) {
                return -1;
            }
            if (attributeValue2 == null) {
                return 1;
            }
            int iCompareTo3 = attributeValue.compareTo(attributeValue2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Filter)) {
            return false;
        }
        Filter filter = (Filter) obj;
        return internalEqualityCheck(getAttributeName(), filter.getAttributeName()) && internalEqualityCheck(getComparisonType(), filter.getComparisonType()) && internalEqualityCheck(getAttributeValue(), filter.getAttributeValue());
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public String getAttributeValue() {
        return this.attributeValue;
    }

    public String getComparisonType() {
        return this.comparisonType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAttributeName(), getComparisonType(), getAttributeValue());
    }
}
