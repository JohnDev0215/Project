package com.amazon.music.metrics.mts.event.definition.applocale;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class LocaleChangedEvent extends MTSEvent {
    private LocaleChangedEvent(Builder builder) {
        super("localeChanged", 1L);
        addAttribute(Splash.PARAMS_LOCALE, builder.locale);
        addAttribute("previousAppLocale", builder.previousAppLocale);
        addAttribute("appLocale", builder.appLocale);
        addAttribute("pageType", builder.pageType);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String appLocale;
        private String locale;
        private String pageType;
        private String previousAppLocale;

        public Builder withLocale(String str) {
            this.locale = str;
            return this;
        }

        public Builder withPreviousAppLocale(String str) {
            this.previousAppLocale = str;
            return this;
        }

        public Builder withAppLocale(String str) {
            this.appLocale = str;
            return this;
        }

        public Builder withPageType(String str) {
            this.pageType = str;
            return this;
        }

        public LocaleChangedEvent build() {
            return new LocaleChangedEvent(this);
        }
    }
}
