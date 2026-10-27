package com.android.textclassifier.common.logging;

import com.google.common.base.Preconditions;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class TextClassificationContext {
    private final String packageName;
    private final String widgetType;
    private final String widgetVersion;

    private TextClassificationContext(String str, String str2, String str3) {
        Preconditions.checkNotNull(str);
        this.packageName = str;
        Preconditions.checkNotNull(str2);
        this.widgetType = str2;
        this.widgetVersion = str3;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getWidgetType() {
        return this.widgetType;
    }

    public String toString() {
        return String.format(Locale.US, "TextClassificationContext{packageName=%s, widgetType=%s, widgetVersion=%s}", this.packageName, this.widgetType, this.widgetVersion);
    }

    public static final class Builder {
        private final String packageName;
        private final String widgetType;
        private String widgetVersion;

        public Builder(String str, String str2) {
            Preconditions.checkNotNull(str);
            this.packageName = str;
            Preconditions.checkNotNull(str2);
            this.widgetType = str2;
        }

        public Builder setWidgetVersion(String str) {
            this.widgetVersion = str;
            return this;
        }

        public TextClassificationContext build() {
            return new TextClassificationContext(this.packageName, this.widgetType, this.widgetVersion);
        }
    }
}
