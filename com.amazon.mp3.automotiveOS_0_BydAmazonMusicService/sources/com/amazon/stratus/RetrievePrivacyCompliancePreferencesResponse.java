package com.amazon.stratus;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RetrievePrivacyCompliancePreferencesResponse implements Comparable<RetrievePrivacyCompliancePreferencesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponse");
    private Map<String, Map<String, String>> preferencesByNamespace;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferencesResponse) {
        Map<String, Map<String, String>> preferencesByNamespace;
        Map<String, Map<String, String>> preferencesByNamespace2;
        if (retrievePrivacyCompliancePreferencesResponse == null) {
            return -1;
        }
        if (retrievePrivacyCompliancePreferencesResponse != this && (preferencesByNamespace = getPreferencesByNamespace()) != (preferencesByNamespace2 = retrievePrivacyCompliancePreferencesResponse.getPreferencesByNamespace())) {
            if (preferencesByNamespace == null) {
                return -1;
            }
            if (preferencesByNamespace2 == null) {
                return 1;
            }
            if (preferencesByNamespace instanceof Comparable) {
                int iCompareTo = ((Comparable) preferencesByNamespace).compareTo(preferencesByNamespace2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!preferencesByNamespace.equals(preferencesByNamespace2)) {
                int iHashCode = preferencesByNamespace.hashCode();
                int iHashCode2 = preferencesByNamespace2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RetrievePrivacyCompliancePreferencesResponse) {
            return internalEqualityCheck(getPreferencesByNamespace(), ((RetrievePrivacyCompliancePreferencesResponse) obj).getPreferencesByNamespace());
        }
        return false;
    }

    public Map<String, Map<String, String>> getPreferencesByNamespace() {
        return this.preferencesByNamespace;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPreferencesByNamespace());
    }

    public void setPreferencesByNamespace(Map<String, Map<String, String>> map) {
        this.preferencesByNamespace = map;
    }
}
