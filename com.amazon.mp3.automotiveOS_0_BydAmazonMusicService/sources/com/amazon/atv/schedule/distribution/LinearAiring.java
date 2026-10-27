package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.LinearProgram;
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
public class LinearAiring {
    public final Optional<ImmutableList<String>> airingAttributes;
    public final Optional<String> airingId;
    public final long endTime;
    public final Optional<LinearProgram> program;
    public final long startTime;

    private LinearAiring(Builder builder) {
        this.airingAttributes = Optional.fromNullable(builder.airingAttributes);
        this.startTime = builder.startTime;
        this.airingId = Optional.fromNullable(builder.airingId);
        this.endTime = builder.endTime;
        this.program = Optional.fromNullable(builder.program);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearAiring)) {
            return false;
        }
        LinearAiring linearAiring = (LinearAiring) obj;
        return Objects.equal(this.airingAttributes, linearAiring.airingAttributes) && Objects.equal(Long.valueOf(this.startTime), Long.valueOf(linearAiring.startTime)) && Objects.equal(this.airingId, linearAiring.airingId) && Objects.equal(Long.valueOf(this.endTime), Long.valueOf(linearAiring.endTime)) && Objects.equal(this.program, linearAiring.program);
    }

    public int hashCode() {
        return Objects.hashCode(this.airingAttributes, Long.valueOf(this.startTime), this.airingId, Long.valueOf(this.endTime), this.program);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("airingAttributes", this.airingAttributes).add("startTime", this.startTime).add("airingId", this.airingId).add("endTime", this.endTime).add("program", this.program).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> airingAttributes;
        public String airingId;
        public long endTime;
        public LinearProgram program;
        public long startTime;

        public LinearAiring build() {
            return new LinearAiring(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearAiring> {
        private final SimpleParsers.StringParser mAiringIdParser;
        private final ListParser<String> mLinearAiringAttributeListParser;
        private final LinearProgram.Parser mLinearProgramParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mLinearAiringAttributeListParser = ListParser.newParser(stringParser);
            this.mLinearProgramParser = new LinearProgram.Parser(objectMapper);
            this.mAiringIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearAiring parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearAiring:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearAiring parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearAiring:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearAiring parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearAiring");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2129294769:
                            if (next.equals("startTime")) {
                                b = 1;
                            }
                            break;
                        case -2128766513:
                            if (next.equals("airingAttributes")) {
                                b = 0;
                            }
                            break;
                        case -1607243192:
                            if (next.equals("endTime")) {
                                b = 3;
                            }
                            break;
                        case -683412077:
                            if (next.equals("airingId")) {
                                b = 2;
                            }
                            break;
                        case -309387644:
                            if (next.equals("program")) {
                                b = 4;
                            }
                            break;
                    }
                    LinearProgram linearProgram = null;
                    ImmutableList<String> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mLinearAiringAttributeListParser.parse(jsonNode2);
                        }
                        builder.airingAttributes = immutableList;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field startTime can't be null");
                        }
                        builder.startTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mAiringIdParser.parse(jsonNode2);
                        }
                        builder.airingId = str;
                    } else if (b == 3) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field endTime can't be null");
                        }
                        builder.endTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            linearProgram = this.mLinearProgramParser.parse(jsonNode2);
                        }
                        builder.program = linearProgram;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearAiring so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearAiring parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2129294769:
                                    if (currentName.equals("startTime")) {
                                        b = 1;
                                    }
                                    break;
                                case -2128766513:
                                    if (currentName.equals("airingAttributes")) {
                                        b = 0;
                                    }
                                    break;
                                case -1607243192:
                                    if (currentName.equals("endTime")) {
                                        b = 3;
                                    }
                                    break;
                                case -683412077:
                                    if (currentName.equals("airingId")) {
                                        b = 2;
                                    }
                                    break;
                                case -309387644:
                                    if (currentName.equals("program")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            LinearProgram linearProgram = null;
                            ImmutableList<String> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mLinearAiringAttributeListParser.parse(jsonParser);
                                }
                                builder.airingAttributes = immutableList;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field startTime can't be null");
                                }
                                builder.startTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mAiringIdParser.parse(jsonParser);
                                }
                                builder.airingId = str;
                            } else if (b == 3) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field endTime can't be null");
                                }
                                builder.endTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    linearProgram = this.mLinearProgramParser.parse(jsonParser);
                                }
                                builder.program = linearProgram;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearAiring so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
