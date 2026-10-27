package Podcast.Touch.DetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DetailTemplateShardsElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardsElement");
    private List<DetailTemplateShardElement> items;
    private int selectedShardPosition;

    public static class Builder extends TemplateElement.Builder {
        protected List<DetailTemplateShardElement> items;
        protected int selectedShardPosition;

        public DetailTemplateShardsElement build() {
            DetailTemplateShardsElement detailTemplateShardsElement = new DetailTemplateShardsElement();
            populate(detailTemplateShardsElement);
            return detailTemplateShardsElement;
        }

        protected void populate(DetailTemplateShardsElement detailTemplateShardsElement) {
            super.populate((TemplateElement) detailTemplateShardsElement);
            detailTemplateShardsElement.setItems(this.items);
            detailTemplateShardsElement.setSelectedShardPosition(this.selectedShardPosition);
        }

        public Builder withItems(List<DetailTemplateShardElement> list) {
            this.items = list;
            return this;
        }

        public Builder withSelectedShardPosition(int i) {
            this.selectedShardPosition = i;
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
        if (!(sOAObject instanceof DetailTemplateShardsElement)) {
            return 1;
        }
        DetailTemplateShardsElement detailTemplateShardsElement = (DetailTemplateShardsElement) sOAObject;
        List<DetailTemplateShardElement> items = getItems();
        List<DetailTemplateShardElement> items2 = detailTemplateShardsElement.getItems();
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
        if (getSelectedShardPosition() < detailTemplateShardsElement.getSelectedShardPosition()) {
            return -1;
        }
        if (getSelectedShardPosition() > detailTemplateShardsElement.getSelectedShardPosition()) {
            return 1;
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DetailTemplateShardsElement)) {
            return false;
        }
        DetailTemplateShardsElement detailTemplateShardsElement = (DetailTemplateShardsElement) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), detailTemplateShardsElement.getItems()) && internalEqualityCheck(Integer.valueOf(getSelectedShardPosition()), Integer.valueOf(detailTemplateShardsElement.getSelectedShardPosition()));
    }

    public List<DetailTemplateShardElement> getItems() {
        return this.items;
    }

    public int getSelectedShardPosition() {
        return this.selectedShardPosition;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), Integer.valueOf(getSelectedShardPosition()));
    }

    public void setItems(List<DetailTemplateShardElement> list) {
        this.items = list;
    }

    public void setSelectedShardPosition(int i) {
        this.selectedShardPosition = i;
    }
}
