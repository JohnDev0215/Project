package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ContextQuery extends Query {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.ContextQuery");
    private String context;

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
        if (!(query instanceof ContextQuery)) {
            return 1;
        }
        String context = getContext();
        String context2 = ((ContextQuery) query).getContext();
        if (context != context2) {
            if (context == null) {
                return -1;
            }
            if (context2 == null) {
                return 1;
            }
            int iCompareTo = context.compareTo(context2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(query);
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public boolean equals(Object obj) {
        if (obj instanceof ContextQuery) {
            return super.equals(obj) && internalEqualityCheck(getContext(), ((ContextQuery) obj).getContext());
        }
        return false;
    }

    public String getContext() {
        return this.context;
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContext());
    }

    public void setContext(String str) {
        this.context = str;
    }
}
