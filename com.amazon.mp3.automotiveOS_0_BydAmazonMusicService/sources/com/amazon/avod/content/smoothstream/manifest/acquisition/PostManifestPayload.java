package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SerializationUtils;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class PostManifestPayload {

    @Nullable
    private final String mConsumptionId;

    @Nonnull
    private final String mDeviceId;

    @Nullable
    private String mException = null;

    @Nonnull
    private final String mGZippedBase64MPD;

    @Nullable
    private Map<String, List<String>> mHeaders;
    private final long mMsSinceEpoch;

    @Nonnull
    private final String mURL;

    private PostManifestPayload(long j, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nonnull String str4, @Nullable Map<String, List<String>> map) {
        this.mMsSinceEpoch = j;
        this.mGZippedBase64MPD = (String) Preconditions.checkNotNull(str, "gzippedBase64MPD");
        this.mConsumptionId = str2;
        this.mDeviceId = (String) Preconditions.checkNotNull(str3, Splash.PARAMS_DEVICE_ID);
        this.mURL = (String) Preconditions.checkNotNull(str4, ImagesContract.URL);
        this.mHeaders = map;
    }

    @JsonProperty
    public long getMsSinceEpoch() {
        return this.mMsSinceEpoch;
    }

    @JsonProperty
    @Nonnull
    public String getGZippedBase64MPD() {
        return this.mGZippedBase64MPD;
    }

    @JsonProperty
    @Nonnull
    public String getConsumptionId() {
        String str = this.mConsumptionId;
        return str == null ? "null" : str;
    }

    @JsonProperty
    @Nonnull
    public String getDeviceId() {
        return this.mDeviceId;
    }

    @JsonProperty
    @Nonnull
    public String getURL() {
        return this.mURL;
    }

    @JsonProperty
    @Nullable
    public String getException() {
        return this.mException;
    }

    @JsonProperty
    @Nullable
    public Map<String, List<String>> getHeaders() {
        return this.mHeaders;
    }

    @Nonnull
    public String toJSON() {
        try {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            DLog.errorf("Failed to JSON serialize manifest: " + e.getMessage());
            return "";
        }
    }

    @Nonnull
    public static PostManifestPayload generate(long j, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nonnull String str4, @Nullable Map<String, List<String>> map) {
        String strEncode;
        try {
            strEncode = BaseEncoding.base64().encode(SerializationUtils.gzipStringToBytes(Charset.forName(ParserUtil.UTF_8), str));
        } catch (IOException unused) {
            DLog.errorf("Failed to compress manifest, sending empty string instead");
            strEncode = "";
        }
        return new PostManifestPayload(j, strEncode, str2, str3, str4, map);
    }

    public void setException(@Nonnull Exception exc) {
        Preconditions.checkNotNull(exc, "exceptionTriggeringUpload");
        this.mException = exc.getMessage();
    }
}
