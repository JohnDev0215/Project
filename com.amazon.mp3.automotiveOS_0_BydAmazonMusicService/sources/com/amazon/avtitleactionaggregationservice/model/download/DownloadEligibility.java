package com.amazon.avtitleactionaggregationservice.model.download;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.download.NonDownloadableReason;
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
public class DownloadEligibility {
    public final Optional<Boolean> canDownload;
    public final Optional<ImmutableList<NonDownloadableReason>> nonDownloadableReasons;

    private DownloadEligibility(Builder builder) {
        this.nonDownloadableReasons = Optional.fromNullable(builder.nonDownloadableReasons);
        this.canDownload = Optional.fromNullable(builder.canDownload);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadEligibility)) {
            return false;
        }
        DownloadEligibility downloadEligibility = (DownloadEligibility) obj;
        return Objects.equal(this.nonDownloadableReasons, downloadEligibility.nonDownloadableReasons) && Objects.equal(this.canDownload, downloadEligibility.canDownload);
    }

    public int hashCode() {
        return Objects.hashCode(this.nonDownloadableReasons, this.canDownload);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("nonDownloadableReasons", this.nonDownloadableReasons).add("canDownload", this.canDownload).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean canDownload;
        public ImmutableList<NonDownloadableReason> nonDownloadableReasons;

        public DownloadEligibility build() {
            return new DownloadEligibility(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DownloadEligibility> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final ListParser<NonDownloadableReason> mNonDownloadableReasonsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNonDownloadableReasonsParser = ListParser.newParser(new NonDownloadableReason.Parser(objectMapper));
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DownloadEligibility parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadEligibility:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DownloadEligibility parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadEligibility:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DownloadEligibility parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DownloadEligibility");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 849468536) {
                        if (iHashCode == 1734244960 && next.equals("nonDownloadableReasons")) {
                            b = 0;
                        }
                    } else if (next.equals("canDownload")) {
                        b = 1;
                    }
                    Boolean bool = null;
                    ImmutableList<NonDownloadableReason> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mNonDownloadableReasonsParser.parse(jsonNode2);
                        }
                        builder.nonDownloadableReasons = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.canDownload = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DownloadEligibility so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DownloadEligibility parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 849468536) {
                                if (iHashCode == 1734244960 && currentName.equals("nonDownloadableReasons")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("canDownload")) {
                                b = 1;
                            }
                            Boolean bool = null;
                            ImmutableList<NonDownloadableReason> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mNonDownloadableReasonsParser.parse(jsonParser);
                                }
                                builder.nonDownloadableReasons = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.canDownload = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DownloadEligibility so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
