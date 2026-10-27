package Podcast.Touch.DetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PodcastDetailsBookmarkElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DetailTemplateInterface.v1_0.PodcastDetailsBookmarkElement");
    private String id;
    private String label;
    private List<Method> onPause;
    private List<Method> onResume;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected String label;
        protected List<Method> onPause;
        protected List<Method> onResume;

        public PodcastDetailsBookmarkElement build() {
            PodcastDetailsBookmarkElement podcastDetailsBookmarkElement = new PodcastDetailsBookmarkElement();
            populate(podcastDetailsBookmarkElement);
            return podcastDetailsBookmarkElement;
        }

        protected void populate(PodcastDetailsBookmarkElement podcastDetailsBookmarkElement) {
            super.populate((TemplateElement) podcastDetailsBookmarkElement);
            podcastDetailsBookmarkElement.setOnResume(this.onResume);
            podcastDetailsBookmarkElement.setLabel(this.label);
            podcastDetailsBookmarkElement.setId(this.id);
            podcastDetailsBookmarkElement.setOnPause(this.onPause);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withOnPause(List<Method> list) {
            this.onPause = list;
            return this;
        }

        public Builder withOnResume(List<Method> list) {
            this.onResume = list;
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
        if (!(sOAObject instanceof PodcastDetailsBookmarkElement)) {
            return 1;
        }
        PodcastDetailsBookmarkElement podcastDetailsBookmarkElement = (PodcastDetailsBookmarkElement) sOAObject;
        List<Method> onResume = getOnResume();
        List<Method> onResume2 = podcastDetailsBookmarkElement.getOnResume();
        if (onResume != onResume2) {
            if (onResume == null) {
                return -1;
            }
            if (onResume2 == null) {
                return 1;
            }
            if (onResume instanceof Comparable) {
                int iCompareTo = ((Comparable) onResume).compareTo(onResume2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onResume.equals(onResume2)) {
                int iHashCode = onResume.hashCode();
                int iHashCode2 = onResume2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String label = getLabel();
        String label2 = podcastDetailsBookmarkElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo2 = label.compareTo(label2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String id = getId();
        String id2 = podcastDetailsBookmarkElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo3 = id.compareTo(id2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onPause = getOnPause();
        List<Method> onPause2 = podcastDetailsBookmarkElement.getOnPause();
        if (onPause != onPause2) {
            if (onPause == null) {
                return -1;
            }
            if (onPause2 == null) {
                return 1;
            }
            if (onPause instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onPause).compareTo(onPause2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onPause.equals(onPause2)) {
                int iHashCode3 = onPause.hashCode();
                int iHashCode4 = onPause2.hashCode();
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
        if (!(obj instanceof PodcastDetailsBookmarkElement)) {
            return false;
        }
        PodcastDetailsBookmarkElement podcastDetailsBookmarkElement = (PodcastDetailsBookmarkElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnResume(), podcastDetailsBookmarkElement.getOnResume()) && internalEqualityCheck(getLabel(), podcastDetailsBookmarkElement.getLabel()) && internalEqualityCheck(getId(), podcastDetailsBookmarkElement.getId()) && internalEqualityCheck(getOnPause(), podcastDetailsBookmarkElement.getOnPause());
    }

    public String getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Method> getOnPause() {
        return this.onPause;
    }

    public List<Method> getOnResume() {
        return this.onResume;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnResume(), getLabel(), getId(), getOnPause());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setOnPause(List<Method> list) {
        this.onPause = list;
    }

    public void setOnResume(List<Method> list) {
        this.onResume = list;
    }
}
