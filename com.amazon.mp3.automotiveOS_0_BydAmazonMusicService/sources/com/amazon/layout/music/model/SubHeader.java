package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SubHeader extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.SubHeader");
    private String image;
    private String title;

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
        if (!(block instanceof SubHeader)) {
            return 1;
        }
        SubHeader subHeader = (SubHeader) block;
        String image = getImage();
        String image2 = subHeader.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo = image.compareTo(image2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = subHeader.getTitle();
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
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof SubHeader)) {
            return false;
        }
        SubHeader subHeader = (SubHeader) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), subHeader.getImage()) && internalEqualityCheck(getTitle(), subHeader.getTitle());
    }

    public String getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), getTitle());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
