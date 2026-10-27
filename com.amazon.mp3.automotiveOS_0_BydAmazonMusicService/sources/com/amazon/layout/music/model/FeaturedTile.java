package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class FeaturedTile extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.FeaturedTile");
    private String header;
    private Hint hint;
    private String image;
    private String logo;
    private String subHeader;
    private String subTitle;
    private String text;
    private String tileTarget;
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
        if (!(block instanceof FeaturedTile)) {
            return 1;
        }
        FeaturedTile featuredTile = (FeaturedTile) block;
        String image = getImage();
        String image2 = featuredTile.getImage();
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
        String subTitle = getSubTitle();
        String subTitle2 = featuredTile.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = subTitle.compareTo(subTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String header = getHeader();
        String header2 = featuredTile.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo3 = header.compareTo(header2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String subHeader = getSubHeader();
        String subHeader2 = featuredTile.getSubHeader();
        if (subHeader != subHeader2) {
            if (subHeader == null) {
                return -1;
            }
            if (subHeader2 == null) {
                return 1;
            }
            int iCompareTo4 = subHeader.compareTo(subHeader2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = featuredTile.getTitle();
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
        String logo = getLogo();
        String logo2 = featuredTile.getLogo();
        if (logo != logo2) {
            if (logo == null) {
                return -1;
            }
            if (logo2 == null) {
                return 1;
            }
            int iCompareTo6 = logo.compareTo(logo2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String text = getText();
        String text2 = featuredTile.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo7 = text.compareTo(text2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Hint hint = getHint();
        Hint hint2 = featuredTile.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo8 = hint.compareTo(hint2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String tileTarget = getTileTarget();
        String tileTarget2 = featuredTile.getTileTarget();
        if (tileTarget != tileTarget2) {
            if (tileTarget == null) {
                return -1;
            }
            if (tileTarget2 == null) {
                return 1;
            }
            int iCompareTo9 = tileTarget.compareTo(tileTarget2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedTile)) {
            return false;
        }
        FeaturedTile featuredTile = (FeaturedTile) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), featuredTile.getImage()) && internalEqualityCheck(getSubTitle(), featuredTile.getSubTitle()) && internalEqualityCheck(getHeader(), featuredTile.getHeader()) && internalEqualityCheck(getSubHeader(), featuredTile.getSubHeader()) && internalEqualityCheck(getTitle(), featuredTile.getTitle()) && internalEqualityCheck(getLogo(), featuredTile.getLogo()) && internalEqualityCheck(getText(), featuredTile.getText()) && internalEqualityCheck(getHint(), featuredTile.getHint()) && internalEqualityCheck(getTileTarget(), featuredTile.getTileTarget());
    }

    public String getHeader() {
        return this.header;
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getImage() {
        return this.image;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getSubHeader() {
        return this.subHeader;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getText() {
        return this.text;
    }

    public String getTileTarget() {
        return this.tileTarget;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), getSubTitle(), getHeader(), getSubHeader(), getTitle(), getLogo(), getText(), getHint(), getTileTarget());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public void setSubHeader(String str) {
        this.subHeader = str;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setTileTarget(String str) {
        this.tileTarget = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
