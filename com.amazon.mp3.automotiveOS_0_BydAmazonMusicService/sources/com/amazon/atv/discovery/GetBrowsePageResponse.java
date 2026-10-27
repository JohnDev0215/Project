package com.amazon.atv.discovery;

import com.amazon.atv.discovery.BrowsePage;
import com.amazon.atv.discovery.DebugAttribute;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetBrowsePageResponse {
    public final BrowsePage browsePage;
    public final Optional<ImmutableMap<DebugType, ImmutableList<DebugAttribute>>> debugAttributes;

    private GetBrowsePageResponse(Builder builder) {
        this.browsePage = (BrowsePage) Preconditions.checkNotNull(builder.browsePage, "Unexpected null field: browsePage");
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetBrowsePageResponse)) {
            return false;
        }
        GetBrowsePageResponse getBrowsePageResponse = (GetBrowsePageResponse) obj;
        return Objects.equal(this.browsePage, getBrowsePageResponse.browsePage) && Objects.equal(this.debugAttributes, getBrowsePageResponse.debugAttributes);
    }

    public int hashCode() {
        return Objects.hashCode(this.browsePage, this.debugAttributes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("browsePage", this.browsePage).add("debugAttributes", this.debugAttributes).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public BrowsePage browsePage;
        public ImmutableMap<DebugType, ImmutableList<DebugAttribute>> debugAttributes;

        public GetBrowsePageResponse build() {
            return new GetBrowsePageResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetBrowsePageResponse> {
        private final BrowsePage.Parser mBrowsePageParser;
        private final MapParser<DebugType, ImmutableList<DebugAttribute>> mDebugAttributesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDebugAttributesParser = MapParser.newParser(EnumParser.newParser(DebugType.class), ListParser.newParser(new DebugAttribute.Parser(objectMapper)));
            this.mBrowsePageParser = new BrowsePage.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetBrowsePageResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetBrowsePageResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetBrowsePageResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetBrowsePageResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetBrowsePageResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetBrowsePageResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -164550599) {
                        if (iHashCode == 2140311562 && next.equals("debugAttributes")) {
                            b = 1;
                        }
                    } else if (next.equals("browsePage")) {
                        b = 0;
                    }
                    ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                    BrowsePage browsePage = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            browsePage = this.mBrowsePageParser.parse(jsonNode2);
                        }
                        builder.browsePage = browsePage;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebugAttributesParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetBrowsePageResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.browsePage, this, "browsePage");
            return builder.build();
        }

        @Nonnull
        private GetBrowsePageResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -164550599) {
                                if (iHashCode == 2140311562 && currentName.equals("debugAttributes")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("browsePage")) {
                                b = 0;
                            }
                            ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                            BrowsePage browsePage = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    browsePage = this.mBrowsePageParser.parse(jsonParser);
                                }
                                builder.browsePage = browsePage;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebugAttributesParser.parse(jsonParser);
                                }
                                builder.debugAttributes = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetBrowsePageResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.browsePage, this, "browsePage");
                    return builder.build();
                }
            }
        }
    }
}
