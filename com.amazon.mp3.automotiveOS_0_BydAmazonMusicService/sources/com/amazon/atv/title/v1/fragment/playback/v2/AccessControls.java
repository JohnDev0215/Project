package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.atv.title.v1.fragment.playback.v2.Restriction;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
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
public class AccessControls {
    public final Optional<Integer> pinLength;
    public final Optional<ImmutableList<Restriction>> restrictions;

    private AccessControls(Builder builder) {
        this.pinLength = Optional.fromNullable(builder.pinLength);
        this.restrictions = Optional.fromNullable(builder.restrictions);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessControls)) {
            return false;
        }
        AccessControls accessControls = (AccessControls) obj;
        return Objects.equal(this.pinLength, accessControls.pinLength) && Objects.equal(this.restrictions, accessControls.restrictions);
    }

    public int hashCode() {
        return Objects.hashCode(this.pinLength, this.restrictions);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("pinLength", this.pinLength).add("restrictions", this.restrictions).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Integer pinLength;
        public ImmutableList<Restriction> restrictions;

        public AccessControls build() {
            return new AccessControls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AccessControls> {
        private final SimpleParsers.IntegerParser mNullableIntegerParser;
        private final ListParser<Restriction> mRestrictionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRestrictionListParser = ListParser.newParser(new Restriction.Parser(objectMapper));
            this.mNullableIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AccessControls parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AccessControls:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AccessControls parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AccessControls:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AccessControls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AccessControls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1148295641) {
                        if (iHashCode == -1146317605 && next.equals("pinLength")) {
                            b = 0;
                        }
                    } else if (next.equals("restrictions")) {
                        b = 1;
                    }
                    ImmutableList<Restriction> immutableList = null;
                    Integer num = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            num = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.pinLength = num;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mRestrictionListParser.parse(jsonNode2);
                        }
                        builder.restrictions = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AccessControls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private AccessControls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1148295641) {
                                if (iHashCode == -1146317605 && currentName.equals("pinLength")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("restrictions")) {
                                b = 1;
                            }
                            ImmutableList<Restriction> immutableList = null;
                            Integer num = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mNullableIntegerParser.parse(jsonParser);
                                }
                                builder.pinLength = num;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mRestrictionListParser.parse(jsonParser);
                                }
                                builder.restrictions = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AccessControls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
