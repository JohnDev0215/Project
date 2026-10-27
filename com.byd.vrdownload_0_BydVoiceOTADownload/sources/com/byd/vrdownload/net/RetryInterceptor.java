package com.byd.vrdownload.net;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public class RetryInterceptor implements Interceptor {
    private static final String TAG = "RetryInterceptor";
    private int mMaxRetryCount;
    private long mRetryInterval;

    public RetryInterceptor(int i, long j) {
        this.mMaxRetryCount = i;
        this.mRetryInterval = j;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response responseDoRequest = doRequest(chain, request);
        int i = 1;
        while (true) {
            if ((responseDoRequest != null && responseDoRequest.isSuccessful()) || i > this.mMaxRetryCount) {
                break;
            }
            try {
                Thread.sleep(this.mRetryInterval);
            } catch (InterruptedException e) {
                Log.e(TAG, "intercept: " + e.getMessage());
            }
            i++;
            responseDoRequest = chain.proceed(request);
        }
        return responseDoRequest;
    }

    private Response doRequest(Interceptor.Chain chain, Request request) {
        try {
            return chain.proceed(request);
        } catch (IOException e) {
            Log.e(TAG, "intercept: " + e.getMessage());
            return null;
        }
    }

    public static class Builder {
        private int mRetryCount = 1;
        private long mRetryInterval = 1000;

        public Builder buildRetryCount(int i) {
            this.mRetryCount = i;
            return this;
        }

        public Builder buildRetryInterval(long j) {
            this.mRetryInterval = j;
            return this;
        }

        public RetryInterceptor build() {
            return new RetryInterceptor(this.mRetryCount, this.mRetryInterval);
        }
    }
}
