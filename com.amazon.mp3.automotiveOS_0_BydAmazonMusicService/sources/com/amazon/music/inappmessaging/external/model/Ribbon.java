package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class Ribbon {
    private static final String BACKGROUND_COLOR = "backgroundColor";
    private static final String BYLINE = "byline";
    private static final String COPY = "copy";
    private static final String DEFAULT_POSITION = "top";
    private static final String NULL_STR = "null";
    private static final String PRIMARY_DEEPLINK = "primaryDeeplink";
    private static final String TEMPLATE = "template";
    private static final String TEXT_COLOR = "textColor";
    public final String backgroundColor;
    public final String blockRef;
    public final String byline;
    public final String copy;
    public final String position;
    public final String primaryDeeplink;
    public final String template;
    public final String textColor;

    public Ribbon(final Builder builder) {
        this.primaryDeeplink = builder.primaryDeeplink;
        this.copy = builder.copy;
        this.byline = builder.byline;
        this.backgroundColor = builder.backgroundColor;
        this.textColor = builder.textColor;
        this.position = builder.position;
        this.blockRef = builder.blockRef;
        this.template = builder.template;
    }

    public static Ribbon fromDynamicMessage(final DynamicMessage message) {
        Map<String, String> map = message.templateAttributes;
        String str = map.get(BYLINE);
        if (str == null || str.equals(NULL_STR)) {
            str = null;
        }
        return new Builder().withPrimaryDeeplink(map.get(PRIMARY_DEEPLINK)).withBackgroundColor(map.get(BACKGROUND_COLOR)).withByline(str).withCopy(map.get(COPY)).withTextColor(map.get(TEXT_COLOR)).withPosition(DEFAULT_POSITION).withBlockRef(message.messageRef).withTemplate(message.templateId).build();
    }

    public static class Builder {
        private String backgroundColor;
        private String blockRef;
        private String byline;
        private String copy;
        private String position;
        private String primaryDeeplink;
        private String template;
        private String textColor;

        public Builder withPrimaryDeeplink(final String primaryDeeplink) {
            this.primaryDeeplink = primaryDeeplink;
            return this;
        }

        public Builder withCopy(final String copy) {
            this.copy = copy;
            return this;
        }

        public Builder withByline(final String byline) {
            this.byline = byline;
            return this;
        }

        public Builder withTextColor(final String textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder withBackgroundColor(final String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder withPosition(final String position) {
            this.position = position;
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

        public Ribbon build() {
            return new Ribbon(this);
        }
    }
}
