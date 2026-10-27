package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogAlbum extends Album {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogAlbum");
    private List<String> alternativeName;
    private List<RelatedArtist> artist;
    private String contributorAsin;
    private List<String> genre;
    private Boolean isInLibrary;
    private String languageOfPerformance;
    private List<LanguageOfPerformance> languageOfPerformances;
    private Date originalReleaseDate;
    private ParentalControls parentalControls;
    private PlaymodeEligibility playmodeEligibility;
    private String titleSetAsin;
    private List<String> version;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Album, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof CatalogAlbum)) {
            return 1;
        }
        CatalogAlbum catalogAlbum = (CatalogAlbum) document;
        List<String> genre = getGenre();
        List<String> genre2 = catalogAlbum.getGenre();
        if (genre != genre2) {
            if (genre == null) {
                return -1;
            }
            if (genre2 == null) {
                return 1;
            }
            if (genre instanceof Comparable) {
                int iCompareTo = ((Comparable) genre).compareTo(genre2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!genre.equals(genre2)) {
                int iHashCode = genre.hashCode();
                int iHashCode2 = genre2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Date originalReleaseDate = getOriginalReleaseDate();
        Date originalReleaseDate2 = catalogAlbum.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo2 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogAlbum.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo3 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> version = getVersion();
        List<String> version2 = catalogAlbum.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            if (version instanceof Comparable) {
                int iCompareTo4 = ((Comparable) version).compareTo(version2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!version.equals(version2)) {
                int iHashCode3 = version.hashCode();
                int iHashCode4 = version2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = catalogAlbum.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo5 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = catalogAlbum.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo6 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!alternativeName.equals(alternativeName2)) {
                int iHashCode5 = alternativeName.hashCode();
                int iHashCode6 = alternativeName2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String titleSetAsin = getTitleSetAsin();
        String titleSetAsin2 = catalogAlbum.getTitleSetAsin();
        if (titleSetAsin != titleSetAsin2) {
            if (titleSetAsin == null) {
                return -1;
            }
            if (titleSetAsin2 == null) {
                return 1;
            }
            int iCompareTo7 = titleSetAsin.compareTo(titleSetAsin2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = catalogAlbum.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = catalogAlbum.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo9 = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String languageOfPerformance = getLanguageOfPerformance();
        String languageOfPerformance2 = catalogAlbum.getLanguageOfPerformance();
        if (languageOfPerformance != languageOfPerformance2) {
            if (languageOfPerformance == null) {
                return -1;
            }
            if (languageOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo10 = languageOfPerformance.compareTo(languageOfPerformance2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        List<LanguageOfPerformance> languageOfPerformances = getLanguageOfPerformances();
        List<LanguageOfPerformance> languageOfPerformances2 = catalogAlbum.getLanguageOfPerformances();
        if (languageOfPerformances != languageOfPerformances2) {
            if (languageOfPerformances == null) {
                return -1;
            }
            if (languageOfPerformances2 == null) {
                return 1;
            }
            if (languageOfPerformances instanceof Comparable) {
                int iCompareTo11 = ((Comparable) languageOfPerformances).compareTo(languageOfPerformances2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!languageOfPerformances.equals(languageOfPerformances2)) {
                int iHashCode7 = languageOfPerformances.hashCode();
                int iHashCode8 = languageOfPerformances2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<RelatedArtist> artist = getArtist();
        List<RelatedArtist> artist2 = catalogAlbum.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            if (artist instanceof Comparable) {
                int iCompareTo12 = ((Comparable) artist).compareTo(artist2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!artist.equals(artist2)) {
                int iHashCode9 = artist.hashCode();
                int iHashCode10 = artist2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Album, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogAlbum)) {
            return false;
        }
        CatalogAlbum catalogAlbum = (CatalogAlbum) obj;
        return super.equals(obj) && internalEqualityCheck(getGenre(), catalogAlbum.getGenre()) && internalEqualityCheck(getOriginalReleaseDate(), catalogAlbum.getOriginalReleaseDate()) && internalEqualityCheck(getParentalControls(), catalogAlbum.getParentalControls()) && internalEqualityCheck(getVersion(), catalogAlbum.getVersion()) && internalEqualityCheck(getPlaymodeEligibility(), catalogAlbum.getPlaymodeEligibility()) && internalEqualityCheck(getAlternativeName(), catalogAlbum.getAlternativeName()) && internalEqualityCheck(getTitleSetAsin(), catalogAlbum.getTitleSetAsin()) && internalEqualityCheck(isIsInLibrary(), catalogAlbum.isIsInLibrary()) && internalEqualityCheck(getContributorAsin(), catalogAlbum.getContributorAsin()) && internalEqualityCheck(getLanguageOfPerformance(), catalogAlbum.getLanguageOfPerformance()) && internalEqualityCheck(getLanguageOfPerformances(), catalogAlbum.getLanguageOfPerformances()) && internalEqualityCheck(getArtist(), catalogAlbum.getArtist());
    }

    public List<String> getAlternativeName() {
        return this.alternativeName;
    }

    public List<RelatedArtist> getArtist() {
        return this.artist;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public List<String> getGenre() {
        return this.genre;
    }

    public String getLanguageOfPerformance() {
        return this.languageOfPerformance;
    }

    public List<LanguageOfPerformance> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    public Date getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getTitleSetAsin() {
        return this.titleSetAsin;
    }

    public List<String> getVersion() {
        return this.version;
    }

    @Override // com.amazon.music.platform.model.Album, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getGenre(), getOriginalReleaseDate(), getParentalControls(), getVersion(), getPlaymodeEligibility(), getAlternativeName(), getTitleSetAsin(), isIsInLibrary(), getContributorAsin(), getLanguageOfPerformance(), getLanguageOfPerformances(), getArtist());
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public void setAlternativeName(List<String> list) {
        this.alternativeName = list;
    }

    public void setArtist(List<RelatedArtist> list) {
        this.artist = list;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setGenre(List<String> list) {
        this.genre = list;
    }

    public void setIsInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    public void setLanguageOfPerformance(String str) {
        this.languageOfPerformance = str;
    }

    public void setLanguageOfPerformances(List<LanguageOfPerformance> list) {
        this.languageOfPerformances = list;
    }

    public void setOriginalReleaseDate(Date date) {
        this.originalReleaseDate = date;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setTitleSetAsin(String str) {
        this.titleSetAsin = str;
    }

    public void setVersion(List<String> list) {
        this.version = list;
    }
}
