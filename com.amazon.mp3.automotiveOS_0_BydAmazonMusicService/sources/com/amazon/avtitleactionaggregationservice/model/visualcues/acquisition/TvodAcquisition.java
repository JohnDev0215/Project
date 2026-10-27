package com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.TvodOfferType;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.TvodOfferMetadata;
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
public class TvodAcquisition {
    public final Optional<Integer> rank;
    public final Optional<TvodOfferMetadata> tvodOfferMetadata;
    public final Optional<TvodOfferType> tvodOfferType;

    private TvodAcquisition(Builder builder) {
        this.tvodOfferType = Optional.fromNullable(builder.tvodOfferType);
        this.tvodOfferMetadata = Optional.fromNullable(builder.tvodOfferMetadata);
        this.rank = Optional.fromNullable(builder.rank);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TvodAcquisition)) {
            return false;
        }
        TvodAcquisition tvodAcquisition = (TvodAcquisition) obj;
        return Objects.equal(this.tvodOfferType, tvodAcquisition.tvodOfferType) && Objects.equal(this.tvodOfferMetadata, tvodAcquisition.tvodOfferMetadata) && Objects.equal(this.rank, tvodAcquisition.rank);
    }

    public int hashCode() {
        return Objects.hashCode(this.tvodOfferType, this.tvodOfferMetadata, this.rank);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("tvodOfferType", this.tvodOfferType).add("tvodOfferMetadata", this.tvodOfferMetadata).add("rank", this.rank).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Integer rank;
        public TvodOfferMetadata tvodOfferMetadata;
        public TvodOfferType tvodOfferType;

        public TvodAcquisition build() {
            return new TvodAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TvodAcquisition> {
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final TvodOfferMetadata.Parser mTvodOfferMetadataParser;
        private final EnumParser<TvodOfferType> mTvodOfferTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mTvodOfferTypeParser = EnumParser.newParser(TvodOfferType.class);
            this.mTvodOfferMetadataParser = new TvodOfferMetadata.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TvodAcquisition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodAcquisition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TvodAcquisition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodAcquisition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TvodAcquisition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TvodAcquisition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2039181921) {
                        if (iHashCode != 3492908) {
                            if (iHashCode == 97238804 && next.equals("tvodOfferMetadata")) {
                                b = 1;
                            }
                        } else if (next.equals("rank")) {
                            b = 2;
                        }
                    } else if (next.equals("tvodOfferType")) {
                        b = 0;
                    }
                    Integer num = null;
                    TvodOfferType tvodOfferType = null;
                    TvodOfferMetadata tvodOfferMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            tvodOfferType = (TvodOfferType) this.mTvodOfferTypeParser.parse(jsonNode2);
                        }
                        builder.tvodOfferType = tvodOfferType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            tvodOfferMetadata = this.mTvodOfferMetadataParser.parse(jsonNode2);
                        }
                        builder.tvodOfferMetadata = tvodOfferMetadata;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.rank = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TvodAcquisition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2039181921) {
                                if (iHashCode != 3492908) {
                                    if (iHashCode == 97238804 && currentName.equals("tvodOfferMetadata")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("rank")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("tvodOfferType")) {
                                b = 0;
                            }
                            Integer num = null;
                            TvodOfferType tvodOfferType = null;
                            TvodOfferMetadata tvodOfferMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    tvodOfferType = (TvodOfferType) this.mTvodOfferTypeParser.parse(jsonParser);
                                }
                                builder.tvodOfferType = tvodOfferType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    tvodOfferMetadata = this.mTvodOfferMetadataParser.parse(jsonParser);
                                }
                                builder.tvodOfferMetadata = tvodOfferMetadata;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.rank = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
