package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCSelectionsClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientState");
    private List<String> categories;
    private List<String> podcasts;

    public static class Builder extends ClientState.Builder {
        protected List<String> categories;
        protected List<String> podcasts;

        public PTCSelectionsClientState build() {
            PTCSelectionsClientState pTCSelectionsClientState = new PTCSelectionsClientState();
            populate(pTCSelectionsClientState);
            return pTCSelectionsClientState;
        }

        protected void populate(PTCSelectionsClientState pTCSelectionsClientState) {
            super.populate((ClientState) pTCSelectionsClientState);
            pTCSelectionsClientState.setCategories(this.categories);
            pTCSelectionsClientState.setPodcasts(this.podcasts);
        }

        public Builder withCategories(List<String> list) {
            this.categories = list;
            return this;
        }

        public Builder withPodcasts(List<String> list) {
            this.podcasts = list;
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
    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof PTCSelectionsClientState)) {
            return 1;
        }
        PTCSelectionsClientState pTCSelectionsClientState = (PTCSelectionsClientState) sOAObject;
        List<String> categories = getCategories();
        List<String> categories2 = pTCSelectionsClientState.getCategories();
        if (categories != categories2) {
            if (categories == null) {
                return -1;
            }
            if (categories2 == null) {
                return 1;
            }
            if (categories instanceof Comparable) {
                int iCompareTo = ((Comparable) categories).compareTo(categories2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!categories.equals(categories2)) {
                int iHashCode = categories.hashCode();
                int iHashCode2 = categories2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> podcasts = getPodcasts();
        List<String> podcasts2 = pTCSelectionsClientState.getPodcasts();
        if (podcasts != podcasts2) {
            if (podcasts == null) {
                return -1;
            }
            if (podcasts2 == null) {
                return 1;
            }
            if (podcasts instanceof Comparable) {
                int iCompareTo2 = ((Comparable) podcasts).compareTo(podcasts2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!podcasts.equals(podcasts2)) {
                int iHashCode3 = podcasts.hashCode();
                int iHashCode4 = podcasts2.hashCode();
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

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCSelectionsClientState)) {
            return false;
        }
        PTCSelectionsClientState pTCSelectionsClientState = (PTCSelectionsClientState) obj;
        return super.equals(obj) && internalEqualityCheck(getCategories(), pTCSelectionsClientState.getCategories()) && internalEqualityCheck(getPodcasts(), pTCSelectionsClientState.getPodcasts());
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public List<String> getPodcasts() {
        return this.podcasts;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCategories(), getPodcasts());
    }

    public void setCategories(List<String> list) {
        this.categories = list;
    }

    public void setPodcasts(List<String> list) {
        this.podcasts = list;
    }
}
