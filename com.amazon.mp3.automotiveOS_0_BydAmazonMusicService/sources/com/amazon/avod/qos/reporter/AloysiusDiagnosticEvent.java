package com.amazon.avod.qos.reporter;

import com.amazon.avod.util.DLog;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusDiagnosticEvent {
    private final String mLabel;
    private final AloysiusDiagnosticsState mState;
    private final String mValue;

    public AloysiusDiagnosticEvent(@Nonnull String str, @Nonnull String str2, AloysiusDiagnosticsState aloysiusDiagnosticsState) {
        this.mValue = getCheckedFieldText(str2, "value", 200);
        this.mLabel = getCheckedFieldText(str, ContextMappingConstants.LABEL, 60);
        this.mState = aloysiusDiagnosticsState;
    }

    public String getValue() {
        return this.mValue;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public AloysiusDiagnosticsState getState() {
        return this.mState;
    }

    private String getCheckedFieldText(@Nonnull String str, @Nonnull String str2, int i) {
        Preconditions.checkNotNull(str2, "fieldName");
        Preconditions.checkNotNull(str, str2);
        if (str.length() <= i) {
            return str;
        }
        DLog.devf("Diagnostic event %s field is greater than allowed %d characters, truncate the text", str2, Integer.valueOf(i));
        return str.substring(0, i - 3) + "...";
    }
}
