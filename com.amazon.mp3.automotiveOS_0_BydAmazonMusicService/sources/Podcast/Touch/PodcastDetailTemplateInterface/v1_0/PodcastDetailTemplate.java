package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PodcastDetailTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplate");
    private AboutElement about;
    private String backgroundImage;
    private FeaturedPinnedElement featuredPinnedElement;
    private FollowElement follow;
    private HeaderElement header;
    private List<EpisodeItemElement> items;
    private List<Method> onEndOfList;
    private RefinementBarElement refinementBarElement;
    private ShareElement share;

    public static class Builder extends Template.Builder {
        protected AboutElement about;
        protected String backgroundImage;
        protected FeaturedPinnedElement featuredPinnedElement;
        protected FollowElement follow;
        protected HeaderElement header;
        protected List<EpisodeItemElement> items;
        protected List<Method> onEndOfList;
        protected RefinementBarElement refinementBarElement;
        protected ShareElement share;

        public PodcastDetailTemplate build() {
            PodcastDetailTemplate podcastDetailTemplate = new PodcastDetailTemplate();
            populate(podcastDetailTemplate);
            return podcastDetailTemplate;
        }

        protected void populate(PodcastDetailTemplate podcastDetailTemplate) {
            super.populate((Template) podcastDetailTemplate);
            podcastDetailTemplate.setHeader(this.header);
            podcastDetailTemplate.setBackgroundImage(this.backgroundImage);
            podcastDetailTemplate.setOnEndOfList(this.onEndOfList);
            podcastDetailTemplate.setAbout(this.about);
            podcastDetailTemplate.setFeaturedPinnedElement(this.featuredPinnedElement);
            podcastDetailTemplate.setFollow(this.follow);
            podcastDetailTemplate.setShare(this.share);
            podcastDetailTemplate.setRefinementBarElement(this.refinementBarElement);
            podcastDetailTemplate.setItems(this.items);
        }

        public Builder withAbout(AboutElement aboutElement) {
            this.about = aboutElement;
            return this;
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withFeaturedPinnedElement(FeaturedPinnedElement featuredPinnedElement) {
            this.featuredPinnedElement = featuredPinnedElement;
            return this;
        }

        public Builder withFollow(FollowElement followElement) {
            this.follow = followElement;
            return this;
        }

        public Builder withHeader(HeaderElement headerElement) {
            this.header = headerElement;
            return this;
        }

        public Builder withItems(List<EpisodeItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
            super.withLatencyMetricElement(latencyMetricElement);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLaunchMode(String str) {
            super.withLaunchMode(str);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnBound(List<Method> list) {
            super.withOnBound(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnBound(List list) {
            return withOnBound((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnCreated(List<Method> list) {
            super.withOnCreated(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnCreated(List list) {
            return withOnCreated((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnDismissed(List<Method> list) {
            super.withOnDismissed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnDismissed(List list) {
            return withOnDismissed((List<Method>) list);
        }

        public Builder withOnEndOfList(List<Method> list) {
            this.onEndOfList = list;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        @Deprecated
        public Builder withPageType(String str) {
            super.withPageType(str);
            return this;
        }

        public Builder withRefinementBarElement(RefinementBarElement refinementBarElement) {
            this.refinementBarElement = refinementBarElement;
            return this;
        }

        public Builder withShare(ShareElement shareElement) {
            this.share = shareElement;
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
    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof PodcastDetailTemplate)) {
            return 1;
        }
        PodcastDetailTemplate podcastDetailTemplate = (PodcastDetailTemplate) sOAObject;
        HeaderElement header = getHeader();
        HeaderElement header2 = podcastDetailTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo = header.compareTo(header2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = podcastDetailTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo2 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = podcastDetailTemplate.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onEndOfList.equals(onEndOfList2)) {
                int iHashCode = onEndOfList.hashCode();
                int iHashCode2 = onEndOfList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        AboutElement about = getAbout();
        AboutElement about2 = podcastDetailTemplate.getAbout();
        if (about != about2) {
            if (about == null) {
                return -1;
            }
            if (about2 == null) {
                return 1;
            }
            int iCompareTo4 = about.compareTo(about2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        FeaturedPinnedElement featuredPinnedElement = getFeaturedPinnedElement();
        FeaturedPinnedElement featuredPinnedElement2 = podcastDetailTemplate.getFeaturedPinnedElement();
        if (featuredPinnedElement != featuredPinnedElement2) {
            if (featuredPinnedElement == null) {
                return -1;
            }
            if (featuredPinnedElement2 == null) {
                return 1;
            }
            int iCompareTo5 = featuredPinnedElement.compareTo(featuredPinnedElement2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        FollowElement follow = getFollow();
        FollowElement follow2 = podcastDetailTemplate.getFollow();
        if (follow != follow2) {
            if (follow == null) {
                return -1;
            }
            if (follow2 == null) {
                return 1;
            }
            int iCompareTo6 = follow.compareTo(follow2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        ShareElement share = getShare();
        ShareElement share2 = podcastDetailTemplate.getShare();
        if (share != share2) {
            if (share == null) {
                return -1;
            }
            if (share2 == null) {
                return 1;
            }
            int iCompareTo7 = share.compareTo(share2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        RefinementBarElement refinementBarElement = getRefinementBarElement();
        RefinementBarElement refinementBarElement2 = podcastDetailTemplate.getRefinementBarElement();
        if (refinementBarElement != refinementBarElement2) {
            if (refinementBarElement == null) {
                return -1;
            }
            if (refinementBarElement2 == null) {
                return 1;
            }
            int iCompareTo8 = refinementBarElement.compareTo(refinementBarElement2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<EpisodeItemElement> items = getItems();
        List<EpisodeItemElement> items2 = podcastDetailTemplate.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo9 = ((Comparable) items).compareTo(items2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!items.equals(items2)) {
                int iHashCode3 = items.hashCode();
                int iHashCode4 = items2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastDetailTemplate)) {
            return false;
        }
        PodcastDetailTemplate podcastDetailTemplate = (PodcastDetailTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), podcastDetailTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), podcastDetailTemplate.getBackgroundImage()) && internalEqualityCheck(getOnEndOfList(), podcastDetailTemplate.getOnEndOfList()) && internalEqualityCheck(getAbout(), podcastDetailTemplate.getAbout()) && internalEqualityCheck(getFeaturedPinnedElement(), podcastDetailTemplate.getFeaturedPinnedElement()) && internalEqualityCheck(getFollow(), podcastDetailTemplate.getFollow()) && internalEqualityCheck(getShare(), podcastDetailTemplate.getShare()) && internalEqualityCheck(getRefinementBarElement(), podcastDetailTemplate.getRefinementBarElement()) && internalEqualityCheck(getItems(), podcastDetailTemplate.getItems());
    }

    public AboutElement getAbout() {
        return this.about;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public FeaturedPinnedElement getFeaturedPinnedElement() {
        return this.featuredPinnedElement;
    }

    public FollowElement getFollow() {
        return this.follow;
    }

    public HeaderElement getHeader() {
        return this.header;
    }

    public List<EpisodeItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    public RefinementBarElement getRefinementBarElement() {
        return this.refinementBarElement;
    }

    public ShareElement getShare() {
        return this.share;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getBackgroundImage(), getOnEndOfList(), getAbout(), getFeaturedPinnedElement(), getFollow(), getShare(), getRefinementBarElement(), getItems());
    }

    public void setAbout(AboutElement aboutElement) {
        this.about = aboutElement;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setFeaturedPinnedElement(FeaturedPinnedElement featuredPinnedElement) {
        this.featuredPinnedElement = featuredPinnedElement;
    }

    public void setFollow(FollowElement followElement) {
        this.follow = followElement;
    }

    public void setHeader(HeaderElement headerElement) {
        this.header = headerElement;
    }

    public void setItems(List<EpisodeItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }

    public void setRefinementBarElement(RefinementBarElement refinementBarElement) {
        this.refinementBarElement = refinementBarElement;
    }

    public void setShare(ShareElement shareElement) {
        this.share = shareElement;
    }
}
