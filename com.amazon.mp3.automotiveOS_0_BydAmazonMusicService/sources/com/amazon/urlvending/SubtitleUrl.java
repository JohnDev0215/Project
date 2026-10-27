package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.urlvending.types.ContentType;
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

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleUrl {
    public final Optional<ContentType> contentType;
    public final Optional<String> displayName;
    public final Optional<String> format;
    public final Optional<String> index;
    public final Optional<String> languageCode;
    public final Optional<String> subtype;
    public final Optional<String> timedTextTrackId;
    public final Optional<String> trackGroupId;
    public final Optional<String> type;
    public final Optional<String> url;

    private SubtitleUrl(Builder builder) {
        this.url = Optional.fromNullable(builder.url);
        this.displayName = Optional.fromNullable(builder.displayName);
        this.contentType = Optional.fromNullable(builder.contentType);
        this.format = Optional.fromNullable(builder.format);
        this.timedTextTrackId = Optional.fromNullable(builder.timedTextTrackId);
        this.subtype = Optional.fromNullable(builder.subtype);
        this.trackGroupId = Optional.fromNullable(builder.trackGroupId);
        this.languageCode = Optional.fromNullable(builder.languageCode);
        this.type = Optional.fromNullable(builder.type);
        this.index = Optional.fromNullable(builder.index);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubtitleUrl)) {
            return false;
        }
        SubtitleUrl subtitleUrl = (SubtitleUrl) obj;
        return Objects.equal(this.url, subtitleUrl.url) && Objects.equal(this.displayName, subtitleUrl.displayName) && Objects.equal(this.contentType, subtitleUrl.contentType) && Objects.equal(this.format, subtitleUrl.format) && Objects.equal(this.timedTextTrackId, subtitleUrl.timedTextTrackId) && Objects.equal(this.subtype, subtitleUrl.subtype) && Objects.equal(this.trackGroupId, subtitleUrl.trackGroupId) && Objects.equal(this.languageCode, subtitleUrl.languageCode) && Objects.equal(this.type, subtitleUrl.type) && Objects.equal(this.index, subtitleUrl.index);
    }

    public int hashCode() {
        return Objects.hashCode(this.url, this.displayName, this.contentType, this.format, this.timedTextTrackId, this.subtype, this.trackGroupId, this.languageCode, this.type, this.index);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(ImagesContract.URL, this.url).add("displayName", this.displayName).add("contentType", this.contentType).add("format", this.format).add("timedTextTrackId", this.timedTextTrackId).add("subtype", this.subtype).add("trackGroupId", this.trackGroupId).add("languageCode", this.languageCode).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("index", this.index).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ContentType contentType;
        public String displayName;
        public String format;
        public String index;
        public String languageCode;
        public String subtype;
        public String timedTextTrackId;
        public String trackGroupId;
        public String type;
        public String url;

        public SubtitleUrl build() {
            return new SubtitleUrl(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SubtitleUrl> {
        private final EnumParser<ContentType> mContentTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mContentTypeParser = EnumParser.newParser(ContentType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SubtitleUrl parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubtitleUrl:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SubtitleUrl parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubtitleUrl:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SubtitleUrl parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            ContentType contentType;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SubtitleUrl");
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
                                b = 7;
                            }
                            break;
                        case -1871378385:
                            if (next.equals("trackGroupId")) {
                                b = 6;
                            }
                            break;
                        case -1867567750:
                            if (next.equals("subtype")) {
                                b = 5;
                            }
                            break;
                        case -1268779017:
                            if (next.equals("format")) {
                                b = 3;
                            }
                            break;
                        case -389131437:
                            if (next.equals("contentType")) {
                                b = 2;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 0;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 8;
                            }
                            break;
                        case 100346066:
                            if (next.equals("index")) {
                                b = 9;
                            }
                            break;
                        case 1230385698:
                            if (next.equals("timedTextTrackId")) {
                                b = 4;
                            }
                            break;
                        case 1714148973:
                            if (next.equals("displayName")) {
                                b = 1;
                            }
                            break;
                    }
                    str = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    contentType = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubtitleUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.displayName = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            contentType = (ContentType) this.mContentTypeParser.parse(jsonNode2);
                        }
                        builder.contentType = contentType;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.format = str3;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.timedTextTrackId = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtype = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.trackGroupId = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.languageCode = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.index = str9;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubtitleUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SubtitleUrl parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 7;
                                    }
                                    break;
                                case -1871378385:
                                    if (currentName.equals("trackGroupId")) {
                                        b = 6;
                                    }
                                    break;
                                case -1867567750:
                                    if (currentName.equals("subtype")) {
                                        b = 5;
                                    }
                                    break;
                                case -1268779017:
                                    if (currentName.equals("format")) {
                                        b = 3;
                                    }
                                    break;
                                case -389131437:
                                    if (currentName.equals("contentType")) {
                                        b = 2;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 0;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 8;
                                    }
                                    break;
                                case 100346066:
                                    if (currentName.equals("index")) {
                                        b = 9;
                                    }
                                    break;
                                case 1230385698:
                                    if (currentName.equals("timedTextTrackId")) {
                                        b = 4;
                                    }
                                    break;
                                case 1714148973:
                                    if (currentName.equals("displayName")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            ContentType contentType = null;
                            String str9 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.url = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.displayName = str9;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        contentType = (ContentType) this.mContentTypeParser.parse(jsonParser);
                                    }
                                    builder.contentType = contentType;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.format = str8;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.timedTextTrackId = str7;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtype = str6;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.trackGroupId = str5;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.languageCode = str4;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str3;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.index = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubtitleUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubtitleUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
