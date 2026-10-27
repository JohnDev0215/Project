package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class GalleryTile extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.GalleryTile");
    private String image;
    private String imageTarget;

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
        if (!(block instanceof GalleryTile)) {
            return 1;
        }
        GalleryTile galleryTile = (GalleryTile) block;
        String imageTarget = getImageTarget();
        String imageTarget2 = galleryTile.getImageTarget();
        if (imageTarget != imageTarget2) {
            if (imageTarget == null) {
                return -1;
            }
            if (imageTarget2 == null) {
                return 1;
            }
            int iCompareTo = imageTarget.compareTo(imageTarget2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = galleryTile.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo2 = image.compareTo(image2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof GalleryTile)) {
            return false;
        }
        GalleryTile galleryTile = (GalleryTile) obj;
        return super.equals(obj) && internalEqualityCheck(getImageTarget(), galleryTile.getImageTarget()) && internalEqualityCheck(getImage(), galleryTile.getImage());
    }

    public String getImage() {
        return this.image;
    }

    public String getImageTarget() {
        return this.imageTarget;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImageTarget(), getImage());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setImageTarget(String str) {
        this.imageTarget = str;
    }
}
