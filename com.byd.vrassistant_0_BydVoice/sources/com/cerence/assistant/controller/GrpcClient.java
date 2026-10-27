package com.cerence.assistant.controller;

import cerence.ark.assistant.common.Logger;
import com.cerence.assistant.controller.OAuthClient;
import io.grpc.ChannelCredentials;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.TlsChannelCredentials;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class GrpcClient {
    private static final String TAG = "GrpcClient";
    private final String OAUTH_GRANT_TYPE = "client_credentials";
    private final ManagedChannel channel;
    private final OneCloudConfig cloudConfig;

    public GrpcClient(String str) {
        try {
            OneCloudConfig oneCloudConfig = new OneCloudConfig(str);
            this.cloudConfig = oneCloudConfig;
            Logger.debug(TAG, String.format("**GRPC_deleteSession** FQDN: " + oneCloudConfig.getFQDN() + ", CACERT: " + oneCloudConfig.getCaCert() + ", CONNECTION_TIMEOUT: " + oneCloudConfig.getCONNECTION_TIMEOUT() + ", IDLE_SESSION_TIMEOUT: " + oneCloudConfig.getIDLE_SESSION_TIMEOUT() + ", CLIENT_ID: " + oneCloudConfig.getCLIENT_ID() + ", CLIENT_SECRET: " + oneCloudConfig.getCLIENT_SECRET() + ", AUTH_URL: " + oneCloudConfig.getAUTH_URL() + ", SCOPE: " + oneCloudConfig.getSCOPE() + ", OAUTH_CONNECTION_TIMEOUT: " + oneCloudConfig.getOAUTH_CONNECTION_TIMEOUT(), new Object[0]));
            this.channel = createChannel(new Interceptor(getAuthToken()), TlsChannelCredentials.newBuilder().trustManager(getCrtStream(oneCloudConfig.getCaCert())).build());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ManagedChannel getChannel() {
        return this.channel;
    }

    public OneCloudConfig getCloudConfig() {
        return this.cloudConfig;
    }

    private String getAuthToken() throws IOException {
        return new OAuthClient.Builder().setGrantType("client_credentials").setClientId(this.cloudConfig.getCLIENT_ID()).setClientSecret(this.cloudConfig.getCLIENT_SECRET()).setScope(this.cloudConfig.getSCOPE()).setAuthUrl(this.cloudConfig.getAUTH_URL() + "/oauth2/token").setOauthConnectTimeout(this.cloudConfig.getOAUTH_CONNECTION_TIMEOUT()).build().getAuthToken();
    }

    private ManagedChannel createChannel(Interceptor interceptor, ChannelCredentials channelCredentials) {
        try {
            return Grpc.newChannelBuilder(this.cloudConfig.getFQDN(), channelCredentials).intercept(interceptor).idleTimeout(this.cloudConfig.getIDLE_SESSION_TIMEOUT(), TimeUnit.SECONDS).build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getCrtStream(String str) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        while (true) {
            char c = 0;
            while (true) {
                line = bufferedReader.readLine();
                if (line != null) {
                    if (line.contains("-----BEGIN CERTIFICATE-----")) {
                        c = 1;
                    } else if (line.contains("-----END CERTIFICATE-----")) {
                        c = 2;
                    }
                    if (c == 1) {
                        if (sb.toString().equals("")) {
                            sb = new StringBuilder(line);
                        } else {
                            sb.append("\n").append(line);
                        }
                    } else if (c == 2) {
                        break;
                    }
                } else {
                    return new ByteArrayInputStream(sb.toString().getBytes());
                }
            }
            sb.append("\n").append(line);
        }
    }
}
