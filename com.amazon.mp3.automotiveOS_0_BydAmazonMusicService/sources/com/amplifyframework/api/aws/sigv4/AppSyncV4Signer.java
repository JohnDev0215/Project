package com.amplifyframework.api.aws.sigv4;

import com.amazonaws.Request;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.util.BinaryUtils;
import java.io.InputStream;
import java.net.URI;

/* JADX INFO: loaded from: classes5.dex */
public final class AppSyncV4Signer extends AWS4Signer {
    private static final String CONNECTION_PATH = "/connect";
    private static final String RESOURCE_PATH = "/graphql";
    private static final String SERVICE_NAME_SCOPE = "appsync";
    private static final String TAG = "AppSyncV4Signer";
    private final boolean isWebSocketConnectionWithIam;

    @Override // com.amazonaws.auth.AWS4Signer
    public String extractServiceName(URI uri) {
        return SERVICE_NAME_SCOPE;
    }

    public AppSyncV4Signer(String str) {
        this(str, false);
    }

    public AppSyncV4Signer(String str, boolean z) {
        super(true);
        this.isWebSocketConnectionWithIam = z;
        setRegionName(str);
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    public String getCanonicalizedResourcePath(String str) {
        return this.isWebSocketConnectionWithIam ? "/graphql/connect" : RESOURCE_PATH;
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    public String getCanonicalizedResourcePath(String str, boolean z) {
        return this.isWebSocketConnectionWithIam ? "/graphql/connect" : RESOURCE_PATH;
    }

    @Override // com.amazonaws.auth.AWS4Signer
    public String calculateContentHash(Request<?> request) {
        InputStream content = request.getContent();
        content.mark(-1);
        return BinaryUtils.toHex(hash(content));
    }
}
