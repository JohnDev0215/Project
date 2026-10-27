package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogPodcastEpisode extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogPodcastEpisode");
    private Art artOriginal;
    private Map<String, Date> availabilityDisplayDate;
    private List<String> contentTier;
    private String description;
    private Integer duration;
    private Integer episodeNum;
    private String episodeType;
    private String id;
    private ParentalControls parentalControls;
    private Date publishDate;
    private Float rating;
    private Integer seasonNum;
    private EpisodeShow show;
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
        if (!(document instanceof CatalogPodcastEpisode)) {
            return 1;
        }
        CatalogPodcastEpisode catalogPodcastEpisode = (CatalogPodcastEpisode) document;
        Integer duration = getDuration();
        Integer duration2 = catalogPodcastEpisode.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo = duration.compareTo(duration2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String id = getId();
        String id2 = catalogPodcastEpisode.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer episodeNum = getEpisodeNum();
        Integer episodeNum2 = catalogPodcastEpisode.getEpisodeNum();
        if (episodeNum != episodeNum2) {
            if (episodeNum == null) {
                return -1;
            }
            if (episodeNum2 == null) {
                return 1;
            }
            int iCompareTo3 = episodeNum.compareTo(episodeNum2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = catalogPodcastEpisode.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo4 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Map<String, Date> availabilityDisplayDate = getAvailabilityDisplayDate();
        Map<String, Date> availabilityDisplayDate2 = catalogPodcastEpisode.getAvailabilityDisplayDate();
        if (availabilityDisplayDate != availabilityDisplayDate2) {
            if (availabilityDisplayDate == null) {
                return -1;
            }
            if (availabilityDisplayDate2 == null) {
                return 1;
            }
            if (availabilityDisplayDate instanceof Comparable) {
                int iCompareTo5 = ((Comparable) availabilityDisplayDate).compareTo(availabilityDisplayDate2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!availabilityDisplayDate.equals(availabilityDisplayDate2)) {
                int iHashCode = availabilityDisplayDate.hashCode();
                int iHashCode2 = availabilityDisplayDate2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = catalogPodcastEpisode.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo6 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode3 = contentTier.hashCode();
                int iHashCode4 = contentTier2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String episodeType = getEpisodeType();
        String episodeType2 = catalogPodcastEpisode.getEpisodeType();
        if (episodeType != episodeType2) {
            if (episodeType == null) {
                return -1;
            }
            if (episodeType2 == null) {
                return 1;
            }
            int iCompareTo7 = episodeType.compareTo(episodeType2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Float rating = getRating();
        Float rating2 = catalogPodcastEpisode.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo8 = rating.compareTo(rating2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Integer seasonNum = getSeasonNum();
        Integer seasonNum2 = catalogPodcastEpisode.getSeasonNum();
        if (seasonNum != seasonNum2) {
            if (seasonNum == null) {
                return -1;
            }
            if (seasonNum2 == null) {
                return 1;
            }
            int iCompareTo9 = seasonNum.compareTo(seasonNum2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogPodcastEpisode.getParentalControls();
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
        String title = getTitle();
        String title2 = catalogPodcastEpisode.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo11 = title.compareTo(title2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Date publishDate = getPublishDate();
        Date publishDate2 = catalogPodcastEpisode.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo12 = publishDate.compareTo(publishDate2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        EpisodeShow show = getShow();
        EpisodeShow show2 = catalogPodcastEpisode.getShow();
        if (show != show2) {
            if (show == null) {
                return -1;
            }
            if (show2 == null) {
                return 1;
            }
            int iCompareTo13 = show.compareTo(show2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Map<String, List<String>> tierBenefit = getTierBenefit();
        Map<String, List<String>> tierBenefit2 = catalogPodcastEpisode.getTierBenefit();
        if (tierBenefit != tierBenefit2) {
            if (tierBenefit == null) {
                return -1;
            }
            if (tierBenefit2 == null) {
                return 1;
            }
            if (tierBenefit instanceof Comparable) {
                int iCompareTo14 = ((Comparable) tierBenefit).compareTo(tierBenefit2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
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
        String variantId = getVariantId();
        String variantId2 = catalogPodcastEpisode.getVariantId();
        if (variantId != variantId2) {
            if (variantId == null) {
                return -1;
            }
            if (variantId2 == null) {
                return 1;
            }
            int iCompareTo15 = variantId.compareTo(variantId2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String description = getDescription();
        String description2 = catalogPodcastEpisode.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo16 = description.compareTo(description2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogPodcastEpisode)) {
            return false;
        }
        CatalogPodcastEpisode catalogPodcastEpisode = (CatalogPodcastEpisode) obj;
        return super.equals(obj) && internalEqualityCheck(getDuration(), catalogPodcastEpisode.getDuration()) && internalEqualityCheck(getId(), catalogPodcastEpisode.getId()) && internalEqualityCheck(getEpisodeNum(), catalogPodcastEpisode.getEpisodeNum()) && internalEqualityCheck(getArtOriginal(), catalogPodcastEpisode.getArtOriginal()) && internalEqualityCheck(getAvailabilityDisplayDate(), catalogPodcastEpisode.getAvailabilityDisplayDate()) && internalEqualityCheck(getContentTier(), catalogPodcastEpisode.getContentTier()) && internalEqualityCheck(getEpisodeType(), catalogPodcastEpisode.getEpisodeType()) && internalEqualityCheck(getRating(), catalogPodcastEpisode.getRating()) && internalEqualityCheck(getSeasonNum(), catalogPodcastEpisode.getSeasonNum()) && internalEqualityCheck(getParentalControls(), catalogPodcastEpisode.getParentalControls()) && internalEqualityCheck(getTitle(), catalogPodcastEpisode.getTitle()) && internalEqualityCheck(getPublishDate(), catalogPodcastEpisode.getPublishDate()) && internalEqualityCheck(getShow(), catalogPodcastEpisode.getShow()) && internalEqualityCheck(getTierBenefit(), catalogPodcastEpisode.getTierBenefit()) && internalEqualityCheck(getVariantId(), catalogPodcastEpisode.getVariantId()) && internalEqualityCheck(getDescription(), catalogPodcastEpisode.getDescription());
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public Map<String, Date> getAvailabilityDisplayDate() {
        return this.availabilityDisplayDate;
    }

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public Integer getEpisodeNum() {
        return this.episodeNum;
    }

    public String getEpisodeType() {
        return this.episodeType;
    }

    public String getId() {
        return this.id;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    public Float getRating() {
        return this.rating;
    }

    public Integer getSeasonNum() {
        return this.seasonNum;
    }

    public EpisodeShow getShow() {
        return this.show;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDuration(), getId(), getEpisodeNum(), getArtOriginal(), getAvailabilityDisplayDate(), getContentTier(), getEpisodeType(), getRating(), getSeasonNum(), getParentalControls(), getTitle(), getPublishDate(), getShow(), getTierBenefit(), getVariantId(), getDescription());
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setAvailabilityDisplayDate(Map<String, Date> map) {
        this.availabilityDisplayDate = map;
    }

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setEpisodeNum(Integer num) {
        this.episodeNum = num;
    }

    public void setEpisodeType(String str) {
        this.episodeType = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPublishDate(Date date) {
        this.publishDate = date;
    }

    public void setRating(Float f) {
        this.rating = f;
    }

    public void setSeasonNum(Integer num) {
        this.seasonNum = num;
    }

    public void setShow(EpisodeShow episodeShow) {
        this.show = episodeShow;
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
