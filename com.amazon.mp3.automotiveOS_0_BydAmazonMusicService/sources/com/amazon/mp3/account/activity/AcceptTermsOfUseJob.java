package com.amazon.mp3.account.activity;

import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class AcceptTermsOfUseJob extends Job implements SignInTask {
    private static final String TAG = "AcceptTermsOfUseJob";

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        run();
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        String str = TAG;
        Log.debug(str, "Executing sign in task " + str);
        if (AccountDetailStorage.get(getContext()).hasAcceptedTermsOfUse()) {
            return 1;
        }
        AccountRequestUtil.acceptTermsOfUse();
        return 1;
    }
}
