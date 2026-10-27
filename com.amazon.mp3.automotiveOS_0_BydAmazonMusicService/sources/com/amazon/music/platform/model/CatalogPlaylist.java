package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogPlaylist extends Playlist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogPlaylist");
    private List<String> alternativeName;
    private String curator;
    private String description;
    private List<String> era;
    private List<String> genre;
    private Boolean isInLibrary;
    private String languageOfPerformance;
    private List<LanguageOfPerformance> languageOfPerformances;
    private String marketplaceId;
    private List<String> mood;
    private ParentalControls parentalControls;
    private PlaymodeEligibility playmodeEligibility;
    private String seriesAsin;
    private List<RelatedTrack> track;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof CatalogPlaylist)) {
            return 1;
        }
        CatalogPlaylist catalogPlaylist = (CatalogPlaylist) document;
        List<LanguageOfPerformance> languageOfPerformances = getLanguageOfPerformances();
        List<LanguageOfPerformance> languageOfPerformances2 = catalogPlaylist.getLanguageOfPerformances();
        if (languageOfPerformances != languageOfPerformances2) {
            if (languageOfPerformances == null) {
                return -1;
            }
            if (languageOfPerformances2 == null) {
                return 1;
            }
            if (languageOfPerformances instanceof Comparable) {
                int iCompareTo = ((Comparable) languageOfPerformances).compareTo(languageOfPerformances2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!languageOfPerformances.equals(languageOfPerformances2)) {
                int iHashCode = languageOfPerformances.hashCode();
                int iHashCode2 = languageOfPerformances2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> mood = getMood();
        List<String> mood2 = catalogPlaylist.getMood();
        if (mood != mood2) {
            if (mood == null) {
                return -1;
            }
            if (mood2 == null) {
                return 1;
            }
            if (mood instanceof Comparable) {
                int iCompareTo2 = ((Comparable) mood).compareTo(mood2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!mood.equals(mood2)) {
                int iHashCode3 = mood.hashCode();
                int iHashCode4 = mood2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = catalogPlaylist.getPlaymodeEligibility();
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
        List<RelatedTrack> track = getTrack();
        List<RelatedTrack> track2 = catalogPlaylist.getTrack();
        if (track != track2) {
            if (track == null) {
                return -1;
            }
            if (track2 == null) {
                return 1;
            }
            if (track instanceof Comparable) {
                int iCompareTo4 = ((Comparable) track).compareTo(track2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!track.equals(track2)) {
                int iHashCode5 = track.hashCode();
                int iHashCode6 = track2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<String> genre = getGenre();
        List<String> genre2 = catalogPlaylist.getGenre();
        if (genre != genre2) {
            if (genre == null) {
                return -1;
            }
            if (genre2 == null) {
                return 1;
            }
            if (genre instanceof Comparable) {
                int iCompareTo5 = ((Comparable) genre).compareTo(genre2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        String seriesAsin = getSeriesAsin();
        String seriesAsin2 = catalogPlaylist.getSeriesAsin();
        if (seriesAsin != seriesAsin2) {
            if (seriesAsin == null) {
                return -1;
            }
            if (seriesAsin2 == null) {
                return 1;
            }
            int iCompareTo6 = seriesAsin.compareTo(seriesAsin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String languageOfPerformance = getLanguageOfPerformance();
        String languageOfPerformance2 = catalogPlaylist.getLanguageOfPerformance();
        if (languageOfPerformance != languageOfPerformance2) {
            if (languageOfPerformance == null) {
                return -1;
            }
            if (languageOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo7 = languageOfPerformance.compareTo(languageOfPerformance2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = catalogPlaylist.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo8 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String curator = getCurator();
        String curator2 = catalogPlaylist.getCurator();
        if (curator != curator2) {
            if (curator == null) {
                return -1;
            }
            if (curator2 == null) {
                return 1;
            }
            int iCompareTo9 = curator.compareTo(curator2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogPlaylist.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo10 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        List<String> era = getEra();
        List<String> era2 = catalogPlaylist.getEra();
        if (era != era2) {
            if (era == null) {
                return -1;
            }
            if (era2 == null) {
                return 1;
            }
            if (era instanceof Comparable) {
                int iCompareTo11 = ((Comparable) era).compareTo(era2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!era.equals(era2)) {
                int iHashCode9 = era.hashCode();
                int iHashCode10 = era2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = catalogPlaylist.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo12 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!alternativeName.equals(alternativeName2)) {
                int iHashCode11 = alternativeName.hashCode();
                int iHashCode12 = alternativeName2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        String description = getDescription();
        String description2 = catalogPlaylist.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo13 = description.compareTo(description2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = catalogPlaylist.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo14 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogPlaylist)) {
            return false;
        }
        CatalogPlaylist catalogPlaylist = (CatalogPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getLanguageOfPerformances(), catalogPlaylist.getLanguageOfPerformances()) && internalEqualityCheck(getMood(), catalogPlaylist.getMood()) && internalEqualityCheck(getPlaymodeEligibility(), catalogPlaylist.getPlaymodeEligibility()) && internalEqualityCheck(getTrack(), catalogPlaylist.getTrack()) && internalEqualityCheck(getGenre(), catalogPlaylist.getGenre()) && internalEqualityCheck(getSeriesAsin(), catalogPlaylist.getSeriesAsin()) && internalEqualityCheck(getLanguageOfPerformance(), catalogPlaylist.getLanguageOfPerformance()) && internalEqualityCheck(getMarketplaceId(), catalogPlaylist.getMarketplaceId()) && internalEqualityCheck(getCurator(), catalogPlaylist.getCurator()) && internalEqualityCheck(getParentalControls(), catalogPlaylist.getParentalControls()) && internalEqualityCheck(getEra(), catalogPlaylist.getEra()) && internalEqualityCheck(getAlternativeName(), catalogPlaylist.getAlternativeName()) && internalEqualityCheck(getDescription(), catalogPlaylist.getDescription()) && internalEqualityCheck(isIsInLibrary(), catalogPlaylist.isIsInLibrary());
    }

    public List<String> getAlternativeName() {
        return this.alternativeName;
    }

    public String getCurator() {
        return this.curator;
    }

    public String getDescription() {
        return this.description;
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

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public List<String> getMood() {
        return this.mood;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getSeriesAsin() {
        return this.seriesAsin;
    }

    public List<RelatedTrack> getTrack() {
        return this.track;
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLanguageOfPerformances(), getMood(), getPlaymodeEligibility(), getTrack(), getGenre(), getSeriesAsin(), getLanguageOfPerformance(), getMarketplaceId(), getCurator(), getParentalControls(), getEra(), getAlternativeName(), getDescription(), isIsInLibrary());
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public void setAlternativeName(List<String> list) {
        this.alternativeName = list;
    }

    public void setCurator(String str) {
        this.curator = str;
    }

    public void setDescription(String str) {
        this.description = str;
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

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMood(List<String> list) {
        this.mood = list;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setSeriesAsin(String str) {
        this.seriesAsin = str;
    }

    public void setTrack(List<RelatedTrack> list) {
        this.track = list;
    }
}
