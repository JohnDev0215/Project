package com.amazon.avod.xray;

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

/* JADX INFO: loaded from: classes2.dex */
public class ReplayBoothLink {
    public final String header;
    public final String id;
    public final Optional<String> miniXrayTabId;
    public final Optional<String> miniXrayUrlMap;
    public final Optional<String> nextUpImageUrl;
    public final String title;
    public final String videoUrlMap;

    private ReplayBoothLink(Builder builder) {
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
        this.miniXrayUrlMap = Optional.fromNullable(builder.miniXrayUrlMap);
        this.miniXrayTabId = Optional.fromNullable(builder.miniXrayTabId);
        this.title = (String) Preconditions.checkNotNull(builder.title, "Unexpected null field: title");
        this.nextUpImageUrl = Optional.fromNullable(builder.nextUpImageUrl);
        this.header = (String) Preconditions.checkNotNull(builder.header, "Unexpected null field: header");
        this.videoUrlMap = (String) Preconditions.checkNotNull(builder.videoUrlMap, "Unexpected null field: videoUrlMap");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReplayBoothLink)) {
            return false;
        }
        ReplayBoothLink replayBoothLink = (ReplayBoothLink) obj;
        return Objects.equal(this.id, replayBoothLink.id) && Objects.equal(this.miniXrayUrlMap, replayBoothLink.miniXrayUrlMap) && Objects.equal(this.miniXrayTabId, replayBoothLink.miniXrayTabId) && Objects.equal(this.title, replayBoothLink.title) && Objects.equal(this.nextUpImageUrl, replayBoothLink.nextUpImageUrl) && Objects.equal(this.header, replayBoothLink.header) && Objects.equal(this.videoUrlMap, replayBoothLink.videoUrlMap);
    }

    public int hashCode() {
        return Objects.hashCode(this.id, this.miniXrayUrlMap, this.miniXrayTabId, this.title, this.nextUpImageUrl, this.header, this.videoUrlMap);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", this.id).add("miniXrayUrlMap", this.miniXrayUrlMap).add("miniXrayTabId", this.miniXrayTabId).add("title", this.title).add("nextUpImageUrl", this.nextUpImageUrl).add("header", this.header).add("videoUrlMap", this.videoUrlMap).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String header;
        public String id;
        public String miniXrayTabId;
        public String miniXrayUrlMap;
        public String nextUpImageUrl;
        public String title;
        public String videoUrlMap;

        public ReplayBoothLink build() {
            return new ReplayBoothLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ReplayBoothLink> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ReplayBoothLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ReplayBoothLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ReplayBoothLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ReplayBoothLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ReplayBoothLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "ReplayBoothLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1221270899:
                            if (next.equals("header")) {
                                b = 5;
                            }
                            break;
                        case -94695577:
                            if (next.equals("miniXrayTabId")) {
                                b = 2;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 0;
                            }
                            break;
                        case 81108258:
                            if (next.equals("nextUpImageUrl")) {
                                b = 4;
                            }
                            break;
                        case 110371416:
                            if (next.equals("title")) {
                                b = 3;
                            }
                            break;
                        case 1098134984:
                            if (next.equals("videoUrlMap")) {
                                b = 6;
                            }
                            break;
                        case 1404035190:
                            if (next.equals("miniXrayUrlMap")) {
                                b = 1;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ReplayBoothLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.miniXrayUrlMap = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.miniXrayTabId = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.title = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.nextUpImageUrl = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.header = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.videoUrlMap = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ReplayBoothLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.title, this, "title");
            JsonParsingUtils.checkNotNull(builder.header, this, "header");
            JsonParsingUtils.checkNotNull(builder.videoUrlMap, this, "videoUrlMap");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ReplayBoothLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1221270899:
                                    if (currentName.equals("header")) {
                                        b = 5;
                                    }
                                    break;
                                case -94695577:
                                    if (currentName.equals("miniXrayTabId")) {
                                        b = 2;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 0;
                                    }
                                    break;
                                case 81108258:
                                    if (currentName.equals("nextUpImageUrl")) {
                                        b = 4;
                                    }
                                    break;
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 3;
                                    }
                                    break;
                                case 1098134984:
                                    if (currentName.equals("videoUrlMap")) {
                                        b = 6;
                                    }
                                    break;
                                case 1404035190:
                                    if (currentName.equals("miniXrayUrlMap")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.miniXrayUrlMap = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.miniXrayTabId = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.title = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.nextUpImageUrl = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.header = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.videoUrlMap = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ReplayBoothLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ReplayBoothLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.title, this, "title");
                    JsonParsingUtils.checkNotNull(builder.header, this, "header");
                    JsonParsingUtils.checkNotNull(builder.videoUrlMap, this, "videoUrlMap");
                    return builder.build();
                }
            }
        }
    }
}
