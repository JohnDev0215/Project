package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetMediaOffsetMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethod");
    private Boolean ignorable;
    private String mediaId;
    private Long rewindOffsetMilliseconds;

    public static class Builder extends Method.Builder {
        protected Boolean ignorable;
        protected String mediaId;
        protected Long rewindOffsetMilliseconds;

        public SetMediaOffsetMethod build() {
            SetMediaOffsetMethod setMediaOffsetMethod = new SetMediaOffsetMethod();
            populate(setMediaOffsetMethod);
            return setMediaOffsetMethod;
        }

        protected void populate(SetMediaOffsetMethod setMediaOffsetMethod) {
            super.populate((Method) setMediaOffsetMethod);
            setMediaOffsetMethod.setMediaId(this.mediaId);
            setMediaOffsetMethod.setRewindOffsetMilliseconds(this.rewindOffsetMilliseconds);
            setMediaOffsetMethod.setIgnorable(this.ignorable);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withIgnorable(Boolean bool) {
            this.ignorable = bool;
            return this;
        }

        public Builder withMediaId(String str) {
            this.mediaId = str;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withRewindOffsetMilliseconds(Long l) {
            this.rewindOffsetMilliseconds = l;
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
        if (!(sOAObject instanceof SetMediaOffsetMethod)) {
            return 1;
        }
        SetMediaOffsetMethod setMediaOffsetMethod = (SetMediaOffsetMethod) sOAObject;
        String mediaId = getMediaId();
        String mediaId2 = setMediaOffsetMethod.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo = mediaId.compareTo(mediaId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long rewindOffsetMilliseconds = getRewindOffsetMilliseconds();
        Long rewindOffsetMilliseconds2 = setMediaOffsetMethod.getRewindOffsetMilliseconds();
        if (rewindOffsetMilliseconds != rewindOffsetMilliseconds2) {
            if (rewindOffsetMilliseconds == null) {
                return -1;
            }
            if (rewindOffsetMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo2 = rewindOffsetMilliseconds.compareTo(rewindOffsetMilliseconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsIgnorable = isIgnorable();
        Boolean boolIsIgnorable2 = setMediaOffsetMethod.isIgnorable();
        if (boolIsIgnorable != boolIsIgnorable2) {
            if (boolIsIgnorable == null) {
                return -1;
            }
            if (boolIsIgnorable2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIgnorable.compareTo(boolIsIgnorable2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SetMediaOffsetMethod)) {
            return false;
        }
        SetMediaOffsetMethod setMediaOffsetMethod = (SetMediaOffsetMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getMediaId(), setMediaOffsetMethod.getMediaId()) && internalEqualityCheck(getRewindOffsetMilliseconds(), setMediaOffsetMethod.getRewindOffsetMilliseconds()) && internalEqualityCheck(isIgnorable(), setMediaOffsetMethod.isIgnorable());
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public Long getRewindOffsetMilliseconds() {
        return this.rewindOffsetMilliseconds;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMediaId(), getRewindOffsetMilliseconds(), isIgnorable());
    }

    public Boolean isIgnorable() {
        return this.ignorable;
    }

    public void setIgnorable(Boolean bool) {
        this.ignorable = bool;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setRewindOffsetMilliseconds(Long l) {
        this.rewindOffsetMilliseconds = l;
    }
}
