package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogPodcastShow extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogPodcastShow");
    private Art artOriginal;
    private List<PodcastCategory> category;
    private List<String> contentTier;
    private String description;
    private Integer episodeCount;
    private String id;
    private ParentalControls parentalControls;
    private String provider;
    private Float rating;
    private Integer seasonCount;
    private Map<String, List<String>> tierBenefit;
    private String title;
    private String variantId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof CatalogPodcastShow)) {
            return 1;
        }
        CatalogPodcastShow catalogPodcastShow = (CatalogPodcastShow) document;
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = catalogPodcastShow.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode = contentTier.hashCode();
                int iHashCode2 = contentTier2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String provider = getProvider();
        String provider2 = catalogPodcastShow.getProvider();
        if (provider != provider2) {
            if (provider == null) {
                return -1;
            }
            if (provider2 == null) {
                return 1;
            }
            int iCompareTo2 = provider.compareTo(provider2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Float rating = getRating();
        Float rating2 = catalogPodcastShow.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo3 = rating.compareTo(rating2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogPodcastShow.getParentalControls();
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
        String variantId = getVariantId();
        String variantId2 = catalogPodcastShow.getVariantId();
        if (variantId != variantId2) {
            if (variantId == null) {
                return -1;
            }
            if (variantId2 == null) {
                return 1;
            }
            int iCompareTo5 = variantId.compareTo(variantId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = catalogPodcastShow.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo6 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String title = getTitle();
        String title2 = catalogPodcastShow.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo7 = title.compareTo(title2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Integer episodeCount = getEpisodeCount();
        Integer episodeCount2 = catalogPodcastShow.getEpisodeCount();
        if (episodeCount != episodeCount2) {
            if (episodeCount == null) {
                return -1;
            }
            if (episodeCount2 == null) {
                return 1;
            }
            int iCompareTo8 = episodeCount.compareTo(episodeCount2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<PodcastCategory> category = getCategory();
        List<PodcastCategory> category2 = catalogPodcastShow.getCategory();
        if (category != category2) {
            if (category == null) {
                return -1;
            }
            if (category2 == null) {
                return 1;
            }
            if (category instanceof Comparable) {
                int iCompareTo9 = ((Comparable) category).compareTo(category2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!category.equals(category2)) {
                int iHashCode3 = category.hashCode();
                int iHashCode4 = category2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Integer seasonCount = getSeasonCount();
        Integer seasonCount2 = catalogPodcastShow.getSeasonCount();
        if (seasonCount != seasonCount2) {
            if (seasonCount == null) {
                return -1;
            }
            if (seasonCount2 == null) {
                return 1;
            }
            int iCompareTo10 = seasonCount.compareTo(seasonCount2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String description = getDescription();
        String description2 = catalogPodcastShow.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo11 = description.compareTo(description2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String id = getId();
        String id2 = catalogPodcastShow.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo12 = id.compareTo(id2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Map<String, List<String>> tierBenefit = getTierBenefit();
        Map<String, List<String>> tierBenefit2 = catalogPodcastShow.getTierBenefit();
        if (tierBenefit != tierBenefit2) {
            if (tierBenefit == null) {
                return -1;
            }
            if (tierBenefit2 == null) {
                return 1;
            }
            if (tierBenefit instanceof Comparable) {
                int iCompareTo13 = ((Comparable) tierBenefit).compareTo(tierBenefit2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!tierBenefit.equals(tierBenefit2)) {
                int iHashCode5 = tierBenefit.hashCode();
                int iHashCode6 = tierBenefit2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogPodcastShow)) {
            return false;
        }
        CatalogPodcastShow catalogPodcastShow = (CatalogPodcastShow) obj;
        return super.equals(obj) && internalEqualityCheck(getContentTier(), catalogPodcastShow.getContentTier()) && internalEqualityCheck(getProvider(), catalogPodcastShow.getProvider()) && internalEqualityCheck(getRating(), catalogPodcastShow.getRating()) && internalEqualityCheck(getParentalControls(), catalogPodcastShow.getParentalControls()) && internalEqualityCheck(getVariantId(), catalogPodcastShow.getVariantId()) && internalEqualityCheck(getArtOriginal(), catalogPodcastShow.getArtOriginal()) && internalEqualityCheck(getTitle(), catalogPodcastShow.getTitle()) && internalEqualityCheck(getEpisodeCount(), catalogPodcastShow.getEpisodeCount()) && internalEqualityCheck(getCategory(), catalogPodcastShow.getCategory()) && internalEqualityCheck(getSeasonCount(), catalogPodcastShow.getSeasonCount()) && internalEqualityCheck(getDescription(), catalogPodcastShow.getDescription()) && internalEqualityCheck(getId(), catalogPodcastShow.getId()) && internalEqualityCheck(getTierBenefit(), catalogPodcastShow.getTierBenefit());
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public List<PodcastCategory> getCategory() {
        return this.category;
    }

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getEpisodeCount() {
        return this.episodeCount;
    }

    public String getId() {
        return this.id;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getProvider() {
        return this.provider;
    }

    public Float getRating() {
        return this.rating;
    }

    public Integer getSeasonCount() {
        return this.seasonCount;
    }

    public Map<String, List<String>> getTierBenefit() {
        return this.tierBenefit;
    }

    public String getTitle() {
        return this.title;
    }

    public String getVariantId() {
        return this.variantId;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentTier(), getProvider(), getRating(), getParentalControls(), getVariantId(), getArtOriginal(), getTitle(), getEpisodeCount(), getCategory(), getSeasonCount(), getDescription(), getId(), getTierBenefit());
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setCategory(List<PodcastCategory> list) {
        this.category = list;
    }

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setEpisodeCount(Integer num) {
        this.episodeCount = num;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setRating(Float f) {
        this.rating = f;
    }

    public void setSeasonCount(Integer num) {
        this.seasonCount = num;
    }

    public void setTierBenefit(Map<String, List<String>> map) {
        this.tierBenefit = map;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setVariantId(String str) {
        this.variantId = str;
    }
}
