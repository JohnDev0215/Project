package org.chromium.ui.picker;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public class DateTimeSuggestion {
    private final String mLabel;
    private final String mLocalizedValue;
    private final double mValue;

    public DateTimeSuggestion(double d, String str, String str2) {
        this.mValue = d;
        this.mLocalizedValue = str;
        this.mLabel = str2;
    }

    double value() {
        return this.mValue;
    }

    String localizedValue() {
        return this.mLocalizedValue;
    }

    String label() {
        return this.mLabel;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DateTimeSuggestion)) {
            return false;
        }
        DateTimeSuggestion dateTimeSuggestion = (DateTimeSuggestion) obj;
        return this.mValue == dateTimeSuggestion.mValue && TextUtils.equals(this.mLocalizedValue, dateTimeSuggestion.mLocalizedValue) && TextUtils.equals(this.mLabel, dateTimeSuggestion.mLabel);
    }

    public int hashCode() {
        return ((((1147 + ((int) this.mValue)) * 37) + this.mLocalizedValue.hashCode()) * 37) + this.mLabel.hashCode();
    }
}
