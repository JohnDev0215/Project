package com.amazon.music.inappmessaging.internal.content;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.Endpoints;
import com.amazon.music.inappmessaging.external.WeblabHelper;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageTemplate;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgNativeContentHandlerImpl implements DynMsgNativeContentHandler {
    private static final String BROWSE_HOME = "https://music.amazon.com/home";
    private static final String CREATE_SUBSCRIPTION = "action:/createSubscription";
    private static final String DISMISS = "action:/dismiss";
    private static final String GENERAL_FALLBACK_REF = "Fallback_";
    private static final String TAG = "DynMsgNativeContentHandlerImpl";
    private static final long ONE_YEARS_IN_MILLISECONDS = 31536000000L;
    private static final long NATIVE_MESSAGE_EXPIRY = System.currentTimeMillis() + ONE_YEARS_IN_MILLISECONDS;

    private String generateBehavioralAdsFallbackMessageRef() {
        return "2|P|19|-1|CX6GDPZCAKP34X16RJH0|-1|-1|null|NightwingBehavioralAdsNotification|T1|serviceNotifications";
    }

    @Override // com.amazon.music.inappmessaging.internal.content.DynMsgNativeContentHandler
    public DynamicMessage getDynamicMessage(final Activity activity, final DynamicMessageEvent event) {
        Resources resources = activity.getResources();
        DynamicMessageEvent.DynamicMessageEventType eventType = event.getEventType();
        Context applicationContext = activity.getApplicationContext();
        if (event.getEvent().equals(Trigger.FIRST_STREAM.getName())) {
            if (BehavioralAdsUtil.hasShownBehavioralAdsThroughDynMsg(applicationContext)) {
                return null;
            }
            BehavioralAdsUtil.saveBehavioralAdsNotificationState(applicationContext);
        }
        if (eventType == DynamicMessageEvent.DynamicMessageEventType.TRIGGER) {
            return getDynamicMessageForTrigger(resources, event.getEvent());
        }
        if (eventType == DynamicMessageEvent.DynamicMessageEventType.ACTION) {
            return getDynamicMessageForAction(event.getEvent());
        }
        Log.e(TAG, "Native content unavailable for eventType: " + eventType);
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.music.inappmessaging.internal.content.DynMsgNativeContentHandlerImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger;

        static {
            int[] iArr = new int[Trigger.values().length];
            $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger = iArr;
            try {
                iArr[Trigger.INELIGIBLE_CONTENT_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.FEATURE_UNAVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.SKIP_LIMIT_REACHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.PERSISTENT_UPSELL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.STREAM_UPSELL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.ADS_UPSELL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.SETTINGS_UPSELL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.ON_DEMAND_LISTENING_UPSELL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.TRACK_SHUFFLE_PLAYBACK_UPSELL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.BROWSE_HOME_WIDGET_UPSELL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.CONCURRENCY_UPSELL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[Trigger.FIRST_STREAM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private DynamicMessage getDynamicMessageForTrigger(final Resources resources, final String eventName) {
        try {
            Trigger triggerFromName = Trigger.fromName(eventName);
            switch (AnonymousClass1.$SwitchMap$com$amazon$music$inappmessaging$external$model$Trigger[triggerFromName.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    return getNativeContentForBauhausModal(resources, generateModalFallbackMessageRef(eventName), triggerFromName);
                case 12:
                    return getNativeContentForBehavioralAdsDialog(resources, generateBehavioralAdsFallbackMessageRef());
                default:
                    Log.e(TAG, "Native content unavailable for trigger: " + eventName);
                    return null;
            }
        } catch (Exception unused) {
            Log.e(TAG, "Error converting to trigger from the given event name: " + eventName);
            return null;
        }
    }

    private DynamicMessage getDynamicMessageForAction(final String eventName) {
        if (eventName.startsWith(CREATE_SUBSCRIPTION)) {
            return getNativeContentForCreateSubscription(generateSplashFallbackMessageRef(eventName));
        }
        Log.e(TAG, "Native content unavailable for action: " + eventName);
        return null;
    }

    private DynamicMessage getNativeContentForCreateSubscription(final String messageRef) {
        HashMap map = new HashMap();
        map.put("deeplink", Endpoints.get().getSignupEndpoint());
        return getNativeContent(DynamicMessageTemplate.DM_DYNMSG_FULL_SCREEN_DEEPLINK, map, messageRef);
    }

    @VisibleForTesting
    DynamicMessage getNativeContentForBauhausModal(final Resources resources, final String messageRef, final Trigger trigger) {
        HashMap map = new HashMap();
        if (WeblabHelper.getInstance().isFireOS()) {
            populateNormalCriticalFallbackWithExternalLink(resources, trigger, map);
            return getNativeContent(DynamicMessageTemplate.DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE, map, messageRef);
        }
        if (WeblabHelper.getInstance().isGPBLaunchedInTerritory() && WeblabHelper.getInstance().isCustomerAmazonWallet()) {
            populateNormalCriticalFallbackWithExternalLink(resources, trigger, map);
        } else {
            populateConsumptionOnlyAttributes(resources, trigger, map);
        }
        return getNativeContent(DynamicMessageTemplate.DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE, map, messageRef);
    }

    private void populateConsumptionOnlyAttributes(Resources resources, Trigger trigger, Map<String, String> templateAttributes) {
        templateAttributes.put("headline", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_title_consumption_only));
        if (trigger.equals(Trigger.PERSISTENT_UPSELL)) {
            templateAttributes.put("byline", resources.getString(R.string.dmusic_nightwing_persistent_upsell_dialog_message_consumption_only));
            templateAttributes.put("primaryButtonLink", DISMISS);
            if (!DynamicMessageModalUtil.isNightwingUser()) {
                templateAttributes.put("primaryButtonLink", BROWSE_HOME);
                templateAttributes.put("secondaryButtonLink", DISMISS);
            }
        } else {
            templateAttributes.put("byline", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_message_consumption_only));
            templateAttributes.put("primaryButtonLink", DISMISS);
        }
        templateAttributes.put("primaryButtonText", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_primary_button_text_consumption_only));
        templateAttributes.put("comfortText", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_comfort_text_consumption_only));
    }

    private void populateNormalCriticalFallbackWithExternalLink(Resources resources, Trigger trigger, Map<String, String> templateAttributes) {
        templateAttributes.put("headline", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_title));
        if (trigger.equals(Trigger.PERSISTENT_UPSELL)) {
            templateAttributes.put("byline", resources.getString(R.string.dmusic_nightwing_persistent_upsell_dialog_message));
            if (!DynamicMessageModalUtil.isNightwingUser()) {
                templateAttributes.put("secondaryButtonLink", DISMISS);
            }
        } else {
            templateAttributes.put("byline", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_message));
        }
        templateAttributes.put("primaryButtonText", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_primary_button_text));
        templateAttributes.put("primaryButtonLink", Endpoints.get().getSignupEndpoint());
        templateAttributes.put("comfortText", resources.getString(R.string.dmusic_bauhaus_blocking_dialog_comfort_text));
    }

    private DynamicMessage getNativeContentForBehavioralAdsDialog(final Resources resources, final String messageRef) {
        HashMap map = new HashMap();
        map.put("headline", resources.getString(R.string.dmusic_behavioral_ads_headline));
        map.put("body", resources.getString(R.string.dmusic_behavioral_ads_body));
        map.put("buttonPrimaryText", resources.getString(R.string.dmusic_behavioral_ads_buttonPrimaryText));
        return getNativeContent(DynamicMessageTemplate.DM_DYNMSG_BEHAVIORAL_ADS_TEMPLATE, map, messageRef);
    }

    private DynamicMessage getNativeContent(final DynamicMessageTemplate dynamicMessageTemplate, final Map<String, String> attributes, final String messageRef) {
        DynamicMessage dynamicMessage = new DynamicMessage();
        dynamicMessage.expirationTime = NATIVE_MESSAGE_EXPIRY;
        dynamicMessage.weblabsToTrigger = new ArrayList();
        dynamicMessage.templateId = dynamicMessageTemplate.getName();
        dynamicMessage.messageRef = messageRef;
        dynamicMessage.templateAttributes = attributes;
        return dynamicMessage;
    }

    private String generateModalFallbackMessageRef(final String eventName) {
        return "1|C|19|13|CX6GDPZCAKP34X16RJH0|0|-1|-1|null|" + generateFallbackCampaignName(eventName) + "|C|upsell";
    }

    private String generateSplashFallbackMessageRef(final String eventName) {
        return "2|P|19|-1|CX6GDPZCAKP34X16RJH0|-1|-1|null|" + generateFallbackCampaignName(eventName) + "|C|upsell";
    }

    private String generateFallbackCampaignName(final String eventName) {
        return GENERAL_FALLBACK_REF + Uri.parse(eventName).getPathSegments().get(0);
    }
}
