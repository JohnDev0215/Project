package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class CustomerContext {
    private final List<String> languageOfPreference;
    private final String locale;
    private final String timezone;

    public interface BuildStep {
    }

    public static class Builder implements LocaleStep, LanguageOfPreferenceStep, TimezoneStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface LanguageOfPreferenceStep {
    }

    public interface LocaleStep {
    }

    public interface TimezoneStep {
    }

    public String getLocale() {
        return this.locale;
    }

    public List<String> getLanguageOfPreference() {
        return this.languageOfPreference;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CustomerContext.class != obj.getClass()) {
            return false;
        }
        CustomerContext customerContext = (CustomerContext) obj;
        return ObjectsCompat.equals(getLocale(), customerContext.getLocale()) && ObjectsCompat.equals(getLanguageOfPreference(), customerContext.getLanguageOfPreference()) && ObjectsCompat.equals(getTimezone(), customerContext.getTimezone());
    }

    public int hashCode() {
        return (getLocale() + getLanguageOfPreference() + getTimezone()).hashCode();
    }
}
