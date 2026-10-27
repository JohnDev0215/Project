package com.amazon.stratus;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdatePreferencesResponse implements Comparable<UpdatePreferencesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.UpdatePreferencesResponse");
    private Map<String, String> preferences;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated UpdatePreferencesResponse updatePreferencesResponse) {
        Map<String, String> preferences;
        Map<String, String> preferences2;
        if (updatePreferencesResponse == null) {
            return -1;
        }
        if (updatePreferencesResponse != this && (preferences = getPreferences()) != (preferences2 = updatePreferencesResponse.getPreferences())) {
            if (preferences == null) {
                return -1;
            }
            if (preferences2 == null) {
                return 1;
            }
            if (preferences instanceof Comparable) {
                int iCompareTo = ((Comparable) preferences).compareTo(preferences2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!preferences.equals(preferences2)) {
                int iHashCode = preferences.hashCode();
                int iHashCode2 = preferences2.hashCode();
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
        if (obj instanceof UpdatePreferencesResponse) {
            return internalEqualityCheck(getPreferences(), ((UpdatePreferencesResponse) obj).getPreferences());
        }
        return false;
    }

    public Map<String, String> getPreferences() {
        return this.preferences;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPreferences());
    }

    public void setPreferences(Map<String, String> map) {
        this.preferences = map;
    }
}
