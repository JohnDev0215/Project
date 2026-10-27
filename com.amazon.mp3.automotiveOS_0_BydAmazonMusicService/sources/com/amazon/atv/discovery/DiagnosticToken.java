package com.amazon.atv.discovery;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticToken {
    public final Optional<String> source;
    public final Optional<ByteBuffer> token;

    private DiagnosticToken(Builder builder) {
        this.token = Optional.fromNullable(builder.token);
        this.source = Optional.fromNullable(builder.source);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DiagnosticToken)) {
            return false;
        }
        DiagnosticToken diagnosticToken = (DiagnosticToken) obj;
        return Objects.equal(this.token, diagnosticToken.token) && Objects.equal(this.source, diagnosticToken.source);
    }

    public int hashCode() {
        return Objects.hashCode(this.token, this.source);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("token", this.token).add(PageUriUtils.SOURCE_QUERY_PARAM, this.source).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String source;
        public ByteBuffer token;

        public DiagnosticToken build() {
            return new DiagnosticToken(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DiagnosticToken> {
        private final SimpleParsers.BlobParser mBlobParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBlobParser = SimpleParsers.BlobParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DiagnosticToken parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DiagnosticToken:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DiagnosticToken parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DiagnosticToken:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DiagnosticToken parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DiagnosticToken");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -896505829) {
                        if (iHashCode == 110541305 && next.equals("token")) {
                            b = 0;
                        }
                    } else if (next.equals(PageUriUtils.SOURCE_QUERY_PARAM)) {
                        b = 1;
                    }
                    String str = null;
                    ByteBuffer byteBuffer = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mBlobParser.parse(jsonNode2);
                        }
                        builder.token = byteBuffer;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.source = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DiagnosticToken so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DiagnosticToken parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -896505829) {
                                if (iHashCode == 110541305 && currentName.equals("token")) {
                                    b = 0;
                                }
                            } else if (currentName.equals(PageUriUtils.SOURCE_QUERY_PARAM)) {
                                b = 1;
                            }
                            String str = null;
                            ByteBuffer byteBuffer = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    byteBuffer = this.mBlobParser.parse(jsonParser);
                                }
                                builder.token = byteBuffer;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.source = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DiagnosticToken so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
