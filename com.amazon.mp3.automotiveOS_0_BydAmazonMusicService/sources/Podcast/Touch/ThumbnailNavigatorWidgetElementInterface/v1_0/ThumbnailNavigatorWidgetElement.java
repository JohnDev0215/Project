package Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0;

import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ThumbnailNavigatorWidgetElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement");
    private String header;
    private List<ThumbnailNavigatorItemElement> items;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<ThumbnailNavigatorItemElement> items;

        public ThumbnailNavigatorWidgetElement build() {
            ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement = new ThumbnailNavigatorWidgetElement();
            populate(thumbnailNavigatorWidgetElement);
            return thumbnailNavigatorWidgetElement;
        }

        protected void populate(ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement) {
            super.populate((WidgetElement) thumbnailNavigatorWidgetElement);
            thumbnailNavigatorWidgetElement.setHeader(this.header);
            thumbnailNavigatorWidgetElement.setItems(this.items);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withItems(List<ThumbnailNavigatorItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public Builder withOnContentFirstViewed(List<Method> list) {
            super.withOnContentFirstViewed(list);
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnContentFirstViewed(List list) {
            return withOnContentFirstViewed((List<Method>) list);
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
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
    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof ThumbnailNavigatorWidgetElement)) {
            return 1;
        }
        ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement = (ThumbnailNavigatorWidgetElement) sOAObject;
        String header = getHeader();
        String header2 = thumbnailNavigatorWidgetElement.getHeader();
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
        List<ThumbnailNavigatorItemElement> items = getItems();
        List<ThumbnailNavigatorItemElement> items2 = thumbnailNavigatorWidgetElement.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo2 = ((Comparable) items).compareTo(items2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ThumbnailNavigatorWidgetElement)) {
            return false;
        }
        ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement = (ThumbnailNavigatorWidgetElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), thumbnailNavigatorWidgetElement.getHeader()) && internalEqualityCheck(getItems(), thumbnailNavigatorWidgetElement.getItems());
    }

    public String getHeader() {
        return this.header;
    }

    public List<ThumbnailNavigatorItemElement> getItems() {
        return this.items;
    }

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getItems());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<ThumbnailNavigatorItemElement> list) {
        this.items = list;
    }
}
