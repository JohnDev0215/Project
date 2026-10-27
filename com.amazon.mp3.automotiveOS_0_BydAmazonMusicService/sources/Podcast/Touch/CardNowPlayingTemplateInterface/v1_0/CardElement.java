package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CardElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElement");
    private String description;
    private EpisodeOptionsElement episodeOptions;
    private FollowButtonElement follow;
    private String id;
    private String image;
    private List<Method> onAboutSelected;
    private List<Method> onCardCentered;
    private List<Method> onCardSelected;
    private List<Method> onCardViewed;
    private List<Method> onPauseSelected;
    private List<Method> onPlaySelected;
    private List<Method> onSwipeNext;
    private List<Method> onSwipePrevious;
    private List<Method> onTapNext;
    private List<Method> onTapPrevious;
    private ButtonElement primaryButton;
    private String primaryText;
    private SaveButtonElement save;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String description;
        protected EpisodeOptionsElement episodeOptions;
        protected FollowButtonElement follow;
        protected String id;
        protected String image;
        protected List<Method> onAboutSelected;
        protected List<Method> onCardCentered;
        protected List<Method> onCardSelected;
        protected List<Method> onCardViewed;
        protected List<Method> onPauseSelected;
        protected List<Method> onPlaySelected;
        protected List<Method> onSwipeNext;
        protected List<Method> onSwipePrevious;
        protected List<Method> onTapNext;
        protected List<Method> onTapPrevious;
        protected ButtonElement primaryButton;
        protected String primaryText;
        protected SaveButtonElement save;
        protected String secondaryText;

        public CardElement build() {
            CardElement cardElement = new CardElement();
            populate(cardElement);
            return cardElement;
        }

        protected void populate(CardElement cardElement) {
            super.populate((TemplateElement) cardElement);
            cardElement.setDescription(this.description);
            cardElement.setFollow(this.follow);
            cardElement.setOnCardViewed(this.onCardViewed);
            cardElement.setOnPauseSelected(this.onPauseSelected);
            cardElement.setOnCardCentered(this.onCardCentered);
            cardElement.setOnSwipeNext(this.onSwipeNext);
            cardElement.setOnTapNext(this.onTapNext);
            cardElement.setSave(this.save);
            cardElement.setOnAboutSelected(this.onAboutSelected);
            cardElement.setOnPlaySelected(this.onPlaySelected);
            cardElement.setPrimaryButton(this.primaryButton);
            cardElement.setEpisodeOptions(this.episodeOptions);
            cardElement.setSecondaryText(this.secondaryText);
            cardElement.setOnSwipePrevious(this.onSwipePrevious);
            cardElement.setOnTapPrevious(this.onTapPrevious);
            cardElement.setImage(this.image);
            cardElement.setPrimaryText(this.primaryText);
            cardElement.setId(this.id);
            cardElement.setOnCardSelected(this.onCardSelected);
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
            this.episodeOptions = episodeOptionsElement;
            return this;
        }

        public Builder withFollow(FollowButtonElement followButtonElement) {
            this.follow = followButtonElement;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withOnAboutSelected(List<Method> list) {
            this.onAboutSelected = list;
            return this;
        }

        public Builder withOnCardCentered(List<Method> list) {
            this.onCardCentered = list;
            return this;
        }

        public Builder withOnCardSelected(List<Method> list) {
            this.onCardSelected = list;
            return this;
        }

        public Builder withOnCardViewed(List<Method> list) {
            this.onCardViewed = list;
            return this;
        }

        public Builder withOnPauseSelected(List<Method> list) {
            this.onPauseSelected = list;
            return this;
        }

        public Builder withOnPlaySelected(List<Method> list) {
            this.onPlaySelected = list;
            return this;
        }

        public Builder withOnSwipeNext(List<Method> list) {
            this.onSwipeNext = list;
            return this;
        }

        public Builder withOnSwipePrevious(List<Method> list) {
            this.onSwipePrevious = list;
            return this;
        }

        public Builder withOnTapNext(List<Method> list) {
            this.onTapNext = list;
            return this;
        }

        public Builder withOnTapPrevious(List<Method> list) {
            this.onTapPrevious = list;
            return this;
        }

        public Builder withPrimaryButton(ButtonElement buttonElement) {
            this.primaryButton = buttonElement;
            return this;
        }

        public Builder withPrimaryText(String str) {
            this.primaryText = str;
            return this;
        }

        public Builder withSave(SaveButtonElement saveButtonElement) {
            this.save = saveButtonElement;
            return this;
        }

        public Builder withSecondaryText(String str) {
            this.secondaryText = str;
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
        if (!(sOAObject instanceof CardElement)) {
            return 1;
        }
        CardElement cardElement = (CardElement) sOAObject;
        String description = getDescription();
        String description2 = cardElement.getDescription();
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
        FollowButtonElement follow = getFollow();
        FollowButtonElement follow2 = cardElement.getFollow();
        if (follow != follow2) {
            if (follow == null) {
                return -1;
            }
            if (follow2 == null) {
                return 1;
            }
            int iCompareTo2 = follow.compareTo(follow2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onCardViewed = getOnCardViewed();
        List<Method> onCardViewed2 = cardElement.getOnCardViewed();
        if (onCardViewed != onCardViewed2) {
            if (onCardViewed == null) {
                return -1;
            }
            if (onCardViewed2 == null) {
                return 1;
            }
            if (onCardViewed instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onCardViewed).compareTo(onCardViewed2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onCardViewed.equals(onCardViewed2)) {
                int iHashCode = onCardViewed.hashCode();
                int iHashCode2 = onCardViewed2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onPauseSelected = getOnPauseSelected();
        List<Method> onPauseSelected2 = cardElement.getOnPauseSelected();
        if (onPauseSelected != onPauseSelected2) {
            if (onPauseSelected == null) {
                return -1;
            }
            if (onPauseSelected2 == null) {
                return 1;
            }
            if (onPauseSelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onPauseSelected).compareTo(onPauseSelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onPauseSelected.equals(onPauseSelected2)) {
                int iHashCode3 = onPauseSelected.hashCode();
                int iHashCode4 = onPauseSelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Method> onCardCentered = getOnCardCentered();
        List<Method> onCardCentered2 = cardElement.getOnCardCentered();
        if (onCardCentered != onCardCentered2) {
            if (onCardCentered == null) {
                return -1;
            }
            if (onCardCentered2 == null) {
                return 1;
            }
            if (onCardCentered instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onCardCentered).compareTo(onCardCentered2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onCardCentered.equals(onCardCentered2)) {
                int iHashCode5 = onCardCentered.hashCode();
                int iHashCode6 = onCardCentered2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<Method> onSwipeNext = getOnSwipeNext();
        List<Method> onSwipeNext2 = cardElement.getOnSwipeNext();
        if (onSwipeNext != onSwipeNext2) {
            if (onSwipeNext == null) {
                return -1;
            }
            if (onSwipeNext2 == null) {
                return 1;
            }
            if (onSwipeNext instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onSwipeNext).compareTo(onSwipeNext2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onSwipeNext.equals(onSwipeNext2)) {
                int iHashCode7 = onSwipeNext.hashCode();
                int iHashCode8 = onSwipeNext2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<Method> onTapNext = getOnTapNext();
        List<Method> onTapNext2 = cardElement.getOnTapNext();
        if (onTapNext != onTapNext2) {
            if (onTapNext == null) {
                return -1;
            }
            if (onTapNext2 == null) {
                return 1;
            }
            if (onTapNext instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onTapNext).compareTo(onTapNext2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onTapNext.equals(onTapNext2)) {
                int iHashCode9 = onTapNext.hashCode();
                int iHashCode10 = onTapNext2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        SaveButtonElement save = getSave();
        SaveButtonElement save2 = cardElement.getSave();
        if (save != save2) {
            if (save == null) {
                return -1;
            }
            if (save2 == null) {
                return 1;
            }
            int iCompareTo8 = save.compareTo(save2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<Method> onAboutSelected = getOnAboutSelected();
        List<Method> onAboutSelected2 = cardElement.getOnAboutSelected();
        if (onAboutSelected != onAboutSelected2) {
            if (onAboutSelected == null) {
                return -1;
            }
            if (onAboutSelected2 == null) {
                return 1;
            }
            if (onAboutSelected instanceof Comparable) {
                int iCompareTo9 = ((Comparable) onAboutSelected).compareTo(onAboutSelected2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!onAboutSelected.equals(onAboutSelected2)) {
                int iHashCode11 = onAboutSelected.hashCode();
                int iHashCode12 = onAboutSelected2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = cardElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo10 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!onPlaySelected.equals(onPlaySelected2)) {
                int iHashCode13 = onPlaySelected.hashCode();
                int iHashCode14 = onPlaySelected2.hashCode();
                if (iHashCode13 < iHashCode14) {
                    return -1;
                }
                if (iHashCode13 > iHashCode14) {
                    return 1;
                }
            }
        }
        ButtonElement primaryButton = getPrimaryButton();
        ButtonElement primaryButton2 = cardElement.getPrimaryButton();
        if (primaryButton != primaryButton2) {
            if (primaryButton == null) {
                return -1;
            }
            if (primaryButton2 == null) {
                return 1;
            }
            int iCompareTo11 = primaryButton.compareTo(primaryButton2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = cardElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo12 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = cardElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo13 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        List<Method> onSwipePrevious = getOnSwipePrevious();
        List<Method> onSwipePrevious2 = cardElement.getOnSwipePrevious();
        if (onSwipePrevious != onSwipePrevious2) {
            if (onSwipePrevious == null) {
                return -1;
            }
            if (onSwipePrevious2 == null) {
                return 1;
            }
            if (onSwipePrevious instanceof Comparable) {
                int iCompareTo14 = ((Comparable) onSwipePrevious).compareTo(onSwipePrevious2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!onSwipePrevious.equals(onSwipePrevious2)) {
                int iHashCode15 = onSwipePrevious.hashCode();
                int iHashCode16 = onSwipePrevious2.hashCode();
                if (iHashCode15 < iHashCode16) {
                    return -1;
                }
                if (iHashCode15 > iHashCode16) {
                    return 1;
                }
            }
        }
        List<Method> onTapPrevious = getOnTapPrevious();
        List<Method> onTapPrevious2 = cardElement.getOnTapPrevious();
        if (onTapPrevious != onTapPrevious2) {
            if (onTapPrevious == null) {
                return -1;
            }
            if (onTapPrevious2 == null) {
                return 1;
            }
            if (onTapPrevious instanceof Comparable) {
                int iCompareTo15 = ((Comparable) onTapPrevious).compareTo(onTapPrevious2);
                if (iCompareTo15 != 0) {
                    return iCompareTo15;
                }
            } else if (!onTapPrevious.equals(onTapPrevious2)) {
                int iHashCode17 = onTapPrevious.hashCode();
                int iHashCode18 = onTapPrevious2.hashCode();
                if (iHashCode17 < iHashCode18) {
                    return -1;
                }
                if (iHashCode17 > iHashCode18) {
                    return 1;
                }
            }
        }
        String image = getImage();
        String image2 = cardElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo16 = image.compareTo(image2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = cardElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo17 = primaryText.compareTo(primaryText2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String id = getId();
        String id2 = cardElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo18 = id.compareTo(id2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        List<Method> onCardSelected = getOnCardSelected();
        List<Method> onCardSelected2 = cardElement.getOnCardSelected();
        if (onCardSelected != onCardSelected2) {
            if (onCardSelected == null) {
                return -1;
            }
            if (onCardSelected2 == null) {
                return 1;
            }
            if (onCardSelected instanceof Comparable) {
                int iCompareTo19 = ((Comparable) onCardSelected).compareTo(onCardSelected2);
                if (iCompareTo19 != 0) {
                    return iCompareTo19;
                }
            } else if (!onCardSelected.equals(onCardSelected2)) {
                int iHashCode19 = onCardSelected.hashCode();
                int iHashCode20 = onCardSelected2.hashCode();
                if (iHashCode19 < iHashCode20) {
                    return -1;
                }
                if (iHashCode19 > iHashCode20) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof CardElement)) {
            return false;
        }
        CardElement cardElement = (CardElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDescription(), cardElement.getDescription()) && internalEqualityCheck(getFollow(), cardElement.getFollow()) && internalEqualityCheck(getOnCardViewed(), cardElement.getOnCardViewed()) && internalEqualityCheck(getOnPauseSelected(), cardElement.getOnPauseSelected()) && internalEqualityCheck(getOnCardCentered(), cardElement.getOnCardCentered()) && internalEqualityCheck(getOnSwipeNext(), cardElement.getOnSwipeNext()) && internalEqualityCheck(getOnTapNext(), cardElement.getOnTapNext()) && internalEqualityCheck(getSave(), cardElement.getSave()) && internalEqualityCheck(getOnAboutSelected(), cardElement.getOnAboutSelected()) && internalEqualityCheck(getOnPlaySelected(), cardElement.getOnPlaySelected()) && internalEqualityCheck(getPrimaryButton(), cardElement.getPrimaryButton()) && internalEqualityCheck(getEpisodeOptions(), cardElement.getEpisodeOptions()) && internalEqualityCheck(getSecondaryText(), cardElement.getSecondaryText()) && internalEqualityCheck(getOnSwipePrevious(), cardElement.getOnSwipePrevious()) && internalEqualityCheck(getOnTapPrevious(), cardElement.getOnTapPrevious()) && internalEqualityCheck(getImage(), cardElement.getImage()) && internalEqualityCheck(getPrimaryText(), cardElement.getPrimaryText()) && internalEqualityCheck(getId(), cardElement.getId()) && internalEqualityCheck(getOnCardSelected(), cardElement.getOnCardSelected());
    }

    public String getDescription() {
        return this.description;
    }

    public EpisodeOptionsElement getEpisodeOptions() {
        return this.episodeOptions;
    }

    public FollowButtonElement getFollow() {
        return this.follow;
    }

    public String getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnAboutSelected() {
        return this.onAboutSelected;
    }

    public List<Method> getOnCardCentered() {
        return this.onCardCentered;
    }

    public List<Method> getOnCardSelected() {
        return this.onCardSelected;
    }

    public List<Method> getOnCardViewed() {
        return this.onCardViewed;
    }

    public List<Method> getOnPauseSelected() {
        return this.onPauseSelected;
    }

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    public List<Method> getOnSwipeNext() {
        return this.onSwipeNext;
    }

    public List<Method> getOnSwipePrevious() {
        return this.onSwipePrevious;
    }

    public List<Method> getOnTapNext() {
        return this.onTapNext;
    }

    public List<Method> getOnTapPrevious() {
        return this.onTapPrevious;
    }

    public ButtonElement getPrimaryButton() {
        return this.primaryButton;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public SaveButtonElement getSave() {
        return this.save;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDescription(), getFollow(), getOnCardViewed(), getOnPauseSelected(), getOnCardCentered(), getOnSwipeNext(), getOnTapNext(), getSave(), getOnAboutSelected(), getOnPlaySelected(), getPrimaryButton(), getEpisodeOptions(), getSecondaryText(), getOnSwipePrevious(), getOnTapPrevious(), getImage(), getPrimaryText(), getId(), getOnCardSelected());
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
        this.episodeOptions = episodeOptionsElement;
    }

    public void setFollow(FollowButtonElement followButtonElement) {
        this.follow = followButtonElement;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setOnAboutSelected(List<Method> list) {
        this.onAboutSelected = list;
    }

    public void setOnCardCentered(List<Method> list) {
        this.onCardCentered = list;
    }

    public void setOnCardSelected(List<Method> list) {
        this.onCardSelected = list;
    }

    public void setOnCardViewed(List<Method> list) {
        this.onCardViewed = list;
    }

    public void setOnPauseSelected(List<Method> list) {
        this.onPauseSelected = list;
    }

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }

    public void setOnSwipeNext(List<Method> list) {
        this.onSwipeNext = list;
    }

    public void setOnSwipePrevious(List<Method> list) {
        this.onSwipePrevious = list;
    }

    public void setOnTapNext(List<Method> list) {
        this.onTapNext = list;
    }

    public void setOnTapPrevious(List<Method> list) {
        this.onTapPrevious = list;
    }

    public void setPrimaryButton(ButtonElement buttonElement) {
        this.primaryButton = buttonElement;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSave(SaveButtonElement saveButtonElement) {
        this.save = saveButtonElement;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }
}
