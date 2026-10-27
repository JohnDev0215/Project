package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Filter implements Comparable<Filter> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Filter");
    private String blockRef;
    private List<Entity> entities;
    private Integer selected;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Filter filter) {
        if (filter == null) {
            return -1;
        }
        if (filter == this) {
            return 0;
        }
        String blockRef = getBlockRef();
        String blockRef2 = filter.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo = blockRef.compareTo(blockRef2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = filter.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer selected = getSelected();
        Integer selected2 = filter.getSelected();
        if (selected != selected2) {
            if (selected == null) {
                return -1;
            }
            if (selected2 == null) {
                return 1;
            }
            int iCompareTo3 = selected.compareTo(selected2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Entity> entities = getEntities();
        List<Entity> entities2 = filter.getEntities();
        if (entities != entities2) {
            if (entities == null) {
                return -1;
            }
            if (entities2 == null) {
                return 1;
            }
            if (entities instanceof Comparable) {
                int iCompareTo4 = ((Comparable) entities).compareTo(entities2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!entities.equals(entities2)) {
                int iHashCode = entities.hashCode();
                int iHashCode2 = entities2.hashCode();
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
        if (!(obj instanceof Filter)) {
            return false;
        }
        Filter filter = (Filter) obj;
        return internalEqualityCheck(getBlockRef(), filter.getBlockRef()) && internalEqualityCheck(getTitle(), filter.getTitle()) && internalEqualityCheck(getSelected(), filter.getSelected()) && internalEqualityCheck(getEntities(), filter.getEntities());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public Integer getSelected() {
        return this.selected;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBlockRef(), getTitle(), getSelected(), getEntities());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setEntities(List<Entity> list) {
        this.entities = list;
    }

    public void setSelected(Integer num) {
        this.selected = num;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
