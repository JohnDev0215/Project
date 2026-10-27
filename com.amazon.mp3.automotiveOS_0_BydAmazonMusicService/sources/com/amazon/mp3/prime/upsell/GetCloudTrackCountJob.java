package com.amazon.mp3.prime.upsell;

import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes4.dex */
public class GetCloudTrackCountJob extends Job {
    private int mLibraryTrackCount = -1;

    @Override // com.amazon.mp3.service.job.Job
    public int run() {
        this.mLibraryTrackCount = AccountRequestUtil.getCirrusTrackCount();
        return 1;
    }

    public int getCloudTrackCount() {
        return this.mLibraryTrackCount;
    }
}
