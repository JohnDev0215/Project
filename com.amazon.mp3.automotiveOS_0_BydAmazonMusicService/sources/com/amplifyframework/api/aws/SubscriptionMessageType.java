package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes5.dex */
enum SubscriptionMessageType {
    CONNECTION_INIT("connection_init"),
    CONNECTION_ACK("connection_ack"),
    CONNECTION_ERROR("connection_error"),
    CONNECTION_KEEP_ALIVE("ka"),
    CONNECTION_TERMINATE("connection_terminate"),
    SUBSCRIPTION_START(AsmString.METHOD_FIREBASE_PERF_TRACE_START),
    SUBSCRIPTION_ACK("start_ack"),
    SUBSCRIPTION_DATA("data"),
    SUBSCRIPTION_ERROR("error"),
    SUBSCRIPTION_COMPLETE("complete"),
    SUBSCRIPTION_STOP(AsmString.METHOD_FIREBASE_PERF_TRACE_STOP);

    private final String value;

    SubscriptionMessageType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.value;
    }

    public static SubscriptionMessageType from(String str) {
        for (SubscriptionMessageType subscriptionMessageType : values()) {
            if (subscriptionMessageType.value.equals(str)) {
                return subscriptionMessageType;
            }
        }
        throw new IllegalArgumentException("No such subscription message type: " + str);
    }
}
