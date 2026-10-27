package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FollowsElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsElement");
    private String header;
    private FollowsSeeMoreElement seeMore;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected FollowsSeeMoreElement seeMore;

        public FollowsElement build() {
            FollowsElement followsElement = new FollowsElement();
            populate(followsElement);
            return followsElement;
        }

        protected void populate(FollowsElement followsElement) {
            super.populate((WidgetElement) followsElement);
            followsElement.setSeeMore(this.seeMore);
            followsElement.setHeader(this.header);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        public Builder withSeeMore(FollowsSeeMoreElement followsSeeMoreElement) {
            this.seeMore = followsSeeMoreElement;
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
    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof FollowsElement)) {
            return 1;
        }
        FollowsElement followsElement = (FollowsElement) sOAObject;
        FollowsSeeMoreElement seeMore = getSeeMore();
        FollowsSeeMoreElement seeMore2 = followsElement.getSeeMore();
        if (seeMore != seeMore2) {
            if (seeMore == null) {
                return -1;
            }
            if (seeMore2 == null) {
                return 1;
            }
            int iCompareTo = seeMore.compareTo(seeMore2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String header = getHeader();
        String header2 = followsElement.getHeader();
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
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FollowsElement)) {
            return false;
        }
        FollowsElement followsElement = (FollowsElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSeeMore(), followsElement.getSeeMore()) && internalEqualityCheck(getHeader(), followsElement.getHeader());
    }

    public String getHeader() {
        return this.header;
    }

    public FollowsSeeMoreElement getSeeMore() {
        return this.seeMore;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSeeMore(), getHeader());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setSeeMore(FollowsSeeMoreElement followsSeeMoreElement) {
        this.seeMore = followsSeeMoreElement;
    }
}
