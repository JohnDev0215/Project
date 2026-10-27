package com.amazon.atvtransitiontimecodeservice;

import com.amazon.atvtransitiontimecodeservice.MatureContentElement;
import com.amazon.atvtransitiontimecodeservice.SkipElement;
import com.amazon.atvtransitiontimecodeservice.types.MatureContentCategory;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class TTSResponse {
    public final Optional<Long> autoPlayTimer;
    public final Optional<ImmutableMap<MatureContentCategory, MatureContentElement>> matureContentElements;
    public final Optional<Long> outroCreditsStart;
    public final Optional<ImmutableList<SkipElement>> skipElements;

    private TTSResponse(Builder builder) {
        this.autoPlayTimer = Optional.fromNullable(builder.autoPlayTimer);
        this.matureContentElements = Optional.fromNullable(builder.matureContentElements);
        this.outroCreditsStart = Optional.fromNullable(builder.outroCreditsStart);
        this.skipElements = Optional.fromNullable(builder.skipElements);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TTSResponse)) {
            return false;
        }
        TTSResponse tTSResponse = (TTSResponse) obj;
        return Objects.equal(this.autoPlayTimer, tTSResponse.autoPlayTimer) && Objects.equal(this.matureContentElements, tTSResponse.matureContentElements) && Objects.equal(this.outroCreditsStart, tTSResponse.outroCreditsStart) && Objects.equal(this.skipElements, tTSResponse.skipElements);
    }

    public int hashCode() {
        return Objects.hashCode(this.autoPlayTimer, this.matureContentElements, this.outroCreditsStart, this.skipElements);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("autoPlayTimer", this.autoPlayTimer).add("matureContentElements", this.matureContentElements).add("outroCreditsStart", this.outroCreditsStart).add("skipElements", this.skipElements).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long autoPlayTimer;
        public ImmutableMap<MatureContentCategory, MatureContentElement> matureContentElements;
        public Long outroCreditsStart;
        public ImmutableList<SkipElement> skipElements;

        public TTSResponse build() {
            return new TTSResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TTSResponse> {
        private final MapParser<MatureContentCategory, MatureContentElement> mMatureContentElementMapParser;
        private final ListParser<SkipElement> mSkipElementListParser;
        private final SimpleParsers.LongParser mautoPlayTimerParser;
        private final SimpleParsers.LongParser moutroCreditsStartParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSkipElementListParser = ListParser.newParser(new SkipElement.Parser(objectMapper));
            this.mMatureContentElementMapParser = MapParser.newParser(EnumParser.newParser(MatureContentCategory.class), new MatureContentElement.Parser(objectMapper));
            SimpleParsers.LongParser longParser = SimpleParsers.LongParser.INSTANCE;
            this.mautoPlayTimerParser = longParser;
            this.moutroCreditsStartParser = longParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TTSResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TTSResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TTSResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TTSResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TTSResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TTSResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1039159917:
                            if (next.equals("outroCreditsStart")) {
                                b = 2;
                            }
                            break;
                        case -1009837162:
                            if (next.equals("skipElements")) {
                                b = 3;
                            }
                            break;
                        case 1312334722:
                            if (next.equals("autoPlayTimer")) {
                                b = 0;
                            }
                            break;
                        case 1745769320:
                            if (next.equals("matureContentElements")) {
                                b = 1;
                            }
                            break;
                    }
                    ImmutableList<SkipElement> immutableList = null;
                    Long l = null;
                    ImmutableMap<MatureContentCategory, MatureContentElement> immutableMap = null;
                    Long l2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l = this.mautoPlayTimerParser.parse(jsonNode2);
                        }
                        builder.autoPlayTimer = l;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mMatureContentElementMapParser.parse(jsonNode2);
                        }
                        builder.matureContentElements = immutableMap;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.moutroCreditsStartParser.parse(jsonNode2);
                        }
                        builder.outroCreditsStart = l2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSkipElementListParser.parse(jsonNode2);
                        }
                        builder.skipElements = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TTSResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TTSResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1039159917:
                                    if (currentName.equals("outroCreditsStart")) {
                                        b = 2;
                                    }
                                    break;
                                case -1009837162:
                                    if (currentName.equals("skipElements")) {
                                        b = 3;
                                    }
                                    break;
                                case 1312334722:
                                    if (currentName.equals("autoPlayTimer")) {
                                        b = 0;
                                    }
                                    break;
                                case 1745769320:
                                    if (currentName.equals("matureContentElements")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableList<SkipElement> immutableList = null;
                            Long l = null;
                            ImmutableMap<MatureContentCategory, MatureContentElement> immutableMap = null;
                            Long l2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mautoPlayTimerParser.parse(jsonParser);
                                }
                                builder.autoPlayTimer = l;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mMatureContentElementMapParser.parse(jsonParser);
                                }
                                builder.matureContentElements = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.moutroCreditsStartParser.parse(jsonParser);
                                }
                                builder.outroCreditsStart = l2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSkipElementListParser.parse(jsonParser);
                                }
                                builder.skipElements = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TTSResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
