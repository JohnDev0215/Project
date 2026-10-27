package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GridItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement");
    private AboutElement about;
    private String altText;
    private String id;
    private String image;
    private List<Method> onAboutSelected;
    private List<Method> onItemDeselected;
    private List<Method> onItemSelected;
    private String podcastShowVariantId;

    public static class Builder extends TemplateElement.Builder {
        protected AboutElement about;
        protected String altText;
        protected String id;
        protected String image;
        protected List<Method> onAboutSelected;
        protected List<Method> onItemDeselected;
        protected List<Method> onItemSelected;
        protected String podcastShowVariantId;

        public GridItemElement build() {
            GridItemElement gridItemElement = new GridItemElement();
            populate(gridItemElement);
            return gridItemElement;
        }

        protected void populate(GridItemElement gridItemElement) {
            super.populate((TemplateElement) gridItemElement);
            gridItemElement.setImage(this.image);
            gridItemElement.setOnItemDeselected(this.onItemDeselected);
            gridItemElement.setPodcastShowVariantId(this.podcastShowVariantId);
            gridItemElement.setOnItemSelected(this.onItemSelected);
            gridItemElement.setAbout(this.about);
            gridItemElement.setOnAboutSelected(this.onAboutSelected);
            gridItemElement.setId(this.id);
            gridItemElement.setAltText(this.altText);
        }

        public Builder withAbout(AboutElement aboutElement) {
            this.about = aboutElement;
            return this;
        }

        public Builder withAltText(String str) {
            this.altText = str;
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

        public Builder withOnItemDeselected(List<Method> list) {
            this.onItemDeselected = list;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withPodcastShowVariantId(String str) {
            this.podcastShowVariantId = str;
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
        if (!(sOAObject instanceof GridItemElement)) {
            return 1;
        }
        GridItemElement gridItemElement = (GridItemElement) sOAObject;
        String image = getImage();
        String image2 = gridItemElement.getImage();
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
        List<Method> onItemDeselected = getOnItemDeselected();
        List<Method> onItemDeselected2 = gridItemElement.getOnItemDeselected();
        if (onItemDeselected != onItemDeselected2) {
            if (onItemDeselected == null) {
                return -1;
            }
            if (onItemDeselected2 == null) {
                return 1;
            }
            if (onItemDeselected instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onItemDeselected).compareTo(onItemDeselected2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onItemDeselected.equals(onItemDeselected2)) {
                int iHashCode = onItemDeselected.hashCode();
                int iHashCode2 = onItemDeselected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = gridItemElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = gridItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        AboutElement about = getAbout();
        AboutElement about2 = gridItemElement.getAbout();
        if (about != about2) {
            if (about == null) {
                return -1;
            }
            if (about2 == null) {
                return 1;
            }
            int iCompareTo5 = about.compareTo(about2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Method> onAboutSelected = getOnAboutSelected();
        List<Method> onAboutSelected2 = gridItemElement.getOnAboutSelected();
        if (onAboutSelected != onAboutSelected2) {
            if (onAboutSelected == null) {
                return -1;
            }
            if (onAboutSelected2 == null) {
                return 1;
            }
            if (onAboutSelected instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onAboutSelected).compareTo(onAboutSelected2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onAboutSelected.equals(onAboutSelected2)) {
                int iHashCode5 = onAboutSelected.hashCode();
                int iHashCode6 = onAboutSelected2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = gridItemElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo7 = id.compareTo(id2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String altText = getAltText();
        String altText2 = gridItemElement.getAltText();
        if (altText != altText2) {
            if (altText == null) {
                return -1;
            }
            if (altText2 == null) {
                return 1;
            }
            int iCompareTo8 = altText.compareTo(altText2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof GridItemElement)) {
            return false;
        }
        GridItemElement gridItemElement = (GridItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), gridItemElement.getImage()) && internalEqualityCheck(getOnItemDeselected(), gridItemElement.getOnItemDeselected()) && internalEqualityCheck(getPodcastShowVariantId(), gridItemElement.getPodcastShowVariantId()) && internalEqualityCheck(getOnItemSelected(), gridItemElement.getOnItemSelected()) && internalEqualityCheck(getAbout(), gridItemElement.getAbout()) && internalEqualityCheck(getOnAboutSelected(), gridItemElement.getOnAboutSelected()) && internalEqualityCheck(getId(), gridItemElement.getId()) && internalEqualityCheck(getAltText(), gridItemElement.getAltText());
    }

    public AboutElement getAbout() {
        return this.about;
    }

    public String getAltText() {
        return this.altText;
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

    public List<Method> getOnItemDeselected() {
        return this.onItemDeselected;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), getOnItemDeselected(), getPodcastShowVariantId(), getOnItemSelected(), getAbout(), getOnAboutSelected(), getId(), getAltText());
    }

    public void setAbout(AboutElement aboutElement) {
        this.about = aboutElement;
    }

    public void setAltText(String str) {
        this.altText = str;
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

    public void setOnItemDeselected(List<Method> list) {
        this.onItemDeselected = list;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }
}
