package Podcast.FollowPromptInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class WriteHidePromptPreferencesMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethod");
    private String podcastId;
    private HidePromptPreferenceElement preference;

    public static class Builder extends Method.Builder {
        protected String podcastId;
        protected HidePromptPreferenceElement preference;

        public WriteHidePromptPreferencesMethod build() {
            WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod = new WriteHidePromptPreferencesMethod();
            populate(writeHidePromptPreferencesMethod);
            return writeHidePromptPreferencesMethod;
        }

        protected void populate(WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod) {
            super.populate((Method) writeHidePromptPreferencesMethod);
            writeHidePromptPreferencesMethod.setPodcastId(this.podcastId);
            writeHidePromptPreferencesMethod.setPreference(this.preference);
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

        public Builder withPreference(HidePromptPreferenceElement hidePromptPreferenceElement) {
            this.preference = hidePromptPreferenceElement;
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
        if (!(sOAObject instanceof WriteHidePromptPreferencesMethod)) {
            return 1;
        }
        WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod = (WriteHidePromptPreferencesMethod) sOAObject;
        String podcastId = getPodcastId();
        String podcastId2 = writeHidePromptPreferencesMethod.getPodcastId();
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
        HidePromptPreferenceElement preference = getPreference();
        HidePromptPreferenceElement preference2 = writeHidePromptPreferencesMethod.getPreference();
        if (preference != preference2) {
            if (preference == null) {
                return -1;
            }
            if (preference2 == null) {
                return 1;
            }
            int iCompareTo2 = preference.compareTo(preference2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof WriteHidePromptPreferencesMethod)) {
            return false;
        }
        WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod = (WriteHidePromptPreferencesMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), writeHidePromptPreferencesMethod.getPodcastId()) && internalEqualityCheck(getPreference(), writeHidePromptPreferencesMethod.getPreference());
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public HidePromptPreferenceElement getPreference() {
        return this.preference;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getPreference());
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setPreference(HidePromptPreferenceElement hidePromptPreferenceElement) {
        this.preference = hidePromptPreferenceElement;
    }
}
