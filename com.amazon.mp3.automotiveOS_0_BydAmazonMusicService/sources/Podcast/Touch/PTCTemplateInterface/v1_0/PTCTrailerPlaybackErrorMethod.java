package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PTCTrailerPlaybackErrorMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethod");
    private String podcastId;
    private String text;

    public static class Builder extends Method.Builder {
        protected String podcastId;
        protected String text;

        public PTCTrailerPlaybackErrorMethod build() {
            PTCTrailerPlaybackErrorMethod pTCTrailerPlaybackErrorMethod = new PTCTrailerPlaybackErrorMethod();
            populate(pTCTrailerPlaybackErrorMethod);
            return pTCTrailerPlaybackErrorMethod;
        }

        protected void populate(PTCTrailerPlaybackErrorMethod pTCTrailerPlaybackErrorMethod) {
            super.populate((Method) pTCTrailerPlaybackErrorMethod);
            pTCTrailerPlaybackErrorMethod.setPodcastId(this.podcastId);
            pTCTrailerPlaybackErrorMethod.setText(this.text);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withPodcastId(String str) {
            this.podcastId = str;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withText(String str) {
            this.text = str;
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
        if (!(sOAObject instanceof PTCTrailerPlaybackErrorMethod)) {
            return 1;
        }
        PTCTrailerPlaybackErrorMethod pTCTrailerPlaybackErrorMethod = (PTCTrailerPlaybackErrorMethod) sOAObject;
        String podcastId = getPodcastId();
        String podcastId2 = pTCTrailerPlaybackErrorMethod.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo = podcastId.compareTo(podcastId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String text = getText();
        String text2 = pTCTrailerPlaybackErrorMethod.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo2 = text.compareTo(text2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCTrailerPlaybackErrorMethod)) {
            return false;
        }
        PTCTrailerPlaybackErrorMethod pTCTrailerPlaybackErrorMethod = (PTCTrailerPlaybackErrorMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), pTCTrailerPlaybackErrorMethod.getPodcastId()) && internalEqualityCheck(getText(), pTCTrailerPlaybackErrorMethod.getText());
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getText());
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}
