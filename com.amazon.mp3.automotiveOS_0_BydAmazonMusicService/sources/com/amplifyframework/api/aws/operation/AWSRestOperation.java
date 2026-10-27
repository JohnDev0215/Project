package com.amplifyframework.api.aws.operation;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazonaws.AmazonClientException;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.utils.RestRequestFactory;
import com.amplifyframework.api.rest.RestOperation;
import com.amplifyframework.api.rest.RestOperationRequest;
import com.amplifyframework.api.rest.RestResponse;
import com.amplifyframework.core.Consumer;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes5.dex */
@SuppressLint({"SyntheticAccessor"})
public final class AWSRestOperation extends RestOperation {
    private final OkHttpClient client;
    private final String endpoint;
    private final Consumer<ApiException> onFailure;
    private final Consumer<RestResponse> onResponse;
    private Call ongoingCall;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AWSRestOperation(@NonNull RestOperationRequest restOperationRequest, @NonNull String str, @NonNull OkHttpClient okHttpClient, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        super(restOperationRequest);
        Objects.requireNonNull(restOperationRequest);
        Objects.requireNonNull(str);
        this.endpoint = str;
        Objects.requireNonNull(okHttpClient);
        this.client = okHttpClient;
        Objects.requireNonNull(consumer);
        this.onResponse = consumer;
        Objects.requireNonNull(consumer2);
        this.onFailure = consumer2;
    }

    @Override // com.amplifyframework.core.async.AmplifyOperation
    public void start() {
        Call call = this.ongoingCall;
        if (call == null || !call.isExecuted()) {
            try {
                Call callNewCall = this.client.newCall(RestRequestFactory.createRequest(RestRequestFactory.createURL(this.endpoint, getRequest().getPath(), getRequest().getQueryParameters()), getRequest().getData(), getRequest().getHeaders(), getRequest().getHttpMethod()));
                this.ongoingCall = callNewCall;
                FirebasePerfOkHttpClient.enqueue(callNewCall, new OkHttpCallback());
            } catch (Exception e) {
                Call call2 = this.ongoingCall;
                if (call2 != null) {
                    call2.cancel();
                }
                this.onFailure.accept(new ApiException("OkHttp client failed to make a successful request.", e, AmplifyException.TODO_RECOVERY_SUGGESTION));
            }
        }
    }

    @Override // com.amplifyframework.core.async.Cancelable
    public synchronized void cancel() {
        Call call = this.ongoingCall;
        if (call != null) {
            call.cancel();
        }
    }

    private final class OkHttpCallback implements Callback {
        private OkHttpCallback() {
        }

        @Override // okhttp3.Callback
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            RestResponse restResponse;
            ResponseBody responseBodyBody = response.body();
            int iCode = response.code();
            HashMap map = new HashMap();
            Map<String, List<String>> multimap = response.headers().toMultimap();
            for (String str : multimap.keySet()) {
                List<String> list = multimap.get(str);
                if (list.size() > 0) {
                    map.put(str, TextUtils.join(",", list));
                }
            }
            if (responseBodyBody != null) {
                restResponse = new RestResponse(iCode, map, responseBodyBody.bytes());
            } else {
                restResponse = new RestResponse(iCode, map);
            }
            AWSRestOperation.this.onResponse.accept(restResponse);
        }

        @Override // okhttp3.Callback
        public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
            ApiException apiException;
            if (AWSRestOperation.this.ongoingCall == null || !AWSRestOperation.this.ongoingCall.getCanceled()) {
                if (iOException.getCause() != null && (iOException.getCause() instanceof AmazonClientException)) {
                    apiException = new ApiException("Received an exception while making the request.", iOException.getCause(), "See attached exception for more details.");
                } else {
                    apiException = new ApiException("Received an IO exception while making the request.", iOException, "Retry the request.");
                }
                AWSRestOperation.this.onFailure.accept(apiException);
            }
        }
    }
}
