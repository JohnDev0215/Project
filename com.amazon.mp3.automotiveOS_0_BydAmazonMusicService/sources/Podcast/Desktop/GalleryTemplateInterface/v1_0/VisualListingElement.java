package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VisualListingElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualListingElement");
    private String backgroundImage;
    private String header;
    private List<VerticalItemElement> items;
    private TemplateMetadataElement metadata;
    private List<Method> onEndOfWidget;

    public static class Builder extends WidgetElement.Builder {
        protected String backgroundImage;
        protected String header;
        protected List<VerticalItemElement> items;
        protected TemplateMetadataElement metadata;
        protected List<Method> onEndOfWidget;

        public VisualListingElement build() {
            VisualListingElement visualListingElement = new VisualListingElement();
            populate(visualListingElement);
            return visualListingElement;
        }

        protected void populate(VisualListingElement visualListingElement) {
            super.populate((WidgetElement) visualListingElement);
            visualListingElement.setOnEndOfWidget(this.onEndOfWidget);
            visualListingElement.setItems(this.items);
            visualListingElement.setBackgroundImage(this.backgroundImage);
            visualListingElement.setHeader(this.header);
            visualListingElement.setMetadata(this.metadata);
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        public Builder withItems(List<VerticalItemElement> list) {
            this.items = list;
            return this;
        }

        public Builder withMetadata(TemplateMetadataElement templateMetadataElement) {
            this.metadata = templateMetadataElement;
            return this;
        }

        public Builder withOnEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
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
        if (!(sOAObject instanceof VisualListingElement)) {
            return 1;
        }
        VisualListingElement visualListingElement = (VisualListingElement) sOAObject;
        List<Method> onEndOfWidget = getOnEndOfWidget();
        List<Method> onEndOfWidget2 = visualListingElement.getOnEndOfWidget();
        if (onEndOfWidget != onEndOfWidget2) {
            if (onEndOfWidget == null) {
                return -1;
            }
            if (onEndOfWidget2 == null) {
                return 1;
            }
            if (onEndOfWidget instanceof Comparable) {
                int iCompareTo = ((Comparable) onEndOfWidget).compareTo(onEndOfWidget2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onEndOfWidget.equals(onEndOfWidget2)) {
                int iHashCode = onEndOfWidget.hashCode();
                int iHashCode2 = onEndOfWidget2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<VerticalItemElement> items = getItems();
        List<VerticalItemElement> items2 = visualListingElement.getItems();
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
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = visualListingElement.getBackgroundImage();
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
        String header = getHeader();
        String header2 = visualListingElement.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo4 = header.compareTo(header2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = visualListingElement.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo5 = metadata.compareTo(metadata2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof VisualListingElement)) {
            return false;
        }
        VisualListingElement visualListingElement = (VisualListingElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnEndOfWidget(), visualListingElement.getOnEndOfWidget()) && internalEqualityCheck(getItems(), visualListingElement.getItems()) && internalEqualityCheck(getBackgroundImage(), visualListingElement.getBackgroundImage()) && internalEqualityCheck(getHeader(), visualListingElement.getHeader()) && internalEqualityCheck(getMetadata(), visualListingElement.getMetadata());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public String getHeader() {
        return this.header;
    }

    public List<VerticalItemElement> getItems() {
        return this.items;
    }

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnEndOfWidget() {
        return this.onEndOfWidget;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnEndOfWidget(), getItems(), getBackgroundImage(), getHeader(), getMetadata());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<VerticalItemElement> list) {
        this.items = list;
    }

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setOnEndOfWidget(List<Method> list) {
        this.onEndOfWidget = list;
    }
}
