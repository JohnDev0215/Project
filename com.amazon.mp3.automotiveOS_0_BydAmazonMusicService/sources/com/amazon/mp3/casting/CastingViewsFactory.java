package com.amazon.mp3.casting;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.DemoModeManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.castingviews.CastingDeviceListView;
import com.amazon.music.castingviews.CastingFactory;
import com.amazon.music.castingviews.Configuration;
import com.amazon.music.chromecast.ChromecastController;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class CastingViewsFactory {
    private static final int DEFAULT_CASTING_SESSION_MAX_TIMEOUT_SECONDS = (int) TimeUnit.MINUTES.toSeconds(30);
    private static final String TAG = "CastingViewsFactory";

    public static Configuration.Builder createCastingConfiguration(final Context activityContext) {
        return Configuration.builder(activityContext, new CastingSessionConfig.Builder(AmazonApplication.getConfiguration(activityContext).getInteger("casting_session_max_timeout_seconds", DEFAULT_CASTING_SESSION_MAX_TIMEOUT_SECONDS), AmazonApplication.getConfiguration(activityContext).getInteger("casting_session_max_device_offline_timeout_seconds", 180), AmazonApplication.getConfiguration(activityContext).getInteger("casting_session_min_reconnect_interval_seconds", 5), AmazonApplication.getConfiguration(activityContext).getInteger("casting_session_max_reconnect_interval_seconds", 5), AmazonApplication.getConfiguration(activityContext).getInteger("casting_session_reconnect_attempts_threshold", 2), createAllowedParentalControls()).build(), new RequestInterceptorFactory(activityContext.getApplicationContext()).provideRequestInterceptor(), AccountDetailUtil.get(activityContext.getApplicationContext()).getHomeMarketPlace());
    }

    public static CastingDeviceListView createCastingDeviceListView(final Fragment fragment) {
        return createCastingDeviceListView(fragment.getActivity());
    }

    public static CastingDeviceListView createCastingDeviceListView(final FragmentActivity activityContext) {
        if (activityContext == null) {
            return null;
        }
        Configuration.Builder builderCreateCastingConfiguration = createCastingConfiguration(activityContext);
        builderCreateCastingConfiguration.withChromecastDevices(ChromecastController.getInstance().isInitialized());
        builderCreateCastingConfiguration.withAlexaDevices(CastingUtil.isAlexaCastingEnabled());
        builderCreateCastingConfiguration.withHelpUrl(getHelpUrl());
        builderCreateCastingConfiguration.withWrapDeviceName(true);
        builderCreateCastingConfiguration.withDemoMode(DemoModeManager.get(activityContext).isDemoMode());
        builderCreateCastingConfiguration.withDemoModeWarningMessage(DemoModeManager.get(activityContext).getDemoWarningString());
        builderCreateCastingConfiguration.withCurrentDeviceId(AccountCredentialStorage.get(activityContext.getApplicationContext()).getDeviceId());
        Uri currentUri = MediaItemHelper.getCurrentUri();
        if (currentUri != null) {
            builderCreateCastingConfiguration.hasTracksLoaded();
            if (DefaultUriMatcher.match(currentUri) == 35) {
                builderCreateCastingConfiguration.disableAlexaDevices(activityContext.getString(R.string.casting_unavailable_with_alexa_tracks));
            }
        }
        Configuration configurationBuild = builderCreateCastingConfiguration.build();
        CastingSessionManager.getInstance().initialize(CastingFactory.createCasting(configurationBuild), 1L);
        return new CastingDeviceListView(configurationBuild);
    }

    public static ClientThingShadow.State.ReportedNode.AllowedParentalControls createAllowedParentalControls() {
        ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls = new ClientThingShadow.State.ReportedNode.AllowedParentalControls();
        allowedParentalControls.hasExplicitLanguage = Boolean.valueOf(!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
        return allowedParentalControls;
    }

    public static String getHelpUrl() {
        EndPointURL endPointURL;
        if (AccountDetailUtil.getMusicTerritoryOfResidence().equals(MusicTerritory.BRAZIL.value)) {
            String webviewRowLocalizedEndpoint = EndpointsProvider.get().getWebviewRowLocalizedEndpoint();
            if (webviewRowLocalizedEndpoint == null || TextUtils.isEmpty(webviewRowLocalizedEndpoint)) {
                Log.error(TAG, "Failed to get valid Casting help url endpoint");
                return null;
            }
            endPointURL = new EndPointURL(EndpointsProvider.get().getWebviewRowLocalizedEndpoint());
            endPointURL.appendPath("gp/help/customer/display.html/?nodeId=GNPY8ZGH6BMS7UJQ");
        } else {
            endPointURL = Environment.WEBVIEW.get();
            if (endPointURL == null || TextUtils.isEmpty(endPointURL.toURL())) {
                Log.error(TAG, "Failed to get valid Casting help url, endpoint: " + endPointURL);
                return null;
            }
            endPointURL.appendPath("gp/help/customer/display.html/?nodeId=G9PLTLV6FNDY29CB");
        }
        return endPointURL.toURL();
    }
}
