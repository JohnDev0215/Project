package com.amazon.atvplaybackoptimizationservice;

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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GeoInfo {
    public final Optional<String> asn;
    public final Optional<String> city;
    public final Optional<String> country;
    public final Optional<String> dma;
    public final Optional<String> msa;
    public final Optional<String> region;
    public final Optional<String> state;
    public final Optional<String> zip;

    private GeoInfo(Builder builder) {
        this.asn = Optional.fromNullable(builder.asn);
        this.state = Optional.fromNullable(builder.state);
        this.msa = Optional.fromNullable(builder.msa);
        this.region = Optional.fromNullable(builder.region);
        this.zip = Optional.fromNullable(builder.zip);
        this.city = Optional.fromNullable(builder.city);
        this.dma = Optional.fromNullable(builder.dma);
        this.country = Optional.fromNullable(builder.country);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeoInfo)) {
            return false;
        }
        GeoInfo geoInfo = (GeoInfo) obj;
        return Objects.equal(this.asn, geoInfo.asn) && Objects.equal(this.state, geoInfo.state) && Objects.equal(this.msa, geoInfo.msa) && Objects.equal(this.region, geoInfo.region) && Objects.equal(this.zip, geoInfo.zip) && Objects.equal(this.city, geoInfo.city) && Objects.equal(this.dma, geoInfo.dma) && Objects.equal(this.country, geoInfo.country);
    }

    public int hashCode() {
        return Objects.hashCode(this.asn, this.state, this.msa, this.region, this.zip, this.city, this.dma, this.country);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("asn", this.asn).add("state", this.state).add("msa", this.msa).add("region", this.region).add("zip", this.zip).add("city", this.city).add("dma", this.dma).add("country", this.country).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String asn;
        public String city;
        public String country;
        public String dma;
        public String msa;
        public String region;
        public String state;
        public String zip;

        public GeoInfo build() {
            return new GeoInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GeoInfo> {
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GeoInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GeoInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GeoInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GeoInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GeoInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GeoInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -934795532:
                            if (next.equals("region")) {
                                b = 3;
                            }
                            break;
                        case 96892:
                            if (next.equals("asn")) {
                                b = 0;
                            }
                            break;
                        case 99576:
                            if (next.equals("dma")) {
                                b = 6;
                            }
                            break;
                        case 108411:
                            if (next.equals("msa")) {
                                b = 2;
                            }
                            break;
                        case 120609:
                            if (next.equals("zip")) {
                                b = 4;
                            }
                            break;
                        case 3053931:
                            if (next.equals("city")) {
                                b = 5;
                            }
                            break;
                        case 109757585:
                            if (next.equals("state")) {
                                b = 1;
                            }
                            break;
                        case 957831062:
                            if (next.equals("country")) {
                                b = 7;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GeoInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.asn = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.state = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.msa = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.region = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.zip = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.city = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.dma = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.country = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GeoInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GeoInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -934795532:
                                    if (currentName.equals("region")) {
                                        b = 3;
                                    }
                                    break;
                                case 96892:
                                    if (currentName.equals("asn")) {
                                        b = 0;
                                    }
                                    break;
                                case 99576:
                                    if (currentName.equals("dma")) {
                                        b = 6;
                                    }
                                    break;
                                case 108411:
                                    if (currentName.equals("msa")) {
                                        b = 2;
                                    }
                                    break;
                                case 120609:
                                    if (currentName.equals("zip")) {
                                        b = 4;
                                    }
                                    break;
                                case 3053931:
                                    if (currentName.equals("city")) {
                                        b = 5;
                                    }
                                    break;
                                case 109757585:
                                    if (currentName.equals("state")) {
                                        b = 1;
                                    }
                                    break;
                                case 957831062:
                                    if (currentName.equals("country")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.asn = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.state = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.msa = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.region = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.zip = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.city = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.dma = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.country = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GeoInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GeoInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
