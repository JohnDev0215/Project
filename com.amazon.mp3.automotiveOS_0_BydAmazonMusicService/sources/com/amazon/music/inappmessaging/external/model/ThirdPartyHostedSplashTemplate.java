package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ThirdPartyHostedSplashTemplate implements Serializable {
    private static final String BUTTON_PRIMARY_COLOR = "buttonPrimaryColor";
    private static final String BUTTON_PRIMARY_LINK = "buttonPrimaryLink";
    private static final String BUTTON_PRIMARY_TEXT = "buttonPrimaryText";
    private static final String BUTTON_SECONDARY_TEXT = "buttonSecondaryText";
    private static final String IS_PRIMARY_BTN_LINK_EXTERNAL = "isPrimaryBtnDeeplinkAnExternalLink";
    private static final String TEMPLATE_IMAGE_LINK = "templateImageLink";
    private static final String TEMPLATE_IMAGE_LINK_LANDSCAPE = "templateImageLinkLandscape";
    private static final String TERMS_OF_USE = "termsOfUse";
    public final String blockRef;
    public final String buttonPrimaryColor;
    public final String buttonPrimaryLink;
    public final String buttonPrimaryText;
    public final String buttonSecondaryText;
    public final String isPrimaryBtnDeeplinkAnExternalLink;
    public final String template;
    public final String templateImageLink;
    public final String templateImageLinkLandscape;
    public final String termsOfUse;

    public ThirdPartyHostedSplashTemplate(final Builder builder) {
        this.buttonPrimaryText = builder.buttonPrimaryText;
        this.isPrimaryBtnDeeplinkAnExternalLink = builder.isPrimaryBtnDeeplinkAnExternalLink;
        this.buttonPrimaryLink = builder.buttonPrimaryLink;
        this.buttonPrimaryColor = builder.buttonPrimaryColor;
        this.buttonSecondaryText = builder.buttonSecondaryText;
        this.termsOfUse = builder.termsOfUse;
        this.templateImageLink = builder.templateImageLink;
        this.templateImageLinkLandscape = builder.templateImageLinkLandscape;
        this.blockRef = builder.blockRef;
        this.template = builder.template;
    }

    public static ThirdPartyHostedSplashTemplate fromDynamicMessage(final DynamicMessage message) {
        Map<String, String> map = message.templateAttributes;
        return new Builder().withButtonPrimaryText(map.get(BUTTON_PRIMARY_TEXT)).withIsPrimaryBtnDeeplinkAnExternalLink(map.get(IS_PRIMARY_BTN_LINK_EXTERNAL)).withButtonPrimaryLink(map.get(BUTTON_PRIMARY_LINK)).withButtonPrimaryColor(map.get(BUTTON_PRIMARY_COLOR)).withButtonSecondaryText(map.get(BUTTON_SECONDARY_TEXT)).withTermsOfUse(map.get(TERMS_OF_USE)).withTemplateImageLink(map.get(TEMPLATE_IMAGE_LINK)).withTemplateImageLinkLandscape(map.get(TEMPLATE_IMAGE_LINK_LANDSCAPE)).withBlockRef(message.messageRef).withTemplate(message.templateId).build();
    }

    public static class Builder {
        private String blockRef;
        private String buttonPrimaryColor;
        private String buttonPrimaryLink;
        private String buttonPrimaryText;
        private String buttonSecondaryText;
        private String isPrimaryBtnDeeplinkAnExternalLink;
        private String template;
        private String templateImageLink;
        private String templateImageLinkLandscape;
        private String termsOfUse;

        public Builder withButtonPrimaryText(final String buttonPrimaryText) {
            this.buttonPrimaryText = buttonPrimaryText;
            return this;
        }

        public Builder withIsPrimaryBtnDeeplinkAnExternalLink(final String isPrimaryBtnDeeplinkAnExternalLink) {
            this.isPrimaryBtnDeeplinkAnExternalLink = isPrimaryBtnDeeplinkAnExternalLink;
            return this;
        }

        public Builder withButtonPrimaryLink(final String buttonPrimaryLink) {
            this.buttonPrimaryLink = buttonPrimaryLink;
            return this;
        }

        public Builder withButtonPrimaryColor(final String buttonPrimaryColor) {
            this.buttonPrimaryColor = buttonPrimaryColor;
            return this;
        }

        public Builder withButtonSecondaryText(final String buttonSecondaryText) {
            this.buttonSecondaryText = buttonSecondaryText;
            return this;
        }

        public Builder withTermsOfUse(final String termsOfUse) {
            this.termsOfUse = termsOfUse;
            return this;
        }

        public Builder withTemplateImageLink(final String templateImageLink) {
            this.templateImageLink = templateImageLink;
            return this;
        }

        public Builder withTemplateImageLinkLandscape(final String templateImageLinkLandscape) {
            this.templateImageLinkLandscape = templateImageLinkLandscape;
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

        public ThirdPartyHostedSplashTemplate build() {
            return new ThirdPartyHostedSplashTemplate(this);
        }
    }
}
