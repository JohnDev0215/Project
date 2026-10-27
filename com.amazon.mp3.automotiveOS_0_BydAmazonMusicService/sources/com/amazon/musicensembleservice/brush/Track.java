package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import com.amazon.musicensembleservice.brush.CatalogEntity;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Track extends CatalogEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Track");
    private Album album;
    private List<Artist> artists;
    private List<AssetQuality> assetQualities;
    private String assetType;
    private Commentary commentary;
    private String concatenatedArtistName;
    private List<String> contentEncoding;
    private List<String> contentTiers;
    private Long discNumber;
    private Long duration;
    private String genreName;
    private String globalAsin;
    private Boolean hasLyrics;
    private Image image;
    private String inLibraryId;
    private Boolean isInLibrary;
    private Boolean isOwned;
    private Boolean isTrending;
    private Date localOriginalReleaseDate;
    private String localTimeZone;
    private Date originalReleaseDate;
    private ParentalControls parentalControls;
    private PlaymodeEligibility playmodeEligibility;
    private Double popularity;
    private String shortTitle;
    private Date singlePromotionDate;
    private String spiritualCategory;
    private String title;
    private Long trackNumber;

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
        if (!(entity instanceof Track)) {
            return 1;
        }
        Track track = (Track) entity;
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = track.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long duration = getDuration();
        Long duration2 = track.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo2 = duration.compareTo(duration2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = track.getParentalControls();
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
        String shortTitle = getShortTitle();
        String shortTitle2 = track.getShortTitle();
        if (shortTitle != shortTitle2) {
            if (shortTitle == null) {
                return -1;
            }
            if (shortTitle2 == null) {
                return 1;
            }
            int iCompareTo4 = shortTitle.compareTo(shortTitle2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Album album = getAlbum();
        Album album2 = track.getAlbum();
        if (album != album2) {
            if (album == null) {
                return -1;
            }
            if (album2 == null) {
                return 1;
            }
            int iCompareTo5 = album.compareTo(album2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String localTimeZone = getLocalTimeZone();
        String localTimeZone2 = track.getLocalTimeZone();
        if (localTimeZone != localTimeZone2) {
            if (localTimeZone == null) {
                return -1;
            }
            if (localTimeZone2 == null) {
                return 1;
            }
            int iCompareTo6 = localTimeZone.compareTo(localTimeZone2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = track.getPlaymodeEligibility();
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
        String title = getTitle();
        String title2 = track.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo8 = title.compareTo(title2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Image image = getImage();
        Image image2 = track.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo9 = image.compareTo(image2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String spiritualCategory = getSpiritualCategory();
        String spiritualCategory2 = track.getSpiritualCategory();
        if (spiritualCategory != spiritualCategory2) {
            if (spiritualCategory == null) {
                return -1;
            }
            if (spiritualCategory2 == null) {
                return 1;
            }
            int iCompareTo10 = spiritualCategory.compareTo(spiritualCategory2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String assetType = getAssetType();
        String assetType2 = track.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo11 = assetType.compareTo(assetType2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Date originalReleaseDate = getOriginalReleaseDate();
        Date originalReleaseDate2 = track.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo12 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        List<Artist> artists = getArtists();
        List<Artist> artists2 = track.getArtists();
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
                int iHashCode = artists.hashCode();
                int iHashCode2 = artists2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = track.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo14 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode3 = contentEncoding.hashCode();
                int iHashCode4 = contentEncoding2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Date localOriginalReleaseDate = getLocalOriginalReleaseDate();
        Date localOriginalReleaseDate2 = track.getLocalOriginalReleaseDate();
        if (localOriginalReleaseDate != localOriginalReleaseDate2) {
            if (localOriginalReleaseDate == null) {
                return -1;
            }
            if (localOriginalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo15 = localOriginalReleaseDate.compareTo(localOriginalReleaseDate2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String genreName = getGenreName();
        String genreName2 = track.getGenreName();
        if (genreName != genreName2) {
            if (genreName == null) {
                return -1;
            }
            if (genreName2 == null) {
                return 1;
            }
            int iCompareTo16 = genreName.compareTo(genreName2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Boolean boolIsIsTrending = isIsTrending();
        Boolean boolIsIsTrending2 = track.isIsTrending();
        if (boolIsIsTrending != boolIsIsTrending2) {
            if (boolIsIsTrending == null) {
                return -1;
            }
            if (boolIsIsTrending2 == null) {
                return 1;
            }
            int iCompareTo17 = boolIsIsTrending.compareTo(boolIsIsTrending2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String globalAsin = getGlobalAsin();
        String globalAsin2 = track.getGlobalAsin();
        if (globalAsin != globalAsin2) {
            if (globalAsin == null) {
                return -1;
            }
            if (globalAsin2 == null) {
                return 1;
            }
            int iCompareTo18 = globalAsin.compareTo(globalAsin2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        Long discNumber = getDiscNumber();
        Long discNumber2 = track.getDiscNumber();
        if (discNumber != discNumber2) {
            if (discNumber == null) {
                return -1;
            }
            if (discNumber2 == null) {
                return 1;
            }
            int iCompareTo19 = discNumber.compareTo(discNumber2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String inLibraryId = getInLibraryId();
        String inLibraryId2 = track.getInLibraryId();
        if (inLibraryId != inLibraryId2) {
            if (inLibraryId == null) {
                return -1;
            }
            if (inLibraryId2 == null) {
                return 1;
            }
            int iCompareTo20 = inLibraryId.compareTo(inLibraryId2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        Long trackNumber = getTrackNumber();
        Long trackNumber2 = track.getTrackNumber();
        if (trackNumber != trackNumber2) {
            if (trackNumber == null) {
                return -1;
            }
            if (trackNumber2 == null) {
                return 1;
            }
            int iCompareTo21 = trackNumber.compareTo(trackNumber2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        Double popularity = getPopularity();
        Double popularity2 = track.getPopularity();
        if (popularity != popularity2) {
            if (popularity == null) {
                return -1;
            }
            if (popularity2 == null) {
                return 1;
            }
            int iCompareTo22 = popularity.compareTo(popularity2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        String concatenatedArtistName = getConcatenatedArtistName();
        String concatenatedArtistName2 = track.getConcatenatedArtistName();
        if (concatenatedArtistName != concatenatedArtistName2) {
            if (concatenatedArtistName == null) {
                return -1;
            }
            if (concatenatedArtistName2 == null) {
                return 1;
            }
            int iCompareTo23 = concatenatedArtistName.compareTo(concatenatedArtistName2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        List<AssetQuality> assetQualities = getAssetQualities();
        List<AssetQuality> assetQualities2 = track.getAssetQualities();
        if (assetQualities != assetQualities2) {
            if (assetQualities == null) {
                return -1;
            }
            if (assetQualities2 == null) {
                return 1;
            }
            if (assetQualities instanceof Comparable) {
                int iCompareTo24 = ((Comparable) assetQualities).compareTo(assetQualities2);
                if (iCompareTo24 != 0) {
                    return iCompareTo24;
                }
            } else if (!assetQualities.equals(assetQualities2)) {
                int iHashCode5 = assetQualities.hashCode();
                int iHashCode6 = assetQualities2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsOwned = isIsOwned();
        Boolean boolIsIsOwned2 = track.isIsOwned();
        if (boolIsIsOwned != boolIsIsOwned2) {
            if (boolIsIsOwned == null) {
                return -1;
            }
            if (boolIsIsOwned2 == null) {
                return 1;
            }
            int iCompareTo25 = boolIsIsOwned.compareTo(boolIsIsOwned2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        List<String> contentTiers = getContentTiers();
        List<String> contentTiers2 = track.getContentTiers();
        if (contentTiers != contentTiers2) {
            if (contentTiers == null) {
                return -1;
            }
            if (contentTiers2 == null) {
                return 1;
            }
            if (contentTiers instanceof Comparable) {
                int iCompareTo26 = ((Comparable) contentTiers).compareTo(contentTiers2);
                if (iCompareTo26 != 0) {
                    return iCompareTo26;
                }
            } else if (!contentTiers.equals(contentTiers2)) {
                int iHashCode7 = contentTiers.hashCode();
                int iHashCode8 = contentTiers2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        Boolean boolIsHasLyrics = isHasLyrics();
        Boolean boolIsHasLyrics2 = track.isHasLyrics();
        if (boolIsHasLyrics != boolIsHasLyrics2) {
            if (boolIsHasLyrics == null) {
                return -1;
            }
            if (boolIsHasLyrics2 == null) {
                return 1;
            }
            int iCompareTo27 = boolIsHasLyrics.compareTo(boolIsHasLyrics2);
            if (iCompareTo27 != 0) {
                return iCompareTo27;
            }
        }
        Commentary commentary = getCommentary();
        Commentary commentary2 = track.getCommentary();
        if (commentary != commentary2) {
            if (commentary == null) {
                return -1;
            }
            if (commentary2 == null) {
                return 1;
            }
            int iCompareTo28 = commentary.compareTo(commentary2);
            if (iCompareTo28 != 0) {
                return iCompareTo28;
            }
        }
        Date singlePromotionDate = getSinglePromotionDate();
        Date singlePromotionDate2 = track.getSinglePromotionDate();
        if (singlePromotionDate != singlePromotionDate2) {
            if (singlePromotionDate == null) {
                return -1;
            }
            if (singlePromotionDate2 == null) {
                return 1;
            }
            int iCompareTo29 = singlePromotionDate.compareTo(singlePromotionDate2);
            if (iCompareTo29 != 0) {
                return iCompareTo29;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) {
            return false;
        }
        Track track = (Track) obj;
        return super.equals(obj) && internalEqualityCheck(isIsInLibrary(), track.isIsInLibrary()) && internalEqualityCheck(getDuration(), track.getDuration()) && internalEqualityCheck(getParentalControls(), track.getParentalControls()) && internalEqualityCheck(getShortTitle(), track.getShortTitle()) && internalEqualityCheck(getAlbum(), track.getAlbum()) && internalEqualityCheck(getLocalTimeZone(), track.getLocalTimeZone()) && internalEqualityCheck(getPlaymodeEligibility(), track.getPlaymodeEligibility()) && internalEqualityCheck(getTitle(), track.getTitle()) && internalEqualityCheck(getImage(), track.getImage()) && internalEqualityCheck(getSpiritualCategory(), track.getSpiritualCategory()) && internalEqualityCheck(getAssetType(), track.getAssetType()) && internalEqualityCheck(getOriginalReleaseDate(), track.getOriginalReleaseDate()) && internalEqualityCheck(getArtists(), track.getArtists()) && internalEqualityCheck(getContentEncoding(), track.getContentEncoding()) && internalEqualityCheck(getLocalOriginalReleaseDate(), track.getLocalOriginalReleaseDate()) && internalEqualityCheck(getGenreName(), track.getGenreName()) && internalEqualityCheck(isIsTrending(), track.isIsTrending()) && internalEqualityCheck(getGlobalAsin(), track.getGlobalAsin()) && internalEqualityCheck(getDiscNumber(), track.getDiscNumber()) && internalEqualityCheck(getInLibraryId(), track.getInLibraryId()) && internalEqualityCheck(getTrackNumber(), track.getTrackNumber()) && internalEqualityCheck(getPopularity(), track.getPopularity()) && internalEqualityCheck(getConcatenatedArtistName(), track.getConcatenatedArtistName()) && internalEqualityCheck(getAssetQualities(), track.getAssetQualities()) && internalEqualityCheck(isIsOwned(), track.isIsOwned()) && internalEqualityCheck(getContentTiers(), track.getContentTiers()) && internalEqualityCheck(isHasLyrics(), track.isHasLyrics()) && internalEqualityCheck(getCommentary(), track.getCommentary()) && internalEqualityCheck(getSinglePromotionDate(), track.getSinglePromotionDate());
    }

    public Album getAlbum() {
        return this.album;
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }

    public String getAssetType() {
        return this.assetType;
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

    public Long getDiscNumber() {
        return this.discNumber;
    }

    public Long getDuration() {
        return this.duration;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public String getGlobalAsin() {
        return this.globalAsin;
    }

    public Image getImage() {
        return this.image;
    }

    public String getInLibraryId() {
        return this.inLibraryId;
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

    public Double getPopularity() {
        return this.popularity;
    }

    public String getShortTitle() {
        return this.shortTitle;
    }

    public Date getSinglePromotionDate() {
        return this.singlePromotionDate;
    }

    public String getSpiritualCategory() {
        return this.spiritualCategory;
    }

    public String getTitle() {
        return this.title;
    }

    public Long getTrackNumber() {
        return this.trackNumber;
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIsInLibrary(), getDuration(), getParentalControls(), getShortTitle(), getAlbum(), getLocalTimeZone(), getPlaymodeEligibility(), getTitle(), getImage(), getSpiritualCategory(), getAssetType(), getOriginalReleaseDate(), getArtists(), getContentEncoding(), getLocalOriginalReleaseDate(), getGenreName(), isIsTrending(), getGlobalAsin(), getDiscNumber(), getInLibraryId(), getTrackNumber(), getPopularity(), getConcatenatedArtistName(), getAssetQualities(), isIsOwned(), getContentTiers(), isHasLyrics(), getCommentary(), getSinglePromotionDate());
    }

    public Boolean isHasLyrics() {
        return this.hasLyrics;
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public Boolean isIsOwned() {
        return this.isOwned;
    }

    public Boolean isIsTrending() {
        return this.isTrending;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setArtists(List<Artist> list) {
        this.artists = list;
    }

    public void setAssetQualities(List<AssetQuality> list) {
        this.assetQualities = list;
    }

    public void setAssetType(String str) {
        this.assetType = str;
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

    public void setDiscNumber(Long l) {
        this.discNumber = l;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setGenreName(String str) {
        this.genreName = str;
    }

    public void setGlobalAsin(String str) {
        this.globalAsin = str;
    }

    public void setHasLyrics(Boolean bool) {
        this.hasLyrics = bool;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setInLibraryId(String str) {
        this.inLibraryId = str;
    }

    public void setIsInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    public void setIsOwned(Boolean bool) {
        this.isOwned = bool;
    }

    public void setIsTrending(Boolean bool) {
        this.isTrending = bool;
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

    public void setPopularity(Double d) {
        this.popularity = d;
    }

    public void setShortTitle(String str) {
        this.shortTitle = str;
    }

    public void setSinglePromotionDate(Date date) {
        this.singlePromotionDate = date;
    }

    public void setSpiritualCategory(String str) {
        this.spiritualCategory = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackNumber(Long l) {
        this.trackNumber = l;
    }
}
