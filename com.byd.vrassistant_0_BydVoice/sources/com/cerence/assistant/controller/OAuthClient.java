package com.cerence.assistant.controller;

import cerence.ark.assistant.common.Logger;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class OAuthClient implements IAuthClient {
    private static final String TAG = "OAuthClient";
    private final String authUrl;
    private final String clientId;
    private final String clientSecret;
    private final String grantType;
    private final int oauthConnectTimeout;
    private final String scope;

    private OAuthClient(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.authUrl = builder.authUrl;
        this.scope = builder.scope;
        this.grantType = builder.grantType;
        this.oauthConnectTimeout = builder.oauthConnectTimeout;
    }

    @Override // com.cerence.assistant.controller.IAuthClient
    public String getAuthToken() throws IOException {
        try {
            Response responseExecute = new OkHttpClient().newBuilder().connectTimeout(this.oauthConnectTimeout, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(this.authUrl).post(new FormBody.Builder().add("grant_type", this.grantType).add("client_id", this.clientId).add("client_secret", this.clientSecret).add("scope", this.scope).build()).build()).execute();
            try {
                if (!responseExecute.isSuccessful()) {
                    throw new IOException("**GRPC_deleteSession** Oauth OKHttp3 POST Unexpected code " + responseExecute);
                }
                String string = responseExecute.body() != null ? new JSONObject(responseExecute.body().string()).getString("access_token") : "";
                if (responseExecute != null) {
                    responseExecute.close();
                }
                Logger.debug(TAG, String.format("**GRPC_deleteSession** access_token: %s", string));
                return string;
            } finally {
            }
        } catch (JSONException e) {
            Logger.error(TAG, "**GRPC_deleteSession** newCall execute ERROR ", e);
            throw new RuntimeException(e);
        }
    }

    public static class Builder {
        private String authUrl;
        private String clientId;
        private String clientSecret;
        private String grantType;
        private int oauthConnectTimeout;
        private String scope;

        public Builder setClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder setClientSecret(String str) {
            this.clientSecret = str;
            return this;
        }

        public Builder setAuthUrl(String str) {
            this.authUrl = str;
            return this;
        }

        public Builder setScope(String str) {
            this.scope = str;
            return this;
        }

        public Builder setGrantType(String str) {
            this.grantType = str;
            return this;
        }

        public Builder setOauthConnectTimeout(int i) {
            this.oauthConnectTimeout = i;
            return this;
        }

        public OAuthClient build() {
            return new OAuthClient(this);
        }
    }
}
