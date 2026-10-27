package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseHierarchyRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseHierarchyRequest");
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
        if (!(requestIdentity instanceof BrowseHierarchyRequest)) {
            return 1;
        }
        BrowseHierarchyRequest browseHierarchyRequest = (BrowseHierarchyRequest) requestIdentity;
        String lang = getLang();
        String lang2 = browseHierarchyRequest.getLang();
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
        List<String> type = getType();
        List<String> type2 = browseHierarchyRequest.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            if (type instanceof Comparable) {
                int iCompareTo2 = ((Comparable) type).compareTo(type2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = browseHierarchyRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof BrowseHierarchyRequest)) {
            return false;
        }
        BrowseHierarchyRequest browseHierarchyRequest = (BrowseHierarchyRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getLang(), browseHierarchyRequest.getLang()) && internalEqualityCheck(getType(), browseHierarchyRequest.getType()) && internalEqualityCheck(isStub(), browseHierarchyRequest.isStub());
    }

    public String getLang() {
        return this.lang;
    }

    public List<String> getType() {
        return this.type;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLang(), getType(), isStub());
    }

    public Boolean isStub() {
        return this.stub;
    }
}
