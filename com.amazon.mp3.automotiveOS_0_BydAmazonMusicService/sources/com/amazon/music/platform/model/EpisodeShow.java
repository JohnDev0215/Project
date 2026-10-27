package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class EpisodeShow implements Comparable<EpisodeShow> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.EpisodeShow");
    private Art artOriginal;
    private List<PodcastCategory> category;
    private String id;
    private Map<String, List<String>> tierBenefit;
    private String title;
    private String variantId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EpisodeShow episodeShow) {
        if (episodeShow == null) {
            return -1;
        }
        if (episodeShow == this) {
            return 0;
        }
        String id = getId();
        String id2 = episodeShow.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, List<String>> tierBenefit = getTierBenefit();
        Map<String, List<String>> tierBenefit2 = episodeShow.getTierBenefit();
        if (tierBenefit != tierBenefit2) {
            if (tierBenefit == null) {
                return -1;
            }
            if (tierBenefit2 == null) {
                return 1;
            }
            if (tierBenefit instanceof Comparable) {
                int iCompareTo2 = ((Comparable) tierBenefit).compareTo(tierBenefit2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!tierBenefit.equals(tierBenefit2)) {
                int iHashCode = tierBenefit.hashCode();
                int iHashCode2 = tierBenefit2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<PodcastCategory> category = getCategory();
        List<PodcastCategory> category2 = episodeShow.getCategory();
        if (category != category2) {
            if (category == null) {
                return -1;
            }
            if (category2 == null) {
                return 1;
            }
            if (category instanceof Comparable) {
                int iCompareTo3 = ((Comparable) category).compareTo(category2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
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
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = episodeShow.getArtOriginal();
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
        String title = getTitle();
        String title2 = episodeShow.getTitle();
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
        String variantId = getVariantId();
        String variantId2 = episodeShow.getVariantId();
        if (variantId != variantId2) {
            if (variantId == null) {
                return -1;
            }
            if (variantId2 == null) {
                return 1;
            }
            int iCompareTo6 = variantId.compareTo(variantId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeShow)) {
            return false;
        }
        EpisodeShow episodeShow = (EpisodeShow) obj;
        return internalEqualityCheck(getId(), episodeShow.getId()) && internalEqualityCheck(getTierBenefit(), episodeShow.getTierBenefit()) && internalEqualityCheck(getCategory(), episodeShow.getCategory()) && internalEqualityCheck(getArtOriginal(), episodeShow.getArtOriginal()) && internalEqualityCheck(getTitle(), episodeShow.getTitle()) && internalEqualityCheck(getVariantId(), episodeShow.getVariantId());
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public List<PodcastCategory> getCategory() {
        return this.category;
    }

    public String getId() {
        return this.id;
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

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId(), getTierBenefit(), getCategory(), getArtOriginal(), getTitle(), getVariantId());
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setCategory(List<PodcastCategory> list) {
        this.category = list;
    }

    public void setId(String str) {
        this.id = str;
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
