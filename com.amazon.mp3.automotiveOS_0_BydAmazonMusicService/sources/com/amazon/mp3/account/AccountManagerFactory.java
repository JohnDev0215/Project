package com.amazon.mp3.account;

import android.content.Context;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerBuilder;
import com.amazon.music.account.AccountServiceConfiguration;
import com.amazon.music.account.MusicIdentityServiceConfiguration;
import com.amazon.music.account.MusicIdentityServiceURLProvider;
import com.amazon.music.account.StratusServiceConfiguration;
import com.amazon.music.account.StratusUrlProvider;
import com.amazon.music.marketplace.Marketplace;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class AccountManagerFactory {
    public AccountManager buildAccountManager(final Context context) {
        if (isSignedIn(context)) {
            return AccountManagerBuilder.newBuilder().withPersistentStorage(context).withUserAlreadySignedIn(buildAccountServiceConfiguration(context), buildStratusServiceConfiguration(context), buildMusicIdentityServiceConfiguration(context)).withPollingDisabled().withAllAutomaticCallsDisabled().withDefaultMarketplace(Marketplace.USA).withDefaultTerritory(MusicTerritory.US.value).build();
        }
        return AccountManagerBuilder.newBuilder().withPersistentStorage(context).withUserNotSignedIn().withAllAutomaticCallsDisabled().withPollingDisabled().withDefaultMarketplace(Marketplace.USA).withDefaultTerritory(MusicTerritory.US.value).build();
    }

    public StratusServiceConfiguration buildStratusServiceConfiguration(final Context context) {
        return new StratusServiceConfiguration.Builder(buildStratusUrlProvider(), buildStratusRequestInterceptor(context)).withRequestInterceptor(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor()).build();
    }

    public MusicIdentityServiceConfiguration buildMusicIdentityServiceConfiguration(final Context context) {
        return new MusicIdentityServiceConfiguration.Builder(buildMISUrlProvider()).withRequestInterceptor(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor()).build();
    }

    private StratusUrlProvider buildStratusUrlProvider() {
        return new MarketStratusUrlProvider();
    }

    private MusicIdentityServiceURLProvider buildMISUrlProvider() {
        return new MarketMusicIdentityServiceUrlProvider();
    }

    private RequestInterceptor buildStratusRequestInterceptor(final Context context) {
        return new RequestInterceptorFactory(context).provideRequestInterceptor();
    }

    public AccountServiceConfiguration buildAccountServiceConfiguration(final Context context) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        return new AccountServiceConfiguration.Builder(accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType()).build();
    }

    private boolean isSignedIn(final Context context) {
        return AccountCredentialUtil.get(context).isSignedIn();
    }

    private static final class MarketStratusUrlProvider implements StratusUrlProvider {
        private static final String TAG = "MarketStratusUrlProvider";

        private MarketStratusUrlProvider() {
        }

        @Override // com.amazon.music.account.StratusUrlProvider
        public URL getStratusUrl(final Marketplace marketplace) {
            try {
                return new URL(Environment.STRATUS.get().toURL());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.amazon.music.account.StratusUrlProvider
        public URL getDefaultStratusUrl() {
            try {
                return EndpointsProvider.get().getDefaultStratusEndpoint();
            } catch (Exception unused) {
                Log.error(TAG, "Stratus default endpoint is null.  Unable to call Stratus for customer information");
                return null;
            }
        }
    }

    private static final class MarketMusicIdentityServiceUrlProvider implements MusicIdentityServiceURLProvider {
        private MarketMusicIdentityServiceUrlProvider() {
        }

        @Override // com.amazon.music.account.MusicIdentityServiceURLProvider
        public URL getMusicIdentityServiceURL() {
            try {
                return EndpointsProvider.get().getMusicIdentityServiceEndpoint();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}
