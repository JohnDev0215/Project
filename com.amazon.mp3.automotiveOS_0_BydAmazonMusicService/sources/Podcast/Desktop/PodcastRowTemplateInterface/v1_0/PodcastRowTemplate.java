package Podcast.Desktop.PodcastRowTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PodcastRowTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowTemplate");
    private String backgroundImage;
    private ButtonElement button;
    private String header;
    private Boolean itemIndexingEnabled;
    private List<PodcastRowItemElement> items;
    private TemplateMetadataElement metadata;
    private List<Method> onEndOfList;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected ButtonElement button;
        protected String header;
        protected Boolean itemIndexingEnabled;
        protected List<PodcastRowItemElement> items;
        protected TemplateMetadataElement metadata;
        protected List<Method> onEndOfList;

        public PodcastRowTemplate build() {
            PodcastRowTemplate podcastRowTemplate = new PodcastRowTemplate();
            populate(podcastRowTemplate);
            return podcastRowTemplate;
        }

        protected void populate(PodcastRowTemplate podcastRowTemplate) {
            super.populate((Template) podcastRowTemplate);
            podcastRowTemplate.setItems(this.items);
            podcastRowTemplate.setHeader(this.header);
            podcastRowTemplate.setBackgroundImage(this.backgroundImage);
            podcastRowTemplate.setItemIndexingEnabled(this.itemIndexingEnabled);
            podcastRowTemplate.setButton(this.button);
            podcastRowTemplate.setMetadata(this.metadata);
            podcastRowTemplate.setOnEndOfList(this.onEndOfList);
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        public Builder withButton(ButtonElement buttonElement) {
            this.button = buttonElement;
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

        public Builder withItemIndexingEnabled(Boolean bool) {
            this.itemIndexingEnabled = bool;
            return this;
        }

        public Builder withItems(List<PodcastRowItemElement> list) {
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
        if (!(sOAObject instanceof PodcastRowTemplate)) {
            return 1;
        }
        PodcastRowTemplate podcastRowTemplate = (PodcastRowTemplate) sOAObject;
        List<PodcastRowItemElement> items = getItems();
        List<PodcastRowItemElement> items2 = podcastRowTemplate.getItems();
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
        String header = getHeader();
        String header2 = podcastRowTemplate.getHeader();
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
        String backgroundImage2 = podcastRowTemplate.getBackgroundImage();
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
        Boolean boolIsItemIndexingEnabled = isItemIndexingEnabled();
        Boolean boolIsItemIndexingEnabled2 = podcastRowTemplate.isItemIndexingEnabled();
        if (boolIsItemIndexingEnabled != boolIsItemIndexingEnabled2) {
            if (boolIsItemIndexingEnabled == null) {
                return -1;
            }
            if (boolIsItemIndexingEnabled2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsItemIndexingEnabled.compareTo(boolIsItemIndexingEnabled2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        ButtonElement button = getButton();
        ButtonElement button2 = podcastRowTemplate.getButton();
        if (button != button2) {
            if (button == null) {
                return -1;
            }
            if (button2 == null) {
                return 1;
            }
            int iCompareTo5 = button.compareTo(button2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = podcastRowTemplate.getMetadata();
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
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = podcastRowTemplate.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastRowTemplate)) {
            return false;
        }
        PodcastRowTemplate podcastRowTemplate = (PodcastRowTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), podcastRowTemplate.getItems()) && internalEqualityCheck(getHeader(), podcastRowTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), podcastRowTemplate.getBackgroundImage()) && internalEqualityCheck(isItemIndexingEnabled(), podcastRowTemplate.isItemIndexingEnabled()) && internalEqualityCheck(getButton(), podcastRowTemplate.getButton()) && internalEqualityCheck(getMetadata(), podcastRowTemplate.getMetadata()) && internalEqualityCheck(getOnEndOfList(), podcastRowTemplate.getOnEndOfList());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public ButtonElement getButton() {
        return this.button;
    }

    public String getHeader() {
        return this.header;
    }

    public List<PodcastRowItemElement> getItems() {
        return this.items;
    }

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getHeader(), getBackgroundImage(), isItemIndexingEnabled(), getButton(), getMetadata(), getOnEndOfList());
    }

    public Boolean isItemIndexingEnabled() {
        return this.itemIndexingEnabled;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setButton(ButtonElement buttonElement) {
        this.button = buttonElement;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItemIndexingEnabled(Boolean bool) {
        this.itemIndexingEnabled = bool;
    }

    public void setItems(List<PodcastRowItemElement> list) {
        this.items = list;
    }

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }
}
