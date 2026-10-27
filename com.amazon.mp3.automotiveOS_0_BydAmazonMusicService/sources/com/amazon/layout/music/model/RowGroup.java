package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class RowGroup extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.RowGroup");
    private String image;
    private String imageSubtitle;
    private String imageTarget;
    private String imageTitle;

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
        if (!(block instanceof RowGroup)) {
            return 1;
        }
        RowGroup rowGroup = (RowGroup) block;
        String imageSubtitle = getImageSubtitle();
        String imageSubtitle2 = rowGroup.getImageSubtitle();
        if (imageSubtitle != imageSubtitle2) {
            if (imageSubtitle == null) {
                return -1;
            }
            if (imageSubtitle2 == null) {
                return 1;
            }
            int iCompareTo = imageSubtitle.compareTo(imageSubtitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String imageTarget = getImageTarget();
        String imageTarget2 = rowGroup.getImageTarget();
        if (imageTarget != imageTarget2) {
            if (imageTarget == null) {
                return -1;
            }
            if (imageTarget2 == null) {
                return 1;
            }
            int iCompareTo2 = imageTarget.compareTo(imageTarget2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String image = getImage();
        String image2 = rowGroup.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo3 = image.compareTo(image2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String imageTitle = getImageTitle();
        String imageTitle2 = rowGroup.getImageTitle();
        if (imageTitle != imageTitle2) {
            if (imageTitle == null) {
                return -1;
            }
            if (imageTitle2 == null) {
                return 1;
            }
            int iCompareTo4 = imageTitle.compareTo(imageTitle2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof RowGroup)) {
            return false;
        }
        RowGroup rowGroup = (RowGroup) obj;
        return super.equals(obj) && internalEqualityCheck(getImageSubtitle(), rowGroup.getImageSubtitle()) && internalEqualityCheck(getImageTarget(), rowGroup.getImageTarget()) && internalEqualityCheck(getImage(), rowGroup.getImage()) && internalEqualityCheck(getImageTitle(), rowGroup.getImageTitle());
    }

    public String getImage() {
        return this.image;
    }

    public String getImageSubtitle() {
        return this.imageSubtitle;
    }

    public String getImageTarget() {
        return this.imageTarget;
    }

    public String getImageTitle() {
        return this.imageTitle;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImageSubtitle(), getImageTarget(), getImage(), getImageTitle());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setImageSubtitle(String str) {
        this.imageSubtitle = str;
    }

    public void setImageTarget(String str) {
        this.imageTarget = str;
    }

    public void setImageTitle(String str) {
        this.imageTitle = str;
    }
}
