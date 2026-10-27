package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeFeaturedPlayElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElement");
    private String backgroundImage;
    private String foregroundImage;
    private String label;
    private List<Method> onItemSelected;
    private EpisodePlayElement play;
    private String primaryText;
    private String secondaryText;
    private String tertiaryText;
    private String title;

    public static class Builder extends WidgetElement.Builder {
        protected String backgroundImage;
        protected String foregroundImage;
        protected String label;
        protected List<Method> onItemSelected;
        protected EpisodePlayElement play;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;
        protected String title;

        public EpisodeFeaturedPlayElement build() {
            EpisodeFeaturedPlayElement episodeFeaturedPlayElement = new EpisodeFeaturedPlayElement();
            populate(episodeFeaturedPlayElement);
            return episodeFeaturedPlayElement;
        }

        protected void populate(EpisodeFeaturedPlayElement episodeFeaturedPlayElement) {
            super.populate((WidgetElement) episodeFeaturedPlayElement);
            episodeFeaturedPlayElement.setTitle(this.title);
            episodeFeaturedPlayElement.setPrimaryText(this.primaryText);
            episodeFeaturedPlayElement.setLabel(this.label);
            episodeFeaturedPlayElement.setBackgroundImage(this.backgroundImage);
            episodeFeaturedPlayElement.setTertiaryText(this.tertiaryText);
            episodeFeaturedPlayElement.setSecondaryText(this.secondaryText);
            episodeFeaturedPlayElement.setOnItemSelected(this.onItemSelected);
            episodeFeaturedPlayElement.setForegroundImage(this.foregroundImage);
            episodeFeaturedPlayElement.setPlay(this.play);
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        public Builder withForegroundImage(String str) {
            this.foregroundImage = str;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        public Builder withPlay(EpisodePlayElement episodePlayElement) {
            this.play = episodePlayElement;
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
    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof EpisodeFeaturedPlayElement)) {
            return 1;
        }
        EpisodeFeaturedPlayElement episodeFeaturedPlayElement = (EpisodeFeaturedPlayElement) sOAObject;
        String title = getTitle();
        String title2 = episodeFeaturedPlayElement.getTitle();
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
        String primaryText = getPrimaryText();
        String primaryText2 = episodeFeaturedPlayElement.getPrimaryText();
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
        String label = getLabel();
        String label2 = episodeFeaturedPlayElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo3 = label.compareTo(label2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = episodeFeaturedPlayElement.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo4 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = episodeFeaturedPlayElement.getTertiaryText();
        if (tertiaryText != tertiaryText2) {
            if (tertiaryText == null) {
                return -1;
            }
            if (tertiaryText2 == null) {
                return 1;
            }
            int iCompareTo5 = tertiaryText.compareTo(tertiaryText2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = episodeFeaturedPlayElement.getSecondaryText();
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
        List<Method> onItemSelected2 = episodeFeaturedPlayElement.getOnItemSelected();
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
        String foregroundImage = getForegroundImage();
        String foregroundImage2 = episodeFeaturedPlayElement.getForegroundImage();
        if (foregroundImage != foregroundImage2) {
            if (foregroundImage == null) {
                return -1;
            }
            if (foregroundImage2 == null) {
                return 1;
            }
            int iCompareTo8 = foregroundImage.compareTo(foregroundImage2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        EpisodePlayElement play = getPlay();
        EpisodePlayElement play2 = episodeFeaturedPlayElement.getPlay();
        if (play != play2) {
            if (play == null) {
                return -1;
            }
            if (play2 == null) {
                return 1;
            }
            int iCompareTo9 = play.compareTo(play2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeFeaturedPlayElement)) {
            return false;
        }
        EpisodeFeaturedPlayElement episodeFeaturedPlayElement = (EpisodeFeaturedPlayElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), episodeFeaturedPlayElement.getTitle()) && internalEqualityCheck(getPrimaryText(), episodeFeaturedPlayElement.getPrimaryText()) && internalEqualityCheck(getLabel(), episodeFeaturedPlayElement.getLabel()) && internalEqualityCheck(getBackgroundImage(), episodeFeaturedPlayElement.getBackgroundImage()) && internalEqualityCheck(getTertiaryText(), episodeFeaturedPlayElement.getTertiaryText()) && internalEqualityCheck(getSecondaryText(), episodeFeaturedPlayElement.getSecondaryText()) && internalEqualityCheck(getOnItemSelected(), episodeFeaturedPlayElement.getOnItemSelected()) && internalEqualityCheck(getForegroundImage(), episodeFeaturedPlayElement.getForegroundImage()) && internalEqualityCheck(getPlay(), episodeFeaturedPlayElement.getPlay());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public String getForegroundImage() {
        return this.foregroundImage;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public EpisodePlayElement getPlay() {
        return this.play;
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

    public String getTitle() {
        return this.title;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getPrimaryText(), getLabel(), getBackgroundImage(), getTertiaryText(), getSecondaryText(), getOnItemSelected(), getForegroundImage(), getPlay());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setForegroundImage(String str) {
        this.foregroundImage = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPlay(EpisodePlayElement episodePlayElement) {
        this.play = episodePlayElement;
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

    public void setTitle(String str) {
        this.title = str;
    }
}
