package com.amazon.mp3.account.credentials;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.hermes.Hermes;
import com.amazon.hermes.RequestFacade;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.net.RequestAuthorizer;
import com.amazon.mp3.net.UserAgent;
import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes3.dex */
public class RequestInterceptorImpl implements RequestInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(RequestInterceptorImpl.class.getSimpleName());
    private final ApplicationAttributes mApplicationAttributes;
    private final RequestAuthorizer mRequestAuthorizer = new RequestAuthorizer();
    private final AccountCredentialStorage mStorage;

    public RequestInterceptorImpl(@NonNull final Context context) {
        this.mStorage = AccountCredentialStorage.get(context);
        this.mApplicationAttributes = ApplicationAttributes.getInstance(context);
    }

    @Override // com.amazon.hermes.RequestInterceptor
    public void intercept(@NonNull RequestFacade requestFacade) throws Exception {
        intercept(requestFacade, Hermes.get().getObjectMapper());
    }

    @Override // com.amazon.hermes.RequestInterceptor
    public void intercept(@NonNull RequestFacade requestFacade, final ObjectMapper mapper) throws Exception {
        this.mRequestAuthorizer.signRequest(requestFacade, getSerializedBody(requestFacade.getBody(), mapper));
        requestFacade.addHeaders(HttpHeader.USER_AGENT, UserAgent.getUserAgent());
        requestFacade.addHeaders("Android-App-Version", this.mApplicationAttributes.getVersion());
    }

    private static final String getSerializedBody(final Object body, final ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(body);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return "";
        }
    }
}
