package com.android.textclassifier.common.logging;

import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class TextClassificationSessionId {
    private final String value;

    public TextClassificationSessionId() {
        this(UUID.randomUUID().toString());
    }

    private TextClassificationSessionId(String str) {
        Preconditions.checkNotNull(str);
        this.value = str;
    }

    public String toString() {
        return String.format(Locale.US, "TextClassificationSessionId {%s}", this.value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TextClassificationSessionId.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.value, ((TextClassificationSessionId) obj).value);
    }

    public String getValue() {
        return this.value;
    }

    public static TextClassificationSessionId unflattenFromString(String str) {
        return new TextClassificationSessionId(str);
    }
}
