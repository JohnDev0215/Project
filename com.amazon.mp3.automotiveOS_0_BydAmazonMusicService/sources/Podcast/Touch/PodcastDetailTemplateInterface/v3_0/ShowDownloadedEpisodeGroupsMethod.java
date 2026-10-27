package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ShowDownloadedEpisodeGroupsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShowDownloadedEpisodeGroupsMethod");

    public static class Builder extends Method.Builder {
        public ShowDownloadedEpisodeGroupsMethod build() {
            ShowDownloadedEpisodeGroupsMethod showDownloadedEpisodeGroupsMethod = new ShowDownloadedEpisodeGroupsMethod();
            populate(showDownloadedEpisodeGroupsMethod);
            return showDownloadedEpisodeGroupsMethod;
        }

        protected void populate(ShowDownloadedEpisodeGroupsMethod showDownloadedEpisodeGroupsMethod) {
            super.populate((Method) showDownloadedEpisodeGroupsMethod);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
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
        if (sOAObject instanceof ShowDownloadedEpisodeGroupsMethod) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof ShowDownloadedEpisodeGroupsMethod) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
