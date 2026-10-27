package com.amazon.music.inappmessaging.external.model;

import androidx.annotation.NonNull;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageTemplateHelper;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class FullScreenBauhausModalTemplate implements Serializable {
    private static final String BYLINE = "byline";
    private static final String BYLINE_FONT_SIZE = "bylineFontSize";
    private static final String COMFORT_FONT_SIZE = "comfortTextFontSize";
    private static final String COMFORT_TEXT = "comfortText";
    private static final String HEADLINE = "headline";
    private static final String HEADLINE_FONT_SIZE = "headlineFontSize";
    private static final String LOGO_IMAGE_URL = "logoImageUrl";
    private static final String MODAL_BACKGROUND_COLOR = "modalBackgroundColor";
    private static final String PRIMARY_BUTTON_BACKGROUND_COLOR = "primaryButtonBackgroundColor";
    private static final String PRIMARY_BUTTON_FONT_SIZE = "primaryButtonFontSize";
    private static final String PRIMARY_BUTTON_LINK = "primaryButtonLink";
    private static final String PRIMARY_BUTTON_TEXT = "primaryButtonText";
    private static final String SECONDARY_BUTTON_BACKGROUND_COLOR = "secondaryButtonBackgroundColor";
    private static final String SECONDARY_BUTTON_FONT_SIZE = "secondaryButtonFontSize";
    private static final String SECONDARY_BUTTON_LINK = "secondaryButtonLink";
    private static final String SECONDARY_BUTTON_TEXT = "secondaryButtonText";
    private static final String TRAILING_TEXT = "trailingText";
    private static final String TRAILING_TEXT_FONT_SIZE = "trailingTextFontSize";
    public final String backgroundColor;
    public final String byline;
    public final String bylineFontSize;
    public final String comfortText;
    public final String comfortTextFontSize;
    public final String headline;
    public final String headlineFontSize;
    public final String logoImageUrl;
    public final String messageRef;
    public final String primaryButtonBackgroundColor;
    public final String primaryButtonFontSize;
    public final URI primaryButtonLink;
    public final String primaryButtonText;
    public final String secondaryButtonBackgroundColor;
    public final String secondaryButtonFontSize;
    public final URI secondaryButtonLink;
    public final String secondaryButtonText;
    public final boolean shouldPrimaryButtonDismissDialog;
    public final boolean shouldSecondaryButtonDismissDialog;
    public final String templateId;
    public final String trailingText;
    public final String trailingTextFontSize;

    public FullScreenBauhausModalTemplate(final Builder builder) {
        this.headline = builder.headline;
        this.byline = builder.byline;
        this.primaryButtonText = builder.primaryButtonText;
        this.primaryButtonLink = builder.primaryButtonLink;
        this.primaryButtonBackgroundColor = builder.primaryButtonBackgroundColor;
        this.secondaryButtonText = builder.secondaryButtonText;
        this.secondaryButtonLink = builder.secondaryButtonLink;
        this.secondaryButtonBackgroundColor = builder.secondaryButtonBackgroundColor;
        this.comfortText = builder.comfortText;
        this.trailingText = builder.trailingText;
        this.logoImageUrl = builder.logoImageUrl;
        this.backgroundColor = builder.backgroundColor;
        this.headlineFontSize = builder.headlineFontSize;
        this.bylineFontSize = builder.bylineFontSize;
        this.comfortTextFontSize = builder.comfortTextFontSize;
        this.trailingTextFontSize = builder.trailingTextFontSize;
        this.primaryButtonFontSize = builder.primaryButtonFontSize;
        this.secondaryButtonFontSize = builder.secondaryButtonFontSize;
        this.shouldPrimaryButtonDismissDialog = builder.shouldPrimaryButtonDismissDialog;
        this.shouldSecondaryButtonDismissDialog = builder.shouldSecondaryButtonDismissDialog;
        this.messageRef = builder.messageRef;
        this.templateId = builder.templateId;
    }

    public static FullScreenBauhausModalTemplate fromDynamicMessage(@NonNull final DynamicMessage dynamicMessage) throws IllegalArgumentException {
        if (!validateRequiredAttributes(dynamicMessage)) {
            throw new IllegalArgumentException("Failed to build FullScreenBauhausModalTemplate due to missing required fields");
        }
        Map<String, String> map = dynamicMessage.templateAttributes;
        String str = map.get(PRIMARY_BUTTON_LINK);
        Builder builderWithTemplateId = new Builder().withHeadline(map.get(HEADLINE)).withByline(map.get(BYLINE)).withPrimaryButtonText(map.get(PRIMARY_BUTTON_TEXT)).withPrimaryButtonLink(URI.create(str)).withPrimaryButtonBackgroundColor(map.get(PRIMARY_BUTTON_BACKGROUND_COLOR)).withSecondaryButtonText(map.get(SECONDARY_BUTTON_TEXT)).withSecondaryButtonBackgroundColor(map.get(SECONDARY_BUTTON_BACKGROUND_COLOR)).withComfortText(map.get(COMFORT_TEXT)).withTrailingText(map.get(TRAILING_TEXT)).withLogoImageUrl(map.get(LOGO_IMAGE_URL)).withBackgroundColor(map.get(MODAL_BACKGROUND_COLOR)).withHeadlineFontSize(map.get(HEADLINE_FONT_SIZE)).withBylineFontSize(map.get(BYLINE_FONT_SIZE)).withComfortTextFontSize(map.get(COMFORT_FONT_SIZE)).withTrailingTextFontSize(map.get(TRAILING_TEXT_FONT_SIZE)).withPrimaryButtonFontSize(map.get(PRIMARY_BUTTON_FONT_SIZE)).withSecondaryButtonFontSize(map.get(SECONDARY_BUTTON_FONT_SIZE)).shouldPrimaryButtonDismissDialog(DynamicMessageTemplateHelper.isDismissButton(str)).withMessageRef(dynamicMessage.messageRef).withTemplateId(dynamicMessage.templateId);
        String str2 = map.get(SECONDARY_BUTTON_LINK);
        if (str2 != null) {
            builderWithTemplateId = builderWithTemplateId.withSecondaryButtonLink(URI.create(str2)).shouldSecondaryButtonDismissDialog(DynamicMessageTemplateHelper.isDismissButton(str2));
        }
        return builderWithTemplateId.build();
    }

    private static boolean validateRequiredAttributes(final DynamicMessage dynamicMessage) {
        Map<String, String> map = dynamicMessage.templateAttributes;
        return (map == null || map.get(HEADLINE) == null || dynamicMessage.templateAttributes.get(BYLINE) == null || dynamicMessage.templateAttributes.get(PRIMARY_BUTTON_TEXT) == null || dynamicMessage.templateAttributes.get(PRIMARY_BUTTON_LINK) == null) ? false : true;
    }

    public static class Builder {
        private String backgroundColor;
        private String byline;
        private String bylineFontSize;
        private String comfortText;
        private String comfortTextFontSize;
        private String headline;
        private String headlineFontSize;
        private String logoImageUrl;
        private String messageRef;
        private String primaryButtonBackgroundColor;
        private String primaryButtonFontSize;
        private URI primaryButtonLink;
        private String primaryButtonText;
        private String secondaryButtonBackgroundColor;
        private String secondaryButtonFontSize;
        private URI secondaryButtonLink;
        private String secondaryButtonText;
        private boolean shouldPrimaryButtonDismissDialog;
        private boolean shouldSecondaryButtonDismissDialog;
        private String templateId;
        private String trailingText;
        private String trailingTextFontSize;

        public Builder withHeadline(final String headline) {
            this.headline = headline;
            return this;
        }

        public Builder withByline(final String byline) {
            this.byline = byline;
            return this;
        }

        public Builder withPrimaryButtonText(final String primaryButtonText) {
            this.primaryButtonText = primaryButtonText;
            return this;
        }

        public Builder withPrimaryButtonLink(final URI primaryButtonLink) {
            this.primaryButtonLink = primaryButtonLink;
            return this;
        }

        public Builder withPrimaryButtonBackgroundColor(final String primaryButtonBackgroundColor) {
            this.primaryButtonBackgroundColor = primaryButtonBackgroundColor;
            return this;
        }

        public Builder withSecondaryButtonText(final String secondaryButtonText) {
            this.secondaryButtonText = secondaryButtonText;
            return this;
        }

        public Builder withSecondaryButtonLink(final URI secondaryButtonLink) {
            this.secondaryButtonLink = secondaryButtonLink;
            return this;
        }

        public Builder withSecondaryButtonBackgroundColor(final String secondaryButtonBackgroundColor) {
            this.secondaryButtonBackgroundColor = secondaryButtonBackgroundColor;
            return this;
        }

        public Builder withComfortText(final String comfortText) {
            this.comfortText = comfortText;
            return this;
        }

        public Builder withTrailingText(final String trailingText) {
            this.trailingText = trailingText;
            return this;
        }

        public Builder withLogoImageUrl(final String logoImageUrl) {
            this.logoImageUrl = logoImageUrl;
            return this;
        }

        public Builder withBackgroundColor(final String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder withHeadlineFontSize(final String headlineFontSize) {
            this.headlineFontSize = headlineFontSize;
            return this;
        }

        public Builder withBylineFontSize(final String bylineFontSize) {
            this.bylineFontSize = bylineFontSize;
            return this;
        }

        public Builder withComfortTextFontSize(final String comfortTextFontSize) {
            this.comfortTextFontSize = comfortTextFontSize;
            return this;
        }

        public Builder withTrailingTextFontSize(final String trailingTextFontSize) {
            this.trailingTextFontSize = trailingTextFontSize;
            return this;
        }

        public Builder withPrimaryButtonFontSize(final String primaryButtonFontSize) {
            this.primaryButtonFontSize = primaryButtonFontSize;
            return this;
        }

        public Builder withSecondaryButtonFontSize(final String secondaryButtonFontSize) {
            this.secondaryButtonFontSize = secondaryButtonFontSize;
            return this;
        }

        public Builder shouldPrimaryButtonDismissDialog(final boolean shouldPrimaryButtonDismissDialog) {
            this.shouldPrimaryButtonDismissDialog = shouldPrimaryButtonDismissDialog;
            return this;
        }

        public Builder shouldSecondaryButtonDismissDialog(final boolean shouldSecondaryButtonDismissDialog) {
            this.shouldSecondaryButtonDismissDialog = shouldSecondaryButtonDismissDialog;
            return this;
        }

        public Builder withMessageRef(final String messageRef) {
            this.messageRef = messageRef;
            return this;
        }

        public Builder withTemplateId(final String templateId) {
            this.templateId = templateId;
            return this;
        }

        public FullScreenBauhausModalTemplate build() {
            return new FullScreenBauhausModalTemplate(this);
        }
    }
}
