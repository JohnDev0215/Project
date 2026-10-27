package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.DebStep;
import com.amazon.urlvending.FirstFilterWithEmptyResult;
import com.amazon.urlvending.types.ErrorCode;
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

/* JADX INFO: loaded from: classes5.dex */
public class UrlVendingException {
    public final Optional<ImmutableMap<String, ImmutableList<DebStep>>> debInfo;
    public final Optional<ErrorCode> errorCode;
    public final Optional<ImmutableMap<String, ImmutableList<FirstFilterWithEmptyResult>>> firstFilterWithEmptyResultMap;
    public final Optional<ImmutableMap<String, String>> holdbacks;
    public final Optional<String> message;

    private UrlVendingException(Builder builder) {
        this.debInfo = Optional.fromNullable(builder.debInfo);
        this.holdbacks = Optional.fromNullable(builder.holdbacks);
        this.message = Optional.fromNullable(builder.message);
        this.firstFilterWithEmptyResultMap = Optional.fromNullable(builder.firstFilterWithEmptyResultMap);
        this.errorCode = Optional.fromNullable(builder.errorCode);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UrlVendingException)) {
            return false;
        }
        UrlVendingException urlVendingException = (UrlVendingException) obj;
        return Objects.equal(this.debInfo, urlVendingException.debInfo) && Objects.equal(this.holdbacks, urlVendingException.holdbacks) && Objects.equal(this.message, urlVendingException.message) && Objects.equal(this.firstFilterWithEmptyResultMap, urlVendingException.firstFilterWithEmptyResultMap) && Objects.equal(this.errorCode, urlVendingException.errorCode);
    }

    public int hashCode() {
        return Objects.hashCode(this.debInfo, this.holdbacks, this.message, this.firstFilterWithEmptyResultMap, this.errorCode);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("debInfo", this.debInfo).add("holdbacks", this.holdbacks).add("message", this.message).add("firstFilterWithEmptyResultMap", this.firstFilterWithEmptyResultMap).add("errorCode", this.errorCode).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, ImmutableList<DebStep>> debInfo;
        public ErrorCode errorCode;
        public ImmutableMap<String, ImmutableList<FirstFilterWithEmptyResult>> firstFilterWithEmptyResultMap;
        public ImmutableMap<String, String> holdbacks;
        public String message;

        public UrlVendingException build() {
            return new UrlVendingException(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<UrlVendingException> {
        private final MapParser<String, ImmutableList<DebStep>> mDebInfoMapParser;
        private final EnumParser<ErrorCode> mErrorCodeParser;
        private final MapParser<String, ImmutableList<FirstFilterWithEmptyResult>> mFirstFilterWithEmptyResultMapParser;
        private final MapParser<String, String> mHoldbacksMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mDebInfoMapParser = MapParser.newParser(stringParser, ListParser.newParser(new DebStep.Parser(objectMapper)));
            this.mHoldbacksMapParser = MapParser.newParser(stringParser, stringParser);
            this.mFirstFilterWithEmptyResultMapParser = MapParser.newParser(stringParser, ListParser.newParser(new FirstFilterWithEmptyResult.Parser(objectMapper)));
            this.mErrorCodeParser = EnumParser.newParser(ErrorCode.class);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public UrlVendingException parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlVendingException:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public UrlVendingException parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlVendingException:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlVendingException parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "UrlVendingException");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1585446912:
                            if (next.equals("firstFilterWithEmptyResultMap")) {
                                b = 3;
                            }
                            break;
                        case 329035797:
                            if (next.equals("errorCode")) {
                                b = 4;
                            }
                            break;
                        case 954925063:
                            if (next.equals("message")) {
                                b = 2;
                            }
                            break;
                        case 1204273933:
                            if (next.equals("holdbacks")) {
                                b = 1;
                            }
                            break;
                        case 1540387919:
                            if (next.equals("debInfo")) {
                                b = 0;
                            }
                            break;
                    }
                    ErrorCode errorCode = null;
                    ImmutableMap<String, ImmutableList<DebStep>> immutableMap = null;
                    ImmutableMap<String, String> immutableMap2 = null;
                    String str = null;
                    ImmutableMap<String, ImmutableList<FirstFilterWithEmptyResult>> immutableMap3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebInfoMapParser.parse(jsonNode2);
                        }
                        builder.debInfo = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mHoldbacksMapParser.parse(jsonNode2);
                        }
                        builder.holdbacks = immutableMap2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.message = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableMap3 = this.mFirstFilterWithEmptyResultMapParser.parse(jsonNode2);
                        }
                        builder.firstFilterWithEmptyResultMap = immutableMap3;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            errorCode = (ErrorCode) this.mErrorCodeParser.parse(jsonNode2);
                        }
                        builder.errorCode = errorCode;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing UrlVendingException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlVendingException parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1585446912:
                                    if (currentName.equals("firstFilterWithEmptyResultMap")) {
                                        b = 3;
                                    }
                                    break;
                                case 329035797:
                                    if (currentName.equals("errorCode")) {
                                        b = 4;
                                    }
                                    break;
                                case 954925063:
                                    if (currentName.equals("message")) {
                                        b = 2;
                                    }
                                    break;
                                case 1204273933:
                                    if (currentName.equals("holdbacks")) {
                                        b = 1;
                                    }
                                    break;
                                case 1540387919:
                                    if (currentName.equals("debInfo")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            ErrorCode errorCode = null;
                            ImmutableMap<String, ImmutableList<DebStep>> immutableMap = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            String str = null;
                            ImmutableMap<String, ImmutableList<FirstFilterWithEmptyResult>> immutableMap3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebInfoMapParser.parse(jsonParser);
                                }
                                builder.debInfo = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap2 = this.mHoldbacksMapParser.parse(jsonParser);
                                }
                                builder.holdbacks = immutableMap2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.message = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap3 = this.mFirstFilterWithEmptyResultMapParser.parse(jsonParser);
                                }
                                builder.firstFilterWithEmptyResultMap = immutableMap3;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    errorCode = (ErrorCode) this.mErrorCodeParser.parse(jsonParser);
                                }
                                builder.errorCode = errorCode;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing UrlVendingException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
