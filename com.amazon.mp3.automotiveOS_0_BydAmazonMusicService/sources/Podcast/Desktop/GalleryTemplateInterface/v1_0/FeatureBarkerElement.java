package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeatureBarkerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.FeatureBarkerElement");
    private BarkerItemElement barker;

    public static class Builder extends WidgetElement.Builder {
        protected BarkerItemElement barker;

        public FeatureBarkerElement build() {
            FeatureBarkerElement featureBarkerElement = new FeatureBarkerElement();
            populate(featureBarkerElement);
            return featureBarkerElement;
        }

        protected void populate(FeatureBarkerElement featureBarkerElement) {
            super.populate((WidgetElement) featureBarkerElement);
            featureBarkerElement.setBarker(this.barker);
        }

        public Builder withBarker(BarkerItemElement barkerItemElement) {
            this.barker = barkerItemElement;
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
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
    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof FeatureBarkerElement)) {
            return 1;
        }
        BarkerItemElement barker = getBarker();
        BarkerItemElement barker2 = ((FeatureBarkerElement) sOAObject).getBarker();
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

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof FeatureBarkerElement) {
            return super.equals(obj) && internalEqualityCheck(getBarker(), ((FeatureBarkerElement) obj).getBarker());
        }
        return false;
    }

    public BarkerItemElement getBarker() {
        return this.barker;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBarker());
    }

    public void setBarker(BarkerItemElement barkerItemElement) {
        this.barker = barkerItemElement;
    }
}
