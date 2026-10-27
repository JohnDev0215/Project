package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.predictions.models.Entity;
import com.amplifyframework.predictions.models.Feature;
import com.amplifyframework.predictions.models.KeyPhrase;
import com.amplifyframework.predictions.models.Language;
import com.amplifyframework.predictions.models.Sentiment;
import com.amplifyframework.predictions.models.Syntax;
import com.amplifyframework.util.Immutable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class InterpretResult {
    private final List<Entity> entities;
    private final List<Feature<?>> features;
    private final List<KeyPhrase> keyPhrases;
    private final Language language;
    private final Sentiment sentiment;
    private final List<Syntax> syntax;

    private InterpretResult(Builder builder) {
        this.language = builder.getLanguage();
        this.sentiment = builder.getSentiment();
        this.keyPhrases = builder.getKeyPhrases();
        this.entities = builder.getEntities();
        this.syntax = builder.getSyntax();
        this.features = builder.getFeatures();
    }

    @Nullable
    public Language getLanguage() {
        return this.language;
    }

    @Nullable
    public Sentiment getSentiment() {
        return this.sentiment;
    }

    @Nullable
    public List<KeyPhrase> getKeyPhrases() {
        return Immutable.of(this.keyPhrases);
    }

    @Nullable
    public List<Entity> getEntities() {
        return Immutable.of(this.entities);
    }

    @Nullable
    public List<Syntax> getSyntax() {
        return Immutable.of(this.syntax);
    }

    @NonNull
    public List<Feature<?>> getFeatures() {
        return Immutable.of(this.features);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Entity> entities;
        private List<Feature<?>> features;
        private List<KeyPhrase> keyPhrases;
        private Language language;
        private Sentiment sentiment;
        private List<Syntax> syntax;

        private Builder() {
            this.features = new ArrayList();
        }

        @NonNull
        public Builder language(@Nullable Language language) {
            this.language = language;
            return this;
        }

        @NonNull
        public Builder sentiment(@Nullable Sentiment sentiment) {
            this.sentiment = sentiment;
            return this;
        }

        @NonNull
        public Builder keyPhrases(@Nullable List<KeyPhrase> list) {
            this.keyPhrases = list;
            return this;
        }

        @NonNull
        public Builder entities(@Nullable List<Entity> list) {
            this.entities = list;
            return this;
        }

        @NonNull
        public Builder syntax(@Nullable List<Syntax> list) {
            this.syntax = list;
            return this;
        }

        @NonNull
        public Builder features(@NonNull List<Feature<?>> list) {
            Objects.requireNonNull(list);
            this.features = list;
            return this;
        }

        @NonNull
        public Builder feature(@NonNull Feature<?> feature) {
            List<Feature<?>> list = this.features;
            Objects.requireNonNull(feature);
            list.add(feature);
            return this;
        }

        @NonNull
        public InterpretResult build() {
            return new InterpretResult(this);
        }

        @Nullable
        Language getLanguage() {
            return this.language;
        }

        @Nullable
        Sentiment getSentiment() {
            return this.sentiment;
        }

        @Nullable
        List<KeyPhrase> getKeyPhrases() {
            return this.keyPhrases;
        }

        @Nullable
        List<Entity> getEntities() {
            return this.entities;
        }

        @Nullable
        List<Syntax> getSyntax() {
            return this.syntax;
        }

        @NonNull
        List<Feature<?>> getFeatures() {
            return this.features;
        }
    }
}
