package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class InQuery extends Query {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.InQuery");
    private String attribute;
    private List<String> values;

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
        if (!(query instanceof InQuery)) {
            return 1;
        }
        InQuery inQuery = (InQuery) query;
        String attribute = getAttribute();
        String attribute2 = inQuery.getAttribute();
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
        List<String> values = getValues();
        List<String> values2 = inQuery.getValues();
        if (values != values2) {
            if (values == null) {
                return -1;
            }
            if (values2 == null) {
                return 1;
            }
            if (values instanceof Comparable) {
                int iCompareTo2 = ((Comparable) values).compareTo(values2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!values.equals(values2)) {
                int iHashCode = values.hashCode();
                int iHashCode2 = values2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(query);
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public boolean equals(Object obj) {
        if (!(obj instanceof InQuery)) {
            return false;
        }
        InQuery inQuery = (InQuery) obj;
        return super.equals(obj) && internalEqualityCheck(getAttribute(), inQuery.getAttribute()) && internalEqualityCheck(getValues(), inQuery.getValues());
    }

    public String getAttribute() {
        return this.attribute;
    }

    public List<String> getValues() {
        return this.values;
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAttribute(), getValues());
    }

    public void setAttribute(String str) {
        this.attribute = str;
    }

    public void setValues(List<String> list) {
        this.values = list;
    }
}
