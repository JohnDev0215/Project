package com.amazon.mp3.library.job;

import android.content.Context;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class CheckAccountIsValidJob extends Job {
    final Context mContext;

    public CheckAccountIsValidJob(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        return AccountRequestUtil.isAccountValid(this.mContext) ? 1 : 3;
    }
}
