package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShareElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShareElement");
    private String deeplinkUrl;
    private String description;
    private String id;
    private String imageUrl;
    private String label;
    private String metricsEntityType;
    private List<Method> onShare;
    private String prompt;
    private String subtitle;
    private String title;

    public static class Builder extends TemplateElement.Builder {
        protected String deeplinkUrl;
        protected String description;
        protected String id;
        protected String imageUrl;
        protected String label;
        protected String metricsEntityType;
        protected List<Method> onShare;
        protected String prompt;
        protected String subtitle;
        protected String title;

        public ShareElement build() {
            ShareElement shareElement = new ShareElement();
            populate(shareElement);
            return shareElement;
        }

        protected void populate(ShareElement shareElement) {
            super.populate((TemplateElement) shareElement);
            shareElement.setDescription(this.description);
            shareElement.setPrompt(this.prompt);
            shareElement.setDeeplinkUrl(this.deeplinkUrl);
            shareElement.setId(this.id);
            shareElement.setMetricsEntityType(this.metricsEntityType);
            shareElement.setLabel(this.label);
            shareElement.setSubtitle(this.subtitle);
            shareElement.setImageUrl(this.imageUrl);
            shareElement.setOnShare(this.onShare);
            shareElement.setTitle(this.title);
        }

        public Builder withDeeplinkUrl(String str) {
            this.deeplinkUrl = str;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withMetricsEntityType(String str) {
            this.metricsEntityType = str;
            return this;
        }

        public Builder withOnShare(List<Method> list) {
            this.onShare = list;
            return this;
        }

        public Builder withPrompt(String str) {
            this.prompt = str;
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
        if (!(sOAObject instanceof ShareElement)) {
            return 1;
        }
        ShareElement shareElement = (ShareElement) sOAObject;
        String description = getDescription();
        String description2 = shareElement.getDescription();
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
        String prompt = getPrompt();
        String prompt2 = shareElement.getPrompt();
        if (prompt != prompt2) {
            if (prompt == null) {
                return -1;
            }
            if (prompt2 == null) {
                return 1;
            }
            int iCompareTo2 = prompt.compareTo(prompt2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String deeplinkUrl = getDeeplinkUrl();
        String deeplinkUrl2 = shareElement.getDeeplinkUrl();
        if (deeplinkUrl != deeplinkUrl2) {
            if (deeplinkUrl == null) {
                return -1;
            }
            if (deeplinkUrl2 == null) {
                return 1;
            }
            int iCompareTo3 = deeplinkUrl.compareTo(deeplinkUrl2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String id = getId();
        String id2 = shareElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo4 = id.compareTo(id2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String metricsEntityType = getMetricsEntityType();
        String metricsEntityType2 = shareElement.getMetricsEntityType();
        if (metricsEntityType != metricsEntityType2) {
            if (metricsEntityType == null) {
                return -1;
            }
            if (metricsEntityType2 == null) {
                return 1;
            }
            int iCompareTo5 = metricsEntityType.compareTo(metricsEntityType2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String label = getLabel();
        String label2 = shareElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo6 = label.compareTo(label2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = shareElement.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo7 = subtitle.compareTo(subtitle2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String imageUrl = getImageUrl();
        String imageUrl2 = shareElement.getImageUrl();
        if (imageUrl != imageUrl2) {
            if (imageUrl == null) {
                return -1;
            }
            if (imageUrl2 == null) {
                return 1;
            }
            int iCompareTo8 = imageUrl.compareTo(imageUrl2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<Method> onShare = getOnShare();
        List<Method> onShare2 = shareElement.getOnShare();
        if (onShare != onShare2) {
            if (onShare == null) {
                return -1;
            }
            if (onShare2 == null) {
                return 1;
            }
            if (onShare instanceof Comparable) {
                int iCompareTo9 = ((Comparable) onShare).compareTo(onShare2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!onShare.equals(onShare2)) {
                int iHashCode = onShare.hashCode();
                int iHashCode2 = onShare2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = shareElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo10 = title.compareTo(title2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ShareElement)) {
            return false;
        }
        ShareElement shareElement = (ShareElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDescription(), shareElement.getDescription()) && internalEqualityCheck(getPrompt(), shareElement.getPrompt()) && internalEqualityCheck(getDeeplinkUrl(), shareElement.getDeeplinkUrl()) && internalEqualityCheck(getId(), shareElement.getId()) && internalEqualityCheck(getMetricsEntityType(), shareElement.getMetricsEntityType()) && internalEqualityCheck(getLabel(), shareElement.getLabel()) && internalEqualityCheck(getSubtitle(), shareElement.getSubtitle()) && internalEqualityCheck(getImageUrl(), shareElement.getImageUrl()) && internalEqualityCheck(getOnShare(), shareElement.getOnShare()) && internalEqualityCheck(getTitle(), shareElement.getTitle());
    }

    public String getDeeplinkUrl() {
        return this.deeplinkUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getLabel() {
        return this.label;
    }

    public String getMetricsEntityType() {
        return this.metricsEntityType;
    }

    public List<Method> getOnShare() {
        return this.onShare;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDescription(), getPrompt(), getDeeplinkUrl(), getId(), getMetricsEntityType(), getLabel(), getSubtitle(), getImageUrl(), getOnShare(), getTitle());
    }

    public void setDeeplinkUrl(String str) {
        this.deeplinkUrl = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setMetricsEntityType(String str) {
        this.metricsEntityType = str;
    }

    public void setOnShare(List<Method> list) {
        this.onShare = list;
    }

    public void setPrompt(String str) {
        this.prompt = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
