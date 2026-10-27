package Podcast.Touch.HorizontalRowTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalRowTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplate");
    private String backgroundImage;
    private ButtonElement button;
    private String header;
    private Boolean itemIndexingEnabled;
    private List<HorizontalItemElement> items;
    private List<Method> onEndOfList;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected ButtonElement button;
        protected String header;
        protected Boolean itemIndexingEnabled;
        protected List<HorizontalItemElement> items;
        protected List<Method> onEndOfList;

        public HorizontalRowTemplate build() {
            HorizontalRowTemplate horizontalRowTemplate = new HorizontalRowTemplate();
            populate(horizontalRowTemplate);
            return horizontalRowTemplate;
        }

        protected void populate(HorizontalRowTemplate horizontalRowTemplate) {
            super.populate((Template) horizontalRowTemplate);
            horizontalRowTemplate.setButton(this.button);
            horizontalRowTemplate.setItemIndexingEnabled(this.itemIndexingEnabled);
            horizontalRowTemplate.setOnEndOfList(this.onEndOfList);
            horizontalRowTemplate.setHeader(this.header);
            horizontalRowTemplate.setBackgroundImage(this.backgroundImage);
            horizontalRowTemplate.setItems(this.items);
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

        public Builder withItems(List<HorizontalItemElement> list) {
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
        if (!(sOAObject instanceof HorizontalRowTemplate)) {
            return 1;
        }
        HorizontalRowTemplate horizontalRowTemplate = (HorizontalRowTemplate) sOAObject;
        ButtonElement button = getButton();
        ButtonElement button2 = horizontalRowTemplate.getButton();
        if (button != button2) {
            if (button == null) {
                return -1;
            }
            if (button2 == null) {
                return 1;
            }
            int iCompareTo = button.compareTo(button2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsItemIndexingEnabled = isItemIndexingEnabled();
        Boolean boolIsItemIndexingEnabled2 = horizontalRowTemplate.isItemIndexingEnabled();
        if (boolIsItemIndexingEnabled != boolIsItemIndexingEnabled2) {
            if (boolIsItemIndexingEnabled == null) {
                return -1;
            }
            if (boolIsItemIndexingEnabled2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsItemIndexingEnabled.compareTo(boolIsItemIndexingEnabled2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = horizontalRowTemplate.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onEndOfList.equals(onEndOfList2)) {
                int iHashCode = onEndOfList.hashCode();
                int iHashCode2 = onEndOfList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String header = getHeader();
        String header2 = horizontalRowTemplate.getHeader();
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
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = horizontalRowTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo5 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<HorizontalItemElement> items = getItems();
        List<HorizontalItemElement> items2 = horizontalRowTemplate.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo6 = ((Comparable) items).compareTo(items2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof HorizontalRowTemplate)) {
            return false;
        }
        HorizontalRowTemplate horizontalRowTemplate = (HorizontalRowTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getButton(), horizontalRowTemplate.getButton()) && internalEqualityCheck(isItemIndexingEnabled(), horizontalRowTemplate.isItemIndexingEnabled()) && internalEqualityCheck(getOnEndOfList(), horizontalRowTemplate.getOnEndOfList()) && internalEqualityCheck(getHeader(), horizontalRowTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), horizontalRowTemplate.getBackgroundImage()) && internalEqualityCheck(getItems(), horizontalRowTemplate.getItems());
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

    public List<HorizontalItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getButton(), isItemIndexingEnabled(), getOnEndOfList(), getHeader(), getBackgroundImage(), getItems());
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

    public void setItems(List<HorizontalItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }
}
