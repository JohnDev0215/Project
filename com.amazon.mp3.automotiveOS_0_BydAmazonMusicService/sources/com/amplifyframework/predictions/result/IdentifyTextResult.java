package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.IdentifiedText;
import com.amplifyframework.util.Immutable;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class IdentifyTextResult implements IdentifyResult {
    private final String fullText;
    private final List<IdentifiedText> lines;
    private final List<String> rawLineText;
    private final List<IdentifiedText> words;

    private IdentifyTextResult(Builder builder) {
        this.fullText = builder.getFullText();
        this.rawLineText = builder.getRawLineText();
        this.words = builder.getWords();
        this.lines = builder.getLines();
    }

    @NonNull
    public String getFullText() {
        return this.fullText;
    }

    @NonNull
    public List<String> getRawLineText() {
        return Immutable.of(this.rawLineText);
    }

    @NonNull
    public List<IdentifiedText> getWords() {
        return Immutable.of(this.words);
    }

    @NonNull
    public List<IdentifiedText> getLines() {
        return Immutable.of(this.lines);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String fullText;
        private List<IdentifiedText> lines;
        private List<String> rawLineText;
        private List<IdentifiedText> words;

        @NonNull
        public Builder fullText(@NonNull String str) {
            Objects.requireNonNull(str);
            this.fullText = str;
            return this;
        }

        @NonNull
        public Builder rawLineText(@NonNull List<String> list) {
            Objects.requireNonNull(list);
            this.rawLineText = list;
            return this;
        }

        @NonNull
        public Builder words(@NonNull List<IdentifiedText> list) {
            Objects.requireNonNull(list);
            this.words = list;
            return this;
        }

        @NonNull
        public Builder lines(@NonNull List<IdentifiedText> list) {
            Objects.requireNonNull(list);
            this.lines = list;
            return this;
        }

        @NonNull
        public IdentifyTextResult build() {
            return new IdentifyTextResult(this);
        }

        @NonNull
        String getFullText() {
            String str = this.fullText;
            Objects.requireNonNull(str);
            return str;
        }

        @NonNull
        List<String> getRawLineText() {
            List<String> list = this.rawLineText;
            Objects.requireNonNull(list);
            return list;
        }

        @NonNull
        List<IdentifiedText> getWords() {
            List<IdentifiedText> list = this.words;
            Objects.requireNonNull(list);
            return list;
        }

        @NonNull
        List<IdentifiedText> getLines() {
            List<IdentifiedText> list = this.lines;
            Objects.requireNonNull(list);
            return list;
        }
    }
}
