package Podcast.Touch.DescriptiveShowcaseElement.v1_0;

import Podcast.Touch.HorizontalItemElementInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DescriptiveShowcaseElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElement");
    private List<HorizontalItemElement> items;
    private String subtitle;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
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
        String title = getTitle();
        String title2 = descriptiveShowcaseElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
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
        String subtitle = getSubtitle();
        String subtitle2 = descriptiveShowcaseElement.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo3 = subtitle.compareTo(subtitle2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DescriptiveShowcaseElement)) {
            return false;
        }
        DescriptiveShowcaseElement descriptiveShowcaseElement = (DescriptiveShowcaseElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), descriptiveShowcaseElement.getTitle()) && internalEqualityCheck(getItems(), descriptiveShowcaseElement.getItems()) && internalEqualityCheck(getSubtitle(), descriptiveShowcaseElement.getSubtitle());
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

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getItems(), getSubtitle());
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
