package com.amplifyframework.api.aws.sigv4;

import com.amazonaws.auth.AWS4Signer;
import java.net.URI;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiGatewayIamSigner extends AWS4Signer {
    private static final String SERVICE_NAME_SCOPE = "execute-api";

    @Override // com.amazonaws.auth.AWS4Signer
    protected String extractServiceName(URI uri) {
        return SERVICE_NAME_SCOPE;
    }

    public ApiGatewayIamSigner(String str) {
        super(true);
        setRegionName(str);
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected String getCanonicalizedResourcePath(String str, boolean z) {
        String canonicalizedResourcePath = super.getCanonicalizedResourcePath(str, z);
        return canonicalizedResourcePath.endsWith("/") ? canonicalizedResourcePath.substring(0, canonicalizedResourcePath.length() - 1) : canonicalizedResourcePath;
    }
}
