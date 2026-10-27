package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import com.amazon.musicensembleservice.brush.CatalogEntity;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Album extends CatalogEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Album");
    private List<Artist> artists;
    private Commentary commentary;
    private String concatenatedArtistName;
    private List<String> contentEncoding;
    private List<String> contentTiers;
    private List<String> contentTypes;
    private String copyright;
    private Long duration;
    private Boolean hasCommentary;
    private Image image;
    private Boolean isInLibrary;
    private Boolean isOwned;
    private Date localOriginalReleaseDate;
    private String localTimeZone;
    private Date originalReleaseDate;
    private ParentalControls parentalControls;
    private PlaymodeEligibility playmodeEligibility;
    private String releaseType;
    private String spiritualCategory;
    private String title;
    private String titleSetAsin;
    private List<Track> tracks;
    private Long tracksCount;
    private List<String> versions;

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
        if (!(entity instanceof Album)) {
            return 1;
        }
        Album album = (Album) entity;
        String concatenatedArtistName = getConcatenatedArtistName();
        String concatenatedArtistName2 = album.getConcatenatedArtistName();
        if (concatenatedArtistName != concatenatedArtistName2) {
            if (concatenatedArtistName == null) {
                return -1;
            }
            if (concatenatedArtistName2 == null) {
                return 1;
            }
            int iCompareTo = concatenatedArtistName.compareTo(concatenatedArtistName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> versions = getVersions();
        List<String> versions2 = album.getVersions();
        if (versions != versions2) {
            if (versions == null) {
                return -1;
            }
            if (versions2 == null) {
                return 1;
            }
            if (versions instanceof Comparable) {
                int iCompareTo2 = ((Comparable) versions).compareTo(versions2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!versions.equals(versions2)) {
                int iHashCode = versions.hashCode();
                int iHashCode2 = versions2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Date originalReleaseDate = getOriginalReleaseDate();
        Date originalReleaseDate2 = album.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo3 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = album.getParentalControls();
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
        Boolean boolIsHasCommentary = isHasCommentary();
        Boolean boolIsHasCommentary2 = album.isHasCommentary();
        if (boolIsHasCommentary != boolIsHasCommentary2) {
            if (boolIsHasCommentary == null) {
                return -1;
            }
            if (boolIsHasCommentary2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsHasCommentary.compareTo(boolIsHasCommentary2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = album.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Date localOriginalReleaseDate = getLocalOriginalReleaseDate();
        Date localOriginalReleaseDate2 = album.getLocalOriginalReleaseDate();
        if (localOriginalReleaseDate != localOriginalReleaseDate2) {
            if (localOriginalReleaseDate == null) {
                return -1;
            }
            if (localOriginalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo7 = localOriginalReleaseDate.compareTo(localOriginalReleaseDate2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String releaseType = getReleaseType();
        String releaseType2 = album.getReleaseType();
        if (releaseType != releaseType2) {
            if (releaseType == null) {
                return -1;
            }
            if (releaseType2 == null) {
                return 1;
            }
            int iCompareTo8 = releaseType.compareTo(releaseType2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String localTimeZone = getLocalTimeZone();
        String localTimeZone2 = album.getLocalTimeZone();
        if (localTimeZone != localTimeZone2) {
            if (localTimeZone == null) {
                return -1;
            }
            if (localTimeZone2 == null) {
                return 1;
            }
            int iCompareTo9 = localTimeZone.compareTo(localTimeZone2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<Artist> artists = getArtists();
        List<Artist> artists2 = album.getArtists();
        if (artists != artists2) {
            if (artists == null) {
                return -1;
            }
            if (artists2 == null) {
                return 1;
            }
            if (artists instanceof Comparable) {
                int iCompareTo10 = ((Comparable) artists).compareTo(artists2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!artists.equals(artists2)) {
                int iHashCode3 = artists.hashCode();
                int iHashCode4 = artists2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<String> contentTypes = getContentTypes();
        List<String> contentTypes2 = album.getContentTypes();
        if (contentTypes != contentTypes2) {
            if (contentTypes == null) {
                return -1;
            }
            if (contentTypes2 == null) {
                return 1;
            }
            if (contentTypes instanceof Comparable) {
                int iCompareTo11 = ((Comparable) contentTypes).compareTo(contentTypes2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!contentTypes.equals(contentTypes2)) {
                int iHashCode5 = contentTypes.hashCode();
                int iHashCode6 = contentTypes2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = album.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo12 = title.compareTo(title2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = album.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo13 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode7 = contentEncoding.hashCode();
                int iHashCode8 = contentEncoding2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        Commentary commentary = getCommentary();
        Commentary commentary2 = album.getCommentary();
        if (commentary != commentary2) {
            if (commentary == null) {
                return -1;
            }
            if (commentary2 == null) {
                return 1;
            }
            int iCompareTo14 = commentary.compareTo(commentary2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Boolean boolIsIsOwned = isIsOwned();
        Boolean boolIsIsOwned2 = album.isIsOwned();
        if (boolIsIsOwned != boolIsIsOwned2) {
            if (boolIsIsOwned == null) {
                return -1;
            }
            if (boolIsIsOwned2 == null) {
                return 1;
            }
            int iCompareTo15 = boolIsIsOwned.compareTo(boolIsIsOwned2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Image image = getImage();
        Image image2 = album.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo16 = image.compareTo(image2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        List<Track> tracks = getTracks();
        List<Track> tracks2 = album.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo17 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode9 = tracks.hashCode();
                int iHashCode10 = tracks2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        Long tracksCount = getTracksCount();
        Long tracksCount2 = album.getTracksCount();
        if (tracksCount != tracksCount2) {
            if (tracksCount == null) {
                return -1;
            }
            if (tracksCount2 == null) {
                return 1;
            }
            int iCompareTo18 = tracksCount.compareTo(tracksCount2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = album.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo19 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String copyright = getCopyright();
        String copyright2 = album.getCopyright();
        if (copyright != copyright2) {
            if (copyright == null) {
                return -1;
            }
            if (copyright2 == null) {
                return 1;
            }
            int iCompareTo20 = copyright.compareTo(copyright2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        Long duration = getDuration();
        Long duration2 = album.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo21 = duration.compareTo(duration2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String titleSetAsin = getTitleSetAsin();
        String titleSetAsin2 = album.getTitleSetAsin();
        if (titleSetAsin != titleSetAsin2) {
            if (titleSetAsin == null) {
                return -1;
            }
            if (titleSetAsin2 == null) {
                return 1;
            }
            int iCompareTo22 = titleSetAsin.compareTo(titleSetAsin2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        String spiritualCategory = getSpiritualCategory();
        String spiritualCategory2 = album.getSpiritualCategory();
        if (spiritualCategory != spiritualCategory2) {
            if (spiritualCategory == null) {
                return -1;
            }
            if (spiritualCategory2 == null) {
                return 1;
            }
            int iCompareTo23 = spiritualCategory.compareTo(spiritualCategory2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        List<String> contentTiers = getContentTiers();
        List<String> contentTiers2 = album.getContentTiers();
        if (contentTiers != contentTiers2) {
            if (contentTiers == null) {
                return -1;
            }
            if (contentTiers2 == null) {
                return 1;
            }
            if (contentTiers instanceof Comparable) {
                int iCompareTo24 = ((Comparable) contentTiers).compareTo(contentTiers2);
                if (iCompareTo24 != 0) {
                    return iCompareTo24;
                }
            } else if (!contentTiers.equals(contentTiers2)) {
                int iHashCode11 = contentTiers.hashCode();
                int iHashCode12 = contentTiers2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        return super.equals(obj) && internalEqualityCheck(getConcatenatedArtistName(), album.getConcatenatedArtistName()) && internalEqualityCheck(getVersions(), album.getVersions()) && internalEqualityCheck(getOriginalReleaseDate(), album.getOriginalReleaseDate()) && internalEqualityCheck(getParentalControls(), album.getParentalControls()) && internalEqualityCheck(isHasCommentary(), album.isHasCommentary()) && internalEqualityCheck(isIsInLibrary(), album.isIsInLibrary()) && internalEqualityCheck(getLocalOriginalReleaseDate(), album.getLocalOriginalReleaseDate()) && internalEqualityCheck(getReleaseType(), album.getReleaseType()) && internalEqualityCheck(getLocalTimeZone(), album.getLocalTimeZone()) && internalEqualityCheck(getArtists(), album.getArtists()) && internalEqualityCheck(getContentTypes(), album.getContentTypes()) && internalEqualityCheck(getTitle(), album.getTitle()) && internalEqualityCheck(getContentEncoding(), album.getContentEncoding()) && internalEqualityCheck(getCommentary(), album.getCommentary()) && internalEqualityCheck(isIsOwned(), album.isIsOwned()) && internalEqualityCheck(getImage(), album.getImage()) && internalEqualityCheck(getTracks(), album.getTracks()) && internalEqualityCheck(getTracksCount(), album.getTracksCount()) && internalEqualityCheck(getPlaymodeEligibility(), album.getPlaymodeEligibility()) && internalEqualityCheck(getCopyright(), album.getCopyright()) && internalEqualityCheck(getDuration(), album.getDuration()) && internalEqualityCheck(getTitleSetAsin(), album.getTitleSetAsin()) && internalEqualityCheck(getSpiritualCategory(), album.getSpiritualCategory()) && internalEqualityCheck(getContentTiers(), album.getContentTiers());
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public Commentary getCommentary() {
        return this.commentary;
    }

    public String getConcatenatedArtistName() {
        return this.concatenatedArtistName;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public List<String> getContentTypes() {
        return this.contentTypes;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public Long getDuration() {
        return this.duration;
    }

    public Image getImage() {
        return this.image;
    }

    public Date getLocalOriginalReleaseDate() {
        return this.localOriginalReleaseDate;
    }

    public String getLocalTimeZone() {
        return this.localTimeZone;
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

    public String getReleaseType() {
        return this.releaseType;
    }

    public String getSpiritualCategory() {
        return this.spiritualCategory;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleSetAsin() {
        return this.titleSetAsin;
    }

    public List<Track> getTracks() {
        return this.tracks;
    }

    public Long getTracksCount() {
        return this.tracksCount;
    }

    public List<String> getVersions() {
        return this.versions;
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getConcatenatedArtistName(), getVersions(), getOriginalReleaseDate(), getParentalControls(), isHasCommentary(), isIsInLibrary(), getLocalOriginalReleaseDate(), getReleaseType(), getLocalTimeZone(), getArtists(), getContentTypes(), getTitle(), getContentEncoding(), getCommentary(), isIsOwned(), getImage(), getTracks(), getTracksCount(), getPlaymodeEligibility(), getCopyright(), getDuration(), getTitleSetAsin(), getSpiritualCategory(), getContentTiers());
    }

    public Boolean isHasCommentary() {
        return this.hasCommentary;
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

    public void setCommentary(Commentary commentary) {
        this.commentary = commentary;
    }

    public void setConcatenatedArtistName(String str) {
        this.concatenatedArtistName = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setContentTiers(List<String> list) {
        this.contentTiers = list;
    }

    public void setContentTypes(List<String> list) {
        this.contentTypes = list;
    }

    public void setCopyright(String str) {
        this.copyright = str;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setHasCommentary(Boolean bool) {
        this.hasCommentary = bool;
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

    public void setLocalOriginalReleaseDate(Date date) {
        this.localOriginalReleaseDate = date;
    }

    public void setLocalTimeZone(String str) {
        this.localTimeZone = str;
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

    public void setReleaseType(String str) {
        this.releaseType = str;
    }

    public void setSpiritualCategory(String str) {
        this.spiritualCategory = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleSetAsin(String str) {
        this.titleSetAsin = str;
    }

    public void setTracks(List<Track> list) {
        this.tracks = list;
    }

    public void setTracksCount(Long l) {
        this.tracksCount = l;
    }

    public void setVersions(List<String> list) {
        this.versions = list;
    }
}
