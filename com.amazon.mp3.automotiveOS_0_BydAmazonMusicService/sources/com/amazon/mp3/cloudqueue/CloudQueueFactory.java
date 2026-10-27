package com.amazon.mp3.cloudqueue;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.cloudqueue.Configuration;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class CloudQueueFactory {
    private static volatile CloudQueueFactory sCloudQueueFactory = null;
    private static volatile boolean sNeedsRefresh = false;
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.cloudqueue.CloudQueueFactory.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (!UserSubscriptionUtil.isNightwingOnly() && set.contains(AccountStateField.USER_BENEFITS)) {
                NowPlayingUtil.clearAndFinishNowPlaying(CloudQueueFactory.this.mContext, PlayStateMutationReason.ACCOUNT_MIGRATED);
            }
            if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE) || set.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE)) {
                AccountManagerSingleton.get().deregisterListener(CloudQueueFactory.this.mAccountStateChangeListener);
                synchronized (CloudQueueFactory.class) {
                    boolean unused = CloudQueueFactory.sNeedsRefresh = true;
                }
            }
        }
    };
    private CloudQueueService mCloudQueueService;
    private final Context mContext;

    public static CloudQueueFactory getInstance(Context context) {
        CloudQueueFactory cloudQueueFactory;
        CloudQueueFactory cloudQueueFactory2 = sCloudQueueFactory;
        if (cloudQueueFactory2 != null && !sNeedsRefresh) {
            return cloudQueueFactory2;
        }
        synchronized (CloudQueueFactory.class) {
            cloudQueueFactory = sCloudQueueFactory;
            if (cloudQueueFactory == null || sNeedsRefresh) {
                AccountDetailUtil.get(context.getApplicationContext());
                String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
                AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
                CloudQueueFactory cloudQueueFactory3 = new CloudQueueFactory(context.getApplicationContext(), musicTerritoryOfResidence, accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType());
                sCloudQueueFactory = cloudQueueFactory3;
                sNeedsRefresh = false;
                cloudQueueFactory = cloudQueueFactory3;
            }
        }
        return cloudQueueFactory;
    }

    @NonNull
    public CloudQueueService getCloudQueueService() {
        return this.mCloudQueueService;
    }

    private CloudQueueFactory(Context context, String musicTerritory, String deviceId, String deviceType) {
        Configuration.Builder builder = new Configuration.Builder(musicTerritory, deviceId, deviceType, MusicURL.getMusicPlayqueueServiceV2Url(), new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor());
        builder.withLanguage(new BrowseLanguageProvider().getLocale(context).toString());
        this.mCloudQueueService = new CloudQueueService(builder.build());
        this.mContext = context.getApplicationContext();
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }
}
