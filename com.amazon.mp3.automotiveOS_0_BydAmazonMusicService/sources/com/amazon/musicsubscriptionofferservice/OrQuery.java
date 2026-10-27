package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class OrQuery extends Query {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.OrQuery");
    private List<Query> queries;

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
        if (!(query instanceof OrQuery)) {
            return 1;
        }
        List<Query> queries = getQueries();
        List<Query> queries2 = ((OrQuery) query).getQueries();
        if (queries != queries2) {
            if (queries == null) {
                return -1;
            }
            if (queries2 == null) {
                return 1;
            }
            if (queries instanceof Comparable) {
                int iCompareTo = ((Comparable) queries).compareTo(queries2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!queries.equals(queries2)) {
                int iHashCode = queries.hashCode();
                int iHashCode2 = queries2.hashCode();
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
        if (obj instanceof OrQuery) {
            return super.equals(obj) && internalEqualityCheck(getQueries(), ((OrQuery) obj).getQueries());
        }
        return false;
    }

    public List<Query> getQueries() {
        return this.queries;
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getQueries());
    }

    public void setQueries(List<Query> list) {
        this.queries = list;
    }
}
