package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistItem implements Comparable<PlaylistItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.PlaylistItem");
    private String asin;
    private List<String> authors;
    private String bannerImage;
    private List<String> contentTypes;
    private String curatedBy;
    private List<CurationDescriptorItem> curationDescriptor;
    private String description;
    private Integer duration;
    private String image;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isInLibrary;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Boolean isSonicRushGolden;
    private Boolean isSonicRushOnDemandPlayable;
    private Boolean isTwitchRadio;
    private Integer itemIndex;
    private String languagesOfPerformance;
    private Boolean owned;
    private PlaymodeEligibility playmodeEligibility;
    private String primaryGenre;
    private List<RelatedEntityItem> relatedEntities;
    private String requestedAsin;
    private Review reviews;
    private String seriesAsin;
    private String spiritualCategory;
    private String title;
    private Integer trackCount;
    private List<TrackItem> tracks;
    private String type;

    public static class Builder {
        protected String asin;
        protected List<String> authors;
        protected String bannerImage;
        protected List<String> contentTypes;
        protected String curatedBy;
        protected List<CurationDescriptorItem> curationDescriptor;
        protected String description;
        protected Integer duration;
        protected String image;
        protected Boolean isFree;
        protected Boolean isFreeOnDemandPlayable;
        protected Boolean isInLibrary;
        protected Boolean isMusicSubscription;
        protected Boolean isPrime;
        protected Boolean isSonicRush;
        protected Boolean isSonicRushGolden;
        protected Boolean isSonicRushOnDemandPlayable;
        protected Boolean isTwitchRadio;
        protected Integer itemIndex;
        protected String languagesOfPerformance;
        protected Boolean owned;
        protected PlaymodeEligibility playmodeEligibility;
        protected String primaryGenre;
        protected List<RelatedEntityItem> relatedEntities;
        protected String requestedAsin;
        protected Review reviews;
        protected String seriesAsin;
        protected String spiritualCategory;
        protected String title;
        protected Integer trackCount;
        protected List<TrackItem> tracks;
        protected String type;

        public PlaylistItem build() {
            PlaylistItem playlistItem = new PlaylistItem();
            populate(playlistItem);
            return playlistItem;
        }

        protected void populate(PlaylistItem playlistItem) {
            playlistItem.setRequestedAsin(this.requestedAsin);
            playlistItem.setCuratedBy(this.curatedBy);
            playlistItem.setIsTwitchRadio(this.isTwitchRadio);
            playlistItem.setLanguagesOfPerformance(this.languagesOfPerformance);
            playlistItem.setIsMusicSubscription(this.isMusicSubscription);
            playlistItem.setSeriesAsin(this.seriesAsin);
            playlistItem.setIsSonicRushOnDemandPlayable(this.isSonicRushOnDemandPlayable);
            playlistItem.setIsPrime(this.isPrime);
            playlistItem.setAsin(this.asin);
            playlistItem.setReviews(this.reviews);
            playlistItem.setPrimaryGenre(this.primaryGenre);
            playlistItem.setOwned(this.owned);
            playlistItem.setBannerImage(this.bannerImage);
            playlistItem.setTracks(this.tracks);
            playlistItem.setDescription(this.description);
            playlistItem.setIsInLibrary(this.isInLibrary);
            playlistItem.setRelatedEntities(this.relatedEntities);
            playlistItem.setIsSonicRush(this.isSonicRush);
            playlistItem.setItemIndex(this.itemIndex);
            playlistItem.setTrackCount(this.trackCount);
            playlistItem.setIsFree(this.isFree);
            playlistItem.setSpiritualCategory(this.spiritualCategory);
            playlistItem.setDuration(this.duration);
            playlistItem.setContentTypes(this.contentTypes);
            playlistItem.setType(this.type);
            playlistItem.setIsFreeOnDemandPlayable(this.isFreeOnDemandPlayable);
            playlistItem.setIsSonicRushGolden(this.isSonicRushGolden);
            playlistItem.setAuthors(this.authors);
            playlistItem.setCurationDescriptor(this.curationDescriptor);
            playlistItem.setPlaymodeEligibility(this.playmodeEligibility);
            playlistItem.setImage(this.image);
            playlistItem.setTitle(this.title);
        }

        public Builder withAsin(String str) {
            this.asin = str;
            return this;
        }

        public Builder withBannerImage(String str) {
            this.bannerImage = str;
            return this;
        }

        public Builder withCuratedBy(String str) {
            this.curatedBy = str;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withDuration(Integer num) {
            this.duration = num;
            return this;
        }

        public Builder withIsFree(Boolean bool) {
            this.isFree = bool;
            return this;
        }

        public Builder withIsFreeOnDemandPlayable(Boolean bool) {
            this.isFreeOnDemandPlayable = bool;
            return this;
        }

        public Builder withIsInLibrary(Boolean bool) {
            this.isInLibrary = bool;
            return this;
        }

        public Builder withIsPrime(Boolean bool) {
            this.isPrime = bool;
            return this;
        }

        public Builder withOwned(Boolean bool) {
            this.owned = bool;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder withTrackCount(Integer num) {
            this.trackCount = num;
            return this;
        }

        public Builder withTracks(List<TrackItem> list) {
            this.tracks = list;
            return this;
        }

        public Builder withType(String str) {
            this.type = str;
            return this;
        }
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaylistItem playlistItem) {
        if (playlistItem == null) {
            return -1;
        }
        if (playlistItem == this) {
            return 0;
        }
        String requestedAsin = getRequestedAsin();
        String requestedAsin2 = playlistItem.getRequestedAsin();
        if (requestedAsin != requestedAsin2) {
            if (requestedAsin == null) {
                return -1;
            }
            if (requestedAsin2 == null) {
                return 1;
            }
            int iCompareTo = requestedAsin.compareTo(requestedAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = playlistItem.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo2 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsIsTwitchRadio = isIsTwitchRadio();
        Boolean boolIsIsTwitchRadio2 = playlistItem.isIsTwitchRadio();
        if (boolIsIsTwitchRadio != boolIsIsTwitchRadio2) {
            if (boolIsIsTwitchRadio == null) {
                return -1;
            }
            if (boolIsIsTwitchRadio2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsTwitchRadio.compareTo(boolIsIsTwitchRadio2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String languagesOfPerformance = getLanguagesOfPerformance();
        String languagesOfPerformance2 = playlistItem.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo4 = languagesOfPerformance.compareTo(languagesOfPerformance2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = playlistItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String seriesAsin = getSeriesAsin();
        String seriesAsin2 = playlistItem.getSeriesAsin();
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
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = playlistItem.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = playlistItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String asin = getAsin();
        String asin2 = playlistItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo9 = asin.compareTo(asin2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Review reviews = getReviews();
        Review reviews2 = playlistItem.getReviews();
        if (reviews != reviews2) {
            if (reviews == null) {
                return -1;
            }
            if (reviews2 == null) {
                return 1;
            }
            int iCompareTo10 = reviews.compareTo(reviews2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = playlistItem.getPrimaryGenre();
        if (primaryGenre != primaryGenre2) {
            if (primaryGenre == null) {
                return -1;
            }
            if (primaryGenre2 == null) {
                return 1;
            }
            int iCompareTo11 = primaryGenre.compareTo(primaryGenre2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Boolean boolIsOwned = isOwned();
        Boolean boolIsOwned2 = playlistItem.isOwned();
        if (boolIsOwned != boolIsOwned2) {
            if (boolIsOwned == null) {
                return -1;
            }
            if (boolIsOwned2 == null) {
                return 1;
            }
            int iCompareTo12 = boolIsOwned.compareTo(boolIsOwned2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String bannerImage = getBannerImage();
        String bannerImage2 = playlistItem.getBannerImage();
        if (bannerImage != bannerImage2) {
            if (bannerImage == null) {
                return -1;
            }
            if (bannerImage2 == null) {
                return 1;
            }
            int iCompareTo13 = bannerImage.compareTo(bannerImage2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        List<TrackItem> tracks = getTracks();
        List<TrackItem> tracks2 = playlistItem.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo14 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode = tracks.hashCode();
                int iHashCode2 = tracks2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String description = getDescription();
        String description2 = playlistItem.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo15 = description.compareTo(description2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = playlistItem.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        List<RelatedEntityItem> relatedEntities = getRelatedEntities();
        List<RelatedEntityItem> relatedEntities2 = playlistItem.getRelatedEntities();
        if (relatedEntities != relatedEntities2) {
            if (relatedEntities == null) {
                return -1;
            }
            if (relatedEntities2 == null) {
                return 1;
            }
            if (relatedEntities instanceof Comparable) {
                int iCompareTo17 = ((Comparable) relatedEntities).compareTo(relatedEntities2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!relatedEntities.equals(relatedEntities2)) {
                int iHashCode3 = relatedEntities.hashCode();
                int iHashCode4 = relatedEntities2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = playlistItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo18 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        Integer itemIndex = getItemIndex();
        Integer itemIndex2 = playlistItem.getItemIndex();
        if (itemIndex != itemIndex2) {
            if (itemIndex == null) {
                return -1;
            }
            if (itemIndex2 == null) {
                return 1;
            }
            int iCompareTo19 = itemIndex.compareTo(itemIndex2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        Integer trackCount = getTrackCount();
        Integer trackCount2 = playlistItem.getTrackCount();
        if (trackCount != trackCount2) {
            if (trackCount == null) {
                return -1;
            }
            if (trackCount2 == null) {
                return 1;
            }
            int iCompareTo20 = trackCount.compareTo(trackCount2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = playlistItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo21 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String spiritualCategory = getSpiritualCategory();
        String spiritualCategory2 = playlistItem.getSpiritualCategory();
        if (spiritualCategory != spiritualCategory2) {
            if (spiritualCategory == null) {
                return -1;
            }
            if (spiritualCategory2 == null) {
                return 1;
            }
            int iCompareTo22 = spiritualCategory.compareTo(spiritualCategory2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = playlistItem.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo23 = duration.compareTo(duration2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        List<String> contentTypes = getContentTypes();
        List<String> contentTypes2 = playlistItem.getContentTypes();
        if (contentTypes != contentTypes2) {
            if (contentTypes == null) {
                return -1;
            }
            if (contentTypes2 == null) {
                return 1;
            }
            if (contentTypes instanceof Comparable) {
                int iCompareTo24 = ((Comparable) contentTypes).compareTo(contentTypes2);
                if (iCompareTo24 != 0) {
                    return iCompareTo24;
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
        String type = getType();
        String type2 = playlistItem.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo25 = type.compareTo(type2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = playlistItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo26 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        Boolean boolIsIsSonicRushGolden = isIsSonicRushGolden();
        Boolean boolIsIsSonicRushGolden2 = playlistItem.isIsSonicRushGolden();
        if (boolIsIsSonicRushGolden != boolIsIsSonicRushGolden2) {
            if (boolIsIsSonicRushGolden == null) {
                return -1;
            }
            if (boolIsIsSonicRushGolden2 == null) {
                return 1;
            }
            int iCompareTo27 = boolIsIsSonicRushGolden.compareTo(boolIsIsSonicRushGolden2);
            if (iCompareTo27 != 0) {
                return iCompareTo27;
            }
        }
        List<String> authors = getAuthors();
        List<String> authors2 = playlistItem.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo28 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo28 != 0) {
                    return iCompareTo28;
                }
            } else if (!authors.equals(authors2)) {
                int iHashCode7 = authors.hashCode();
                int iHashCode8 = authors2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<CurationDescriptorItem> curationDescriptor = getCurationDescriptor();
        List<CurationDescriptorItem> curationDescriptor2 = playlistItem.getCurationDescriptor();
        if (curationDescriptor != curationDescriptor2) {
            if (curationDescriptor == null) {
                return -1;
            }
            if (curationDescriptor2 == null) {
                return 1;
            }
            if (curationDescriptor instanceof Comparable) {
                int iCompareTo29 = ((Comparable) curationDescriptor).compareTo(curationDescriptor2);
                if (iCompareTo29 != 0) {
                    return iCompareTo29;
                }
            } else if (!curationDescriptor.equals(curationDescriptor2)) {
                int iHashCode9 = curationDescriptor.hashCode();
                int iHashCode10 = curationDescriptor2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = playlistItem.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo30 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo30 != 0) {
                return iCompareTo30;
            }
        }
        String image = getImage();
        String image2 = playlistItem.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo31 = image.compareTo(image2);
            if (iCompareTo31 != 0) {
                return iCompareTo31;
            }
        }
        String title = getTitle();
        String title2 = playlistItem.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo32 = title.compareTo(title2);
            if (iCompareTo32 != 0) {
                return iCompareTo32;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistItem)) {
            return false;
        }
        PlaylistItem playlistItem = (PlaylistItem) obj;
        return internalEqualityCheck(getRequestedAsin(), playlistItem.getRequestedAsin()) && internalEqualityCheck(getCuratedBy(), playlistItem.getCuratedBy()) && internalEqualityCheck(isIsTwitchRadio(), playlistItem.isIsTwitchRadio()) && internalEqualityCheck(getLanguagesOfPerformance(), playlistItem.getLanguagesOfPerformance()) && internalEqualityCheck(isIsMusicSubscription(), playlistItem.isIsMusicSubscription()) && internalEqualityCheck(getSeriesAsin(), playlistItem.getSeriesAsin()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), playlistItem.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(isIsPrime(), playlistItem.isIsPrime()) && internalEqualityCheck(getAsin(), playlistItem.getAsin()) && internalEqualityCheck(getReviews(), playlistItem.getReviews()) && internalEqualityCheck(getPrimaryGenre(), playlistItem.getPrimaryGenre()) && internalEqualityCheck(isOwned(), playlistItem.isOwned()) && internalEqualityCheck(getBannerImage(), playlistItem.getBannerImage()) && internalEqualityCheck(getTracks(), playlistItem.getTracks()) && internalEqualityCheck(getDescription(), playlistItem.getDescription()) && internalEqualityCheck(isIsInLibrary(), playlistItem.isIsInLibrary()) && internalEqualityCheck(getRelatedEntities(), playlistItem.getRelatedEntities()) && internalEqualityCheck(isIsSonicRush(), playlistItem.isIsSonicRush()) && internalEqualityCheck(getItemIndex(), playlistItem.getItemIndex()) && internalEqualityCheck(getTrackCount(), playlistItem.getTrackCount()) && internalEqualityCheck(isIsFree(), playlistItem.isIsFree()) && internalEqualityCheck(getSpiritualCategory(), playlistItem.getSpiritualCategory()) && internalEqualityCheck(getDuration(), playlistItem.getDuration()) && internalEqualityCheck(getContentTypes(), playlistItem.getContentTypes()) && internalEqualityCheck(getType(), playlistItem.getType()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), playlistItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(isIsSonicRushGolden(), playlistItem.isIsSonicRushGolden()) && internalEqualityCheck(getAuthors(), playlistItem.getAuthors()) && internalEqualityCheck(getCurationDescriptor(), playlistItem.getCurationDescriptor()) && internalEqualityCheck(getPlaymodeEligibility(), playlistItem.getPlaymodeEligibility()) && internalEqualityCheck(getImage(), playlistItem.getImage()) && internalEqualityCheck(getTitle(), playlistItem.getTitle());
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String getBannerImage() {
        return this.bannerImage;
    }

    public List<String> getContentTypes() {
        return this.contentTypes;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public List<CurationDescriptorItem> getCurationDescriptor() {
        return this.curationDescriptor;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getImage() {
        return this.image;
    }

    public Integer getItemIndex() {
        return this.itemIndex;
    }

    public String getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getPrimaryGenre() {
        return this.primaryGenre;
    }

    public List<RelatedEntityItem> getRelatedEntities() {
        return this.relatedEntities;
    }

    public String getRequestedAsin() {
        return this.requestedAsin;
    }

    public Review getReviews() {
        return this.reviews;
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

    public Integer getTrackCount() {
        return this.trackCount;
    }

    public List<TrackItem> getTracks() {
        return this.tracks;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRequestedAsin(), getCuratedBy(), isIsTwitchRadio(), getLanguagesOfPerformance(), isIsMusicSubscription(), getSeriesAsin(), isIsSonicRushOnDemandPlayable(), isIsPrime(), getAsin(), getReviews(), getPrimaryGenre(), isOwned(), getBannerImage(), getTracks(), getDescription(), isIsInLibrary(), getRelatedEntities(), isIsSonicRush(), getItemIndex(), getTrackCount(), isIsFree(), getSpiritualCategory(), getDuration(), getContentTypes(), getType(), isIsFreeOnDemandPlayable(), isIsSonicRushGolden(), getAuthors(), getCurationDescriptor(), getPlaymodeEligibility(), getImage(), getTitle());
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean isIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public Boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public Boolean isIsPrime() {
        return this.isPrime;
    }

    public Boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public Boolean isIsSonicRushGolden() {
        return this.isSonicRushGolden;
    }

    public Boolean isIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public Boolean isIsTwitchRadio() {
        return this.isTwitchRadio;
    }

    public Boolean isOwned() {
        return this.owned;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setAuthors(List<String> list) {
        this.authors = list;
    }

    public void setBannerImage(String str) {
        this.bannerImage = str;
    }

    public void setContentTypes(List<String> list) {
        this.contentTypes = list;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setCurationDescriptor(List<CurationDescriptorItem> list) {
        this.curationDescriptor = list;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setIsFree(Boolean bool) {
        this.isFree = bool;
    }

    public void setIsFreeOnDemandPlayable(Boolean bool) {
        this.isFreeOnDemandPlayable = bool;
    }

    public void setIsInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    public void setIsMusicSubscription(Boolean bool) {
        this.isMusicSubscription = bool;
    }

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setIsSonicRushGolden(Boolean bool) {
        this.isSonicRushGolden = bool;
    }

    public void setIsSonicRushOnDemandPlayable(Boolean bool) {
        this.isSonicRushOnDemandPlayable = bool;
    }

    public void setIsTwitchRadio(Boolean bool) {
        this.isTwitchRadio = bool;
    }

    public void setItemIndex(Integer num) {
        this.itemIndex = num;
    }

    public void setLanguagesOfPerformance(String str) {
        this.languagesOfPerformance = str;
    }

    public void setOwned(Boolean bool) {
        this.owned = bool;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setPrimaryGenre(String str) {
        this.primaryGenre = str;
    }

    public void setRelatedEntities(List<RelatedEntityItem> list) {
        this.relatedEntities = list;
    }

    public void setRequestedAsin(String str) {
        this.requestedAsin = str;
    }

    public void setReviews(Review review) {
        this.reviews = review;
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

    public void setTrackCount(Integer num) {
        this.trackCount = num;
    }

    public void setTracks(List<TrackItem> list) {
        this.tracks = list;
    }

    public void setType(String str) {
        this.type = str;
    }
}
