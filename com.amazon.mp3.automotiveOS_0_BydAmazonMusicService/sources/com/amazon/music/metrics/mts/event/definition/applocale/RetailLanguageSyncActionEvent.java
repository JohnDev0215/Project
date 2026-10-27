package com.amazon.music.metrics.mts.event.definition.applocale;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class RetailLanguageSyncActionEvent extends MTSEvent {
    private RetailLanguageSyncActionEvent(Builder builder) {
        super("retailLanguageSyncAction", 1L);
        addAttribute(Splash.PARAMS_LOCALE, builder.locale);
        addAttribute("previousAppLocale", builder.previousAppLocale);
        addAttribute("appLocale", builder.appLocale);
        addAttribute("retailLocale", builder.retailLocale);
        addAttribute("preferenceAction", builder.preferenceAction);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String appLocale;
        private String locale;
        private String preferenceAction;
        private String previousAppLocale;
        private String retailLocale;

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

        public Builder withRetailLocale(String str) {
            this.retailLocale = str;
            return this;
        }

        public Builder withPreferenceAction(String str) {
            this.preferenceAction = str;
            return this;
        }

        public RetailLanguageSyncActionEvent build() {
            return new RetailLanguageSyncActionEvent(this);
        }
    }
}
