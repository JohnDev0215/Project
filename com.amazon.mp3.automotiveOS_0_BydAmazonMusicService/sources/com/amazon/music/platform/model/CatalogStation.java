package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogStation extends Station {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogStation");
    private Art artOriginal;
    private List<String> artistName;
    private List<String> contentTier;
    private List<String> era;
    private List<String> genres;
    private String isMusicSubscription;
    private List<String> moods;
    private ParentalControls parentalControls;
    private Popularities popularities;
    private String primeStatus;
    private String seedType;
    private String stationSeedId;
    private String stationSeedType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Station, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof CatalogStation)) {
            return 1;
        }
        CatalogStation catalogStation = (CatalogStation) document;
        String seedType = getSeedType();
        String seedType2 = catalogStation.getSeedType();
        if (seedType != seedType2) {
            if (seedType == null) {
                return -1;
            }
            if (seedType2 == null) {
                return 1;
            }
            int iCompareTo = seedType.compareTo(seedType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> genres = getGenres();
        List<String> genres2 = catalogStation.getGenres();
        if (genres != genres2) {
            if (genres == null) {
                return -1;
            }
            if (genres2 == null) {
                return 1;
            }
            if (genres instanceof Comparable) {
                int iCompareTo2 = ((Comparable) genres).compareTo(genres2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!genres.equals(genres2)) {
                int iHashCode = genres.hashCode();
                int iHashCode2 = genres2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> moods = getMoods();
        List<String> moods2 = catalogStation.getMoods();
        if (moods != moods2) {
            if (moods == null) {
                return -1;
            }
            if (moods2 == null) {
                return 1;
            }
            if (moods instanceof Comparable) {
                int iCompareTo3 = ((Comparable) moods).compareTo(moods2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!moods.equals(moods2)) {
                int iHashCode3 = moods.hashCode();
                int iHashCode4 = moods2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String stationSeedId = getStationSeedId();
        String stationSeedId2 = catalogStation.getStationSeedId();
        if (stationSeedId != stationSeedId2) {
            if (stationSeedId == null) {
                return -1;
            }
            if (stationSeedId2 == null) {
                return 1;
            }
            int iCompareTo4 = stationSeedId.compareTo(stationSeedId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> era = getEra();
        List<String> era2 = catalogStation.getEra();
        if (era != era2) {
            if (era == null) {
                return -1;
            }
            if (era2 == null) {
                return 1;
            }
            if (era instanceof Comparable) {
                int iCompareTo5 = ((Comparable) era).compareTo(era2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!era.equals(era2)) {
                int iHashCode5 = era.hashCode();
                int iHashCode6 = era2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        Popularities popularities = getPopularities();
        Popularities popularities2 = catalogStation.getPopularities();
        if (popularities != popularities2) {
            if (popularities == null) {
                return -1;
            }
            if (popularities2 == null) {
                return 1;
            }
            int iCompareTo6 = popularities.compareTo(popularities2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogStation.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo7 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<String> artistName = getArtistName();
        List<String> artistName2 = catalogStation.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            if (artistName instanceof Comparable) {
                int iCompareTo8 = ((Comparable) artistName).compareTo(artistName2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!artistName.equals(artistName2)) {
                int iHashCode7 = artistName.hashCode();
                int iHashCode8 = artistName2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = catalogStation.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo9 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String primeStatus = getPrimeStatus();
        String primeStatus2 = catalogStation.getPrimeStatus();
        if (primeStatus != primeStatus2) {
            if (primeStatus == null) {
                return -1;
            }
            if (primeStatus2 == null) {
                return 1;
            }
            int iCompareTo10 = primeStatus.compareTo(primeStatus2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = catalogStation.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo11 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = catalogStation.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo12 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode9 = contentTier.hashCode();
                int iHashCode10 = contentTier2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        String stationSeedType = getStationSeedType();
        String stationSeedType2 = catalogStation.getStationSeedType();
        if (stationSeedType != stationSeedType2) {
            if (stationSeedType == null) {
                return -1;
            }
            if (stationSeedType2 == null) {
                return 1;
            }
            int iCompareTo13 = stationSeedType.compareTo(stationSeedType2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Station, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogStation)) {
            return false;
        }
        CatalogStation catalogStation = (CatalogStation) obj;
        return super.equals(obj) && internalEqualityCheck(getSeedType(), catalogStation.getSeedType()) && internalEqualityCheck(getGenres(), catalogStation.getGenres()) && internalEqualityCheck(getMoods(), catalogStation.getMoods()) && internalEqualityCheck(getStationSeedId(), catalogStation.getStationSeedId()) && internalEqualityCheck(getEra(), catalogStation.getEra()) && internalEqualityCheck(getPopularities(), catalogStation.getPopularities()) && internalEqualityCheck(getParentalControls(), catalogStation.getParentalControls()) && internalEqualityCheck(getArtistName(), catalogStation.getArtistName()) && internalEqualityCheck(getArtOriginal(), catalogStation.getArtOriginal()) && internalEqualityCheck(getPrimeStatus(), catalogStation.getPrimeStatus()) && internalEqualityCheck(getIsMusicSubscription(), catalogStation.getIsMusicSubscription()) && internalEqualityCheck(getContentTier(), catalogStation.getContentTier()) && internalEqualityCheck(getStationSeedType(), catalogStation.getStationSeedType());
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public List<String> getArtistName() {
        return this.artistName;
    }

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public List<String> getEra() {
        return this.era;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public List<String> getMoods() {
        return this.moods;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public Popularities getPopularities() {
        return this.popularities;
    }

    public String getPrimeStatus() {
        return this.primeStatus;
    }

    public String getSeedType() {
        return this.seedType;
    }

    @Deprecated
    public String getStationSeedId() {
        return this.stationSeedId;
    }

    @Deprecated
    public String getStationSeedType() {
        return this.stationSeedType;
    }

    @Override // com.amazon.music.platform.model.Station, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSeedType(), getGenres(), getMoods(), getStationSeedId(), getEra(), getPopularities(), getParentalControls(), getArtistName(), getArtOriginal(), getPrimeStatus(), getIsMusicSubscription(), getContentTier(), getStationSeedType());
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setArtistName(List<String> list) {
        this.artistName = list;
    }

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setEra(List<String> list) {
        this.era = list;
    }

    public void setGenres(List<String> list) {
        this.genres = list;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setMoods(List<String> list) {
        this.moods = list;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPopularities(Popularities popularities) {
        this.popularities = popularities;
    }

    public void setPrimeStatus(String str) {
        this.primeStatus = str;
    }

    public void setSeedType(String str) {
        this.seedType = str;
    }

    @Deprecated
    public void setStationSeedId(String str) {
        this.stationSeedId = str;
    }

    @Deprecated
    public void setStationSeedType(String str) {
        this.stationSeedType = str;
    }
}
