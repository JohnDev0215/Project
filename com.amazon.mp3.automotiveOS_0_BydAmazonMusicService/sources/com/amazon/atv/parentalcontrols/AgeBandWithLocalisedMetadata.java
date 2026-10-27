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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AgeBandWithLocalisedMetadata {
    public final Optional<String> ageBandIdentifier;
    public final boolean isSelectable;
    public final Optional<String> localisedDescription;
    public final Optional<String> localisedName;

    private AgeBandWithLocalisedMetadata(Builder builder) {
        this.isSelectable = builder.isSelectable;
        this.ageBandIdentifier = Optional.fromNullable(builder.ageBandIdentifier);
        this.localisedName = Optional.fromNullable(builder.localisedName);
        this.localisedDescription = Optional.fromNullable(builder.localisedDescription);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AgeBandWithLocalisedMetadata)) {
            return false;
        }
        AgeBandWithLocalisedMetadata ageBandWithLocalisedMetadata = (AgeBandWithLocalisedMetadata) obj;
        return Objects.equal(Boolean.valueOf(this.isSelectable), Boolean.valueOf(ageBandWithLocalisedMetadata.isSelectable)) && Objects.equal(this.ageBandIdentifier, ageBandWithLocalisedMetadata.ageBandIdentifier) && Objects.equal(this.localisedName, ageBandWithLocalisedMetadata.localisedName) && Objects.equal(this.localisedDescription, ageBandWithLocalisedMetadata.localisedDescription);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.isSelectable), this.ageBandIdentifier, this.localisedName, this.localisedDescription);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("isSelectable", this.isSelectable).add("ageBandIdentifier", this.ageBandIdentifier).add("localisedName", this.localisedName).add("localisedDescription", this.localisedDescription).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String ageBandIdentifier;
        public boolean isSelectable;
        public String localisedDescription;
        public String localisedName;

        public AgeBandWithLocalisedMetadata build() {
            return new AgeBandWithLocalisedMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AgeBandWithLocalisedMetadata> {
        private final SimpleParsers.StringParser mageBandIdentifierParser;
        private final SimpleParsers.StringParser mlocalisedDescriptionParser;
        private final SimpleParsers.StringParser mlocalisedNameParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mageBandIdentifierParser = stringParser;
            this.mlocalisedNameParser = stringParser;
            this.mlocalisedDescriptionParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AgeBandWithLocalisedMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AgeBandWithLocalisedMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AgeBandWithLocalisedMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AgeBandWithLocalisedMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AgeBandWithLocalisedMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AgeBandWithLocalisedMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1221009953:
                            if (next.equals("localisedName")) {
                                b = 2;
                            }
                            break;
                        case -294179939:
                            if (next.equals("ageBandIdentifier")) {
                                b = 1;
                            }
                            break;
                        case 214792744:
                            if (next.equals("localisedDescription")) {
                                b = 3;
                            }
                            break;
                        case 515696928:
                            if (next.equals("isSelectable")) {
                                b = 0;
                            }
                            break;
                    }
                    if (b != 0) {
                        String str = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                str = this.mageBandIdentifierParser.parse(jsonNode2);
                            }
                            builder.ageBandIdentifier = str;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                str = this.mlocalisedNameParser.parse(jsonNode2);
                            }
                            builder.localisedName = str;
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                str = this.mlocalisedDescriptionParser.parse(jsonNode2);
                            }
                            builder.localisedDescription = str;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field isSelectable can't be null");
                        }
                        builder.isSelectable = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AgeBandWithLocalisedMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AgeBandWithLocalisedMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1221009953:
                                    if (currentName.equals("localisedName")) {
                                        b = 2;
                                    }
                                    break;
                                case -294179939:
                                    if (currentName.equals("ageBandIdentifier")) {
                                        b = 1;
                                    }
                                    break;
                                case 214792744:
                                    if (currentName.equals("localisedDescription")) {
                                        b = 3;
                                    }
                                    break;
                                case 515696928:
                                    if (currentName.equals("isSelectable")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            if (b != 0) {
                                String str = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mageBandIdentifierParser.parse(jsonParser);
                                    }
                                    builder.ageBandIdentifier = str;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mlocalisedNameParser.parse(jsonParser);
                                    }
                                    builder.localisedName = str;
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mlocalisedDescriptionParser.parse(jsonParser);
                                    }
                                    builder.localisedDescription = str;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field isSelectable can't be null");
                                }
                                builder.isSelectable = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AgeBandWithLocalisedMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
