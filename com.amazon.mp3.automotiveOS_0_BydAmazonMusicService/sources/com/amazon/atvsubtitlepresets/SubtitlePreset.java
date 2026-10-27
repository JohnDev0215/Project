package com.amazon.atvsubtitlepresets;

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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitlePreset {
    public final Optional<String> bgColor;
    public final Optional<Double> bgOpacity;
    public final Optional<Boolean> customized;
    public final Optional<String> transition;
    public final Optional<String> txtColor;
    public final Optional<String> txtEdge;
    public final Optional<String> txtFont;
    public final Optional<Double> txtOpacity;
    public final Optional<Double> txtSize;
    public final Optional<String> uri;
    public final Optional<String> winColor;
    public final Optional<Double> winOpacity;

    private SubtitlePreset(Builder builder) {
        this.winColor = Optional.fromNullable(builder.winColor);
        this.uri = Optional.fromNullable(builder.uri);
        this.txtColor = Optional.fromNullable(builder.txtColor);
        this.winOpacity = Optional.fromNullable(builder.winOpacity);
        this.txtFont = Optional.fromNullable(builder.txtFont);
        this.bgOpacity = Optional.fromNullable(builder.bgOpacity);
        this.customized = Optional.fromNullable(builder.customized);
        this.txtOpacity = Optional.fromNullable(builder.txtOpacity);
        this.txtEdge = Optional.fromNullable(builder.txtEdge);
        this.bgColor = Optional.fromNullable(builder.bgColor);
        this.transition = Optional.fromNullable(builder.transition);
        this.txtSize = Optional.fromNullable(builder.txtSize);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubtitlePreset)) {
            return false;
        }
        SubtitlePreset subtitlePreset = (SubtitlePreset) obj;
        return Objects.equal(this.winColor, subtitlePreset.winColor) && Objects.equal(this.uri, subtitlePreset.uri) && Objects.equal(this.txtColor, subtitlePreset.txtColor) && Objects.equal(this.winOpacity, subtitlePreset.winOpacity) && Objects.equal(this.txtFont, subtitlePreset.txtFont) && Objects.equal(this.bgOpacity, subtitlePreset.bgOpacity) && Objects.equal(this.customized, subtitlePreset.customized) && Objects.equal(this.txtOpacity, subtitlePreset.txtOpacity) && Objects.equal(this.txtEdge, subtitlePreset.txtEdge) && Objects.equal(this.bgColor, subtitlePreset.bgColor) && Objects.equal(this.transition, subtitlePreset.transition) && Objects.equal(this.txtSize, subtitlePreset.txtSize);
    }

    public int hashCode() {
        return Objects.hashCode(this.winColor, this.uri, this.txtColor, this.winOpacity, this.txtFont, this.bgOpacity, this.customized, this.txtOpacity, this.txtEdge, this.bgColor, this.transition, this.txtSize);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("winColor", this.winColor).add("uri", this.uri).add("txtColor", this.txtColor).add("winOpacity", this.winOpacity).add("txtFont", this.txtFont).add("bgOpacity", this.bgOpacity).add("customized", this.customized).add("txtOpacity", this.txtOpacity).add("txtEdge", this.txtEdge).add("bgColor", this.bgColor).add("transition", this.transition).add("txtSize", this.txtSize).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String bgColor;
        public Double bgOpacity;
        public Boolean customized;
        public String transition;
        public String txtColor;
        public String txtEdge;
        public String txtFont;
        public Double txtOpacity;
        public Double txtSize;
        public String uri;
        public String winColor;
        public Double winOpacity;

        public SubtitlePreset build() {
            return new SubtitlePreset(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SubtitlePreset> {
        private final SimpleParsers.StringParser mbgColorParser;
        private final SimpleParsers.DoubleParser mbgOpacityParser;
        private final SimpleParsers.BooleanParser mcustomizedParser;
        private final SimpleParsers.StringParser mtransitionParser;
        private final SimpleParsers.StringParser mtxtColorParser;
        private final SimpleParsers.StringParser mtxtEdgeParser;
        private final SimpleParsers.StringParser mtxtFontParser;
        private final SimpleParsers.DoubleParser mtxtOpacityParser;
        private final SimpleParsers.DoubleParser mtxtSizeParser;
        private final SimpleParsers.StringParser muriParser;
        private final SimpleParsers.StringParser mwinColorParser;
        private final SimpleParsers.DoubleParser mwinOpacityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.DoubleParser doubleParser = SimpleParsers.DoubleParser.INSTANCE;
            this.mwinOpacityParser = doubleParser;
            this.mbgOpacityParser = doubleParser;
            this.mtxtOpacityParser = doubleParser;
            this.mtxtSizeParser = doubleParser;
            this.mcustomizedParser = SimpleParsers.BooleanParser.INSTANCE;
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mwinColorParser = stringParser;
            this.muriParser = stringParser;
            this.mtxtColorParser = stringParser;
            this.mtxtFontParser = stringParser;
            this.mtxtEdgeParser = stringParser;
            this.mbgColorParser = stringParser;
            this.mtransitionParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SubtitlePreset parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubtitlePreset:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SubtitlePreset parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubtitlePreset:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SubtitlePreset parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            Double d;
            String str4;
            Double d2;
            Boolean bool;
            Double d3;
            String str5;
            String str6;
            String str7;
            Double d4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SubtitlePreset");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1724158635:
                            if (next.equals("transition")) {
                                b = 10;
                            }
                            break;
                        case -1581056895:
                            if (next.equals("customized")) {
                                b = 6;
                            }
                            break;
                        case -1518593018:
                            if (next.equals("bgOpacity")) {
                                b = 5;
                            }
                            break;
                        case -1479900461:
                            if (next.equals("txtColor")) {
                                b = 2;
                            }
                            break;
                        case -1145864985:
                            if (next.equals("winColor")) {
                                b = 0;
                            }
                            break;
                        case -878973875:
                            if (next.equals("txtEdge")) {
                                b = 8;
                            }
                            break;
                        case -878933281:
                            if (next.equals("txtFont")) {
                                b = 4;
                            }
                            break;
                        case -878551407:
                            if (next.equals("txtSize")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -204859874:
                            if (next.equals("bgColor")) {
                                b = 9;
                            }
                            break;
                        case 116076:
                            if (next.equals("uri")) {
                                b = 1;
                            }
                            break;
                        case 413594767:
                            if (next.equals("winOpacity")) {
                                b = 3;
                            }
                            break;
                        case 1528049531:
                            if (next.equals("txtOpacity")) {
                                b = 7;
                            }
                            break;
                    }
                    str = null;
                    d4 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    d3 = null;
                    bool = null;
                    d2 = null;
                    str4 = null;
                    d = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubtitlePreset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mwinColorParser.parse(jsonNode2);
                        }
                        builder.winColor = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.muriParser.parse(jsonNode2);
                        }
                        builder.uri = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mtxtColorParser.parse(jsonNode2);
                        }
                        builder.txtColor = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            d = this.mwinOpacityParser.parse(jsonNode2);
                        }
                        builder.winOpacity = d;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mtxtFontParser.parse(jsonNode2);
                        }
                        builder.txtFont = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            d2 = this.mbgOpacityParser.parse(jsonNode2);
                        }
                        builder.bgOpacity = d2;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            bool = this.mcustomizedParser.parse(jsonNode2);
                        }
                        builder.customized = bool;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            d3 = this.mtxtOpacityParser.parse(jsonNode2);
                        }
                        builder.txtOpacity = d3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mtxtEdgeParser.parse(jsonNode2);
                        }
                        builder.txtEdge = str5;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mbgColorParser.parse(jsonNode2);
                        }
                        builder.bgColor = str6;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mtransitionParser.parse(jsonNode2);
                        }
                        builder.transition = str7;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            d4 = this.mtxtSizeParser.parse(jsonNode2);
                        }
                        builder.txtSize = d4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubtitlePreset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SubtitlePreset parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1724158635:
                                    if (currentName.equals("transition")) {
                                        b = 10;
                                    }
                                    break;
                                case -1581056895:
                                    if (currentName.equals("customized")) {
                                        b = 6;
                                    }
                                    break;
                                case -1518593018:
                                    if (currentName.equals("bgOpacity")) {
                                        b = 5;
                                    }
                                    break;
                                case -1479900461:
                                    if (currentName.equals("txtColor")) {
                                        b = 2;
                                    }
                                    break;
                                case -1145864985:
                                    if (currentName.equals("winColor")) {
                                        b = 0;
                                    }
                                    break;
                                case -878973875:
                                    if (currentName.equals("txtEdge")) {
                                        b = 8;
                                    }
                                    break;
                                case -878933281:
                                    if (currentName.equals("txtFont")) {
                                        b = 4;
                                    }
                                    break;
                                case -878551407:
                                    if (currentName.equals("txtSize")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -204859874:
                                    if (currentName.equals("bgColor")) {
                                        b = 9;
                                    }
                                    break;
                                case 116076:
                                    if (currentName.equals("uri")) {
                                        b = 1;
                                    }
                                    break;
                                case 413594767:
                                    if (currentName.equals("winOpacity")) {
                                        b = 3;
                                    }
                                    break;
                                case 1528049531:
                                    if (currentName.equals("txtOpacity")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            String str = null;
                            Double d = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            Double d2 = null;
                            Boolean bool = null;
                            Double d3 = null;
                            String str5 = null;
                            Double d4 = null;
                            String str6 = null;
                            String str7 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mwinColorParser.parse(jsonParser);
                                    }
                                    builder.winColor = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.muriParser.parse(jsonParser);
                                    }
                                    builder.uri = str7;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mtxtColorParser.parse(jsonParser);
                                    }
                                    builder.txtColor = str6;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        d4 = this.mwinOpacityParser.parse(jsonParser);
                                    }
                                    builder.winOpacity = d4;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mtxtFontParser.parse(jsonParser);
                                    }
                                    builder.txtFont = str5;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        d3 = this.mbgOpacityParser.parse(jsonParser);
                                    }
                                    builder.bgOpacity = d3;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mcustomizedParser.parse(jsonParser);
                                    }
                                    builder.customized = bool;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        d2 = this.mtxtOpacityParser.parse(jsonParser);
                                    }
                                    builder.txtOpacity = d2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mtxtEdgeParser.parse(jsonParser);
                                    }
                                    builder.txtEdge = str4;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mbgColorParser.parse(jsonParser);
                                    }
                                    builder.bgColor = str3;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mtransitionParser.parse(jsonParser);
                                    }
                                    builder.transition = str2;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        d = this.mtxtSizeParser.parse(jsonParser);
                                    }
                                    builder.txtSize = d;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubtitlePreset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubtitlePreset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
