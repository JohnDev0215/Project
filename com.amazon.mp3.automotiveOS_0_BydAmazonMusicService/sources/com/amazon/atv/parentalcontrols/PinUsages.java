package com.amazon.atv.parentalcontrols;

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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PinUsages {
    public final boolean legacyRoku;
    public final boolean legacyWeb;
    public final boolean parentalControls;
    public final boolean purchase;

    private PinUsages(Builder builder) {
        this.parentalControls = builder.parentalControls;
        this.purchase = builder.purchase;
        this.legacyWeb = builder.legacyWeb;
        this.legacyRoku = builder.legacyRoku;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PinUsages)) {
            return false;
        }
        PinUsages pinUsages = (PinUsages) obj;
        return Objects.equal(Boolean.valueOf(this.parentalControls), Boolean.valueOf(pinUsages.parentalControls)) && Objects.equal(Boolean.valueOf(this.purchase), Boolean.valueOf(pinUsages.purchase)) && Objects.equal(Boolean.valueOf(this.legacyWeb), Boolean.valueOf(pinUsages.legacyWeb)) && Objects.equal(Boolean.valueOf(this.legacyRoku), Boolean.valueOf(pinUsages.legacyRoku));
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.parentalControls), Boolean.valueOf(this.purchase), Boolean.valueOf(this.legacyWeb), Boolean.valueOf(this.legacyRoku));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("parentalControls", this.parentalControls).add("purchase", this.purchase).add("legacyWeb", this.legacyWeb).add("legacyRoku", this.legacyRoku).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public boolean legacyRoku;
        public boolean legacyWeb;
        public boolean parentalControls;
        public boolean purchase;

        public PinUsages build() {
            return new PinUsages(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PinUsages> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PinUsages parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PinUsages:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PinUsages parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PinUsages:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PinUsages parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PinUsages");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 345037776:
                            if (next.equals("legacyRoku")) {
                                b = 3;
                            }
                            break;
                        case 790763563:
                            if (next.equals("parentalControls")) {
                                b = 0;
                            }
                            break;
                        case 1743324417:
                            if (next.equals("purchase")) {
                                b = 1;
                            }
                            break;
                        case 2089344715:
                            if (next.equals("legacyWeb")) {
                                b = 2;
                            }
                            break;
                    }
                    if (b == 0) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field parentalControls can't be null");
                        }
                        builder.parentalControls = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field purchase can't be null");
                        }
                        builder.purchase = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field legacyWeb can't be null");
                        }
                        builder.legacyWeb = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 3) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field legacyRoku can't be null");
                        }
                        builder.legacyRoku = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PinUsages so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PinUsages parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 345037776:
                                    if (currentName.equals("legacyRoku")) {
                                        b = 3;
                                    }
                                    break;
                                case 790763563:
                                    if (currentName.equals("parentalControls")) {
                                        b = 0;
                                    }
                                    break;
                                case 1743324417:
                                    if (currentName.equals("purchase")) {
                                        b = 1;
                                    }
                                    break;
                                case 2089344715:
                                    if (currentName.equals("legacyWeb")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            if (b == 0) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field parentalControls can't be null");
                                }
                                builder.parentalControls = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field purchase can't be null");
                                }
                                builder.purchase = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field legacyWeb can't be null");
                                }
                                builder.legacyWeb = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 3) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field legacyRoku can't be null");
                                }
                                builder.legacyRoku = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PinUsages so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
