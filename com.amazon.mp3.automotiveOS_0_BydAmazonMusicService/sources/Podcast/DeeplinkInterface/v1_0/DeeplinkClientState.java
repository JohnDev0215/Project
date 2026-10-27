package Podcast.DeeplinkInterface.v1_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DeeplinkClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.DeeplinkInterface.v1_0.DeeplinkClientState");
    private boolean backStackRequired;
    private String url;

    public static class Builder extends ClientState.Builder {
        protected boolean backStackRequired;
        protected String url;

        public DeeplinkClientState build() {
            DeeplinkClientState deeplinkClientState = new DeeplinkClientState();
            populate(deeplinkClientState);
            return deeplinkClientState;
        }

        protected void populate(DeeplinkClientState deeplinkClientState) {
            super.populate((ClientState) deeplinkClientState);
            deeplinkClientState.setUrl(this.url);
            deeplinkClientState.setBackStackRequired(this.backStackRequired);
        }

        public Builder withBackStackRequired(boolean z) {
            this.backStackRequired = z;
            return this;
        }

        public Builder withUrl(String str) {
            this.url = str;
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
        if (!(sOAObject instanceof DeeplinkClientState)) {
            return 1;
        }
        DeeplinkClientState deeplinkClientState = (DeeplinkClientState) sOAObject;
        String url = getUrl();
        String url2 = deeplinkClientState.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo = url.compareTo(url2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (!isBackStackRequired() && deeplinkClientState.isBackStackRequired()) {
            return -1;
        }
        if (!isBackStackRequired() || deeplinkClientState.isBackStackRequired()) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DeeplinkClientState)) {
            return false;
        }
        DeeplinkClientState deeplinkClientState = (DeeplinkClientState) obj;
        return super.equals(obj) && internalEqualityCheck(getUrl(), deeplinkClientState.getUrl()) && internalEqualityCheck(Boolean.valueOf(isBackStackRequired()), Boolean.valueOf(deeplinkClientState.isBackStackRequired()));
    }

    public String getUrl() {
        return this.url;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUrl(), Boolean.valueOf(isBackStackRequired()));
    }

    public boolean isBackStackRequired() {
        return this.backStackRequired;
    }

    public void setBackStackRequired(boolean z) {
        this.backStackRequired = z;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
