package com.amplifyframework.api.aws;

/* JADX INFO: loaded from: classes5.dex */
public enum EndpointType {
    GRAPHQL("GraphQL"),
    REST("REST");

    private final String name;

    EndpointType(String str) {
        this.name = str;
    }

    static EndpointType from(String str) {
        for (EndpointType endpointType : values()) {
            if (endpointType.name.equals(str)) {
                return endpointType;
            }
        }
        throw new IllegalArgumentException("No such endpoint type: " + str);
    }
}
