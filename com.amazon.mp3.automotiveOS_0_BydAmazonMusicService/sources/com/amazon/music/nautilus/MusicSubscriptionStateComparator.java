package com.amazon.music.nautilus;

import java.util.EnumSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
class MusicSubscriptionStateComparator {
    MusicSubscriptionStateComparator() {
    }

    public Set<MusicSubscriptionStateField> getDifferences(SubscriptionOffers subscriptionOffers, SubscriptionOffers subscriptionOffers2) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(MusicSubscriptionStateField.class);
        if (subscriptionOffers != null || subscriptionOffers2 != null) {
            if (subscriptionOffers == null || subscriptionOffers2 == null) {
                enumSetNoneOf.addAll(EnumSet.of(MusicSubscriptionStateField.SUBSCRIPTION_OFFERS_FREE_TRIAL));
            } else if (subscriptionOffers.hasFreeTrial() != subscriptionOffers2.hasFreeTrial()) {
                enumSetNoneOf.add(MusicSubscriptionStateField.SUBSCRIPTION_OFFERS_FREE_TRIAL);
            }
        }
        return enumSetNoneOf;
    }

    public Set<MusicSubscriptionStateField> getDifferences(Subscription subscription, Subscription subscription2) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(MusicSubscriptionStateField.class);
        if (subscription != null || subscription2 != null) {
            if (subscription == null || subscription2 == null) {
                enumSetNoneOf.addAll(EnumSet.of(MusicSubscriptionStateField.SUBSCRIPTION_PLAN, MusicSubscriptionStateField.SUBSCRIPTION_ROLLOVER_PLAN, MusicSubscriptionStateField.HAS_OPEN_PROBLEMS, MusicSubscriptionStateField.END_DATE, MusicSubscriptionStateField.IS_AUTO_RENEW));
            } else {
                if (!equals(subscription.getPlanId(), subscription2.getPlanId())) {
                    enumSetNoneOf.add(MusicSubscriptionStateField.SUBSCRIPTION_PLAN);
                }
                if (!equals(subscription.getRolloverPlanId(), subscription2.getRolloverPlanId())) {
                    enumSetNoneOf.add(MusicSubscriptionStateField.SUBSCRIPTION_ROLLOVER_PLAN);
                }
                if (subscription.hasOpenProblems() != subscription2.hasOpenProblems()) {
                    enumSetNoneOf.add(MusicSubscriptionStateField.HAS_OPEN_PROBLEMS);
                }
                if (!equals(subscription.getEndDate(), subscription2.getEndDate())) {
                    enumSetNoneOf.add(MusicSubscriptionStateField.END_DATE);
                }
                if (subscription.isAutoRenew() != subscription2.isAutoRenew()) {
                    enumSetNoneOf.add(MusicSubscriptionStateField.IS_AUTO_RENEW);
                }
            }
        }
        return enumSetNoneOf;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
