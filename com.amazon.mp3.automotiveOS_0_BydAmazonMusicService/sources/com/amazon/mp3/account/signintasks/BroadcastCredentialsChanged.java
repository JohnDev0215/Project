package com.amazon.mp3.account.signintasks;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.external.ads.AmazonAds;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.service.metrics.CampaignMetrics;
import com.amazon.mp3.util.Log;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class BroadcastCredentialsChanged implements SignInTask {
    private static final String TAG = "BroadcastCredentialsChanged";
    private final String accountId;
    private final String lastAccountId;
    private final Context mContext;

    public BroadcastCredentialsChanged(Context context, String accountId, String lastAccountId) {
        this.mContext = context;
        this.accountId = accountId;
        this.lastAccountId = lastAccountId;
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        String str = TAG;
        Log.debug(str, "Executing sign in task " + str);
        if (!this.accountId.equals(this.lastAccountId)) {
            new AmazonAds(this.mContext).identifyCustomer();
            boolean z = true;
            if ("".equals(this.lastAccountId)) {
                int persistedTrackCount = CirrusDatabaseUtil.getPersistedTrackCount(this.mContext, "cirrus");
                int cirrusTrackCount = AccountRequestUtil.getCirrusTrackCount();
                if (persistedTrackCount >= 1500 && persistedTrackCount == cirrusTrackCount) {
                    new Thread("Clear Stream Cache") { // from class: com.amazon.mp3.account.signintasks.BroadcastCredentialsChanged.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            Log.info(BroadcastCredentialsChanged.TAG, "Starting clear cache job (on the current thread).");
                            ClearCacheService.clearCacheOnCurrentThread(BroadcastCredentialsChanged.this.mContext, 1);
                        }
                    }.start();
                    z = false;
                }
            }
            if (z) {
                Log.info(str, "Starting clear cache job (through SettingsUtil).");
                SettingsUtil.clearCache(this.mContext);
            }
            CampaignMetrics.report(this.mContext);
        }
        this.mContext.sendBroadcast(new Intent("com.amazon.mp3.account.credentials.ACTION_NEW_CREDENTIALS"));
    }
}
