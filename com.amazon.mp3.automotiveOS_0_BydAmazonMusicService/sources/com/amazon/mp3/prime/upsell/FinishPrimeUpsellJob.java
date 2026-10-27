package com.amazon.mp3.prime.upsell;

import android.app.Application;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes4.dex */
public class FinishPrimeUpsellJob extends Job {
    private final PrimeUpsellActivity.PrimeUpsellResult mResult;

    FinishPrimeUpsellJob(PrimeUpsellActivity.PrimeUpsellResult result) {
        this.mResult = result;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        Application context = getContext();
        if (PrimeUpsellActivity.PrimeUpsellResult.UPSELL_SUCCESS.equals(this.mResult) || PrimeUpsellActivity.PrimeUpsellResult.EXPLORE_PRIME_MUSIC.equals(this.mResult)) {
            AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(context);
            AccountRequestUtil.setPrimeUserStatus(context, true);
        }
        if (PrimeUpsellActivity.PrimeUpsellResult.EXPLORE_PRIME_MUSIC.equals(this.mResult) && !AccountDetailStorage.get().hasAcceptedTermsOfUse()) {
            AccountRequestUtil.acceptTermsOfUse();
        }
        return 1;
    }

    public PrimeUpsellActivity.PrimeUpsellResult getUpsellResult() {
        return this.mResult;
    }
}
