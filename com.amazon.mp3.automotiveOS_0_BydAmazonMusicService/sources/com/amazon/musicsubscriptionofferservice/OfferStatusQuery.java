package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class OfferStatusQuery extends Query {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.OfferStatusQuery");
    private String offerStatus;

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
        if (!(query instanceof OfferStatusQuery)) {
            return 1;
        }
        String offerStatus = getOfferStatus();
        String offerStatus2 = ((OfferStatusQuery) query).getOfferStatus();
        if (offerStatus != offerStatus2) {
            if (offerStatus == null) {
                return -1;
            }
            if (offerStatus2 == null) {
                return 1;
            }
            int iCompareTo = offerStatus.compareTo(offerStatus2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(query);
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public boolean equals(Object obj) {
        if (obj instanceof OfferStatusQuery) {
            return super.equals(obj) && internalEqualityCheck(getOfferStatus(), ((OfferStatusQuery) obj).getOfferStatus());
        }
        return false;
    }

    public String getOfferStatus() {
        return this.offerStatus;
    }

    @Override // com.amazon.musicsubscriptionofferservice.Query
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOfferStatus());
    }

    public void setOfferStatus(String str) {
        this.offerStatus = str;
    }
}
