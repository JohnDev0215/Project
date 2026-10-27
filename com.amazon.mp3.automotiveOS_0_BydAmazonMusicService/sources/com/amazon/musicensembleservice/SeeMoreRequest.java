package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SeeMoreRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.SeeMoreRequest");
    private AllowedParentalControls allowedParentalControls;
    private Integer count;
    private String lang;
    private String next;
    private Boolean stub;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.RequestIdentity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestIdentity requestIdentity) {
        if (requestIdentity == null) {
            return -1;
        }
        if (requestIdentity == this) {
            return 0;
        }
        if (!(requestIdentity instanceof SeeMoreRequest)) {
            return 1;
        }
        SeeMoreRequest seeMoreRequest = (SeeMoreRequest) requestIdentity;
        Integer count = getCount();
        Integer count2 = seeMoreRequest.getCount();
        if (count != count2) {
            if (count == null) {
                return -1;
            }
            if (count2 == null) {
                return 1;
            }
            int iCompareTo = count.compareTo(count2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = seeMoreRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String next = getNext();
        String next2 = seeMoreRequest.getNext();
        if (next != next2) {
            if (next == null) {
                return -1;
            }
            if (next2 == null) {
                return 1;
            }
            int iCompareTo3 = next.compareTo(next2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = seeMoreRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo4 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String lang = getLang();
        String lang2 = seeMoreRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo5 = lang.compareTo(lang2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof SeeMoreRequest)) {
            return false;
        }
        SeeMoreRequest seeMoreRequest = (SeeMoreRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getCount(), seeMoreRequest.getCount()) && internalEqualityCheck(isStub(), seeMoreRequest.isStub()) && internalEqualityCheck(getNext(), seeMoreRequest.getNext()) && internalEqualityCheck(getAllowedParentalControls(), seeMoreRequest.getAllowedParentalControls()) && internalEqualityCheck(getLang(), seeMoreRequest.getLang());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public Integer getCount() {
        return this.count;
    }

    public String getLang() {
        return this.lang;
    }

    public String getNext() {
        return this.next;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCount(), isStub(), getNext(), getAllowedParentalControls(), getLang());
    }

    public Boolean isStub() {
        return this.stub;
    }
}
