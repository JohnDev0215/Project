package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedPinnedElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElement");
    private String id;
    private List<Method> onViewed;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onViewed;

        public FeaturedPinnedElement build() {
            FeaturedPinnedElement featuredPinnedElement = new FeaturedPinnedElement();
            populate(featuredPinnedElement);
            return featuredPinnedElement;
        }

        protected void populate(FeaturedPinnedElement featuredPinnedElement) {
            super.populate((TemplateElement) featuredPinnedElement);
            featuredPinnedElement.setOnViewed(this.onViewed);
            featuredPinnedElement.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
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
        FeaturedPinnedElement featuredPinnedElement = (FeaturedPinnedElement) sOAObject;
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = featuredPinnedElement.getOnViewed();
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
        String id = getId();
        String id2 = featuredPinnedElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedPinnedElement)) {
            return false;
        }
        FeaturedPinnedElement featuredPinnedElement = (FeaturedPinnedElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnViewed(), featuredPinnedElement.getOnViewed()) && internalEqualityCheck(getId(), featuredPinnedElement.getId());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnViewed(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}
