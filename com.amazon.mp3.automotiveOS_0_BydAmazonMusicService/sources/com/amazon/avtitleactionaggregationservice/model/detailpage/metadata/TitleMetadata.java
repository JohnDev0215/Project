package com.amazon.avtitleactionaggregationservice.model.detailpage.metadata;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.metadata.AccessDetails;
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
public class TitleMetadata {
    public final Optional<ImmutableList<AccessDetails>> accessDetails;
    public final Optional<String> childTitle;

    private TitleMetadata(Builder builder) {
        this.childTitle = Optional.fromNullable(builder.childTitle);
        this.accessDetails = Optional.fromNullable(builder.accessDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleMetadata)) {
            return false;
        }
        TitleMetadata titleMetadata = (TitleMetadata) obj;
        return Objects.equal(this.childTitle, titleMetadata.childTitle) && Objects.equal(this.accessDetails, titleMetadata.accessDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.childTitle, this.accessDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("childTitle", this.childTitle).add("accessDetails", this.accessDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<AccessDetails> accessDetails;
        public String childTitle;

        public TitleMetadata build() {
            return new TitleMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleMetadata> {
        private final ListParser<AccessDetails> mAccessDetailListParser;
        private final SimpleParsers.StringParser mIdentifierParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAccessDetailListParser = ListParser.newParser(new AccessDetails.Parser(objectMapper));
            this.mIdentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TitleMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 1344671388) {
                        if (iHashCode == 1404681278 && next.equals("accessDetails")) {
                            b = 1;
                        }
                    } else if (next.equals("childTitle")) {
                        b = 0;
                    }
                    ImmutableList<AccessDetails> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mIdentifierParser.parse(jsonNode2);
                        }
                        builder.childTitle = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAccessDetailListParser.parse(jsonNode2);
                        }
                        builder.accessDetails = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TitleMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 1344671388) {
                                if (iHashCode == 1404681278 && currentName.equals("accessDetails")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("childTitle")) {
                                b = 0;
                            }
                            ImmutableList<AccessDetails> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mIdentifierParser.parse(jsonParser);
                                }
                                builder.childTitle = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAccessDetailListParser.parse(jsonParser);
                                }
                                builder.accessDetails = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
