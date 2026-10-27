package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class NotQuery extends Query {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.NotQuery");
    private Query query;

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
        if (!(query instanceof NotQuery)) {
            return 1;
        }
        Query query2 = getQuery();
        Query query3 = ((NotQuery) query).getQuery();
        if (query2 != query3) {
            if (query2 == null) {
                return -1;
            }
            if (query3 == null) {
                return 1;
            }
            int iCompareTo = query2.compareTo(query3);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(query);
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public boolean equals(Object obj) {
        if (obj instanceof NotQuery) {
            return super.equals(obj) && internalEqualityCheck(getQuery(), ((NotQuery) obj).getQuery());
        }
        return false;
    }

    public Query getQuery() {
        return this.query;
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getQuery());
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
