package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class HorizontalTileGrid extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.HorizontalTileGrid");
    private Hint hint;
    private String moreTarget;
    private String moreText;
    private String subTitle;
    private String title;

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
        if (!(block instanceof HorizontalTileGrid)) {
            return 1;
        }
        HorizontalTileGrid horizontalTileGrid = (HorizontalTileGrid) block;
        String title = getTitle();
        String title2 = horizontalTileGrid.getTitle();
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
        String subTitle = getSubTitle();
        String subTitle2 = horizontalTileGrid.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = subTitle.compareTo(subTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String moreTarget = getMoreTarget();
        String moreTarget2 = horizontalTileGrid.getMoreTarget();
        if (moreTarget != moreTarget2) {
            if (moreTarget == null) {
                return -1;
            }
            if (moreTarget2 == null) {
                return 1;
            }
            int iCompareTo3 = moreTarget.compareTo(moreTarget2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String moreText = getMoreText();
        String moreText2 = horizontalTileGrid.getMoreText();
        if (moreText != moreText2) {
            if (moreText == null) {
                return -1;
            }
            if (moreText2 == null) {
                return 1;
            }
            int iCompareTo4 = moreText.compareTo(moreText2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Hint hint = getHint();
        Hint hint2 = horizontalTileGrid.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo5 = hint.compareTo(hint2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof HorizontalTileGrid)) {
            return false;
        }
        HorizontalTileGrid horizontalTileGrid = (HorizontalTileGrid) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), horizontalTileGrid.getTitle()) && internalEqualityCheck(getSubTitle(), horizontalTileGrid.getSubTitle()) && internalEqualityCheck(getMoreTarget(), horizontalTileGrid.getMoreTarget()) && internalEqualityCheck(getMoreText(), horizontalTileGrid.getMoreText()) && internalEqualityCheck(getHint(), horizontalTileGrid.getHint());
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getMoreTarget() {
        return this.moreTarget;
    }

    public String getMoreText() {
        return this.moreText;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getSubTitle(), getMoreTarget(), getMoreText(), getHint());
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setMoreTarget(String str) {
        this.moreTarget = str;
    }

    public void setMoreText(String str) {
        this.moreText = str;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
