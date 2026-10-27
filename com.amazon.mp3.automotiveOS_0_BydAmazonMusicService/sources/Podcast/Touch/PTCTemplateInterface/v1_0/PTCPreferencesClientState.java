package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PTCPreferencesClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientState");
    private Boolean showPTCIngress;

    public static class Builder extends ClientState.Builder {
        protected Boolean showPTCIngress;

        public PTCPreferencesClientState build() {
            PTCPreferencesClientState pTCPreferencesClientState = new PTCPreferencesClientState();
            populate(pTCPreferencesClientState);
            return pTCPreferencesClientState;
        }

        protected void populate(PTCPreferencesClientState pTCPreferencesClientState) {
            super.populate((ClientState) pTCPreferencesClientState);
            pTCPreferencesClientState.setShowPTCIngress(this.showPTCIngress);
        }

        public Builder withShowPTCIngress(Boolean bool) {
            this.showPTCIngress = bool;
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
        if (!(sOAObject instanceof PTCPreferencesClientState)) {
            return 1;
        }
        Boolean boolIsShowPTCIngress = isShowPTCIngress();
        Boolean boolIsShowPTCIngress2 = ((PTCPreferencesClientState) sOAObject).isShowPTCIngress();
        if (boolIsShowPTCIngress != boolIsShowPTCIngress2) {
            if (boolIsShowPTCIngress == null) {
                return -1;
            }
            if (boolIsShowPTCIngress2 == null) {
                return 1;
            }
            int iCompareTo = boolIsShowPTCIngress.compareTo(boolIsShowPTCIngress2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof PTCPreferencesClientState) {
            return super.equals(obj) && internalEqualityCheck(isShowPTCIngress(), ((PTCPreferencesClientState) obj).isShowPTCIngress());
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isShowPTCIngress());
    }

    public Boolean isShowPTCIngress() {
        return this.showPTCIngress;
    }

    public void setShowPTCIngress(Boolean bool) {
        this.showPTCIngress = bool;
    }
}
