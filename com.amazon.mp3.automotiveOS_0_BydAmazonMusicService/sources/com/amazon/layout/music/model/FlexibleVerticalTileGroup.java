package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class FlexibleVerticalTileGroup extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.FlexibleVerticalTileGroup");
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
        if (!(block instanceof FlexibleVerticalTileGroup)) {
            return 1;
        }
        FlexibleVerticalTileGroup flexibleVerticalTileGroup = (FlexibleVerticalTileGroup) block;
        String moreTarget = getMoreTarget();
        String moreTarget2 = flexibleVerticalTileGroup.getMoreTarget();
        if (moreTarget != moreTarget2) {
            if (moreTarget == null) {
                return -1;
            }
            if (moreTarget2 == null) {
                return 1;
            }
            int iCompareTo = moreTarget.compareTo(moreTarget2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Hint hint = getHint();
        Hint hint2 = flexibleVerticalTileGroup.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo2 = hint.compareTo(hint2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String subTitle = getSubTitle();
        String subTitle2 = flexibleVerticalTileGroup.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo3 = subTitle.compareTo(subTitle2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String moreText = getMoreText();
        String moreText2 = flexibleVerticalTileGroup.getMoreText();
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
        String title = getTitle();
        String title2 = flexibleVerticalTileGroup.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof FlexibleVerticalTileGroup)) {
            return false;
        }
        FlexibleVerticalTileGroup flexibleVerticalTileGroup = (FlexibleVerticalTileGroup) obj;
        return super.equals(obj) && internalEqualityCheck(getMoreTarget(), flexibleVerticalTileGroup.getMoreTarget()) && internalEqualityCheck(getHint(), flexibleVerticalTileGroup.getHint()) && internalEqualityCheck(getSubTitle(), flexibleVerticalTileGroup.getSubTitle()) && internalEqualityCheck(getMoreText(), flexibleVerticalTileGroup.getMoreText()) && internalEqualityCheck(getTitle(), flexibleVerticalTileGroup.getTitle());
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMoreTarget(), getHint(), getSubTitle(), getMoreText(), getTitle());
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
