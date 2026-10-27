package com.amazon.dv.xray.game.runtime.service.shared;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.dv.xray.game.runtime.service.shared.NavigationalParams;
import com.amazon.dv.xray.game.runtime.service.shared.PublishedUserState;
import com.amazon.dv.xray.game.runtime.service.shared.XRayId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetGameTabInputPayload {
    public final NavigationalParams navigationalParams;
    public final PublishedUserState publishedUserState;
    public final XRayId xrayId;

    private GetGameTabInputPayload(Builder builder) {
        this.publishedUserState = (PublishedUserState) Preconditions.checkNotNull(builder.publishedUserState, "Unexpected null field: publishedUserState");
        this.navigationalParams = (NavigationalParams) Preconditions.checkNotNull(builder.navigationalParams, "Unexpected null field: navigationalParams");
        this.xrayId = (XRayId) Preconditions.checkNotNull(builder.xrayId, "Unexpected null field: xrayId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGameTabInputPayload)) {
            return false;
        }
        GetGameTabInputPayload getGameTabInputPayload = (GetGameTabInputPayload) obj;
        return Objects.equal(this.publishedUserState, getGameTabInputPayload.publishedUserState) && Objects.equal(this.navigationalParams, getGameTabInputPayload.navigationalParams) && Objects.equal(this.xrayId, getGameTabInputPayload.xrayId);
    }

    public int hashCode() {
        return Objects.hashCode(this.publishedUserState, this.navigationalParams, this.xrayId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("publishedUserState", this.publishedUserState).add("navigationalParams", this.navigationalParams).add("xrayId", this.xrayId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public NavigationalParams navigationalParams;
        public PublishedUserState publishedUserState;
        public XRayId xrayId;

        public GetGameTabInputPayload build() {
            return new GetGameTabInputPayload(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetGameTabInputPayload> {
        private final NavigationalParams.Parser mNavigationalParamsParser;
        private final PublishedUserState.Parser mPublishedUserStateParser;
        private final XRayId.Parser mXRayIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPublishedUserStateParser = new PublishedUserState.Parser(objectMapper);
            this.mXRayIdParser = new XRayId.Parser(objectMapper);
            this.mNavigationalParamsParser = new NavigationalParams.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetGameTabInputPayload parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabInputPayload:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetGameTabInputPayload parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabInputPayload:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetGameTabInputPayload parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetGameTabInputPayload");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2043176027) {
                        if (iHashCode != -1093904072) {
                            if (iHashCode == -751179411 && next.equals("xrayId")) {
                                b = 2;
                            }
                        } else if (next.equals("publishedUserState")) {
                            b = 0;
                        }
                    } else if (next.equals("navigationalParams")) {
                        b = 1;
                    }
                    XRayId xRayId = null;
                    PublishedUserState publishedUserState = null;
                    NavigationalParams navigationalParams = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            publishedUserState = this.mPublishedUserStateParser.parse(jsonNode2);
                        }
                        builder.publishedUserState = publishedUserState;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            navigationalParams = this.mNavigationalParamsParser.parse(jsonNode2);
                        }
                        builder.navigationalParams = navigationalParams;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            xRayId = this.mXRayIdParser.parse(jsonNode2);
                        }
                        builder.xrayId = xRayId;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetGameTabInputPayload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.publishedUserState, this, "publishedUserState");
            JsonParsingUtils.checkNotNull(builder.navigationalParams, this, "navigationalParams");
            JsonParsingUtils.checkNotNull(builder.xrayId, this, "xrayId");
            return builder.build();
        }

        @Nonnull
        private GetGameTabInputPayload parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2043176027) {
                                if (iHashCode != -1093904072) {
                                    if (iHashCode == -751179411 && currentName.equals("xrayId")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("publishedUserState")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("navigationalParams")) {
                                b = 1;
                            }
                            XRayId xRayId = null;
                            PublishedUserState publishedUserState = null;
                            NavigationalParams navigationalParams = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    publishedUserState = this.mPublishedUserStateParser.parse(jsonParser);
                                }
                                builder.publishedUserState = publishedUserState;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    navigationalParams = this.mNavigationalParamsParser.parse(jsonParser);
                                }
                                builder.navigationalParams = navigationalParams;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    xRayId = this.mXRayIdParser.parse(jsonParser);
                                }
                                builder.xrayId = xRayId;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetGameTabInputPayload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.publishedUserState, this, "publishedUserState");
                    JsonParsingUtils.checkNotNull(builder.navigationalParams, this, "navigationalParams");
                    JsonParsingUtils.checkNotNull(builder.xrayId, this, "xrayId");
                    return builder.build();
                }
            }
        }
    }
}
