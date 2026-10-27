package com.amazon.avod.drm.playready;

import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseResponse {
    private final Map<String, DrmLicensingException> mErrorMap;
    private final String mSoapResponse;

    private PlayReadyLicenseResponse(@Nullable String str, @Nonnull Map<String, DrmLicensingException> map) {
        String str2;
        if (str == null) {
            str2 = null;
        } else {
            try {
                str2 = new String(Base64.decode(str));
            } catch (IOException e) {
                DLog.exceptionf(e, "Base64-decoding licensing server response", new Object[0]);
                throw new RuntimeException("Base64-decoding licensing server response", e);
            }
        }
        this.mSoapResponse = str2;
        this.mErrorMap = map;
    }

    public String getResponseString() {
        return this.mSoapResponse;
    }

    public Map<String, DrmLicensingException> getErrorMap() {
        return this.mErrorMap;
    }

    public String toString() {
        return this.mSoapResponse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Map<String, DrmLicensingException> mErrorMap;
        private String mSoapResponse;

        private Builder() {
            this.mSoapResponse = null;
            this.mErrorMap = Maps.newHashMap();
        }

        public Builder setResponseString(String str) {
            this.mSoapResponse = str;
            return this;
        }

        public Builder addToErrorMap(String str, DrmLicensingException drmLicensingException) {
            if (this.mErrorMap.get(str) == null) {
                this.mErrorMap.put(str, drmLicensingException);
            }
            return this;
        }

        public String getResponseString() {
            return this.mSoapResponse;
        }

        public PlayReadyLicenseResponse build() {
            return new PlayReadyLicenseResponse(this.mSoapResponse, this.mErrorMap);
        }
    }
}
