package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCCategoriesTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplate");
    private List<PillItemElement> items;
    private LeadingButtonElement leadingButton;
    private String subtitle;
    private String title;
    private TrailingButtonElement trailingButton;

    public static class Builder extends Template.Builder {
        protected List<PillItemElement> items;
        protected LeadingButtonElement leadingButton;
        protected String subtitle;
        protected String title;
        protected TrailingButtonElement trailingButton;

        public PTCCategoriesTemplate build() {
            PTCCategoriesTemplate pTCCategoriesTemplate = new PTCCategoriesTemplate();
            populate(pTCCategoriesTemplate);
            return pTCCategoriesTemplate;
        }

        protected void populate(PTCCategoriesTemplate pTCCategoriesTemplate) {
            super.populate((Template) pTCCategoriesTemplate);
            pTCCategoriesTemplate.setTrailingButton(this.trailingButton);
            pTCCategoriesTemplate.setSubtitle(this.subtitle);
            pTCCategoriesTemplate.setLeadingButton(this.leadingButton);
            pTCCategoriesTemplate.setItems(this.items);
            pTCCategoriesTemplate.setTitle(this.title);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withItems(List<PillItemElement> list) {
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

        public Builder withLeadingButton(LeadingButtonElement leadingButtonElement) {
            this.leadingButton = leadingButtonElement;
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

        public Builder withSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder withTrailingButton(TrailingButtonElement trailingButtonElement) {
            this.trailingButton = trailingButtonElement;
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
        if (!(sOAObject instanceof PTCCategoriesTemplate)) {
            return 1;
        }
        PTCCategoriesTemplate pTCCategoriesTemplate = (PTCCategoriesTemplate) sOAObject;
        TrailingButtonElement trailingButton = getTrailingButton();
        TrailingButtonElement trailingButton2 = pTCCategoriesTemplate.getTrailingButton();
        if (trailingButton != trailingButton2) {
            if (trailingButton == null) {
                return -1;
            }
            if (trailingButton2 == null) {
                return 1;
            }
            int iCompareTo = trailingButton.compareTo(trailingButton2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = pTCCategoriesTemplate.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo2 = subtitle.compareTo(subtitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        LeadingButtonElement leadingButton = getLeadingButton();
        LeadingButtonElement leadingButton2 = pTCCategoriesTemplate.getLeadingButton();
        if (leadingButton != leadingButton2) {
            if (leadingButton == null) {
                return -1;
            }
            if (leadingButton2 == null) {
                return 1;
            }
            int iCompareTo3 = leadingButton.compareTo(leadingButton2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<PillItemElement> items = getItems();
        List<PillItemElement> items2 = pTCCategoriesTemplate.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo4 = ((Comparable) items).compareTo(items2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        String title = getTitle();
        String title2 = pTCCategoriesTemplate.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCCategoriesTemplate)) {
            return false;
        }
        PTCCategoriesTemplate pTCCategoriesTemplate = (PTCCategoriesTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getTrailingButton(), pTCCategoriesTemplate.getTrailingButton()) && internalEqualityCheck(getSubtitle(), pTCCategoriesTemplate.getSubtitle()) && internalEqualityCheck(getLeadingButton(), pTCCategoriesTemplate.getLeadingButton()) && internalEqualityCheck(getItems(), pTCCategoriesTemplate.getItems()) && internalEqualityCheck(getTitle(), pTCCategoriesTemplate.getTitle());
    }

    public List<PillItemElement> getItems() {
        return this.items;
    }

    public LeadingButtonElement getLeadingButton() {
        return this.leadingButton;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public TrailingButtonElement getTrailingButton() {
        return this.trailingButton;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrailingButton(), getSubtitle(), getLeadingButton(), getItems(), getTitle());
    }

    public void setItems(List<PillItemElement> list) {
        this.items = list;
    }

    public void setLeadingButton(LeadingButtonElement leadingButtonElement) {
        this.leadingButton = leadingButtonElement;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrailingButton(TrailingButtonElement trailingButtonElement) {
        this.trailingButton = trailingButtonElement;
    }
}
