package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PositionSelector implements Comparable<PositionSelector> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.PositionSelector");
    private Position currentPosition;
    private String icon;
    private List<Position> positionOptions;
    private String positionTitle;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PositionSelector positionSelector) {
        if (positionSelector == null) {
            return -1;
        }
        if (positionSelector == this) {
            return 0;
        }
        List<Position> positionOptions = getPositionOptions();
        List<Position> positionOptions2 = positionSelector.getPositionOptions();
        if (positionOptions != positionOptions2) {
            if (positionOptions == null) {
                return -1;
            }
            if (positionOptions2 == null) {
                return 1;
            }
            if (positionOptions instanceof Comparable) {
                int iCompareTo = ((Comparable) positionOptions).compareTo(positionOptions2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!positionOptions.equals(positionOptions2)) {
                int iHashCode = positionOptions.hashCode();
                int iHashCode2 = positionOptions2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String icon = getIcon();
        String icon2 = positionSelector.getIcon();
        if (icon != icon2) {
            if (icon == null) {
                return -1;
            }
            if (icon2 == null) {
                return 1;
            }
            int iCompareTo2 = icon.compareTo(icon2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String positionTitle = getPositionTitle();
        String positionTitle2 = positionSelector.getPositionTitle();
        if (positionTitle != positionTitle2) {
            if (positionTitle == null) {
                return -1;
            }
            if (positionTitle2 == null) {
                return 1;
            }
            int iCompareTo3 = positionTitle.compareTo(positionTitle2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Position currentPosition = getCurrentPosition();
        Position currentPosition2 = positionSelector.getCurrentPosition();
        if (currentPosition != currentPosition2) {
            if (currentPosition == null) {
                return -1;
            }
            if (currentPosition2 == null) {
                return 1;
            }
            int iCompareTo4 = currentPosition.compareTo(currentPosition2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PositionSelector)) {
            return false;
        }
        PositionSelector positionSelector = (PositionSelector) obj;
        return internalEqualityCheck(getPositionOptions(), positionSelector.getPositionOptions()) && internalEqualityCheck(getIcon(), positionSelector.getIcon()) && internalEqualityCheck(getPositionTitle(), positionSelector.getPositionTitle()) && internalEqualityCheck(getCurrentPosition(), positionSelector.getCurrentPosition());
    }

    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    public String getIcon() {
        return this.icon;
    }

    public List<Position> getPositionOptions() {
        return this.positionOptions;
    }

    public String getPositionTitle() {
        return this.positionTitle;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPositionOptions(), getIcon(), getPositionTitle(), getCurrentPosition());
    }

    public void setCurrentPosition(Position position) {
        this.currentPosition = position;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setPositionOptions(List<Position> list) {
        this.positionOptions = list;
    }

    public void setPositionTitle(String str) {
        this.positionTitle = str;
    }
}
