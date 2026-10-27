package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.InternationalCustomerRequest;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetLinearRestrictionsRequest extends InternationalCustomerRequest {
    public final String channelId;
    public final Long endTime;
    public final Long startTime;

    private GetLinearRestrictionsRequest(Builder builder) {
        super(builder);
        this.endTime = (Long) Preconditions.checkNotNull(builder.endTime, "Unexpected null field: endTime");
        this.channelId = (String) Preconditions.checkNotNull(builder.channelId, "Unexpected null field: channelId");
        this.startTime = (Long) Preconditions.checkNotNull(builder.startTime, "Unexpected null field: startTime");
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLinearRestrictionsRequest)) {
            return false;
        }
        GetLinearRestrictionsRequest getLinearRestrictionsRequest = (GetLinearRestrictionsRequest) obj;
        return super.equals(obj) && Objects.equal(this.endTime, getLinearRestrictionsRequest.endTime) && Objects.equal(this.channelId, getLinearRestrictionsRequest.channelId) && Objects.equal(this.startTime, getLinearRestrictionsRequest.startTime);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.endTime, this.channelId, this.startTime);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("endTime", this.endTime).add("channelId", this.channelId).add("startTime", this.startTime).toString();
    }

    @NotThreadSafe
    public static class Builder extends InternationalCustomerRequest.Builder {
        public String channelId;
        public Long endTime;
        public Long startTime;

        public GetLinearRestrictionsRequest build() {
            return new GetLinearRestrictionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLinearRestrictionsRequest> {
        private final SimpleParsers.LongParser mepochMilliParser;
        private final SimpleParsers.StringParser midentifierParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mepochMilliParser = SimpleParsers.LongParser.INSTANCE;
            this.midentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLinearRestrictionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLinearRestrictionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLinearRestrictionsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLinearRestrictionsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2129294769) {
                        if (iHashCode != -1607243192) {
                            if (iHashCode == 1461735806 && next.equals("channelId")) {
                                b = 1;
                            }
                        } else if (next.equals("endTime")) {
                            b = 0;
                        }
                    } else if (next.equals("startTime")) {
                        b = 2;
                    }
                    Long l = null;
                    Long l2 = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.mepochMilliParser.parse(jsonNode2);
                        }
                        builder.endTime = l2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentifierParser.parse(jsonNode2);
                        }
                        builder.channelId = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            l = this.mepochMilliParser.parse(jsonNode2);
                        }
                        builder.startTime = l;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLinearRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.endTime, this, "endTime");
            JsonParsingUtils.checkNotNull(builder.channelId, this, "channelId");
            JsonParsingUtils.checkNotNull(builder.startTime, this, "startTime");
            return builder.build();
        }

        @Nonnull
        private GetLinearRestrictionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2129294769) {
                                if (iHashCode != -1607243192) {
                                    if (iHashCode == 1461735806 && currentName.equals("channelId")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("endTime")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("startTime")) {
                                b = 2;
                            }
                            Long l = null;
                            Long l2 = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.mepochMilliParser.parse(jsonParser);
                                }
                                builder.endTime = l2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentifierParser.parse(jsonParser);
                                }
                                builder.channelId = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mepochMilliParser.parse(jsonParser);
                                }
                                builder.startTime = l;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLinearRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.endTime, this, "endTime");
                    JsonParsingUtils.checkNotNull(builder.channelId, this, "channelId");
                    JsonParsingUtils.checkNotNull(builder.startTime, this, "startTime");
                    return builder.build();
                }
            }
        }
    }
}
