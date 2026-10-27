package com.amazon.music.nautilus;

import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersResponse;
import com.amazon.music.subscription.RetrieveSubscriptionsResponse;
import com.amazon.stratus.Plan;

/* JADX INFO: loaded from: classes4.dex */
class SubscriptionManagementServiceResponseParser {
    public SubscriptionOffers parseSubscriptionOffers(RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse) {
        return new SubscriptionOffers((retrieveEligibleSubscriptionOffersResponse == null || retrieveEligibleSubscriptionOffersResponse.getTrial() == null || retrieveEligibleSubscriptionOffersResponse.getTrial().isEmpty()) ? false : true);
    }

    public Subscription parseSubscriptions(RetrieveSubscriptionsResponse retrieveSubscriptionsResponse) {
        if (retrieveSubscriptionsResponse != null && retrieveSubscriptionsResponse.getSubscriptions() != null) {
            for (com.amazon.stratus.Subscription subscription : retrieveSubscriptionsResponse.getSubscriptions()) {
                if (subscription.getPlanCategory().equals("HAWKFIRE")) {
                    return convertStratusSubscription(subscription);
                }
            }
        }
        return new Subscription(null, null, false, null, false);
    }

    private Subscription convertStratusSubscription(com.amazon.stratus.Subscription subscription) {
        Plan plan = subscription.getPlan();
        String planId = plan != null ? plan.getPlanId() : null;
        Plan rolloverPlan = subscription.getRolloverPlan();
        String planId2 = rolloverPlan != null ? rolloverPlan.getPlanId() : null;
        Boolean boolIsHasOpenProblems = subscription.isHasOpenProblems();
        boolean zBooleanValue = boolIsHasOpenProblems == null ? false : boolIsHasOpenProblems.booleanValue();
        Boolean boolIsAutoRenew = subscription.isAutoRenew();
        return new Subscription(planId, planId2, zBooleanValue, subscription.getEndDate(), boolIsAutoRenew == null ? false : boolIsAutoRenew.booleanValue());
    }
}
