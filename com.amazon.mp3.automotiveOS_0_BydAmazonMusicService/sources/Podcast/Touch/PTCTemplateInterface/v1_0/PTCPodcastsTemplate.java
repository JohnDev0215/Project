package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCPodcastsTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplate");
    private List<GridElement> items;
    private LeadingButtonElement leadingButton;
    private List<Method> onEndOfList;
    private String subtitle;
    private String title;
    private TrailingButtonElement trailingButton;

    public static class Builder extends Template.Builder {
        protected List<GridElement> items;
        protected LeadingButtonElement leadingButton;
        protected List<Method> onEndOfList;
        protected String subtitle;
        protected String title;
        protected TrailingButtonElement trailingButton;

        public PTCPodcastsTemplate build() {
            PTCPodcastsTemplate pTCPodcastsTemplate = new PTCPodcastsTemplate();
            populate(pTCPodcastsTemplate);
            return pTCPodcastsTemplate;
        }

        protected void populate(PTCPodcastsTemplate pTCPodcastsTemplate) {
            super.populate((Template) pTCPodcastsTemplate);
            pTCPodcastsTemplate.setOnEndOfList(this.onEndOfList);
            pTCPodcastsTemplate.setSubtitle(this.subtitle);
            pTCPodcastsTemplate.setItems(this.items);
            pTCPodcastsTemplate.setTitle(this.title);
            pTCPodcastsTemplate.setLeadingButton(this.leadingButton);
            pTCPodcastsTemplate.setTrailingButton(this.trailingButton);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withItems(List<GridElement> list) {
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
        if (!(sOAObject instanceof PTCPodcastsTemplate)) {
            return 1;
        }
        PTCPodcastsTemplate pTCPodcastsTemplate = (PTCPodcastsTemplate) sOAObject;
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = pTCPodcastsTemplate.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        String subtitle = getSubtitle();
        String subtitle2 = pTCPodcastsTemplate.getSubtitle();
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
        List<GridElement> items = getItems();
        List<GridElement> items2 = pTCPodcastsTemplate.getItems();
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
        String title = getTitle();
        String title2 = pTCPodcastsTemplate.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        LeadingButtonElement leadingButton = getLeadingButton();
        LeadingButtonElement leadingButton2 = pTCPodcastsTemplate.getLeadingButton();
        if (leadingButton != leadingButton2) {
            if (leadingButton == null) {
                return -1;
            }
            if (leadingButton2 == null) {
                return 1;
            }
            int iCompareTo5 = leadingButton.compareTo(leadingButton2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        TrailingButtonElement trailingButton = getTrailingButton();
        TrailingButtonElement trailingButton2 = pTCPodcastsTemplate.getTrailingButton();
        if (trailingButton != trailingButton2) {
            if (trailingButton == null) {
                return -1;
            }
            if (trailingButton2 == null) {
                return 1;
            }
            int iCompareTo6 = trailingButton.compareTo(trailingButton2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCPodcastsTemplate)) {
            return false;
        }
        PTCPodcastsTemplate pTCPodcastsTemplate = (PTCPodcastsTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getOnEndOfList(), pTCPodcastsTemplate.getOnEndOfList()) && internalEqualityCheck(getSubtitle(), pTCPodcastsTemplate.getSubtitle()) && internalEqualityCheck(getItems(), pTCPodcastsTemplate.getItems()) && internalEqualityCheck(getTitle(), pTCPodcastsTemplate.getTitle()) && internalEqualityCheck(getLeadingButton(), pTCPodcastsTemplate.getLeadingButton()) && internalEqualityCheck(getTrailingButton(), pTCPodcastsTemplate.getTrailingButton());
    }

    public List<GridElement> getItems() {
        return this.items;
    }

    public LeadingButtonElement getLeadingButton() {
        return this.leadingButton;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnEndOfList(), getSubtitle(), getItems(), getTitle(), getLeadingButton(), getTrailingButton());
    }

    public void setItems(List<GridElement> list) {
        this.items = list;
    }

    public void setLeadingButton(LeadingButtonElement leadingButtonElement) {
        this.leadingButton = leadingButtonElement;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
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
