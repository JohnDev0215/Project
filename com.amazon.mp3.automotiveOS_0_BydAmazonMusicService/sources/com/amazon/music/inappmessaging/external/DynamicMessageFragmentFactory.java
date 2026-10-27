package com.amazon.music.inappmessaging.external;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.external.model.BehavioralAdsTemplate;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.EngagementSplashTemplate;
import com.amazon.music.inappmessaging.external.model.EvergreenSplashTemplate;
import com.amazon.music.inappmessaging.external.model.FullScreenBauhausModalTemplate;
import com.amazon.music.inappmessaging.external.model.LegacyModalTemplate;
import com.amazon.music.inappmessaging.external.model.ThirdPartyHostedSplashTemplate;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.ui.BehavioralAdsDialogFragment;
import com.amazon.music.inappmessaging.ui.DynamicMessageLegacyModalFragment;
import com.amazon.music.inappmessaging.ui.EngagementSplashDialogFragment;
import com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment;
import com.amazon.music.inappmessaging.ui.FullScreenDynamicMessageModalFragment;
import com.amazon.music.inappmessaging.ui.ThirdPartyHostedDialogFragment;
import com.google.gson.Gson;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageFragmentFactory {
    private static final String BEHAVIORAL_ADS_TEMPLATE = "digital-music-iam-behavioral-ads-notification";
    private static final String ENGAGEMENT_ONE_CTA_NO_ACTION_TEMPLATE = "digital-music-iam-engagement-template-1-cta-no-action";
    private static final String EVERGREEN_SPLASH_TEMPLATE = "digital-music-iam-evergreen";
    private static final String FULL_SCREEN_BAUHAUS_TEMPLATE = "dm-dynmsg-full-screen-bauhaus-modal";
    private static final String JSON_KEY = "json";
    private static final String LEGACY_TEMPLATE = "dm-dynmsg-legacy-modal";
    private static final String METRIC_ATTRIBUTES_KEY = "metricsAttributesJson";
    private static final String TAG = "DynamicMessageFragmentFactory";
    private static final String THIRD_PARTY_HOSTED_TEMPLATE = "third-party-hosted-iam-template";
    private static final Gson gson = new Gson();

    public static DialogFragment createDynamicMessageFragment(final Bundle bundle, final Boolean isBlocking) {
        String string = bundle.getString(JSON_KEY);
        String string2 = bundle.getString(METRIC_ATTRIBUTES_KEY);
        Gson gson2 = gson;
        return createDynamicMessageFragment((DynamicMessage) gson2.fromJson(string, DynamicMessage.class), (DynamicMessageMetricsAttributes) gson2.fromJson(string2, DynamicMessageMetricsAttributes.class), isBlocking);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static DialogFragment createDynamicMessageFragment(final DynamicMessage message, final DynamicMessageMetricsAttributes metricsAttributes, final Boolean isBlocking) {
        try {
            String str = message.templateId;
            byte b = -1;
            switch (str.hashCode()) {
                case -1278521430:
                    if (str.equals(LEGACY_TEMPLATE)) {
                        b = 1;
                    }
                    break;
                case -827925909:
                    if (str.equals(THIRD_PARTY_HOSTED_TEMPLATE)) {
                        b = 5;
                    }
                    break;
                case 177229392:
                    if (str.equals(EVERGREEN_SPLASH_TEMPLATE)) {
                        b = 3;
                    }
                    break;
                case 340935695:
                    if (str.equals(ENGAGEMENT_ONE_CTA_NO_ACTION_TEMPLATE)) {
                        b = 4;
                    }
                    break;
                case 1559312803:
                    if (str.equals(BEHAVIORAL_ADS_TEMPLATE)) {
                        b = 2;
                    }
                    break;
                case 2006144457:
                    if (str.equals(FULL_SCREEN_BAUHAUS_TEMPLATE)) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                return FullScreenDynamicMessageModalFragment.newInstance(FullScreenBauhausModalTemplate.fromDynamicMessage(message), metricsAttributes, isBlocking);
            }
            if (b == 1) {
                return DynamicMessageLegacyModalFragment.newInstance(LegacyModalTemplate.fromDynamicMessage(message), metricsAttributes);
            }
            if (b == 2) {
                return BehavioralAdsDialogFragment.newInstance(BehavioralAdsTemplate.fromDynamicMessage(message));
            }
            if (b == 3) {
                if (WeblabHelper.getInstance().isSplashV2Enabled()) {
                    return EvergreenSplashDialogFragment.newInstance(EvergreenSplashTemplate.fromDynamicMessage(message), metricsAttributes);
                }
                return null;
            }
            if (b == 4) {
                if (WeblabHelper.getInstance().isSplashV2Enabled()) {
                    return EngagementSplashDialogFragment.newInstance(EngagementSplashTemplate.fromDynamicMessage(message), metricsAttributes);
                }
                return null;
            }
            if (b == 5) {
                if (WeblabHelper.getInstance().isSplashV2Enabled()) {
                    return ThirdPartyHostedDialogFragment.newInstance(ThirdPartyHostedSplashTemplate.fromDynamicMessage(message), metricsAttributes);
                }
                return null;
            }
            Log.e(TAG, "Unexpected template from Dynamic Message with templateId: " + message.templateId);
            return null;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "IllegalArgument occurs when construct template for " + message.templateId + " " + e.toString());
            return null;
        }
    }
}
