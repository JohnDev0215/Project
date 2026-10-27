package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.TimeRange;
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
public class BlockedSegment {
    public final Optional<SegmentType> segmentType;
    public final Optional<TimeRange> timeRange;

    private BlockedSegment(Builder builder) {
        this.timeRange = Optional.fromNullable(builder.timeRange);
        this.segmentType = Optional.fromNullable(builder.segmentType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BlockedSegment)) {
            return false;
        }
        BlockedSegment blockedSegment = (BlockedSegment) obj;
        return Objects.equal(this.timeRange, blockedSegment.timeRange) && Objects.equal(this.segmentType, blockedSegment.segmentType);
    }

    public int hashCode() {
        return Objects.hashCode(this.timeRange, this.segmentType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("timeRange", this.timeRange).add("segmentType", this.segmentType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public SegmentType segmentType;
        public TimeRange timeRange;

        public BlockedSegment build() {
            return new BlockedSegment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BlockedSegment> {
        private final EnumParser<SegmentType> mSegmentTypeParser;
        private final TimeRange.Parser mTimeRangeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimeRangeParser = new TimeRange.Parser(objectMapper);
            this.mSegmentTypeParser = EnumParser.newParser(SegmentType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BlockedSegment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BlockedSegment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BlockedSegment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BlockedSegment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private BlockedSegment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "BlockedSegment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -952905459) {
                        if (iHashCode == 24096368 && next.equals("timeRange")) {
                            b = 0;
                        }
                    } else if (next.equals("segmentType")) {
                        b = 1;
                    }
                    SegmentType segmentType = null;
                    TimeRange timeRange = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            timeRange = this.mTimeRangeParser.parse(jsonNode2);
                        }
                        builder.timeRange = timeRange;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            segmentType = (SegmentType) this.mSegmentTypeParser.parse(jsonNode2);
                        }
                        builder.segmentType = segmentType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BlockedSegment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private BlockedSegment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -952905459) {
                                if (iHashCode == 24096368 && currentName.equals("timeRange")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("segmentType")) {
                                b = 1;
                            }
                            SegmentType segmentType = null;
                            TimeRange timeRange = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeRange = this.mTimeRangeParser.parse(jsonParser);
                                }
                                builder.timeRange = timeRange;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    segmentType = (SegmentType) this.mSegmentTypeParser.parse(jsonParser);
                                }
                                builder.segmentType = segmentType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BlockedSegment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
