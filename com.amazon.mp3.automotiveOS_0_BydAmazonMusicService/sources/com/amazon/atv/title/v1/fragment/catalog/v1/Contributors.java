package com.amazon.atv.title.v1.fragment.catalog.v1;

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
public class Contributors {
    public final Optional<ImmutableList<String>> directors;
    public final Optional<ImmutableList<String>> producers;
    public final Optional<ImmutableList<String>> starringCast;
    public final Optional<ImmutableList<String>> supportingCast;

    private Contributors(Builder builder) {
        this.starringCast = Optional.fromNullable(builder.starringCast);
        this.producers = Optional.fromNullable(builder.producers);
        this.supportingCast = Optional.fromNullable(builder.supportingCast);
        this.directors = Optional.fromNullable(builder.directors);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Contributors)) {
            return false;
        }
        Contributors contributors = (Contributors) obj;
        return Objects.equal(this.starringCast, contributors.starringCast) && Objects.equal(this.producers, contributors.producers) && Objects.equal(this.supportingCast, contributors.supportingCast) && Objects.equal(this.directors, contributors.directors);
    }

    public int hashCode() {
        return Objects.hashCode(this.starringCast, this.producers, this.supportingCast, this.directors);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("starringCast", this.starringCast).add("producers", this.producers).add("supportingCast", this.supportingCast).add("directors", this.directors).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> directors;
        public ImmutableList<String> producers;
        public ImmutableList<String> starringCast;
        public ImmutableList<String> supportingCast;

        public Contributors build() {
            return new Contributors(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Contributors> {
        private final ListParser<String> mStringListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringListParser = ListParser.newParser(SimpleParsers.StringParser.INSTANCE);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Contributors parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Contributors:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Contributors parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Contributors:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Contributors parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Contributors");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1051843807:
                            if (next.equals("producers")) {
                                b = 1;
                            }
                            break;
                        case -962584985:
                            if (next.equals("directors")) {
                                b = 3;
                            }
                            break;
                        case 955274721:
                            if (next.equals("starringCast")) {
                                b = 0;
                            }
                            break;
                        case 1922215826:
                            if (next.equals("supportingCast")) {
                                b = 2;
                            }
                            break;
                    }
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.starringCast = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.producers = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.supportingCast = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.directors = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Contributors so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Contributors parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1051843807:
                                    if (currentName.equals("producers")) {
                                        b = 1;
                                    }
                                    break;
                                case -962584985:
                                    if (currentName.equals("directors")) {
                                        b = 3;
                                    }
                                    break;
                                case 955274721:
                                    if (currentName.equals("starringCast")) {
                                        b = 0;
                                    }
                                    break;
                                case 1922215826:
                                    if (currentName.equals("supportingCast")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.starringCast = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.producers = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.supportingCast = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.directors = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Contributors so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
