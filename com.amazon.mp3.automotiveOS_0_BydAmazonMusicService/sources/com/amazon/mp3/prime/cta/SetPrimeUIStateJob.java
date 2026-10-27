package com.amazon.mp3.prime.cta;

import android.content.Context;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes4.dex */
public class SetPrimeUIStateJob extends Job {
    private String mAsin;
    private CTAPrimeCache mPrimeCache;
    private PrimeTracksCache.PrimeUIState mState;

    public SetPrimeUIStateJob(Context context, String asin, PrimeTracksCache.PrimeUIState state) {
        this.mAsin = asin;
        this.mState = state;
        this.mPrimeCache = new CTAPrimeCache(context);
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        this.mPrimeCache.setPrimeUIState(this.mAsin, this.mState);
        return 1;
    }
}
