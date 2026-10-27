package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class Portfolio extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Portfolio");
    private String moreTarget;
    private String moreText;
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
        if (!(block instanceof Portfolio)) {
            return 1;
        }
        Portfolio portfolio = (Portfolio) block;
        String moreText = getMoreText();
        String moreText2 = portfolio.getMoreText();
        if (moreText != moreText2) {
            if (moreText == null) {
                return -1;
            }
            if (moreText2 == null) {
                return 1;
            }
            int iCompareTo = moreText.compareTo(moreText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String moreTarget = getMoreTarget();
        String moreTarget2 = portfolio.getMoreTarget();
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
        String title2 = portfolio.getTitle();
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
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof Portfolio)) {
            return false;
        }
        Portfolio portfolio = (Portfolio) obj;
        return super.equals(obj) && internalEqualityCheck(getMoreText(), portfolio.getMoreText()) && internalEqualityCheck(getMoreTarget(), portfolio.getMoreTarget()) && internalEqualityCheck(getTitle(), portfolio.getTitle());
    }

    public String getMoreTarget() {
        return this.moreTarget;
    }

    public String getMoreText() {
        return this.moreText;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMoreText(), getMoreTarget(), getTitle());
    }

    public void setMoreTarget(String str) {
        this.moreTarget = str;
    }

    public void setMoreText(String str) {
        this.moreText = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
