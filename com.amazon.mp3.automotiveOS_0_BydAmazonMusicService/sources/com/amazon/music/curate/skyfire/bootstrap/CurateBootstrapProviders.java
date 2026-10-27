package com.amazon.music.curate.skyfire.bootstrap;

import android.content.Context;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.provider.EndpointSettingsProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.FragmentsStackProvider;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.provider.LibraryNavigationProvider;
import com.amazon.music.curate.provider.NetworkProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.PlaylistProvider;
import com.amazon.music.curate.provider.StyleSheetProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateBootstrapProviders {
    private static final String TAG = "CurateBootstrapProviders";
    private static final Logger logger = LoggerFactory.getLogger(CurateBootstrapProviders.class.getSimpleName());
    private AuthenticationProvider authenticationProvider;
    private BuildInfoProvider buildInfoProvider;
    private Context context;
    private CustomerMetadataProvider customerMetadataProvider;
    private DeviceInfoProvider deviceInfoProvider;
    private EndpointSettingsProvider endpointSettingsProvider;
    private FeatureFlagProvider featureFlagProvider;
    private FragmentsStackProvider fragmentsStackProvider;
    private LibraryNavigationProvider libraryNavigationProvider;
    private NetworkProvider networkProvider;
    private PlaybackProvider playbackProvider;
    private PlaylistProvider playlistProvider;
    private StyleSheetProvider styleSheetProvider;
    private SubscriptionProvider subscriptionProvider;

    public final Context getContext() {
        return this.context;
    }

    public final EndpointSettingsProvider getEndpointSettingsProvider() {
        return this.endpointSettingsProvider;
    }

    public final FragmentsStackProvider getFragmentsStackProvider() {
        return this.fragmentsStackProvider;
    }

    public final AuthenticationProvider getAuthProvider() {
        return this.authenticationProvider;
    }

    public final BuildInfoProvider getBuildInfoProvider() {
        return this.buildInfoProvider;
    }

    public final CustomerMetadataProvider getCustomerMetadataProvider() {
        return this.customerMetadataProvider;
    }

    public final DeviceInfoProvider getDeviceInfoProvider() {
        return this.deviceInfoProvider;
    }

    public final LibraryNavigationProvider getLibraryNavigationProvider() {
        return this.libraryNavigationProvider;
    }

    public final PlaybackProvider getPlaybackProvider() {
        return this.playbackProvider;
    }

    public final void setPlaybackProvider(PlaybackProvider provider) {
        this.playbackProvider = provider;
    }

    public final PlaylistProvider getPlaylistProvider() {
        return this.playlistProvider;
    }

    public final NetworkProvider getNetworkProvider() {
        return this.networkProvider;
    }

    public final void setNetworkProvider(NetworkProvider provider) {
        this.networkProvider = provider;
    }

    public final StyleSheetProvider getStyleSheetProvider() {
        return this.styleSheetProvider;
    }

    public final void setStyleSheetProvider(StyleSheetProvider provider) {
        this.styleSheetProvider = provider;
    }

    public final SubscriptionProvider getSubscriptionProvider() {
        return this.subscriptionProvider;
    }

    public final void setSubscriptionProvider(SubscriptionProvider provider) {
        this.subscriptionProvider = provider;
    }

    public final FeatureFlagProvider getFeatureFlagProvider() {
        return this.featureFlagProvider;
    }

    public final AppDatabase getAppDatabase() {
        InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
        AppDatabase database = internalProvider.getDatabase();
        return database == null ? internalProvider.provideDatabase(this.context) : database;
    }

    public CurateBootstrapProviders(final Context context, final EndpointSettingsProvider endpointSettingsProvider, final FragmentsStackProvider fragmentsStackProvider, final LibraryNavigationProvider libraryNavigationProvider, final NetworkProvider networkProvider, final StyleSheetProvider styleSheetProvider, final PlaybackProvider playbackProvider, final SubscriptionProvider subscriptionProvider, final PlaylistProvider playlistProvider, final FeatureFlagProvider featureFlagProvider) {
        this.context = context;
        this.fragmentsStackProvider = fragmentsStackProvider;
        Providers providers = Providers.INSTANCE;
        this.authenticationProvider = (AuthenticationProvider) providers.get(AuthenticationProvider.class);
        this.buildInfoProvider = (BuildInfoProvider) providers.get(BuildInfoProvider.class);
        this.customerMetadataProvider = (CustomerMetadataProvider) providers.get(CustomerMetadataProvider.class);
        this.deviceInfoProvider = (DeviceInfoProvider) providers.get(DeviceInfoProvider.class);
        this.endpointSettingsProvider = endpointSettingsProvider;
        this.libraryNavigationProvider = libraryNavigationProvider;
        this.networkProvider = networkProvider;
        this.styleSheetProvider = styleSheetProvider;
        this.playbackProvider = playbackProvider;
        this.playlistProvider = playlistProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.featureFlagProvider = featureFlagProvider;
    }

    public void reset() {
        logger.debug("Reset CurateBootstrapProviders");
        this.context = null;
        this.fragmentsStackProvider = null;
        this.authenticationProvider = null;
        this.buildInfoProvider = null;
        this.customerMetadataProvider = null;
        this.deviceInfoProvider = null;
        this.endpointSettingsProvider = null;
        this.libraryNavigationProvider = null;
        this.networkProvider = null;
        this.styleSheetProvider = null;
        this.playbackProvider = null;
        this.playlistProvider = null;
        this.subscriptionProvider = null;
        this.featureFlagProvider = null;
    }

    public boolean isAtLeastOneProviderNull() {
        Object[] objArr = {this.context, this.fragmentsStackProvider, this.endpointSettingsProvider, this.libraryNavigationProvider, this.networkProvider, this.styleSheetProvider, this.playbackProvider, this.playlistProvider, this.subscriptionProvider, this.featureFlagProvider, this.authenticationProvider, this.buildInfoProvider, this.customerMetadataProvider, this.deviceInfoProvider};
        for (int i = 0; i < 14; i++) {
            if (objArr[i] == null) {
                return true;
            }
        }
        return false;
    }
}
