package com.amazon.avtitleactionaggregationservice.model.detailpage.download;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.download.NonDownloadableReason;
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
    public final Optional<Boolean> isDownloadable;
    public final Optional<ImmutableList<NonDownloadableReason>> nonDownloadableReasons;

    private Eligibility(Builder builder) {
        this.isDownloadable = Optional.fromNullable(builder.isDownloadable);
        this.nonDownloadableReasons = Optional.fromNullable(builder.nonDownloadableReasons);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Eligibility)) {
            return false;
        }
        Eligibility eligibility = (Eligibility) obj;
        return Objects.equal(this.isDownloadable, eligibility.isDownloadable) && Objects.equal(this.nonDownloadableReasons, eligibility.nonDownloadableReasons);
    }

    public int hashCode() {
        return Objects.hashCode(this.isDownloadable, this.nonDownloadableReasons);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("isDownloadable", this.isDownloadable).add("nonDownloadableReasons", this.nonDownloadableReasons).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean isDownloadable;
        public ImmutableList<NonDownloadableReason> nonDownloadableReasons;

        public Eligibility build() {
            return new Eligibility(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Eligibility> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final ListParser<NonDownloadableReason> mNonDownloadableReasonsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNonDownloadableReasonsParser = ListParser.newParser(new NonDownloadableReason.Parser(objectMapper));
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
                    if (iHashCode != 348113964) {
                        if (iHashCode == 1734244960 && next.equals("nonDownloadableReasons")) {
                            b = 1;
                        }
                    } else if (next.equals("isDownloadable")) {
                        b = 0;
                    }
                    ImmutableList<NonDownloadableReason> immutableList = null;
                    Boolean bool = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isDownloadable = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mNonDownloadableReasonsParser.parse(jsonNode2);
                        }
                        builder.nonDownloadableReasons = immutableList;
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
                            if (iHashCode != 348113964) {
                                if (iHashCode == 1734244960 && currentName.equals("nonDownloadableReasons")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("isDownloadable")) {
                                b = 0;
                            }
                            ImmutableList<NonDownloadableReason> immutableList = null;
                            Boolean bool = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.isDownloadable = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mNonDownloadableReasonsParser.parse(jsonParser);
                                }
                                builder.nonDownloadableReasons = immutableList;
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
