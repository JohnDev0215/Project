package Podcast.Touch.RegularBarkerElementInterface.v1_0;

import Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RegularBarkerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement");
    private BarkerItemElement barker;

    public static class Builder extends WidgetElement.Builder {
        protected BarkerItemElement barker;

        public RegularBarkerElement build() {
            RegularBarkerElement regularBarkerElement = new RegularBarkerElement();
            populate(regularBarkerElement);
            return regularBarkerElement;
        }

        protected void populate(RegularBarkerElement regularBarkerElement) {
            super.populate((WidgetElement) regularBarkerElement);
            regularBarkerElement.setBarker(this.barker);
        }

        public Builder withBarker(BarkerItemElement barkerItemElement) {
            this.barker = barkerItemElement;
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
        if (!(sOAObject instanceof RegularBarkerElement)) {
            return 1;
        }
        BarkerItemElement barker = getBarker();
        BarkerItemElement barker2 = ((RegularBarkerElement) sOAObject).getBarker();
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
        if (obj instanceof RegularBarkerElement) {
            return super.equals(obj) && internalEqualityCheck(getBarker(), ((RegularBarkerElement) obj).getBarker());
        }
        return false;
    }

    public BarkerItemElement getBarker() {
        return this.barker;
    }

    @Override // Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBarker());
    }

    public void setBarker(BarkerItemElement barkerItemElement) {
        this.barker = barkerItemElement;
    }
}
