package com.amazon.avod.drm.widevine;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class WidevineLicenseResponse {
    private final ImmutableList<String> mContentKeys;
    private final int mHdcpEnforcementResolutionPixels;
    private final String mHdcpTestKeyId;
    private final String mHdcpVersion;
    private final String mWidevineLicense;

    private WidevineLicenseResponse(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, @Nullable String str2, int i, @Nullable String str3) {
        this.mWidevineLicense = str;
        this.mContentKeys = immutableList;
        this.mHdcpVersion = str2;
        this.mHdcpEnforcementResolutionPixels = i;
        this.mHdcpTestKeyId = str3;
    }

    @Nonnull
    public String getWidevineLicense() {
        return this.mWidevineLicense;
    }

    public int getHdcpEnforcementResolutionPixels() {
        return this.mHdcpEnforcementResolutionPixels;
    }

    @Nonnull
    public String toString() {
        return this.mWidevineLicense;
    }

    public static Builder newBuilder(@Nonnull String str, @Nonnull ImmutableList<String> immutableList) {
        return new Builder(str, immutableList);
    }

    public static final class Builder {
        private ImmutableList<String> mContentKeys;
        private int mHdcpEnforcementResolutionPixels;
        private String mHdcpTestKeyId;
        private String mHdcpVersion;
        private String mWidevineLicense;

        private Builder(@Nonnull String str, @Nonnull ImmutableList<String> immutableList) {
            this.mWidevineLicense = null;
            this.mContentKeys = null;
            this.mHdcpVersion = null;
            this.mHdcpEnforcementResolutionPixels = -1;
            this.mHdcpTestKeyId = null;
            this.mWidevineLicense = (String) Preconditions.checkNotNull(str, "widevineLicense");
            this.mContentKeys = (ImmutableList) Preconditions.checkNotNull(immutableList, "contentKeys");
        }

        public Builder setHdcpEnforcementResolutionPixels(int i) {
            this.mHdcpEnforcementResolutionPixels = i;
            return this;
        }

        public Builder setHdcpVersion(@Nonnull String str) {
            this.mHdcpVersion = (String) Preconditions.checkNotNull(str, "hdcpVersion");
            return this;
        }

        public Builder setHdcpTestKeyId(@Nullable String str) {
            this.mHdcpTestKeyId = str;
            return this;
        }

        @Nonnull
        public WidevineLicenseResponse build() {
            return new WidevineLicenseResponse(this.mWidevineLicense, this.mContentKeys, this.mHdcpVersion, this.mHdcpEnforcementResolutionPixels, this.mHdcpTestKeyId);
        }
    }
}
