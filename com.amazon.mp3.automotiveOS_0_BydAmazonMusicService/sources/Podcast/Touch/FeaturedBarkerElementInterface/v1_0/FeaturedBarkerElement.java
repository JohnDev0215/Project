package Podcast.Touch.FeaturedBarkerElementInterface.v1_0;

import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedBarkerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElement");
    private FeaturedBarkerItemElement barker;

    public static class Builder extends WidgetElement.Builder {
        protected FeaturedBarkerItemElement barker;

        public FeaturedBarkerElement build() {
            FeaturedBarkerElement featuredBarkerElement = new FeaturedBarkerElement();
            populate(featuredBarkerElement);
            return featuredBarkerElement;
        }

        protected void populate(FeaturedBarkerElement featuredBarkerElement) {
            super.populate((WidgetElement) featuredBarkerElement);
            featuredBarkerElement.setBarker(this.barker);
        }

        public Builder withBarker(FeaturedBarkerItemElement featuredBarkerItemElement) {
            this.barker = featuredBarkerItemElement;
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public Builder withOnContentFirstViewed(List<Method> list) {
            super.withOnContentFirstViewed(list);
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnContentFirstViewed(List list) {
            return withOnContentFirstViewed((List<Method>) list);
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
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
    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof FeaturedBarkerElement)) {
            return 1;
        }
        FeaturedBarkerItemElement barker = getBarker();
        FeaturedBarkerItemElement barker2 = ((FeaturedBarkerElement) sOAObject).getBarker();
        if (barker != barker2) {
            if (barker == null) {
                return -1;
            }
            if (barker2 == null) {
                return 1;
            }
            int iCompareTo = barker.compareTo(barker2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof FeaturedBarkerElement) {
            return super.equals(obj) && internalEqualityCheck(getBarker(), ((FeaturedBarkerElement) obj).getBarker());
        }
        return false;
    }

    public FeaturedBarkerItemElement getBarker() {
        return this.barker;
    }

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBarker());
    }

    public void setBarker(FeaturedBarkerItemElement featuredBarkerItemElement) {
        this.barker = featuredBarkerItemElement;
    }
}
