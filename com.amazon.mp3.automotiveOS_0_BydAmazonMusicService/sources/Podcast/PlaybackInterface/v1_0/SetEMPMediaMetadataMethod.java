package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetEMPMediaMetadataMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetEMPMediaMetadataMethod");
    private EMPMediaMetadataElement metadata;

    public static class Builder extends Method.Builder {
        protected EMPMediaMetadataElement metadata;

        public SetEMPMediaMetadataMethod build() {
            SetEMPMediaMetadataMethod setEMPMediaMetadataMethod = new SetEMPMediaMetadataMethod();
            populate(setEMPMediaMetadataMethod);
            return setEMPMediaMetadataMethod;
        }

        protected void populate(SetEMPMediaMetadataMethod setEMPMediaMetadataMethod) {
            super.populate((Method) setEMPMediaMetadataMethod);
            setEMPMediaMetadataMethod.setMetadata(this.metadata);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withMetadata(EMPMediaMetadataElement eMPMediaMetadataElement) {
            this.metadata = eMPMediaMetadataElement;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
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
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof SetEMPMediaMetadataMethod)) {
            return 1;
        }
        EMPMediaMetadataElement metadata = getMetadata();
        EMPMediaMetadataElement metadata2 = ((SetEMPMediaMetadataMethod) sOAObject).getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo = metadata.compareTo(metadata2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SetEMPMediaMetadataMethod) {
            return super.equals(obj) && internalEqualityCheck(getMetadata(), ((SetEMPMediaMetadataMethod) obj).getMetadata());
        }
        return false;
    }

    public EMPMediaMetadataElement getMetadata() {
        return this.metadata;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMetadata());
    }

    public void setMetadata(EMPMediaMetadataElement eMPMediaMetadataElement) {
        this.metadata = eMPMediaMetadataElement;
    }
}
