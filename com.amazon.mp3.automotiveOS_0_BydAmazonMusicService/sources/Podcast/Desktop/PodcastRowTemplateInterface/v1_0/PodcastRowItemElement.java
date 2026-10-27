package Podcast.Desktop.PodcastRowTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PodcastRowItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowItemElement");
    private FollowElement follow;
    private String image;
    private List<Method> onItemSelected;
    private String primaryText;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected FollowElement follow;
        protected String image;
        protected List<Method> onItemSelected;
        protected String primaryText;
        protected String secondaryText;

        public PodcastRowItemElement build() {
            PodcastRowItemElement podcastRowItemElement = new PodcastRowItemElement();
            populate(podcastRowItemElement);
            return podcastRowItemElement;
        }

        protected void populate(PodcastRowItemElement podcastRowItemElement) {
            super.populate((TemplateElement) podcastRowItemElement);
            podcastRowItemElement.setSecondaryText(this.secondaryText);
            podcastRowItemElement.setPrimaryText(this.primaryText);
            podcastRowItemElement.setImage(this.image);
            podcastRowItemElement.setOnItemSelected(this.onItemSelected);
            podcastRowItemElement.setFollow(this.follow);
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
        if (!(sOAObject instanceof PodcastRowItemElement)) {
            return 1;
        }
        PodcastRowItemElement podcastRowItemElement = (PodcastRowItemElement) sOAObject;
        String secondaryText = getSecondaryText();
        String secondaryText2 = podcastRowItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo = secondaryText.compareTo(secondaryText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = podcastRowItemElement.getPrimaryText();
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
        String image = getImage();
        String image2 = podcastRowItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo3 = image.compareTo(image2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = podcastRowItemElement.getOnItemSelected();
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
        FollowElement follow = getFollow();
        FollowElement follow2 = podcastRowItemElement.getFollow();
        if (follow != follow2) {
            if (follow == null) {
                return -1;
            }
            if (follow2 == null) {
                return 1;
            }
            int iCompareTo5 = follow.compareTo(follow2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastRowItemElement)) {
            return false;
        }
        PodcastRowItemElement podcastRowItemElement = (PodcastRowItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSecondaryText(), podcastRowItemElement.getSecondaryText()) && internalEqualityCheck(getPrimaryText(), podcastRowItemElement.getPrimaryText()) && internalEqualityCheck(getImage(), podcastRowItemElement.getImage()) && internalEqualityCheck(getOnItemSelected(), podcastRowItemElement.getOnItemSelected()) && internalEqualityCheck(getFollow(), podcastRowItemElement.getFollow());
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

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSecondaryText(), getPrimaryText(), getImage(), getOnItemSelected(), getFollow());
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
}
