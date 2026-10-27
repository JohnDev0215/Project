package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.DefaultRestriction;
import com.amazon.atv.parentalcontrols.GetLinearRestrictionsResponseMetadata;
import com.amazon.atv.parentalcontrols.RestrictedProgram;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
public class GetLinearRestrictionsResponse {
    public final Optional<DefaultRestriction> defaultRestriction;
    public final Optional<GetLinearRestrictionsResponseMetadata> metadata;
    public final Optional<ImmutableList<RestrictedProgram>> programs;

    private GetLinearRestrictionsResponse(Builder builder) {
        this.programs = Optional.fromNullable(builder.programs);
        this.defaultRestriction = Optional.fromNullable(builder.defaultRestriction);
        this.metadata = Optional.fromNullable(builder.metadata);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLinearRestrictionsResponse)) {
            return false;
        }
        GetLinearRestrictionsResponse getLinearRestrictionsResponse = (GetLinearRestrictionsResponse) obj;
        return Objects.equal(this.programs, getLinearRestrictionsResponse.programs) && Objects.equal(this.defaultRestriction, getLinearRestrictionsResponse.defaultRestriction) && Objects.equal(this.metadata, getLinearRestrictionsResponse.metadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.programs, this.defaultRestriction, this.metadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("programs", this.programs).add("defaultRestriction", this.defaultRestriction).add("metadata", this.metadata).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public DefaultRestriction defaultRestriction;
        public GetLinearRestrictionsResponseMetadata metadata;
        public ImmutableList<RestrictedProgram> programs;

        public GetLinearRestrictionsResponse build() {
            return new GetLinearRestrictionsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLinearRestrictionsResponse> {
        private final DefaultRestriction.Parser mDefaultRestrictionParser;
        private final GetLinearRestrictionsResponseMetadata.Parser mGetLinearRestrictionsResponseMetadataParser;
        private final ListParser<RestrictedProgram> mrestrictedProgramListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mrestrictedProgramListParser = ListParser.newParser(new RestrictedProgram.Parser(objectMapper));
            this.mGetLinearRestrictionsResponseMetadataParser = new GetLinearRestrictionsResponseMetadata.Parser(objectMapper);
            this.mDefaultRestrictionParser = new DefaultRestriction.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLinearRestrictionsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLinearRestrictionsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLinearRestrictionsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLinearRestrictionsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1001082257) {
                        if (iHashCode != -456973621) {
                            if (iHashCode == -450004177 && next.equals("metadata")) {
                                b = 2;
                            }
                        } else if (next.equals("defaultRestriction")) {
                            b = 1;
                        }
                    } else if (next.equals("programs")) {
                        b = 0;
                    }
                    GetLinearRestrictionsResponseMetadata getLinearRestrictionsResponseMetadata = null;
                    ImmutableList<RestrictedProgram> immutableList = null;
                    DefaultRestriction defaultRestriction = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mrestrictedProgramListParser.parse(jsonNode2);
                        }
                        builder.programs = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            defaultRestriction = this.mDefaultRestrictionParser.parse(jsonNode2);
                        }
                        builder.defaultRestriction = defaultRestriction;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            getLinearRestrictionsResponseMetadata = this.mGetLinearRestrictionsResponseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = getLinearRestrictionsResponseMetadata;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLinearRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetLinearRestrictionsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1001082257) {
                                if (iHashCode != -456973621) {
                                    if (iHashCode == -450004177 && currentName.equals("metadata")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("defaultRestriction")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("programs")) {
                                b = 0;
                            }
                            GetLinearRestrictionsResponseMetadata getLinearRestrictionsResponseMetadata = null;
                            ImmutableList<RestrictedProgram> immutableList = null;
                            DefaultRestriction defaultRestriction = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mrestrictedProgramListParser.parse(jsonParser);
                                }
                                builder.programs = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    defaultRestriction = this.mDefaultRestrictionParser.parse(jsonParser);
                                }
                                builder.defaultRestriction = defaultRestriction;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getLinearRestrictionsResponseMetadata = this.mGetLinearRestrictionsResponseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = getLinearRestrictionsResponseMetadata;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLinearRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
