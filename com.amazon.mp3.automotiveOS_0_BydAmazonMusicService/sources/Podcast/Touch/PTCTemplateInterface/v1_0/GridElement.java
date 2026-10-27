package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GridElement implements Comparable<GridElement> {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.GridElement");
    private List<GridItemElement> items;
    private List<Method> onViewed;
    private String title;

    public static class Builder {
        protected List<GridItemElement> items;
        protected List<Method> onViewed;
        protected String title;

        public GridElement build() {
            GridElement gridElement = new GridElement();
            populate(gridElement);
            return gridElement;
        }

        protected void populate(GridElement gridElement) {
            gridElement.setTitle(this.title);
            gridElement.setItems(this.items);
            gridElement.setOnViewed(this.onViewed);
        }

        public Builder withItems(List<GridItemElement> list) {
            this.items = list;
            return this;
        }

        public Builder withOnViewed(List<Method> list) {
            this.onViewed = list;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GridElement gridElement) {
        if (gridElement == null) {
            return -1;
        }
        if (gridElement == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = gridElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<GridItemElement> items = getItems();
        List<GridItemElement> items2 = gridElement.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo2 = ((Comparable) items).compareTo(items2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = gridElement.getOnViewed();
        if (onViewed != onViewed2) {
            if (onViewed == null) {
                return -1;
            }
            if (onViewed2 == null) {
                return 1;
            }
            if (onViewed instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onViewed).compareTo(onViewed2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onViewed.equals(onViewed2)) {
                int iHashCode3 = onViewed.hashCode();
                int iHashCode4 = onViewed2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GridElement)) {
            return false;
        }
        GridElement gridElement = (GridElement) obj;
        return internalEqualityCheck(getTitle(), gridElement.getTitle()) && internalEqualityCheck(getItems(), gridElement.getItems()) && internalEqualityCheck(getOnViewed(), gridElement.getOnViewed());
    }

    public List<GridItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getItems(), getOnViewed());
    }

    public void setItems(List<GridItemElement> list) {
        this.items = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
