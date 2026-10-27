package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.TitleActions;
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
public class TitleActionsV1 extends TitleActions {
    public final boolean isPlayable;
    public final Optional<String> playbackSummary;
    public final String titleSummary;

    private TitleActionsV1(Builder builder) {
        super(builder);
        this.playbackSummary = Optional.fromNullable(builder.playbackSummary);
        this.titleSummary = (String) Preconditions.checkNotNull(builder.titleSummary, "Unexpected null field: titleSummary");
        this.isPlayable = builder.isPlayable;
    }

    @Override // com.amazon.atv.xrayv2.TitleActions
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleActionsV1)) {
            return false;
        }
        TitleActionsV1 titleActionsV1 = (TitleActionsV1) obj;
        return super.equals(obj) && Objects.equal(this.playbackSummary, titleActionsV1.playbackSummary) && Objects.equal(this.titleSummary, titleActionsV1.titleSummary) && Objects.equal(Boolean.valueOf(this.isPlayable), Boolean.valueOf(titleActionsV1.isPlayable));
    }

    @Override // com.amazon.atv.xrayv2.TitleActions
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.playbackSummary, this.titleSummary, Boolean.valueOf(this.isPlayable));
    }

    @Override // com.amazon.atv.xrayv2.TitleActions
    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackSummary", this.playbackSummary).add("titleSummary", this.titleSummary).add("isPlayable", this.isPlayable).toString();
    }

    @NotThreadSafe
    public static class Builder extends TitleActions.Builder {
        public boolean isPlayable;
        public String playbackSummary;
        public String titleSummary;

        public TitleActionsV1 build() {
            return new TitleActionsV1(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleActionsV1> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleActionsV1 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleActionsV1:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleActionsV1 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleActionsV1:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleActionsV1 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleActionsV1");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1127724498:
                            if (next.equals("titleSummary")) {
                                b = 2;
                            }
                            break;
                        case -509582069:
                            if (next.equals("playbackSummary")) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 1085869528:
                            if (next.equals("isPlayable")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playbackSummary = str;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleSummary = str;
                    } else if (b == 3) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field isPlayable can't be null");
                        }
                        builder.isPlayable = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleActionsV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.titleSummary, this, "titleSummary");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleActionsV1 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1127724498:
                                    if (currentName.equals("titleSummary")) {
                                        b = 2;
                                    }
                                    break;
                                case -509582069:
                                    if (currentName.equals("playbackSummary")) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 1085869528:
                                    if (currentName.equals("isPlayable")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.playbackSummary = str;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.titleSummary = str;
                            } else if (b == 3) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field isPlayable can't be null");
                                }
                                builder.isPlayable = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleActionsV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.titleSummary, this, "titleSummary");
                    return builder.build();
                }
            }
        }
    }
}
