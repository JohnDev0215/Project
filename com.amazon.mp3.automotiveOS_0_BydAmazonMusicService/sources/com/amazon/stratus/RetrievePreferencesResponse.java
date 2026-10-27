package com.amazon.stratus;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RetrievePreferencesResponse implements Comparable<RetrievePreferencesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrievePreferencesResponse");
    private Map<String, String> preferences;
    private Map<String, Map<String, String>> preferencesByNamespace;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrievePreferencesResponse retrievePreferencesResponse) {
        if (retrievePreferencesResponse == null) {
            return -1;
        }
        if (retrievePreferencesResponse == this) {
            return 0;
        }
        Map<String, Map<String, String>> preferencesByNamespace = getPreferencesByNamespace();
        Map<String, Map<String, String>> preferencesByNamespace2 = retrievePreferencesResponse.getPreferencesByNamespace();
        if (preferencesByNamespace != preferencesByNamespace2) {
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
        Map<String, String> preferences = getPreferences();
        Map<String, String> preferences2 = retrievePreferencesResponse.getPreferences();
        if (preferences != preferences2) {
            if (preferences == null) {
                return -1;
            }
            if (preferences2 == null) {
                return 1;
            }
            if (preferences instanceof Comparable) {
                int iCompareTo2 = ((Comparable) preferences).compareTo(preferences2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!preferences.equals(preferences2)) {
                int iHashCode3 = preferences.hashCode();
                int iHashCode4 = preferences2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RetrievePreferencesResponse)) {
            return false;
        }
        RetrievePreferencesResponse retrievePreferencesResponse = (RetrievePreferencesResponse) obj;
        return internalEqualityCheck(getPreferencesByNamespace(), retrievePreferencesResponse.getPreferencesByNamespace()) && internalEqualityCheck(getPreferences(), retrievePreferencesResponse.getPreferences());
    }

    public Map<String, String> getPreferences() {
        return this.preferences;
    }

    public Map<String, Map<String, String>> getPreferencesByNamespace() {
        return this.preferencesByNamespace;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPreferencesByNamespace(), getPreferences());
    }

    public void setPreferences(Map<String, String> map) {
        this.preferences = map;
    }

    public void setPreferencesByNamespace(Map<String, Map<String, String>> map) {
        this.preferencesByNamespace = map;
    }
}
