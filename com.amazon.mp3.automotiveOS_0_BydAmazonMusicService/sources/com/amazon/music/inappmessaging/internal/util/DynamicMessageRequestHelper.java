package com.amazon.music.inappmessaging.internal.util;

import com.amazon.music.inappmessaging.external.model.Channel;
import com.amazon.music.inappmessaging.external.model.ContextualTriggerAllowList;
import com.amazon.music.inappmessaging.external.model.DynamicMessageClientCapabilities;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageRequest;
import com.amazon.music.inappmessaging.external.model.DynamicMessageTemplate;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageRequestHelper {
    public static DynamicMessageRequest createDynamicMessageRequestForBatchCall(final String mtr) {
        ArrayList arrayList = new ArrayList();
        for (Trigger trigger : Trigger.values()) {
            arrayList.add(new DynamicMessageEvent(trigger));
        }
        return new DynamicMessageRequest(Channel.IAM, arrayList, DynamicMessageTemplate.getAllTemplates(), null, mtr, ContextualTriggerAllowList.getEligibleContextualTriggerParams(), getClientCapabilities());
    }

    public static DynamicMessageRequest createDynamicMessageDeeplinkRequest(final DynamicMessageEvent event, final String mtr, final boolean isFTU) {
        return new DynamicMessageRequest(Channel.IAM, Collections.singletonList(event), isFTU ? DynamicMessageTemplate.getFTUTemplates() : DynamicMessageTemplate.getAllTemplates(), null, mtr, ContextualTriggerAllowList.getEligibleContextualTriggerParams(), getClientCapabilities());
    }

    public static boolean isDynamicMessageValid(final boolean isCriticalMessage, final long currentEpochInMillis, final long dynMsgResponseEpochTimeToLiveinMillis, final DynamicMessage dynamicMessage) {
        return isCriticalMessage ? currentEpochInMillis < dynamicMessage.expirationTime : currentEpochInMillis < dynMsgResponseEpochTimeToLiveinMillis && currentEpochInMillis < dynamicMessage.expirationTime;
    }

    public static DynamicMessageClientCapabilities getClientCapabilities() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return new DynamicMessageClientCapabilities(featureGateProvider != null && featureGateProvider.isFeatureAvailable(ClientCapabilitiesSupportFeature.empty_control_support));
    }
}
