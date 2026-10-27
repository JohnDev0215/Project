package com.amplifyframework.api.aws;

/* JADX INFO: loaded from: classes5.dex */
public enum AuthModeStrategyType {
    DEFAULT("default"),
    MULTIAUTH("multiauth");

    private final String strategyName;

    AuthModeStrategyType(String str) {
        this.strategyName = str;
    }

    public AuthModeStrategyType from(String str) {
        for (AuthModeStrategyType authModeStrategyType : values()) {
            if (authModeStrategyType.name().equalsIgnoreCase(str) || authModeStrategyType.strategyName.equalsIgnoreCase(str)) {
                return authModeStrategyType;
            }
        }
        throw new IllegalArgumentException("Cannot find an authorization strategy for " + str);
    }
}
