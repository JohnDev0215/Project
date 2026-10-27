package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCEpisodesTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplate");
    private CloseButtonElement closeButton;
    private FooterButtonElement footerButton;
    private List<ListItemElement> items;
    private String subtitle;
    private String title;

    public static class Builder extends Template.Builder {
        protected CloseButtonElement closeButton;
        protected FooterButtonElement footerButton;
        protected List<ListItemElement> items;
        protected String subtitle;
        protected String title;

        public PTCEpisodesTemplate build() {
            PTCEpisodesTemplate pTCEpisodesTemplate = new PTCEpisodesTemplate();
            populate(pTCEpisodesTemplate);
            return pTCEpisodesTemplate;
        }

        protected void populate(PTCEpisodesTemplate pTCEpisodesTemplate) {
            super.populate((Template) pTCEpisodesTemplate);
            pTCEpisodesTemplate.setSubtitle(this.subtitle);
            pTCEpisodesTemplate.setItems(this.items);
            pTCEpisodesTemplate.setTitle(this.title);
            pTCEpisodesTemplate.setCloseButton(this.closeButton);
            pTCEpisodesTemplate.setFooterButton(this.footerButton);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withCloseButton(CloseButtonElement closeButtonElement) {
            this.closeButton = closeButtonElement;
            return this;
        }

        public Builder withFooterButton(FooterButtonElement footerButtonElement) {
            this.footerButton = footerButtonElement;
            return this;
        }

        public Builder withItems(List<ListItemElement> list) {
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
        if (!(sOAObject instanceof PTCEpisodesTemplate)) {
            return 1;
        }
        PTCEpisodesTemplate pTCEpisodesTemplate = (PTCEpisodesTemplate) sOAObject;
        String subtitle = getSubtitle();
        String subtitle2 = pTCEpisodesTemplate.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo = subtitle.compareTo(subtitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<ListItemElement> items = getItems();
        List<ListItemElement> items2 = pTCEpisodesTemplate.getItems();
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
        String title = getTitle();
        String title2 = pTCEpisodesTemplate.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo3 = title.compareTo(title2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        CloseButtonElement closeButton = getCloseButton();
        CloseButtonElement closeButton2 = pTCEpisodesTemplate.getCloseButton();
        if (closeButton != closeButton2) {
            if (closeButton == null) {
                return -1;
            }
            if (closeButton2 == null) {
                return 1;
            }
            int iCompareTo4 = closeButton.compareTo(closeButton2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        FooterButtonElement footerButton = getFooterButton();
        FooterButtonElement footerButton2 = pTCEpisodesTemplate.getFooterButton();
        if (footerButton != footerButton2) {
            if (footerButton == null) {
                return -1;
            }
            if (footerButton2 == null) {
                return 1;
            }
            int iCompareTo5 = footerButton.compareTo(footerButton2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCEpisodesTemplate)) {
            return false;
        }
        PTCEpisodesTemplate pTCEpisodesTemplate = (PTCEpisodesTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getSubtitle(), pTCEpisodesTemplate.getSubtitle()) && internalEqualityCheck(getItems(), pTCEpisodesTemplate.getItems()) && internalEqualityCheck(getTitle(), pTCEpisodesTemplate.getTitle()) && internalEqualityCheck(getCloseButton(), pTCEpisodesTemplate.getCloseButton()) && internalEqualityCheck(getFooterButton(), pTCEpisodesTemplate.getFooterButton());
    }

    public CloseButtonElement getCloseButton() {
        return this.closeButton;
    }

    public FooterButtonElement getFooterButton() {
        return this.footerButton;
    }

    public List<ListItemElement> getItems() {
        return this.items;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubtitle(), getItems(), getTitle(), getCloseButton(), getFooterButton());
    }

    public void setCloseButton(CloseButtonElement closeButtonElement) {
        this.closeButton = closeButtonElement;
    }

    public void setFooterButton(FooterButtonElement footerButtonElement) {
        this.footerButton = footerButtonElement;
    }

    public void setItems(List<ListItemElement> list) {
        this.items = list;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
