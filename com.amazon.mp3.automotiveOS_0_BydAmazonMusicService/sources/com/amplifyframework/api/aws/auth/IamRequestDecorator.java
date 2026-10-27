package com.amplifyframework.api.aws.auth;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.MediaType;

/* JADX INFO: loaded from: classes5.dex */
public class IamRequestDecorator implements RequestDecorator {
    private static final String CONTENT_TYPE = "application/json";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse(CONTENT_TYPE);
    private final AWSCredentialsProvider credentialsProvider;
    private final String serviceName;
    private final AWS4Signer v4Signer;

    public IamRequestDecorator(AWS4Signer aWS4Signer, AWSCredentialsProvider aWSCredentialsProvider, String str) {
        this.v4Signer = aWS4Signer;
        this.credentialsProvider = aWSCredentialsProvider;
        this.serviceName = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0067 A[Catch: IOException -> 0x00da, TryCatch #0 {IOException -> 0x00da, blocks: (B:8:0x0044, B:10:0x004e, B:12:0x0056, B:14:0x006e, B:13:0x0067), top: B:28:0x0044 }] */
    @Override // com.amplifyframework.api.aws.auth.RequestDecorator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okhttp3.Request decorate(okhttp3.Request r8) throws com.amplifyframework.api.ApiException.ApiAuthException {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplifyframework.api.aws.auth.IamRequestDecorator.decorate(okhttp3.Request):okhttp3.Request");
    }

    @NonNull
    private static Map<String, String> splitQuery(URL url) throws IOException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Uri uri = Uri.parse(url.toString());
        for (String str : uri.getQueryParameterNames()) {
            linkedHashMap.put(str, URLDecoder.decode(uri.getQueryParameter(str), "UTF8"));
        }
        return linkedHashMap;
    }
}
