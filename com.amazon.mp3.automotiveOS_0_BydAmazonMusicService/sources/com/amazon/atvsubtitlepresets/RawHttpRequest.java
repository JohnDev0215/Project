package com.amazon.atvsubtitlepresets;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RawHttpRequest {
    public final Optional<ByteBuffer> body;
    public final Optional<ImmutableMap<String, String>> parameters;
    public final Optional<String> uri;

    private RawHttpRequest(Builder builder) {
        this.parameters = Optional.fromNullable(builder.parameters);
        this.uri = Optional.fromNullable(builder.uri);
        this.body = Optional.fromNullable(builder.body);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawHttpRequest)) {
            return false;
        }
        RawHttpRequest rawHttpRequest = (RawHttpRequest) obj;
        return Objects.equal(this.parameters, rawHttpRequest.parameters) && Objects.equal(this.uri, rawHttpRequest.uri) && Objects.equal(this.body, rawHttpRequest.body);
    }

    public int hashCode() {
        return Objects.hashCode(this.parameters, this.uri, this.body);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("parameters", this.parameters).add("uri", this.uri).add("body", this.body).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ByteBuffer body;
        public ImmutableMap<String, String> parameters;
        public String uri;

        public RawHttpRequest build() {
            return new RawHttpRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RawHttpRequest> {
        private final SimpleParsers.BlobParser mbodyParser;
        private final MapParser<String, String> mparametersParser;
        private final SimpleParsers.StringParser muriParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mparametersParser = MapParser.newParser(stringParser, stringParser);
            this.mbodyParser = SimpleParsers.BlobParser.INSTANCE;
            this.muriParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RawHttpRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RawHttpRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RawHttpRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RawHttpRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RawHttpRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RawHttpRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 116076) {
                        if (iHashCode != 3029410) {
                            if (iHashCode == 458736106 && next.equals("parameters")) {
                                b = 0;
                            }
                        } else if (next.equals("body")) {
                            b = 2;
                        }
                    } else if (next.equals("uri")) {
                        b = 1;
                    }
                    ByteBuffer byteBuffer = null;
                    ImmutableMap<String, String> immutableMap = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mparametersParser.parse(jsonNode2);
                        }
                        builder.parameters = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.muriParser.parse(jsonNode2);
                        }
                        builder.uri = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mbodyParser.parse(jsonNode2);
                        }
                        builder.body = byteBuffer;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RawHttpRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private RawHttpRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 116076) {
                                if (iHashCode != 3029410) {
                                    if (iHashCode == 458736106 && currentName.equals("parameters")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("body")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("uri")) {
                                b = 1;
                            }
                            ByteBuffer byteBuffer = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mparametersParser.parse(jsonParser);
                                }
                                builder.parameters = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.muriParser.parse(jsonParser);
                                }
                                builder.uri = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    byteBuffer = this.mbodyParser.parse(jsonParser);
                                }
                                builder.body = byteBuffer;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RawHttpRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
