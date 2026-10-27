package com.amazon.atv.discovery;

import com.amazon.atv.discovery.DebugAttribute;
import com.amazon.atv.discovery.Page;
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
public class GetLandingPageResponse {
    public final Optional<ImmutableMap<DebugType, ImmutableList<DebugAttribute>>> debugAttributes;
    public final Page landingPage;

    private GetLandingPageResponse(Builder builder) {
        this.landingPage = (Page) Preconditions.checkNotNull(builder.landingPage, "Unexpected null field: landingPage");
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLandingPageResponse)) {
            return false;
        }
        GetLandingPageResponse getLandingPageResponse = (GetLandingPageResponse) obj;
        return Objects.equal(this.landingPage, getLandingPageResponse.landingPage) && Objects.equal(this.debugAttributes, getLandingPageResponse.debugAttributes);
    }

    public int hashCode() {
        return Objects.hashCode(this.landingPage, this.debugAttributes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("landingPage", this.landingPage).add("debugAttributes", this.debugAttributes).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<DebugType, ImmutableList<DebugAttribute>> debugAttributes;
        public Page landingPage;

        public GetLandingPageResponse build() {
            return new GetLandingPageResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLandingPageResponse> {
        private final MapParser<DebugType, ImmutableList<DebugAttribute>> mDebugAttributesParser;
        private final Page.Parser mPageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPageParser = new Page.Parser(objectMapper);
            this.mDebugAttributesParser = MapParser.newParser(EnumParser.newParser(DebugType.class), ListParser.newParser(new DebugAttribute.Parser(objectMapper)));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLandingPageResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLandingPageResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLandingPageResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLandingPageResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLandingPageResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLandingPageResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 497102150) {
                        if (iHashCode == 2140311562 && next.equals("debugAttributes")) {
                            b = 1;
                        }
                    } else if (next.equals("landingPage")) {
                        b = 0;
                    }
                    ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                    Page page = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            page = this.mPageParser.parse(jsonNode2);
                        }
                        builder.landingPage = page;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebugAttributesParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLandingPageResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.landingPage, this, "landingPage");
            return builder.build();
        }

        @Nonnull
        private GetLandingPageResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 497102150) {
                                if (iHashCode == 2140311562 && currentName.equals("debugAttributes")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("landingPage")) {
                                b = 0;
                            }
                            ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                            Page page = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    page = this.mPageParser.parse(jsonParser);
                                }
                                builder.landingPage = page;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebugAttributesParser.parse(jsonParser);
                                }
                                builder.debugAttributes = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLandingPageResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.landingPage, this, "landingPage");
                    return builder.build();
                }
            }
        }
    }
}
