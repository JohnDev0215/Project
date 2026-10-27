package com.amazon.atvplaybackresource;

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
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Callback {
    public final Optional<Long> delayMillis;
    public final Optional<String> url;

    private Callback(Builder builder) {
        this.url = Optional.fromNullable(builder.url);
        this.delayMillis = Optional.fromNullable(builder.delayMillis);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Callback)) {
            return false;
        }
        Callback callback = (Callback) obj;
        return Objects.equal(this.url, callback.url) && Objects.equal(this.delayMillis, callback.delayMillis);
    }

    public int hashCode() {
        return Objects.hashCode(this.url, this.delayMillis);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(ImagesContract.URL, this.url).add("delayMillis", this.delayMillis).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long delayMillis;
        public String url;

        public Callback build() {
            return new Callback(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Callback> {
        private final SimpleParsers.LongParser mlongTargetParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
            this.mlongTargetParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Callback parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Callback:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Callback parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Callback:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Callback parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Callback");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -38678103) {
                        if (iHashCode == 116079 && next.equals(ImagesContract.URL)) {
                            b = 0;
                        }
                    } else if (next.equals("delayMillis")) {
                        b = 1;
                    }
                    Long l = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.url = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            l = this.mlongTargetParser.parse(jsonNode2);
                        }
                        builder.delayMillis = l;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Callback so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Callback parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -38678103) {
                                if (iHashCode == 116079 && currentName.equals(ImagesContract.URL)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("delayMillis")) {
                                b = 1;
                            }
                            Long l = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.url = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mlongTargetParser.parse(jsonParser);
                                }
                                builder.delayMillis = l;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Callback so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
