package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum AWSAppSyncScalarType {
    ID("ID"),
    STRING("String"),
    INT("Int"),
    FLOAT("Float"),
    BOOLEAN("Boolean"),
    AWS_DATE("AWSDate"),
    AWS_TIME("AWSTime"),
    AWS_DATE_TIME("AWSDateTime"),
    AWS_TIMESTAMP("AWSTimestamp"),
    AWS_EMAIL("AWSEmail"),
    AWS_JSON("AWSJSON"),
    AWS_URL("AWSURL"),
    AWS_PHONE("AWSPhone"),
    AWS_IP_ADDRESS("AWSIPAddress");

    private final String stringValue;

    AWSAppSyncScalarType(String str) {
        this.stringValue = str;
    }

    public String stringValue() {
        return this.stringValue;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return stringValue();
    }

    public static AWSAppSyncScalarType fromString(String str) {
        for (AWSAppSyncScalarType aWSAppSyncScalarType : values()) {
            if (aWSAppSyncScalarType.stringValue.equalsIgnoreCase(str)) {
                return aWSAppSyncScalarType;
            }
        }
        throw new IllegalArgumentException("Provided value of " + str + " is not a AWSAppSyncScalarType.");
    }
}
