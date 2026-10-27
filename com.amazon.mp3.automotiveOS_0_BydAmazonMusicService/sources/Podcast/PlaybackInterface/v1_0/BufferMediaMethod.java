package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BufferMediaMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.BufferMediaMethod");
    private String audioStreamFormat;
    private String audioUri;
    private Boolean ignorable;
    private String mediaId;

    public static class Builder extends Method.Builder {
        protected String audioStreamFormat;
        protected String audioUri;
        protected Boolean ignorable;
        protected String mediaId;

        public BufferMediaMethod build() {
            BufferMediaMethod bufferMediaMethod = new BufferMediaMethod();
            populate(bufferMediaMethod);
            return bufferMediaMethod;
        }

        protected void populate(BufferMediaMethod bufferMediaMethod) {
            super.populate((Method) bufferMediaMethod);
            bufferMediaMethod.setAudioUri(this.audioUri);
            bufferMediaMethod.setIgnorable(this.ignorable);
            bufferMediaMethod.setAudioStreamFormat(this.audioStreamFormat);
            bufferMediaMethod.setMediaId(this.mediaId);
        }

        public Builder withAudioStreamFormat(String str) {
            this.audioStreamFormat = str;
            return this;
        }

        public Builder withAudioUri(String str) {
            this.audioUri = str;
            return this;
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
        if (!(sOAObject instanceof BufferMediaMethod)) {
            return 1;
        }
        BufferMediaMethod bufferMediaMethod = (BufferMediaMethod) sOAObject;
        String audioUri = getAudioUri();
        String audioUri2 = bufferMediaMethod.getAudioUri();
        if (audioUri != audioUri2) {
            if (audioUri == null) {
                return -1;
            }
            if (audioUri2 == null) {
                return 1;
            }
            int iCompareTo = audioUri.compareTo(audioUri2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIgnorable = isIgnorable();
        Boolean boolIsIgnorable2 = bufferMediaMethod.isIgnorable();
        if (boolIsIgnorable != boolIsIgnorable2) {
            if (boolIsIgnorable == null) {
                return -1;
            }
            if (boolIsIgnorable2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIgnorable.compareTo(boolIsIgnorable2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String audioStreamFormat = getAudioStreamFormat();
        String audioStreamFormat2 = bufferMediaMethod.getAudioStreamFormat();
        if (audioStreamFormat != audioStreamFormat2) {
            if (audioStreamFormat == null) {
                return -1;
            }
            if (audioStreamFormat2 == null) {
                return 1;
            }
            int iCompareTo3 = audioStreamFormat.compareTo(audioStreamFormat2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String mediaId = getMediaId();
        String mediaId2 = bufferMediaMethod.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo4 = mediaId.compareTo(mediaId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof BufferMediaMethod)) {
            return false;
        }
        BufferMediaMethod bufferMediaMethod = (BufferMediaMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getAudioUri(), bufferMediaMethod.getAudioUri()) && internalEqualityCheck(isIgnorable(), bufferMediaMethod.isIgnorable()) && internalEqualityCheck(getAudioStreamFormat(), bufferMediaMethod.getAudioStreamFormat()) && internalEqualityCheck(getMediaId(), bufferMediaMethod.getMediaId());
    }

    public String getAudioStreamFormat() {
        return this.audioStreamFormat;
    }

    public String getAudioUri() {
        return this.audioUri;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAudioUri(), isIgnorable(), getAudioStreamFormat(), getMediaId());
    }

    public Boolean isIgnorable() {
        return this.ignorable;
    }

    public void setAudioStreamFormat(String str) {
        this.audioStreamFormat = str;
    }

    public void setAudioUri(String str) {
        this.audioUri = str;
    }

    public void setIgnorable(Boolean bool) {
        this.ignorable = bool;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }
}
