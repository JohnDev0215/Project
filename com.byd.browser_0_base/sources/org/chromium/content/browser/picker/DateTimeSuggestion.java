package org.chromium.content.browser.picker;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DateTimeSuggestion {
    public final double a;
    public final String b;
    public final String c;

    public DateTimeSuggestion(String str, String str2, double d) {
        this.a = d;
        this.b = str;
        this.c = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DateTimeSuggestion)) {
            return false;
        }
        DateTimeSuggestion dateTimeSuggestion = (DateTimeSuggestion) obj;
        return this.a == dateTimeSuggestion.a && TextUtils.equals(this.b, dateTimeSuggestion.b) && TextUtils.equals(this.c, dateTimeSuggestion.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + ((1147 + ((int) this.a)) * 37)) * 37);
    }
}
