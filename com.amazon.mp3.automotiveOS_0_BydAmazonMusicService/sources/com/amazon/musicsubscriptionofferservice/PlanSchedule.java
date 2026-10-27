package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PlanSchedule implements Comparable<PlanSchedule> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.PlanSchedule");
    private Boolean addonRequired;
    private List<List<String>> addonScheduleIds;
    private MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata;
    private List<PlanOffer> planOffers;
    private String promoType;
    private String scheduleId;
    private String subscriptionType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlanSchedule planSchedule) {
        if (planSchedule == null) {
            return -1;
        }
        if (planSchedule == this) {
            return 0;
        }
        String scheduleId = getScheduleId();
        String scheduleId2 = planSchedule.getScheduleId();
        if (scheduleId != scheduleId2) {
            if (scheduleId == null) {
                return -1;
            }
            if (scheduleId2 == null) {
                return 1;
            }
            int iCompareTo = scheduleId.compareTo(scheduleId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsAddonRequired = isAddonRequired();
        Boolean boolIsAddonRequired2 = planSchedule.isAddonRequired();
        if (boolIsAddonRequired != boolIsAddonRequired2) {
            if (boolIsAddonRequired == null) {
                return -1;
            }
            if (boolIsAddonRequired2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsAddonRequired.compareTo(boolIsAddonRequired2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String subscriptionType = getSubscriptionType();
        String subscriptionType2 = planSchedule.getSubscriptionType();
        if (subscriptionType != subscriptionType2) {
            if (subscriptionType == null) {
                return -1;
            }
            if (subscriptionType2 == null) {
                return 1;
            }
            int iCompareTo3 = subscriptionType.compareTo(subscriptionType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata = getMusicSubscriptionOfferMetadata();
        MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata2 = planSchedule.getMusicSubscriptionOfferMetadata();
        if (musicSubscriptionOfferMetadata != musicSubscriptionOfferMetadata2) {
            if (musicSubscriptionOfferMetadata == null) {
                return -1;
            }
            if (musicSubscriptionOfferMetadata2 == null) {
                return 1;
            }
            int iCompareTo4 = musicSubscriptionOfferMetadata.compareTo(musicSubscriptionOfferMetadata2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String promoType = getPromoType();
        String promoType2 = planSchedule.getPromoType();
        if (promoType != promoType2) {
            if (promoType == null) {
                return -1;
            }
            if (promoType2 == null) {
                return 1;
            }
            int iCompareTo5 = promoType.compareTo(promoType2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<List<String>> addonScheduleIds = getAddonScheduleIds();
        List<List<String>> addonScheduleIds2 = planSchedule.getAddonScheduleIds();
        if (addonScheduleIds != addonScheduleIds2) {
            if (addonScheduleIds == null) {
                return -1;
            }
            if (addonScheduleIds2 == null) {
                return 1;
            }
            if (addonScheduleIds instanceof Comparable) {
                int iCompareTo6 = ((Comparable) addonScheduleIds).compareTo(addonScheduleIds2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!addonScheduleIds.equals(addonScheduleIds2)) {
                int iHashCode = addonScheduleIds.hashCode();
                int iHashCode2 = addonScheduleIds2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<PlanOffer> planOffers = getPlanOffers();
        List<PlanOffer> planOffers2 = planSchedule.getPlanOffers();
        if (planOffers != planOffers2) {
            if (planOffers == null) {
                return -1;
            }
            if (planOffers2 == null) {
                return 1;
            }
            if (planOffers instanceof Comparable) {
                int iCompareTo7 = ((Comparable) planOffers).compareTo(planOffers2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!planOffers.equals(planOffers2)) {
                int iHashCode3 = planOffers.hashCode();
                int iHashCode4 = planOffers2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlanSchedule)) {
            return false;
        }
        PlanSchedule planSchedule = (PlanSchedule) obj;
        return internalEqualityCheck(getScheduleId(), planSchedule.getScheduleId()) && internalEqualityCheck(isAddonRequired(), planSchedule.isAddonRequired()) && internalEqualityCheck(getSubscriptionType(), planSchedule.getSubscriptionType()) && internalEqualityCheck(getMusicSubscriptionOfferMetadata(), planSchedule.getMusicSubscriptionOfferMetadata()) && internalEqualityCheck(getPromoType(), planSchedule.getPromoType()) && internalEqualityCheck(getAddonScheduleIds(), planSchedule.getAddonScheduleIds()) && internalEqualityCheck(getPlanOffers(), planSchedule.getPlanOffers());
    }

    public List<List<String>> getAddonScheduleIds() {
        return this.addonScheduleIds;
    }

    public MusicSubscriptionOfferMetadata getMusicSubscriptionOfferMetadata() {
        return this.musicSubscriptionOfferMetadata;
    }

    public List<PlanOffer> getPlanOffers() {
        return this.planOffers;
    }

    public String getPromoType() {
        return this.promoType;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public String getSubscriptionType() {
        return this.subscriptionType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getScheduleId(), isAddonRequired(), getSubscriptionType(), getMusicSubscriptionOfferMetadata(), getPromoType(), getAddonScheduleIds(), getPlanOffers());
    }

    public Boolean isAddonRequired() {
        return this.addonRequired;
    }

    public void setAddonRequired(Boolean bool) {
        this.addonRequired = bool;
    }

    public void setAddonScheduleIds(List<List<String>> list) {
        this.addonScheduleIds = list;
    }

    public void setMusicSubscriptionOfferMetadata(MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata) {
        this.musicSubscriptionOfferMetadata = musicSubscriptionOfferMetadata;
    }

    public void setPlanOffers(List<PlanOffer> list) {
        this.planOffers = list;
    }

    public void setPromoType(String str) {
        this.promoType = str;
    }

    public void setScheduleId(String str) {
        this.scheduleId = str;
    }

    public void setSubscriptionType(String str) {
        this.subscriptionType = str;
    }
}
