package com.amplifyframework.api.aws;

import com.amplifyframework.api.aws.UserAgentInterceptor;
import com.amplifyframework.util.UserAgent;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class AWSApiPlugin$$ExternalSyntheticLambda0 implements UserAgentInterceptor.UserAgentProvider {
    @Override // com.amplifyframework.api.aws.UserAgentInterceptor.UserAgentProvider
    public final String getUserAgent() {
        return UserAgent.string();
    }
}
