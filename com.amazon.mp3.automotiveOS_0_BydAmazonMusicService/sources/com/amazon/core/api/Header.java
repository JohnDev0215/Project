package com.amazon.core.api;

import CSRFInterface.v1_0.CSRFHeaderElement;
import VideoPlaybackInterface.v1_0.ImmutableVideoPlaybackHeaderElement;
import VideoPlaybackInterface.v1_0.VideoPlaybackHeaderElement;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
@JsonDeserialize(using = HeaderDeserializer.class)
@JsonSerialize(using = HeaderSerializer.class)
public final class Header {
    private final Map<String, String> values;

    public static final Builder builder() {
        return new Builder();
    }

    public String sessionId() {
        return this.values.get("session-id");
    }

    public String userAgent() {
        return this.values.get("user-agent");
    }

    public String deviceId() {
        return this.values.get("device-id");
    }

    public String deviceModel() {
        return this.values.get("device-model");
    }

    @Nullable
    public String deviceTypeId() {
        return this.values.get("device-type-id");
    }

    @Nullable
    public String hardwareDeviceTypeId() {
        return this.values.get("hardware-device-type-id");
    }

    public String deviceFamily() {
        return this.values.get("device-family");
    }

    @Nullable
    public String deviceManufacturer() {
        return this.values.get("device-manufacturer");
    }

    public String deviceTimeZone() {
        return this.values.get("device-time-zone");
    }

    public String deviceLanguage() {
        return this.values.get("device-language");
    }

    public String currencyOfPreference() {
        return this.values.get("currency-of-preference");
    }

    public String deviceWidth() {
        return this.values.get("device-width");
    }

    public String deviceHeight() {
        return this.values.get("device-height");
    }

    @Nullable
    public String deviceScale() {
        return this.values.get("device-scale");
    }

    public String osVersion() {
        return this.values.get("os-version");
    }

    public String applicationVersion() {
        return this.values.get("application-version");
    }

    public String referer() {
        return this.values.get("referer");
    }

    public String pageUrl() {
        return this.values.get("page-url");
    }

    public String affiliateTags() {
        return this.values.get("affiliate-tags");
    }

    public String refMarker() {
        return this.values.get("ref-marker");
    }

    public String requestId() {
        return this.values.get("request-id");
    }

    public long timestamp() {
        String str = this.values.get(TetheredMessageKey.timestamp);
        if (StringUtils.isNotEmpty(str)) {
            return Long.parseLong(str);
        }
        throw new RuntimeException("Header#timestamp is empty or null");
    }

    public String authentication() {
        return this.values.get("authentication");
    }

    public List<String> weblabIdOverrides() {
        String str = this.values.get("weblab-id-overrides");
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split("&"));
    }

    public List<String> featureFlags() {
        String str = this.values.get("feature-flags");
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split(","));
    }

    @Nullable
    public AmazonDomain domain() {
        return AmazonDomain.lookupByValue(this.values.get("domain"));
    }

    @Nullable
    public CSRFHeader csrf() {
        String str = this.values.get("csrf");
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            CSRFHeaderElement cSRFHeaderElement = (CSRFHeaderElement) Mappers.mapper().readValue(str, CSRFHeaderElement.class);
            return ImmutableCSRFHeader.builder().token(cSRFHeaderElement.token()).rndNonce(cSRFHeaderElement.rndNonce()).timestamp(cSRFHeaderElement.timestamp()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public VideoPlaybackHeaderElement videoPlaybackHeader() {
        String str = this.values.get("video-player-token");
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            VideoPlaybackHeaderElement videoPlaybackHeaderElement = (VideoPlaybackHeaderElement) Mappers.mapper().readValue(str, VideoPlaybackHeaderElement.class);
            return ImmutableVideoPlaybackHeaderElement.builder().token(videoPlaybackHeaderElement.token()).expirationMS(videoPlaybackHeaderElement.expirationMS()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Header{values=" + this.values + "}";
    }

    public static final class Builder {
        private final Map<String, String> values;

        public final Builder sessionId(String str) {
            this.values.put("session-id", str);
            return this;
        }

        public final Builder userAgent(String str) {
            this.values.put("user-agent", str);
            return this;
        }

        public final Builder deviceId(String str) {
            this.values.put("device-id", str);
            return this;
        }

        public final Builder deviceTypeId(String str) {
            this.values.put("device-type-id", str);
            return this;
        }

        public final Builder hardwareDeviceTypeId(String str) {
            this.values.put("hardware-device-type-id", str);
            return this;
        }

        public final Builder deviceFamily(String str) {
            this.values.put("device-family", str);
            return this;
        }

        public final Builder deviceModel(String str) {
            this.values.put("device-model", str);
            return this;
        }

        public final Builder deviceManufacturer(String str) {
            this.values.put("device-manufacturer", str);
            return this;
        }

        public final Builder deviceTimeZone(String str) {
            this.values.put("device-time-zone", str);
            return this;
        }

        public final Builder deviceLanguage(String str) {
            this.values.put("device-language", str);
            return this;
        }

        public final Builder currencyOfPreference(String str) {
            this.values.put("currency-of-preference", str);
            return this;
        }

        public final Builder deviceWidth(String str) {
            this.values.put("device-width", str);
            return this;
        }

        public final Builder deviceHeight(String str) {
            this.values.put("device-height", str);
            return this;
        }

        public final Builder deviceScale(String str) {
            this.values.put("device-scale", str);
            return this;
        }

        public final Builder osVersion(String str) {
            this.values.put("os-version", str);
            return this;
        }

        public final Builder applicationVersion(String str) {
            this.values.put("application-version", str);
            return this;
        }

        public final Builder referer(String str) {
            this.values.put("referer", str);
            return this;
        }

        public final Builder pageUrl(String str) {
            this.values.put("page-url", str);
            return this;
        }

        public final Builder affiliateTags(String str) {
            this.values.put("affiliate-tags", str);
            return this;
        }

        public final Builder refMarker(String str) {
            this.values.put("ref-marker", str);
            return this;
        }

        public final Builder requestId(String str) {
            this.values.put("request-id", str);
            return this;
        }

        public final Builder timestamp(long j) {
            this.values.put(TetheredMessageKey.timestamp, Long.toString(j));
            return this;
        }

        public final Builder authentication(String str) {
            this.values.put("authentication", str);
            return this;
        }

        public final Builder videoPlayerToken(String str) {
            this.values.put("video-player-token", str);
            return this;
        }

        public final Builder csrf(String str) {
            this.values.put("csrf", str);
            return this;
        }

        public final Builder domain(String str) {
            this.values.put("domain", str);
            return this;
        }

        public final Builder weblabIdOverrides(String str) {
            this.values.put("weblab-id-overrides", str);
            return this;
        }

        public final Builder featureFlags(String str) {
            this.values.put("feature-flags", str);
            return this;
        }

        public Header build() {
            return new Header(this.values);
        }

        public Header buildAndValidate() {
            Header headerBuild = build();
            Header.validate(headerBuild);
            return headerBuild;
        }

        private Builder() {
            this.values = new HashMap();
        }
    }

    Header(Map<String, String> map) {
        this.values = map;
    }

    public static void validate(Header header) {
        if (StringUtils.isEmpty(header.requestId())) {
            throw new IllegalArgumentException("Empty Request ID");
        }
        if (StringUtils.isEmpty(header.deviceId())) {
            throw new IllegalArgumentException("Empty Device ID");
        }
        if (StringUtils.isEmpty(header.deviceFamily())) {
            throw new IllegalArgumentException("Empty Device Family");
        }
    }

    static final class HeaderSerializer extends StdSerializer<Header> {
        public HeaderSerializer() {
            this(null);
        }

        public HeaderSerializer(Class<Header> cls) {
            super(cls);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Header header, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            for (Map.Entry entry : header.values.entrySet()) {
                jsonGenerator.writeStringField("x-amzn-" + ((String) entry.getKey()), (String) entry.getValue());
            }
            jsonGenerator.writeEndObject();
        }
    }

    static final class HeaderDeserializer extends StdDeserializer<Header> {
        public HeaderDeserializer() {
            this(null);
        }

        public HeaderDeserializer(Class<Header> cls) {
            super(cls);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Header deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Iterator<Map.Entry<String, JsonNode>> itFields = ((JsonNode) jsonParser.getCodec().readTree(jsonParser)).fields();
            HashMap map = new HashMap();
            while (itFields.hasNext()) {
                Map.Entry<String, JsonNode> next = itFields.next();
                if (next.getKey().startsWith("x-amzn-")) {
                    map.put(next.getKey().substring(7), next.getValue().textValue());
                }
            }
            return new Header(map);
        }
    }
}
