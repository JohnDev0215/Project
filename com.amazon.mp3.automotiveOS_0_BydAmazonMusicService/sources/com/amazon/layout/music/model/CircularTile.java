package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class CircularTile extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.CircularTile");
    private String circularTileTarget;
    private Hint hint;
    private String image;
    private String imageTarget;
    private String title;
    private String titleTarget;

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
        if (!(block instanceof CircularTile)) {
            return 1;
        }
        CircularTile circularTile = (CircularTile) block;
        Hint hint = getHint();
        Hint hint2 = circularTile.getHint();
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
        String titleTarget = getTitleTarget();
        String titleTarget2 = circularTile.getTitleTarget();
        if (titleTarget != titleTarget2) {
            if (titleTarget == null) {
                return -1;
            }
            if (titleTarget2 == null) {
                return 1;
            }
            int iCompareTo2 = titleTarget.compareTo(titleTarget2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String image = getImage();
        String image2 = circularTile.getImage();
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
        String circularTileTarget = getCircularTileTarget();
        String circularTileTarget2 = circularTile.getCircularTileTarget();
        if (circularTileTarget != circularTileTarget2) {
            if (circularTileTarget == null) {
                return -1;
            }
            if (circularTileTarget2 == null) {
                return 1;
            }
            int iCompareTo4 = circularTileTarget.compareTo(circularTileTarget2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = circularTile.getTitle();
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
        String imageTarget = getImageTarget();
        String imageTarget2 = circularTile.getImageTarget();
        if (imageTarget != imageTarget2) {
            if (imageTarget == null) {
                return -1;
            }
            if (imageTarget2 == null) {
                return 1;
            }
            int iCompareTo6 = imageTarget.compareTo(imageTarget2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof CircularTile)) {
            return false;
        }
        CircularTile circularTile = (CircularTile) obj;
        return super.equals(obj) && internalEqualityCheck(getHint(), circularTile.getHint()) && internalEqualityCheck(getTitleTarget(), circularTile.getTitleTarget()) && internalEqualityCheck(getImage(), circularTile.getImage()) && internalEqualityCheck(getCircularTileTarget(), circularTile.getCircularTileTarget()) && internalEqualityCheck(getTitle(), circularTile.getTitle()) && internalEqualityCheck(getImageTarget(), circularTile.getImageTarget());
    }

    public String getCircularTileTarget() {
        return this.circularTileTarget;
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getImage() {
        return this.image;
    }

    public String getImageTarget() {
        return this.imageTarget;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleTarget() {
        return this.titleTarget;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHint(), getTitleTarget(), getImage(), getCircularTileTarget(), getTitle(), getImageTarget());
    }

    public void setCircularTileTarget(String str) {
        this.circularTileTarget = str;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setImageTarget(String str) {
        this.imageTarget = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleTarget(String str) {
        this.titleTarget = str;
    }
}
