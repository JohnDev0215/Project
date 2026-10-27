package com.amazon.avod.ads.http;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class HttpParameters {
    private final int mBeaconTimeout;
    private final int mBeaconTries;
    private final boolean mFollowRedirects;
    private final int mRequestTimeout;
    private final int mRequestTries;
    private final String mUserAgent;

    private HttpParameters(Builder builder) {
        this.mFollowRedirects = builder.mFollowRedirects;
        this.mUserAgent = (String) Preconditions.checkNotNull(builder.mUserAgent);
        this.mBeaconTimeout = ((Integer) Preconditions.checkNotNull(builder.mBeaconTimeout)).intValue();
        this.mBeaconTries = ((Integer) Preconditions.checkNotNull(builder.mBeaconTries)).intValue();
        this.mRequestTimeout = ((Integer) Preconditions.checkNotNull(builder.mRequestTimeout)).intValue();
        this.mRequestTries = ((Integer) Preconditions.checkNotNull(builder.mRequestTries)).intValue();
    }

    public boolean getFollowRedirects() {
        return this.mFollowRedirects;
    }

    @Nonnull
    public String getUserAgent() {
        return this.mUserAgent;
    }

    public int getBeaconTimeout() {
        return this.mBeaconTimeout;
    }

    public int getBeaconRetries() {
        return this.mBeaconTries;
    }

    public int getRequestTimeout() {
        return this.mRequestTimeout;
    }

    public int getRequestRetries() {
        return this.mRequestTries;
    }

    public static class Builder {
        private boolean mFollowRedirects = true;
        private String mUserAgent = "AivAndroidPlayer/1.0 (AndroidDevice)";
        private Integer mBeaconTimeout = 10000;
        private Integer mBeaconTries = 3;
        private Integer mRequestTimeout = Integer.valueOf(PathInterpolatorCompat.MAX_NUM_POINTS);
        private Integer mRequestTries = 3;

        public Builder followRedirects(boolean z) {
            this.mFollowRedirects = z;
            return this;
        }

        public Builder withBeaconTimeout(int i) {
            this.mBeaconTimeout = Integer.valueOf(i);
            return this;
        }

        public Builder withRequestTimeout(int i) {
            this.mRequestTimeout = Integer.valueOf(i);
            return this;
        }

        public Builder withBeaconRetryCount(int i) {
            this.mBeaconTries = Integer.valueOf(i);
            return this;
        }

        public Builder withRequestRetryCount(int i) {
            this.mRequestTries = Integer.valueOf(i);
            return this;
        }

        public Builder withUserAgent(String str) {
            this.mUserAgent = str;
            return this;
        }

        public HttpParameters build() {
            return new HttpParameters(this);
        }
    }
}
