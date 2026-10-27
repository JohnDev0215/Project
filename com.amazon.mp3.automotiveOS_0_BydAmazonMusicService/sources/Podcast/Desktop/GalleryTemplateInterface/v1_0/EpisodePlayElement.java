package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodePlayElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodePlayElement");
    private String mediaId;
    private List<Method> onPlaySelected;

    public static class Builder extends TemplateElement.Builder {
        protected String mediaId;
        protected List<Method> onPlaySelected;

        public EpisodePlayElement build() {
            EpisodePlayElement episodePlayElement = new EpisodePlayElement();
            populate(episodePlayElement);
            return episodePlayElement;
        }

        protected void populate(EpisodePlayElement episodePlayElement) {
            super.populate((TemplateElement) episodePlayElement);
            episodePlayElement.setMediaId(this.mediaId);
            episodePlayElement.setOnPlaySelected(this.onPlaySelected);
        }

        public Builder withMediaId(String str) {
            this.mediaId = str;
            return this;
        }

        public Builder withOnPlaySelected(List<Method> list) {
            this.onPlaySelected = list;
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
        if (!(sOAObject instanceof EpisodePlayElement)) {
            return 1;
        }
        EpisodePlayElement episodePlayElement = (EpisodePlayElement) sOAObject;
        String mediaId = getMediaId();
        String mediaId2 = episodePlayElement.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo = mediaId.compareTo(mediaId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = episodePlayElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onPlaySelected.equals(onPlaySelected2)) {
                int iHashCode = onPlaySelected.hashCode();
                int iHashCode2 = onPlaySelected2.hashCode();
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
        if (!(obj instanceof EpisodePlayElement)) {
            return false;
        }
        EpisodePlayElement episodePlayElement = (EpisodePlayElement) obj;
        return super.equals(obj) && internalEqualityCheck(getMediaId(), episodePlayElement.getMediaId()) && internalEqualityCheck(getOnPlaySelected(), episodePlayElement.getOnPlaySelected());
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMediaId(), getOnPlaySelected());
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }
}
