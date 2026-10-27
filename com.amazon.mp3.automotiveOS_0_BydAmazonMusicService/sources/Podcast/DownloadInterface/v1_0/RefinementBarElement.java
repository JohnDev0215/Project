package Podcast.DownloadInterface.v1_0;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RefinementBarElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.DownloadInterface.v1_0.RefinementBarElement");
    private CheckBoxRefinementOptionElement sortingOptions;
    private ToggleRefinementOptionElement unplayedFilter;

    public static class Builder extends TemplateElement.Builder {
        protected CheckBoxRefinementOptionElement sortingOptions;
        protected ToggleRefinementOptionElement unplayedFilter;

        public RefinementBarElement build() {
            RefinementBarElement refinementBarElement = new RefinementBarElement();
            populate(refinementBarElement);
            return refinementBarElement;
        }

        protected void populate(RefinementBarElement refinementBarElement) {
            super.populate((TemplateElement) refinementBarElement);
            refinementBarElement.setUnplayedFilter(this.unplayedFilter);
            refinementBarElement.setSortingOptions(this.sortingOptions);
        }

        public Builder withSortingOptions(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
            this.sortingOptions = checkBoxRefinementOptionElement;
            return this;
        }

        public Builder withUnplayedFilter(ToggleRefinementOptionElement toggleRefinementOptionElement) {
            this.unplayedFilter = toggleRefinementOptionElement;
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
        if (!(sOAObject instanceof RefinementBarElement)) {
            return 1;
        }
        RefinementBarElement refinementBarElement = (RefinementBarElement) sOAObject;
        ToggleRefinementOptionElement unplayedFilter = getUnplayedFilter();
        ToggleRefinementOptionElement unplayedFilter2 = refinementBarElement.getUnplayedFilter();
        if (unplayedFilter != unplayedFilter2) {
            if (unplayedFilter == null) {
                return -1;
            }
            if (unplayedFilter2 == null) {
                return 1;
            }
            int iCompareTo = unplayedFilter.compareTo(unplayedFilter2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        CheckBoxRefinementOptionElement sortingOptions = getSortingOptions();
        CheckBoxRefinementOptionElement sortingOptions2 = refinementBarElement.getSortingOptions();
        if (sortingOptions != sortingOptions2) {
            if (sortingOptions == null) {
                return -1;
            }
            if (sortingOptions2 == null) {
                return 1;
            }
            int iCompareTo2 = sortingOptions.compareTo(sortingOptions2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RefinementBarElement)) {
            return false;
        }
        RefinementBarElement refinementBarElement = (RefinementBarElement) obj;
        return super.equals(obj) && internalEqualityCheck(getUnplayedFilter(), refinementBarElement.getUnplayedFilter()) && internalEqualityCheck(getSortingOptions(), refinementBarElement.getSortingOptions());
    }

    public CheckBoxRefinementOptionElement getSortingOptions() {
        return this.sortingOptions;
    }

    public ToggleRefinementOptionElement getUnplayedFilter() {
        return this.unplayedFilter;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUnplayedFilter(), getSortingOptions());
    }

    public void setSortingOptions(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
        this.sortingOptions = checkBoxRefinementOptionElement;
    }

    public void setUnplayedFilter(ToggleRefinementOptionElement toggleRefinementOptionElement) {
        this.unplayedFilter = toggleRefinementOptionElement;
    }
}
