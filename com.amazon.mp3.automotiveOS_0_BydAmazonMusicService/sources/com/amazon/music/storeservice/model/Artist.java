package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Artist implements Comparable<Artist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.Artist");
    private String asin;
    private List<String> contributorAsins;
    private String name;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Artist artist) {
        if (artist == null) {
            return -1;
        }
        if (artist == this) {
            return 0;
        }
        List<String> contributorAsins = getContributorAsins();
        List<String> contributorAsins2 = artist.getContributorAsins();
        if (contributorAsins != contributorAsins2) {
            if (contributorAsins == null) {
                return -1;
            }
            if (contributorAsins2 == null) {
                return 1;
            }
            if (contributorAsins instanceof Comparable) {
                int iCompareTo = ((Comparable) contributorAsins).compareTo(contributorAsins2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!contributorAsins.equals(contributorAsins2)) {
                int iHashCode = contributorAsins.hashCode();
                int iHashCode2 = contributorAsins2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String asin = getAsin();
        String asin2 = artist.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo2 = asin.compareTo(asin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String name = getName();
        String name2 = artist.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo3 = name.compareTo(name2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) obj;
        return internalEqualityCheck(getContributorAsins(), artist.getContributorAsins()) && internalEqualityCheck(getAsin(), artist.getAsin()) && internalEqualityCheck(getName(), artist.getName());
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getContributorAsins() {
        return this.contributorAsins;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContributorAsins(), getAsin(), getName());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContributorAsins(List<String> list) {
        this.contributorAsins = list;
    }

    public void setName(String str) {
        this.name = str;
    }
}
