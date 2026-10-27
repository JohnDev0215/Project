package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseHierarchyV2Request extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseHierarchyV2Request");
    private String lang;
    private Boolean stub;
    private List<String> type;

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
        if (!(requestIdentity instanceof BrowseHierarchyV2Request)) {
            return 1;
        }
        BrowseHierarchyV2Request browseHierarchyV2Request = (BrowseHierarchyV2Request) requestIdentity;
        String lang = getLang();
        String lang2 = browseHierarchyV2Request.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo = lang.compareTo(lang2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = browseHierarchyV2Request.isStub();
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
        List<String> type = getType();
        List<String> type2 = browseHierarchyV2Request.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            if (type instanceof Comparable) {
                int iCompareTo3 = ((Comparable) type).compareTo(type2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!type.equals(type2)) {
                int iHashCode = type.hashCode();
                int iHashCode2 = type2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof BrowseHierarchyV2Request)) {
            return false;
        }
        BrowseHierarchyV2Request browseHierarchyV2Request = (BrowseHierarchyV2Request) obj;
        return super.equals(obj) && internalEqualityCheck(getLang(), browseHierarchyV2Request.getLang()) && internalEqualityCheck(isStub(), browseHierarchyV2Request.isStub()) && internalEqualityCheck(getType(), browseHierarchyV2Request.getType());
    }

    public String getLang() {
        return this.lang;
    }

    public List<String> getType() {
        return this.type;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLang(), isStub(), getType());
    }

    public Boolean isStub() {
        return this.stub;
    }
}
