package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RefinementOptionsClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementOptionsClientState");
    private Boolean download;
    private String seasonNumber;
    private String sort;
    private Boolean unplayed;

    public static class Builder extends ClientState.Builder {
        protected Boolean download;
        protected String seasonNumber;
        protected String sort;
        protected Boolean unplayed;

        public RefinementOptionsClientState build() {
            RefinementOptionsClientState refinementOptionsClientState = new RefinementOptionsClientState();
            populate(refinementOptionsClientState);
            return refinementOptionsClientState;
        }

        protected void populate(RefinementOptionsClientState refinementOptionsClientState) {
            super.populate((ClientState) refinementOptionsClientState);
            refinementOptionsClientState.setUnplayed(this.unplayed);
            refinementOptionsClientState.setSort(this.sort);
            refinementOptionsClientState.setSeasonNumber(this.seasonNumber);
            refinementOptionsClientState.setDownload(this.download);
        }

        public Builder withDownload(Boolean bool) {
            this.download = bool;
            return this;
        }

        public Builder withSeasonNumber(String str) {
            this.seasonNumber = str;
            return this;
        }

        public Builder withSort(String str) {
            this.sort = str;
            return this;
        }

        public Builder withUnplayed(Boolean bool) {
            this.unplayed = bool;
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
        if (!(sOAObject instanceof RefinementOptionsClientState)) {
            return 1;
        }
        RefinementOptionsClientState refinementOptionsClientState = (RefinementOptionsClientState) sOAObject;
        Boolean boolIsUnplayed = isUnplayed();
        Boolean boolIsUnplayed2 = refinementOptionsClientState.isUnplayed();
        if (boolIsUnplayed != boolIsUnplayed2) {
            if (boolIsUnplayed == null) {
                return -1;
            }
            if (boolIsUnplayed2 == null) {
                return 1;
            }
            int iCompareTo = boolIsUnplayed.compareTo(boolIsUnplayed2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String sort = getSort();
        String sort2 = refinementOptionsClientState.getSort();
        if (sort != sort2) {
            if (sort == null) {
                return -1;
            }
            if (sort2 == null) {
                return 1;
            }
            int iCompareTo2 = sort.compareTo(sort2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String seasonNumber = getSeasonNumber();
        String seasonNumber2 = refinementOptionsClientState.getSeasonNumber();
        if (seasonNumber != seasonNumber2) {
            if (seasonNumber == null) {
                return -1;
            }
            if (seasonNumber2 == null) {
                return 1;
            }
            int iCompareTo3 = seasonNumber.compareTo(seasonNumber2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsDownload = isDownload();
        Boolean boolIsDownload2 = refinementOptionsClientState.isDownload();
        if (boolIsDownload != boolIsDownload2) {
            if (boolIsDownload == null) {
                return -1;
            }
            if (boolIsDownload2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsDownload.compareTo(boolIsDownload2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RefinementOptionsClientState)) {
            return false;
        }
        RefinementOptionsClientState refinementOptionsClientState = (RefinementOptionsClientState) obj;
        return super.equals(obj) && internalEqualityCheck(isUnplayed(), refinementOptionsClientState.isUnplayed()) && internalEqualityCheck(getSort(), refinementOptionsClientState.getSort()) && internalEqualityCheck(getSeasonNumber(), refinementOptionsClientState.getSeasonNumber()) && internalEqualityCheck(isDownload(), refinementOptionsClientState.isDownload());
    }

    public String getSeasonNumber() {
        return this.seasonNumber;
    }

    public String getSort() {
        return this.sort;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isUnplayed(), getSort(), getSeasonNumber(), isDownload());
    }

    public Boolean isDownload() {
        return this.download;
    }

    public Boolean isUnplayed() {
        return this.unplayed;
    }

    public void setDownload(Boolean bool) {
        this.download = bool;
    }

    public void setSeasonNumber(String str) {
        this.seasonNumber = str;
    }

    public void setSort(String str) {
        this.sort = str;
    }

    public void setUnplayed(Boolean bool) {
        this.unplayed = bool;
    }
}
