package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VisualShovelerCompactElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.VisualShovelerCompactElement");
    private String header;
    private List<VerticalItemElement> items;
    private VerticalSeeMoreItemElement seeMore;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<VerticalItemElement> items;
        protected VerticalSeeMoreItemElement seeMore;

        public VisualShovelerCompactElement build() {
            VisualShovelerCompactElement visualShovelerCompactElement = new VisualShovelerCompactElement();
            populate(visualShovelerCompactElement);
            return visualShovelerCompactElement;
        }

        protected void populate(VisualShovelerCompactElement visualShovelerCompactElement) {
            super.populate((WidgetElement) visualShovelerCompactElement);
            visualShovelerCompactElement.setItems(this.items);
            visualShovelerCompactElement.setSeeMore(this.seeMore);
            visualShovelerCompactElement.setHeader(this.header);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withItems(List<VerticalItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnContentFirstViewed(List<Method> list) {
            super.withOnContentFirstViewed(list);
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnContentFirstViewed(List list) {
            return withOnContentFirstViewed((List<Method>) list);
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        public Builder withSeeMore(VerticalSeeMoreItemElement verticalSeeMoreItemElement) {
            this.seeMore = verticalSeeMoreItemElement;
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
    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof VisualShovelerCompactElement)) {
            return 1;
        }
        VisualShovelerCompactElement visualShovelerCompactElement = (VisualShovelerCompactElement) sOAObject;
        List<VerticalItemElement> items = getItems();
        List<VerticalItemElement> items2 = visualShovelerCompactElement.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo = ((Comparable) items).compareTo(items2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!items.equals(items2)) {
                int iHashCode = items.hashCode();
                int iHashCode2 = items2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        VerticalSeeMoreItemElement seeMore = getSeeMore();
        VerticalSeeMoreItemElement seeMore2 = visualShovelerCompactElement.getSeeMore();
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
        String header = getHeader();
        String header2 = visualShovelerCompactElement.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo3 = header.compareTo(header2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof VisualShovelerCompactElement)) {
            return false;
        }
        VisualShovelerCompactElement visualShovelerCompactElement = (VisualShovelerCompactElement) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), visualShovelerCompactElement.getItems()) && internalEqualityCheck(getSeeMore(), visualShovelerCompactElement.getSeeMore()) && internalEqualityCheck(getHeader(), visualShovelerCompactElement.getHeader());
    }

    public String getHeader() {
        return this.header;
    }

    public List<VerticalItemElement> getItems() {
        return this.items;
    }

    public VerticalSeeMoreItemElement getSeeMore() {
        return this.seeMore;
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getSeeMore(), getHeader());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<VerticalItemElement> list) {
        this.items = list;
    }

    public void setSeeMore(VerticalSeeMoreItemElement verticalSeeMoreItemElement) {
        this.seeMore = verticalSeeMoreItemElement;
    }
}
