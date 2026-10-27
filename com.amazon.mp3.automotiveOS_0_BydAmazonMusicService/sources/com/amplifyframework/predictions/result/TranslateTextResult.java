package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.LanguageType;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class TranslateTextResult {
    private final LanguageType targetLanguage;
    private final String translatedText;

    private TranslateTextResult(Builder builder) {
        this.translatedText = builder.getTranslatedText();
        this.targetLanguage = builder.getTargetLanguage();
    }

    @NonNull
    public String getTranslatedText() {
        return this.translatedText;
    }

    @NonNull
    public LanguageType getTargetLanguage() {
        return this.targetLanguage;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private LanguageType targetLanguage;
        private String translatedText;

        @NonNull
        public Builder translatedText(@NonNull String str) {
            Objects.requireNonNull(str);
            this.translatedText = str;
            return this;
        }

        @NonNull
        public Builder targetLanguage(@NonNull LanguageType languageType) {
            Objects.requireNonNull(languageType);
            this.targetLanguage = languageType;
            return this;
        }

        @NonNull
        public TranslateTextResult build() {
            return new TranslateTextResult(this);
        }

        @NonNull
        String getTranslatedText() {
            String str = this.translatedText;
            Objects.requireNonNull(str);
            return str;
        }

        @NonNull
        LanguageType getTargetLanguage() {
            LanguageType languageType = this.targetLanguage;
            Objects.requireNonNull(languageType);
            return languageType;
        }
    }
}
