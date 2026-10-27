package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationToggle extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.NavigationToggle");
    private Hint hint;
    private String image;
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
        if (!(block instanceof NavigationToggle)) {
            return 1;
        }
        NavigationToggle navigationToggle = (NavigationToggle) block;
        String title = getTitle();
        String title2 = navigationToggle.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = navigationToggle.getImage();
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
        String subTitle = getSubTitle();
        String subTitle2 = navigationToggle.getSubTitle();
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
        Hint hint = getHint();
        Hint hint2 = navigationToggle.getHint();
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
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof NavigationToggle)) {
            return false;
        }
        NavigationToggle navigationToggle = (NavigationToggle) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), navigationToggle.getTitle()) && internalEqualityCheck(getImage(), navigationToggle.getImage()) && internalEqualityCheck(getSubTitle(), navigationToggle.getSubTitle()) && internalEqualityCheck(getHint(), navigationToggle.getHint());
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getImage() {
        return this.image;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getImage(), getSubTitle(), getHint());
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
