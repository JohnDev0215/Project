package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PlayMediaMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.PlayMediaMethod");
    private String mediaId;
    private Boolean resumeAtBookmark;
    private Long rewindOffsetMilliseconds;

    public static class Builder extends Method.Builder {
        protected String mediaId;
        protected Boolean resumeAtBookmark;
        protected Long rewindOffsetMilliseconds;

        public PlayMediaMethod build() {
            PlayMediaMethod playMediaMethod = new PlayMediaMethod();
            populate(playMediaMethod);
            return playMediaMethod;
        }

        protected void populate(PlayMediaMethod playMediaMethod) {
            super.populate((Method) playMediaMethod);
            playMediaMethod.setMediaId(this.mediaId);
            playMediaMethod.setResumeAtBookmark(this.resumeAtBookmark);
            playMediaMethod.setRewindOffsetMilliseconds(this.rewindOffsetMilliseconds);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
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

        public Builder withResumeAtBookmark(Boolean bool) {
            this.resumeAtBookmark = bool;
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
        if (!(sOAObject instanceof PlayMediaMethod)) {
            return 1;
        }
        PlayMediaMethod playMediaMethod = (PlayMediaMethod) sOAObject;
        String mediaId = getMediaId();
        String mediaId2 = playMediaMethod.getMediaId();
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
        Boolean boolIsResumeAtBookmark = isResumeAtBookmark();
        Boolean boolIsResumeAtBookmark2 = playMediaMethod.isResumeAtBookmark();
        if (boolIsResumeAtBookmark != boolIsResumeAtBookmark2) {
            if (boolIsResumeAtBookmark == null) {
                return -1;
            }
            if (boolIsResumeAtBookmark2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsResumeAtBookmark.compareTo(boolIsResumeAtBookmark2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long rewindOffsetMilliseconds = getRewindOffsetMilliseconds();
        Long rewindOffsetMilliseconds2 = playMediaMethod.getRewindOffsetMilliseconds();
        if (rewindOffsetMilliseconds != rewindOffsetMilliseconds2) {
            if (rewindOffsetMilliseconds == null) {
                return -1;
            }
            if (rewindOffsetMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo3 = rewindOffsetMilliseconds.compareTo(rewindOffsetMilliseconds2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayMediaMethod)) {
            return false;
        }
        PlayMediaMethod playMediaMethod = (PlayMediaMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getMediaId(), playMediaMethod.getMediaId()) && internalEqualityCheck(isResumeAtBookmark(), playMediaMethod.isResumeAtBookmark()) && internalEqualityCheck(getRewindOffsetMilliseconds(), playMediaMethod.getRewindOffsetMilliseconds());
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public Long getRewindOffsetMilliseconds() {
        return this.rewindOffsetMilliseconds;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMediaId(), isResumeAtBookmark(), getRewindOffsetMilliseconds());
    }

    public Boolean isResumeAtBookmark() {
        return this.resumeAtBookmark;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setResumeAtBookmark(Boolean bool) {
        this.resumeAtBookmark = bool;
    }

    public void setRewindOffsetMilliseconds(Long l) {
        this.rewindOffsetMilliseconds = l;
    }
}
