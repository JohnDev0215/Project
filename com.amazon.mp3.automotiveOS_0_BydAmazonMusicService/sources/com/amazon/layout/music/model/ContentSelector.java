package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class ContentSelector extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.ContentSelector");
    private String colorDecorator;
    private FilterSelector filterSelector;
    private PositionSelector positionSelector;
    private Boolean shouldDisplaySelectors;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        if (!(block instanceof ContentSelector)) {
            return 1;
        }
        ContentSelector contentSelector = (ContentSelector) block;
        Boolean boolIsShouldDisplaySelectors = isShouldDisplaySelectors();
        Boolean boolIsShouldDisplaySelectors2 = contentSelector.isShouldDisplaySelectors();
        if (boolIsShouldDisplaySelectors != boolIsShouldDisplaySelectors2) {
            if (boolIsShouldDisplaySelectors == null) {
                return -1;
            }
            if (boolIsShouldDisplaySelectors2 == null) {
                return 1;
            }
            int iCompareTo = boolIsShouldDisplaySelectors.compareTo(boolIsShouldDisplaySelectors2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        PositionSelector positionSelector = getPositionSelector();
        PositionSelector positionSelector2 = contentSelector.getPositionSelector();
        if (positionSelector != positionSelector2) {
            if (positionSelector == null) {
                return -1;
            }
            if (positionSelector2 == null) {
                return 1;
            }
            int iCompareTo2 = positionSelector.compareTo(positionSelector2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String colorDecorator = getColorDecorator();
        String colorDecorator2 = contentSelector.getColorDecorator();
        if (colorDecorator != colorDecorator2) {
            if (colorDecorator == null) {
                return -1;
            }
            if (colorDecorator2 == null) {
                return 1;
            }
            int iCompareTo3 = colorDecorator.compareTo(colorDecorator2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        FilterSelector filterSelector = getFilterSelector();
        FilterSelector filterSelector2 = contentSelector.getFilterSelector();
        if (filterSelector != filterSelector2) {
            if (filterSelector == null) {
                return -1;
            }
            if (filterSelector2 == null) {
                return 1;
            }
            int iCompareTo4 = filterSelector.compareTo(filterSelector2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof ContentSelector)) {
            return false;
        }
        ContentSelector contentSelector = (ContentSelector) obj;
        return super.equals(obj) && internalEqualityCheck(isShouldDisplaySelectors(), contentSelector.isShouldDisplaySelectors()) && internalEqualityCheck(getPositionSelector(), contentSelector.getPositionSelector()) && internalEqualityCheck(getColorDecorator(), contentSelector.getColorDecorator()) && internalEqualityCheck(getFilterSelector(), contentSelector.getFilterSelector());
    }

    public String getColorDecorator() {
        return this.colorDecorator;
    }

    public FilterSelector getFilterSelector() {
        return this.filterSelector;
    }

    public PositionSelector getPositionSelector() {
        return this.positionSelector;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isShouldDisplaySelectors(), getPositionSelector(), getColorDecorator(), getFilterSelector());
    }

    public Boolean isShouldDisplaySelectors() {
        return this.shouldDisplaySelectors;
    }

    public void setColorDecorator(String str) {
        this.colorDecorator = str;
    }

    public void setFilterSelector(FilterSelector filterSelector) {
        this.filterSelector = filterSelector;
    }

    public void setPositionSelector(PositionSelector positionSelector) {
        this.positionSelector = positionSelector;
    }

    public void setShouldDisplaySelectors(Boolean bool) {
        this.shouldDisplaySelectors = bool;
    }
}
