package com.amazon.music.nautilus;

import com.amazon.musicsubscriptionofferservice.BillingPeriod;
import com.amazon.musicsubscriptionofferservice.MonetaryAmount;
import com.amazon.musicsubscriptionofferservice.Period;
import com.amazon.musicsubscriptionofferservice.PlanOffer;
import com.amazon.musicsubscriptionofferservice.PlanSchedule;
import com.amazon.musicsubscriptionofferservice.Promotion;
import com.amazon.musicsubscriptionofferservice.Result;
import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class MSOSScheduleBuilder {
    MSOSScheduleBuilder() {
    }

    MSOSSchedule buildSchedule(RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse) throws MalformedMSOSResponseException {
        return buildSchedule(((RetrieveEligibleSubscriptionOffersResponse) Validate.notNull(retrieveEligibleSubscriptionOffersResponse, "Response was null", new Object[0])).getResults());
    }

    MSOSSchedule buildSchedule(List<Result> list) throws MalformedMSOSResponseException {
        try {
            PlanSchedule planSchedule = (PlanSchedule) Validate.notNull(((Result) ((List) Validate.notEmpty(list, "Results list was empty", new Object[0])).get(0)).getPlanSchedule(), "Plan Schedule is missing from response", new Object[0]);
            boolean zBooleanValue = planSchedule.isAddonRequired() != null ? planSchedule.isAddonRequired().booleanValue() : false;
            if (zBooleanValue) {
                if (list.size() >= 2) {
                    return buildSchedule(list.subList(1, list.size()));
                }
                return null;
            }
            String promoType = planSchedule.getPromoType();
            String str = (String) Validate.notNull(planSchedule.getScheduleId(), "Schedule ID is missing from response", new Object[0]);
            List list2 = (List) Validate.notEmpty(planSchedule.getPlanOffers(), "Plan offers are missing from response", new Object[0]);
            PlanOffer planOffer = (PlanOffer) list2.get(0);
            PlanOffer planOffer2 = list2.size() >= 2 ? (PlanOffer) list2.get(1) : null;
            return new MSOSSchedule(buildOfferWith(planOffer), planOffer2 != null ? buildOfferWith(planOffer2) : null, str, promoType, zBooleanValue);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new MalformedMSOSResponseException("Could not build schedule." + e);
        }
    }

    private MSOSSubscriptionOffer buildOfferWith(PlanOffer planOffer) throws IllegalStateException, NullPointerException {
        MonetaryAmount monetaryAmount = (MonetaryAmount) Validate.notNull(((BillingPeriod) ((List) Validate.notEmpty(planOffer.getBillingSchedule(), "Billing Schedule is missing from response", new Object[0])).get(0)).getPrice(), "Monetary Amount is missing from response", new Object[0]);
        Period period = (Period) Validate.notNull(planOffer.getCommitmentPeriod(), "Commitment period is missing from response", new Object[0]);
        Promotion promotion = planOffer.getPromotion();
        boolean zBooleanValue = planOffer.getRenewalPolicy().isAutoRenew().booleanValue();
        String str = (String) Validate.notEmpty(period.getTimeInterval(), "Time Interval is missing from response", new Object[0]);
        String string = ((BigDecimal) Validate.notNull(monetaryAmount.getValue(), "Price is missing from response", new Object[0])).toString();
        String str2 = (String) Validate.notEmpty(monetaryAmount.getCurrencyCode(), "Currency Code is missing from response", new Object[0]);
        return new MSOSSubscriptionOffer(zBooleanValue, (String) Validate.notEmpty(planOffer.getPlanId(), "Plan ID is missing from response", new Object[0]), string, str2, str, promotion != null ? promotion.getPromotionId() : null, planOffer.getContractType());
    }
}
