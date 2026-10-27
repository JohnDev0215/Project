package com.amazon.atvsubtitlepresets;

import com.amazon.atvsubtitlepresets.SubtitlePreset;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetPresetsOutput {
    public final Optional<String> customerId;
    public final Optional<String> marketplaceId;
    public final Optional<String> message;
    public final Optional<ImmutableMap<String, SubtitlePreset>> presets;
    public final Optional<Boolean> succeeded;

    private GetPresetsOutput(Builder builder) {
        this.message = Optional.fromNullable(builder.message);
        this.marketplaceId = Optional.fromNullable(builder.marketplaceId);
        this.presets = Optional.fromNullable(builder.presets);
        this.customerId = Optional.fromNullable(builder.customerId);
        this.succeeded = Optional.fromNullable(builder.succeeded);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetPresetsOutput)) {
            return false;
        }
        GetPresetsOutput getPresetsOutput = (GetPresetsOutput) obj;
        return Objects.equal(this.message, getPresetsOutput.message) && Objects.equal(this.marketplaceId, getPresetsOutput.marketplaceId) && Objects.equal(this.presets, getPresetsOutput.presets) && Objects.equal(this.customerId, getPresetsOutput.customerId) && Objects.equal(this.succeeded, getPresetsOutput.succeeded);
    }

    public int hashCode() {
        return Objects.hashCode(this.message, this.marketplaceId, this.presets, this.customerId, this.succeeded);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", this.message).add("marketplaceId", this.marketplaceId).add("presets", this.presets).add("customerId", this.customerId).add("succeeded", this.succeeded).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String customerId;
        public String marketplaceId;
        public String message;
        public ImmutableMap<String, SubtitlePreset> presets;
        public Boolean succeeded;

        public GetPresetsOutput build() {
            return new GetPresetsOutput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetPresetsOutput> {
        private final MapParser<String, SubtitlePreset> mSubtitlePresetMapParser;
        private final SimpleParsers.StringParser mcustomerIdParser;
        private final SimpleParsers.StringParser mmarketplaceIdParser;
        private final SimpleParsers.StringParser mmessageParser;
        private final SimpleParsers.BooleanParser msucceededParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mSubtitlePresetMapParser = MapParser.newParser(stringParser, new SubtitlePreset.Parser(objectMapper));
            this.msucceededParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mmessageParser = stringParser;
            this.mmarketplaceIdParser = stringParser;
            this.mcustomerIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetPresetsOutput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPresetsOutput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetPresetsOutput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPresetsOutput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetPresetsOutput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetPresetsOutput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1581184615:
                            if (next.equals("customerId")) {
                                b = 3;
                            }
                            break;
                        case -318277260:
                            if (next.equals("presets")) {
                                b = 2;
                            }
                            break;
                        case 945734241:
                            if (next.equals("succeeded")) {
                                b = 4;
                            }
                            break;
                        case 954925063:
                            if (next.equals("message")) {
                                b = 0;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 1;
                            }
                            break;
                    }
                    Boolean bool = null;
                    String str = null;
                    String str2 = null;
                    ImmutableMap<String, SubtitlePreset> immutableMap = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mmessageParser.parse(jsonNode2);
                        }
                        builder.message = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mmarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mSubtitlePresetMapParser.parse(jsonNode2);
                        }
                        builder.presets = immutableMap;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mcustomerIdParser.parse(jsonNode2);
                        }
                        builder.customerId = str3;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            bool = this.msucceededParser.parse(jsonNode2);
                        }
                        builder.succeeded = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetPresetsOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetPresetsOutput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1581184615:
                                    if (currentName.equals("customerId")) {
                                        b = 3;
                                    }
                                    break;
                                case -318277260:
                                    if (currentName.equals("presets")) {
                                        b = 2;
                                    }
                                    break;
                                case 945734241:
                                    if (currentName.equals("succeeded")) {
                                        b = 4;
                                    }
                                    break;
                                case 954925063:
                                    if (currentName.equals("message")) {
                                        b = 0;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            String str = null;
                            String str2 = null;
                            ImmutableMap<String, SubtitlePreset> immutableMap = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mmessageParser.parse(jsonParser);
                                }
                                builder.message = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mmarketplaceIdParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mSubtitlePresetMapParser.parse(jsonParser);
                                }
                                builder.presets = immutableMap;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mcustomerIdParser.parse(jsonParser);
                                }
                                builder.customerId = str3;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.msucceededParser.parse(jsonParser);
                                }
                                builder.succeeded = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetPresetsOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
