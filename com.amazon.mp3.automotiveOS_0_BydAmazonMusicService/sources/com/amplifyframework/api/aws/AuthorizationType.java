package com.amplifyframework.api.aws;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.util.Empty;

/* JADX INFO: loaded from: classes5.dex */
public enum AuthorizationType {
    API_KEY,
    AWS_IAM,
    OPENID_CONNECT,
    AMAZON_COGNITO_USER_POOLS,
    AWS_LAMBDA,
    NONE;

    public static AuthorizationType from(String str) {
        for (AuthorizationType authorizationType : values()) {
            if (authorizationType.name().equals(str)) {
                return authorizationType;
            }
        }
        throw new IllegalArgumentException("No such authorization type: " + str);
    }

    public static AuthorizationType from(AuthRule authRule) {
        String strProvider = authRule.provider();
        if (Empty.check(strProvider)) {
            strProvider = authRule.allow().getDefaultAuthProvider().name();
        }
        return from(AuthStrategy.Provider.from(strProvider));
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.AuthorizationType$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider;

        static {
            int[] iArr = new int[AuthStrategy.Provider.values().length];
            $SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider = iArr;
            try {
                iArr[AuthStrategy.Provider.USER_POOLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider[AuthStrategy.Provider.OIDC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider[AuthStrategy.Provider.IAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider[AuthStrategy.Provider.API_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider[AuthStrategy.Provider.FUNCTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static AuthorizationType from(AuthStrategy.Provider provider) {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$AuthStrategy$Provider[provider.ordinal()];
        if (i == 1) {
            return AMAZON_COGNITO_USER_POOLS;
        }
        if (i == 2) {
            return OPENID_CONNECT;
        }
        if (i == 3) {
            return AWS_IAM;
        }
        if (i == 4) {
            return API_KEY;
        }
        if (i == 5) {
            return AWS_LAMBDA;
        }
        throw new IllegalArgumentException("No such authorization type: " + provider.name());
    }
}
