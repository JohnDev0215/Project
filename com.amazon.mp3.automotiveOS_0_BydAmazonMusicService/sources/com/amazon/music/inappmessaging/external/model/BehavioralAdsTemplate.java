package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class BehavioralAdsTemplate implements Serializable {
    private static final String BODY = "body";
    private static final String BUTTON_PRIMARY_TEXT = "buttonPrimaryText";
    private static final String HEADLINE = "headline";
    public final String blockRef;
    public final String body;
    public final String buttonPrimaryText;
    public final String headline;
    public final String template;

    public BehavioralAdsTemplate(final Builder builder) {
        this.buttonPrimaryText = builder.buttonPrimaryText;
        this.body = builder.body;
        this.headline = builder.headline;
        this.blockRef = builder.blockRef;
        this.template = builder.template;
    }

    public static BehavioralAdsTemplate fromDynamicMessage(final DynamicMessage message) throws IllegalArgumentException {
        if (!validateRequiredAttributes(message)) {
            throw new IllegalArgumentException("Failed to build BehavioralAdsTemplate due to missing required fields");
        }
        Map<String, String> map = message.templateAttributes;
        return new Builder().withButtonPrimaryText(map.get(BUTTON_PRIMARY_TEXT)).withBody(map.get(BODY)).withHeadline(map.get(HEADLINE)).withBlockRef(message.messageRef).withTemplate(message.templateId).build();
    }

    private static boolean validateRequiredAttributes(final DynamicMessage dynamicMessage) {
        Map<String, String> map = dynamicMessage.templateAttributes;
        return (map == null || map.get(BUTTON_PRIMARY_TEXT) == null || dynamicMessage.templateAttributes.get(BODY) == null || dynamicMessage.templateAttributes.get(HEADLINE) == null) ? false : true;
    }

    public static class Builder {
        private String blockRef;
        private String body;
        private String buttonPrimaryText;
        private String headline;
        private String template;

        public Builder withButtonPrimaryText(final String buttonPrimaryText) {
            this.buttonPrimaryText = buttonPrimaryText;
            return this;
        }

        public Builder withBody(final String body) {
            this.body = body;
            return this;
        }

        public Builder withHeadline(final String headline) {
            this.headline = headline;
            return this;
        }

        public Builder withBlockRef(final String blockRef) {
            this.blockRef = blockRef;
            return this;
        }

        public Builder withTemplate(final String template) {
            this.template = template;
            return this;
        }

        public BehavioralAdsTemplate build() {
            return new BehavioralAdsTemplate(this);
        }
    }
}
