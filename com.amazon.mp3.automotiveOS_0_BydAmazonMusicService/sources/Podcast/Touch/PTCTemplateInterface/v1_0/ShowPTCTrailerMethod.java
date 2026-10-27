package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ShowPTCTrailerMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethod");
    private AboutElement aboutElement;
    private String podcastId;

    public static class Builder extends Method.Builder {
        protected AboutElement aboutElement;
        protected String podcastId;

        public ShowPTCTrailerMethod build() {
            ShowPTCTrailerMethod showPTCTrailerMethod = new ShowPTCTrailerMethod();
            populate(showPTCTrailerMethod);
            return showPTCTrailerMethod;
        }

        protected void populate(ShowPTCTrailerMethod showPTCTrailerMethod) {
            super.populate((Method) showPTCTrailerMethod);
            showPTCTrailerMethod.setPodcastId(this.podcastId);
            showPTCTrailerMethod.setAboutElement(this.aboutElement);
        }

        public Builder withAboutElement(AboutElement aboutElement) {
            this.aboutElement = aboutElement;
            return this;
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
        if (!(sOAObject instanceof ShowPTCTrailerMethod)) {
            return 1;
        }
        ShowPTCTrailerMethod showPTCTrailerMethod = (ShowPTCTrailerMethod) sOAObject;
        String podcastId = getPodcastId();
        String podcastId2 = showPTCTrailerMethod.getPodcastId();
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
        AboutElement aboutElement = getAboutElement();
        AboutElement aboutElement2 = showPTCTrailerMethod.getAboutElement();
        if (aboutElement != aboutElement2) {
            if (aboutElement == null) {
                return -1;
            }
            if (aboutElement2 == null) {
                return 1;
            }
            int iCompareTo2 = aboutElement.compareTo(aboutElement2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ShowPTCTrailerMethod)) {
            return false;
        }
        ShowPTCTrailerMethod showPTCTrailerMethod = (ShowPTCTrailerMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), showPTCTrailerMethod.getPodcastId()) && internalEqualityCheck(getAboutElement(), showPTCTrailerMethod.getAboutElement());
    }

    public AboutElement getAboutElement() {
        return this.aboutElement;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getAboutElement());
    }

    public void setAboutElement(AboutElement aboutElement) {
        this.aboutElement = aboutElement;
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }
}
