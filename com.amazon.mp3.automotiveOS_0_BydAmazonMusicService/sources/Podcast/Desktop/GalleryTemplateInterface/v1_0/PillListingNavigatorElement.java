package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PillListingNavigatorElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.PillListingNavigatorElement");
    private String header;
    private List<PillItemElement> items;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<PillItemElement> items;

        public PillListingNavigatorElement build() {
            PillListingNavigatorElement pillListingNavigatorElement = new PillListingNavigatorElement();
            populate(pillListingNavigatorElement);
            return pillListingNavigatorElement;
        }

        protected void populate(PillListingNavigatorElement pillListingNavigatorElement) {
            super.populate((WidgetElement) pillListingNavigatorElement);
            pillListingNavigatorElement.setHeader(this.header);
            pillListingNavigatorElement.setItems(this.items);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        public Builder withItems(List<PillItemElement> list) {
            this.items = list;
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
        if (!(sOAObject instanceof PillListingNavigatorElement)) {
            return 1;
        }
        PillListingNavigatorElement pillListingNavigatorElement = (PillListingNavigatorElement) sOAObject;
        String header = getHeader();
        String header2 = pillListingNavigatorElement.getHeader();
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
        List<PillItemElement> items = getItems();
        List<PillItemElement> items2 = pillListingNavigatorElement.getItems();
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

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PillListingNavigatorElement)) {
            return false;
        }
        PillListingNavigatorElement pillListingNavigatorElement = (PillListingNavigatorElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), pillListingNavigatorElement.getHeader()) && internalEqualityCheck(getItems(), pillListingNavigatorElement.getItems());
    }

    public String getHeader() {
        return this.header;
    }

    public List<PillItemElement> getItems() {
        return this.items;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getItems());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<PillItemElement> list) {
        this.items = list;
    }
}
