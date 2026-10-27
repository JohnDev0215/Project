package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.RetryHandler;
import com.amazon.commsnetworking.metrics.MetricMetadata;
import java.io.File;
import java.util.UUID;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes2.dex */
public class CommsNetworkRequest implements INetworkRequest {
    static int MAX_ATTEMPT = 5;
    int attempt;
    Request.Builder builder;
    private Call call;
    Integer maxAttempt;
    String mediaType;
    private Method method;
    byte[] payloadBytes;
    File payloadFile;
    String payloadString;
    private PayloadType payloadType;
    private String requestId;
    RetryHandler retryHandler;
    HttpUrl.Builder urlBuilder;

    public enum Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    enum PayloadType {
        STRING,
        FILE,
        BYTES,
        NONE
    }

    CommsNetworkRequest(Builder builder) {
        this.payloadType = PayloadType.NONE;
        this.attempt = 0;
        this.method = builder.method;
        this.requestId = builder.requestTag.getRequestId();
        this.urlBuilder = builder.urlBuilder;
        this.builder = builder.builder;
    }

    CommsNetworkRequest(@NonNull Method method, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        this(new Builder().withMethod(method).withUrl(str2, str3).withDefaultRequestBuilder().withCommsNetworkingRequestTag(new MetricMetadata(str, str4)));
    }

    public static CommsNetworkRequest get(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.GET, str, str2, str3, str4);
    }

    public static CommsNetworkRequest post(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.POST, str, str2, str3, str4);
    }

    public static CommsNetworkRequest patch(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.PATCH, str, str2, str3, str4);
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withParameter(@NonNull String str, @NonNull String str2) {
        this.urlBuilder.addQueryParameter(str, str2);
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withPayload(@NonNull String str, @NonNull String str2) {
        this.payloadType = PayloadType.STRING;
        this.mediaType = str;
        this.payloadString = str2;
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withHeader(@NonNull String str, @NonNull String str2) {
        this.builder.header(str, str2);
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public int getAttemptCount() {
        return this.attempt;
    }

    private RequestBody prepareRequestBody() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType[this.payloadType.ordinal()];
        if (i == 1) {
            return RequestBody.create(MediaType.parse(this.mediaType), this.payloadString);
        }
        if (i == 2) {
            return RequestBody.create(MediaType.parse(this.mediaType), this.payloadBytes);
        }
        if (i == 3) {
            return RequestBody.create(MediaType.parse(this.mediaType), this.payloadFile);
        }
        String str = this.mediaType;
        return RequestBody.create(str == null ? null : MediaType.parse(str), new byte[0]);
    }

    /* JADX INFO: renamed from: com.amazon.commsnetworking.CommsNetworkRequest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType;

        static {
            int[] iArr = new int[Method.values().length];
            $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method = iArr;
            try {
                iArr[Method.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.PUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.PATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[PayloadType.values().length];
            $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType = iArr2;
            try {
                iArr2[PayloadType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType[PayloadType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType[PayloadType.FILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public Request buildRequest() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[this.method.ordinal()];
        if (i == 1) {
            this.builder.get();
        } else if (i == 2) {
            this.builder.post(prepareRequestBody());
        } else if (i == 3) {
            this.builder.put(prepareRequestBody());
        } else if (i == 4) {
            this.builder.patch(prepareRequestBody());
        } else {
            if (i != 5) {
                return null;
            }
            this.builder.delete(prepareRequestBody());
        }
        this.builder.addHeader("X-Amzn-RequestId", this.requestId);
        return this.builder.url(this.urlBuilder.build()).build();
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public void setCall(@NonNull Call call) {
        this.attempt++;
        this.call = call;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public boolean canRetry(INetworkResponse iNetworkResponse) {
        int i = this.attempt;
        return i < MAX_ATTEMPT && this.retryHandler != null && i < this.maxAttempt.intValue() && this.retryHandler.shouldRetry(iNetworkResponse);
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public void prepareForRetry(INetworkResponse iNetworkResponse) {
        RetryHandler retryHandler = this.retryHandler;
        if (retryHandler != null) {
            retryHandler.prepareRetryRequest(this, iNetworkResponse);
        }
    }

    public static class Builder {
        Method method = null;
        RequestTag requestTag = null;
        Request.Builder builder = null;
        HttpUrl.Builder urlBuilder = null;

        public Builder withMethod(@NonNull Method method) {
            this.method = method;
            return this;
        }

        public Builder withUrl(@NonNull String str, @NonNull String str2) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("/");
            if (str2.startsWith("/")) {
                str2 = str2.substring(1);
            }
            sb.append(str2);
            this.urlBuilder = HttpUrl.parse(sb.toString()).newBuilder();
            return this;
        }

        public Builder withCommsNetworkingRequestTag(@Nullable MetricMetadata metricMetadata) {
            return withCommsNetworkingRequestTag(null, metricMetadata);
        }

        public Builder withCommsNetworkingRequestTag(@Nullable String str, @Nullable MetricMetadata metricMetadata) {
            withRequestTag(new RequestTag(generateRequestId(str), metricMetadata));
            return this;
        }

        @VisibleForTesting
        public Builder withRequestTag(@NonNull RequestTag requestTag) {
            this.requestTag = requestTag;
            return this;
        }

        @VisibleForTesting
        String generateRequestId(@Nullable String str) {
            StringBuilder sb = new StringBuilder();
            if (str == null) {
                str = "";
            }
            sb.append(str);
            sb.append(UUID.randomUUID().toString());
            return sb.toString();
        }

        @VisibleForTesting
        Builder withDefaultRequestBuilder() {
            this.builder = new Request.Builder();
            return this;
        }
    }
}
