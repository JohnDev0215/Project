package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElement");
    private String header;
    private JumpBackInSeeMoreElement seeMore;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected JumpBackInSeeMoreElement seeMore;

        public JumpBackInElement build() {
            JumpBackInElement jumpBackInElement = new JumpBackInElement();
            populate(jumpBackInElement);
            return jumpBackInElement;
        }

        protected void populate(JumpBackInElement jumpBackInElement) {
            super.populate((WidgetElement) jumpBackInElement);
            jumpBackInElement.setHeader(this.header);
            jumpBackInElement.setSeeMore(this.seeMore);
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

        public Builder withSeeMore(JumpBackInSeeMoreElement jumpBackInSeeMoreElement) {
            this.seeMore = jumpBackInSeeMoreElement;
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
        if (!(sOAObject instanceof JumpBackInElement)) {
            return 1;
        }
        JumpBackInElement jumpBackInElement = (JumpBackInElement) sOAObject;
        String header = getHeader();
        String header2 = jumpBackInElement.getHeader();
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
        JumpBackInSeeMoreElement seeMore = getSeeMore();
        JumpBackInSeeMoreElement seeMore2 = jumpBackInElement.getSeeMore();
        if (seeMore != seeMore2) {
            if (seeMore == null) {
                return -1;
            }
            if (seeMore2 == null) {
                return 1;
            }
            int iCompareTo2 = seeMore.compareTo(seeMore2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof JumpBackInElement)) {
            return false;
        }
        JumpBackInElement jumpBackInElement = (JumpBackInElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), jumpBackInElement.getHeader()) && internalEqualityCheck(getSeeMore(), jumpBackInElement.getSeeMore());
    }

    public String getHeader() {
        return this.header;
    }

    public JumpBackInSeeMoreElement getSeeMore() {
        return this.seeMore;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getSeeMore());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setSeeMore(JumpBackInSeeMoreElement jumpBackInSeeMoreElement) {
        this.seeMore = jumpBackInSeeMoreElement;
    }
}
