package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Artist implements Comparable<Artist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.Artist");
    private List<ArtistRole> artistRoles;
    private String contributorAsin;
    private String digitalAsin;
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
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = artist.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
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
            int iCompareTo2 = name.compareTo(name2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String digitalAsin = getDigitalAsin();
        String digitalAsin2 = artist.getDigitalAsin();
        if (digitalAsin != digitalAsin2) {
            if (digitalAsin == null) {
                return -1;
            }
            if (digitalAsin2 == null) {
                return 1;
            }
            int iCompareTo3 = digitalAsin.compareTo(digitalAsin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<ArtistRole> artistRoles = getArtistRoles();
        List<ArtistRole> artistRoles2 = artist.getArtistRoles();
        if (artistRoles != artistRoles2) {
            if (artistRoles == null) {
                return -1;
            }
            if (artistRoles2 == null) {
                return 1;
            }
            if (artistRoles instanceof Comparable) {
                int iCompareTo4 = ((Comparable) artistRoles).compareTo(artistRoles2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!artistRoles.equals(artistRoles2)) {
                int iHashCode = artistRoles.hashCode();
                int iHashCode2 = artistRoles2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) obj;
        return internalEqualityCheck(getContributorAsin(), artist.getContributorAsin()) && internalEqualityCheck(getName(), artist.getName()) && internalEqualityCheck(getDigitalAsin(), artist.getDigitalAsin()) && internalEqualityCheck(getArtistRoles(), artist.getArtistRoles());
    }

    public List<ArtistRole> getArtistRoles() {
        return this.artistRoles;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getDigitalAsin() {
        return this.digitalAsin;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContributorAsin(), getName(), getDigitalAsin(), getArtistRoles());
    }

    public void setArtistRoles(List<ArtistRole> list) {
        this.artistRoles = list;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setDigitalAsin(String str) {
        this.digitalAsin = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
