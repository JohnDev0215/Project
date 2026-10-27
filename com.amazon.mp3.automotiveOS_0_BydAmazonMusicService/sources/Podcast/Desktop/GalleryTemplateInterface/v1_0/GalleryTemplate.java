package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GalleryTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.GalleryTemplate");
    private AboutElement about;
    private String backgroundImage;
    private String header;
    private TemplateMetadataElement metadata;
    private List<Method> onEndOfList;
    private List<WidgetElement> widgets;

    public static class Builder extends Template.Builder {
        protected AboutElement about;
        protected String backgroundImage;
        protected String header;
        protected TemplateMetadataElement metadata;
        protected List<Method> onEndOfList;
        protected List<WidgetElement> widgets;

        public GalleryTemplate build() {
            GalleryTemplate galleryTemplate = new GalleryTemplate();
            populate(galleryTemplate);
            return galleryTemplate;
        }

        protected void populate(GalleryTemplate galleryTemplate) {
            super.populate((Template) galleryTemplate);
            galleryTemplate.setWidgets(this.widgets);
            galleryTemplate.setHeader(this.header);
            galleryTemplate.setBackgroundImage(this.backgroundImage);
            galleryTemplate.setAbout(this.about);
            galleryTemplate.setOnEndOfList(this.onEndOfList);
            galleryTemplate.setMetadata(this.metadata);
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

        public Builder withMetadata(TemplateMetadataElement templateMetadataElement) {
            this.metadata = templateMetadataElement;
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

        public Builder withWidgets(List<WidgetElement> list) {
            this.widgets = list;
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
        if (!(sOAObject instanceof GalleryTemplate)) {
            return 1;
        }
        GalleryTemplate galleryTemplate = (GalleryTemplate) sOAObject;
        List<WidgetElement> widgets = getWidgets();
        List<WidgetElement> widgets2 = galleryTemplate.getWidgets();
        if (widgets != widgets2) {
            if (widgets == null) {
                return -1;
            }
            if (widgets2 == null) {
                return 1;
            }
            if (widgets instanceof Comparable) {
                int iCompareTo = ((Comparable) widgets).compareTo(widgets2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!widgets.equals(widgets2)) {
                int iHashCode = widgets.hashCode();
                int iHashCode2 = widgets2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String header = getHeader();
        String header2 = galleryTemplate.getHeader();
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
        String backgroundImage2 = galleryTemplate.getBackgroundImage();
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
        AboutElement about = getAbout();
        AboutElement about2 = galleryTemplate.getAbout();
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
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = galleryTemplate.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onEndOfList.equals(onEndOfList2)) {
                int iHashCode3 = onEndOfList.hashCode();
                int iHashCode4 = onEndOfList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = galleryTemplate.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo6 = metadata.compareTo(metadata2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof GalleryTemplate)) {
            return false;
        }
        GalleryTemplate galleryTemplate = (GalleryTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getWidgets(), galleryTemplate.getWidgets()) && internalEqualityCheck(getHeader(), galleryTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), galleryTemplate.getBackgroundImage()) && internalEqualityCheck(getAbout(), galleryTemplate.getAbout()) && internalEqualityCheck(getOnEndOfList(), galleryTemplate.getOnEndOfList()) && internalEqualityCheck(getMetadata(), galleryTemplate.getMetadata());
    }

    public AboutElement getAbout() {
        return this.about;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public String getHeader() {
        return this.header;
    }

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    public List<WidgetElement> getWidgets() {
        return this.widgets;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getWidgets(), getHeader(), getBackgroundImage(), getAbout(), getOnEndOfList(), getMetadata());
    }

    public void setAbout(AboutElement aboutElement) {
        this.about = aboutElement;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }

    public void setWidgets(List<WidgetElement> list) {
        this.widgets = list;
    }
}
