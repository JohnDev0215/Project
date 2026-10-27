package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AboutElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.AboutElement");
    private String description;
    private String image;
    private NegativeButtonElement negativeButton;
    private List<Method> onViewed;
    private List<CompactPillItemElement> pills;
    private PlayButtonElement playButton;
    private PositiveButtonElement positiveButton;
    private String subtitle;
    private String title;

    public static class Builder extends TemplateElement.Builder {
        protected String description;
        protected String image;
        protected NegativeButtonElement negativeButton;
        protected List<Method> onViewed;
        protected List<CompactPillItemElement> pills;
        protected PlayButtonElement playButton;
        protected PositiveButtonElement positiveButton;
        protected String subtitle;
        protected String title;

        public AboutElement build() {
            AboutElement aboutElement = new AboutElement();
            populate(aboutElement);
            return aboutElement;
        }

        protected void populate(AboutElement aboutElement) {
            super.populate((TemplateElement) aboutElement);
            aboutElement.setDescription(this.description);
            aboutElement.setOnViewed(this.onViewed);
            aboutElement.setNegativeButton(this.negativeButton);
            aboutElement.setTitle(this.title);
            aboutElement.setImage(this.image);
            aboutElement.setSubtitle(this.subtitle);
            aboutElement.setPills(this.pills);
            aboutElement.setPlayButton(this.playButton);
            aboutElement.setPositiveButton(this.positiveButton);
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withNegativeButton(NegativeButtonElement negativeButtonElement) {
            this.negativeButton = negativeButtonElement;
            return this;
        }

        public Builder withOnViewed(List<Method> list) {
            this.onViewed = list;
            return this;
        }

        public Builder withPills(List<CompactPillItemElement> list) {
            this.pills = list;
            return this;
        }

        public Builder withPlayButton(PlayButtonElement playButtonElement) {
            this.playButton = playButtonElement;
            return this;
        }

        public Builder withPositiveButton(PositiveButtonElement positiveButtonElement) {
            this.positiveButton = positiveButtonElement;
            return this;
        }

        public Builder withSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof AboutElement)) {
            return 1;
        }
        AboutElement aboutElement = (AboutElement) sOAObject;
        String description = getDescription();
        String description2 = aboutElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo = description.compareTo(description2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = aboutElement.getOnViewed();
        if (onViewed != onViewed2) {
            if (onViewed == null) {
                return -1;
            }
            if (onViewed2 == null) {
                return 1;
            }
            if (onViewed instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onViewed).compareTo(onViewed2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onViewed.equals(onViewed2)) {
                int iHashCode = onViewed.hashCode();
                int iHashCode2 = onViewed2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        NegativeButtonElement negativeButton = getNegativeButton();
        NegativeButtonElement negativeButton2 = aboutElement.getNegativeButton();
        if (negativeButton != negativeButton2) {
            if (negativeButton == null) {
                return -1;
            }
            if (negativeButton2 == null) {
                return 1;
            }
            int iCompareTo3 = negativeButton.compareTo(negativeButton2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title = getTitle();
        String title2 = aboutElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String image = getImage();
        String image2 = aboutElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo5 = image.compareTo(image2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = aboutElement.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo6 = subtitle.compareTo(subtitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<CompactPillItemElement> pills = getPills();
        List<CompactPillItemElement> pills2 = aboutElement.getPills();
        if (pills != pills2) {
            if (pills == null) {
                return -1;
            }
            if (pills2 == null) {
                return 1;
            }
            if (pills instanceof Comparable) {
                int iCompareTo7 = ((Comparable) pills).compareTo(pills2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!pills.equals(pills2)) {
                int iHashCode3 = pills.hashCode();
                int iHashCode4 = pills2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        PlayButtonElement playButton = getPlayButton();
        PlayButtonElement playButton2 = aboutElement.getPlayButton();
        if (playButton != playButton2) {
            if (playButton == null) {
                return -1;
            }
            if (playButton2 == null) {
                return 1;
            }
            int iCompareTo8 = playButton.compareTo(playButton2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        PositiveButtonElement positiveButton = getPositiveButton();
        PositiveButtonElement positiveButton2 = aboutElement.getPositiveButton();
        if (positiveButton != positiveButton2) {
            if (positiveButton == null) {
                return -1;
            }
            if (positiveButton2 == null) {
                return 1;
            }
            int iCompareTo9 = positiveButton.compareTo(positiveButton2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AboutElement)) {
            return false;
        }
        AboutElement aboutElement = (AboutElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDescription(), aboutElement.getDescription()) && internalEqualityCheck(getOnViewed(), aboutElement.getOnViewed()) && internalEqualityCheck(getNegativeButton(), aboutElement.getNegativeButton()) && internalEqualityCheck(getTitle(), aboutElement.getTitle()) && internalEqualityCheck(getImage(), aboutElement.getImage()) && internalEqualityCheck(getSubtitle(), aboutElement.getSubtitle()) && internalEqualityCheck(getPills(), aboutElement.getPills()) && internalEqualityCheck(getPlayButton(), aboutElement.getPlayButton()) && internalEqualityCheck(getPositiveButton(), aboutElement.getPositiveButton());
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public NegativeButtonElement getNegativeButton() {
        return this.negativeButton;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    public List<CompactPillItemElement> getPills() {
        return this.pills;
    }

    public PlayButtonElement getPlayButton() {
        return this.playButton;
    }

    public PositiveButtonElement getPositiveButton() {
        return this.positiveButton;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDescription(), getOnViewed(), getNegativeButton(), getTitle(), getImage(), getSubtitle(), getPills(), getPlayButton(), getPositiveButton());
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setNegativeButton(NegativeButtonElement negativeButtonElement) {
        this.negativeButton = negativeButtonElement;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }

    public void setPills(List<CompactPillItemElement> list) {
        this.pills = list;
    }

    public void setPlayButton(PlayButtonElement playButtonElement) {
        this.playButton = playButtonElement;
    }

    public void setPositiveButton(PositiveButtonElement positiveButtonElement) {
        this.positiveButton = positiveButtonElement;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
