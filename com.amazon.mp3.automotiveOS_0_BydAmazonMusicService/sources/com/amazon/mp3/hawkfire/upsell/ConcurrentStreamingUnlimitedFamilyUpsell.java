package com.amazon.mp3.hawkfire.upsell;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.playback.activity.ConcurrencyErrorDialogActivity;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.weblab.ExperimentWeblabs;
import com.amazon.mp3.weblab.LegacyWeblabController;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.platform.data.WeblabTreatment;

/* JADX INFO: loaded from: classes3.dex */
public class ConcurrentStreamingUnlimitedFamilyUpsell {
    private final Context context;
    private final AccountDetailUtil mAccountDetailUtil;
    private final SharedPreferences sharedPreferences;

    public ConcurrentStreamingUnlimitedFamilyUpsell(final Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("concurrentStreamingSharedPrefs", 0);
        this.mAccountDetailUtil = AccountDetailUtil.get(context);
    }

    public void updateConcurrentStreamingReachedCounter() {
        int i = (this.sharedPreferences.getInt("concurrentFamilyUpsellCount", 0) % 3) + 1;
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putInt("concurrentFamilyUpsellCount", i);
        editorEdit.apply();
    }

    public boolean isEligibleForConcurrencyFamilyUpsell(final boolean isInEligibleSubscriptionTier) {
        return isInEligibleSubscriptionTier && (this.sharedPreferences.getInt("concurrentFamilyUpsellCount", 0) == 1) && this.mAccountDetailUtil.isUserInHawkfireMarketplace();
    }

    public boolean isEligibleForConcurrencyFamilyOneClickUpsell(final boolean isInEligibleSubscriptionTier) {
        return isInEligibleSubscriptionTier && (this.sharedPreferences.getInt("concurrentFamilyUpsellCount", 0) == 1);
    }

    public Intent handleConcurrencyErrorForNightWingAndGetIntent(final Context context, final ConcurrencyException concurrencyException) {
        boolean z = ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.getFamilyPlanPrice() != null;
        LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(Weblab.DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AA.toString());
        if (WeblabTreatment.T1.equals(LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(Weblab.DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AB.toString())) && z) {
            return ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
        }
        return ConcurrencyErrorDialogActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
    }

    public Intent handleConcurrencyErrorForPrimeAndGetIntent(final Context context, final ConcurrencyException concurrencyException) {
        boolean z = ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.getFamilyPlanPrice() != null;
        LegacyWeblabController legacyWeblabController = LegacyWeblabController.getInstance();
        ExperimentWeblabs experimentWeblabs = ExperimentWeblabs.DM_SUBS_CONCURRENCY_ONE_CLICK_SIGNUP;
        legacyWeblabController.getTreatmentAndRecordTrigger(experimentWeblabs.getAaWeblab());
        if (WeblabTreatment.T1.equals(LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(experimentWeblabs.getAbWeblab())) && z) {
            return ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
        }
        return ConcurrentStreamingUnlimitedFamilyUpsellActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
    }

    public Intent handleConcurrencyErrorForHawkfireAndGetIntent(final Context context, final ConcurrencyException concurrencyException) {
        boolean z = ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.getFamilyPlanPrice() != null;
        LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(Weblab.DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AA.toString());
        if (WeblabTreatment.T1.equals(LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(Weblab.DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AB.toString())) && z) {
            if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                return ConcurrentStreamingUnlimitedFamilyUpsellActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
            }
            return ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
        }
        return ConcurrencyErrorDialogActivity.getIntent(context, concurrencyException.getDevices().get(0).getName());
    }
}
