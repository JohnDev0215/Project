package com.amazon.mp3.navigation.webtargetconverter;

import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilder;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.destination.SubscriptionSettingsDestination;

/* JADX INFO: loaded from: classes3.dex */
public class SubscriptionSettingsWebTargetConverter {
    private SettingsPageWebTargetBuilderFactory settingsPageWebTargetBuilderFactory;

    public SubscriptionSettingsWebTargetConverter(final SettingsPageWebTargetBuilderFactory settingsPageWebTargetBuilderFactory) {
        this.settingsPageWebTargetBuilderFactory = settingsPageWebTargetBuilderFactory;
    }

    public WebTarget convert(final SubscriptionSettingsDestination destination) {
        SettingsPageWebTargetBuilder settingsPageWebTargetBuilderNewBuilder = this.settingsPageWebTargetBuilderFactory.newBuilder();
        SubscriptionSettingsDestination.SubscriptionSetting subscriptionSetting = destination.getSubscriptionSetting();
        if (subscriptionSetting == SubscriptionSettingsDestination.SubscriptionSetting.PAYMENT) {
            settingsPageWebTargetBuilderNewBuilder.withEditPaymentView();
        } else if (subscriptionSetting == SubscriptionSettingsDestination.SubscriptionSetting.RENEWAL) {
            if (destination.getIsUserConfirmationRequired()) {
                settingsPageWebTargetBuilderNewBuilder.withConfirmRenewalView();
            } else {
                settingsPageWebTargetBuilderNewBuilder.withAutoRenewalView();
            }
        }
        settingsPageWebTargetBuilderNewBuilder.withQueryParamsToForward(destination.getQueryParameters());
        return settingsPageWebTargetBuilderNewBuilder.build();
    }
}
