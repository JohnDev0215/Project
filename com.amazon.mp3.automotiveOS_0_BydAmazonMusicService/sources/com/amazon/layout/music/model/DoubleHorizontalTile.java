package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DoubleHorizontalTile extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.DoubleHorizontalTile");
    private String decorator1;
    private String decorator2;
    private Map<String, String> image1;
    private Map<String, String> image2;
    private Map<String, String> image3;
    private Hint programHint;
    private String subtitle;
    private String subtitleIcon;
    private String surtitle;
    private String target;
    private Hint timerHint;
    private String title;
    private String title1;
    private String title2;
    private Hint topicHint;

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
        if (!(block instanceof DoubleHorizontalTile)) {
            return 1;
        }
        DoubleHorizontalTile doubleHorizontalTile = (DoubleHorizontalTile) block;
        String title2 = getTitle2();
        String title22 = doubleHorizontalTile.getTitle2();
        if (title2 != title22) {
            if (title2 == null) {
                return -1;
            }
            if (title22 == null) {
                return 1;
            }
            int iCompareTo = title2.compareTo(title22);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Hint topicHint = getTopicHint();
        Hint topicHint2 = doubleHorizontalTile.getTopicHint();
        if (topicHint != topicHint2) {
            if (topicHint == null) {
                return -1;
            }
            if (topicHint2 == null) {
                return 1;
            }
            int iCompareTo2 = topicHint.compareTo(topicHint2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Map<String, String> image2 = getImage2();
        Map<String, String> image22 = doubleHorizontalTile.getImage2();
        if (image2 != image22) {
            if (image2 == null) {
                return -1;
            }
            if (image22 == null) {
                return 1;
            }
            if (image2 instanceof Comparable) {
                int iCompareTo3 = ((Comparable) image2).compareTo(image22);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!image2.equals(image22)) {
                int iHashCode = image2.hashCode();
                int iHashCode2 = image22.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title3 = doubleHorizontalTile.getTitle();
        if (title != title3) {
            if (title == null) {
                return -1;
            }
            if (title3 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title3);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String decorator2 = getDecorator2();
        String decorator22 = doubleHorizontalTile.getDecorator2();
        if (decorator2 != decorator22) {
            if (decorator2 == null) {
                return -1;
            }
            if (decorator22 == null) {
                return 1;
            }
            int iCompareTo5 = decorator2.compareTo(decorator22);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String surtitle = getSurtitle();
        String surtitle2 = doubleHorizontalTile.getSurtitle();
        if (surtitle != surtitle2) {
            if (surtitle == null) {
                return -1;
            }
            if (surtitle2 == null) {
                return 1;
            }
            int iCompareTo6 = surtitle.compareTo(surtitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String target = getTarget();
        String target2 = doubleHorizontalTile.getTarget();
        if (target != target2) {
            if (target == null) {
                return -1;
            }
            if (target2 == null) {
                return 1;
            }
            int iCompareTo7 = target.compareTo(target2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Map<String, String> image1 = getImage1();
        Map<String, String> image12 = doubleHorizontalTile.getImage1();
        if (image1 != image12) {
            if (image1 == null) {
                return -1;
            }
            if (image12 == null) {
                return 1;
            }
            if (image1 instanceof Comparable) {
                int iCompareTo8 = ((Comparable) image1).compareTo(image12);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!image1.equals(image12)) {
                int iHashCode3 = image1.hashCode();
                int iHashCode4 = image12.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = doubleHorizontalTile.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo9 = subtitle.compareTo(subtitle2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String title1 = getTitle1();
        String title12 = doubleHorizontalTile.getTitle1();
        if (title1 != title12) {
            if (title1 == null) {
                return -1;
            }
            if (title12 == null) {
                return 1;
            }
            int iCompareTo10 = title1.compareTo(title12);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Map<String, String> image3 = getImage3();
        Map<String, String> image32 = doubleHorizontalTile.getImage3();
        if (image3 != image32) {
            if (image3 == null) {
                return -1;
            }
            if (image32 == null) {
                return 1;
            }
            if (image3 instanceof Comparable) {
                int iCompareTo11 = ((Comparable) image3).compareTo(image32);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!image3.equals(image32)) {
                int iHashCode5 = image3.hashCode();
                int iHashCode6 = image32.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        Hint programHint = getProgramHint();
        Hint programHint2 = doubleHorizontalTile.getProgramHint();
        if (programHint != programHint2) {
            if (programHint == null) {
                return -1;
            }
            if (programHint2 == null) {
                return 1;
            }
            int iCompareTo12 = programHint.compareTo(programHint2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Hint timerHint = getTimerHint();
        Hint timerHint2 = doubleHorizontalTile.getTimerHint();
        if (timerHint != timerHint2) {
            if (timerHint == null) {
                return -1;
            }
            if (timerHint2 == null) {
                return 1;
            }
            int iCompareTo13 = timerHint.compareTo(timerHint2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String decorator1 = getDecorator1();
        String decorator12 = doubleHorizontalTile.getDecorator1();
        if (decorator1 != decorator12) {
            if (decorator1 == null) {
                return -1;
            }
            if (decorator12 == null) {
                return 1;
            }
            int iCompareTo14 = decorator1.compareTo(decorator12);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String subtitleIcon = getSubtitleIcon();
        String subtitleIcon2 = doubleHorizontalTile.getSubtitleIcon();
        if (subtitleIcon != subtitleIcon2) {
            if (subtitleIcon == null) {
                return -1;
            }
            if (subtitleIcon2 == null) {
                return 1;
            }
            int iCompareTo15 = subtitleIcon.compareTo(subtitleIcon2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof DoubleHorizontalTile)) {
            return false;
        }
        DoubleHorizontalTile doubleHorizontalTile = (DoubleHorizontalTile) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle2(), doubleHorizontalTile.getTitle2()) && internalEqualityCheck(getTopicHint(), doubleHorizontalTile.getTopicHint()) && internalEqualityCheck(getImage2(), doubleHorizontalTile.getImage2()) && internalEqualityCheck(getTitle(), doubleHorizontalTile.getTitle()) && internalEqualityCheck(getDecorator2(), doubleHorizontalTile.getDecorator2()) && internalEqualityCheck(getSurtitle(), doubleHorizontalTile.getSurtitle()) && internalEqualityCheck(getTarget(), doubleHorizontalTile.getTarget()) && internalEqualityCheck(getImage1(), doubleHorizontalTile.getImage1()) && internalEqualityCheck(getSubtitle(), doubleHorizontalTile.getSubtitle()) && internalEqualityCheck(getTitle1(), doubleHorizontalTile.getTitle1()) && internalEqualityCheck(getImage3(), doubleHorizontalTile.getImage3()) && internalEqualityCheck(getProgramHint(), doubleHorizontalTile.getProgramHint()) && internalEqualityCheck(getTimerHint(), doubleHorizontalTile.getTimerHint()) && internalEqualityCheck(getDecorator1(), doubleHorizontalTile.getDecorator1()) && internalEqualityCheck(getSubtitleIcon(), doubleHorizontalTile.getSubtitleIcon());
    }

    public String getDecorator1() {
        return this.decorator1;
    }

    public String getDecorator2() {
        return this.decorator2;
    }

    public Map<String, String> getImage1() {
        return this.image1;
    }

    public Map<String, String> getImage2() {
        return this.image2;
    }

    public Map<String, String> getImage3() {
        return this.image3;
    }

    public Hint getProgramHint() {
        return this.programHint;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getSubtitleIcon() {
        return this.subtitleIcon;
    }

    public String getSurtitle() {
        return this.surtitle;
    }

    public String getTarget() {
        return this.target;
    }

    public Hint getTimerHint() {
        return this.timerHint;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitle1() {
        return this.title1;
    }

    public String getTitle2() {
        return this.title2;
    }

    public Hint getTopicHint() {
        return this.topicHint;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle2(), getTopicHint(), getImage2(), getTitle(), getDecorator2(), getSurtitle(), getTarget(), getImage1(), getSubtitle(), getTitle1(), getImage3(), getProgramHint(), getTimerHint(), getDecorator1(), getSubtitleIcon());
    }

    public void setDecorator1(String str) {
        this.decorator1 = str;
    }

    public void setDecorator2(String str) {
        this.decorator2 = str;
    }

    public void setImage1(Map<String, String> map) {
        this.image1 = map;
    }

    public void setImage2(Map<String, String> map) {
        this.image2 = map;
    }

    public void setImage3(Map<String, String> map) {
        this.image3 = map;
    }

    public void setProgramHint(Hint hint) {
        this.programHint = hint;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setSubtitleIcon(String str) {
        this.subtitleIcon = str;
    }

    public void setSurtitle(String str) {
        this.surtitle = str;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public void setTimerHint(Hint hint) {
        this.timerHint = hint;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitle1(String str) {
        this.title1 = str;
    }

    public void setTitle2(String str) {
        this.title2 = str;
    }

    public void setTopicHint(Hint hint) {
        this.topicHint = hint;
    }
}
