package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: loaded from: classes5.dex */
final class UserAgentInterceptor implements Interceptor {
    private final UserAgentProvider userAgentProvider;

    interface UserAgentProvider {
        String getUserAgent();
    }

    private UserAgentInterceptor(UserAgentProvider userAgentProvider) {
        this.userAgentProvider = userAgentProvider;
    }

    static UserAgentInterceptor using(UserAgentProvider userAgentProvider) {
        return new UserAgentInterceptor(userAgentProvider);
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header(HttpHeader.USER_AGENT, this.userAgentProvider.getUserAgent()).build());
    }
}
