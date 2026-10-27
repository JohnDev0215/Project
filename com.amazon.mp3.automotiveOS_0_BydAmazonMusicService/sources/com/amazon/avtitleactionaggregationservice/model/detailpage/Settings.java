package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.ContractPeriodType;
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
public class Settings {
    public final Optional<ContractPeriodType> contractPeriodType;
    public final Optional<Integer> endsAfterNumberOfPeriod;
    public final Optional<Integer> precedence;

    private Settings(Builder builder) {
        this.endsAfterNumberOfPeriod = Optional.fromNullable(builder.endsAfterNumberOfPeriod);
        this.contractPeriodType = Optional.fromNullable(builder.contractPeriodType);
        this.precedence = Optional.fromNullable(builder.precedence);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Settings)) {
            return false;
        }
        Settings settings = (Settings) obj;
        return Objects.equal(this.endsAfterNumberOfPeriod, settings.endsAfterNumberOfPeriod) && Objects.equal(this.contractPeriodType, settings.contractPeriodType) && Objects.equal(this.precedence, settings.precedence);
    }

    public int hashCode() {
        return Objects.hashCode(this.endsAfterNumberOfPeriod, this.contractPeriodType, this.precedence);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("endsAfterNumberOfPeriod", this.endsAfterNumberOfPeriod).add("contractPeriodType", this.contractPeriodType).add("precedence", this.precedence).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ContractPeriodType contractPeriodType;
        public Integer endsAfterNumberOfPeriod;
        public Integer precedence;

        public Settings build() {
            return new Settings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Settings> {
        private final EnumParser<ContractPeriodType> mContractPeriodTypeParser;
        private final SimpleParsers.IntegerParser mIntegerParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mContractPeriodTypeParser = EnumParser.newParser(ContractPeriodType.class);
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Settings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Settings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Settings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Settings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Settings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Settings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1784484019) {
                        if (iHashCode != 159695370) {
                            if (iHashCode == 1471241157 && next.equals("endsAfterNumberOfPeriod")) {
                                b = 0;
                            }
                        } else if (next.equals("precedence")) {
                            b = 2;
                        }
                    } else if (next.equals("contractPeriodType")) {
                        b = 1;
                    }
                    Integer num = null;
                    Integer num2 = null;
                    ContractPeriodType contractPeriodType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            num2 = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.endsAfterNumberOfPeriod = num2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            contractPeriodType = (ContractPeriodType) this.mContractPeriodTypeParser.parse(jsonNode2);
                        }
                        builder.contractPeriodType = contractPeriodType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.precedence = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Settings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Settings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1784484019) {
                                if (iHashCode != 159695370) {
                                    if (iHashCode == 1471241157 && currentName.equals("endsAfterNumberOfPeriod")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("precedence")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("contractPeriodType")) {
                                b = 1;
                            }
                            Integer num = null;
                            Integer num2 = null;
                            ContractPeriodType contractPeriodType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num2 = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.endsAfterNumberOfPeriod = num2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    contractPeriodType = (ContractPeriodType) this.mContractPeriodTypeParser.parse(jsonParser);
                                }
                                builder.contractPeriodType = contractPeriodType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.precedence = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Settings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
