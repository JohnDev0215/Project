package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.CSSException;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Error {
    public final Optional<String> airingId;
    public final Optional<CSSException> exception;

    private Error(Builder builder) {
        this.exception = Optional.fromNullable(builder.exception);
        this.airingId = Optional.fromNullable(builder.airingId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Error)) {
            return false;
        }
        Error error = (Error) obj;
        return Objects.equal(this.exception, error.exception) && Objects.equal(this.airingId, error.airingId);
    }

    public int hashCode() {
        return Objects.hashCode(this.exception, this.airingId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("exception", this.exception).add("airingId", this.airingId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String airingId;
        public CSSException exception;

        public Error build() {
            return new Error(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Error> {
        private final SimpleParsers.StringParser mAiringIdParser;
        private final CSSException.Parser mCSSExceptionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCSSExceptionParser = new CSSException.Parser(objectMapper);
            this.mAiringIdParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Error parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Error:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Error parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Error:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Error parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Error");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -683412077) {
                        if (iHashCode == 1481625679 && next.equals("exception")) {
                            b = 0;
                        }
                    } else if (next.equals("airingId")) {
                        b = 1;
                    }
                    String str = null;
                    CSSException cSSException = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            cSSException = this.mCSSExceptionParser.parse(jsonNode2);
                        }
                        builder.exception = cSSException;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mAiringIdParser.parse(jsonNode2);
                        }
                        builder.airingId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Error so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Error parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -683412077) {
                                if (iHashCode == 1481625679 && currentName.equals("exception")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("airingId")) {
                                b = 1;
                            }
                            String str = null;
                            CSSException cSSException = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cSSException = this.mCSSExceptionParser.parse(jsonParser);
                                }
                                builder.exception = cSSException;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mAiringIdParser.parse(jsonParser);
                                }
                                builder.airingId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Error so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
