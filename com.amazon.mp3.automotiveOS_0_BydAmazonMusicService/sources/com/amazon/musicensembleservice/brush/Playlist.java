package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import com.amazon.musicensembleservice.brush.CatalogEntity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Playlist extends CatalogEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Playlist");
    private List<Artist> artists;
    private List<String> contentTiers;
    private List<String> contentTypes;
    private List<String> contributors;
    private String curator;
    private String description;
    private Long duration;
    private Image image;
    private Boolean isInLibrary;
    private Boolean isOwned;
    private ParentalControls parentalControls;
    private PlaymodeEligibility playmodeEligibility;
    private String seriesAsin;
    private String spiritualCategory;
    private String title;
    private List<Track> tracks;
    private Long tracksCount;

    public static class Builder extends CatalogEntity.Builder {
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
    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof Playlist)) {
            return 1;
        }
        Playlist playlist = (Playlist) entity;
        String curator = getCurator();
        String curator2 = playlist.getCurator();
        if (curator != curator2) {
            if (curator == null) {
                return -1;
            }
            if (curator2 == null) {
                return 1;
            }
            int iCompareTo = curator.compareTo(curator2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String description = getDescription();
        String description2 = playlist.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo2 = description.compareTo(description2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = playlist.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = playlist.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo4 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> contentTypes = getContentTypes();
        List<String> contentTypes2 = playlist.getContentTypes();
        if (contentTypes != contentTypes2) {
            if (contentTypes == null) {
                return -1;
            }
            if (contentTypes2 == null) {
                return 1;
            }
            if (contentTypes instanceof Comparable) {
                int iCompareTo5 = ((Comparable) contentTypes).compareTo(contentTypes2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!contentTypes.equals(contentTypes2)) {
                int iHashCode = contentTypes.hashCode();
                int iHashCode2 = contentTypes2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Long duration = getDuration();
        Long duration2 = playlist.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo6 = duration.compareTo(duration2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = playlist.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo7 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Long tracksCount = getTracksCount();
        Long tracksCount2 = playlist.getTracksCount();
        if (tracksCount != tracksCount2) {
            if (tracksCount == null) {
                return -1;
            }
            if (tracksCount2 == null) {
                return 1;
            }
            int iCompareTo8 = tracksCount.compareTo(tracksCount2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String seriesAsin = getSeriesAsin();
        String seriesAsin2 = playlist.getSeriesAsin();
        if (seriesAsin != seriesAsin2) {
            if (seriesAsin == null) {
                return -1;
            }
            if (seriesAsin2 == null) {
                return 1;
            }
            int iCompareTo9 = seriesAsin.compareTo(seriesAsin2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<Track> tracks = getTracks();
        List<Track> tracks2 = playlist.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo10 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode3 = tracks.hashCode();
                int iHashCode4 = tracks2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<String> contentTiers = getContentTiers();
        List<String> contentTiers2 = playlist.getContentTiers();
        if (contentTiers != contentTiers2) {
            if (contentTiers == null) {
                return -1;
            }
            if (contentTiers2 == null) {
                return 1;
            }
            if (contentTiers instanceof Comparable) {
                int iCompareTo11 = ((Comparable) contentTiers).compareTo(contentTiers2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!contentTiers.equals(contentTiers2)) {
                int iHashCode5 = contentTiers.hashCode();
                int iHashCode6 = contentTiers2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<String> contributors = getContributors();
        List<String> contributors2 = playlist.getContributors();
        if (contributors != contributors2) {
            if (contributors == null) {
                return -1;
            }
            if (contributors2 == null) {
                return 1;
            }
            if (contributors instanceof Comparable) {
                int iCompareTo12 = ((Comparable) contributors).compareTo(contributors2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!contributors.equals(contributors2)) {
                int iHashCode7 = contributors.hashCode();
                int iHashCode8 = contributors2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<Artist> artists = getArtists();
        List<Artist> artists2 = playlist.getArtists();
        if (artists != artists2) {
            if (artists == null) {
                return -1;
            }
            if (artists2 == null) {
                return 1;
            }
            if (artists instanceof Comparable) {
                int iCompareTo13 = ((Comparable) artists).compareTo(artists2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!artists.equals(artists2)) {
                int iHashCode9 = artists.hashCode();
                int iHashCode10 = artists2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        String spiritualCategory = getSpiritualCategory();
        String spiritualCategory2 = playlist.getSpiritualCategory();
        if (spiritualCategory != spiritualCategory2) {
            if (spiritualCategory == null) {
                return -1;
            }
            if (spiritualCategory2 == null) {
                return 1;
            }
            int iCompareTo14 = spiritualCategory.compareTo(spiritualCategory2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Image image = getImage();
        Image image2 = playlist.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo15 = image.compareTo(image2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Boolean boolIsIsOwned = isIsOwned();
        Boolean boolIsIsOwned2 = playlist.isIsOwned();
        if (boolIsIsOwned != boolIsIsOwned2) {
            if (boolIsIsOwned == null) {
                return -1;
            }
            if (boolIsIsOwned2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsIsOwned.compareTo(boolIsIsOwned2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String title = getTitle();
        String title2 = playlist.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo17 = title.compareTo(title2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Playlist)) {
            return false;
        }
        Playlist playlist = (Playlist) obj;
        return super.equals(obj) && internalEqualityCheck(getCurator(), playlist.getCurator()) && internalEqualityCheck(getDescription(), playlist.getDescription()) && internalEqualityCheck(isIsInLibrary(), playlist.isIsInLibrary()) && internalEqualityCheck(getParentalControls(), playlist.getParentalControls()) && internalEqualityCheck(getContentTypes(), playlist.getContentTypes()) && internalEqualityCheck(getDuration(), playlist.getDuration()) && internalEqualityCheck(getPlaymodeEligibility(), playlist.getPlaymodeEligibility()) && internalEqualityCheck(getTracksCount(), playlist.getTracksCount()) && internalEqualityCheck(getSeriesAsin(), playlist.getSeriesAsin()) && internalEqualityCheck(getTracks(), playlist.getTracks()) && internalEqualityCheck(getContentTiers(), playlist.getContentTiers()) && internalEqualityCheck(getContributors(), playlist.getContributors()) && internalEqualityCheck(getArtists(), playlist.getArtists()) && internalEqualityCheck(getSpiritualCategory(), playlist.getSpiritualCategory()) && internalEqualityCheck(getImage(), playlist.getImage()) && internalEqualityCheck(isIsOwned(), playlist.isIsOwned()) && internalEqualityCheck(getTitle(), playlist.getTitle());
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public List<String> getContentTypes() {
        return this.contentTypes;
    }

    public List<String> getContributors() {
        return this.contributors;
    }

    public String getCurator() {
        return this.curator;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getDuration() {
        return this.duration;
    }

    public Image getImage() {
        return this.image;
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

    public String getSpiritualCategory() {
        return this.spiritualCategory;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Track> getTracks() {
        return this.tracks;
    }

    public Long getTracksCount() {
        return this.tracksCount;
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCurator(), getDescription(), isIsInLibrary(), getParentalControls(), getContentTypes(), getDuration(), getPlaymodeEligibility(), getTracksCount(), getSeriesAsin(), getTracks(), getContentTiers(), getContributors(), getArtists(), getSpiritualCategory(), getImage(), isIsOwned(), getTitle());
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public Boolean isIsOwned() {
        return this.isOwned;
    }

    public void setArtists(List<Artist> list) {
        this.artists = list;
    }

    public void setContentTiers(List<String> list) {
        this.contentTiers = list;
    }

    public void setContentTypes(List<String> list) {
        this.contentTypes = list;
    }

    public void setContributors(List<String> list) {
        this.contributors = list;
    }

    public void setCurator(String str) {
        this.curator = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setIsInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    public void setIsOwned(Boolean bool) {
        this.isOwned = bool;
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

    public void setSpiritualCategory(String str) {
        this.spiritualCategory = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTracks(List<Track> list) {
        this.tracks = list;
    }

    public void setTracksCount(Long l) {
        this.tracksCount = l;
    }
}
