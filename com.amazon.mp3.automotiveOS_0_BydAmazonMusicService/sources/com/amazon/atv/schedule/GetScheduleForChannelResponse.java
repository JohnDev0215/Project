package com.amazon.atv.schedule;

import com.amazon.atv.schedule.ChannelSchedule;
import com.amazon.atv.schedule.ScheduleResponse;
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
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetScheduleForChannelResponse extends ScheduleResponse {
    public final Optional<ImmutableList<ChannelSchedule>> result;

    private GetScheduleForChannelResponse(Builder builder) {
        super(builder);
        this.result = Optional.fromNullable(builder.result);
    }

    @Override // com.amazon.atv.schedule.ScheduleResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetScheduleForChannelResponse) {
            return super.equals(obj) && Objects.equal(this.result, ((GetScheduleForChannelResponse) obj).result);
        }
        return false;
    }

    @Override // com.amazon.atv.schedule.ScheduleResponse
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.result);
    }

    @Override // com.amazon.atv.schedule.ScheduleResponse
    public String toString() {
        return MoreObjects.toStringHelper(this).add("result", this.result).toString();
    }

    @NotThreadSafe
    public static class Builder extends ScheduleResponse.Builder {
        public ImmutableList<ChannelSchedule> result;

        public GetScheduleForChannelResponse build() {
            return new GetScheduleForChannelResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetScheduleForChannelResponse> {
        private final ListParser<ChannelSchedule> mChannelScheduleListParser;
        private final SimpleParsers.DateParser mTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mChannelScheduleListParser = ListParser.newParser(new ChannelSchedule.Parser(objectMapper));
            this.mTimestampParser = SimpleParsers.DateParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetScheduleForChannelResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetScheduleForChannelResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetScheduleForChannelResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetScheduleForChannelResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetScheduleForChannelResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetScheduleForChannelResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1289159373) {
                        if (iHashCode != -934426595) {
                            if (iHashCode == 109270 && next.equals("now")) {
                                b = 2;
                            }
                        } else if (next.equals("result")) {
                            b = 0;
                        }
                    } else if (next.equals("expiry")) {
                        b = 1;
                    }
                    Date date = null;
                    ImmutableList<ChannelSchedule> immutableList = null;
                    Date date2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mChannelScheduleListParser.parse(jsonNode2);
                        }
                        builder.result = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            date2 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.expiry = date2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.now = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetScheduleForChannelResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetScheduleForChannelResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1289159373) {
                                if (iHashCode != -934426595) {
                                    if (iHashCode == 109270 && currentName.equals("now")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("result")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("expiry")) {
                                b = 1;
                            }
                            Date date = null;
                            ImmutableList<ChannelSchedule> immutableList = null;
                            Date date2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mChannelScheduleListParser.parse(jsonParser);
                                }
                                builder.result = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date2 = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.expiry = date2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.now = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetScheduleForChannelResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
