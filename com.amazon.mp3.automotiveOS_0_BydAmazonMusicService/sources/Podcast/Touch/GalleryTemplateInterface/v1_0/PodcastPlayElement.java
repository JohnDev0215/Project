package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PodcastPlayElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastPlayElement");
    private String collectionId;
    private List<Method> onPlaySelected;

    public static class Builder extends TemplateElement.Builder {
        protected String collectionId;
        protected List<Method> onPlaySelected;

        public PodcastPlayElement build() {
            PodcastPlayElement podcastPlayElement = new PodcastPlayElement();
            populate(podcastPlayElement);
            return podcastPlayElement;
        }

        protected void populate(PodcastPlayElement podcastPlayElement) {
            super.populate((TemplateElement) podcastPlayElement);
            podcastPlayElement.setCollectionId(this.collectionId);
            podcastPlayElement.setOnPlaySelected(this.onPlaySelected);
        }

        public Builder withCollectionId(String str) {
            this.collectionId = str;
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
        if (!(sOAObject instanceof PodcastPlayElement)) {
            return 1;
        }
        PodcastPlayElement podcastPlayElement = (PodcastPlayElement) sOAObject;
        String collectionId = getCollectionId();
        String collectionId2 = podcastPlayElement.getCollectionId();
        if (collectionId != collectionId2) {
            if (collectionId == null) {
                return -1;
            }
            if (collectionId2 == null) {
                return 1;
            }
            int iCompareTo = collectionId.compareTo(collectionId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = podcastPlayElement.getOnPlaySelected();
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
        if (!(obj instanceof PodcastPlayElement)) {
            return false;
        }
        PodcastPlayElement podcastPlayElement = (PodcastPlayElement) obj;
        return super.equals(obj) && internalEqualityCheck(getCollectionId(), podcastPlayElement.getCollectionId()) && internalEqualityCheck(getOnPlaySelected(), podcastPlayElement.getOnPlaySelected());
    }

    public String getCollectionId() {
        return this.collectionId;
    }

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCollectionId(), getOnPlaySelected());
    }

    public void setCollectionId(String str) {
        this.collectionId = str;
    }

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }
}
