package Podcast.Touch.DetailTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DetailTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplate");
    private AboutElement about;
    private String backgroundImage;
    private FeaturedElement featuredItem;
    private FeaturedPinnedElement featuredPinnedElement;
    private FollowElement follow;
    private HeaderElement header;
    private DetailTemplateShardsElement shards;
    private ShareElement share;

    public static class Builder extends Template.Builder {
        protected AboutElement about;
        protected String backgroundImage;
        protected FeaturedElement featuredItem;
        protected FeaturedPinnedElement featuredPinnedElement;
        protected FollowElement follow;
        protected HeaderElement header;
        protected DetailTemplateShardsElement shards;
        protected ShareElement share;

        public DetailTemplate build() {
            DetailTemplate detailTemplate = new DetailTemplate();
            populate(detailTemplate);
            return detailTemplate;
        }

        protected void populate(DetailTemplate detailTemplate) {
            super.populate((Template) detailTemplate);
            detailTemplate.setFeaturedPinnedElement(this.featuredPinnedElement);
            detailTemplate.setShards(this.shards);
            detailTemplate.setAbout(this.about);
            detailTemplate.setShare(this.share);
            detailTemplate.setFeaturedItem(this.featuredItem);
            detailTemplate.setBackgroundImage(this.backgroundImage);
            detailTemplate.setFollow(this.follow);
            detailTemplate.setHeader(this.header);
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

        public Builder withFeaturedItem(FeaturedElement featuredElement) {
            this.featuredItem = featuredElement;
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

        public Builder withShards(DetailTemplateShardsElement detailTemplateShardsElement) {
            this.shards = detailTemplateShardsElement;
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
        if (!(sOAObject instanceof DetailTemplate)) {
            return 1;
        }
        DetailTemplate detailTemplate = (DetailTemplate) sOAObject;
        FeaturedPinnedElement featuredPinnedElement = getFeaturedPinnedElement();
        FeaturedPinnedElement featuredPinnedElement2 = detailTemplate.getFeaturedPinnedElement();
        if (featuredPinnedElement != featuredPinnedElement2) {
            if (featuredPinnedElement == null) {
                return -1;
            }
            if (featuredPinnedElement2 == null) {
                return 1;
            }
            int iCompareTo = featuredPinnedElement.compareTo(featuredPinnedElement2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        DetailTemplateShardsElement shards = getShards();
        DetailTemplateShardsElement shards2 = detailTemplate.getShards();
        if (shards != shards2) {
            if (shards == null) {
                return -1;
            }
            if (shards2 == null) {
                return 1;
            }
            int iCompareTo2 = shards.compareTo(shards2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        AboutElement about = getAbout();
        AboutElement about2 = detailTemplate.getAbout();
        if (about != about2) {
            if (about == null) {
                return -1;
            }
            if (about2 == null) {
                return 1;
            }
            int iCompareTo3 = about.compareTo(about2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ShareElement share = getShare();
        ShareElement share2 = detailTemplate.getShare();
        if (share != share2) {
            if (share == null) {
                return -1;
            }
            if (share2 == null) {
                return 1;
            }
            int iCompareTo4 = share.compareTo(share2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        FeaturedElement featuredItem = getFeaturedItem();
        FeaturedElement featuredItem2 = detailTemplate.getFeaturedItem();
        if (featuredItem != featuredItem2) {
            if (featuredItem == null) {
                return -1;
            }
            if (featuredItem2 == null) {
                return 1;
            }
            int iCompareTo5 = featuredItem.compareTo(featuredItem2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = detailTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo6 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        FollowElement follow = getFollow();
        FollowElement follow2 = detailTemplate.getFollow();
        if (follow != follow2) {
            if (follow == null) {
                return -1;
            }
            if (follow2 == null) {
                return 1;
            }
            int iCompareTo7 = follow.compareTo(follow2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        HeaderElement header = getHeader();
        HeaderElement header2 = detailTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo8 = header.compareTo(header2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DetailTemplate)) {
            return false;
        }
        DetailTemplate detailTemplate = (DetailTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getFeaturedPinnedElement(), detailTemplate.getFeaturedPinnedElement()) && internalEqualityCheck(getShards(), detailTemplate.getShards()) && internalEqualityCheck(getAbout(), detailTemplate.getAbout()) && internalEqualityCheck(getShare(), detailTemplate.getShare()) && internalEqualityCheck(getFeaturedItem(), detailTemplate.getFeaturedItem()) && internalEqualityCheck(getBackgroundImage(), detailTemplate.getBackgroundImage()) && internalEqualityCheck(getFollow(), detailTemplate.getFollow()) && internalEqualityCheck(getHeader(), detailTemplate.getHeader());
    }

    public AboutElement getAbout() {
        return this.about;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public FeaturedElement getFeaturedItem() {
        return this.featuredItem;
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

    public DetailTemplateShardsElement getShards() {
        return this.shards;
    }

    public ShareElement getShare() {
        return this.share;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getFeaturedPinnedElement(), getShards(), getAbout(), getShare(), getFeaturedItem(), getBackgroundImage(), getFollow(), getHeader());
    }

    public void setAbout(AboutElement aboutElement) {
        this.about = aboutElement;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setFeaturedItem(FeaturedElement featuredElement) {
        this.featuredItem = featuredElement;
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

    public void setShards(DetailTemplateShardsElement detailTemplateShardsElement) {
        this.shards = detailTemplateShardsElement;
    }

    public void setShare(ShareElement shareElement) {
        this.share = shareElement;
    }
}
