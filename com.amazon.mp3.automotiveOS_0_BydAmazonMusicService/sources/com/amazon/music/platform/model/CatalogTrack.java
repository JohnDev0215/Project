package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogTrack extends Track {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogTrack");
    private Date albumOriginalReleaseDate;
    private List<String> alternativeName;
    private List<TrackArtist> artist;
    private List<String> era;
    private List<String> genre;
    private Boolean isInLibrary;
    private String languageOfPerformance;
    private List<LanguageOfPerformance> languageOfPerformances;
    private List<String> mood;
    private Date originalReleaseDate;
    private PlaymodeEligibility playmodeEligibility;
    private List<RelatedPlaylist> relatedPlaylist;
    private Date singlePromotionDate;
    private String titleSetAsin;
    private List<String> version;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Track, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof CatalogTrack)) {
            return 1;
        }
        CatalogTrack catalogTrack = (CatalogTrack) document;
        List<String> mood = getMood();
        List<String> mood2 = catalogTrack.getMood();
        if (mood != mood2) {
            if (mood == null) {
                return -1;
            }
            if (mood2 == null) {
                return 1;
            }
            if (mood instanceof Comparable) {
                int iCompareTo = ((Comparable) mood).compareTo(mood2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!mood.equals(mood2)) {
                int iHashCode = mood.hashCode();
                int iHashCode2 = mood2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<LanguageOfPerformance> languageOfPerformances = getLanguageOfPerformances();
        List<LanguageOfPerformance> languageOfPerformances2 = catalogTrack.getLanguageOfPerformances();
        if (languageOfPerformances != languageOfPerformances2) {
            if (languageOfPerformances == null) {
                return -1;
            }
            if (languageOfPerformances2 == null) {
                return 1;
            }
            if (languageOfPerformances instanceof Comparable) {
                int iCompareTo2 = ((Comparable) languageOfPerformances).compareTo(languageOfPerformances2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!languageOfPerformances.equals(languageOfPerformances2)) {
                int iHashCode3 = languageOfPerformances.hashCode();
                int iHashCode4 = languageOfPerformances2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = catalogTrack.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo3 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Date albumOriginalReleaseDate = getAlbumOriginalReleaseDate();
        Date albumOriginalReleaseDate2 = catalogTrack.getAlbumOriginalReleaseDate();
        if (albumOriginalReleaseDate != albumOriginalReleaseDate2) {
            if (albumOriginalReleaseDate == null) {
                return -1;
            }
            if (albumOriginalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo4 = albumOriginalReleaseDate.compareTo(albumOriginalReleaseDate2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<TrackArtist> artist = getArtist();
        List<TrackArtist> artist2 = catalogTrack.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            if (artist instanceof Comparable) {
                int iCompareTo5 = ((Comparable) artist).compareTo(artist2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!artist.equals(artist2)) {
                int iHashCode5 = artist.hashCode();
                int iHashCode6 = artist2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<String> genre = getGenre();
        List<String> genre2 = catalogTrack.getGenre();
        if (genre != genre2) {
            if (genre == null) {
                return -1;
            }
            if (genre2 == null) {
                return 1;
            }
            if (genre instanceof Comparable) {
                int iCompareTo6 = ((Comparable) genre).compareTo(genre2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!genre.equals(genre2)) {
                int iHashCode7 = genre.hashCode();
                int iHashCode8 = genre2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = catalogTrack.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo7 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!alternativeName.equals(alternativeName2)) {
                int iHashCode9 = alternativeName.hashCode();
                int iHashCode10 = alternativeName2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        List<RelatedPlaylist> relatedPlaylist = getRelatedPlaylist();
        List<RelatedPlaylist> relatedPlaylist2 = catalogTrack.getRelatedPlaylist();
        if (relatedPlaylist != relatedPlaylist2) {
            if (relatedPlaylist == null) {
                return -1;
            }
            if (relatedPlaylist2 == null) {
                return 1;
            }
            if (relatedPlaylist instanceof Comparable) {
                int iCompareTo8 = ((Comparable) relatedPlaylist).compareTo(relatedPlaylist2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!relatedPlaylist.equals(relatedPlaylist2)) {
                int iHashCode11 = relatedPlaylist.hashCode();
                int iHashCode12 = relatedPlaylist2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        List<String> version = getVersion();
        List<String> version2 = catalogTrack.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            if (version instanceof Comparable) {
                int iCompareTo9 = ((Comparable) version).compareTo(version2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!version.equals(version2)) {
                int iHashCode13 = version.hashCode();
                int iHashCode14 = version2.hashCode();
                if (iHashCode13 < iHashCode14) {
                    return -1;
                }
                if (iHashCode13 > iHashCode14) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = catalogTrack.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String languageOfPerformance = getLanguageOfPerformance();
        String languageOfPerformance2 = catalogTrack.getLanguageOfPerformance();
        if (languageOfPerformance != languageOfPerformance2) {
            if (languageOfPerformance == null) {
                return -1;
            }
            if (languageOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo11 = languageOfPerformance.compareTo(languageOfPerformance2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        List<String> era = getEra();
        List<String> era2 = catalogTrack.getEra();
        if (era != era2) {
            if (era == null) {
                return -1;
            }
            if (era2 == null) {
                return 1;
            }
            if (era instanceof Comparable) {
                int iCompareTo12 = ((Comparable) era).compareTo(era2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!era.equals(era2)) {
                int iHashCode15 = era.hashCode();
                int iHashCode16 = era2.hashCode();
                if (iHashCode15 < iHashCode16) {
                    return -1;
                }
                if (iHashCode15 > iHashCode16) {
                    return 1;
                }
            }
        }
        Date originalReleaseDate = getOriginalReleaseDate();
        Date originalReleaseDate2 = catalogTrack.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo13 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String titleSetAsin = getTitleSetAsin();
        String titleSetAsin2 = catalogTrack.getTitleSetAsin();
        if (titleSetAsin != titleSetAsin2) {
            if (titleSetAsin == null) {
                return -1;
            }
            if (titleSetAsin2 == null) {
                return 1;
            }
            int iCompareTo14 = titleSetAsin.compareTo(titleSetAsin2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Date singlePromotionDate = getSinglePromotionDate();
        Date singlePromotionDate2 = catalogTrack.getSinglePromotionDate();
        if (singlePromotionDate != singlePromotionDate2) {
            if (singlePromotionDate == null) {
                return -1;
            }
            if (singlePromotionDate2 == null) {
                return 1;
            }
            int iCompareTo15 = singlePromotionDate.compareTo(singlePromotionDate2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Track, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogTrack)) {
            return false;
        }
        CatalogTrack catalogTrack = (CatalogTrack) obj;
        return super.equals(obj) && internalEqualityCheck(getMood(), catalogTrack.getMood()) && internalEqualityCheck(getLanguageOfPerformances(), catalogTrack.getLanguageOfPerformances()) && internalEqualityCheck(getPlaymodeEligibility(), catalogTrack.getPlaymodeEligibility()) && internalEqualityCheck(getAlbumOriginalReleaseDate(), catalogTrack.getAlbumOriginalReleaseDate()) && internalEqualityCheck(getArtist(), catalogTrack.getArtist()) && internalEqualityCheck(getGenre(), catalogTrack.getGenre()) && internalEqualityCheck(getAlternativeName(), catalogTrack.getAlternativeName()) && internalEqualityCheck(getRelatedPlaylist(), catalogTrack.getRelatedPlaylist()) && internalEqualityCheck(getVersion(), catalogTrack.getVersion()) && internalEqualityCheck(isIsInLibrary(), catalogTrack.isIsInLibrary()) && internalEqualityCheck(getLanguageOfPerformance(), catalogTrack.getLanguageOfPerformance()) && internalEqualityCheck(getEra(), catalogTrack.getEra()) && internalEqualityCheck(getOriginalReleaseDate(), catalogTrack.getOriginalReleaseDate()) && internalEqualityCheck(getTitleSetAsin(), catalogTrack.getTitleSetAsin()) && internalEqualityCheck(getSinglePromotionDate(), catalogTrack.getSinglePromotionDate());
    }

    public Date getAlbumOriginalReleaseDate() {
        return this.albumOriginalReleaseDate;
    }

    public List<String> getAlternativeName() {
        return this.alternativeName;
    }

    public List<TrackArtist> getArtist() {
        return this.artist;
    }

    public List<String> getEra() {
        return this.era;
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

    public List<String> getMood() {
        return this.mood;
    }

    public Date getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public List<RelatedPlaylist> getRelatedPlaylist() {
        return this.relatedPlaylist;
    }

    public Date getSinglePromotionDate() {
        return this.singlePromotionDate;
    }

    public String getTitleSetAsin() {
        return this.titleSetAsin;
    }

    public List<String> getVersion() {
        return this.version;
    }

    @Override // com.amazon.music.platform.model.Track, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMood(), getLanguageOfPerformances(), getPlaymodeEligibility(), getAlbumOriginalReleaseDate(), getArtist(), getGenre(), getAlternativeName(), getRelatedPlaylist(), getVersion(), isIsInLibrary(), getLanguageOfPerformance(), getEra(), getOriginalReleaseDate(), getTitleSetAsin(), getSinglePromotionDate());
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public void setAlbumOriginalReleaseDate(Date date) {
        this.albumOriginalReleaseDate = date;
    }

    public void setAlternativeName(List<String> list) {
        this.alternativeName = list;
    }

    public void setArtist(List<TrackArtist> list) {
        this.artist = list;
    }

    public void setEra(List<String> list) {
        this.era = list;
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

    public void setMood(List<String> list) {
        this.mood = list;
    }

    public void setOriginalReleaseDate(Date date) {
        this.originalReleaseDate = date;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setRelatedPlaylist(List<RelatedPlaylist> list) {
        this.relatedPlaylist = list;
    }

    public void setSinglePromotionDate(Date date) {
        this.singlePromotionDate = date;
    }

    public void setTitleSetAsin(String str) {
        this.titleSetAsin = str;
    }

    public void setVersion(List<String> list) {
        this.version = list;
    }
}
