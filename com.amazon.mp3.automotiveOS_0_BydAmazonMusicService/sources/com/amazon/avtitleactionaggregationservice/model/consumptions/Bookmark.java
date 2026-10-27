package com.amazon.avtitleactionaggregationservice.model.consumptions;

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
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Bookmark {
    public final Optional<Long> startPos;
    public final Optional<Date> startPosEpoch;
    public final Optional<Long> totalLength;
    public final Optional<Date> updateTime;

    private Bookmark(Builder builder) {
        this.startPosEpoch = Optional.fromNullable(builder.startPosEpoch);
        this.startPos = Optional.fromNullable(builder.startPos);
        this.updateTime = Optional.fromNullable(builder.updateTime);
        this.totalLength = Optional.fromNullable(builder.totalLength);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bookmark)) {
            return false;
        }
        Bookmark bookmark = (Bookmark) obj;
        return Objects.equal(this.startPosEpoch, bookmark.startPosEpoch) && Objects.equal(this.startPos, bookmark.startPos) && Objects.equal(this.updateTime, bookmark.updateTime) && Objects.equal(this.totalLength, bookmark.totalLength);
    }

    public int hashCode() {
        return Objects.hashCode(this.startPosEpoch, this.startPos, this.updateTime, this.totalLength);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("startPosEpoch", this.startPosEpoch).add("startPos", this.startPos).add("updateTime", this.updateTime).add("totalLength", this.totalLength).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long startPos;
        public Date startPosEpoch;
        public Long totalLength;
        public Date updateTime;

        public Bookmark build() {
            return new Bookmark(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Bookmark> {
        private final SimpleParsers.DateParser mDateTimeParser;
        private final SimpleParsers.LongParser mTimeSecondsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mTimeSecondsParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Bookmark parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Bookmark:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Bookmark parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Bookmark:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Bookmark parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Bookmark");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -949911734:
                            if (next.equals("totalLength")) {
                                b = 3;
                            }
                            break;
                        case -295931082:
                            if (next.equals("updateTime")) {
                                b = 2;
                            }
                            break;
                        case 841561367:
                            if (next.equals("startPosEpoch")) {
                                b = 0;
                            }
                            break;
                        case 1316782738:
                            if (next.equals("startPos")) {
                                b = 1;
                            }
                            break;
                    }
                    Long l = null;
                    Date date = null;
                    Long l2 = null;
                    Date date2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.startPosEpoch = date;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.mTimeSecondsParser.parse(jsonNode2);
                        }
                        builder.startPos = l2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date2 = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.updateTime = date2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            l = this.mTimeSecondsParser.parse(jsonNode2);
                        }
                        builder.totalLength = l;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Bookmark so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Bookmark parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -949911734:
                                    if (currentName.equals("totalLength")) {
                                        b = 3;
                                    }
                                    break;
                                case -295931082:
                                    if (currentName.equals("updateTime")) {
                                        b = 2;
                                    }
                                    break;
                                case 841561367:
                                    if (currentName.equals("startPosEpoch")) {
                                        b = 0;
                                    }
                                    break;
                                case 1316782738:
                                    if (currentName.equals("startPos")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            Long l = null;
                            Date date = null;
                            Long l2 = null;
                            Date date2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.startPosEpoch = date;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.mTimeSecondsParser.parse(jsonParser);
                                }
                                builder.startPos = l2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date2 = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.updateTime = date2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mTimeSecondsParser.parse(jsonParser);
                                }
                                builder.totalLength = l;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Bookmark so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
