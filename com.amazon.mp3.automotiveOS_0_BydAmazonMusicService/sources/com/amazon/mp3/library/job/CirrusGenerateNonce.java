package com.amazon.mp3.library.job;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusGenerateNonce extends Job {
    private static final String TAG = "CirrusGenerateNonce";

    @Override // com.amazon.mp3.service.job.Job
    public boolean restartable() {
        return false;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            AccountRequestUtil.getCustomerHome(getContext());
            return AccountRequestUtil.generateNonce(getContext()) != null ? 1 : 3;
        }
        Log.error(TAG, "Cloud not supported");
        return 3;
    }
}
