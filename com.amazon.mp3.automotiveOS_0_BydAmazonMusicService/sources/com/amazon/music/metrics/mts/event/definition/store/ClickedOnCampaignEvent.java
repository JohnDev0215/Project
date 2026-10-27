package com.amazon.music.metrics.mts.event.definition.store;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class ClickedOnCampaignEvent extends MTSEvent {
    public ClickedOnCampaignEvent(Campaign campaign) {
        super("userClickedonCampaign");
        addEventAttributes(campaign);
    }

    private void addEventAttributes(Campaign campaign) {
        addAttribute(Splash.PARAMS_CAMPAIGN_ID, campaign.getName());
        addAttribute("refTag", campaign.getSource());
        addAttribute("campaignMedium", campaign.getMedium());
        addAttribute("campaignContent", campaign.getContent());
    }
}
