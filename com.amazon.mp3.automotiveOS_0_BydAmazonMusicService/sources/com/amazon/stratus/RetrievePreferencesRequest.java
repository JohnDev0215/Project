package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrievePreferencesRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrievePreferencesRequest");
    private List<String> keys;
    private String namespace;

    public static class Builder extends CirrusBaseRequestV2.Builder {
        protected List<String> keys;
        protected String namespace;

        public RetrievePreferencesRequest build() {
            RetrievePreferencesRequest retrievePreferencesRequest = new RetrievePreferencesRequest();
            populate(retrievePreferencesRequest);
            return retrievePreferencesRequest;
        }

        protected void populate(RetrievePreferencesRequest retrievePreferencesRequest) {
            super.populate((CirrusBaseRequestV2) retrievePreferencesRequest);
            retrievePreferencesRequest.setNamespace(this.namespace);
            retrievePreferencesRequest.setKeys(this.keys);
        }

        @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2.Builder
        public Builder withCustomerId(String str) {
            super.withCustomerId(str);
            return this;
        }

        @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2.Builder
        public Builder withDeviceId(String str) {
            super.withDeviceId(str);
            return this;
        }

        @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2.Builder
        public Builder withDeviceType(String str) {
            super.withDeviceType(str);
            return this;
        }

        public Builder withKeys(List<String> list) {
            this.keys = list;
            return this;
        }

        public Builder withNamespace(String str) {
            this.namespace = str;
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
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof RetrievePreferencesRequest)) {
            return 1;
        }
        RetrievePreferencesRequest retrievePreferencesRequest = (RetrievePreferencesRequest) cirrusBaseRequestV2;
        String namespace = getNamespace();
        String namespace2 = retrievePreferencesRequest.getNamespace();
        if (namespace != namespace2) {
            if (namespace == null) {
                return -1;
            }
            if (namespace2 == null) {
                return 1;
            }
            int iCompareTo = namespace.compareTo(namespace2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> keys = getKeys();
        List<String> keys2 = retrievePreferencesRequest.getKeys();
        if (keys != keys2) {
            if (keys == null) {
                return -1;
            }
            if (keys2 == null) {
                return 1;
            }
            if (keys instanceof Comparable) {
                int iCompareTo2 = ((Comparable) keys).compareTo(keys2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!keys.equals(keys2)) {
                int iHashCode = keys.hashCode();
                int iHashCode2 = keys2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof RetrievePreferencesRequest)) {
            return false;
        }
        RetrievePreferencesRequest retrievePreferencesRequest = (RetrievePreferencesRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getNamespace(), retrievePreferencesRequest.getNamespace()) && internalEqualityCheck(getKeys(), retrievePreferencesRequest.getKeys());
    }

    public List<String> getKeys() {
        return this.keys;
    }

    public String getNamespace() {
        return this.namespace;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getNamespace(), getKeys());
    }

    public void setKeys(List<String> list) {
        this.keys = list;
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }
}
