package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.LinearAssetUrl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class LinearAsset {
    public final Optional<Long> duration;
    public final Optional<String> playbackId;
    public final Optional<Integer> sequence;
    public final boolean stitched;
    public final Optional<String> streamingTechnology;
    public final Optional<ImmutableList<LinearAssetUrl>> urls;

    private LinearAsset(Builder builder) {
        this.duration = Optional.fromNullable(builder.duration);
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
        this.playbackId = Optional.fromNullable(builder.playbackId);
        this.sequence = Optional.fromNullable(builder.sequence);
        this.stitched = builder.stitched;
        this.urls = Optional.fromNullable(builder.urls);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearAsset)) {
            return false;
        }
        LinearAsset linearAsset = (LinearAsset) obj;
        return Objects.equal(this.duration, linearAsset.duration) && Objects.equal(this.streamingTechnology, linearAsset.streamingTechnology) && Objects.equal(this.playbackId, linearAsset.playbackId) && Objects.equal(this.sequence, linearAsset.sequence) && Objects.equal(Boolean.valueOf(this.stitched), Boolean.valueOf(linearAsset.stitched)) && Objects.equal(this.urls, linearAsset.urls);
    }

    public int hashCode() {
        return Objects.hashCode(this.duration, this.streamingTechnology, this.playbackId, this.sequence, Boolean.valueOf(this.stitched), this.urls);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("duration", this.duration).add("streamingTechnology", this.streamingTechnology).add("playbackId", this.playbackId).add("sequence", this.sequence).add("stitched", this.stitched).add("urls", this.urls).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long duration;
        public String playbackId;
        public Integer sequence;
        public boolean stitched;
        public String streamingTechnology;
        public ImmutableList<LinearAssetUrl> urls;

        public LinearAsset build() {
            return new LinearAsset(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearAsset> {
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final ListParser<LinearAssetUrl> mLinearAssetUrlListParser;
        private final SimpleParsers.LongParser mLongParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLinearAssetUrlListParser = ListParser.newParser(new LinearAssetUrl.Parser(objectMapper));
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearAsset parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearAsset:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearAsset parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearAsset:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearAsset parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearAsset");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 0;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 1;
                            }
                            break;
                        case 3598564:
                            if (next.equals("urls")) {
                                b = 5;
                            }
                            break;
                        case 1349547969:
                            if (next.equals("sequence")) {
                                b = 3;
                            }
                            break;
                        case 1547179280:
                            if (next.equals("stitched")) {
                                b = 4;
                            }
                            break;
                        case 1994704022:
                            if (next.equals("playbackId")) {
                                b = 2;
                            }
                            break;
                    }
                    ImmutableList<LinearAssetUrl> immutableList = null;
                    Long l = null;
                    String str = null;
                    String str2 = null;
                    Integer num = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.duration = l;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playbackId = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.sequence = num;
                    } else if (b == 4) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field stitched can't be null");
                        }
                        builder.stitched = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mLinearAssetUrlListParser.parse(jsonNode2);
                        }
                        builder.urls = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearAsset parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = 0;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 1;
                                    }
                                    break;
                                case 3598564:
                                    if (currentName.equals("urls")) {
                                        b = 5;
                                    }
                                    break;
                                case 1349547969:
                                    if (currentName.equals("sequence")) {
                                        b = 3;
                                    }
                                    break;
                                case 1547179280:
                                    if (currentName.equals("stitched")) {
                                        b = 4;
                                    }
                                    break;
                                case 1994704022:
                                    if (currentName.equals("playbackId")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ImmutableList<LinearAssetUrl> immutableList = null;
                            Long l = null;
                            String str = null;
                            String str2 = null;
                            Integer num = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.duration = l;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.streamingTechnology = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.playbackId = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.sequence = num;
                            } else if (b == 4) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field stitched can't be null");
                                }
                                builder.stitched = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mLinearAssetUrlListParser.parse(jsonParser);
                                }
                                builder.urls = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
