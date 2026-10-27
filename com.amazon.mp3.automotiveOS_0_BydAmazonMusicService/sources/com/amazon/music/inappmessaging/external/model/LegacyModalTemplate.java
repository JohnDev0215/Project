package com.amazon.music.inappmessaging.external.model;

import androidx.annotation.NonNull;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LegacyModalTemplate implements Serializable {
    private static final String BUTTON_FONT_SIZE = "buttonFontSize";
    private static final String BYLINE = "byline";
    private static final String BYLINE_FONT_SIZE = "bylineFontSize";
    private static final String COMFORT_FONT_SIZE = "comfortTextFontSize";
    private static final String COMFORT_TEXT = "comfortText";
    private static final String HEADLINE = "headline";
    private static final String HEADLINE_FONT_SIZE = "headlineFontSize";
    private static final String MODAL_BACKGROUND_COLOR = "backgroundColor";
    private static final String PRIMARY_BUTTON_BACKGROUND_COLOR = "primaryButtonBackgroundColor";
    private static final String PRIMARY_BUTTON_LINK = "primaryButtonLink";
    private static final String PRIMARY_BUTTON_TEXT = "primaryButtonText";
    private static final String PRIMARY_BUTTON_TEXT_COLOR = "primaryButtonTextColor";
    private static final String SECONDARY_BUTTON_BACKGROUND_COLOR = "secondaryButtonBackgroundColor";
    private static final String SECONDARY_BUTTON_LINK = "secondaryButtonLink";
    private static final String SECONDARY_BUTTON_TEXT = "secondaryButton";
    private static final String SECONDARY_BUTTON_TEXT_COLOR = "secondaryButtonTextColor";
    private static final String TRAILING_TEXT = "trailingText";
    private static final String TRAILING_TEXT_FONT_SIZE = "trailingTextFontSize";
    public final String backgroundColor;
    public final String buttonFontSize;
    public final String byline;
    public final String bylineFontSize;
    public final String comfortText;
    public final String comfortTextFontSize;
    public final String headline;
    public final String headlineFontSize;
    public final String messageRef;
    public final String primaryButtonBackgroundColor;
    public final URI primaryButtonLink;
    public final String primaryButtonText;
    public final String primaryButtonTextColor;
    public final String secondaryButtonBackgroundColor;
    public final URI secondaryButtonLink;
    public final String secondaryButtonText;
    public final String secondaryButtonTextColor;
    public final String templateId;
    public final String trailingText;
    public final String trailingTextFontSize;

    public LegacyModalTemplate(final Builder builder) {
        this.headline = builder.headline;
        this.byline = builder.byline;
        this.primaryButtonText = builder.primaryButtonText;
        this.primaryButtonLink = builder.primaryButtonLink;
        this.primaryButtonTextColor = builder.primaryButtonTextColor;
        this.primaryButtonBackgroundColor = builder.primaryButtonBackgroundColor;
        this.secondaryButtonText = builder.secondaryButtonText;
        this.secondaryButtonLink = builder.secondaryButtonLink;
        this.secondaryButtonTextColor = builder.secondaryButtonTextColor;
        this.secondaryButtonBackgroundColor = builder.secondaryButtonBackgroundColor;
        this.comfortText = builder.comfortText;
        this.trailingText = builder.trailingText;
        this.backgroundColor = builder.backgroundColor;
        this.headlineFontSize = builder.headlineFontSize;
        this.bylineFontSize = builder.bylineFontSize;
        this.comfortTextFontSize = builder.comfortTextFontSize;
        this.trailingTextFontSize = builder.trailingTextFontSize;
        this.buttonFontSize = builder.buttonFontSize;
        this.messageRef = builder.messageRef;
        this.templateId = builder.templateId;
    }

    public static LegacyModalTemplate fromDynamicMessage(@NonNull final DynamicMessage dynamicMessage) throws IllegalArgumentException {
        Map<String, String> map = dynamicMessage.templateAttributes;
        if (!validateRequiredAttributes(map)) {
            throw new IllegalArgumentException("Failed to build LegacyModalTemplate due to missing required fields");
        }
        URI uriCreate = URI.create(map.get(PRIMARY_BUTTON_LINK));
        String str = map.get(SECONDARY_BUTTON_LINK);
        return new Builder().withHeadline(map.get(HEADLINE)).withByline(map.get(BYLINE)).withPrimaryButtonText(map.get(PRIMARY_BUTTON_TEXT)).withPrimaryButtonLink(uriCreate).withPrimaryButtonTextColor(map.get(PRIMARY_BUTTON_TEXT_COLOR)).withPrimaryButtonBackgroundColor(map.get(PRIMARY_BUTTON_BACKGROUND_COLOR)).withSecondaryButtonText(map.get(SECONDARY_BUTTON_TEXT)).withSecondaryButtonLink(str != null ? URI.create(str) : null).withSecondaryButtonTextColor(map.get(SECONDARY_BUTTON_TEXT_COLOR)).withSecondaryButtonBackgroundColor(map.get(SECONDARY_BUTTON_BACKGROUND_COLOR)).withComfortText(map.get(COMFORT_TEXT)).withTrailingText(map.get(TRAILING_TEXT)).withBackgroundColor(map.get(MODAL_BACKGROUND_COLOR)).withHeadlineFontSize(map.get(HEADLINE_FONT_SIZE)).withBylineFontSize(map.get(BYLINE_FONT_SIZE)).withComfortTextFontSize(map.get(COMFORT_FONT_SIZE)).withTrailingTextFontSize(map.get(TRAILING_TEXT_FONT_SIZE)).withButtonFontSize(map.get(BUTTON_FONT_SIZE)).withMessageRef(dynamicMessage.messageRef).withTemplateId(dynamicMessage.templateId).build();
    }

    private static boolean validateRequiredAttributes(final Map<String, String> templateAttributes) {
        if (templateAttributes == null) {
            return false;
        }
        return ((templateAttributes.get(SECONDARY_BUTTON_TEXT) != null && templateAttributes.get(SECONDARY_BUTTON_LINK) == null) || templateAttributes.get(HEADLINE) == null || templateAttributes.get(BYLINE) == null || templateAttributes.get(PRIMARY_BUTTON_TEXT) == null || templateAttributes.get(PRIMARY_BUTTON_LINK) == null) ? false : true;
    }

    public static class Builder {
        private String backgroundColor;
        private String buttonFontSize;
        private String byline;
        private String bylineFontSize;
        private String comfortText;
        private String comfortTextFontSize;
        private String headline;
        private String headlineFontSize;
        private String messageRef;
        private String primaryButtonBackgroundColor;
        private URI primaryButtonLink;
        private String primaryButtonText;
        private String primaryButtonTextColor;
        private String secondaryButtonBackgroundColor;
        private URI secondaryButtonLink;
        private String secondaryButtonText;
        private String secondaryButtonTextColor;
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

        public Builder withPrimaryButtonTextColor(final String primaryButtonTextColor) {
            this.primaryButtonTextColor = primaryButtonTextColor;
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

        public Builder withSecondaryButtonTextColor(final String secondaryButtonTextColor) {
            this.secondaryButtonTextColor = secondaryButtonTextColor;
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

        public Builder withButtonFontSize(final String buttonFontSize) {
            this.buttonFontSize = buttonFontSize;
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

        public LegacyModalTemplate build() {
            return new LegacyModalTemplate(this);
        }
    }
}
