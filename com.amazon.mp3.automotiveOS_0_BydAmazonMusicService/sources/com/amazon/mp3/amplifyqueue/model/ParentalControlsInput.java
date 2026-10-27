package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ParentalControlsInput {
    private final Boolean hasExplicitLanguage;

    public interface BuildStep {
    }

    public static class Builder implements HasExplicitLanguageStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface HasExplicitLanguageStep {
    }

    public Boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ParentalControlsInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getHasExplicitLanguage(), ((ParentalControlsInput) obj).getHasExplicitLanguage());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHasExplicitLanguage());
        return sb.toString().hashCode();
    }
}
