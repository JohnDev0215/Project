package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class TrackArtist implements Comparable<TrackArtist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.TrackArtist");
    private List<String> alternativeName;
    private String asin;
    private String contributorAsin;
    private List<Role> role;
    private String title;
    private String titleUnlocalized;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackArtist trackArtist) {
        if (trackArtist == null) {
            return -1;
        }
        if (trackArtist == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = trackArtist.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String asin = getAsin();
        String asin2 = trackArtist.getAsin();
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
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = trackArtist.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo3 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!alternativeName.equals(alternativeName2)) {
                int iHashCode = alternativeName.hashCode();
                int iHashCode2 = alternativeName2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = trackArtist.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo4 = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Role> role = getRole();
        List<Role> role2 = trackArtist.getRole();
        if (role != role2) {
            if (role == null) {
                return -1;
            }
            if (role2 == null) {
                return 1;
            }
            if (role instanceof Comparable) {
                int iCompareTo5 = ((Comparable) role).compareTo(role2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!role.equals(role2)) {
                int iHashCode3 = role.hashCode();
                int iHashCode4 = role2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String titleUnlocalized = getTitleUnlocalized();
        String titleUnlocalized2 = trackArtist.getTitleUnlocalized();
        if (titleUnlocalized != titleUnlocalized2) {
            if (titleUnlocalized == null) {
                return -1;
            }
            if (titleUnlocalized2 == null) {
                return 1;
            }
            int iCompareTo6 = titleUnlocalized.compareTo(titleUnlocalized2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackArtist)) {
            return false;
        }
        TrackArtist trackArtist = (TrackArtist) obj;
        return internalEqualityCheck(getTitle(), trackArtist.getTitle()) && internalEqualityCheck(getAsin(), trackArtist.getAsin()) && internalEqualityCheck(getAlternativeName(), trackArtist.getAlternativeName()) && internalEqualityCheck(getContributorAsin(), trackArtist.getContributorAsin()) && internalEqualityCheck(getRole(), trackArtist.getRole()) && internalEqualityCheck(getTitleUnlocalized(), trackArtist.getTitleUnlocalized());
    }

    public List<String> getAlternativeName() {
        return this.alternativeName;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public List<Role> getRole() {
        return this.role;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleUnlocalized() {
        return this.titleUnlocalized;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getAsin(), getAlternativeName(), getContributorAsin(), getRole(), getTitleUnlocalized());
    }

    public void setAlternativeName(List<String> list) {
        this.alternativeName = list;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setRole(List<Role> list) {
        this.role = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleUnlocalized(String str) {
        this.titleUnlocalized = str;
    }
}
