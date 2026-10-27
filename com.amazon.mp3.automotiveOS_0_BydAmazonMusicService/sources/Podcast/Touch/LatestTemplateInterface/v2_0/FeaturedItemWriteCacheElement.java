package Podcast.Touch.LatestTemplateInterface.v2_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACacheInterface.v1_0.WriteCacheElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedItemWriteCacheElement extends WriteCacheElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElement");
    private EpisodeRowItemElement item;
    private String podcastId;
    private String podcastImage;

    public static class Builder extends WriteCacheElement.Builder {
        protected EpisodeRowItemElement item;
        protected String podcastId;
        protected String podcastImage;

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public FeaturedItemWriteCacheElement build() {
            FeaturedItemWriteCacheElement featuredItemWriteCacheElement = new FeaturedItemWriteCacheElement();
            populate(featuredItemWriteCacheElement);
            return featuredItemWriteCacheElement;
        }

        protected void populate(FeaturedItemWriteCacheElement featuredItemWriteCacheElement) {
            super.populate((WriteCacheElement) featuredItemWriteCacheElement);
            featuredItemWriteCacheElement.setPodcastId(this.podcastId);
            featuredItemWriteCacheElement.setItem(this.item);
            featuredItemWriteCacheElement.setPodcastImage(this.podcastImage);
        }

        public Builder withItem(EpisodeRowItemElement episodeRowItemElement) {
            this.item = episodeRowItemElement;
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public Builder withOnInvalidated(List<Method> list) {
            super.withOnInvalidated(list);
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public /* bridge */ /* synthetic */ WriteCacheElement.Builder withOnInvalidated(List list) {
            return withOnInvalidated((List<Method>) list);
        }

        public Builder withPodcastId(String str) {
            this.podcastId = str;
            return this;
        }

        public Builder withPodcastImage(String str) {
            this.podcastImage = str;
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
    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof FeaturedItemWriteCacheElement)) {
            return 1;
        }
        FeaturedItemWriteCacheElement featuredItemWriteCacheElement = (FeaturedItemWriteCacheElement) sOAObject;
        String podcastId = getPodcastId();
        String podcastId2 = featuredItemWriteCacheElement.getPodcastId();
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
        EpisodeRowItemElement item = getItem();
        EpisodeRowItemElement item2 = featuredItemWriteCacheElement.getItem();
        if (item != item2) {
            if (item == null) {
                return -1;
            }
            if (item2 == null) {
                return 1;
            }
            int iCompareTo2 = item.compareTo(item2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String podcastImage = getPodcastImage();
        String podcastImage2 = featuredItemWriteCacheElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastImage.compareTo(podcastImage2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedItemWriteCacheElement)) {
            return false;
        }
        FeaturedItemWriteCacheElement featuredItemWriteCacheElement = (FeaturedItemWriteCacheElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), featuredItemWriteCacheElement.getPodcastId()) && internalEqualityCheck(getItem(), featuredItemWriteCacheElement.getItem()) && internalEqualityCheck(getPodcastImage(), featuredItemWriteCacheElement.getPodcastImage());
    }

    public EpisodeRowItemElement getItem() {
        return this.item;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getPodcastImage() {
        return this.podcastImage;
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getItem(), getPodcastImage());
    }

    public void setItem(EpisodeRowItemElement episodeRowItemElement) {
        this.item = episodeRowItemElement;
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setPodcastImage(String str) {
        this.podcastImage = str;
    }
}
