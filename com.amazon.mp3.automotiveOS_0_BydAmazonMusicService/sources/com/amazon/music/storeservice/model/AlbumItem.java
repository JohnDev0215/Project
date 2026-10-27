package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumItem implements Comparable<AlbumItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.AlbumItem");
    private List<AlbumPurchaseOptionsData> albumPurchaseOptionsDataList;
    private Artist artist;
    private String asin;
    private List<String> contentEncoding;
    private List<String> contentTypes;
    private Integer duration;
    private String globalAsin;
    private String image;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isInLibrary;
    private Boolean isMusicSubscription;
    private Boolean isPreorderEligible;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Boolean isSonicRushOnDemandPlayable;
    private Boolean isTwitchRadio;
    private Integer itemIndex;
    private String label;
    private String languagesOfPerformance;
    private Long merchantReleaseDate;
    private Long originalReleaseDate;
    private PlaymodeEligibility playmodeEligibility;
    private String primaryArtistName;
    private ProductDetails productDetails;
    private Purchase purchase;
    private String requestedAsin;
    private Review reviews;
    private String spiritualCategory;
    private Long streetDate;
    private String title;
    private Integer trackCount;
    private List<TrackItem> tracks;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AlbumItem albumItem) {
        if (albumItem == null) {
            return -1;
        }
        if (albumItem == this) {
            return 0;
        }
        Boolean boolIsIsPreorderEligible = isIsPreorderEligible();
        Boolean boolIsIsPreorderEligible2 = albumItem.isIsPreorderEligible();
        if (boolIsIsPreorderEligible != boolIsIsPreorderEligible2) {
            if (boolIsIsPreorderEligible == null) {
                return -1;
            }
            if (boolIsIsPreorderEligible2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsPreorderEligible.compareTo(boolIsIsPreorderEligible2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer itemIndex = getItemIndex();
        Integer itemIndex2 = albumItem.getItemIndex();
        if (itemIndex != itemIndex2) {
            if (itemIndex == null) {
                return -1;
            }
            if (itemIndex2 == null) {
                return 1;
            }
            int iCompareTo2 = itemIndex.compareTo(itemIndex2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = albumItem.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo3 = duration.compareTo(duration2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String spiritualCategory = getSpiritualCategory();
        String spiritualCategory2 = albumItem.getSpiritualCategory();
        if (spiritualCategory != spiritualCategory2) {
            if (spiritualCategory == null) {
                return -1;
            }
            if (spiritualCategory2 == null) {
                return 1;
            }
            int iCompareTo4 = spiritualCategory.compareTo(spiritualCategory2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = albumItem.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = albumItem.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo6 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode = contentEncoding.hashCode();
                int iHashCode2 = contentEncoding2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String image = getImage();
        String image2 = albumItem.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo7 = image.compareTo(image2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = albumItem.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo8 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Long originalReleaseDate = getOriginalReleaseDate();
        Long originalReleaseDate2 = albumItem.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo9 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<AlbumPurchaseOptionsData> albumPurchaseOptionsDataList = getAlbumPurchaseOptionsDataList();
        List<AlbumPurchaseOptionsData> albumPurchaseOptionsDataList2 = albumItem.getAlbumPurchaseOptionsDataList();
        if (albumPurchaseOptionsDataList != albumPurchaseOptionsDataList2) {
            if (albumPurchaseOptionsDataList == null) {
                return -1;
            }
            if (albumPurchaseOptionsDataList2 == null) {
                return 1;
            }
            if (albumPurchaseOptionsDataList instanceof Comparable) {
                int iCompareTo10 = ((Comparable) albumPurchaseOptionsDataList).compareTo(albumPurchaseOptionsDataList2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!albumPurchaseOptionsDataList.equals(albumPurchaseOptionsDataList2)) {
                int iHashCode3 = albumPurchaseOptionsDataList.hashCode();
                int iHashCode4 = albumPurchaseOptionsDataList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = albumItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = albumItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo12 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = albumItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Artist artist = getArtist();
        Artist artist2 = albumItem.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            int iCompareTo14 = artist.compareTo(artist2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        ProductDetails productDetails = getProductDetails();
        ProductDetails productDetails2 = albumItem.getProductDetails();
        if (productDetails != productDetails2) {
            if (productDetails == null) {
                return -1;
            }
            if (productDetails2 == null) {
                return 1;
            }
            int iCompareTo15 = productDetails.compareTo(productDetails2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String primaryArtistName = getPrimaryArtistName();
        String primaryArtistName2 = albumItem.getPrimaryArtistName();
        if (primaryArtistName != primaryArtistName2) {
            if (primaryArtistName == null) {
                return -1;
            }
            if (primaryArtistName2 == null) {
                return 1;
            }
            int iCompareTo16 = primaryArtistName.compareTo(primaryArtistName2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = albumItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo17 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String globalAsin = getGlobalAsin();
        String globalAsin2 = albumItem.getGlobalAsin();
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
        Long streetDate = getStreetDate();
        Long streetDate2 = albumItem.getStreetDate();
        if (streetDate != streetDate2) {
            if (streetDate == null) {
                return -1;
            }
            if (streetDate2 == null) {
                return 1;
            }
            int iCompareTo19 = streetDate.compareTo(streetDate2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        Long merchantReleaseDate = getMerchantReleaseDate();
        Long merchantReleaseDate2 = albumItem.getMerchantReleaseDate();
        if (merchantReleaseDate != merchantReleaseDate2) {
            if (merchantReleaseDate == null) {
                return -1;
            }
            if (merchantReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo20 = merchantReleaseDate.compareTo(merchantReleaseDate2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String type = getType();
        String type2 = albumItem.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo21 = type.compareTo(type2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        Boolean boolIsIsTwitchRadio = isIsTwitchRadio();
        Boolean boolIsIsTwitchRadio2 = albumItem.isIsTwitchRadio();
        if (boolIsIsTwitchRadio != boolIsIsTwitchRadio2) {
            if (boolIsIsTwitchRadio == null) {
                return -1;
            }
            if (boolIsIsTwitchRadio2 == null) {
                return 1;
            }
            int iCompareTo22 = boolIsIsTwitchRadio.compareTo(boolIsIsTwitchRadio2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        Review reviews = getReviews();
        Review reviews2 = albumItem.getReviews();
        if (reviews != reviews2) {
            if (reviews == null) {
                return -1;
            }
            if (reviews2 == null) {
                return 1;
            }
            int iCompareTo23 = reviews.compareTo(reviews2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        String asin = getAsin();
        String asin2 = albumItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo24 = asin.compareTo(asin2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = albumItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo25 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        String languagesOfPerformance = getLanguagesOfPerformance();
        String languagesOfPerformance2 = albumItem.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo26 = languagesOfPerformance.compareTo(languagesOfPerformance2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        List<String> contentTypes = getContentTypes();
        List<String> contentTypes2 = albumItem.getContentTypes();
        if (contentTypes != contentTypes2) {
            if (contentTypes == null) {
                return -1;
            }
            if (contentTypes2 == null) {
                return 1;
            }
            if (contentTypes instanceof Comparable) {
                int iCompareTo27 = ((Comparable) contentTypes).compareTo(contentTypes2);
                if (iCompareTo27 != 0) {
                    return iCompareTo27;
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
        Integer trackCount = getTrackCount();
        Integer trackCount2 = albumItem.getTrackCount();
        if (trackCount != trackCount2) {
            if (trackCount == null) {
                return -1;
            }
            if (trackCount2 == null) {
                return 1;
            }
            int iCompareTo28 = trackCount.compareTo(trackCount2);
            if (iCompareTo28 != 0) {
                return iCompareTo28;
            }
        }
        List<TrackItem> tracks = getTracks();
        List<TrackItem> tracks2 = albumItem.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo29 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo29 != 0) {
                    return iCompareTo29;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode7 = tracks.hashCode();
                int iHashCode8 = tracks2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        Purchase purchase = getPurchase();
        Purchase purchase2 = albumItem.getPurchase();
        if (purchase != purchase2) {
            if (purchase == null) {
                return -1;
            }
            if (purchase2 == null) {
                return 1;
            }
            int iCompareTo30 = purchase.compareTo(purchase2);
            if (iCompareTo30 != 0) {
                return iCompareTo30;
            }
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = albumItem.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo31 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo31 != 0) {
                return iCompareTo31;
            }
        }
        String label = getLabel();
        String label2 = albumItem.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo32 = label.compareTo(label2);
            if (iCompareTo32 != 0) {
                return iCompareTo32;
            }
        }
        String requestedAsin = getRequestedAsin();
        String requestedAsin2 = albumItem.getRequestedAsin();
        if (requestedAsin != requestedAsin2) {
            if (requestedAsin == null) {
                return -1;
            }
            if (requestedAsin2 == null) {
                return 1;
            }
            int iCompareTo33 = requestedAsin.compareTo(requestedAsin2);
            if (iCompareTo33 != 0) {
                return iCompareTo33;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = albumItem.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo34 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo34 != 0) {
                return iCompareTo34;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AlbumItem)) {
            return false;
        }
        AlbumItem albumItem = (AlbumItem) obj;
        return internalEqualityCheck(isIsPreorderEligible(), albumItem.isIsPreorderEligible()) && internalEqualityCheck(getItemIndex(), albumItem.getItemIndex()) && internalEqualityCheck(getDuration(), albumItem.getDuration()) && internalEqualityCheck(getSpiritualCategory(), albumItem.getSpiritualCategory()) && internalEqualityCheck(getTitle(), albumItem.getTitle()) && internalEqualityCheck(getContentEncoding(), albumItem.getContentEncoding()) && internalEqualityCheck(getImage(), albumItem.getImage()) && internalEqualityCheck(getPlaymodeEligibility(), albumItem.getPlaymodeEligibility()) && internalEqualityCheck(getOriginalReleaseDate(), albumItem.getOriginalReleaseDate()) && internalEqualityCheck(getAlbumPurchaseOptionsDataList(), albumItem.getAlbumPurchaseOptionsDataList()) && internalEqualityCheck(isIsSonicRush(), albumItem.isIsSonicRush()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), albumItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(isIsFree(), albumItem.isIsFree()) && internalEqualityCheck(getArtist(), albumItem.getArtist()) && internalEqualityCheck(getProductDetails(), albumItem.getProductDetails()) && internalEqualityCheck(getPrimaryArtistName(), albumItem.getPrimaryArtistName()) && internalEqualityCheck(isIsMusicSubscription(), albumItem.isIsMusicSubscription()) && internalEqualityCheck(getGlobalAsin(), albumItem.getGlobalAsin()) && internalEqualityCheck(getStreetDate(), albumItem.getStreetDate()) && internalEqualityCheck(getMerchantReleaseDate(), albumItem.getMerchantReleaseDate()) && internalEqualityCheck(getType(), albumItem.getType()) && internalEqualityCheck(isIsTwitchRadio(), albumItem.isIsTwitchRadio()) && internalEqualityCheck(getReviews(), albumItem.getReviews()) && internalEqualityCheck(getAsin(), albumItem.getAsin()) && internalEqualityCheck(isIsPrime(), albumItem.isIsPrime()) && internalEqualityCheck(getLanguagesOfPerformance(), albumItem.getLanguagesOfPerformance()) && internalEqualityCheck(getContentTypes(), albumItem.getContentTypes()) && internalEqualityCheck(getTrackCount(), albumItem.getTrackCount()) && internalEqualityCheck(getTracks(), albumItem.getTracks()) && internalEqualityCheck(getPurchase(), albumItem.getPurchase()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), albumItem.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getLabel(), albumItem.getLabel()) && internalEqualityCheck(getRequestedAsin(), albumItem.getRequestedAsin()) && internalEqualityCheck(isIsInLibrary(), albumItem.isIsInLibrary());
    }

    public List<AlbumPurchaseOptionsData> getAlbumPurchaseOptionsDataList() {
        return this.albumPurchaseOptionsDataList;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public List<String> getContentTypes() {
        return this.contentTypes;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getGlobalAsin() {
        return this.globalAsin;
    }

    public String getImage() {
        return this.image;
    }

    public Integer getItemIndex() {
        return this.itemIndex;
    }

    public String getLabel() {
        return this.label;
    }

    public String getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public Long getMerchantReleaseDate() {
        return this.merchantReleaseDate;
    }

    public Long getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getPrimaryArtistName() {
        return this.primaryArtistName;
    }

    public ProductDetails getProductDetails() {
        return this.productDetails;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public String getRequestedAsin() {
        return this.requestedAsin;
    }

    public Review getReviews() {
        return this.reviews;
    }

    public String getSpiritualCategory() {
        return this.spiritualCategory;
    }

    public Long getStreetDate() {
        return this.streetDate;
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIsPreorderEligible(), getItemIndex(), getDuration(), getSpiritualCategory(), getTitle(), getContentEncoding(), getImage(), getPlaymodeEligibility(), getOriginalReleaseDate(), getAlbumPurchaseOptionsDataList(), isIsSonicRush(), isIsFreeOnDemandPlayable(), isIsFree(), getArtist(), getProductDetails(), getPrimaryArtistName(), isIsMusicSubscription(), getGlobalAsin(), getStreetDate(), getMerchantReleaseDate(), getType(), isIsTwitchRadio(), getReviews(), getAsin(), isIsPrime(), getLanguagesOfPerformance(), getContentTypes(), getTrackCount(), getTracks(), getPurchase(), isIsSonicRushOnDemandPlayable(), getLabel(), getRequestedAsin(), isIsInLibrary());
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

    public Boolean isIsPreorderEligible() {
        return this.isPreorderEligible;
    }

    public Boolean isIsPrime() {
        return this.isPrime;
    }

    public Boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public Boolean isIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public Boolean isIsTwitchRadio() {
        return this.isTwitchRadio;
    }

    public void setAlbumPurchaseOptionsDataList(List<AlbumPurchaseOptionsData> list) {
        this.albumPurchaseOptionsDataList = list;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setContentTypes(List<String> list) {
        this.contentTypes = list;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setGlobalAsin(String str) {
        this.globalAsin = str;
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

    public void setIsPreorderEligible(Boolean bool) {
        this.isPreorderEligible = bool;
    }

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
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

    public void setLabel(String str) {
        this.label = str;
    }

    public void setLanguagesOfPerformance(String str) {
        this.languagesOfPerformance = str;
    }

    public void setMerchantReleaseDate(Long l) {
        this.merchantReleaseDate = l;
    }

    public void setOriginalReleaseDate(Long l) {
        this.originalReleaseDate = l;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setPrimaryArtistName(String str) {
        this.primaryArtistName = str;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setRequestedAsin(String str) {
        this.requestedAsin = str;
    }

    public void setReviews(Review review) {
        this.reviews = review;
    }

    public void setSpiritualCategory(String str) {
        this.spiritualCategory = str;
    }

    public void setStreetDate(Long l) {
        this.streetDate = l;
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
