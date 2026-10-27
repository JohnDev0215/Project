package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.PCSException;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class FailedRestrictedAiring {
    public final Optional<String> errorMessage;
    public final Optional<PCSException> exception;
    public final String identifier;

    private FailedRestrictedAiring(Builder builder) {
        this.exception = Optional.fromNullable(builder.exception);
        this.identifier = (String) Preconditions.checkNotNull(builder.identifier, "Unexpected null field: identifier");
        this.errorMessage = Optional.fromNullable(builder.errorMessage);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FailedRestrictedAiring)) {
            return false;
        }
        FailedRestrictedAiring failedRestrictedAiring = (FailedRestrictedAiring) obj;
        return Objects.equal(this.exception, failedRestrictedAiring.exception) && Objects.equal(this.identifier, failedRestrictedAiring.identifier) && Objects.equal(this.errorMessage, failedRestrictedAiring.errorMessage);
    }

    public int hashCode() {
        return Objects.hashCode(this.exception, this.identifier, this.errorMessage);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("exception", this.exception).add("identifier", this.identifier).add("errorMessage", this.errorMessage).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String errorMessage;
        public PCSException exception;
        public String identifier;

        public FailedRestrictedAiring build() {
            return new FailedRestrictedAiring(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<FailedRestrictedAiring> {
        private final PCSException.Parser mPCSExceptionParser;
        private final SimpleParsers.StringParser merrorMessageStringParser;
        private final SimpleParsers.StringParser midentifierParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPCSExceptionParser = new PCSException.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.midentifierParser = stringParser;
            this.merrorMessageStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public FailedRestrictedAiring parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FailedRestrictedAiring:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FailedRestrictedAiring parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FailedRestrictedAiring:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private FailedRestrictedAiring parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "FailedRestrictedAiring");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1618432855) {
                        if (iHashCode != 1203236063) {
                            if (iHashCode == 1481625679 && next.equals("exception")) {
                                b = 0;
                            }
                        } else if (next.equals("errorMessage")) {
                            b = 2;
                        }
                    } else if (next.equals("identifier")) {
                        b = 1;
                    }
                    String str = null;
                    PCSException pCSException = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pCSException = this.mPCSExceptionParser.parse(jsonNode2);
                        }
                        builder.exception = pCSException;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.midentifierParser.parse(jsonNode2);
                        }
                        builder.identifier = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.merrorMessageStringParser.parse(jsonNode2);
                        }
                        builder.errorMessage = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing FailedRestrictedAiring so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
            return builder.build();
        }

        @Nonnull
        private FailedRestrictedAiring parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1618432855) {
                                if (iHashCode != 1203236063) {
                                    if (iHashCode == 1481625679 && currentName.equals("exception")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("errorMessage")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("identifier")) {
                                b = 1;
                            }
                            String str = null;
                            PCSException pCSException = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pCSException = this.mPCSExceptionParser.parse(jsonParser);
                                }
                                builder.exception = pCSException;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.midentifierParser.parse(jsonParser);
                                }
                                builder.identifier = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.merrorMessageStringParser.parse(jsonParser);
                                }
                                builder.errorMessage = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing FailedRestrictedAiring so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
                    return builder.build();
                }
            }
        }
    }
}
