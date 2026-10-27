package com.amazon.music.inappmessaging.internal.handler;

import android.content.Context;
import android.util.Log;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageTemplateHandler {
    private static final String DM_DYNMSG_BEHAVIORAL_ADS_TEMPLATE = "digital-music-iam-behavioral-ads-notification";
    private static final String DM_DYNMSG_ENGAGEMENT_1CTA_NO_ACTION_TEMPLATE = "digital-music-iam-engagement-template-1-cta-no-action";
    private static final String DM_DYNMSG_EVERGREEN_TEMPLATE = "digital-music-iam-evergreen";
    private static final String DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE = "dm-dynmsg-full-screen-bauhaus-modal";
    private static final String DM_DYNMSG_FULL_SCREEN_DEEPLINK = "dm-dynmsg-full-screen-deeplink";
    private static final String DM_DYNMSG_LEGACY_TEMPLATE = "dm-dynmsg-legacy-modal";
    private static final String DM_DYNMSG_RIBBON_TEMPLATE = "digital-music-dynmsg-ribbon";
    private static final String DM_THIRD_PARTY_HOSTED_TEMPLATE = "third-party-hosted-iam-template";
    private static final String TAG = "DynamicMessageTemplateHandler";
    private static final Map<String, TemplateHandler> templateHandlerMap;

    static {
        HashMap map = new HashMap();
        map.put(DM_DYNMSG_RIBBON_TEMPLATE, DigitalMusicDynMsgRibbonTemplateHandler.get());
        map.put(DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE, DynMsgBlockingTemplateHandler.get());
        map.put(DM_DYNMSG_FULL_SCREEN_DEEPLINK, DynMsgDeeplinkTemplateHandler.get());
        map.put(DM_DYNMSG_LEGACY_TEMPLATE, DynMsgBlockingTemplateHandler.get());
        map.put(DM_DYNMSG_BEHAVIORAL_ADS_TEMPLATE, DynMsgBlockingTemplateHandler.get());
        map.put(DM_DYNMSG_EVERGREEN_TEMPLATE, DynMsgBlockingTemplateHandler.get());
        map.put(DM_DYNMSG_ENGAGEMENT_1CTA_NO_ACTION_TEMPLATE, DynMsgBlockingTemplateHandler.get());
        map.put(DM_THIRD_PARTY_HOSTED_TEMPLATE, DynMsgBlockingTemplateHandler.get());
        templateHandlerMap = Collections.unmodifiableMap(map);
    }

    public Observable<Boolean> displayMessageForTemplate(final Context context, final DynamicMessage message, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        Map<String, TemplateHandler> map = templateHandlerMap;
        if (map.containsKey(message.templateId)) {
            return map.get(message.templateId).displayMessage(context, message, metricsAttributes);
        }
        String str = "Unable to find template handler for given template ID: " + message.templateId;
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.VIEW_MODEL_MAPPING_ERROR).withErrorMessage(str).withEvent(event.getEvent()).send();
        Log.e(TAG, str);
        return Observable.just(Boolean.FALSE);
    }
}
