package com.amazon.atv.discovery;

import com.amazon.atv.discovery.BaseResponse;
import com.amazon.atv.discovery.DebugAttribute;
import com.amazon.atv.discovery.Widgets;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetWidgetsResponse extends BaseResponse {
    public final Widgets widgets;

    private GetWidgetsResponse(Builder builder) {
        super(builder);
        this.widgets = (Widgets) Preconditions.checkNotNull(builder.widgets, "Unexpected null field: widgets");
    }

    @Override // com.amazon.atv.discovery.BaseResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetWidgetsResponse) {
            return super.equals(obj) && Objects.equal(this.widgets, ((GetWidgetsResponse) obj).widgets);
        }
        return false;
    }

    @Override // com.amazon.atv.discovery.BaseResponse
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.widgets);
    }

    @Override // com.amazon.atv.discovery.BaseResponse
    public String toString() {
        return MoreObjects.toStringHelper(this).add("widgets", this.widgets).toString();
    }

    @NotThreadSafe
    public static class Builder extends BaseResponse.Builder {
        public Widgets widgets;

        public GetWidgetsResponse build() {
            return new GetWidgetsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetWidgetsResponse> {
        private final MapParser<DebugType, ImmutableList<DebugAttribute>> mDebugAttributesParser;
        private final Widgets.Parser mWidgetsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidgetsParser = new Widgets.Parser(objectMapper);
            this.mDebugAttributesParser = MapParser.newParser(EnumParser.newParser(DebugType.class), ListParser.newParser(new DebugAttribute.Parser(objectMapper)));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetWidgetsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetWidgetsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetWidgetsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetWidgetsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetWidgetsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetWidgetsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 1340337839) {
                        if (iHashCode == 2140311562 && next.equals("debugAttributes")) {
                            b = 1;
                        }
                    } else if (next.equals("widgets")) {
                        b = 0;
                    }
                    ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                    Widgets widgets = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            widgets = this.mWidgetsParser.parse(jsonNode2);
                        }
                        builder.widgets = widgets;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebugAttributesParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetWidgetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
            return builder.build();
        }

        @Nonnull
        private GetWidgetsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 1340337839) {
                                if (iHashCode == 2140311562 && currentName.equals("debugAttributes")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("widgets")) {
                                b = 0;
                            }
                            ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                            Widgets widgets = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    widgets = this.mWidgetsParser.parse(jsonParser);
                                }
                                builder.widgets = widgets;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebugAttributesParser.parse(jsonParser);
                                }
                                builder.debugAttributes = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetWidgetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
                    return builder.build();
                }
            }
        }
    }
}
