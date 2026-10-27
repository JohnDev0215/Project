package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SingleList extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.SingleList");
    private String image;
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
        if (!(block instanceof SingleList)) {
            return 1;
        }
        SingleList singleList = (SingleList) block;
        String moreText = getMoreText();
        String moreText2 = singleList.getMoreText();
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
        String title = getTitle();
        String title2 = singleList.getTitle();
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
        String moreTarget = getMoreTarget();
        String moreTarget2 = singleList.getMoreTarget();
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
        String subTitle = getSubTitle();
        String subTitle2 = singleList.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo4 = subTitle.compareTo(subTitle2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String image = getImage();
        String image2 = singleList.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo5 = image.compareTo(image2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof SingleList)) {
            return false;
        }
        SingleList singleList = (SingleList) obj;
        return super.equals(obj) && internalEqualityCheck(getMoreText(), singleList.getMoreText()) && internalEqualityCheck(getTitle(), singleList.getTitle()) && internalEqualityCheck(getMoreTarget(), singleList.getMoreTarget()) && internalEqualityCheck(getSubTitle(), singleList.getSubTitle()) && internalEqualityCheck(getImage(), singleList.getImage());
    }

    public String getImage() {
        return this.image;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMoreText(), getTitle(), getMoreTarget(), getSubTitle(), getImage());
    }

    public void setImage(String str) {
        this.image = str;
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
