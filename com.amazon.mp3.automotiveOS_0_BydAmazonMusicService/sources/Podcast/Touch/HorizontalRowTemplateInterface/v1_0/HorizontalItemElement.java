package Podcast.Touch.HorizontalRowTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement");
    private BadgeElement badge;
    private FollowElement follow;
    private String image;
    private List<Method> onItemSelected;
    private String primaryText;
    private String secondaryText;
    private String tertiaryText;

    public static class Builder extends TemplateElement.Builder {
        protected BadgeElement badge;
        protected FollowElement follow;
        protected String image;
        protected List<Method> onItemSelected;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;

        public HorizontalItemElement build() {
            HorizontalItemElement horizontalItemElement = new HorizontalItemElement();
            populate(horizontalItemElement);
            return horizontalItemElement;
        }

        protected void populate(HorizontalItemElement horizontalItemElement) {
            super.populate((TemplateElement) horizontalItemElement);
            horizontalItemElement.setFollow(this.follow);
            horizontalItemElement.setTertiaryText(this.tertiaryText);
            horizontalItemElement.setBadge(this.badge);
            horizontalItemElement.setImage(this.image);
            horizontalItemElement.setPrimaryText(this.primaryText);
            horizontalItemElement.setSecondaryText(this.secondaryText);
            horizontalItemElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withBadge(BadgeElement badgeElement) {
            this.badge = badgeElement;
            return this;
        }

        public Builder withFollow(FollowElement followElement) {
            this.follow = followElement;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withPrimaryText(String str) {
            this.primaryText = str;
            return this;
        }

        public Builder withSecondaryText(String str) {
            this.secondaryText = str;
            return this;
        }

        public Builder withTertiaryText(String str) {
            this.tertiaryText = str;
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
        if (!(sOAObject instanceof HorizontalItemElement)) {
            return 1;
        }
        HorizontalItemElement horizontalItemElement = (HorizontalItemElement) sOAObject;
        FollowElement follow = getFollow();
        FollowElement follow2 = horizontalItemElement.getFollow();
        if (follow != follow2) {
            if (follow == null) {
                return -1;
            }
            if (follow2 == null) {
                return 1;
            }
            int iCompareTo = follow.compareTo(follow2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = horizontalItemElement.getTertiaryText();
        if (tertiaryText != tertiaryText2) {
            if (tertiaryText == null) {
                return -1;
            }
            if (tertiaryText2 == null) {
                return 1;
            }
            int iCompareTo2 = tertiaryText.compareTo(tertiaryText2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        BadgeElement badge = getBadge();
        BadgeElement badge2 = horizontalItemElement.getBadge();
        if (badge != badge2) {
            if (badge == null) {
                return -1;
            }
            if (badge2 == null) {
                return 1;
            }
            int iCompareTo3 = badge.compareTo(badge2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String image = getImage();
        String image2 = horizontalItemElement.getImage();
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
        String primaryText = getPrimaryText();
        String primaryText2 = horizontalItemElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo5 = primaryText.compareTo(primaryText2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = horizontalItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo6 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = horizontalItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode = onItemSelected.hashCode();
                int iHashCode2 = onItemSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof HorizontalItemElement)) {
            return false;
        }
        HorizontalItemElement horizontalItemElement = (HorizontalItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getFollow(), horizontalItemElement.getFollow()) && internalEqualityCheck(getTertiaryText(), horizontalItemElement.getTertiaryText()) && internalEqualityCheck(getBadge(), horizontalItemElement.getBadge()) && internalEqualityCheck(getImage(), horizontalItemElement.getImage()) && internalEqualityCheck(getPrimaryText(), horizontalItemElement.getPrimaryText()) && internalEqualityCheck(getSecondaryText(), horizontalItemElement.getSecondaryText()) && internalEqualityCheck(getOnItemSelected(), horizontalItemElement.getOnItemSelected());
    }

    public BadgeElement getBadge() {
        return this.badge;
    }

    public FollowElement getFollow() {
        return this.follow;
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    public String getTertiaryText() {
        return this.tertiaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getFollow(), getTertiaryText(), getBadge(), getImage(), getPrimaryText(), getSecondaryText(), getOnItemSelected());
    }

    public void setBadge(BadgeElement badgeElement) {
        this.badge = badgeElement;
    }

    public void setFollow(FollowElement followElement) {
        this.follow = followElement;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }

    public void setTertiaryText(String str) {
        this.tertiaryText = str;
    }
}
