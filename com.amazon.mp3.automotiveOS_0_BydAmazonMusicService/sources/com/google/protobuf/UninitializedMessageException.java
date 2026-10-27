package com.google.protobuf;

import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class UninitializedMessageException extends RuntimeException {
    private final List<String> missingFields;

    public UninitializedMessageException(final MessageLite message) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.missingFields = null;
    }

    public UninitializedMessageException(final List<String> missingFields) {
        super(buildDescription(missingFields));
        this.missingFields = missingFields;
    }

    public InvalidProtocolBufferException asInvalidProtocolBufferException() {
        return new InvalidProtocolBufferException(getMessage());
    }

    private static String buildDescription(final List<String> missingFields) {
        StringBuilder sb = new StringBuilder("Message missing required fields: ");
        boolean z = true;
        for (String str : missingFields) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
