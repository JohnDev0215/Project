package com.amazon.atvbookmarking;

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
public class TitleBookmark {

    @Deprecated
    public final Optional<String> asin;
    public final Optional<Date> epochUtc;

    @Deprecated
    public final Optional<String> parentAsin;
    public final Optional<Long> timecodeSec;
    public final Optional<String> titleId;

    @Deprecated
    public final Optional<Long> titleLengthSec;
    public final Optional<Date> updateTime;

    private TitleBookmark(Builder builder) {
        this.asin = Optional.fromNullable(builder.asin);
        this.epochUtc = Optional.fromNullable(builder.epochUtc);
        this.parentAsin = Optional.fromNullable(builder.parentAsin);
        this.updateTime = Optional.fromNullable(builder.updateTime);
        this.timecodeSec = Optional.fromNullable(builder.timecodeSec);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.titleLengthSec = Optional.fromNullable(builder.titleLengthSec);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleBookmark)) {
            return false;
        }
        TitleBookmark titleBookmark = (TitleBookmark) obj;
        return Objects.equal(this.asin, titleBookmark.asin) && Objects.equal(this.epochUtc, titleBookmark.epochUtc) && Objects.equal(this.parentAsin, titleBookmark.parentAsin) && Objects.equal(this.updateTime, titleBookmark.updateTime) && Objects.equal(this.timecodeSec, titleBookmark.timecodeSec) && Objects.equal(this.titleId, titleBookmark.titleId) && Objects.equal(this.titleLengthSec, titleBookmark.titleLengthSec);
    }

    public int hashCode() {
        return Objects.hashCode(this.asin, this.epochUtc, this.parentAsin, this.updateTime, this.timecodeSec, this.titleId, this.titleLengthSec);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("asin", this.asin).add("epochUtc", this.epochUtc).add("parentAsin", this.parentAsin).add("updateTime", this.updateTime).add("timecodeSec", this.timecodeSec).add("titleId", this.titleId).add("titleLengthSec", this.titleLengthSec).toString();
    }

    @NotThreadSafe
    public static class Builder {

        @Deprecated
        public String asin;
        public Date epochUtc;

        @Deprecated
        public String parentAsin;
        public Long timecodeSec;
        public String titleId;

        @Deprecated
        public Long titleLengthSec;
        public Date updateTime;

        public TitleBookmark build() {
            return new TitleBookmark(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleBookmark> {
        private final SimpleParsers.DateParser mDateParser;
        private final SimpleParsers.LongParser mLongParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDateParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleBookmark parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleBookmark:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleBookmark parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleBookmark:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleBookmark parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            Date date;
            String str2;
            Date date2;
            Long l;
            String str3;
            Long l2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleBookmark");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 5;
                            }
                            break;
                        case -476170149:
                            if (next.equals("epochUtc")) {
                                b = 1;
                            }
                            break;
                        case -295931082:
                            if (next.equals("updateTime")) {
                                b = 3;
                            }
                            break;
                        case -245240671:
                            if (next.equals("parentAsin")) {
                                b = 2;
                            }
                            break;
                        case -19034185:
                            if (next.equals("timecodeSec")) {
                                b = 4;
                            }
                            break;
                        case 3003607:
                            if (next.equals("asin")) {
                                b = 0;
                            }
                            break;
                        case 1268545555:
                            if (next.equals("titleLengthSec")) {
                                b = 6;
                            }
                            break;
                    }
                    str = null;
                    l2 = null;
                    str3 = null;
                    l = null;
                    date2 = null;
                    str2 = null;
                    date = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleBookmark so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.asin = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            date = this.mDateParser.parse(jsonNode2);
                        }
                        builder.epochUtc = date;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.parentAsin = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            date2 = this.mDateParser.parse(jsonNode2);
                        }
                        builder.updateTime = date2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.timecodeSec = l;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleId = str3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            l2 = this.mLongParser.parse(jsonNode2);
                        }
                        builder.titleLengthSec = l2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleBookmark so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleBookmark parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 5;
                                    }
                                    break;
                                case -476170149:
                                    if (currentName.equals("epochUtc")) {
                                        b = 1;
                                    }
                                    break;
                                case -295931082:
                                    if (currentName.equals("updateTime")) {
                                        b = 3;
                                    }
                                    break;
                                case -245240671:
                                    if (currentName.equals("parentAsin")) {
                                        b = 2;
                                    }
                                    break;
                                case -19034185:
                                    if (currentName.equals("timecodeSec")) {
                                        b = 4;
                                    }
                                    break;
                                case 3003607:
                                    if (currentName.equals("asin")) {
                                        b = 0;
                                    }
                                    break;
                                case 1268545555:
                                    if (currentName.equals("titleLengthSec")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            String str = null;
                            Long l = null;
                            String str2 = null;
                            Long l2 = null;
                            Date date = null;
                            String str3 = null;
                            Date date2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.asin = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        date2 = this.mDateParser.parse(jsonParser);
                                    }
                                    builder.epochUtc = date2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.parentAsin = str3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        date = this.mDateParser.parse(jsonParser);
                                    }
                                    builder.updateTime = date;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l2 = this.mLongParser.parse(jsonParser);
                                    }
                                    builder.timecodeSec = l2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.titleId = str2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mLongParser.parse(jsonParser);
                                    }
                                    builder.titleLengthSec = l;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleBookmark so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleBookmark so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
