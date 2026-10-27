package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdatePreferencesRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.UpdatePreferencesRequest");
    private String namespace;
    private Map<String, String> preferences;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof UpdatePreferencesRequest)) {
            return 1;
        }
        UpdatePreferencesRequest updatePreferencesRequest = (UpdatePreferencesRequest) cirrusBaseRequestV2;
        Map<String, String> preferences = getPreferences();
        Map<String, String> preferences2 = updatePreferencesRequest.getPreferences();
        if (preferences != preferences2) {
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
        String namespace = getNamespace();
        String namespace2 = updatePreferencesRequest.getNamespace();
        if (namespace != namespace2) {
            if (namespace == null) {
                return -1;
            }
            if (namespace2 == null) {
                return 1;
            }
            int iCompareTo2 = namespace.compareTo(namespace2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof UpdatePreferencesRequest)) {
            return false;
        }
        UpdatePreferencesRequest updatePreferencesRequest = (UpdatePreferencesRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getPreferences(), updatePreferencesRequest.getPreferences()) && internalEqualityCheck(getNamespace(), updatePreferencesRequest.getNamespace());
    }

    public String getNamespace() {
        return this.namespace;
    }

    public Map<String, String> getPreferences() {
        return this.preferences;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPreferences(), getNamespace());
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }

    public void setPreferences(Map<String, String> map) {
        this.preferences = map;
    }
}
