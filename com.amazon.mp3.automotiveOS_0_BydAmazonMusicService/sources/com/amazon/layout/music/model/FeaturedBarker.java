package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FeaturedBarker extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.FeaturedBarker");
    private String altDescription;
    private List<Image> backgroundImages;
    private String backgroundTarget;
    private String squareImage;
    private String squareTarget;
    private String subTitle;
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
        if (!(block instanceof FeaturedBarker)) {
            return 1;
        }
        FeaturedBarker featuredBarker = (FeaturedBarker) block;
        String backgroundTarget = getBackgroundTarget();
        String backgroundTarget2 = featuredBarker.getBackgroundTarget();
        if (backgroundTarget != backgroundTarget2) {
            if (backgroundTarget == null) {
                return -1;
            }
            if (backgroundTarget2 == null) {
                return 1;
            }
            int iCompareTo = backgroundTarget.compareTo(backgroundTarget2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String squareImage = getSquareImage();
        String squareImage2 = featuredBarker.getSquareImage();
        if (squareImage != squareImage2) {
            if (squareImage == null) {
                return -1;
            }
            if (squareImage2 == null) {
                return 1;
            }
            int iCompareTo2 = squareImage.compareTo(squareImage2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = featuredBarker.getTitle();
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
        String altDescription = getAltDescription();
        String altDescription2 = featuredBarker.getAltDescription();
        if (altDescription != altDescription2) {
            if (altDescription == null) {
                return -1;
            }
            if (altDescription2 == null) {
                return 1;
            }
            int iCompareTo4 = altDescription.compareTo(altDescription2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Image> backgroundImages = getBackgroundImages();
        List<Image> backgroundImages2 = featuredBarker.getBackgroundImages();
        if (backgroundImages != backgroundImages2) {
            if (backgroundImages == null) {
                return -1;
            }
            if (backgroundImages2 == null) {
                return 1;
            }
            if (backgroundImages instanceof Comparable) {
                int iCompareTo5 = ((Comparable) backgroundImages).compareTo(backgroundImages2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!backgroundImages.equals(backgroundImages2)) {
                int iHashCode = backgroundImages.hashCode();
                int iHashCode2 = backgroundImages2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String subTitle = getSubTitle();
        String subTitle2 = featuredBarker.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo6 = subTitle.compareTo(subTitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String squareTarget = getSquareTarget();
        String squareTarget2 = featuredBarker.getSquareTarget();
        if (squareTarget != squareTarget2) {
            if (squareTarget == null) {
                return -1;
            }
            if (squareTarget2 == null) {
                return 1;
            }
            int iCompareTo7 = squareTarget.compareTo(squareTarget2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedBarker)) {
            return false;
        }
        FeaturedBarker featuredBarker = (FeaturedBarker) obj;
        return super.equals(obj) && internalEqualityCheck(getBackgroundTarget(), featuredBarker.getBackgroundTarget()) && internalEqualityCheck(getSquareImage(), featuredBarker.getSquareImage()) && internalEqualityCheck(getTitle(), featuredBarker.getTitle()) && internalEqualityCheck(getAltDescription(), featuredBarker.getAltDescription()) && internalEqualityCheck(getBackgroundImages(), featuredBarker.getBackgroundImages()) && internalEqualityCheck(getSubTitle(), featuredBarker.getSubTitle()) && internalEqualityCheck(getSquareTarget(), featuredBarker.getSquareTarget());
    }

    public String getAltDescription() {
        return this.altDescription;
    }

    public List<Image> getBackgroundImages() {
        return this.backgroundImages;
    }

    public String getBackgroundTarget() {
        return this.backgroundTarget;
    }

    public String getSquareImage() {
        return this.squareImage;
    }

    public String getSquareTarget() {
        return this.squareTarget;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBackgroundTarget(), getSquareImage(), getTitle(), getAltDescription(), getBackgroundImages(), getSubTitle(), getSquareTarget());
    }

    public void setAltDescription(String str) {
        this.altDescription = str;
    }

    public void setBackgroundImages(List<Image> list) {
        this.backgroundImages = list;
    }

    public void setBackgroundTarget(String str) {
        this.backgroundTarget = str;
    }

    public void setSquareImage(String str) {
        this.squareImage = str;
    }

    public void setSquareTarget(String str) {
        this.squareTarget = str;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
