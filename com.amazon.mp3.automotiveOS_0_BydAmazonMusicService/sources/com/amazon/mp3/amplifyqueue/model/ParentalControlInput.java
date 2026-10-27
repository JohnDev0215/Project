package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ParentalControlInput {
    private final Boolean hasExplicitLanguage;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public Boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ParentalControlInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getHasExplicitLanguage(), ((ParentalControlInput) obj).getHasExplicitLanguage());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHasExplicitLanguage());
        return sb.toString().hashCode();
    }
}
