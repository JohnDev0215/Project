package com.amazon.atv.discovery;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PresentationData {
    public final Optional<TemplateIdEnum> templateId;
    public final Optional<Date> time;
    public final String version;

    private PresentationData(Builder builder) {
        this.version = (String) Preconditions.checkNotNull(builder.version, "Unexpected null field: version");
        this.templateId = Optional.fromNullable(builder.templateId);
        this.time = Optional.fromNullable(builder.time);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PresentationData)) {
            return false;
        }
        PresentationData presentationData = (PresentationData) obj;
        return Objects.equal(this.version, presentationData.version) && Objects.equal(this.templateId, presentationData.templateId) && Objects.equal(this.time, presentationData.time);
    }

    public int hashCode() {
        return Objects.hashCode(this.version, this.templateId, this.time);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("templateId", this.templateId).add("time", this.time).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public TemplateIdEnum templateId;
        public Date time;
        public String version;

        public PresentationData build() {
            return new PresentationData(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PresentationData> {
        private final SimpleParsers.StringParser mStringParser;
        private final EnumParser<TemplateIdEnum> mTemplateIdEnumParser;
        private final SimpleParsers.DateParser mTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTemplateIdEnumParser = EnumParser.newParser(TemplateIdEnum.class);
            this.mTimestampParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PresentationData parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PresentationData:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PresentationData parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PresentationData:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PresentationData parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PresentationData");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3560141) {
                        if (iHashCode != 351608024) {
                            if (iHashCode == 1304010549 && next.equals("templateId")) {
                                b = 1;
                            }
                        } else if (next.equals("version")) {
                            b = 0;
                        }
                    } else if (next.equals("time")) {
                        b = 2;
                    }
                    Date date = null;
                    String str = null;
                    TemplateIdEnum templateIdEnum = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.version = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            templateIdEnum = (TemplateIdEnum) this.mTemplateIdEnumParser.parse(jsonNode2);
                        }
                        builder.templateId = templateIdEnum;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.time = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PresentationData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        @Nonnull
        private PresentationData parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3560141) {
                                if (iHashCode != 351608024) {
                                    if (iHashCode == 1304010549 && currentName.equals("templateId")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("version")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("time")) {
                                b = 2;
                            }
                            Date date = null;
                            String str = null;
                            TemplateIdEnum templateIdEnum = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.version = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    templateIdEnum = (TemplateIdEnum) this.mTemplateIdEnumParser.parse(jsonParser);
                                }
                                builder.templateId = templateIdEnum;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.time = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PresentationData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
