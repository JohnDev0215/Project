package com.amplifyframework.api.aws.utils;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.api.rest.HttpMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes5.dex */
public final class RestRequestFactory {

    interface BodyCreationStrategy {
        void buildRequest(Request.Builder builder, byte[] bArr);
    }

    private RestRequestFactory() {
    }

    @NonNull
    public static URL createURL(@NonNull String str, @Nullable String str2, @Nullable Map<String, String> map) throws MalformedURLException {
        URL url = new URL(str);
        HttpUrl.Builder builderAddPathSegment = new HttpUrl.Builder().scheme(url.getProtocol()).host(url.getHost()).addPathSegment(stripLeadingSlashes(url.getPath()));
        if (url.getPort() != -1) {
            builderAddPathSegment.port(url.getPort());
        }
        if (str2 != null) {
            builderAddPathSegment.addPathSegments(stripLeadingSlashes(str2));
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builderAddPathSegment.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        try {
            return new URL(Uri.decode(builderAddPathSegment.build().url().toString()));
        } catch (MalformedURLException e) {
            throw new MalformedURLException(e.getMessage());
        }
    }

    @NonNull
    public static Request createRequest(@NonNull URL url, @Nullable byte[] bArr, @Nullable Map<String, String> map, @NonNull HttpMethod httpMethod) {
        Objects.requireNonNull(url);
        Objects.requireNonNull(httpMethod);
        Request.Builder builderUrl = new Request.Builder().url(url);
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$api$rest$HttpMethod[httpMethod.ordinal()]) {
            case 1:
                builderUrl.get();
                break;
            case 2:
                populateBody(builderUrl, bArr, new BodyCreationStrategy() { // from class: com.amplifyframework.api.aws.utils.RestRequestFactory$$ExternalSyntheticLambda0
                    @Override // com.amplifyframework.api.aws.utils.RestRequestFactory.BodyCreationStrategy
                    public final void buildRequest(Request.Builder builder, byte[] bArr2) {
                        RestRequestFactory.lambda$createRequest$0(builder, bArr2);
                    }
                });
                break;
            case 3:
                populateBody(builderUrl, bArr, new BodyCreationStrategy() { // from class: com.amplifyframework.api.aws.utils.RestRequestFactory$$ExternalSyntheticLambda1
                    @Override // com.amplifyframework.api.aws.utils.RestRequestFactory.BodyCreationStrategy
                    public final void buildRequest(Request.Builder builder, byte[] bArr2) {
                        RestRequestFactory.lambda$createRequest$1(builder, bArr2);
                    }
                });
                break;
            case 4:
                builderUrl.head();
                break;
            case 5:
                populateBody(builderUrl, bArr, new BodyCreationStrategy() { // from class: com.amplifyframework.api.aws.utils.RestRequestFactory$$ExternalSyntheticLambda2
                    @Override // com.amplifyframework.api.aws.utils.RestRequestFactory.BodyCreationStrategy
                    public final void buildRequest(Request.Builder builder, byte[] bArr2) {
                        RestRequestFactory.lambda$createRequest$2(builder, bArr2);
                    }
                });
                break;
            case 6:
                populateBody(builderUrl, bArr, new BodyCreationStrategy() { // from class: com.amplifyframework.api.aws.utils.RestRequestFactory$$ExternalSyntheticLambda3
                    @Override // com.amplifyframework.api.aws.utils.RestRequestFactory.BodyCreationStrategy
                    public final void buildRequest(Request.Builder builder, byte[] bArr2) {
                        RestRequestFactory.lambda$createRequest$3(builder, bArr2);
                    }
                });
                break;
        }
        if (map != null) {
            builderUrl.headers(Headers.of(map));
        }
        return builderUrl.build();
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.utils.RestRequestFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$rest$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$amplifyframework$api$rest$HttpMethod = iArr;
            try {
                iArr[HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.POST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.HEAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.PATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$rest$HttpMethod[HttpMethod.DELETE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createRequest$0(Request.Builder builder, byte[] bArr) {
        builder.put(RequestBody.create(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createRequest$1(Request.Builder builder, byte[] bArr) {
        builder.post(RequestBody.create(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createRequest$2(Request.Builder builder, byte[] bArr) {
        builder.patch(RequestBody.create(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createRequest$3(Request.Builder builder, byte[] bArr) {
        builder.delete(RequestBody.create(bArr));
    }

    private static void populateBody(Request.Builder builder, byte[] bArr, BodyCreationStrategy bodyCreationStrategy) {
        if (bArr != null) {
            bodyCreationStrategy.buildRequest(builder, bArr);
        }
    }

    private static String stripLeadingSlashes(String str) {
        return str.replaceAll("^[\\\\/]+", "");
    }
}
