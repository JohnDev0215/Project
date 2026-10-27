package com.amazon.atvplaybackresource;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleUrlLanguageOption {
    public final Optional<String> displayName;
    public final Optional<String> format;
    public final Optional<String> index;
    public final Optional<String> languageCode;
    public final Optional<String> subtype;
    public final Optional<String> timedTextTrackId;
    public final Optional<String> trackGroupId;
    public final Optional<String> type;
    public final Optional<String> url;
    public final Optional<String> videoMaterialType;

    private SubtitleUrlLanguageOption(Builder builder) {
        this.index = Optional.fromNullable(builder.index);
        this.displayName = Optional.fromNullable(builder.displayName);
        this.format = Optional.fromNullable(builder.format);
        this.languageCode = Optional.fromNullable(builder.languageCode);
        this.videoMaterialType = Optional.fromNullable(builder.videoMaterialType);
        this.trackGroupId = Optional.fromNullable(builder.trackGroupId);
        this.type = Optional.fromNullable(builder.type);
        this.url = Optional.fromNullable(builder.url);
        this.timedTextTrackId = Optional.fromNullable(builder.timedTextTrackId);
        this.subtype = Optional.fromNullable(builder.subtype);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubtitleUrlLanguageOption)) {
            return false;
        }
        SubtitleUrlLanguageOption subtitleUrlLanguageOption = (SubtitleUrlLanguageOption) obj;
        return Objects.equal(this.index, subtitleUrlLanguageOption.index) && Objects.equal(this.displayName, subtitleUrlLanguageOption.displayName) && Objects.equal(this.format, subtitleUrlLanguageOption.format) && Objects.equal(this.languageCode, subtitleUrlLanguageOption.languageCode) && Objects.equal(this.videoMaterialType, subtitleUrlLanguageOption.videoMaterialType) && Objects.equal(this.trackGroupId, subtitleUrlLanguageOption.trackGroupId) && Objects.equal(this.type, subtitleUrlLanguageOption.type) && Objects.equal(this.url, subtitleUrlLanguageOption.url) && Objects.equal(this.timedTextTrackId, subtitleUrlLanguageOption.timedTextTrackId) && Objects.equal(this.subtype, subtitleUrlLanguageOption.subtype);
    }

    public int hashCode() {
        return Objects.hashCode(this.index, this.displayName, this.format, this.languageCode, this.videoMaterialType, this.trackGroupId, this.type, this.url, this.timedTextTrackId, this.subtype);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("index", this.index).add("displayName", this.displayName).add("format", this.format).add("languageCode", this.languageCode).add("videoMaterialType", this.videoMaterialType).add("trackGroupId", this.trackGroupId).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add(ImagesContract.URL, this.url).add("timedTextTrackId", this.timedTextTrackId).add("subtype", this.subtype).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String displayName;
        public String format;
        public String index;
        public String languageCode;
        public String subtype;
        public String timedTextTrackId;
        public String trackGroupId;
        public String type;
        public String url;
        public String videoMaterialType;

        public SubtitleUrlLanguageOption build() {
            return new SubtitleUrlLanguageOption(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SubtitleUrlLanguageOption> {
        private final SimpleParsers.StringParser mstringTargetParser;
        private final SimpleParsers.StringParser msubtitleDisplayNameParser;
        private final SimpleParsers.StringParser msubtitleLanguageCodeParser;
        private final SimpleParsers.StringParser murlParser;
        private final SimpleParsers.StringParser mvideoMaterialTypeInternalParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mstringTargetParser = stringParser;
            this.msubtitleDisplayNameParser = stringParser;
            this.msubtitleLanguageCodeParser = stringParser;
            this.mvideoMaterialTypeInternalParser = stringParser;
            this.murlParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SubtitleUrlLanguageOption parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubtitleUrlLanguageOption:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SubtitleUrlLanguageOption parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubtitleUrlLanguageOption:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SubtitleUrlLanguageOption parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SubtitleUrlLanguageOption");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2092349083:
                            if (next.equals("languageCode")) {
                                b = 3;
                            }
                            break;
                        case -1871378385:
                            if (next.equals("trackGroupId")) {
                                b = 5;
                            }
                            break;
                        case -1867567750:
                            if (next.equals("subtype")) {
                                b = 9;
                            }
                            break;
                        case -1268779017:
                            if (next.equals("format")) {
                                b = 2;
                            }
                            break;
                        case -539491044:
                            if (next.equals("videoMaterialType")) {
                                b = 4;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 7;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 6;
                            }
                            break;
                        case 100346066:
                            if (next.equals("index")) {
                                b = 0;
                            }
                            break;
                        case 1230385698:
                            if (next.equals("timedTextTrackId")) {
                                b = 8;
                            }
                            break;
                        case 1714148973:
                            if (next.equals("displayName")) {
                                b = 1;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubtitleUrlLanguageOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.index = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.msubtitleDisplayNameParser.parse(jsonNode2);
                        }
                        builder.displayName = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.format = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.msubtitleLanguageCodeParser.parse(jsonNode2);
                        }
                        builder.languageCode = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mvideoMaterialTypeInternalParser.parse(jsonNode2);
                        }
                        builder.videoMaterialType = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.trackGroupId = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.type = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str = this.murlParser.parse(jsonNode2);
                        }
                        builder.url = str;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.timedTextTrackId = str;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.subtype = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubtitleUrlLanguageOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SubtitleUrlLanguageOption parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2092349083:
                                    if (currentName.equals("languageCode")) {
                                        b = 3;
                                    }
                                    break;
                                case -1871378385:
                                    if (currentName.equals("trackGroupId")) {
                                        b = 5;
                                    }
                                    break;
                                case -1867567750:
                                    if (currentName.equals("subtype")) {
                                        b = 9;
                                    }
                                    break;
                                case -1268779017:
                                    if (currentName.equals("format")) {
                                        b = 2;
                                    }
                                    break;
                                case -539491044:
                                    if (currentName.equals("videoMaterialType")) {
                                        b = 4;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 7;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 6;
                                    }
                                    break;
                                case 100346066:
                                    if (currentName.equals("index")) {
                                        b = 0;
                                    }
                                    break;
                                case 1230385698:
                                    if (currentName.equals("timedTextTrackId")) {
                                        b = 8;
                                    }
                                    break;
                                case 1714148973:
                                    if (currentName.equals("displayName")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.index = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.msubtitleDisplayNameParser.parse(jsonParser);
                                    }
                                    builder.displayName = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.format = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.msubtitleLanguageCodeParser.parse(jsonParser);
                                    }
                                    builder.languageCode = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mvideoMaterialTypeInternalParser.parse(jsonParser);
                                    }
                                    builder.videoMaterialType = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.trackGroupId = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.type = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.murlParser.parse(jsonParser);
                                    }
                                    builder.url = str;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.timedTextTrackId = str;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.subtype = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubtitleUrlLanguageOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubtitleUrlLanguageOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
