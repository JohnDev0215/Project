package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class HorizontalTile extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.HorizontalTile");
    private Hint hint;
    private String image;
    private String playbackTarget;
    private String subTitle;
    private String subTitleTarget;
    private String text;
    private String tileTarget;
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
        if (!(block instanceof HorizontalTile)) {
            return 1;
        }
        HorizontalTile horizontalTile = (HorizontalTile) block;
        String subTitle = getSubTitle();
        String subTitle2 = horizontalTile.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo = subTitle.compareTo(subTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String tileTarget = getTileTarget();
        String tileTarget2 = horizontalTile.getTileTarget();
        if (tileTarget != tileTarget2) {
            if (tileTarget == null) {
                return -1;
            }
            if (tileTarget2 == null) {
                return 1;
            }
            int iCompareTo2 = tileTarget.compareTo(tileTarget2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String text = getText();
        String text2 = horizontalTile.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo3 = text.compareTo(text2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String image = getImage();
        String image2 = horizontalTile.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo4 = image.compareTo(image2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String playbackTarget = getPlaybackTarget();
        String playbackTarget2 = horizontalTile.getPlaybackTarget();
        if (playbackTarget != playbackTarget2) {
            if (playbackTarget == null) {
                return -1;
            }
            if (playbackTarget2 == null) {
                return 1;
            }
            int iCompareTo5 = playbackTarget.compareTo(playbackTarget2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String subTitleTarget = getSubTitleTarget();
        String subTitleTarget2 = horizontalTile.getSubTitleTarget();
        if (subTitleTarget != subTitleTarget2) {
            if (subTitleTarget == null) {
                return -1;
            }
            if (subTitleTarget2 == null) {
                return 1;
            }
            int iCompareTo6 = subTitleTarget.compareTo(subTitleTarget2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String titleTarget = getTitleTarget();
        String titleTarget2 = horizontalTile.getTitleTarget();
        if (titleTarget != titleTarget2) {
            if (titleTarget == null) {
                return -1;
            }
            if (titleTarget2 == null) {
                return 1;
            }
            int iCompareTo7 = titleTarget.compareTo(titleTarget2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String title = getTitle();
        String title2 = horizontalTile.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo8 = title.compareTo(title2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Hint hint = getHint();
        Hint hint2 = horizontalTile.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo9 = hint.compareTo(hint2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof HorizontalTile)) {
            return false;
        }
        HorizontalTile horizontalTile = (HorizontalTile) obj;
        return super.equals(obj) && internalEqualityCheck(getSubTitle(), horizontalTile.getSubTitle()) && internalEqualityCheck(getTileTarget(), horizontalTile.getTileTarget()) && internalEqualityCheck(getText(), horizontalTile.getText()) && internalEqualityCheck(getImage(), horizontalTile.getImage()) && internalEqualityCheck(getPlaybackTarget(), horizontalTile.getPlaybackTarget()) && internalEqualityCheck(getSubTitleTarget(), horizontalTile.getSubTitleTarget()) && internalEqualityCheck(getTitleTarget(), horizontalTile.getTitleTarget()) && internalEqualityCheck(getTitle(), horizontalTile.getTitle()) && internalEqualityCheck(getHint(), horizontalTile.getHint());
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getImage() {
        return this.image;
    }

    public String getPlaybackTarget() {
        return this.playbackTarget;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getSubTitleTarget() {
        return this.subTitleTarget;
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

    public String getTitleTarget() {
        return this.titleTarget;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubTitle(), getTileTarget(), getText(), getImage(), getPlaybackTarget(), getSubTitleTarget(), getTitleTarget(), getTitle(), getHint());
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setPlaybackTarget(String str) {
        this.playbackTarget = str;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setSubTitleTarget(String str) {
        this.subTitleTarget = str;
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

    public void setTitleTarget(String str) {
        this.titleTarget = str;
    }
}
