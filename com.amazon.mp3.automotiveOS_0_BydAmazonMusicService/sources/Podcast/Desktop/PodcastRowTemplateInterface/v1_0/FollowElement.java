package Podcast.Desktop.PodcastRowTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FollowElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.PodcastRowTemplateInterface.v1_0.FollowElement");
    private String id;
    private List<Method> onFollow;
    private List<Method> onUnFollow;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onFollow;
        protected List<Method> onUnFollow;

        public FollowElement build() {
            FollowElement followElement = new FollowElement();
            populate(followElement);
            return followElement;
        }

        protected void populate(FollowElement followElement) {
            super.populate((TemplateElement) followElement);
            followElement.setId(this.id);
            followElement.setOnFollow(this.onFollow);
            followElement.setOnUnFollow(this.onUnFollow);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnFollow(List<Method> list) {
            this.onFollow = list;
            return this;
        }

        public Builder withOnUnFollow(List<Method> list) {
            this.onUnFollow = list;
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
        if (!(sOAObject instanceof FollowElement)) {
            return 1;
        }
        FollowElement followElement = (FollowElement) sOAObject;
        String id = getId();
        String id2 = followElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onFollow = getOnFollow();
        List<Method> onFollow2 = followElement.getOnFollow();
        if (onFollow != onFollow2) {
            if (onFollow == null) {
                return -1;
            }
            if (onFollow2 == null) {
                return 1;
            }
            if (onFollow instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onFollow).compareTo(onFollow2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onFollow.equals(onFollow2)) {
                int iHashCode = onFollow.hashCode();
                int iHashCode2 = onFollow2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onUnFollow = getOnUnFollow();
        List<Method> onUnFollow2 = followElement.getOnUnFollow();
        if (onUnFollow != onUnFollow2) {
            if (onUnFollow == null) {
                return -1;
            }
            if (onUnFollow2 == null) {
                return 1;
            }
            if (onUnFollow instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onUnFollow).compareTo(onUnFollow2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onUnFollow.equals(onUnFollow2)) {
                int iHashCode3 = onUnFollow.hashCode();
                int iHashCode4 = onUnFollow2.hashCode();
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
        if (!(obj instanceof FollowElement)) {
            return false;
        }
        FollowElement followElement = (FollowElement) obj;
        return super.equals(obj) && internalEqualityCheck(getId(), followElement.getId()) && internalEqualityCheck(getOnFollow(), followElement.getOnFollow()) && internalEqualityCheck(getOnUnFollow(), followElement.getOnUnFollow());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnFollow() {
        return this.onFollow;
    }

    public List<Method> getOnUnFollow() {
        return this.onUnFollow;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getOnFollow(), getOnUnFollow());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnFollow(List<Method> list) {
        this.onFollow = list;
    }

    public void setOnUnFollow(List<Method> list) {
        this.onUnFollow = list;
    }
}
