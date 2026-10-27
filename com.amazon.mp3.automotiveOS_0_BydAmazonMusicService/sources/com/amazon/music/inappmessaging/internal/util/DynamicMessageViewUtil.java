package com.amazon.music.inappmessaging.internal.util;

import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.DynamicMessageTemplate;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageViewUtil {
    private static final String FTU_PAGE_SUBTYPE = "ftu";
    private static final String IAM_PAGE_TYPE = "inAppMessaging";
    private static final Map<String, ContainerType> contentViewContainerTypeMap;

    static {
        HashMap map = new HashMap();
        map.put(DynamicMessageTemplate.DIGITAL_MUSIC_DYNMSG_RIBBON.getName(), ContainerType.RIBBON);
        String name = DynamicMessageTemplate.DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE.getName();
        ContainerType containerType = ContainerType.MODAL;
        map.put(name, containerType);
        map.put(DynamicMessageTemplate.DM_DYNMSG_FULL_SCREEN_DEEPLINK.getName(), containerType);
        map.put(DynamicMessageTemplate.DM_DYNMSG_LEGACY_TEMPLATE.getName(), containerType);
        contentViewContainerTypeMap = Collections.unmodifiableMap(map);
    }

    public static void sendViewEvent(final DynamicMessage message, final DynamicMessageMetricsAttributes metricsAttributes) {
        Map<String, ContainerType> map = contentViewContainerTypeMap;
        if (map.containsKey(message.templateId)) {
            sendUIContentViewEvent(message.messageRef, UUID.randomUUID().toString().toLowerCase(), metricsAttributes.getPageType(), metricsAttributes.getReasonType(), map.get(message.templateId));
        } else {
            sendUIPageViewEvent(message.messageRef, metricsAttributes.getIsFTU());
        }
    }

    public static void sendUIContentViewEvent(final String blockRef, final String localContentId, final String pageType, final String reasonType, final ContainerType containerType) {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withBlockRef(blockRef).withLocalContentId(localContentId).withPageType(pageType).withReasonType(reasonType).withContainerType(containerType).build());
    }

    public static void sendUIPageViewEvent(final String blockRef, final boolean isFTU) {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder("inAppMessaging").withBlockRef(blockRef).withPageSubType(isFTU ? FTU_PAGE_SUBTYPE : null).build());
    }
}
