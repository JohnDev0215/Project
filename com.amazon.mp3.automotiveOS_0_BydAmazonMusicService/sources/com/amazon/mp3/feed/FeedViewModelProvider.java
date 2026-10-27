package com.amazon.mp3.feed;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.connect.feed.FeedMetrics;
import com.amazon.music.connect.feed.FeedViewModel;
import com.amazon.music.connect.feed.FeedViewModelFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedViewModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/feed/FeedViewModelProvider;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "WOODSTOCK_ACTIVITY_FEED_VERSION", "", "feedVersion", "get", "Lcom/amazon/music/connect/feed/FeedViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeedViewModelProvider {
    private final String WOODSTOCK_ACTIVITY_FEED_VERSION;
    private final FragmentActivity activity;
    private String feedVersion;

    public FeedViewModelProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.WOODSTOCK_ACTIVITY_FEED_VERSION = "3";
        this.activity = activity;
        this.feedVersion = "2";
    }

    public final FeedViewModel get() {
        if (!AmazonApplication.getCapabilities().isActivityFeedSupported()) {
            return null;
        }
        if (AmazonApplication.getCapabilities().isWoodstockEnabled()) {
            this.feedVersion = this.WOODSTOCK_ACTIVITY_FEED_VERSION;
        }
        boolean zIsArtistFollowSupported = AmazonApplication.getCapabilities().isArtistFollowSupported();
        final AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.activity);
        final AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(this.activity);
        EndpointsProvider endpointsProvider = EndpointsProvider.get();
        return (FeedViewModel) ViewModelProviders.of(this.activity, new FeedViewModelFactory(new IdentityProvider() { // from class: com.amazon.mp3.feed.FeedViewModelProvider$get$identityProvider$1
            @Override // com.amazon.music.connect.IdentityProvider
            public String getToken() {
                try {
                    return MAPCookiesManager.getCookiesManager().getToken();
                } catch (Exception e) {
                    FeedMetrics.emitErrorMetric("AuthenticationError", e.toString());
                    return null;
                }
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getCustomerId() {
                AccountDetailUtil accountDetailUtil2 = accountDetailUtil;
                if (accountDetailUtil2 == null) {
                    return null;
                }
                return accountDetailUtil2.getCustomerId();
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getDeviceId() {
                AccountCredentialStorage accountCredentialStorage2 = accountCredentialStorage;
                if (accountCredentialStorage2 == null) {
                    return null;
                }
                return accountCredentialStorage2.getDeviceId();
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getDeviceType() {
                AccountCredentialStorage accountCredentialStorage2 = accountCredentialStorage;
                if (accountCredentialStorage2 == null) {
                    return null;
                }
                return accountCredentialStorage2.getDeviceType();
            }
        }, this.feedVersion, endpointsProvider != null ? endpointsProvider.getActivityFeedApiEndpoint() : null, zIsArtistFollowSupported)).get(FeedViewModel.class);
    }
}
