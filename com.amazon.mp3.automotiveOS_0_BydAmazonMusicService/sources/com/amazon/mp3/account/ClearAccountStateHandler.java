package com.amazon.mp3.account;

import android.content.Context;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.mp3.account.preauth.PreAuthSplashEligibilityProvider;
import com.amazon.mp3.account.signintasks.SignInFlowTasks;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.cache.UserSubscriptionCache;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.download.DownloadCoordinatorProvider;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.inappmessaging.InAppMessagingManager;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.mp3.library.provider.source.nowplaying.RecentItemsManager;
import com.amazon.mp3.lyrics.LyricsManager;
import com.amazon.mp3.platform.providers.CachedFeatureFlagProviderImpl;
import com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.music.casting.session.iot.IotConnectionManager;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.find.service.FindService;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.podcast.Podcast;
import io.reactivex.functions.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class ClearAccountStateHandler {
    private static final String TAG = "ClearAccountStateHandler";
    private final Context mContext;

    public ClearAccountStateHandler(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void clearAppState() {
        Log.info(TAG, "Starting clear cache job.");
        if (!ClearCacheService.isRunning()) {
            ClearCacheService.startClearCache(this.mContext, true);
        }
        Podcast.clearAppState(this.mContext);
        LibraryPreferences.INSTANCE.clear(this.mContext);
        MusicDownloader.getInstance(this.mContext).cancelAllDownloads();
        RecentItemsManager.getInstance(this.mContext).clearRecentItems();
        RecentlyPlayedManager.getInstance(this.mContext).clearAll();
        LyricsManager.removeAllLyrics(this.mContext);
        BootstrapSingletonTask.get(this.mContext).reset();
        new SignInFlowTasks(this.mContext).clearExecutedTasks();
        FindService.INSTANCE.clearAppState(this.mContext);
        AbstractPrimeTabFragmentHost.forceRefreshTabs();
        VoiceManagerFactory.forceRefresh();
        InAppMessagingManager.forceRefresh();
        DynamicMessagingManager.clearCache();
        FTUSettingsUtil.clearFTUSettings(this.mContext);
        IotConnectionManager.reset();
        CastingUtil.clearSession();
        SettingsUtil.setHasCompletedWidevineDatabaseUpdate(this.mContext, false);
        DownloadCoordinatorProvider.INSTANCE.resetDownloadCoordinator();
        MusicDownloader.setCreateNewInstanceFlag();
        CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().blockingSubscribe(new Consumer() { // from class: com.amazon.mp3.account.ClearAccountStateHandler$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                ClearAccountStateHandler.lambda$clearAppState$0((CloudQueueClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.account.ClearAccountStateHandler$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                ClearAccountStateHandler.lambda$clearAppState$1((Throwable) obj);
            }
        });
        Curate.clearAppState(this.mContext);
        PreAuthSplashEligibilityProvider.reset(this.mContext);
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider != null && (featureGateProvider instanceof CachedFeatureFlagProviderImpl)) {
            ((CachedFeatureFlagProviderImpl) featureGateProvider).clearCache();
        }
        UserSubscriptionCache.clearCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$clearAppState$0(CloudQueueClient cloudQueueClient) throws Exception {
        if (cloudQueueClient instanceof NoOpCloudQueueClient) {
            return;
        }
        cloudQueueClient.clearDataStore();
        CloudQueueClientSingleton.INSTANCE.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$clearAppState$1(Throwable th) throws Exception {
        Log.error(TAG, "fail to call getCloudQueueClientObservable", th);
    }
}
