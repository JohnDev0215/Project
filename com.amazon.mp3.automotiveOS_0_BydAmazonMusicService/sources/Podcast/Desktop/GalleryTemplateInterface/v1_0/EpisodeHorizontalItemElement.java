package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeHorizontalItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalItemElement");
    private String altText;
    private EpisodeOptionsElement episodeOptions;
    private String image;
    private Boolean isDisabled;
    private List<Method> onImageSelected;
    private List<Method> onItemSelected;
    private String primaryText;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String altText;
        protected EpisodeOptionsElement episodeOptions;
        protected String image;
        protected Boolean isDisabled;
        protected List<Method> onImageSelected;
        protected List<Method> onItemSelected;
        protected String primaryText;
        protected String secondaryText;

        public EpisodeHorizontalItemElement build() {
            EpisodeHorizontalItemElement episodeHorizontalItemElement = new EpisodeHorizontalItemElement();
            populate(episodeHorizontalItemElement);
            return episodeHorizontalItemElement;
        }

        protected void populate(EpisodeHorizontalItemElement episodeHorizontalItemElement) {
            super.populate((TemplateElement) episodeHorizontalItemElement);
            episodeHorizontalItemElement.setImage(this.image);
            episodeHorizontalItemElement.setPrimaryText(this.primaryText);
            episodeHorizontalItemElement.setAltText(this.altText);
            episodeHorizontalItemElement.setOnImageSelected(this.onImageSelected);
            episodeHorizontalItemElement.setEpisodeOptions(this.episodeOptions);
            episodeHorizontalItemElement.setIsDisabled(this.isDisabled);
            episodeHorizontalItemElement.setSecondaryText(this.secondaryText);
            episodeHorizontalItemElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withAltText(String str) {
            this.altText = str;
            return this;
        }

        public Builder withEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
            this.episodeOptions = episodeOptionsElement;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withIsDisabled(Boolean bool) {
            this.isDisabled = bool;
            return this;
        }

        public Builder withOnImageSelected(List<Method> list) {
            this.onImageSelected = list;
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
        if (!(sOAObject instanceof EpisodeHorizontalItemElement)) {
            return 1;
        }
        EpisodeHorizontalItemElement episodeHorizontalItemElement = (EpisodeHorizontalItemElement) sOAObject;
        String image = getImage();
        String image2 = episodeHorizontalItemElement.getImage();
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
        String primaryText = getPrimaryText();
        String primaryText2 = episodeHorizontalItemElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo2 = primaryText.compareTo(primaryText2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String altText = getAltText();
        String altText2 = episodeHorizontalItemElement.getAltText();
        if (altText != altText2) {
            if (altText == null) {
                return -1;
            }
            if (altText2 == null) {
                return 1;
            }
            int iCompareTo3 = altText.compareTo(altText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onImageSelected = getOnImageSelected();
        List<Method> onImageSelected2 = episodeHorizontalItemElement.getOnImageSelected();
        if (onImageSelected != onImageSelected2) {
            if (onImageSelected == null) {
                return -1;
            }
            if (onImageSelected2 == null) {
                return 1;
            }
            if (onImageSelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onImageSelected).compareTo(onImageSelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onImageSelected.equals(onImageSelected2)) {
                int iHashCode = onImageSelected.hashCode();
                int iHashCode2 = onImageSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = episodeHorizontalItemElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo5 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsIsDisabled = isIsDisabled();
        Boolean boolIsIsDisabled2 = episodeHorizontalItemElement.isIsDisabled();
        if (boolIsIsDisabled != boolIsIsDisabled2) {
            if (boolIsIsDisabled == null) {
                return -1;
            }
            if (boolIsIsDisabled2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = episodeHorizontalItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo7 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = episodeHorizontalItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo8 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode3 = onItemSelected.hashCode();
                int iHashCode4 = onItemSelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeHorizontalItemElement)) {
            return false;
        }
        EpisodeHorizontalItemElement episodeHorizontalItemElement = (EpisodeHorizontalItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), episodeHorizontalItemElement.getImage()) && internalEqualityCheck(getPrimaryText(), episodeHorizontalItemElement.getPrimaryText()) && internalEqualityCheck(getAltText(), episodeHorizontalItemElement.getAltText()) && internalEqualityCheck(getOnImageSelected(), episodeHorizontalItemElement.getOnImageSelected()) && internalEqualityCheck(getEpisodeOptions(), episodeHorizontalItemElement.getEpisodeOptions()) && internalEqualityCheck(isIsDisabled(), episodeHorizontalItemElement.isIsDisabled()) && internalEqualityCheck(getSecondaryText(), episodeHorizontalItemElement.getSecondaryText()) && internalEqualityCheck(getOnItemSelected(), episodeHorizontalItemElement.getOnItemSelected());
    }

    public String getAltText() {
        return this.altText;
    }

    public EpisodeOptionsElement getEpisodeOptions() {
        return this.episodeOptions;
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnImageSelected() {
        return this.onImageSelected;
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

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), getPrimaryText(), getAltText(), getOnImageSelected(), getEpisodeOptions(), isIsDisabled(), getSecondaryText(), getOnItemSelected());
    }

    public Boolean isIsDisabled() {
        return this.isDisabled;
    }

    public void setAltText(String str) {
        this.altText = str;
    }

    public void setEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
        this.episodeOptions = episodeOptionsElement;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setIsDisabled(Boolean bool) {
        this.isDisabled = bool;
    }

    public void setOnImageSelected(List<Method> list) {
        this.onImageSelected = list;
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
}
