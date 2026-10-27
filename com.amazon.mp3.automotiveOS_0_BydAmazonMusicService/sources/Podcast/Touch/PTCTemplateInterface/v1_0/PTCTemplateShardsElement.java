package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Shard;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCTemplateShardsElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElement");
    private List<Shard> items;

    public static class Builder extends TemplateElement.Builder {
        protected List<Shard> items;

        public PTCTemplateShardsElement build() {
            PTCTemplateShardsElement pTCTemplateShardsElement = new PTCTemplateShardsElement();
            populate(pTCTemplateShardsElement);
            return pTCTemplateShardsElement;
        }

        protected void populate(PTCTemplateShardsElement pTCTemplateShardsElement) {
            super.populate((TemplateElement) pTCTemplateShardsElement);
            pTCTemplateShardsElement.setItems(this.items);
        }

        public Builder withItems(List<Shard> list) {
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
        if (!(sOAObject instanceof PTCTemplateShardsElement)) {
            return 1;
        }
        List<Shard> items = getItems();
        List<Shard> items2 = ((PTCTemplateShardsElement) sOAObject).getItems();
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
        if (obj instanceof PTCTemplateShardsElement) {
            return super.equals(obj) && internalEqualityCheck(getItems(), ((PTCTemplateShardsElement) obj).getItems());
        }
        return false;
    }

    public List<Shard> getItems() {
        return this.items;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems());
    }

    public void setItems(List<Shard> list) {
        this.items = list;
    }
}
