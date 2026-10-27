package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class MatchQuery extends Query {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.MatchQuery");
    private String attribute;
    private String value;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicsubscriptionofferservice.Query, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Query query) {
        if (query == null) {
            return -1;
        }
        if (query == this) {
            return 0;
        }
        if (!(query instanceof MatchQuery)) {
            return 1;
        }
        MatchQuery matchQuery = (MatchQuery) query;
        String attribute = getAttribute();
        String attribute2 = matchQuery.getAttribute();
        if (attribute != attribute2) {
            if (attribute == null) {
                return -1;
            }
            if (attribute2 == null) {
                return 1;
            }
            int iCompareTo = attribute.compareTo(attribute2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String value = getValue();
        String value2 = matchQuery.getValue();
        if (value != value2) {
            if (value == null) {
                return -1;
            }
            if (value2 == null) {
                return 1;
            }
            int iCompareTo2 = value.compareTo(value2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(query);
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public boolean equals(Object obj) {
        if (!(obj instanceof MatchQuery)) {
            return false;
        }
        MatchQuery matchQuery = (MatchQuery) obj;
        return super.equals(obj) && internalEqualityCheck(getAttribute(), matchQuery.getAttribute()) && internalEqualityCheck(getValue(), matchQuery.getValue());
    }

    public String getAttribute() {
        return this.attribute;
    }

    public String getValue() {
        return this.value;
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAttribute(), getValue());
    }

    public void setAttribute(String str) {
        this.attribute = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
