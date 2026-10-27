package Podcast.Touch.CompactPillNavigatorElementInterface.v1_0;

import Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompactPillNavigator extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CompactPillNavigatorElementInterface.v1_0.CompactPillNavigator");
    private List<CompactPillItemElement> items;

    public static class Builder extends TemplateElement.Builder {
        protected List<CompactPillItemElement> items;

        public CompactPillNavigator build() {
            CompactPillNavigator compactPillNavigator = new CompactPillNavigator();
            populate(compactPillNavigator);
            return compactPillNavigator;
        }

        protected void populate(CompactPillNavigator compactPillNavigator) {
            super.populate((TemplateElement) compactPillNavigator);
            compactPillNavigator.setItems(this.items);
        }

        public Builder withItems(List<CompactPillItemElement> list) {
            this.items = list;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof CompactPillNavigator)) {
            return 1;
        }
        List<CompactPillItemElement> items = getItems();
        List<CompactPillItemElement> items2 = ((CompactPillNavigator) sOAObject).getItems();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof CompactPillNavigator) {
            return super.equals(obj) && internalEqualityCheck(getItems(), ((CompactPillNavigator) obj).getItems());
        }
        return false;
    }

    public List<CompactPillItemElement> getItems() {
        return this.items;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems());
    }

    public void setItems(List<CompactPillItemElement> list) {
        this.items = list;
    }
}
