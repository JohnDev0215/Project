package Podcast.Touch.DetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedElement");
    private FeaturedSingleItemElement alternate;
    private PodcastDetailsBookmarkElement podcastBookmark;

    public static class Builder extends TemplateElement.Builder {
        protected FeaturedSingleItemElement alternate;
        protected PodcastDetailsBookmarkElement podcastBookmark;

        public FeaturedElement build() {
            FeaturedElement featuredElement = new FeaturedElement();
            populate(featuredElement);
            return featuredElement;
        }

        protected void populate(FeaturedElement featuredElement) {
            super.populate((TemplateElement) featuredElement);
            featuredElement.setPodcastBookmark(this.podcastBookmark);
            featuredElement.setAlternate(this.alternate);
        }

        public Builder withAlternate(FeaturedSingleItemElement featuredSingleItemElement) {
            this.alternate = featuredSingleItemElement;
            return this;
        }

        public Builder withPodcastBookmark(PodcastDetailsBookmarkElement podcastDetailsBookmarkElement) {
            this.podcastBookmark = podcastDetailsBookmarkElement;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof FeaturedElement)) {
            return 1;
        }
        FeaturedElement featuredElement = (FeaturedElement) sOAObject;
        PodcastDetailsBookmarkElement podcastBookmark = getPodcastBookmark();
        PodcastDetailsBookmarkElement podcastBookmark2 = featuredElement.getPodcastBookmark();
        if (podcastBookmark != podcastBookmark2) {
            if (podcastBookmark == null) {
                return -1;
            }
            if (podcastBookmark2 == null) {
                return 1;
            }
            int iCompareTo = podcastBookmark.compareTo(podcastBookmark2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        FeaturedSingleItemElement alternate = getAlternate();
        FeaturedSingleItemElement alternate2 = featuredElement.getAlternate();
        if (alternate != alternate2) {
            if (alternate == null) {
                return -1;
            }
            if (alternate2 == null) {
                return 1;
            }
            int iCompareTo2 = alternate.compareTo(alternate2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedElement)) {
            return false;
        }
        FeaturedElement featuredElement = (FeaturedElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastBookmark(), featuredElement.getPodcastBookmark()) && internalEqualityCheck(getAlternate(), featuredElement.getAlternate());
    }

    public FeaturedSingleItemElement getAlternate() {
        return this.alternate;
    }

    public PodcastDetailsBookmarkElement getPodcastBookmark() {
        return this.podcastBookmark;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastBookmark(), getAlternate());
    }

    public void setAlternate(FeaturedSingleItemElement featuredSingleItemElement) {
        this.alternate = featuredSingleItemElement;
    }

    public void setPodcastBookmark(PodcastDetailsBookmarkElement podcastDetailsBookmarkElement) {
        this.podcastBookmark = podcastDetailsBookmarkElement;
    }
}
