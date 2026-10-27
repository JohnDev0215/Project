package com.amazon.podcast.cast;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.hermes.RequestFacade;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingDeviceListView;
import com.amazon.music.castingviews.CastingFactory;
import com.amazon.music.castingviews.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class CastingViewFactory {
    private static final int DEFAULT_CASTING_SESSION_MAX_TIMEOUT_SECONDS = (int) TimeUnit.MINUTES.toSeconds(30);

    public static Configuration.Builder createCastingConfiguration(final Context context) {
        return Configuration.builder(context, new CastingSessionConfig.Builder(DEFAULT_CASTING_SESSION_MAX_TIMEOUT_SECONDS, 60, 5, 5, 2, null).build(), new RequestInterceptor() { // from class: com.amazon.podcast.cast.CastingViewFactory.1
            @Override // com.amazon.hermes.RequestInterceptor
            public void intercept(RequestFacade requestFacade) throws Exception {
            }

            @Override // com.amazon.hermes.RequestInterceptor
            public void intercept(RequestFacade requestFacade, ObjectMapper objectMapper) throws Exception {
            }
        }, AccountManagerSingleton.get().getMarketplace());
    }

    public static CastingDeviceListView createCastingDeviceListView(final FragmentActivity activityContext) {
        if (activityContext == null) {
            return null;
        }
        Configuration.Builder builderCreateCastingConfiguration = createCastingConfiguration(activityContext);
        builderCreateCastingConfiguration.withChromecastDevices(true);
        builderCreateCastingConfiguration.withAlexaDevices(false);
        builderCreateCastingConfiguration.withHelpUrl("https://www.amazon.com:443/gp/help/customer/display.html/?nodeId=G9PLTLV6FNDY29CB");
        Configuration configurationBuild = builderCreateCastingConfiguration.build();
        CastingSessionManager.getInstance().initialize(CastingFactory.createCasting(configurationBuild));
        return new CastingDeviceListView(configurationBuild);
    }
}
