package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class EngagementSplashTemplate implements Splash {
    private static final String BACKGROUND_IMG_LANDSCAPE_LARGE_RETINA_SRC = "backgroundImageLandscapeLargeRetinaSource";
    private static final String BACKGROUND_IMG_LANDSCAPE_LARGE_SRC = "backgroundImageLandscapeLargeSource";
    private static final String BACKGROUND_IMG_LANDSCAPE_SMALL_RETINA_SRC = "backgroundImageLandscapeSmallRetinaSource";
    private static final String BACKGROUND_IMG_LANDSCAPE_SMALL_SRC = "backgroundImageLandscapeSmallSource";
    private static final String BACKGROUND_IMG_PORTRAIT_LARGE_RETINA_SRC = "backgroundImagePortraitLargeRetinaSource";
    private static final String BACKGROUND_IMG_PORTRAIT_LARGE_SRC = "backgroundImagePortraitLargeSource";
    private static final String BACKGROUND_IMG_PORTRAIT_SMALL_RETINA_SRC = "backgroundImagePortraitSmallRetinaSource";
    private static final String BACKGROUND_IMG_PORTRAIT_SMALL_SRC = "backgroundImagePortraitSmallSource";
    private static final String BUTTON_PRIMARY_COLOR = "buttonPrimaryColor";
    private static final String BUTTON_PRIMARY_TEXT = "buttonPrimaryText";
    private static final String BYLINE = "byline";
    private static final String COPY = "copy";
    private static final String HAS_GRADIENT = "hasGradient";
    private static final String HEADLINE = "headline";
    private static final String SUBHEAD = "subhead";
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
    public final String buttonPrimaryText;
    public final String byline;
    public final String copy;
    public final String hasGradient;
    public final String headline;
    public final String subhead;
    public final String template;

    public EngagementSplashTemplate(final Builder builder) {
        this.headline = builder.headline;
        this.subhead = builder.subhead;
        this.byline = builder.byline;
        this.copy = builder.copy;
        this.buttonPrimaryText = builder.buttonPrimaryText;
        this.hasGradient = builder.hasGradient;
        this.buttonPrimaryColor = builder.buttonPrimaryColor;
        this.backgroundImagePortraitSmallSource = builder.backgroundImagePortraitSmallSource;
        this.backgroundImageLandscapeSmallSource = builder.backgroundImageLandscapeSmallSource;
        this.backgroundImagePortraitLargeSource = builder.backgroundImagePortraitLargeSource;
        this.backgroundImageLandscapeLargeSource = builder.backgroundImageLandscapeLargeSource;
        this.backgroundImagePortraitSmallRetinaSource = builder.backgroundImagePortraitSmallRetinaSource;
        this.backgroundImageLandscapeSmallRetinaSource = builder.backgroundImageLandscapeSmallRetinaSource;
        this.backgroundImagePortraitLargeRetinaSource = builder.backgroundImagePortraitLargeRetinaSource;
        this.backgroundImageLandscapeLargeRetinaSource = builder.backgroundImageLandscapeLargeRetinaSource;
        this.blockRef = builder.blockRef;
        this.template = builder.template;
    }

    public static EngagementSplashTemplate fromDynamicMessage(final DynamicMessage message) {
        Map<String, String> map = message.templateAttributes;
        return new Builder().withHeadline(map.get(HEADLINE)).withSubhead(map.get(SUBHEAD)).withByline(map.get(BYLINE)).withCopy(map.get(COPY)).withButtonPrimaryText(map.get(BUTTON_PRIMARY_TEXT)).withButtonPrimaryColor(map.get(BUTTON_PRIMARY_COLOR)).withHasGradient(map.get(HAS_GRADIENT)).withBackgroundImagePortraitSmallSource(map.get(BACKGROUND_IMG_PORTRAIT_SMALL_SRC)).withBackgroundImageLandscapeSmallSource(map.get(BACKGROUND_IMG_LANDSCAPE_SMALL_SRC)).withBackgroundImagePortraitLargeSource(map.get(BACKGROUND_IMG_PORTRAIT_LARGE_SRC)).withBackgroundImageLandscapeLargeSource(map.get(BACKGROUND_IMG_LANDSCAPE_LARGE_SRC)).withBackgroundImagePortraitSmallRetinaSource(map.get(BACKGROUND_IMG_PORTRAIT_SMALL_RETINA_SRC)).withBackgroundImageLandscapeSmallRetinaSource(map.get(BACKGROUND_IMG_LANDSCAPE_SMALL_RETINA_SRC)).withBackgroundImagePortraitLargeRetinaSource(map.get(BACKGROUND_IMG_PORTRAIT_LARGE_RETINA_SRC)).withBackgroundImageLandscapeLargeRetinaSource(map.get(BACKGROUND_IMG_LANDSCAPE_LARGE_RETINA_SRC)).withBlockRef(message.messageRef).withTemplate(message.templateId).build();
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
        private String buttonPrimaryText;
        private String byline;
        private String copy;
        private String hasGradient;
        private String headline;
        private String subhead;
        private String template;

        public Builder withHeadline(final String headline) {
            this.headline = headline;
            return this;
        }

        public Builder withSubhead(final String subhead) {
            this.subhead = subhead;
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

        public Builder withButtonPrimaryText(final String buttonPrimaryText) {
            this.buttonPrimaryText = buttonPrimaryText;
            return this;
        }

        public Builder withButtonPrimaryColor(final String buttonPrimaryColor) {
            this.buttonPrimaryColor = buttonPrimaryColor;
            return this;
        }

        public Builder withHasGradient(final String hasGradient) {
            this.hasGradient = hasGradient;
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

        public Builder withBlockRef(final String blockRef) {
            this.blockRef = blockRef;
            return this;
        }

        public Builder withTemplate(final String template) {
            this.template = template;
            return this;
        }

        public EngagementSplashTemplate build() {
            return new EngagementSplashTemplate(this);
        }
    }
}
