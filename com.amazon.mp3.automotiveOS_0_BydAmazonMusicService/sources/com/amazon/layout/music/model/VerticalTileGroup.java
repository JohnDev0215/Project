package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class VerticalTileGroup extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.VerticalTileGroup");
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
        if (!(block instanceof VerticalTileGroup)) {
            return 1;
        }
        VerticalTileGroup verticalTileGroup = (VerticalTileGroup) block;
        Hint hint = getHint();
        Hint hint2 = verticalTileGroup.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo = hint.compareTo(hint2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String moreTarget = getMoreTarget();
        String moreTarget2 = verticalTileGroup.getMoreTarget();
        if (moreTarget != moreTarget2) {
            if (moreTarget == null) {
                return -1;
            }
            if (moreTarget2 == null) {
                return 1;
            }
            int iCompareTo2 = moreTarget.compareTo(moreTarget2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = verticalTileGroup.getTitle();
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
        String moreText = getMoreText();
        String moreText2 = verticalTileGroup.getMoreText();
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
        String subTitle = getSubTitle();
        String subTitle2 = verticalTileGroup.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo5 = subTitle.compareTo(subTitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof VerticalTileGroup)) {
            return false;
        }
        VerticalTileGroup verticalTileGroup = (VerticalTileGroup) obj;
        return super.equals(obj) && internalEqualityCheck(getHint(), verticalTileGroup.getHint()) && internalEqualityCheck(getMoreTarget(), verticalTileGroup.getMoreTarget()) && internalEqualityCheck(getTitle(), verticalTileGroup.getTitle()) && internalEqualityCheck(getMoreText(), verticalTileGroup.getMoreText()) && internalEqualityCheck(getSubTitle(), verticalTileGroup.getSubTitle());
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHint(), getMoreTarget(), getTitle(), getMoreText(), getSubTitle());
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
