package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Barker extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Barker");
    private String altDescription;
    private String blocksTitle;
    private List<Image> images;
    private String target;

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
        if (!(block instanceof Barker)) {
            return 1;
        }
        Barker barker = (Barker) block;
        String altDescription = getAltDescription();
        String altDescription2 = barker.getAltDescription();
        if (altDescription != altDescription2) {
            if (altDescription == null) {
                return -1;
            }
            if (altDescription2 == null) {
                return 1;
            }
            int iCompareTo = altDescription.compareTo(altDescription2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String blocksTitle = getBlocksTitle();
        String blocksTitle2 = barker.getBlocksTitle();
        if (blocksTitle != blocksTitle2) {
            if (blocksTitle == null) {
                return -1;
            }
            if (blocksTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = blocksTitle.compareTo(blocksTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String target = getTarget();
        String target2 = barker.getTarget();
        if (target != target2) {
            if (target == null) {
                return -1;
            }
            if (target2 == null) {
                return 1;
            }
            int iCompareTo3 = target.compareTo(target2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Image> images = getImages();
        List<Image> images2 = barker.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo4 = ((Comparable) images).compareTo(images2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!images.equals(images2)) {
                int iHashCode = images.hashCode();
                int iHashCode2 = images2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof Barker)) {
            return false;
        }
        Barker barker = (Barker) obj;
        return super.equals(obj) && internalEqualityCheck(getAltDescription(), barker.getAltDescription()) && internalEqualityCheck(getBlocksTitle(), barker.getBlocksTitle()) && internalEqualityCheck(getTarget(), barker.getTarget()) && internalEqualityCheck(getImages(), barker.getImages());
    }

    public String getAltDescription() {
        return this.altDescription;
    }

    public String getBlocksTitle() {
        return this.blocksTitle;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public String getTarget() {
        return this.target;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAltDescription(), getBlocksTitle(), getTarget(), getImages());
    }

    public void setAltDescription(String str) {
        this.altDescription = str;
    }

    public void setBlocksTitle(String str) {
        this.blocksTitle = str;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }

    public void setTarget(String str) {
        this.target = str;
    }
}
