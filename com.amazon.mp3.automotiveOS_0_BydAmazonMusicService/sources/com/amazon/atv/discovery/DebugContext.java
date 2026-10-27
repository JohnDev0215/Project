package com.amazon.atv.discovery;

import com.amazon.atv.discovery.AutobotDebugContext;
import com.amazon.atv.discovery.WPODebugContext;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class DebugContext {
    public final Optional<AutobotDebugContext> autobotDebugContext;
    public final Optional<DebugLevel> debugLevel;
    public final Optional<WPODebugContext> wpoDebugContext;

    private DebugContext(Builder builder) {
        this.wpoDebugContext = Optional.fromNullable(builder.wpoDebugContext);
        this.autobotDebugContext = Optional.fromNullable(builder.autobotDebugContext);
        this.debugLevel = Optional.fromNullable(builder.debugLevel);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DebugContext)) {
            return false;
        }
        DebugContext debugContext = (DebugContext) obj;
        return Objects.equal(this.wpoDebugContext, debugContext.wpoDebugContext) && Objects.equal(this.autobotDebugContext, debugContext.autobotDebugContext) && Objects.equal(this.debugLevel, debugContext.debugLevel);
    }

    public int hashCode() {
        return Objects.hashCode(this.wpoDebugContext, this.autobotDebugContext, this.debugLevel);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("wpoDebugContext", this.wpoDebugContext).add("autobotDebugContext", this.autobotDebugContext).add("debugLevel", this.debugLevel).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AutobotDebugContext autobotDebugContext;
        public DebugLevel debugLevel;
        public WPODebugContext wpoDebugContext;

        public DebugContext build() {
            return new DebugContext(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DebugContext> {
        private final AutobotDebugContext.Parser mAutobotDebugContextParser;
        private final EnumParser<DebugLevel> mDebugLevelParser;
        private final WPODebugContext.Parser mWPODebugContextParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWPODebugContextParser = new WPODebugContext.Parser(objectMapper);
            this.mDebugLevelParser = EnumParser.newParser(DebugLevel.class);
            this.mAutobotDebugContextParser = new AutobotDebugContext.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DebugContext parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DebugContext:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DebugContext parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DebugContext:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DebugContext parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DebugContext");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1858203407) {
                        if (iHashCode != 5411252) {
                            if (iHashCode == 2033042354 && next.equals("wpoDebugContext")) {
                                b = 0;
                            }
                        } else if (next.equals("autobotDebugContext")) {
                            b = 1;
                        }
                    } else if (next.equals("debugLevel")) {
                        b = 2;
                    }
                    DebugLevel debugLevel = null;
                    WPODebugContext wPODebugContext = null;
                    AutobotDebugContext autobotDebugContext = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            wPODebugContext = this.mWPODebugContextParser.parse(jsonNode2);
                        }
                        builder.wpoDebugContext = wPODebugContext;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            autobotDebugContext = this.mAutobotDebugContextParser.parse(jsonNode2);
                        }
                        builder.autobotDebugContext = autobotDebugContext;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            debugLevel = (DebugLevel) this.mDebugLevelParser.parse(jsonNode2);
                        }
                        builder.debugLevel = debugLevel;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DebugContext so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DebugContext parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1858203407) {
                                if (iHashCode != 5411252) {
                                    if (iHashCode == 2033042354 && currentName.equals("wpoDebugContext")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("autobotDebugContext")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("debugLevel")) {
                                b = 2;
                            }
                            DebugLevel debugLevel = null;
                            WPODebugContext wPODebugContext = null;
                            AutobotDebugContext autobotDebugContext = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    wPODebugContext = this.mWPODebugContextParser.parse(jsonParser);
                                }
                                builder.wpoDebugContext = wPODebugContext;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    autobotDebugContext = this.mAutobotDebugContextParser.parse(jsonParser);
                                }
                                builder.autobotDebugContext = autobotDebugContext;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    debugLevel = (DebugLevel) this.mDebugLevelParser.parse(jsonParser);
                                }
                                builder.debugLevel = debugLevel;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DebugContext so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
