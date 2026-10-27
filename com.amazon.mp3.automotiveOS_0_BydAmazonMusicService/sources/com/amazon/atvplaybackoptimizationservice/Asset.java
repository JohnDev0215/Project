package com.amazon.atvplaybackoptimizationservice;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class Asset {
    public final Optional<String> audioQuality;
    public final Optional<String> bitrateAdaption;
    public final Optional<String> contentId;
    public final Optional<String> drm;
    public final Optional<String> encode;
    public final Optional<String> globalTitleId;
    public final Optional<String> hdrFormat;
    public final Optional<String> requestedAsin;
    public final String streamingTechnology;
    public final Optional<String> titleId;
    public final String videoMaterialType;
    public final Optional<String> videoQuality;

    private Asset(Builder builder) {
        this.bitrateAdaption = Optional.fromNullable(builder.bitrateAdaption);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.videoMaterialType = (String) Preconditions.checkNotNull(builder.videoMaterialType, "Unexpected null field: videoMaterialType");
        this.hdrFormat = Optional.fromNullable(builder.hdrFormat);
        this.contentId = Optional.fromNullable(builder.contentId);
        this.encode = Optional.fromNullable(builder.encode);
        this.audioQuality = Optional.fromNullable(builder.audioQuality);
        this.requestedAsin = Optional.fromNullable(builder.requestedAsin);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.drm = Optional.fromNullable(builder.drm);
        this.streamingTechnology = (String) Preconditions.checkNotNull(builder.streamingTechnology, "Unexpected null field: streamingTechnology");
        this.globalTitleId = Optional.fromNullable(builder.globalTitleId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) obj;
        return Objects.equal(this.bitrateAdaption, asset.bitrateAdaption) && Objects.equal(this.videoQuality, asset.videoQuality) && Objects.equal(this.videoMaterialType, asset.videoMaterialType) && Objects.equal(this.hdrFormat, asset.hdrFormat) && Objects.equal(this.contentId, asset.contentId) && Objects.equal(this.encode, asset.encode) && Objects.equal(this.audioQuality, asset.audioQuality) && Objects.equal(this.requestedAsin, asset.requestedAsin) && Objects.equal(this.titleId, asset.titleId) && Objects.equal(this.drm, asset.drm) && Objects.equal(this.streamingTechnology, asset.streamingTechnology) && Objects.equal(this.globalTitleId, asset.globalTitleId);
    }

    public int hashCode() {
        return Objects.hashCode(this.bitrateAdaption, this.videoQuality, this.videoMaterialType, this.hdrFormat, this.contentId, this.encode, this.audioQuality, this.requestedAsin, this.titleId, this.drm, this.streamingTechnology, this.globalTitleId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("bitrateAdaption", this.bitrateAdaption).add("videoQuality", this.videoQuality).add("videoMaterialType", this.videoMaterialType).add("hdrFormat", this.hdrFormat).add("contentId", this.contentId).add("encode", this.encode).add("audioQuality", this.audioQuality).add("requestedAsin", this.requestedAsin).add("titleId", this.titleId).add("drm", this.drm).add("streamingTechnology", this.streamingTechnology).add("globalTitleId", this.globalTitleId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioQuality;
        public String bitrateAdaption;
        public String contentId;
        public String drm;
        public String encode;
        public String globalTitleId;
        public String hdrFormat;
        public String requestedAsin;
        public String streamingTechnology;
        public String titleId;
        public String videoMaterialType;
        public String videoQuality;

        public Asset build() {
            return new Asset(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Asset> {
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Asset parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Asset:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Asset parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Asset:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Asset parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Asset");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 8;
                            }
                            break;
                        case -1298776554:
                            if (next.equals("encode")) {
                                b = 5;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 10;
                            }
                            break;
                        case -935141303:
                            if (next.equals("audioQuality")) {
                                b = 6;
                            }
                            break;
                        case -614336461:
                            if (next.equals("bitrateAdaption")) {
                                b = 0;
                            }
                            break;
                        case -603068787:
                            if (next.equals("hdrFormat")) {
                                b = 3;
                            }
                            break;
                        case -539491044:
                            if (next.equals("videoMaterialType")) {
                                b = 2;
                            }
                            break;
                        case -407108748:
                            if (next.equals("contentId")) {
                                b = 4;
                            }
                            break;
                        case 99743:
                            if (next.equals("drm")) {
                                b = 9;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 1;
                            }
                            break;
                        case 1596636272:
                            if (next.equals("globalTitleId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1902541157:
                            if (next.equals("requestedAsin")) {
                                b = 7;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaption = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.videoQuality = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.videoMaterialType = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.hdrFormat = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.contentId = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.encode = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.audioQuality = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.requestedAsin = str;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.titleId = str;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.drm = str;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.globalTitleId = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.videoMaterialType, this, "videoMaterialType");
            JsonParsingUtils.checkNotNull(builder.streamingTechnology, this, "streamingTechnology");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Asset parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        JsonToken currentToken = jsonParser.getCurrentToken();
                        byte b = -1;
                        try {
                            switch (currentName.hashCode()) {
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 8;
                                    }
                                    break;
                                case -1298776554:
                                    if (currentName.equals("encode")) {
                                        b = 5;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 10;
                                    }
                                    break;
                                case -935141303:
                                    if (currentName.equals("audioQuality")) {
                                        b = 6;
                                    }
                                    break;
                                case -614336461:
                                    if (currentName.equals("bitrateAdaption")) {
                                        b = 0;
                                    }
                                    break;
                                case -603068787:
                                    if (currentName.equals("hdrFormat")) {
                                        b = 3;
                                    }
                                    break;
                                case -539491044:
                                    if (currentName.equals("videoMaterialType")) {
                                        b = 2;
                                    }
                                    break;
                                case -407108748:
                                    if (currentName.equals("contentId")) {
                                        b = 4;
                                    }
                                    break;
                                case 99743:
                                    if (currentName.equals("drm")) {
                                        b = 9;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 1;
                                    }
                                    break;
                                case 1596636272:
                                    if (currentName.equals("globalTitleId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1902541157:
                                    if (currentName.equals("requestedAsin")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaption = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.videoMaterialType = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.hdrFormat = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.contentId = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.encode = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.audioQuality = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.requestedAsin = str;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.titleId = str;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.drm = str;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnology = str;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.globalTitleId = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.videoMaterialType, this, "videoMaterialType");
                    JsonParsingUtils.checkNotNull(builder.streamingTechnology, this, "streamingTechnology");
                    return builder.build();
                }
            }
        }
    }
}
