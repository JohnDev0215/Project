package com.amplifyframework.api.aws.sigv4;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.results.Tokens;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.core.Amplify;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes5.dex */
public final class DefaultCognitoUserPoolsAuthProvider implements CognitoUserPoolsAuthProvider {
    private static final String AUTH_DEPENDENCY_PLUGIN_KEY = "awsCognitoAuthPlugin";
    private AWSMobileClient awsMobileClient;
    private String lastTokenRetrievalFailureMessage;
    private String token;

    public DefaultCognitoUserPoolsAuthProvider() throws ApiException.ApiAuthException {
        try {
            this.awsMobileClient = (AWSMobileClient) Amplify.Auth.getPlugin(AUTH_DEPENDENCY_PLUGIN_KEY).getEscapeHatch();
        } catch (IllegalStateException e) {
            throw new ApiException.ApiAuthException("AWSApiPlugin depends on AWSCognitoAuthPlugin but it is currently missing", e, "Before configuring Amplify, be sure to add AWSCognitoAuthPlugin same as you added AWSApiPlugin.");
        }
    }

    public DefaultCognitoUserPoolsAuthProvider(AWSMobileClient aWSMobileClient) {
        this.awsMobileClient = aWSMobileClient;
    }

    private synchronized void fetchToken() throws ApiException {
        final Semaphore semaphore = new Semaphore(0);
        this.lastTokenRetrievalFailureMessage = null;
        this.awsMobileClient.getTokens(new Callback<Tokens>() { // from class: com.amplifyframework.api.aws.sigv4.DefaultCognitoUserPoolsAuthProvider.1
            @Override // com.amazonaws.mobile.client.Callback
            public void onResult(Tokens tokens) {
                DefaultCognitoUserPoolsAuthProvider.this.token = tokens.getAccessToken().getTokenString();
                semaphore.release();
            }

            @Override // com.amazonaws.mobile.client.Callback
            public void onError(Exception exc) {
                DefaultCognitoUserPoolsAuthProvider.this.lastTokenRetrievalFailureMessage = exc.getLocalizedMessage();
                semaphore.release();
            }
        });
        try {
            semaphore.acquire();
            if (this.lastTokenRetrievalFailureMessage != null) {
                throw new ApiException.ApiAuthException(this.lastTokenRetrievalFailureMessage, AmplifyException.TODO_RECOVERY_SUGGESTION);
            }
        } catch (InterruptedException e) {
            throw new ApiException("Interrupted waiting for Cognito Userpools token.", e, AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
    }

    @Override // com.amplifyframework.api.aws.sigv4.CognitoUserPoolsAuthProvider
    public String getLatestAuthToken() throws ApiException {
        fetchToken();
        return this.token;
    }

    @Override // com.amplifyframework.api.aws.sigv4.CognitoUserPoolsAuthProvider
    public String getUsername() {
        return this.awsMobileClient.getUsername();
    }
}
