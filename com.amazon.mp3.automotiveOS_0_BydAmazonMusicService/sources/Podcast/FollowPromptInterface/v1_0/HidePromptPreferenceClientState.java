package Podcast.FollowPromptInterface.v1_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HidePromptPreferenceClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientState");
    private Map<String, HidePromptPreferenceElement> preferenceMap;

    public static class Builder extends ClientState.Builder {
        protected Map<String, HidePromptPreferenceElement> preferenceMap;

        public HidePromptPreferenceClientState build() {
            HidePromptPreferenceClientState hidePromptPreferenceClientState = new HidePromptPreferenceClientState();
            populate(hidePromptPreferenceClientState);
            return hidePromptPreferenceClientState;
        }

        protected void populate(HidePromptPreferenceClientState hidePromptPreferenceClientState) {
            super.populate((ClientState) hidePromptPreferenceClientState);
            hidePromptPreferenceClientState.setPreferenceMap(this.preferenceMap);
        }

        public Builder withPreferenceMap(Map<String, HidePromptPreferenceElement> map) {
            this.preferenceMap = map;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof HidePromptPreferenceClientState)) {
            return 1;
        }
        Map<String, HidePromptPreferenceElement> preferenceMap = getPreferenceMap();
        Map<String, HidePromptPreferenceElement> preferenceMap2 = ((HidePromptPreferenceClientState) sOAObject).getPreferenceMap();
        if (preferenceMap != preferenceMap2) {
            if (preferenceMap == null) {
                return -1;
            }
            if (preferenceMap2 == null) {
                return 1;
            }
            if (preferenceMap instanceof Comparable) {
                int iCompareTo = ((Comparable) preferenceMap).compareTo(preferenceMap2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!preferenceMap.equals(preferenceMap2)) {
                int iHashCode = preferenceMap.hashCode();
                int iHashCode2 = preferenceMap2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof HidePromptPreferenceClientState) {
            return super.equals(obj) && internalEqualityCheck(getPreferenceMap(), ((HidePromptPreferenceClientState) obj).getPreferenceMap());
        }
        return false;
    }

    public Map<String, HidePromptPreferenceElement> getPreferenceMap() {
        return this.preferenceMap;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPreferenceMap());
    }

    public void setPreferenceMap(Map<String, HidePromptPreferenceElement> map) {
        this.preferenceMap = map;
    }
}
