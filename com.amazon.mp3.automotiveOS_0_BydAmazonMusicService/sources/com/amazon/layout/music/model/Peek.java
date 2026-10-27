package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class Peek extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Peek");
    private String expandText;
    private String expandTextTarget;
    private String moreTarget;
    private String moreText;
    private String subTitle;
    private Block tile;
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
        if (!(block instanceof Peek)) {
            return 1;
        }
        Peek peek = (Peek) block;
        String subTitle = getSubTitle();
        String subTitle2 = peek.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo = subTitle.compareTo(subTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Block tile = getTile();
        Block tile2 = peek.getTile();
        if (tile != tile2) {
            if (tile == null) {
                return -1;
            }
            if (tile2 == null) {
                return 1;
            }
            int iCompareTo2 = tile.compareTo(tile2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = peek.getTitle();
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
        String expandText = getExpandText();
        String expandText2 = peek.getExpandText();
        if (expandText != expandText2) {
            if (expandText == null) {
                return -1;
            }
            if (expandText2 == null) {
                return 1;
            }
            int iCompareTo4 = expandText.compareTo(expandText2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String expandTextTarget = getExpandTextTarget();
        String expandTextTarget2 = peek.getExpandTextTarget();
        if (expandTextTarget != expandTextTarget2) {
            if (expandTextTarget == null) {
                return -1;
            }
            if (expandTextTarget2 == null) {
                return 1;
            }
            int iCompareTo5 = expandTextTarget.compareTo(expandTextTarget2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String moreText = getMoreText();
        String moreText2 = peek.getMoreText();
        if (moreText != moreText2) {
            if (moreText == null) {
                return -1;
            }
            if (moreText2 == null) {
                return 1;
            }
            int iCompareTo6 = moreText.compareTo(moreText2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String moreTarget = getMoreTarget();
        String moreTarget2 = peek.getMoreTarget();
        if (moreTarget != moreTarget2) {
            if (moreTarget == null) {
                return -1;
            }
            if (moreTarget2 == null) {
                return 1;
            }
            int iCompareTo7 = moreTarget.compareTo(moreTarget2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof Peek)) {
            return false;
        }
        Peek peek = (Peek) obj;
        return super.equals(obj) && internalEqualityCheck(getSubTitle(), peek.getSubTitle()) && internalEqualityCheck(getTile(), peek.getTile()) && internalEqualityCheck(getTitle(), peek.getTitle()) && internalEqualityCheck(getExpandText(), peek.getExpandText()) && internalEqualityCheck(getExpandTextTarget(), peek.getExpandTextTarget()) && internalEqualityCheck(getMoreText(), peek.getMoreText()) && internalEqualityCheck(getMoreTarget(), peek.getMoreTarget());
    }

    public String getExpandText() {
        return this.expandText;
    }

    public String getExpandTextTarget() {
        return this.expandTextTarget;
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

    public Block getTile() {
        return this.tile;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubTitle(), getTile(), getTitle(), getExpandText(), getExpandTextTarget(), getMoreText(), getMoreTarget());
    }

    public void setExpandText(String str) {
        this.expandText = str;
    }

    public void setExpandTextTarget(String str) {
        this.expandTextTarget = str;
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

    public void setTile(Block block) {
        this.tile = block;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
