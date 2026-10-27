package Podcast.Touch.LatestTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedPinnedElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedPinnedElement");
    private List<Method> onViewed;

    public static class Builder extends TemplateElement.Builder {
        protected List<Method> onViewed;

        public FeaturedPinnedElement build() {
            FeaturedPinnedElement featuredPinnedElement = new FeaturedPinnedElement();
            populate(featuredPinnedElement);
            return featuredPinnedElement;
        }

        protected void populate(FeaturedPinnedElement featuredPinnedElement) {
            super.populate((TemplateElement) featuredPinnedElement);
            featuredPinnedElement.setOnViewed(this.onViewed);
        }

        public Builder withOnViewed(List<Method> list) {
            this.onViewed = list;
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
        if (!(sOAObject instanceof FeaturedPinnedElement)) {
            return 1;
        }
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = ((FeaturedPinnedElement) sOAObject).getOnViewed();
        if (onViewed != onViewed2) {
            if (onViewed == null) {
                return -1;
            }
            if (onViewed2 == null) {
                return 1;
            }
            if (onViewed instanceof Comparable) {
                int iCompareTo = ((Comparable) onViewed).compareTo(onViewed2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onViewed.equals(onViewed2)) {
                int iHashCode = onViewed.hashCode();
                int iHashCode2 = onViewed2.hashCode();
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
        if (obj instanceof FeaturedPinnedElement) {
            return super.equals(obj) && internalEqualityCheck(getOnViewed(), ((FeaturedPinnedElement) obj).getOnViewed());
        }
        return false;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnViewed());
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}
