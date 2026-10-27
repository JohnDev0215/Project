package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.LocalizedString;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
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
public class ParentDetails {
    public final Optional<Integer> childNumber;
    public final Optional<LinearProgramType> parentType;
    public final Optional<LocalizedString> title;

    private ParentDetails(Builder builder) {
        this.title = Optional.fromNullable(builder.title);
        this.parentType = Optional.fromNullable(builder.parentType);
        this.childNumber = Optional.fromNullable(builder.childNumber);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParentDetails)) {
            return false;
        }
        ParentDetails parentDetails = (ParentDetails) obj;
        return Objects.equal(this.title, parentDetails.title) && Objects.equal(this.parentType, parentDetails.parentType) && Objects.equal(this.childNumber, parentDetails.childNumber);
    }

    public int hashCode() {
        return Objects.hashCode(this.title, this.parentType, this.childNumber);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("title", this.title).add("parentType", this.parentType).add("childNumber", this.childNumber).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Integer childNumber;
        public LinearProgramType parentType;
        public LocalizedString title;

        public ParentDetails build() {
            return new ParentDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ParentDetails> {
        private final SimpleParsers.IntegerParser mBoxedIntegerParser;
        private final EnumParser<LinearProgramType> mLinearProgramTypeParser;
        private final LocalizedString.Parser mLocalizedStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBoxedIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mLinearProgramTypeParser = EnumParser.newParser(LinearProgramType.class);
            this.mLocalizedStringParser = new LocalizedString.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ParentDetails parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ParentDetails:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ParentDetails parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ParentDetails:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ParentDetails parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ParentDetails");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1425770619) {
                        if (iHashCode != -244668668) {
                            if (iHashCode == 110371416 && next.equals("title")) {
                                b = 0;
                            }
                        } else if (next.equals("parentType")) {
                            b = 1;
                        }
                    } else if (next.equals("childNumber")) {
                        b = 2;
                    }
                    Integer num = null;
                    LocalizedString localizedString = null;
                    LinearProgramType linearProgramType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            localizedString = this.mLocalizedStringParser.parse(jsonNode2);
                        }
                        builder.title = localizedString;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            linearProgramType = (LinearProgramType) this.mLinearProgramTypeParser.parse(jsonNode2);
                        }
                        builder.parentType = linearProgramType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            num = this.mBoxedIntegerParser.parse(jsonNode2);
                        }
                        builder.childNumber = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ParentDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private ParentDetails parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1425770619) {
                                if (iHashCode != -244668668) {
                                    if (iHashCode == 110371416 && currentName.equals("title")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("parentType")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("childNumber")) {
                                b = 2;
                            }
                            Integer num = null;
                            LocalizedString localizedString = null;
                            LinearProgramType linearProgramType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedString = this.mLocalizedStringParser.parse(jsonParser);
                                }
                                builder.title = localizedString;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    linearProgramType = (LinearProgramType) this.mLinearProgramTypeParser.parse(jsonParser);
                                }
                                builder.parentType = linearProgramType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mBoxedIntegerParser.parse(jsonParser);
                                }
                                builder.childNumber = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ParentDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
