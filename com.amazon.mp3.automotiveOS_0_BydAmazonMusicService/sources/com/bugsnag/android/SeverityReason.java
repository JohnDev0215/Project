package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.bugsnag.android.JsonStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
final class SeverityReason implements JsonStream.Streamable {

    @Nullable
    private final String attributeKey;

    @Nullable
    private final String attributeValue;
    private Severity currentSeverity;
    private final Severity defaultSeverity;
    final boolean originalUnhandled;
    private final String severityReasonType;
    private boolean unhandled;

    static SeverityReason newInstance(String str) {
        return newInstance(str, null, null);
    }

    static SeverityReason newInstance(String str, @Nullable Severity severity, @Nullable String str2) {
        if (str.equals("strictMode") && Intrinsics.isEmpty(str2)) {
            throw new IllegalArgumentException("No reason supplied for strictmode");
        }
        if (!str.equals("strictMode") && !str.equals("log") && !Intrinsics.isEmpty(str2)) {
            throw new IllegalArgumentException("attributeValue should not be supplied");
        }
        switch (str) {
            case "strictMode":
                return new SeverityReason(str, Severity.WARNING, true, true, str2, "violationType");
            case "userCallbackSetSeverity":
            case "userSpecifiedSeverity":
                return new SeverityReason(str, severity, false, false, null, null);
            case "unhandledException":
            case "anrError":
            case "unhandledPromiseRejection":
                return new SeverityReason(str, Severity.ERROR, true, true, null, null);
            case "handledError":
            case "handledException":
                return new SeverityReason(str, Severity.WARNING, false, false, null, null);
            case "log":
                return new SeverityReason(str, severity, false, false, str2, "level");
            default:
                throw new IllegalArgumentException("Invalid argument for severityReason: '" + str + '\'');
        }
    }

    SeverityReason(String str, Severity severity, boolean z, boolean z2, @Nullable String str2, @Nullable String str3) {
        this.severityReasonType = str;
        this.unhandled = z;
        this.originalUnhandled = z2;
        this.defaultSeverity = severity;
        this.currentSeverity = severity;
        this.attributeValue = str2;
        this.attributeKey = str3;
    }

    String calculateSeverityReasonType() {
        return this.defaultSeverity == this.currentSeverity ? this.severityReasonType : "userCallbackSetSeverity";
    }

    Severity getCurrentSeverity() {
        return this.currentSeverity;
    }

    boolean getUnhandled() {
        return this.unhandled;
    }

    boolean getUnhandledOverridden() {
        return this.unhandled != this.originalUnhandled;
    }

    @Nullable
    String getAttributeValue() {
        return this.attributeValue;
    }

    String getAttributeKey() {
        return this.attributeKey;
    }

    String getSeverityReasonType() {
        return this.severityReasonType;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject().name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE).value(calculateSeverityReasonType()).name("unhandledOverridden").value(getUnhandledOverridden());
        if (this.attributeKey != null && this.attributeValue != null) {
            jsonStream.name("attributes").beginObject().name(this.attributeKey).value(this.attributeValue).endObject();
        }
        jsonStream.endObject();
    }
}
