package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ParentalControls {
    private final Boolean hasExplicitLanguage;

    public interface BuildStep {
        ParentalControls build();
    }

    public interface HasExplicitLanguageStep {
        BuildStep hasExplicitLanguage(Boolean bool);
    }

    public Boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    private ParentalControls(Boolean bool) {
        this.hasExplicitLanguage = bool;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ParentalControls.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getHasExplicitLanguage(), ((ParentalControls) obj).getHasExplicitLanguage());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHasExplicitLanguage());
        return sb.toString().hashCode();
    }

    public static HasExplicitLanguageStep builder() {
        return new Builder();
    }

    public static class Builder implements HasExplicitLanguageStep, BuildStep {
        private Boolean hasExplicitLanguage;

        @Override // com.amazon.mp3.amplifyqueue.model.ParentalControls.BuildStep
        public ParentalControls build() {
            return new ParentalControls(this.hasExplicitLanguage);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ParentalControls.HasExplicitLanguageStep
        public BuildStep hasExplicitLanguage(Boolean bool) {
            Objects.requireNonNull(bool);
            this.hasExplicitLanguage = bool;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.ParentalControls.Builder, com.amazon.mp3.amplifyqueue.model.ParentalControls.HasExplicitLanguageStep
        public CopyOfBuilder hasExplicitLanguage(Boolean bool) {
            return (CopyOfBuilder) super.hasExplicitLanguage(bool);
        }
    }
}
