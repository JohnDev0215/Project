package com.amazon.avtitleactionaggregationservice.model.detailpage.metadata;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.metadata.AccessMetadata;
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
public class AccessDetails {
    public final Optional<AccessActionType> accessActionType;
    public final Optional<ImmutableList<AccessMetadata>> accessMetadataList;
    public final Optional<String> sourceId;

    private AccessDetails(Builder builder) {
        this.sourceId = Optional.fromNullable(builder.sourceId);
        this.accessActionType = Optional.fromNullable(builder.accessActionType);
        this.accessMetadataList = Optional.fromNullable(builder.accessMetadataList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessDetails)) {
            return false;
        }
        AccessDetails accessDetails = (AccessDetails) obj;
        return Objects.equal(this.sourceId, accessDetails.sourceId) && Objects.equal(this.accessActionType, accessDetails.accessActionType) && Objects.equal(this.accessMetadataList, accessDetails.accessMetadataList);
    }

    public int hashCode() {
        return Objects.hashCode(this.sourceId, this.accessActionType, this.accessMetadataList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("sourceId", this.sourceId).add("accessActionType", this.accessActionType).add("accessMetadataList", this.accessMetadataList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AccessActionType accessActionType;
        public ImmutableList<AccessMetadata> accessMetadataList;
        public String sourceId;

        public AccessDetails build() {
            return new AccessDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AccessDetails> {
        private final EnumParser<AccessActionType> mAccessActionTypeParser;
        private final ListParser<AccessMetadata> mAccessMetadataListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAccessActionTypeParser = EnumParser.newParser(AccessActionType.class);
            this.mAccessMetadataListParser = ListParser.newParser(new AccessMetadata.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AccessDetails parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AccessDetails:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AccessDetails parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AccessDetails:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AccessDetails parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AccessDetails");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -414016748) {
                        if (iHashCode != 1340900337) {
                            if (iHashCode == 1746327190 && next.equals("sourceId")) {
                                b = 0;
                            }
                        } else if (next.equals("accessMetadataList")) {
                            b = 2;
                        }
                    } else if (next.equals("accessActionType")) {
                        b = 1;
                    }
                    ImmutableList<AccessMetadata> immutableList = null;
                    String str = null;
                    AccessActionType accessActionType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.sourceId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            accessActionType = (AccessActionType) this.mAccessActionTypeParser.parse(jsonNode2);
                        }
                        builder.accessActionType = accessActionType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAccessMetadataListParser.parse(jsonNode2);
                        }
                        builder.accessMetadataList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AccessDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private AccessDetails parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -414016748) {
                                if (iHashCode != 1340900337) {
                                    if (iHashCode == 1746327190 && currentName.equals("sourceId")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("accessMetadataList")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("accessActionType")) {
                                b = 1;
                            }
                            ImmutableList<AccessMetadata> immutableList = null;
                            String str = null;
                            AccessActionType accessActionType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.sourceId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    accessActionType = (AccessActionType) this.mAccessActionTypeParser.parse(jsonParser);
                                }
                                builder.accessActionType = accessActionType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAccessMetadataListParser.parse(jsonParser);
                                }
                                builder.accessMetadataList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AccessDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
