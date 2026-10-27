package Podcast.Touch.VerticalGridTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VerticalGridTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplate");
    private ButtonElement button;
    private String header;
    private List<VerticalItemElement> items;
    private List<Method> onEndOfList;

    public static class Builder extends Template.Builder {
        protected ButtonElement button;
        protected String header;
        protected List<VerticalItemElement> items;
        protected List<Method> onEndOfList;

        public VerticalGridTemplate build() {
            VerticalGridTemplate verticalGridTemplate = new VerticalGridTemplate();
            populate(verticalGridTemplate);
            return verticalGridTemplate;
        }

        protected void populate(VerticalGridTemplate verticalGridTemplate) {
            super.populate((Template) verticalGridTemplate);
            verticalGridTemplate.setHeader(this.header);
            verticalGridTemplate.setOnEndOfList(this.onEndOfList);
            verticalGridTemplate.setItems(this.items);
            verticalGridTemplate.setButton(this.button);
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

        public Builder withItems(List<VerticalItemElement> list) {
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
        if (!(sOAObject instanceof VerticalGridTemplate)) {
            return 1;
        }
        VerticalGridTemplate verticalGridTemplate = (VerticalGridTemplate) sOAObject;
        String header = getHeader();
        String header2 = verticalGridTemplate.getHeader();
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
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = verticalGridTemplate.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        List<VerticalItemElement> items = getItems();
        List<VerticalItemElement> items2 = verticalGridTemplate.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo3 = ((Comparable) items).compareTo(items2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
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
        ButtonElement button = getButton();
        ButtonElement button2 = verticalGridTemplate.getButton();
        if (button != button2) {
            if (button == null) {
                return -1;
            }
            if (button2 == null) {
                return 1;
            }
            int iCompareTo4 = button.compareTo(button2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof VerticalGridTemplate)) {
            return false;
        }
        VerticalGridTemplate verticalGridTemplate = (VerticalGridTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), verticalGridTemplate.getHeader()) && internalEqualityCheck(getOnEndOfList(), verticalGridTemplate.getOnEndOfList()) && internalEqualityCheck(getItems(), verticalGridTemplate.getItems()) && internalEqualityCheck(getButton(), verticalGridTemplate.getButton());
    }

    public ButtonElement getButton() {
        return this.button;
    }

    public String getHeader() {
        return this.header;
    }

    public List<VerticalItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getOnEndOfList(), getItems(), getButton());
    }

    public void setButton(ButtonElement buttonElement) {
        this.button = buttonElement;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<VerticalItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }
}
