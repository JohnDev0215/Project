package Podcast.Touch.WidgetsInterface.v1_0;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.WidgetsInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DescriptiveShowcaseElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement");
    private List<HorizontalItemElement> items;
    private String subtitle;
    private String title;

    public static class Builder extends WidgetElement.Builder {
        protected List<HorizontalItemElement> items;
        protected String subtitle;
        protected String title;

        public DescriptiveShowcaseElement build() {
            DescriptiveShowcaseElement descriptiveShowcaseElement = new DescriptiveShowcaseElement();
            populate(descriptiveShowcaseElement);
            return descriptiveShowcaseElement;
        }

        protected void populate(DescriptiveShowcaseElement descriptiveShowcaseElement) {
            super.populate((WidgetElement) descriptiveShowcaseElement);
            descriptiveShowcaseElement.setSubtitle(this.subtitle);
            descriptiveShowcaseElement.setItems(this.items);
            descriptiveShowcaseElement.setTitle(this.title);
        }

        @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withItems(List<HorizontalItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement.Builder
        public Builder withOnContentFirstViewed(List<Method> list) {
            super.withOnContentFirstViewed(list);
            return this;
        }

        @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnContentFirstViewed(List list) {
            return withOnContentFirstViewed((List<Method>) list);
        }

        @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
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
    @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof DescriptiveShowcaseElement)) {
            return 1;
        }
        DescriptiveShowcaseElement descriptiveShowcaseElement = (DescriptiveShowcaseElement) sOAObject;
        String subtitle = getSubtitle();
        String subtitle2 = descriptiveShowcaseElement.getSubtitle();
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
        List<HorizontalItemElement> items = getItems();
        List<HorizontalItemElement> items2 = descriptiveShowcaseElement.getItems();
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
        String title2 = descriptiveShowcaseElement.getTitle();
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
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DescriptiveShowcaseElement)) {
            return false;
        }
        DescriptiveShowcaseElement descriptiveShowcaseElement = (DescriptiveShowcaseElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSubtitle(), descriptiveShowcaseElement.getSubtitle()) && internalEqualityCheck(getItems(), descriptiveShowcaseElement.getItems()) && internalEqualityCheck(getTitle(), descriptiveShowcaseElement.getTitle());
    }

    public List<HorizontalItemElement> getItems() {
        return this.items;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // Podcast.Touch.WidgetsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubtitle(), getItems(), getTitle());
    }

    public void setItems(List<HorizontalItemElement> list) {
        this.items = list;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
