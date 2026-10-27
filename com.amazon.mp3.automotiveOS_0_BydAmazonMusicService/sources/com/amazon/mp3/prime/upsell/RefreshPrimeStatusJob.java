package com.amazon.mp3.prime.upsell;

import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes4.dex */
public class RefreshPrimeStatusJob extends Job {
    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        AccountRequestUtil.refreshPrimeBenefits(getContext());
        return 1;
    }
}
