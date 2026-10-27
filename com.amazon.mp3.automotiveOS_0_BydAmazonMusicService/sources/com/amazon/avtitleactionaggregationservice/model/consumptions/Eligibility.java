package com.amazon.avtitleactionaggregationservice.model.consumptions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.consumptions.NonPlayableConsumptionReason;
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

/* JADX INFO: loaded from: classes2.dex */
public class Eligibility {
    public final Optional<Boolean> canPlay;
    public final Optional<ImmutableList<NonPlayableConsumptionReason>> nonPlayableConsumptionReasons;

    private Eligibility(Builder builder) {
        this.nonPlayableConsumptionReasons = Optional.fromNullable(builder.nonPlayableConsumptionReasons);
        this.canPlay = Optional.fromNullable(builder.canPlay);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Eligibility)) {
            return false;
        }
        Eligibility eligibility = (Eligibility) obj;
        return Objects.equal(this.nonPlayableConsumptionReasons, eligibility.nonPlayableConsumptionReasons) && Objects.equal(this.canPlay, eligibility.canPlay);
    }

    public int hashCode() {
        return Objects.hashCode(this.nonPlayableConsumptionReasons, this.canPlay);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("nonPlayableConsumptionReasons", this.nonPlayableConsumptionReasons).add("canPlay", this.canPlay).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean canPlay;
        public ImmutableList<NonPlayableConsumptionReason> nonPlayableConsumptionReasons;

        public Eligibility build() {
            return new Eligibility(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Eligibility> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final ListParser<NonPlayableConsumptionReason> mNonPlayableConsumptionReasonsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNonPlayableConsumptionReasonsParser = ListParser.newParser(new NonPlayableConsumptionReason.Parser(objectMapper));
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Eligibility parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Eligibility:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Eligibility parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Eligibility:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Eligibility parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Eligibility");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 549656356) {
                        if (iHashCode == 1872573039 && next.equals("nonPlayableConsumptionReasons")) {
                            b = 0;
                        }
                    } else if (next.equals("canPlay")) {
                        b = 1;
                    }
                    Boolean bool = null;
                    ImmutableList<NonPlayableConsumptionReason> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mNonPlayableConsumptionReasonsParser.parse(jsonNode2);
                        }
                        builder.nonPlayableConsumptionReasons = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.canPlay = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Eligibility so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Eligibility parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 549656356) {
                                if (iHashCode == 1872573039 && currentName.equals("nonPlayableConsumptionReasons")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("canPlay")) {
                                b = 1;
                            }
                            Boolean bool = null;
                            ImmutableList<NonPlayableConsumptionReason> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mNonPlayableConsumptionReasonsParser.parse(jsonParser);
                                }
                                builder.nonPlayableConsumptionReasons = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.canPlay = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Eligibility so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
