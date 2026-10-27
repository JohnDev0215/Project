package com.amazon.dv.xray.game.runtime.service.generated;

import com.amazon.atv.xrayv2.PollingAction;
import com.amazon.atv.xrayv2.XRayActionsCollection;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class GetGameTabOutput {
    public final Optional<PollingAction> pollingAction;
    public final Optional<XRayActionsCollection> xrayActions;

    private GetGameTabOutput(Builder builder) {
        this.xrayActions = Optional.fromNullable(builder.xrayActions);
        this.pollingAction = Optional.fromNullable(builder.pollingAction);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGameTabOutput)) {
            return false;
        }
        GetGameTabOutput getGameTabOutput = (GetGameTabOutput) obj;
        return Objects.equal(this.xrayActions, getGameTabOutput.xrayActions) && Objects.equal(this.pollingAction, getGameTabOutput.pollingAction);
    }

    public int hashCode() {
        return Objects.hashCode(this.xrayActions, this.pollingAction);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("xrayActions", this.xrayActions).add("pollingAction", this.pollingAction).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PollingAction pollingAction;
        public XRayActionsCollection xrayActions;

        public GetGameTabOutput build() {
            return new GetGameTabOutput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetGameTabOutput> {
        private final PollingAction.Parser mPollingActionParser;
        private final XRayActionsCollection.Parser mXRayActionsCollectionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPollingActionParser = new PollingAction.Parser(objectMapper);
            this.mXRayActionsCollectionParser = new XRayActionsCollection.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetGameTabOutput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabOutput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetGameTabOutput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabOutput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetGameTabOutput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetGameTabOutput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1093025831) {
                        if (iHashCode == -113422357 && next.equals("xrayActions")) {
                            b = 0;
                        }
                    } else if (next.equals("pollingAction")) {
                        b = 1;
                    }
                    PollingAction pollingAction = null;
                    XRayActionsCollection xRayActionsCollection = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            xRayActionsCollection = this.mXRayActionsCollectionParser.parse(jsonNode2);
                        }
                        builder.xrayActions = xRayActionsCollection;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pollingAction = this.mPollingActionParser.parse(jsonNode2);
                        }
                        builder.pollingAction = pollingAction;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetGameTabOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetGameTabOutput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1093025831) {
                                if (iHashCode == -113422357 && currentName.equals("xrayActions")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("pollingAction")) {
                                b = 1;
                            }
                            PollingAction pollingAction = null;
                            XRayActionsCollection xRayActionsCollection = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    xRayActionsCollection = this.mXRayActionsCollectionParser.parse(jsonParser);
                                }
                                builder.xrayActions = xRayActionsCollection;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pollingAction = this.mPollingActionParser.parse(jsonParser);
                                }
                                builder.pollingAction = pollingAction;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetGameTabOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
