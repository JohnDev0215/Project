package com.amazon.atv.discovery;

import com.amazon.atv.discovery.DebugAttribute;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class InternalServiceException {
    public final Optional<ImmutableMap<DebugType, ImmutableList<DebugAttribute>>> debugAttributes;
    public final Optional<String> errorCode;
    public final Optional<String> message;

    protected InternalServiceException(Builder builder) {
        this.message = Optional.fromNullable(builder.message);
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
        this.errorCode = Optional.fromNullable(builder.errorCode);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InternalServiceException)) {
            return false;
        }
        InternalServiceException internalServiceException = (InternalServiceException) obj;
        return Objects.equal(this.message, internalServiceException.message) && Objects.equal(this.debugAttributes, internalServiceException.debugAttributes) && Objects.equal(this.errorCode, internalServiceException.errorCode);
    }

    public int hashCode() {
        return Objects.hashCode(this.message, this.debugAttributes, this.errorCode);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", this.message).add("debugAttributes", this.debugAttributes).add("errorCode", this.errorCode).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<DebugType, ImmutableList<DebugAttribute>> debugAttributes;
        public String errorCode;
        public String message;

        public InternalServiceException build() {
            return new InternalServiceException(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<InternalServiceException> {
        private final MapParser<DebugType, ImmutableList<DebugAttribute>> mDebugAttributesParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDebugAttributesParser = MapParser.newParser(EnumParser.newParser(DebugType.class), ListParser.newParser(new DebugAttribute.Parser(objectMapper)));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public InternalServiceException parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternalServiceException:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public InternalServiceException parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternalServiceException:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private InternalServiceException parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "InternalServiceException");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 329035797) {
                        if (iHashCode != 954925063) {
                            if (iHashCode == 2140311562 && next.equals("debugAttributes")) {
                                b = 1;
                            }
                        } else if (next.equals("message")) {
                            b = 0;
                        }
                    } else if (next.equals("errorCode")) {
                        b = 2;
                    }
                    String str = null;
                    String str2 = null;
                    ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.message = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebugAttributesParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = immutableMap;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.errorCode = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing InternalServiceException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private InternalServiceException parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 329035797) {
                                if (iHashCode != 954925063) {
                                    if (iHashCode == 2140311562 && currentName.equals("debugAttributes")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("message")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("errorCode")) {
                                b = 2;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.message = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebugAttributesParser.parse(jsonParser);
                                }
                                builder.debugAttributes = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.errorCode = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing InternalServiceException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
