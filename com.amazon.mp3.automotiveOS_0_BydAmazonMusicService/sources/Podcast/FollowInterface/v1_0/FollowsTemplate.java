package Podcast.FollowInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FollowsTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FollowInterface.v1_0.FollowsTemplate");
    private String backgroundImage;
    private FooterElement footer;
    private String header;
    private RefinementBarElement refinementBar;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected FooterElement footer;
        protected String header;
        protected RefinementBarElement refinementBar;

        public FollowsTemplate build() {
            FollowsTemplate followsTemplate = new FollowsTemplate();
            populate(followsTemplate);
            return followsTemplate;
        }

        protected void populate(FollowsTemplate followsTemplate) {
            super.populate((Template) followsTemplate);
            followsTemplate.setFooter(this.footer);
            followsTemplate.setHeader(this.header);
            followsTemplate.setBackgroundImage(this.backgroundImage);
            followsTemplate.setRefinementBar(this.refinementBar);
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

        public Builder withFooter(FooterElement footerElement) {
            this.footer = footerElement;
            return this;
        }

        public Builder withHeader(String str) {
            this.header = str;
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

        public Builder withRefinementBar(RefinementBarElement refinementBarElement) {
            this.refinementBar = refinementBarElement;
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
        if (!(sOAObject instanceof FollowsTemplate)) {
            return 1;
        }
        FollowsTemplate followsTemplate = (FollowsTemplate) sOAObject;
        FooterElement footer = getFooter();
        FooterElement footer2 = followsTemplate.getFooter();
        if (footer != footer2) {
            if (footer == null) {
                return -1;
            }
            if (footer2 == null) {
                return 1;
            }
            int iCompareTo = footer.compareTo(footer2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String header = getHeader();
        String header2 = followsTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo2 = header.compareTo(header2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = followsTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo3 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        RefinementBarElement refinementBar = getRefinementBar();
        RefinementBarElement refinementBar2 = followsTemplate.getRefinementBar();
        if (refinementBar != refinementBar2) {
            if (refinementBar == null) {
                return -1;
            }
            if (refinementBar2 == null) {
                return 1;
            }
            int iCompareTo4 = refinementBar.compareTo(refinementBar2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FollowsTemplate)) {
            return false;
        }
        FollowsTemplate followsTemplate = (FollowsTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getFooter(), followsTemplate.getFooter()) && internalEqualityCheck(getHeader(), followsTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), followsTemplate.getBackgroundImage()) && internalEqualityCheck(getRefinementBar(), followsTemplate.getRefinementBar());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public FooterElement getFooter() {
        return this.footer;
    }

    public String getHeader() {
        return this.header;
    }

    public RefinementBarElement getRefinementBar() {
        return this.refinementBar;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getFooter(), getHeader(), getBackgroundImage(), getRefinementBar());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setFooter(FooterElement footerElement) {
        this.footer = footerElement;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setRefinementBar(RefinementBarElement refinementBarElement) {
        this.refinementBar = refinementBarElement;
    }
}
