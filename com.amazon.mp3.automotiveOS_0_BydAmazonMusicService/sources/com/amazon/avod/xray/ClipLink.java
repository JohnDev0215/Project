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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ClipLink {
    public final long duration;
    public final Optional<String> id;
    public final Optional<String> nextUpImageUrl;
    public final long timecode;
    public final Optional<String> title;

    private ClipLink(Builder builder) {
        this.duration = builder.duration;
        this.timecode = builder.timecode;
        this.id = Optional.fromNullable(builder.id);
        this.title = Optional.fromNullable(builder.title);
        this.nextUpImageUrl = Optional.fromNullable(builder.nextUpImageUrl);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClipLink)) {
            return false;
        }
        ClipLink clipLink = (ClipLink) obj;
        return Objects.equal(Long.valueOf(this.duration), Long.valueOf(clipLink.duration)) && Objects.equal(Long.valueOf(this.timecode), Long.valueOf(clipLink.timecode)) && Objects.equal(this.id, clipLink.id) && Objects.equal(this.title, clipLink.title) && Objects.equal(this.nextUpImageUrl, clipLink.nextUpImageUrl);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.duration), Long.valueOf(this.timecode), this.id, this.title, this.nextUpImageUrl);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("duration", this.duration).add("timecode", this.timecode).add("id", this.id).add("title", this.title).add("nextUpImageUrl", this.nextUpImageUrl).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public long duration;
        public String id;
        public String nextUpImageUrl;
        public long timecode;
        public String title;

        public ClipLink build() {
            return new ClipLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ClipLink> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ClipLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ClipLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ClipLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ClipLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ClipLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ClipLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2076913094:
                            if (next.equals("timecode")) {
                                b = 1;
                            }
                            break;
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 0;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 2;
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
                    }
                    if (b == 0) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field duration can't be null");
                        }
                        builder.duration = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    } else if (b != 1) {
                        String str = null;
                        if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str;
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.title = str;
                        } else if (b == 4) {
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.nextUpImageUrl = str;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field timecode can't be null");
                        }
                        builder.timecode = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ClipLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.duration), this, "duration");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.timecode), this, "timecode");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ClipLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2076913094:
                                    if (currentName.equals("timecode")) {
                                        b = 1;
                                    }
                                    break;
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = 0;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 2;
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
                            }
                            if (b == 0) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field duration can't be null");
                                }
                                builder.duration = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else if (b != 1) {
                                String str = null;
                                if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str;
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.title = str;
                                } else if (b == 4) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.nextUpImageUrl = str;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field timecode can't be null");
                                }
                                builder.timecode = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ClipLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.duration), this, "duration");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.timecode), this, "timecode");
                    return builder.build();
                }
            }
        }
    }
}
