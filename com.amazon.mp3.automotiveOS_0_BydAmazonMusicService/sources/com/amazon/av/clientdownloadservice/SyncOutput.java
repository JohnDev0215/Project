package com.amazon.av.clientdownloadservice;

import com.amazon.av.clientdownloadservice.OnDeviceDownloadAction;
import com.amazon.av.clientdownloadservice.autodownloadsResponseStatus;
import com.amazon.av.clientdownloadservice.responseStatus;
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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SyncOutput {
    public final Optional<autodownloadsResponseStatus> autodownloadsResponseStatus;
    public final Optional<Integer> minutesToNextSync;
    public final Optional<responseStatus> responseStatus;
    public final Optional<ImmutableList<OnDeviceDownloadAction>> results;

    private SyncOutput(Builder builder) {
        this.minutesToNextSync = Optional.fromNullable(builder.minutesToNextSync);
        this.results = Optional.fromNullable(builder.results);
        this.autodownloadsResponseStatus = Optional.fromNullable(builder.autodownloadsResponseStatus);
        this.responseStatus = Optional.fromNullable(builder.responseStatus);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyncOutput)) {
            return false;
        }
        SyncOutput syncOutput = (SyncOutput) obj;
        return Objects.equal(this.minutesToNextSync, syncOutput.minutesToNextSync) && Objects.equal(this.results, syncOutput.results) && Objects.equal(this.autodownloadsResponseStatus, syncOutput.autodownloadsResponseStatus) && Objects.equal(this.responseStatus, syncOutput.responseStatus);
    }

    public int hashCode() {
        return Objects.hashCode(this.minutesToNextSync, this.results, this.autodownloadsResponseStatus, this.responseStatus);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("minutesToNextSync", this.minutesToNextSync).add("results", this.results).add("autodownloadsResponseStatus", this.autodownloadsResponseStatus).add("responseStatus", this.responseStatus).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public autodownloadsResponseStatus autodownloadsResponseStatus;
        public Integer minutesToNextSync;
        public responseStatus responseStatus;
        public ImmutableList<OnDeviceDownloadAction> results;

        public SyncOutput build() {
            return new SyncOutput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyncOutput> {
        private final ListParser<OnDeviceDownloadAction> mOnDeviceDownloadActionListTypeParser;
        private final autodownloadsResponseStatus.Parser mautodownloadsResponseStatusParser;
        private final SimpleParsers.IntegerParser mintTypeParser;
        private final responseStatus.Parser mresponseStatusParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mresponseStatusParser = new responseStatus.Parser(objectMapper);
            this.mOnDeviceDownloadActionListTypeParser = ListParser.newParser(new OnDeviceDownloadAction.Parser(objectMapper));
            this.mautodownloadsResponseStatusParser = new autodownloadsResponseStatus.Parser(objectMapper);
            this.mintTypeParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyncOutput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyncOutput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyncOutput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyncOutput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyncOutput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyncOutput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 96459667:
                            if (next.equals("responseStatus")) {
                                b = 3;
                            }
                            break;
                        case 1084991183:
                            if (next.equals("autodownloadsResponseStatus")) {
                                b = 2;
                            }
                            break;
                        case 1097546742:
                            if (next.equals("results")) {
                                b = 1;
                            }
                            break;
                        case 2105794056:
                            if (next.equals("minutesToNextSync")) {
                                b = 0;
                            }
                            break;
                    }
                    responseStatus responsestatus = null;
                    Integer num = null;
                    ImmutableList<OnDeviceDownloadAction> immutableList = null;
                    autodownloadsResponseStatus autodownloadsresponsestatus = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            num = this.mintTypeParser.parse(jsonNode2);
                        }
                        builder.minutesToNextSync = num;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mOnDeviceDownloadActionListTypeParser.parse(jsonNode2);
                        }
                        builder.results = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            autodownloadsresponsestatus = this.mautodownloadsResponseStatusParser.parse(jsonNode2);
                        }
                        builder.autodownloadsResponseStatus = autodownloadsresponsestatus;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            responsestatus = this.mresponseStatusParser.parse(jsonNode2);
                        }
                        builder.responseStatus = responsestatus;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyncOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyncOutput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 96459667:
                                    if (currentName.equals("responseStatus")) {
                                        b = 3;
                                    }
                                    break;
                                case 1084991183:
                                    if (currentName.equals("autodownloadsResponseStatus")) {
                                        b = 2;
                                    }
                                    break;
                                case 1097546742:
                                    if (currentName.equals("results")) {
                                        b = 1;
                                    }
                                    break;
                                case 2105794056:
                                    if (currentName.equals("minutesToNextSync")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            responseStatus responsestatus = null;
                            Integer num = null;
                            ImmutableList<OnDeviceDownloadAction> immutableList = null;
                            autodownloadsResponseStatus autodownloadsresponsestatus = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mintTypeParser.parse(jsonParser);
                                }
                                builder.minutesToNextSync = num;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mOnDeviceDownloadActionListTypeParser.parse(jsonParser);
                                }
                                builder.results = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    autodownloadsresponsestatus = this.mautodownloadsResponseStatusParser.parse(jsonParser);
                                }
                                builder.autodownloadsResponseStatus = autodownloadsresponsestatus;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    responsestatus = this.mresponseStatusParser.parse(jsonParser);
                                }
                                builder.responseStatus = responsestatus;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyncOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
