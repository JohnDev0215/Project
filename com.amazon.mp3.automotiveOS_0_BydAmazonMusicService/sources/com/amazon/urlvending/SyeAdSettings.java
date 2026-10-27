package com.amazon.urlvending;

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

/* JADX INFO: loaded from: classes5.dex */
public class SyeAdSettings {
    public final Optional<ImmutableMap<String, String>> adParams;
    public final Optional<String> advertisingId;
    public final Optional<String> optOutAdTracking;

    private SyeAdSettings(Builder builder) {
        this.adParams = Optional.fromNullable(builder.adParams);
        this.advertisingId = Optional.fromNullable(builder.advertisingId);
        this.optOutAdTracking = Optional.fromNullable(builder.optOutAdTracking);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeAdSettings)) {
            return false;
        }
        SyeAdSettings syeAdSettings = (SyeAdSettings) obj;
        return Objects.equal(this.adParams, syeAdSettings.adParams) && Objects.equal(this.advertisingId, syeAdSettings.advertisingId) && Objects.equal(this.optOutAdTracking, syeAdSettings.optOutAdTracking);
    }

    public int hashCode() {
        return Objects.hashCode(this.adParams, this.advertisingId, this.optOutAdTracking);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("adParams", this.adParams).add("advertisingId", this.advertisingId).add("optOutAdTracking", this.optOutAdTracking).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, String> adParams;
        public String advertisingId;
        public String optOutAdTracking;

        public SyeAdSettings build() {
            return new SyeAdSettings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeAdSettings> {
        private final MapParser<String, String> mAdParamMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mAdParamMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeAdSettings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeAdSettings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeAdSettings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeAdSettings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SyeAdSettings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeAdSettings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1806627959) {
                        if (iHashCode != -124344481) {
                            if (iHashCode == 1307785717 && next.equals("optOutAdTracking")) {
                                b = 2;
                            }
                        } else if (next.equals("advertisingId")) {
                            b = 1;
                        }
                    } else if (next.equals("adParams")) {
                        b = 0;
                    }
                    String str = null;
                    ImmutableMap<String, String> immutableMap = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mAdParamMapParser.parse(jsonNode2);
                        }
                        builder.adParams = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.advertisingId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.optOutAdTracking = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeAdSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private SyeAdSettings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1806627959) {
                                if (iHashCode != -124344481) {
                                    if (iHashCode == 1307785717 && currentName.equals("optOutAdTracking")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("advertisingId")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("adParams")) {
                                b = 0;
                            }
                            String str = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mAdParamMapParser.parse(jsonParser);
                                }
                                builder.adParams = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.advertisingId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.optOutAdTracking = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeAdSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
