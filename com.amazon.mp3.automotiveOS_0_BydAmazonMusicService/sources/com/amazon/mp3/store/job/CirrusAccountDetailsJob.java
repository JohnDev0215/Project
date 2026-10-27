package com.amazon.mp3.store.job;

import android.app.Application;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.WebViewUtil;

/* JADX INFO: loaded from: classes4.dex */
public class CirrusAccountDetailsJob extends Job {
    private final WebViewUtil.FlowType mFlowType;

    public static class UserNotSignedInException extends RuntimeException {
    }

    @Override // com.amazon.mp3.service.job.Job
    public void cancel() {
    }

    @Override // com.amazon.mp3.service.job.Job
    public boolean restartable() {
        return true;
    }

    public CirrusAccountDetailsJob() {
        this(WebViewUtil.FlowType.LIBRARY);
    }

    public CirrusAccountDetailsJob(WebViewUtil.FlowType flowType) {
        this.mFlowType = flowType;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        if (AccountCredentialUtil.get().isSignedOut()) {
            throw new UserNotSignedInException();
        }
        Application context = getContext();
        AccountRequestUtil.getCustomerHome(context);
        DeviceAuthorizationManager.getInstance().refresh();
        DeviceAuthorizationManager.getInstance().autoAuthorize(context);
        AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(context);
        return 1;
    }
}
