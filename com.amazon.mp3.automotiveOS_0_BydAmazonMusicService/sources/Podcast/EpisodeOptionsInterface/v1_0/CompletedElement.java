package Podcast.EpisodeOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompletedElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.EpisodeOptionsInterface.v1_0.CompletedElement");
    private String id;
    private List<Method> onMarkPlayed;
    private List<Method> onMarkUnplayed;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onMarkPlayed;
        protected List<Method> onMarkUnplayed;

        public CompletedElement build() {
            CompletedElement completedElement = new CompletedElement();
            populate(completedElement);
            return completedElement;
        }

        protected void populate(CompletedElement completedElement) {
            super.populate((TemplateElement) completedElement);
            completedElement.setOnMarkPlayed(this.onMarkPlayed);
            completedElement.setOnMarkUnplayed(this.onMarkUnplayed);
            completedElement.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnMarkPlayed(List<Method> list) {
            this.onMarkPlayed = list;
            return this;
        }

        public Builder withOnMarkUnplayed(List<Method> list) {
            this.onMarkUnplayed = list;
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
        if (!(sOAObject instanceof CompletedElement)) {
            return 1;
        }
        CompletedElement completedElement = (CompletedElement) sOAObject;
        List<Method> onMarkPlayed = getOnMarkPlayed();
        List<Method> onMarkPlayed2 = completedElement.getOnMarkPlayed();
        if (onMarkPlayed != onMarkPlayed2) {
            if (onMarkPlayed == null) {
                return -1;
            }
            if (onMarkPlayed2 == null) {
                return 1;
            }
            if (onMarkPlayed instanceof Comparable) {
                int iCompareTo = ((Comparable) onMarkPlayed).compareTo(onMarkPlayed2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onMarkPlayed.equals(onMarkPlayed2)) {
                int iHashCode = onMarkPlayed.hashCode();
                int iHashCode2 = onMarkPlayed2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onMarkUnplayed = getOnMarkUnplayed();
        List<Method> onMarkUnplayed2 = completedElement.getOnMarkUnplayed();
        if (onMarkUnplayed != onMarkUnplayed2) {
            if (onMarkUnplayed == null) {
                return -1;
            }
            if (onMarkUnplayed2 == null) {
                return 1;
            }
            if (onMarkUnplayed instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onMarkUnplayed).compareTo(onMarkUnplayed2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onMarkUnplayed.equals(onMarkUnplayed2)) {
                int iHashCode3 = onMarkUnplayed.hashCode();
                int iHashCode4 = onMarkUnplayed2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = completedElement.getId();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof CompletedElement)) {
            return false;
        }
        CompletedElement completedElement = (CompletedElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnMarkPlayed(), completedElement.getOnMarkPlayed()) && internalEqualityCheck(getOnMarkUnplayed(), completedElement.getOnMarkUnplayed()) && internalEqualityCheck(getId(), completedElement.getId());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnMarkPlayed() {
        return this.onMarkPlayed;
    }

    public List<Method> getOnMarkUnplayed() {
        return this.onMarkUnplayed;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnMarkPlayed(), getOnMarkUnplayed(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnMarkPlayed(List<Method> list) {
        this.onMarkPlayed = list;
    }

    public void setOnMarkUnplayed(List<Method> list) {
        this.onMarkUnplayed = list;
    }
}
