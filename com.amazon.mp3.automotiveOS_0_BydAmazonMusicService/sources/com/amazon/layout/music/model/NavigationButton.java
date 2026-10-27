package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationButton extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.NavigationButton");
    private String backgroundDark;
    private String backgroundLight;
    private String target;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Block, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        if (!(block instanceof NavigationButton)) {
            return 1;
        }
        NavigationButton navigationButton = (NavigationButton) block;
        String backgroundDark = getBackgroundDark();
        String backgroundDark2 = navigationButton.getBackgroundDark();
        if (backgroundDark != backgroundDark2) {
            if (backgroundDark == null) {
                return -1;
            }
            if (backgroundDark2 == null) {
                return 1;
            }
            int iCompareTo = backgroundDark.compareTo(backgroundDark2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String target = getTarget();
        String target2 = navigationButton.getTarget();
        if (target != target2) {
            if (target == null) {
                return -1;
            }
            if (target2 == null) {
                return 1;
            }
            int iCompareTo2 = target.compareTo(target2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = navigationButton.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo3 = title.compareTo(title2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String backgroundLight = getBackgroundLight();
        String backgroundLight2 = navigationButton.getBackgroundLight();
        if (backgroundLight != backgroundLight2) {
            if (backgroundLight == null) {
                return -1;
            }
            if (backgroundLight2 == null) {
                return 1;
            }
            int iCompareTo4 = backgroundLight.compareTo(backgroundLight2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof NavigationButton)) {
            return false;
        }
        NavigationButton navigationButton = (NavigationButton) obj;
        return super.equals(obj) && internalEqualityCheck(getBackgroundDark(), navigationButton.getBackgroundDark()) && internalEqualityCheck(getTarget(), navigationButton.getTarget()) && internalEqualityCheck(getTitle(), navigationButton.getTitle()) && internalEqualityCheck(getBackgroundLight(), navigationButton.getBackgroundLight());
    }

    public String getBackgroundDark() {
        return this.backgroundDark;
    }

    public String getBackgroundLight() {
        return this.backgroundLight;
    }

    public String getTarget() {
        return this.target;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBackgroundDark(), getTarget(), getTitle(), getBackgroundLight());
    }

    public void setBackgroundDark(String str) {
        this.backgroundDark = str;
    }

    public void setBackgroundLight(String str) {
        this.backgroundLight = str;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
