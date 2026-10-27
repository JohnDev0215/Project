package Podcast.EpisodeOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FollowElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.EpisodeOptionsInterface.v1_0.FollowElement");
    private Map<String, String> descriptions;
    private String id;
    private List<Method> onFollow;
    private List<Method> onUnFollow;

    public static class Builder extends TemplateElement.Builder {
        protected Map<String, String> descriptions;
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
            followElement.setOnUnFollow(this.onUnFollow);
            followElement.setDescriptions(this.descriptions);
            followElement.setOnFollow(this.onFollow);
            followElement.setId(this.id);
        }

        public Builder withDescriptions(Map<String, String> map) {
            this.descriptions = map;
            return this;
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
                int iCompareTo = ((Comparable) onUnFollow).compareTo(onUnFollow2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onUnFollow.equals(onUnFollow2)) {
                int iHashCode = onUnFollow.hashCode();
                int iHashCode2 = onUnFollow2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Map<String, String> descriptions = getDescriptions();
        Map<String, String> descriptions2 = followElement.getDescriptions();
        if (descriptions != descriptions2) {
            if (descriptions == null) {
                return -1;
            }
            if (descriptions2 == null) {
                return 1;
            }
            if (descriptions instanceof Comparable) {
                int iCompareTo2 = ((Comparable) descriptions).compareTo(descriptions2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!descriptions.equals(descriptions2)) {
                int iHashCode3 = descriptions.hashCode();
                int iHashCode4 = descriptions2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
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
                int iCompareTo3 = ((Comparable) onFollow).compareTo(onFollow2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onFollow.equals(onFollow2)) {
                int iHashCode5 = onFollow.hashCode();
                int iHashCode6 = onFollow2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = followElement.getId();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FollowElement)) {
            return false;
        }
        FollowElement followElement = (FollowElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnUnFollow(), followElement.getOnUnFollow()) && internalEqualityCheck(getDescriptions(), followElement.getDescriptions()) && internalEqualityCheck(getOnFollow(), followElement.getOnFollow()) && internalEqualityCheck(getId(), followElement.getId());
    }

    public Map<String, String> getDescriptions() {
        return this.descriptions;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnUnFollow(), getDescriptions(), getOnFollow(), getId());
    }

    public void setDescriptions(Map<String, String> map) {
        this.descriptions = map;
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
