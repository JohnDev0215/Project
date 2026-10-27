package com.amazon.music.metrics.mts.event.definition.push;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class PushCampaignEvent extends MTSEvent {
    private PushCampaignEvent(Builder builder) {
        super("pushNotificationInteraction", 2L);
        addAttribute("actionType", "push.campaign." + builder.actionType.toLowerCase());
        addAttribute("subDeviceId", builder.endpointId);
        addAttribute(Splash.PARAMS_CAMPAIGN_ID, builder.campaignId);
        addAttribute("campaignContent", builder.treatmentId);
        addAttribute("campaignMedium", builder.campaignType);
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, builder.optOutType);
        addAttribute("errorMessage", builder.errorMessage);
    }

    public static Builder builder(String str, String str2, String str3, String str4, String str5) {
        return new Builder(str, str2, str3, str4, str5);
    }

    public static class Builder {
        private String actionType;
        private String campaignId;
        private String campaignType;
        private String endpointId;
        private String errorMessage;
        private String optOutType;
        private String treatmentId;

        private Builder(String str, String str2, String str3, String str4, String str5) {
            this.actionType = str;
            this.endpointId = str2;
            this.campaignId = str3;
            this.treatmentId = str4;
            this.campaignType = str5;
        }

        public Builder withOptOutType(String str) {
            this.optOutType = str;
            return this;
        }

        public PushCampaignEvent build() {
            return new PushCampaignEvent(this);
        }
    }
}
