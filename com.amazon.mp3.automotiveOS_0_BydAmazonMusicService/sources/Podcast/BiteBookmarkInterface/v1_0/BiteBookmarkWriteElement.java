package Podcast.BiteBookmarkInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BiteBookmarkWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElement");
    private String episodeId;
    private String podcastId;
    private Long progressMilliseconds;
    private Long totalDurationMilliseconds;

    public static class Builder extends WriteElement.Builder {
        protected String episodeId;
        protected String podcastId;
        protected Long progressMilliseconds;
        protected Long totalDurationMilliseconds;

        public BiteBookmarkWriteElement build() {
            BiteBookmarkWriteElement biteBookmarkWriteElement = new BiteBookmarkWriteElement();
            populate(biteBookmarkWriteElement);
            return biteBookmarkWriteElement;
        }

        protected void populate(BiteBookmarkWriteElement biteBookmarkWriteElement) {
            super.populate((WriteElement) biteBookmarkWriteElement);
            biteBookmarkWriteElement.setPodcastId(this.podcastId);
            biteBookmarkWriteElement.setProgressMilliseconds(this.progressMilliseconds);
            biteBookmarkWriteElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
            biteBookmarkWriteElement.setEpisodeId(this.episodeId);
        }

        public Builder withEpisodeId(String str) {
            this.episodeId = str;
            return this;
        }

        @Override // SOAAppSyncInterface.v1_0.WriteElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withPodcastId(String str) {
            this.podcastId = str;
            return this;
        }

        public Builder withProgressMilliseconds(Long l) {
            this.progressMilliseconds = l;
            return this;
        }

        public Builder withTotalDurationMilliseconds(Long l) {
            this.totalDurationMilliseconds = l;
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
    @Override // SOAAppSyncInterface.v1_0.WriteElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        if (writeElement == null) {
            return -1;
        }
        if (writeElement == this) {
            return 0;
        }
        if (!(writeElement instanceof BiteBookmarkWriteElement)) {
            return 1;
        }
        BiteBookmarkWriteElement biteBookmarkWriteElement = (BiteBookmarkWriteElement) writeElement;
        String podcastId = getPodcastId();
        String podcastId2 = biteBookmarkWriteElement.getPodcastId();
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
        Long progressMilliseconds = getProgressMilliseconds();
        Long progressMilliseconds2 = biteBookmarkWriteElement.getProgressMilliseconds();
        if (progressMilliseconds != progressMilliseconds2) {
            if (progressMilliseconds == null) {
                return -1;
            }
            if (progressMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo2 = progressMilliseconds.compareTo(progressMilliseconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long totalDurationMilliseconds = getTotalDurationMilliseconds();
        Long totalDurationMilliseconds2 = biteBookmarkWriteElement.getTotalDurationMilliseconds();
        if (totalDurationMilliseconds != totalDurationMilliseconds2) {
            if (totalDurationMilliseconds == null) {
                return -1;
            }
            if (totalDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo3 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String episodeId = getEpisodeId();
        String episodeId2 = biteBookmarkWriteElement.getEpisodeId();
        if (episodeId != episodeId2) {
            if (episodeId == null) {
                return -1;
            }
            if (episodeId2 == null) {
                return 1;
            }
            int iCompareTo4 = episodeId.compareTo(episodeId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof BiteBookmarkWriteElement)) {
            return false;
        }
        BiteBookmarkWriteElement biteBookmarkWriteElement = (BiteBookmarkWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), biteBookmarkWriteElement.getPodcastId()) && internalEqualityCheck(getProgressMilliseconds(), biteBookmarkWriteElement.getProgressMilliseconds()) && internalEqualityCheck(getTotalDurationMilliseconds(), biteBookmarkWriteElement.getTotalDurationMilliseconds()) && internalEqualityCheck(getEpisodeId(), biteBookmarkWriteElement.getEpisodeId());
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public Long getProgressMilliseconds() {
        return this.progressMilliseconds;
    }

    public Long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getProgressMilliseconds(), getTotalDurationMilliseconds(), getEpisodeId());
    }

    public void setEpisodeId(String str) {
        this.episodeId = str;
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setProgressMilliseconds(Long l) {
        this.progressMilliseconds = l;
    }

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
