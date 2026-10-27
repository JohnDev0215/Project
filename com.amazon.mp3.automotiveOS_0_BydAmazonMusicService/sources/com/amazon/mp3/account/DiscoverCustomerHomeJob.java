package com.amazon.mp3.account;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.net.stratus.StratusExceptions;
import com.amazon.mp3.store.job.CirrusAccountDetailsJob;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class DiscoverCustomerHomeJob extends CirrusAccountDetailsJob {
    public static final String TAG = "DiscoverCustomerHomeJob";

    public static class UserNotSignedInException extends RuntimeException {
    }

    @Override // com.amazon.mp3.store.job.CirrusAccountDetailsJob, com.amazon.mp3.service.job.Job
    public void cancel() {
    }

    @Override // com.amazon.mp3.store.job.CirrusAccountDetailsJob, com.amazon.mp3.service.job.Job
    public boolean restartable() {
        return true;
    }

    @Override // com.amazon.mp3.store.job.CirrusAccountDetailsJob, com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        if (AccountCredentialUtil.get().isSignedOut()) {
            throw new UserNotSignedInException();
        }
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            return 1;
        }
        try {
            if (AccountRequestUtil.getCustomerHome(getContext()).getCloudPlayerAccountExists() && AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
                return super.run();
            }
            return 1;
        } catch (StratusExceptions.StratusResourceNotFoundException unused) {
            Log.warning(TAG, "Customer has no account home");
            return 1;
        }
    }
}
