package com.amazon.music.inappmessaging.external;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import com.amazon.music.inappmessaging.config.Configuration;
import com.amazon.music.inappmessaging.config.ConfigurationFactory;
import com.amazon.music.inappmessaging.external.events.EventType;
import com.amazon.music.inappmessaging.external.events.IAMEventSender;
import com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApi;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApiImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class InAppMessageClientApi {
    private static InAppMessageClientApi inAppMessageClientApi;
    protected final Configuration configuration;
    protected final IAMCredentialsVendor credentialsVendor;
    protected final IAMWebViewDeeplinkHandler deeplinkHandler;
    protected final DeviceInformationVendor deviceInformationVendor;
    protected final InAppMessagesApi inAppMessagesApiClient;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) InAppMessageClientApi.class);
    private static boolean shouldRefresh = false;

    public abstract void dismissVisibleSplash(FragmentManager fragmentManager);

    public abstract Observable<Boolean> displayInAppMessage(final FragmentManager fragmentManager, final Context context, final IAMRequest request, final Boolean isNightwingOnly, final Boolean isFTU);

    public abstract Observable<Splash> loadInAppMessage(IAMRequest request);

    protected InAppMessageClientApi(InAppMessagesApi inAppMessagesApi, IAMCredentialsVendor credentialsVendor, DeviceInformationVendor deviceInformationVendor, IAMWebViewDeeplinkHandler deeplinkHandler, Configuration configuration) {
        this.inAppMessagesApiClient = inAppMessagesApi;
        this.credentialsVendor = credentialsVendor;
        this.deviceInformationVendor = deviceInformationVendor;
        this.deeplinkHandler = deeplinkHandler;
        this.configuration = configuration;
    }

    public static synchronized InAppMessageClientApi newInstance(IAMCredentialsVendor iamCredentialsVendor, DeviceInformationVendor deviceInformationVendor, String iamEndpointUrlOverride, boolean shouldUseGammaOverride, IAMWebViewDeeplinkHandler deeplinkHandler, Context context, String marketplace, String territory) {
        try {
            if (inAppMessageClientApi == null || shouldRefresh) {
                IAMEventSender.withType(EventType.CLIENT_INITIALIZE).send();
                shouldRefresh = false;
                Configuration configurationCreate = new ConfigurationFactory().create(marketplace, shouldUseGammaOverride);
                inAppMessageClientApi = new InAppMessageClientApiImpl(context, new InAppMessagesApiImpl(iamEndpointUrlOverride != null ? iamEndpointUrlOverride : configurationCreate.getIamEndpointUrl(), configurationCreate.getDynMsgEndpointUrl(), configurationCreate.getWeblabTriggerEndpointUrl(), configurationCreate.getHandleActionEndpointUrl()), iamCredentialsVendor, deviceInformationVendor, deeplinkHandler, configurationCreate);
            }
        } catch (IllegalStateException e) {
            IAMEventSender.withType(EventType.ERROR_EXCEPTION).withDescription("Could not instantiate IAM: " + e.getMessage()).send();
            return null;
        }
        return inAppMessageClientApi;
    }

    public static synchronized void forceRefresh() {
        shouldRefresh = true;
    }
}
