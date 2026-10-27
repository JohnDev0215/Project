package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class EvergreenSplashTemplate implements Splash {
    private static final String BACKGROUND_COLOR = "backgroundColor";
    private static final String BACKGROUND_IMG_LANDSCAPE_LARGE_RETINA_SRC = "backgroundImageLandscapeLargeRetinaSource";
    private static final String BACKGROUND_IMG_LANDSCAPE_LARGE_SRC = "backgroundImageLandscapeLargeSource";
    private static final String BACKGROUND_IMG_LANDSCAPE_SMALL_RETINA_SRC = "backgroundImageLandscapeSmallRetinaSource";
    private static final String BACKGROUND_IMG_LANDSCAPE_SMALL_SRC = "backgroundImageLandscapeSmallSource";
    private static final String BACKGROUND_IMG_PORTRAIT_LARGE_RETINA_SRC = "backgroundImagePortraitLargeRetinaSource";
    private static final String BACKGROUND_IMG_PORTRAIT_LARGE_SRC = "backgroundImagePortraitLargeSource";
    private static final String BACKGROUND_IMG_PORTRAIT_SMALL_RETINA_SRC = "backgroundImagePortraitSmallRetinaSource";
    private static final String BACKGROUND_IMG_PORTRAIT_SMALL_SRC = "backgroundImagePortraitSmallSource";
    private static final String BUTTON_PRIMARY_COLOR = "buttonPrimaryColor";
    private static final String BUTTON_PRIMARY_LINK = "buttonPrimaryLink";
    private static final String BUTTON_PRIMARY_TEXT = "buttonPrimaryText";
    private static final String BUTTON_SECONDARY_TEXT = "buttonSecondaryText";
    private static final String BYLINE = "byline";
    private static final String CAPTION = "caption";
    private static final String COPY = "copy";
    private static final String HEADLINE = "headline";
    private static final String HEADLINE_CSS_CLASSES = "headlineCssClasses";
    private static final String IS_PRIMARY_BTN_LINK_EXTERNAL = "isPrimaryBtnDeeplinkAnExternalLink";
    private static final String LOGO_RETINA_URL = "logoRetinaUrl";
    private static final String LOGO_URL = "logoUrl";
    private static final String PROMO_END_DATE = "promoEndDate";
    private static final String TERMS_OF_USE = "termsOfUse";
    public final String backgroundColor;
    public final String backgroundImageLandscapeLargeRetinaSource;
    public final String backgroundImageLandscapeLargeSource;
    public final String backgroundImageLandscapeSmallRetinaSource;
    public final String backgroundImageLandscapeSmallSource;
    public final String backgroundImagePortraitLargeRetinaSource;
    public final String backgroundImagePortraitLargeSource;
    public final String backgroundImagePortraitSmallRetinaSource;
    public final String backgroundImagePortraitSmallSource;
    public final String blockRef;
    public final String buttonPrimaryColor;
    public final String buttonPrimaryLink;
    public final String buttonPrimaryText;
    public final String buttonSecondaryText;
    public final String byline;
    public final String caption;
    public final String copy;
    public final String headline;
    public final String headlineCssClasses;
    public final String isPrimaryBtnDeeplinkAnExternalLink;
    public final String logoRetinaUrl;
    public final String logoUrl;
    public final String promoEndDate;
    public final String template;
    public final String termsOfUse;

    public EvergreenSplashTemplate(final Builder builder) {
        this.headline = builder.headline;
        this.headlineCssClasses = builder.headlineCssClasses;
        this.byline = builder.byline;
        this.copy = builder.copy;
        this.promoEndDate = builder.promoEndDate;
        this.buttonPrimaryText = builder.buttonPrimaryText;
        this.isPrimaryBtnDeeplinkAnExternalLink = builder.isPrimaryBtnDeeplinkAnExternalLink;
        this.buttonPrimaryLink = builder.buttonPrimaryLink;
        this.caption = builder.caption;
        this.buttonPrimaryColor = builder.buttonPrimaryColor;
        this.buttonSecondaryText = builder.buttonSecondaryText;
        this.termsOfUse = builder.termsOfUse;
        this.backgroundColor = builder.backgroundColor;
        this.backgroundImagePortraitSmallSource = builder.backgroundImagePortraitSmallSource;
        this.backgroundImageLandscapeSmallSource = builder.backgroundImageLandscapeSmallSource;
        this.backgroundImagePortraitLargeSource = builder.backgroundImagePortraitLargeSource;
        this.backgroundImageLandscapeLargeSource = builder.backgroundImageLandscapeLargeSource;
        this.backgroundImagePortraitSmallRetinaSource = builder.backgroundImagePortraitSmallRetinaSource;
        this.backgroundImageLandscapeSmallRetinaSource = builder.backgroundImageLandscapeSmallRetinaSource;
        this.backgroundImagePortraitLargeRetinaSource = builder.backgroundImagePortraitLargeRetinaSource;
        this.backgroundImageLandscapeLargeRetinaSource = builder.backgroundImageLandscapeLargeRetinaSource;
        this.logoUrl = builder.logoUrl;
        this.logoRetinaUrl = builder.logoRetinaUrl;
        this.blockRef = builder.blockRef;
        this.template = builder.template;
    }

    public static EvergreenSplashTemplate fromDynamicMessage(final DynamicMessage message) throws IllegalArgumentException {
        if (!validateRequiredAttributes(message)) {
            throw new IllegalArgumentException("Failed to build EvergreenSplashTemplate due to missing required fields");
        }
        Map<String, String> map = message.templateAttributes;
        return new Builder().withHeadline(map.get(HEADLINE)).withHeadlineCssClasses(map.get(HEADLINE_CSS_CLASSES)).withByline(map.get(BYLINE)).withCopy(map.get(COPY)).withPromoEndDate(map.get(PROMO_END_DATE)).withButtonPrimaryText(map.get(BUTTON_PRIMARY_TEXT)).withIsPrimaryBtnDeeplinkAnExternalLink(map.get(IS_PRIMARY_BTN_LINK_EXTERNAL)).withButtonPrimaryLink(map.get(BUTTON_PRIMARY_LINK)).withCaption(map.get("caption")).withButtonPrimaryColor(map.get(BUTTON_PRIMARY_COLOR)).withButtonSecondaryText(map.get(BUTTON_SECONDARY_TEXT)).withTermsOfUse(map.get(TERMS_OF_USE)).withBackgroundColor(map.get(BACKGROUND_COLOR)).withBackgroundImagePortraitSmallSource(map.get(BACKGROUND_IMG_PORTRAIT_SMALL_SRC)).withBackgroundImageLandscapeSmallSource(map.get(BACKGROUND_IMG_LANDSCAPE_SMALL_SRC)).withBackgroundImagePortraitLargeSource(map.get(BACKGROUND_IMG_PORTRAIT_LARGE_SRC)).withBackgroundImageLandscapeLargeSource(map.get(BACKGROUND_IMG_LANDSCAPE_LARGE_SRC)).withBackgroundImagePortraitSmallRetinaSource(map.get(BACKGROUND_IMG_PORTRAIT_SMALL_RETINA_SRC)).withBackgroundImageLandscapeSmallRetinaSource(map.get(BACKGROUND_IMG_LANDSCAPE_SMALL_RETINA_SRC)).withBackgroundImagePortraitLargeRetinaSource(map.get(BACKGROUND_IMG_PORTRAIT_LARGE_RETINA_SRC)).withBackgroundImageLandscapeLargeRetinaSource(map.get(BACKGROUND_IMG_LANDSCAPE_LARGE_RETINA_SRC)).withLogoUrl(map.get(LOGO_URL)).withLogoRetinaUrl(map.get(LOGO_RETINA_URL)).withBlockRef(message.messageRef).withTemplate(message.templateId).build();
    }

    private static boolean validateRequiredAttributes(final DynamicMessage dynamicMessage) {
        Map<String, String> map = dynamicMessage.templateAttributes;
        return (map == null || map.get(HEADLINE) == null || dynamicMessage.templateAttributes.get(BUTTON_PRIMARY_TEXT) == null || dynamicMessage.templateAttributes.get(BUTTON_PRIMARY_LINK) == null) ? false : true;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImagePortraitSmallSource() {
        return this.backgroundImagePortraitSmallSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImageLandscapeSmallSource() {
        return this.backgroundImageLandscapeSmallSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImagePortraitLargeSource() {
        return this.backgroundImagePortraitLargeSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImageLandscapeLargeSource() {
        return this.backgroundImageLandscapeLargeSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImagePortraitSmallRetinaSource() {
        return this.backgroundImagePortraitSmallRetinaSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImageLandscapeSmallRetinaSource() {
        return this.backgroundImageLandscapeSmallRetinaSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImagePortraitLargeRetinaSource() {
        return this.backgroundImagePortraitLargeRetinaSource;
    }

    @Override // com.amazon.music.inappmessaging.external.model.Splash
    public String getBackgroundImageLandscapeLargeRetinaSource() {
        return this.backgroundImageLandscapeLargeRetinaSource;
    }

    public static class Builder {
        private String backgroundColor;
        private String backgroundImageLandscapeLargeRetinaSource;
        private String backgroundImageLandscapeLargeSource;
        private String backgroundImageLandscapeSmallRetinaSource;
        private String backgroundImageLandscapeSmallSource;
        private String backgroundImagePortraitLargeRetinaSource;
        private String backgroundImagePortraitLargeSource;
        private String backgroundImagePortraitSmallRetinaSource;
        private String backgroundImagePortraitSmallSource;
        private String blockRef;
        private String buttonPrimaryColor;
        private String buttonPrimaryLink;
        private String buttonPrimaryText;
        private String buttonSecondaryText;
        private String byline;
        private String caption;
        private String copy;
        private String headline;
        private String headlineCssClasses;
        private String isPrimaryBtnDeeplinkAnExternalLink;
        private String logoRetinaUrl;
        private String logoUrl;
        private String promoEndDate;
        private String template;
        private String termsOfUse;

        public Builder withHeadline(final String headline) {
            this.headline = headline;
            return this;
        }

        public Builder withHeadlineCssClasses(final String headlineCssClasses) {
            this.headlineCssClasses = headlineCssClasses;
            return this;
        }

        public Builder withByline(final String byline) {
            this.byline = byline;
            return this;
        }

        public Builder withCopy(final String copy) {
            this.copy = copy;
            return this;
        }

        public Builder withPromoEndDate(final String promoEndDate) {
            this.promoEndDate = promoEndDate;
            return this;
        }

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

        public Builder withCaption(final String caption) {
            this.caption = caption;
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

        public Builder withBackgroundColor(final String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder withBackgroundImagePortraitSmallSource(final String backgroundImagePortraitSmallSource) {
            this.backgroundImagePortraitSmallSource = backgroundImagePortraitSmallSource;
            return this;
        }

        public Builder withBackgroundImageLandscapeSmallSource(final String backgroundImageLandscapeSmallSource) {
            this.backgroundImageLandscapeSmallSource = backgroundImageLandscapeSmallSource;
            return this;
        }

        public Builder withBackgroundImagePortraitLargeSource(final String backgroundImagePortraitLargeSource) {
            this.backgroundImagePortraitLargeSource = backgroundImagePortraitLargeSource;
            return this;
        }

        public Builder withBackgroundImageLandscapeLargeSource(final String backgroundImageLandscapeLargeSource) {
            this.backgroundImageLandscapeLargeSource = backgroundImageLandscapeLargeSource;
            return this;
        }

        public Builder withBackgroundImagePortraitSmallRetinaSource(final String backgroundImagePortraitSmallRetinaSource) {
            this.backgroundImagePortraitSmallRetinaSource = backgroundImagePortraitSmallRetinaSource;
            return this;
        }

        public Builder withBackgroundImageLandscapeSmallRetinaSource(final String backgroundImageLandscapeSmallRetinaSource) {
            this.backgroundImageLandscapeSmallRetinaSource = backgroundImageLandscapeSmallRetinaSource;
            return this;
        }

        public Builder withBackgroundImagePortraitLargeRetinaSource(final String backgroundImagePortraitLargeRetinaSource) {
            this.backgroundImagePortraitLargeRetinaSource = backgroundImagePortraitLargeRetinaSource;
            return this;
        }

        public Builder withBackgroundImageLandscapeLargeRetinaSource(final String backgroundImageLandscapeLargeRetinaSource) {
            this.backgroundImageLandscapeLargeRetinaSource = backgroundImageLandscapeLargeRetinaSource;
            return this;
        }

        public Builder withLogoUrl(final String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public Builder withLogoRetinaUrl(final String logoRetinaUrl) {
            this.logoRetinaUrl = logoRetinaUrl;
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

        public EvergreenSplashTemplate build() {
            return new EvergreenSplashTemplate(this);
        }
    }
}
