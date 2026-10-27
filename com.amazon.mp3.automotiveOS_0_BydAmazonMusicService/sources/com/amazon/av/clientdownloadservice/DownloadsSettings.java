package com.amazon.av.clientdownloadservice;

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
public class DownloadsSettings {
    public final Optional<Boolean> autoDownloads;
    public final Optional<Integer> autoDownloadsCount;
    public final Optional<Boolean> deleteWatchedDownloads;

    private DownloadsSettings(Builder builder) {
        this.autoDownloads = Optional.fromNullable(builder.autoDownloads);
        this.deleteWatchedDownloads = Optional.fromNullable(builder.deleteWatchedDownloads);
        this.autoDownloadsCount = Optional.fromNullable(builder.autoDownloadsCount);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadsSettings)) {
            return false;
        }
        DownloadsSettings downloadsSettings = (DownloadsSettings) obj;
        return Objects.equal(this.autoDownloads, downloadsSettings.autoDownloads) && Objects.equal(this.deleteWatchedDownloads, downloadsSettings.deleteWatchedDownloads) && Objects.equal(this.autoDownloadsCount, downloadsSettings.autoDownloadsCount);
    }

    public int hashCode() {
        return Objects.hashCode(this.autoDownloads, this.deleteWatchedDownloads, this.autoDownloadsCount);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("autoDownloads", this.autoDownloads).add("deleteWatchedDownloads", this.deleteWatchedDownloads).add("autoDownloadsCount", this.autoDownloadsCount).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean autoDownloads;
        public Integer autoDownloadsCount;
        public Boolean deleteWatchedDownloads;

        public DownloadsSettings build() {
            return new DownloadsSettings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DownloadsSettings> {
        private final SimpleParsers.BooleanParser mboolTypeParser;
        private final SimpleParsers.IntegerParser mintTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mboolTypeParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mintTypeParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DownloadsSettings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadsSettings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DownloadsSettings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadsSettings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DownloadsSettings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DownloadsSettings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1596283224) {
                        if (iHashCode != -1547600269) {
                            if (iHashCode == 218071036 && next.equals("autoDownloads")) {
                                b = 0;
                            }
                        } else if (next.equals("autoDownloadsCount")) {
                            b = 2;
                        }
                    } else if (next.equals("deleteWatchedDownloads")) {
                        b = 1;
                    }
                    Integer num = null;
                    Boolean bool = null;
                    Boolean bool2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mboolTypeParser.parse(jsonNode2);
                        }
                        builder.autoDownloads = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mboolTypeParser.parse(jsonNode2);
                        }
                        builder.deleteWatchedDownloads = bool2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            num = this.mintTypeParser.parse(jsonNode2);
                        }
                        builder.autoDownloadsCount = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DownloadsSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DownloadsSettings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1596283224) {
                                if (iHashCode != -1547600269) {
                                    if (iHashCode == 218071036 && currentName.equals("autoDownloads")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("autoDownloadsCount")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("deleteWatchedDownloads")) {
                                b = 1;
                            }
                            Integer num = null;
                            Boolean bool = null;
                            Boolean bool2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mboolTypeParser.parse(jsonParser);
                                }
                                builder.autoDownloads = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool2 = this.mboolTypeParser.parse(jsonParser);
                                }
                                builder.deleteWatchedDownloads = bool2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mintTypeParser.parse(jsonParser);
                                }
                                builder.autoDownloadsCount = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DownloadsSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
