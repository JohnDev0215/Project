package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogVideoPlaylist extends Playlist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogVideoPlaylist");
    private List<String> alternativeName;
    private String curator;
    private String description;
    private List<String> era;
    private List<String> genre;
    private List<String> mood;
    private String musicTerritory;
    private ParentalControls parentalControls;
    private String seriesAsin;
    private List<RelatedTrack> video;

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
        if (!(document instanceof CatalogVideoPlaylist)) {
            return 1;
        }
        CatalogVideoPlaylist catalogVideoPlaylist = (CatalogVideoPlaylist) document;
        List<String> genre = getGenre();
        List<String> genre2 = catalogVideoPlaylist.getGenre();
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
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogVideoPlaylist.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo2 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String curator = getCurator();
        String curator2 = catalogVideoPlaylist.getCurator();
        if (curator != curator2) {
            if (curator == null) {
                return -1;
            }
            if (curator2 == null) {
                return 1;
            }
            int iCompareTo3 = curator.compareTo(curator2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String description = getDescription();
        String description2 = catalogVideoPlaylist.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo4 = description.compareTo(description2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> mood = getMood();
        List<String> mood2 = catalogVideoPlaylist.getMood();
        if (mood != mood2) {
            if (mood == null) {
                return -1;
            }
            if (mood2 == null) {
                return 1;
            }
            if (mood instanceof Comparable) {
                int iCompareTo5 = ((Comparable) mood).compareTo(mood2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        List<RelatedTrack> video = getVideo();
        List<RelatedTrack> video2 = catalogVideoPlaylist.getVideo();
        if (video != video2) {
            if (video == null) {
                return -1;
            }
            if (video2 == null) {
                return 1;
            }
            if (video instanceof Comparable) {
                int iCompareTo6 = ((Comparable) video).compareTo(video2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!video.equals(video2)) {
                int iHashCode5 = video.hashCode();
                int iHashCode6 = video2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String seriesAsin = getSeriesAsin();
        String seriesAsin2 = catalogVideoPlaylist.getSeriesAsin();
        if (seriesAsin != seriesAsin2) {
            if (seriesAsin == null) {
                return -1;
            }
            if (seriesAsin2 == null) {
                return 1;
            }
            int iCompareTo7 = seriesAsin.compareTo(seriesAsin2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<String> era = getEra();
        List<String> era2 = catalogVideoPlaylist.getEra();
        if (era != era2) {
            if (era == null) {
                return -1;
            }
            if (era2 == null) {
                return 1;
            }
            if (era instanceof Comparable) {
                int iCompareTo8 = ((Comparable) era).compareTo(era2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!era.equals(era2)) {
                int iHashCode7 = era.hashCode();
                int iHashCode8 = era2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = catalogVideoPlaylist.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo9 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = catalogVideoPlaylist.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo10 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
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
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogVideoPlaylist)) {
            return false;
        }
        CatalogVideoPlaylist catalogVideoPlaylist = (CatalogVideoPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getGenre(), catalogVideoPlaylist.getGenre()) && internalEqualityCheck(getParentalControls(), catalogVideoPlaylist.getParentalControls()) && internalEqualityCheck(getCurator(), catalogVideoPlaylist.getCurator()) && internalEqualityCheck(getDescription(), catalogVideoPlaylist.getDescription()) && internalEqualityCheck(getMood(), catalogVideoPlaylist.getMood()) && internalEqualityCheck(getVideo(), catalogVideoPlaylist.getVideo()) && internalEqualityCheck(getSeriesAsin(), catalogVideoPlaylist.getSeriesAsin()) && internalEqualityCheck(getEra(), catalogVideoPlaylist.getEra()) && internalEqualityCheck(getMusicTerritory(), catalogVideoPlaylist.getMusicTerritory()) && internalEqualityCheck(getAlternativeName(), catalogVideoPlaylist.getAlternativeName());
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

    public List<String> getMood() {
        return this.mood;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getSeriesAsin() {
        return this.seriesAsin;
    }

    public List<RelatedTrack> getVideo() {
        return this.video;
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getGenre(), getParentalControls(), getCurator(), getDescription(), getMood(), getVideo(), getSeriesAsin(), getEra(), getMusicTerritory(), getAlternativeName());
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

    public void setMood(List<String> list) {
        this.mood = list;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setSeriesAsin(String str) {
        this.seriesAsin = str;
    }

    public void setVideo(List<RelatedTrack> list) {
        this.video = list;
    }
}
