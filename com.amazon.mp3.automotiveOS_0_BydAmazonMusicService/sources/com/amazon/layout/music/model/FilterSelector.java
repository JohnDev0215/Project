package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FilterSelector implements Comparable<FilterSelector> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.FilterSelector");
    private Filter currentFilter;
    private List<Filter> filterOptions;
    private String filterTitle;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated FilterSelector filterSelector) {
        if (filterSelector == null) {
            return -1;
        }
        if (filterSelector == this) {
            return 0;
        }
        Filter currentFilter = getCurrentFilter();
        Filter currentFilter2 = filterSelector.getCurrentFilter();
        if (currentFilter != currentFilter2) {
            if (currentFilter == null) {
                return -1;
            }
            if (currentFilter2 == null) {
                return 1;
            }
            int iCompareTo = currentFilter.compareTo(currentFilter2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String filterTitle = getFilterTitle();
        String filterTitle2 = filterSelector.getFilterTitle();
        if (filterTitle != filterTitle2) {
            if (filterTitle == null) {
                return -1;
            }
            if (filterTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = filterTitle.compareTo(filterTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Filter> filterOptions = getFilterOptions();
        List<Filter> filterOptions2 = filterSelector.getFilterOptions();
        if (filterOptions != filterOptions2) {
            if (filterOptions == null) {
                return -1;
            }
            if (filterOptions2 == null) {
                return 1;
            }
            if (filterOptions instanceof Comparable) {
                int iCompareTo3 = ((Comparable) filterOptions).compareTo(filterOptions2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!filterOptions.equals(filterOptions2)) {
                int iHashCode = filterOptions.hashCode();
                int iHashCode2 = filterOptions2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FilterSelector)) {
            return false;
        }
        FilterSelector filterSelector = (FilterSelector) obj;
        return internalEqualityCheck(getCurrentFilter(), filterSelector.getCurrentFilter()) && internalEqualityCheck(getFilterTitle(), filterSelector.getFilterTitle()) && internalEqualityCheck(getFilterOptions(), filterSelector.getFilterOptions());
    }

    public Filter getCurrentFilter() {
        return this.currentFilter;
    }

    public List<Filter> getFilterOptions() {
        return this.filterOptions;
    }

    public String getFilterTitle() {
        return this.filterTitle;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCurrentFilter(), getFilterTitle(), getFilterOptions());
    }

    public void setCurrentFilter(Filter filter) {
        this.currentFilter = filter;
    }

    public void setFilterOptions(List<Filter> list) {
        this.filterOptions = list;
    }

    public void setFilterTitle(String str) {
        this.filterTitle = str;
    }
}
