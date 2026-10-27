package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetEMPPlaybackSessionMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetEMPPlaybackSessionMethod");
    private Boolean isAlexaAppInitiatedPlayback;
    private String playbackSessionId;

    public static class Builder extends Method.Builder {
        protected Boolean isAlexaAppInitiatedPlayback;
        protected String playbackSessionId;

        public SetEMPPlaybackSessionMethod build() {
            SetEMPPlaybackSessionMethod setEMPPlaybackSessionMethod = new SetEMPPlaybackSessionMethod();
            populate(setEMPPlaybackSessionMethod);
            return setEMPPlaybackSessionMethod;
        }

        protected void populate(SetEMPPlaybackSessionMethod setEMPPlaybackSessionMethod) {
            super.populate((Method) setEMPPlaybackSessionMethod);
            setEMPPlaybackSessionMethod.setPlaybackSessionId(this.playbackSessionId);
            setEMPPlaybackSessionMethod.setIsAlexaAppInitiatedPlayback(this.isAlexaAppInitiatedPlayback);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withIsAlexaAppInitiatedPlayback(Boolean bool) {
            this.isAlexaAppInitiatedPlayback = bool;
            return this;
        }

        public Builder withPlaybackSessionId(String str) {
            this.playbackSessionId = str;
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
        if (!(sOAObject instanceof SetEMPPlaybackSessionMethod)) {
            return 1;
        }
        SetEMPPlaybackSessionMethod setEMPPlaybackSessionMethod = (SetEMPPlaybackSessionMethod) sOAObject;
        String playbackSessionId = getPlaybackSessionId();
        String playbackSessionId2 = setEMPPlaybackSessionMethod.getPlaybackSessionId();
        if (playbackSessionId != playbackSessionId2) {
            if (playbackSessionId == null) {
                return -1;
            }
            if (playbackSessionId2 == null) {
                return 1;
            }
            int iCompareTo = playbackSessionId.compareTo(playbackSessionId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsAlexaAppInitiatedPlayback = isIsAlexaAppInitiatedPlayback();
        Boolean boolIsIsAlexaAppInitiatedPlayback2 = setEMPPlaybackSessionMethod.isIsAlexaAppInitiatedPlayback();
        if (boolIsIsAlexaAppInitiatedPlayback != boolIsIsAlexaAppInitiatedPlayback2) {
            if (boolIsIsAlexaAppInitiatedPlayback == null) {
                return -1;
            }
            if (boolIsIsAlexaAppInitiatedPlayback2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsAlexaAppInitiatedPlayback.compareTo(boolIsIsAlexaAppInitiatedPlayback2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SetEMPPlaybackSessionMethod)) {
            return false;
        }
        SetEMPPlaybackSessionMethod setEMPPlaybackSessionMethod = (SetEMPPlaybackSessionMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getPlaybackSessionId(), setEMPPlaybackSessionMethod.getPlaybackSessionId()) && internalEqualityCheck(isIsAlexaAppInitiatedPlayback(), setEMPPlaybackSessionMethod.isIsAlexaAppInitiatedPlayback());
    }

    public String getPlaybackSessionId() {
        return this.playbackSessionId;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPlaybackSessionId(), isIsAlexaAppInitiatedPlayback());
    }

    public Boolean isIsAlexaAppInitiatedPlayback() {
        return this.isAlexaAppInitiatedPlayback;
    }

    public void setIsAlexaAppInitiatedPlayback(Boolean bool) {
        this.isAlexaAppInitiatedPlayback = bool;
    }

    public void setPlaybackSessionId(String str) {
        this.playbackSessionId = str;
    }
}
