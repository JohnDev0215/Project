package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetAvailableContentLanguagesRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetAvailableContentLanguagesRequest");
    private String displayLanguage;

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
        if (!(requestIdentity instanceof GetAvailableContentLanguagesRequest)) {
            return 1;
        }
        String displayLanguage = getDisplayLanguage();
        String displayLanguage2 = ((GetAvailableContentLanguagesRequest) requestIdentity).getDisplayLanguage();
        if (displayLanguage != displayLanguage2) {
            if (displayLanguage == null) {
                return -1;
            }
            if (displayLanguage2 == null) {
                return 1;
            }
            int iCompareTo = displayLanguage.compareTo(displayLanguage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (obj instanceof GetAvailableContentLanguagesRequest) {
            return super.equals(obj) && internalEqualityCheck(getDisplayLanguage(), ((GetAvailableContentLanguagesRequest) obj).getDisplayLanguage());
        }
        return false;
    }

    public String getDisplayLanguage() {
        return this.displayLanguage;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDisplayLanguage());
    }

    public void setDisplayLanguage(String str) {
        this.displayLanguage = str;
    }
}
