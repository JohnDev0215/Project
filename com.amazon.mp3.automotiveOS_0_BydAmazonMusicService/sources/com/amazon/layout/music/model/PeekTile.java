package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class PeekTile extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.PeekTile");
    private Hint hint;
    private String image;
    private String imageSubtitle1;
    private String imageSubtitle2;
    private String imageTarget1;
    private String imageTarget2;
    private String imageTitle;
    private String imageTitleTarget;

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
        if (!(block instanceof PeekTile)) {
            return 1;
        }
        PeekTile peekTile = (PeekTile) block;
        String imageTitleTarget = getImageTitleTarget();
        String imageTitleTarget2 = peekTile.getImageTitleTarget();
        if (imageTitleTarget != imageTitleTarget2) {
            if (imageTitleTarget == null) {
                return -1;
            }
            if (imageTitleTarget2 == null) {
                return 1;
            }
            int iCompareTo = imageTitleTarget.compareTo(imageTitleTarget2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String imageTitle = getImageTitle();
        String imageTitle2 = peekTile.getImageTitle();
        if (imageTitle != imageTitle2) {
            if (imageTitle == null) {
                return -1;
            }
            if (imageTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = imageTitle.compareTo(imageTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String imageTarget2 = getImageTarget2();
        String imageTarget22 = peekTile.getImageTarget2();
        if (imageTarget2 != imageTarget22) {
            if (imageTarget2 == null) {
                return -1;
            }
            if (imageTarget22 == null) {
                return 1;
            }
            int iCompareTo3 = imageTarget2.compareTo(imageTarget22);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Hint hint = getHint();
        Hint hint2 = peekTile.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo4 = hint.compareTo(hint2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String imageTarget1 = getImageTarget1();
        String imageTarget12 = peekTile.getImageTarget1();
        if (imageTarget1 != imageTarget12) {
            if (imageTarget1 == null) {
                return -1;
            }
            if (imageTarget12 == null) {
                return 1;
            }
            int iCompareTo5 = imageTarget1.compareTo(imageTarget12);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String imageSubtitle1 = getImageSubtitle1();
        String imageSubtitle12 = peekTile.getImageSubtitle1();
        if (imageSubtitle1 != imageSubtitle12) {
            if (imageSubtitle1 == null) {
                return -1;
            }
            if (imageSubtitle12 == null) {
                return 1;
            }
            int iCompareTo6 = imageSubtitle1.compareTo(imageSubtitle12);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String imageSubtitle2 = getImageSubtitle2();
        String imageSubtitle22 = peekTile.getImageSubtitle2();
        if (imageSubtitle2 != imageSubtitle22) {
            if (imageSubtitle2 == null) {
                return -1;
            }
            if (imageSubtitle22 == null) {
                return 1;
            }
            int iCompareTo7 = imageSubtitle2.compareTo(imageSubtitle22);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String image = getImage();
        String image2 = peekTile.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo8 = image.compareTo(image2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof PeekTile)) {
            return false;
        }
        PeekTile peekTile = (PeekTile) obj;
        return super.equals(obj) && internalEqualityCheck(getImageTitleTarget(), peekTile.getImageTitleTarget()) && internalEqualityCheck(getImageTitle(), peekTile.getImageTitle()) && internalEqualityCheck(getImageTarget2(), peekTile.getImageTarget2()) && internalEqualityCheck(getHint(), peekTile.getHint()) && internalEqualityCheck(getImageTarget1(), peekTile.getImageTarget1()) && internalEqualityCheck(getImageSubtitle1(), peekTile.getImageSubtitle1()) && internalEqualityCheck(getImageSubtitle2(), peekTile.getImageSubtitle2()) && internalEqualityCheck(getImage(), peekTile.getImage());
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getImage() {
        return this.image;
    }

    public String getImageSubtitle1() {
        return this.imageSubtitle1;
    }

    public String getImageSubtitle2() {
        return this.imageSubtitle2;
    }

    public String getImageTarget1() {
        return this.imageTarget1;
    }

    public String getImageTarget2() {
        return this.imageTarget2;
    }

    public String getImageTitle() {
        return this.imageTitle;
    }

    public String getImageTitleTarget() {
        return this.imageTitleTarget;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImageTitleTarget(), getImageTitle(), getImageTarget2(), getHint(), getImageTarget1(), getImageSubtitle1(), getImageSubtitle2(), getImage());
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setImageSubtitle1(String str) {
        this.imageSubtitle1 = str;
    }

    public void setImageSubtitle2(String str) {
        this.imageSubtitle2 = str;
    }

    public void setImageTarget1(String str) {
        this.imageTarget1 = str;
    }

    public void setImageTarget2(String str) {
        this.imageTarget2 = str;
    }

    public void setImageTitle(String str) {
        this.imageTitle = str;
    }

    public void setImageTitleTarget(String str) {
        this.imageTitleTarget = str;
    }
}
