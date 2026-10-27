package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.DefaultLinearRestriction;
import com.amazon.atv.parentalcontrols.LinearRestrictedProgram;
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
public class GetLinearRestrictionsV2Response {
    public final Optional<DefaultLinearRestriction> defaultChallenge;
    public final Optional<ImmutableList<LinearRestrictedProgram>> programs;

    private GetLinearRestrictionsV2Response(Builder builder) {
        this.programs = Optional.fromNullable(builder.programs);
        this.defaultChallenge = Optional.fromNullable(builder.defaultChallenge);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLinearRestrictionsV2Response)) {
            return false;
        }
        GetLinearRestrictionsV2Response getLinearRestrictionsV2Response = (GetLinearRestrictionsV2Response) obj;
        return Objects.equal(this.programs, getLinearRestrictionsV2Response.programs) && Objects.equal(this.defaultChallenge, getLinearRestrictionsV2Response.defaultChallenge);
    }

    public int hashCode() {
        return Objects.hashCode(this.programs, this.defaultChallenge);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("programs", this.programs).add("defaultChallenge", this.defaultChallenge).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public DefaultLinearRestriction defaultChallenge;
        public ImmutableList<LinearRestrictedProgram> programs;

        public GetLinearRestrictionsV2Response build() {
            return new GetLinearRestrictionsV2Response(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLinearRestrictionsV2Response> {
        private final DefaultLinearRestriction.Parser mDefaultLinearRestrictionParser;
        private final ListParser<LinearRestrictedProgram> mlinearRestrictedProgramListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mlinearRestrictedProgramListParser = ListParser.newParser(new LinearRestrictedProgram.Parser(objectMapper));
            this.mDefaultLinearRestrictionParser = new DefaultLinearRestriction.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLinearRestrictionsV2Response parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsV2Response:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLinearRestrictionsV2Response parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsV2Response:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLinearRestrictionsV2Response parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLinearRestrictionsV2Response");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1081131230) {
                        if (iHashCode == -1001082257 && next.equals("programs")) {
                            b = 0;
                        }
                    } else if (next.equals("defaultChallenge")) {
                        b = 1;
                    }
                    DefaultLinearRestriction defaultLinearRestriction = null;
                    ImmutableList<LinearRestrictedProgram> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mlinearRestrictedProgramListParser.parse(jsonNode2);
                        }
                        builder.programs = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            defaultLinearRestriction = this.mDefaultLinearRestrictionParser.parse(jsonNode2);
                        }
                        builder.defaultChallenge = defaultLinearRestriction;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLinearRestrictionsV2Response so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetLinearRestrictionsV2Response parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1081131230) {
                                if (iHashCode == -1001082257 && currentName.equals("programs")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("defaultChallenge")) {
                                b = 1;
                            }
                            DefaultLinearRestriction defaultLinearRestriction = null;
                            ImmutableList<LinearRestrictedProgram> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mlinearRestrictedProgramListParser.parse(jsonParser);
                                }
                                builder.programs = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    defaultLinearRestriction = this.mDefaultLinearRestrictionParser.parse(jsonParser);
                                }
                                builder.defaultChallenge = defaultLinearRestriction;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLinearRestrictionsV2Response so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
