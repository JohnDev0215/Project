package com.android.textclassifier.common.statsd;

import com.android.textclassifier.common.logging.TextClassificationSessionId;

/* JADX INFO: loaded from: classes.dex */
public final class TextClassificationSessionIdConverter {
    public static TextClassificationSessionId fromPlatform(android.view.textclassifier.TextClassificationSessionId textClassificationSessionId) {
        if (textClassificationSessionId == null) {
            return null;
        }
        return TextClassificationSessionId.unflattenFromString(textClassificationSessionId.getValue());
    }
}
