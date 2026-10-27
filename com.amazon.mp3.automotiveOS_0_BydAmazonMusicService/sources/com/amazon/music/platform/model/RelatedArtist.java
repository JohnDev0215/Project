package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RelatedArtist implements Comparable<RelatedArtist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.RelatedArtist");
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
    public int compareTo(@Deprecated RelatedArtist relatedArtist) {
        if (relatedArtist == null) {
            return -1;
        }
        if (relatedArtist == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = relatedArtist.getTitle();
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
        String titleUnlocalized = getTitleUnlocalized();
        String titleUnlocalized2 = relatedArtist.getTitleUnlocalized();
        if (titleUnlocalized != titleUnlocalized2) {
            if (titleUnlocalized == null) {
                return -1;
            }
            if (titleUnlocalized2 == null) {
                return 1;
            }
            int iCompareTo2 = titleUnlocalized.compareTo(titleUnlocalized2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String asin = getAsin();
        String asin2 = relatedArtist.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo3 = asin.compareTo(asin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = relatedArtist.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo4 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        String contributorAsin2 = relatedArtist.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo5 = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Role> role = getRole();
        List<Role> role2 = relatedArtist.getRole();
        if (role != role2) {
            if (role == null) {
                return -1;
            }
            if (role2 == null) {
                return 1;
            }
            if (role instanceof Comparable) {
                int iCompareTo6 = ((Comparable) role).compareTo(role2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RelatedArtist)) {
            return false;
        }
        RelatedArtist relatedArtist = (RelatedArtist) obj;
        return internalEqualityCheck(getTitle(), relatedArtist.getTitle()) && internalEqualityCheck(getTitleUnlocalized(), relatedArtist.getTitleUnlocalized()) && internalEqualityCheck(getAsin(), relatedArtist.getAsin()) && internalEqualityCheck(getAlternativeName(), relatedArtist.getAlternativeName()) && internalEqualityCheck(getContributorAsin(), relatedArtist.getContributorAsin()) && internalEqualityCheck(getRole(), relatedArtist.getRole());
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getTitleUnlocalized(), getAsin(), getAlternativeName(), getContributorAsin(), getRole());
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
