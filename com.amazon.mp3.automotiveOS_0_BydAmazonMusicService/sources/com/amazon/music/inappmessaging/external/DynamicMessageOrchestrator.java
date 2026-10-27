package com.amazon.music.inappmessaging.external;

import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.inappmessaging.config.Configuration;
import com.amazon.music.inappmessaging.config.ConfigurationFactory;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl;
import com.amazon.music.inappmessaging.internal.cache.database.DynamicMessagingCache;
import com.amazon.music.inappmessaging.internal.content.DynMsgActionHandlerImpl;
import com.amazon.music.inappmessaging.internal.content.DynMsgContentHandlerImpl;
import com.amazon.music.inappmessaging.internal.content.DynMsgNativeContentHandlerImpl;
import com.amazon.music.inappmessaging.internal.content.DynMsgWeblabHandlerImpl;
import com.amazon.music.inappmessaging.internal.handler.DynamicMessageTemplateHandler;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApiImpl;
import com.amazon.music.inappmessaging.internal.util.IAMCredentialsHelper;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DynamicMessageOrchestrator {
    private static final String TAG = "DynamicMessageOrchestrator";
    private static DynamicMessageOrchestrator dynamicMessageOrchestrator;

    public abstract void clearCache();

    public abstract Observable<Boolean> displayDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes);

    public abstract Observable<Fragment> displayNonBlockingDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes);

    public abstract void loadDeeplinkDynamicMessage(final DynamicMessageEvent event, final boolean isFTU);

    public abstract void loadDynamicMessage();

    public static synchronized DynamicMessageOrchestrator getInstance() {
        DynamicMessageOrchestrator dynamicMessageOrchestrator2 = dynamicMessageOrchestrator;
        if (dynamicMessageOrchestrator2 != null) {
            return dynamicMessageOrchestrator2;
        }
        Log.e(TAG, "Dynamic message orchestrator has not been initialized");
        return null;
    }

    public static synchronized DynamicMessageOrchestrator newInstance(final Context context, final String marketplace, final IAMCredentialsVendor credentialsVendor, final DeviceInformationVendor deviceInfoVendor, final AssociateTagVendor associateTagVendor, final boolean shouldUseGammaOverride) {
        try {
            if (dynamicMessageOrchestrator == null) {
                DynamicMessagingCache dynamicMessagingCache = DynamicMessagingCache.getInstance(context);
                Configuration configurationCreate = new ConfigurationFactory().create(marketplace, shouldUseGammaOverride);
                InAppMessagesApiImpl inAppMessagesApiImpl = new InAppMessagesApiImpl(configurationCreate.getIamEndpointUrl(), configurationCreate.getDynMsgEndpointUrl(), configurationCreate.getWeblabTriggerEndpointUrl(), configurationCreate.getHandleActionEndpointUrl());
                Observable<IAMCredentials> andValidateIAMCredentials = IAMCredentialsHelper.getAndValidateIAMCredentials(credentialsVendor);
                Observable<DeviceInformation> deviceInformation = IAMCredentialsHelper.getDeviceInformation(deviceInfoVendor);
                DynMsgCacheHandlerImpl dynMsgCacheHandlerImpl = new DynMsgCacheHandlerImpl(dynamicMessagingCache, inAppMessagesApiImpl, andValidateIAMCredentials, deviceInformation, new DynMsgWeblabHandlerImpl(inAppMessagesApiImpl, andValidateIAMCredentials, deviceInformation));
                DynMsgNativeContentHandlerImpl dynMsgNativeContentHandlerImpl = new DynMsgNativeContentHandlerImpl();
                dynamicMessageOrchestrator = new DynamicMessageOrchestratorImpl(context, dynMsgCacheHandlerImpl, new DynMsgContentHandlerImpl(dynMsgCacheHandlerImpl, dynMsgNativeContentHandlerImpl), new DynamicMessageTemplateHandler(), new DynMsgActionHandlerImpl(inAppMessagesApiImpl, andValidateIAMCredentials, deviceInformation, associateTagVendor, dynMsgNativeContentHandlerImpl));
            }
        } catch (Exception e) {
            String str = "Error in initializing dynamic message orchestrator: " + e.getMessage();
            String str2 = TAG;
            Log.e(str2, str);
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.OTHER).withErrorMessage(str).withTrigger("N/A").send();
            Log.d(str2, "Initializing fallback orchestrator instance for native content.");
            DynamicMessageNativeOrchestratorImpl dynamicMessageNativeOrchestratorImpl = new DynamicMessageNativeOrchestratorImpl(context, new DynMsgNativeContentHandlerImpl(), new DynamicMessageTemplateHandler());
            dynamicMessageOrchestrator = dynamicMessageNativeOrchestratorImpl;
            return dynamicMessageNativeOrchestratorImpl;
        }
        return dynamicMessageOrchestrator;
    }
}
